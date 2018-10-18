package com.jcdecaux.recuiting.developers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcdecaux.recuiting.developers.models.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
	

}
