package com.game.questionsanswers.model;

import java.util.List;

public class QuestionCategory {

    public static List<Question> getQuestionsByCategory(String category) {
        switch (category.toLowerCase()) {
            case "historia":
                return HistoryQuestions.getQuestions();
            case "capitales":
                return CapitalQuestions.getQuestions();
            case "países":
                return CountryQuestions.getQuestions();
            default:
                throw new IllegalArgumentException("Categoría no válida: " + category);
        }
    }
}
