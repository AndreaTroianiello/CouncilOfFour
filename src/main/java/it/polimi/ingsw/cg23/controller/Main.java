package it.polimi.ingsw.cg23.controller;

//import it.polimi.ingsw.cg23.view.CliInterface;

public class Main {
	private Main(){//costruttore vuoto
		
	}
	public static void main(String[] args) {
		Partita game=new Partita();
		game.start();
		//CliInterface cl=new CliInterface();
		//cl.printArray(cl.randomCostruction(15,3));
		
	}
}
