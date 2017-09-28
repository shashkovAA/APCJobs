package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.Job;
import Objects.JobSettingsField;


public class TJob
{
	private Job job = new Job();	

	@Before
    public void setUp() throws Exception {

		//String logSettingsFileName = "config\\logging.xml";		
		//Debug.initDebugLog(logSettingsFileName);
 	
    }

	@After
	public void tearDown() throws Exception {
	
	}
	
	@Test
	public void test1()
	{
		
		assertEquals(job.getJobSettingsField("CONTROL:YES:").getName(), "CONTROL");	
	}
	@Test
	public void test2()
	{
		
		assertEquals(job.getJobSettingsField("CONTROL:YES:").getValue(), "YES");	
	}
	
	@Test
	public void test3()
	{
	
		assertEquals(job.getJobSettingsField("CONTROL::").getValue(), "");	
	}


}
