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

import com.bezkoder.springjwt.models.Domain;
import com.bezkoder.springjwt.repository.DomainRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class DomainControllers {
	@Autowired
	private DomainRepository domainRepository;
	@RequestMapping(value = "/domains",method = RequestMethod.GET)
	public List<Domain> getdomain(){
		return domainRepository.findAll();
	}

	@RequestMapping(value = "/domains/{id}",method = RequestMethod.GET)
	public Optional<Domain> getDomain(@PathVariable Long id){
		return domainRepository.findById(id);
	}
	@RequestMapping(value = "/domains",method = RequestMethod.POST)
	public Domain save(@RequestBody Domain c ){
		return domainRepository.save(c);
	}
	@RequestMapping(value = "/domains/{id}",method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id ){
		domainRepository.deleteById(id);
		return true;
	}
	@RequestMapping(value ="/domains/{id}",method = RequestMethod.PUT)
	public Domain save(@PathVariable Long id,@RequestBody Domain c ){
		c.setId(id);
		return domainRepository.save(c);
	}

@RequestMapping(value = "/chercherdomain",method = RequestMethod.GET)
public Page<Domain> chercher(
		@RequestParam(name="mc",defaultValue ="")String mc,
@RequestParam(name="page",defaultValue ="0") int page,
			@RequestParam(name="size",defaultValue ="5")int size){
	return domainRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
}
}
