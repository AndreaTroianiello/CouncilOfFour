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
 * create the east panel
 * @author viga94_
 *
 */
public class EastPanel extends JPanel {
	private static final long serialVersionUID = -7843525022282542659L;
	private ButtonPanel buttonPanel;
	private JTextArea loggerArea;
	private JTextField write;
	private transient ControllerGUI controller;

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
		GridBagLayout layout = new GridBagLayout();//creazione di un nuovo layout
		setLayout(layout);//applicazione del layout al pannello

		GridBagConstraints lim = new GridBagConstraints();//settaggio layout
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------logger area----------
		loggerArea.setName("textara");//nome loggerArea
		loggerArea.setText("Welcome to Council of four " + controller.getModel().getPlayer().getUser());
		loggerArea.setEditable(false);//loggerArea non modificabile
		loggerArea.setFont(new Font("Calibre", Font.PLAIN, 15));//font loggerArea
		Component scrollLogger1 = new JScrollPane(loggerArea);//scroller loggerArea
		scrollLogger1.setName("scrollPane text area logger");//nome scroll

		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=2;

		layout.setConstraints(scrollLogger1, lim);//applicazione del layout allo scroller
		add(scrollLogger1);//aggiunta dello scroll al pannello

		//----------write area----------
		write.setEditable(true);//write area modificabile
		write.setFont(new Font("Calibre", Font.PLAIN, 18));//font write area
		write.setName("write area");//nome write area
		write.setToolTipText("Write the message you want to send.");
		write.setText("Write your message.");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;

		layout.setConstraints(write, lim);//applicazione del layout alla write area
		add(write);//aggiunta write area al pannello

		write.addMouseListener(new MouseAdapter(){//cancella il testo presente cliccando con il mouse
			@Override
			public void mouseClicked(MouseEvent me)
			{
				write.setText("");
			}
		});

		write.addKeyListener(new KeyListener() {//aggiunta tasto invio
			@Override
			public void keyTyped(KeyEvent e) {/**empty, not erasable*/}
			@Override
			public void keyReleased(KeyEvent e) {/**empty, not erasable*/}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					sendMessage();
				}
			}
		});

		//----------send button----------
		JButton button3=new JButton("Send");//creazione di un nuovo bottone
		button3.setName("Send");//nome del bottone
		button3.setToolTipText("Send the message");

		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;

		layout.setConstraints(button3, lim); //applicazione del layout al bottone
		add(button3);//aggiunta del bottone al pannello

		button3.addActionListener(new ActionListener() {//azioni bottone
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		//----------button panel----------
		buttonPanel.setName("button panel");//nome del button panel
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		lim.fill=GridBagConstraints.HORIZONTAL;

		layout.setConstraints(buttonPanel, lim);//applicazione del layout al button panel
		add(buttonPanel);//aggiunta button panel al panel
	}

	/**
	 * send the message write in the write area
	 */
	private void sendMessage(){
		if(("").equals(write.getText())||"Write your message.".equals(write.getText())){
			JOptionPane.showMessageDialog(null, "Invalid text.");
		}else{
			controller.updateController(new SendMessage(write.getText(), controller.getModel().getPlayer()));
			write.setText("");
		}

	}
}
