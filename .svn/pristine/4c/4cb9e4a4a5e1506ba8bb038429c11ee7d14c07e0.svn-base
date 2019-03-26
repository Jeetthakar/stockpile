/*
 * Created on Aug 21, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
public class ChangepassAction extends Action {
	Logger Logging = Logger.getLogger(ChangepassAction.class);
	String username,password;//oldPassword
	String password1;//newPassword
	String encryOld="";
	String encryNew="";
	public ActionForward execute(ActionMapping mapping,ActionForm form,
				   HttpServletRequest request,
				   HttpServletResponse response)
			   throws IOException, ServletException {
		   	
	username=((ChangepassForm)form).getLoginName();
	password=((ChangepassForm)form).getOldPassword();
	password1=((ChangepassForm)form).getNewPassword();
	encryOld = Encript(password);
	encryNew = Encript(password1);
	Connect c=ConnectInit.getConnect();
	Connection connection=null;			
	try{	
		if(connection==null)
			connection=c.getdbConnection();
	//ConnectInit.getConnect().getConnection();
	ResultSet rst=ConnectInit.getConnect().changepass("1000",username,encryNew,encryOld,connection);
	try{	Logging.debug(username+password+password1+"1");
		Logging.debug(rst.first());
		Logging.debug(username+password+password1+"1");
	if(rst.next()){
		
	}else{
		ActionErrors errors=new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
				new ActionError("error.newuser.wrong"));
		   saveErrors(request,errors);
		   return (new ActionForward(mapping.getInput()));
	}
	}catch(Exception e){}
	Logging.debug("before");
	
	return (mapping.findForward(Constants.CHANGED));
	}
	finally{
		try{
			if(connection!=null)
				connection.close();
		}catch(Exception ee){
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ex){
				Logging.error("Error : Unable to close Connection "+ex.getMessage());
			}
			Logging.error("Error : Unable to close Connection "+ee.getMessage());
		}
	}
	}
	String Encript(String str)
	{
		/**
		 * Encription : The password string is concatenated with the same string again
		 * it is then converted as first half of the string str1 characters are added with 5 to there 
		 * ascii values and rest half string str2 characters are added by 7 in there ascii values.
		 */
		String ret_str= null;
		String str1 = "";
		String str2 =  "" ;
		int num;
		char [] inp_str1 = str.toCharArray();
		char [] inp_str2 = str.toCharArray();
		for(int i= 0 ; i < inp_str1.length; i++)
		{
			num = inp_str1[i] + 5;
			inp_str1[i] = (char)num;
		}
		for(int i= 0 ; i < inp_str1.length; i++)
		{
			str1 = str1 + inp_str1[i]; 
		}
		for(int i= 0 ; i < inp_str2.length; i++)
		{
			num = inp_str2[i] + 7;
			inp_str2[i] = (char)num;
		}
		for(int i= 0 ; i < inp_str2.length; i++)
		{
			str2 = str2 + inp_str2[i]; 
		}
		ret_str = str1 + str2;
		return ret_str;
	}
}
