package com.company;

/**
 * Create by elijahreyes on 11/10/21.
 */
public class Room {
    private int myStatus;

    // 0 will represent an untouched status
    public Room(){
        myStatus = 0;
    } 

    //standard getter method
    int getStatus(){
        return myStatus;
    }
    
    // 1 represents opened room and 2 represents locked room
    void changeStatus(boolean pass){
        if(pass){
            myStatus = 1;
        }
        else{
            myStatus = 2;
        }
    }

    //* represents available room, o represents opened room, and x represents locked room
    public String toString(){
        String result;
        if(getStatus() == 0){
            result = "* ";
        }
        else if (getStatus() == 1){
            result = "o ";
        }
        else{
            result = "x ";
        }
        return result;
    }
}
