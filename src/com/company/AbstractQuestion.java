package com.company;

/**
 *
 */
public abstract class AbstractQuestion {
    /**
     *
     */
    private final String myQuestion;
    /**
     *
     */
    private final String myAnswer;

    /**
     *
     * @param theQuestion
     * @param theAnswer
     */
    public AbstractQuestion(final String theQuestion, final String theAnswer) {
        myQuestion = theQuestion;
        myAnswer = theAnswer;
    }

    /**
     *
     * @return
     */
    public String getMyQuestion() {
        return myQuestion;
    }

    /**
     *
     * @return
     */
    public String getMyAnswer() {
        return myAnswer;
    }

    /**
     *
     * @param theAnswer
     * @return
     */
    public boolean checkAnswer(String theAnswer) {
        return myAnswer.equals(theAnswer);
    }


}
