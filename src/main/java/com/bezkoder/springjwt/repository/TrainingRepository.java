package com.bezkoder.springjwt.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bezkoder.springjwt.models.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

	@Query("select c from Training c where c.title like :x")
	public Page<Training> chercher( @Param("x")String mc,Pageable pageable);
	//List<Training> findTrainingByUserEmailOrderByIdDesc(String email);
	@Query("select c from Training c where c.title like :x ")
	public List<Training>chercherList(@Param("x")String mc);
	
	//////////////
	
	
	 @Query(value = "SELECT COUNT(training_id) FROM training_domain td JOIN domain d ON td.domain_id = d.id WHERE d.id LIKE '1' ", nativeQuery = true)
	public long getCountOfTrainingAssurance();
	 
	  @Query("SELECT COUNT(*) FROM Training ")
	 public long getCountOfTraining();
	   
		 @Query(value = "SELECT COUNT(training_id) FROM training_domain td JOIN domain d ON td.domain_id = d.id WHERE d.id LIKE '2' ", nativeQuery = true)
		public long getCountOfTrainingBanque();
	 
		 @Query(value = "SELECT COUNT(training_id) FROM training_domain td JOIN domain d ON td.domain_id = d.id WHERE d.id LIKE '3' ", nativeQuery = true)
		public long getCountOfTrainingImmobilier();

	 @Query(value ="SELECT COUNT(training_id) FROM training_modality td JOIN modality d ON td.modality_id = d.id WHERE d.id LIKE '1' ", nativeQuery = true)
		public long getCountOfTrainingEnligne();
	 @Query(value ="SELECT COUNT(training_id) FROM training_modality td JOIN modality d ON td.modality_id = d.id WHERE d.id LIKE '2'", nativeQuery = true)
		public long getCountOfTrainingPresentiel();
	
	
	
}
