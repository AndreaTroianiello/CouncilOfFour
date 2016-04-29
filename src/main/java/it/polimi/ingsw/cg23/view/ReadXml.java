package it.polimi.ingsw.cg23.view;

import java.io.File;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.omg.CORBA.IdentifierHelper;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXml {
/*
 * legge il file xml
 */
	public String ReadFile(){
		try {
		/*	DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			Document document = builder.parse(new File("ConfigurazionePartita.xml"));
 
			NodeList persone = document.getElementsByTagName("persona");
			Node nodo = persone.item(i);
			Element persona = (Element)nodo;
			String nome = persona.getElementsByTagName("nome").item(0).getFirstChild().getNodeValue();
			String cognome = persona.getElementsByTagName("cognome").item(0).getFirstChild().getNodeValue();
			String telefono = persona.getElementsByTagName("telefono").item(0).getFirstChild().getNodeValue();
			//System.out.println("Totale persone: " + persone.getLength());
 
			/*for(int i=0; i<persone.getLength(); i++) {
				
 
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					
 
					
 
					System.out.println("Nome: " + nome);
					System.out.println("Cognome: " + cognome);
					System.out.println("Telefono: " + telefono);
				}*/
			return null;
		} catch(Exception e) {
			return null;
		}
	}
}
