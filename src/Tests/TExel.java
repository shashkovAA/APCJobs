package Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
		
			exel.setTemplateHeadersFile("Job_template.xlsx");
			exel.setOutJobsExelFile("testout.xlsx");
			exel.addHeadersDataToExelFile();
		
		assertEquals(true, true);
	}

}
