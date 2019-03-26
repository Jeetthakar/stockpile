package subscription.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.msgsend;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import subscription.dao.SubscriptionDao;
import subscription.form.subscriptionForm;
import app.Connect;

public class LoadCollection extends Action{
//  

	
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
       throws IOException, ServletException {
		
       String status="success";
       HttpSession session=request.getSession();
       
       subscriptionForm newtempform=((subscriptionForm)form);
		//Collection subscriprion_id_list=newtempform.getSubscriprion_id_list() ;
		Vector v =new Vector(10);
		//here is collection of subscriptionformbeen with subscription id
		v=(Vector) session.getAttribute("sub_collect");


		String resourceurl = app.Connect.getPropertiespath("Open16.gif");
		   Properties rs = new Properties();
		   rs.load(new FileInputStream(resourceurl+"resources/database.properties"));
		   String server=rs.getProperty("server");
           session.setAttribute("server",server);
		
		
		 

		 if(newtempform.getSubscriprion_id_list()!=null && newtempform.getSubscriprion_id_list().length==1)
		 {
			 int arr[]=new int[newtempform.getSubscriprion_id_list().length]; 
			 arr=newtempform.getSubscriprion_id_list();
		   for(int j=0;j<arr.length;j++)
		    {   
		    	
		    	Integer id=new Integer(arr[j]);
		    	session.setAttribute("subscribe_for",id.toString()) ;
//here we retrive the been for perticular sub_id that user selected we get it by adding
//+2 to user selected id (as in subscriptionForm)    
//can retrive It for getting subscription id

		    	subscriptionForm sub= new subscriptionForm();
		    	SubscriptionDao dao=new SubscriptionDao();
		    	 sub=(subscriptionForm) v.get(id.intValue()-2);
		    	 session.setAttribute("subscribe_for",sub) ;
		    
		    	// status=dao.checkexistuserSubscription(session.getAttribute("userid").toString(),sub.getSubscriprion_id());
		    	
		    	 /*if(status.equalsIgnoreCase("exist"))
			 		{	
			    		 session.setAttribute("subexist","You are alredy subscribe for this subscription");
			 		}*/
			    	 if(status.equalsIgnoreCase("datafail"))
				 		{	
				    		 session.setAttribute("datafail","database fail...try again");
				 		}
		    	 
		    	//status=d.AdduserSubscription("cmdehankar@gmail.com",id.toString());
		    	//System.out.println(arr[j]);
		    }
		   
		 }
		return (mapping.findForward(status) );
		
		
	}
		
}	