package com.aravind.QuestionService.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.QuestionService.util.ResponseStructure;


@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundException(IdNotFoundException ex){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(ex.msg);
		rs.setDt(LocalDateTime.now());
		rs.setMessage("data not found...!");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> dataNotFoundException(DataNotFoundException ex){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(ex.msg);
		rs.setDt(LocalDateTime.now());
		rs.setMessage("data not found...!");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
}
