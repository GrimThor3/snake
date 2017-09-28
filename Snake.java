
public class Snake 
{
	private int direction;
		/*
		 * North = 1
		 * West = 2
		 * South = 3
		 * East = 4
		 */
	private final int x;
	private final int y;
	private int[] bodyX = new int[400];
	private int[] bodyY = new int[400];
	
	private int numSeg;
	
	public Snake()
	{
		direction = 1;
		x = Board.num_cols / 2;
		y = Board.num_rows / 2;
		
		bodyX[0] = x;
		bodyY[0] = y;
		
		bodyX[1] = x;
		bodyY[1] = y + 1;
		
		bodyX[2] = x;
		bodyY[2] = y + 2;
				
		Board.grid[bodyX[0]][bodyY[0]] = 1;
		Board.grid[bodyX[1]][bodyY[1]] = 2;
		Board.grid[bodyX[2]][bodyY[2]] = 2;
		
		numSeg = 3;
	}
	
	public void addSeg()
	{
		numSeg += 2;
	}
	
	public void move()
	{
		for (int i = numSeg-1; i >= 0; i--)
		{
			Board.grid[bodyX[i]][bodyY[i]] = 0;
			if (i != 0)
			{
				bodyX[i] = bodyX[i - 1];
				bodyY[i] = bodyY[i - 1];
			}
			else
			{
				if (direction == 1)
				{
					bodyY[0] -= 1;
				}
				else if (direction == 2)
				{
					bodyX[0] -= 1;
				}
				else if (direction == 3)
				{
					bodyY[0] += 1;
				}
				else if (direction == 4)
				{
					bodyX[0] += 1;
				}
			}
		}
			
		for (int i = 0; i < numSeg; i++)
		{
			Board.grid[bodyX[i]][bodyY[i]] = 2;
			if (i == 0)
			{
				Board.grid[bodyX[i]][bodyY[i]] = 1;
			}
		}	
	}
	
	public void changeDirection(int newDirection)
	{
		direction = newDirection;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public boolean checkCollision()
	{
		if (direction == 1)
		{
			if (Board.grid[bodyX[0]][bodyY[0]-1] == 2 || bodyY[0] == 0) {
				return true;
			}
		}
		else if (direction == 2)
		{
			if (Board.grid[bodyX[0]-1][bodyY[0]] == 2 || bodyX[0] == 0) {
				return true;
			}
		}
		else if (direction == 3)
		{
			if (Board.grid[bodyX[0]][bodyY[0]+1] == 2 || bodyY[0] == Board.num_cols-1) {
				return true;
			}
		}
		else if (direction == 4)
		{
			if (Board.grid[bodyX[0]+1][bodyY[0]] == 2 || bodyX[0] == Board.num_rows-1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkAppleCollision()
	{
		if (direction == 1)
		{
			if (Board.grid[bodyX[0]][bodyY[0]-1] == 3) {
				return true;
			}
		}
		else if (direction == 2)
		{
			if (Board.grid[bodyX[0]-1][bodyY[0]] == 3) {
				return true;
			}
		}
		else if (direction == 3)
		{
			if (Board.grid[bodyX[0]][bodyY[0]+1] == 3) {
				return true;
			}
		}
		else if (direction == 4)
		{
			if (Board.grid[bodyX[0]+1][bodyY[0]] == 3) {
				return true;
			}
		}
		return false;
	}
}
