package com.game.questionsanswers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static final int MAX_QUESTIONS = 20; // Número máximo de preguntas que se pueden hacer en un juego
    private int score;
    private int questionCount;
    private List<Question> questions;
    private Question currentQuestion;
    private Random random;

    public Game(List<Question> questions) {
        this.score = 0;
        this.questionCount = 0;
        this.questions = new ArrayList<>(questions); // Copia la lista para evitar modificar la original
        this.random = new Random();
        this.currentQuestion = getNextQuestion();
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score += 10;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void incrementQuestionCount() {
        this.questionCount++;
        this.currentQuestion = getNextQuestion(); // Actualiza la pregunta actual
    }

    public void reset() {
        this.score = 0;
        this.questionCount = 0;
    }

    public boolean hasMoreQuestions() {
        return questionCount < MAX_QUESTIONS && !questions.isEmpty();
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    private Question getNextQuestion() {
        if (hasMoreQuestions()) {
            int randomIndex = random.nextInt(questions.size()); // Genera un índice aleatorio
            return questions.remove(randomIndex); // Devuelve y elimina la pregunta en el índice aleatorio
        }
        return null; // No hay más preguntas
    }

    public void addQuestions(List<Question> additionalQuestions) {
        questions.addAll(additionalQuestions);
    }
}
