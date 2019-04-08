package subscription.action;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import subscription.dao.SubscriptionDao;
import subscription.form.subscriptionForm;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class SubscriptiondataAction  extends Action{

	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
	
		String status="success";
        HttpSession session=request.getSession();
        SubscriptionDao d=new SubscriptionDao();
		subscriptionForm sub= new subscriptionForm();
		sub=(subscriptionForm) session.getAttribute("subscribe_for");
		session.setAttribute("subscribe_for",sub) ;
		Connection con=null;	
		Connect c=ConnectInit.getConnect();
		con=c.getdbConnection(); 
		
		
		//status=d.AdduserSubscription((String)session.getAttribute("userid"),sub.getSubscriprion_id());    
		status=d.AdduserSubscription((String)session.getAttribute("userid"),sub.getSubscriprion_id(),con);
		session.setAttribute("already_login","y");
		return (mapping.findForward(status) );
		
	}
		
}	