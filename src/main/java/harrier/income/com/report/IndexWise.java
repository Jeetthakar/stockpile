package harrier.income.com.report;



public class IndexWise
{
	private String industryname;
	private String marketcap;
	private String weightage ;
	
	public IndexWise(String industryname,String marketcap,String weightage)
	{
		this.industryname=industryname;
		this.marketcap=marketcap;
		this.weightage=weightage;
	}
	/**
	 * @return Returns the industryname.
	 */
	public String getIndustryname() {
		return industryname;
	}
	/**
	 * @param industryname The industryname to set.
	 */
	public void setIndustryname(String industryname) {
		this.industryname = industryname;
	}
	/**
	 * @return Returns the marketcap.
	 */
	public String getMarketcap() {
		return marketcap;
	}
	/**
	 * @param marketcap The marketcap to set.
	 */
	public void setMarketcap(String marketcap) {
		this.marketcap = marketcap;
	}
	/**
	 * @return Returns the weightage.
	 */
	public String getWeightage() {
		return weightage;
	}
	/**
	 * @param weightage The weightage to set.
	 */
	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}
}