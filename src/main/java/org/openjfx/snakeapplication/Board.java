package org.openjfx.snakeapplication;

// import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class Board extends Canvas {
	
	private static final int NUM_COLUMNS = 20;
	private static final int NUM_ROWS = 20;
	private static final double CELL_SIZE = 15.0;
	
	private static int[][] boardState = new int[NUM_ROWS][NUM_COLUMNS];

	public Board() {
		super(getBoardWidth(), getBoardHeight());
		refreshBoard();
		
		setOnMouseClicked(event -> {
			System.out.println(String.format("Clicked at (%d,%d)", (int)Math.floor(event.getX() / CELL_SIZE), (int)Math.floor(event.getY() / CELL_SIZE)));
		});
		
		GraphicsContext gc = getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, NUM_COLUMNS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
	}
	
	public static int rowCount() {
		return NUM_ROWS;
	}
	
	public static int columnCount() {
		return NUM_COLUMNS;
	}
	
	public static double getBoardHeight() {
		return NUM_ROWS * CELL_SIZE;
	}
	
	public static double getBoardWidth() {
		return NUM_COLUMNS * CELL_SIZE;
	}
	
	public void refreshBoard() {
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				boardState[i][j] = 0;
			}
		}
	}
	
	public static void updateBoardState(int x, int y, int value) {
		try {
			boardState[x][y] = value;
		} catch(IndexOutOfBoundsException e) {}
	}
	
	public static int getBoardState(int x, int y) {
		try {
			return boardState[x][y];
		} catch(IndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	public void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				drawTile(j * CELL_SIZE, i * CELL_SIZE, boardState[i][j], gc);
			}
		}
	}
	
	private void drawTile(double x, double y, int state, GraphicsContext gc) {
		switch(state) {
			case 0:
				gc.setFill(Color.WHITE);
				break;
			case 1:
				gc.setFill(Color.GREEN);
				break;
			case 2:
				gc.setFill(Color.RED);
				break;
			default:
				return;
		}
		gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
	}
}
