/*
 * Created on Jan 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CalculateCorrelation {
	Logger Logging = Logger.getLogger(CalculateCorrelation.class);
	public static Vector ind_idname,vector_corelation,vector_index_rv,vname;
	java.sql.ResultSet rst=null;
	public static double tmcv=0.0,tmcv1=0.0;
	static Connect con1 = ConnectInit.getConnect();
	/**
	 * generate a vector containing index_id & name for the selected(multiple) indices.
	 * @param var
	 * @return vector (selected index list).
	 */
	Connection connection=null;
	public Vector getId_name(String[] var)
	{
		Logging.debug("array length is "+var.length);
		String query="select index_id,index_name from index_master where index_id="+var[0];
    	for(int n=1;n<var.length;n++){
    		query=query+" or index_id="+var[n];
  	    }
    	Logging.debug("query is "+query);
    	int i=0;
 //   	app.Connect con = new app.Connect();
    	Connect con = ConnectInit.getConnect();
//    	Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
    	if(connection==null)
		{
			connection=con.getdbConnection();
		}
		rst = con.returnResultcorrelation(query);
		Logging.debug("resultset is "+rst);
    	try {
    		ind_idname=new Vector();
    		while (rst.next()) 
			{				
				if (rst.getString(1) == null) {
					ind_idname.add(i, "--");
				} else {
					ind_idname.add(i, rst.getString(1));
				}
				i++;
				if (rst.getString(2) == null) {
					ind_idname.add(i, "--");
				} else {
					ind_idname.add(i, rst.getString(2));
				}
				i++;
			}
    		
    		//Closing the dynamic Connection : By Manoj Adekar
    		con.closeDynaCon();
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error : "+sqlexp.getMessage());
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
		Logging.debug("Size of vector : "+ind_idname.size());
		return ind_idname;
	}
	/**
	 * populate a vector containing index names of selected indices 
	 * and their correlation among each other.
	 * @param fdate
	 * @param tdate
	 * @return
	 */
	public Vector getCalculatedCorrelation(String fdate,String tdate)
	{
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		vector_corelation=new Vector();
		vname=new  Vector();
		double r=0.0;
		String rstr=null;
		vname.clear();
		int k=0,m=0;
		Logging.debug("ind_idname.size() "+ind_idname.size()+" "+(k<(ind_idname.size())));
		//generate vector vname containing only names of selected indices.
		while(k<(ind_idname.size()))
		{
			Logging.debug("inside while1 ");
			k++;
			vname.add(m,((String)ind_idname.get(k)));k++;
			m++;
		}
		k=0;m=0;
		Logging.debug("list of Index name ");
		for(int i=0;i<vname.size();i++)
		{
			Logging.debug("  "+(String)vname.get(i));
		}
		for(int i=0;i<(ind_idname.size()/2);i++)
		{
			vector_corelation.add(k,((String)vname.get(m)));k++;m++;//add index name to the vector.
			for(int p=0;p<i;p++)
			{
				vector_corelation.add(k," ");
				k++;
			}
			for(int j=i;j<(ind_idname.size()/2);j++)
			{
				String id1=(String)ind_idname.get(i*2);
				String id2=(String)ind_idname.get(j*2);
				if((id1.equals(id2)))
				{
					rstr="1";//correlation between same index =1.					
				}else{
					r=getIndexCorrelation(id1,id2,fdate,tdate);//get correlation between two indices.
					rstr=new Double(r).toString();
					rstr=ad.indexcompose4digit(rstr);//limit correlation upto 4 digits after decimal point.
				}
				vector_corelation.add(k,rstr);k++;
			}
		}
		return vector_corelation;
	}
	/**
	 * get correlation between two indices within specified time duraion.
	 * index correlation=(nr/dr).
	 * where nr=summation(x-x1)(y-y1) and dr=square root((summation(x-x1)'2 *summation(y-y1)'2)).
	 * x1 and y1=mean index value first and second index respectively.
	 * @param id1
	 * @param id2
	 * @param fdate
	 * @param tdate
	 * @return
	 */
	public double getIndexCorrelation(String id1,String id2,String fdate,String tdate)
	{
		double correlation=0.0,indexmean1=0.0,indexmean2=0.0,volsqr1=0.0,volsqr2=0.0,volsqrxy=0.0;
		Logging.debug("Inside getIndexCorrelation");
		vector_index_rv = new Vector();	
		vector_index_rv=getvolatility(id1,id2,fdate,tdate);
		indexmean1=getmean1();//mean value for index first index.
		indexmean2=getmean2();//mean index value for second index.
		Logging.debug("v1 size "+vector_index_rv.size()+"indexmean1 "+indexmean1+"  indexmean2 "+indexmean2);
		int i=0;
		while(i<vector_index_rv.size())
		{
			volsqrxy=volsqrxy+(((double)Double.parseDouble((String)vector_index_rv.get(i))-indexmean1)*((double)Double.parseDouble((String)vector_index_rv.get(i+1))-indexmean2));//summation of (x-x1)*(y-y1)where x,x1 for first index and y,y1 for second index.
			volsqr1=volsqr1+(((double)Double.parseDouble((String)vector_index_rv.get(i))-indexmean1)*((double)Double.parseDouble((String)vector_index_rv.get(i))-indexmean1));//summation(x-x1)'2 for first index.
			i++;
			volsqr2=volsqr2+((double)Double.parseDouble((String)vector_index_rv.get(i))-indexmean2)*((double)Double.parseDouble((String)vector_index_rv.get(i))-indexmean2);//summation(y-y1)'2 for second index.
			i++;
		}
		double sumxy=(Math.sqrt((volsqr1*volsqr2)));//generate denominator for correltion calculation.
		if(sumxy!=0.0){
			correlation=volsqrxy/sumxy;
		}else{
			correlation=0.0;
		}
		return correlation;
	}
	/**
	 * get vector populated with changed index values within period mentioned
	 * for two indices whose correlation need to de calculated. 
	 * @param id1
	 * @param id2
	 * @param fdate
	 * @param tdate
	 * @return
	 */
	public Vector getvolatility(String id1,String id2,String fdate,String tdate)
	{
		Vector v=new Vector();
		tmcv=0.0;tmcv1=0.0;
		try {
		//		app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
//				Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
				if(connection==null)
				{
					connection=con.getdbConnection();
				}
					
				int j = 0,m=0;	
				Logging.debug(id1+"  "+id2+"  "+fdate+" "+"  "+tdate);
				rst = con.IndexCorrelationResult("Index_correlation_report",id1,id2,fdate,tdate);
				Logging.debug("setVector_index_rv1");
				int i=0;
				while(rst.next())
				{
					if (rst.getString(1) == null) {
						v.add(i, "0");
					} else {
						v.add(i, rst.getString(1));
						tmcv=tmcv+(double)Double.parseDouble(rst.getString(1));
					}
					i++;

					if (rst.getString(2) == null) {
						v.add(i, "0");
					} else {
						v.add(i, rst.getString(2));	
						tmcv1=tmcv1+(double)Double.parseDouble(rst.getString(2));
					}
					i++;					
				}					
				tmcv=tmcv/(v.size()/2);
				tmcv1=tmcv/(v.size()/2);
				Logging.debug("vector size "+v.size()+" tmcv is"+tmcv);
				
				//Close the Dynamic Connection of Connect Class : By Manoj Adekar
				con.closeDynaCon();
				
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :"+sqlexp.getMessage());
			}	
//			Close the Dynamic Connection : Manoj Adekar
			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
			return v;
	}
	/**
	 * get mean index value for index one.
	 * @return double index value.
	 */
	public double getmean1()
	{
		return tmcv;
	}
	/**
	 * get mean index value for second index.
	 * @return double index value.
	 */
	public double getmean2()
	{
		return tmcv1;
	}
}
