package gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.GameMap;

public class Map  extends JPanel {
	
	protected ImageIcon wall;
	protected ImageIcon guard;
	protected ImageIcon black_cell;
	protected ImageIcon hero;
	protected ImageIcon key;
	protected ImageIcon door;
	protected ImageIcon ogre;
	protected ImageIcon club;
	protected ImageIcon stuned_ogre;
	protected ImageIcon armed_hero;
	protected ImageIcon end;
	protected ImageIcon sword;
	protected ImageIcon closed_door;
	
	public Map() {
		super();

	}
	
	protected void loadImages() {

		wall = new ImageIcon(this.getClass().getResource("res/wall.png"));

		guard = new ImageIcon(this.getClass().getResource("res/guard.png"));
		black_cell = new ImageIcon(this.getClass().getResource("res/black.png"));
		hero = new ImageIcon(this.getClass().getResource("res/Hero.png"));
		key = new ImageIcon(this.getClass().getResource("res/Key3.png"));
		door = new ImageIcon(this.getClass().getResource("res/door.png"));
		ogre = new ImageIcon(this.getClass().getResource("res/ogre.png"));
		sword = new ImageIcon(this.getClass().getResource("res/Sword.png"));
		stuned_ogre = new ImageIcon(this.getClass().getResource("res/stuned_ogre.png"));
		end = new ImageIcon(this.getClass().getResource("res/end.png"));
		club = new ImageIcon(this.getClass().getResource("res/club.png"));
		closed_door = new ImageIcon(this.getClass().getResource("res/close_door.png"));

		wall = scaleImage(wall);
		guard = scaleImage(guard);
		black_cell = scaleImage(black_cell);
		hero = scaleImage(hero);
		key = scaleImage(key);
		door = scaleImage(door);
		ogre = scaleImage(ogre);
		club = scaleImage(club);
		stuned_ogre= scaleImage(stuned_ogre);
		sword = scaleImage(sword);
		closed_door = scaleImage(closed_door);
	

	}

	private ImageIcon scaleImage(ImageIcon im) {

		Image img = im.getImage();
		Image newimg = img.getScaledInstance(this.getWidth() / 10, this.getHeight() / 10, Image.SCALE_FAST);

		return new ImageIcon(newimg);
	}
	
	public void paintComponent (char[][] map) {
		Point p = new Point();
		char c;

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map.length; j++) {

				c = map[i][j];

				switch (c) {
				case 'X':
					this.add(new JLabel(wall));
					break;
				case 'I':
					this.add(new JLabel(closed_door));
					break;
				case 'G':
					this.add(new JLabel(ogre));
					break;
				case 'H':
					this.add(new JLabel(hero));
					break;
				case 'k':
					this.add(new JLabel(key));
					break;
				case 'S':
					this.add(new JLabel(door));
					break;
				case 'O':
					this.add(new JLabel(ogre));
					break;
				case '*':
					this.add(new JLabel(sword));
					break;
				case '$':
					this.add(new JLabel(club));
					break;
				case 'A':
					this.add(new JLabel(hero));
					break;
				case 'K':
					this.add(new JLabel(hero));
					break;
				case '8':
					this.add(new JLabel(stuned_ogre));
					break;
				case 'g':
					this.add(new JLabel(stuned_ogre));
					break;
				default:
					this.add(new JLabel(black_cell));
					break;
				}

			}
		

	}
	
	public void update(char[][] map){
		removeAll();

		repaint();
		
		paintComponent(map);
		
		
		revalidate();
	}

}
