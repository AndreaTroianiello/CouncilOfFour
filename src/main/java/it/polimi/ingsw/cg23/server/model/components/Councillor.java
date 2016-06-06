package it.polimi.ingsw.cg23.server.model.components;

import java.awt.Color;
import java.io.Serializable;

/**
 * The councillor can be used in a council and can be matched a politic card of the same color.
 * @author Andrea
 *
 */
public class Councillor implements Serializable {
	
	private static final long serialVersionUID = -4682589451268368747L;
	private final Color color;
	
	/**
	 * The constructor of councillor.
	 * 
	 * @param color The color of councillor.
	 */
	public Councillor(Color color){
		this.color=color;
	}
	
	/**
	 * Returns the color of the coucillor.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * It generates a string formed by the most significant statistics of the Councillor.
	 * @return string
	 */
	@Override
	public String toString() {
		return "Councillor [color=" + color + "]";
	}
	
	
}
