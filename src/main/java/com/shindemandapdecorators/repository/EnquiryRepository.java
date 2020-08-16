package com.shindemandapdecorators.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shindemandapdecorators.entity.EnquiryEntity;

@Repository
public interface EnquiryRepository extends JpaRepository<EnquiryEntity, Integer> {

}
