package it.polimi.ingsw.cg23.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Setting {
	CliInterface cl=new CliInterface();
	
	/**
	 * create the politic cards
	 * @param numberColor, the number of cards for each color
	 * @param jolly, the number of jolly cards
	 * @return a list with all the politic cards
	 */
	public List<PoliticCard> politicList(int numberColor, int jolly){
		List<PoliticCard> politics= new ArrayList<>();//lista della carte politiche
		for(int i=0;i<numberColor; i++){//aggiungo alla lista le carte nere
			politics.add(new PoliticCard(Color.BLACK,false));
		}
		for(int i=0;i<numberColor; i++){//aggiungo alla lista le carte rosa
			politics.add(new PoliticCard(Color.PINK,false));
		}
		for(int i=0;i<numberColor; i++){//aggiungo alla lista le carte arancioni
			politics.add(new PoliticCard(Color.ORANGE,false));
		}
		
		for(int i=0;i<numberColor; i++){//aggiungo alla lista le carte blu
			politics.add(new PoliticCard(Color.BLUE,false));
		}
		for(int i=0;i<numberColor; i++){//aggiungo alla lista le carte bianche
			politics.add(new PoliticCard(Color.WHITE,false));
		}
		for(int i=0;i<numberColor; i++){//aggiungo alla lista le carte rosse (non ci sono viola)
			politics.add(new PoliticCard(Color.RED,false));
		}
		for(int i=0;i<jolly; i++){//aggiungo alla lista le carte jolly
			politics.add(new PoliticCard(null,true));
		}
		return politics;
	}
	
	/**
	 * find the king's city
	 * @param citta, a list with all the cities
	 * @return the king
	 */
	public King king(List<City>citta){
		for(int i=0; i<citta.size(); i++){//scorre le citta' per cercare quella del re
			if("purple".equals(citta.get(i).getType())){//la citta' del re e' di colore purple
				return new King(citta.get(i));//ritorno il re con la citta' trovata
			}
		}
		return null;
	}
	
	/**
	 * create a list of the city from the regions list and add the neighbors
	 * @param regioni, a list with the regions
	 */
	public void cityList(List<Region>regioni){
		List<City>citta=new ArrayList<>(); //lista delle citta'
		for(int i=0; i<regioni.size(); i++){//ciclo che scorre le regioni
			citta.addAll(regioni.get(i).getCities());//recupero le citta' di di tutte le regioni e le aggiungo alla lista		
		}
		addNeighbors(citta);//aggiungo i vicini alle citta'
	}
	
	/**
	 * add at a city their neighbors
	 * @param citta a list with all the cities
	 */
	public void addNeighbors(List<City>citta){
		String[][]cityInfo=cl.leggiXml("ConfigurazionePartita.xml");//recupero le informazioni delle citta' dall'xml
		for(int j=0; j<citta.size(); j++){//ciclo che scorre le citta'
			for(int i=0; i<cityInfo[j][2].length(); i++){//ciclo che scorre i vicini delle citta
				String id=cityInfo[j][2].substring(i, i+1);//id della citta' vicina
				City vicina=getVicina(citta, id);//recupero la citta' vicina sapendo l'id
				citta.get(j).addNeighbor(vicina);//aggiungo alla citta' la sua vicina
			}
		}
	}
	
	/**
	 * find and return the neighbors city
	 * @param citta, a list with all the cities
	 * @param id, the id of a neighbors city
	 * @return the neighbors city
	 */
	public City getVicina(List<City>citta, String id){
		for(int i=0; i<citta.size(); i++){//ciclo che scorre le citta'
			if(citta.get(i).getId()==id.charAt(0)){//trovo la citta' con l'id desiderato
				return citta.get(i);//ritorno la citta' trovata
			}
		}
		return null;
	}
}
