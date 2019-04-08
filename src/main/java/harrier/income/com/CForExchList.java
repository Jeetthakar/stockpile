/*
 * Created on Sep 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com;
import harrier.income.com.entities.CStock;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CForExchList extends ActionForm{
	private Vector col_forexCodeList;
	
	/**
	 * Constructor 
	 */
	public CForExchList() {
		// TODO Auto-generated constructor stub
		col_forexCodeList = new Vector(100,20);
		
	}
	/**
	 * @return Returns the col_forexCodeList.
	 */
	public Vector getCol_forexCodeList() {
		return col_forexCodeList;
	}
	/**
	 * @param col_forexCodeList The col_forexCodeList to set.
	 */
	public void setCol_forexCodeList(Vector col_forexCodeList) {
		this.col_forexCodeList = col_forexCodeList;
	}
}
