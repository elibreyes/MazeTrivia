package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class MultipleChoiceQuestion extends AbstractQuestion {
    ArrayList<String> myPossibleAnswers;

    public MultipleChoiceQuestion(String theQuestion, String theAnswer, ArrayList<String> thePossibleAnswers) {
        super(theQuestion, theAnswer);
        Collections.shuffle(thePossibleAnswers);
        myPossibleAnswers = thePossibleAnswers;

    }
    public ArrayList<String> getPossibleAnswers() {
        return myPossibleAnswers;
    }
}


