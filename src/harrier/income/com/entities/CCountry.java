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
public class CCountry extends ActionForm{
	private String s_cntryName;
	private String s_cntryShortName;
	private String s_continentName;
	private String s_currName;

	/**
	 * @return Returns the s_cntryName.
	 */
	public String getS_cntryName() {
		return s_cntryName;
	}
	/**
	 * @param name The s_cntryName to set.
	 */
	public void setS_cntryName(String name) {
		s_cntryName = name;
	}
	/**
	 * @return Returns the s_cntryShortName.
	 */
	public String getS_cntryShortName() {
		return s_cntryShortName;
	}
	/**
	 * @param shortName The s_cntryShortName to set.
	 */
	public void setS_cntryShortName(String shortName) {
		s_cntryShortName = shortName;
	}
	/**
	 * @return Returns the s_continentName.
	 */
	public String getS_continentName() {
		return s_continentName;
	}
	/**
	 * @param name The s_continentName to set.
	 */
	public void setS_continentName(String name) {
		s_continentName = name;
	}
	/**
	 * @return Returns the s_currName.
	 */
	public String getS_currName() {
		return s_currName;
	}
	/**
	 * @param name The s_currName to set.
	 */
	public void setS_currName(String name) {
		s_currName = name;
	}
}
