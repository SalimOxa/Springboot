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

import com.bezkoder.springjwt.models.Modality;
import com.bezkoder.springjwt.repository.ModalityRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")public class ModalityController {
	@Autowired
	private ModalityRepository modalityRepository;
	
	@RequestMapping(value = "/modalitys",method = RequestMethod.GET)
	public List<Modality> getevent(){
		return modalityRepository.findAll();
	}

	@RequestMapping(value = "/modalitys/{id}",method = RequestMethod.GET)
	public Optional<Modality> getEvent(@PathVariable Long id){
		return modalityRepository.findById(id);
	}
	@RequestMapping(value = "/modalitys",method = RequestMethod.POST)
	public Modality save(@RequestBody Modality c ){
		return modalityRepository.save(c);
	}
	@RequestMapping(value = "/modalitys/{id}",method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id ){
		modalityRepository.deleteById(id);
		return true;
	}
	@RequestMapping(value ="/modalitys/{id}",method = RequestMethod.PUT)
	public Modality save(@PathVariable Long id,@RequestBody Modality c ){
		c.setId(id);
		return modalityRepository.save(c);
	}
	/*@RequestMapping(value = "/chercherEvent",method = RequestMethod.GET)
	public Page<Modality> chercher(
			@RequestParam(name="mc",defaultValue ="")String mc,
	@RequestParam(name="page",defaultValue ="0") int page,
				@RequestParam(name="size",defaultValue ="5")int size){
		return modalityRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
	}*/

}
