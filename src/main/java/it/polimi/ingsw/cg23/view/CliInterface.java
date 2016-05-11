package it.polimi.ingsw.cg23.view;

import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;



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
	 * stampa la mappa (funziona parzialmente, NON TIENE CONTO DEI LINK FRA CITY)
	 * @return void
	 * @param city, a city list with city info
	 * @param giocatori, a list with the players
	 */
	public void createMap(List<City> city, List<Player>giocatori){//NON TIENE CONTO DEI COLLEGAMENTI
		String plancia="";//la stringa che stampa la plancia di gioco
		int space=35;//spazio da mettere tra una regione e l'altra
		plancia+=addSpace("COSTA", space);//nomi delle regioni
		plancia+=addSpace("COLLINA", space);
		plancia+=addSpace("MONTAGNA", space);
		plancia+="\n";
		int i;
		int regionNumber=scrittureXml.regionNumber(cityInfo);
		int c=city.size()/regionNumber;
		int m=city.size()/regionNumber*2;//posizioni delle zone nella lista 
		for(i=0; i<city.size()/regionNumber; i++,c++,m++){//ciclo che scorre le citta' per regione da stampare 5
			/* i posizione citta' costa
			 * c posizione citta' collina
			 * m posizione citta' montagna
			 */
			for(int k=0; k<regionNumber; k++){//aggiunge le 3 citta', una per ogni regione
				int kk=0;//salvo il valore di k
				if(k==0)
					kk=i;//assegno a kk il valore della zona "costa" in base a k
				else if(k==1)
					kk=c;//assegno a kk il valore della zona "collina" in base a k
				else if(k==2)
					kk=m;//assegno a kk il valore della zona "montagna" in base a k
				String newcity=city.get(kk).getName()+"("+city.get(kk).getType()+")("+city.get(kk).getEmporiums()+") ";//recupero le informazioni dall'array

				if("purple".equals(city.get(kk).getName())){//se la città e' quella viola inizialmente c'è il re (all'avvio della partita)
					newcity=newcity.substring(0,newcity.length()-4)+"KING";//la citta del re non ha bonus
					plancia+=addSpace(newcity, space);
				}else
					plancia+=addSpace(newcity, space);//aggiungo la citta' appena aggiunta a quelle presenti
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)
		}
		plancia+="\n"+createPlayerInfo(giocatori);//aggiunge alla plancia di gioco i punteggi giocatore
		print("",plancia);//stampo la plancia di gioco
	}
	
	/**
	 * stampa la mappa (funziona parzialmente, NON TIENE CONTO DEI LINK FRA CITY)
	 * @return void
	 * @param city, a bidimensional array with city info
	 * @param giocatori, a list with the players
	 */
	public void createMap(String[][] city, List<Player>giocatori){//NON TIENE CONTO DEI COLLEGAMENTI
		String plancia="";//la stringa che stampa la plancia di gioco
		int space=35;//spazio da mettere tra una regione e l'altra
		plancia+=addSpace("COSTA", space);//nomi delle regioni
		plancia+=addSpace("COLLINA", space);
		plancia+=addSpace("MONTAGNA", space);
		plancia+="\n";
		int i;
		int regionNumber=scrittureXml.regionNumber(city);
		int c=city.length/regionNumber;
		int m=city.length/regionNumber*2;//posizioni delle zone nell'array (le citta' devono essere multiple di 3)
		for(i=0; i<city.length/regionNumber; i++,c++,m++){//ciclo che scorre le citta' per regione da stampare 5
			/* i posizione citta' costa
			 * c posizione citta' collina
			 * m posizione citta' montagna
			 */
			for(int k=0; k<regionNumber; k++){//aggiunge le 3 citta', una per ogni regione
				int kk=0;//salvo il valore di k
				if(k==0)
					kk=i;//assegno a kk il valore della zona "costa" in base a k
				else if(k==1)
					kk=c;//assegno a kk il valore della zona "collina" in base a k
				else if(k==2)
					kk=m;//assegno a kk il valore della zona "montagna" in base a k
				String newcity=city[kk][0]+"("+city[kk][1]+")("+city[kk][4]+") ";//recupero le informazioni dall'array

				if("purple".equals(city[kk][1])){//se la città e' quella viola inizialmente c'è il re (all'avvio della partita)
					newcity=newcity.substring(0,newcity.length()-4)+"KING";//la citta del re non ha bonus
					plancia+=addSpace(newcity, space);
				}else
					plancia+=addSpace(newcity, space);//aggiungo la citta' appena aggiunta a quelle presenti
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)
		}
		plancia+="\n"+createPlayerInfo(giocatori);//aggiunge alla plancia di gioco i punteggi giocatore
		print("",plancia);//stampo la plancia di gioco
	}
	
	/**
	 * create the players info
	 * @param giocatori, a list with the players
	 * @return a string to print
	 */
	public String createPlayerInfo(List<Player>giocatori){
		int space=20;//spazi da mettere per rendere il testo ordinato
		String percorsi="";
		percorsi+=addSpace("Player", space)+addSpace("Richness(coin)", space)+addSpace("Victory", space)+addSpace("Nobility", space);
		percorsi+="\n";
		for(int i=0; i<giocatori.size(); i++){//stampa i punteggi dei giocatori
			percorsi+=addSpace(giocatori.get(i).getUser(),space)+giocatori.get(i).getCoins()+"                      "
		+giocatori.get(i).getVictoryPoints()+"                    "+giocatori.get(i).getNobilityBoxPosition();
			percorsi+="\n";
		}
		return percorsi;
	}
	
	/**
	 * calculate the number of regions
	 * @param nome bidimensional array with the city infos
	 * @return the number of the region
	 */
	public int regionsNumber(String[][] nome){
		return scrittureXml.regionNumber(nome);
	}
	
	/**
	 * @param nome the String you want to extend
	 * @param totalSpace the total length you need (string + space)
	 * @return the String extended
	 */
	public String addSpace(String nome, int totalSpace){//aggiunge spazi alla stringa data per raggiungere la lunghezza desiderata
		int length=totalSpace-nome.length();//spazi da aggiungere
		String nomeExtended=nome;
		if(nome.length()<totalSpace){
			for(int j=0; j<length; j++){//ciclo per aggiungere spazi
				nomeExtended+=" ";//aggiungo spazi
			}
		}
		return nomeExtended;
	}
}
