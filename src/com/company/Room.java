package com.company;

import java.io.Serializable;

/**
 * Create by elijahreyes on 11/10/21.
 */
public class Room implements Serializable {
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

    public String toString(){
        String result;
        //* represents available room -> status = 0
        if(getStatus() == 0){
            result = "* ";
        }
        //o represents opened room -> status = 1
        else if (getStatus() == 1){
            result = "o ";
        }
        //x represents locked room -> status = 2
        else{
            result = "x ";
        }
        return result;
    }
}
