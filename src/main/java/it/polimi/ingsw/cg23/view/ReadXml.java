package it.polimi.ingsw.cg23.view;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * classe che si occupa della lettura del file xml 
 * @author viga94
 *
 */
public class ReadXml {//http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
	//http://www.mrwebmaster.it/java/xml-java-esempio-parsing-jaxp_7488_2.html

	/**
	 * legge il file xml
	 * @return bidimensional array with city info, null if there is some problem
	 */
	public String[][] ReadFileXml(){
		try {	
			File inputFile = new File("src/main/java/it/polimi/ingsw/cg23/view/ConfigurazionePartita.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file

			Node rootnode = doc.getFirstChild();//recupera il primo nodo dell'xml (map)
			Node zone = rootnode.getChildNodes().item(1);//primo elemento dei figli di map = secondo nodo xml (zone)
			Node cities= zone.getChildNodes().item(3);//terzo elemento dei filgi di zone = quarto nodo xml (cities)
			Node citty=cities.getChildNodes().item(1);// primo elemento dei figli di cities = qunto nodo xml (city)
			
			int cityNodeNumber=(citty.getChildNodes().getLength()-1)/2; //numero di nodi di city
						
			NodeList citylist=doc.getElementsByTagName("city");//lista dei nodi che contengono "city"

			int citynum=citylist.getLength();//numero di citta'
			String[][] city=new String[citynum][cityNodeNumber];//array per salvare le infromazioni delle citta'

			for (int i=0; i<citylist.getLength(); i++){//scorre le citta' presenti nel file xml
				Node actualNode=citylist.item(i);//nodo attualmente in uso
				Element actualElement=(Element) actualNode;//cast del nodo in elemento per poterlo usare

				city[i][0]=actualElement.getElementsByTagName("name").item(0).getTextContent();//recupera il nome della città
				city[i][1]=actualElement.getElementsByTagName("color").item(0).getTextContent();//recupera il colore della città
				//city[i][2]=actualElement.getElementsByTagName("link").item(0).getTextContent();//recupera i link della città
				city[i][3]=actualElement.getElementsByTagName("Id").item(0).getTextContent();//recupera l'id della città
				city[i][4]=actualElement.getElementsByTagName("bonus").item(0).getTextContent();//recupera i bonus della città
			}
			return city;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * calcola il numero di citta' nel file xml
	 * @return the number of cities in the xml file, 0 if no city or error
	 */
	public int cityNumber(){
		try {	
			File inputFile = new File("src/main/java/it/polimi/ingsw/cg23/view/ConfigurazionePartita.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file
			NodeList citylist=doc.getElementsByTagName("city");//lista dei nodi che contengono "city"
			return citylist.getLength();//numero di citta'
		}
		catch(Exception e) {
			// e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * calcola il numero di nodi (attributi) di citta'
	 * @return the number of city nodes (attributes), 0 if no city or error
	 */
	public int cityNodeNumber(){
		try {	
			File inputFile = new File("src/main/java/it/polimi/ingsw/cg23/view/ConfigurazionePartita.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file
			Node rootnode = doc.getFirstChild();//recupera il primo nodo dell'xml (map)
			Node zone = rootnode.getChildNodes().item(1);//primo elemento dei figli di map = secondo nodo xml (zone)
			Node cities= zone.getChildNodes().item(3);//terzo elemento dei filgi di zone = quarto nodo xml (cities)
			Node citty=cities.getChildNodes().item(1);// primo elemento dei figli di cities = qunto nodo xml (city)
			
			return (citty.getChildNodes().getLength()-1)/2; //numero di nodi di city
		}
		catch(Exception e) {
			// e.printStackTrace();
			return 0;
		}
	}
}
