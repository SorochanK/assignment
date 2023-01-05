package com.aposhealth.assignment.service;

import com.aposhealth.assignment.dao.ActorRepository;
import com.aposhealth.assignment.entitity.Actor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorService {

	private final ActorRepository actorRepository;
	private final ImdbParser imdbParser;

	public List<Actor> findAllByJob(final String job) {
		log.info("finding actors by jobs: {}", job);
		return actorRepository.findActorsByJobsContains(job);
	}

	public void reset() {
		deleteAll();
		var actors = findActorsFromImdb();
		saveAll(actors);
		log.info("reset() worked successfully");
	}

	private List<Actor> findActorsFromImdb() {
		List<String> topActorsIds = imdbParser.getTopActorsUrls();
		return topActorsIds.parallelStream()
			.map(imdbParser::getActorInfo)
			.collect(Collectors.toList());
	}

	private List<Actor> saveAll(List<Actor> actors) {
		log.info("Saving all actors");
		return actorRepository.saveAll(actors);
	}

	private void deleteAll() {
		log.info("Deleting all actors");
		actorRepository.deleteAll();
	}
}
