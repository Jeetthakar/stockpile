/*
 * Created on Oct 26, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

//import app.Connect;

/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexCompareOHLC {
	Logger Logging = Logger.getLogger(IndexCompareOHLC.class);
	Vector vector_compareOHLC,v1,v2,v3,vdate,vid;
	ResultSet rst;
	int flag1=0,flag2=0,flag3=0;
	String index_id1,index_id2,index_id3;
	static Connect con1 = ConnectInit.getConnect();
	private Connection connection=null;
	/**
	 * @param id1,id2,id3,fromdate,toDate
	 *            The vector_compareOHL to set.
	 */
	public  void setVector_compareOHLC(String id1,String id2,String id3,String fromdate,String toDate)
	{
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		index_id1=id1;
		index_id2=id2;
		index_id3=id3;	
		Logging.debug("index id first "+id1+"index id second "+id2+"index id third "+id3);
//		Connect connect = new Connect();
		Connect connect = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try {
		if(connection == null){
			connection = connect.getdbConnection();
		}
		
		PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.
		getProperty("get_index_daily_values_between_date_OHLC"));
		pst.setString(1,id1);
		pst.setString(2,id2);
		pst.setString(3,id3);
		pst.setString(4,fromdate);
		pst.setString(5,toDate);
		rst=pst.executeQuery();
		vector_compareOHLC.clear();
		vector_compareOHLC = new Vector();
		int i = 0;
		Logging.debug("setVector_tabledata of Compose Index");
		
			while (rst.next()) {
				if (rst.getString(1) == null) {
					vector_compareOHLC.add(i, "--");
				} else {
					vector_compareOHLC.add(i, rst.getString(1));
				}
				i++;
				if (rst.getString(2) == null) {
					vector_compareOHLC.add(i, "--");
				} else {
					vector_compareOHLC.add(i, rst.getString(2));
				}
				i++;
				if (rst.getString(3) == null) {
					vector_compareOHLC.add(i, "0.00");
				} else {
					String ad1=(String)rst.getString(3);
            		ad1=ad.indexcompose(ad1);
           			vector_compareOHLC.add(i, ad1);
				}
				i++;

				if (rst.getString(4) == null) {
					vector_compareOHLC.add(i, "0.00");
				} else {
					String ad2=(String)rst.getString(4);
            		ad2=ad.indexcompose(ad2);
           			vector_compareOHLC.add(i, ad2);
				}
				i++;

				if (rst.getString(5) == null) {
					vector_compareOHLC.add(i, "0.00");
				} else {
					String ad3=(String)rst.getString(5);
            		ad3=ad.indexcompose(ad3);
           			vector_compareOHLC.add(i, ad3);
				}
				i++;
				if (rst.getString(6) == null) {
					vector_compareOHLC.add(i, "0.00");
				} else {
					
            		String ad4=(String)rst.getString(6);
            		ad4=ad.indexcompose(ad4);
           			vector_compareOHLC.add(i, ad4);
				}
				i++;
				
			}
		} catch (SQLException sqlexp) {
			Logging.debug(sqlexp+"");
		}
//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	/**
	 * @return Returns the vector_compareOHLC.
	 */
	public Vector getVector_compareOHLC() {
		return vector_compareOHLC;
	}
	/**
	 * @param id[],fromdate,toDate
	 *            The vector_compareOHL1 to set.
	 */
	public  void setVector_compareOHLC1(String[] id1,String fromdate,String toDate)
	{
		
    //	 org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Logging.debug("inside setVector_compareOHLC1"+id1);
    	 for(int i=0;i<id1.length;i++){
    	 	Logging.debug("index id is "+id1[i]);
    	 }
		//System.out.println("index id first "+id1+"index id second "+id2+"index id third "+id3);
	//	Connect connect = new Connect();
    	 Connect connect = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try {
		if(connection == null){
			connection = connect.getdbConnection();
		}
	
				Logging.debug("Inside setVector_compareOHLC1 try ");
				vector_compareOHLC = new Vector();
			 for(int k=0;k<id1.length;k++){
				PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.
				getProperty("get_index_values_OHLC"));
				String indexid=id1[k];
				pst.setString(1,indexid);		
				pst.setString(2,fromdate);
				pst.setString(3,toDate);
				rst=pst.executeQuery();				
				int i = 0;
				Logging.debug("setVector_tabledata of Compose Index"+indexid);
				
					while (rst.next()) {
						if (rst.getString(1) == null) {
							vector_compareOHLC.add(i, "0");
						} else {
							vector_compareOHLC.add(i, rst.getString(1));
						}
						i++;
						if (rst.getString(2) == null) {
							vector_compareOHLC.add(i, "--");
						} else {
							vector_compareOHLC.add(i, rst.getString(2));
						}
						i++;
						if (rst.getString(3) == null) {
							vector_compareOHLC.add(i, "0.00");
						} else {
							String ad1=(String)rst.getString(3);
		            		ad1=ad.indexcompose(ad1);
		           			vector_compareOHLC.add(i, ad1);
						}
						i++;
		
						if (rst.getString(4) == null) {
							vector_compareOHLC.add(i, "0.00");
						} else {
							String ad2=(String)rst.getString(4);
		            		ad2=ad.indexcompose(ad2);
		           			vector_compareOHLC.add(i, ad2);
						}
						i++;
		
						if (rst.getString(5) == null) {
							vector_compareOHLC.add(i, "0.00");
						} else {
							String ad3=(String)rst.getString(5);
		            		ad3=ad.indexcompose(ad3);
		           			vector_compareOHLC.add(i, ad3);
						}
						i++;
						if (rst.getString(6) == null) {
							vector_compareOHLC.add(i, "0.00");
						} else {
							
		            		String ad4=(String)rst.getString(6);
		            		ad4=ad.indexcompose(ad4);
		           			vector_compareOHLC.add(i, ad4);
						}
						i++;
						
					}
			 }
		} catch (SQLException sqlexp) {
			Logging.debug(sqlexp+"");
		}
//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}

	}
	/**
	 * @return Returns the vid.
	 */
	public Vector getVector_vid() {
		return vid;
	}
	/**
	 * @return Returns the v1.
	 */
	public Vector getVector_compareOHLC1() {
		return v1;
	}
	/**
	 * @return Returns the v2.
	 */
	public Vector getVector_compareOHLC2() {
		return v2;
	}
	/**
	 * @return Returns the v3.
	 */
	public Vector getVector_compareOHLC3() {
		return v3;
	}
	public Vector ArrangeVectorCompareOHLC(Vector v)
	{
		int flag=0,flagdate=0,flagid=0;
		vdate=new Vector();
		vid=new Vector();		
		for(int i=0;i<(v.size());i++)
		{
			String str=(String)v.get(i);
			Logging.debug(str);
		}
		String str=(String)v.get(1);
		vdate.add(v.get(1));
		vid.add(v.get(0));
		for(int i=1;i<(v.size()-6);i+=6)
		{
			String str1=(String)v.get(i);
			String str2=(String)v.get(i+6);			
			int compare=(str1.compareTo(str2));
			Logging.debug("compare"+compare);
			if(compare!=0)
			{
				flagdate=0;
				Logging.debug("Indide compare if loop"+str2);
				if(vdate.size()==0){
					flagdate=0;
				}else{
					for(int l=0;l<vdate.size();l++){
						if(str2.equals((String)vdate.get(l))){
							flagdate=1;
						}
					}
				}
				if(flagdate==0){
					vdate.add(str2);
				}
			}			
		}
		for(int i=0;i<(v.size()-6);i+=6)
		{
			String str11=(String)v.get(i);
			String str12=(String)v.get(i+6);					
			Logging.debug(str11);
			Logging.debug(str12);
			int compare1=(str11.compareTo(str12));
			Logging.debug("compare1 "+compare1);
			if(compare1!=0)
			{
				/*flagid=0;
				Logging.getDebug("Indide compare1 if loop"+str12);
				for(int l=0;l<vdate.size();l++){
					if(str12.equals((String)vid.get(l))){
						flagid=1;
					}
				}
				if(flagid==0){*/
					vid.add(str12);
				//}
			}
		}
		//Logging.log.deug("size of date vector is  "+vdate.size());
		v1=new Vector();
		v1.clear();
		for(int p=0;p<(vdate.size());p++)
		{
			flag1=0;flag2=0;flag3=0;
			String str1=(String)vdate.get(p);
			Logging.debug(" Date "+str1);
			v1.add(str1);
			for(int j=0;j<vid.size();j++)
			{
				flag=0;
				String index_id1=(String)vid.get(j);
				for(int i=0;i<(v.size());i+=6)
				{
					if(((((String)v.get(i)).equals(index_id1))) && (((String)v.get(i+1)).equals(str1)))
					{
							v1.add(v.get(i+2));
							v1.add(v.get(i+3));
							v1.add(v.get(i+4));
							v1.add(v.get(i+5));
							flag=1;
					}
				}
				if(flag==0){
						v1.add("0.00");
						v1.add("0.00");
						v1.add("0.00");
						v1.add("0.00");
				}
			}
		}
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Logging.debug("size of First vector is  "+v1.size());
		for(int p=0;p<(v1.size());p++)
		{
			String str1=(String)v1.get(p);
			Logging.debug(" v1 element  "+str1);
		}
		return v1;
	}
	public void ArrangeVector(Vector v)
	{
		v1=new Vector();
		v2=new Vector();
		v3=new Vector();
		vdate=new Vector();
		for(int i=0;i<(v.size());i++)
		{
			String str=(String)v.get(i);
			//Logging.getDebug(str);
		}
		String str=(String)v.get(1);
		vdate.add(v.get(1));
		
		for(int i=1;i<(v.size()-6);i+=6)
		{
			String str1=(String)v.get(i);
			String str2=(String)v.get(i+6);
			//Logging.getDebug(str1);
			//Logging.getDebug(str2);
			int compare=(str1.compareTo(str2));
			//Logging.getDebug(compare);
			if(compare!=0)
			{
			//	Logging.log.deug("Indide compare if loop");
				vdate.add(v.get(i+6));
			}
		}
		//app.Logging.log.deug("size of date vector is  "+vdate.size());
		for(int p=0;p<(vdate.size());p++)
		{
			flag1=0;flag2=0;flag3=0;
			String str1=(String)vdate.get(p);
			//app.Logging.getDebug(str1);
			for(int i=0;i<(v.size());i+=6)
			{
				if((((String)v.get(i+1)).equals(str1))&&((((String)v.get(i)).equals(index_id1))))
				{
						v1.add(str1);
						v1.add(v.get(i+2));
						v1.add(v.get(i+3));
						v1.add(v.get(i+4));
						v1.add(v.get(i+5));
						flag1=1;
				}	
				if((((String)v.get(i+1)).equals(str1))&&((((String)v.get(i)).equals(index_id2))))
				{
						v2.add(str1);
						v2.add(v.get(i+2));
						v2.add(v.get(i+3));
						v2.add(v.get(i+4));
						v2.add(v.get(i+5));
						flag2=1;
				}			
				if((((String)v.get(i+1)).equals(str1))&&((((String)v.get(i)).equals(index_id3))))
				{
						v3.add(str1);
						v3.add(v.get(i+2));
						v3.add(v.get(i+3));
						v3.add(v.get(i+4));
						v3.add(v.get(i+5));
						flag3=1;
				}			
			}
			if(flag1==0)
			{
				v1.add(str1);
				v1.add("0.00");
				v1.add("0.00");
				v1.add("0.00");
				v1.add("0.00");
			}
			if(flag2==0)
			{
				v2.add(str1);
				v2.add("0.00");
				v2.add("0.00");
				v2.add("0.00");
				v2.add("0.00");
			}if(flag3==0)
			{
				v3.add(str1);
				v3.add("0.00");
				v3.add("0.00");
				v3.add("0.00");
				v3.add("0.00");
			}
		}
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Logging.debug("size of First vector is  "+v1.size());
		for(int p=1;p<(v1.size());p+=5)
		{
			String str1=(String)v1.get(p);
			Logging.debug("  "+str1);
		}
		Logging.debug("size of second vector is  "+v2.size());
		for(int p=1;p<(v2.size());p+=5)
		{
			String str1=(String)v2.get(p);
			Logging.debug("  "+str1);
		}
		Logging.debug("size of Third vector is  "+v3.size());
		for(int p=1;p<(v3.size());p+=5)
		{
			String str1=(String)v3.get(p);
			Logging.debug("  "+str1);
		}
	}
	/**
	 * method to compare two dates inputed in string format.
	 * returns true if first date is greater than second date otherwise returns false.
	 */
	public boolean compareDate(String d_creationDate,String d_baseDate) {
		boolean flag = false;
		try {
			//	logger.info("d_creationDate : "+d_creationDate);
			//	logger.info("d_creationDate : Year:month:date->
			// "+d_creationDate.trim().substring(6,10)+":"+d_creationDate.trim().substring(3,5)+":"+d_creationDate.trim().substring(0,2));
			//	logger.info("d_baseDate : "+d_baseDate);
			Date creationDate = new Date(new Integer(d_creationDate.trim()
					.substring(6, 10)).intValue(), new Integer(d_creationDate
					.trim().substring(3, 5)).intValue(), new Integer(
					d_creationDate.trim().substring(0, 2)).intValue());
			Date baseDate = new Date(new Integer(d_baseDate.trim().substring(6,
					10)).intValue(), new Integer(d_baseDate.trim().substring(3,
					5)).intValue(), new Integer(d_baseDate.trim().substring(0,
					2)).intValue());
			//	logger.info("creationDate :"+creationDate);
			//	logger.info("baseDate :"+baseDate);
			int result = creationDate.compareTo(baseDate);
			Logging.info("result :" + result);
			if (result > 0)
				flag = false;
			else
				flag = true;
		} catch (Exception e) {

		}
		return flag;
	}
}
