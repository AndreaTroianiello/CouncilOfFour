package it.polimi.ingsw.cg23.server.controller.xmlreader;

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
 * read the type from xml file
 */
public class ReadType {
	private String path="src/main/resources/xmlFiles/";//file location

	/**
	 * find the colors in the xml file
	 * @param endPath, the xml file name
	 * @return an array with the colors
	 * @throws XmlException
	 */
	public String[][] typeXml(String endPath) throws XmlException{

		try {

			File inputFile = new File(path+endPath);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList color=doc.getElementsByTagName("color");//lista dei nodi che contengono "color"
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

}
