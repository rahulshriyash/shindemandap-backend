package com.shindemandapdecorators.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shindemandapdecorators.dto.EnquiryDto;
import com.shindemandapdecorators.entity.EnquiryEntity;
import com.shindemandapdecorators.exception.RecordNotFoundException;
import com.shindemandapdecorators.service.EnquiryService;

@RestController
@RequestMapping
public class EnquiryController {
	@Autowired
	private EnquiryService enquiryService;

	@PostMapping("/enquiry")
	public EnquiryDto addEnquiry(@RequestBody EnquiryDto enquiryDto) {
		System.out.println("in post Enquiry controller" + enquiryDto);
		EnquiryEntity enquiryEntity = enquiryService.addEnquiry(enquiryDto);
		enquiryDto.setId(enquiryEntity.getId());
		return enquiryDto;
	}

	@PutMapping("/enquiry/{id}")
	public EnquiryDto updateEnquiry(@PathVariable int id, @RequestBody EnquiryDto enquiryDto) {
		enquiryDto.setId(id);
		enquiryService.updateEnquiry(enquiryDto);
		return enquiryDto;
	}

	@GetMapping("/enquiries")
	public List<EnquiryEntity> getEnquiries() {
		System.out.println("in getEnquiries contoller");
		return enquiryService.getEnquiries();
	}

	@GetMapping("/enquiry/{id}")
	public ResponseEntity<EnquiryEntity> getEnquiryById(@PathVariable int id) {
		EnquiryEntity enquiryEntity = enquiryService.getEnquiryById(id);
		if (enquiryEntity == null) {
			throw new RecordNotFoundException("Invalid enquiry id : " + id);
		}
		return new ResponseEntity<EnquiryEntity>(enquiryEntity, HttpStatus.OK);

	}

	@DeleteMapping("/enquiry/{id}")
	public ResponseEntity<EnquiryEntity> deleteEnquiry(@PathVariable int id) {
		EnquiryEntity enquiryEntity = this.enquiryService.deleteEnquiry(id);
		if (enquiryEntity == null) {
			throw new RecordNotFoundException("Invalid enquiry id : " + id);
		}
		return new ResponseEntity<EnquiryEntity>(enquiryEntity, HttpStatus.OK);

	}

}
