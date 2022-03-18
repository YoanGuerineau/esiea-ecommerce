package com.esiea.ecommerceapi.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.esiea.ecommerceapi.model.InternalUser;
import com.esiea.ecommerceapi.repository.InternalUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private InternalUserRepository internalUserRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		InternalUser user = internalUserRepository.findByUsername(username);
		
		User userDetails = new User(user.getUsername(), user.getPassword(), getGrantedAuthorities());
		
		return userDetails;
		
	}
	
	private List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add( new SimpleGrantedAuthority( "ROLE_USER" ) );
		
		return authorities;
	}

}
