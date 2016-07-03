package it.polimi.ingsw.cg23.client.gui.board;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.client.gui.board.southpanel.CostructionCardPanel;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

/**
 * create the used business permit tiles frame
 * @author viga94_
 *
 */
public class CardFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3779106307996531668L;
	private JPanel contentPane;
	private transient CostructionCardPanel ccp;

	/**
	 * Create the frame.
	 * @param loggerArea the area to read on
	 * @param controller, the controller
	 */
	public CardFrame(JTextArea loggerArea, ControllerGUI controller) {
		this.ccp=new CostructionCardPanel(loggerArea,controller);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		setTitle("Used business permit tile");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}

	/**
	 * create the used costruction cards for the player
	 * @param p, the player
	 */
	public void createCard(Player p){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		List<BusinessPermitTile> bpt=p.getUsedBusinessPermit();//carte permesso costrucione giocatore usate
		if(bpt.isEmpty()){//non ci sono carte permesso usate

			//----------no card----------
			JLabel noCardLabel = new JLabel("No used business permit tiles");//aggiungo l'immagine alla label
			noCardLabel.setFont(new Font("Calibre", Font.PLAIN, 20));
			
			lim.gridx = 0;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			
			layout.setConstraints(noCardLabel, lim);
			panel.add(noCardLabel);

		}else{//ci sono carte permesso usate

			//----------costruction card usate disponibili----------
			JLabel yesCardLabel = new JLabel("Used business permit tiles");//aggiungo l'immagine alla label
			yesCardLabel.setFont(new Font("Calibre", Font.PLAIN, 20));
			
			lim.gridx = 0;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=5;
			
			layout.setConstraints(yesCardLabel, lim);
			panel.add(yesCardLabel);

			int q=0;//posizione griglia orizzonatale (larghezza)
			int r=1;//posizione griglia verticale (altezza)
			for(int i=0; i<bpt.size(); i++){
				JLabel costructionCard = ccp.oldCostructionWB(bpt.get(i));//aggiungo l'immagine alla label
				
				lim.gridx = q;//posizione componenti nella griglia
				lim.gridy = r;
				lim.weightx=1;//espansione in verticale e orizzontale
				lim.weighty=1;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;
				
				layout.setConstraints(costructionCard, lim);
				panel.add(costructionCard);
				final int k=i;
				costructionCard.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
					@Override
					public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
					@Override
					public void mouseExited(MouseEvent e) {/**empty, not erasable*/}
					@Override
					public void mouseEntered(MouseEvent e) {/**empty, not erasable*/}
					@Override
					public void mouseClicked(MouseEvent e) {
						ccp.writeArea(bpt.get(k));
					}
				});
				q++;
				if(q%5==0){//metto al massino 5 carte costruzione per riga
					q=0;
					r++;
				}
			}
		}

		contentPane.add(panel);
	}

}
