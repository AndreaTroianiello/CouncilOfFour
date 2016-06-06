package it.polimi.ingsw.cg23.server.view;

import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.King;

/**
 * il file xml da cui si caricano le informazioni per la partita è "ConfigurazionePartita.xml"
 * classe per stampare le info sulla cl
 */
public class Print {

	private static Logger logger;//logger

	public Print(){
		//configurazione logger
		logger = Logger.getLogger(Print.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * 	print all the element of a list
	 */
	public void printList(List<?>lista){
		for(int i=0;i<lista.size();i++){//scorre la lista da stampare
			print("",lista.get(i).toString());//stampa la lista
		}
	}

	/**
	 * stampa un array bidimensionale
	 * @return void
	 * @param bidimensional array
	 */
	public void printArray(String[][] array){
		String stampa="";

		for(int i=0;i<array.length;i++){//ciclo che scorre le righe
			for(int k=0; k<array[0].length; k++){//ciclo che scorre le colonne
				stampa+=array[i][k]+"      ";
			}
			stampa+="\n";//alla fine di una riga si va a capo
		}
		print("", stampa);//stampo l'array
	}

	/**
	 * stampa una qualunque cosa gli viene passata e ritorna il valore letto dalla cl
	 * @param testo, what you want to show on the cl
	 * @param ogg, the object you want to show with the test on the cl (null if none)
	 * @return what the user write on the cl
	 */
	public Object writeReturnValue(String testo, Object ogg){
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);//creo uno scanner per leggere l'input da cl

		if(ogg==null)//se l'oggetto passato e' nullo stampo solo il testo
			print("",testo);
		else
			print(ogg, testo);

		return scan.nextLine();//ritorno il valore letto dalla cl
	}

	/**
	 * stampa una qualunque cosa gli viene passata
	 * @return void
	 * @param object (something to print, must be "" is there isn't)
	 * @param testo da stampare
	 */
	public void print(Object ogg, String testo){
		logger.info(testo+" "+ogg);
		
	}

	/**
	 * calculate the number of regions from an city list
	 * @param citta, a city list
	 * @return the number of regions
	 */
	public int regionNumber(List<City> citta){
		int regionNumber=0;

		for(int i=0; i<citta.size()-1; i++){//scorre la lista delle citta'
			if(citta.get(i).getRegion()!=citta.get(i+1).getRegion())//controlla se le regioni sono uguali
				regionNumber++;//incrementa il numero di regioni
		}

		return regionNumber+1;//ritorna il numero di regioni +1 perchè la prima regione
	}

	/**
	 * call the class PrintMap to create the game map
	 * @param city, a list with the city
	 * @param giocatori, a list with the players
	 * @param k, the king
	 */
	public void createMap(List<Region>reg, List<Player> giocatori, King k){
		new CreateMap().createMapDraw(reg, giocatori, k);
	}

	/**
	 * calculate the number of regions
	 * @return the number of regions
	 */
	public int regionsNumber(String[][] cityInfo){
		int n=0;

		for(int i=0; i<cityInfo.length; i++){
			int k;//usata per evitare di eccedere i limiti dell'array
			if(i>0)
				k=i-1;
			else 
				k=i;
			if(cityInfo[i][5]!=cityInfo[k][5])//conta le variazioni delle regioni
				n++;
		}

		return n+1;//aggiunge 1 per contare la prima regione
	}
}
