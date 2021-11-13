package com.company;

/**
 * Create by elijahreyes on 11/12/21.
 */
public class Practice {
    //0 is up       1 is right      2 is down       3 is left
    public static void main(String[] args){
        Maze m = new Maze();

        System.out.println(m);

        //move to the right and open the door
        m.updateChoice(1);
        m.openRoom();
        m.movePosition();
        System.out.println("move to the right and open the door");
        System.out.println(m);

        //move down and open the door
        m.updateChoice(2);
        m.openRoom();
        m.movePosition();
        System.out.println("move down and open the door");
        System.out.println(m);

        //move to the right and open the door
        m.updateChoice(1);
        m.openRoom();
        m.movePosition();
        System.out.println("move to the right and open the door");
        System.out.println(m);

        //move up and lock the door
        m.updateChoice(0);
        m.lockRoom();
        m.updateChoice(2);
        System.out.println("move up and lock the door");
        System.out.println(m);

        m.updateChoice(2);
        m.openRoom();
        m.movePosition();
        System.out.println("move down and open the door");
        System.out.println(m);
    }
}
