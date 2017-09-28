import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Board extends JPanel
{

	public static final int num_rows = 30;
	public static final int num_cols = 30;
	public static final int size = 20;
	
	public static int[][] grid;
	private SnakeGame snakeGame;
	
	public Board(SnakeGame snakeyGame)
	{
		setPreferredSize(new Dimension((num_cols+7)*size, num_rows*size));
		setBackground(Color.BLACK);
		
		grid = new int[num_cols][num_rows];
		for (int i = 0; i < num_cols; i++)
		{
			for (int j = 0; j < num_rows; j++)
			{
				grid[i][j] = 0;
			}
		}
		snakeGame = snakeyGame;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.DARK_GRAY);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		
		for (int x = 0; x < num_cols+1; x++)
		{
			for (int y = 0; y < num_rows; y++)
			{
				g.drawLine(x*size, 0, x*size, num_rows*size);
				g.drawLine(0, y*size, num_cols*size, y*size);
			}
		}
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 28));
		g.drawString("Snake", (num_cols+2)*size, 4*size);
		g.drawString("Score:", (num_cols+2)*size, ((num_rows/2)-1)*size);
		g.drawString(snakeGame.score+"", (num_cols+3)*size, ((num_rows/2)+1)*size);
		
		for (int i = 0; i < num_cols; i++)
		{
			for (int j = 0; j < num_rows; j++)
			{
				if (grid[i][j] == 1)
				{
					drawTile(i, j, 1, g);
				}
				else if (grid[i][j] == 2)
				{
					drawTile(i, j, 2, g);
				}
				else if (grid[i][j] == 3)
				{
					drawTile(i, j, 3, g);
				}
			}
		}
	}
	
	private void drawTile(int x, int y, int type, Graphics g)
	{
		x *= size;
		y *= size;
		
		switch(type)
		{
			case 1:
				g.setColor(Color.GREEN);
				g.fillRect(x, y, size, size);
				break;
			
			case 2:
				g.setColor(Color.BLUE);
				g.fillRect(x, y, size, size);
				break;
				
			case 3:
				g.setColor(Color.RED);
				g.fillOval(x+2, y+2, size-4, size-4);
		}
	}
}
