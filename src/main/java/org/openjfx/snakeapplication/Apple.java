package org.openjfx.snakeapplication;
import org.openjfx.snakeapplication.*;

public class Apple extends GameObject {
	
	public Apple() {
		super(2, true);
		Board.updateBoardState(getX(), getY(), 2);
	}
	
}
