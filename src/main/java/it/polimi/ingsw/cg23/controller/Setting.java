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
		}
		addNeighbours(citta);//aggiungo i vicini alle citta'
	}

	public void addNeighbours(List<City>citta){
		String[][]cityInfo=cl.leggiXml("ConfigurazionePartita.xml");
		for(int j=0; j<citta.size(); j++){//ciclo che scorre le citta

			for(int i=0; i<cityInfo[j][2].length(); i++){//ciclo che scorre i vicini delle citta
				//System.out.println(cityInfo[j][2].length());
				String id=cityInfo[j][2].substring(i, i+1);//	CONTROLLARE
				//System.out.println(id);
				City vicina=getVicina(citta, id);

				citta.get(j).addNeighbor(vicina);
				//System.out.println("ok"+cityInfo[j][2]);
			}
		}
	}
	public City getVicina(List<City>citta, String id){
		for(int i=0; i<citta.size(); i++){
			if(citta.get(i).getId()==id.charAt(0)){
				return citta.get(i);
			}
			
		}
		return null;
	}
}
