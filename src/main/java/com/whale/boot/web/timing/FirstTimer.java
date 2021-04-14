package com.whale.boot.web.timing;

import com.whale.boot.web.comment.QuartzComment;
import com.whale.boot.web.timing.timeJob.ArticleJob;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

/**
 * @author litian
 */
@Configuration
@EnableScheduling
public class FirstTimer implements ApplicationRunner {
//public class FirstTimer {

    @Resource
    private QuartzComment quartzComment;


    @Override
    public void run(ApplicationArguments args) {
        /*String jobName = "动态任务调度";
        String jobGroupName = "任务组名";
        String triggerName = "触发器名";
        String triggerGroupName = "触发器组名";*/
//        String time = "0/20 * * * * ? ";

        String time = "0 0 1 * * ?";
        //添加一个定时器任务
        quartzComment.addJob(ArticleJob.class, time);
    }
}
