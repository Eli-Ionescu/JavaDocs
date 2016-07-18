package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/18/2016.
 */
public class JobServiceImpl implements JobService {
    JobDao jobDao = new JobDao();

    @Override
    public List<Job> findAllJobs() {
        return jobDao.getAllJobs();
    }

    @Override
    public Job findOneJob(String id) {
        return jobDao.getJobById(id);
    }

    @Override
    public void deleteOneJob(String id) {
        jobDao.deleteJob(jobDao.getJobById(id));
    }

    @Override
    public Job insertJob(Job job) {
        return jobDao.insertJob(job);
    }

    @Override
    public Job updateJob(Job job) {
        return jobDao.updateJob(job);
    }
}
