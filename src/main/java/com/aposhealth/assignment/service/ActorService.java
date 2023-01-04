package com.aposhealth.assignment.service;

import com.aposhealth.assignment.dao.ActorRepository;
import com.aposhealth.assignment.entitity.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

	private final ActorRepository actorRepository;

	public List<Actor> findAllByJob(final String job) {
		return actorRepository.findActorsByJobsContains(job);
	}

}
