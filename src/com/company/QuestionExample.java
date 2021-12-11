package com.company;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionExample {
    public static void main(String[] theArgs) {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:TrueFalseQuestions.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );
        // Takes a row from the table at random
        String query = "SELECT * FROM TrueFalseQuestions ORDER BY RANDOM() LIMIT 1";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                String question = rs.getString( "Question" );
                String answer = rs.getString( "Answer" );

                System.out.println( "Result: Question = " + question +
                        ", Answer = " + answer );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}
