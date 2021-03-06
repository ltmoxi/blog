package com.whale.boot.web.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.whale.boot.web.bean.Article;
import com.whale.boot.web.bean.Manager;
import com.whale.boot.web.bean.vo.TimeLineVo;
import com.whale.boot.web.mapper.ArticleMapper;
import com.whale.boot.web.repository.ArticleRepository;
import com.whale.boot.web.service.ArticleService;
import com.whale.boot.web.utils.BusinessException;
import com.whale.boot.web.utils.ConstUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author litian
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private ArticleRepository articleRepository;


    @Override
    public void updateArticle(Article article) throws BusinessException {
        try {
            Manager manager = (Manager) SecurityUtils.getSubject().getPrincipal();
            if (manager == null) {
                throw new BusinessException("?????????????????????");
            } else {
                article.setManagerName(manager.getName());
            }

            //????????????
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Article::getArticleName, article.getArticleName())
                    .ne(article.getId() != null, Article::getId, article.getId());
            int count = articleMapper.selectCount(queryWrapper);

            if (count > 0) {
                throw new BusinessException("???????????????????????????");
            }

            if (null != article.getId()) {
                //??????
                articleMapper.updateById(article);
            } else {
                //??????
                articleMapper.insert(article);
            }
            if (ConstUtil.ARTICLE_ID != article.getId()) {
                //?????????????????????????????????id???1??????????????????
                articleRepository.save(article);
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        }
    }

    @Override
    public void deleteArticle(List<Integer> ids) throws BusinessException {
        try {
            if (ids.contains(ConstUtil.ARTICLE_ID)) {
                throw new BusinessException("????????????????????????");
            }
            List<Article> articles = articleMapper.selectBatchIds(ids);
            //????????????
            articleRepository.deleteAll(articles);
            //????????????
            this.removeByIds(ids);
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        }
    }

    @Override
    public List<Article> getArticleByPage(Article article) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(null != article.getArticleType(), Article::getArticleType, article.getArticleType())
                .like(StringUtils.isNotBlank(article.getArticleName()), Article::getArticleName, article.getArticleName())
                .ne(Article::getId, ConstUtil.ARTICLE_ID)
                .orderByDesc(article.isRecommend(), Article::getArticleReadCount)
                .orderByDesc(article.isFavorite(), Article::getArticleStarNum)
                .orderByDesc(article.isCommentMost(), Article::getArticleConNum)
                .orderByDesc(Article::getCreateTime);
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public Article searchById(int id) {
        Article article = articleMapper.selectById(id);
        //???????????????
        article.setArticleReadCount(article.getArticleReadCount() + 1);
        articleMapper.updateById(article);
        return article;
    }

    @Override
    public List<Article> getArticleList(String keywords, Integer currentPage, Integer pageSize) {
        //?????????es??????
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        //???????????????????????????,boost??????????????????????????????????????????
        builder.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.fuzzyQuery("articleTag", keywords).boost(1))
                .should(QueryBuilders.fuzzyQuery("articleRemark", keywords).boost(1))
                .should(QueryBuilders.fuzzyQuery("articleContent", keywords).boost(2))
                .should(QueryBuilders.matchQuery("articleName", keywords).boost(3))
                .should(QueryBuilders.matchQuery("managerName", keywords).boost(1)));

        //?????????????????????????????????
        builder.withHighlightFields(new HighlightBuilder.Field("articleName"), new HighlightBuilder.Field("articleContent"), new HighlightBuilder.Field("articleRemark"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red;font-weight:bold'>").postTags("</span>")).build();

        //??????
        builder.withPageable(PageRequest.of(currentPage - 1, pageSize));

        AggregatedPage<Article> page = elasticsearchTemplate.queryForPage(builder.build(), Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                ArrayList<Article> list = new ArrayList<>();
                SearchHits hits = response.getHits();
                for (SearchHit searchHit : hits) {
                    if (hits.getHits().length <= 0) {
                        return null;
                    }
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    Integer id = (Integer) sourceAsMap.get("id");
                    String articleName = (String) sourceAsMap.get("articleName");
                    String articleHeadPic = (String) sourceAsMap.get("articleHeadPic");
                    String articleTag = (String) sourceAsMap.get("articleTag");
                    String managerName = (String) sourceAsMap.get("managerName");
                    String category = (String) sourceAsMap.get("articleContent");
                    String content = (String) sourceAsMap.get("articleRemark");
                    String createTime = (String) sourceAsMap.get("createTime");
                    Article fileBean = new Article();
                    HighlightField field = searchHit.getHighlightFields().get("articleName");
                    if (field == null) {
                        fileBean.setArticleName(articleName);
                    } else {
                        fileBean.setArticleName(field.fragments()[0].toString());
                    }

                    field = searchHit.getHighlightFields().get("articleContent");
                    if (field == null) {
                        fileBean.setArticleContent(category);
                    } else {
                        fileBean.setArticleContent(field.fragments()[0].toString());
                    }

                    field = searchHit.getHighlightFields().get("articleRemark");
                    if (field == null) {
                        fileBean.setArticleRemark(content);
                    } else {
                        fileBean.setArticleRemark(field.fragments()[0].toString());
                    }

                    field = searchHit.getHighlightFields().get("articleTag");
                    if (field == null) {
                        fileBean.setArticleTag(articleTag);
                    } else {
                        fileBean.setArticleTag(field.fragments()[0].toString());
                    }

                    field = searchHit.getHighlightFields().get("managerName");
                    if (field == null) {
                        fileBean.setManagerName(managerName);
                    } else {
                        fileBean.setManagerName(field.fragments()[0].toString());
                    }

                    fileBean.setArticleHeadPic(articleHeadPic);
                    fileBean.setCreateTime(createTime);
                    fileBean.setId(id);
                    list.add(fileBean);
                }
                if (list.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) list);
                }
                return null;
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
                return null;
            }
        });
        if (page == null) {
            return null;
        }
        return page.getContent();
    }

    @Override
    public List<String> searchTag() {
        List<String> tags = articleMapper.searchTag();
        List<String> articleTags = new ArrayList<>();
        for (String str : tags) {
            ArrayList<String> strings = Lists.newArrayList(str.split(","));
            articleTags.addAll(strings);
        }
        //??????
        return articleTags.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<TimeLineVo> searchTimeLine() {
        List<Article> articles = articleMapper.searchTimeLine();
        List<TimeLineVo> lineVos = new ArrayList<>();
        //????????????
        List<String> yearTimesList = articles.stream().map(Article::getCreateTime).collect(Collectors.toList());
        //?????????????????????
        List<Integer> years = yearTimesList.stream().map(x -> DateUtil.year(DateUtil.parse(x))).collect(Collectors.toList());
        //????????????
        List<Integer> collect = years.stream().distinct().collect(Collectors.toList());
        for (Integer yearTimes : collect) {
            TimeLineVo timeLineVo = new TimeLineVo();
            //??????????????????????????????????????????
            List<Article> articleList = articles.stream().filter(item -> item.getCreateTime().contains(yearTimes.toString())).collect(Collectors.toList());
            timeLineVo.setYear(yearTimes.toString());
            timeLineVo.setArticle(articleList);
            lineVos.add(timeLineVo);
        }
        return lineVos;
    }

    @Override
    public Article searchByArticleIdAndManagerId(int articleId, int managerId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Article::getId, articleId)
                .eq(Article::getManagerId, managerId)
                .eq(Article::getArticleState, ConstUtil.OTHER_STATE);
        Article article = articleMapper.selectOne(queryWrapper);
        Manager manager = (Manager) SecurityUtils.getSubject().getPrincipal();
        if (!manager.getId().equals(article.getManagerId())) {
            throw new BusinessException("???????????????????????????????????????");
        }

        return article;
    }

}
