package com.shindemandapdecorators.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shindemandapdecorators.entity.BlogEntity;


@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer>{

}
