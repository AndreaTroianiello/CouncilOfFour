package it.polimi.ingsw.cg23;

import org.junit.Before;

import it.polimi.ingsw.cg23.model.components.Richness;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RichnessTest extends TestCase{
	private Richness r1,r2;
	
	public RichnessTest( String testName )
    {
        super( testName );
    }
	
	@Before
	public void setUp() {
		r1=new Richness(0);
		r2=new Richness(10);
	}
	
	public void testGetCoins(){
    	assertEquals(0,r1.getCoins());
    	assertNotSame(0,r2.getCoins());
    	assertNotNull(r2.getCoins());
    	r1.setCoins(11);
    	r2.setCoins(0);
    	assertNotSame(0,r1.getCoins());
    	assertEquals(0,r2.getCoins());
    }

	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite= new TestSuite();
        suite.addTest(new RichnessTest("testGetCoins"));
        return suite;
    }
    
    /**
     * Run the suite of tests.
     * 
     * @param args
     */
    public static void main(String args[]){
    	junit.textui.TestRunner.run(suite());
    }

}
