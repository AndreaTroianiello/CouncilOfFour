package it.polimi.ingsw.cg23.server.view;

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
 * classe che si occupa della lettura del file xml 
 *
 */
public class ReadCittaXml {
	private String path="src/main/resources/xmlFiles/";//file location
	/* array city prototype
	 * coloumn 0: name of the city
	 * coloumn 1: color of the city
	 * coloumn 2: link of the city (the id to which it is connected the city)
	 * coloumn 3: id of the city
	 * coloumn 4: bonus of the city
	 * coloumn 5: region of the city
	 */
	
	/**
	 * legge il file xml
	 * @param endpath, the name of file (with the extension ".xml")
	 * @return bidimensional array with city info, null array if there is some problems
	 * @throws XmlException 
	 */
	public String[][] readFileXml(String endPath) throws XmlException{
		final int cityNodeNumber=cityNodeNumber(endPath); //numero di nodi di city
		final int citynum=cityNumber(endPath);//numero di citta'
		String[][] city=new String[citynum][cityNodeNumber];//array per salvare le infromazioni delle citta'

		try {
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
		city[i][4]=actualElement.getElementsByTagName("bonus").item(0).getTextContent();//recupera i bonus della città

		Node actualZoneNode=zoneName.item(i/(citynum/zoneName.getLength()));//nodo zona delle citta'
		city[i][5]=actualZoneNode.getTextContent();//recupera il tipo di citta' (costa, collina, montagna)

		return city;
	}

	/**
	 * strasforma la stringa che contiene le città vicine in una piu' leggibile
	 * @param nome, the string to executhe the substring (the string contain the city link)
	 * @param idnum, the number of the city link
	 * @return a string with the city link easily to know 
	 */
	private String idConversion(String nome, int idnum){
		String idConcat="";//id delle citta' vicine
		for(int i=1; i<=idnum; i++){
			int k=7*i;//nella stringa originaria gli id della citta' vicine sono a distanza di 7
			idConcat+=nome.substring(k-1, k);
		}
		return idConcat;//ritorna la stringa della città
	}

	/**
	 * calcola il numero di citta' nel file xml
	 * @return the number of cities in the xml file
	 * @throws XmlException 
	 */
	public int cityNumber(String endPath) throws XmlException{
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
	 * calcola il numero di nodi di citta'
	 * @return the number of city nodes, 0 if no city or error
	 * @throws XmlException 
	 */
	public int cityNodeNumber(String endPath) throws XmlException{
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
	 * find the type of city
	 * @param endPath, the name of the file with .xml
	 * @return a bidimensional array with the type of city
	 * @throws XmlException 
	 */
	public String[][] getType(String endPath) throws XmlException{
		try {
			File inputFile = new File(path+endPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList color=doc.getElementsByTagName("colors");//lista dei nodi che contengono "color"
			int typeNum=color.getLength();
			String[][] type=new String[typeNum][2];
			for(int i=0; i<typeNum; i++){
				type[i][0]=color.item(i).getChildNodes().item(1).getTextContent();//recupero il nome del colore
				type[i][1]=color.item(i).getChildNodes().item(3).getTextContent();//recupero i punti del colore
			}
			return type;
		
		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
	}

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
