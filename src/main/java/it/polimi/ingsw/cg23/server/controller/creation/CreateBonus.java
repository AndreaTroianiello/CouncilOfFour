package it.polimi.ingsw.cg23.server.controller.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.server.controller.xmlreader.XmlInterface;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAdditionalAction;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAssistants;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCityToken;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCoin;
import it.polimi.ingsw.cg23.server.model.bonus.BonusGetPermitTile;
import it.polimi.ingsw.cg23.server.model.bonus.BonusNobility;
import it.polimi.ingsw.cg23.server.model.bonus.BonusPolitics;
import it.polimi.ingsw.cg23.server.model.bonus.BonusTileBonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

/**
 * create the bonus (city, king, costruction card)
 */
public class CreateBonus {

	private XmlInterface leggiXml;
	private BonusKing bk;
	private List <Bonus> bonusList;//lista di bonus
	private String[] bonus;//array con i bonus delle citta'

	/**
	 * costructor
	 * @param endpath the name of the file xml
	 */
	public CreateBonus(){
		this.bonusList=new ArrayList<>();
		this.leggiXml=new XmlInterface();
		this.bk=new BonusKing(null);
		this.bonus=leggiXml.bonusCity("CityBonus.xml");
	}

	/**
	 * aggiunta di bonus alla citta'
	 * @param c the actual city
	 */
	public void getCityBonus(City c){

		if("Purple".equals(c.getType())){//la citta' del re non ha bonus
			return;
		}
		String b;//contiene il nome del bonus

		StringTokenizer st = new StringTokenizer(bonus());//string tokenizer del nome dei bonus
		while(st.hasMoreTokens()){

			String name=st.nextToken(",");//estrae la sottostring fino alla virgola
			b=name.substring(1, name.length());//isolo il nome del bonus
			int number=Integer.parseInt(name.substring(0, 1));//contiene il numero es. 1 carta politica, 2 coins

			for(int j=0; j<bonusList.size(); j++){//ciclo che scorre la lista dei bonus
				if(bonusList.get(j).toString().contains(b)){//controllo se il bonus contiene quello che sto cercando

					Bonus bo=bonusList.get(j).copy();//clono il bonus preso dalla lista dei bonus
					c.addBonus(bo);//aggiungo il bonus alla citta'
					bo.setNumber(number);//setta il numero di bonus
				}
			}
		}
	}

	/**
	 * find a random bonus in the array
	 * @return a bonus find in the array
	 */
	private String bonus(){
		Random rnd=new Random();
		String myBonus;//stringa contenenete il bonus
		int n=rnd.nextInt(bonus.length);//intero random massimo come il numero di bonus

		while(bonus[n]=="x"){//creca un bonus disponibile
			n=rnd.nextInt(bonus.length);
		}

		myBonus=bonus[n];//recupera il bonus disponibile
		bonus[n]="x";//annullo il bonus trovato

		return myBonus;
	}

	/**
	 * create the bonuses (null)
	 * @return a bonus list with all the type of bonus
	 * @param board the board
	 */
	public List<Bonus> bonusList(Board board){//creo e aggiungo i bonus alla lista bonus
		bonusList.add(new BonusAdditionalAction());
		bonusList.add(new BonusAssistants());
		bonusList.add(new BonusCityToken(1));
		bonusList.add(new BonusCoin(0));
		bonusList.add(new BonusGetPermitTile());
		bonusList.add(new BonusNobility(0,board));
		bonusList.add(new BonusPolitics(0,board));
		bonusList.add(new BonusTileBonus());
		bonusList.add(new BonusVictoryPoints(0));
		return bonusList;
	}

	/**
	 * create the bonus king
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
		bk.setBonusValues(kingList);
		return bk;
	}

	/**
	 * add bonuses in the construction card
	 * @param bpt the business permit tile
	 * @param bonusTotali a string with all the bonuses
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
					Bonus bo=bonusList.get(j).copy();
					bpt.addBonus(bo);//aggiungo alla carta costruzione i suoi bonus
					bo.setNumber(number);//setto il numero di bonus
					break;
				}
			}
		}
	}
}
