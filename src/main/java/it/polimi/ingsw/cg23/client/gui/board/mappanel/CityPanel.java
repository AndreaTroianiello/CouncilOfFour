package it.polimi.ingsw.cg23.client.gui.board.mappanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.utility.MapSetting;

/**
 * create the city panel
 * 
 * @author viga94
 *
 */
public class CityPanel {

	private Logger logger;
	private MapSetting ms;
	private final double lung;
	private JTextArea loggerArea;
	private ControllerGUI controller;

	/**
	 * @param loggerArea,
	 *            the area area to read on
	 * @param controller,
	 *            the controller
	 */
	public CityPanel(JTextArea loggerArea, ControllerGUI controller) {
		lung = Toolkit.getDefaultToolkit().getScreenSize().width - 10.0;// larghezza
																		// schermo
																		// -10

		this.loggerArea = loggerArea;
		this.controller = controller;
		this.ms = new MapSetting();

		// configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");// carica
																				// la
																				// configurazione
																				// del
																				// logger
	}

	/**
	 * create the city panel
	 * 
	 * @param k,
	 *            the king
	 * @param c,
	 *            the city
	 * @return the city panel
	 */
	public JPanel createCity(City c, King k) {
		JPanel panel = new JPanel();// pannello citta'
		GridBagLayout layout = new GridBagLayout();// nuovo layout
		panel.setLayout(layout);// applicazione del layout al pannello

		GridBagConstraints lim = new GridBagConstraints();// settaggio del
															// layout
		lim.fill = GridBagConstraints.NONE;// grandezza componenti nei riquadri
											// (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;// posizione componenti nei
												// riquadri

		int p = 0;// posizione componenti nella griglia in orizzontale

		// ----------nome citta'----------
		JLabel nameLabel = new JLabel(Character.toString(c.getId()));// label
																		// nome
																		// della
																		// citta'
		double font = ((double) 2 / 100) * lung;// grandezza font lettera della
												// citta'
		nameLabel.setFont(new Font("Calibre", Font.ITALIC, (int) font));// applicazione
																		// del
																		// font
																		// alla
																		// label
		nameLabel.setForeground(new Color(255, 255, 255));// colore del font
		lim.gridx = p;// posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;// occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight = 2;// grandezza del riquadro
		lim.gridwidth = 1;
		layout.setConstraints(nameLabel, lim);// applicazione del layout alla
												// label
		panel.add(nameLabel);// aggiunta della label al pannello
		p++;// incremento posizione

		// ----------king label----------
		if (c.equals(k.getCity())) {// controllo se e' la citta' del re
			BufferedImage imgKing = getImg("king");// lettura immagine
			JLabel kingLabel = new JLabel(new ImageIcon(imgKing));// creazione
																	// di una
																	// nuova
																	// label con
																	// il
																	// simbolo
																	// del re
			lim.gridx = p;// posizione componenti nella griglia
			lim.gridy = 0;
			lim.weightx = 1;// occupa tutto lo spazio all'interno del riquadro
			lim.weighty = 1;
			lim.gridheight = 1;// grandezza del riquadro
			lim.gridwidth = 1;

			if (c.getToken().isEmpty()) // se la citta' non ha bonus incremento
										// la posizione
				p++;

			layout.setConstraints(kingLabel, lim);// applicazione del layout
													// alla label
			panel.add(kingLabel);// aggiunta della label al panel
		}

		// ----------bonus label----------
		if (!c.getToken().isEmpty()) {// controllo se la citta' ha il bonus
			BufferedImage imgCityBonus = getImg("cityBonus/" + ms.cityBonus(c));// lettura
																				// immagine
			double width = (2.0 / 50) * lung;// larghezza immagine
			double height = ((double) imgCityBonus.getHeight() / imgCityBonus.getWidth()) * width;// altezza
																									// immagine
			Image myim = imgCityBonus.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);// ridimensionamento
																										// immagine

			JLabel bonusCityLabel = new JLabel(new ImageIcon(myim));// creazione
																	// della
																	// label con
																	// il bonus
			lim.gridx = p;// posizione componenti nella griglia
			lim.gridy = 1;
			lim.weightx = 1;// occupa tutto lo spazio all'interno del riquadro
			lim.weighty = 1;
			lim.gridheight = 1;// grandezza del riquadro
			lim.gridwidth = 1;
			p++;// inccremento della posizione nella griglia

			layout.setConstraints(bonusCityLabel, lim);// applicazioen layout
														// alla label
			panel.add(bonusCityLabel);// aggiunta della label al pannello
		}

		// ----------immagine citta'----------
		BufferedImage img = getImg("city/" + c.getType() + "City");// lettura
																	// immagine
		double width = ((double) 3 / 50) * lung;// larghezza immagine citta'
		double height = ((double) img.getHeight() / img.getWidth()) * width;// altezza
																			// immagine
																			// citta0
		Image myim = img.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);// ridimensionamento
																							// immagine

		JLabel label = new JLabel(new ImageIcon(myim));// creazione etichetta
														// con l'immagine della
														// citta'
		lim.gridx = p;// posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;// occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight = 2;// grandezza del riquadro
		lim.gridwidth = 1;
		layout.setConstraints(label, lim);// applicazione del layout alla label
		panel.add(label);// aggiunta della label al pannello

		listener(panel, c, k);// azioni per stampare sulla logger area le
								// informazioni della citta'

		return panel;
	}

	/**
	 * add the listener at the city panel
	 * 
	 * @param panel,
	 *            the panel to add the listener
	 * @param c,
	 *            the actual city in the panel
	 * @param k,
	 *            the king
	 */
	private void listener(JPanel panel, City c, King k) {
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				/** empty, not erasable */
			}

			@Override
			public void mouseExited(MouseEvent e) {
				/** empty, not erasable */
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {// bottone sinistro
					controller.getSelectedElements().setCity(c);
					loggerArea.append("\n Element selected (City:" + c.getName() + ").");
				}
				if (SwingUtilities.isRightMouseButton(e)) // bottone destro
					writeArea(c, k);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				/** empty, not erasable */
			}

			@Override
			public void mousePressed(MouseEvent e) {
				/** empty, not erasable */
			}
		});
	}

	/**
	 * print the city informaztion on the logger area
	 * 
	 * @param c,
	 *            the actual city
	 * @param k,
	 *            the king
	 */
	private void writeArea(City c, King k) {

		loggerArea.append("\nCitta' " + c.getName());
		loggerArea.append("\n  " + ms.getNeighbourID(c));
		loggerArea.append("\n  Regione: " + c.getRegion().getName());

		if (c.equals(k.getCity()))
			loggerArea.append("\n  King city");
		if (!c.getToken().isEmpty())
			loggerArea.append("\n  Bonus: " + ms.cityBonus(c));

		loggerArea.append("\n  Tipo: " + c.getType());
		loggerArea.append("\n  Empori: ");

		if (c.getEmporiums().isEmpty())
			loggerArea.append("0");
		else {
			for (int i = 0; i < c.getEmporiums().size(); i++) {// aggiunge tutti
																// gli empori
																// della citta'
				if ("NaN".equals(c.getEmporiums().get(i).getPlayer().getUser()))
					loggerArea.append("");
				else
					loggerArea.append(c.getEmporiums().get(i).toString() + " ");
			}
		}
	}

	/**
	 * load the image
	 * 
	 * @param name,
	 *            the image name
	 * @return
	 */
	private BufferedImage getImg(String name) {// recupero le immagini
		BufferedImage image = null;
		String path = "src/main/resources/images/" + name + ".png";// percorso
																	// dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine della carta costruzione: " + path, e);
		}

		return image;
	}
}
