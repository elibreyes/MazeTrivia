package com.company;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Standard Test class for unit testing each type of questions
 */
public class QuestionTest {

    /**
     * Tests T/F question as if it was correct
     */
    @Test
    public void tFCorrect() {
        TrueFalseQuestion test = new TrueFalseQuestion("Sharks are mammals.", "F");
        assertTrue(test.checkAnswer("F"));
    }

    /**
     * Tests T/F question as if it was incorrect
     */
    @Test
    public void tFIncorrect() {
        TrueFalseQuestion test = new TrueFalseQuestion("Sharks are mammals.", "F");
        assertFalse(test.checkAnswer("T"));
    }

    /**
     * Tests MC question as if it was correct
     */
    @Test
    public void mcCorrect(){
        ArrayList<String> possible = new ArrayList<>();
        possible.add("Berlin");
        possible.add("Munich");
        possible.add("Hamburg");
        possible.add("Cologne");
        MultipleChoiceQuestion test = new MultipleChoiceQuestion("What is the capital of Germany?", "Berlin", possible);
        assertTrue(test.checkAnswer("Berlin"));
    }

    /**
     * Tests MC question as if it was incorrect
     */
    @Test
    public void mcIncorrect(){
        ArrayList<String> possible = new ArrayList<>();
        possible.add("Berlin");
        possible.add("Munich");
        possible.add("Hamburg");
        possible.add("Cologne");
        MultipleChoiceQuestion test = new MultipleChoiceQuestion("What is the capital of Germany?", "Berlin", possible);
        assertFalse(test.checkAnswer("Munich"));
    }

    /**
     * Tests SA question as if it was correct
     */
    @Test
    public void sACorrect(){
        ShortAnswerQuestion test = new ShortAnswerQuestion("What is the value of x if 5x = 25?", "5");
        assertTrue(test.checkAnswer("5"));

    }

    /**
     * Tests SA question as if it was incorrect
     */
    @Test
    public void sAIncorrect(){
        ShortAnswerQuestion test = new ShortAnswerQuestion("What is the value of x if 5x = 25?", "5");
        assertFalse(test.checkAnswer("25"));
    }
}
