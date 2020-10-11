package com.shindemandapdecorators.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shindemandapdecorators.dto.UserDto;
import com.shindemandapdecorators.entity.UserEntity;
import com.shindemandapdecorators.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public List<UserEntity> getUsers() {
		System.out.println("in user getusers Service");
		return repository.findAll();
	}

	public UserEntity inserUser(UserDto userDto) {
		System.out.println("in user insert Service");
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setUsername(userDto.getUsername());
		userEntity = repository.saveAndFlush(userEntity);
		return userEntity;
	}

	public UserEntity updateUser(UserDto userDto) {
		Optional<UserEntity> userDb = this.repository.findById(userDto.getId());
		System.out.println("in user insert Service");
		if (userDb.isPresent()) {
			UserEntity userEntity = userDb.get();
			userEntity.setFirstName(userDto.getFirstName());
			userEntity.setLastName(userDto.getLastName());
			userEntity.setEmail(userDto.getEmail());
			userEntity.setPassword(userDto.getPassword());
			userEntity = repository.saveAndFlush(userEntity);
			return userEntity;
		} else {
			System.out.println("Record not found with id : " + userDb.get());
			return null;
		}

	}

	public UserEntity authenticate(UserDto userDto) {
		System.out.println("authentication service.");

		UserEntity user = repository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
		if (user != null) {
			System.out.println("user..." + user);
			return user;

		} else {
			System.out.println("not found invalid");
			return null;
		}

	}

	public UserEntity getUserById(int userId) {

		Optional<UserEntity> userDb = this.repository.findById(userId);

		if (userDb.isPresent()) {
			return userDb.get();
		} else {
			System.out.println("Record not found with id : " + userId);
			return null;
		}
	}

	public UserEntity deleteUser(int userId) {
		Optional<UserEntity> userDb = this.repository.findById(userId);

		if (userDb.isPresent()) {
			this.repository.delete(userDb.get());
			return userDb.get();
		} else {
			System.out.println("Record not found with id : " + userId);
			return null;
		}

	}
}
