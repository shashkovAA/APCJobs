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
		String outXlsxFile = "out.xlsx";
		
		
		Debug.initDebugLog(logSettingsFileName);
		Debug.log.debug("=================================================================");
		Debug.log.info("Programm is started.");
		
		String jobFileName; 
		
		Dictionary dictionary = new Dictionary();
		dictionary.getDictionary("config\\dictionary.txt");

		Exel exel = new Exel();
		exel.setTemplateHeadersFileName("config\\Job_template.xlsx");
		exel.setOutJobsExelFileName(outXlsxFile);
		exel.addHeadersDataToExelFile();
		
		
		ArrayList<String> jobFilesList = new ArrayList<String>();
		jobFilesList = new JobFilesList("jobs\\", ".job").get();
		
		for (int i=0; i<jobFilesList.size(); i++) {
			jobFileName = currentPath + "\\jobs\\" + jobFilesList.get(i);
			Job job = new Job();
			job.setDictionaryList(dictionary.dictionaryList);
			HashMap<String, String> jobMap = job.getJobMap(jobFileName);
			//dictionary.applyToMap(dictionary.dictionaryList);
			exel.addJobDataToExel(jobMap);
		}
		
		Debug.log.info("Programm is finished. Processed "+ jobFilesList.size() +" JOB files. Output results writed to file :" + outXlsxFile);
		
	}

}
