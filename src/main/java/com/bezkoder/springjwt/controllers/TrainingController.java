package com.bezkoder.springjwt.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.core.io.Resource;
import com.bezkoder.springjwt.models.Domain;
import com.bezkoder.springjwt.models.Funder;
import com.bezkoder.springjwt.models.Job;
import com.bezkoder.springjwt.models.Modality;
import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.repository.DomainRepository;
import com.bezkoder.springjwt.repository.FilesStorageService;
import com.bezkoder.springjwt.repository.FunderRepository;
import com.bezkoder.springjwt.repository.JobRepository;
import com.bezkoder.springjwt.repository.ModalityRepository;
import com.bezkoder.springjwt.repository.TrainingRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class TrainingController {
	
	@Autowired
	private TrainingRepository trainingRepository;
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	FilesStorageService storageService;
	
	@Autowired
	 FunderRepository funderRepository;
	@Autowired
	ModalityRepository modalityRepository;
	
	@RequestMapping(value = "/trainings",method = RequestMethod.GET)
	public List<Training> getTraining(){
		return trainingRepository.findAll();
	}
	@RequestMapping(value = "/trainings/{id}",method = RequestMethod.GET)
	public Optional<Training> getTrainings(@PathVariable Long id){
		return trainingRepository.findById(id);
	}
	@RequestMapping(value = "/trainings",method = RequestMethod.POST)
	public Training save(@RequestBody Training c ){
		return trainingRepository.save(c);
	}
	@RequestMapping(value = "/trainings/new", method = RequestMethod.POST)
	Training addTraining(@RequestParam(name = "jobids") String[] jobIds,
			       @RequestParam(name = "domainids") String[] domainids,
			       @RequestParam(name = "funderids") String[] funderids,
			       @RequestParam(name = "modalitysids") String[] modalitysids,
			       @RequestBody Training trainings) {
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
		trainings.setModalitys(listOfModalitys);
		trainings.setFunders(listOfFunders);
		trainings.setDomains(listOfDomains);
		trainings.setJobs(listOfJobs);
		return trainingRepository.save(trainings);

	}
	@RequestMapping(value = "/trainings/{id}",method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id ){
		trainingRepository.deleteById(id);
		return true;
	}
	@RequestMapping(value ="/trainings/{id}",method = RequestMethod.PUT)
	public Training save(@PathVariable Long id,@RequestBody Training c ){
		c.setId(id);
		return trainingRepository.save(c);
	}
	@RequestMapping(value = "/chercherTraining",method = RequestMethod.GET)
	public Page<Training> chercher(
			@RequestParam(name="mc",defaultValue ="")String mc,
	@RequestParam(name="page",defaultValue ="0") int page,
				@RequestParam(name="size",defaultValue ="5")int size){
		return trainingRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
	}
	@RequestMapping(value = "/trainings/{idtraining}/addjobtotraining/{idjob}", method = RequestMethod.GET)
	Training addJobtoTrainig(@PathVariable("idtraining") Long idtraining, @PathVariable("idjob") Long idjob) {
		Job job = jobRepository.findById(idjob).get();
		Training trainig = trainingRepository.findById(idtraining).get();
		Set<Job> listOfJobs = trainig.getJobs();
		listOfJobs.add(job);
		trainig.setJobs(listOfJobs);
		return trainingRepository.save(trainig);

	}
	@RequestMapping(value = "/trainings/{idtraining}/adddomaintotraining/{iddomain}", method = RequestMethod.GET)
	Training addDomaintoTrainig(@PathVariable("idtraining") Long idtraining, @PathVariable("iddomain") Long iddomain) {
		Domain domain = domainRepository.findById(iddomain).get();
		Training trainig = trainingRepository.findById(idtraining).get();
		Set<Domain> listOfDomains = trainig.getDomains();
		listOfDomains.add(domain);
		trainig.setDomains(listOfDomains);
		return trainingRepository.save(trainig);

	}
	@RequestMapping(value = "/trainings/{idtraining}/addfundertotraining/{idfunder}", method = RequestMethod.GET)
	Training addFundertoTrainig(@PathVariable("idtraining") Long idtraining, @PathVariable("idfunder") Long idfunder) {
		Funder funder = funderRepository.findById(idfunder).get();
		Training trainig = trainingRepository.findById(idtraining).get();
		Set<Funder> listOffunders = trainig.getFunders();
		listOffunders.add(funder);
		trainig.setFunders(listOffunders);
		return trainingRepository.save(trainig);

	}
	@RequestMapping(value = "/trainings/{idtraining}/addmodalitytotraining/{idmodality}", method = RequestMethod.GET)
	Training addModalitytoTrainig(@PathVariable("idtraining") Long idtraining, @PathVariable("idmodality") Long idmodality) {
		Modality funder = modalityRepository.findById(idmodality).get();
		Training trainig = trainingRepository.findById(idtraining).get();
		Set<Modality> listOfmodalitys = trainig.getModalitys();
		listOfmodalitys.add(funder);
		trainig.setModalitys(listOfmodalitys);
		return trainingRepository.save(trainig);

	}
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
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
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
/*	 @GetMapping("trainings/panier/{id}")
		public List<Training> getTrainingByEmail(@PathVariable Long id) {
		    return trainingRepository.findTrainingBypaniersIdOrderByIdDesc(id);
		}*/
	/* @GetMapping("trainingss/{email}")
		public List<Training> getTrainingByEmail(@PathVariable String email) {
		    return trainingRepository.findTrainingByUserEmailOrderByIdDesc(email);
		}*/
	  @GetMapping("/domaines")
	  public List<Domain> getAllDomain() {
	      return domainRepository.findAll();
	  }
	/*  @RequestMapping(value ="/chercherFormationAssurance", method = RequestMethod.GET)
		public Page<Training>chercherTrainingAssurance(
				@RequestParam(name="mc",defaultValue ="" )String mc,
				@RequestParam(name="page",defaultValue ="" )String  page,
				@RequestParam(name="size",defaultValue ="" )String  size){
			
			List<Training> trainings=new ArrayList<Training>();
			
			for(Training training:trainingRepository.chercherList("%"+mc+"%")) {
				for (Domain domain:training.getDomains()) {
					
					String assurance = "assurance";
					if(domain.getName().equals(assurance)) {
						
						trainings.add(training);				}
				}
			}
			Page<Training> trainingpegable=new PageImpl<>(trainings);
		return trainingpegable;
		}*/
		
		@RequestMapping(value ="/chercherFormationbanque", method = RequestMethod.GET)
		public Page<Training>chercherTrainingbanque(
				@RequestParam(name="mc",defaultValue ="" )String mc,
				@RequestParam(name="page",defaultValue ="" )String  page,
				@RequestParam(name="size",defaultValue ="" )String  size){
			
			List<Training> trainings=new ArrayList<Training>();
			
			for(Training training:trainingRepository.chercherList("%"+mc+"%")) {
				for (Domain domain:training.getDomains()) {
					
					String banque = "Banque";
					if(domain.getName().equals(banque)) {
						
						trainings.add(training);				}
				}
			}
			Page<Training> trainingpegable=new PageImpl<>(trainings);
		return trainingpegable;
		}
	  @RequestMapping(value ="/chercherFormationAssurance", method = RequestMethod.GET)
		public Page<Training>chercherTrainingAssurance(
				@RequestParam(name="mc",defaultValue ="" )String mc,
				@RequestParam(name="page",defaultValue ="" )String  page,
				@RequestParam(name="size",defaultValue ="" )String  size){
			
			List<Training> trainings=new ArrayList<Training>();
			
			for(Training training:trainingRepository.chercherList("%"+mc+"%")) {
				for (Domain domain:training.getDomains()) {
					
					String assurance = "Assurance";
					if(domain.getName().equals(assurance)) {
						
						trainings.add(training);				}
				}
			}
			Page<Training> trainingpegable=new PageImpl<>(trainings);
		return trainingpegable;
		}
		
		@RequestMapping(value ="/chercherFormationimmobilier", method = RequestMethod.GET)
		public Page<Training>chercherTrainingimmobilier(
				@RequestParam(name="mc",defaultValue ="" )String mc,
				@RequestParam(name="page",defaultValue ="" )String  page,
				@RequestParam(name="size",defaultValue ="" )String  size){
			
			List<Training> trainings=new ArrayList<Training>();
			
			for(Training training:trainingRepository.chercherList("%"+mc+"%")) {
				for (Domain domain:training.getDomains()) {
					
					String immobilier = "Immobilier";
					if(domain.getName().equals(immobilier)) {
						
						trainings.add(training);				}
				}
			}
			Page<Training> trainingpegable=new PageImpl<>(trainings);
		return trainingpegable;
		}
		@RequestMapping(value = "/trainings/{idtraining}/update", method = RequestMethod.PUT)
		Training updateTraining(@PathVariable("idtraining") Long idtraining,
				          @RequestParam(required = false,defaultValue = "",name ="jobids") String[] jobIds,
				          @RequestParam(required = false,defaultValue = "",name = "domainids") String[] domainids,
				          @RequestParam(required = false,defaultValue = "",name = "funderids") String[] funderids,
				          @RequestParam(required = false,defaultValue = "",name = "modalitysids") String[] modalitysids,
				          @RequestBody Training trainings) {
			Training training = trainingRepository.findById(idtraining).get();
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
			training=trainings;
			training.setJobs(listOfJobs);
			training.setFunders(listOfFunders);
			training.setDomains(listOfDomains);
			training.setModalitys(listOfModalitys);
			System.out.println(training);
			return trainingRepository.save(training);
		}
		
		@RequestMapping(value ="/chercherFormationenligne", method = RequestMethod.GET)
		public Page<Training>chercherTrainingEnligne(
				@RequestParam(name="mc",defaultValue ="" )String mc,
				@RequestParam(name="page",defaultValue ="" )String  page,
				@RequestParam(name="size",defaultValue ="" )String  size){
			
			List<Training> trainings=new ArrayList<Training>();
			
			for(Training training:trainingRepository.chercherList("%"+mc+"%")) {
				for (Modality modality:training.getModalitys()) {
					
					String enligne = "En ligne";
					if(modality.getName().equals(enligne)) {
						
						trainings.add(training);				}
				}
			}
			Page<Training> trainingpegable=new PageImpl<>(trainings);
		return trainingpegable;
		}
		@RequestMapping(value ="/chercherFormationpresentiel", method = RequestMethod.GET)
		public Page<Training>chercherTrainingPresentiel(
				@RequestParam(name="mc",defaultValue ="" )String mc,
				@RequestParam(name="page",defaultValue ="" )String  page,
				@RequestParam(name="size",defaultValue ="" )String  size){
			
			List<Training> trainings=new ArrayList<Training>();
			
			for(Training training:trainingRepository.chercherList("%"+mc+"%")) {
				for (Modality modality:training.getModalitys()) {
					
					String presentiel = "En présentiel";
					if(modality.getName().equals(presentiel)) {
						
						trainings.add(training);				}
				}
			}
			Page<Training> trainingpegable=new PageImpl<>(trainings);
		return trainingpegable;
		}
		
		@RequestMapping(value="/training/count", method = RequestMethod.GET)
		public long countTraining () {
			long count = trainingRepository.getCountOfTraining();
			return count;
		}
		
		@RequestMapping(value="/training/countAssurance", method = RequestMethod.GET)
		public long countTrainingAssurance () {
			long count = trainingRepository.getCountOfTrainingAssurance();
			return count;
		}
		@RequestMapping(value="/training/countBanque", method = RequestMethod.GET)
		public long countTrainingBanque () {
			long count = trainingRepository.getCountOfTrainingBanque();
			return count;
		}
		
		@RequestMapping(value="/training/countImmobilier", method = RequestMethod.GET)
		public long countTrainingImmobilier () {
			long count = trainingRepository.getCountOfTrainingImmobilier();
			return count;
		}
		@RequestMapping(value="/training/countEnligne", method = RequestMethod.GET)
		public long countTrainingEnligne () {
			long count = trainingRepository.getCountOfTrainingEnligne();
			return count;
		}
		@RequestMapping(value="/training/countPresentiel", method = RequestMethod.GET)
		public long countTrainingPresentiel () {
			long count = trainingRepository.getCountOfTrainingPresentiel();
			return count;
		}
}
