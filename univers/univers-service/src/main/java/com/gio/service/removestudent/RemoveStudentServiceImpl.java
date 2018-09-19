package com.gio.service.removestudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gio.model.entity.Student;
import com.gio.repository.student.StudentRepository;

@Service
@Transactional(readOnly=true)
public class RemoveStudentServiceImpl implements RemoveStudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	@Transactional
	public void removeStudent(Student student) {
		studentRepository.delete(student);
		
	}

}
