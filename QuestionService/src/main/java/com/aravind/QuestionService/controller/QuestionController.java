package com.aravind.QuestionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aravind.QuestionService.dto.Question;
import com.aravind.QuestionService.dto.QuizAnswerResponse;
import com.aravind.QuestionService.dto.QuizWrapper;
import com.aravind.QuestionService.exception.DataNotFoundException;
import com.aravind.QuestionService.exception.IdNotFoundException;
import com.aravind.QuestionService.service.QuestionService;
import com.example.QuestionService.util.ResponseStructure;


@RestController
@RequestMapping("questions")
public class QuestionController {
	@Autowired
	private QuestionService service;
	
	@Autowired
	private Environment environment; //load balancer used to tell which instance used
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Question>> saveQuestion(@RequestBody Question question){
		return service.saveQuestion(question);
	}
	
	@GetMapping("/getQuestion")
	public ResponseEntity<ResponseStructure<Question>> getQuestion(@RequestParam int id) throws IdNotFoundException{
		return service.getQuestion(id);
	}
	
	@GetMapping("/getAllQuestion")
	public ResponseEntity<ResponseStructure<List<Question>>> getAllQuestion() throws IdNotFoundException, DataNotFoundException{
		return service.getAllQuestion();
	}
	
	@PostMapping("/updateQuestion")
	public ResponseEntity<ResponseStructure<Question>> updateQuestion(@RequestBody Question question) throws IdNotFoundException{
		return service.updateQuestion(question);
	}
	
	@GetMapping("/deleteQuestion")
	public ResponseEntity<ResponseStructure<Question>> deleteQuestion(@RequestParam int id) throws IdNotFoundException{
		return service.deleteQuestion(id);
	}
	
	@GetMapping("/getQuestionByCatagory")
	public ResponseEntity<ResponseStructure<List<Question>>> getQuestionByCatagory(@RequestParam String catagory) throws DataNotFoundException{
		return service.getQuestionByCatagory(catagory);
	}
	
	@GetMapping("/generateQuestion")
	public ResponseEntity<ResponseStructure<List<Integer>>> generateQuestion(@RequestParam String catagory) throws DataNotFoundException{
		return service.generateQuestion(catagory);
	}
	
	@PostMapping("/getQuestionsById")
	public ResponseEntity<ResponseStructure<List<QuizWrapper>>> getQuestionsById(@RequestBody List<Integer> ids) throws DataNotFoundException{
		System.out.println(environment.getProperty("localhost.server.port"));
		return service.getQuestionsById(ids);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<ResponseStructure<Integer>> getScore(@RequestBody List<QuizAnswerResponse> responses) throws DataNotFoundException{
		return service.getScore(responses);
	}
}
