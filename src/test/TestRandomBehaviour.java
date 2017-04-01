package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.awt.Point;

import logic.Character;
import logic.DungeonMap;
import logic.Game;
import logic.Guard;
import logic.KeepMap;

public class TestRandomBehaviour {

	private char[][] map = { 
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'O', 'X' }, 
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
			{ 'X', 'H', ' ', '*', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, };

	@Test
	public void printMap() {
		Game g = new Game(1,"Rookie");
		g.setMap(new KeepMap(map,1));
		
		assertEquals(g.printMap()," X X X X X X X X X X \n"
								+ " I               O X \n"
								+ " X               * X \n"
								+ " X                 X \n"
								+ " X               k X \n"
								+ " X                 X \n"
								+ " X                 X \n"
								+ " X                 X \n"
								+ " X H   *           X \n"
								+ " X X X X X X X X X X \n");
	}
	

	@Test(timeout =1000)
	public void testOgreRandomPositions() {
		Game g = new Game(1,"Rookie");
		g.setMap(new KeepMap(map,1));
		
		boolean left= false;
		boolean right= false;
		boolean up = false;
		boolean down = false;
		boolean key =false;

		
		Point old_pos;
		Point new_pos;
		
		while(!left || !right || !up || !down || !key) {
			
			old_pos = ((KeepMap) g.getMap()).getOgres().get(0).getPosition();
			
			((KeepMap) g.getMap()).getOgres().get(0).move(g.getMap());
			
			new_pos = ((KeepMap) g.getMap()).getOgres().get(0).getPosition();
			
			if(isLeft(new_pos, old_pos))
				left = true;
			
			if(isRight(new_pos, old_pos))
				right = true;
			
			if( isUp(new_pos, old_pos))
				up = true;
			
			if( isDown(new_pos, old_pos))
				down = true;
			
			if(((KeepMap) g.getMap()).getOgres().get(0).getChar() == '$'){
				assertEquals(((KeepMap) g.getMap()).getOgres().get(0).getPosition(), new Point(8,1));
				key = true;
				
			}
	
		}
	}
	
	@Test(timeout =1000)
	public void testClubRandomPositions() {
		Game g = new Game(1,"Rookie");
		g.setMap(new KeepMap(map,1));
		
		boolean left= false;
		boolean right= false;
		boolean up = false;
		boolean down = false;
		boolean key = false;
		
		Point ogre_pos;
		Point club_pos;
		
		while(!left || !right || !up || !down){
			
			((KeepMap) g.getMap()).getOgres().get(0).move(g.getMap());
			((KeepMap) g.getMap()).getOgres().get(0).swingClub(g.getMap());
			
			ogre_pos = ((KeepMap) g.getMap()).getOgres().get(0).getPosition();
			club_pos = ((KeepMap) g.getMap()).getOgres().get(0).getClub().getPosition();
			
			if(isLeft(ogre_pos, club_pos))
				left = true;
			
			if(isRight(ogre_pos, club_pos))
				right = true;
			
			if( isUp(ogre_pos, club_pos))
				up = true;
			
			if( isDown(ogre_pos, club_pos))
				down = true;
			
			if(((KeepMap) g.getMap()).getOgres().get(0).getClub().getChar() == '$'){
				
				assertEquals(((KeepMap) g.getMap()).getOgres().get(0).getClub().getPosition(), new Point(8,1));
				key = true;
				
			}
			
	
		}
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
