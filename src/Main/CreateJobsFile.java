package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Objects.Debug;
import Objects.Exel;
import Objects.Job;

public class CreateJobsFile
{
	
	public static void main(String[] args)
	{
		String currentPath =  new File("").getAbsolutePath();	
		String logSettingsFileName = currentPath + "\\config\\logging.xml";
		
		Debug.initDebugLog(logSettingsFileName);
		Debug.log.info("Programm is started.");
		
		
		String jobFileName = currentPath + "\\jobs\\learningJob.job";
		Job job = new Job();
		HashMap<String, String> jobMap = job.getJobMap(jobFileName);
		//job.printJobMap();
		
		Exel exel = new Exel();
		ArrayList<String> headerList = new ArrayList<String>();
		
		try
		{
			headerList = exel.readJobsHeadersFromFileToList("Job_template.xlsx");
			exel.addHeadersDataToExel(headerList, "out.xlsx");
			exel.addJobDataToExel(jobMap,"out.xlsx");
			
		} catch (InvalidFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
