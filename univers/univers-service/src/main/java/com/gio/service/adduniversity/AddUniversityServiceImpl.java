package com.gio.service.adduniversity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gio.model.entity.University;
import com.gio.repository.university.UniversityRepository;


// component injected for autowiring
@Service
@Transactional(readOnly=true)
public class AddUniversityServiceImpl implements AddUniversityService {

	@Autowired
	private UniversityRepository universityRepository;
	
	@Override
	@Transactional
	public void addUniversity(University universityDAO) {
		
		// pass in the DAO, it will have new values per add
		// that is why we instantiate a new university
		// otherwise we will only update the prior university persisted
		University university = new University();
		university.setUniversityName(universityDAO.getUniversityName());
		university.setUniversityCountry(universityDAO.getUniversityCountry());
		university.setUniversityCity(universityDAO.getUniversityCity());
		
		universityRepository.save(university);
		
	}

}
