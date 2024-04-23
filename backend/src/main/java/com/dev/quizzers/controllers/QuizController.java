package com.dev.quizzers.controllers;


import com.dev.quizzers.models.Answer;
import com.dev.quizzers.models.QuestionWrapper;
import com.dev.quizzers.models.Quiz;
import com.dev.quizzers.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    QuizService service;
    public QuizController(QuizService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title) {
        service.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public List<QuestionWrapper> getQuizById(@PathVariable Integer id) {
        return service.getQuizById(id);
    }

    @GetMapping("/get/{title}")
    public List<QuestionWrapper> getQuizByTitle(@PathVariable String title) {
        return service.getQuizByTitle(title);
    }

    @GetMapping("/getAll")
    public List<List<QuestionWrapper>> getAllQuiz() {
        return service.getAllQuiz();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/results/{id}")
    public Double getResults(@PathVariable Integer id, @RequestBody List<Answer> answers) {
        return service.getResults(id, answers);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{title}")
    public void deleteQuiz(@PathVariable String title) {
        service.deleteQuizByTitle(title);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteQuizById(@PathVariable Integer id) {
        service.deleteQuizById(id);
    }


}
