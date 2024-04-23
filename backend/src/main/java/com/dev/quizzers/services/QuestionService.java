package com.dev.quizzers.services;


import com.dev.quizzers.dao.QuestionDao;
import com.dev.quizzers.models.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    QuestionDao dao;

    public QuestionService(QuestionDao dao) {
        this.dao = dao;
    }

    public void addQuestion(Question question) {
        dao.save(question);
    }

    public List<Question> getAllQuestions(){
        return dao.findAll();
    }


    public List<Question> findByCategory(String category) {
        return dao.findByCategory(category);
    }

    public void deleteQuestion(Integer id) {
        dao.deleteById(id);
    }

    public void updateQuestion(Integer questionId,
                               String questionTitle,
                               String option1,
                               String option2,
                               String option3,
                               String option4,
                               String rightAnswer,
                               String difficultyLevel,
                               String category) {
        Question question = dao.findById(questionId).orElse(null);
        if (question != null) {
            question.setQuestionTitle(questionTitle);
            question.setOption1(option1);
            question.setOption2(option2);
            question.setOption3(option3);
            question.setOption4(option4);
            question.setRightAnswer(rightAnswer);
            question.setDifficultyLevel(difficultyLevel);
            question.setCategory(category);
            dao.save(question);
        }
    }
}
