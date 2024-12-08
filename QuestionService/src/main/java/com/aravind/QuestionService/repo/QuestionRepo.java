package com.aravind.QuestionService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aravind.QuestionService.dto.Question;


public interface QuestionRepo extends JpaRepository<Question, Integer>{
	List<Question> findQuestionByCatagory(String catagory);
	
	@Query(value = "SELECT q.id FROM question q WHERE q.catagory = :catagory", nativeQuery = true)
	List<Integer> findRandomQuestionsByCatagory(String catagory);

}
