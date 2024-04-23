package com.dev.quizzers.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity

@Data public class Question {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    Integer id;
    String questionTitle;
    String option1;
    String option2;
    String option3;
    String option4;
    String rightAnswer;
    String difficultyLevel;
    String category;

}
