package com.company;

import java.awt.*;

public class Maze {

//    private Point myEntrance;
    private Point myExit;
    private Point myPosition;
    private Point myChoice;
    private Room[][] myMaze;

    public Maze() {
        myMaze = new Room[4][4];
        buildMaze();
//        myEntrance = new Point(0,0);
        myPosition = new Point(0,0);
        myExit = new Point(3,3);
    }

    //is this going to be necessary if we already have something to check the status of the room in the room class
//    private boolean checkRoom(){
//        boolean roomStatus = true;
//        if(myMaze[myChoice.x][myChoice.y].getStatus() == 2){
//            roomStatus = false;
//        }
//        return roomStatus;
//    }

    private void lockRoom(){
        myMaze[myChoice.x][myChoice.y].changeStatus(false);
    }

    private void openRoom(){
        myMaze[myChoice.x][myChoice.y].changeStatus(true);
    }

    private void updateChoice(int choice){
        if (choice == 0){               //move up
            myChoice = new Point(myPosition.x, (myPosition.y-1));
        } else if(choice == 1){         //move right
            myChoice = new Point((myPosition.x+1), myPosition.y);
        } else if(choice == 2){         //move down
            myChoice = new Point(myPosition.x, (myPosition.y+1));
        } else {                        //move left
            myChoice = new Point((myPosition.x-1), myPosition.y);
        }
    }

    private void movePosition(){
        myPosition = myChoice;
    }

    //creates the maze and fills it with rooms of * status
    public void buildMaze(){
        for(int i = 0; i < myMaze.length; i++){
            for(int j = 0; j < myMaze[i].length; j++){
                myMaze[i][j] = new Room();
            }
        }
    }

    public boolean solved(){
        return ((myPosition.x == myExit.x)&&(myPosition.y == myExit.y));
    }

    //figure out the traversal stuff in order to check if it is solvable
    public boolean solvable(){
        return false;
    }

    //prints out the maze
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (Room[] rooms : myMaze) {
            for (int j = 0; j < rooms.length; j++) {
                result.append(rooms[j].toString());
            }
            result.append("\n");
        }
        return result.toString();
    }
}
