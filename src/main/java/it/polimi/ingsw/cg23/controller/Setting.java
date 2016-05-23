package it.polimi.ingsw.cg23.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityBox;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Setting {


	List <Bonus> bonusList=new ArrayList<>();//lista di bonus

	List <Type> typeList=new ArrayList<>();//lista di type

	CliInterface cl=new CliInterface();

	String endpath="ConfigurazionePartita.xml";//nome del file che contine le info della citta'
	String[][] cityInfo=cl.leggiXml(endpath);//array con le informazioni delle citta'

	NobilityTrack nT=new NobilityTrack(cl.getNobilityTrackLenght("NobilityTrack.xml"));//recupero la lunghezza dall'xm


	/**
	 * 	print all the element of a list
	 */
	public void printList(List<?>lista){
		for(int i=0;i<lista.size();i++){//scorre la lista da stampare
			cl.print("",lista.get(i).toString());
		}
	}

	/*public List <Type> getTypeList(){
		return typeList;
	}*/



	/**
	 * refill the nobility track with the info find in the xml file
	 */
	public void nobilityTrackFill(){
		String[][]nobilityInfo=cl.getNobilityTrackBonus("NobilityTrack.xml");//informazioni recuperate dall'xml
		List<NobilityBox> boxList=nT.getNobilityBoxes();//lista delle caselle del nobility track

		for(int i=0; i<nobilityInfo.length; i++){//ciclo che scorre il numero di caselle presenti nel file xml
			String b;//contiene il nome del bonus
			StringTokenizer st = new StringTokenizer(nobilityInfo[i][1]);//string tokenizer del nome dei bonus
			while(st.hasMoreTokens()){
				String name=st.nextToken(",");//estrae la sottostring fino alla virgola
				b=name.substring(1, name.length());//isolo il nome del bonus
				int number=Integer.parseInt(name.substring(0, 1));//contiene il numero es. 1 carta politica, 2 coins

				for(int j=0; j<bonusList.size(); j++){//ciclo che scorre la lista dei bonus
					if(bonusList.get(j).toString().contains(b)){//controllo se il bonus contiene quello che sto cercando
						Bonus bo=bonusList.get(j).clone();//clono il bonus preso dalla lista dei bonus

						bo.setNumber(number);//setta il numero di bonus
						int boxNum=Integer.parseInt(nobilityInfo[i][0]);//numero della casella del nobility track
						boxList.get(boxNum-1).addBonus(bo);//aggiungo alla casella del nobility track il bonus corretto
					}
				}
			}
		}
	}

	/**
	 * @param nome, the string you want to search on
	 * @param c, what you want to find
	 * @return, a int with the coccurences
	 */
	/*public int occorrenze(String nome, char c){//calcola il numero di volte che compare il carattere nella stringa
		int n=0;
		for(int i=0; i<nome.length(); i++){//scorro la lunghezza del nome
			if(nome.charAt(i)==c)//cerco la lettera nella stringa
				n++;
		}
		return n;
	}*/


	/**
	 * create the type(color) of the city
	 * @return a list with all the types
	 */
	public List<Type> createType(){
		String[][] array=cl.getType(endpath);//recupero i type dall'xml
		for(int i=0; i<array.length; i++){
			int number=Integer.parseInt(array[i][1]);
			Type t=new Type(array[i][0], number, null);
			typeList.add(t);
		}
		return typeList;
	}





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
			for(int i=0; i<numberColor; i++){//aggiungo alla lista le carte colorate
				
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
	 * 
	 * @param dec, the deck
	 * @param giocatori, a list with the players
	 * @param cardsNumber, the number of cards to drow for each player
	 */
	public void pesca(Deck dec, List<Player> giocatori, int cardsNumber){
		for(int i=0; i<giocatori.size(); i++){//ciclo che scorre i giocatori
			for(int k=0; k<cardsNumber; k++){//ciclo che scorre il numero di carte
				giocatori.get(i).addPoliticCard(dec.draw());
			}
		}

	}

	/**
	 * find the king's city
	 * @param citta, a list with all the cities
	 * @return the king
	 */
	public King king(List<City>citta){
		//System.out.println(citta.size());
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
	/*public void cityList(List<Region> regioni){
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
	/*public void addNeighbors(List<City>citta){
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
	/*public City getVicina(List<City>citta, String id){
		for(int i=0; i<citta.size(); i++){//ciclo che scorre le citta'
			if(citta.get(i).getId()==id.charAt(0)){//trovo la citta' con l'id desiderato
				return citta.get(i);//ritorno la citta' trovata
			}
		}
		return null;
	}*/
}
