package com.whale.boot.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whale.boot.web.bean.Comment;
import com.whale.boot.web.service.CommentService;
import com.whale.boot.web.utils.BusinessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author litian
 * @since 2020-12-2
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     *
     */
    @ResponseBody
    @GetMapping(value = "{articleId}")
    public HashMap<String, Object> getComment(@PathVariable(value = "articleId") int articleId) {
        HashMap<String, Object> result = new HashMap<>();
        List<Comment> comment = commentService.getCommentsByArticleId(articleId);
        result.put("data", comment);
        return result;
    }

    /**
     * 新增评论
     *
     * @param comment 评论内容
     * @return result
     */
    @ResponseBody
    @PostMapping("/save")
    public HashMap<String, Object> saveComment(Comment comment) {
        HashMap<String, Object> result = new HashMap<>();
        //新增评论
        commentService.saveComment(comment);
        return result;
    }

    /**
     * 删除评论
     *
     * @param id                 评论id
     * @param articleByManagerId 作者id
     * @return result
     */
    @ResponseBody
    @DeleteMapping("/delete")
    public HashMap<String, Object> deleteComment(Integer id, Integer articleByManagerId) {
        HashMap<String, Object> result = new HashMap<>();
        //删除评论
        commentService.deleteComment(id, articleByManagerId);
        return result;
    }

    /**
     * 分页查询
     */
    @GetMapping("/list")
    public HashMap<String, Object> getCommentPage(Comment comment, Integer pageSize, Integer currentPage) {
        HashMap<String, Object> result = new HashMap<>();
        if (pageSize != null && currentPage != null) {
            PageHelper.startPage(currentPage, pageSize);
            List<Comment> comments = commentService.getCommentPage(comment);
            PageInfo<Comment> pageInfo = new PageInfo<>(comments);
            result.put("data", pageInfo);
        } else {
            List<Comment> comments = commentService.getCommentPage(comment);
            result.put("data", comments);
        }
        return result;
    }

    /**
     * 通过id删除
     */
    @DeleteMapping("/deletes")
    public HashMap<String, Object> delComment(Integer[] ids) throws BusinessException {
        HashMap<String, Object> result = new HashMap<>();
        commentService.delComment(Arrays.asList(ids));
        return result;
    }

}
