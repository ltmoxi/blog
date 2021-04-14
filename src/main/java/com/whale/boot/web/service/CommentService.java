package com.whale.boot.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.whale.boot.web.bean.Comment;
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
public interface CommentService extends IService<Comment> {

    /**
     * @param comment 新增评论
     * @throws BusinessException 异常处理
     */
    void saveComment(Comment comment) throws BusinessException;

    /**
     * 删除评论
     *
     * @param id                 通过id删除
     * @param articleByManagerId 文章作者id
     * @throws BusinessException 异常处理
     */
    void deleteComment(Integer id, Integer articleByManagerId) throws BusinessException;

    /**
     * 通过文章id获取评论
     *
     * @param articleId 文章id
     * @return List<Comment>
     */
    List<Comment> getCommentsByArticleId(Integer articleId) throws BusinessException;

    /**
     * 评论列表
     *
     * @param comment
     * @return
     */
    List<Comment> getCommentPage(Comment comment) throws BusinessException;

    /**
     * 通过id删除
     *
     * @param asList
     * @throws BusinessException
     */
    void delComment(List<Integer> ids) throws BusinessException;
}
