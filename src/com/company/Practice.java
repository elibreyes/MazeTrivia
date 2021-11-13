package com.company;

/**
 * Create by elijahreyes on 11/12/21.
 */
public class Practice {
    //0 is up       1 is right      2 is down       3 is left
    public static void main(String[] args){
        Maze m = new Maze();
        System.out.println(m);
        m.updateChoice(1);
        m.openRoom();
        m.movePosition();
        System.out.println("open door to the right");
        System.out.println(m);
        m.updateChoice(2);
        m.openRoom();
        m.movePosition();
        System.out.println("open door down below");
        System.out.println(m);
        m.updateChoice(1);
        m.openRoom();
        m.movePosition();
        System.out.println("open door below");
        System.out.println(m);
        m.updateChoice(0);
        m.lockRoom();
        m.movePosition();
        System.out.println("lock door above");
        System.out.println(m);
    }
}
