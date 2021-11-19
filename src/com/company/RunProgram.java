package com.company;

import java.io.File;
import java.util.Scanner;

/**
 * Create by elijahreyes on 11/18/21.
 */
public class RunProgram {

    private static PrintMaze myPrintMaze;

    public static void run() {
        Scanner myReader = new Scanner(System.in);
        String gameName = getName(myReader);


        int myChoice;
        boolean myPass;
        Maze myMaze;

        /**
         * need to have a way to check if they want to load a game or if it is
         * a new game, if it is a new game the following code will be fine otherwise
         * need to use deserialization to pull up results
         */

        if(gameName.equals("new")) {
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

            PrintMaze.print(myMaze);
            System.out.println("Where would you like to move\n0 is up || 1 is right || 2 is down || 3 is left"/*|| 4 to exit*/);
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
        PrintMaze.print(myMaze);
    }


    public static String getName(Scanner theReader){
        String mySavedGame;
        boolean exists;
        do {
            System.out.println("Please enter the name of the previously saved game otherwise enter new");
            mySavedGame = theReader.nextLine();
            File myFile = new File(mySavedGame + ".txt");
            exists = myFile.exists();

        } while((!(mySavedGame.equals("new"))) || (!exists));

        return mySavedGame;
    }
}
