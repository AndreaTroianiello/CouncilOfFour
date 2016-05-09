package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Controller {
	List <Player> giocatori = new ArrayList<>();//lista giocatori
	List <Region> regioni = new ArrayList<>();//lista regioni
	List <City> citta = new ArrayList<>();//lista citta
	NobilityTrack nT=new NobilityTrack(20);
	CliInterface cl=new CliInterface();
	String[][] cityInfo;

	/**
	 * add a player to the list
	 */
	public void createPlayer(){
		int assistant=playerNumber();//numero di giocatori gia' presenti nella lista
		String name=cl.writeReturnValue("come ti chiami?",null).toString();//recupero il nome del giocatore
		Player p=new Player(name, assistant+10, 0, nT);
		giocatori.add(p);//aggiunge un giocatore alla lista
		cityInfo=cl.leggiXml("ConfigurazionePartita.xml");
	}

	public int playerNumber(){
		return giocatori.size();	
	}

	public void printList(){
		for(int i=0;i<giocatori.size();i++){
			cl.print(null,giocatori.get(i).toString());
		}
	}

	public void createRegions(){
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		int c=cityInfo.length/regionNumber;
		for(int i=0; i<regionNumber; i++){//ciclo per creare le regioni
			regioni.add(new Region(cityInfo[i*c][5],null));
			createCities(i);//crea le citta'
		}
	}

	/**
	 * 
	 * @param j
	 */
	public void createCities(int j){
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		for(int i=0; i<cityInfo.length/regionNumber; i++){
			citta.add(new City(cityInfo[i][3].charAt(0), cityInfo[i][0], null, cityInfo[i][1], regioni.get(j)));
		}
	}
}