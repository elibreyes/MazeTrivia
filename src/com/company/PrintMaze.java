package com.company;

/**
 * Create by elijahreyes on 11/18/21.
 */
public class PrintMaze {

    //prints out the maze
    public static void print(Maze theMaze){
        System.out.println(theMaze);
    }

    //prints out a winning statement
    public static void printWin(){
        System.out.println("You won!");
    }

    //prints out a losing statement
    public static void printLost(){
        System.out.println("You Lost!");
    }
}
