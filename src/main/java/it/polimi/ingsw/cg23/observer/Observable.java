package it.polimi.ingsw.cg23.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The obsrvable is observed by one or more observer objects.
 * @author Andrea
 * @param <C> The type of object notification.
 */
public abstract class Observable<C> {

	private List<Observer<C>> observers;
	
	/**
	 * The contructor of Observable. Initializes the list of observers.
	 */
	public Observable(){
		this.observers=new ArrayList<>();
	}
	
	/**
	 * Keeps track of the observer.
	 * @param o The observer of the object.
	 */
	public void registerObserver(Observer<C> o){
		this.observers.add(o);
	}
	
	/**
	 * Clears the observer's registration.
	 * @param o The observer of the object.
	 */
	public void unregisterObserver(Observer<C> o){
		this.observers.remove(o);
	}
	
	/**
	 * Notifies the observers with the object.
	 * @param c The object to be notified.
	 */
	public void notifyObserver(C c){
		System.out.println("I am the "+this.getClass().getSimpleName());
		for(Observer<C> o: this.observers){
			o.update(c);
		}
	} 
}
	
