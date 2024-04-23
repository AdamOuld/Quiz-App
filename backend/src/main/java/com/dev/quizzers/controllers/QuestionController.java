package com.dev.quizzers.controllers;


import com.dev.quizzers.models.Question;
import com.dev.quizzers.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions() {
        return service.getAllQuestions();
    }

    @GetMapping("/{category}")
    public List<Question> findByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void addQuestion(@RequestBody Question question) {
        service.addQuestion(question);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        service.deleteQuestion(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/update/{id}")
    public void updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        service.updateQuestion(id,
                question.getQuestionTitle(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4(),
                question.getRightAnswer(),
                question.getDifficultyLevel(),
                question.getCategory());
    }
}
