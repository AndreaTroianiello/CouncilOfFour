package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * fist gui class
 *
 */
public class HelloFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5251939376496501206L;
	private JPanel contentPane;
	private static Logger logger;

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
					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("Impossibile creare il frame", e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HelloFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel1 = new JPanel();//panel nord
		contentPane.add(panel1, BorderLayout.NORTH);

		JLabel lblCof = new JLabel("Cof");
		panel1.add(lblCof);

		JPanel panel2 = new JPanel();//panel centrale
		contentPane.add(panel2, BorderLayout.CENTER);

		JButton btnRmi = new JButton("RMI");//bottone rmi
		btnRmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel2.add(btnRmi);

		JButton socket = new JButton("Socket");//bottone socket

		socket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel2.add(socket);
	}

}

