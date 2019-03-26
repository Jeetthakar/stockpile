/*
 * Created on Sep 9, 2004
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
public class CSector extends ActionForm{
	private String s_sectName;
	private String s_indGrpName;
	

	/**
	 * @return Returns the s_indGrpName.
	 */
	public String getS_indGrpName() {
		return s_indGrpName;
	}
	/**
	 * @param grpName The s_indGrpName to set.
	 */
	public void setS_indGrpName(String grpName) {
		s_indGrpName = grpName;
	}
	/**
	 * @return Returns the s_secName.
	 */
	public String getS_sectName() {
		return s_sectName;
	}
	/**
	 * @param name The s_secName to set.
	 */
	public void setS_sectName(String name) {
		s_sectName = name;
	}
	
	public boolean addIndGrp(String indgrpName){
		return true;
	}
	
	public ActionErrors validate(ActionMapping mapping,
			 HttpServletRequest request) {
		ActionErrors error = new ActionErrors();
		char temp[] = new char[s_sectName.toCharArray().length];
		if(s_sectName==null||s_sectName.trim().equals(""))
			error.add("s_sectName",
					new ActionError("error.sectName.required"));
		if(s_indGrpName==null||s_indGrpName.trim().equals(""))
			error.add("s_indGrpName",
					new ActionError("error.s_indGrpName.required"));
		if(temp.length!=0){
			for(int i=0;i<temp.length;i++){
				if(Character.isDigit(temp[i]))
					error.add("s_sectName",new ActionError(
							"error.field.digit"));
				break;
			}			
		}
		return error;
	}
}
