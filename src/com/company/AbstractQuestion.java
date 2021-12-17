package com.company;

/**
 *  An abstract class of a question
 */
public abstract class AbstractQuestion {
    /**
     * myQuestion holds the question
     */
    private final String myQuestion;
    /**
     * myAnswer holds the answer
     */
    private final String myAnswer;

    /**
     * Constructor that takes in a question and answer
     * @param theQuestion is the question
     * @param theAnswer is the answer
     */
    public AbstractQuestion(final String theQuestion, final String theAnswer) {
        myQuestion = theQuestion;
        myAnswer = theAnswer;
    }

    /**
     * Gets the question
     * @return a string of the question
     */
    public String getMyQuestion() {
        return myQuestion;
    }

    /**
     * Gets the answer
     * @return a string of the answer
     */
    public String getMyAnswer() {
        return myAnswer;
    }

    /**
     * Checks whether or not the given answer is equal
     * @param theAnswer the given answer
     * @return a boolean of whether or not the answer is the same
     */
    public boolean checkAnswer(String theAnswer) {
        return myAnswer.equals(theAnswer);
    }


}
