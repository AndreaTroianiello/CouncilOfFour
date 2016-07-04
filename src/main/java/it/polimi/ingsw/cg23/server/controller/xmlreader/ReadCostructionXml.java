package it.polimi.ingsw.cg23.server.controller.xmlreader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

/**
 * the class read the xml who contains the construction cards
 *
 */
public class ReadCostructionXml {
	private String path = "src/main/resources/xmlFiles/";// file location

	/**
	 * read the xml file
	 * 
	 * @param endPath,
	 *            the name of file (with the extension ".xml")
	 * @return two-dimensional array with cotructionCard info, null array if
	 *         there is some problems
	 * @throws XmlException
	 */
	public String[][] readCardXml(String endPath) throws XmlException {
		String[][] card;

		try {
			final int cardNumber = cardNumber(endPath);// numero di carte
														// costruzione nell'xml
			final int cardNode = cardNodeNumber(endPath);// numero di nodi figli
															// di card nell'xml
															// +1
			card = new String[cardNumber][cardNode];// array che contiene le
													// info delle carte
													// costruzione

			File inputFile = new File(path + endPath);// creato nuovo file
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();// creata
																					// la
																					// factory
																					// per
																					// processare
																					// il
																					// flusso
																					// di
																					// dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();// inizializzato
																		// un
																		// nuovo
																		// documento
			Document doc = dBuilder.parse(inputFile);// carica il documento dal
														// file

			NodeList cards = doc.getElementsByTagName("card");// lista dei nodi
																// che
																// contengono
																// "card"
			NodeList zoneName = doc.getElementsByTagName("name");// lista dei
																	// nodi che
																	// contengono
																	// "name"

			for (int i = 0; i < cardNumber; i++) {// scorre le carte costruzione
													// presenti nell'xml
				Node actualZoneNode = zoneName.item(i / (cardNumber / zoneName.getLength()));// nodo
																								// regione
				card[i][0] = actualZoneNode.getTextContent();// recupera il tipo
																// di regione
																// (costa,
																// collina,
																// montagna)

				Node actualNode = cards.item(i);// nodo attualmente in uso
				Element actualElement = (Element) actualNode;// cast del nodo in
																// elemento per
																// poterlo usare

				String nome = actualElement.getElementsByTagName("city").item(0).getTextContent();// recupera
																									// gli
																									// id
																									// delle
																									// citta'
																									// delle
																									// carte
																									// costruzione

				int idnum = actualElement.getElementsByTagName("city").item(0).getChildNodes().getLength();// numero
																											// dei
																											// tag
																											// filgi
																											// di
																											// card
				idnum = (idnum - 1) / 2;// numero delle citta' su ciascuna carta
										// costruzione

				card[i][1] = idConversion(nome, idnum);// scrivo meglio gli id
														// della citta'
				card[i][2] = actualElement.getElementsByTagName("bonus").item(0).getTextContent();// recupera
																									// i
																									// bonus
																									// delle
																									// carte
																									// costruzione
			}

			return card;

		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new XmlException(e);
		}
	}

	/**
	 * trasforms the string that contains the cities in one more readable
	 * 
	 * @param nome
	 *            the string to execute the substring (the string contain the
	 *            city id)
	 * @param idnum
	 *            the number of the city link
	 * @return a string with the city link easily to know
	 */
	private String idConversion(String nome, int idnum) {
		String idConcat = "";// id delle citta' vicine

		for (int i = 1; i <= idnum; i++) {
			int k = 7 * i;// nella stringa originaria gli id della citta' vicine
							// sono a distanza di 6
			idConcat = idConcat.concat(nome.substring(k - 1, k));
		}

		return idConcat;// ritorna la stringa della città
	}

	/**
	 * count the card's number in the xml file
	 * 
	 * @return the number of cards in the xml file
	 * @throws XmlException
	 */
	private int cardNumber(String endPath) throws XmlException {
		try {
			File inputFile = new File(path + endPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();// creata
																					// la
																					// factory
																					// per
																					// processare
																					// il
																					// flusso
																					// di
																					// dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();// inizializzato
																		// un
																		// nuovo
																		// documento
			Document doc = dBuilder.parse(inputFile);// carica il documento dal
														// file

			NodeList citylist = doc.getElementsByTagName("card");// lista dei
																	// nodi che
																	// contengono
																	// "city"

			return citylist.getLength();// numero di citta'

		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new XmlException(e);
		}
	}

	/**
	 * count the card's nodes' number
	 * 
	 * @return the number of card nodes, 0 if no card or error
	 * @throws XmlException
	 */
	private int cardNodeNumber(String endPath) throws XmlException {
		try {
			File inputFile = new File(path + endPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();// creata
																					// la
																					// factory
																					// per
																					// processare
																					// il
																					// flusso
																					// di
																					// dati
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();// inizializzato
																		// un
																		// nuovo
																		// documento
			Document doc = dBuilder.parse(inputFile);// carica il documento dal
														// file

			Node rootnode = doc.getFirstChild();// recupera il primo nodo
												// dell'xml (costruction)

			Node region = rootnode.getChildNodes().item(1);// primo elemento dei
															// figli di
															// costruction =
															// secondo nodo xml
															// (region)
			Node cards = region.getChildNodes().item(3);// terzo elemento dei
														// figli di region =
														// quarto nodo xml
														// (cards)
			Node card = cards.getChildNodes().item(1);

			return (card.getChildNodes().getLength() - 1) / 2 + 1;// più 1 per
																	// la
																	// regione

		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new XmlException(e);
		}
	}
}
