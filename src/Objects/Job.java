package Objects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Job
{
	private HashMap<String,String> JobMap;
	private  ArrayList<DictionaryRecord> dictionaryList = new ArrayList<DictionaryRecord>();
	
	public Job(){
		JobMap = new HashMap<String,String>();
	}
		
	public void printJobMap() {
		
		for (HashMap.Entry<String, String> it : JobMap.entrySet()) 
			Debug.log.debug(it.getKey() + " " + it.getValue());	   	
	}
	
	public HashMap<String,String> getJobMap(String jobFile) {		
		getJobMapFromFile(jobFile);
		
		if (dictionaryList.size() != 0 ) 
			applyDictionaryToMap();
		
		return JobMap;			
	}	
	
	

	public boolean getJobMapFromFile(String fullFileName) {
		String readline;
		JobMap.put("NAME", ConvertNames.getFileName(fullFileName));

		try (BufferedReader buffer = new BufferedReader(new FileReader(fullFileName));) {
			Debug.log.debug("---=== Reading rows from "+ ConvertNames.getFileNameWithExt(fullFileName) +" file ===---");
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
	
	
	private void applyDictionaryToMap(){
		DictionaryRecord record = new DictionaryRecord();
		String key;
		String findExpression;
		String oldValue;
		String oldValueInJobMap;
		String newValueInJobMap;
		String newValue;
		//boolean replaceResult = false;
		
		for (int i = 0; i < dictionaryList.size(); i++) {
			record = dictionaryList.get(i);
			key = record.getTag();		
			findExpression = record.getFindExpression();
			oldValue = record.getOldValue();
			newValue = record.getNewValue();
			Debug.log.debug("key =" + key + ", findExpression = " + findExpression + ", oldValue =" + oldValue + ", newValue =" + newValue);
			
			if (JobMap.get(key) != null) {
					
				if (JobMap.get(key).matches(findExpression)) {
				
					oldValueInJobMap = JobMap.get(key);
					newValueInJobMap = oldValueInJobMap.replaceAll(oldValue, newValue);
					JobMap.replace(key,oldValueInJobMap,newValueInJobMap);
					Debug.log.debug("Replace value [" + oldValue + "] to [" + newValue + "] in " + key + "." );			
				}
			}
		
		}

		
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

	public void setDictionaryList(ArrayList<DictionaryRecord> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}	
}
