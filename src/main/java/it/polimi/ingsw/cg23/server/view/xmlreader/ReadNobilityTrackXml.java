package it.polimi.ingsw.cg23.server.view.xmlreader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

/**
 * class to read the nobility track from xml
 *
 */
public class ReadNobilityTrackXml {
	private String path="src/main/resources/xmlFiles/";//file location

	/**
	 * read the lenght of the nobility track in the xml file
	 * @param endPath, the name of the xml file with .xml
	 * @return an int with the lenght of the nobility track
	 * @throws XmlException 
	 */
	public int nobilityTrackLenght(String endPath) throws XmlException{

		try {
			File inputFile = new File(path+endPath);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList lenght=doc.getElementsByTagName("lenght");//lista dei nodi che contengono "lenght"
			
			return Integer.parseInt(lenght.item(0).getTextContent());//ritorno la lunghezza del nobility track
		
		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
	}

	/**
	 * create a bidimensional array with the nobility track info
	 * @param endPath, the name of the xml file with .xml
	 * @return a bidimensional array with the nobility track info
	 * @throws XmlException 
	 */
	public String[][] nobilityTrackBonus(String endPath) throws XmlException{
		try {
			File inputFile = new File(path+endPath);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList box=doc.getElementsByTagName("box");//lista dei nodi che contengono "box"
			String[][] nobilityBonus=new String[box.getLength()][2];//array lungo quanto i box del'xml
			for(int i=0; i<box.getLength(); i++){//ciclo che scorre il numero di box trovati nell'xml
				nobilityBonus[i][0]=doc.getElementsByTagName("number").item(i).getTextContent();
				nobilityBonus[i][1]=doc.getElementsByTagName("bonus").item(i).getTextContent();
			}
			
			return nobilityBonus;//ritorno l'array con le informzioni dell'xml
			
		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
	}

}
