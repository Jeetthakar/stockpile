/**
 * Created on Sep 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.entities;

import org.apache.struts.action.ActionForm;


/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSubIndustry extends ActionForm{
	private String s_subIndName;
	private String s_indName;
	private String s_companyName;
	
	/**
	 * @author Administrator
	 * @return boolean value depending upon the success or 
	 * failures of method
	 * addCompany function adds the company to the subIndustry
	 */
	
	public boolean addCompany(String compName){
		
		return true;
	}
	
	
	/**
	 * @return Returns the s_companyName.
	 */
	public String getS_companyName() {
		return s_companyName;
	}
	/**
	 * @param name The s_companyName to set.
	 */
	public void setS_companyName(String name) {
		s_companyName = name;
	}
	/**
	 * @return Returns the s_indName.
	 */
	public String getS_indName() {
		return s_indName;
	}
	/**
	 * @param name The s_indName to set.
	 */
	public void setS_indName(String name) {
		s_indName = name;
	}
	/**
	 * @return Returns the s_subIndName.
	 */
	public String getS_subIndName() {
		return s_subIndName;
	}
	/**
	 * @param indName The s_subIndName to set.
	 */
	public void setS_subIndName(String indName) {
		s_subIndName = indName;
	}
}
