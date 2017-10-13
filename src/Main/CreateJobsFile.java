package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import Objects.*;


public class CreateJobsFile
{
	
	public static void main(String[] args)
	{
		String currentPath =  new File("").getAbsolutePath();	
		String logSettingsFileName = currentPath + "\\config\\logging.xml";
		
		Debug.initDebugLog(logSettingsFileName);
		Debug.log.debug("=================================================================");
		Debug.log.info("Programm is started.");
		
		String jobFileName; 
		
		Dictionary dictionary = new Dictionary();
		dictionary.getDictionary("config\\dictionary.txt");

		Exel exel = new Exel();
		exel.setTemplateHeadersFileName("config\\Job_template.xlsx");
		exel.setOutJobsExelFileName("out.xlsx");
		exel.addHeadersDataToExelFile();
		
		JobFilesList  list = new JobFilesList("jobs\\", ".job");
		ArrayList<String> jobFilesList = new ArrayList<String>();
		jobFilesList = list.get();
		
		for (int i=0; i<jobFilesList.size(); i++) {
			jobFileName = currentPath + "\\jobs\\" + jobFilesList.get(i);
			Job job = new Job();
			job.setDictionaryList(dictionary.dictionaryList);
			HashMap<String, String> jobMap = job.getJobMap(jobFileName);
			//dictionary.applyToMap(dictionary.dictionaryList);
			exel.addJobDataToExel(jobMap);
		}
		
		
	}

}
