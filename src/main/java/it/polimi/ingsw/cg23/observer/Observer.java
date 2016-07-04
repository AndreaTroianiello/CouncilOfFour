package it.polimi.ingsw.cg23.observer;

/**
 * The observer observes one or more observable objects.
 * 
 * @author Andrea
 * @param <C>
 *            The type of object notification.
 */
public interface Observer<C> {

	/**
	 * Updates the observer with a object notification,this sent by an object
	 * observed.
	 * 
	 * @param o
	 *            The object notification.
	 */
	public default void update(C o) {
		System.out.println("I am the" + this.getClass().getSimpleName() + " I have been notified with the "
				+ o.getClass().getSimpleName());
	}
}
