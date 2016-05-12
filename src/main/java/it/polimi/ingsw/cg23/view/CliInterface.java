package it.polimi.ingsw.cg23.view;

import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.King;



/**
 * il file xml da cui si caricano le informazioni per la partita è "ConfigurazionePartita.xml"
 * classe per stampare le info sulla cl
 */
public class CliInterface {
	ReadXml lettureXml=new ReadXml();//classe per leggere l'xml
	WriteXml scrittureXml=new WriteXml();//classe per scrivre l'xml
	RandomCity randomC=new RandomCity();
	//il file xml da cui comincia la partita è "ConfigurazionePartita.xml"
	final int citynum=lettureXml.cityNumber("ConfigurazionePartita.xml");//numero di citta'
	final int cityNodeNumber=lettureXml.cityNodeNumber("ConfigurazionePartita.xml");//numero di attributi delle citta'
	String[][] cityInfo=new String[citynum][cityNodeNumber];//array multidim con city name, color, link, id, bonus, zone

	/**
	 * code to use the logger (request by sonar)
	 */
	public void logging(){
		Logger logger=Logger.getLogger("my logger");
		ConsoleHandler handler = new ConsoleHandler();
		logger.setLevel(Level.ALL);
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
		handler.setLevel(Level.ALL);
	}

	/**
	 * carica il file xml con le infromazioni della partita
	 * @return a bidimensional array with all the city info
	 * @param endPath, the name of the file to read
	 */
	public String[][] leggiXml(String endPath){
		cityInfo=lettureXml.readFileXml(endPath);
		/* array cityInfo prototype returned
		 * coloumn 0: name of the city
		 * coloumn 1: color of the city
		 * coloumn 2: link of the city (the id to which it is connected the city)
		 * coloumn 3: id of the city
		 * coloumn 4: bonus of the city
		 * coloumn 5: region of the city
		 */
		return cityInfo;
	}

	/**
	 * salva le informazioni della partita in un file xml
	 * @param bidimensional array with city info
	 * @return an error if there is some errors
	 */
	public String savePartita(String [][] cityInfo, String endPath){
		String err=scrittureXml.writeXmlFile(cityInfo, endPath);
		print("", err);//stampa il valore di eventuali errori
		return err;
		/* array cityInfo prototype require
		 * coloumn 0: name of the city
		 * coloumn 1: color of the city
		 * coloumn 2: link of the city (the id to which it is connected the city)
		 * coloumn 3: id of the city
		 * coloumn 4: bonus of the city
		 * coloumn 5: region of the city
		 */
	}

	/**
	 * create randomly the cities
	 * @param citynum the number of cities to create
	 */
	public String[][] createCity(int citynum){
		return randomC.createCity(citynum);
	}

	/**
	 * stampa un array bidinemsionale
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
	 * ritorna il valore letto dalla cl
	 * @return an object of the readed value from the command line
	 */
	public Object returnValue(){
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);//creo uno scanner per leggere l'input da cl
		return scan.nextLine();
	}

	/**
	 * stampa una qualunque cosa gli viene passata e ritorna il valore letto dalla cl
	 * @param testo, what you want to show on the cl
	 * @param ogg, the object yu want to show with the test on the cl (null if none)
	 * @return what the user write on the cl
	 */
	public Object writeReturnValue(String testo, Object ogg){
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);//creo uno scanner per leggere l'input da cl
		if(ogg!=null)//se l'oggetto passato e' nullo stampo solo il testo
			print("",testo);
		else
			print("",testo);
		return scan.nextLine();//ritorno il valore letto dalla cl
	}

	/**
	 * stampa una qualunque cosa gli viene passata
	 * it is the ONLY method to use the system.out.println
	 * @return void
	 * @param object (something to print, must be "" is there isn't)
	 * @param testo da stampare
	 */
	public void print(Object ogg, String testo){
		System.out.println(testo+" "+ogg);

	}

	/**
	 * calculate the number of regions from an city list
	 * @param citta, a city list
	 * @return the number of regions
	 */
	public int regionNumber(List<City> citta){
		int regionNumber=0;
		for(int i=0; i<citta.size()-1; i++){
			if(citta.get(i).getRegion()!=citta.get(i+1).getRegion())
				regionNumber++;
		}
		return regionNumber+1;
	}
	
	/**
	 * call the class PrintMap to create the game map
	 * @param city, a list with the city
	 * @param giocatori, a list with the players
	 * @param k, the king
	 */
	public void createMap(List<City>city, List<Player>giocatori, King k){
		new PrintMap().createMap(city, giocatori, k);
	}
	
	/**
	 * calculate the number of regions
	 * @param nome bidimensional array with the city infos
	 * @return the number of the region
	 */
	public int regionsNumber(String[][] nome){
		return scrittureXml.regionNumber(nome);
	}

}
