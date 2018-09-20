package com.kinexcs.demo.getway.application;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsService implements UserDetailsService {

	private String userNameInDB = "kinexcs";
	// private String passwordInDB="kinexcs";
	private String passwordInDB = "cff0654169e38578947d3fb0e187209629f276db0307994cffb863f7728b5bcf";

	private String role = "admin";

	Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		LOGGER.info("user name in request:" + s);

		// User user = userRepository.findByUsername(s);
		//
		// if(user == null) {
		// throw new
		// UsernameNotFoundException(String.format("The username %s doesn't exist",
		// s));
		// }
		//
		// List<GrantedAuthority> authorities = new ArrayList<>();
		// user.getRoles().forEach(role -> {
		// authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		// });
		//
		// UserDetails userDetails = new
		// org.springframework.security.core.userdetails.
		// User(user.getUsername(), user.getPassword(), authorities);
		//
		// return userDetails;
		//

		String userName = s;
		if (!userNameInDB.equals(userName)) {
			LOGGER.info("FAILED");
			throw new UsernameNotFoundException(String.format("The username %s doesn't exist", userName));
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(userNameInDB, passwordInDB, authorities);
		LOGGER.info("Final Obj: " + userName);
		return userDetails;

	}
}
