package com.gio.service.universitystatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gio.repository.university.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class UniversityStatisticsServiceImpl implements UniversityStatisticsService {

	@Autowired
	private UniversityRepository uniRepo;
	
	@Override
	public Integer getNumOfStudentsForUniversity(Integer universityId) {
		return uniRepo.getNumOfStudentsForUniversity(universityId);
	}

}
