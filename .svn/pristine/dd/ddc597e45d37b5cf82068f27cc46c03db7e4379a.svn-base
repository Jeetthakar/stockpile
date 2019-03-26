package subscription.action;

import harrier.income.com.FormBean.ListTypeClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.msgsend;

import org.apache.log4j.Logger;
import org.apache.struts.actions.DispatchAction;

import org.apache.struts.action. Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import subscription.dao.SubscriptionDao;
import subscription.dao.UserDao;
import subscription.form.UseraccountinfoForm;
import subscription.form.subscribeUserForm;
import subscription.form.subscriptionForm;
import subscription.form.CancelForm; 
import app.Connect;

public class cancelsubscriptionAction extends DispatchAction
{
//  

	 Logger Logging = Logger.getLogger(cancelsubscriptionAction.class);
	public ActionForward Go(ActionMapping mapping,
            ActionForm form,     
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
		
       String status="success";
       HttpSession session=request.getSession();
       
       CancelForm newtempform=((CancelForm)form);
       Collection c=newtempform.getUseraccountinfo();
       session.setAttribute("cancelinfo",c);
       Iterator i1=c.iterator();
       session.setAttribute("tempbeen",newtempform );
       CancelForm ct=(CancelForm)session.getAttribute("tempbeen");
       Logging.debug(ct.getFromdate()+"---"+ct.getUsername());
      while(i1.hasNext() )
       {
    	   CancelForm c1=(CancelForm)i1.next();
    	   Logging.debug(c1.getCanceldate()+" "+c1.getFromdate()+""+c1.getUsername() );    	   
        c1=null;
       } 
    	   
    	    
		return (mapping.findForward(status) );
		
		
	}
	
	
	
	
	public ActionForward cancel(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
		
       String status="success";
       HttpSession session=request.getSession();
       CancelForm newtempform=((CancelForm)form);
       //CancelForm newtempform =(CancelForm)session.getAttribute("tempbeen");
       int[] arr=newtempform.getOrder_id_list();
	   UserDao u= new UserDao();
	   status=u.CancelSubscription(arr);
		
       
	   if(status.equalsIgnoreCase("success" ))
		{
			session.setAttribute("done","subscription cancel successfuly.." );
			session.setAttribute("userbeen","ok");
		}     
	   if(status.equalsIgnoreCase("datafail" ))
		{
			session.setAttribute("ddatafailone","database fail.." );
			session.setAttribute("userbeen","ok");
		}      
	   
	   if(status.equalsIgnoreCase("notexist" ))
		{
			session.setAttribute("notexist","This subscription not exist for users" );
			session.setAttribute("userbeen","ok");
		}    
		return (mapping.findForward(status) );
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}	