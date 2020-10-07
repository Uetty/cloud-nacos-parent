//package com.uetty.nacos.blogfetcher.job;
//
//import com.uetty.nacos.blogfetcher.constants.Constants;
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@Configuration
//public class QuartzConfig {
//
//    /**
//     * 配置定时任务详情类
//     */
//    @Bean
//    public JobDetail downloadGithubJob() {
//        JobKey jobKey = new JobKey(Constants.JOB_NAME_DOWNLOAD_BLOG);
//        return JobBuilder.newJob(BlogDownloadJob.class).withIdentity(jobKey).storeDurably().build();
//    }
//}
