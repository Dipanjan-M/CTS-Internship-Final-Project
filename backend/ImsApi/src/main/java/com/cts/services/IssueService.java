package com.cts.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Issue;
import com.cts.models.requestModels.CreateIssueRequest;
import com.cts.models.requestModels.ResolveIssueRequest;
import com.cts.repository.IssueRepository;

@Service
public class IssueService {

	@Autowired
	private IssueRepository repository;

	/**
	 * Save an Issue into the database
	 * 
	 * @param createIssue
	 * @return com.cts.model.Issue
	 */
	public Issue saveIssue(CreateIssueRequest createIssue) {
		Issue issue = new Issue();
		issue.setEmpId(createIssue.getEmpId());
		issue.setTitle(createIssue.getTitle());
		issue.setDescription(createIssue.getDescription());
		Date currentDate = new Date();
		issue.setCreatedAt(currentDate);
		issue.setSolved(false);
		issue.setLastUpdate(currentDate);
		return repository.save(issue);
	}

	/**
	 * Fetch all issues from the database
	 * 
	 * @return List<Issue>
	 * @throws ApiException
	 */
	public List<Issue> fetchAllIssues() throws ApiException {
		List<Issue> issues = repository.findAll();
		if (issues.isEmpty()) {
			throw new ApiException("No issues found");
		}
		return issues;
	}

	/**
	 * Fetch an Issue by its ID
	 * 
	 * @param id
	 * @return com.cts.model.Issue
	 * @throws ApiException
	 */
	public Issue fetchIssueById(int id) throws ApiException {
		Optional<Issue> fetchedIssue = repository.findById(id);
		if (fetchedIssue.isEmpty()) {
			throw new ApiException("Issue with requested id wasn't found");
		}
		return fetchedIssue.get();
	}

	/**
	 * Fetched all issues associated with an employee
	 * 
	 * @param empId
	 * @return
	 * @throws ApiException
	 */
	public List<Issue> fetchIssuesByEmpId(int empId) throws ApiException {
		List<Issue> fetchedIssues = repository.findByEmpId(empId).get();
		if (fetchedIssues.isEmpty()) {
			throw new ApiException("No issue has been raised by you yet");
		}
		return fetchedIssues;
	}

	/**
	 * Delete an Issue by its Id
	 * 
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	public String deleteIssue(int id) throws ApiException {
		Optional<Issue> issue = repository.findById(id);
		issue.orElseThrow(() -> new ApiException("Issue with requested id wasn't found"));
		repository.deleteById(id);
		return "Successfully deleted";
	}

	/**
	 * Update response to an Issue
	 * 
	 * @param responseIssue
	 * @return
	 */
	public Issue updateIssue(ResolveIssueRequest responseIssue) {
		Issue existingIssue = repository.findById(responseIssue.getIssueId()).get();
		existingIssue.setResponse(responseIssue.getResponse());
		existingIssue.setSolved(true);
		existingIssue.setLastUpdate(new Date());
		return repository.save(existingIssue);
	}

	/**
	 * Fetch all unresolved issues
	 * 
	 * @return
	 * @throws ApiException
	 */
	public List<Issue> fetchUnresolvedIssues() throws ApiException {
		List<Issue> unresolved = repository.findBySolved(false).get();
		if (unresolved.isEmpty()) {
			throw new ApiException("All issues are resolved");
		}
		return unresolved;
	}

	/**
	 * Fetch all resolved issues
	 * 
	 * @return
	 * @throws ApiException
	 */
	public List<Issue> fetchResolvedIssues() throws ApiException {
		List<Issue> resolved = repository.findBySolved(true).get();
		if (resolved.isEmpty()) {
			throw new ApiException("No issue has been resolved yet");
		}
		return resolved;
	}

}
