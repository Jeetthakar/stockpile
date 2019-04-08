
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

import subscription.dao.UserDao;
import subscription.form.UseraccountinfoForm;
import app.Connect;

import com.harrier.initializeation.ConnectInit;


public class CancelationdataAction  extends Action{

	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
	
		String status="success";
        HttpSession session=request.getSession();
        UseraccountinfoForm sub=(UseraccountinfoForm)form;
		//int l=sub.getOrder_id_list().length;
		int[] arr=sub.getOrder_id_list();
		UserDao u= new UserDao();
		status=u.insertCanceldata(arr,session.getAttribute("userid").toString(),session.getAttribute("user_name").toString());
		
		session.setAttribute("subscribe_for",sub) ;
		Connection con=null;	
		Connect c=ConnectInit.getConnect();
		con=c.getdbConnection(); //order_id_list
		
		session.setAttribute("already_login","y");
		return (mapping.findForward(status) );
		
	}
		
}	