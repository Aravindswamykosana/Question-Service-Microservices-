package com.aravind.QuestionService.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aravind.QuestionService.Dao.QuestionDao;
import com.aravind.QuestionService.dto.Question;
import com.aravind.QuestionService.dto.QuizAnswerResponse;
import com.aravind.QuestionService.dto.QuizWrapper;
import com.aravind.QuestionService.exception.DataNotFoundException;
import com.aravind.QuestionService.exception.IdNotFoundException;
import com.example.QuestionService.util.ResponseStructure;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@Service
public class QuestionService {
	@Autowired
	private QuestionDao dao;
	
	public ResponseEntity<ResponseStructure<Question>> saveQuestion(Question question){
		Question q = dao.saveQuestion(question);
		ResponseStructure<Question> rs=new ResponseStructure<Question>();
		rs.setData(q);
		rs.setDt(LocalDateTime.now());
		rs.setMessage("your data saved succesfully in our database...!");
		rs.setStatus(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Question>>(rs,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Question>> getQuestion(int id) throws IdNotFoundException{
		Question q = dao.getQuestion(id);
		if(q!=null) {
			ResponseStructure<Question> rs=new ResponseStructure<Question>();
			rs.setData(q);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("your data fetched succesfully in our database...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Question>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new IdNotFoundException();
		}
		
	}

	public ResponseEntity<ResponseStructure<List<Question>>> getAllQuestion() throws DataNotFoundException {
		List<Question> li = dao.getAllQuestion();
		if(li!=null) {
			ResponseStructure<List<Question>> rs=new ResponseStructure<List<Question>>();
			rs.setData(li);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("your data fetched succesfully in our database...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<List<Question>>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new DataNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Question>> updateQuestion(Question question) throws IdNotFoundException {
		Question db = dao.updateQuestion(question);
		if(db!=null) {
			ResponseStructure<Question> rs=new ResponseStructure<Question>();
			rs.setData(db);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("your data updated succesfully in our database...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Question>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Question>> deleteQuestion(int id) throws IdNotFoundException {
		Question db = dao.deleteQuestion(id);
		if(db!=null) {
			ResponseStructure<Question> rs=new ResponseStructure<Question>();
			rs.setData(db);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("your data deleted succesfully in our database...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Question>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Question>>> getQuestionByCatagory(String catagory) throws DataNotFoundException {
		List<Question> ques = dao.getQuestionByCatagory(catagory);
		try{
			if(ques!=null) {
				ResponseStructure<List<Question>> rs=new ResponseStructure<List<Question>>();
				rs.setData(ques);
				rs.setDt(LocalDateTime.now());
				rs.setMessage("your data fetched succesfully in our database...!");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<List<Question>>>(rs,HttpStatus.ACCEPTED);
			}
			else {
				throw new DataNotFoundException();
			}
		}
		catch(Exception ex) {
			throw new DataNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Integer>>> generateQuestion(String catagory) throws DataNotFoundException {
		List<Integer> db = dao.generateQuestion(catagory);
		if(db!=null) {
			ResponseStructure<List<Integer>> rs= new ResponseStructure<List<Integer>>();
			rs.setData(db);
			rs.setDt(LocalDateTime.now());
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMessage("your questions are created..!");
			return new ResponseEntity<ResponseStructure<List<Integer>>>(rs,HttpStatus.CREATED);
		}
		else {
			throw new DataNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<QuizWrapper>>> getQuestionsById(List<Integer> ids) throws DataNotFoundException {
		List<QuizWrapper> db = dao.getQuestionsById(ids);
		if(db!=null) {
			ResponseStructure<List<QuizWrapper>> rs=new ResponseStructure<List<QuizWrapper>>();
			rs.setData(db);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("getting questions succesfully...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<List<QuizWrapper>>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new DataNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<Integer>> getScore(List<QuizAnswerResponse> responses) throws DataNotFoundException {
		Integer db = dao.getScore(responses);
		if(db!=null) {
			ResponseStructure<Integer> rs=new ResponseStructure<Integer>();
			rs.setData(db);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("getting score is: "+db);
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Integer>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new DataNotFoundException();
		}
	}
}
