package com.aposhealth.assignment.service;

import com.aposhealth.assignment.entitity.Actor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

import static com.aposhealth.assignment.util.AssignmentUtil.convertToDate;
import static com.aposhealth.assignment.util.AssignmentUtil.determineGenderByJob;

@Service
@Slf4j
public class ImdbParser {

	@Value("${imdb.top-actors.url}")
	private String topActorsListUrl;

	@Value("${imdb.base.url}")
	private String imdbBaseUrl;

	public List<String> getTopActorsUrls() {
		try {
			log.info("fetching top actors by URL");

			var document = Jsoup.connect(topActorsListUrl).get();
			return document.select(".lister-item-header > a").eachAttr("href");
		} catch (IOException e) {
			log.error("error fetching top actors {}", e.getMessage());
			e.printStackTrace();
		}
		return List.of();
	}

	public Actor getActorInfo(final String url) {
		try {
			log.info("fetching actor by URL {}", url);

			var document = Jsoup.connect(imdbBaseUrl + url).get();

			var name = document.selectFirst("[data-testid='hero__pageTitle'] > span").text();
			var jobs = document.select("[class=ipc-inline-list ipc-inline-list--show-dividers sc-856aec89-4 ihBMCA baseAlt] li").eachText();
			var imageUrl = document.selectFirst("[class=ipc-lockup-overlay ipc-focusable]").attr("href");
			var gender = determineGenderByJob(jobs);

			String birthDateText = document.selectFirst("[data-testid='birth-and-death-birthdate'] > span + span").text();
			LocalDate birthDate = convertToDate(birthDateText);

			return Actor.builder()
				.name(name)
				.jobs(jobs)
				.imageUrl(imageUrl)
				.birthDate(birthDate)
				.gender(gender)
				.build();

		} catch (IOException e) {
			log.error("error fetching actor by URL {}", url);
			e.printStackTrace();
		}

		return Actor.builder().build();
	}
}
