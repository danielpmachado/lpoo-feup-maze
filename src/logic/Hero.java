package logic;

import logic.Character;
import java.awt.Point;


/**  
* Hero.java - This is the heros of the game.
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 
public class Hero extends Character {

	public enum HeroState {
		STAIR, MOVE, DOOR, ARMED
	};

	private HeroState state;
	private boolean armed = false;
	private boolean key = false;

	
	/**  
	 * Hero constructor  
	 * 
	 * @param position  initial character position
	 *  
	 */
	public Hero(Point position) {
	
		setPosition(position);
		setChar('H');
		state = HeroState.MOVE;

	}

	/**  
	 * Gets hero direction 
	 * 
	 * @param direction new direction 
	 * @return new direction 
	 */
	int getDirection(String direction){
		
		int direction_int = 4;
		
		switch (direction) {
		case "D":
			direction_int =0;
			break;
		case "A":
			direction_int =1;
			break;
		case "S":
			direction_int =2;
			break;
		case "W":
			direction_int = 3;
			break;

		default:
			break;
		}
		return direction_int;
	}
	
	
	/**  
	 * Sets hero state
	 * 
	 * @param c symbol
	 * @param new_position  positionof the hero
	 */
	public void setState(char c, Point new_position){
		
		switch (c) {
		case 'X':
			break;
		case 'I':
			state = HeroState.DOOR;
			break;
		case ' ':
			position = new_position;
			state = HeroState.MOVE;
			break;
		case 'S':
			position = new_position;
			state = HeroState.STAIR;
			break;
		case 'k':
			position = new_position;
			key = true;
			break;

		case '*':
			position = new_position;
			armed = true;
			setChar('A');

		default:
			break;
		}
		
	}
	
	
	/**  
	 * Moves the hero
	 * 
	 * @param direction new direction
	 * @param map  game map
	 */
	public void move(String direction, GameMap map) {

		Point new_position = new Point(getX(), getY());
		char c;
		int direction_int=4;

		direction_int = getDirection(direction);
				
		new_position = super.getNewPosition(getPosition(), direction_int);
		
		c = map.getChar(new_position);
		
		setState(c, new_position);

	}


	/**  
	 * Gets hero state
	 * 
	 * @return state of the hero
	 */
	public HeroState getState() {
		return state;
	}


	/**  
	 * Checks if the hero got the key
	 * 
	 * @return true f the hero got the key, false otherwise
	 */
	public boolean gotKey() {

		return key;
	}

	/**  
	 * Checks if the hero is armed
	 * 
	 * @return true f the hero is armed, false otherwise
	 */
	public boolean isArmed(){
		return armed;
	}
	
	/**  
	 * Sets if the hero got the key
	 * 
	 * @param key  the key
	 */
	public void setKey(boolean key){
		this.key = key;
	}
	
}
