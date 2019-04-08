/*
 * Created on Jun 19, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package app;


import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.harrier.initializeation.ConnectInit;

/**
 * @author W
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ForgotAction extends Action{
	Logger Logging = Logger.getLogger(ForgotAction.class);
	String id,zip,country,tempdob, BirthDay;
	String birthDay, birthMonth,birthYear;
	private  Logger log; 
      
	public ActionForward execute(ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response)
			throws IOException, ServletException {
			ForgotForm newForgotForm=((ForgotForm)form);	
		log =Logger.getLogger(ForgotAction.class.getName());
		 PropertyConfigurator.configure("resources/l4j3.properties");
				
		 id = newForgotForm.getId();
				
		birthDay=newForgotForm.getBirthDay();
if((birthDay.equals("1"))||(birthDay.equals("2"))||(birthDay.equals("3"))||(birthDay.equals("4"))||(birthDay.equals("5"))||(birthDay.equals("6"))||(birthDay.equals("7"))||(birthDay.equals("8"))||(birthDay.equals("9")))
		{
	birthDay="0"+birthDay.toString();
	}
		 birthMonth=newForgotForm.getBirthMonth();
		 if((birthMonth.equals("1"))||(birthMonth.equals("2"))||(birthMonth.equals("3"))||(birthMonth.equals("4"))||(birthMonth.equals("5"))||(birthMonth.equals("6"))||(birthMonth.equals("7"))||(birthMonth.equals("8"))||(birthMonth.equals("9")))
			{
		birthMonth="0"+birthMonth.toString();
		}
		 birthYear=newForgotForm.getBirthYear();
		 BirthDay=birthDay+"-"+birthMonth+"-"+birthYear;
		 Logging.debug("=============================="+BirthDay);
		 	/*birthDay=((ForgotForm)form).getBirthDay();
		 		birthMonth=((ForgotForm)form).getBirthMonth();
		 		birthYear=((ForgotForm)form)*/
	           // BirthDay=""+birthDay+""+birthMonth+""+birthYear;	 
				//id2=tempdob.substring(3,5)+"-"+tempdob.substring(0,2)+"-"+tempdob.substring(6,10);
		 		//	id2=BirthDay;
		  String id2="04-11-1978"; 
				zip=newForgotForm.getZip();
				country=newForgotForm.getCountry();
				log.debug("forgot details"+id+country+BirthDay+zip);
				ConnectInit.getConnect().getConnection();
				Logging.debug(id2);
				ResultSet rst=ConnectInit.getConnect().forgotpass("forgot_password",id,country,BirthDay,zip);
				try{ 
					if(!(rst.next())){		
						log.info("No Such User"+rst.first());
						ActionErrors errors = new ActionErrors();
					   errors.add(ActionErrors.GLOBAL_ERROR,
					   new ActionError("error.forgot.invaliduser"));
					   saveErrors(request,errors);
					   // return to input page
					   return (new ActionForward(mapping.getInput()));
					}else{
						log.info(" User found"+rst.first());
					}
					}catch(Exception e){
						log.debug("Inside Catch");
					}					
			
				return (mapping.findForward(Constants.FORGOT));
			}
			
}
