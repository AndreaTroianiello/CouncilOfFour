package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Setting {
	CliInterface cl=new CliInterface();

	public void cityList(List<Region>regioni){

		List<City>citta=new ArrayList<City>();
		for(int i=0; i<regioni.size(); i++){//ciclo che scorre le regioni
			citta.addAll(regioni.get(i).getCities());//recupero la lista delle citta' di tutte le regioni
			
			//System.out.println(regioni.get(i).getCities().get(0));
			
		}
		addNeighbours(citta);
	}

	public void addNeighbours(List<City>citta){
		String[][]cityInfo=cl.leggiXml("ConfigurazionePartita.xml");
		//cl.print(citta.size(), "ciao");
		for(int j=0; j<citta.size(); j++){//ciclo che scorre le citta
			
			for(int i=0; i<cityInfo[j][2].length(); i++){//ciclo che scorre i vicini delle citta
				//citta.get(0).addNeighbor(citta.get(index));
				System.out.println("ok"+cityInfo[j][2]);
			}
		}
	}
}
