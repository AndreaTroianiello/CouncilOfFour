package it.polimi.ingsw.cg23.view;

import java.util.Scanner;



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

	/* codice se di dovesse usare il logger (come dice sonar)
	 * Logger logger=Logger.getLogger("my logger");
	 * ConsoleHandler handler = new ConsoleHandler();
	 * logger.setLevel(Level.ALL);
	 * handler.setFormatter(new SimpleFormatter());
	 * logger.addHandler(handler);
	 * handler.setLevel(Level.ALL);
	 */

	/*	public void addGiocatore(String name){
		Player P1=new Player(name, ,0,)
	}
	 */

	/**
	 * carica il file xml con le infromazioni della partita
	 * @return a bidimensional array with all the city info
	 * @param endPath, the name of the file to read
	 */
	public String[][] leggiXml(String endPath){
		cityInfo=lettureXml.readFileXml(endPath);
		printArray(cityInfo);
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
		String k=scrittureXml.writeXmlFile(cityInfo, endPath);
		System.out.println(k);//stampa il valore di eventuali errori
		return k;
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
		for(int i=0;i<array.length;i++){
			for(int k=0; k<array[0].length; k++){
				System.out.print(array[i][k]+"      ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * ritorna il valore letto dalla cl
	 * @return an object of the readed value from the command line
	 */
	public Object returnValue(){
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);//creto uno scanner per leggere l'input da cl
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
		Scanner scan=new Scanner(System.in);
		if(ogg!=null)
			System.out.println(testo+" "+ogg);
		else
			System.out.println(testo);
		return scan.nextLine();
	}

	/**
	 * stampa una qualunque cosa gli viene passata
	 * @return void
	 * @param object (something to print)
	 * @param testo da stampare
	 */
	public void print(Object ogg, String testo){
		if(ogg!=null)
			System.out.println(testo+" "+ogg);
		else
			System.out.println(testo);
	}

	/**
	 * stampa la mappa (funziona parzialmente)
	 * @return void
	 * @param bidimensional array with city
	 */
	public void createMap(String[][] city, int nPlayer){
		String plancia="";//la stringa che stampa la plancia di gioco
		int space=35;
		plancia+=addSpace("COSTA", space);//nomi delle regioni
		plancia+=addSpace("COLLINA", space);
		plancia+=addSpace("MONTAGNA", space);
		plancia+="\n";
		int i;
		int regionNumber=scrittureXml.regionNumber(city);
		int c=city.length/regionNumber;
		int m=city.length/regionNumber*2;//posizioni delle zone nall'array (le citta' devono essere multiple di 3)
		for(i=0; i<city.length/regionNumber; i++,c++,m++){//array che scorre le citta' per regione da stampare 5
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
					newcity+="KING";
					plancia+=addSpace(newcity, space);
				}else
					plancia+=addSpace(newcity, space);//aggiungo la citta' appena aggiunta a quelle presenti
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)
		}
		String percorsi="Player     Richness          Victory            Money\n";
		//int sup=c.playerNumber();
		
		int rich=10;//variabili provvisorie
		int vict=10;//variabili provvisorie
		int money=10;//variabili provvisorie
		for(i=0; i<nPlayer; i++){//stampa i punteggi dei giocatori
			percorsi+="P"+(i+1)+"            "+rich+"                "+vict+"                "+money;
			percorsi+="\n";
		}		
		plancia+="\n"+percorsi;//aggiunge alla plancia di gioco i punteggi giocatore
		System.out.println(plancia);//stampo la plancia di gioco
	}
	
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
