package harrier.income.com.report;



public class Latest
{
	private String indexname;
	private String divisor;
	private String id;
	
	public Latest(String indexname,String divisor,String id)
	{
		this.divisor=divisor;
		this.indexname=indexname;
		this.id=id;
	}
	/**
	 * @return Returns the divisor.
	 */
	public String getDivisor() {
		return divisor;
	}
	/**
	 * @param divisor The divisor to set.
	 */
	public void setDivisor(String divisor) {
		this.divisor = divisor;
	}
	/**
	 * @return Returns the iD.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The iD to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the indexname.
	 */
	public String getIndexname() {
		return indexname;
	}
	/**
	 * @param indexname The indexname to set.
	 */
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
}