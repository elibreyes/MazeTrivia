package com.company;

/**
 * Acts like the view part of the code that prints whatever is necessary
 */
public class PrintMaze {

    /**
     * Prints out the content of the maze
     * @param theMaze the instance of the maze
     */
    public static void print(Maze theMaze){
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
}
