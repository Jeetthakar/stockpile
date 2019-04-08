/*
 * Created on Sep 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.entities;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CIndustryGroup extends ActionForm{
	
	private String s_indGrpName;
	private String s_sectorName;
	private String s_indName;
	
	/**
	 * 
	 */
	public CIndustryGroup() {
		// TODO Auto-generated constructor stub
	}	
	
	/**
	 * @return Returns the s_indGrpName.
	 */
	public String getS_indGrpName() {
		return s_indGrpName;
	}
	/**
	 * @return Returns the s_indName.
	 */
	public String getS_indName() {
		return s_indName;
	}
	/**
	 * @return Returns the s_sectorName.
	 */
	public String getS_sectorName() {
		return s_sectorName;
	}
	/**
	 * @param grpName The s_indGrpName to set.
	 */
	public void setS_indGrpName(String grpName) {
		s_indGrpName = grpName;
	}
	/**
	 * @param name The s_indName to set.
	 */
	public void setS_indName(String name) {
		s_indName = name;
	}
	/**
	 * @param name The s_sectorName to set.
	 */
	public void setS_sectorName(String name) {
		s_sectorName = name;
	}
}
