package logic;

import java.awt.Point;
import java.util.ArrayList;

import logic.Hero.HeroState;

/**  
* DungeonMap.java - First Level implementation
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 
public class KeepMap extends GameMap {

	
	private Character key;
	private Character club;
	private ArrayList<Ogre> ogres = new ArrayList<Ogre>();

	boolean won = false;

	/**  
	 * Level 2 constructor - KeepMap 
	 * 
	 * @param map  level map
	 * @param num_ogres number of ogres
	 */
	public KeepMap(char[][] map, int num_ogres) {
		super(map);

		Point hero_pos = super.searchChar('H');
		Point club_pos = super.searchChar('*');
		Point key_pos = super.searchChar('k');
	

		hero = new Hero(hero_pos);
		key = new Character(key_pos, 'k');
		club = new Character(club_pos, '*');

		for (int i = 0; i < num_ogres; i++)
			ogres.add(new Ogre(new Point(7, 1)));

	}
	
	/**  
	 * Level 2 constructor - KeepMap 
	 * 
	 * @param map  level map
	 * @param nuogres_positionsm_ogres ogres positions
	 */
	public KeepMap(char[][] map, ArrayList<Point> ogres_positions){
		super(map);
		
		Point hero_pos = super.searchChar('H');
		Point club_pos = super.searchChar('*');
		Point key_pos = super.searchChar('k');
	

		hero = new Hero(hero_pos);
		key = new Character(key_pos, 'k');
		club = new Character(club_pos, '*');

		for (int i = 0; i <ogres_positions.size(); i++){
			
			ogres.add(new Ogre(ogres_positions.get(i)));
			
		}
		
	}

	
	/**  
	 * Updates level2 map
	 * 
	 * @param direction  next hero direction 
	 */
	@Override
	public void update(String direction) {

		hero.move(direction, this);

		if (hero.gotKey())
			hero.setChar('K');

		for (Ogre ogre : ogres) {
			ogre.move(this);
			ogre.swingClub(this);
		}

		if (hero.getState() == HeroState.DOOR && hero.gotKey()){
			openDoors();
			
		}

		if (hero.getState() == HeroState.STAIR)
			won = true;
		

	}

	
	/**  
	 * Cleans level2 map
	 */
	@Override
	public void clean() {
		cleanCharacter(hero);

		for (Ogre ogre : ogres) {
			cleanCharacter(ogre);
			cleanCharacter(ogre.getClub());
		}

	}

	/**  
	 * Draws level2 map
	 */
	@Override
	public void draw() {
		drawCharacter(hero);

		if (!hero.gotKey())
			drawCharacter(key);

		if (!hero.isArmed())
			drawCharacter(club);

		for (Ogre ogre : ogres) {
			drawCharacter(ogre);
			drawCharacter(ogre.getClub());
		}

	}

	
	/**  
	 * Checks whether level2 is over
	 * 
	 * @return true if level2 is over, false otherwise
	 */
	@Override
	public boolean isOver() {
		for (Ogre ogre : ogres) {

			if (isCaptured(ogre, hero) && hero.isArmed())
				ogre.getStuned();

			if (isCaptured(hero, ogre) && !hero.isArmed()) {
				return true;
				
			}

			if (isCaptured(hero, ogre.getClub())) {
				return true;
			}
		}

		return false;
	}

	/**  
	 * Checks whether you should win
	 * 
	 * @return true if you should win, false otherwise
	 */
	@Override
	public boolean next() {
		return won;
	}
	
	/**  
	 * Gets all ogres
	 * 
	 * @return ogres
	 */
	public ArrayList<Ogre> getOgres(){
		return ogres;
	}

	
	/**  
	 * Gets the position of the key
	 * 
	 * @return position
	 */
	@Override
	public Point getKPos() {
		return new Point(8,1);
	}
	


}