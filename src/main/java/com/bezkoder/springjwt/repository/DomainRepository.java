package com.bezkoder.springjwt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {
   
	@Query("select c from Training c where c.title like :x")
	public Page<Domain> chercher( @Param("x")String mc,Pageable pageable);
}
