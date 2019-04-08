package subscription.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import subscription.dao.UserDao;
import subscription.form.UseraccountinfoForm;
import subscription.form.subscribeUserForm;

public class  ConfirmUserAction extends Action  {

	
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
		String confirm="fail";
		subscribeUserForm  urForm = (subscribeUserForm)form;
		HttpSession session=request.getSession() ;
		UserDao u =new UserDao();
		
		
		confirm=u.ConfirmUser(urForm);
		session.setAttribute("userbeen",urForm );
		session.setAttribute("user_name",urForm.getUserid());
		
		session.setAttribute("password",urForm.getPassword() );
		
		if(confirm.equalsIgnoreCase("exist"))
		{	
			session.setAttribute("exist","User with this UserId Already confirm");
		}
		

		if(confirm.equalsIgnoreCase("noexist"))
		{	
			session.setAttribute("noexist","User does not exist");
		}
		
		if(confirm.equalsIgnoreCase("datafail"))
		{	session.setAttribute("datafail","the server has acted out in an unexpected way. Hopefully, it will return to its helpful self if you try again in a few minutes.");}
		
		
		
		session.setAttribute("already_login","y");
		return (mapping.findForward(confirm) );
		
	     }
	}