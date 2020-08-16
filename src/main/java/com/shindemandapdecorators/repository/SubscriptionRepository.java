package com.shindemandapdecorators.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shindemandapdecorators.entity.SubscriptionEntity;

@Repository

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {

}
