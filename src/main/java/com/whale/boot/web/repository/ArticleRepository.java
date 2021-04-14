package com.whale.boot.web.repository;


import com.whale.boot.web.bean.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

}
