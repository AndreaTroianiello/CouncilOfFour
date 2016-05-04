package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.view.CliInterface;

public class Main {

	public static void main(String[] args) {
		CliInterface cl=new CliInterface();
		String[][]nopm=cl.startPartita();
		//cl.printArray(nopm);
		cl.savePartita(nopm);
		cl.createMap(nopm, 4);
		
	}
}
