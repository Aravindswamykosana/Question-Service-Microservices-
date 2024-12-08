package com.aravind.QuestionService.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DataNotFoundException extends Exception{
	String msg="data not found in our database..!";
}
