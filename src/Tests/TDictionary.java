package Tests;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import Objects.Debug;
import Objects.Dictionary;
import Objects.DictionaryRecord;

public class TDictionary
{
	

	@Test
	public void test() {
		
		String logSettingsFileName = "config\\logging.xml";		
		Debug.initDebugLog(logSettingsFileName);
		
		
		String readline = "IDMODE:Q[0-9]{2}:Q=Calls in the wait queue";
		Dictionary dictionary = new Dictionary();
		
		DictionaryRecord record = new DictionaryRecord();
		record = dictionary.getDictionaryRecord(readline);
				
		dictionary.getDictionary("config\\dictionary.txt");
		
		String oldValue = "W40";
		String newValue = oldValue.replaceAll("W", "w");
		System.out.println("newValue = " + newValue);
		System.out.println(oldValue.matches("^W[4][0]"));
		
		for (int i=0;i<dictionary.dictionaryList.size(); i++) {
			Debug.log.debug("[" + dictionary.dictionaryList.get(i).getTag() + "]");
			Debug.log.debug("[" + dictionary.dictionaryList.get(i).getFindExpression() + "]");
			Debug.log.debug("[" + dictionary.dictionaryList.get(i).getOldValue() + "]");
			Debug.log.debug("[" + dictionary.dictionaryList.get(i).getNewValue() + "]");
		}
			
		
		assertEquals("IDMODE", record.getTag());
		assertEquals("Q", record.getOldValue());
		assertEquals("Q[0-9]{2}", record.getFindExpression());
		assertEquals("Calls in the wait queue", record.getNewValue());
	}

}
