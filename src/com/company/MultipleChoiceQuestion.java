package com.company;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends AbstractQuestion {
    ArrayList<String> myPossibleAnswers;

    public MultipleChoiceQuestion(String theQuestion, String theAnswer, ArrayList<String> thePossibleAnswers) {
        super(theQuestion, theAnswer);
        myPossibleAnswers = thePossibleAnswers;

    }
    public ArrayList<String> getPossibleAnswers() {
        return myPossibleAnswers;
    }
}


