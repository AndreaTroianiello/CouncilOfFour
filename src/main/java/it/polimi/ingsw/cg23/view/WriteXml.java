package it.polimi.ingsw.cg23.view;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * the class write the info in a xml file
 *
 */
public class WriteXml {
	String path="src/main/java/it/polimi/ingsw/cg23/view/SalvataggioPartita.xml";//percorso del file
	CliInterface interfaccia=new CliInterface();
	int regionNumber=interfaccia.regionNumber();//numero di regioni

	/**
	 * write the xml file
	 * @param bidimensional array with the cityInfo
	 */
	public void writeXmlFile(String[][] cityInfo){
		try {
			int cityNumber=cityInfo.length;//numero di citta'
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element rootElement = doc.createElement("map");// root element
			doc.appendChild(rootElement);//elemento inserito nel file

			for(int j=0; j<regionNumber; j++){//ciclo che scorre le zone
				Element zone = doc.createElement("zone");//creazione tag regioni
				rootElement.appendChild(zone);//elemento inserito nel file come figlio di map
				
				String regione=null;
				if(j==0)
					regione=cityInfo[0][5];//nome della prima regione
				if(j==1)
					regione=cityInfo[cityInfo.length/regionNumber][5];//nome della seconda regione
				if(j==2)
					regione=cityInfo[cityInfo.length/regionNumber*2][5];//nome della terza regione
				
				Element namez=doc.createElement("namez"); //creazione tag nome della regione
				zone.appendChild(namez);//elemento inserito nel file come figlio di zone
				namez.setTextContent(regione);//settato il nome della regione

				Element cities=doc.createElement("cities");//creazione tag cities
				zone.appendChild(cities);//elemento inserito nel file come figlio di zone

				for(int i=0; i<cityNumber/regionNumber; i++){//ciclo che scorre le citta' di una regione
					Element city=doc.createElement("city");//creazione tag city
					cities.appendChild(city);//elemento inserito nel file come figlio di cities

					Element id=doc.createElement("Id");////creazione tag Id
					city.appendChild(id);//elemento inserito nel file come figlio di city
					id.setTextContent(cityInfo[i][3]);//settato l'id della citta'
					
					Element name=doc.createElement("name");//creazione tag name della citta'
					city.appendChild(name);//elemento inserito nel file come figlio di city
					name.setTextContent(cityInfo[i][0]);//settato il nome della citta'
					
					Element link=doc.createElement("link");//creazione tag link
					city.appendChild(link);//elemento inserito nel file come figlio di city
					Element idl1=doc.createElement("Id");//creazione tag id di link
					link.appendChild(idl1);
					//idl1.setTextContent(cityInfo[i][2]);
					Element idl2=doc.createElement("Id");
					link.appendChild(idl2);
					//idl2.setTextContent(textContent);
					
					Element color=doc.createElement("color");
					city.appendChild(color);
					color.setTextContent(cityInfo[i][1]);
					
					Element bonus=doc.createElement("bonus");
					city.appendChild(bonus);
					bonus.setTextContent(cityInfo[i][4]);
				}
			}
			// write the content into xml file
			TransformerFactory transformerFactory =	TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");//modalita' di encodig
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");//manda a capo
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");//indenta i tag
			DOMSource source = new DOMSource(doc);
			File xmlFile=new File(path);//creo un nuovo file nel percorso
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
