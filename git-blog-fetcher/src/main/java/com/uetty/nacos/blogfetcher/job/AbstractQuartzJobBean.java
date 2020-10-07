//package com.uetty.nacos.blogfetcher.job;
//
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//public abstract class AbstractQuartzJobBean extends QuartzJobBean {
//
//
//    @Autowired
//    protected Scheduler scheduler;
//
//    protected abstract String getJobKeyName();
//
//    public void triggerJobs() {
//        try {
//            // 发送消息触发
//            JobKey jobKey = new JobKey(getJobKeyName());
//
//            scheduler.triggerJob(jobKey);
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
