package it.polimi.ingsw.cg23.model.components;


public class NobilityTrack {
	
	private final NobilityBox[] nobilityBoxes;
	private final int lenght;
	
	/**
	 * the constructor creates a nobility track of a certain lenght
	 * then it initializes the box in the track
	 * 
	 * @param lenght
	 */
	public NobilityTrack(int lenght) {
		this.lenght = lenght;
		this.nobilityBoxes = new NobilityBox[lenght];
		for(int i=0; i<lenght; i++){
			nobilityBoxes[i] = new NobilityBox(i);
		}
	}

	/**
	 * @return the nobilityBoxes
	 */
	public NobilityBox[] getNobilityBoxes() {
		return nobilityBoxes;
	}

	/**
	 * @return the lenght
	 */
	public int getLenght() {
		return lenght;
	}
	
	
	

}
