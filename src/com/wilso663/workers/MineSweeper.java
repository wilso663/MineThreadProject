package com.wilso663.workers;

import java.util.Random;

import com.wilso663.constants.Constants;
import com.wilso663.view.*;

public class MineSweeper implements Runnable{

	private int id;
	private Board board;
	
	
	
	public MineSweeper(int id, Board board){
		this.id = id;
		this.board = board;
	}
	
	@Override
	public void run()
	{
		Random random = new Random();
		
		while(true)
		{
			int index = random.nextInt(Constants.BOARD_ROWS * Constants.BOARD_COLUMNS);
			board.sweepMine(index);
			
			try{
			Thread.sleep(2000);
			}catch(InterruptedException ex){
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
