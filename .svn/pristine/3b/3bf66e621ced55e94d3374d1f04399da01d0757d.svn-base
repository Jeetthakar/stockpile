package subscription.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.msgsend;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import subscription.dao.UserDao;
import subscription.form.subscribeUserForm;
import sysconfig.action.UserRolesForm;

//import app.Constants;

public class  UserRegistrationAction extends Action  {

	
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException, SQLException {
		
		String status="success";
		HttpSession session=request.getSession() ;
		
		subscribeUserForm  urForm 	= 	(subscribeUserForm)form;
	
		session.setAttribute("emailid",urForm.getUserid() );
		
		UserDao u =new UserDao();
		
		status=u.saveUser(urForm);
		session.setAttribute("userbeen",urForm );
		
		
		if(status.equalsIgnoreCase("exist"))
		{	session.setAttribute("exist","User with this UserId Already Exist");}
		
		if(status.equalsIgnoreCase("datafail"))
		{	session.setAttribute("datafail","the server has acted out in an unexpected way. Hopefully, it will return to its helpful self if you try again in a few minutes.");}
		
		
		if(status.equalsIgnoreCase("datafail")==false )
		{
			if(status.equalsIgnoreCase("exist")==false)
			{
				try{
					//UserDao u =new UserDao();
					//u.saveUser(urForm);
					msgsend msg =new msgsend();		
					msg.sendmail(urForm.getFirstname(), urForm.getUserid(),urForm.getPassword()  );
					String s=urForm.getUserid();
					int i=s.indexOf('@');
					String webmail="www."+s.substring(i+1,s.length() );
					session.setAttribute("usermail",webmail);
				
				   }
				catch(Exception e)
				{
					status="fail";
					//return (mapping.findForward("fail") );
					
				}
				
			}
		}
		
		
		
		
		
		
		
		/*if(status.equalsIgnoreCase("datafail")&& status.equalsIgnoreCase("exist") )
		
			try{
			//UserDao u =new UserDao();
			//u.saveUser(urForm);
			msgsend msg =new msgsend();		
			msg.sendmail(urForm.getUserid() );
			}
		catch(Exception e)
		{
			status="fail";
			//return (mapping.findForward("fail") );
			
		}*/
		
		
		
		
		
		return (mapping.findForward(status) );
		
		
	}
		
		
	
}
