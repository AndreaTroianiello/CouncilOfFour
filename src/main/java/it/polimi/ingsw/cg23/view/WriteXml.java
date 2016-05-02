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
 * @author viga94
 *
 */
public class WriteXml {
	String path="src/main/java/it/polimi/ingsw/cg23/view/SalvataggioPartita.xml";
	CliInterface interfaccia=new CliInterface();
	int regionNumber=interfaccia.regionNumber();

	/**
	 * write the xml file
	 */
	public void writeXmlFile(String[][] cityInfo){
		try {
			int cityNumber=cityInfo.length;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element rootElement = doc.createElement("map");// root element
			doc.appendChild(rootElement);//elemnto inserito nel file

			for(int j=0; j<regionNumber; j++){//ciclo che scorre le zone
				Element zone = doc.createElement("zone");//creazione tag regioni
				rootElement.appendChild(zone);
				String regione=null;
				if(j==0)
					regione=cityInfo[0][5];
				if(j==1)
					regione=cityInfo[cityInfo.length/3][5];
				if(j==2)
					regione=cityInfo[cityInfo.length/3*2][5];
				
				Element namez=doc.createElement("namez"); //creazione tag nome della regione
				zone.appendChild(namez);
				namez.setTextContent(regione);

				Element cities=doc.createElement("cities");
				zone.appendChild(cities);

				for(int i=0; i<cityNumber/3; i++){
					Element city=doc.createElement("city");
					cities.appendChild(city);

					Element id=doc.createElement("Id");//id della citta'
					city.appendChild(id);
					id.setTextContent(cityInfo[i][3]);
					
					Element name=doc.createElement("name");//nome della citta'
					city.appendChild(name);
					name.setTextContent(cityInfo[i][0]);
					
					Element link=doc.createElement("link");
					city.appendChild(link);
					Element idl1=doc.createElement("Id");
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
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
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
