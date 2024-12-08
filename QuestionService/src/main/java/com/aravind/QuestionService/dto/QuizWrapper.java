package com.aravind.QuestionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizWrapper {
	private int id;
	private String questiionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
}
