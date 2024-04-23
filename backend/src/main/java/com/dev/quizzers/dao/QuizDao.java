package com.dev.quizzers.dao;

import com.dev.quizzers.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {


    Quiz getQuizById(Integer id);

    Quiz getQuizByTitle(String title);
}
