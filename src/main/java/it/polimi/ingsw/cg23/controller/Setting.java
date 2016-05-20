package it.polimi.ingsw.cg23.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.bonus.BonusAdditionalAction;
import it.polimi.ingsw.cg23.model.bonus.BonusAssistants;
import it.polimi.ingsw.cg23.model.bonus.BonusCityToken;
import it.polimi.ingsw.cg23.model.bonus.BonusCoin;
import it.polimi.ingsw.cg23.model.bonus.BonusGetPermitTile;
import it.polimi.ingsw.cg23.model.bonus.BonusNobility;
import it.polimi.ingsw.cg23.model.bonus.BonusPolitics;
import it.polimi.ingsw.cg23.model.bonus.BonusTileBonus;
import it.polimi.ingsw.cg23.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.model.components.BonusKing;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.components.RegionDeck;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Setting {
	List <Player> giocatori = new ArrayList<>();//lista giocatori
	List <Region> regioni = new ArrayList<>();//lista regioni
	List <City> citta = new ArrayList<>();//lista citta
	List <Bonus> bonusList=new ArrayList<>();//lista di bonus
	List <BusinessPermitTile> costructionCard=new ArrayList<>();//lista di carte costruzione
	List <Type> typeList=new ArrayList<>();//lista di type

	CliInterface cl=new CliInterface();
	NobilityTrack nT=new NobilityTrack(cl.getNobilityTrackLenght("NobilityTrack.xml"));//recupero la lunghezza dall'xml
	String endpath="ConfigurazionePartita.xml";//nome del file che contine le info della citta'
	String[][] cityInfo=cl.leggiXml(endpath);//array con le informazioni delle citta'

	/**
	 * create and add a player to the list
	 */
	public void createPlayer(){
		int assistant=playerNumber();//numero di giocatori gia' presenti nella lista
		String name=cl.writeReturnValue("Come ti chiami giocatore?",null).toString();//recupero il nome del giocatore
		Player p=new Player(name, assistant+10, 0, nT);//creo i giocatori
		giocatori.add(p);//aggiunge un giocatore alla lista
	}

	/**
	 * 
	 * @return the player list
	 */
	public List<Player> getGiocatori(){
		return giocatori;
	}

	/**
	 * return the number of player in the list
	 * @return the number of player
	 */
	public int playerNumber(){
		return giocatori.size();	
	}

	/**
	 * 	print all the element of a list
	 */
	public void printList(List<?>lista){
		for(int i=0;i<lista.size();i++){//scorre la lista da stampare
			cl.print("",lista.get(i).toString());
		}
	}

	/**
	 * create the regions object and add at the regions list
	 */
	public List<Region> createRegions(){
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		int c=cityInfo.length/regionNumber;//numero di citta' per regione
		String[][] regionBonus=cl.getBonusRegion("ConfigurazionePartita.xml");

		for(int i=0; i<regionNumber; i++){//ciclo che scorre le regioni
			RegionDeck rd=new RegionDeck(2);//creo il regiondeck
			int regBonus=Integer.parseInt(regionBonus[i][1]);//trasformo i bonus regione in interi
			Region r=new Region(cityInfo[i*c][5],regBonus,rd,bonusKing());//creo la regione
			regioni.add(r);//creata una nuova regione e aggiunta alla lista
		}
		return regioni;
	}

	/**
	 * create the cities object and add at the citta list
	 * @param j, the number of the region
	 */
	public List<City> createCities(int j, Region r){
		int regionNumber=cl.regionsNumber(cityInfo);//numero di regioni
		int ii=0;
		int i=0;
		if(j==0)
			ii=i;//se e' la prima regione le citta' partono da 0
		if(j==1)
			ii=cityInfo.length/regionNumber;//se e' la seconda regione le citta' partono da 5(si autoregolano)
		if(j==2)
			ii=cityInfo.length/regionNumber*2;//se e' la terza regione le citta' partono da 10(si autoregolano)

		for(i=0; i<cityInfo.length/regionNumber; i++, ii++){//ciclo che scorre le citta' di una regione
			for(Type t:typeList){
				if(cityInfo[ii][1].equals(t.getName())){
					City c=new City(cityInfo[ii][3].charAt(0), cityInfo[ii][0], t, r);//creo la citta'
					getCityBonus(ii,c);//aggiungo i bonus alle citta'
					citta.add(c);//aggiungo la citta' alla lista delle citta'
				}
			}
		}
		return citta;
	}

	/**
	 * set the neighbors of a city
	 * @param c a city
	 */
	public void addNeighbors(){
		for(int h=0; h<citta.size(); h++){//scorre le citta' a cui aggiungere i vicini
			for(int i=0; i<cityInfo.length; i++){//scorre le citta' prese dall'xml
				if(cityInfo[i][0].equals(citta.get(h).getName())){//cerco la citta' attuale da quelle dell'xml
					nearVicini(i,h);
				}
			}
		}
	}

	public void nearVicini(int i, int h){//rompe la funzione addNeighbors che aveva troppi innesti
		for(int k=0; k<cityInfo[i][2].length(); k++){//scorro il numero di link delle citta'
			char link=cityInfo[i][2].charAt(k);
			for(int j=0; j<citta.size(); j++){//scorro le citta'
				if(link==citta.get(j).getId()){
					citta.get(h).addNeighbor(citta.get(j));//aggiungo alla citta' il vicino trovato
				}
			}
		}
	}


	/**
	 * aggiunta di bonus alla citta'
	 * @param i, to define the actual city (where find bonus in the array)
	 * @param c, the actual city
	 */
	public void getCityBonus(int i, City c){
		if("purple".equals(c.getType()))//la citta' del re non ha bonus
			return;
		String b;//contiene il nome del bonus
		StringTokenizer st = new StringTokenizer(cityInfo[i][4]);//string tokenizer del nome dei bonus
		while(st.hasMoreTokens()){
			String name=st.nextToken(",");//estrae la sottostring fino alla virgola
			b=name.substring(1, name.length());//isolo il nome del bonus
			int number=Integer.parseInt(name.substring(0, 1));//contiene il numero es. 1 carta politica, 2 coins

			for(int j=0; j<bonusList.size(); j++){//ciclo che scorre la lista dei bonus
				if(bonusList.get(j).toString().contains(b)){//controllo se il bonus contiene quello che sto cercando
					Bonus bo=bonusList.get(j).clone();//clono il bonus preso dalla lista dei bonus
					c.addBonus(bo);//aggiungo il bonus alla citta'
					bo.setNumber(number);//setta il numero di bonus
				}
			}
		}
	}

	/**
	 * @param nome, the string you want to search on
	 * @param c, what you want to find
	 * @return, a int with the coccurences
	 */
	public int occorrenze(String nome, char c){//calcola il numero di volte che compare il carattere nella stringa
		int n=0;
		for(int i=0; i<nome.length(); i++){//scorro la lunghezza del nome
			if(nome.charAt(i)==c)//cerco la lettera nella stringa
				n++;
		}
		return n;
	}

	/**
	 * @return a bonus list with all the type of bonus
	 */
	public List<Bonus> bonusList(Board board){//creo e aggiungo i bonus alla lista bonus
		bonusList.add(new BonusAdditionalAction());
		bonusList.add(new BonusAssistants());
		bonusList.add(new BonusCityToken(0, null, board));
		bonusList.add(new BonusCoin(0));
		bonusList.add(new BonusGetPermitTile(0,0,board));
		bonusList.add(new BonusNobility(0,board));
		bonusList.add(new BonusPolitics(0,board));
		bonusList.add(new BonusTileBonus(0));
		bonusList.add(new BonusVictoryPoints(0));
		return bonusList;
	}

	/**
	 * 
	 * @return a list with the king bonus
	 */
	public BonusKing bonusKing(){
		List<Integer> kingList=new ArrayList<>();//creo la lista con i bonus del re
		kingList.add(25);
		kingList.add(18);
		kingList.add(12);
		kingList.add(7);
		kingList.add(3);
		kingList.add(0);//una lista di interi con i passi di avanzamento del percorso vittoria
		return new BonusKing(kingList);
	}

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
	 * create and add at the list the costruction cards
	 */
	public List<BusinessPermitTile> createCardCostruction(){
		String[][] array=cl.getCostruction("CostructionCard.xml");//informazioni sulle carte costruzione

		for(int i=0; i<array.length; i++){//ciclo che scorre tutte le carte costruzione
			List<Character> citiesId=new ArrayList<>();//lista di id delle citta'

			for(int j=0; j<array[i][1].length(); j++){//ciclo che scorre il numero di citta' della carta costruzione
				citiesId.add(array[i][1].charAt(j));//aggiungo l'id della citta' alla lista
			}
			BusinessPermitTile bpt=new BusinessPermitTile(citiesId, array[i][0]);//creo una nuova carta costruzione
			getCostructorBonus(bpt, array[i][2]);//aggiungo i bonus alla carta costruzione
			costructionCard.add(bpt);//aggiungo la nuova carta costruzione alla lista			
		}
		return costructionCard;
	}

	/**
	 * fill the regiondeck with the costruction card
	 * @param region, the regions list
	 */
	public void createRegionDeck(List<Region> region){
		List<BusinessPermitTile> costructionRegionlist=new ArrayList<>();
		for(int k=0; k<region.size(); k++){//scorre le regioni
			for(int i=0; i<costructionCard.size(); i++){//scorre le carte costruzione
				if(region.get(k).getName().equals(costructionCard.get(i).getZone()))
					costructionRegionlist.add(costructionCard.get(i));
			}
			region.get(k).getDeck().setBusinessPermitTiles(costructionRegionlist);
		}
	}

	/**
	 * aggiunta di bonus alla carta costruzione
	 * @param i, to define the actual city (where find bonus in the array
	 * @param c, the actual city
	 */
	public void getCostructorBonus(BusinessPermitTile bpt, String bonusTotali){
		String b;//contiene il nome del bonus
		StringTokenizer st = new StringTokenizer(bonusTotali);//string tokenizer del nome dei bonus

		while(st.hasMoreTokens()){//ciclo finche' ci sono token
			String name=st.nextToken(",");//estrae la sottostring fino alla virgola
			b=name.substring(1, name.length());//isolo il nome del bonus
			int number=Integer.parseInt(name.substring(0, 1));//contiene il numero es. 1 carta politica, 2 coins

			for(int j=0; j<bonusList.size(); j++){//scorro la lista dei bonus
				if(bonusList.get(j).toString().contains(b)){//cerco il bonus nella lista dei bonus
					Bonus bo=bonusList.get(j).clone();
					bpt.addBonus(bo);//aggiungo alla carta costruzione i suoi bonus
					bo.setNumber(number);//setto il numero di bonus
				}
			}
		}
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
	public List<Councillor>  createCouncillor(int numberCouncillor, Board b){
		Color[] arrayColori=color();//recupero un array di Color con i possibili colori
		List<Councillor> consiglieri=new ArrayList<>();
		for(int i=0; i<arrayColori.length; i++){//ciclo che scorre i colori
			for(int k=0; k<numberCouncillor; k++){//ciclo che scorre il numero di consiglieri per colore
				Councillor c= new Councillor(arrayColori[i]);//creo un nuovo consigliere
				consiglieri.add(c);//aggiungo il consigliere alla lista
				b.setCouncillor(c);//aggiungo il consigliere alla board
			}
		}
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
	public void cityList(List<Region> regioni){
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
		String[][]cityInfo=cl.leggiXml(endpath);//recupero le informazioni delle citta' dall'xml
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
