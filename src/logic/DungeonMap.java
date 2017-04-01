package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import logic.Game.GameState;
import logic.Hero.HeroState;

/**  
* DungeonMap.java - First Level implementation
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 
public class DungeonMap extends GameMap {

	private Guard guard;
	private Character lever;

	boolean next_map = false;
	
	/**  
	 * Level 1 constructor - DungeonMap 
	 * 
	 * @param map  level map
	 * @param guard2 type of guard
	 */
	public DungeonMap(char[][] map, String guard2) {
		super(map);
		Point hero_pos = super.searchChar('H');
		Point guard_pos = super.searchChar('G');
		Point lever_pos = super.searchChar('k');

		hero = new Hero(hero_pos);
		lever = new Character(lever_pos, 'k');

		switch (guard2) {
		case "Suspicious":
			guard = new Suspicious(guard_pos);
			break;
		case "Rookie":
			guard = new Rookie(guard_pos);
			break;
		case "Druken":
			guard = new Drunken(guard_pos);
			break;

		}
	}

	
	/**  
	 * Update the state of the level
	 * 
	 * @param direction hero next direction
	 */
	@Override
	public void update(String direction) {

		hero.move(direction, this);

		guard.updateGuard();

		if (hero.getState() == HeroState.STAIR) {
			next_map = true;
			hero.setKey(false);

		}

		if (hero.gotKey())
			super.openDoors();

	}

	/**  
	 * Draws the level
	 */
	@Override
	public void draw() {
		drawCharacter(lever);
		drawCharacter(hero);
		drawCharacter(guard);
	}

	/**  
	 * Cleans the map
	 */
	@Override
	public void clean() {
		cleanCharacter(hero);
		cleanCharacter(guard);

	}

	/**  
	 * Checks whether the hero was captured or not
	 * 
	 * @return true is the hero was captured, false otherwise
	 */
	public boolean isOver() {

		return super.isCaptured(hero, guard);
			
	}
	
	/**  
	 * Checks whether you have to move to the next level
	 * 
	 * @return true if you have to move to the next level, false otherwise
	 */
	public boolean next(){
		return next_map;
	}
	
	/**  
	 * Sets guard
	 * 
	 * @param g  the guard
	 */
	public void setGuard(Guard g){
		guard = g;
	}
	
	/**  
	 * Gets guard
	 * 
	 * @return  g  the guard
	 */
	public Guard getGuard(){
		return guard;
	}

	/**  
	 * Gets key position
	 * 
	 * @return key position
	 */
	@Override
	public Point getKPos() {
		return lever.getPosition();
	}

}