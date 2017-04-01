package logic;

import java.awt.Point;


/**  
* Character.java - responsible for all the characters/entities of the game 
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 
public class Character {

	protected Point position;
	protected char c;
	
	/**  
	 * Character default constructor  
	 *  
	 */  
	public Character(){}
	
	/**  
	 * Character constructor  
	 * 
	 * @param position  initial character position
	 * @param c char that represents the Character
	 *  
	 */
	public Character(Point position, char c) {

		this.position = position;
		this.c = c;

	}
	
	/**  
	 * Gets x position  
	 * 
	 * @return x position
	 *  
	 */
	public int getX() {
		return position.x;
	}

	/**  
	 * Gets y position  
	 * 
	 * @return y position
	 *  
	 */
	public int getY() {
		return position.y;
	}

	/**  
	 * Sets x position  
	 * 
	 * @param x position
	 *  
	 */
	public void setX(int x) {
		this.position.x = x;
	}

	/**  
	 * Sets y position  
	 * 
	 * @param y position
	 *  
	 */
	public void setY(int y) {
		this.position.y = y;
	}


	/**  
	 * Gets position  
	 * 
	 * @return  position
	 */
	public Point getPosition() {
		return position;
	}
	
	/**  
	 * Sets position with coordinates
	 * 
	 * @param  x position
	 * @param  y position
	 */
	public void setPosition(int x, int y){
		this.position.x = x;
		this.position.y = y;
	}
	
	/**  
	 * Sets position with Point Library
	 * 
	 * @param  p position
	 */
	public void setPosition(Point p){
		this.position =p;
	}

	/**  
	 * Gets character symbol  
	 * 
	 * @return  symbol
	 */
	public char getChar() {
		return c;
	}

	/**  
	 * Gets character symbol  
	 * 
	 * @param c character symbol
	 */
	public void setChar(char c){
		this.c = c;
	}
	
	
	/**  
	 * Gets a new Character position 
	 * 
	 * @param actual_pos the actual position
	 * @param direction the direction to follow
	 * @return  new position
	 */
	public Point getNewPosition(Point actual_pos,int direction){
		Point new_pos = new Point();
		
		switch(direction){
		case 0:
			new_pos.x = actual_pos.x+ 1;
			new_pos.y = actual_pos.y;
			break;
		case 1:
			new_pos.x = actual_pos.x - 1;
			new_pos.y = actual_pos.y;
			break;
		case 2:
			new_pos.x = actual_pos.x;
			new_pos.y = actual_pos.y + 1;
			break;
		case 3:
			new_pos.x = actual_pos.x;
			new_pos.y = actual_pos.y - 1;
			break;
		default:
			break;
		}
		
		return new_pos;

	}
		
	
}

