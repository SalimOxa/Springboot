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
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Training {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String title;
		
	
    @Size(max = 5000)
	private String description;
	
	private Double rate;
	
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
	
    @Nullable
	 @OneToMany(mappedBy = "training", cascade = {
		        CascadeType.ALL
		    })
	 @JsonIgnore
		    private List < Panier > paniers;
    
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "TRAINING_DOMAIN", joinColumns = { @JoinColumn(name = "TRAINING_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "DOMAIN_ID") })
	    private Set<Domain> domains;
	 
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "TRAINING_JOB", joinColumns = { @JoinColumn(name = "TRAINING_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "JOB_ID") })
	    private Set<Job> jobs;
	 
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "TRAINING_FUNDER", joinColumns = { @JoinColumn(name = "TRAINING_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "FUNDER_ID") })
	    private Set<Funder> funders;
	 
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "TRAINING_MODALITY", joinColumns = { @JoinColumn(name = "TRAINING_ID") }, 
	    inverseJoinColumns = { @JoinColumn(name = "MODALITY_ID") })
	    private Set<Modality> modalitys;

	/*
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
		@JoinTable(	name = "USER_TRAINING", joinColumns = @JoinColumn(name = "training_id"), 
					inverseJoinColumns ={ @JoinColumn(name = "user_id")})
		private Set<User> user ;
	 
	
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
*/
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
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


	public Set<Domain> getDomains() {
		return domains;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public void setDomains(Set<Domain> domains) {
		this.domains = domains;
	}

	public Set<Funder> getFunders() {
		return funders;
	}

	public void setFunders(Set<Funder> funders) {
		this.funders = funders;
	}

	public Set<Modality> getModalitys() {
		return modalitys;
	}

	public void setModalitys(Set<Modality> modalitys) {
		this.modalitys = modalitys;
	}



	public Training(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}


	public Training() {
		super();
		// TODO Auto-generated constructor stub
	}

}
