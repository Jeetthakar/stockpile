/*
 * Created on Jan 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.Logger;

import sysconfig.model.indexMovement;
import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Indexwise_pe_pb {
	Logger Logging = Logger.getLogger(Indexwise_pe_pb.class);
	public static Vector vdata;
	static Connect con1 = ConnectInit.getConnect();
	public ArrayList tableDate;
	public indexMovement im;
	public String tradingDate,close,mCap,divisor,pe,pb,divYield;
	private Connection connection=null;
	/**
	 * @return Returns the tableDate.
	 */
	public ArrayList getTableDate(String index,String fodate,String todate,String seperator) {
		ArrayList Pp 	=	new ArrayList();
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
	//	app.Connect con=new app.Connect();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Connect con = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(connection==null)
		{
			connection=con.getdbConnection();
		}
		if(fodate!=null && todate!=null){
				try {
					//PreparedStatement pst = Connect.con.prepareStatement(con.queries.getProperty("indexMovement1"));
					PreparedStatement pst;
					if(seperator.equals("movingIndex")){
						 pst =connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value1"));
					}
					else {
						pst = connection.prepareStatement(ConnectInit.queries.getProperty("index_divisor_date_wise1"));
					}
					pst.setString(1,index);
					pst.setString(2,fodate);
					pst.setString(3,todate);
					/*pst.setString(4,index);
					pst.setString(5,fodate);
					pst.setString(6,todate);
					pst.setString(7,index);
					pst.setString(8,fodate);
					pst.setString(9,todate);
					pst.setString(10,fodate);
					pst.setString(11,todate);
					pst.setString(12,index);*/
					ResultSet rst = pst.executeQuery(); 
						while(rst.next()){
							if (rst.getString(1) == null) {
								tradingDate="--";
							} else {
								tradingDate=rst.getString(1);
								
							}
							if (rst.getString(2) == null) {
								close="0";
							} else {
								String strclose=(String)rst.getString(2);
								close=ad.indexcompose(strclose);
								
							}
							if (rst.getString(3) == null) {
								mCap="0";
							} else {
								double mcv=(double)rst.getDouble(3);
								mcv=mcv/1000000.0;
								String strmcv=new Double(mcv).toString();
								strmcv=ad.shareholdingpatt(strmcv);
								mCap=ad.indexcompose(strmcv);
								
							}
							if (rst.getString(4) == null) {
								divisor="0";
							} else {
								double mcv=(double)rst.getDouble(4);
								String strmcv=new Double(mcv).toString();
								strmcv=ad.shareholdingpatt(strmcv);
								divisor=ad.indexcompose(strmcv);
							}
							/*if (rst.getString(5) == null) {
								pe="0";
							} else {
								double tmcv=(double)rst.getDouble(3);
								double dperatio=(double)rst.getDouble(5);
								app.Logging.getDebug("pe ratio is "+dperatio+" tmcv "+tmcv);
								dperatio=tmcv/dperatio;
								app.Logging.getDebug("pe ratio is "+dperatio+" actaual "+(double)rst.getDouble(6));
								String peratio=new Double(dperatio).toString();
								peratio=ad.shareholdingpatt(peratio);
								peratio=ad.indexcompose(peratio);
								pe=peratio;
							}
							
							if (rst.getString(6) == null) {
								pb="0";
							} else {
								double tmcv=(double)rst.getDouble(3);
								double dpbratio=(double)rst.getDouble(6);
								app.Logging.getDebug("pb ratio is "+dpbratio+" tmcv "+tmcv);
								dpbratio=tmcv/dpbratio;
								app.Logging.getDebug("pb ratio is "+dpbratio+" actual "+(double)rst.getDouble(7));
								String pbratio=new Double(dpbratio).toString();
								pbratio=ad.shareholdingpatt(pbratio);
								pbratio=ad.indexcompose(pbratio);
								pb= pbratio;
							}
							if (rst.getString(7) == null) {
								divYield= "0";
							} else {
								double tmcv=(double)rst.getDouble(3);
								double divvalue=(double)rst.getDouble(7);
								divvalue=tmcv/divvalue;
								app.Logging.getDebug("div value is "+divvalue);
								String div=new Double(divvalue).toString();
								div=ad.indexcompose(div);
								divYield= div;					
							}*/			
							im=new indexMovement(tradingDate,close,mCap,divisor, pe, pb,divYield);
							Pp.add(im);
							/*sdf=(stockDetailFromDate)Pp.get(1);
							  sdf.getIc()*/
							 
						}
						pst.close();
						rst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
					Logging.debug(e);
				}
				
			
			}

		} catch (Exception e) {
			Logging.debug("Error :" + e);
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

	
		tableDate=Pp;
		return tableDate;
	}
	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableDate(ArrayList tableDate) {
		this.tableDate = tableDate;
	}
	public Vector getVectorIndexpe_pb()
	{
		return vdata;
	}
	public void setVectorIndexpe_pb(String index,String fodate,String todate)
	{
		Logging.debug("Inside Vector_stock_performance");
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try {
		//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal(); 
		//		app.Connect con=new app.Connect();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			Connect con = ConnectInit.getConnect();
				if(connection==null)
				{
					connection=con.getdbConnection();
				}
				vdata = new Vector();
				double lastclose=getlastclosing(index,fodate);
		Logging.debug(index+"  "+fodate+" "+"  "+todate);
				PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("indexwise_pe_pb_ratio"));
				pst.setString(1,index);
				pst.setString(2,fodate);
				pst.setString(3,todate);
				pst.setString(4,index);
				pst.setString(5,fodate);
				pst.setString(6,todate);
				pst.setString(7,index);
				pst.setString(8,fodate);
				pst.setString(9,todate);
				pst.setString(10,fodate);
				pst.setString(11,todate);
				pst.setString(12,index);
				ResultSet rst = pst.executeQuery(); 
				int i = 0;
				Logging.debug("setVector_vdata");	
				while(rst.next())
				{
					if (rst.getString(1) == null) {
						vdata.add(i, "--");
					} else {
						vdata.add(i, rst.getString(1));
					}
					i++;

					if (rst.getString(2) == null) {
						vdata.add(i, "0");
					} else {
						String strclose=(String)rst.getString(2);
						strclose=ad.indexcompose(strclose);
						vdata.add(i, strclose);
					}
					i++;					
					double pchange=0.0;
					if(lastclose!=0.0){
						pchange=(((double)rst.getDouble(2))-lastclose)/lastclose;
					}else{
						pchange=0.0;
					}
					String strpchange=new Double(pchange).toString();
					strpchange=ad.indexcompose(strpchange);
					vdata.add(i, strpchange);
					lastclose=(double)rst.getDouble(2);
					i++;					
					if (rst.getString(3) == null) {
						vdata.add(i, "0");
					} else {
						double mcv=(double)rst.getDouble(3);
						mcv=mcv/1000000.0;
						String strmcv=new Double(mcv).toString();
						strmcv=ad.shareholdingpatt(strmcv);
						strmcv=ad.indexcompose(strmcv);
						vdata.add(i, strmcv);
					}
					i++;	
					if (rst.getString(4) == null) {
						vdata.add(i, "0");
					} else {						
						vdata.add(i, rst.getString(4));						
					}
					i++;	
					if (rst.getString(5) == null) {
						vdata.add(i, "0");
					} else {
						double trv=(double)rst.getDouble(5);
						trv=trv/1000000.0;
						String turnover=new Double(trv).toString();
						turnover=ad.indexcompose(turnover);
						vdata.add(i, turnover);
					}
					i++;

					if (rst.getString(6) == null) {
						vdata.add(i, "0");
					} else {
						double tmcv=(double)rst.getDouble(3);
						double dperatio=(double)rst.getDouble(6);
						Logging.debug("pe ratio is "+dperatio+" tmcv "+tmcv);
						dperatio=tmcv/dperatio;
						Logging.debug("pe ratio is "+dperatio+" actaual "+(double)rst.getDouble(6));
						String peratio=new Double(dperatio).toString();
						peratio=ad.shareholdingpatt(peratio);
						peratio=ad.indexcompose(peratio);
						vdata.add(i, peratio);
					}
					i++;

					if (rst.getString(7) == null) {
						vdata.add(i, "0");
					} else {
						double tmcv=(double)rst.getDouble(3);
						double dpbratio=(double)rst.getDouble(7);
						Logging.debug("pb ratio is "+dpbratio+" tmcv "+tmcv);
						dpbratio=tmcv/dpbratio;
						Logging.debug("pb ratio is "+dpbratio+" actual "+(double)rst.getDouble(7));
						String pbratio=new Double(dpbratio).toString();
						pbratio=ad.shareholdingpatt(pbratio);
						pbratio=ad.indexcompose(pbratio);
						vdata.add(i, pbratio);
					}
					i++;	
					if (rst.getString(8) == null) {
						vdata.add(i, "0");
					} else {
						double tmcv=(double)rst.getDouble(3);
						double divvalue=(double)rst.getDouble(8);
						divvalue=tmcv/divvalue;
						Logging.debug("div value is "+divvalue);
						String div=new Double(divvalue).toString();
						div=ad.indexcompose(div);
						vdata.add(i, div);					
					}
					i++;	
				}	
				Logging.debug("vector size "+vdata.size());			
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
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
	public double getlastclosing(String id,String fdate)
	{
		double lastclose=0.0;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try
		{
		//	Connect con=new Connect();
			Connect con = ConnectInit.getConnect();
			if(connection==null)
			{
				connection=con.getdbConnection();
			}
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_index_closing_max_value"));
			pst.setString(1,id);
			pst.setString(2,id);
			pst.setString(3,fdate);
			ResultSet rst = pst.executeQuery(); 
			int i = 0;
			while(rst.next())
			{
				if(rst.getString(1)==null){
					lastclose=0.0;
				}else{
					lastclose=rst.getDouble(1);
				}
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
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

		return lastclose;
	}
}
