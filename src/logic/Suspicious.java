package logic;

import java.awt.Point;
import java.util.Random;

/**  
* Suspicious.java - Sometimes this guard inverts his direction.
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 
public class Suspicious extends Guard{

	
	/**  
	 * Guard constructor  
	 * 
	 * @param position  initial guard position
	 */
	public Suspicious(Point position){
		
		super(position);
	
	}
	
	/**  
	 * Sets guard actions  
	 *
	 */
	public void updateGuard(){
			
		int direction = getIdxDirection();
				
		if(direction <3){
			if(guard_idx >= 23)
				guard_idx = 0;
			else
				guard_idx++;
		
		}else{
			if(guard_idx > 0){
				guard_idx--;
			}else{
				guard_idx = 23;
			}
		}
		
		position = new Point(guard_x[guard_idx],guard_y[guard_idx]);
		
	}
	
}
