package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestBodyParam;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobService;
import ro.teamnet.zth.appl.service.JobServiceImpl;

import java.util.List;

/**
 * Created by Eli on 7/14/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {
    JobService jobService = new JobServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJObs(){
        return jobService.findAllJobs();
    }

    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(name = "id") String id){
        return jobService.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public void deleteOneJob(@MyRequestParam(name = "id") String id){
        jobService.deleteOneJob(id);

    }

    @MyRequestMethod(urlPath = "/create", methodType = "POST")
    public Job insertJob(@MyRequestBodyParam Job job){
        return jobService.insertJob(job);
    }

    @MyRequestMethod(urlPath = "/update", methodType = "POST")
    public Job updateJob(@MyRequestBodyParam Job job){
        return jobService.updateJob(job);
    }
}