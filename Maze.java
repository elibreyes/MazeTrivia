package com.company;

import java.awt.*;
import java.io.Serializable;

/**
 * Maze class used to hold the rooms and show the maze for the user
 */
public class Maze implements Serializable {

	/**
	 * private enum Status is an enum to hold the different room status
	 */
	private enum Status{
		AVAILABLE, OPEN
	}

	/**
	 * private enum Location is an enum to hold the different movements
	 */
	private enum Location{
		UP, RIGHT, DOWN
	}

	/**
	 * myExit holds the location of the end goal
	 * myPosition holds the current location of user
	 * myChoice holds the desired location to move to
	 * myMaze holds the instance of the maze
	 * myTraversalGraph holds a traversable graph to solve
	 */
	private Point myExit;
	private Point myPosition;
	private Point myChoice;
	private Room[][] myMaze;
	private char[][] myTraversalGraph;

	/**
	 * Constructor that creates a 4x4 2D maze and sets the
	 * exits at location [3,3], user position and choice at
	 * [0,0] and builds a traversal graph so that it can be
	 * used for our traversal solvable method
	 */
	public Maze() {
		myMaze = new Room[4][4];
		buildMaze();
		myTraversalGraph = new char[4][4];
		buildTraversalGraph();
		myPosition = new Point(0, 0);
		myExit = new Point(3, 3);
		myChoice = new Point(0, 0);
		openRoom();
	}


	/**
	 * Checks the status of the desired room to move to
	 * @return boolean false if the room is locked
	 * otherwise true if the room is available/open
	 */
	boolean checkRoom() {
		boolean roomStatus = false; // if open or available
		if (withinRange()) {
			if (myMaze[myChoice.y][myChoice.x].getStatus() == Status.AVAILABLE.ordinal() || myMaze[myChoice.y][myChoice.x].getStatus() == Status.OPEN.ordinal()) {
				roomStatus = true; // if the room is locked
			}
		}
		return roomStatus;
	}

	/**
	 * Checks to see if the choice is within range of the maze so
	 * no out of bounds exceptions occurs
	 * @return boolean on if the choice is within range
	 */
	private boolean withinRange() {
		return myChoice.x >= 0 && myChoice.x <= myExit.x && myChoice.y >= 0 && myChoice.y <= myExit.y;
	}

	/**
	 * Locks the room if the user gets the question incorrect
	 */
	void lockRoom() {
		myMaze[myChoice.y][myChoice.x].changeStatus(false);
		myTraversalGraph[myChoice.y][myChoice.x] = '*';
	}

	/**
	 * Opens up the room if the user gets the question correct
	 */
	void openRoom() {
		myMaze[myChoice.y][myChoice.x].changeStatus(true);
		myTraversalGraph[myChoice.y][myChoice.x] = '.';
	}

	/**
	 * Updates choice based on what was decided from scanner and compares to Location enum
	 * @param choice numeric choice from scanner which compares to the enum to decide
	 */
	void updateChoice(int choice) {
		if (choice == Location.UP.ordinal()) {
			myChoice.y = (myChoice.y - 1);
		} else if (choice == Location.RIGHT.ordinal()) {
			myChoice.x = (myChoice.x + 1);
		} else if (choice == Location.DOWN.ordinal()) {
			myChoice.y = (myChoice.y + 1);
		} else {
			myChoice.x = (myChoice.x - 1);
		}
	}

	/**
	 * Resets the user's choice to be at current location if invalid choice
	 */
	void resetRoom() {
		myChoice.x = myPosition.x;
		myChoice.y = myPosition.y;
	}

	/**
	 * Changes the user's position to the desired position
	 */
	void movePosition() {
		myPosition.x = myChoice.x;
		myPosition.y = myChoice.y;
		myTraversalGraph[myPosition.y][myPosition.x] = '.'; 
	}

	/**
	 * Builds the maze of rooms with available status
	 */
	void buildMaze() {
		for (int i = 0; i < myMaze.length; i++) {
			for (int j = 0; j < myMaze[i].length; j++) {
				myMaze[i][j] = new Room();
			}
		}
	}

	/**
	 * Checks to see if the user has reached the end goal
	 * @return boolean on if the user is at the exit
	 */
	boolean solved() {
		return ((myPosition.x == myExit.x) && (myPosition.y == myExit.y));
	}

	/**
	 *
	 * @return
	 */
	boolean solvable() {
		boolean canWin = move(myTraversalGraph, myPosition.y, myPosition.x);
		resetToNonVisited(myMaze, myTraversalGraph);
		return canWin;

	}

	/**
	 *
	 */
	private void buildTraversalGraph(){
		for (int row = 0; row < myMaze.length; row++) {
			for (int col = 0; col < myMaze[0].length; col++) {
				if (myMaze[row][col].getStatus() != 2) {
					myTraversalGraph[row][col] = '.';
				} else {
					myTraversalGraph[row][col] = '*';
				}
			}
		}
	}


	/**
	 *
	 * @param theMaze
	 * @param theRow
	 * @param theCol
	 * @return
	 */
	private static boolean move(char[][] theMaze, final int theRow, final int theCol) {
		boolean success = false;
		if (validMove(theMaze, theRow, theCol)) {
			markVisited(theMaze, theRow, theCol); // drop a bread crumb to track we've been here
			if (atExit(theMaze, theRow, theCol))
				return true;
			success = move(theMaze, theRow + 1, theCol); // down
			if (!success)
				success = move(theMaze, theRow, theCol + 1); // right
			if (!success)
				success = move(theMaze, theRow - 1, theCol); // up
			if (!success)
				success = move(theMaze, theRow, theCol - 1); // left
			if (!success)
				markDeadEnd(theMaze, theRow, theCol);

		}

		return success;
	}

	/**
	 *
	 * @param theMaze
	 * @param theGraph
	 */
	private static void resetToNonVisited(Room[][] theMaze, char[][] theGraph) {
		for (int row = 0; row < theGraph.length; row++) {
			for (int col = 0; col < theGraph[0].length; col++) {
				if (theMaze[row][col].getStatus() == 2) {
					theGraph[row][col] = '*';
				} else {
					theGraph[row][col] = '.';
				}
			}
		}
	}

	/**
	 *
	 * @param theMaze
	 * @param theRow
	 * @param theCol
	 */
	private static void markDeadEnd(char[][] theMaze, final int theRow, final int theCol) {
		theMaze[theRow][theCol] = 'd';
	}

	/**
	 *
	 * @param theMaze
	 * @param theRow
	 * @param theCol
	 */
	private static void markVisited(char[][] theMaze, final int theRow, final int theCol) {
		theMaze[theRow][theCol] = '*';
	}

	/**
	 *
	 * @param theMaze
	 * @param theRow
	 * @param theCol
	 * @return
	 */
	private static boolean atExit(final char[][] theMaze, final int theRow, final int theCol) {
		return theRow == theMaze.length - 1 && theCol == theMaze[theRow].length - 1;
	}

	/**
	 *
	 * @param theMaze
	 * @param theRow
	 * @param theCol
	 * @return
	 */
	private static boolean validMove(char[][] theMaze, final int theRow, final int theCol) {
		// inside maze and non visited room
		return theRow >= 0 && theRow < theMaze.length && theCol >= 0 && theCol < theMaze[theRow].length && theMaze[theRow][theCol] == '.';

	}

	/**
	 * Checks to see if the room has already been opened
 	 * @return boolean if the room is opened
	 */
	boolean alreadyOpened() {
		return myMaze[myChoice.y][myChoice.x].getStatus() == Status.OPEN.ordinal();
	}

	/**
	 * Standard toString method in which U represents the user's location
	 * @return a string version of the 2D array of the maze at its current instance
	 */	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < myMaze.length; i++) {
			for (int j = 0; j < myMaze[i].length; j++) {
				if (myPosition.y == i && myPosition.x == j) {
					result.append("U ");
					continue;
				}
				result.append(myMaze[i][j].toString());
			}
			result.append("\n");
		}
		return result.toString();
	}
}
