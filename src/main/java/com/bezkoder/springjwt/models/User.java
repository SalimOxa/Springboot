package com.bezkoder.springjwt.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	 @Size(min = 3, max = 120)
	private String username;
	
    @Size(max = 120)
	private String photo;
    
	@NotBlank
	@Size(min = 8, max = 20)
	private String tel;

	@NotBlank
	@Size(max = 120)
	@Email
	private String email;

	@NotBlank
	 @Size(min = 6, max = 120)
	private String password;

	 @Nullable
	 @OneToMany(mappedBy = "user", cascade = {
		        CascadeType.ALL
		    })
	 @JsonIgnore
		    private List < Panier > paniers;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public User() {
	}

	public User(String username, String tel, String email, String password) {
		this.username = username;
		this.tel = tel;
		this.email = email;
		this.password = password;
	}

	public User(Long id, @NotBlank @Size(min = 3, max = 120) String username, @Size(max = 5000) String photo,
			@NotBlank @Size(min = 8, max = 20) String tel, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(min = 6, max = 120) String password, List<Panier> paniers, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.photo = photo;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.paniers = paniers;
		this.roles = roles;
	}

	public User(@NotBlank @Size(min = 3, max = 120) String username, @NotBlank @Size(min = 8, max = 20) String tel, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 50) String photo, @NotBlank @Size(min = 6, max = 120) String password) {
		this.username = username;
		this.tel = tel;
		this.email = email;
		this.photo = photo;
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
