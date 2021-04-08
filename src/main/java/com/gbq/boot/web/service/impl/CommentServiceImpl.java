package com.gbq.boot.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbq.boot.web.bean.Article;
import com.gbq.boot.web.bean.Comment;
import com.gbq.boot.web.bean.Manager;
import com.gbq.boot.web.mapper.ArticleMapper;
import com.gbq.boot.web.mapper.CommentMapper;
import com.gbq.boot.web.service.CommentService;
import com.gbq.boot.web.utils.BusinessException;
import com.gbq.boot.web.utils.TreeNodeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aqian666
 * @since 2019-09-12
 */
@Service
@Transactional(rollbackFor=BusinessException.class)
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private HttpServletRequest request;
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public void saveComment(Comment comment)throws BusinessException {
        try {
//            if (StringUtils.isEmpty(comment.getNickname())){
//                Random random=new Random();
//                comment.setNickname("游客"+random.nextInt(90)+10);
//            }
            Article article = articleMapper.selectById(comment.getArticleId());
            comment.setArticleName(article.getArticleName());
            commentMapper.insert(comment);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void deleteComment(Integer id,Integer articleByManagerId)throws BusinessException{
        try {
            //查询当前登录人,是否为文章发表人
            Manager managerByArticle = (Manager) request.getSession().getAttribute("managerInfo"+articleByManagerId);
            //为null说明当前不是文章发表人
            if (managerByArticle == null){
                //查询评论人id
                Comment comment = commentMapper.selectById(id);
                Manager managerByComment = (Manager) request.getSession().getAttribute("managerInfo"+comment.getByManagerId());
                if (managerByComment == null){
                    throw new BusinessException("你没有资格删除这条评论");
                }
            }
            commentMapper.deleteById(id);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Comment> getCommentsByArticleId(Integer articleId)throws BusinessException{
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Comment::getArticleId,articleId);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return TreeNodeUtil.assembleTree(comments);
    }

    @Override
    public List<Comment> getCommentPage(Comment comment) throws BusinessException {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotBlank(comment.getNickname()), Comment::getNickname, comment.getNickname())
                .orderByDesc(Comment::getCreateTime);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public void delComment(List<Integer> ids) throws BusinessException {
        try {
            commentMapper.deleteBatchIds(ids);
        }catch (BusinessException e){
            throw new BusinessException(e.getErrorMessage());
        }
    }
}
