package it.polimi.ingsw.cg23.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.utility.CreateMap;

public class Nuovo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328028752338623444L;
	private JPanel contentPane;
	private JTextArea log;
	private transient Avvio s;

	/**
	 * Create the frame.
	 */
	public Nuovo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		//
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


		JPanel c4=new JPanel();
		setSouthPanel(c4);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(c4, lim); //Associazione
		contentPane.add(c4); //Inserimento

	}

	private void setSouthPanel(JPanel c4){
		GridBagLayout layout = new GridBagLayout();
		c4.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//-------------carte permesso di costruzione----------------
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
		c4.add(costruzione1);

		//
		JTextPane costruzione2=new JTextPane();
		costruzione2.setName("Carte costruzione collina");
		costruzione2.setEditable(false);
		costruzione2.setText(costruzione(s.getBoard().getRegions().get(1)));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(costruzione2, lim);
		c4.add(costruzione2);

		//
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
		c4.add(costruzione3);

		//---------------balconi------
		JTextPane balcone1=new JTextPane();
		balcone1.setName("balcone costa");
		balcone1.setEditable(false);
		balcone1.setText(balcone(s.getBoard().getRegions().get(0)));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(balcone1, lim);
		c4.add(balcone1);

		JTextPane balcone2=new JTextPane();
		balcone2.setName("balcone collina");
		balcone2.setBackground(new Color(158, 219, 147));
		balcone2.setEditable(false);
		balcone2.setText(balcone(s.getBoard().getRegions().get(1)));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(balcone2, lim);
		c4.add(balcone2);

		JTextPane balcone3=new JTextPane();
		balcone3.setName("balcone montagna");
		balcone3.setEditable(false);
		balcone3.setText(balcone(s.getBoard().getRegions().get(2)));
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(balcone3, lim);
		c4.add(balcone3);

		//----------------nobility track------------
		JTextPane l4=new JTextPane();
		l4.setEditable(false);
		l4.setName("Nobility pane");
		l4.setText(nobilityTrack());
		Component c8 = new JScrollPane(l4);

		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(c8, lim);
		c4.add(c8);

		//-----------azioni----------
		JPanel p5=new JPanel();
		mainAction(p5);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(p5, lim);
		c4.add(p5);
		//
		JPanel p6=new JPanel();
		secondAction(p6);
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(p6, lim);
		c4.add(p6);

		//
		JPanel p7=new JPanel();
		infoAction(p7);
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(p7, lim);
		c4.add(p7);

	}

	private String balcone(Region reg){
		return reg.getCouncil().getCouncillors().toString();
	}

	private String costruzione(Region reg){
		return reg.getDeck().getShowedDeck().toString();
	}

	private String nobilityTrack(){
		return new CreateMap().printNobility(s.getBoard());
	}

	private void infoAction(JPanel panel){
		GridBagLayout layout = new GridBagLayout();

		panel.setLayout(layout);
		panel.setBackground(new Color(123,123,123));

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.SOUTH;//posizione componenti nei riquadri

		JLabel label=new JLabel("Info:");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		layout.setConstraints(label, lim);
		panel.add(label);

		JButton button1 = new JButton("Exit");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button1, lim);
		panel.add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE exit
				new HelloFrame().setVisible(true);
				setVisible(false);
			}
		});

		JButton button2 = new JButton("Clear");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button2, lim);
		panel.add(button2);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE clear
				log.setText("cancello");

			}
		});

	}

	private void mainAction(JPanel panel){
		GridBagLayout layout = new GridBagLayout();
		panel.setBackground(new Color(245,123,123));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 

		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		JLabel label=new JLabel("Main action:");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(label, lim);
		panel.add(label);

		JButton button1 = new JButton("Action 1");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button1, lim);
		panel.add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE 1
			}
		});

		JButton button2 = new JButton("Action 2");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button2, lim);
		panel.add(button2);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE 2
			}
		});

		JButton button3 = new JButton("Action 3");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button3, lim);
		panel.add(button3);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE 3
			}
		});

		JButton button4 = new JButton("Action 4");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button4, lim);
		panel.add(button4);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE 4
			}
		});
	}

	private void secondAction(JPanel panel){
		panel.setBackground(new Color(123,255,123));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagLayout layout = new GridBagLayout();


		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		panel.setLayout(layout);

		JLabel label=new JLabel("Secondary action:");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(label, lim);
		panel.add(label);

		JButton button1 = new JButton("Action 1");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button1, lim);
		panel.add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE 1
			}
		});

		JButton button2 = new JButton("Action 2");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button2, lim);
		panel.add(button2);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE 2
			}
		});

		JButton button3 = new JButton("Action 3");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button3, lim);
		panel.add(button3);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE 3
			}
		});

		JButton button4 = new JButton("Action 4");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button4, lim);
		panel.add(button4);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//AZIONI AZIONE 4
			}
		});
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
					Nuovo frame = new Nuovo();

					frame.setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}

}
