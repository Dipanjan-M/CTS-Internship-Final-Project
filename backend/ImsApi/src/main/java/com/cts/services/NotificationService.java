package com.cts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Notification;
import com.cts.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository repository;
	
	/**
	 * Creates new Notice if any exception occurs then throws 
	 * ApiException with proper message
	 * @param notification
	 * @return
	 * @throws ApiException
	 */
	public Notification createNotification(Notification notification) throws ApiException {
		Notification createdNotification = null;
		try {
			createdNotification = repository.save(notification);
			return createdNotification;
		} catch(Exception e) {
			throw new ApiException(e.getCause().getCause().getMessage());
		}
	}
	
	/**
	 * Fetches all notices from the database if no notice found 
	 * throws ApiException with proper message
	 * @return
	 * @throws ApiException
	 */
	public List<Notification> getAllNotifications() throws ApiException {
		List<Notification> notices = repository.findAll();
		if(notices.isEmpty()) {
			throw new ApiException("No notice found.");
		}
		return notices;
	}
	
	/**
	 * Fetches a notice by its id from database
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	public Notification getNotificationById(int id) throws ApiException {
		Optional<Notification> notification = repository.findById(id);
		notification.orElseThrow(() -> new ApiException("Unable to find requested notice."));
		return notification.get();
	}
	
	/**
	 * Fetches notices by its scope from database
	 * 0 => global
	 * other => corresponds to employee associated with the ID
	 * @param scope
	 * @return
	 * @throws ApiException
	 */
	public List<Notification> getNotificationsByScope(int scope) throws ApiException {
		List<Notification> notices = repository.findByScope(scope).get();
		if(notices.isEmpty()) {
			throw new ApiException("No notice has been found with requested scope.");
		}
		return notices;
	}
	
	/**
	 * Deletes a Notice
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	public String deleteNotification(int id) throws ApiException {
		Optional<Notification> notification = repository.findById(id);
		notification.orElseThrow(() -> new ApiException("Unable to delete noification."));
		Notification toBeDeleted = notification.get();
		repository.deleteById(toBeDeleted.getId());
		return "Notification deleted Successfully";
	}

}
