package com.shindemandapdecorators.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.shindemandapdecorators.dto.UserDto;
import com.shindemandapdecorators.entity.UserEntity;
import com.shindemandapdecorators.repository.ApplicationUserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	private ApplicationUserRepository applicationUserRepository;

	public ApplicationUserDetailsService(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		Set<GrantedAuthority> authorities = new HashSet<>();

		UserDto userDto = new UserDto(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
		userDto.setFirstName(applicationUser.getFirstName());
		userDto.setLastName(applicationUser.getLastName());
		userDto.setEmail(applicationUser.getEmail());
		userDto.setId(applicationUser.getId());
		userDto.setPassword(applicationUser.getPassword());
		userDto.setUsername(applicationUser.getUsername());
		return userDto;
	}
}