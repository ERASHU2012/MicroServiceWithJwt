package com.ashu.ms.getway.application;

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

	
//	using hard coaded username and passwors and role
	private String userNameInDB = "ashu";
	private String passwordInDB = "84815cfd6abcc8486ebc81613d098c45468ca431f45af87950f77096986bac4c";
	private String role = "admin";

	Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		LOGGER.info("user name in request:" + s);

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
