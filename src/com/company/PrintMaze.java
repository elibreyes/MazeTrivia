package com.company;

/**
 * Acts like the view part of the code that prints whatever is necessary
 */
public class PrintMaze {

    /**
     * Prints out the content of the maze
     * @param theMaze the instance of the maze
     */
    public static void print(final Maze theMaze){
        System.out.println(theMaze);
    }

    /**
     * Prints a winner tag for the user to know they won
     */
    public static void printWin(){
        System.out.println("You won!");
    }

    /**
     * Prints a loser tag for the user to know they lost
     */
    public static void printLost(){
        System.out.println("You Lost!");
    }

    /**
     * Prints out the rules of the game
     */
    public static void rules(){
        System.out.println("INSTRUCTIONS:\n" +
                "Goal of the game is to start at the top left and reach the bottom right\n" +
                "Can only move to doors next to you and must answer them correctly to move in\n" +
                "\u001B[31mx\u001B[0m represents a locked door that cannot be accessed anymore\n" +
                "\u001B[32mo\u001B[0m represents an opened door that can be entered anytime\n" +
                "\u001B[34m*\u001B[0m represents an available door that must be answered first\n" +
                "\u001B[33mU\u001B[0m represents the user\n");
    }

    /**
     * Prints out background about the game and creators
     */
    public static void about(){
        System.out.println("Welcome to TriviaMaze!\nThis program was created by" +
                "Sam Huynh, Ryan Trepanier, and Elijah Reyes\n" +
                "This program was created for the TCSS360B course.\n" +
                "It does currently run on console due to limited time.\n" +
                "However the GUI version is coming out soon!\n");
    }
}
