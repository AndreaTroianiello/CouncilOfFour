package it.polimi.ingsw.cg23.gui.homepanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.controller.action.CreationGame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelLogin extends JPanel {

	private static final long serialVersionUID = 8087900714680895083L;
	private transient ControllerGUI controller;
	private JLabel labelInfo1;
	private JLabel labelLogin;
	private JButton buttonLogin;
	private JLabel labelInfo2;
	private JTextField fieldLogin;
	private JLabel labelMap;
	private JComboBox<String> comboMap;

	/**
	 * Create the panel.
	 */
	public PanelLogin(ControllerGUI controller) {
		this.controller=controller;
		initComponent();
	}

	private void initComponent() {

		this.setOpaque(false);

		labelInfo1=new JLabel();
		labelLogin=new JLabel();
		buttonLogin=new JButton();
		labelInfo2=new JLabel(new ImageIcon("src/main/resources/images/loading.gif"));
		fieldLogin=new JTextField();
		comboMap=new JComboBox<>();
		labelMap=new JLabel();
		labelInfo1.setForeground(new java.awt.Color(255, 215, 0));
		labelInfo1.setText("Connection created");

		labelLogin.setForeground(new java.awt.Color(255, 215, 0));
		labelLogin.setText("Insert your username:");

		buttonLogin.setText("Login");
		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonLogginActionPerformed(evt);
			}
		});

		labelInfo2.setVisible(false);

		labelMap.setForeground(new Color(255, 215, 0));
		labelMap.setText("Choose a map:");

		comboMap.setModel(new DefaultComboBoxModel<>(new String[] { "Map 1", "Map 2","Map 3", "Map 4","Map 5", "Map 6","Map 7", "Map 8" }));

		GroupLayout panelLogginLayout = new GroupLayout(this);
		this.setLayout(panelLogginLayout);
		panelLogginLayout.setHorizontalGroup(
				panelLogginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelLogginLayout.createSequentialGroup()
						.addGap(137, 137, 137)
						.addComponent(labelInfo1)
						.addContainerGap(169, Short.MAX_VALUE))
				.addGroup(panelLogginLayout.createSequentialGroup()
						.addGroup(panelLogginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(panelLogginLayout.createSequentialGroup()
										.addGap(152, 152, 152)
										.addGroup(panelLogginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(panelLogginLayout.createSequentialGroup()
														.addGap(10, 10, 10)
														.addComponent(labelInfo2))
												.addComponent(buttonLogin)))
								.addGroup(panelLogginLayout.createSequentialGroup()
										.addGap(23, 23, 23)
										.addGroup(panelLogginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(labelLogin)
												.addComponent(labelMap))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(panelLogginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(panelLogginLayout.createSequentialGroup()
														.addComponent(comboMap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(0, 0, Short.MAX_VALUE))
												.addComponent(fieldLogin))))
						.addGap(12, 12, 12))
				);
		panelLogginLayout.setVerticalGroup(
				panelLogginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelLogginLayout.createSequentialGroup()
						.addGap(73, 73, 73)
						.addComponent(labelInfo1)
						.addGap(3, 3, 3)
						.addGroup(panelLogginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelLogin)
								.addComponent(fieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(panelLogginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelMap)
								.addComponent(comboMap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(9, 9, 9)
						.addComponent(buttonLogin)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(labelInfo2)
						.addContainerGap(20, Short.MAX_VALUE))
				);

	}

	private void buttonLogginActionPerformed(ActionEvent evt) {
		String map=(String)comboMap.getSelectedItem();
		map=map.substring(0, map.length()-2)+map.charAt(map.length()-1);//tolgo lo spazio
		map=map.toLowerCase();//metto tutto in minuscolo
		controller.updateController(new CreationGame(fieldLogin.getText(),map));
	}

	public void infoPopup(String message){
		if(message.contains("player has been created.")){
			labelInfo2.setVisible(true);
			buttonLogin.setEnabled(false);
		}
		JOptionPane.showMessageDialog(null, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}

}
