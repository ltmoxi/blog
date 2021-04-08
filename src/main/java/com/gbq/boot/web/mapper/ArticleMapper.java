package com.gbq.boot.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gbq.boot.web.bean.Article;

import java.util.List;


/**
 * @author aqian666
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<String> searchTag();

    List<Article> searchTimeLine();
}