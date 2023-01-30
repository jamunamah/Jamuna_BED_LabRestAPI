package com.greatlearning.labrestapi.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.labrestapi.entity.Student;
import com.greatlearning.labrestapi.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	@Autowired
	StudentService studentService;

	// expose "/students" and return list of students
	@GetMapping("/students")
	public List<Student> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return studentService.findAll();
	}

	// add mapping for GET - Read
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		Student theStudent = studentService.findById(studentId);

		if (theStudent == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}
		return theStudent;
	}

	// add mapping for POST - add new student - Create
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student theStudent) {

		// if id passed in JSON --> set id to 0
		// this is to force a save of new item, instead of update

		theStudent.setId(0);
		studentService.save(theStudent);
		return theStudent;
	}

	// add mapping for PUT - Update existing student
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student theStudent) {
		studentService.save(theStudent);
		return theStudent;
	}

	// add mapping for DELETE - Delete student
	@DeleteMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable int studentId) {
		Student tempStudent = studentService.findById(studentId);

		// throw exception if null

		if (tempStudent == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}

		studentService.deleteById(studentId);
		return "Deleted Student id - " + studentId;
	}

}
