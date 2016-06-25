package it.polimi.ingsw.cg23.server.controller.creation;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.controller.xmlreader.XmlInterface;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
import it.polimi.ingsw.cg23.server.model.exception.XmlException;
import it.polimi.ingsw.cg23.utility.Print;

/**
 * create the cities and the regions
 */
public class CreateRegionCity {

	private Print p;
	private XmlInterface leggiXml;

	private List <Region> regioni;//lista regioni
	private String endPath;//nome del file che contine le info della citta'
	private String[][] cityInfo;//array con le informazioni delle citta'

	/**
	 * constructor
	 * @param endPath the name of the file xml
	 * @throws XmlException 
	 */
	public CreateRegionCity(String endPath) throws XmlException{
		this.p=new Print();
		this.leggiXml=new XmlInterface();
		this.regioni = new ArrayList<>();
		this.endPath=endPath;//endpath e' il nome del file xml da leggere
		this.cityInfo=leggiXml.cittaXml(endPath);
	}

	/**
	 * create the regions object and add at the regions list
	 * @param bk the bonus king
	 * @return the regions list
	 */
	public List<Region> createRegions(BonusKing bk){
		int regionNumber=p.regionsNumber(cityInfo);//numero di regioni
		int c=cityInfo.length/regionNumber;//numero di citta' per regione
		String[][] regionBonus=leggiXml.getBonusRegion(endPath);

		for(int i=0; i<regionNumber; i++){//ciclo che scorre le regioni
			RegionDeck rd=new RegionDeck(2);//creo il regiondeck
			int regBonus=Integer.parseInt(regionBonus[i][1]);//trasformo i bonus regione in interi
			Region r=new Region(cityInfo[i*c][4],regBonus,rd,bk);//creo la regione
			regioni.add(r);//creata una nuova regione e aggiunta alla lista
		}
		return regioni;
	}

	/**
	 * create the cities object and add at the citta list
	 * @param j the number of the region
	 * @param r the region
	 * @param typeList the type list
	 * @return the city list
	 */
	public List<City> createCities(int j, Region r, List<Type> typeList){//crea le citta' della regione
		int regionNumber=p.regionsNumber(cityInfo);//numero di regioni
		List <City> citta=new ArrayList<>();//lista citta

		for(int i=0; i<cityInfo.length/regionNumber; i++){//ciclo che scorre le citta' di una regione
			int ii=j*cityInfo.length/regionNumber+i;

			for(int k=0; k<typeList.size(); k++){
				if(cityInfo[ii][1].equals(typeList.get(k).getName())){
					City c=new City(cityInfo[ii][3].charAt(0), cityInfo[ii][0], typeList.get(k), r);//creo la citta'
					citta.add(c);//aggiungo la citta' alla lista delle citta'
				}
			}
		}
		return citta;
	}

	/**
	 * set the neighbors of a city
	 * @param citta the list of the cities
	 */
	public void addNeighbors(List<City> citta){
		for(int h=0; h<citta.size(); h++){//scorre le citta' a cui aggiungere i vicini
			for(int i=0; i<cityInfo.length; i++){//scorre le citta' prese dall'xml
				if(cityInfo[i][0].equals(citta.get(h).getName())){//cerco la citta' attuale da quelle dell'xml
					nearVicini(i,h, citta);
				}
			}
		}
	}

	/**
	 * broken the addNeighbors() function that there was 4 innest
	 * @param i the xml city
	 * @param h the city to add neighbors
	 */
	private void nearVicini(int i, int h, List<City> citta){//rompe la funzione addNeighbors che aveva troppi innesti
		for(int k=0; k<cityInfo[i][2].length(); k++){//scorro il numero di link delle citta'
			char link=cityInfo[i][2].charAt(k);
			for(int j=0; j<citta.size(); j++){//scorro le citta'
				if(link==citta.get(j).getId()){
					citta.get(h).addNeighbor(citta.get(j));//aggiungo alla citta' il vicino trovato
				}
			}
		}
	}

}
