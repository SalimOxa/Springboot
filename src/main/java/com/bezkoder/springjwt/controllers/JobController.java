package com.bezkoder.springjwt.controllers;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Job;
import com.bezkoder.springjwt.repository.JobRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class JobController {
	@Autowired
	private JobRepository jobRepository;
	
	@RequestMapping(value = "/jobs",method = RequestMethod.GET)
	public List<Job> getjob(){
		return jobRepository.findAll();
	}

	@RequestMapping(value = "/jobs/{id}",method = RequestMethod.GET)
	public Optional<Job> getJob(@PathVariable Long id){
		return jobRepository.findById(id);
	}
	@RequestMapping(value = "/jobs",method = RequestMethod.POST)
	public Job save(@RequestBody Job c ){
		return jobRepository.save(c);
	}
	@RequestMapping(value = "/jobs/{id}",method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id ){
		jobRepository.deleteById(id);
		return true;
	}
	@RequestMapping(value ="/jobs/{id}",method = RequestMethod.PUT)
	public Job save(@PathVariable Long id,@RequestBody Job c ){
		c.setId(id);
		return jobRepository.save(c);
	}
	@RequestMapping(value = "/chercherJob",method = RequestMethod.GET)
	public Page<Job> chercher(
			@RequestParam(name="mc",defaultValue ="")String mc,
	@RequestParam(name="page",defaultValue ="0") int page,
				@RequestParam(name="size",defaultValue ="5")int size){
		return jobRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
	}
}
