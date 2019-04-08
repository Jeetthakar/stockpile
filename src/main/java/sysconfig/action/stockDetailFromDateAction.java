/*
 * Created on May 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class stockDetailFromDateAction extends Action{
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		stockDetailFromDateForm sdf=(stockDetailFromDateForm)form;
		String filter=sdf.getFilter();
		String select=sdf.getSelectDemo();
		String clear=sdf.getClear();
		//System.out.println("value is yessssssssssssssssss: "+clear);
		if(filter.equals("0") ){
			sdf.setSelect("0");
			sdf.setText(null);
			sdf.setSelectStock(null);
			sdf.setFromDate(null);
			sdf.setTableDate(null);
		}
		else if(select.equals("0") ){
			sdf.setSelect("0");
			sdf.setText(null);
			sdf.setSelectStock(null);
			sdf.setFromDate(null);
			sdf.setTableDate(null);
		}
		else if(!(clear.equals("yes"))){
		//	System.out.println("value is yessssssssssssssssss");
			sdf.setFromDate(null);
			sdf.setTableDate(null);
		}
		return  mapping.getInputForward();
		//return (new ActionForward("/pages/stockDetailFromDate.jsp"));
	}
}
