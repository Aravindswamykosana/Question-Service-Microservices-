package com.aravind.QuestionService.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aravind.QuestionService.dto.Question;
import com.aravind.QuestionService.dto.QuizAnswerResponse;
import com.aravind.QuestionService.dto.QuizWrapper;
import com.aravind.QuestionService.exception.IdNotFoundException;
import com.aravind.QuestionService.repo.QuestionRepo;
@Repository
public class QuestionDao {
	@Autowired
	private QuestionRepo repo;
	
	public Question saveQuestion(Question question) {
		return repo.save(question);
	}
	
	public Question getQuestion(int id) {
		Optional<Question> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
	
	public List<Question> getAllQuestion(){
		return repo.findAll();
	}
	
	public Question updateQuestion(Question question) throws IdNotFoundException {
	    Optional<Question> dbOptional = repo.findById(question.getId());
	    
	    if (dbOptional.isPresent()) {
	        Question db = dbOptional.get();
	        
	        if (question.getQuestiionTitle() != null) {
	            db.setQuestiionTitle(question.getQuestiionTitle());
	        }
	        if (question.getOption1() != null) {
	            db.setOption1(question.getOption1());
	        }
	        if (question.getOption2() != null) {
	            db.setOption2(question.getOption2());
	        }
	        if (question.getOption3() != null) {
	            db.setOption3(question.getOption3());
	        }
	        if (question.getOption4() != null) {
	            db.setOption4(question.getOption4());
	        }
	        if (question.getDifficultyLevel() != null) {
	            db.setDifficultyLevel(question.getDifficultyLevel());
	        }
	        if (question.getRightAnswer() != null) {
	            db.setRightAnswer(question.getRightAnswer());
	        }
	        return repo.save(db);
	    } else {
	        return null;
	    }
	}

	public Question deleteQuestion(int id) {
		Optional<Question> db = repo.findById(id);
		if(db.isPresent()) {
			Question d = db.get();
			repo.deleteById(id);
			return d;
		}
		else {
			return null;
		}
	}
	
	public List<Question> getQuestionByCatagory(String catagory){
		List<Question> db = repo.findQuestionByCatagory(catagory);
		if(db!=null) {
			return db;
		}
		else {
			return null;
		}
	}

	public List<Integer> generateQuestion(String catagory) {
		List<Integer> db = repo.findRandomQuestionsByCatagory(catagory);
		if(db!=null) {
			return db;
		}
		else {
			return null;
		}
	}

	public List<QuizWrapper> getQuestionsById(List<Integer> ids) {
		List<QuizWrapper> qw=new ArrayList<QuizWrapper>();
		List<Question> q=new ArrayList<Question>();
		for(Integer i:ids) {
			if(i!=null) {
				Question db = repo.findById(i).get();
				q.add(db);
			}
		}
		for(Question q1:q) {
			QuizWrapper qw1=new QuizWrapper();
			qw1.setId(q1.getId());
			qw1.setQuestiionTitle(q1.getQuestiionTitle());
			qw1.setOption1(q1.getOption1());
			qw1.setOption2(q1.getOption2());
			qw1.setOption3(q1.getOption3());
			qw1.setOption4(q1.getOption4());
			qw.add(qw1);
		}
		return qw;
	}

	public Integer getScore(List<QuizAnswerResponse> responses){
		int right=0;
		for(QuizAnswerResponse q:responses) {
			Question qw=repo.findById(q.getId()).get();
			if(q.getResponse().equals(qw.getRightAnswer())) {
				right++;
			}
		}
		return right;
	}
}
