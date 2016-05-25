package it.polimi.ingsw.cg23.observer;

@FunctionalInterface
public interface Observer<C> {

	public default void update(C o) {
		System.out.println("I am the" + this.getClass().getSimpleName() +
				" I have been notified with the "+o.getClass().getSimpleName());
	}

	public void update();
}

