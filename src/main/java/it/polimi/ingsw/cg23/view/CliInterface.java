package it.polimi.ingsw.cg23.view;

import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.exception.XmlException;

/**
 * il file xml da cui si caricano le informazioni per la partita è "ConfigurazionePartita.xml"
 * classe per stampare le info sulla cl
 */
public class CliInterface {
	private ReadCittaXml lettureXml;//classe che legge l'xml delle citta'
	private ReadCostructionXml costructionXml;//classe che legge l'xml delle carte costruzione
	private ReadNobilityTrackXml nt;//classe che si occupa di leggere il nobility track dall'xml

	Logger logger=Logger.getLogger("my logger");//logger
	ConsoleHandler handler = new ConsoleHandler();
	
	//il file xml da cui comincia la partita è "ConfigurazionePartita.xml"
	private final int citynum;//numero di citta'
	private final int cityNodeNumber;//numero di attributi delle citta'
	private String[][] cityInfo;//array multidim con city name, color, link, id, bonus, zone
	
	public CliInterface(){
		this.lettureXml=new ReadCittaXml();
		this.costructionXml=new ReadCostructionXml();
		this.nt=new ReadNobilityTrackXml();
		this.citynum=lettureXml.cityNumber("ConfigurazionePartita.xml");
		this.cityNodeNumber=lettureXml.cityNodeNumber("ConfigurazionePartita.xml");
		this.cityInfo=new String[citynum][cityNodeNumber];
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
	 * carica il file xml con le infromazioni della partita
	 * @return a bidimensional array with all the city info
	 * @param endPath, the name of the file to read
	 */
	public String[][] leggiXml(String endPath){
		
			try {
				cityInfo=lettureXml.readFileXml(endPath);
			} catch (XmlException e) {
				logger.log(Level.ALL, e.toString());
			}
		/* array cityInfo prototype returned
		 * coloumn 0: name of the city
		 * coloumn 1: color of the city
		 * coloumn 2: link of the city (the id to which it is connected the city)
		 * coloumn 3: id of the city
		 * coloumn 4: bonus of the city
		 * coloumn 5: region of the city
		 */
		return cityInfo;//ritorna l'array con le informazioni delle citta'
	}
	
	/**
	 * read the bonus region from the xml file
	 * @param endPath, the name of the file with .xml
	 * @return a bidimensional array with the region name and bonus region
	 */
	public String[][] getBonusRegion(String endPath){
		 return lettureXml.getBonusRegion(endPath);//ritorna un array con i bonus della regione
	}
	
	/**
	 * 
	 * @param endPath, the final part of the xml file path (name+extension)
	 * @return a bidimensional array with the costruction cards info
	 */
	public String[][]getCostruction(String endPath){
		return costructionXml.readCardXml(endPath);
		/* array CostructionCard prototype require
		 * coloumn 0: region
		 * coloumn 1: city
		 * coloumn 2: bonus
		 */
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
	 * it is the ONLY method to use the system.out.println
	 * @return void
	 * @param object (something to print, must be "" is there isn't)
	 * @param testo da stampare
	 */
	public void print(Object ogg, String testo){
		logger.setLevel(Level.ALL);
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		System.out.println(testo+" "+ogg);
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
	public void createMap(List<Region>reg, List<Player>giocatori, King k){
		new PrintMap().createMapDraw(reg, giocatori, k);
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

	/**
	 * find the type(color) of the city
	 * @param endPath, the name of the file with the .xml
	 * @return a bidimensional array with the type(color)
	 */
	public String[][] getType(String endPath){
		return lettureXml.getType(endPath);
	}
	
	/**
	 * find the lenght of the nobility track
	 * @param endPath, the name of the file with the .xml
	 * @return the lenght of the nobility track
	 */
	public int getNobilityTrackLenght(String endPath){
		return nt.nobilityTrackLenght(endPath);
	}

	/**
	 * return the info of nobility track
	 * @param endPath, the name of the file with the .xml
	 * @return a bidimensional array with the nobility track info
	 */
	public String[][] getNobilityTrackBonus(String endPath){
		return nt.nobilityTrackBonus(endPath);
	}
}
