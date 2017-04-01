package gui;

import java.awt.GridLayout;

import javax.swing.border.EmptyBorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




public class GamePanel extends Map implements KeyListener{


	
	
	public GamePanel(int width, int height, int size) {
		super();

		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		setBackground(java.awt.Color.BLACK);
		this.setLayout(new GridLayout(size, size));
		this.setSize(width, height);
		this.setVisible(true);
		
		

		loadImages();
		
		addKeyListener(this);
		
	}

	
	public void update(){
		super.update(GameWindow.getMap().getMatrix());
	}
	
	public void playGameRound(String direction) throws InterruptedException{
		
		

		if (GameWindow.getGame().isOver()){
			
			GameWindow.EndGame();
			
		}
		GameWindow.getGame().setDirection(direction);
		
		
		GameWindow.getGame().update();
		update();
		
	
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		try{
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT: 
			playGameRound("A");  
			break;
		case KeyEvent.VK_RIGHT:
			playGameRound("D");  
			break;
		case KeyEvent.VK_UP: 
			playGameRound("W");   
			break;
		case KeyEvent.VK_DOWN: 
			playGameRound("S");  
			break;
		 }	
		
		}catch (InterruptedException e1){
			
		}
		
		requestFocus();
		
	}
	
	public void fillWalls(){
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void setSize(int size) {
		this.setLayout(new GridLayout(size, size));
		
	}



}