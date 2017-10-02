package Objects;

public class JobSettingsHeader
{
	public JobSettingsHeader(String tag, String overview)
	{
		super();
		this.tag = tag;
		this.overview = overview;
	}
	private String tag;
	private String overview;
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
}
