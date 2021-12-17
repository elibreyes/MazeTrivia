package com.company;

import org.junit.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Standard test class for testing Maze functions
 */
public class MazeTest {

    /**
     * Tests to confirm movement
     */
    @Test
    public void moveRightDown() {
        Maze testMaze = new Maze();
        testMaze.updateChoice(1);
        testMaze.movePosition();
        testMaze.updateChoice(2);
        testMaze.movePosition();
        assertEquals(testMaze.getMyPosition(), new Point(1, 1));
    }

    /**
     * Tests to confirm a lockout
     */
    @Test
    public void lockedOut() {
        Maze testMaze = new Maze();
        testMaze.updateChoice(1);
        testMaze.lockRoom();
        testMaze.resetRoom();
        testMaze.updateChoice(2);
        testMaze.lockRoom();
        assertEquals(testMaze.solvable(), false);
    }

    /**
     * Test to confirm a solved maze
     */
    @Test
    public void solved(){
        Maze testMaze = new Maze();
        testMaze.updateChoice(1);
        testMaze.movePosition();
        testMaze.updateChoice(1);
        testMaze.movePosition();
        testMaze.updateChoice(1);
        testMaze.movePosition();
        testMaze.updateChoice(2);
        testMaze.movePosition();
        testMaze.updateChoice(2);
        testMaze.movePosition();
        testMaze.updateChoice(2);
        testMaze.movePosition();
        assertEquals(testMaze.solved(),true);

    }
}