package com.company;

import java.util.Scanner;

/**
 * Create by elijahreyes on 11/16/21.
 */
public class Main {

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter the name of the previously saved game otherwise enter new");
        String savedGame = reader.nextLine();
        Maze m;
        int choice;
        boolean pass;

        /**
         * need to have a way to check if they want to load a game or if it is
         * a new game, if it is a new game the following code will be fine otherwise
         * need to use deserialization to pull up results
         */

        if(savedGame.equals("new")) {
            m = new Maze();
            choice = 0;
            pass = false;

        } else{
            System.out.println("deserialize");
            m = new Maze();
            choice = 0;
            pass = false;
        }

        while(!m.solved() /*&& m.solvable*/ ){
            System.out.println(m);
            System.out.println("Where would you like to move\n0 is up || 1 is right || 2 is down || 3 is left");
            choice = reader.nextInt();
            m.updateChoice(choice);
            System.out.println("Did you get the question correct?");
            pass = reader.nextBoolean();
            if(pass){
                m.openRoom();
                m.movePosition();
            }
            else{
                m.lockRoom();
                m.resetRoom();
            }
        }
        System.out.println(m);
    }

}
