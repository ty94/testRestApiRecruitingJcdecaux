package com.jcdecaux.recuiting.developers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcdecaux.recuiting.developers.models.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Integer>{
	
	List<Developer> findByLangageId(Integer langageId);

}
