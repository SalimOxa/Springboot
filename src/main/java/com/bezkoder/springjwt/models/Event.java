package com.bezkoder.springjwt.models;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
		
    @Size(max = 5000)
	private String description;
	
	private float rate;
	
	private int duration;
	
	
    @Size(max = 5000)
	private String conditions;
	

    @Size(max = 5000)
	private String targetedaudience;
	
	
    @Size(max = 5000)
	private String object;
	
	
    @Size(max = 5000)
	private String exammodalities;

	
    @Size(max = 5000)
	private String program;
	

    @Size(max = 5000)
	private String photo;
	
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "EVENT_DOMAIN", joinColumns = { @JoinColumn(name = "EVENT_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "DOMAIN_ID") })
	    private Set<Domain> domains;
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "EVENT_JOB", joinColumns = { @JoinColumn(name = "EVENT_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "JOB_ID") })
	    private Set<Job> jobs;
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "EVENT_FUNDER", joinColumns = { @JoinColumn(name = "EVENT_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "FUNDER_ID") })
	    private Set<Funder> funders;
	 
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "EVENT_MODALITY", joinColumns = { @JoinColumn(name = "EVENT_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "MODALITY_ID") })
	    private Set<Modality> modalitys;
		
	    @Nullable
		 @OneToMany(mappedBy = "event", cascade = {
			        CascadeType.ALL
			    })
		 @JsonIgnore
			    private List < Panier > paniers;
	    
	    
	public Set<Domain> getDomains() {
			return domains;
		}

		public void setDomains(Set<Domain> domains) {
			this.domains = domains;
		}

		public Set<Job> getJobs() {
			return jobs;
		}

		public void setJobs(Set<Job> jobs) {
			this.jobs = jobs;
		}

		public Set<Funder> getFunders() {
			return funders;
		}

		public void setFunders(Set<Funder> funders) {
			this.funders = funders;
		}

		public List<Panier> getPaniers() {
			return paniers;
		}

		public void setPaniers(List<Panier> paniers) {
			this.paniers = paniers;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getTargetedaudience() {
		return targetedaudience;
	}

	public void setTargetedaudience(String targetedaudience) {
		this.targetedaudience = targetedaudience;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getExammodalities() {
		return exammodalities;
	}

	public void setExammodalities(String exammodalities) {
		this.exammodalities = exammodalities;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Set<Domain> getDomaines() {
		return domains;
	}

	public void setDomaines(Set<Domain> domaines) {
		this.domains = domaines;
	}

	public Set<Job> getJobes() {
		return jobs;
	}

	public void setJobes(Set<Job> jobes) {
		this.jobs = jobes;
	}

	public Set<Funder> getFunderes() {
		return funders;
	}

	public void setFunderes(Set<Funder> funderes) {
		this.funders = funderes;
	}

	

	public Set<Modality> getModalitys() {
		return modalitys;
	}

	public void setModalitys(Set<Modality> modalitys) {
		this.modalitys = modalitys;
	}

	public Event(String title, String description, float rate, int duration, String conditions,
			String targetedaudience, String object, String exammodalities, String program, String photo,
			Set<Domain> domains, Set<Job> jobs, Set<Funder> funders, Set<Modality> modalitys) {
		super();
		this.title = title;
		this.description = description;
		this.rate = rate;
		this.duration = duration;
		this.conditions = conditions;
		this.targetedaudience = targetedaudience;
		this.object = object;
		this.exammodalities = exammodalities;
		this.program = program;
		this.photo = photo;
		this.domains = domains;
		this.jobs = jobs;
		this.funders = funders;
		this.modalitys = modalitys;
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
