package com.game.questionsanswers.service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.game.questionsanswers.model.Question;
import com.game.questionsanswers.model.QuestionCategory;

@Service
public class QuestionService {
    private Random random;

    public QuestionService() {
        random = new Random();
    }

    public List<Question> loadQuestionsByCategory(String category) {
        List<Question> questions = QuestionCategory.getQuestionsByCategory(category);
        Collections.shuffle(questions, random); // Mezcla las preguntas con un Random para aleatoriedad reproducible
        return questions;
    }

    public boolean checkAnswer(Question question, String answer) {
        return question.getAnswer().equalsIgnoreCase(answer);
    }
}


