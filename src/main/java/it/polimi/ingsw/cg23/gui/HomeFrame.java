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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomeFrame extends JFrame {
	
	private static final long serialVersionUID = -7250417194617661777L;
	private ControllerGUI controller;
	
	private JButton buttonSocket;	//button1
    private JButton buttonRMI;      //button2
    private JLabel labelBackground; //label1
    private JLabel labelAddress;	//label2
    private JPanel panelConnaction;			//panel1
    private JTextField fieldAddress;//field1
	private static Logger logger;
	
	private JPanel panelLoggin;
	private JLabel labelInfo1;
	private JLabel labelLoggin;
	private JButton buttonLoggin;
	private JLabel labelInfo2;
	private JTextField fieldLoggin;

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
					logger.error(e);
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
		 	panelLoggin=new JPanel();
			labelInfo1=new JLabel();
			labelLoggin=new JLabel();
			buttonLoggin=new JButton();
			labelInfo2=new JLabel();
			fieldLoggin=new JTextField();

		 
		 	//panel1
	        panelConnaction = new JPanel();
	        panelLoggin = new JPanel();
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

	        //Panel 2
	        panelLoggin.setOpaque(false);
	        panelLoggin.setVisible(false);
	        labelInfo1.setForeground(new Color(255, 215, 0));
	        labelInfo1.setText("Connection created");

	        labelLoggin.setForeground(new java.awt.Color(255, 215, 0));
	        labelLoggin.setText("Insert your username:");

	        buttonLoggin.setText("Loggin");
	        buttonLoggin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                buttonLogginActionPerformed(evt);
	            }
	        });

	        labelInfo2.setForeground(new java.awt.Color(255, 215, 0));
	        labelInfo2.setText("");

	        GroupLayout panelLogginLayout = new javax.swing.GroupLayout(panelLoggin);
	        panelLoggin.setLayout(panelLogginLayout);
	        panelLogginLayout.setHorizontalGroup(
	            panelLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelLogginLayout.createSequentialGroup()
	                .addGap(23, 23, 23)
	                .addComponent(labelLoggin)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(panelLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(panelLogginLayout.createSequentialGroup()
	                        .addComponent(labelInfo1)
	                        .addGap(0, 159, Short.MAX_VALUE))
	                    .addComponent(fieldLoggin))
	                .addContainerGap())
	            .addGroup(panelLogginLayout.createSequentialGroup()
	                .addGap(152, 152, 152)
	                .addGroup(panelLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(panelLogginLayout.createSequentialGroup()
	                        .addGap(10, 10, 10)
	                        .addComponent(labelInfo2))
	                    .addComponent(buttonLoggin))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        panelLogginLayout.setVerticalGroup(
	        		panelLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelLogginLayout.createSequentialGroup()
	                .addGap(88, 88, 88)
	                .addComponent(labelInfo1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(panelLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(labelLoggin)
	                    .addComponent(fieldLoggin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addComponent(buttonLoggin)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelInfo2)
	                .addContainerGap(26, Short.MAX_VALUE))
	        );

	        getContentPane().add(panelLoggin);
	        panelLoggin.setBounds(-10, 230, 400, 220);
	        
	        
	        //Panel 1
	        panelConnaction.setOpaque(false);

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

	        GroupLayout panelLayout = new GroupLayout(panelConnaction);
	        panelConnaction.setLayout(panelLayout);
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

	        getContentPane().add(panelConnaction);
	        panelConnaction.setBounds(0, 230, 400, 230);
	        
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
				panelConnaction.setVisible(false);
				panelLoggin.setVisible(true);
			} catch (IOException e) {
				logger.error(e);
				JOptionPane.showMessageDialog(null, "Unable to contact server with a SOCKET connection.", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
	 }
	 
	 private void buttonRMIActionPerformed(java.awt.event.ActionEvent evt) {                                         
		 try {
				ClientRMI clientRMI=new ClientRMI();
				clientRMI.startClient(controller,fieldAddress.getText());
				panelConnaction.setVisible(false);
				panelLoggin.setVisible(true);
			} catch (IOException | NotBoundException e) {
				logger.error(e);
				JOptionPane.showMessageDialog(null, "Unable to contact server with a RMI connection.", "ERROR", JOptionPane.WARNING_MESSAGE);
		}
	 }
	 
	 private void buttonLogginActionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			
	}
}