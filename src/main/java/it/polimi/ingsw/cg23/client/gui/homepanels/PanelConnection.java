package it.polimi.ingsw.cg23.client.gui.homepanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.client.gui.HomeFrame;
import it.polimi.ingsw.cg23.client.rmi.ClientRMI;
import it.polimi.ingsw.cg23.client.socket.ClientSocket;

/**
 * This panel allows to create a connection with the server.
 * @author Andrea
 *
 */
public class PanelConnection extends JPanel {

	private static final long serialVersionUID = -6068500950633356140L;

	private transient ControllerGUI controller;

	private JButton buttonSocket;	//button1
	private JButton buttonRMI;      //button2
	private JLabel labelAddress;	//label2
	private JTextField fieldAddress;//field1

	private HomeFrame homeFrame;
	private static Logger logger;
	/**
	 * Create the panel.
	 * @param controller The controller of the GUI. 
	 * @param homeFrame The frame that contains this panel.
	 */
	public PanelConnection(ControllerGUI controller, HomeFrame homeFrame) {
		this.controller=controller;
		this.homeFrame=homeFrame;
		logger = Logger.getLogger(PanelConnection.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		initComponent();
	}

	/**
	 * Initializes components of the panel.
	 */
	private void initComponent() {
		this.setOpaque(false);

		labelAddress = new JLabel();
		fieldAddress = new JTextField();
		buttonSocket = new JButton();
		buttonRMI = new JButton();

		labelAddress.setForeground(new Color(255, 215, 0));
		labelAddress.setText("IP Address:");

		buttonSocket.setText("SOCKET");
		buttonSocket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonSocketActionPerformed(evt);
			}
		});

		buttonRMI.setText("RMI");
		buttonRMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonRMIActionPerformed(evt);
			}
		});

		GroupLayout panelLayout = new GroupLayout(this);
		this.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(
				panelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, panelLayout.createSequentialGroup()
						.addGap(33, 33, 33)
						.addComponent(labelAddress)
						.addGap(18, 18, 18)
						.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(panelLayout.createSequentialGroup()
										.addComponent(buttonSocket)
										.addGap(104, 104, 104)
										.addComponent(buttonRMI))
								.addComponent(fieldAddress, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(67, Short.MAX_VALUE))
				);
		panelLayout.setVerticalGroup(
				panelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup()
						.addGap(105, 105, 105)
						.addGroup(panelLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelAddress)
								.addComponent(fieldAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(panelLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(buttonSocket)
								.addComponent(buttonRMI))
						.addContainerGap(64, Short.MAX_VALUE))
				);

	}

	/**
	 * The action performed when the buttonSocket receives a event.
	 * @param evt Event received.
	 */
	private void buttonSocketActionPerformed(ActionEvent evt) {                                         
		try {
			ClientSocket clientSocket=new ClientSocket();
			clientSocket.startClient(controller,fieldAddress.getText());
			homeFrame.switchPanel();
		} catch (IOException e) {
			logger.error(e);
			JOptionPane.showMessageDialog(null, "Unable to contact server with a SOCKET connection.", "ERROR", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * The action performed when the buttonReceive receives a event.
	 * @param evt Event received.
	 */
	private void buttonRMIActionPerformed(ActionEvent evt) {                                         
		try {
			ClientRMI clientRMI=new ClientRMI();
			clientRMI.startClient(controller,fieldAddress.getText());
			homeFrame.switchPanel();
		} catch (IOException | NotBoundException e) {
			logger.error(e);
			JOptionPane.showMessageDialog(null, "Unable to contact server with a RMI connection.", "ERROR", JOptionPane.WARNING_MESSAGE);
		}
	}

}
