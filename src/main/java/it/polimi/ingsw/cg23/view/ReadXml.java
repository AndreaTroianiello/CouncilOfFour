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
	public String ReadFileXml(String nodo, String attributo){
		try {	
			File inputFile = new File("src/main/java/it/polimi/ingsw/cg23/view/ConfigurazionePartita.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file
			Node rootnode = doc.getFirstChild();//recupera il primo nodo dell'xml

			/////////////OK
			if(rootnode.getNodeName()==nodo){//il nodo di root e' quello cercato
				return rootnode.getTextContent();
			}else{
				for(int i=0; i<rootnode.getChildNodes().getLength(); i++){//cerco i nodi figli del nodo root (map)
					Node zonenode = rootnode.getChildNodes().item(i);//il secondo nodo di xml e' zone
					if(zonenode.getNodeName()==nodo){//il secondo nodo è quello cercato
						if(zonenode.hasAttributes()==true&&zonenode.getAttributes().toString()==attributo)
						return rootnode.getTextContent();
					}else{
						for(int j=0; i<zonenode.getChildNodes().getLength(); j++){//cerco i nodi figli del nodo zone
							Node cities = zonenode.getChildNodes().item(j);//il terzo nodo di xml e' cities
							if(cities.getNodeName()==nodo){//il terzo nodo è quello cercato
								return cities.getTextContent();
							}else{
								for(int k=0; i<cities.getChildNodes().getLength(); k++){//cerco i nodi figlio del nodo cities
									Node city = cities.getChildNodes().item(k);//il quarto nodo di xml e' city
									/*if(zonenode.getNodeName()==nodo){//il quarto nodo è quello cercato
										//System.out.println("okno");
										return cities.getTextContent();
										
									}*/
								}
							}
						}
					}
				}
			}
			//System.out.println(rootnode.getNodeName());
			// Iteriamo per ogni nodo presente nella lista dei nodi della radice
			/*for (int i = 0; i < rootnode.getChildNodes().getLength(); i++) {

				// Sapendo che il primo nodo è il nodo zona procediamo iterando nei suoi nodi figli
				Node zonenode = rootnode.getChildNodes().item(i);
				String zoneAttribute=null;
				/*if(zonenode.hasAttributes()){
		            	 zoneAttribute=zonenode.getTextContent();
		             }
				System.out.println("1"+zonenode.getNodeName()+"1");
				for (int j = 0; j < zonenode.getChildNodes().getLength(); j++) {

					Node element = zonenode.getChildNodes().item(j);
					/* if (element.getNodeType() == Node.ELEMENT_NODE) {
		                     System.out.println("Nome: " + element.getNodeName() + " - Valore: " + element.getTextContent());
		                   /*  if (element.hasAttributes()) {
		                         for (int k = 0; k < element.getAttributes().getLength(); k++) {
		                             Node attribute = element.getAttributes().item(k);
		                             System.out.println("\tNome dell'attributo: " + attribute.getNodeName() + " - Valore del testo = " + attribute.getTextContent());
		                         }
		                     }
					//}
				}
			}*/

			return "ok";
		} catch (Exception e) {
			// e.printStackTrace();
			return "errore";
		}

	}
}
