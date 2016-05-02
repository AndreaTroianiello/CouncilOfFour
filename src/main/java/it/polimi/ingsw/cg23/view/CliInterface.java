package it.polimi.ingsw.cg23.view;

import java.util.Scanner;

/**
 * classe per stampare le info sulla cli
 */
public class CliInterface {
	ReadXml lettureXml=new ReadXml();
	final int citynum=lettureXml.cityNumber();//numero di citta'
	final int cityNodeNumber=lettureXml.cityNodeNumber();//numero di attributi delle citta'
	
	String[][] cityInfo=new String[citynum][cityNodeNumber];//array multidim con city name, link, color, zone

	/**
	 * carica il file con le infromazioni della partita
	 * @return a bidimensional array with all the city info
	 */
	public String[][] startPartita(){
		cityInfo=lettureXml.readFileXml();
		return cityInfo;
	}
	
	public int regionNumber(){
		startPartita();
		int n=0;
		for(int i=0; i<cityInfo.length; i++){
			int k;
			if(i>0)
				k=i-1;
			else 
				k=i;
			if(cityInfo[i][5]!=cityInfo[k][5])
				n++;
		}
		return n+1;
	}
	
	/**
	 * stampa un array bidinemsionale
	 * @return void
	 * @param bidimensional array
	 */
	public void printArray(String[][] array){
		for(int i=0;i<array.length;i++){
			for(int k=0; k<array[0].length; k++){
				System.out.print(array[i][k]+"    ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * @return an object of the readed value from the command line
	 */
	public Object returnValue(){
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);//creto uno scanner per leggere l'input da cl
		return scan.nextLine();
	}
	
	/**
	 * 
	 * @param testo, what you want to show on the cl
	 * @param ogg, the object yu want to show with the test on the cl (null if none)
	 * @return what the user write on the cl
	 */
	public Object writeReturnValue(String testo, Object ogg){
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		System.out.println(testo+" "+ogg);
		return scan.nextLine();
	}

	/**
	 * stampa una qualunque cosa gli viene passata
	 * @return void
	 * @param object (something to print)
	 * @param testo da stampare
	 */
	public void print(Object ogg, String testo){
		System.out.println(testo+" "+ogg);
	}
	
	/**
	 * stampa la mappa (funziona parzialmente)
	 * @return void
	 * @param bidimensional array with city
	 */
	public void createMap(String[][] city, int nPlayer){
		String plancia="     Costa            Collina              Montagna\n";//la stringa che stampa la plancia di gioco
		int i;
		int c=city.length/3;
		int m=city.length/3*2;//posizioni delle zone nall'array (le citta' devono essere multiple di 3)
		for(i=0; i<city.length/3; i++,c++,m++){//array che scorre le citta' per regione dastampare 5
			/* i posizione citta' costa
			 * c posizione citta' collina
			 * m posizione citta' montagna
			 */
			for(int k=0; k<3; k++){//aggiunge le 3 citta', una per ogni regione
				int kk=0;//salvo il valore di k
				if(k==0)
					kk=i;//assegno a kk il valore della zona "costa" in base a k
				else if(k==1)
					kk=c;//assegno a kk il valore della zona "collina" in base a k
				else if(k==2)
					kk=m;//assegno a kk il valore della zona "montagna" in base a k
				String newcity=city[kk][0]+"("+city[kk][1]+") ";//recupero le informazioni dall'array
				plancia+=addSpace(newcity, 20);//aggiungo le citta' appena aggiunte a quelle presenti
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)
		}
		String percorsi="Player     Richness          Victory            Money\n";
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

	/**
	 * @param nome the String you want to extend
	 * @param totalSpace the total length you need (string + space)
	 * @return the String extended
	 */
	public String addSpace(String nome, int totalSpace){//aggiunge spazi alla stringa data per raggiungere la lunghezza desiderata
		int length=totalSpace-nome.length();//spazi da aggiungere
		String nomeExtended=nome;
		if(nome.length()<20){
			for(int j=0; j<length; j++){//ciclo per aggiungere spazi
				nomeExtended+=" ";//aggiungo spazi
			}
		}
		return nomeExtended;
	}
}
