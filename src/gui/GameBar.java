package gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class GameBar extends JPanel implements MouseListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static GamePanel pnlGame;
	
	/**
	 * Create the panel.
	 */
	public GameBar() {
		setBackground(Color.BLACK);
		
		setUpButton();
		setLeftButton();
		setDownButton();
		setRightButton();
		
		pnlGame = new GamePanel(400, 400,10);
		pnlGame.setBounds(0, 0, 550, 491);
		this.add(pnlGame);
				
		sethowToPlayButton();		
		setMenuButton();	
		addMouseListener(this);
		addKeyListener(this);
		
	}
	
	
	public void setMenuButton(){
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(27, 519, 113, 49);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindow.pnlGameBar.setVisible(false);
				GameWindow.pnlMenu.setVisible(true);
			}
		});
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setIcon(new ImageIcon(GameBar.class.getResource("/gui/res/rsz_menu.png")));
		btnNewButton.setBackground(Color.BLACK);
		add(btnNewButton);
	}
	
	
	public void sethowToPlayButton(){
		JButton howToPlay = new JButton("");
		howToPlay.setBounds(164, 519, 188, 44);
		howToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(GameWindow.frmMazeGame, " Press keyboard arrow keys to move your hero\n "
						+ "around the maze. You can also click on the buttons.\n"
						+ " Your goal is to catch the key and then reach the doors.\n"
						+ "                                                        \n"
						+ " Good luck!!!              ");
			}
		});
		howToPlay.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		howToPlay.setIcon(new ImageIcon(GameBar.class.getResource("/gui/res/rsz_2howtoplay.png")));
		howToPlay.setBackground(Color.BLACK);
		add(howToPlay);
	}
	
	public void setUpButton(){
		
		JButton up = new JButton("");
		up.setBounds(415, 492, 29, 25);
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					pnlGame.playGameRound("W");
				}catch (InterruptedException e1){

				}
			}
		});
		setLayout(null);
		up.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		up.setIcon(new ImageIcon(GameBar.class.getResource("/gui/res/arrowUp.png")));
		up.setBackground(Color.BLACK);
		add(up);
		
	}
	
	public void setLeftButton(){

		JButton left = new JButton("");
		left.setBounds(386, 519, 29, 25);
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					pnlGame.playGameRound("A");
				}catch (InterruptedException e1){
				}
			}
		});
		left.setIcon(new ImageIcon(GameBar.class.getResource("/gui/res/arrowLeft.png")));
		left.setBackground(Color.BLACK);
		left.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		add(left);

	}

	public void setDownButton(){
		
		JButton down = new JButton("");
		down.setBounds(415, 547, 29, 25);
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					pnlGame.playGameRound("S");
				}catch (InterruptedException e1){
				}
			}
		});
		down.setIcon(new ImageIcon(GameBar.class.getResource("/gui/res/arrowDown.png")));
		down.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		down.setBackground(Color.BLACK);
		add(down);
	}
	
	public void setRightButton(){

		JButton right = new JButton("");
		right.setBounds(445, 519, 29, 29);
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					pnlGame.playGameRound("D");
				}catch (InterruptedException e1){
				}
			}
		});
		right.setAlignmentX(Component.CENTER_ALIGNMENT);
		right.setBackground(Color.BLACK);
		right.setIcon(new ImageIcon(GameBar.class.getResource("/gui/res/arrowRight.png")));
		right.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		add(right);
	}
	
	public void update() {
		pnlGame.update();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.requestFocus();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.requestFocus();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.requestFocus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		try{
			
			switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT: 
				pnlGame.playGameRound("A");  
				break;
			case KeyEvent.VK_RIGHT:
				pnlGame.playGameRound("D");  
				break;
			case KeyEvent.VK_UP: 
				pnlGame.playGameRound("W");   
				break;
			case KeyEvent.VK_DOWN: 
				pnlGame.playGameRound("S");  
				break;
			 }	
			
			}catch (InterruptedException e1){
				
			}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
