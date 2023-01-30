package com.greatlearning.labrestapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcyptPasswordEncoderUtil {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword1 = passwordEncoder.encode("welcome");
		String encodedPassword2 = passwordEncoder.encode("welcome");
		String encodedPassword3 = passwordEncoder.encode("welcome");
		String encodedPassword4 = passwordEncoder.encode("welcome");

		System.out.println(encodedPassword1);
		System.out.println(encodedPassword2);
		System.out.println(encodedPassword3);
		System.out.println(encodedPassword4);
	}

}
