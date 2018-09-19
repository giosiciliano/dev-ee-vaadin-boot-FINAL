package com.gio.repository.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gio.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	// Basic CRUD operations already built (insert, delete)
	
	// custom queries
	@Query("select s from Student s order by s.firstName")
	List<Student> getAllStudents();
	
}
