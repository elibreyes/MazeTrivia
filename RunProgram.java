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
 * Controller class that moves data around to be used and printed as necessary
 */
public class RunProgram {

    /**
     * Controller method that calls necessary methods within the class to run the program
     */
    public static void run() {
        Scanner myReader = new Scanner(System.in);
        String gameName = getName(myReader);
        Maze myMaze = loadMaze(gameName);


        while(!myMaze.solved() && myMaze.solvable() ){
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

    /**
     * Deserialization method used to load previous game or create a new maze
     * @param gameName name of the file user desires to play
     * @return Maze object to be played
     */
    private static Maze loadMaze(String gameName){
        Maze myMaze = null;
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

    /**
     * Method used from loadMaze to get the name of a potential loaded file or a new file
     * @param theReader scanner used to get the input for the name
     * @return the name of the file to be played
     */
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

    /**
     * Serializes and save the status of the instance of the maze
     * @param myReader scanner used to determine what file name to put it under
     * @param myMaze state of the map to be saved
     */
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

    /**
     * Method used to run the game and play while accepting decisions
     * @param myReader scanner used to determine movements and also answer of the choices
     * @param myMaze original status of the maze either previously loaded or new
     */
    private static void playing(Scanner myReader, Maze myMaze){
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
                boolean myPass;
                /*SQLiteDataSource ds = null;

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
                }*/
                myPass = giveQuestion(myReader);
                if (myPass) {
                    System.out.println("Answer was correct!");
                    myMaze.openRoom();
                    myMaze.movePosition();
                }
                else {
                    System.out.println("Answer was wrong!");
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

    private static boolean giveQuestion(Scanner theReader) {
        Random rand = new Random();
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
                    String userInput = theReader.next();
                    System.out.println(userInput);
                    return tfq.checkAnswer(userInput);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
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
                    String userInput = theReader.next();
                    System.out.println(userInput);
                    return saq.checkAnswer(userInput);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
            default -> {
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
                    String userInput = "";
                    theReader.nextLine();
                    userInput+= theReader.nextLine();
                    System.out.println(userInput);
                    return mcq.checkAnswer(userInput);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }
        return false;
    }

}
