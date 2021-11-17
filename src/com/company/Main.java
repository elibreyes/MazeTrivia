package com.company;

import java.util.Scanner;

/**
 * Create by elijahreyes on 11/16/21.
 */
public class Main {

    public static void main(String[] args){

        Maze m = new Maze();
        Scanner reader = new Scanner(System.in);
        int choice = 0;
        boolean pass = false;

        while(!m.solved()){
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
