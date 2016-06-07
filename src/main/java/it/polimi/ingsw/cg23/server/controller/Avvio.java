package it.polimi.ingsw.cg23.server.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.controller.creation.*;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.view.Print;
import it.polimi.ingsw.cg23.server.view.XmlInterface;

/**
 * class that start the game
 *
 */
public class Avvio {
	Print cl;
	Setting s;
	CreateBonus cb;
	CreateCostruction cc;
	CreateCouncillor cco;
	CreateRegionCity crc;
	Deck dec;
	King king;

	private List <Player> giocatori;//lista giocatori
	private List <City> citta;//lista giocatori
	private List <Region> regions;//lista giocatori
	private List <PoliticCard> politcards;//lista giocatori
	private List <Bonus> bonusList;//lista dei bonus
	private List <BusinessPermitTile> costructionCard;//lista dei bonus
	private List <Type> tipi;//lista dei bonus
	private List <Councillor> consiglieri;

	private Board board;
	private BonusKing bk;
	private XmlInterface leggiXml;
	private NobilityTrack nT;

	public Avvio(){
		cc=new CreateCostruction();
		cco=new CreateCouncillor();
		crc=new CreateRegionCity("ConfigurazionePartita.xml");
		cl=new Print();
		s=new Setting();
		cb=new CreateBonus("ConfigurazionePartita.xml");

		this.leggiXml= new XmlInterface();
		this.board=null;
		this.bk=cb.bonusKing();
		this.citta=new ArrayList<>();
		this.giocatori=new ArrayList<>();
		this.consiglieri=new ArrayList<>();
		this.nT= new NobilityTrack(leggiXml.getNobilityTrackLenght("NobilityTrack.xml"));//recupero la lunghezza dall'xml

	}

	/**
	 * to start the game
	 */
	public void startPartita(){//metodo per avviare la partita
		cl.print("", "Benvenuti a cof!");

		//----------giocatori----------
		/*int playerNumber=numeroGiocatori();//numero di giocatori della partita (richiesto per ora da cl)
		for(int i=0; i<playerNumber; i++){//ciclo per creare i giocatori
			cp.createPlayer();//creo i giocatori
		}*/

		cl.print("", "\nCreo gli elementi di gioco:");
		cl.print("", "-Creo i giocatori");

		//----------creo i type----------
		tipi=s.createType(bk);//creo i type (colori) delle citta'
		cl.print("", "-Creo i type");

		//----------board creazione----------
		board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);//creata la board
		cl.print("", "-Creo la board");

		//----------bonus----------
		bonusList=cb.bonusList(board);//recupero la lista con tutti i bonus
		cl.print("", "-Creo i bonus");

		//----------regioni----------
		//giocatori=cp.getGiocatori();//recupero la lista dei giocatori dal controller
		regions=crc.createRegions(bk);//crea le regioni e ne ritorna la lista
		cl.print("", "-Creo le regioni");

		//----------citta'----------
		for(int i=0; i<regions.size(); i++){//ciclo che scorre le regioni
			citta.addAll(crc.createCities(i, regions.get(i), bk));//recupero le citta' della regione
		}

		for(int j=0; j<citta.size(); j++){//ciclo che scorre le citta'
			cb.getCityBonus(j, citta.get(j));//aggiungo alla citta' i bonus
		}
		crc.addNeighbors(citta);//aggiungo i vicini alle citta'
		cl.print("", "-Creo le citta'");

		//----------king----------
		king=s.king(citta);//creato il re
		cl.print("", "-Creo il re");

		//----------carte politiche----------
		politcards=s.politicList(13,12);//crea le carte politiche e le mette in una lista
		cl.print("", "-Creo le carte politiche"); 

		//----------deck----------
		dec=new Deck(politcards);//creato il deck
		//s.pesca(dec, giocatori, 4);//i giocatori pescano 4 carte
		cl.print("", "-Creo il deck");

		//----------carte permesso di costruzione----------
		costructionCard=cc.createCardCostruction(board);//crea le carte costruzione e le metto nella lista
		cc.createRegionDeck(regions);//riempie i regiondeck delle regioni
		cl.print("", "-Creo le carte permesso di costruzione");

		//----------consiglieri e balconi----------
		consiglieri=cco.createCouncillor(4);//crea i consiglieri
		
		for(int i=0; i<regions.size(); i++){//scorro il numero di regioni
			cco.setBalconi(regions.get(i), consiglieri);//crea i balconi delle regioni
		}
		cco.setBalconi(king, consiglieri);//crea il balcone del re
		cl.print("", "-Creo i consiglieri");

		//----------nobility track----------
		s.nobilityTrackFill(nT);//riempio il nobility track
		cl.print("", "-Setto il Nobility track");

		//----------board settaggio----------
		setBoard(board);//setta la baord
		cl.print("", "-Setto la board");

		//----------plancia----------
		cl.print("", "-Creo la plancia di gioco");
		cl.createMap(regions, giocatori, king);//stampa la plancia di gioco dalla lista
		//printAll();//stampa tutte le liste
	}

	/**
	 * print all the list
	 */
	public void printAll(){
		cl.print("", "STAMPO TUTTO");
		cl.print(bonusList.size(),"");
		cl.printList(bonusList);
		cl.print(regions.size(),"");
		cl.printList(regions);
		cl.print(citta.size(),"");
		cl.printList(citta);
		cl.print(giocatori.size(),"");
		cl.printList(giocatori);
		cl.print(politcards.size(),"");
		cl.printList(politcards);
		cl.print(costructionCard.size(),"");
		cl.printList(costructionCard);
		cl.print(tipi.size(),"");
		cl.printList(tipi);
		cl.print(bk.toString(), "");
		cl.print(board, "");
	}

	/**
	 * set the board
	 * @param b, the board
	 */
	public void setBoard(Board board){

		board.setDeck(dec);//aggiungo il dec alla board
		board.setKing(king);//aggiungo il re alla board
		board.setRegions(regions);//aggiungo le regioni alla board
		board.setTypes(tipi);//aggiungo i tipi alla board
		/*for(int i=0; i<giocatori.size(); i++){
			board.addPlayer(giocatori.get(i));//aggiungo i gicatori alla board
		}*/
		for(int i=0; i<consiglieri.size(); i++){
			board.setCouncillor(consiglieri.get(i));//aggiungo i consiglieri avanzati alla board
		}
		board.setNobilityTrack(nT);//aggiungo il nobility track alla board
	}

	/**
	 * 
	 * @return the board
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * 
	 * @return the number of players
	 */
	public int numeroGiocatori(){
		int playerNumber=0;
		while(playerNumber==0){//si continua a ciclare finche' non e' stato inserito un numero valido
			try{//provo a recuperare il numero di giocatori
				playerNumber=Integer.parseInt(cl.writeReturnValue("Quanti giocatori siete?", null).toString());

			}catch(NumberFormatException e){
				cl.print(null, "devi inserire un numero");
				playerNumber=0;
			}
		}
		return playerNumber;
	}

	/**
	 * @return the giocatori
	 */
	public List<Player> getGiocatori() {
		return giocatori;
	}
}
