package it.polimi.ingsw.cg23.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.gui.panel.CouncilPanel;
import it.polimi.ingsw.cg23.gui.panel.InfoActionPanel;
import it.polimi.ingsw.cg23.gui.panel.MainActionPanel;
import it.polimi.ingsw.cg23.gui.panel.SecondaryActionPanel;
import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.model.Region;

/**
 * create the map
 * @author viga94_
 *
 */
public class FrameMap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328028752338623444L;
	private JPanel contentPane;
	private JTextArea log;
	private transient Avvio s;
	private static Logger logger;

	/**
	 * Create the frame.
	 */
	public FrameMap() {
		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		//carica le informazioni sulle citta'--- PROVVISORIO (poi gia' caricate)
		s=new Avvio("RegionCity.xml");
		s.startPartita();

		grid();
	}

	private void grid(){
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------pannello nord (mappa)----------
		JButton b1=new JButton();
		b1.setText("b1");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(b1, lim);
		contentPane.add(b1);

		//----------text area (logger)----------
		log=new JTextArea(10, 10);
		log.setName("textara");
		log.setText("Benvenuti a Cof");
		Component c8 = new JScrollPane(log);
		lim.gridx = 3;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=3;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(c8, lim); //Associazione
		contentPane.add(c8); //Inserimento

		//----------pannello sud (informazioni)----------
		JPanel c4=setSouthPanel();
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(c4, lim); //Associazione
		contentPane.add(c4); //Inserimento
	}

	private JPanel setSouthPanel(){
		JPanel southPanel= new JPanel();
		GridBagLayout layout = new GridBagLayout();
		southPanel.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------carte permesso di costruzione 1----------
		JTextPane costruzione1=new JTextPane();
		costruzione1.setName("Carte costruzione costa");
		costruzione1.setEditable(false);
		costruzione1.setText(costruzione(s.getBoard().getRegions().get(0)));
		costruzione1.setBackground(new Color(234, 125, 198));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(costruzione1, lim);
		southPanel.add(costruzione1);

		//----------carte permesso di costruzione 2----------
		JTextPane costruzione2=new JTextPane();
		costruzione2.setName("Carte costruzione collina");
		costruzione2.setEditable(false);
		costruzione2.setText(costruzione(s.getBoard().getRegions().get(1)));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(costruzione2, lim);
		southPanel.add(costruzione2);

		//----------carte permesso di costruzione 3----------
		JTextPane costruzione3=new JTextPane();
		costruzione3.setBackground(new Color(123,124,234));
		costruzione3.setEditable(false);
		costruzione3.setName("Carte costruzione montagna");
		costruzione3.setText(costruzione(s.getBoard().getRegions().get(2)));
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(costruzione3, lim);
		southPanel.add(costruzione3);

		//----------balcone 1----------
		JPanel balcone1=new CouncilPanel().balcone(s.getBoard().getRegions().get(0));
		balcone1.setName("balcone costa");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(balcone1, lim);
		southPanel.add(balcone1);

		//----------balcone 2----------
		JPanel balcone2=new CouncilPanel().balcone(s.getBoard().getRegions().get(1));
		balcone2.setName("balcone collina");
		balcone2.setBackground(new Color(158, 219, 147));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(balcone2, lim);
		southPanel.add(balcone2);

		//----------balcone 3----------
		JPanel balcone3=new CouncilPanel().balcone(s.getBoard().getRegions().get(2));
		balcone3.setName("balcone montagna");
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(balcone3, lim);
		southPanel.add(balcone3);

		//----------------nobility track------------
		JTextPane l4=new JTextPane();
		l4.setEditable(false);
		l4.setName("Nobility panel");
		l4.setText("nobility track");
		Component c8 = new JScrollPane(l4);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(c8, lim);
		southPanel.add(c8);

		//----------azioni principali----------
		JPanel p5=new MainActionPanel().mainAction();
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(p5, lim);
		southPanel.add(p5);

		//----------azioni secondarie----------
		JPanel p6=new SecondaryActionPanel().secondAction();
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(p6, lim);
		southPanel.add(p6);

		//----------azioni informative----------
		JPanel p7=new InfoActionPanel().infoAction();
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(p7, lim);
		southPanel.add(p7);

		return southPanel;
	}

	private String costruzione(Region reg){
		return reg.getDeck().getShowedDeck().toString();
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameMap frame = new FrameMap();

					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("errore", e);
				}
			}
		});
	}

}
