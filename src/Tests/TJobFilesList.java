/**
 * 
 */
package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Objects.Debug;
import Objects.JobFilesList;

/**
 * @author Натуся
 *
 */
public class TJobFilesList
{

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Test
	public void test()
	{
		String logSettingsFileName = "config\\logging.xml";		
		Debug.initDebugLog(logSettingsFileName);
		
		//String currentPath =  new File("").getAbsolutePath();	
		//String logSettingsFileName = currentPath + \\jobs\\
		
		JobFilesList jfl = new JobFilesList("jobs\\", ".job");
		
		assertEquals(2, jfl.get().size());
	}

}
