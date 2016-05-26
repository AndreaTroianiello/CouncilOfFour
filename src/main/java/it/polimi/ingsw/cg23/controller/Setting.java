package it.polimi.ingsw.cg23.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.controller.creation.CreateBonus;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.BonusKing;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityBox;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Setting {

	CliInterface cl=new CliInterface();
	CreateBonus cb=new CreateBonus("ConfigurazionePartita.xml");
	
	private List <Bonus> bonusList;//lista di bonus
	private String endpath;//nome del file che contine le info della citta'
	private NobilityTrack nT;//recupero la lunghezza dall'xm
	private List <Type> typeList;//lista di type
	
	public Setting(){
		this.bonusList=cb.bonusList(null);
		this.nT=new NobilityTrack(cl.getNobilityTrackLenght("NobilityTrack.xml"));//configurazione bovility track
		this.endpath="ConfigurazionePartita.xml";
		this.typeList=new ArrayList<>();
	}

	/**
	 * 	print all the element of a list
	 */
	public void printList(List<?>lista){
		for(int i=0;i<lista.size();i++){//scorre la lista da stampare
			cl.print("",lista.get(i).toString());//stampa la lista
		}
	}

	/**
	 * refill the nobility track with the info find in the xml file
	 */
	public void nobilityTrackFill(){
		String[][]nobilityInfo=cl.getNobilityTrackBonus("NobilityTrack.xml");//informazioni recuperate dall'xml
		
		for(int i=0; i<nobilityInfo.length; i++){//ciclo che scorre il numero di caselle presenti nel file xml
			String b;//contiene il nome del bonus
			StringTokenizer st = new StringTokenizer(nobilityInfo[i][1]);//string tokenizer del nome dei bonus
			while(st.hasMoreTokens()){//ciclo finche' ci sono token
				String name=st.nextToken(",");//estrae la sottostring fino alla virgola
				b=name.substring(1, name.length());//isolo il nome del bonus
				int number=Integer.parseInt(name.substring(0, 1));//contiene il numero es. 1 carta politica, 2 coins

				nobilityBonus(b, number, nobilityInfo[i][0]);//riempie il nobility track con i bonus
			}
		}
	}
	
	/**
	 * broken the nobilityTrackFill because was too eavy for sonar 
	 * @param b, the bonus name
	 * @param number, the bonus number
	 * @param nobinfo, nobility track number
	 */
	public void nobilityBonus(String b, int number, String nobinfo){
		List<NobilityBox> boxList=nT.getNobilityBoxes();//lista delle caselle del nobility track
		
		for(int j=0; j<bonusList.size(); j++){//ciclo che scorre la lista dei bonus
			if(bonusList.get(j).toString().contains(b)){//controllo se il bonus contiene quello che sto cercando
				Bonus bo=bonusList.get(j).clone();//clono il bonus preso dalla lista dei bonus
				
				bo.setNumber(number);//setta il numero di bonus
				int boxNum=Integer.parseInt(nobinfo);//numero della casella del nobility track
				boxList.get(boxNum-1).addBonus(bo);//aggiungo alla casella del nobility track il bonus corretto
			}
		}
	}

	/**
	 * create the type(color) of the city
	 * @return a list with all the types
	 */
	public List<Type> createType(BonusKing bk){
		String[][] array=cl.getType(endpath);//recupero i type dall'xml
		for(int i=0; i<array.length; i++){//scorre i type che ci sono nell'xml
			if("purple".equals(array[i][0])){
				Type t=new Type(array[i][0], Integer.parseInt(array[i][1]), null);//crea un nuovo tipo
				typeList.add(t);//aggiungo il tipo alla lista dei tipi
			}
			else{
			int number=Integer.parseInt(array[i][1]);//numero di victory points che da il tipo
			Type t=new Type(array[i][0], number, bk);//crea un nuovo tipo
			
			typeList.add(t);//aggiungo il tipo alla lista dei tipi
			}
		}
		return typeList;//ritorna la lista dei tipi
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
		return politics;//ritorna la lista dei tipi
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
		return arrayColori;//ritorna un array con i possibili colori
	}


	/**
	 * @param dec, the deck
	 * @param giocatori, a list with the players
	 * @param cardsNumber, the number of cards to drow for each player
	 */
	public void pesca(Deck dec, List<Player> giocatori, int cardsNumber){
		for(int i=0; i<giocatori.size(); i++){//ciclo che scorre i giocatori
			for(int k=0; k<cardsNumber; k++){//ciclo che scorre il numero di carte
				giocatori.get(i).addPoliticCard(dec.draw());//aggiungo la carta politica al giocatore
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
	
}
