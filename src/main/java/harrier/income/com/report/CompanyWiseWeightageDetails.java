/*
 * Created on Feb 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CompanyWiseWeightageDetails {
String company_name=null;
String mcap=null;
String weightage=null;


	/**
	 * @param company_name
	 * @param mcap
	 * @param weightage
	 */
	public CompanyWiseWeightageDetails(String company_name, String mcap,
			String weightage) {
		
		this.company_name = company_name;
		this.mcap = mcap;
		this.weightage = weightage;
	}
/**
 * @return Returns the company_name.
 */
public String getCompany_name() {
	return company_name;
}
/**
 * @param company_name The company_name to set.
 */
public void setCompany_name(String company_name) {
	this.company_name = company_name;
}
/**
 * @return Returns the mcap.
 */
public String getMcap() {
	return mcap;
}
/**
 * @param mcap The mcap to set.
 */
public void setMcap(String mcap) {
	this.mcap = mcap;
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
/**
 * @return Returns the indexId.
 */

}
