package com.whale.boot.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whale.boot.web.bean.Article;
import com.whale.boot.web.bean.vo.TimeLineVo;
import com.whale.boot.web.utils.BusinessException;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author litian
 * @since 2020-12-2
 */
public interface ArticleService extends IService<Article> {


    /**
     * @param article 添加文章
     * @throws BusinessException 异常提示
     */
    void updateArticle(Article article) throws BusinessException;

    /**
     * @param article 通过各个条件查询全部
     * @throws BusinessException 异常提示
     */
    List<Article> getArticleByPage(Article article) throws BusinessException;

    /**
     * @param id 通过id查询
     */
    Article searchById(int id);

    /**
     * 分页查询
     *
     * @param keywords    搜索框查询
     * @param currentPage 当前页
     * @param pageSize    分页大小
     * @return Article
     */
    List<Article> getArticleList(String keywords, Integer currentPage, Integer pageSize);


    /**
     * 查询标签墙
     *
     * @return 标签墙
     */
    List<String> searchTag();


    /**
     * 删除文章
     *
     * @param ids
     */
    void deleteArticle(List<Integer> ids) throws BusinessException;

    /**
     * 文章归档
     *
     * @return TimeLineVo
     */
    List<TimeLineVo> searchTimeLine();

    /**
     * 通过文章id和发布者名称查询
     *
     * @param articleId
     * @param managerId
     * @return
     */
    Article searchByArticleIdAndManagerId(int articleId, int managerId);
}
