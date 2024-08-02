package com.game.questionsanswers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.questionsanswers.model.Game;
import com.game.questionsanswers.model.QuestionCategory;
import com.game.questionsanswers.service.QuestionService;

@Controller
public class GameController {

    private Game game;
    private final QuestionService questionService;

    @Autowired
    public GameController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String welcome(Model model) {
        return "welcome";
    }

    @PostMapping("/startGame")
    public String startGame(@RequestParam("category") String category, Model model) {
        try {
            game = new Game(QuestionCategory.getQuestionsByCategory(category));
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "welcome";
        }
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String game(Model model) {
        if (game == null) {
            return "redirect:/";
        }
        if (!game.hasMoreQuestions()) {
            model.addAttribute("score", game.getScore());
            return "result";
        }
        model.addAttribute("question", game.getCurrentQuestion().getQuestion());
        model.addAttribute("score", game.getScore());
        model.addAttribute("questionCount", game.getQuestionCount());
        return "game";
    }

    @PostMapping("/answer")
    public String answer(@RequestParam("answer") String answer, Model model) {
        boolean correct = questionService.checkAnswer(game.getCurrentQuestion(), answer);
        if (correct) {
            game.incrementScore();
        }
        game.incrementQuestionCount();
        model.addAttribute("correct", correct);
        model.addAttribute("score", game.getScore());
        return "feedback";
    }

    @GetMapping("/restart")
    public String restart() {
        game = null;
        return "redirect:/";
    }
}


