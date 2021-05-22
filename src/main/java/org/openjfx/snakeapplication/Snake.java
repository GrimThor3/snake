package org.openjfx.snakeapplication;

import java.util.ArrayList;

public class Snake extends GameObject {

	private int direction;
	/*
	 * North = 1
	 * West = 2
	 * South = 3
	 * East = 4
	 */
	
	private int segmentCount = 0;
	private ArrayList<Integer> xBody;
	private ArrayList<Integer> yBody;
	
	public Snake() {
		super(1, (int)Math.floor(Board.columnCount() / 2), (int)Math.floor(Board.rowCount() / 2));
		xBody = new ArrayList<Integer>();
		yBody = new ArrayList<Integer>();
		initialState();
	}
	
	public void initialState() {
		direction = 1;
		
		// create three segments
		segmentCount = 3;
		
		xBody.clear();
		xBody.add(Integer.valueOf(getX()));
		xBody.add(Integer.valueOf(getX() + 1));
		xBody.add(Integer.valueOf(getX() + 2));
		
		yBody.clear();
		yBody.add(Integer.valueOf(getY()));
		yBody.add(Integer.valueOf(getY()));
		yBody.add(Integer.valueOf(getY()));
		updateBoardPositions();
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int newDirection) {
		// System.out.println(String.format("New direction: %d", newDirection));
		if (newDirection >= 1 && newDirection <= 4) {
			direction = newDirection;
		}
	}
	
	public int nextX() {
		switch(direction) {
			case 1:
				return getX() - 1;
			case 2:
				return getX();
			case 3:
				return getX() + 1;
			case 4:
				return getX();
		}
		
		return 0;
	}
	
	public int nextY() {
		switch(direction) {
			case 1:
				return getY();
			case 2:
				return getY() - 1;
			case 3:
				return getY();
			case 4:
				return getY() + 1;
		}
		
		return 0;
	}
	
	public void move() {
		setCoords(nextX(), nextY());
		xBody.add(0, getX());
		yBody.add(0, getY());
		if (xBody.size() > segmentCount) {
			Board.updateBoardState(xBody.get(segmentCount), yBody.get(segmentCount), 0);
			xBody.remove(segmentCount);
			yBody.remove(segmentCount);
		}
		updateBoardPositions();
		// System.out.println(String.format("Moved to (%d,%d)", getX(), getY()));
	}
	
	public void updateBoardPositions() {
		for (int i = 0; i < segmentCount; i++) {
			Board.updateBoardState(xBody.get(i), yBody.get(i), 1);
		}
	}
	
	public void grow() {
		segmentCount++;
	}
	
	public boolean checkCollision() {
		switch(direction) {
			case 1:
				return Board.getBoardState(getX() - 1, getY()) == 1 || getX() == 0;
			case 2:
				return Board.getBoardState(getX(), getY() - 1) == 1 || getY() == 0;
			case 3:
				return Board.getBoardState(getX() + 1, getY()) == 1 || getX() == Board.rowCount() - 1;
			case 4:
				return Board.getBoardState(getX(), getY() + 1) == 1 || getY() == Board.columnCount() - 1;
		}
		
		return false;
	}
	
	public boolean appleCollision() {
		switch(direction) {
		case 1:
			return Board.getBoardState(getX() - 1, getY()) == 2;
		case 2:
			return Board.getBoardState(getX(), getY() - 1) == 2;
		case 3:
			return Board.getBoardState(getX() + 1, getY()) == 2;
		case 4:
			return Board.getBoardState(getX(), getY() + 1) == 2;
		}
		
		return false;
	}
}
