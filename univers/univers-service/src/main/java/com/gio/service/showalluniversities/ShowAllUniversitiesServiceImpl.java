package com.gio.service.showalluniversities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gio.model.entity.University;
import com.gio.repository.university.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService {

	@Autowired
	private UniversityRepository universityRepository;
	
	@Override
	public List<University> getAllUniversities() {
		return universityRepository.getAllUniversities();
	}

}
