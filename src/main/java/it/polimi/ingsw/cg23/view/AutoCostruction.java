package it.polimi.ingsw.cg23.view;

import java.util.List;

import it.polimi.ingsw.cg23.model.City;

public class AutoCostruction {//CLASSE PeR GENERARE TESSERE COSTRUZIONE GENERICHE
	
	public void createIdCity(List<City> citta, String region){
		int regNum=regionsNumber(citta);//numero di regioni
		int cityPerReg=numberCityReg(citta, region);
		System.out.println(cityPerReg);
		for(int i=0; i<citta.size(); i++){
			//if(citta.get(i).getRegion().toString())
				//System.out.println(citta.get(i).getId());
		}
	}
	
	public int numberCityReg(List<City> citta, String region){
		int c=0;
		for(int i=0; i<citta.size()-1; i++){
			if(citta.get(i).getRegion().toString().contains(region))
				c++;
		}
		return c+1;
	}
	
	public int regionsNumber(List<City> citta){
		int c=0;
		for(int i=0; i<citta.size()-1; i++){
			if(citta.get(i).getRegion()!=citta.get(i+1).getRegion())
				c++;
		}
		return c+1;
	}
}
