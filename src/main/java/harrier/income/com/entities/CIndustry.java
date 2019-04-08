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
public class CIndustry extends ActionForm{
	private String s_indName;
	private String s_indGrpName;
	private String s_subIndName;
	
	
	
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
	 * @return Returns the s_subIndName.
	 */
	public String getS_subIndName() {
		return s_subIndName;
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
	 * @param indName The s_subIndName to set.
	 */
	public void setS_subIndName(String indName) {
		s_subIndName = indName;
	}
	
	public boolean addSubInd(String subIndName){
		return true;//false for failure
	}
}
