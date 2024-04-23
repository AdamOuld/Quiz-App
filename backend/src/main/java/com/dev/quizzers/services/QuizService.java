package com.dev.quizzers.services;


import com.dev.quizzers.dao.QuestionDao;
import com.dev.quizzers.dao.QuizDao;
import com.dev.quizzers.models.Answer;
import com.dev.quizzers.models.Question;
import com.dev.quizzers.models.QuestionWrapper;
import com.dev.quizzers.models.Quiz;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    QuizDao quizDao;
    QuestionDao questionDao;

    public QuizService(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }

    public void createQuiz(String category, Integer numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
    }

    public List<QuestionWrapper> getQuizById(Integer id) {
        Quiz quiz = quizDao.getQuizById(id);
        List<Question> questions = quiz.getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question question : questions) {
            QuestionWrapper temp = new QuestionWrapper(question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4());
            questionWrappers.add(temp);
        }
        return questionWrappers;
    }

    public List<QuestionWrapper> getQuizByTitle(String title) {
        Quiz quiz = quizDao.getQuizByTitle(title);
        List<Question> questions = quiz.getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question question : questions) {
            QuestionWrapper temp = new QuestionWrapper(question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4());
            questionWrappers.add(temp);
        }
        return questionWrappers;
    }

    public Double getResults(Integer id, List<Answer> answers) {
        Quiz quiz = quizDao.getQuizById(id);
        Double totalQuestions = (double) quiz.getQuestions().size();
        Double score = (double) 0;
        for (Answer answer : answers) {
            Integer questionId = answer.getId();
            Question currentQuestion = questionDao.findById(questionId).orElse(null);
            if (currentQuestion != null && currentQuestion.getRightAnswer().equals(answer.getUserAnswer())) {
                score++;
            }
        }
        return score/totalQuestions;
    }

    public void deleteQuizByTitle(String title) {
        Quiz quiz = quizDao.getQuizByTitle(title);
        Integer id = quiz.getId();
        quizDao.deleteById(id);
    }

    public List<List<QuestionWrapper>> getAllQuiz() {
        List<Quiz> quizzes = quizDao.findAll();
        List<List<QuestionWrapper>> userQuizzes = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            List<Question> questions = quiz.getQuestions();
            List<QuestionWrapper> questionWrappers = new ArrayList<>();
            for (Question question : questions) {
                QuestionWrapper temp = new QuestionWrapper(question.getId(),
                        question.getQuestionTitle(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4());
                questionWrappers.add(temp);
            }
            userQuizzes.add(questionWrappers);
        }
        return userQuizzes;
    }

    public void deleteQuizById(Integer id) {
        quizDao.deleteById(id);
    }
}
