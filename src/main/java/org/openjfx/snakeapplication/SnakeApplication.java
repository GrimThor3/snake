package org.openjfx.snakeapplication;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

import org.openjfx.snakeapplication.*;

public class SnakeApplication extends Application {
	
	private VBox panel;
	
	private Board board;
	private Snake snake;
	private Apple apple;
	
	private int speed = 400; // speed (update canvas every x ms)
	private boolean running = false;
	
	private int appleCount = 0;
	private AnimationTimer animationThread;
	
	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox();
		Scene scene = new Scene(root, Board.getBoardWidth() + 100, Board.getBoardHeight());
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case UP:
					if (running && snake.getDirection() != 3) {
						snake.setDirection(1);
					}
					break;
				case W:
					if (running && snake.getDirection() != 3) {
						snake.setDirection(1);
					}
					break;
				case LEFT:
					if (running && snake.getDirection() != 4) {
						snake.setDirection(2);
					}
					break;
				case A:
					if (running && snake.getDirection() != 4) {
						snake.setDirection(2);
					}
					break;
				case DOWN:
					if (running && snake.getDirection() != 1) {
						snake.setDirection(3);
					}
					break;
				case S:
					if (running && snake.getDirection() != 1) {
						snake.setDirection(3);
					}
					break;
				case RIGHT:
					if (running && snake.getDirection() != 2) {
						snake.setDirection(4);
					}
					break;
				case D:
					if (running && snake.getDirection() != 2) {
						snake.setDirection(4);
					}
					break;
				default:
					break;
				}
			}
		});
		
		animationThread = new AnimationTimer() {
			private long lastUpdate;
			private long start;
			
			@Override
			public void start() {
				start = lastUpdate = System.nanoTime();
				super.start();
			}
			
			@Override
			public void handle(long now) {
				if (now - lastUpdate >= (1000000*speed)) {
					// System.out.println("Update at " + (now-start)/1000000 + "ms");
					lastUpdate = now;
					run();
				}
			}
		};
		
		board = new Board();
		board.draw();
		root.getChildren().addAll(board, createScorePanel());
		
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private VBox createScorePanel() {
		panel = new VBox();
		
		Button buttonNewGame = new Button("New Game");
		buttonNewGame.setOnAction(event -> {
			newGame();
		});
		
		panel.getChildren().add(buttonNewGame);
		updatePanel();
		return panel;
	}
	
	private void updatePanel() {
		try {
			panel.getChildren().remove(1, 2);
		} catch (IndexOutOfBoundsException e) {}
		
		Text appleLabel = new Text(String.format("Apples: %d", appleCount));
		panel.getChildren().add(appleLabel);
	}
	
	private void newGame() {
		board.refreshBoard();
		
		appleCount = 0;
		updatePanel();
		
		snake = new Snake();
		apple = new Apple();
		board.draw();
		
		running = true;
		animationThread.start();
	}
	
	private void endGame() {
		animationThread.stop();
		running = false;
		System.out.println("Game Over");
	}
	
	private void run() {
		if (snake.checkCollision()) {
			endGame();
		} else {
			if (snake.appleCollision()) {
				appleCount++;
				if (appleCount % 5 == 0) {
					speed = speed - 20;
				}
				
				Board.updateBoardState(apple.getX(), apple.getY(), 0);
				
				apple = new Apple();
				snake.grow();
				
				updatePanel();
			}
			snake.move();
		}
		board.draw();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
