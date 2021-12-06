package com.company;
import java.io.Serializable;

/**
 * Room class used to keep track of the status of each room within the maze
 */
public class Room implements Serializable {

    /**
     * private int myStatus contains the status of the room
     */
    private int myStatus;

    /**
     * private enum Status is an enum to hold the different room status
     */
    private enum Status{
        AVAILABLE, OPENED, CLOSED
    }

    /**
     * Standard constructor in which we set our status to be available
     */
    public Room(){myStatus = Status.AVAILABLE.ordinal();
    }

    /**
     * Standard getter method used to retrieve the status
     * @return status of the room
     */
    int getStatus(){
        return myStatus;
    }

    /**
     * Changes the status of the room based on if the question was correct
     * @param pass boolean to determine if the question was answered correctly
     */
    void changeStatus(boolean pass){
        if(pass){
            myStatus = Status.OPENED.ordinal();
        }
        else{
            myStatus = Status.CLOSED.ordinal();
        }
    }

    /**
     * Standard toString method in which * represents an available room,
     * o represents an opened room, and x represents a locked room
     * @return a string version of the room status
     */
    public String toString(){
        String result;
        if(getStatus() == Status.AVAILABLE.ordinal()){
            result = "* ";
        }
        else if (getStatus() == Status.OPENED.ordinal()){
            result = "o ";
        }
        else{
            result = "x ";
        }
        return result;
    }
}
