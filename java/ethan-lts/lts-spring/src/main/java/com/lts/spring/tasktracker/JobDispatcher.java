package com.lts.spring.tasktracker;

import com.lts.core.commons.utils.StringUtils;
import com.lts.core.domain.Job;
import com.lts.tasktracker.Result;
import com.lts.tasktracker.runner.JobRunner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robert HG (254963746@qq.com) on 10/20/15.
 */
public class JobDispatcher implements JobRunner {

    private String shardField = "taskId";
    @Autowired
    private LtsJobRunnerScanner ltsJobRunnerScanner;

    @Override
    public Result run(Job job) throws Throwable {

        String value;
        if (shardField.equals("taskId")) {
            value = job.getTaskId();
        } else {
            value = job.getParam(shardField);
        }

        JobRunner jobRunner = null;
        if(StringUtils.isNotEmpty(value)){
            jobRunner = ltsJobRunnerScanner.getJobRunner(value);
        }
        if (jobRunner == null) {
            jobRunner = ltsJobRunnerScanner.getJobRunner("_LTS_DEFAULT");

            if (jobRunner == null) {
                throw new JobDispatchException("Can not find JobRunner by Shard Value : [" + value + "]");
            }
        }
        return jobRunner.run(job);
    }

    public void setShardField(String shardField) {
        if (StringUtils.isNotEmpty(shardField)) {
            this.shardField = shardField;
        }
    }
}
