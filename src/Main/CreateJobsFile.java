package Main;

import java.io.File;

import Objects.Debug;
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
		job.getJobMapFromFile(jobFileName);
		job.printJobMap();
		
		
	}

}
