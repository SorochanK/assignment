package com.aposhealth.assignment.dao;

import com.aposhealth.assignment.entitity.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends MongoRepository<Actor, String> {

	List<Actor> findActorsByJobsContains(String job);
}
