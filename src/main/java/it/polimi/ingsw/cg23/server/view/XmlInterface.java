package it.polimi.ingsw.cg23.server.view;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;
import it.polimi.ingsw.cg23.server.view.xmlreader.ReadCittaXml;
import it.polimi.ingsw.cg23.server.view.xmlreader.ReadColorXml;
import it.polimi.ingsw.cg23.server.view.xmlreader.ReadCostructionXml;
import it.polimi.ingsw.cg23.server.view.xmlreader.ReadNobilityTrackXml;
import it.polimi.ingsw.cg23.server.view.xmlreader.ReadType;

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
	 */
	public String[][] cittaXml(String endPath){

		String[][] cityInfo = null;

		try {//provo a leggere il file xml
			//int citynum=cityNum(endPath);//numero di citta' nel file xml
			//int cityNodeNumber=cityNodeNumber(endPath);//numero di nodi di citta' nel file xml
			//cityInfo=new String[citynum][cityNodeNumber];//array con le informazioni delle citta'

			cityInfo=cittaXml.readFileXml(endPath);
		} catch (XmlException e) {
			logger.error(err + endPath, e);
			cityInfo=null;
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
		/* array CostructionCard prototype require
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
}
