package com.wilso663.app;

import java.awt.BorderLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import com.wilso663.constants.Constants;
import com.wilso663.view.*;
import com.wilso663.workers.*;


public class MainFrame extends JFrame implements ButtonListener{
	
	
	
	private static final long serialVersionUID = 1L;
	private Toolbar toolbar;
	private Board board;
	private ExecutorService layerExecutioner;
	private ExecutorService sweeperExecutioner;
	private MineLayer[] mineLayers;
	private MineSweeper[] mineSweepers;

	public MainFrame(){
		super(Constants.APP_NAME);
		toolbar = new Toolbar();
		board = new Board();
		
		initializeVariables();
		toolbar.setButtonListener(this);
		
		add(toolbar, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		
		setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initializeVariables() {
		mineLayers = new MineLayer[Constants.NUMBER_OF_LAYERS];
		mineSweepers = new MineSweeper[Constants.NUMBER_OF_SWEEPERS];
		
	}
	@Override
	public void startClicked() {
		this.layerExecutioner = Executors.newFixedThreadPool(Constants.NUMBER_OF_LAYERS);
		this.sweeperExecutioner = Executors.newFixedThreadPool(Constants.NUMBER_OF_SWEEPERS);
		
		try{
			
			for(int i = 0; i < Constants.NUMBER_OF_LAYERS;i++)
			{
				mineLayers[i] = new MineLayer(i, board);
				layerExecutioner.execute(mineLayers[i]);
			}
			for(int i = 0; i < Constants.NUMBER_OF_SWEEPERS;i++)
			{
				mineSweepers[i] = new MineSweeper(i, board);
				sweeperExecutioner.execute(mineSweepers[i]);
			}
		
		
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			layerExecutioner.shutdown();
			sweeperExecutioner.shutdown();
		}
		
	}

	@Override
	public void stopClicked() {
		System.gc();
		System.exit(0);
		
	}

	
}
