package subscription.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.msgsend;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import subscription.dao.UserDao;
import subscription.form.UseraccountinfoForm;
import subscription.form.subscribeUserForm;

public class  ForgotPasswordAction extends Action  {

	
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
		String confirm="fail";
		subscribeUserForm  urForm = (subscribeUserForm)form;
		HttpSession session=request.getSession() ;
		UserDao u =new UserDao();
		String password=u.getPassword(urForm.getUserid());
		
		
		if(!password.equalsIgnoreCase("noexist") && !password.equalsIgnoreCase("datafail") )
		{
			
			
			 int num1;
			char [] s1 =password.toCharArray();
			char [] s2=password.toCharArray();
			String decodepassword = "";
			for(int i= 0 ; i < s1.length/2; i++)
			{
				num1 =s1[i]-5;
				
			//	System.out.println("after num2"+num1 );
				s2[i] = (char)num1;
				decodepassword=decodepassword+s2[i];
				
			}
			
			msgsend msg =new msgsend();	
			msg.sendpassword(urForm.getUserid(),decodepassword);
			password="success";
		}
		else {	
		if(password.equalsIgnoreCase("noexist"))
		{	
			session.setAttribute("noexist","User does not exist");
		}
		
		if(password.equalsIgnoreCase("datafail"))
		{	session.setAttribute("datafail","the server has acted out in an unexpected way. Hopefully, it will return to its helpful self if you try again in a few minutes.");}
		
		}
		
		
		
		return (mapping.findForward(password) );
		
	     }
	}