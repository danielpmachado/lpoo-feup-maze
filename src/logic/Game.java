package logic;

import logic.Hero;
import logic.Hero.HeroState;
import logic.Guard;
import logic.Ogre;

import java.awt.Point;

import java.util.ArrayList;

import java.util.Random;

import java.util.Scanner;


/**  
* Game.java - The Game Class
* @author  Daniel e Sofia
* @version 1.0 
* 
*/ 
public class Game {

	public enum GameState {
		WON, RUNNING, LOST
	};

	private char[][] map_1 = { 
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, 
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, 
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, };

	private char[][] map_2 = {
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, 
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
			{ 'X', 'H', ' ', '*', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, };


	private GameState state;
	private String direction;
	private GameMap map; 
	private int num_ogres;
	private char guard;
	
	/**  
	 * Game constructor
	 * 
	 * @param num_ogres  number of ogres
	 * @param guard2 type of guard
	 */
	public Game(int num_ogres,String guard2) {
		state = GameState.RUNNING;

		map = new DungeonMap(map_1, guard2);
		this.num_ogres = num_ogres;
		
	}

	/**  
	 * Gets the map
	 * 
	 * @return map of the game
	 */
	public GameMap getMap() {
		return map;
	}
	
	/**  
	 * Sets the map
	 * 
	 * @param map the map of the game
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	/**  
	 * Sets the hero next movement
	 * 
	 * @param direction the direction of the hero
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**  
	 * Prints the map on the console
	 * 
	 * @return the map printed on the console
	 */
	public String printMap() {

		 return map.printMap();

	}
		
	/**  
	 * Cleans the screen
	 */
	public void clean() {
		map.clean();	
	}

	/**  
	 * Draws the map
	 */
	public void updateMap() {
				
		map.draw();	
	}

	/**  
	 * Reads next move from the keyboard
	 */
	public void readMove() {

		Scanner keyboard = new Scanner(System.in);

		direction = keyboard.nextLine().toUpperCase();

	}

	/**  
	 * Updates the game state
	 */
	public void updateGame() {
		
		map.update(direction);
		
		if (map instanceof DungeonMap && map.next()){
			map = new KeepMap(map_2,num_ogres);
		}
		
		if (map instanceof KeepMap && map.next()){
			state = GameState.WON;
		}		 
	}

	/**  
	 * Checks if the game is Over
	 */
	public boolean isOver() {
				
		if(map.isOver()){
			state = GameState.LOST;
			return true;
		}
	
		if( state == GameState.WON) return true;
	
		return false;
	}

	
	/**  
	 * Prints the End of the Game
	 */
	public void printEnd() {
		System.out.println();
		System.out.println();

		switch (state) {
		case LOST:
			System.out.print("--------------");
			System.out.print(" GAME OVER ");
			System.out.print("--------------");
			break;
		case WON:
			System.out.println();
			System.out.println();

			System.out.print("---------------");
			System.out.print(" VICTORY ");
			System.out.print("---------------");
			break;
		default:
			break;
		}
	}

	/**  
	 * Gets the hero
	 * 
	 * @return hero
	 */
	public Hero getHero() {
		return map.getHero();
	}

	/**  
	 * Updates the game with clean, updateGame and updateMap functions
	 * 
	 * @return hero
	 */
	public void update(){
		
		clean();
		updateGame();
		updateMap();
	}
	
	/**  
	 * Gets the game state
	 * 
	 * @return state of the game
	 */
	public GameState getState(){
		return state;
	}


}
