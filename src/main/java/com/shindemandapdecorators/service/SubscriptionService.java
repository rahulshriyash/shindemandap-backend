package com.shindemandapdecorators.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shindemandapdecorators.dto.SubscriptionDto;

import com.shindemandapdecorators.entity.SubscriptionEntity;

import com.shindemandapdecorators.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public SubscriptionEntity addSubscriptions(SubscriptionDto subscriptionDto) {
		System.out.println("in addSubscriptions Service");
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();

		subscriptionEntity.setEmail(subscriptionDto.getEmail());
		subscriptionEntity = subscriptionRepository.saveAndFlush(subscriptionEntity);
		return subscriptionEntity;
	}

	public List<SubscriptionEntity> getSubscriptions() {
		System.out.println("in Subscription getSubscriptions Service");
		return subscriptionRepository.findAll();
	}

	public SubscriptionEntity updateSubscription(SubscriptionDto subscriptionDto) {
		Optional<SubscriptionEntity> subscriptionDb = this.subscriptionRepository.findById(subscriptionDto.getId());
		System.out.println("in Subscription insert Service");
		if (subscriptionDb.isPresent()) {
			SubscriptionEntity subscriptionEntity = subscriptionDb.get();

			subscriptionEntity.setEmail(subscriptionDto.getEmail());

			subscriptionRepository.saveAndFlush(subscriptionEntity);
			return subscriptionEntity;
		} else {
			System.out.println("Record not found with id : " + subscriptionDb.get());
			return null;
		}

	}

	public SubscriptionEntity getSubscriptionById(int id) {

		Optional<SubscriptionEntity> subscriptionDb = this.subscriptionRepository.findById(id);

		if (subscriptionDb.isPresent()) {
			return subscriptionDb.get();
		} else {
			System.out.println("Record not found with id : " + id);
			return null;
		}
	}

	public SubscriptionEntity deleteSubscription(int id) {
		Optional<SubscriptionEntity> subscriptionDb = this.subscriptionRepository.findById(id);

		if (subscriptionDb.isPresent()) {
			this.subscriptionRepository.delete(subscriptionDb.get());
			return subscriptionDb.get();
		} else {
			System.out.println("Record not found with id : " + id);
			return null;
		}

	}
}
