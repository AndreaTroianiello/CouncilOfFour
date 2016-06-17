package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.cli.ControllerCLI;
import it.polimi.ingsw.cg23.client.rmi.ClientRMI;
import it.polimi.ingsw.cg23.client.socket.ClientSocket;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.AttributedCharacterIterator;
import java.awt.event.ActionEvent;

/**
 * fist gui class
 *
 */
public class HelloFrame extends JFrame {

	private static final long serialVersionUID = -5251939376496501206L;
	private JPanel contentPane;
	private static Logger logger;
	private FrameMap fm;
	private transient BufferedImage image = null;
	private ControllerGUI controller;
	private int time=20000;//tempo di attesa
	private JLabel countdownLabel;//etichetta countdown

	/**
	 * Create the frame.
	 */
	public HelloFrame() {
		this.setName("Council of Four");
		controller=new ControllerGUI();
		fm=new FrameMap(controller);
		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger


		//immagine per informazioni grandezza
		try {
			image = ImageIO.read(new File("src/main/resources/images/preview.jpg"));
		} catch (IOException e) {

			logger.error("impossibile caricare l'ìmmagine", e);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(0, 0, image.getWidth()+50, image.getHeight()+90);

		contentPane = new JPanel();
		/*contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setMaximumSize(new Dimension(image.getWidth()+50, image.getHeight()+90));*/
		setContentPane(contentPane);

		setPanel();

	}
	private void setPanel(){
		//panel north
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.NORTH);
		panel1.setBorder(null);
		setBackgorund(panel1);//aggiungo lo sfondo

		//panel south
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel2.setBorder(null);
		addButton(panel2);//aggiungo i bottoni
	}

	private JLabel labelCountdown(){
		JLabel label=new JLabel(); 
		TimerClass tc=new TimerClass(label, time);//countdown
		tc.start();
		return label;
	}

	/**
	 * create the background
	 * @param panel2
	 */
	private void setBackgorund(JPanel panel){

		//Create Image Label
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(0, 0, image.getWidth(), image.getHeight());
		panel.add(label, JPanel.CENTER_ALIGNMENT);
	}

	/**
	 * add rmi and socket button
	 * @param panel2
	 */
	private void addButton(JPanel panel2){

		JButton btnRmi = new JButton("RMI");//bottone rmi
		btnRmi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countdownLabel=labelCountdown();
				fm.setVisible(true);//apro la finestra FrameMap
				setVisible(false);//chiudo la finestra corrente

				//azioni per rmi
				
				try {
					ClientRMI clientRMI=new ClientRMI();
					clientRMI.startClient(controller,"127.0.0.1");
				} catch (RemoteException | NotBoundException e1) {
					logger.error(e);
				}

			}
		});
		panel2.add(btnRmi, JPanel.BOTTOM_ALIGNMENT);

		JButton socket = new JButton("Socket");//bottone socket
		socket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countdownLabel=labelCountdown();

				panel2.add(countdownLabel, JPanel.RIGHT_ALIGNMENT);//countodwn di attesa

				fm.setVisible(true);//apro la finestra FrameMap
				setVisible(false);//chiudo la finestra corrente


				//azioni per socket
				
				try {
					ClientSocket clientSocket=new ClientSocket();
					clientSocket.startClient(controller,"127.0.0.1");
				} catch (IOException e1) {
					logger.error(e);
				}
			}
		});
		panel2.add(socket, JPanel.BOTTOM_ALIGNMENT);
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		//configurazione logger
		logger = Logger.getLogger(HelloFrame.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					HelloFrame frame = new HelloFrame();
					frame.setBounds(100, 100,400,600);
					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("Impossibile creare il frame", e);
				}
			}
		});
	}
}

