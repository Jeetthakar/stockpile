/*
 * Created on Jun 19, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.harrier.initializeation.ConnectInit;

/**
 * @author W
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ForgotForm extends ActionForm{
	Logger Logging = Logger.getLogger(ForgotForm.class);
	String zip,country,id,birthDay,birthMonth,birthYear;
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	ResultSet rst;
	/**
	 * @return
	 * 
	 */
	public String getBirthYear() {
	Logging.debug("**********birth year*********************************"+birthYear);
	//	System.out.println("*******************************************"+birthYear);
		return birthYear;
	}
	public void setBirthYear(String string) {
		birthYear = string;
	}
	public String getBirthMonth() {
		Logging.debug("*************birth month*****************************"+birthMonth);
		//System.out.println("*******************************************"+birthMonth);
		return birthMonth;
	}
	public void setBirthMonth(String string) {
		birthMonth = string;
	}
	public String getBirthDay() {
		
		Logging.debug(""+"*************birth day*******************************"+birthDay);
		//System.out.println("birth date"+id2+"********************************************"+birthDay);
		return birthDay;
	}
	public void setBirthDay(String string) {
		birthDay = string;
		Logging.debug("*******************************************"+birthDay);
	}

	public String getCountry() {
		return country;
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
	/*public String getId2() {
		id2=""+birthDay+""+birthMonth+""+birthYear;
		//Logging.getDebug(""+id2+"********************************************"+birthDay);
		System.out.println(""+id2+"********************************************"+birthDay);
		return id2;
	}*/

	/**
	 * @return
	 */
	
	public String getZip() {
		return zip;
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
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	/*public void setId2(String string) {
		id2 = string;
	}*/

	/**
	 * @param string
	 */
	public void setZip(String string) {
		zip = string;
	}
	
	public ActionErrors validate(ActionMapping mapping,
									HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if(id==null || id.trim().equals("")){
			errors.add("id",
							new ActionError("error.id.required"));									
		}
		//***************************id2 is BirthDate field *******************
		//id2=""+birthDay+""+birthMonth+""+birthYear;
		Logging.debug(""+birthDay+""+birthMonth+""+birthYear);
		//System.out.println(""+id2+"***********bithday********************************"+birthDay);
		Logging.debug("==========================aaa  ===========================");
		/*if(id2==null || id2.trim().equals("")){
			
					errors.add("id2",
									new ActionError("error.dob.required"));									
		}*/
		if(zip==null || zip.trim().equals("")){
			errors.add("zip1",
							new ActionError("error.zip1.required"));									
		}
		if(country==null || country.trim().equals("")){
			errors.add("country",
							new ActionError("error.country.required"));									
		}
		return errors;
}
	Collection dayCollection=null;
	/**
	 * @return Returns the day of BirthDate.
	 */
	public Collection getDayCollection() {
		Vector day=new Vector();
		/*day.add(new LabelValueBean("01","1"));    
		day.add(new LabelValueBean("02","2"));  
		day.add(new LabelValueBean("03","3"));  
		day.add(new LabelValueBean("04","4"));  
		day.add(new LabelValueBean("05","5"));  
		day.add(new LabelValueBean("06","6"));  
		day.add(new LabelValueBean("07","7"));
		day.add(new LabelValueBean("08","8"));
		day.add(new LabelValueBean("09","9")); */ 
		
		
		for(int i=1;i<=31;i++)
		{
		//vec.add(new LabelValueBean(""+i,""+i));
			if(i<10){
				day.add(new LabelValueBean("0"+""+i,""+i));
				}
			else{
				day.add(new LabelValueBean(""+i,""+i));
				}
			
	
		}
          	
		 		dayCollection =day ;
	        	
		return dayCollection;
	}
	/**
	 * @param countryIdCollection The countryIdCollection to set.
	 */
	public void setDayCollection(Collection dayCollection) {
		
		this.dayCollection = dayCollection;
	}
	
	Collection monthCollection=null;
	/**
	 * @return Returns the month of BirthDate.
	 */
	public Collection getMonthCollection() {
		Vector month=new Vector();
		/*month.add(new LabelValueBean("01","1"));    
		month.add(new LabelValueBean("02","2"));  
		month.add(new LabelValueBean("03","3"));  
		month.add(new LabelValueBean("04","4"));  
		month.add(new LabelValueBean("05","5"));  
		month.add(new LabelValueBean("06","6"));  
		month.add(new LabelValueBean("07","7"));
		month.add(new LabelValueBean("08","8"));
		month.add(new LabelValueBean("09","9")); */          
		
		for(int i=1;i<=12;i++)
		{
		//vec.add(new LabelValueBean(""+i,""+i));
			if(i<10){
				month.add(new LabelValueBean("0"+""+i,""+i));
				}
			else{
							month.add(new LabelValueBean(""+i,""+i));
			}
			
	

		}
          	
		 		monthCollection =month ;
	        	
		return monthCollection;
	}
	/**
	 * @param countryIdCollection The countryIdCollection to set.
	 */
	public void setMonthCollection(Collection monthCollection) {
		
		this.monthCollection = monthCollection;
	}
	
	Collection yearCollection=null;
	/**
	 * @return Returns the year of BirthDate.
	 */
	public Collection getYearCollection() {
		Vector year=new Vector();
		
		java.util.Date d=new java.util.Date();   
	//j
	int CurrentYear=(d.getYear())+1900;
	//System.out.println("**********************"+d+""+j);
		for(int i=1970;i<=CurrentYear;i++)
		{
		//vec.add(new LabelValueBean(""+i,""+i));
		year.add(new LabelValueBean(""+i,""+i));

		}
          	
		 		yearCollection =year ;
	        	
		return yearCollection;
	}
	
	public void setYearCollection(Collection yearCollection) {
		
		this.yearCollection = yearCollection;
	}
	Collection countryIdCollection=null;
	/**
	 * @return Returns the countryIdCollection.
	 */
	public Collection getCountryIdCollection() {
		Vector countries=new Vector();
		
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
	        			rst.close();
	        			stmt.close();
	        		} catch (SQLException e) {
	        			// TODO Auto-generated catch block
	        		//	e.printStackTrace();
	        			Logging.debug(e);
	        		}	
	        	   		
	        		if(countries.isEmpty()){
	        			countries.add(new LabelValueBean("Not Selected","Not Selected"));
	        			countryIdCollection =countries ;
	        			return countryIdCollection;
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
}
