package it.polimi.ingsw.cg23.view;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * class to read the nobility track from xml
 *
 */
public class ReadNobilityTrackXml {
	String path="src/main/resources/";//file location

	/**
	 * read the lenght of the nobility track in the xml file
	 * @param endPath, the name of the xml file with .xml
	 * @return an int with the lenght of the nobility track
	 */
	public int nobilityTrackLenght(String endPath){

		try {
			File inputFile = new File(path+endPath);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			NodeList lenght=doc.getElementsByTagName("lenght");//lista dei nodi che contengono "lenght"
			return Integer.parseInt(lenght.item(0).getTextContent());//ritorno la lunghezza del nobility track
		} 
		catch (Exception e) {
			return 0;
		}
	}

	/**
	 * create a bidimensional array with the nobility track info
	 * @param endPath, the name of the xml file with .xml
	 * @return a bidimensional array with the nobility track info
	 */
	public String[][] nobilityTrackBonus(String endPath){
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
			
		}catch (Exception e) {//se ci sono dei problemi ritorna l'array null
			return new String[0][0];
		}
	}

}
