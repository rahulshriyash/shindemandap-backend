package com.shindemandapdecorators.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shindemandapdecorators.dto.UserDto;
import com.shindemandapdecorators.entity.UserEntity;
import com.shindemandapdecorators.exception.RecordNotFoundException;
import com.shindemandapdecorators.repository.ApplicationUserRepository;
import com.shindemandapdecorators.securiry.AuthenticationFilter;
import com.shindemandapdecorators.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
private AuthenticationFilter authenticationFilter;
	/* private ApplicationUserRepository applicationUserRepository; */
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
		/* this.applicationUserRepository = applicationUserRepository; */
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users/record")
    public UserDto inserUser(@RequestBody UserDto userDto) {
    	userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity=service.inserUser(userDto); userDto.setId(userEntity.getId());
        return userDto;
    }
	
	/*
	 * @PostMapping("/login") public UserEntity authenticate(@RequestBody UserDto
	 * userDto) { System.out.println("in post user authenticate controller email" +
	 * userDto.getEmail());
	 * System.out.println("in post user authenticate controller password" +
	 * userDto.getPassword()); UserEntity userEntity =
	 * service.authenticate(userDto); userDto.setId(userEntity.getId()); return
	 * userEntity; }
	 */

	
	
	
	
	
	
	
	
	@PutMapping("/users/{id}")
	public UserDto updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
		userDto.setId(id);
		service.updateUser(userDto);
		return userDto;
	}

	@GetMapping("/users")
	public List<UserEntity> getAllUsers() {
		System.out.println("in users contoller");
		return service.getUsers();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable int id) {
		UserEntity userEntity = service.getUserById(id);
		if (userEntity == null) {
			throw new RecordNotFoundException("Invalid user id : " + id);
		}
		return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
		// return ResponseEntity.ok().body(service.getUserById(id));
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<UserEntity> deleteUser(@PathVariable int id) {
		UserEntity userEntity = this.service.deleteUser(id);
		if (userEntity == null) {
			throw new RecordNotFoundException("Invalid user id : " + id);
		}
		return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
		// return ResponseEntity.ok().body(service.getUserById(id));
	}

}
