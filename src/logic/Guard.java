package logic;

import logic.Character;
import java.awt.Point;
import java.util.Random;

/**  
* Guard.java - This is the enemy of the game.
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 
public class Guard extends Character{

	protected int[] guard_y= {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
	protected int[] guard_x= {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};
	protected int guard_idx;
	protected int moveNumber = 4;
	protected boolean front = true;
	
	/**  
	 * Guard constructor  
	 * 
	 * @param position  initial guard position
	 */
	public Guard(Point position){
	
		setPosition(position);
		c= 'G';
		guard_idx =0;
	
	}
	
	/**  
	 * Sets guard actions  
	 *
	 */
	public void updateGuard(){};
	
	
	/**  
	 * Sets the new direction index  
	 *
	 * @return direction index
	 */
	public int getIdxDirection(){
		
		Random rand = new Random();		
		int direction = rand.nextInt(4);
		return direction;
		
	}
	



	/**  
	 * Gets an auxiliary variable that helps with ogre sleeping time
	 *
	 * @return number 
	 */
	public int getMoveNumber() {
		return moveNumber;
	}

	/**  
	 * Checks whereas the guard is front turned 
	 *
	 * @return true if the guard is front turned , false otherwise
	 */
	public boolean isFront() {
		return front;
	}
	
	/**  
	 * Sets guard front state
	 *
	 * @param true if the guard is front turned, false otherwise
	 */
	public void setFront(boolean front) {
		this.front = front;
	}
	
}