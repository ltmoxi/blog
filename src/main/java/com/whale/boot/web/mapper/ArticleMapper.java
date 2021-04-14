package com.whale.boot.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whale.boot.web.bean.Article;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author litian
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<String> searchTag();

    List<Article> searchTimeLine();
}