package com.greatlearning.labrestapi.serviceImpl;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.labrestapi.dao.UserRepository;
import com.greatlearning.labrestapi.entity.DomainUserDetails;
import com.greatlearning.labrestapi.entity.User;

//translator - my user to user details which Spring Security can understand
@Service
@Primary
public class DomainUserDetailsServiceImpl implements UserDetailsService {
	// our users are here
	private final UserRepository userRepository;

	public DomainUserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	/*
	 * Accepting the username entered by the user. Try to fetch the user from the db
	 * with the matching username If the user is not present in the database, throw
	 * UsernameNotFoundException. Throwing the UsernameNotFoundException will result
	 * in HTTP 401
	 * 
	 * If the user is present, then return a new DomainUserDetails which is an
	 * adaptor
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Entered username is " + username);
		// Search for the user in the database and return the user
		Optional<User> optionalUser = this.userRepository.findByUsername(username);

		if (optionalUser.isPresent()) {
			System.out.println("User found with the given username");
			// User user = optionalUser.get()
			return new DomainUserDetails(optionalUser.get());
		} else {
			throw new UsernameNotFoundException("Invalid username");
		}
	}
}
