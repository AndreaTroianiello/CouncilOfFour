package it.polimi.ingsw.cg23.view;

//import java.util.Scanner;

public class CliInterface {//classe per la comunicazione con l'utente
	//per main
	//CliInterface nuovo=new CliInterface();
	//nuovo.StartPartita(3);
	
	
	//Scanner scan=new Scanner(System.in);
	ReadXml newpartita=new ReadXml();
	
	public void StartPartita(int players){//inizializza il file per la partita
		String lettura=newpartita.ReadFileXml("city", "yellow");
		System.out.println(lettura);
	}
	
}
