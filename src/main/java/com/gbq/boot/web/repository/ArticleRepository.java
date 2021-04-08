package com.gbq.boot.web.repository;


import com.gbq.boot.web.bean.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article,Long> {

}
