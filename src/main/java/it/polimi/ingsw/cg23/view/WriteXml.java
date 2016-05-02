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

	/**
	 * write the xml file
	 * @param bidimensional array with the cityInfo
	 * @return if array columns is less then 6 or there are some problems
	 */
	public String writeXmlFile(String[][] cityInfo){
		try {
			if(cityInfo[0].length<6) 
				return "Errore, le colonne sono meno di 6"; //nel caso venga passato un array con meno di 6 colonne termina
			int regionNumber=regionNumber(cityInfo);//numero di regioni
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element rootElement = doc.createElement("map");// root element
			doc.appendChild(rootElement);//elemento inserito nel file

			for(int j=0; j<regionNumber; j++){//ciclo che scorre le zone
				Element zone = doc.createElement("zone");//creazione tag regioni
				rootElement.appendChild(zone);//elemento inserito nel file come figlio di map
				int ii=0;
				String regione=null;
				if(j==0){
					regione=cityInfo[0][5];//nome della prima regione
					ii=0;//le citta' di costa iniziano da 0 nell'array
				}
				if(j==1){
					regione=cityInfo[cityInfo.length/regionNumber][5];//nome della seconda regione
					ii=cityInfo.length/regionNumber;//le citta' di collina iniziano da '5' nell'array
				}
				if(j==2){
					regione=cityInfo[cityInfo.length/regionNumber*2][5];//nome della terza regione
					ii=cityInfo.length/regionNumber*2;//le citta' di montagna iniziano da '10' nell'array
				}

				Element namez=doc.createElement("namez"); //creazione tag nome della regione
				zone.appendChild(namez);//elemento inserito nel file come figlio di zone
				namez.setTextContent(regione);//settato il nome della regione

				Element cities=doc.createElement("cities");//creazione tag cities
				zone.appendChild(cities);//elemento inserito nel file come figlio di zone

				doc=ezpansione(doc, cities, cityInfo, ii);
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
			return "";
		} catch (Exception e) {
			return e.toString();
		}
	}

	/**
	 * aggiunta questa funzione che rende piu' semplice la funzione precedente (troppo difficile per sonar come era prima)
	 * @param doc, the document to write on the xml
	 * @param cities, the elmenti who contains the tag cities
	 * @param cityInfo, the array with the city info
	 * @param ii 
	 * @return the document to write on the xml
	 */
	public Document ezpansione(Document doc, Element cities, String[][] cityInfo, int io){

		int cityNumber=cityInfo.length;//numero di citta'
		int regionNumber=regionNumber(cityInfo);//numero di regioni
		int ii=io;
		for(int i=0; i<cityNumber/regionNumber; i++,ii++){//ciclo che scorre le citta' di una regione
			Element city=doc.createElement("city");//creazione tag city
			cities.appendChild(city);//elemento inserito nel file come figlio di cities

			Element id=doc.createElement("Id");////creazione tag Id
			city.appendChild(id);//elemento inserito nel file come figlio di city
			id.setTextContent(cityInfo[ii][3]);//settato l'id della citta'

			Element name=doc.createElement("name");//creazione tag name della citta'
			city.appendChild(name);//elemento inserito nel file come figlio di city
			name.setTextContent(cityInfo[ii][0]);//settato il nome della citta'

			Element link=doc.createElement("link");//creazione tag link
			city.appendChild(link);//elemento inserito nel file come figlio di city

			for(int k=0; k<cityInfo[ii][2].length(); k++){//ciclo che scorre tutti gli id delle citta' vicine
				Element idl=doc.createElement("Id");//creazione tag id di link
				link.appendChild(idl);//elemento inserito nel file come figlio di link
				idl.setTextContent(cityInfo[ii][2].substring(k, k+1));//settato l'id della citta' collegata
			}

			Element color=doc.createElement("color");//creazione tag color
			city.appendChild(color);//elemento inserito nel file come figlio di city
			color.setTextContent(cityInfo[ii][1]);//settato il colore della citta'

			Element bonus=doc.createElement("bonus");//creazione tag bonus
			city.appendChild(bonus);//elemento inserito nel file come figlio di city
			bonus.setTextContent(cityInfo[ii][4]);//settato il bonus della citta'
		}
		return doc;
	}

	/**
	 * calcola il numero di regioni
	 * @return the number of regions
	 */
	public int regionNumber(String[][] cityInfo){
		int n=0;
		for(int i=0; i<cityInfo.length; i++){
			int k;//usata per evitare di eccedere i limiti dell'array
			if(i>0)
				k=i-1;
			else 
				k=i;
			if(cityInfo[i][5]!=cityInfo[k][5])//conta le variazioni delle regioni
				n++;
		}
		return n+1;//aggiunge 1 per contare la prima regione
	}
}
