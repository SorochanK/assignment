package com.aposhealth.assignment.controller;

import com.aposhealth.assignment.entitity.Actor;
import com.aposhealth.assignment.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ActorController {

	private final ActorService actorService;

	@GetMapping("/api/v1/actors")
	public ResponseEntity<List<Actor>> getAllActorsByJob(@RequestParam final String job) {
		var actors = actorService.findAllByJob(job);
		return actors.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(actors);
	}
}
