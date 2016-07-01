package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.model.action.MarketSell;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.server.model.marketplace.CanBeSold;

public class MarketDialog extends JFrame {
	
	private static final long serialVersionUID = 6353088208693082884L;
	private JComboBox<Integer> boxAssistants;
    private JButton buttonCancel;
    private JButton buttonSell;
    private JTextField fieldCoins;
    private JLabel labelAssistants;
    private JLabel labelCoins;
    private JLabel labelInfo;
    private JPanel panelDialog;
	private ControllerGUI controller;
	private CanBeSold item;
	private Logger logger;

	/**
	 * Create the frame.
	 */
	public MarketDialog(ControllerGUI controller,CanBeSold item) {
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.controller=controller;
		this.item=item;
		setBounds(100, 100, 0,0);
		initComponents();
	}
	
	private void initComponents() {

        panelDialog = new JPanel();
        labelInfo = new JLabel();
        boxAssistants = new JComboBox<>();
        labelAssistants = new JLabel();
        fieldCoins = new JTextField();
        buttonCancel = new JButton();
        buttonSell = new JButton();
        labelCoins = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(300, 168));
        
        labelInfo.setText("Choose the parameters");
        
        if(item instanceof AssistantsPool){
        	//Fill the JComboBox with the assistants.
        	if(controller.getModel().getPlayer().getAssistantsPool().getAssistants()==0){
        		boxAssistants.addItem(0);
        	}else{
        		for(int i=0; i<controller.getModel().getPlayer().getAssistantsPool().getAssistants(); i++){
        			boxAssistants.addItem(i+1);
        		}
        	}
        	labelAssistants.setText("Assistants:");
        }
        else{
        	boxAssistants.setVisible(false);
        	labelAssistants.setVisible(false);
        }
        
        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonSell.setText("Ok");
        buttonSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonSellActionPerformed(evt);
            }
        });

        labelCoins.setText("Coins:");

        GroupLayout panelDialogLayout = new GroupLayout(panelDialog);
        panelDialog.setLayout(panelDialogLayout);
        panelDialogLayout.setHorizontalGroup(
            panelDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addGroup(panelDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(labelAssistants)
                            .addComponent(labelCoins))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(fieldCoins, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDialogLayout.createSequentialGroup()
                                .addComponent(buttonSell)
                                .addGap(43, 43, 43)
                                .addComponent(buttonCancel))
                            .addComponent(boxAssistants, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDialogLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(labelInfo)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDialogLayout.setVerticalGroup(
            panelDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfo)
                .addGap(18, 18, 18)
                .addGroup(panelDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCoins, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCoins))
                .addGap(18, 18, 18)
                .addGroup(panelDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(boxAssistants, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAssistants))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancel)
                    .addComponent(buttonSell))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelDialog, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelDialog, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                    

	private void buttonSellActionPerformed(ActionEvent evt) {                                           

		try {
			if(item instanceof AssistantsPool)
				((AssistantsPool)item).setAssistants(boxAssistants.getSelectedIndex());
			controller.updateController(new MarketSell(item, Integer.parseInt(fieldCoins.getText())));
		} catch (NegativeNumberException | NumberFormatException e) {
			logger.error(e);
		}

	}                                          
    private void buttonCancelActionPerformed(ActionEvent evt) {                                             
        this.dispose();
    }                                            
}