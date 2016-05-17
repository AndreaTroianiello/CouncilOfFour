package it.polimi.ingsw.cg23.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Setting {
	CliInterface cl=new CliInterface();
	List<Councillor> consiglieri=new ArrayList<>();

	/**
	 * create the politic cards
	 * @param numberColor, the number of cards for each color
	 * @param jolly, the number of jolly cards
	 * @return a list with all the politic cards
	 */
	public List<PoliticCard> politicList(int numberColor, int jolly){
		List<PoliticCard> politics= new ArrayList<>();//lista della carte politiche
		Color[] arrayColori=color();//recupero un array di Color con i possibili colori
		for(int k=0; k<arrayColori.length; k++){//ciclo che scorre i colori
			for(int i=0;i<numberColor; i++){//aggiungo alla lista le carte nere
				politics.add(new PoliticCard(arrayColori[k],false));//creo una nuova carta polita e la aggiungo alla lista
			}
		}
		for(int i=0;i<jolly; i++){//aggiungo alla lista le carte jolly
			politics.add(new PoliticCard(null,true));//creo una nuova carta politica jolly e la laggiungo alla lista
		}
		return politics;
	}

	/**
	 * the Color array contains alla the possibitly colors
	 * @return a Color array
	 */
	public Color[] color(){
		Color[] arrayColori=new Color[6];//array di Color con i possibili colori
		arrayColori[0]=Color.BLACK;
		arrayColori[1]=Color.RED;
		arrayColori[2]=Color.WHITE;
		arrayColori[3]=Color.BLUE;
		arrayColori[4]=Color.ORANGE;
		arrayColori[5]=Color.PINK;
		return arrayColori;
	}

	/**
	 * the function create the counsillor
	 * @param numberCouncillor, number of councillors per color to create
	 * @param b, the board
	 */
	public void createCouncillor(int numberCouncillor, Board b){
		Color[] arrayColori=color();//recupero un array di Color con i possibili colori
		for(int i=0; i<arrayColori.length; i++){//ciclo che scorre i colori
			for(int k=0; k<numberCouncillor; k++){//ciclo che scorre il numero di consiglieri per colore
				Councillor c= new Councillor(arrayColori[i]);//creo un nuovo consigliere
				consiglieri.add(c);//aggiungo il consigliere alla lista
				b.setCouncillor(c);//aggiungo il consigliere alla board
			}
		}
	}

	/**
	 * 
	 * @return a list with the Councillors
	 */
	public List<Councillor> getConsiglieri(){
		return consiglieri;
	}

	/**
	 * create the balconi region
	 * @param b, the board
	 * @param reg, the region
	 */
	public void setBalconi(Board b, Region reg){
		List<Councillor> consiglieri=b.getCouncillorPool();//lista di tutti i consiglieri
		Random rnd = new Random();
		List<Councillor> nuoviConsiglieri = new ArrayList<>();//consiglieri della regione
		for(int i=0; i<4; i++){//ciclo che scorre i consiglieri (4 per regione)
			int randomNumber=rnd.nextInt(consiglieri.size());//numero random per recuperrare a caso i consiglieri
			nuoviConsiglieri.add(consiglieri.get(randomNumber));//aggiungo i consiglieri alla nuova lista
			consiglieri.remove(randomNumber);//rimuovo i consiglieri dalla vecchia lista
		}
		reg.getCouncil().getCouncillors().addAll(nuoviConsiglieri);//aggiungo la nuova lista alla regione
	}

	/**
	 * create the balconi king
	 * @param b, the board
	 * @param k, the king
	 */
	public void setBalconi(Board b, King k){
		List<Councillor> consiglieri=b.getCouncillorPool();//lista di tutti consiglieri
		Random rnd = new Random();
		List<Councillor> nuoviConsiglieri = new ArrayList<>();//lista dei consiglieri del re
		for(int i=0; i<4; i++){//ciclo che scorre i consiglieri (4 per il balcone del re)
			int randomNumber=rnd.nextInt(consiglieri.size());//numero casuale per trovare i consiglieri
			nuoviConsiglieri.add(consiglieri.get(randomNumber));//aggiungo il consigliere random alla lista dei consiglieri del re
			consiglieri.remove(randomNumber);//cancello il consigliere aggiunto dalla lista dei consiglieri disponibili
		}
		k.getCouncil().getCouncillors().addAll(nuoviConsiglieri);//aggiungo la lista dei 4 consiglieri al re
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
