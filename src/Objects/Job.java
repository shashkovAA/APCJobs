package Objects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Job
{
	private HashMap<String,String> JobMap; 
	
	public Job(){
		JobMap = new HashMap<String,String>();
	}
		
	public void printJobMap() {
		
		for (HashMap.Entry<String, String> it : JobMap.entrySet()) 
			Debug.log.debug(it.getKey() + " " + it.getValue());	   	
	}
	
	public HashMap<String,String> getJobMap(String jobFile) {
		
		if (getJobMapFromFile(jobFile))
				return JobMap;
		else return new HashMap<String,String>();
	}	
	
	public boolean getJobMapFromFile(String fullFileName) {
		String readline;
		JobMap.put("NAME", ConvertNames.getFileName(fullFileName));
		
		try (BufferedReader buffer = new BufferedReader(new FileReader(fullFileName));) {
			Debug.log.debug("---=== Reading rows from Job_Template file ===---");
			while ((readline = buffer.readLine()) != null) {
		    	Debug.log.debug(readline);
		    	JobMap.put(getJobSettingsField(readline).getName(), getJobSettingsField(readline).getValue());
		    	Debug.log.debug("[name] = " + getJobSettingsField(readline).getName());
		    	Debug.log.debug("[value] = " + getJobSettingsField(readline).getValue());
		    }
		} catch (FileNotFoundException e) {
		    Debug.log.error(e.getMessage());
		    return false;
		    
		} catch (IOException e) {
		    Debug.log.error(e.getMessage());
		    return false;
		}
		return true;
	}
	
	public JobSettingsField getJobSettingsField(String line) {
		int semicolonFirstIndex = line.indexOf(':');
		int semicolonLastIndex = line.lastIndexOf(':');
		
		JobSettingsField josefi = new JobSettingsField();
		josefi.setName(line.substring(0, semicolonFirstIndex));
		if (semicolonLastIndex - semicolonFirstIndex > 1  )
			josefi.setValue(line.substring(semicolonFirstIndex + 1, semicolonLastIndex));
		else josefi.setValue("");
		
		return josefi;
	}	
}
