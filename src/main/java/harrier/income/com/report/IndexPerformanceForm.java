/*
 * Created on Feb 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import com.harrier.initializeation.ConnectInit;


/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexPerformanceForm extends ActionForm{
	Logger Logging = Logger.getLogger(IndexPerformanceForm.class);
private String date=null;
private String b1=null;
Vector index_performance=null;
ResultSet rst=null;
ArrayList index_arraylist=new ArrayList();
Vector vExcel=new Vector();
String dt_today1=null;
private String hiddenVar=null;
String userid1;

public void reset(ActionMapping arg0, HttpServletRequest arg1) {
	this.date=null;
	this.b1=null;

	
}
public ActionErrors validate(ActionMapping mapping,HttpServletRequest request){
	ActionErrors errors = new ActionErrors();
	ActionError err=null;
		if(getDate()==null){
			  errors.add("name",new ActionMessage("error.name.required"));
		}
			return errors;
}
public ArrayList getIndex_arraylist() {
	ArrayList temp=new ArrayList();
	String dt_today= date;
	String id=null,idname=null,m1=null,m3=null,m6=null,m12=null;
	IndexPerformanceModel ipm;
//	AdjustDecimal ad=new AdjustDecimal();
	AdjustDecimal ad = ConnectInit.getAdjustDecimal();
	String m1date,m3date,m6date,y1date,year11;
 try{
  	Logging.debug("INSIDE getIndex_arraylist()");
	Logging.debug("Before if  "+dt_today);
	String year=dt_today.substring(6,10);
	year11=year;
    String month=dt_today.substring(3,5);
    String day=dt_today.substring(0,2);
    int dd=Integer.parseInt(day);
    
    if(dd>=28){
    	dd=28;
    }
    String day1=(new Integer(dd)).toString();
    int mm=Integer.parseInt(month)-01;
    if(mm==0 || mm<0){
    	mm=12+mm;
    	int yy=Integer.parseInt(year)-01;
	    year11=(new Integer(yy)).toString();
    }
    String month1=(new Integer(mm)).toString();
    if(month1.length()==1)
    {
    	month1="0"+month1;
    }
    m1date=day1+"-"+month1+"-"+year11;
    mm=Integer.parseInt(month)-03;
    if(mm==0 || mm<0){
    	mm=12+mm;
    	int yy=Integer.parseInt(year)-01;
	    year11=(new Integer(yy)).toString();
    }
    String month3=(new Integer(mm)).toString();
    if(month3.length()==1)
    {
    	month3="0"+month3;
    }
    m3date=day1+"-"+month3+"-"+year11;
    mm=Integer.parseInt(month)-06;
    if(mm==0 || mm<0){
    	mm=12+mm;
    	int yy=Integer.parseInt(year)-01;
	    year11=(new Integer(yy)).toString();
    }
    String month6=(new Integer(mm)).toString();
    if(month6.length()==1)
    {
    	month6="0"+month6;
    }
    m6date=day1+"-"+month6+"-"+year11;
    mm=Integer.parseInt(year)-01;
    String year1=(new Integer(mm)).toString();
    y1date=day+"-"+month+"-"+year1;
	Logging.debug(dt_today+" "+m1date+" "+m3date+" "+m6date+" "+y1date);
	index_performance = new Vector();
	Connect con=ConnectInit.getConnect();
    Connection connection=null;
	String query ;
	//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
	if(connection==null)

	{
		connection=con.getdbConnection();

	}
	rst = con.IndexPerformanceResult("index_performance_over_month_quater_half_annual_online",dt_today,m1date,m3date,m6date,y1date,userid1);
 	
	int i = 0;
	
	Logging.debug("setIndex_performance of Compose Index"+rst);		
	try {			
		vExcel.clear();
		while (rst.next()) {			
				if (rst.getString(1) == null) { 		//id					
					id="0";
					
				} else {
					id=rst.getString(1);
					
				}
				vExcel.add(i,id);
				i++;			
			
			//app.Logging.getDebug("inside while");
				if (rst.getString(2) == null) {			//idname		
					idname="--";
				} else {
				idname=rst.getString(2);
				}
				vExcel.add(i,idname);
				i++;
				if (rst.getString(4) == null) {			//m1
					m1="0";
				} else {
					month=null;
					if((double)rst.getDouble(3)!=0.00){
						double per1=((double)rst.getDouble(4)/(double)rst.getDouble(3))*100;
						month=new Double(per1).toString();
						month=ad.indexcompose(month);
					}else{
						month="0.00";
					}					
					m1=month;
				}
				vExcel.add(i,m1);
				i++;
				if (rst.getString(5) == null) {				//m3
					m3="0";
				} else {
					month=null;
					if((double)rst.getDouble(3)!=0.00){
						double per1=((double)rst.getDouble(5)/(double)rst.getDouble(3))*100;
						month=new Double(per1).toString();
						month=ad.indexcompose(month);
					}else{
						month="0.00";
					}					
					m3=month;
				}
				vExcel.add(i,m3);
				i++;
				if (rst.getString(6) == null) {				//m6
					m6="0";
				} else {
					month=null;
					if((double)rst.getDouble(3)!=0.00){
						double per1=((double)rst.getDouble(6)/(double)rst.getDouble(3))*100;
						month=new Double(per1).toString();
						month=ad.indexcompose(month);
					}else{
						month="0.00";
					}					
					m6=month;
				}
				vExcel.add(i,m6);
				i++;
				if (rst.getString(7) == null) {			//m12
					m12="0";
				} else {
					month=null;
					if((double)rst.getDouble(3)!=0.00){
						double per1=((double)rst.getDouble(7)/(double)rst.getDouble(3))*100;
						month=new Double(per1).toString();
						month=ad.indexcompose(month);
					}else{
						month="0.00";
					}					
					m12=month;
				}
				vExcel.add(i,m12);
				i++;
				ipm=new IndexPerformanceModel(id,idname,m1,m3,m6,m12);
				temp.add(ipm);
				Logging.debug(" rows = "+ temp.size());
		} //end of while
		
		
	} catch (SQLException sqlexp) {
		Logging.error("SQL Error :"+sqlexp.getMessage());
	}
	
	//Closing Connection : By Manoj Adekar
	con.closeDynaCon();
  }catch(Exception e){ 
  
  }

  	setVExcel(vExcel);
	index_arraylist=temp;
	return index_arraylist;
  
  
  } //end of getIndex_arraylist

public String getB1() {
	return b1;
}
/**
 * @param b1 The b1 to set.
 */
public void setB1(String b1) {
	this.b1 = b1;
}
/**
 * @return Returns the date.
 */
public String getDate() {
	return date;
}
/**
 * @param date The date to set.
 */
public void setDate(String date) {
	this.date = date;
}
/**
 * @param index_performance The index_performance to set.
 */
public void setIndex_performance(Vector index_performance) {
	this.index_performance = index_performance;
}
/**
 * @return Returns the dt_today1.
 */
public String getDt_today1() {
	return dt_today1;
}
/**
 * @param dt_today1 The dt_today1 to set.
 */
public void setDt_today1(String dt_today1) {
	this.dt_today1 = dt_today1;
}
/**
 * @param index_Arraylist The index_Arraylist to set.
 */
public void setIndex_Arraylist(ArrayList index_Arraylist) {
	index_arraylist = index_Arraylist;
}
/**
 * @return Returns the index_performance.
 */
public Vector getIndex_performance() {
	return index_performance;
}
/**
 * @return Returns the index_arraylist.
 */
/**
 * @param index_arraylist The index_arraylist to set.
 */
public void setIndex_arraylist(ArrayList index_arraylist) {
	this.index_arraylist = index_arraylist;
}
/**
 * @return Returns the hiddenVar.
 */
public String getHiddenVar() {
	return hiddenVar;
}
/**
 * @param hiddenVar The hiddenVar to set.
 */
public void setHiddenVar(String hiddenVar) {
	this.hiddenVar = hiddenVar;
}
/**
 * @return Returns the vExcel.
 */
public Vector getVExcel() {
	return vExcel;
}
/**
 * @param excel The vExcel to set.
 */
public void setVExcel(Vector excel) {
	vExcel = excel;
}

public String getUserid1() {
	return userid1;
}
public void setUserid1(String userid1) {
	this.userid1 = userid1;
}
}
