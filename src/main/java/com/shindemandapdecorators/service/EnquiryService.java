package com.shindemandapdecorators.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shindemandapdecorators.dto.EnquiryDto;
import com.shindemandapdecorators.entity.EnquiryEntity;
import com.shindemandapdecorators.repository.EnquiryRepository;

@Service
public class EnquiryService {
	@Autowired
	private EnquiryRepository enquiryRepository;

	public List<EnquiryEntity> getEnquiries() {
		System.out.println("in enquiryRepository");
		return enquiryRepository.findAll();
	}

	public EnquiryEntity addEnquiry(EnquiryDto enquiryDto) {
		System.out.println("in addEnquiry Service");
		EnquiryEntity enquiryEntity = new EnquiryEntity();
		enquiryEntity.setFullName(enquiryDto.getFullName());

		enquiryEntity.setEmail(enquiryDto.getEmail());
		enquiryEntity.setPhone(enquiryDto.getPhone());
		enquiryEntity.setMessage(enquiryDto.getMessage());
		enquiryEntity = enquiryRepository.saveAndFlush(enquiryEntity);
		return enquiryEntity;
	}

	public EnquiryEntity updateEnquiry(EnquiryDto enquiryDto) {
		Optional<EnquiryEntity> enquiryDB = this.enquiryRepository.findById(enquiryDto.getId());
		System.out.println("in updateEnquiry Service");
		if (enquiryDB.isPresent()) {
			EnquiryEntity enquiryEntity = enquiryDB.get();
			enquiryEntity.setFullName(enquiryDto.getFullName());
			enquiryEntity.setEmail(enquiryDto.getEmail());
			enquiryEntity.setPhone(enquiryDto.getPhone());
			enquiryEntity.setMessage(enquiryDto.getMessage());
			enquiryEntity = enquiryRepository.saveAndFlush(enquiryEntity);
			return enquiryEntity;
		} else {
			System.out.println("Record not found with id : " + enquiryDB.get());
			return null;
		}

	}

	public EnquiryEntity getEnquiryById(int enquiryId) {

		Optional<EnquiryEntity> enquiryDB = this.enquiryRepository.findById(enquiryId);

		if (enquiryDB.isPresent()) {
			return enquiryDB.get();
		} else {
			System.out.println("Record not found with id : " + enquiryId);
			return null;
		}
	}

	public EnquiryEntity deleteEnquiry(int enquiryId) {
		Optional<EnquiryEntity> enquiryDB = this.enquiryRepository.findById(enquiryId);
		if (enquiryDB.isPresent()) {
			this.enquiryRepository.delete(enquiryDB.get());
			return enquiryDB.get();
		} else {
			System.out.println("Record not found with id : " + enquiryId);
			return null;
		}

	}

}
