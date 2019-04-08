package harrier.income.com.report;

public class SectorWeightage {
	private String sectorName;
	private String weightage;
	

	
	public SectorWeightage(String sectorName,String weightage)
	{
		this.sectorName=sectorName;
		this.weightage=weightage;
			
	}
	
	
	/**
	 * @return Returns the sectorName.
	 */
	
	public String getSectorName() {
		return sectorName;
	}


	/**
	 * @param sectorName The sectorName to set.
	 */
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
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
