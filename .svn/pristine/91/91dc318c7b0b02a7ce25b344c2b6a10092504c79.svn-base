
package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

import com.harrier.initializeation.ConnectInit;

/**
 * @author W
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NewusersForm extends ValidatorForm {
	static Logger Logging = Logger.getLogger(NewusersForm.class);
//	app.Connect con=new app.Connect();		
	Connect con = ConnectInit.getConnect();
	ResultSet rst;
	/**
	 * @return Returns the branch.
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch The branch to set.
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return Returns the clientName.
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName The clientName to set.
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	private String id;

	private String pw;

	private String pw1;

	private String seqq;

	private String ans;

	private String id2;

	private String fn;

	private String mn;

	private String ln;

	private String addr1;

	private String addr2;

	private String city1;

	private String zip1;

	private String country;

	private String gender;

	private String phone;

	private String mobile;

	private String designation;

	private String dob;

	private String branch, email, clientName;

	/**
	 * @return
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * @return
	 */
	public String getAddr2() {
		return addr2;
	}

	/**  
	 * @return
	 */
	public String getAns() {
		return ans;
	}

	/**
	 * @return
	 */
	public String getCity1() {
		return city1;
	}

	/**
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @return
	 */
	public String getFn() {
		return fn;
	}

	/**
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getId2() {
		return id2;
	}

	/**
	 * @return
	 */
	public String getLn() {
		return ln;
	}

	/**
	 * @return
	 */
	public String getMn() {
		return mn;
	}

	/**
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * @return
	 */
	public String getPw1() {
		return pw1;
	}

	/**
	 * @return
	 */
	public String getSeqq() {
		return seqq;
	}

	/**
	 * @return
	 */
	public String getZip1() {
		return zip1;
	}

	/**
	 * @param string
	 */
	public void setAddr1(String string) {
		addr1 = string;
	}

	/**
	 * @param string
	 */
	public void setAddr2(String string) {
		addr2 = string;
	}

	/**
	 * @param string
	 */
	public void setAns(String string) {
		ans = string;
	}

	/**
	 * @param string
	 */
	public void setCity1(String string) {
		city1 = string;
	}

	/**
	 * @param string
	 */
	public void setCountry(String string) {
		country = string;
	}

	/**
	 * @param string
	 */
	public void setDesignation(String string) {
		designation = string;
	}

	/**
	 * @param string
	 */
	public void setFn(String string) {
		fn = string;
	}

	/**
	 * @param string
	 */
	public void setGender(String string) {
		gender = string;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	public void setId2(String string) {
		id2 = string;
	}

	/**
	 * @param string
	 */
	public void setLn(String string) {
		ln = string;
	}

	/**
	 * @param string
	 */
	public void setMn(String string) {
		mn = string;
	}

	/**
	 * @param string
	 */
	public void setMobile(String string) {
		mobile = string;
	}

	/**
	 * @param string
	 */
	public void setPhone(String string) {
		phone = string;
	}

	/**
	 * @param string
	 */
	public void setPw(String string) {
		pw = string;
	}

	/**
	 * @param string
	 */
	public void setPw1(String string) {
		pw1 = string;
	}

	/**
	 * @param string
	 */
	public void setSeqq(String string) {
		seqq = string;
	}

	/**
	 * @param string
	 */
	public void setZip1(String string) {
		zip1 = string;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
	
		
		if (errors.isEmpty()) {
			if (pw != null && !pw.trim().equals(pw1)) 
				errors.add("pw1", new ActionError("error.pw1.unequal"));
			if (errors.isEmpty()) {
				
				
				if (id.equalsIgnoreCase(pw)) 
					errors.add("eq", new ActionError("error.eq.equal"));
				
				if (checkwhitespace(id)) 
					errors.add("id", new ActionError("error.id.present"));
				
				if (checkwhitespace(pw)) 
					errors.add("pw", new ActionError("error.pw.present"));
				
				if (!Character.isLetterOrDigit(id.charAt(0))) 
					errors.add("pw", new ActionError("error.pw.present1"));
				
				if (errors.isEmpty()) {
						
					if (invalid(fn)) 
						errors.add("fn", new ActionError("error.fn.invalid"));					
					/*if (invalid(mn)) 
						errors.add("mn", new ActionError("error.mn.invalid"));	*/				
					if (invalid(ln)) 
						errors.add("ln", new ActionError("error.ln.invalid"));					
					
					if(checkEmail(email))
							errors.add("branch",new ActionError("error.email.invalid"));
										
					/*if(invalid(branch))
						errors.add("branch",
								new ActionError("error.branch.invalid"));*/
				}
			}
		}
				
		return errors;
	}

	public boolean checkwhitespace(String local) {
		int length = local.length();
		boolean b = true;
		char ch;
		char[] charr = new char[length];
		charr = local.toCharArray();
		for (int i = 0; i < length; i++) {
			ch = charr[i];
			if (ch == '@' || ch == '_' || ch == '*' || ch == '#' || ch == '$'
					|| ch == '%' || ch == '^' || ch == '~' || ch == '&'
					|| ch == '?') {
				b = false;
				break;
			}
		}
		return b;
	}

	public boolean invalid(String local) {
		int length = local.length();
		boolean b = false;
		char[] charr = new char[length + 1];
		charr = local.toCharArray();
		for (int i = 0; i < length; i++) {
			if (Character.isDigit(charr[i])) {
				b = true;
				break;
			}
		}
		return b;
	}
	
	public static boolean checkEmail(String email){
		boolean b = false;
		char arr[] = new char[email.length()];
		arr = email.toCharArray();
		
		if(!Character.isLetter(arr[0]))
			return true;
		int len = arr.length;
		int atcount=0,dotcount=0;
		Logging.debug(len);
		for(int i=1;i<len;i++){
			if(arr[i]=='@'){
				if(i==len-1) return true;
				if(arr[i-1]=='.'||arr[i+1]=='.')return true;				
				atcount++;
			}
			if(arr[i]=='.'){
				if(i==len-1) return true;
				dotcount++;
			}			
		}	
		if(atcount!=1||dotcount==0)
			return true;
		return b;
	}

	public boolean invalidint(String local) {
		int length = local.length();
		boolean b = false;
		char[] charr = new char[length + 1];
		charr = local.toCharArray();
		for (int i = 0; i < length; i++) {
			if (Character.isLetter(charr[i])) {
				b = true;
				break;
			}
		}
		return b;
	}

	/**
	 * @return
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param string
	 */
	public void setDob(String string) {
		dob = string;
	}
	
	public static void main(String[] args) {
		ConnectInit.getConnect();
	}
	
	Collection roleCollection=null;
	String selectRole=null;
	/**
	 * @return Returns the roleCollection.
	 */
	public Collection getRoleCollection() {
		
		try {
  		  	if(app.Connect.con==null)
		{
			con.getConnection();
		}

		} catch (Exception e) {	Logging.debug(e);} 
		Vector roles = new Vector(10);
 		try{
 				PreparedStatement stmt = Connect.con.prepareStatement(ConnectInit.queries.getProperty("roles_select_*_from_roles"));	
 			 	rst = stmt.executeQuery();	
 			   	while(rst.next()){
					String count=rst.getString(1);
					roles.add(new LabelValueBean(rst.getString(2),count));
					
				}	
	   	}catch(Exception e){Logging.debug(e);}
	   	roleCollection=roles;
		return roleCollection;
	}
	/**
	 * @param roleCollection The roleCollection to set.
	 */
	public void setRoleCollection(Collection roleCollection) {
		this.roleCollection = roleCollection;
	}
	
	/**
	 * @return Returns the selectRole.
	 */
	public String getSelectRole() {
		return selectRole;
	}
	/**
	 * @param selectRole The selectRole to set.
	 */
	public void setSelectRole(String selectRole) {
		this.selectRole = selectRole;
	}
	
	Collection countryIdCollection=null;
	/**
	 * @return Returns the countryIdCollection.
	 */
	public Collection getCountryIdCollection() {
		Vector countries=new Vector();
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String NotSelected=asc.getLangValues("StockPerformance.notsel");
		Logging.debug(" Inside getCountryIdCollection(): Not selected ="+NotSelected);
		
		countries.add(new LabelValueBean(NotSelected,"value0"));	
		try {
  		  	if(app.Connect.con==null)
		{
			con.getConnection();
		}

		} catch (Exception e) {	Logging.debug(e);} 
	               
	               	try {
	        			
	        			PreparedStatement stmt = Connect.con.prepareStatement(ConnectInit.queries.getProperty("sysconfig_select_countries"));	
	        			rst = stmt.executeQuery();
	        			
	        			while(rst.next()){
	        				String count=rst.getString(1);
	        				countries.add(new LabelValueBean(rst.getString(2),rst.getString(2)));
	        			}
	        		} catch (SQLException e) {
	        			// TODO Auto-generated catch block
	        		//	e.printStackTrace();
	        			Logging.debug(e);
	        		}	
	        	   		
	        		countryIdCollection =countries ;
	        	
		return countryIdCollection;
	}
	/**
	 * @param countryIdCollection The countryIdCollection to set.
	 */
	public void setCountryIdCollection(Collection countryIdCollection) {
		
		this.countryIdCollection = countryIdCollection;
	}
	
	Collection clientCollection=null;
	
	/**
	 * @return Returns the clientCollection.
	 */
	public Collection getClientCollection() {
		Vector countries=new Vector();
//		AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String ClientName=asc.getLangValues("newUsers.client");
		Logging.debug(" Inside getClientCollection(): Client Name="+ClientName);
		
	               
	               	try {
	               		countries.add(new LabelValueBean(ClientName,"0"));
	               		Connect con = ConnectInit.getConnect();
	               		//new app.Connect().getConnection();
	               		con.getConnection();
	               	//	rst=new app.Connect().getClientList("client_list");
	               		rst = con.getClientList("client_list");
	               		while(rst.next()){
	        				String count=rst.getString(1);
	        				countries.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
	        			}
	        		} catch (SQLException e) {
	        			// TODO Auto-generated catch block
	        		//	e.printStackTrace();
	        			Logging.debug(e);
	        		}	
	        	   		
	        clientCollection =countries ;
	        	
		
		return clientCollection;
	}
	/**
	 * @param clientCollection The clientCollection to set.
	 */
	public void setClientCollection(Collection clientCollection) {
		this.clientCollection = clientCollection;
	}
}