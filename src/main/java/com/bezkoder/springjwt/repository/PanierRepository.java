package com.bezkoder.springjwt.repository;

import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.bezkoder.springjwt.models.Panier;
import com.bezkoder.springjwt.models.Training;

@Repository
public interface PanierRepository extends CrudRepository<Panier, Long>, JpaRepository<Panier, Long> {

	List<Panier> findAllByOrderByIdDesc();
	List<Panier> findPanierByUserEmailOrderByIdDesc(String email);
	
	/*SELECT * FROM `training` AS training INNER JOIN `paniers` AS panier ON panier.training_id = training.id INNER JOIN `users` AS users ON users.id = panier.user_id  WHERE panier.user_id = users.id*/
	/*SELECT SUM(rate) FROM `training` AS training INNER JOIN `paniers` AS panier ON panier.training_id = training.id INNER JOIN `users` AS users ON users.id = panier.user_id  WHERE panier.user_id = users.id*/
	
	/*@Query("SELECT SUM(rate) FROM Training t where t.title = 'HONORAIRES EN IMMOBILIER'----JOIN `users` AS user ON panier.user_id = user.id WHERE panier.user_id = user.id")
	Long getPrixTotal();*/
	/*  @Query(value = "SELECT SUM(rate) FROM `training` AS training JOIN `paniers` AS panier ON panier.training_id = training.id WHERE panier.user_id = users.id ", nativeQuery = true)
		Long getPrixTotalTraining();*/

	  
	  
	  @Query(value = "SELECT SUM(rate) FROM `training` AS training INNER JOIN `paniers` AS panier ON panier.training_id = training.id INNER JOIN `users` AS users ON users.id = panier.user_id WHERE panier.user_id = ?1 ", nativeQuery = true)
		Long getPrixTotalTraining( Long userId);
	  
	  
	  @Query(value = "SELECT SUM(rate) FROM `event` AS event JOIN `paniers` AS panier ON panier.event_id = event.id ", nativeQuery = true)
		Long getPrixTotal1();

}
