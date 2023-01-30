package com.greatlearning.labrestapi.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.labrestapi.dao.StudentRepository;
import com.greatlearning.labrestapi.entity.Student;
import com.greatlearning.labrestapi.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(int theId) {

		Optional<Student> result = studentRepository.findById(theId);

		Student student = null;

		if (result.isPresent()) {
			student = result.get();
		} else
			try {
				throw new RuntimeException("Did not find student id - " + theId);
			} catch (Exception e) {
			}
		return student;
	}

	@Override
	public void save(Student student) {
		studentRepository.saveAndFlush(student);
	}

	@Override
	public void deleteById(int theId) {
		studentRepository.deleteById(theId);
	}

}
