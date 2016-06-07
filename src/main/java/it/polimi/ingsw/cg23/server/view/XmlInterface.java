package it.polimi.ingsw.cg23.server.view;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

/**
 * recupera le informazioni dai file xml
 *
 */
public class XmlInterface {
	private ReadCittaXml cittaXml;//legge l'xml delle citta'
	private ReadCostructionXml costructionXml;//legge l'xml delle carte costruzione
	private ReadNobilityTrackXml nobilityXml;//legge il nobility track dall'xml

	private static Logger logger;//logger

	//variabile err nel caso di errore nella lettura del file xml
	String err="ERROR! Errore nella lettura del file xml: ";

	/**
	 * costructor
	 */
	public XmlInterface(){
		//configurazione logger
		logger = Logger.getLogger(XmlInterface.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		this.cittaXml=new ReadCittaXml();
		this.costructionXml=new ReadCostructionXml();
		this.nobilityXml=new ReadNobilityTrackXml();
	}
	
	/**
	 * find the type(color) of the city
	 * @param endPath, the name of the file with the .xml
	 * @return a bidimensional array with the type(color)
	 */
	public String[][] getType(String endPath){
		String[][] type=null;

		try {//provo a leggere il file xml
			type=cittaXml.getType(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
		}

		return type;
	}

	/**
	 * find the lenght of the nobility track
	 * @param endPath, the name of the file with the .xml
	 * @return the lenght of the nobility track
	 */
	public int getNobilityTrackLenght(String endPath){
		int nobilityTrackLenght=0;

		try {//provo a leggere il file xml
			nobilityTrackLenght=nobilityXml.nobilityTrackLenght(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
		}

		return nobilityTrackLenght;
	}

	/**
	 * return the info of nobility track
	 * @param endPath, the name of the file with the .xml
	 * @return a bidimensional array with the nobility track info
	 */
	public String[][] getNobilityTrackBonus(String endPath){
		String[][] nobilityTrackBonus=null;

		try {//provo a leggere il file xml
			nobilityTrackBonus=nobilityXml.nobilityTrackBonus(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
		}

		return nobilityTrackBonus;
	}
	
	/**
	 * carica il file xml con le infromazioni della partita
	 * @return a bidimensional array with all the city info
	 * @param endPath, the name of the file to read
	 */
	public String[][] cittaXml(String endPath){
		int citynum=cityNum("ConfigurazionePartita.xml");//numero di citta' nel file xml
		int cityNodeNumber=cityNodeNumber("ConfigurazionePartita.xml");//numero di nodi di citta' nel file xml
		String[][] cityInfo=new String[citynum][cityNodeNumber];//array con le informazioni delle citta'
		
		try {//provo a leggere il file xml
			cityInfo=cittaXml.readFileXml(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
		}
		/* array cityInfo prototype returned
		 * coloumn 0: name of the city
		 * coloumn 1: color of the city
		 * coloumn 2: link of the city (the id to which it is connected the city)
		 * coloumn 3: id of the city
		 * coloumn 4: bonus of the city
		 * coloumn 5: region of the city
		 */
		return cityInfo;//ritorna l'array con le informazioni delle citta'
	}

	/**
	 * read the bonus region from the xml file
	 * @param endPath, the name of the file with .xml
	 * @return a bidimensional array with the region name and bonus region
	 */
	public String[][] getBonusRegion(String endPath){
		String[][] bonusRegion = null;

		try {//provo a leggere il file xml
			bonusRegion=cittaXml.getBonusRegion(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
		}

		return bonusRegion;//ritorna un array con i bonus della regione
	}

	/**
	 * 
	 * @param endPath, the final part of the xml file path (name+extension)
	 * @return a bidimensional array with the costruction cards info
	 */
	public String[][] costructionCard(String endPath){
		String[][] costructionCard=null;

		try {//provo a leggere il file xml
			costructionCard=costructionXml.readCardXml(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
		}

		return costructionCard;
		/* array CostructionCard prototype require
		 * coloumn 0: region
		 * coloumn 1: city
		 * coloumn 2: bonus
		 */
	}
	
	/**
	 * 
	 * @param endpath the name of xml file with .xml
	 * @return the number of cities in the xml file
	 */
	private int cityNum(String endPath){
		int numeroCitta=0;
		
		try {//provo a leggere il file xml
			numeroCitta=cittaXml.cityNumber(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
			numeroCitta=-1;
		}

		return numeroCitta;
	}

	/**
	 * 
	 * @param endPath the name of xml file with .xml
	 * @return the number city node in the xml file
	 */
	public int cityNodeNumber(String endPath){
		int nodeNumber=0;
		
		try {//provo a leggere il file xml
			nodeNumber=cittaXml.cityNodeNumber(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
			nodeNumber=-1;
		}

		return nodeNumber;
	}
}
