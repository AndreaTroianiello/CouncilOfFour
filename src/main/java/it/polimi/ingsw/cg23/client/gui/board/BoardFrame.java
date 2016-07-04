package it.polimi.ingsw.cg23.client.gui.board;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.client.gui.board.eastpanel.EastPanel;
import it.polimi.ingsw.cg23.client.gui.board.mappanel.MapPanel;
import it.polimi.ingsw.cg23.client.gui.board.marketplace.MarketPanel;
import it.polimi.ingsw.cg23.client.gui.board.rank.RankPanel;
import it.polimi.ingsw.cg23.client.gui.board.southpanel.SouthPanel;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.RankChange;

/**
 * create the map
 * 
 * @author viga94_
 *
 */
public class BoardFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328028752338623444L;
	private JPanel contentPane;
	private JTextArea loggerArea;
	private JTextField write;

	private EastPanel eastPanel;
	private MapPanel mapPanel;
	private SouthPanel southPanel;
	private MarketPanel marketPanel;
	private RankPanel rankPanel;
	private transient ControllerGUI controller;

	/**
	 * Create the frame.
	 * 
	 * @param controller
	 */
	public BoardFrame(ControllerGUI controller) {
		this.controller = controller;
		loggerArea = new JTextArea();
		write = new JTextField();
		// pannel
		eastPanel = new EastPanel(loggerArea, write, controller);
		mapPanel = new MapPanel(loggerArea, controller);
		southPanel = new SouthPanel(controller, loggerArea);
		setTitle("Board");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 100);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		grid();

	}

	/**
	 * create the main panel (map + south + logger)
	 */
	private void grid() {
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints();

		// ----------text area (logger)----------
		lim.gridx = 3;// posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 0;// occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight = 3;// grandezza del riquadro
		lim.gridwidth = 1;
		lim.fill = GridBagConstraints.VERTICAL;// occupazione dello spazio
												// libero della griglia
												// (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;// posizione componenti nei
												// riquadri
		layout.setConstraints(eastPanel, lim); // Associazione
		contentPane.add(eastPanel); // Inserimento

		// ----------pannello nord (mappa)----------
		lim.gridx = 0;// posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 0;// occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight = 2;// grandezza del riquadro
		lim.gridwidth = 3;
		lim.fill = GridBagConstraints.VERTICAL;// occupazione dello spazio
												// libero della griglia
												// (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;// posizione componenti nei
												// riquadri
		layout.setConstraints(mapPanel, lim);
		contentPane.add(mapPanel);

		// ----------pannello nord (market)----------
		marketPanel = new MarketPanel(controller, loggerArea);
		marketPanel.setBackground(new Color(151, 111, 51));
		lim.gridx = 0;// posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;// occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight = 2;// grandezza del riquadro
		lim.gridwidth = 3;
		lim.fill = GridBagConstraints.BOTH;// occupazione dello spazio libero
											// della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;// posizione componenti nei
												// riquadri
		layout.setConstraints(marketPanel, lim);
		contentPane.add(marketPanel);
		marketPanel.setVisible(false);

		// ----------pannello nord (rank)----------
		rankPanel = new RankPanel();
		rankPanel.setBackground(new Color(151, 111, 51));
		lim.gridx = 0;// posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;// occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight = 2;// grandezza del riquadro
		lim.gridwidth = 3;
		lim.fill = GridBagConstraints.BOTH;// occupazione dello spazio libero
											// della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;// posizione componenti nei
												// riquadri
		layout.setConstraints(rankPanel, lim);
		contentPane.add(rankPanel);
		rankPanel.setVisible(false);

		// ----------pannello sud (informazioni)----------
		JScrollPane scroll = new JScrollPane(southPanel);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		southPanel.setName("south panel");
		lim.gridx = 0;// posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;// occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight = 1;// grandezza del riquadro
		lim.gridwidth = 3;
		lim.fill = GridBagConstraints.BOTH;// occupazione dello spazio libero
											// della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;// posizione componenti nei
												// riquadri
		layout.setConstraints(scroll, lim); // Associazione
		contentPane.add(scroll); // Inserimento

		pack();// necessario
	}

	/**
	 * Notifies the user when receives a change.
	 * 
	 * @param change
	 *            The change received.
	 */
	public void updateInfo(Change change) {
		if (change instanceof RankChange) {
			rankPanel.fillRankTable(((RankChange) change).getRank());
			rankPanel.setVisible(true);
			mapPanel.setVisible(false);
			marketPanel.setVisible(false);
			JOptionPane.showMessageDialog(null, "Game is finished.", "INFO", JOptionPane.INFORMATION_MESSAGE);
		} else
			loggerArea.append("\n" + change.toString());
	}

	/**
	 * update the gui
	 */
	public void update() {
		southPanel.update();
		boolean value = controller.getModel().getBoard().getStatus().getStatus().contains("MARKET");
		marketPanel.setVisible(value);
		mapPanel.setVisible(!value);
		marketPanel.fillTable();
		mapPanel.update();
		this.revalidate();
		this.repaint();
	}
}
