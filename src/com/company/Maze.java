package com.company;

import java.awt.*;
import java.io.Serializable;

public class Maze implements Serializable {

	private Point myExit;
	private Point myPosition;
	private Point myChoice;
	private Room[][] myMaze;
	private char[][] myTraversalGraph;

	public Maze() {
		myMaze = new Room[4][4];
		buildMaze();
		myTraversalGraph = new char[4][4];
		for (int row = 0; row < myMaze.length; row++) {
			for (int col = 0; col < myMaze[0].length; col++) {
				if (myMaze[row][col].getStatus() != 2) {
					myTraversalGraph[row][col] = '.';
				} else {
					myTraversalGraph[row][col] = '*';
				}
			}
		}
		myPosition = new Point(0, 0);
		myExit = new Point(3, 3);
		myChoice = new Point(0, 0);
		// open up the entrance room
		openRoom();
	}

	// checks to make sure if the room has been locked or not
	boolean checkRoom() {
		boolean roomStatus = false; // if open or available
		if (withinRange()) {
			if (myMaze[myChoice.y][myChoice.x].getStatus() == 0 || myMaze[myChoice.y][myChoice.x].getStatus() == 1) {
				roomStatus = true; // if the room is locked
			}
		}
		return roomStatus;
	}

	// checks to make sure the room decision is within the range so no index out of
	// bounds
	private boolean withinRange() {
		return myChoice.x >= 0 && myChoice.x <= myExit.x && myChoice.y >= 0 && myChoice.y <= myExit.y;
	}

	// updates the status of the room by using the room's change status
	void lockRoom() {
		myMaze[myChoice.y][myChoice.x].changeStatus(false);
		myTraversalGraph[myChoice.y][myChoice.x] = '*';
	}

	// updates the status of the room by using the room's change status
	void openRoom() {
		myMaze[myChoice.y][myChoice.x].changeStatus(true);
		myTraversalGraph[myChoice.y][myChoice.x] = '.';
	}

	// choice would be from the scanner from main when choosing which rooms
	void updateChoice(int choice) {
		if (choice == 0) { // move up
//            myChoice = new Point(myPosition.x, (myPosition.y-1));
			myChoice.y = (myChoice.y - 1);
		} else if (choice == 1) { // move right
//            myChoice = new Point((myPosition.x+1), myPosition.y);
			myChoice.x = (myChoice.x + 1);
		} else if (choice == 2) { // move down
//            myChoice = new Point(myPosition.x, (myPosition.y+1));
			myChoice.y = (myChoice.y + 1);

		} else { // move left
//            myChoice = new Point((myPosition.x-1), myPosition.y);
			myChoice.x = (myChoice.x - 1);
		}
	}

	void resetRoom() {
		myChoice.x = myPosition.x;
		myChoice.y = myPosition.y;
	}

	// would call movePosition if the user decides to move that way so would
	// practically update it by moving it to the choice
	void movePosition() {
		myPosition.x = myChoice.x;
		myPosition.y = myChoice.y;
		myTraversalGraph[myPosition.y][myPosition.x] = '.'; 
	}

	// creates the maze and fills it with rooms of * status
	void buildMaze() {
		for (int i = 0; i < myMaze.length; i++) {
			for (int j = 0; j < myMaze[i].length; j++) {
				myMaze[i][j] = new Room();
			}
		}
	}

	// the maze will be solved once myPosition is the same as myExit
	boolean solved() {
		return ((myPosition.x == myExit.x) && (myPosition.y == myExit.y));
	}

	// figure out the traversal stuff in order to check if it is solvable
	boolean solvable() {
		boolean canWin = move(myTraversalGraph, myPosition.y, myPosition.x);
		resetToNonVisited(myMaze, myTraversalGraph);
		return canWin;

	}

	private static boolean move(char[][] theMaze, final int theRow, final int theCol) {
		boolean success = false;
		if (validMove(theMaze, theRow, theCol)) {
			markVisited(theMaze, theRow, theCol); // drop a bread crumb to track we've been here
			if (atExit(theMaze, theRow, theCol))
				return true;

			// not at exit so need to try other directions
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
	
	private static void resetToNonVisited(Room[][] theMaze, char[][] theGraph) {
		int rowLength = theGraph.length;
		int colLength = theGraph[0].length;
		for (int row = 0; row < rowLength; row++) {
			for (int col = 0; col < colLength; col++) {
				if (theMaze[row][col].getStatus() == 2) {
					theGraph[row][col] = '*';
				} else {
					theGraph[row][col] = '.';
				}
			}
		}
	}

	private static void markDeadEnd(char[][] theMaze, final int theRow, final int theCol) {

		theMaze[theRow][theCol] = 'd';

	}

	private static void markVisited(char[][] theMaze, final int theRow, final int theCol) {

		theMaze[theRow][theCol] = '*';

	}

	private static boolean atExit(final char[][] theMaze, final int theRow, final int theCol) {

		return theRow == theMaze.length - 1 && theCol == theMaze[theRow].length - 1;
	}

	private static boolean validMove(char[][] theMaze, final int theRow, final int theCol) {
		// inside maze and non visited room
		return theRow >= 0 && theRow < theMaze.length && theCol >= 0 && theCol < theMaze[theRow].length && theMaze[theRow][theCol] == '.';

	}

	// checks to see if the room is already opened so it can just enter
	boolean alreadyOpened() {
		return myMaze[myChoice.y][myChoice.x].getStatus() == 1;
	}

	// prints out the maze
	public String toString() {
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
