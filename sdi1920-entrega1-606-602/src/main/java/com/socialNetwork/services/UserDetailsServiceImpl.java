package com.socialNetwork.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialNetwork.entities.User;
import com.socialNetwork.repositories.UsersRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		User user = usersRepository.findByDni(dni);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"));
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

		return new org.springframework.security.core.userdetails.User(user.getDni(), user.getPassword(),
				grantedAuthorities);
	}
}