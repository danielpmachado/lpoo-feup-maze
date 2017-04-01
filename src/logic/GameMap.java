package logic;

import java.awt.Point;
import java.util.ArrayList;

/**  
* GameMap.java - The Game Map Class
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 
public abstract class GameMap {

	private char[][] map;
	private int rows;
	private int cols;
	
	protected Hero hero;
	
	/**  
	 * Game constructor
	 * 
	 * @param map the map of the game
	 */
	public GameMap(char[][] map) {
		this.map = map;
		rows = map.length;
		cols = map[0].length;
	}

	/**  
	 * Gets the character symbol
	 * 
	 * @param position the position of the character
	 * @return the symbol
	 */
	public char getChar(Point position) {

		char c = 'X';

		
		c = map[position.y][position.x];

		return c;
	}

	
	/**  
	 * Prints the map
	 *
	 * @return the map printed
	 */
	public String printMap() {
		
		String printed_map ="";

		for (int i = 0; i < rows; i++) {
			printed_map += " ";
			for (int j = 0; j < cols; j++) {
				printed_map += map[i][j] + " ";
			}
			printed_map += "\n";
		}	
		return printed_map;
	}

	
	/**  
	 * Draws a Character
	 *
	 * @return c the Character printed
	 */
	public  void drawCharacter(Character c) {
		map[c.getY()][c.getX()] = c.getChar();

	}
	

	/**  
	 * Cleans a Character
	 *
	 * @return c the Character cleaned
	 */
	public  void cleanCharacter(Character c) {
	
		map[c.getY()][c.getX()] = ' ';
	}

	
	/**  
	 * Opens the doors of the maze
	 *
	 */
	public  void openDoors() {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (map[i][j] == 'I')
					map[i][j] = 'S';
			}
		}
	}
	
	/**  
	 * Searches a character
	 *
	 * @param c the symbol of the character
	 * @return the position of the character
	 */
	public Point searchChar(char c){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (map[i][j] == c)
					return new Point(j,i);
			}
		}
		
		return new Point(-1,-1);
	}
	
	/**  
	 * Verifies if the victim is captured or not
	 *
	 * @param victim the victim
	 * @param captor the captor
	 * @return true if the victim is captured, false otherwise
	 */
	public boolean isCaptured(Character victim, Character captor) {

		if (captor.getChar() == 'g')
			return false;

		if (victim.getX() == captor.getX() + 1 && victim.getY() == captor.getY())
			return true;

		if (victim.getX() == captor.getX() - 1 && victim.getY() == captor.getY())
			return true;

		if (victim.getX() == captor.getX() && victim.getY() == captor.getY() + 1)
			return true;

		if (victim.getX() == captor.getX() && victim.getY() == captor.getY() - 1)
			return true;

		if (victim.getX() == captor.getX() && victim.getY() == captor.getY())
			return true;

		return false;
	}
	
	/**  
	 * Updates the game state
	 */
	public abstract void update(String Direction);
	
	/**  
	 * Cleans the screen
	 */
	public abstract void clean();
	
	/**  
	 * Draws the game map
	 */
	public abstract void draw();

	/**  
	 * Checks if the game is over
	 * 
	 * @return true if the game is over
	 */
	public abstract boolean isOver();

	/**  
	 * Checks if you should pass to next level
	 * 
	 * @return true if you should, false otherwise
	 */
	public abstract boolean next();

	/**  
	 * Gets hero
	 * 
	 * @return hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**  
	 * Gets the position of the key
	 * 
	 * @return position of the key
	 */
	public abstract Point getKPos();

	
	/**  
	 * Gets the rows
	 * 
	 * @return rows
	 */
	public int getRows() {
		return rows;
		
	}
	
	/**  
	 * Gets the cols
	 * 
	 * @return cols
	 */
	public int getCols() {
		return cols;
		
	}

	
	/**  
	 * Gets the map
	 * 
	 * @return map
	 */
	public char[][] getMatrix() {
		return map;
	}

	


	
}
