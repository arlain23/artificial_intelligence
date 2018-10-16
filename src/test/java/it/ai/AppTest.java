package it.ai;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
/*
 board  | number of moves | solution(s)
123405786	2		RD
123745086	4		URRD
123480765	5		DLURD
413726580	8		LLUURDDR
162530478	9		LURDLLDRR
512630478	11		LLURRDLLDRR
126350478	13		ULDLDRRULURDD
356148072	16		RRUULLDRDRUULDRD
436871052	18		URRULDDRULDLUURDRD
302651478	21		DRULDLURRDLLDRRULURDD or DLURDRULDLURDRULDLDRR
012345678	22		RDLDRRULLDRUURDDLLURRD or DRRULLDDRUURDLLURRDLDR
503284671	23		LDDRRULLDRRULLDRUULDDRR
874320651	25		DLULURDRULDDLUURDRDLLURRD
876543021	28		UURDRDLLUURDRULDDRULDLUURDRD or UURDLDRURDLLUURDRULDDLUURDDR
876543210
	
 */
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    public void testApp()
    {
        assertTrue( true );
    }
    
}
