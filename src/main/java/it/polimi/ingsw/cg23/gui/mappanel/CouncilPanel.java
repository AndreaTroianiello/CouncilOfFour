package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.server.controller.Setting;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.utility.ColorManager;

/**
 * create the council panel for the gui
 * @author viga94_
 *
 */
public class CouncilPanel {
	
	private JTextArea loggerArea;

	/**
	 * Create the panel.
	 */
	public CouncilPanel(JTextArea loggerArea){
		this.loggerArea=loggerArea;
	}

	/**
	 * create the balcone of region
	 * @param reg, the region
	 * @return a panel with the balcone
	 */
	public JPanel regionBalcone(Region reg){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		JLabel label0 = new JLabel("etichetta consiglieri");
		label0.setText("Consiglieri "+reg.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=8;
		layout.setConstraints(label0, lim);
		panel.add(label0);//aggiunta della label al panel

		List<Councillor> councillors=reg.getCouncil().getCouncillors();
		for(int i=0; i<councillors.size(); i++){//scrorre i consiglieri

			JLabel label1 = new JLabel();
			label1.setBackground(councillors.get(i).getColor());
			label1.setName("consigliere "+i);
			label1.setToolTipText("Consigliere "+reg.getName()+" numero "+(i+1));
			label1.setOpaque(true);
			label1.setPreferredSize(new Dimension(50, 20));
			lim.gridx = i*2;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.weightx=0;//espansione in verticale e orizzontale
			lim.weighty=0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(label1, lim);
			panel.add(label1);//aggiunta della label al panel

			JLabel label2 = new JLabel();//label per aggiungere spazio
			label2.setPreferredSize(new Dimension(5, 20));
			lim.gridx = i*2+1;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.weightx=0;//espansione in verticale e orizzontale
			lim.weighty=0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(label2, lim);
			panel.add(label2);//aggiunta della label al panel
		}

		return panel;
	}

	/**
	 * create the king councillor
	 * @param k, the king
	 * @return the king conucillor
	 */
	public JPanel kingBalcone(King k){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		JLabel label0 = new JLabel("etichetta consiglieri");
		label0.setText("Consiglieri king");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=8;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(label0, lim);
		panel.add(label0);//aggiunta della label al panel

		List<Councillor> councillors=k.getCouncil().getCouncillors();
		for(int i=0; i<councillors.size(); i++){//scrorre i consiglieri

			JLabel label1 = new JLabel();
			label1.setBackground(councillors.get(i).getColor());
			label1.setName("consigliere "+i);
			label1.setToolTipText("Consigliere king numero "+(i+1));
			label1.setOpaque(true);
			label1.setPreferredSize(new Dimension(50, 20));
			lim.gridx = i*2;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
			layout.setConstraints(label1, lim);
			panel.add(label1);//aggiunta della label al panel

			JLabel label2 = new JLabel();//label per aggiungere spazio
			label2.setPreferredSize(new Dimension(5, 20));
			lim.gridx = i*2+1;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
			layout.setConstraints(label2, lim);
			panel.add(label2);//aggiunta della label al panel
		}
		panel.setOpaque(false);

		return panel;
	}

	/**
	 * find the avaiable councillors
	 * @param b, the board
	 * @return the panel with the avaiable councillors
	 */
	public JPanel colurCouncillors(Board b){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)

		Color[] color=new Setting().color();//array con i colori

		//----------label name----------
		JLabel labelCouncillors = new JLabel("Councillors Color");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=color.length*2;
		layout.setConstraints(labelCouncillors, lim);
		panel.add(labelCouncillors);//aggiunta della label al panel

		//----------colori consiglieri----------
		for(int i=0; i<color.length; i++){//scrorre i consiglieri
			int councillorNumber=councillColourcunt(color[i], b.getCouncillorPool());
			JLabel label1 = new JLabel(Integer.toString(councillorNumber), JLabel.CENTER);
			String colorName=new ColorManager().getColorName(color[i]);
			if("Black".equals(colorName)||"Violet".equals(colorName)||"Blue".equals(colorName))
				label1.setForeground(new Color(255, 255, 255));
			label1.setBackground(color[i]);
			String text="Consiglieri "+colorName+" disponibili: "+councillorNumber;
			label1.setToolTipText(text);
			label1.setOpaque(true);
			label1.setPreferredSize(new Dimension(35, 20));
			lim.gridx = i*2;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
			layout.setConstraints(label1, lim);
			panel.add(label1);//aggiunta della label al panel
			listener(label1, text);

			JLabel label2 = new JLabel();//label per aggiungere spazio
			label2.setPreferredSize(new Dimension(5, 20));
			lim.gridx = i*2+1;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
			layout.setConstraints(label2, lim);
			panel.add(label2);//aggiunta della label al panel
		}

		return panel;
	}

	private void listener(JLabel label, String text){
		label.addMouseListener(new MouseListener() {
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
				loggerArea.append("\n"+text);
			}
		});
	}

	/**
	 * calcolate the number of councillors for the color
	 * @param color, the color of councillor
	 * @param cou, the councillors list
	 * @return the number of councillors for the color you choosen
	 */
	private int councillColourcunt(Color color, List<Councillor> cou){
		int num=0;
		for(int i=0; i<cou.size(); i++){

			if(color.equals(cou.get(i).getColor()))
				num++;
		}
		return num;
	}
}
