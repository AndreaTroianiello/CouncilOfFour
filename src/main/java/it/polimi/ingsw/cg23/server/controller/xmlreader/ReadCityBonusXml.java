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
 * read the city bonus in the xml file
 * @author viga94
 *
 */
public class ReadCityBonusXml {
	private String path;

	public ReadCityBonusXml(){
		path="src/main/resources/xmlFiles/";//file location
	}

	/**
	 * 
	 * @param endPath, the file name
	 * @return an array with the bonus find in the xml file
	 * @throws XmlException
	 */
	public String[] typeXml(String endPath) throws XmlException{

		try {

			File inputFile = new File(path+endPath);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList bonus=doc.getElementsByTagName("bonus");//lista dei nodi che contengono "bonus"
			String[] bonuses=new String[bonus.getLength()];//array che contiene i bonus trovati
			
			for(int i=0; i<bonus.getLength(); i++){//ciclo che scorre i bonus trovati
				bonuses[i]=bonus.item(i).getTextContent();
			}

			return bonuses;
		}catch (IOException | ParserConfigurationException | SAXException  e) {
			throw new XmlException(e);
		}
	}
}
