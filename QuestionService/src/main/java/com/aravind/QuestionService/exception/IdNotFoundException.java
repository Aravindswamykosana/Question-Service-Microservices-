package com.aravind.QuestionService.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class IdNotFoundException extends Exception{
	String msg="id not found in our database..!";
}
