package com.dev.quizzers.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;



@Entity
@Data
public class Quiz {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    String title;
    @ManyToMany
    List<Question> questions;
}
