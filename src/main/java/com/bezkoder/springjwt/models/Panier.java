package com.bezkoder.springjwt.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(	name = "paniers" )
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Boolean etatPanier;
	
	
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id")
	    private User user ;
	 
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "training_id")
	    private Training training ;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "event_id")
	    private Event event ;

	    
public Event getEvent() {
			return event;
		}

		public void setEvent(Event event) {
			this.event = event;
		}

public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	/*
	 @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id")
	    private User user ;

	 @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "training_id")
	    private Training training ;
	 
	 
	 
	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
		@JoinTable(	name = "TRAINING_PANIER", joinColumns = @JoinColumn(name = "panier_id"), 
					inverseJoinColumns ={ @JoinColumn(name = "training_id")})
		private Set<Training> training ;
	
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
		@JoinTable(	name = "EVENT_PANIER", joinColumns = @JoinColumn(name = "panier_id"), 
					inverseJoinColumns ={ @JoinColumn(name = "event_id")})
		private Set<Event> event ;
	 
	 
	public Set<Event> getEvent() {
		return event;
	}

	public void setEvent(Set<Event> event) {
		this.event = event;
	}

	public Set<Training> getTraining() {
		return training;
	}

	public void setTraining(Set<Training> training) {
		this.training = training;
	}
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEtatPanier() {
		return etatPanier;
	}

	public void setEtatPanier(Boolean etatPanier) {
		this.etatPanier = etatPanier;
	}
/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
	public Panier() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Panier(Long id, @NotBlank Boolean etatPanier, User user) {
		super();
		this.id = id;
		this.etatPanier = etatPanier;
		this.user = user;
	}

	public Panier(Long id, Boolean etatPanier, User user, Training training) {
		super();
		this.id = id;
		this.etatPanier = etatPanier;
		this.user = user;
		this.training = training;
	}

	public Panier(Boolean etatPanier) {
		super();
		this.etatPanier = etatPanier;
	}


	

	 
	    
	 
	 
	 
}
