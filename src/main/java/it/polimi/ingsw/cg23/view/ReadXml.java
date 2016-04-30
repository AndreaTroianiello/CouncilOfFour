package it.polimi.ingsw.cg23.view;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReadXml {//http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
	//http://www.mrwebmaster.it/java/xml-java-esempio-parsing-jaxp_7488_2.html

	/*
	 * legge il file xml
	 */
	public String ReadFileXml(){
		try {	
			File inputFile = new File("src/main/java/it/polimi/ingsw/cg23/view/ConfigurazionePartita.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			Node rootnode = doc.getFirstChild();//recupera il primo nodo dell'xml (map)

			int citynum=15;//PROVVISORIO -> DA RECUPERARE DA XML
			System.out.println(rootnode.getNodeName());
			String[][] city=new String[citynum][4];//si salvano le infromazioni delle citta'
			/////////////OK
			
			//Node zone = rootnode.getChildNodes().item(1);//secondo nodo xml (zone)
			//Node zonename= zone.getChildNodes().item(1);//attributo name di costa
			//Node cities= zone.getChildNodes().item(3);//quarto nodo xml (cities)
			
			NodeList lista=doc.getElementsByTagName("city");
			
			
			
			// Iteriamo per ogni nodo presente nella lista dei nodi della radice
			for (int i = 0; i < lista.getLength(); i++) {
				Node actual=lista.item(i);
				Element ele=(Element) actual;
				
				//System.out.println(ele.getElementsByTagName("name").item(0).getTextContent());
				
				city[i][0]=ele.getElementsByTagName("name").item(0).getTextContent();
				
			}
				// Sapendo che il primo nodo è il nodo libro procediamo iterando nei suoi nodi figli
				
				
				//Se il nodo è un Elemento allora ne stampiamo il nome, il testo contenuto e gli attributi se presenti
				/*for (int j = 0; j < zone.getChildNodes().getLength(); j++) {
					Node cities = zone.getChildNodes().item(j);
					for(int m=0; m<cities.getChildNodes().getLength(); m++){
						Node citty = cities.getChildNodes().item(m);
					
					if (element.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println("Nome dell'elemento: " + element.getNodeName() + " - Valore del testo = " + element.getTextContent());
						if (element.hasAttributes()) {
							for (int k = 0; k < element.getAttributes().getLength(); k++) {
								Node attribute = element.getAttributes().item(k);
								System.out.println("\tNome dell'attributo: " + attribute.getNodeName() + " - Valore del testo = " + attribute.getTextContent());
							}
						}
					}
					}
				}*/
			
			
			for(int i=0;i<city.length;i++){
				for(int k=0;k<4;k++){
					System.out.print(city[i][k]+" ");
				}
				System.out.println("\n");
			}
			return "ok";
		} catch (Exception e) {
			// e.printStackTrace();
			return "errore";
		}

	}
}
