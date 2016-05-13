package it.polimi.ingsw.cg23.observer;

public interface Observer {
	
		public void update();
		public <C> void update(C change);
}
