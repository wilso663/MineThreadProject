package com.wilso663.workers;

import java.util.Random;

import com.wilso663.view.*;

public class MineLayer implements Runnable
{
	private int id;
	private Board board;
	
	public MineLayer(int id, Board board)
	{
		this.id = id;
		this.board = board;
	}
	
	@Override
	public void run()
	{
		Random random = new Random();
		
		while(true)
		{
			int index = random.nextInt(100);
			board.setMine(index);
			
			try{
				Thread.sleep(200);
			}catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}
			
		}
	}
	
	@Override
	public String toString()
	{
		return ""+this.id;
	}
}
