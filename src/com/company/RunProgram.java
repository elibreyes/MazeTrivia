package com.company;

import java.util.Scanner;

/**
 * Create by elijahreyes on 11/18/21.
 */
public class RunProgram {

    public static void run() {
        System.out.println("Please enter the name of the previously saved game otherwise enter new");
        Scanner myReader = new Scanner(System.in);
        String mySavedGame = myReader.nextLine();
        int myChoice;
        boolean myPass;
        Maze myMaze;

        /**
         * need to have a way to check if they want to load a game or if it is
         * a new game, if it is a new game the following code will be fine otherwise
         * need to use deserialization to pull up results
         */

        if(mySavedGame.equals("new")) {
            myMaze = new Maze();
            myChoice = 0;
            myPass = false;

        } else{
            /* need to do some deserialization */
            System.out.println("deserialize");
            myMaze = new Maze();
            myChoice = 0;
            myPass = false;
        }

        while(!myMaze.solved() /*&& m.solvable*/ ){
            System.out.println(myMaze);

            System.out.println("Where would you like to move\n0 is up || 1 is right || 2 is down || 3 is left");
            myChoice = myReader.nextInt();
            myMaze.updateChoice(myChoice);
            System.out.println("Did you get the question correct?");
            myPass = myReader.nextBoolean();
            if(myPass){
                myMaze.openRoom();
                myMaze.movePosition();
            }
            else{
                myMaze.lockRoom();
                myMaze.resetRoom();
            }
        }
        System.out.println(myMaze);
    }
}
