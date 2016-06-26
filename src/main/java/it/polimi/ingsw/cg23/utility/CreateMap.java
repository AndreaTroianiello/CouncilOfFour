package it.polimi.ingsw.cg23.utility;

import java.util.List;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityBox;

/**
 * print the game map
 *
 */
public class CreateMap {
	private MapSetting ms;
	private final int space;//spazio da mettere tra una regione e l'altra

	/**
	 * constructor
	 */
	public CreateMap(){
		this.space=50;
		this.ms=new MapSetting();
	}

	/**
	 * Print the map
	 * @return void
	 * @param king the king
	 * @param reg a region list
	 * @param giocatori a list of the players
	 */
	public String createMap(List<Region> reg, List<Player>giocatori, King king){//NON TIENE CONTO DEI COLLEGAMENTI

		List<City> city=ms.getCityfromRegion(reg);
		String gamemap="";//la stringa che stampa la plancia di gioco

		gamemap+=ms.printName(reg, space)+"\n";//aggiungo i nomi delle regioni

		int regionNumber=reg.size();//recupera il numero di regioni
		for(int i=0; i<city.size()/regionNumber; i++){//ciclo che scorre le citta' per regione da stampare 5

			for(int k=0; k<regionNumber; k++){//aggiunge le 3 citta', una per ogni regione
				int kk=i+(city.size()/regionNumber)*k;//salvo il valore di k

				String newcity=city.get(kk).getName()+"("+city.get(kk).getType()+")"+city.get(kk).getEmporiums()+"";//recupero le informazioni dall'array

				String citybonus=ms.cityBonus(city.get(kk));//bonus della citta'

				newcity+="("+citybonus+")";//aggiungo alla nuova citta' i suoi bonus
				String kingCity=king.getCity().getName();//nome della citta' del re

				if(kingCity.equals(city.get(kk).getName())){//se la città e' quella che sta ciclando la segno
					newcity=newcity.substring(0,newcity.length()-2)+"KING";//la citta del re non ha bonus
					gamemap=gamemap.concat(ms.addSpace(newcity, space));
				}else
					gamemap=gamemap.concat(ms.addSpace(newcity, space));//aggiungo la citta' appena aggiunta a quelle presenti
			}
			gamemap=gamemap.concat("\n");//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)
		}

		gamemap=gamemap.concat("\n");
		gamemap=gamemap.concat(createPlayerInfo(giocatori));//aggiunge alla plancia di gioco i punteggi giocatore

		return gamemap;//stampo la plancia di gioco
	}



	/**
	 * create the map with draw card
	 * @param b the board
	 * @return the string of the map
	 */
	public String createMapDraw(Board b){
		List<Region> reg=b.getRegions();
		List<City> city=ms.getCityfromRegion(reg);
		String plancia="\nGame Board\n";//la stringa che stampa la plancia di gioco
		int regionNumber=reg.size();//numero di regioni
		int citypReg=city.size()/regionNumber;
		plancia+=ms.printName(reg, space);//aggiungo i nomi delle regioni
		plancia=plancia.concat("\n");

		for(int i=0; i<citypReg; i++){//ciclo che scorre le citta' per regione da stampare 5
			int minus=32;
			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge i -
				plancia=plancia.concat(ms.addSpace(ms.addMinus(minus), space));
			}
			plancia=plancia.concat("\n");

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge la il nome della citta'
				String name;
				if(b.getKing().getCity().getName().equals(city.get(i+citypReg*k).getName())){
					name=ms.addSpace("|"+city.get(i+citypReg*k).getName()+" - "+city.get(i+citypReg*k).getId(), minus-1)+"|";
					name=name.substring(0, name.length()-7);//faccio spazio per aggiungere king
					name+="KING  |";//aggiungo king alla citta' del re
				}
				else
					name=ms.addSpace("|"+city.get(i+citypReg*k).getName()+" - "+city.get(i+citypReg*k).getId(), minus-1)+"|";

				plancia=plancia.concat(ms.addSpace(name, space));//aggiungo il nome della citta'
			}
			plancia=plancia.concat("\n");//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge il tipo(colore) della citta'
				String tipo=ms.addSpace("|"+city.get(i+citypReg*k).getType(), minus-1);
				tipo=tipo.concat("|");
				plancia=plancia.concat(ms.addSpace(tipo, space));//aggiungo il tipo della citta'
			}
			plancia=plancia.concat("\n");//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge i bonus
				String bonus=ms.addSpace("|"+ms.cityBonus(city.get(i+citypReg*k)), minus-1)+"|";
				plancia=plancia.concat(ms.addSpace(bonus, space));//aggiungo i bonus della citta'
			}
			plancia=plancia.concat("\n");//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge i vicini della citta'
				String vicini=ms.addSpace("|"+ms.getNeighbourID(city.get(i+citypReg*k)), minus-1)+"|";
				plancia=plancia.concat(ms.addSpace(vicini, space));//aggiungo i vicini della citta'
			}
			plancia=plancia.concat("\n");//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge gli empori
				String empori=ms.addSpace("|Emporiums:"+city.get(i+citypReg*k).getEmporiums(), minus-1)+"|";
				plancia=plancia.concat(ms.addSpace(empori, space));//aggiungo gli empori della citta'
			}
			plancia=plancia.concat("\n");//aggiungo un a capo dopo aver messo 3 citta' su una riga (una per regione)

			for(int k=0; k<regionNumber; k++){//ciclo che aggiunge i -
				plancia=plancia.concat(ms.addSpace(ms.addMinus(minus), space));
			}
			plancia=plancia.concat("\n");
		}

		plancia+=ms.councillors(reg);
		plancia=plancia.concat("\n");
		plancia+=ms.createCostructionShowed(reg, space/2);//aggiungo le carte costruzione alla plancia
		plancia=plancia.concat(ms.bonusCouncilKingType(b));
		plancia=plancia.concat("\n");

		plancia+=createPlayerInfo(b.getPlayers());//aggiunge alla plancia di gioco i punteggi giocatore

		plancia+=printNobility(b);

		return plancia;//stampo la plancia di gioco

	}

	/**
	 * print the nobility track
	 * @param b the board
	 * @return a string with the nobility track
	 */
	public String printNobility(Board b){
		String nobility = "Nobility Track:\n";
		List<NobilityBox> nb = b.getNobilityTrack().getNobilityBoxes();
		List<Player> players=b.getPlayers();
		int nobilitySpace=20;

		for(int i=0; i<nb.size()-1; i++){//aggiunge i -
			nobility=nobility.concat(ms.addMinus(nobilitySpace));
		}

		nobility+="\n|";

		for(int i=0; i<nb.size(); i++){//aggiunge il numero di box e il primo bonus (se c'è)
			if(nb.get(i).getBonus().isEmpty())
				nobility=nobility.concat(ms.addSpace(i, nobilitySpace-2)+"|");
			else
				nobility=nobility.concat(ms.addSpace(i+" "+nb.get(i).getBonus().get(0).getName(), nobilitySpace-2)+"|");
		}

		nobility+="\n|";

		for(int i=0; i<nb.size(); i++){//aggiunge il secondo bonus (se c'è)
			if(nb.get(i).getBonus().size()<2)
				nobility=nobility.concat(ms.addSpace("", nobilitySpace-2)+"|");
			else
				nobility=nobility.concat(ms.addSpace(nb.get(i).getBonus().get(1).getName(), nobilitySpace-2)+"|");
		}

		nobility+="\n|";

		for(int i=0; i<nb.size(); i++){//aggiunge i giocatori (se ci sono)
			String giocatori="";

			for(int k=0; k<players.size(); k++){//scorre i giocatori
				if(players.get(k).getNobilityBoxPosition()==i)
					giocatori=giocatori.concat(players.get(k).getUser()+", ");

			}
			if(giocatori.length()==0)
				nobility=nobility.concat(ms.addSpace(giocatori, nobilitySpace-2)+"|");
			else
				nobility=nobility.concat(ms.addSpace(giocatori.substring(0, giocatori.length()-2), nobilitySpace-2)+"|");
		}

		nobility+="\n";

		for(int i=0; i<nb.size()-1; i++){//aggiunge i -
			nobility=nobility.concat(ms.addMinus(nobilitySpace));
		}

		return nobility;
	}

	/**
	 * create the players info
	 * @param giocatori a list with the players
	 * @return a string to print
	 */
	public String createPlayerInfo(List<Player>giocatori){
		int playerSpace=20;//spazi da mettere per rendere il testo ordinato
		String percorsi="";
		percorsi=percorsi.concat(ms.addSpace("Player", playerSpace));
		percorsi=percorsi.concat(ms.addSpace("Richness(coin)", playerSpace));
		percorsi=percorsi.concat(ms.addSpace("Victory", playerSpace));
		percorsi=percorsi.concat(ms.addSpace("Nobility", playerSpace));
		percorsi=percorsi.concat(ms.addSpace("Assistants", playerSpace));
		percorsi=percorsi.concat(ms.addSpace("Politics Cards", playerSpace));
		percorsi=percorsi.concat("\n");

		for(int i=0; i<giocatori.size(); i++){//stampa i punteggi dei giocatori
			percorsi=percorsi.concat(ms.addSpace(giocatori.get(i).getUser(),playerSpace));//aggiungo il nome dei giocatori
			percorsi=percorsi.concat(ms.addSpace(giocatori.get(i).getRichness().getCoins(),playerSpace));//aggiungo il numero di coins
			percorsi=percorsi.concat(ms.addSpace(giocatori.get(i).getVictoryTrack().getVictoryPoints(),playerSpace));//aggiungo i victory points
			percorsi=percorsi.concat(ms.addSpace(giocatori.get(i).getNobilityBoxPosition(),playerSpace));//aggiungo la posizione sul nobility box
			percorsi=percorsi.concat(ms.addSpace(giocatori.get(i).getAssistantsPool().getAssistants(), playerSpace));//aggiungo il numero di assistenti
			percorsi=percorsi.concat(ms.addSpace(giocatori.get(i).getHand().size(), playerSpace));//aggiungo il numero di carte politiche
			percorsi=percorsi.concat("\n");
		}

		return percorsi;
	}
}
