package com.bezkoder.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.Domain;
import com.bezkoder.springjwt.models.Event;
import com.bezkoder.springjwt.models.Funder;
import com.bezkoder.springjwt.models.Job;
import com.bezkoder.springjwt.models.Modality;
import com.bezkoder.springjwt.repository.DomainRepository;
import com.bezkoder.springjwt.repository.EventRepository;
import com.bezkoder.springjwt.repository.FilesStorageService;
import com.bezkoder.springjwt.repository.FunderRepository;
import com.bezkoder.springjwt.repository.JobRepository;
import com.bezkoder.springjwt.repository.ModalityRepository;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private  DomainRepository domainRepository;
	@Autowired
	FilesStorageService storageService;
	@Autowired
	FunderRepository funderRepository;
	@Autowired
	ModalityRepository modalityRepository;
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public List<Event> getEvents() {
		return eventRepository.findAll();
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	public Optional<Event> getEvents(@PathVariable Long id) {
		return eventRepository.findById(id);
	}
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public Event save(@RequestBody Event c) {
		return eventRepository.save(c);
	}
	@RequestMapping(value = "/events/new", method = RequestMethod.POST)
	Event addEvent(@RequestParam(name = "jobids") String[] jobIds,
			       @RequestParam(name = "domainids") String[] domainids,
			       @RequestParam(name = "funderids") String[] funderids,
			       @RequestParam(name = "modalitysids") String[] modalitysids,
			       @RequestBody Event events) {
		Set<Job> listOfJobs = new HashSet<>();
		for (String id : jobIds) {
			listOfJobs.add(jobRepository.findById(Long.parseLong(id)).get());
		}
		
		Set<Domain> listOfDomains = new HashSet<>();
		for (String id : domainids) {
			listOfDomains.add(domainRepository.findById(Long.parseLong(id)).get());
		}	
		Set<Funder> listOfFunders = new HashSet<>();
		for (String id : funderids) {
			listOfFunders.add(funderRepository.findById(Long.parseLong(id)).get());
		}
			
		Set<Modality> listOfModalitys = new HashSet<>();
		for (String id :modalitysids) {
			listOfModalitys .add(modalityRepository.findById(Long.parseLong(id)).get());
		}
		events.setModalitys(listOfModalitys);
		events.setFunderes(listOfFunders);
		events.setDomaines(listOfDomains);
		events.setJobes(listOfJobs);
		return eventRepository.save(events);

	}
	@RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id) {
		eventRepository.deleteById(id);
		return true;
	}
	@RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
	public Event save(@PathVariable Long id, @RequestBody Event c) {
		c.setId(id);
		return eventRepository.save(c);
	}
	//@RequestMapping(value = "/chercherEvent", method = RequestMethod.GET)
	//public Page<Event> chercher(@RequestParam(name = "mc", defaultValue = "") String mc,
		//	@RequestParam(name = "page", defaultValue = "0") int page,
		//	@RequestParam(name = "size", defaultValue = "5") int size) {
		//return eventRepository.chercher("%" + mc + "%", PageRequest.of(page, size));
//	}
	@RequestMapping(value = "/events/{idevent}/addjobtoevent/{idjobes}", method = RequestMethod.GET)
	Event addJobtoEvent(@PathVariable("idevent") Long idevent, @PathVariable("idjobes") Long idjobes) {
		Job job = jobRepository.findById(idjobes).get();
		Event event = eventRepository.findById(idevent).get();
		Set<Job> listOfJobs = event.getJobes();
		listOfJobs.add(job);
		event.setJobes(listOfJobs);
		return eventRepository.save(event);

	}
	@RequestMapping(value = "/events/{idevent}/adddomaintoevent/{iddomaines}", method = RequestMethod.GET)
	Event addDomaintoEvent(@PathVariable("idevent") Long idevent, @PathVariable("iddomaines") Long iddomaines) {
		Domain domain = domainRepository.findById(iddomaines).get();
		Event event = eventRepository.findById(idevent).get();
		Set<Domain> listOfDomains = event.getDomaines();
		listOfDomains.add(domain);
		event.setDomaines(listOfDomains);
		return eventRepository.save(event);

	}
	@RequestMapping(value = "/events/{idevent}/addfendertoevent/{idfunderes}", method = RequestMethod.GET)
	Event addFundertoEvent(@PathVariable("idevent") Long idevent, @PathVariable("idfunderes") Long idfunderes) {
		Funder funder = funderRepository.findById(idfunderes).get();
		Event event = eventRepository.findById(idevent).get();
		Set<Funder> listOfFunders = event.getFunderes();
		listOfFunders.add(funder);
		event.setFunderes(listOfFunders);
		return eventRepository.save(event);

	}
	
	@RequestMapping(value = "/events/{idevent}/addmodalitytoevent/{idmodalityes}", method = RequestMethod.GET)
	Event addModalitytoEvent(@PathVariable("idevent") Long idevent, @PathVariable("idmodalityes") Long idmodalityes) {
		Modality modality = modalityRepository.findById(idmodalityes).get();
		Event event = eventRepository.findById(idevent).get();
		Set<Modality> listOfModalitys = event.getModalitys();
		listOfModalitys.add(modality);
		event.setModalitys(listOfModalitys);
		return eventRepository.save(event);

	}
	
	@RequestMapping(value = "/events/{idevent}/update", method = RequestMethod.PUT)
	Event updateEvent(@PathVariable("idevent") Long idevent,
			          @RequestParam(required = false,defaultValue = "",name ="jobids") String[] jobIds,
			          @RequestParam(required = false,defaultValue = "",name = "domainids") String[] domainids,
			          @RequestParam(required = false,defaultValue = "",name = "funderids") String[] funderids,
			          @RequestParam(required = false,defaultValue = "",name = "modalitysids") String[] modalitysids,
			          @RequestBody Event events) {
		Event event = eventRepository.findById(idevent).get();
		Set<Job> listOfJobs = new HashSet<>();
		for (String id : jobIds) {
			listOfJobs.add(jobRepository.findById(Long.parseLong(id)).get());
		}	
		Set<Domain> listOfDomains = new HashSet<>();
		for (String id : domainids) {
			listOfDomains.add(domainRepository.findById(Long.parseLong(id)).get());
		}	
		Set<Funder> listOfFunders = new HashSet<>();
		for (String id : funderids) {
			listOfFunders.add(funderRepository.findById(Long.parseLong(id)).get());
		}
			
		Set<Modality> listOfModalitys = new HashSet<>();
		for (String id :modalitysids) {
			listOfModalitys.add(modalityRepository.findById(Long.parseLong(id)).get());
		}
	    event=events;
		event.setJobes(listOfJobs);
		event.setFunderes(listOfFunders);
		event.setDomaines(listOfDomains);
		event.setModalitys(listOfModalitys);
		System.out.println(event);
		return eventRepository.save(event);
	}
	/*@PostMapping("/upload")
	public String uploadFile1(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.save(file);
			return file.getOriginalFilename();

		} catch (Exception e) {
			message = "Could not upload the file";
			return message;
		}
	}
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile1(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}*/
	
}
