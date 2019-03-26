/*
 * Created on Jun 30, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DynamicTableForm extends ActionForm 
{
	Logger Logging = Logger.getLogger(DynamicTableForm.class);
	public String sqltext;
	public static String sqlerror;
	public static int parse_error;
	
	 
	/**
	 * @return Returns the sqltext.
	 */
	public String getSqltext() {
		return sqltext;
	}
	/**
	 * @param sqltext The sqltext to set.
	 */
	public void setSqltext(String sqltext) {
		Logging.debug("in set.....");
		this.sqltext = sqltext;
	}
	
	
	public ArrayList ar1;
	
	
	/**
	 * @return Returns the ar1.
	 */
	public ArrayList getAr1() {
		return ar1;
	}
	/**
	 * @param ar1 The ar1 to set.
	 */
	public void setAr1(ArrayList ar1) {
		this.ar1 = ar1;
	}

	/**
	 * @return Returns the parse_error.
	 */
	public int getParse_error() {
		return parse_error;
	}
	/**
	 * @param parse_error The parse_error to set.
	 */
	public void setParse_error(int parse_error) {
		this.parse_error = parse_error;
	}
	/**
	 * @return Returns the sqlerror.
	 */
	public String getSqlerror() {
		return sqlerror;
	}
	/**
	 * @param sqlerror The sqlerror to set.
	 */
	public void setSqlerror(String sqlerror) {
		this.sqlerror = sqlerror;
	}
}
