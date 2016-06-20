package it.polimi.ingsw.cg23.server.controller.xmlreader;

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
	private ReadColorXml colorXml;//legge i colori dall'xml
	private ReadType typeXml;//legge i tipi dall'xml
	private ReadCityBonusXml bonusXml;//legge i bonus delle citta' dal file xml

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
		this.colorXml=new ReadColorXml();
		this.typeXml=new ReadType();
		this.bonusXml=new ReadCityBonusXml();
	}

	/**
	 * find the type(color) of the city
	 * @param endPath, the name of the file with the .xml
	 * @return a bidimensional array with the type(color)
	 */
	public String[][] getType(String endPath){
		String[][] type=null;

		try {//provo a leggere il file xml
			type=typeXml.typeXml(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
			type=null;
		}

		return type;
	}

	/**
	 * find the lenght of the nobility track
	 * @param endPath, the name of the file with the .xml
	 * @return the lenght of the nobility track
	 */
	public int getNobilityTrackLength(String endPath){
		int nobilityTrackLenght=0;

		try {//provo a leggere il file xml
			nobilityTrackLenght=nobilityXml.nobilityTrackLenght(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
			nobilityTrackLenght=0;
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
			nobilityTrackBonus=null;
		}

		return nobilityTrackBonus;
	}

	/**
	 * carica il file xml con le infromazioni della partita
	 * @return a bidimensional array with all the city info
	 * @param endPath, the name of the file to read
	 * @throws XmlException 
	 */
	public String[][] cittaXml(String endPath) throws XmlException{

		String[][] cityInfo = null;
		cityInfo=cittaXml.readFileXml(endPath);
		
		/* array cityInfo prototype returned
		 * coloumn 0: name of the city
		 * coloumn 1: color of the city
		 * coloumn 2: link of the city (the id to which it is connected the city)
		 * coloumn 3: id of the city
		 * coloumn 4: region of the city
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
			bonusRegion = null;
		}

		return bonusRegion;//ritorna un array con i bonus della regione
	}

	/**
	 * read the costruction card in the xml file
	 * @param endPath, the final part of the xml file path (name+extension)
	 * @return a bidimensional array with the costruction cards info
	 */
	public String[][] costructionCard(String endPath){
		String[][] costructionCard=null;

		try {//provo a leggere il file xml
			costructionCard=costructionXml.readCardXml(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
			costructionCard=null;
		}

		return costructionCard;
		/* array CostructionCard prototype
		 * coloumn 0: region
		 * coloumn 1: city
		 * coloumn 2: bonus
		 */
	}

	/**
	 * find the colors in the xml file
	 * @param endPath, the xml file name
	 * @return an array with the colors
	 */
	public String[] colorXml(String endPath){
		String[] color=null;

		try {//provo a leggere il file xml
			color=colorXml.coloriXml(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
			color=null;
		}

		return color;
	}

	/**
	 * find the number of colors in the xml file
	 * @param endPath, the name of the xml file
	 * @return the number of color in the xml file
	 */
	public int colorNumberXml(String endPath){
		int color=0;

		try {//provo a leggere il file xml
			color=colorXml.colorNumber(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
			color=0;
		}

		return color;
	}
	
	/**
	 * find the city bonus in the xml file
	 * @param endPath, the file name
	 * @return an array with the city bonus
	 */
	public String[] bonusCity(String endPath){
		String[] bonus=null;

		try {//provo a leggere il file xml
			bonus=bonusXml.bonusCityXml(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);//errore
			bonus=null;
		}

		return bonus;
	}
}
