package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import logic.Game;
import logic.KeepMap;


import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;


public class MapEditor extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = -6282798323298335606L;
	
	private char c= ' ';
	private MapEditorGrid game_panel;
	private double MAP_SIZE = 350;
	private double DIVISOR;
	
	private int size;

	

	public MapEditor(int size) {
		super();
		setLayout(null);

		setWallButton();
		setExitButton();
		setKeyButton();
		setOgreButton();
		setHeroButton();
		setPlayButton();
		setBackButton();
		setClubButton();
		setBackground(java.awt.Color.BLACK);

		
		
		this.size =size;
	
		
		setGamePanel();
		
		DIVISOR = MAP_SIZE/size;
		
		addMouseListener(this);

	}

	private void setClubButton() {
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c='*';
			}
		});
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setIcon(new ImageIcon(MapEditor.class.getResource("/gui/res/iconSword.png")));
		btnNewButton.setBounds(448, 506, 49, 49);
		add(btnNewButton);
		
	}

	private void setGamePanel() {
		game_panel = new MapEditorGrid(400, 400, size);
		game_panel.setBounds(75, 75, 400, 400);
		add(game_panel);
		
		
	}

	private void setBackButton() {
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindow.pnlCustomMap.setVisible(false);
				GameWindow.pnlMenu.setVisible(true);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(MapEditor.class.getResource("/gui/res/rsz_menu.png")));
		btnNewButton_2.setBounds(300, 15, 121, 42);
		add(btnNewButton_2);

	}

	

	private void setHeroButton() {
		JButton btnHero = new JButton("");
		btnHero.setIcon(new ImageIcon(MapEditor.class.getResource("/gui/res/iconHeroR.png")));

		btnHero.setBounds(300, 506, 49, 49);

		btnHero.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = 'H';
			}
		});

		add(btnHero);


	}

	private void setOgreButton() {
		  JButton btnOgre = new JButton("");
	        btnOgre.setIcon(new ImageIcon(MapEditor.class.getResource("/gui/res/iconOgre.png")));
	        btnOgre.setBounds(221, 506, 49, 49);
	        btnOgre.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = 'O';
			}
		});
	
		add(btnOgre);

	}

	private void setKeyButton() {
		JButton btnKey = new JButton("");

		btnKey.setIcon(new ImageIcon(MapEditor.class.getResource("/gui/res/iconKey3.png")));

		btnKey.setBounds(377, 506, 52, 49);
		btnKey.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = 'k';
			}
		});

		add(btnKey);

	}

	private void setExitButton() {
		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(MapEditor.class.getResource("/gui/res/iconDoor.png")));
		btnExit.setBounds(134, 506, 52, 49);
		btnExit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = 'S';
			}
		});

		add(btnExit);

	}

	private void setWallButton() {
		JButton btnWall = new JButton("");
		btnWall.setHorizontalTextPosition(SwingConstants.CENTER);
		btnWall.setIcon(new ImageIcon(MapEditor.class.getResource("/gui/res/iconWall.png")));
		btnWall.setBackground(Color.BLACK);
		btnWall.setBounds(63, 506, 49, 49);
		btnWall.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = 'X';
			}
		});

		add(btnWall);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int x = (int) Math.floor((e.getX()-100)/DIVISOR);
		int y = (int) Math.floor((e.getY()-100)/DIVISOR);
		
	
		
		if(c != ' ' && x >=0 && y>=0 && x<size && y <size){
			game_panel.setNewChar(x,y,c);
			game_panel.update();
		}
	
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	private void setPlayButton() {
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				ArrayList<Point> ogres_positions = game_panel.getOgresPositions();
				
				if(!game_panel.mapIsValid()){
					JOptionPane.showMessageDialog(GameWindow.frmMazeGame, 
							  "                             ----- Invalid Map! -----\n"
							+ "Your map must have one hero, one key, one club, one or\n"
							+ "more exit doors and it must be possible for the hero to\n"
							+ "access all positions that are not walls.\n");
				
				game_panel.resetMap();
				game_panel.update();
				return;
				}
				
				Game g =  new Game(0, "R");

				
				GameWindow.pnlGameBar.pnlGame.setSize(game_panel.getMap().length);
				game_panel.normalizeMap();
				g.setMap(new KeepMap(game_panel.getMap(),ogres_positions));
						
				GameWindow.setGame(g);
				
				GameWindow.pnlCustomMap.setVisible(false);
				GameWindow.pnlGameBar.update();
				GameWindow.pnlGameBar.setVisible(true);
				GameWindow.pnlGameBar.requestFocus();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(MapEditor.class.getResource("/gui/res/rsz_play.png")));
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setBounds(134, 15, 121, 49);
		add(btnNewButton_1);

	}
}
