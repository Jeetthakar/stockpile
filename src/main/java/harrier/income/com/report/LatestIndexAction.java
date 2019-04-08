
package harrier.income.com.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

// LatestIndexActin class for latestindex report
public class LatestIndexAction extends Action
{

/**
* Action forward class for IndexCalculator.
*/
public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)
		{
			ActionForward fr = null;
			LatestIndexForm LatestIndexForm=(LatestIndexForm) form;
			LatestIndexForm.getVector_latestdivisor();
			request.getSession().setAttribute("ci2",LatestIndexForm.getVector_latestdivisor());
		   
		   return mapping.getInputForward();
		}
}
