/*
 * Created on 8th June, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */ 
package harrier.income.com.report; 

import java.io.File; 
import java.util.*;

import jxl.*; 
import jxl.format.UnderlineStyle;
import jxl.write.*;

import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.*;
import javax.servlet.http.*; 

import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.OutputStreamWriter;

import org.apache.struts.action.ActionMapping;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import sysconfig.model.indexMovement;
import sysconfig.model.stockDetailFromDate;
/**
 * @author neha
 * Xml for individual report
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


public class MultiFact {
	
	ArrayList getdata=new ArrayList();
	
	 harrier.income.com.report.BatchReportFormN abc= new harrier.income.com.report.BatchReportFormN();
	 harrier.income.com.report.AddFactData data=new  harrier.income.com.report.AddFactData();
	/**
	 * @return Returns the getdata.
	 */
	 
	 
	 public void reset(ActionMapping mapping,HttpServletRequest request){
	  
	 	 HttpSession session = request.getSession();
	 	harrier.income.com.report.BatchReportFormN abc=(harrier.income.com.report.BatchReportFormN)session.getAttribute("batchReportBeanN");
	 	session.setAttribute("ci2Composition",abc.getVw());
	 	session.setAttribute("ci2divisor",abc.getVExcel());
	 	session.setAttribute("ci2pepb",abc.getVExcel20());
	  }
	public ArrayList getGetdata() {
		ArrayList list=new ArrayList();
		if(abc.is1()) // index composition 
        {
			     abc.getTabledata3();
	             String var=abc.get31();
	             String type="1";
	             String iename=abc.getIndex_name11();
	             String from=null;
	             String to=null;
	             String idxname=null;
	            AddFactData  data=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data);
					
        }
		else if(abc.is4()){  // indexpepb
			
			abc.getIndex_arraylist();
	    	String var=abc.get34();
	    	String type="20";
	    	String iename=abc.getIndex_name11();
	    	String from=abc.get14();
			String to=abc.get24();
			String idxname=null;
			 AddFactData  data=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data);
	    	
	    	
			
		}
		else if(abc.is2()) // index divisor
        {
		abc.getTableData2();
    	String var=abc.get32();
	    String type="19";
		String iename=abc.getIndex_name11();
		String from=abc.get12();
		String to=abc.get22();
		String idxname=null;
		AddFactData  data=new AddFactData(var,type,iename,from,to,idxname);          
		list.add(data);
        }	
		
		
		
		else if(abc.is5()){  // stock details report
			abc.getTableDatas();
			String stk_name6= (String)abc.getStock_name();//stock name
	  		String ind_name6=(String)abc.getIndex_name11();// index name
	  		String[] var6= abc.get45();	//stockid
	  		String var=var6[1];
	  		String to = abc.get25();
	  		String from = abc.get15();
	  		String type="6";
	  		String 	iename6 = abc.getIndex_name11();
	  	
		}
		
		return getdata;
	}
	/**
	 * @param getdata The getdata to set.
	 */
	public void setGetdata(ArrayList getdata) {
		this.getdata = getdata;
	}
}

