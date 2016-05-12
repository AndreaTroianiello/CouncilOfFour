package it.polimi.ingsw.cg23;

import org.junit.Before;

import it.polimi.ingsw.cg23.model.components.AssistantsPool;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AssistantsPoolTest extends TestCase{
	private AssistantsPool p1,p2;
	
	public AssistantsPoolTest( String testName )
    {
        super( testName );
    }
	
	@Before
	public void setUp() {
		p1=new AssistantsPool(0);
		p2=new AssistantsPool(10);
	}
	
	public void testGetAssistants(){
    	assertEquals(0,p1.getAssistants());
    	assertNotSame(0,p2.getAssistants());
    	assertNotNull(p2.getAssistants());
    	p1.setAssistants(11);
    	p2.setAssistants(5);
    	assertNotSame(0,p1.getAssistants());
    	assertEquals(5,p2.getAssistants());
    }

	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite= new TestSuite();
        suite.addTest(new AssistantsPoolTest("testGetAssistants"));
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

