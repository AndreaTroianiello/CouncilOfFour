package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.cg23.client.ClientController;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameLoggin extends JFrame {

	private static final long serialVersionUID = -1100722416931156230L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLoggin frame = new FrameLoggin(new ControllerGUI());
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
	public FrameLoggin(ClientController controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		gbl_contentPane.columnWidths = new int[]{0,1,2, 0,3,4,5};
		gbl_contentPane.rowHeights = new int[]{0,1,2,3,4,5, 0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblInsertYourUsername = new JLabel("Insert your username:");
		GridBagConstraints gbc_lblInsertYourUsername = new GridBagConstraints();
		gbc_lblInsertYourUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblInsertYourUsername.gridx = 3;
		gbc_lblInsertYourUsername.gridy = 0;
		contentPane.add(lblInsertYourUsername, gbc_lblInsertYourUsername);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 2;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnLoggin = new JButton("Loggin");
		GridBagConstraints gbc_btnLoggin = new GridBagConstraints();
		gbc_btnLoggin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoggin.gridx = 3;
		gbc_btnLoggin.gridy = 4;
		contentPane.add(btnLoggin, gbc_btnLoggin);
		btnLoggin.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//controller.
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 6;
		lblNewLabel.setVisible(false);
		
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

	}

}
