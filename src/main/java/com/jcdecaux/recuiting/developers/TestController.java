package com.jcdecaux.recuiting.developers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcdecaux.recuiting.developers.models.Developer;
import com.jcdecaux.recuiting.developers.models.Language;
import com.jcdecaux.recuiting.developers.repositories.DeveloperRepository;
import com.jcdecaux.recuiting.developers.repositories.LanguageRepository;
import com.jcdecaux.recuiting.developers.utils.NotFoundException;

@RestController
public class TestController {
	
	@Autowired
	private DeveloperRepository developerRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	
	// create developer
	
	@PostMapping("/developers")
	public Developer createStudent(@RequestBody Developer developer) {
		Developer savedDeveloper = developerRepository.save(developer);



		return savedDeveloper;

	}
	
	
	// create language
	
	@PostMapping("/languages")
	public Language createLanguage(@RequestBody Language language) {
		Language savedLangage = languageRepository.save(language);


		return savedLangage;

	}

	// update developer
	
	@PutMapping("/developers/{id}")
	public Developer updateDeveloper(@RequestBody Developer developer, @PathVariable Integer id) {

		Optional<Developer> developpeurOptional = developerRepository.findById(id);

		if (!developpeurOptional.isPresent())
			throw new NotFoundException("developer with id "+id+" not found");

		developer.setId(id);
		
		developerRepository.save(developer);

		return developer;
	}

	// associate language to developer
	
	@PutMapping("/developers/associateLanguage/{id}")
	public Developer addLanguage(@RequestParam Integer languageId, @PathVariable Integer id) {

		Optional<Developer> developerOptional = developerRepository.findById(id);
		
		if (!developerOptional.isPresent())
			throw new NotFoundException("developer with id "+id+" not found");

		Developer dev=developerOptional.get();
		
		dev.setLangageId(languageId);
		
		developerRepository.save(dev);

		return dev;
	}

	
	// get developers by language id
	
	@GetMapping("/getDevelopers/{languageId}")
	public List<Developer> getDevelopersByLanguage(@PathVariable Integer languageId) {

		List<Developer> listDev=developerRepository.findByLangageId(languageId);

		if (listDev.isEmpty())
			return new ArrayList<Developer>();


		return listDev;
	}
	

}
