package it.polimi.ingsw.cg23.view;

//import java.util.Scanner;

public class CliInterface {//classe per la comunicazione con l'utente
	//per main
	//CliInterface nuovo=new CliInterface();
	//nuovo.StartPartita(4);

	//Scanner scan=new Scanner(System.in);
	ReadXml newpartita=new ReadXml();
	final int citynum=15;//numero di citta'
	String[][] city=new String[citynum][4];//array multidim con city name, link e color, zone
	
	public void prova(){//PROVA PER ME
		
		String[][] city=new String[citynum][4];
		for(int i=0;i<city.length;i++){
			for(int k=0;k<4;k++){
				city[i][k]=Integer.toString(i);
			}
		}
		//String[][] city={[][][][]};
		CreateMap(city);
	}
	
	public void StartPartita(int players){//carica il file con le infromazioni della partita
		String lettura=newpartita.ReadFileXml();
		System.out.println(lettura);
	}
	
	public void Print(Object ogg, String testo){//stampa una qualunque cosa gli viene passata
		System.out.println(testo+" "+ogg);
	}
	
	public void CreateMap(String[][] city){//stampa la mappa
		/*String plancia="   costa         collina          montagna\n";//la stringa che stampa la plancia di gioco
		//System.out.println(city.length);
		int n=city.length;
		for(int i=0; i<n; i++){//le righe da stampare sono 3
			//for(int k=0; k<3; k++){
				plancia+=city[i][0]+"("+city[i][1]+") ";//0: nome citta', 1: colore citta'
				if(i<=9)plancia+="  ";//aggiunge piu' spazi se 
			//}
				if((i+1)%6==0)plancia+="\n";
				//if()
				
		}
		
		System.out.println(plancia);
	*/	
	}
		
}
