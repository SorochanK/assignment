package com.aposhealth.assignment.util;

import com.aposhealth.assignment.entitity.Gender;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class AssignmentUtil {

	private static final String ACTRESS = "Actress";
	private static final String DATE_PATTERN = "MMMM d, yyyy";

	public static LocalDate convertToDate(final String dateString) {
		DateTimeFormatter df = new DateTimeFormatterBuilder()
			.parseCaseInsensitive()
			.appendPattern(DATE_PATTERN)
			.toFormatter(Locale.ENGLISH);
		return LocalDate.parse(dateString, df);
	}

	public static Gender determineGenderByJob(List<String> jobs) {
		return jobs.contains(ACTRESS) ? Gender.FEMALE : Gender.MALE;
	}

}
