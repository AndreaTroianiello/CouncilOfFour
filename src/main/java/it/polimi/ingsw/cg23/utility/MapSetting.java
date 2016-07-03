package it.polimi.ingsw.cg23.utility;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
/**
 * methods to create the map
 * @author viga94_
 *
 */
public class MapSetting {

	private final int space;
	private ColorManager cm;

	/**
	 * constructor
	 */
	public MapSetting(){
		this.space=50;
		this.cm=new ColorManager();
	}

	/**
	 * find the city from the regions
	 * @param regions the regions
	 * @return the list of the cities
	 */
	public List<City> getCityfromRegion(List<Region> regions){
		List<City> city = new ArrayList<>();
		for(int i=0; i<regions.size(); i++){
			city.addAll(regions.get(i).getCities());
		}
		return city;
	}

	/**
	 * convert all the city bonus to a string
	 * @param city the city you want to have the bonus
	 * @return a string with all the cities' bonus
	 */
	public String cityBonus(City city){
		String bonus="";

		for(int i=0; i<city.getToken().size(); i++){//ciclo che scorre i bonus di una citta'
			bonus=bonus.concat(city.getToken().get(i).getName());//ritorna il nome del bonus
			bonus=bonus.concat(", ");
		}
		if(bonus.length()==0)//se non ci sono bonus ritorna una stringa vuota
			return "";
		bonus=bonus.substring(0, bonus.length()-2);//tolgo l'ultima virgola e spazio
		return bonus;
	}

	/**
	 * add some spaces in a string to reach the wanted length
	 * 
	 * @param nome the String you want to extend
	 * @param totalSpace the total length you need (string + space)
	 * @return the String extended
	 */
	public String addSpace(String nome, int totalSpace){//aggiunge spazi alla stringa data per raggiungere la lunghezza desiderata
		int length=totalSpace-nome.length();//spazi da aggiungere
		String nomeExtended=nome;

		if(nome.length()<totalSpace){
			for(int j=0; j<length; j++){//ciclo per aggiungere spazi
				nomeExtended=nomeExtended.concat(" ");//aggiungo spazi
			}
		}

		return nomeExtended;
	}

	/**
	 * @param number the number to add space
	 * @param totalSpace the number of space you want to add
	 * @return a string with the number and the space
	 */
	public String addSpace(int number, int totalSpace){	
		String numberExtended=Integer.toString(number);
		int length=totalSpace-digits(number);//spazi da aggiungere

		if(digits(number)<totalSpace){
			for(int j=0; j<length; j++){//ciclo per aggiungere spazi
				numberExtended=numberExtended.concat(" ");//aggiungo spazi
			}
		}

		return numberExtended;
	}

	/**
	 * calculate the number of digits in a number
	 * @param number the number you want to calculate the digits
	 * @return the number of digits
	 */
	private int digits(int number){
		int count=0;
		int num=number;//creata nuova variabile perche' richiesta da sonar

		if(num==0)//se il numero e' 0 ha una cifra
			return 1;
		if(num<0){//se il numero e' minore di 0 lo modulizzo
			num=Math.abs(num);
			count++;//aggiungo uno per il meno
		}
		while(num>0){
			num/=10;
			count++; 
		}

		return count;
	}

	/**
	 * print the region name and the bonus region
	 * @param reg the regions list
	 * @param space the space
	 * @return a string with the spaced name of the region
	 */
	public String printName(List<Region>reg, int space){
		String name="";

		for(int j=0; j<reg.size(); j++){//ciclo che scorre le regioni
			String n;
			if(reg.get(j).isBonusAvailable())
				n=reg.get(j).getName().toUpperCase()+" Victory points: "+reg.get(j).getBonus().getNumber();
			else
				n=reg.get(j).getName().toUpperCase();

			name=name.concat(addSpace(n, space));//nomi delle regioni
		}

		return name;
	}

	/**
	 * creates the business permit tiles to print
	 * @param region the region
	 * @param space half of the space
	 * @return a string with the business permit tiles
	 */
	public String createCostructionShowed(List<Region> region, int space){
		String cardShowed="";//plancia delle carte costrucione
		int regSize=region.size();//numero di regioni

		for(int j=0; j<regSize; j++){//ciclo che scorre le regioni
			cardShowed=cardShowed.concat(addSpace("Business Permit Tiles "+region.get(j).getName()+":", space*2));//carte costruzione
		}
		cardShowed=cardShowed.concat("\n");

		for(int j=0; j<regSize; j++){//ciclo che scorre le regioni
			String mino="";
			List<BusinessPermitTile> costruction=region.get(j).getDeck().getShowedDeck();//recupero la lista delle carte permesso costruzione

			for(int i=0; i<costruction.size(); i++){//ciclo che scorre le carte costruzione della regione
				mino=mino.concat(addSpace(addMinus(space-5), space));//aggiungo i -
			}
			cardShowed=cardShowed.concat(mino);//aggiungo il tutto alla string finale
		}
		cardShowed=cardShowed.concat("\n");

		for(int j=0; j<regSize; j++){//ciclo che scorre le regioni
			List<BusinessPermitTile> costruction=region.get(j).getDeck().getShowedDeck();//recupero la lista delle carte permesso costruzione

			for(int i=0; i<costruction.size(); i++){//ciclo che scorre le carte costruzione della regione
				String card="";//stringa con gli id delle citta'
				card+=addSpace("|"+costruction.get(i).getCitiesId().toString(), space-6);//aggiungo gli id delle citta'

				card=addSpace(card+"|", space);
				cardShowed=cardShowed.concat(card);//aggiungo il tutto alla string finale
			}
		}
		cardShowed=cardShowed.concat("\n");

		cardShowed+=getBonusCostructor(region, space, 0)+"\n";//recupero i bonus delle carte costruzione (bonus 1)
		cardShowed+=getBonusCostructor(region, space, 1)+"\n";//recupero i bonus delle carte costruzione (bonus 2)

		for(int j=0; j<regSize; j++){//ciclo che scorre le regioni
			String mino="";
			List<BusinessPermitTile> costruction=region.get(j).getDeck().getShowedDeck();//recupero la lista delle carte permesso costruzione

			for(int i=0; i<costruction.size(); i++){//ciclo che scorre le carte costruzione della regione
				mino=mino.concat(addSpace(addMinus(space-5), space));
			}
			cardShowed=cardShowed.concat(mino);//aggiungo il tutto alla string finale
		}
		cardShowed=cardShowed.concat("\n");

		return cardShowed;
	}

	/**
	 * 
	 * @param reg the regions list
	 * @param space the space
	 * @param n the number of bonus (0,1)
	 * @return a string with the bonus name
	 */
	private String getBonusCostructor(List<Region> reg, int space, int n){
		String bonusName="";

		for(int j=0; j<reg.size(); j++){//ciclo che scorre le regioni
			List<BusinessPermitTile> costruction=reg.get(j).getDeck().getShowedDeck();//recupero la lista delle carte permesso costruzione

			for(int i=0; i<costruction.size(); i++){//ciclo che scorre le carte costruzione della regione
				String bon="";//stringa che contiene i bonus
				List<Bonus> bo=costruction.get(i).getBonusTile();//lista con i bonus

				if(bo.size()>1||n<1){//il secondo bonus può non esserci
					bon=bon.concat(addSpace("|"+bo.get(n).getName(), space-6));
				}else{
					bon=bon.concat(addSpace("|",space-6));//nel caso non ci sia il secondo bonus
				}
				bon=addSpace(bon+"|", space);//aggiungo gli spazi
				bonusName=bonusName.concat(bon);//aggiungo il tutto alla string finale
			}
		}

		return bonusName;
	}

	/**
	 * transforms a list of cities in a string of cities' id
	 * @param c the city
	 * @return a string with the id of the city
	 */
	public String getNeighbourID(City c){
		List<City> vicini=c.getNeighbors();//recupero la lista dei vicini della citta'
		String viciniId="Neighbors: ";//stringa che contiene i vicini della citta'

		for(int i=0; i<vicini.size(); i++){//scorre il numero di vicini della citta'
			viciniId=viciniId.concat(Character.toString(vicini.get(i).getId()));//aggiunge alla stringa l'id della citta' vicina
			viciniId=viciniId.concat(", ");
		}
		return viciniId.substring(0, viciniId.length()-2);//tolgo gli ultimi due caratteri (virgola e spazio)
	}

	/**
	 * return a string with the specified number of minus -
	 * @param number the number of minus you want
	 * @return a string with minus
	 */
	public String addMinus(int number){
		String minus="";

		for(int i=0; i<number; i++){//ciclo che scorre la quantita' di meno da aggiungere
			minus=minus.concat("-");//aggiungo un meno
		}

		return minus;
	}

	/**
	 * create the region's council
	 * @param regions the regions
	 * @return a string with the councillors
	 */
	public String councillors(List<Region> regions){
		String councillor="";

		for(int k=0; k<regions.size(); k++){
			Region reg=regions.get(k);
			String aiutanti = reg.getName() +" Council: ";
			for(int i=0; i<reg.getCouncil().getCouncillors().size(); i++){
				aiutanti=aiutanti.concat(cm.getColorName(reg.getCouncil().getCouncillors().get(i).getColor()));
				aiutanti=aiutanti.concat(" ");
			}
			councillor=councillor.concat(addSpace(aiutanti, space));
		}

		return councillor;
	}

	/**
	 * print the bonus king, the king council and the type
	 * @param b the board
	 * @return a string with bonus king and king council
	 */
	public String bonusCouncilKingType(Board b){
		String consiglieri="King Council: ";
		List<Councillor> kingCouncillors=b.getKing().getCouncil().getCouncillors();//consiglieri del re

		for(int i=0; i<kingCouncillors.size(); i++){
			consiglieri=consiglieri.concat(cm.getColorName(kingCouncillors.get(i).getColor()));//converto i colori in nomi
			consiglieri=consiglieri.concat(" ");
		}
		consiglieri=addSpace(consiglieri, space);

		String bonus="Bonus king available: ";
		BonusKing bk=b.getBonusKing();//bonus king

		for(int i=bk.getCurrentIndexBonusKing(); i<bk.getBonusValues().size()-1; i++){//scorre i bonus king
			bonus=bonus.concat(b.getBonusKing().getBonusValues().get(i).toString()+" ");
		}

		String tipi="Available type: ";
		List<Type> tipes=b.getTypes();
		for(int i=0; i<tipes.size(); i++){
			if("Purple".equals(tipes.get(i).getName()))
				tipi=tipi.concat("");
			else
				if(tipes.get(i).isBonusAvailable())
					tipi=tipi.concat(tipes.get(i).getName()+": "+tipes.get(i).getBonus().getNumber()+", ");
		}

		return addSpace(consiglieri, space)+addSpace(bonus, space)+addSpace(tipi.substring(0, tipi.length()-2), space);
	}

}
