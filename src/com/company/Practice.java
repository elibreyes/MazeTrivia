package com.company;

import java.util.Scanner;

/**
 * Create by elijahreyes on 11/12/21.
 */
public class Practice {
    //0 is up       1 is right      2 is down       3 is left
    public static void main(String[] args){
//        Maze m = new Maze();
//
//        System.out.println(m);
//
//        //move to the right and open the door
//        m.updateChoice(1);
//        m.openRoom();
//        m.movePosition();
//        System.out.println("move to the right and open the door");
//        System.out.println(m);
//
//        //move down and open the door
//        m.updateChoice(2);
//        m.openRoom();
//        m.movePosition();
//        System.out.println("move down and open the door");
//        System.out.println(m);
//
//        //move to the right and open the door
//        m.updateChoice(1);
//        m.openRoom();
//        m.movePosition();
//        System.out.println("move to the right and open the door");
//        System.out.println(m);
//
//        //move up and lock the door
//        m.updateChoice(0);
//        m.lockRoom();
//        m.updateChoice(2);
//        System.out.println("move up and lock the door");
//        System.out.println(m);
//
//        m.updateChoice(2);
//        m.openRoom();
//        m.movePosition();
//        System.out.println("move down and open the door");
//        System.out.println(m);
        whileTest();
    }

    //assuming that the correct answer is always inputed
    public static void whileTest(){
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
