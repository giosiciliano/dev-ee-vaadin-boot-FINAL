package com.gio.repository.university;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gio.model.entity.University;

// repo is a component that will be injected into the springs IoC container
@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

	
	// custom queries
	
	//JPA query language JPQL (according to Java classes/variables)
	@Query("select u from University u order by u.universityName")
	List<University> getAllUniversities();
	
	//dealing with java entities with a Student.University.getId and set that to param
	// :universityId
	@Query("select count(s) from Student s where s.university.id=:universityId")
	Integer getNumOfStudentsForUniversity(@Param("universityId") Integer universityId);
	
}
