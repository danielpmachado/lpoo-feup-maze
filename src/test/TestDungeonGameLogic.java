package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.Drunken;
import logic.DungeonMap;
import logic.Game;
import logic.GameMap;
import logic.Guard;
import logic.Hero;
import logic.KeepMap;
import logic.Rookie;
import logic.Suspicious;
import logic.Game.GameState;

import java.awt.Point;
import java.util.ArrayList;

public class TestDungeonGameLogic {
	char[][] map_1 = {
			{ 'X', 'I', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', 'G', 'X' }, 
			{ 'X', ' ', ' ', ' ', 'X' },
			{ 'X', 'k', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };
	
	private char[][] map_2 = { 
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

	@Test
	public void printMap() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));
		((DungeonMap) g.getMap()).setGuard(new Guard(new Point(3, 1)));
		
		assertEquals(g.printMap()," X I X X X \n X H   G X \n X       X \n X k     X \n X X X X X \n");
	}
	
	
	@Test
	public void moveHeroIntoFreeCell() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));
		((DungeonMap) g.getMap()).setGuard(new Guard(new Point(3, 1)));

		assertEquals(new Point(1, 1), g.getHero().getPosition());

		g.setDirection("S");
		g.updateGame();
		g.updateMap();

		assertEquals(new Point(1, 2), g.getHero().getPosition());
		assertEquals(g.getMap().getChar(new Point(1, 2)), 'H');
	}

	@Test
	public void moveHeroIntoWall() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));

		g.setDirection("A");
		g.updateGame();

		assertEquals(new Point(1, 1), g.getHero().getPosition());
	}
	
	@Test
	public void testInvalidDirection() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));

		g.setDirection("X");
		g.updateGame();

		assertEquals(new Point(1, 1), g.getHero().getPosition());
	}
	
	

	@Test
	public void heroIsCapturedLeft() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));
		((DungeonMap) g.getMap()).setGuard(new Guard(new Point(3, 1)));

		assertFalse(g.isOver());

		g.setDirection("D");
		g.updateGame();

		assertTrue(g.isOver());

	}

	@Test
	public void heroIsCapturedDown() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));
		((DungeonMap) g.getMap()).setGuard(new Guard(new Point(3, 1)));

		assertEquals(g.getMap().getChar(new Point(3, 1)), 'G');

		assertFalse(g.isOver());

		g.setDirection("S");
		g.updateGame();
		g.setDirection("D");
		g.updateGame();
		g.setDirection("D");
		g.updateGame();

		assertTrue(g.isOver());

	}

	@Test
	public void heroIsCapturedUp() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));
		((DungeonMap) g.getMap()).setGuard(new Guard(new Point(2, 2)));

		g.clean();
		g.updateMap();

		assertFalse(g.isOver());

		g.setDirection("D");
		g.updateGame();

		assertTrue(g.isOver());

	}

	@Test
	public void heroIsCapturedRight() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));
		((DungeonMap) g.getMap()).setGuard(new Guard(new Point(2, 3)));

		g.clean();
		g.updateMap();

		assertFalse(g.isOver());

		g.setDirection("D");
		g.updateGame();
		g.setDirection("D");
		g.updateGame();
		g.setDirection("S");
		g.updateGame();
		g.setDirection("S");
		g.updateGame();

		assertTrue(g.isOver());

	}

	@Test
	public void moveHeroIntoClosedDoor() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));

		g.setDirection("W");
		g.updateGame();

		assertEquals(new Point(1, 1), g.getHero().getPosition());
		assertFalse(g.isOver());

	}

	@Test
	public void doorsOpenWhenLeverActivated() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));
		assertEquals('I', g.getMap().getChar(new Point(1, 0)));

		g.setDirection("S");
		g.updateGame();
		g.setDirection("S");
		g.updateGame();

		assertEquals('S', g.getMap().getChar(new Point(1, 0)));
	}

	@Test
	public void testDoors() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));;
		assertEquals('I', g.getMap().getChar(new Point(1, 0)));

		g.setDirection("S");
		g.updateGame();

		assertEquals('I', g.getMap().getChar(new Point(1, 0)));
	}

	@Test
	public void levelUpUponReachingStairs() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_1,"Rookie"));
		((DungeonMap) g.getMap()).setGuard(new Guard(new Point(3, 1)));

		g.clean();
		g.setDirection("S");
		g.updateGame();

		g.clean();
		g.setDirection("S");
		g.updateGame();

		g.clean();
		g.setDirection("W");
		g.updateGame();

		g.clean();
		g.setDirection("W");
		g.updateGame();

		g.clean();
		g.setDirection("W");
		g.updateGame();

		assertTrue(g.getMap() instanceof KeepMap);

	}


	@Test
	public void heroNotCapuredByDrunken() {
		Game g = new Game(1,"Druken");
		g.setMap(new DungeonMap(map_1,"Druken"));

		((DungeonMap) g.getMap()).setGuard(new Drunken(new Point(3, 1)));

		assertFalse(g.isOver());

		g.clean();
		g.updateMap();
		g.setDirection("S");
		g.updateGame();

		((DungeonMap) g.getMap()).getGuard().setPosition(3, 1);

		assertFalse(g.isOver());
	}
	
	/*
	 * Teste Manhoso
	 * 
	@Test
	public void testRookie() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_2,"Rookie"));
		
		boolean left= false;
		boolean right= false;
		boolean up = false;
		boolean down = false;
		
		Point old_pos;
		Point new_pos;
		
		while(!left || !right || !up || !down){
			
			old_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();
		
			((DungeonMap) g.getMap()).getGuard().updateGuard();
			
			new_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();
			
			if(isLeft(new_pos, old_pos))
				left = true;
			
			if(isRight(new_pos, old_pos))
				right = true;
			
			if( isUp(new_pos, old_pos))
				up = true;
			
			if( isDown(new_pos, old_pos))
				down = true;
			
	
		}
	}
*/
	@Test
	public void testRookiePath() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_2,"Rookie"));
		
		boolean completePath= false;
		int numSteps = 0;
		
		
		Point pos_0 = new Point(8,1);
		Point pos_1 = new Point(7,1);
		Point pos_2 = new Point(7,2);
		Point pos_3 = new Point(7,3);
		Point pos_4 = new Point(7,4);
		Point pos_5 = new Point(7,5);
		Point pos_6 = new Point(6,5);
		Point pos_7 = new Point(5,5);
		Point pos_8 = new Point(4,5);
		Point pos_9 = new Point(3,5);
		Point pos_10 = new Point(2,5);
		Point pos_11 = new Point(1,5);
		Point pos_12 = new Point(1,6);
		Point pos_13 = new Point(2,6);
		Point pos_14 = new Point(3,6);
		Point pos_15 = new Point(4,6);
		Point pos_16 = new Point(5,6);
		Point pos_17 = new Point(6,6);
		Point pos_18 = new Point(7,6);
		Point pos_19 = new Point(8,6);
		Point pos_20 = new Point(8,5);
		Point pos_21 = new Point(8,4);
		Point pos_22 = new Point(8,3);
		Point pos_23 = new Point(8,2);
		
		
		Point[] path = {pos_0,pos_1,pos_2,pos_3,pos_4,pos_5,pos_6,pos_7,pos_8,pos_9,pos_10,pos_11,pos_12,
						pos_13,pos_14,pos_15,pos_16,pos_17,pos_18,pos_19,pos_20,pos_21,pos_22,pos_23};
		
		Point old_pos;
		Point new_pos;
		Point array_pos;
		
		
		while(!completePath){
			
			if(numSteps > 23){
			
				((DungeonMap) g.getMap()).getGuard().setPosition(path[numSteps]);
				old_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();

				((DungeonMap) g.getMap()).getGuard().updateGuard();
				new_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();

				((DungeonMap) g.getMap()).getGuard().setPosition(path[numSteps+1]);
				array_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();

				if( array_pos == new_pos)
					numSteps++;				
			}
			else completePath = true;
		}
	}


	@Test
	public void testRookieNewRound() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_2,"Rookie"));
		
		for(int i = 0; i < 100; i++){
			((DungeonMap) g.getMap()).getGuard().updateGuard();
			
		}
		
	}
	
	@Test
	public void testRookieInicialPosition() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_2,"Rookie"));
		
		Point oldp = ((DungeonMap) g.getMap()).getGuard().getPosition();
		Point newp = ((DungeonMap) g.getMap()).getGuard().getPosition();
		
		for(int i = 0; i < 47; i++){
			
			((DungeonMap) g.getMap()).getGuard().updateGuard();
			newp = ((DungeonMap) g.getMap()).getGuard().getPosition();
		}
		
		assertEquals(newp,oldp);
	}
	
	@Test
	public void testRookieSetPosition() {
		Game g = new Game(1,"Rookie");
		g.setMap(new DungeonMap(map_2,"Rookie"));
		
		Point pos_1 = new Point(7,1);
		Point pos_2 = new Point(7,2);
					
		((DungeonMap) g.getMap()).getGuard().updateGuard();
		Point newp_1 = ((DungeonMap) g.getMap()).getGuard().getPosition();
				
		assertEquals(newp_1,pos_1);
		
		((DungeonMap) g.getMap()).getGuard().updateGuard();
		Point newp_2 = ((DungeonMap) g.getMap()).getGuard().getPosition();
		
		assertEquals(newp_2,pos_2);
	}
	
	@Test
	public void testSuspicious() {
		Game g = new Game(1,"Suspicious");
		g.setMap(new DungeonMap(map_2,"Suspicious"));

		((DungeonMap) g.getMap()).setGuard(new Suspicious(new Point(8, 1)));
		
		Point oldp;
		Point newp;

		for (int i = 0; i < 100; i++) {
			
			oldp = ((DungeonMap) g.getMap()).getGuard().getPosition();

		
			g.setDirection("W");
			g.updateGame();
	
			
			newp = ((DungeonMap) g.getMap()).getGuard().getPosition();
			
			assertTrue(adjacent(oldp,newp));
			
		}

	}
	
	@Test
	public void testSuspiciousArray() {
		Game g = new Game(1,"Suspicious");
		g.setMap(new DungeonMap(map_2,"Suspicious"));
		
		Point pos_0 = new Point(8,1);
		Point pos_1 = new Point(7,1);
		Point pos_2 = new Point(7,2);
		Point pos_3 = new Point(7,3);
		Point pos_4 = new Point(7,4);
		Point pos_5 = new Point(7,5);
		Point pos_6 = new Point(6,5);
		Point pos_7 = new Point(5,5);
		Point pos_8 = new Point(4,5);
		Point pos_9 = new Point(3,5);
		Point pos_10 = new Point(2,5);
		Point pos_11 = new Point(1,5);
		Point pos_12 = new Point(1,6);
		Point pos_13 = new Point(2,6);
		Point pos_14 = new Point(3,6);
		Point pos_15 = new Point(4,6);
		Point pos_16 = new Point(5,6);
		Point pos_17 = new Point(6,6);
		Point pos_18 = new Point(7,6);
		Point pos_19 = new Point(8,6);
		Point pos_20 = new Point(8,5);
		Point pos_21 = new Point(8,4);
		Point pos_22 = new Point(8,3);
		Point pos_23 = new Point(8,2);
		
		
		Point[] path = {pos_0,pos_1,pos_2,pos_3,pos_4,pos_5,pos_6,pos_7,pos_8,pos_9,pos_10,pos_11,pos_12,
						pos_13,pos_14,pos_15,pos_16,pos_17,pos_18,pos_19,pos_20,pos_21,pos_22,pos_23};
		
		Point old_pos;
		Point new_pos;
		Point array_pos_1;
		Point array_pos_2;




		boolean completePath= false;
		int numSteps = 0;

		while(!completePath){

			if(numSteps > 23){

				((DungeonMap) g.getMap()).getGuard().setPosition(path[numSteps]);
				old_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();

				((DungeonMap) g.getMap()).getGuard().updateGuard();
				new_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();

				((DungeonMap) g.getMap()).getGuard().setPosition(path[numSteps+1]);
				array_pos_1 = ((DungeonMap) g.getMap()).getGuard().getPosition();

				if(numSteps == 0)
					numSteps = 24;

				((DungeonMap) g.getMap()).getGuard().setPosition(path[numSteps-1]);
				array_pos_2 = ((DungeonMap) g.getMap()).getGuard().getPosition();

				if( array_pos_1 == new_pos){
					numSteps++;
					assertEquals(array_pos_1,new_pos);
				}

				if(array_pos_2 == new_pos){
					numSteps--;
					assertEquals(array_pos_2,new_pos);
				}
			}
			else completePath = true;
		}	


	}

	@Test
	public void testSuspiciousOneMove() {
		Game g = new Game(1,"Suspicious");
		g.setMap(new DungeonMap(map_2,"Suspicious"));
		
		Point pos_4 = new Point(8,2);
		Point pos_2 = new Point(7,1);
		
		((DungeonMap) g.getMap()).getGuard().setPosition(new Point(7,3));
		
		((DungeonMap) g.getMap()).getGuard().updateGuard();
		Point new_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();
			
		
		if( new_pos ==  pos_4)
			assertEquals(pos_4,new_pos);
		if( new_pos == pos_2)
			assertEquals(pos_2,new_pos);
			
	}
	
	

	@Test
	public void testDrunken() {
		Game g = new Game(1,"Drunken");
		g.setMap(new DungeonMap(map_1,"Drunken"));
		((DungeonMap) g.getMap()).setGuard(new Drunken(new Point(8, 1)));

		Point oldp;
		Point newp;

		for (int i = 0; i < 100; i++) {
			oldp = ((DungeonMap) g.getMap()).getGuard().getPosition();

	
			g.setDirection("W");
			g.updateGame();

			
			newp = ((DungeonMap) g.getMap()).getGuard().getPosition();
			
			assertTrue(adjacent(oldp,newp));

			if (((Drunken) ((DungeonMap) g.getMap()).getGuard()).isSleeping()){
				assertEquals(((DungeonMap) g.getMap()).getGuard().getChar(), 'g');
				assertEquals(newp,oldp);
			}
			

		}
	}
	
	@Test
	public void testDrunkenArray() {
		Game g = new Game(1,"Drunken");
		g.setMap(new DungeonMap(map_2,"Drunken"));
		((DungeonMap) g.getMap()).setGuard(new Drunken(new Point(8, 1)));
		
		
		Point pos_0 = new Point(8,1);
		Point pos_1 = new Point(7,1);
		Point pos_2 = new Point(7,2);
		Point pos_3 = new Point(7,3);
		Point pos_4 = new Point(7,4);
		Point pos_5 = new Point(7,5);
		Point pos_6 = new Point(6,5);
		Point pos_7 = new Point(5,5);
		Point pos_8 = new Point(4,5);
		Point pos_9 = new Point(3,5);
		Point pos_10 = new Point(2,5);
		Point pos_11 = new Point(1,5);
		Point pos_12 = new Point(1,6);
		Point pos_13 = new Point(2,6);
		Point pos_14 = new Point(3,6);
		Point pos_15 = new Point(4,6);
		Point pos_16 = new Point(5,6);
		Point pos_17 = new Point(6,6);
		Point pos_18 = new Point(7,6);
		Point pos_19 = new Point(8,6);
		Point pos_20 = new Point(8,5);
		Point pos_21 = new Point(8,4);
		Point pos_22 = new Point(8,3);
		Point pos_23 = new Point(8,2);
		
		
		Point[] path = {pos_0,pos_1,pos_2,pos_3,pos_4,pos_5,pos_6,pos_7,pos_8,pos_9,pos_10,pos_11,pos_12,
						pos_13,pos_14,pos_15,pos_16,pos_17,pos_18,pos_19,pos_20,pos_21,pos_22,pos_23};
		
		Point old_pos;
		Point new_pos;
		Point array_pos_1;
		Point array_pos_2;




		boolean completePath= false;
		int numSteps = 0;

		while(!completePath){

			if(numSteps > 23){

				((DungeonMap) g.getMap()).getGuard().setPosition(path[numSteps]);
				old_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();

				((DungeonMap) g.getMap()).getGuard().updateGuard();
				new_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();

				((DungeonMap) g.getMap()).getGuard().setPosition(path[numSteps+1]);
				array_pos_1 = ((DungeonMap) g.getMap()).getGuard().getPosition();

				if(numSteps == 0)
					numSteps = 24;

				((DungeonMap) g.getMap()).getGuard().setPosition(path[numSteps-1]);
				array_pos_2 = ((DungeonMap) g.getMap()).getGuard().getPosition();

				if( array_pos_1 == new_pos){
					numSteps++;
					assertEquals(array_pos_1,new_pos);
				}

				if(array_pos_2 == new_pos){
					numSteps--;
					assertEquals(array_pos_2,new_pos);
				}
				if(old_pos == new_pos)
					assertEquals(old_pos,new_pos);
				
			}
			else completePath = true;
		}	
	}
	
	@Test
	public void testDrunkenSleeping() {
		
		Game g = new Game(1,"Drunken");
		g.setMap(new DungeonMap(map_2,"Drunken"));
		((DungeonMap) g.getMap()).setGuard(new Drunken(new Point(8, 1)));
		
		for(int i = 0; i < 1000; i++){
		
			((DungeonMap) g.getMap()).getGuard().updateGuard();
			
			if(((DungeonMap) g.getMap()).getGuard().getChar() == 'g')
				assertEquals(((DungeonMap) g.getMap()).getGuard().getChar(),'g');
			else assertEquals(((DungeonMap) g.getMap()).getGuard().getChar(),'G');
		
		}
		
	}
	
	
	
	@Test
	public void testDrunkenSleeps() {
		
		Game g = new Game(1,"Drunken");
		g.setMap(new DungeonMap(map_2,"Drunken"));
		((DungeonMap) g.getMap()).setGuard(new Drunken(new Point(8, 1)));

		for(int i = 0; i < 1000; i++){
			((DungeonMap) g.getMap()).getGuard().updateGuard();

			int num  = ((DungeonMap) g.getMap()).getGuard().getMoveNumber();
			boolean sleeping = ((Drunken)((DungeonMap) g.getMap()).getGuard()).isSleeping();
			
			if(num > 0 && num < 4 && sleeping)
				assertEquals(((DungeonMap) g.getMap()).getGuard().getChar(),'g');	
			
			if(num == 4 && !sleeping)
				assertEquals(((DungeonMap) g.getMap()).getGuard().getChar(),'G');
			
			if(num == 0 && !sleeping)
				assertEquals(((DungeonMap) g.getMap()).getGuard().getChar(),'G');
					
			if(((Drunken)((DungeonMap) g.getMap()).getGuard()).isSleeping())
				assertEquals(((DungeonMap) g.getMap()).getGuard().getChar(),'g');
		}
	}
	
	@Test
	public void testDrunkenFront() {
		
		Game g = new Game(1,"Drunken");
		g.setMap(new DungeonMap(map_2,"Drunken"));
		((DungeonMap) g.getMap()).setGuard(new Drunken(new Point(8, 1)));
		
			
		for(int i = 0; i < 100; i++){
			((DungeonMap) g.getMap()).getGuard().updateGuard();
			
			Point old_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();
			
			((DungeonMap) g.getMap()).getGuard().setFront(true);
			((DungeonMap) g.getMap()).getGuard().updateGuard();
			
			Point new_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();
			
			if(((Drunken)((DungeonMap) g.getMap()).getGuard()).isSleeping())
				assertEquals(old_pos,new_pos);	
		}
		
		for(int i = 0; i < 100; i++){
			((DungeonMap) g.getMap()).getGuard().updateGuard();
			
			Point old_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();
			
			((DungeonMap) g.getMap()).getGuard().setFront(false);
			((DungeonMap) g.getMap()).getGuard().updateGuard();
			
			Point new_pos = ((DungeonMap) g.getMap()).getGuard().getPosition();
			
			if(((DungeonMap) g.getMap()).getGuard().isFront())
				assertEquals(old_pos,new_pos);	
			
		}
		
	}
	
	
	private boolean adjacent(Point oldp, Point newp) {

		if (oldp.getX() == newp.getX() + 1 && oldp.getY() == newp.getY())
			return true;

		if (oldp.getX() == newp.getX() - 1 && oldp.getY() == newp.getY())
			return true;

		if (oldp.getX() == newp.getX() && oldp.getY() == newp.getY() + 1)
			return true;

		if (oldp.getX() == newp.getX() && oldp.getY() == newp.getY() - 1)
			return true;

		if (oldp.getX() == newp.getX() && oldp.getY() == newp.getY())
			return true;

		return false;
	}
	
	
	private boolean isLeft(Point new_pos, Point old_pos) {

		if (new_pos.getX() == old_pos.getX() - 1 && new_pos.getY() == old_pos.getY())
			return true;
		else
			return false;
	}
	
	private boolean isRight(Point new_pos, Point old_pos) {

		if (new_pos.getX() == old_pos.getX() + 1 && new_pos.getY() == old_pos.getY())
			return true;
		else
			return false;
	}
	
	private boolean isUp(Point new_pos, Point old_pos) {

		if (new_pos.getX() == old_pos.getX()  && new_pos.getY() == old_pos.getY()-1)
			return true;
		else
			return false;
		
	}
	
	private boolean isDown(Point new_pos, Point old_pos) {

		if (new_pos.getX() == old_pos.getX()  && new_pos.getY() == old_pos.getY()+1)
			return true;
		else
			return false;
		
	}
}
