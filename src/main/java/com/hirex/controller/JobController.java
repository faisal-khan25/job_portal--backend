package com.hirex.controller;

import com.hirex.dto.JobDto.JobRequest;
import com.hirex.dto.JobDto.JobResponse;
import com.hirex.service.JobService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class JobController {
    @Autowired
    private  JobService jobService;
    

    

    // public browse - no auth needed
    @GetMapping("/api/jobs/browse")
    public ResponseEntity<List<JobResponse>> browse(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isBlank()) {
            return ResponseEntity.ok(jobService.searchJobs(keyword));
        }
        return ResponseEntity.ok(jobService.getAllActiveJobs());
    }

    @PostMapping("/api/manager/jobs")
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest req, Principal principal) {
        return ResponseEntity.ok(jobService.createJob(req, principal.getName()));
    }

    @PutMapping("/api/manager/jobs/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long id,
                                                  @RequestBody JobRequest req,
                                                  Principal principal) {
        return ResponseEntity.ok(jobService.updateJob(id, req, principal.getName()));
    }

    @DeleteMapping("/api/manager/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id, Principal principal) {
       
        jobService.deleteJob(id, principal.getName());
        
        return ResponseEntity.ok("Job removed");
    }

    @GetMapping("/api/manager/jobs")
    public ResponseEntity<List<JobResponse>> myJobs(Principal principal) {
        return ResponseEntity.ok(jobService.getManagerJobs(principal.getName()));
    }
}
