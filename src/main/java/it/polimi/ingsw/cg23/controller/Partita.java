package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.view.*;

public class Partita {
	CliInterface interfaccia=new CliInterface();
	String[][] cityInfo=interfaccia.startPartita();//recupero le informazioni delle citta'
	Region R1;
	Region R2;
	Region R3;
	
	public void regionObject(){
		int i=0;//zona costa
		int c=cityInfo.length/3;//zona collina
		int m=cityInfo.length/3*2;//zona montagna
		Bonus bi = null;//OGGETTO BONUS
		Bonus bc = null;//OGGETTO BONUS
		Bonus bm = null;//OGGETTO BONUS
		R1=new Region(cityInfo[i][5],bi);
		R2=new Region(cityInfo[c][5],bc);
		R3=new Region(cityInfo[m][5],bm);
	}

	public void cityObject(){
		for(int i=0; i<cityInfo.length; i++){
			Bonus b=null;//OGGETTO BONUS
			new City(cityInfo[i][3].charAt(0), cityInfo[i][0], b, cityInfo[i][1], new Region(cityInfo[i][5],b));
		}
	}
}
