package com.company;

import java.io.*;
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
        Maze myMaze = null;

        /**
         * need to have a way to check if they want to load a game or if it is
         * a new game, if it is a new game the following code will be fine otherwise
         * need to use deserialization to pull up results
         */

        if(gameName.equals("new")) {
            myMaze = new Maze();

        } else {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(gameName + ".txt"));
                myMaze = (Maze)in.readObject();

                /* need to do some deserialization */
                System.out.println("deserialize");
                in.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        while(!myMaze.solved() /*&& m.solvable*/ ){

            PrintMaze.print(myMaze);
            System.out.println("Where would you like to move\n0 is up || 1 is right || 2 is down || 3 is left || 4 to exit");
            myChoice = myReader.nextInt();
            if(myChoice==4){
                System.out.println("Please enter the file name to be saved as");
                gameName = myReader.next();
                try {
//                    System.out.println(gameName);
                    FileOutputStream fout = new FileOutputStream(gameName + ".txt");
                    ObjectOutputStream out = new ObjectOutputStream(fout);
                    out.writeObject(myMaze);
                    out.flush();
                    out.close();
                    System.out.println("serialized " + gameName + " as " + gameName);
                    return;
                }catch(Exception e){
                    System.out.println(e);
                }
            }else {
                myMaze.updateChoice(myChoice);
                System.out.println("Did you get the question correct?");
                myPass = myReader.nextBoolean();
                if (myPass) {
                    myMaze.openRoom();
                    myMaze.movePosition();
                } else {
                    myMaze.lockRoom();
                    myMaze.resetRoom();
                }
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
            if(mySavedGame.equals("new")){
                break;
            }
            File myFile = new File(mySavedGame + ".txt");
            exists = myFile.exists();

        } while(!exists);

        return mySavedGame;
    }
}
