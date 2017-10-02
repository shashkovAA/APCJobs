package Tests;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import Objects.Debug;
import Objects.Exel;

public class TExel
{
	@Test
	public void test()
	{
		String logSettingsFileName = "config\\logging.xml";		
		Debug.initDebugLog(logSettingsFileName);
		
		Exel exel = new Exel();
		
			//exel.setTemplateHeadersFile("Job_template.xlsx");
			//exel.setOutJobsExelFile("testout.xlsx");
			//exel.addHeadersDataToExelFile();
		exel.createWorkBook();
		
		assertEquals(true, true);
	}

}
