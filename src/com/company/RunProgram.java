package com.company;

import org.sqlite.SQLiteDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Create by elijahreyes on 11/18/21.
 */
public class RunProgram {

    //method used to run the whole program that acts as a controller by itself
    public static void run() {
        Scanner myReader = new Scanner(System.in);
        String gameName = getName(myReader);
        Maze myMaze = loadMaze(gameName/*, myReader*/);


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

    //loads a maze based on if it is a new maze or needs to be deserialized
    private static Maze loadMaze(String gameName/*, Scanner myReader*/){
        Maze myMaze = null;
//        String myDimension;
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

    //method used from loadMaze to get the name of a potential loaded file
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

    //method used to serialize and save the status of the instance of the maze
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

    //method used to run the game and play while accepting decisions
    private static void playing(Scanner myReader, Maze myMaze){
        Random rand = new Random();
        PrintMaze.print(myMaze);
        System.out.println("Where would you like to move\n0 is up || 1 is right || 2 is down || 3 is left || 4 to exit");
        int myChoice = myReader.nextInt();

        if(myChoice == 4){
            saveGame(myReader, myMaze);
            System.exit(0);

        } else if (myChoice == 3 || myChoice == 2 || myChoice == 1 || myChoice == 0) {
            myMaze.updateChoice(myChoice);
            if(myMaze.checkRoom()) {

                if(myMaze.alreadyOpened()){
                    myMaze.movePosition();
                    return;
                }
                boolean myPass = false;
                int randomChoice = rand.nextInt(3);
                SQLiteDataSource ds = null;
                switch (randomChoice) {
                    case 0 -> {
                        TrueFalseQuestion tfq;
                        try {
                            ds = new SQLiteDataSource();
                            ds.setUrl("jdbc:sqlite:TrueFalseQuestions.db");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.exit(0);
                        }
                        String query = "SELECT * FROM TrueFalseQuestions ORDER BY RANDOM() LIMIT 1";
                        try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
                            ResultSet rs = stmt.executeQuery(query);

                            tfq = new TrueFalseQuestion(rs.getString("Question"), rs.getString("Answer"));
                            System.out.println(tfq.getMyQuestion());
                            String userInput = myReader.next();
                            System.out.println(userInput);
                            myPass = tfq.checkAnswer(userInput);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.exit(0);
                        }
                        break;
                    }
                    case 1 -> {
                        ShortAnswerQuestion saq;
                        try {
                            ds = new SQLiteDataSource();
                            ds.setUrl("jdbc:sqlite:ShortAnswerQuestions.db");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.exit(0);
                        }
                        String query = "SELECT * FROM ShortAnswerQuestions ORDER BY RANDOM() LIMIT 1";
                        try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
                            ResultSet rs = stmt.executeQuery(query);
                            saq = new ShortAnswerQuestion(rs.getString("Question"), rs.getString("Answer"));
                            System.out.println(saq.getMyQuestion());
                            String userInput = myReader.next();
                            System.out.println(userInput);
                            myPass = saq.checkAnswer(userInput);
                            myReader.nextLine();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.exit(0);
                        }
                        break;
                    }
                    case 2 -> {
                        MultipleChoiceQuestion mcq;
                        try {
                            ds = new SQLiteDataSource();
                            ds.setUrl("jdbc:sqlite:MultipleChoiceQuestions.db");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.exit(0);
                        }
                        String query = "SELECT * FROM MultipleChoiceQuestions ORDER BY RANDOM() LIMIT 1";
                        try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
                            ResultSet rs = stmt.executeQuery(query);
                            ArrayList<String> choices = new ArrayList<>();
                            choices.add(rs.getString("Answer"));
                            choices.add(rs.getString("Choice1"));
                            choices.add(rs.getString("Choice2"));
                            choices.add(rs.getString("Choice3"));
                            mcq = new MultipleChoiceQuestion(rs.getString("Question"), rs.getString("Answer"), choices);
                            System.out.println(mcq.getMyQuestion());
                            System.out.println(mcq.getPossibleAnswers());
                            String userInput = "";
                            myReader.nextLine();
                            userInput+= myReader.nextLine();
                            System.out.println(userInput);
                            myPass = mcq.checkAnswer(userInput);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.exit(0);
                        }
                    }
                }

                /*System.out.println("Did you get the question correct?");
                boolean myPass = myReader.nextBoolean();*/

                if (myPass) {
                    myMaze.openRoom();
                    myMaze.movePosition();
                }

                else {
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
