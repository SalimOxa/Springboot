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

import com.bezkoder.springjwt.models.Funder;
import com.bezkoder.springjwt.repository.FunderRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class FunderController {
	@Autowired
	private FunderRepository funderRepository;
	@RequestMapping(value = "/funders",method = RequestMethod.GET)
	public List<Funder> getevent(){
		return funderRepository.findAll();
	}

	@RequestMapping(value = "/funders/{id}",method = RequestMethod.GET)
	public Optional<Funder> getEvent(@PathVariable Long id){
		return funderRepository.findById(id);
	}
	@RequestMapping(value = "/funders",method = RequestMethod.POST)
	public Funder save(@RequestBody Funder c ){
		return funderRepository.save(c);
	}
	@RequestMapping(value = "/funders/{id}",method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id ){
		funderRepository.deleteById(id);
		return true;
	}
	@RequestMapping(value ="/funders/{id}",method = RequestMethod.PUT)
	public Funder save(@PathVariable Long id,@RequestBody Funder c ){
		c.setId(id);
		return funderRepository.save(c);
	}
	@RequestMapping(value = "/chercherFunder",method = RequestMethod.GET)
	public Page<Funder> chercher(
			@RequestParam(name="mc",defaultValue ="")String mc,
	@RequestParam(name="page",defaultValue ="0") int page,
				@RequestParam(name="size",defaultValue ="5")int size){
		return funderRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
	}
	}
