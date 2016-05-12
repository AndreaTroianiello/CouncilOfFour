package it.polimi.ingsw.cg23;

import java.awt.Color;

import org.junit.Before;

import it.polimi.ingsw.cg23.model.components.PoliticCard;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for PoliticCard.
 */
public class PoliticCardTest extends TestCase{
	
    private PoliticCard c1,c2,c3;
	
	/**
     * Create the test case.
     *
     * @param testName name of the test case
     */
    public PoliticCardTest( String testName )
    {
        super( testName );
    }
    
    /**
     * Initialize the object to test.
     */
    @Before
    public void setUp(){
    	c1=new PoliticCard(Color.RED, false);
    	c2=new PoliticCard(null, true);
    	c3=new PoliticCard(Color.BLACK, false);
    }

    public void testIsJolly(){
    	assertFalse(c1.isJolly());
    	assertTrue(c2.isJolly());
    	assertFalse(c3.isJolly());
    }
    
    public void testGetColor(){
    	assertEquals(Color.RED,c1.getColor());
    	assertNull(c2.getColor());
    	assertNotSame(Color.RED,c3.getColor());
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite= new TestSuite();
        suite.addTest(new PoliticCardTest("testIsJolly"));
        suite.addTest(new PoliticCardTest("testGetColor"));
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
