/*
 * Created on Feb 14, 2005
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
import sysconfig.model.*;

/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class ActivityAction extends Action{
	
	public Activity activity;
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		
		/**
		 * If Reset Button Or Selction Of Activity Occure then Return To Main Page 
		 * */

		ActivityForm aForm	= 	(ActivityForm)form;
		
		String operation	=	aForm.getOperation();
		if((operation!=null && operation.equals("Reset")) ){
			aForm.setActivityName(null);
			aForm.setActivityDescription(null);
			aForm.setActivityCode(null);
			aForm.setSelectActivityName("value0");
			return (new ActionForward("/pages/activitiesView.jsp"));
		}
		else if(operation!=null && operation.equals("changeFields")){
			activity			=	new Activity();
			String sIName			=	aForm.getSelectActivityName().split("e")[1];
			int numIndex			=	Integer.parseInt(sIName);
			activity.getnameDescriptionCode(numIndex);
			aForm.setActivityName(activity.name);
			aForm.setActivityDescription(activity.des);
			aForm.setActivityCode(activity.code);
			
			return (new ActionForward("/pages/activitiesView.jsp"));
		}
				
		/**
		 * Call The Constructor SysConfig To Make Datsbase Entries  
		 * **/
		activity			=	new Activity();
		int val				=	activity.AddActivity(aForm.getSelectActivityName(),aForm.getActivityName(),aForm.getActivityDescription(),aForm.getActivityCode());
		if(activity.activityAdded==1){
			aForm.setSelectActivityName("value"+val);
			aForm.setOperation("Reset");
			return (new ActionForward("/pages/roleAdded.jsp?flag=activityRoles&value=insert"));
		}
		if(activity.activityAdded==0){
			
			return (new ActionForward("/pages/roleAdded.jsp?flag=activityRoles&value=update"));
		}
		return  mapping.getInputForward();
	}

}
