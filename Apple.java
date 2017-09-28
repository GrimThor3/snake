
public class Apple 
{
	private int x;
	private int y;

	public Apple()
	{
		x = 0;
		y = 0;
		setNewCoords();
	}
	
	private void setNewCoords()
	{
		x = (int)(Math.random()*Board.num_cols);
		y = (int)(Math.random()*Board.num_rows);
		
		if (Board.grid[x][y] != 0)
		{
			setNewCoords();
		}
		
		Board.grid[x][y] = 3;
	}
	
	private int getX()
	{
		return x;
	}
	
	private int getY()
	{
		return y;
	}
}
