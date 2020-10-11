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

import com.shindemandapdecorators.dto.SubscriptionDto;
import com.shindemandapdecorators.entity.SubscriptionEntity;
import com.shindemandapdecorators.exception.RecordNotFoundException;
import com.shindemandapdecorators.service.SubscriptionService;

@RestController
@RequestMapping
public class SubscriptionController {
	@Autowired

	private SubscriptionService subscriptionService;

	@PostMapping("/subscription")
	public SubscriptionDto addSubscriptions(@RequestBody SubscriptionDto subscriptionDto) {
		System.out.println("in post subscriptionDto" + subscriptionDto);
		SubscriptionEntity subscriptionEntity = subscriptionService.addSubscriptions(subscriptionDto);
		subscriptionDto.setId(subscriptionEntity.getId());
		return subscriptionDto;
	}

	@PutMapping("/subscription/{id}")
	public SubscriptionDto updateUser(@PathVariable int id, @RequestBody SubscriptionDto subscriptionDto) {
		subscriptionDto.setId(id);
		subscriptionService.updateSubscription(subscriptionDto);
		return subscriptionDto;
	}

	@GetMapping("/subscriptions")
	public List<SubscriptionEntity> getAllSubscriptions() {
		System.out.println("in Subscriptions contoller");
		return subscriptionService.getSubscriptions();
	}

	@GetMapping("/subscription/{id}")
	public ResponseEntity<SubscriptionEntity> getSubscriptionById(@PathVariable int id) {
		SubscriptionEntity subscriptionEntity = subscriptionService.getSubscriptionById(id);
		if (subscriptionEntity == null) {
			throw new RecordNotFoundException("Invalid subscription id : " + id);
		}
		return new ResponseEntity<SubscriptionEntity>(subscriptionEntity, HttpStatus.OK);
	}

	@DeleteMapping("/subscription/{id}")
	public ResponseEntity<SubscriptionEntity> deleteSubscription(@PathVariable int id) {
		SubscriptionEntity subscriptionEntity = this.subscriptionService.deleteSubscription(id);
		if (subscriptionEntity == null) {
			throw new RecordNotFoundException("Invalid user id : " + id);
		}
		return new ResponseEntity<SubscriptionEntity>(subscriptionEntity, HttpStatus.OK);
	}
}
