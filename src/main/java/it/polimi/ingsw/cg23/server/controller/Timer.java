package it.polimi.ingsw.cg23.server.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.view.View;

public class Timer implements Runnable {

	private View view;
	private static Logger logger;
	private Controller controller;

	/**
	 * The constructor of PlayersControl.
	 * @param index The number of all views.
	 * @param controller The game's controller.
	 */
	public Timer(View view,Controller controller) {
		this.view=view;
		this.controller=controller;
		logger = Logger.getLogger(Timer.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * The method performed by the thread. It controls if the number of players is equals than the number of views.
	 */
	@Override
	public void run() {
		try {
			System.out.println(1);
			Thread.sleep(120000);
			if(view.getSuspended()){
				System.out.println(2);
				new EndTurn().runAction(controller.getTurn());
			}
			System.out.println(3);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}

}
