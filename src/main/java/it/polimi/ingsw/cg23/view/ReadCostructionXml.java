package it.polimi.ingsw.cg23.view;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * the class read the xml who contains the costruction cards
 *
 */
public class ReadCostructionXml {
	String path="src/main/resources/";//file location
	
	/**
	 * legge il file xml
	 * @param endpath, the name of file (with the extension ".xml")
	 * @return bidimensional array with cotructionCard info, null array if there is some problems
	 */
	public String[][] readCardXml(String endPath){
		final int cardNumber=cardNumber(endPath);//numero di carte costruzione nell'xml
		final int cardNode=cardNodeNumber(endPath);//numero di nodi figli di card nell'xml +1
		String[][]card=new String[cardNumber][cardNode];//array che contiene le info delle carte costruzione
		path+=endPath;
		try{
			File inputFile = new File(path);//creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file
			
			NodeList cards=doc.getElementsByTagName("card");//lista dei nodi che contengono "card"
			NodeList zoneName=doc.getElementsByTagName("name");//lista dei nodi che contengono "name"

			for (int i=0; i<cardNumber; i++){//scorre le carte costruzione presenti nell'xml
				Node actualZoneNode=zoneName.item(i/(cardNumber/zoneName.getLength()));//nodo regione
				card[i][0]=actualZoneNode.getTextContent();//recupera il tipo di regione (costa, collina, montagna)

				Node actualNode=cards.item(i);//nodo attualmente in uso
				Element actualElement=(Element) actualNode;//cast del nodo in elemento per poterlo usare

				String nome=actualElement.getElementsByTagName("city").item(0).getTextContent();//recupera gli id delle citta' delle carte costruzione

				int idnum=actualElement.getElementsByTagName("city").item(0).getChildNodes().getLength();//numero dei tag filgi di card
				idnum=(idnum-1)/2;//numero delle citta' su ciascuna carta costruzione

				card[i][1]=idConversion(nome,idnum);
				card[i][2]=actualElement.getElementsByTagName("bonus").item(0).getTextContent();//recupera i bonus delle carte costruzione
			}

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

	/**
	 * strasforma la stringa che contiene le città in una piu' leggibile
	 * @param nome, the string to executhe the substring (the string contain the city id)
	 * @param idnum, the number of the city link
	 * @return a string with the city link easily to know 
	 */
	public String idConversion(String nome, int idnum){
		String idConcat="";//id delle citta' vicine
		for(int i=1; i<=idnum; i++){
			int k=6*i;//nella stringa originaria gli id della citta' vicine sono a distanza di 6
			idConcat+=nome.substring(k-1, k);
		}
		return idConcat;//ritorna la stringa della città
	}

	/**
	 * calcola il numero di carte nel file xml
	 * @return the number of carts in the xml file
	 */
	public int cardNumber(String endPath){
		try {	
			File inputFile = new File(path+endPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file
			
			NodeList citylist=doc.getElementsByTagName("card");//lista dei nodi che contengono "city"
			return citylist.getLength();//numero di citta'
		}
		catch(Exception e){
			return 0;
		}
	}

	/**
	 * calcola il numero di nodi di card
	 * @return the number of card nodes, 0 if no card or error
	 */
	public int cardNodeNumber(String endPath){
		try {	
			File inputFile = new File(path+endPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creata la factory per processare il flusso di dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//inizializzato un nuovo documento
			Document doc = dBuilder.parse(inputFile);//carica il documento dal file
			
			Node rootnode = doc.getFirstChild();//recupera il primo nodo dell'xml (costruction)
			Node region = rootnode.getChildNodes().item(1);//primo elemento dei figli di costruction = secondo nodo xml (region)
			Node card= region.getChildNodes().item(3);//terzo elemento dei figli di region = quarto nodo xml (card)

			return (card.getChildNodes().getLength()-1)/2+1;
		}
		catch(Exception e) {
			return 0;
		}
	}
}
