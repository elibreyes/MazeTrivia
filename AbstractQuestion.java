package com.company;

public abstract class AbstractQuestion {
    private String myQuestion;
    private String myAnswer;


    public AbstractQuestion(String theQuestion, String theAnswer) {
        myQuestion = theQuestion;
        myAnswer = theAnswer;
    }

    public String getMyQuestion() {
        return myQuestion;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public boolean checkAnswer(String theAnswer) {
        return myAnswer.equals(theAnswer);
    }


}
