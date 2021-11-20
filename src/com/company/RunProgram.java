package com.company;

import java.io.*;
import java.util.Scanner;


/**
 * Create by elijahreyes on 11/18/21.
 */
public class RunProgram {

    public static void run() {
        Scanner myReader = new Scanner(System.in);
        String gameName = getName(myReader);
        Maze myMaze = loadMaze(gameName);


        while(!myMaze.solved() /*&& m.solvable*/ ){
            playing(myReader,myMaze);

        }
        PrintMaze.print(myMaze);
        if(myMaze.solved()){
            PrintMaze.printWin();

        }
        else{
            PrintMaze.printLost();
        }
    }

    private static Maze loadMaze(String gameName){
        Maze myMaze = null;
        if(gameName.equals("new")) {
            myMaze = new Maze();

        } else {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(gameName + ".txt"));
                myMaze = (Maze)in.readObject();
                System.out.println("deserialization successful");
                in.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return myMaze;
    }

    private static String getName(Scanner theReader){
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

    private static void saveGame(Scanner myReader, Maze myMaze){
        System.out.println("Please enter the file name to be saved as");
        String gameName = myReader.next();
        try {
            FileOutputStream fout = new FileOutputStream(gameName + ".txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(myMaze);
            out.flush();
            out.close();
            System.out.println("serialization successful file name: " + gameName);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void playing(Scanner myReader, Maze myMaze){
        PrintMaze.print(myMaze);
        System.out.println("Where would you like to move\n0 is up || 1 is right || 2 is down || 3 is left || 4 to exit");
        String myChoice = myReader.next();
        if(myChoice.equals("4")){
            saveGame(myReader, myMaze);
            System.exit(0);
        } else if (myChoice.equals("3") || myChoice.equals("2") || myChoice.equals("1") || myChoice.equals("0")) {
            myMaze.updateChoice(Integer.parseInt(myChoice));
            if(myMaze.checkRoom()) {
                if(myMaze.alreadyOpened()){
                    myMaze.movePosition();
                    return;
                }
                System.out.println("Did you get the question correct?");
                boolean myPass = myReader.nextBoolean();
                if (myPass) {
                    myMaze.openRoom();
                    myMaze.movePosition();
                } else {
                    myMaze.lockRoom();
                    myMaze.resetRoom();
                }
                return;
            }
            myMaze.resetRoom();
            System.out.println();
        }
        else{
            System.out.println("\nNot a valid choice try again\n");
        }
    }

}
