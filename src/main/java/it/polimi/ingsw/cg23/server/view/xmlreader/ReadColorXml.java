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
 * read the color from the xml file
 */
public class ReadColorXml {
	private String path="src/main/resources/xmlFiles/";//file location

	/**
	 * find the colors in the xml file
	 * @param endPath, the xml file name
	 * @return an array with the colors
	 * @throws XmlException
	 */
	public String[] coloriXml(String endPath) throws XmlException{
		

		try {
			String[] color = new String[colorNumber(endPath)];//array con i colori

			File inputFile = new File(path+endPath);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList colori=doc.getElementsByTagName("color");//lista dei nodi che contengono "city"

			for (int i=0; i<colori.getLength(); i++){//scorre le citta' presenti nel file xml
				color[i]=colori.item(i).getTextContent();
			}

			
			return color;
		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
		
		
	}

	/**
	 * calcolate the number of colors in the xml file
	 * @param endPath, the name of the xml file
	 * @return the number of colors
	 * @throws XmlException
	 */
	public int colorNumber(String endPath) throws XmlException{
		int colorsNum=0;

		try {
			File inputFile = new File(path+endPath);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList colori=doc.getElementsByTagName("color");//lista dei nodi che contengono "city"
			colorsNum=colori.getLength();

			return colorsNum;
		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
		
	}

}
