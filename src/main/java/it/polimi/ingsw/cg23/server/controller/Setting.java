package it.polimi.ingsw.cg23.server.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.server.controller.creation.CreateBonus;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityBox;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.view.XmlInterface;
import it.polimi.ingsw.cg23.utility.ColorManager;

/**
 * 
 * create the element of the game
 */
public class Setting {

	private XmlInterface leggiXml;
	private CreateBonus cb;
	private List <Bonus> bonusList;//lista di bonus
	private String endpath;//nome del file che contine le info della citta'
	
	/**
	 * costructor
	 */
	public Setting(){
		this.leggiXml=new XmlInterface();
		this.cb=new CreateBonus("ConfigurazionePartita.xml");
		this.bonusList=cb.bonusList(null);
		this.endpath="ConfigurazionePartita.xml";
	}

	/**
	 * refill the nobility track with the info find in the xml file
	 * @param nT, the bobility track to fill
	 * @return the nobility track filled
	 */
	public NobilityTrack nobilityTrackFill(NobilityTrack nT){
		String[][]nobilityInfo=leggiXml.getNobilityTrackBonus("NobilityTrack.xml");//informazioni recuperate dall'xml
		
		for(int i=0; i<nobilityInfo.length; i++){//ciclo che scorre il numero di caselle presenti nel file xml
			String b;//contiene il nome del bonus
			StringTokenizer st = new StringTokenizer(nobilityInfo[i][1]);//string tokenizer del nome dei bonus
			while(st.hasMoreTokens()){//ciclo finche' ci sono token
				String name=st.nextToken(",");//estrae la sottostring fino alla virgola
				b=name.substring(1, name.length());//isolo il nome del bonus
				int number=Integer.parseInt(name.substring(0, 1));//contiene il numero es. 1 carta politica, 2 coins

				nobilityBonus(b, number, nobilityInfo[i][0], nT);//riempie il nobility track con i bonus
			}
		}
		return nT;
	}
	
	/**
	 * broken the nobilityTrackFill because was too eavy for sonar 
	 * @param b, the bonus name
	 * @param number, the bonus number
	 * @param nobinfo, nobility track number
	 * @param nT, the nobility track
	 */
	private void nobilityBonus(String b, int number, String nobinfo, NobilityTrack nT){
		List<NobilityBox> boxList=nT.getNobilityBoxes();//lista delle caselle del nobility track
		
		for(int j=0; j<bonusList.size(); j++){//ciclo che scorre la lista dei bonus
			if(bonusList.get(j).toString().contains(b)){//controllo se il bonus contiene quello che sto cercando
				Bonus bo=bonusList.get(j).copy();//clono il bonus preso dalla lista dei bonus
				
				bo.setNumber(number);//setta il numero di bonus
				int boxNum=Integer.parseInt(nobinfo);//numero della casella del nobility track
				boxList.get(boxNum).addBonus(bo);//aggiungo alla casella del nobility track il bonus corretto
			}
		}
	}

	/**
	 * create the type(color) of the city
	 * @param bk, the bonus king
	 * @return a list with all the types
	 */
	public List<Type> createType(BonusKing bk){
		String[][] array=leggiXml.getType(endpath);//recupero i type dall'xml
		List <Type> typeList=new ArrayList<>();;//lista di type
		
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
	 * the Color array contains all the avaiable colors
	 * @return a Color array
	 */
	public Color[] color(){
		int colorNumber=leggiXml.colorNumberXml("Colori.xml");//numero di colori nel file xml
		Color[] arrayColori=new Color[colorNumber];//array di Color con i possibili colori
		ColorManager cm=new ColorManager();//color manager per creare i colori
		String[] colorsXml=leggiXml.colorXml("Colori.xml");
		
		for(int i=0; i<colorNumber; i++){
			arrayColori[i]=cm.getColor(colorsXml[i]);
		}
		
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
