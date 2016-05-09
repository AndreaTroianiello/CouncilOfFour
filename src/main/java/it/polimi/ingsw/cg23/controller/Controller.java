package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Controller {
	List <Player> giocatori = new ArrayList<>();//lista giocatori
	NobilityTrack nT=new NobilityTrack(20);
	CliInterface cl=new CliInterface();


	/**
	 * add a player to the list
	 */
	public void createPlayer(){
		int assistant=playerNumber();//numero di giocatori gia' presenti nella lista
		String name=cl.writeReturnValue("come ti chiami?",null).toString();//recupero il nome del giocatore
		Player p=new Player(name, assistant+10, 0, nT);
		giocatori.add(p);//aggiunge un giocatore alla lista
	}
	
	public int playerNumber(){
		return giocatori.size();
	}
	
	public void printList(){
		for(int i=0;i<giocatori.size();i++){
			cl.print(null,giocatori.get(i).toString());
		}
	}
	
	//NON FUNZIONA
	public void createregion(){
		String[][] cityInfo=cl.leggiXml("ConfigurazionePartita.xml");
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		cl.printArray(cityInfo);
		//System.out.println(regionNumber);
		for(int i=0; i<regionNumber; i++){//ciclo per creare le regioni
			//new Region();
		}
	}
		//NON FUNZIONA
	public void createCities(){
		String[][] cityInfo=cl. leggiXml("ConfigurazionePartita.xml");
		for(int i=0; i<cityInfo.length; i++){
		//City c=new City(cityInfo[i][3].charAt(0), cityInfo[i][0], null, cityInfo[i][1], cityInfo[i][5]);
		}
	}
}
