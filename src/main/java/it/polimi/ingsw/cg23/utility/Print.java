package it.polimi.ingsw.cg23.utility;

import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.Board;

/**
 * il file xml da cui si caricano le informazioni per la partita è "ConfigurazionePartita.xml"
 * classe per stampare le info sulla cl
 */
public class Print {
	
	private CreateMap cm;
	private static Logger logger;//logger

	/**
	 * costructor
	 */
	public Print(){
		//configurazione logger
		logger = Logger.getLogger(Print.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
		
		this.cm=new CreateMap();
	}

	/**
	 * 	print all the element of a list
	 * @param lista, the list to print
	 */
	public void printList(List<?>lista){
		
		for(int i=0;i<lista.size();i++){//scorre la lista da stampare
			print("",lista.get(i).toString());//stampa la lista
		}
	}

	/**
	 * stampa un array bidimensionale
	 * @return void
	 * @param array bidimensional array
	 */
	public void printArray(String[][] array){
		String stampa="";

		for(int i=0;i<array.length;i++){//ciclo che scorre le righe
			for(int k=0; k<array[0].length; k++){//ciclo che scorre le colonne
				stampa=stampa.concat(array[i][k]+"      ");
			}
			stampa=stampa.concat("\n");//alla fine di una riga si va a capo
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
	 * @param ogg (something to print, must be "" is there isn't)
	 * @param testo da stampare
	 */
	public void print(Object ogg, String testo){
		logger.info(testo+" "+ogg);	
	}



	/**
	 * call the class PrintMap to create the game map
	 * @param board, the board
	 */
	public void createMap(Board board){
		print("", cm.createMapDraw(board));
	}

	/**
	 * calculate the number of regions
	 * @param cityInfo, the array 
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
