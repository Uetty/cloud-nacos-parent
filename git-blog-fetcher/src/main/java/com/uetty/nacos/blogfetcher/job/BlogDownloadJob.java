//package com.uetty.nacos.blogfetcher.job;
//
//import com.uetty.nacos.blogfetcher.constants.Constants;
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@DisallowConcurrentExecution // 禁止并发
//public class BlogDownloadJob extends AbstractQuartzJobBean {
//
//
//    @Autowired
//    protected Scheduler scheduler;
//
//
//    @Override
//    protected String getJobKeyName() {
//        return Constants.JOB_NAME_DOWNLOAD_BLOG;
//    }
//
//    public void triggerJob() {
//        try {
//            // 发送消息触发
//            JobKey jobKey = new JobKey(getJobKeyName());
//            scheduler.triggerJob(jobKey);
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        log.info("consume job {}", jobExecutionContext.getJobDetail().getKey().getName());
//    }
//}
