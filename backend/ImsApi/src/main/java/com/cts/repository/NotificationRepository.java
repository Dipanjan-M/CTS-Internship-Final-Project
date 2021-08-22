package com.cts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.models.entityModels.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	Optional<List<Notification>> findByScope(int scope);

}
