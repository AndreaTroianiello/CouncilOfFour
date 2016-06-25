package it.polimi.ingsw.cg23.server.controller.xmlreader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * class that deals with the xml files reading 
 *
 */
public class ReadCittaXml {
	private String path="src/main/resources/xmlFiles/map/";//file location
	/* array city prototype
	 * coloumn 0: name of the city
	 * coloumn 1: color of the city
	 * coloumn 2: link of the city (the id to which it is connected the city)
	 * coloumn 3: id of the city
	 * coloumn 4: region of the city
	 */

	/**
	 * reads the xml file
	 * @param endPath, the name of file (with the extension ".xml")
	 * @return two-dimensional array with city info, null array if there is some problems
	 * @throws XmlException 
	 */
	public String[][] readFileXml(String endPath) throws XmlException{
		String[][] city;

		try {
			int citynum=cityNumber(endPath);//numero di citta'
			int cityNodeNumber=cityNodeNumber(endPath); //numero di nodi di city
			city=new String[citynum][cityNodeNumber];//array per salvare le infromazioni delle citta'
			
			File inputFile = new File(path+endPath);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList citylist=doc.getElementsByTagName("city");//lista dei nodi che contengono "city"
			NodeList zoneName=doc.getElementsByTagName("namez");//lista dei nodi che cntengono "namez" (nome della zona)

			for (int i=0; i<citynum; i++){//scorre le citta' presenti nel file xml
				city=createArray(i, citylist, zoneName, city, citynum);//recupero le informazioni della citta'
			}

			return city;

		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
	}

	private String[][] createArray(int i, NodeList citylist, NodeList zoneName, String[][]city, int citynum){
		Node actualNode=citylist.item(i);//nodo attualmente in uso
		Element actualElement=(Element) actualNode;//cast del nodo in elemento per poterlo usare

		city[i][0]=actualElement.getElementsByTagName("name").item(0).getTextContent();//recupera il nome della città

		city[i][1]=actualElement.getElementsByTagName("color").item(0).getTextContent();//recupera il colore della città

		String nome=actualElement.getElementsByTagName("link").item(0).getTextContent();//recupera i link della città (id delle citta' vicine)
		int idnum=actualElement.getElementsByTagName("link").item(0).getChildNodes().getLength();//numero dei tag filgi di link + il tag di chiusura di link
		idnum=(idnum-1)/2;//numero degli id (numero delle citta' vicine)

		city[i][2]=idConversion(nome,idnum);

		city[i][3]=actualElement.getElementsByTagName("Id").item(0).getTextContent();//recupera l'id della città
				
		Node actualZoneNode=zoneName.item(i/(citynum/zoneName.getLength()));//nodo zona delle citta'
		city[i][4]=actualZoneNode.getTextContent();//recupera il tipo di citta' (costa, collina, montagna)

		return city;
	}

	/**
	 * transforms the string that contains the neighbor city in one more readable 
	 * @param nome the string to execute the substring (the string contain the city link)
	 * @param idnum the number of the city link
	 * @return a string with the linked city easily to know 
	 */
	private String idConversion(String nome, int idnum){
		String idConcat="";//id delle citta' vicine
		for(int i=1; i<=idnum; i++){
			int k=7*i;//nella stringa originaria gli id della citta' vicine sono a distanza di 7
			idConcat=idConcat.concat(nome.substring(k-1, k));
		}
		return idConcat;//ritorna la stringa della città
	}

	/**
	 * count the number of the cities in the xml file
	 * @return the number of cities in the xml file
	 * @throws XmlException 
	 */
	private int cityNumber(String endPath) throws XmlException{
		try {
			File inputFile = new File(path+endPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file
			NodeList citylist=doc.getElementsByTagName("city");//lista dei nodi che contengono "city"

			return citylist.getLength();//numero di citta'

		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
	}

	/**
	 * count the number of cities' nodes
	 * @return the number of city nodes, 0 if no city or error
	 * @throws XmlException 
	 */
	private int cityNodeNumber(String endPath) throws XmlException{
		try {	
			File inputFile = new File(path+endPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file
			Node rootnode = doc.getFirstChild();//recupera il primo nodo dell'xml (map)
			Node zone = rootnode.getChildNodes().item(3);//primo elemento dei figli di map = secondo nodo xml (zone)
			Node cities= zone.getChildNodes().item(3);//terzo elemento dei figli di zone = quarto nodo xml (cities)
			Node citty=cities.getChildNodes().item(1);// primo elemento dei figli di cities = qunto nodo xml (city)

			return (citty.getChildNodes().getLength()-1)/2+1; //numero di nodi di city, +1 perchè c'è da aggiungere zona

		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
	}

	/**
	 * read the region bonus in the xml file
	 * @param endPath the xml file name
	 * @return an array with the region bonus
	 * @throws XmlException
	 */
	public String[][] getBonusRegion(String endPath) throws XmlException{

		try {
			File inputFile = new File(path+endPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList coin=doc.getElementsByTagName("coin");//lista dei nodi che contengono "color"
			NodeList namez=doc.getElementsByTagName("namez");//lista dei nodi che contengono "color"
			String[][] regionBonus=new String[namez.getLength()][2];

			for(int i=0; i<namez.getLength(); i++){
				regionBonus[i][0]=namez.item(i).getTextContent();
				regionBonus[i][1]=coin.item(i).getTextContent();
			}

			return regionBonus;

		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
	}
}
