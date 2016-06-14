package it.polimi.ingsw.cg23.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Nuovo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328028752338623444L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Nuovo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		grid();
	}
	
	public void grid(){
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		JLabel b1=new JLabel();
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(b1, lim);
		contentPane.add(b1);
		
		//
		JLabel c1=new JLabel();
		lim.gridx = 1; //Colonna 1
		lim.gridy = 0; //Riga 0
		lim.weightx = 1;
		lim.weighty = 1;
		lim.gridheight=1;
		lim.gridwidth=1;
		layout.setConstraints(c1, lim); //Associazione
		contentPane.add(c1); //Inserimento
		//
		
		JLabel c2=new JLabel();
		lim.gridx = 2; //Colonna 1
		lim.gridy = 0; //Riga 0
		lim.weightx = 1;
		lim.weighty = 1;
		lim.gridheight=1;
		lim.gridwidth=1;
		layout.setConstraints(c2, lim); //Associazione
		contentPane.add(c2); //Inserimento
		//
		Component c7=new JTextArea(10, 10);
		c7.setName("textara");
		Component c8 = new JScrollPane(c7);
		lim.gridx = 3; //Colonna 1
		lim.gridy = 0; //Riga 0
		lim.weightx = 1;
		lim.weighty = 2;
		lim.gridheight=2;
		lim.gridwidth=1;
		layout.setConstraints(c8, lim); //Associazione
		contentPane.add(c8); //Inserimento
		//
		
		JPanel c4=new JPanel();
		setSouthPanel(c4);
		lim.gridx = 0; //Colonna 1
		lim.gridy = 1; //Riga 0
		lim.weightx = 1;
		lim.weighty = 1;
		lim.gridwidth=3;
		lim.gridheight=1;
		layout.setConstraints(c4, lim); //Associazione
		contentPane.add(c4); //Inserimento
	}
	
	public void setSouthPanel(JPanel c4){
		GridBagLayout layout = new GridBagLayout();
		c4.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		JComponent b1=new JButton("b1");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(b1, lim);
		c4.add(b1);
		//
		JComponent b2=new JButton("b2");
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(b2, lim);
		c4.add(b2);
		
		//
		JComponent b3=new JButton("b3");
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(b3, lim);
		c4.add(b3);
		
		//
		JComponent b4=new JButton("b4");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(b4, lim);
		c4.add(b4);
		//
		
		JPanel b5=new JPanel();
		mainAction(b5);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(b5, lim);
		c4.add(b5);
		//
		JComponent b6=new JButton("b6");
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(b6, lim);
		c4.add(b6);
		
		//
		JComponent b7=new JButton("b7");
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(b7, lim);
		c4.add(b7);
		
	}
	
	private void mainAction(JPanel panel){
		JLabel label=new JLabel("Main action:");
		panel.add(label);
		
		JButton button1 = new JButton("Action 1");
		panel.add(button1, JPanel.LEFT_ALIGNMENT);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelloFrame().setVisible(true);//apro la finestra HelloFrame
				setVisible(false);//chiudo la finestra corrente
			}
		});
		
		JButton button2 = new JButton("Action 2");
		panel.add(button2, JPanel.LEFT_ALIGNMENT);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelloFrame().setVisible(true);//apro la finestra HelloFrame
				setVisible(false);//chiudo la finestra corrente
			}
		});
		
		JButton button3 = new JButton("Action 3");
		panel.add(button3, JPanel.LEFT_ALIGNMENT);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelloFrame().setVisible(true);//apro la finestra HelloFrame
				setVisible(false);//chiudo la finestra corrente
			}
		});
		
		JButton button4 = new JButton("Action 4");
		panel.add(button4, JPanel.LEFT_ALIGNMENT);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelloFrame().setVisible(true);//apro la finestra HelloFrame
				setVisible(false);//chiudo la finestra corrente
			}
		});
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nuovo frame = new Nuovo();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
