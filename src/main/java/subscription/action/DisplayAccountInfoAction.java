package subscription.action;

import harrier.income.com.FormBean.ListTypeClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import subscription.dao.SubscriptionDao;
import subscription.dao.UserDao;
import subscription.form.UserAccountInfo;
import subscription.form.subscribeUserForm;

import app.Connect;

public class DisplayAccountInfoAction extends Action {
	 Logger Logging = Logger.getLogger(DisplayAccountInfoAction.class);
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
		
		SubscriptionDao sdao= new SubscriptionDao();
	    Vector  col=(Vector) sdao.UserAccountInfo("cmdehankar@gmail.com");         
	    Iterator i=col.iterator();
	    while(i.hasNext() )
		 {
			 
			 UserAccountInfo t=(UserAccountInfo) i.next()  ;
			 Logging.debug(t.getSubdate()+"--"+t.getSubid()+"--"+t.getSubname()+"--"+t.getValidfor() ) ;
			 i.next();	
	   	     t=null;
	   }

	    
	    return (mapping.findForward("success") );
	   
	
	
	}
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	