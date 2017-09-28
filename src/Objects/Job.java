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
	
	/*public void getJobMapFromFile() {
		JobMap.put("value1", "test");
		JobMap.put("value2", "test2");
		JobMap.put("value3", "test3");
			
	}*/
	
	
	public void printJobMap() {
		
		for (HashMap.Entry<String, String> it : JobMap.entrySet()) 
			Debug.log.debug(it.getKey() + " " + it.getValue());	   	
	}
	
	public void getJobMapFromFile(String fileName) {
		String readline;
		
		try (BufferedReader buffer = new BufferedReader(new FileReader(fileName));) {
		    while ((readline = buffer.readLine()) != null) {
		    	Debug.log.info(readline);
		    	JobMap.put(getJobSettingsField(readline).getName(), getJobSettingsField(readline).getValue());
		    	Debug.log.debug("[name] = " + getJobSettingsField(readline).getName());
		    	Debug.log.debug("[value] = " + getJobSettingsField(readline).getValue());
		    }
		} catch (FileNotFoundException e) {
		    Debug.log.error(e.getMessage());
		    
		} catch (IOException e) {
		    Debug.log.error(e.getMessage());
		    
		}
	}
	
	public JobSettingsField getJobSettingsField(String line) {
		int semicolonIndex = line.indexOf(':');
		
		JobSettingsField josefi = new JobSettingsField();
		josefi.setName(line.substring(0, semicolonIndex));
		josefi.setValue(line.substring(semicolonIndex + 1, line.length()-1));
		
		return josefi;
	}
	

	
	
	
}
