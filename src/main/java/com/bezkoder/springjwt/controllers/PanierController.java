package com.bezkoder.springjwt.controllers;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bezkoder.springjwt.models.Panier;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.EventRepository;
import com.bezkoder.springjwt.repository.PanierRepository;
import com.bezkoder.springjwt.repository.TrainingRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.grokonez.jwtauthentication.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class PanierController {
	
	@Autowired
	private PanierRepository panierRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TrainingRepository trainingRepository;
	@Autowired
	private EventRepository eventRepository;
	
	@Transactional
	  @GetMapping("/panier")
	    public List<Panier> getAllPanier() {
	        return panierRepository.findAllByOrderByIdDesc();
	    }


	@RequestMapping(value = "/panier/{id}",method = RequestMethod.GET)
	public Optional<Panier> getPanier(@PathVariable Long id){
		return panierRepository.findById(id);
	}

	//@PostMapping("/{userId}/panier")

	//public ResponseEntity<?> registerPanier(@PathVariable(value = "userId") Long userId, @Valid @RequestBody Panier panier) throws ResourceNotFoundException {
	/*	Set<Training> trainings = new HashSet<>();*/

			 //userRepository.findById(userId).map(user -> {
		    	//  panier.setUser(user);
		      //    return panierRepository.save(panier);
			// }).orElseThrow(() -> new ResourceNotFoundException("instructor not found"));	
			 
			/* panier.setTraining(trainings);
			 panierRepository.save(panier);*/
			/* trainingRepository.findById(trainigId).map(training -> {
		    	  panier.setTraining(trainings);
		          return panierRepository.save(panier);
			 }).orElseThrow(() -> new ResourceNotFoundException("instructor not found"));		*/	
//	return ResponseEntity.ok(new MessageResponse("Panier registered successfully!"));
	// }
	@RequestMapping(value = "/panier",method = RequestMethod.POST)
	public Panier save(@RequestBody Panier c ){
		return panierRepository.save(c);
	}
	@RequestMapping(value = "/panier/{id}",method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id ){
		panierRepository.deleteById(id);
		return true;
	}
	@RequestMapping(value ="/panier/{id}",method = RequestMethod.PUT)
	public Panier save(@PathVariable Long id,@RequestBody Panier c ){
		c.setId(id);
		return panierRepository.save(c);
	}
	 @GetMapping("/paniers/{email}")
		public List<Panier> getPanierByEmail(@PathVariable String email) {
		    return panierRepository.findPanierByUserEmailOrderByIdDesc(email);
		}
	 @PostMapping("/panier/user/{userId}/training/{trainingId}")
		public ResponseEntity<?> registerPanier(@PathVariable(value = "userId") Long userId, 
				@PathVariable(value = "trainingId") Long trainingId,
				@Valid @RequestBody Panier panier) throws ResourceNotFoundException {
		// Panier paniers = new Panier(paniers.setEtatPanier(false));

				userRepository.findById(userId).map(user -> {
			    	  panier.setUser(user);
			          return panierRepository.save(panier);
				 }).orElseThrow(() -> new ResourceNotFoundException("instructor not found"));
				 
				  trainingRepository.findById(trainingId).map(training -> {
			    	  panier.setTraining(training);
			          return panierRepository.save(panier);
				 }).orElseThrow(() -> new ResourceNotFoundException("instructor not found"));
				 
		return ResponseEntity.ok(new MessageResponse("panier registered successfully!"));
		}
	 @PostMapping("/panier/user/{userId}/event/{eventId}")
		public ResponseEntity<?> registerPanier1(@PathVariable(value = "userId") Long userId, 
				@PathVariable(value = "eventId") Long eventId,
				@Valid @RequestBody Panier panier) throws ResourceNotFoundException {
		// Panier paniers = new Panier(paniers.setEtatPanier(false));

				userRepository.findById(userId).map(user -> {
			    	  panier.setUser(user);
			          return panierRepository.save(panier);
				 }).orElseThrow(() -> new ResourceNotFoundException("instructor not found"));
				 
				  eventRepository.findById(eventId).map(event -> {
			    	  panier.setEvent(event);
			          return panierRepository.save(panier);
				 }).orElseThrow(() -> new ResourceNotFoundException("instructor not found"));
				 
		return ResponseEntity.ok(new MessageResponse("panier registered successfully!"));
		}
	

	 @GetMapping("/panier/totalPrix/{userId}")
		public Long getTotalPrix(@PathVariable(value = "userId") Long userId) {
	 	 return panierRepository.getPrixTotalTraining(userId);
		    /*return panierRepository.getPrixTotal() + panierRepository.getPrixTotal1();*/
		}
}
