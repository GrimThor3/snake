import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class SnakeGame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Board board;
	public Snake snake;
	public Apple apple;
	
	private static final int speed = 50;
	public boolean gameOver;
	public boolean pause;
	public int score;
	
	public SnakeGame()
	{
		super("SnakeGame");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		gameOver = false;
		pause = false;
		score = 0;
		
		this.board = new Board(this);
		add(board, BorderLayout.CENTER);
		
		snake = new Snake();
		apple = new Apple();
		
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_W:
					case KeyEvent.VK_UP:
						if (snake.getDirection() != 3)
						{
							snake.changeDirection(1);
						}
						break;
						
					case KeyEvent.VK_S:
					case KeyEvent.VK_DOWN:
						if (snake.getDirection() != 1)
						{
							snake.changeDirection(3);
						}
						break;
						
					case KeyEvent.VK_A:
					case KeyEvent.VK_LEFT:
						if (snake.getDirection() != 4)
						{
							snake.changeDirection(2);
						}
						break;
						
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						if (snake.getDirection() != 2)
						{
							snake.changeDirection(4);
						}
						break;
						
					case KeyEvent.VK_P:
						if (pause == true)
						{
							pause = false;
						}
						else
						{
							pause = true;
						}
						break;
						
					case KeyEvent.VK_ENTER:
						System.exit(0);
						break;
				}
			}
		});
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		repaint();
		
		runGame();
	}
	
	public void runGame()
	{
		while (gameOver == false)
		{
			if (pause == false)
			{
				try {
                Thread.sleep(speed);
            } 
			catch (Exception e) 
			{
				//There should be no errors that need to be caught
            }
			
			if (snake.checkCollision() == false)
			{
				if (snake.checkAppleCollision() == true)
				{
					apple = new Apple();
					snake.addSeg();
					score++;
				}
				snake.move();
			}
			else
			{
				gameOver = true;
				System.exit(0);
			}
			repaint();
			}
		}
	}
	
	public static void main(String[] args)
	{
		new SnakeGame();
	}
}