package it.polimi.ingsw.cg23.view;

import java.util.Scanner;

public class CliInterface {//classe per la comunicazione con l'utente

	Scanner scan=new Scanner(System.in);
	ReadXml newpartita=new ReadXml();
	public void StartPartita(int players){//inizializza il file per la partita
		String lettura=newpartita.ReadFile();
	}
	
}
