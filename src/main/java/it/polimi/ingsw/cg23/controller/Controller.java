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
	String[][] cityInfo;//array con le informazioni delle citta'

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
	public void printList(List<?> lista){
		for(int i=0;i<lista.size();i++){
			cl.print(null,lista.get(i).toString());
		}
	}

	/**
	 * create the regions object and add at the regions list
	 */
	public void createRegions(){//ARRAY VUOTO XKE'?
		//ERRORE
		//cityInfo=cl.leggiXml("ConfigurazionePartita.xml");
		//System.out.println("cok");
		//cl.printArray(cityInfo);
		
		//ERRORE
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		int c=cityInfo.length/regionNumber;//numero di citta' per regione
		//System.out.println(regionNumber);
		for(int i=0; i<regionNumber; i++){//ciclo che scorre le regioni
			regioni.add(new Region(cityInfo[i*c][5],null));//creata una nuova regione e aggiunta alla lista
			createCities(i);
		}
	}

	/**
	 * create the cities object and add at the citta list
	 * @param j, the number of the region
	 */
	public void createCities(int j){
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		for(int i=0; i<cityInfo.length/regionNumber; i++){//ciclo che scorre le citta' di una regione
			//crea una nuova citta' e la aggiunge all'array
			citta.add(new City(cityInfo[i][3].charAt(0), cityInfo[i][0], null, cityInfo[i][1], regioni.get(j)));
		}
	}
}