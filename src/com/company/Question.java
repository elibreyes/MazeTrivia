package com.company;

// Mock up, we can possibly split into three classes, True/False, Multiple choice...
public class Question {
    private String myQuestion;
    private String myAnswer;


    public Question() {
        this.myQuestion = "What is 5 + 5?";
        this.myAnswer = "10";
    }


    public String getQuestion() {
        return myQuestion;
    }


    public String getAnswer() {
        return myAnswer;
    }

    public boolean checkAnswer(String theAnswer) {
        return myAnswer.equals(theAnswer);
    }
}
