package com.greatlearning.labrestapi.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.labrestapi.dao.StudentRepository;
import com.greatlearning.labrestapi.dao.UserRepository;
import com.greatlearning.labrestapi.entity.Role;
import com.greatlearning.labrestapi.entity.Student;
import com.greatlearning.labrestapi.entity.User;

//add data to our DataBase

@Configuration
public class BootstrapAppData {

	// add dependencies
	private final StudentRepository studentRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	// add constructor
	public BootstrapAppData(UserRepository userRepository, PasswordEncoder passwordEncoder,
			StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// dynamically adding data
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertAppData(ApplicationReadyEvent event) {
		System.out.println("Application is ready :: ");
		System.out.println("Inserting the test data :: ");

		Student Suresh = new Student("Suresh", "Reddy", "B.Tech", "India");
		Student Murali = new Student("Murali", "Mohan", "B.Arch", "Canada");
		Student Daniel = new Student("Daniel", "Denson", "B.Tech", "New Zealand");
		Student Tanya = new Student("Tanya", "Gupta", "B.Com", "USA");

		User user = new User("User", this.passwordEncoder.encode("welcome"));
		User admin = new User("Admin", this.passwordEncoder.encode("welcome"));

		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_ADMIN");

		// regular user
		user.addRole(userRole);

		// both regular and admin
		admin.addRole(userRole);
		admin.addRole(adminRole);

		this.userRepository.save(user);
		this.userRepository.save(admin);

		this.studentRepository.save(Suresh);
		this.studentRepository.save(Murali);
		this.studentRepository.save(Daniel);
		this.studentRepository.save(Tanya);
	}

}
