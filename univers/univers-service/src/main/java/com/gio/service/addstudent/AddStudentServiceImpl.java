package com.gio.service.addstudent;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gio.model.entity.Student;
import com.gio.repository.student.StudentRepository;

@Service
@Transactional(readOnly=true)
public class AddStudentServiceImpl implements AddStudentService {
 
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	@Transactional // overriding the default readOnly (if it fails rollback will occur)
	public void saveStudent(Student studentDAO) {
		
		Student student = new Student();
		student.setFirstName(studentDAO.getFirstName());
		student.setLastName(studentDAO.getLastName());
		student.setAge(studentDAO.getAge());
		student.setGender(studentDAO.getGender());
		student.setUniversity(studentDAO.getUniversity());
		
		studentRepository.save(student);
		
	}

	
	
}
