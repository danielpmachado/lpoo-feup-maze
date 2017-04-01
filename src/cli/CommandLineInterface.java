package cli;

import logic.Game;


public class CommandLineInterface {
	public static void main(String[] args){
	

		Game g = new Game(1,"Rookie");
	
		while(true){

		g.updateMap();
		
		System.out.println();
		System.out.println(g.printMap());
		System.out.println("\n Use the WASD keys to control the Hero.");
		System.out.print(" Your Move : ");
		
		g.clean();
		
		if(g.isOver())
			break;
		
		g.readMove();
		g.updateGame();
		
			
		}
		
		
		g.printEnd();
		
	}
	
}
