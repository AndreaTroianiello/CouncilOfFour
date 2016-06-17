package it.polimi.ingsw.cg23.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.rmi.ClientRMI;
import it.polimi.ingsw.cg23.client.socket.ClientSocket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomeFrame extends JFrame {
	
	private static final long serialVersionUID = -7250417194617661777L;
	private JButton buttonSocket;	//button1
    private JButton buttonRMI;      //button2
    private JLabel labelBackground; //label1
    private JLabel labelAddress;	//label2
    private JPanel panel;			//panel1
    private JTextField fieldAddress;//field1
	private Logger logger;
	private ControllerGUI controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame frame = new HomeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeFrame() {
		logger = Logger.getLogger(HomeFrame.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		controller=new ControllerGUI();
		initComponents();
	}

	
	 private void initComponents() {

	        panel = new JPanel();
	        labelAddress = new JLabel();
	        fieldAddress = new JTextField();
	        buttonSocket = new JButton();
	        buttonRMI = new JButton();
	        labelBackground = new JLabel();

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("Council of Four");
	        setBounds(new Rectangle(100, 100, 400, 497));
	        setMaximumSize(new Dimension(700, 870));
	        setMinimumSize(new Dimension(400, 497));
	        setName("home"); // NOI18N
	        setPreferredSize(new Dimension(400, 497));
	        setResizable(false);
	        getContentPane().setLayout(null);

	        panel.setOpaque(false);

	        labelAddress.setForeground(new Color(255, 215, 0));
	        labelAddress.setText("IP Address:");

	        buttonSocket.setText("SOCKET");
	        buttonSocket.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                buttonSocketActionPerformed(evt);
	            }
	        });

	        buttonRMI.setText("RMI");
	        buttonRMI.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                buttonRMIActionPerformed(evt);
	            }
	        });

	        GroupLayout panelLayout = new GroupLayout(panel);
	        panel.setLayout(panelLayout);
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

	        getContentPane().add(panel);
	        panel.setBounds(0, 230, 400, 230);
	        
	        try {
				BufferedImage image = ImageIO.read(new File("src/main/resources/images/Home-CouncilOfFour.jpg"));
				labelBackground.setIcon(new ImageIcon(image));
		        labelBackground.setAlignmentY(0.0F);
		        getContentPane().add(labelBackground);
		        labelBackground.setBounds(0, 0, image.getWidth(), image.getHeight());
			} catch (IOException e) {

				logger.error("impossibile caricare l'ìmmagine", e);
			}
	 }

	 private void buttonSocketActionPerformed(java.awt.event.ActionEvent evt) {                                         
		 try {
				ClientSocket clientSocket=new ClientSocket();
				clientSocket.startClient(controller,fieldAddress.getText());
			} catch (IOException e) {
				logger.error(e);
			}
	 }
	 
	 private void buttonRMIActionPerformed(java.awt.event.ActionEvent evt) {                                         
		 try {
				ClientRMI clientRMI=new ClientRMI();
				clientRMI.startClient(controller,fieldAddress.getText());
			} catch (IOException | NotBoundException e) {
				logger.error(e);
		}
	 } 
}
