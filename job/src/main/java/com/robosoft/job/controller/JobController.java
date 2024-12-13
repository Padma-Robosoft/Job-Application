package com.robosoft.job.controller;

import com.robosoft.job.model.Job;
import com.robosoft.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobByID(@PathVariable Long id) {
        Job job = jobService.
                getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updateJobById){
        boolean updated=jobService.updateJobById(id, updateJobById);
        if(updated){
            return ResponseEntity.ok("Updated Sucessfullly");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted) {
            return ResponseEntity.ok("deleted sucessfully");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
