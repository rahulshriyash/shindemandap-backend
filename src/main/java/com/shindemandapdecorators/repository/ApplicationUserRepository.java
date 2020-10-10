package com.shindemandapdecorators.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shindemandapdecorators.entity.UserEntity;

public interface ApplicationUserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}