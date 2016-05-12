package it.polimi.ingsw.cg23;

import org.junit.Before;

import it.polimi.ingsw.cg23.model.components.VictoryTrack;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class VictoryTrackTest extends TestCase{
	private VictoryTrack t1,t2;
	
	public VictoryTrackTest( String testName )
    {
        super( testName );
    }
	
	@Before
	public void setUp() {
		t1=new VictoryTrack();
		t2=new VictoryTrack();
	}
	
	public void testGetVictoryPoints(){
    	assertEquals(0,t1.getVictoryPoints());
    	assertFalse(0!=t2.getVictoryPoints());
    	t1.setVictoryPoints(11);
    	t2.setVictoryPoints(0);
    	assertNotSame(0,t1.getVictoryPoints());
    	assertEquals(11,t1.getVictoryPoints());
    	assertEquals(0,t2.getVictoryPoints());
    }

	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite= new TestSuite();
        suite.addTest(new VictoryTrackTest("testGetVictoryPoints"));
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
