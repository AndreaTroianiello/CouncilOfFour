package it.polimi.ingsw.cg23.view;

import java.util.List;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.King;

public class PrintMap {
	CliInterface cl=new CliInterface();
	/**
	 * stampa la mappa (funziona parzialmente, NON TIENE CONTO DEI LINK FRA CITY)
	 * @return void
	 * @param king, the king
	 * @param city, a city list with city info
	 * @param giocatori, a list with the players
	 */
	public void createMap(List<City> city, List<Player>giocatori, King king){//NON TIENE CONTO DEI COLLEGAMENTI
		String plancia="";//la stringa che stampa la plancia di gioco
		int space=50;//spazio da mettere tra una regione e l'altra
		plancia+=addSpace("COSTA", space);//nomi delle regioni
		plancia+=addSpace("COLLINA", space);
		plancia+=addSpace("MONTAGNA", space);
		plancia+="\n";
		int i;
		int regionNumber=cl.regionNumber(city);//recupera il numero di regioni
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
				String newcity=city.get(kk).getName()+"("+city.get(kk).getType()+")("+city.get(kk).getEmporiums()+")";//recupero le informazioni dall'array
				
				String citybonus=cityBonus(city.get(kk));//bonus della citta'

				newcity+="("+citybonus+")";//aggiungo alla nuova citta' i suoi bonus
				
				String kingCity=king.getCity().getName();//nome della citta' del re
				if(kingCity.equals(city.get(kk).getName())){//se la città e' quella che sta ciclando la segno
					newcity=newcity.substring(0,newcity.length()-6)+"KING";//la citta del re non ha bonus
					plancia+=addSpace(newcity, space);
				}else
					plancia+=addSpace(newcity, space);//aggiungo la citta' appena aggiunta a quelle presenti
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)
		}
		plancia+="\n"+createPlayerInfo(giocatori);//aggiunge alla plancia di gioco i punteggi giocatore
		cl.print("",plancia);//stampo la plancia di gioco
	}
	
	/**
	 * convert all the city bonus to a string
	 * @param city, the city you want to have the bonus
	 * @return a string with all the city bonus
	 */
	public String cityBonus(City city){
		String bonus="";
		
		for(int i=0; i<city.getToken().size(); i++){//ciclo che scorre i bonus di una citta'
			bonus+=city.getToken().get(i);//ritorna il nome del bonus
			bonus+=", ";
		}
		if(bonus.length()==0)//se non ci sono bonus ritorna una stringa vuota
			return "";
		bonus=bonus.substring(0, bonus.length()-2);//tolgo l'ultima virgola e spazio
		return bonus;
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
		int regionNumber=cl.regionsNumber(city);
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
		plancia+=createPlayerInfo(giocatori);//aggiunge alla plancia di gioco i punteggi giocatore
		cl.print("",plancia);//stampo la plancia di gioco
	}

	/**
	 * create the players info
	 * @param giocatori, a list with the players
	 * @return a string to print
	 */
	public String createPlayerInfo(List<Player>giocatori){
		int space=20;//spazi da mettere per rendere il testo ordinato
		String percorsi="";
		percorsi+=addSpace("Player", space)+addSpace("Richness(coin)", space);
		percorsi+=addSpace("Victory", space)+addSpace("Nobility", space)+addSpace("Assistants", space);
		percorsi+="\n";
		for(int i=0; i<giocatori.size(); i++){//stampa i punteggi dei giocatori
			percorsi+=addSpace(giocatori.get(i).getUser(),space)+addSpace(giocatori.get(i).getCoins(),space);
			percorsi+=addSpace(giocatori.get(i).getVictoryPoints(),space)+addSpace(giocatori.get(i).getNobilityBoxPosition(),space);
			percorsi+=addSpace(giocatori.get(i).getAssistants(), space);//VERIFICARE
			percorsi+="\n";
		}
		return percorsi;
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

	/**
	 * 
	 * @param number, the number to add space
	 * @param totalSpace, the number of space you want to add
	 * @return a string with the number and the space
	 */
	public String addSpace(int number, int totalSpace){	
		String numberExtended=Integer.toString(number);
		int length=totalSpace-digits(number);//spazi da aggiungere
		if(digits(number)<totalSpace){
			for(int j=0; j<length; j++){//ciclo per aggiungere spazi
				numberExtended+=" ";//aggiungo spazi
			}
		}
		return numberExtended;
	}
	
	/**
	 * calculate the number of digits in a number
	 * @param number, the number you want to calculate the digits
	 * @return the number of digits
	 */
	public int digits(int number){
		int count=0;
		int num=number;//creata nuova variabile perche' richiesta da sonar
		if(num==0)//se il numero e' 0 ha una cifra
			return 1;
		if(num<0)//se il numero e' minore di 0 lo modulizzo
			num=Math.abs(num);
		while(num>0){
			num/=10;
			count++; 
		}
		return count;
	}
}
