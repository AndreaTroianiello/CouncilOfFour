package it.polimi.ingsw.cg23.view;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadCostructionXml {
	String path="src/main/resources/";//file location
	/**
	 * legge il file xml
	 * @param endpath, the name of file (with the extension ".xml")
	 * @return bidimensional array with cotructionCard info, null array if there is some problems
	 */
	public String[][] readFileXml(String endPath){
		String[][]card=null;
			try{
				File inputFile = new File(path);//creato nuovo file
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
				Document doc = dBuilder.parse(inputFile);//carica il documento dal file
				
			return card;
		} catch(Exception e) {//se ci sono dei problemi ritorna l'array null
			for(int i=0;i<card.length;i++){//cicli per annullare l'array (richiesto da sonar)
				for(int k=0; k<card[0].length; k++){
					card[i][k]=null;
				}
			}
			return card;
		}
	}

}
