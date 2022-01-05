package com.bezkoder.springjwt.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Modality {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
	
	private String name;
	
	@ManyToMany(mappedBy = "modalitys")
    @JsonIgnore
    private Set<Training> trainings;
	
	@ManyToMany(mappedBy = "modalitys")
    @JsonIgnore
    private Set<Event> events;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Modality() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Modality(String name) {
		super();
		this.name = name;
	}

}
