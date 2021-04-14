package com.whale.boot.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whale.boot.web.bean.Article;
import com.whale.boot.web.bean.Category;
import com.whale.boot.web.bean.vo.TimeLineVo;
import com.whale.boot.web.service.ArticleService;
import com.whale.boot.web.service.CategoryService;
import com.whale.boot.web.utils.PageBounds;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author litian
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private CategoryService categoryService;

    /**
     * 搜索标题，描述，内容
     *
     * @param keywords
     * @param currentPage 当前页
     * @param pageSize    分页大小
     * @return result
     */
    @GetMapping("/search")
    public HashMap<String, Object> search(String keywords, Integer currentPage, Integer pageSize) {
        HashMap<String, Object> result = new HashMap<>();
        List<Article> articleList = articleService.getArticleList(keywords, currentPage, pageSize);
        if (null != articleList) {
            PageBounds<Article> pageBounds = new PageBounds(currentPage, articleList.size(), pageSize);
            pageBounds.setPageList(articleList);
            result.put("data", pageBounds);
        } else {
            result.put("data", null);
        }
        return result;
    }


    /**
     * 发表文章
     */
    @PostMapping("/save")
    public HashMap<String, Object> saveArticle(@RequestBody Article article) {
        HashMap<String, Object> result = new HashMap<>();
        articleService.updateArticle(article);
        return result;
    }

    /**
     * 修改文章
     */
    @PutMapping("/update")
    public HashMap<String, Object> updateArticle(@RequestBody Article article) {
        HashMap<String, Object> result = new HashMap<>();
        articleService.updateArticle(article);
        return result;
    }

    /**
     * 删除文章
     */
    @DeleteMapping("/delete")
    public HashMap<String, Object> deleteArticle(Integer[] ids) {
        HashMap<String, Object> result = new HashMap<>();
        articleService.deleteArticle(Arrays.asList(ids));
        return result;
    }

    /**
     * 分页查询
     */
    @GetMapping("/list")
    public HashMap<String, Object> getArticlePage(Article article, Integer pageSize, Integer currentPage) {
        HashMap<String, Object> result = new HashMap<>();
        if (pageSize != null && currentPage != null) {
            PageHelper.startPage(currentPage, pageSize);
            List<Article> articleList = articleService.getArticleByPage(article);
            PageInfo<Article> pageInfo = new PageInfo<>(articleList);
            result.put("data", pageInfo);
        } else {
            List<Article> articleList = articleService.getArticleByPage(article);
            result.put("data", articleList);
        }
        return result;
    }


    /**
     * 根据id查询文章具体内容
     *
     * @param id 文章id
     * @return result
     */
    @GetMapping(value = "/{id}")
    public HashMap<String, Object> searchById(@PathVariable(value = "id") int id) {
        HashMap<String, Object> result = new HashMap<>();
        Article article = articleService.searchById(id);
        result.put("data", article);
        return result;
    }

    /**
     * 根据id查询文章具体内容
     *
     * @return result
     */
    @GetMapping(value = "/searchArticle")
    public HashMap<String, Object> searchByArticleIdAndManagerId(int articleId, int managerId) {
        HashMap<String, Object> result = new HashMap<>();
        Article article = articleService.searchByArticleIdAndManagerId(articleId, managerId);
        result.put("data", article);
        return result;
    }

    /**
     * 查询标签墙
     *
     * @return 标签内容
     */
    @GetMapping(value = "/searchTag")
    public HashMap<String, Object> searchTag() {
        HashMap<String, Object> result = new HashMap<>();
        // 对所有索引进行搜索
        List<String> string = articleService.searchTag();
        result.put("data", string);
        return result;
    }

    /**
     * 文章归档
     */
    @GetMapping(value = "/timeLine")
    public HashMap<String, Object> searchTimeLine() {
        HashMap<String, Object> result = new HashMap<>();
        // 对所有索引进行搜索
        List<TimeLineVo> timeLine = articleService.searchTimeLine();
        result.put("data", timeLine);
        return result;
    }

    /**
     * 获取全部的文章类型
     *
     * @return result
     */
    @GetMapping("getAllArticleType")
    public HashMap<String, Object> getAllArticleType() {
        HashMap<String, Object> result = new HashMap<>();
        List<Category> lists = categoryService.findAll();
        List<HashMap<String, Object>> hashMaps = new ArrayList<>();
        for (Category category : lists) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", category.getId());
            hashMap.put("name", category.getName());
            hashMaps.add(hashMap);
        }
        result.put("data", hashMaps);
        return result;
    }

}
