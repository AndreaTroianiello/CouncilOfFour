package it.polimi.ingsw.cg23.gui.mappanel;


import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.controller.action.SendMessage;

/**
 * create the logger panel
 * @author viga94_
 *
 */
public class EastPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7843525022282542659L;
	private ButtonPanel buttonPanel;
	private JTextArea loggerArea;
	private JTextField write;
	private ControllerGUI controller;

	/**
	 * 
	 * @param loggerArea, the area to read on
	 * @param write, the area to write on
	 * @param controller
	 */
	public EastPanel(JTextArea loggerArea, JTextField write, ControllerGUI controller) {
		this.loggerArea=loggerArea;
		this.write=write;
		this.buttonPanel=new ButtonPanel(loggerArea, write, controller);
		this.controller=controller;
		init();
	}

	private void init(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------logger area----------
		loggerArea.setName("textara");
		//loggerArea.setText("Welcome to Council of four " + controller.getModel().getPlayer().getUser());
		loggerArea.setText("Welcome to Council of four (nome utente da attivare)");
		loggerArea.setEditable(false);
		loggerArea.setFont(new Font("Calibre", Font.PLAIN, 15));
		Component scrollLogger1 = new JScrollPane(loggerArea);
		scrollLogger1.setName("scrollPane text area logger");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(scrollLogger1, lim); //Associazione
		add(scrollLogger1); //Inserimento*/

		//----------write area----------
		write.setEditable(true);
		write.setFont(new Font("Calibre", Font.PLAIN, 18));
		write.setName("write area");
		write.setToolTipText("Write your message.");
		write.setText("Write your message.");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(write, lim); //Associazione
		add(write); //Inserimento
		write.addMouseListener(new MouseAdapter(){//cancella il testo presente
			@Override
			public void mouseClicked(MouseEvent me)
			{
				write.setText("");
			}
		});

		write.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {/**empty*/}
			@Override
			public void keyReleased(KeyEvent e) {/**empty*/}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					sendMessage();
				}
			}
		});
		JButton button3=new JButton("Send");
		button3.setName("Send");
		button3.setToolTipText("Send the message");
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		layout.setConstraints(button3, lim); //Associazione
		add(button3); //Inserimento
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		//----------button----------
		buttonPanel.setName("button panel");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		lim.fill=GridBagConstraints.HORIZONTAL;
		layout.setConstraints(buttonPanel, lim); //Associazione
		add(buttonPanel); //Inserimento
	}

	private void sendMessage(){
		if(("").equals(write.getText())||"Write your message.".equals(write.getText())){
			JOptionPane.showMessageDialog(null, "Invalid text.");
		}else{
			controller.updateController(new SendMessage(write.getText(), controller.getModel().getPlayer()));
			write.setText("");
		}

	}
}
