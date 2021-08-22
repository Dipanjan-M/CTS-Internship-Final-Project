package com.cts.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Notification;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	private NotificationService service;

	/**
	 * Create Notice By Admin only
	 * 
	 * @param notification
	 * @return
	 * @throws ApiException
	 */

	@PostMapping("admin/notice")
	public ResponseEntity<?> addNotification(@RequestBody Notification notification) throws ApiException {
		notification.setCreateDate(new Date());
		return ResponseEntity
				.ok(new ApiResponse(200, "New notice added successfully", service.createNotification(notification)));
	}

	/**
	 * Read All Notices By Admin only
	 * 
	 * @return
	 * @throws ApiException
	 */
	@GetMapping("admin/notice")
	public ResponseEntity<?> findAllNotifications() throws ApiException {
		List<Notification> notices = service.getAllNotifications();
		return ResponseEntity.ok(new ApiResponse(200, "Fetched all notices", notices));
	}

	/**
	 * Delete a Notice with given ID by Admin only
	 * 
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	@DeleteMapping("admin/notice/{id}")
	public ResponseEntity<?> deleteNotification(@PathVariable int id) throws ApiException {
		String status = service.deleteNotification(id);
		return ResponseEntity.ok(new ApiResponse(200, status));
	}

	/**
	 * Read Notices by scope either by admin or employee
	 * 
	 * @param scope
	 * @return
	 * @throws ApiException
	 */
	@GetMapping("employee/notice/scope/{scope}")
	public ResponseEntity<?> findNotificationByScope(@PathVariable int scope) throws ApiException {
		List<Notification> notices = service.getNotificationsByScope(scope);
		return ResponseEntity.ok(new ApiResponse(200, "Fetched notices with requested scope", notices));
	}

	/**
	 * Read a Notice with a given ID by either Admin or Employee
	 * 
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	@GetMapping("employee/notice/{id}")
	public ResponseEntity<?> findNotificationById(@PathVariable int id) throws ApiException {
		Notification notice = service.getNotificationById(id);
		return ResponseEntity.ok(new ApiResponse(200, "Fetched notice", notice));
	}

}
