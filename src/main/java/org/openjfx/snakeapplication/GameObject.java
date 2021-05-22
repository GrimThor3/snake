package org.openjfx.snakeapplication;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.openjfx.snakeapplication.*;

public class GameObject {

	private int x = 0;
	private int y = 0;
	int type = 0;
	
	public GameObject(int type, boolean random) {
		this.type = type;
		if (random)
			randomCoords(Board.columnCount(), Board.rowCount());
	}
	
	public GameObject(int type, int x, int y) {
		this.type = type;
		setCoords(x, y);
	}
	
	public int getType() {
		return type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setCoords(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void randomCoords(int xMax, int yMax) {
		int x = (int)Math.floor(Math.random() * xMax);
		int y = (int)Math.floor(Math.random() * yMax);
		
		while (Board.getBoardState(x, y) != 0) {
			x = (int)Math.floor(Math.random() * xMax);
			y = (int)Math.floor(Math.random() * yMax);
		}
		
		setCoords(x, y);
	}
}
