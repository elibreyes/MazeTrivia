package com.company;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    @Test
    public void answerCorrectly() {
        Question test = new Question();
        assertTrue(test.checkAnswer("10"));
    }
    @Test
    public void answerWrong() {
        Question test = new Question();
        assertFalse(test.checkAnswer("wrong"));
    }
}