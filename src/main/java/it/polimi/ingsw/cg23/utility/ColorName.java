package it.polimi.ingsw.cg23.utility;

public class ColorName {
	public int r,g,b;
    public String name;

    public ColorName(String name, int r, int g, int b) {
      this.r = r;
      this.g = g;
      this.b = b;
      this.name = name;
    }

    public float computeMSE(int pixR, int pixG, int pixB) {
      //return ((pixR-r)*(pixR-r) + (pixG-g)*(pixG-g) + (pixB-b)*(pixB-b)))/3);
    	return 0;
    }
    
    public int getR() {
      return r;
    }
    
    public int getG() {
      return g;
    }
    
    public int getB() {
      return b;
    }
    
    public String getName() {
      return name;
    }
}
