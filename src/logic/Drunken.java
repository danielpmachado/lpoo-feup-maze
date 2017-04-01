package logic;

import java.awt.Point;
import java.util.Random;

/**  
* Drunken.java - Guard subclasse, this guard is able to fall asleep and to change direction
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 



public class Drunken extends Guard {
	private boolean isSleeping = false;

	/**  
	 * Drunken constructor  
	 * 
	 * @param position  inicial guard position
	 */

	public Drunken(Point position) {
		super(position);

	}
	
	/**  
	 * Guard action when he is sleeping  
	 * 
	 * @param rand  determines if he inverts direction or not
	 */
	public void sleepingAction(int rand){
		
		if(moveNumber <= 0){
			isSleeping  = false;
			c = 'G';
			moveNumber = 4;
			if(rand > 1)
				front = true;
			else front  = false;				
		}else{
			moveNumber--;			
		}			
	}
	
	/**  
	 * Guard action when he is awake   
	 * 
	 * @param rand  determines if he inverts direction or not
	 */
	public void awakeAction(int rand){
		
		if(rand <3){
			if(front){
				if(guard_idx >= 23)
					guard_idx = 0;
				else guard_idx++;
			}
			else {
				if(guard_idx <= 0)
					guard_idx = 23;
				else guard_idx--;
			}
		}else{
			isSleeping = true;
			c= 'g';
		}
	}
	
	/**  
	 * Sets guard actions  
	 *
	 */
	public void updateGuard(){
		
		int random_number = getIdxDirection();
			
		if(isSleeping){
			sleepingAction(random_number);
		}else{			
			awakeAction(random_number);
		}
					
		position = new Point(guard_x[guard_idx],guard_y[guard_idx]);

	}
	
	/**  
	 * Checks whereas the guard is sleeping 
	 *
	 * @return true if the guard is sleeping, false otherwise
	 */
	public boolean isSleeping() {
		return isSleeping;
	}

	/**  
	 * Sets the guard sleeping state
	 *
	 * @param true if the guard is sleeping, false otherwise
	 */
	public void setSleeping(boolean isSleeping) {
		this.isSleeping = isSleeping;
	}

}
