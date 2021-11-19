package com.company;

import java.util.ArrayList;

public class ShortAnswerQuestion extends AbstractQuestion{
    private ArrayList<String> myPossibleAnswers;

    public ShortAnswerQuestion(String theQuestion, String theAnswer, ArrayList<String> thePossibleAnswers) {
        super(theQuestion, theAnswer);
        myPossibleAnswers = thePossibleAnswers;
    }

    public ArrayList<String> getPossibleAnswers() {
        return myPossibleAnswers;
    }


}