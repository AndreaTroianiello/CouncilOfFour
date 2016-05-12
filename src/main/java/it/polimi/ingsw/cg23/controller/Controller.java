package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.bonus.*;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Controller {
	List <Player> giocatori = new ArrayList<>();//lista giocatori
	List <Region> regioni = new ArrayList<>();//lista regioni
	List <City> citta = new ArrayList<>();//lista citta
	NobilityTrack nT=new NobilityTrack(20);//20 numero di caselle del percorso nobilta'
	CliInterface cl=new CliInterface();
	String[][] cityInfo=cl.leggiXml("ConfigurazionePartita.xml");;//array con le informazioni delle citta'

	/**
	 * add a player to the list
	 */
	public void createPlayer(){
		int assistant=playerNumber();//numero di giocatori gia' presenti nella lista
		String name=cl.writeReturnValue("Come ti chiami giocatore?",null).toString();//recupero il nome del giocatore
		Player p=new Player(name, assistant+10, 0, nT);
		giocatori.add(p);//aggiunge un giocatore alla lista
	}

	public List<Player> getGiocatori(){
		return giocatori;
	}

	public List<Region> getRegioni(){
		return regioni;
	}

	public List<City> getCitta(){
		return citta;
	}
	/**
	 * return the number of player in the list
	 * @return the number of player
	 */
	public int playerNumber(){
		return giocatori.size();	
	}

	/**
	 * 	print the element of a list
	 */
	public void printList(){
		for(int i=0;i<giocatori.size();i++){
			cl.print("",giocatori.get(i).toString());
		}
		for(int i=0;i<regioni.size();i++){
			cl.print("",regioni.get(i).toString());
		}
		for(int i=0;i<citta.size();i++){
			cl.print("",citta.get(i).toString());
		}
	}

	/**
	 * create the regions object and add at the regions list
	 */
	public void createRegions(){
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		int c=cityInfo.length/regionNumber;//numero di citta' per regione
		for(int i=0; i<regionNumber; i++){//ciclo che scorre le regioni
			regioni.add(new Region(cityInfo[i*c][5],null,null));//creata una nuova regione e aggiunta alla lista
			createCities(i);
		}
	}

	/**
	 * create the cities object and add at the citta list
	 * @param j, the number of the region
	 */
	public void createCities(int j){
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		int ii=0;
		int i=0;
		if(j==0)
			ii=i;//se e' la prima regione le citta' partono da 0
		if(j==1)
			ii=cityInfo.length/regionNumber;//se e' la seconda regione le citta' partono da 5(si autoregolano)
		if(j==2)
			ii=cityInfo.length/regionNumber*2;//se e' la terza regione le citta' partono da 10(si autoregolano)

		for(i=0; i<cityInfo.length/regionNumber; i++, ii++){//ciclo che scorre le citta' di una regione
			City c=new City(cityInfo[ii][3].charAt(0), cityInfo[ii][0], null, cityInfo[ii][1], regioni.get(j));
			citta.add(c);//aggiungo la citta' alla lista
			regioni.get(j).addCity(c);//aggiungo alla regione le sue citta'
		}
	}
	
	/**
	 * PARTIAL
	 * @return a bonus list with all the type of bonus
	 */
	public List<Bonus> bonusList(){//PARZIALE----------
		List<Bonus> bonusList=new ArrayList<>();
			bonusList.add(new BonusAdditionalAction());
			bonusList.add(new BonusAssistants(0));
			//bonusList.add(new BonusCityToken(0,null));//altrimenti crasha
			bonusList.add(new BonusCoin(0));
			bonusList.add(new BonusGetPermitTile(0,0,null));
			bonusList.add(new BonusNobility(0,null));
			bonusList.add(new BonusPolitics(0,null));
			bonusList.add(new BonusTileBonus());
			bonusList.add(new BonusVictoryPoints(0));
			return bonusList;
	}
	
}