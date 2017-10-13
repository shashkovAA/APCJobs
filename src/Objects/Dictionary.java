package Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Dictionary
{	
	
	public ArrayList<DictionaryRecord> dictionaryList = new ArrayList<DictionaryRecord>();
	
	public void getDictionary(String filename) {
			
		String readline;
		File file = new File(filename);
		
		try (BufferedReader buffer = new BufferedReader(new FileReader(file));) {
			while ((readline = buffer.readLine()) != null) {
		    	if (!readline.startsWith("#")) {
		    		Debug.log.debug("ReadLine from dictionary file [" +readline + "]");
		    		dictionaryList.add(getDictionaryRecord(readline));
		    	}
		    }

		} catch (FileNotFoundException evt) {
		    Debug.log.error(evt.getMessage());
		} catch (IOException ev) {
		    Debug.log.error(ev.getMessage());
		}

	}
	
	public DictionaryRecord getDictionaryRecord(String fileLine) {
		String tag = fileLine.substring(0, fileLine.indexOf(':'));
		String findExpression = fileLine.substring((fileLine.indexOf(':') + 1), (fileLine.lastIndexOf(':')));
		String oldValue = fileLine.substring((fileLine.lastIndexOf(':') + 1), fileLine.indexOf('='));
		String newValue = fileLine.substring((fileLine.indexOf('=') + 1));
		return new DictionaryRecord(tag,findExpression,oldValue,newValue);
		
		
	}
}
