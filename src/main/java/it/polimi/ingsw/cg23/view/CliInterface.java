package it.polimi.ingsw.cg23.view;
//import it.polimi.ingsw.cg23.model.*;

/**
 * classe per stampare le info sulla cli
 */
public class CliInterface {
	//per main
	//CliInterface nuovo=new CliInterface();
	//nuovo.startPartita();

	ReadXml lettureXml=new ReadXml();
	final int citynum=lettureXml.cityNumber();//numero di citta'
	final int cityNodeNumber=lettureXml.cityNodeNumber();//numero di attributi delle citta'

	String[][] cityInfo=new String[citynum][cityNodeNumber];//array multidim con city name, link, color, zone

	/**
	 * carica il file con le infromazioni della partita
	 * @return void
	 * @param number of players
	 */
	public void startPartita(){
		cityInfo=lettureXml.readFileXml();
		createMap(cityInfo,4);//stampa array solo per le prove
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
		for(i=0; i<city.length/3;i++){//array che scorre le citta' per regione dastampare 5

			for(int k=0; k<3; k++){//aggiunge le 3 citta', una per ogni regione
				int kk=k;//salvo il valore di k
				if(k==0)k=i;//assegno a k il valore della zona "costa"
				else if(k==1)k=c;//assegno a k il valore della zona "collina"
				else if(k==2)k=m;//assegno a k il valore della zona "montagna"
				String newcity=city[k][0]+"("+city[k][1]+") ";//recupero le informazioni dall'array

				int length=20-newcity.length();//numero di spazi da aggiungere per raggiungere 20
				if(newcity.length()<20){
					for(int j=0; j<length; j++){
						newcity+=" ";//aggiungo spazi
					}
				}
				plancia+=newcity;//aggiungo le citta' appena aggiunte a quelle presenti
				k=kk;//ripristino il valore di k
			}
			c++;//incremento le posizioni delle citta' di collina
			m++;//incremento le posizioni delle citta' di montagna
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)
		}
		String percorsi="Player     Richness          Victory            Money\n";
		int rich=10, vict=10, money=10;//variabili provvisorie
		for(i=0; i<nPlayer; i++){//stampa i punteggi dei giocatori
			percorsi+="P"+(i+1)+"            "+rich+"                "+vict+"                "+money;
			percorsi+="\n";
		}		
		plancia+="\n"+percorsi;//aggiunge alla plancia di gioco i punteggi giocatore
		System.out.println(plancia);//stampo la plancia di gioco
	}
}
