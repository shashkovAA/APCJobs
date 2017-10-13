package Objects;

public class DictionaryRecord
{
	private String tag = "blank";
	private String findExpression = "*"; 
	private String oldValue = "blank";
	private String newValue = "blank";
	
	public DictionaryRecord() {}
	public DictionaryRecord(String tag, String findExpression, String oldValue, String newValue) {
		if (tag.length() != 0)
			this.tag = tag;
		if (findExpression.length() != 0)
			this.findExpression = findExpression;
		if (oldValue.length() != 0)
			this.oldValue = oldValue;
		if (newValue.length() != 0)
			this.newValue = newValue;		
	}

	public String getTag(){
		return tag;
	}

	public String getOldValue(){
		return oldValue;
	}

	public String getNewValue(){
		return newValue;
	}
	
	public String getFindExpression() {
		return findExpression;
	}

}
