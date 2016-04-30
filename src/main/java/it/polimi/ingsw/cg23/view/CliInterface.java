package it.polimi.ingsw.cg23.view;

//import java.util.Scanner;

public class CliInterface {//classe per la comunicazione con l'utente
	//per main
	//CliInterface nuovo=new CliInterface();
	//nuovo.StartPartita(3);

	//Scanner scan=new Scanner(System.in);
	ReadXml newpartita=new ReadXml();
	int citynum=15;//numero di citta'
	String[][] city=new String[citynum][3];//array multidim con city name, link e color
	
	public void StartPartita(int players){//carica il file con le infromazioni della partita
		String lettura=newpartita.ReadFileXml("cities");
		System.out.println(lettura);
	}
	
	public void Print(Object ogg, String testo){//stampa una qualunque cosa gli viene passata
		System.out.println(testo+" "+ogg);
	}
	
	public void CreateMap(int del, String[][] city){
		String plancia=null;//la stringa che stampa la plancia di gioco
		/*for(int i=0; i<city.length; i++){
			for(int k=0; k<3; k++){
				
				if(k==0)System.out.println(city[i][0]);
				if(k==1){
					System.out.println("--"+city[i][1]+"\n");
					System.out.println("--"+city[i][1]+"\n");
				}
			}
		}*/
	}
	
}
