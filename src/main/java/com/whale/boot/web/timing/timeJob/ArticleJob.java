package com.whale.boot.web.timing.timeJob;

import com.whale.boot.web.bean.Article;
import com.whale.boot.web.repository.ArticleRepository;
import com.whale.boot.web.service.ArticleService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * 通过定时任务删除创建索引
 */
@Configuration
@Component
@EnableScheduling
public class ArticleJob implements Job {
    //注入需要执行任务的service
    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleRepository articleRepository;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //删除全部es所以
        articleRepository.deleteAll();
        //查询全部文章加入到es中
        Article article = new Article();
        List<Article> articleByPage = articleService.getArticleByPage(article);
        articleRepository.saveAll(articleByPage);
    }

}
