package it.polimi.ingsw.cg23.controller;

import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.view.AutoCostruction;
import it.polimi.ingsw.cg23.view.CliInterface;

/**
 * class that start the game
 *
 */
public class Avvio {
	CliInterface cl=new CliInterface();
	Controller c=new Controller();
	Setting s=new Setting();
	
	List <Player> giocatori;//lista giocatori
	List <City> citta;//lista giocatori
	List <Region> regions;//lista giocatori
	List <PoliticCard> politcards;//lista giocatori
	List <Bonus> bonusList;//lista dei bonus
	List <BusinessPermitTile> costructionCard;//lista dei bonus
	Board board;

	public Avvio(){
		this.board=null;
	}
	
	/**
	 * to start the game
	 */
	public void startPartita(){//metodo per avviare la partita
		cl.print("", "Benvenuti a cof!");
		
		//----------giocatori----------
		int playerNumber=numeroGiocatori();//numero di giocatori della partita (richiesto per ora da cl)
		for(int i=0; i<playerNumber; i++){//ciclo per creare i giocatori
			c.createPlayer();//creo i giocatori
		}
		cl.print("", "\nCreo gli elementi di gioco:");
		cl.print("", "-Creo i giocatori");
		
		//----------bonus----------
		bonusList=c.bonusList();//recupero la lista con tutti i bonus
		cl.print("", "-Creo i bonus");
	
		//----------regioni e citta'----------
		setGiocatori(c.getGiocatori());//recupero la lista dei giocatori dal controller
		c.createRegions();//crea le regioni
		setRegioni(c.getRegioni());//recupero la lista delle regioni dal controller
		s.cityList(regions);//setta i vicini delle citta
		setCitta(c.getCitta());//recupero la lista delle citta'
		cl.print("", "-Creo le regioni e le citta'");
		
		//----------carte politiche----------
		politcards=s.politicList(13,12);//crea le carte politiche e le mette in una lista
		cl.print("", "-Creo le carte politiche");
		
		//----------king----------
		King king=s.king(citta);//creato il re
		cl.print("", "-Creo il re");
		
		//----------deck----------
		Deck dec=new Deck(politcards);//creato il deck
		cl.print("", "-Creo il deck");
		
		//----------board----------
		board=new Board(dec, regions,null, new NobilityTrack(20), king);//creata la board
		cl.print("", "-Creo la board");
		
		//----------carte permesso di costruzione----------
		c.createCardCostruction();//crea le carte costruzione
		costructionCard=c.getCostructionCard();//recupero la liste di carte costruzione
		cl.print("", "-Creo le carte permesso di costruzione");
		
		//----------consiglieri e balconi----------
		s.CreateCouncillor(4, board);
		for(int i=0; i<regions.size(); i++){
			s.setBalconi(board, regions.get(i));
		}
		s.setBalconi(board, king);
		cl.print("", "-Creo i consiglieri");
		
		//----------plancia----------
		cl.print("", "-Creo la plancia di gioco\n");
		cl.createMap(citta, giocatori,king);//stampa la plancia di gioco dalla lista
		PrintAll();
		AutoCostruction ac=new AutoCostruction();
		ac.createIdCity(citta, "costa");
		}
	
	/**
	 * print all the list
	 */
	public void PrintAll(){
		cl.print("", "STAMPO TUTTO");
		c.printList(bonusList);
		c.printList(regions);
		c.printList(citta);
		c.printList(giocatori);
		c.printList(politcards);
		c.printList(costructionCard);
		c.printList(s.getConsiglieri());
	}
	
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

	/**
	 * @return the regioni
	 */
	public List<Region> getRegioni() {
		return regions;
	}
	
	/**
	 * @param giocatori the giocatori to set
	 */
	public void setGiocatori(List<Player> giocatori) {
		this.giocatori = giocatori;
	}

	/**
	 * @param cittas the city to set
	 */
	public void setCitta(List<City> cittas) {
		this.citta = cittas;
	}
	
	/**
	 * @return the bo
	 */
	public Board getBo() {
		return board;
	}

	/**
	 * @param bo the board to set
	 */
	public void setBo(Board bo) {
		this.board = bo;
	}
	
	/**
	 * @param region the region to set
	 */
	public void setRegioni(List<Region> region) {
		this.regions = region;
	}
}
