/*
 * Created on Jun 17, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package app;
import java.io.IOException;
import java.sql.PreparedStatement;
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
public class NewusersAction extends Action {
	Logger Logging = Logger.getLogger(NewusersAction.class);
	ResultSet rst;
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	String query,user_Id;
	String id;
	String pw;
	String encPw;
	String pw1;
	String seqq;
	String ans;
	String id2,tempdob;
	String fn;
	String mn;
	String ln;
	String lang;
	String addr1;
	String addr2;
	String city1;
	String zip1;
	String country;
	String gender;
	String phone;
	String mobile;
	String designation;
	String email;
	String branch;
	String clientname;
	int roleId;
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,
			   HttpServletResponse response)
		   throws IOException, ServletException {
		   	
			NewusersForm nuForm 		=	((NewusersForm)form);
			roleId						=	Integer.parseInt(nuForm.getSelectRole());
			id 							= 	nuForm.getId();
			pw = nuForm.getPw();
			encPw=Encript(pw);
			seqq = nuForm.getSeqq();
			ans = nuForm.getAns();
			tempdob = nuForm.getId2();
			//FOLLOWING CONDITION IS APPLIED BECAUSE id2 field 
			//ie DATE OF BIRTH FIELD IS NOT COMPULSORY IN THE NewUserForm
			if(id2!=null)
			id2=tempdob.substring(3,5)+"-"+tempdob.substring(0,2)+"-"+tempdob.substring(6,10);
			fn = nuForm.getFn();
			mn = nuForm.getMn();
			ln = nuForm.getLn();
			country =nuForm.getCountry();
			addr1=nuForm.getAddr1();
			addr2 = nuForm.getAddr2();
			city1 = nuForm.getCity1();
			phone = nuForm.getPhone();
			mobile=nuForm.getMobile();
			designation = nuForm.getDesignation();
			zip1 = nuForm.getZip1();
			Logging.debug(zip1+"  "+city1);
			gender=nuForm.getGender();
			email=nuForm.getEmail();
			branch=nuForm.getBranch();
			clientname=nuForm.getClientName();
			
			Data_userdetails d=new Data_userdetails();
			d.userid=id;
			d.password=encPw;
			d.address1=addr1;
			d.address2=addr2;
			d.answer=ans;
			d.city=city1;
			d.country=country;
			d.designation=designation;
			d.dob=id2;
			d.firstname=fn;
			d.middlename=mn;
			d.lastname=ln;
			if(gender.trim().equals("m"))
			{
			d.gender='m';
			}else if(gender.trim().equals("f"))
			{
				d.gender='f';
			}
		    d.email=email;
		    d.branch=branch;
		    d.clientname=clientname;
			d.mobile=mobile;
			d.phone=phone;	
			d.sequrityque=seqq;
			d.zipcode=zip1;
			ConnectInit.getConnect().getConnection();
			
			/**Get The Maximum User Id*/
			try {
				if(app.Connect.con==null)
	  		  	{
	  		  		con.getConnection();
	  		  	}
				int j=ConnectInit.getConnect().getUserId("select nextval('user_id')");
				/*PreparedStatement stmt = Connect.con.prepareStatement(con.queries.getProperty("select nextval('user_id')"));
				rst = stmt.executeQuery();
				rst.first();
				Integer i = new Integer(rst.getString(1));
				int j = i.intValue();*/
				Integer i;
				//j++;
				i = new Integer(j);
				user_Id = i.toString();
			} catch (Exception e) {	Logging.debug(e);} 
			
			/**Check for duplicate user_id(first name)*/
			if(!(ConnectInit.getConnect().checkId("check_user_already_exists",d.userid))){
					
				/**Insert data in users table*/
				boolean b=ConnectInit.getConnect().insert(d,"insert_into_users_is_not_client",user_Id);
				
			
			}
			else{
				ActionErrors errors=new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,
				new ActionError("error.newuser.invaliduser"));
				saveErrors(request,errors);
				return (new ActionForward(mapping.getInput()));
		   }
		   
			/**Insert data in user_roles table*/
//			app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			ResultSet rst;
			try {
	  		  	if(app.Connect.con==null)
	  		  	{
	  		  		con.getConnection();
	  		  	}
			
	  		  PreparedStatement stmt = Connect.con.prepareStatement(ConnectInit.queries.getProperty("userRoles_insert"));
	  		   	stmt.setString(1,user_Id);
				stmt.setInt(2,roleId);
			    stmt.executeUpdate();	
			} catch (Exception e) {	Logging.debug(e);} 
		   return (mapping.findForward(Constants.REGISTER));
		}	
	  String Encript(String str)
		{
			/**
			 * Encription : The password string is concatenated with the same string again
			 * it is then converted as first half of the string str1 characters are added with 5 to there 
			 * ascii values and rest half string str2 characters are added by 7 in there ascii values.
			 */
			String ret_str= null;
			String str1 = "", str2 =  "" ;
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
