package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bezkoder.springjwt.models.Panier;

@Repository
public interface PanierRepository extends CrudRepository<Panier, Long>, JpaRepository<Panier, Long> {

	List<Panier> findAllByOrderByIdDesc();
	List<Panier> findPanierByUserEmailOrderByIdDesc(String email);
	
	/*@Query("SELECT SUM(rate) FROM Training t where t.title = 'HONORAIRES EN IMMOBILIER'")
	Long getPrixTotal();*/
	  @Query(value = "SELECT SUM(rate) FROM `training` AS training JOIN `paniers` AS panier ON panier.training_id = training.id WHERE panier.user_id = 1", nativeQuery = true)
	Long getPrixTotal();
	  @Query(value = "SELECT SUM(rate) FROM `event` AS event JOIN `paniers` AS panier ON panier.event_id = event.id WHERE panier.user_id = 1", nativeQuery = true)
		Long getPrixTotal1();

}
