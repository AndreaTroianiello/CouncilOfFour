package it.polimi.ingsw.cg23.server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.controller.Controller;
import it.polimi.ingsw.cg23.controller.PlayersControl;
import it.polimi.ingsw.cg23.view.ServerSocketView;

public class NewGame implements Runnable{
	
	private Server server;
	private static Logger logger;
	
	public NewGame(Server server){
		this.server=server;
		logger = Logger.getLogger(ServerSocketView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(20000);
			int index=server.getIndex();
			Controller controller=server.getController();
			server.resetIndex();
			new Thread(new PlayersControl(index,controller)).start();
		} catch (InterruptedException e) {
			logger.error(e);
		}

	}

}
