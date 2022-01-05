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
}
