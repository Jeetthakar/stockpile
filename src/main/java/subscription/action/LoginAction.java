package subscription.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import subscription.dao.SubscriptionDao;
import subscription.dao.UserDao;
import subscription.form.UserAccountInfo;
import subscription.form.UseraccountinfoForm;
import subscription.form.subscribeUserForm;

public class LoginAction  extends Action  {

	
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
		String status="fail";
		subscribeUserForm  urForm1 = (subscribeUserForm)form;
		
////	TEMP CODE FOR ADMIN
		
		UserDao u =new UserDao();
		HttpSession session=request.getSession() ;
		if(session.getAttribute("already_login")!=null)
		{	subscribeUserForm  urForm2=new subscribeUserForm();
			urForm2.setUserid((String)session.getAttribute("user_name"));
			//urForm2.setUserid("");
			urForm2.setPassword((String)session.getAttribute("password"));
			//urForm2.setPassword("" );
		    status=u.UserLogin(urForm2,session ) ;
		    session.setAttribute("userbeen",urForm2 );
		}
		else	
		{	
			
			
			
			
			status=u.UserLogin(urForm1,session ) ;
			session.setAttribute("userbeen",urForm1 );
	
		if(status.equalsIgnoreCase("noexist"))
		{	session.setAttribute("noexist","invalid Username/Password");}
		
		if(status.equalsIgnoreCase("datafail"))
		{	session.setAttribute("datafail","Server fail..");}
				
		if(status.equalsIgnoreCase("success" ))
		{
			session.setAttribute("user_name",urForm1.getUserid() );
			session.setAttribute("password",urForm1.getPassword());
		}   
			
		}	
			
		///////////     upload user data                         /////////////
		/*if(status.equals("success"))
		{
		SubscriptionDao sdao= new SubscriptionDao();
	    Vector  col=(Vector) sdao.UserAccountInfo("cmdehankar@gmail.com");         
	    Iterator i=col.iterator();
	    while(i.hasNext() )
		 {
			 
			 UserAccountInfo t=(UserAccountInfo) i.next()  ;
			 System.out.println(t.getSubdate()+"--"+t.getSubid()+"--"+t.getSubname()+"--"+t.getValidfor() ) ;
			
	   	     t=null;
	   }
 
		
		}*/
		///////////  upload user data      /////////////
		
		return (mapping.findForward(status) );
		
	     }
	}