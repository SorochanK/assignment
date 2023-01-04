package com.aposhealth.assignment.entitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Getter
@Document(collection = "actors")
public class Actor {

	@Id
	private String id;

	private String name;

	private String birthDate;

	private Gender gender;

	private List<String> jobs;

	private String imageUrl;
}
