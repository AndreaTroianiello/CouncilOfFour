package it.polimi.ingsw.cg23.view;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.King;

/**
 * print the game map
 *
 */
public class PrintMap {
	CliInterface cl;

	public PrintMap(){
		this.cl=new CliInterface();
	}
	
	/**
	 * stampa la mappa (funziona parzialmente, NON TIENE CONTO DEI LINK FRA CITY)
	 * @return void
	 * @param king, the king
	 * @param reg, a region list
	 * @param giocatori, a list with the players
	 */
	public void createMap(List<Region> reg, List<Player>giocatori, King king){//NON TIENE CONTO DEI COLLEGAMENTI

		List<City> city=getCityfromRegion(reg);


		String gamemap="";//la stringa che stampa la plancia di gioco
		int space=60;//spazio da mettere tra una regione e l'altra

		gamemap+=printName(reg, space)+"\n";//aggiungo i nomi delle regioni

		int regionNumber=cl.regionNumber(city);//recupera il numero di regioni
		for(int i=0; i<city.size()/regionNumber; i++){//ciclo che scorre le citta' per regione da stampare 5

			for(int k=0; k<regionNumber; k++){//aggiunge le 3 citta', una per ogni regione
				int kk=i+(city.size()/regionNumber)*k;//salvo il valore di k

				String newcity=city.get(kk).getName()+"("+city.get(kk).getType()+")"+city.get(kk).getEmporiums()+"";//recupero le informazioni dall'array

				String citybonus=cityBonus(city.get(kk));//bonus della citta'

				newcity+="("+citybonus+")";//aggiungo alla nuova citta' i suoi bonus
				String kingCity=king.getCity().getName();//nome della citta' del re

				if(kingCity.equals(city.get(kk).getName())){//se la città e' quella che sta ciclando la segno
					newcity=newcity.substring(0,newcity.length()-2)+"KING";//la citta del re non ha bonus
					gamemap+=addSpace(newcity, space);
				}else
					gamemap+=addSpace(newcity, space);//aggiungo la citta' appena aggiunta a quelle presenti
			}
			gamemap+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)
		}
		
		gamemap+="\n"+createPlayerInfo(giocatori);//aggiunge alla plancia di gioco i punteggi giocatore
		cl.print("",gamemap);//stampo la plancia di gioco
	}

	/**
	 * find the city from the regions
	 * @param regions, the regions
	 * @return teh city list
	 */
	public List<City> getCityfromRegion(List<Region> regions){
		List<City> city = new ArrayList<>();
		for(int i=0; i<regions.size(); i++){
			city.addAll(regions.get(i).getCities());
		}
		return city;
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
	 * create the players info
	 * @param giocatori, a list with the players
	 * @return a string to print
	 */
	public String createPlayerInfo(List<Player>giocatori){
		int space=20;//spazi da mettere per rendere il testo ordinato
		String percorsi="";
		percorsi+=addSpace("Player", space)+addSpace("Richness(coin)", space)+addSpace("Victory", space)+
				addSpace("Nobility", space)+addSpace("Assistants", space)+addSpace("Carte politiche", space);
		percorsi+="\n";
		for(int i=0; i<giocatori.size(); i++){//stampa i punteggi dei giocatori
			percorsi+=addSpace(giocatori.get(i).getUser(),space)+addSpace(giocatori.get(i).getRichness().getCoins(),space);
			percorsi+=addSpace(giocatori.get(i).getVictoryTrack().getVictoryPoints(),space)+addSpace(giocatori.get(i).getNobilityBoxPosition(),space);
			percorsi+=addSpace(giocatori.get(i).getAssistantsPool().getAssistants(), space);
			percorsi+=addSpace(giocatori.get(i).getHand().size(), space);
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

	/**
	 * create the map with draw card
	 * @param reg, the regions list
	 * @param giocatori, the player list
	 * @param king, the king
	 */
	public void createMapDraw(List<Region> reg, List<Player> giocatori, King king){
		List<City> city=getCityfromRegion(reg);

		String plancia="\nPlancia di gioco\n";//la stringa che stampa la plancia di gioco
		int space=50;//spazio da mettere tra una regione e l'altra
		int regionNumber=cl.regionNumber(city);//recupera il numero di regioni
		int citypReg=city.size()/regionNumber;
		
		plancia+=printName(reg, space)+"\n";//aggiungo i nomi delle regioni

		for(int i=0; i<citypReg; i++){//ciclo che scorre le citta' per regione da stampare 5
			int minus=32;
			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge i -
				plancia+=addSpace(addMinus(minus), space);
			}
			plancia+="\n";

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge la il nome della citta'
				String name;
				if(king.getCity().getName().equals(city.get(i+citypReg*k).getName())){
					name=addSpace("|"+city.get(i+citypReg*k).getName()+" - "+city.get(i+citypReg*k).getId(), minus-1)+"|";
					name=name.substring(0, name.length()-7);//faccio spazio per aggiungere king
					name+="KING  |";//aggiungo king alla citta' del re
				}
				else
					name=addSpace("|"+city.get(i+citypReg*k).getName()+" - "+city.get(i+citypReg*k).getId(), minus-1)+"|";

				plancia=plancia+addSpace(name, space);//aggiungo il nome della citta'
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge il tipo(colore) della citta'
				String tipo=addSpace("|"+city.get(i+citypReg*k).getType(), minus-1)+"|";
				plancia=plancia+addSpace(tipo, space);//aggiungo il tipo della citta'
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge i bonus
				String bonus=addSpace("|"+cityBonus(city.get(i+citypReg*k)), minus-1)+"|";
				plancia=plancia+addSpace(bonus, space);//aggiungo i bonus della citta'
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge i vicini della citta'
				String vicini=addSpace("|"+getNeighbourID(city.get(i+citypReg*k)), minus-1)+"|";
				plancia=plancia+addSpace(vicini, space);//aggiungo i vicini della citta'
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge gli empori
				String empori=addSpace("|Empori:"+city.get(i+citypReg*k).getEmporiums(), minus-1)+"|";
				plancia=plancia+addSpace(empori, space);//aggiungo gli empori della citta'
			}
			plancia+="\n";//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge i -
				plancia+=addSpace(addMinus(minus), space);
			}
			plancia+="\n";

		}
		plancia+=createCostructionShowed(reg, space/2);//aggiungo le carte costruzione alla plancia
		plancia+="\n";
		plancia+=createPlayerInfo(giocatori);//aggiunge alla plancia di gioco i punteggi giocatore
		cl.print("",plancia);//stampo la plancia di gioco
	}
	
	/**
	 * 
	 * @param reg, the regions list
	 * @param space, the space
	 * @return, a string with the spaced name of the region
	 */
	public String printName(List<Region>reg, int space){
		String name="";
		for(int j=0; j<reg.size(); j++){//ciclo che scorre le regioni
			name+=addSpace(reg.get(j).getName().toUpperCase(), space);//nomi delle regioni
		}
		return name;
	}
	
	/**
	 * creathe the costruction card to print
	 * @param region, the region
	 * @param space, half of the space
	 * @return a string with the costruction card
	 */
	public String createCostructionShowed(List<Region> region, int space){
		String cardShowed="";//plancia delle carte costrucione
		int regSize=region.size();//numero di regioni
		
		for(int j=0; j<regSize; j++){//ciclo che scorre le regioni
			cardShowed+=addSpace("Carte Costruzione "+region.get(j).getName()+":", space*2);//carte costruzione
		}
		cardShowed+="\n";

		for(int j=0; j<regSize; j++){//ciclo che scorre le regioni
			String mino="";
			List<BusinessPermitTile> costruction=region.get(j).getDeck().getShowedDeck();//recupero la lista delle carte permesso costruzione
			for(int i=0; i<costruction.size(); i++){//ciclo che scorre le carte costruzione della regione
				mino+=addSpace(addMinus(space-5), space);//aggiungo i -
			}
			cardShowed+=mino;//aggiungo il tutto alla string finale
		}
		cardShowed+="\n";

		for(int j=0; j<regSize; j++){//ciclo che scorre le regioni
			List<BusinessPermitTile> costruction=region.get(j).getDeck().getShowedDeck();//recupero la lista delle carte permesso costruzione
			for(int i=0; i<costruction.size(); i++){//ciclo che scorre le carte costruzione della regione
				String card="";//stringa con gli id delle citta'
				card+=addSpace("|"+costruction.get(i).getCitiesId().toString(), space-6);//aggiungo gli id delle citta'
				
				card=addSpace(card+"|", space);
				cardShowed+=card;//aggiungo il tutto alla string finale
			}
		}
		cardShowed+="\n";

		cardShowed+=getBonusCostructor(region, space, 0)+"\n";//recupero i bonus delle carte costruzione

		cardShowed+=getBonusCostructor(region, space, 1)+"\n";//recupero i bonus delle carte costruzione

		for(int j=0; j<regSize; j++){//ciclo che scorre le regioni
			String mino="";
			List<BusinessPermitTile> costruction=region.get(j).getDeck().getShowedDeck();//recupero la lista delle carte permesso costruzione
			for(int i=0; i<costruction.size(); i++){//ciclo che scorre le carte costruzione della regione
				mino+=addSpace(addMinus(space-5), space);
			}
			cardShowed+=mino;//aggiungo il tutto alla string finale
		}
		cardShowed+="\n";

		return cardShowed;
	}
	
	/**
	 * 
	 * @param reg, the regions list
	 * @param space, the space
	 * @param n, the numer of bonus (0,1)
	 * @return a string with the bonus name
	 */
	public String getBonusCostructor(List<Region> reg, int space, int n){
		String bonusName="";
		for(int j=0; j<reg.size(); j++){//ciclo che scorre le regioni
			List<BusinessPermitTile> costruction=reg.get(j).getDeck().getShowedDeck();//recupero la lista delle carte permesso costruzione
			
			for(int i=0; i<costruction.size(); i++){//ciclo che scorre le carte costruzione della regione
				String bon="";//stringa che contiene i bonus
				List<Bonus> bo=costruction.get(i).getBonusTile();//lista con i bonus
				
				if(bo.size()>1||n<1){//il secondo bonus può non esserci
					bon+=addSpace("|"+bo.get(n).getName(), space-6);
				}else{
					bon+=addSpace("|",space-6);//nel caso non ci sia il secondo bonus
				}
				bon=addSpace(bon+"|", space);//aggiungo gli spazi
				bonusName+=bon;//aggiungo il tutto alla string finale
			}
		}
		return bonusName;
	}
	
	/**
	 * trasmor a list of city in a string of the city id
	 * @param c, the city
	 * @return a string with the id of the city
	 */
	public String getNeighbourID(City c){
		List<City> vicini=c.getNeighbors();//recupero la lista dei vicini della citta'
		String viciniId="Vicini: ";//stringa che contiene i vicini della citta'
		for(int i=0; i<vicini.size(); i++){//scorre il numero di vicini della citta'
			viciniId+=Character.toString(vicini.get(i).getId());//aggiunge alla stringa l'id della citta' vicina
			viciniId+=", ";
		}
		return viciniId.substring(0, viciniId.length()-2);//tolgo gli ultimi due caratteri (virgola e spazio)
	}

	/**
	 * return a string with the specified number of minus -
	 * @param number, the number of minus you want
	 * @return a string with minus
	 */
	public String addMinus(int number){
		String minus="";
		for(int i=0; i<number; i++){//ciclo che scorre la quantita' di meno da aggiungere
			minus+="-";//aggiungo un meno
		}
		return minus;
	}
}
