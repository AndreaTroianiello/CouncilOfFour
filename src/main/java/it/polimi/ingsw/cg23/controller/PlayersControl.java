package it.polimi.ingsw.cg23.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.view.ServerSocketView;

public class PlayersControl implements Runnable {

	private int index;
	private Controller controller;
	private static Logger logger;

	public PlayersControl(int index,Controller controller) {
		this.index=index;
		this.controller=controller;
		logger = Logger.getLogger(ServerSocketView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	@Override
	public void run() {
		boolean run=true;
		while(run){
			try {
				if(controller.getPlayersNumber()==index){
					controller.startGame();
					run=false;
				}
				else
					Thread.sleep(2000);
			} catch (InterruptedException e) {
				logger.error(e);
			}

		}
	}

}
