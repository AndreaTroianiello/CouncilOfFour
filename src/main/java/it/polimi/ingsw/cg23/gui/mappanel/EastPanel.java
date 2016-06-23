package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * create the logger panel
 * @author viga94_
 *
 */
public class EastPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7843525022282542659L;
	private ButtonPanel bp;
	private JTextArea loggerArea;
	private JTextField write;
	
	/**
	 * Create the panel.
	 */
	public EastPanel(JTextArea loggerArea, JTextField write) {
		this.loggerArea=loggerArea;
		this.write=write;
		this.bp=new ButtonPanel(loggerArea, write);
		
	}

	/**
	 * create the logger panel
	 * @param loggerArea, the area to view
	 * @param write, the area to write on
	 * @return the panel
	 */
	public JPanel loggerPanel(){
		JPanel logger=new JPanel();

		GridBagLayout layout = new GridBagLayout();
		logger.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------logger area----------
		loggerArea.setName("textara");
		loggerArea.setText("Benvenuti a Cof");
		loggerArea.setEditable(false);
		loggerArea.setFont(new Font("Calibre", Font.PLAIN, 15));
		Component scrollLogger1 = new JScrollPane(loggerArea);
		scrollLogger1.setName("scrollPane text area logger");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(scrollLogger1, lim); //Associazione
		logger.add(scrollLogger1); //Inserimento*/

		//----------write area----------
		write.setEditable(true);
		write.setFont(new Font("Calibre", Font.PLAIN, 18));
		write.setName("write area");
		write.setToolTipText("Scrivi il testo che vuoi inviare");
		write.setText("Scrivi il testo che vuoi inviare");
		Component scrollLogger2 = new JScrollPane(write);
		scrollLogger2.setName("scrollPane write area");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(scrollLogger2, lim); //Associazione
		logger.add(scrollLogger2); //Inserimento
		write.addMouseListener(new MouseAdapter(){//cancella il testo presente
			@Override
			public void mouseClicked(MouseEvent me)
			{
				write.setText("");
			}
				});
		
		write.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {/**empty*/}
			@Override
			public void keyReleased(KeyEvent e) {/**empty*/}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(("").equals(write.getText())){
						JOptionPane.showMessageDialog(null, "Devi scrivere del testo!");
					}else{
						loggerArea.append("\ntesto inviato: "+write.getText());
						write.setText("");
					}
				}
			}
		});


		//----------button----------
		JPanel panel=bp.buttonPanel();
		panel.setName("button panel");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.HORIZONTAL;
		layout.setConstraints(panel, lim); //Associazione
		logger.add(panel); //Inserimento
		
		return logger;
	}

	public void update(){
		bp.update();
		bp.buttonPanel();
		this.repaint();
	}
}
