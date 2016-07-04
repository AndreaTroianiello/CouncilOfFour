package it.polimi.ingsw.cg23.client.gui.board.bonus;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.controller.action.PerformBonus;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCityToken;
import it.polimi.ingsw.cg23.server.model.bonus.BonusGetPermitTile;
import it.polimi.ingsw.cg23.server.model.bonus.BonusTileBonus;

/**
 * This dialog allows to perform a bonus.
 * 
 * @author Andrea
 *
 */
public class BonusDialog extends JFrame {

	private static final long serialVersionUID = 7093234192379688331L;
	private JButton buttonCancel;
	private JButton buttonSend;
	private JTextField fieldParameter1;
	private JTextField fieldParameter2;
	private JPanel panel;
	private JLabel labelInfo;
	private JLabel labelParameter1;
	private JLabel labelParameter2;
	private transient ControllerGUI controller;
	private Bonus bonus;

	/**
	 * Creates new form BonusDialog
	 */
	public BonusDialog(ControllerGUI controller, Bonus bonus) {
		this.controller = controller;
		this.bonus = bonus;
		initComponents();
	}

	/**
	 * Initializes the components.
	 */
	private void initComponents() {

		panel = new JPanel();
		labelInfo = new JLabel();
		fieldParameter1 = new JTextField();
		labelParameter1 = new JLabel();
		labelParameter2 = new JLabel();
		fieldParameter2 = new JTextField();
		buttonSend = new JButton();
		buttonCancel = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Bonus");
		setMaximumSize(new Dimension(387, 134));
		setMinimumSize(new Dimension(387, 134));
		setResizable(false);

		panel.setMinimumSize(new Dimension(387, 134));

		labelInfo.setText("You have activated the " + bonus.getName() + ". Choose the appropriate parameters.");

		labelParameter1.setText("Parameter1:");

		labelParameter2.setText("Parameter2:");

		if (bonus.getNumber() > 1 || bonus instanceof BonusGetPermitTile) {
			labelParameter2.setVisible(false);
			fieldParameter2.setVisible(false);
		}

		buttonSend.setText("Send");
		buttonSend.addActionListener(e -> buttonSendActionPerformed());

		buttonCancel.setText("Cancel");
		buttonCancel.addActionListener(e -> buttonCancelActionPerformed());

		GroupLayout jPanel1Layout = new GroupLayout(panel);
		panel.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(labelInfo)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(29, 29, 29)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(labelParameter1).addComponent(labelParameter2))
										.addGap(49, 49, 49).addGroup(
												jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(fieldParameter1).addComponent(fieldParameter2,
																GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(88, 88, 88).addComponent(buttonSend)
								.addGap(70, 70, 70).addComponent(buttonCancel)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(labelInfo)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(fieldParameter1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelParameter1))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelParameter2).addComponent(fieldParameter2, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(buttonSend).addComponent(buttonCancel))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panel,
				GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

		pack();
	}

	/**
	 * The action performed when the buttonSend receives a event.
	 */
	private void buttonSendActionPerformed() {
		if (bonus instanceof BonusCityToken) {
			List<City> cities = new ArrayList<>();
			cities.add(controller.getModel().findCity(fieldParameter1.getText()));
			if (controller.getModel().findCity(fieldParameter1.getText()) != null)
				cities.add(controller.getModel().findCity(fieldParameter1.getText()));
			((BonusCityToken) bonus).setCities(cities);
		}
		if (bonus instanceof BonusGetPermitTile) {
			((BonusGetPermitTile) bonus).setTile(controller.getModel().findRegion(fieldParameter1.getText()),
					Integer.parseInt(fieldParameter2.getText()));
		}
		if (bonus instanceof BonusTileBonus) {
			((BonusTileBonus) bonus).setNumberTile(Integer.parseInt(fieldParameter1.getText()));
		}
		controller.updateController(new PerformBonus(controller.getModel().getPlayer(), bonus));
	}

	/**
	 * The action performed when the buttonCancel receives a event.
	 */
	private void buttonCancelActionPerformed() {
		dispose();
	}

}
