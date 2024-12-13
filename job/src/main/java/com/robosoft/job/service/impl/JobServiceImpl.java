package com.robosoft.job.service.impl;

import com.robosoft.job.model.Job;
import com.robosoft.job.service.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private long nextId = 1L;

    public List<Job> findAll() {
        return jobs;
    }

    public Job getJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId() == id) {
                return job;
            }
        }
        return null;
    }

    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    public boolean updateJobById(Long id, Job updatedJob) {
        for (Job job : jobs) {
            if (job.getId() == id) {
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }

        }
        return false;
    }


        public boolean deleteJobById(Long id){
            Iterator<Job> iterator = jobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.getId() == id) {
                    iterator.remove(); // Safe removal
                    return true;
                }
            }

            return false;
        }
    }

