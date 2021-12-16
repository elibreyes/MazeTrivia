package com.company;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Multiple choice question class holds multiple choice questions
 */
public class MultipleChoiceQuestion extends AbstractQuestion {
    private final ArrayList<String> myPossibleAnswers;

    /**
     *
     * @param theQuestion holds the questions
     * @param theAnswer holds the answer
     * @param thePossibleAnswers holds other options however are not the correct answer
     */
    public MultipleChoiceQuestion(final String theQuestion, final String theAnswer, final ArrayList<String> thePossibleAnswers) {
        super(theQuestion, theAnswer);
        Collections.shuffle(thePossibleAnswers);
        myPossibleAnswers = thePossibleAnswers;

    }

    /**
     * Method used to return possible answers for the question
     * @return returns an arraylist of possible answers for the question
     */
    public ArrayList<String> getPossibleAnswers() {
        return myPossibleAnswers;
    }

    /**
     * Method used to return question and options
     * @return returns the question and possible options
     */
    public String getMyQuestion() {
        return super.getMyQuestion() + "\n" + getPossibleAnswers().toString();
    }
}


