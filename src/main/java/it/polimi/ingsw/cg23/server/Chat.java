package it.polimi.ingsw.cg23.server;

import java.util.HashSet;
import java.util.Set;


import it.polimi.ingsw.cg23.observer.Observer;
import it.polimi.ingsw.cg23.server.controller.action.SendMessage;
import it.polimi.ingsw.cg23.server.view.View;

public class Chat implements Observer<SendMessage> {

	private Set<View> views;
	
	/**
	 * The constructor of the Chat. 
	 * Initializes the set of the connections.
	 */
	public Chat(){
		this.views=new HashSet<>();
	}
	
	/**
	 * Add the view in the set 
	 * @param view
	 */
	public void addView(View view){
		views.add(view);
	}
	
	/**
	 * Clear the set of the views
	 */
	public void resetViews(){
		views.clear();
	}
	/**
	 * Controls the action and performs it.
	 */
	@Override
	public  void update(SendMessage action){
		action.runAction(views);
	}

}
