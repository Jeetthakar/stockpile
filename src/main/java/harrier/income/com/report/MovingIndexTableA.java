package harrier.income.com.report;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import uk.ltd.getahead.dwr.ExecutionContext;
import uk.ltd.getahead.dwr.WebContextFactory;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class MovingIndexTableA extends HttpServlet {
	Logger Logging = Logger.getLogger(MovingIndexTableA.class);
	private  String sectorIndexCheck;
	
	ArrayList Table_data =new ArrayList();
	Vector Table_data_vector = new Vector();
	Vector vExcel = new Vector();
	String tradingDate=null;
	String close=null;
	String mCap=null;
	String divisor=null;
	PrintWriter out = null;
//	AdjustDecimal ad=new AdjustDecimal();	
	Connection connection=null;
	Connect  con = ConnectInit.getConnect();
	PreparedStatement pst;
	java.sql.ResultSet rst;
	
	private Map indexMovingTable = new HashMap();
	//HttpSession session = WebContextFactory.get().getSession(true);/*changed by Pranay*/
	HttpSession session = ExecutionContext.get().getSession();
      /*
       * Variables Added on 07/04/07
       * by Pankaj Bhende
       */
	 String option = null; //For Column Value
	  static int order = 1; //Using for Ascending-Descending order
	
	//HttpServletRequest request = null;
	
	public ArrayList getIndexMovingTable(String fodate,String todate,String selectIndex) {
			
		try{
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			if(connection==null)
				connection=null;
				connection=con.getdbConnection();
					if(fodate!=null && todate!=null){
							try {
					
								PreparedStatement pst;
								
								  pst = connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value1"));
					    		  pst.setString(1,selectIndex);
					              pst.setString(2,fodate);
					              pst.setString(3,todate);
					              ResultSet rst = pst.executeQuery(); 
					              //vExcel.clear();
					              int i=0;
					              int ii=0;
					              int j=0;
					              while(rst.next()){
													if (rst.getString(1) == null) {
														tradingDate="--";
													} else {
														tradingDate=rst.getString(1);
														Table_data_vector.add(i,tradingDate);
														i++;
													}
													//vExcel.add(ii,tradingDate);
													ii++;
													
													if (rst.getString(2) == null) {
														close="0";
													} else {
														String strclose=(String)rst.getString(2);
														close=ad.indexcompose(strclose);
														close=AdjustDecimal.ArrangeAsNumeric(close);
														Table_data_vector.add(i,close);
														i++;														
													}
													//vExcel.add(ii,close);
													ii++;
													
													if (rst.getString(3) == null) {
														mCap="0";
													} else {
														double mcv=(double)rst.getDouble(3);
														mcv=mcv/1000000.0;
														String strmcv=new Double(mcv).toString();
														strmcv=ad.shareholdingpatt(strmcv);
														mCap=ad.indexcompose(strmcv);
														mCap=AdjustDecimal.ArrangeAsNumeric(mCap);
														Table_data_vector.add(i,mCap);
														i++;
														
													}
													//vExcel.add(ii,mCap);
													ii++;
													
													if (rst.getString(4) == null) {
														divisor="0";
													} else {
														double mcv=(double)rst.getDouble(4);
														String strmcv=new Double(mcv).toString();
														strmcv=ad.shareholdingpatt(strmcv);
														divisor=ad.indexcompose(strmcv);
														divisor=AdjustDecimal.ArrangeAsNumeric(divisor);
														Table_data_vector.add(i,divisor);
														i++;											
														
													}
													//vExcel.add(ii,divisor);
													ii++;
													
							            		// Table_data.add(new IndexMovingDetail(tradingDate,close,mCap,divisor, pe, pb,divYield));
							            		Table_data.add(new IndexMovingDetail(tradingDate,close,mCap,divisor));
							            		IndexMovingDetail indMovDet = new IndexMovingDetail(tradingDate,close,mCap,divisor);
							            		indexMovingTable.put(new Integer(++j),indMovDet);
					              		}
					              	//setVar_Table_data_vector(Table_data_vector);
									} catch (SQLException e)
										{
													// TODO Auto-generated catch block
												//	e.printStackTrace();
										Logging.debug(e);
										}
					}
		}catch(Exception e){
					    	Logging.error(" Error : "+e.getMessage());
		 }
		finally{
					    	try{
					    		if(connection!=null)
					    			connection.close();
					    		}
					    		catch(Exception ee)
					    		{
					    			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
					    		}
	    }			
		/**
		 * To provide data fro links
		 */
		session.setAttribute("ci2",Table_data_vector);
		session.setAttribute("type","24");	
		session.setAttribute("varIndexId",selectIndex);
		session.setAttribute("from",fodate);  
		session.setAttribute("to",todate);
		
		//Logging.debug("it;s done************************"+indexMovingTable+fodate+todate+selectIndex);		
		return Table_data;			
	}
	
	/* =========================== Index Calculator ======================================================== */
	
	public ArrayList getTableData(String local_id) {
		//Logging.debug("inside the gettabledata");
		/*Connect con=ConnectInit.getConnect();
		Connection connection=null; */
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		String stk_id=null,symbol=null,price=null,myRates=null;
		ArrayList temp=new ArrayList();
		int serialNo = 0;
		int j=0;
		
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
					String query = ConnectInit.queries.getProperty("index_calculator_report_price");
					Logging.debug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					pst.setString(2,local_id);
					rs = pst.executeQuery();
					int i=0;
					while(rs.next())
					{
						 stk_id=rs.getString(1);
						 symbol=rs.getString(2);
						if(rs.getString(3)!=null){
							 price=rs.getString(3);
						}else{
							price="0.00";
						}
						serialNo = serialNo+1;
						
						IndexCalculatorDetails icd= new IndexCalculatorDetails(serialNo, stk_id, symbol,price);
						temp.add(icd);
						//indexMovingTable.put(new Integer(++j),icd);
					}
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
									  }
				    }catch(Exception e){
				    	Logging.error(" Error : "+e.getMessage());
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
						    Logging.error(" Error : Unable to close Connection "+ex.getMessage());
		    					 }
					 	   Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    		}
				    }		    	      
				    
				    Table_data = temp; 				    				    
				    //return tableData;
				    //Logging.debug("THis is the icd :"+temp);
				    return  Table_data;
	}
	
 /* ================================================ Latest Divisor ======================================== */
	
	public ArrayList getLatestdivisor() 
	{
	//Connection connection=null;
	String Indexname=null, Divisor=null, ID=null;	
	Logging.debug("Inside latestdivisor");
	//app.Connect con=new app.Connect();
	//ArrayList arr=new ArrayList();
	//Table_data=new ArrayList();
	AdjustDecimal ad = ConnectInit.getAdjustDecimal();
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	Latest latest;
	try {
		if(connection==null)
			connection=con.getdbConnection();
	
	
	rst = Icr.divisorResult(connection,"get_all_latest_divisor","1");
	
	
	int i=0;
	Logging.debug("setVector_latestdivisor of list of latest divisor");
	
	try {
		while (rst.next()) 
		{
			if (rst.getString(2) == null) 
			{
				Indexname= "0";
				
			} else 
			{
				Indexname=rst.getString(2);
				
			}
			
			if (rst.getString(3) == null) 
			{
				Divisor= "0";
			} else 
			{
				Divisor=ad.indexcompose(rst.getString(3));
				Divisor=AdjustDecimal.ArrangeAsNumeric(Divisor);
				
			}
			
            	ID=rst.getString(1);
            	latest = new Latest(Indexname, Divisor ,ID);
            	indexMovingTable.put(new Integer(++i),latest);
            	Table_data.add(latest);          	            	
		}
		rst.close();	
		} 
	catch (SQLException sqlexp) 
	{
		Logging.error("Error : "+sqlexp.getMessage());
	}
	}catch(Exception e){
		Logging.error(" Error : "+e.getMessage());
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
				Logging.error(" Error : Unable to close connection "+ex.getMessage());
			}
			Logging.error(" Error : Unable to close connection "+ee.getMessage());
		}
	}
	//Table_data=arr;
		//Logging.debug("arraylist"+Table_data);
		return Table_data;
}
	
	/* ================================================ Latest Divisor ===================================== */
	
	/* ================================================ Index Performance ================================== */
	
	public ArrayList getIndexPerformance(String date) {
		Connection connection=null;
		
		String dt_today= date;
		session.setAttribute("dt",date);
		String id=null,idname=null,m1=null,m3=null,m6=null,m12=null;
		IndexPerformanceModel ipm;
	//	AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		String m1date,m3date,m6date,y1date,year11;
	 try{
	  //Logging.debug("INSIDE getIndex_arraylist()");
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
		//index_performance = new Vector();
		//Connect con=ConnectInit.getConnect();
	    
		String query ;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		if(connection==null)
		{
			connection=con.getdbConnection();

		}
		rst = con.IndexPerformanceResult("index_performance_over_month_quater_half_annual_online",dt_today,m1date,m3date,m6date,y1date,"1");
	 	
		int i = 0;
		int j = 0;
		
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
					Table_data.add(ipm);
					indexMovingTable.put(new Integer(++j),ipm);
					session.setAttribute("ci2",vExcel);
					Logging.debug(" ipm = "+ indexMovingTable);
			} //end of while
			
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
		}
	  }catch(Exception e){ 
	  
	  }
//	Close the Dynamic Connection : Manoj Adekar
	  finally {
	  	try {
	  		if (connection != null)
	  			connection.close();
	  	} catch (Exception ee) {
	  		Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
	  	}
	  }
	  	//setVExcel(vExcel);
		//index_arraylist=temp;
		return Table_data;
	  
	  
	  } //end of getIndexMovingTable

	/* ================================================ Index Performance =================================== */
	
	/* ================================================getDetails ===================================== */
	 public ArrayList getDetails(String from) {
		  	//Connection connection=null;
		  	//va=new Vector();
		  	Vector ve=new Vector();
		  	String l_date="";
			String date1 = from;
			String indexname=null,indexid=null,current=null,indexopen=null,indexhigh=null,indexlow=null,indexclosing=null,tmcv=null,divisor=null,currency=null,indexdate=null,indexclsv=null,indextime=null,vachange=null;
			Logging.debug("Before if  "+date1);
			
			if((date1==null)||(date1.equals("null")))
		    {
				Logging.debug("date null inside if");
				SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
				Date dt = new Date();
				l_date = fr.format(dt).toString();
			}
			Logging.debug(l_date);
			int i = 0; int j = 0;
			Double dd = new Double("0");
			Logging.debug("setIndex_details of Compose Index");
			String value=null,open=null,high=null,low=null,close=null,mcap=null;
			ArrayList tempdata=new ArrayList();
			//details = new ArrayList();
			DetailIndex detailindex;
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
	//		Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			try {
				if(connection==null)
					connection=con.getdbConnection();
			
			
			if((date1==null)||(date1.equals("null")))
			{
				rst = con.getLatestIndexDetails1("get_latest_all_index_details_bench",l_date);
			}
			else
			{
				rst = con.getIndexDetails("all_index_details",date1);
			}
			 
			
			
			try {
				
				while (rst.next()) {
					
					String strpchange="0.00",status=null;
					if(((double)rst.getDouble(7))!=0.00){
						double pchange=(((double)rst.getDouble(2)-(double)rst.getDouble(7))/(double)rst.getDouble(7))*100;
						strpchange=new Double(pchange).toString();
						Logging.debug("strpchange before adjusting is "+strpchange);
						strpchange=ad.indexcompose(strpchange);
						vachange=strpchange;
						
						Logging.debug("strpchange after adjusting is "+strpchange);
					}
					if (rst.getString(13) == null) {
						indexid= "0";
						
					} else {
						indexid=rst.getString(13);								
					}
					ve.add(i,indexid);
					i++;					
					if (rst.getString(1) == null) {					
						indexname= "0";
					} else {
					indexname=rst.getString(1);						
					}
					ve.add(i,indexname);
					i++;					
					if (rst.getString(2) == null) {
						current= "0";
					} else {
						current=rst.getString(2);
						current=ad.indexcompose(current);
						current=AdjustDecimal.ArrangeAsNumeric(current);							
					}
					ve.add(i,current);
					i++;
					if (rst.getString(3) == null) {
						status="--";
					} else {
						double change = (double)Double.parseDouble(strpchange);
						if (change > 0) {
							status= "up";
						} else {
							if(change==0.00)
							{
								status= "mid";
							}else{
								//dd = new Double(change);
								status= "down";
							}
						}
					}
					
					if (rst.getString(3) == null) {
					indexopen="0.00";
					} else {
						indexopen=rst.getString(3);
						indexopen=ad.indexcompose(indexopen);
						indexopen=AdjustDecimal.ArrangeAsNumeric(indexopen);						
					}
					ve.add(i,indexopen);
					i++;			
					if (rst.getString(4) == null) {
						indexhigh= "0.00";
					} else {
						indexhigh=rst.getString(4);
						indexhigh=ad.indexcompose(indexhigh);
						indexhigh=AdjustDecimal.ArrangeAsNumeric(indexhigh);
					}	
					ve.add(i,indexhigh);
					i++;
					if (rst.getString(5) == null) {
						indexlow= "0.00";
					} else {
						indexlow=rst.getString(5);
						indexlow=ad.indexcompose(indexlow);
						indexlow=AdjustDecimal.ArrangeAsNumeric(indexlow);						
					}
					ve.add(i,indexlow);
					i++;
					if (rst.getString(12) == null) {
						indextime= "0";						
					} else {
						int time = comapreTime(rst.getString(12));
						if (time > 0) {
							String temp = rst.getString(7);						
							if(temp==null){
								temp="0.00";
							}
							temp=ad.indexcompose(temp);
							temp=AdjustDecimal.ArrangeAsNumeric(temp);
							indexclsv= temp;
							ve.add(i,indexclsv);
							
						} else {
							String temp1=rst.getString(6);
							if(temp1==null){
								temp1="0.00";
							}
							temp1=ad.indexcompose(temp1);
							temp1=AdjustDecimal.ArrangeAsNumeric(temp1);
							indexclosing= temp1	;
							ve.add(i,indexclosing);
						}
					}
					i++;
					ve.add(i,strpchange);
					i++;
					if (rst.getString(8) == null) {
						tmcv= "0.00";						
					} else {
						String temp=rst.getString(8);
						if(temp==null){
							temp="0.00";
						}
						int k=temp.indexOf(".");
						if(k==-1){
							tmcv=temp ;
						
						}else{
							temp=temp+"00";
							temp=temp.substring(0,k+2);
							temp=ad.indexcompose(temp);
							temp=AdjustDecimal.ArrangeAsNumeric(temp);
							tmcv= temp;
							
						}
					}
					ve.add(i,tmcv);
					i++;
					
					if (rst.getString(9) == null) {
						divisor="0.00";
					} else {
						String temp=rst.getString(9);
						if(temp==null){
							temp="0.00";
						}
						int k=temp.indexOf(".");
						if(k==-1){
							divisor=temp ;							
						}else{
							temp=temp+"00";
							temp=temp.substring(0,k+2);
							temp=ad.indexcompose(temp);
							temp=AdjustDecimal.ArrangeAsNumeric(temp);
							divisor= temp;							
						}
					}
					ve.add(i,divisor);
					i++;
					if (rst.getString(10) == null) {
						currency= "0";
						ve.add(i,"--");
					} else {
						currency= rst.getString(10);
						ve.add(i,currency);
						}									
					i++;
					if (rst.getString(11) == null) {
						indexdate= "0";
						ve.add(i,"--");
					} else {
						indexdate=rst.getString(11);
						ve.add(i,indexdate);
					}
					i++;
					Logging.debug("Vec Values =" + indexname +" : "+ indexid +" : "+current+" : "+ indexopen+" : "+indexhigh+" : "+indexlow+" : "+indexclosing+" : "+vachange+" : "+tmcv+" : "+divisor+" : "+currency+": "+indexdate+": "+indexclsv+": "+indextime);
					detailindex = new DetailIndex(indexname,indexid,current,status,indexopen,indexhigh,indexlow,indexclosing,vachange,tmcv,divisor,currency,indexdate,indexclsv,indextime);
					tempdata.add(detailindex);									
					//indexMovingTable.put(new Integer(++j),detailindex);
				}
				session.setAttribute("ci2",ve);
				
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :"+sqlexp.getMessage());
			}
			}
			catch(Exception ee)
			{
				Logging.error("DEbug"+ee.getMessage());
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
						Logging.error(" Error : Unable to close connection "+ex.getMessage());
					}
					Logging.error(" Error : Unable to close connection "+ee.getMessage());
				}
			}
			/*app.Logging.getDebug("Return Index_details of size "+details.size());
			details=tempdata;
			Logging.getDebug("The vector is......... "+ve);
		    setVa(ve);*/
			return tempdata;
		  	}
	 
	 
	 public int comapreTime(String time) {
			String[] time1 = time.split(":");
			Date dt = new Date();
			dt.getDate();
			String[] time2 = dt.toString().split(" ");
			time2 = time2[3].toString().split(":");
			for (int i = 0; i < time1.length; i++) {
				if (Integer.parseInt(time1[i]) > Integer.parseInt(time2[i]))
					return 1;
				else if (Integer.parseInt(time1[i]) < Integer.parseInt(time2[i]))
					return -1;
			}
			return 2;
		  	}
	 
		  
 /* ================================================getDetails ===================================== */
	 
 /* ============================================  getIndexWisePePb  ===================================== */	 
	 
	
	 public ArrayList getIndexWisePePb(String local_d1, String local_from, String local_to) {
		Connection connection=null;	
		 ArrayList vdata=new ArrayList();
			int j = 0;
			String td=null,close=null,change=null,mcap=null,shtr=null,trnovr=null,perat=null,pbrat=null,dividend=null;
			try {
			//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal(); 
				AdjustDecimal ad = ConnectInit.getAdjustDecimal();
				//app.Connect con=new app.Connect();

					//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
					if(connection==null)
					{
						connection=con.getdbConnection();
					}
					vdata = new ArrayList();
				/*	String local_d1 = getD1();
					String local_from = getFrom() ;
					String local_to = getTo() ; */
					double lastclose=getlastclosing(local_d1,local_from);
					Logging.debug(local_d1+"  "+local_from+" "+"  "+local_to);
					PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("indexwise_pe_pb_ratio"));
					pst.setString(1,local_d1);
					pst.setString(2,local_from);
					pst.setString(3,local_to);
					pst.setString(4,local_d1);
					pst.setString(5,local_from);
					pst.setString(6,local_to);
					pst.setString(7,local_d1);
					pst.setString(8,local_from);
					pst.setString(9,local_to);
					pst.setString(10,local_from);
					pst.setString(11,local_to);
					pst.setString(12,local_d1);
					ResultSet rst = pst.executeQuery(); 
					Logging.debug("setVector_vdata");
					int i=0;
					vExcel.clear();
					while(rst.next())
					{
						if (rst.getString(1) == null) {
							td="---";
						} else {
							td=rst.getString(1);
						}
						vExcel.add(i,td);
						i++;
						if (rst.getString(2) == null) {
							close="0";
						} else {
							String strclose=(String)rst.getString(2);
							strclose=ad.indexcompose(strclose);
							strclose=AdjustDecimal.ArrangeAsNumeric(strclose);
							close=strclose;
						}
						vExcel.add(i,close);
						i++;
						double pchange=0.0;
						if(lastclose!=0.0){
							pchange=(((double)rst.getDouble(2))-lastclose)/lastclose;
						}else{
							pchange=0.0;
						}
						String strpchange=new Double(pchange).toString();
						strpchange=ad.indexcompose(strpchange);
						strpchange=AdjustDecimal.ArrangeAsNumeric(strpchange);
						change=strpchange;
						vExcel.add(i,strpchange);
						i++;
						
						lastclose=(double)rst.getDouble(2);
						
						if (rst.getString(3) == null) {
							mcap="0";
						} else {
							double mcv=(double)rst.getDouble(3);
							mcv=mcv/1000000.0;
							String strmcv=new Double(mcv).toString();
							strmcv=ad.shareholdingpatt(strmcv);
							strmcv=ad.indexcompose(strmcv);
							strmcv=AdjustDecimal.ArrangeAsNumeric(strmcv);
							mcap=strmcv;
						}
						vExcel.add(i,mcap);
						i++;
						if (rst.getString(4) == null) {
							shtr="0";
						} else {						
							shtr=rst.getString(4);
						}
						vExcel.add(i,shtr);
						i++;
						if (rst.getString(5) == null) {
							trnovr="0";
						} else {
							double trv=(double)rst.getDouble(5);
							trv=trv/1000000.0;
							String turnover=new Double(trv).toString();
							turnover=ad.indexcompose(turnover);
							turnover=AdjustDecimal.ArrangeAsNumeric(turnover);
							trnovr=turnover;
						}
						vExcel.add(i,trnovr);
						i++;
						if (rst.getString(6) == null) {
							perat="0";
						} else {
							double tmcv=(double)rst.getDouble(3);
							double dperatio=(double)rst.getDouble(6);
							Logging.debug("pe ratio is "+dperatio+" tmcv "+tmcv);
							dperatio=tmcv/dperatio;
							Logging.debug("pe ratio is "+dperatio+" actaual "+(double)rst.getDouble(6));
							String peratio=new Double(dperatio).toString();
							peratio=ad.shareholdingpatt(peratio);
							peratio=ad.indexcompose(peratio);
							peratio=AdjustDecimal.ArrangeAsNumeric(peratio);
							perat=peratio;
						}
						vExcel.add(i,perat);
						i++;
						if (rst.getString(7) == null) {
							pbrat="0";
						} else {
							double tmcv=(double)rst.getDouble(3);
							double dpbratio=(double)rst.getDouble(7);
							Logging.debug("pb ratio is "+dpbratio+" tmcv "+tmcv);
							dpbratio=tmcv/dpbratio;
							Logging.debug("pb ratio is "+dpbratio+" actual "+(double)rst.getDouble(7));
							String pbratio=new Double(dpbratio).toString();
							pbratio=ad.shareholdingpatt(pbratio);
							pbratio=ad.indexcompose(pbratio);
							pbratio=AdjustDecimal.ArrangeAsNumeric(pbratio);
							pbrat=pbratio;
						}
						vExcel.add(i,pbrat);
						i++;
						if (rst.getString(8) == null) {
							dividend="0";
						} else {
							double tmcv=(double)rst.getDouble(3);
							double divvalue=(double)rst.getDouble(8);
							divvalue=tmcv/divvalue;
							Logging.debug("div value is "+divvalue);
							String div=new Double(divvalue).toString();
							div=ad.indexcompose(div);
							div=AdjustDecimal.ArrangeAsNumeric(div);
							dividend=div;
						}
						vExcel.add(i,dividend);
					    i++;
						IndexPePbDetails ipepb=new IndexPePbDetails(td,close,change,mcap,shtr,trnovr,perat,pbrat,dividend);
						//indexMovingTable.put(new Integer(++j),ipepb);	
						vdata.add(ipepb);
					}	//end of while
					pst.close();
					rst.close();
					
					session.setAttribute("ci2",vExcel);
					session.setAttribute("var",local_d1);
					session.setAttribute("fromDate",local_from);
					session.setAttribute("toDate",local_to);
					session.setAttribute("StoredVector",vExcel);
					session.setAttribute("ci2",vExcel);
					session.setAttribute("selectIndex",local_d1);
	   				session.setAttribute("from",local_from);
	   				session.setAttribute("to",local_to);
					Logging.debug("vector size "+vdata.size());			
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
			
			//setVExcel(vExcel);
			//index_arraylist=vdata;
			return vdata;
		} 
	 public double getlastclosing(String id,String fdate)
	 {
	 	Connection  connection=null;
		double lastclose=0.0;
	 	try
	 	{
	 		//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
	// 		app.Connect con=new app.Connect();
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
//	 	Close the Dynamic Connection : Manoj Adekar
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
 /* ============================================  getIndexWisePePb  ===================================== */
	 
 /* ============================================  getIndexDivisor  ===================================== */	 
	  /*
	   * Following method is edited on 07/04/07 for Sorting purpose
	   * By Pankaj Bhende
	   * 
	   */
	 //public ArrayList getIndexDivisor(String selectIndex, String from, String to, String option ) {
	 public ArrayList getIndexDivisor(String selectIndex, String from, String to , String option) {
		   Connection connection=null;
		 	String pe = null;
		    String pb = null;
			String divYield = null;
			//this.option = option ;
			this.option = "0";
			ArrayList Pp 	=	new ArrayList();
			Vector Table_data_vector = new Vector();
			
			harrier.income.com.report.AdjustDecimal ad=new harrier.income.com.report.AdjustDecimal();
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			
			if(connection==null)
			{
				//Logging.debug("Connecting using getDB ");
				connection=con.getdbConnection();
				
			}
			
			if(from!=null && to!=null){
					try {
						//PreparedStatement pst = Connect.con.prepareStatement(con.queries.getProperty("indexMovement1"));
						
						/*if(check_mavg!=null)
						{
						if(check_mavg.equals("movingIndex")){
							 pst = Connect.con.prepareStatement(con.queries.getProperty("moving_index_value1"));
						}
						}
						else {*/
						
						/*
						 * Code for Ajax Sorting
						 */
						
						Logging.debug("Init Order"+order);
						if(option.equals("0"))
						{
							
							String query = ConnectInit.queries.getProperty("index_divisor");
							Logging.debug("Option "+option+" Order"+order);
						 	if(order == 1)
						 	{	
						 	 query=query+"order by to_date(index_value_date,'dd-mm-yyyy')";
						 	 order++;
						 	Logging.debug("Order "+order);
						 	}else
						 	{
						 		query=query+"order by to_date(index_value_date,'dd-mm-yyyy') desc";
						 		order = 1;
						 	}
						 	
						 	pst = connection.prepareStatement(query);
					     //pst = Connect.con.prepareStatement(con.queries.getProperty("index_divisor_date_wise1"));
						}
						
						 if(option.equals("1"))
						 {
						 	String query = ConnectInit.queries.getProperty("index_divisor");
						 	
						 	Logging.debug("Option "+option+" Order"+order);
						 	if(order == 1)
						 	{	
						 	 query=query+"order by index_closing_value";
						 	 order++;
						 	Logging.debug("Order "+order);
						 	}else
						 	{
						 		query=query+"order by index_closing_value desc";
						 		order = 1;
						 	}
						 	
						 	pst = connection.prepareStatement(query);
						 }
							
						 
						 if(option.equals("2"))
						 {
						 	String query = ConnectInit.queries.getProperty("index_divisor");
						 	
						 	Logging.debug("Option "+option+" Order"+order);
						 	if(order == 1)
						 	{	
						 	 query=query+"order by adjusted_tmcv";
						 	 order++;
						 	Logging.debug("Order "+order);
						 	}else
						 	{
						 		query=query+"order by adjusted_tmcv desc";
						 		order = 1;
						 	}
						 	
						 	pst = connection.prepareStatement(query);
						 }
						 
						 
						 if(option.equals("3"))
						 {
						 	String query = ConnectInit.queries.getProperty("index_divisor");
						 	
						 	Logging.debug("Option "+option+" Order"+order);
						 	if(order == 1)
						 	{	
						 	 query=query+"order by adjusted_divisor";
						 	 order++;
						 	Logging.debug("Order "+order);
						 	}else
						 	{
						 		query=query+"order by adjusted_divisor desc";
						 		order = 1;
						 	}
						 	
						 	pst = connection.prepareStatement(query);
						 }
						 
						//}
							
						pst.setString(1,selectIndex);
						pst.setString(2,from);
						pst.setString(3,to);
						int ii=0;
						int j = 0;
						rst = pst.executeQuery(); 
						vExcel.clear();
							while(rst.next()){
								if (rst.getString(1) == null) {
									tradingDate="--";
									
								} else {
									tradingDate=rst.getString(1);
									
								}
								vExcel.add(ii,tradingDate);
								ii++;
								
								if (rst.getString(2) == null) {
									close="0";
									
								} else {
									String strclose=(String)rst.getString(2);
									close=ad.indexcompose(strclose);
									
								}
								vExcel.add(ii,close);
								ii++;
								
								if (rst.getString(3) == null) {
									mCap="0";
									
								} else {
									double mcv=(double)rst.getDouble(3);
									mcv=mcv/1000000.0;
									String strmcv=new Double(mcv).toString();
									Logging.debug("Strmcv Value is "+strmcv);
									strmcv=ad.shareholdingpatt(strmcv);
									mCap=ad.indexcompose(strmcv);
									
								}
								vExcel.add(ii,mCap);
								ii++;
								
								if (rst.getString(4) == null) {
									divisor="0";
									
								} else {
									double mcv=(double)rst.getDouble(4);
									String strmcv=new Double(mcv).toString();
									Logging.debug("Strmcv1 Value is  "+strmcv);
									strmcv=ad.shareholdingpatt(strmcv);
									divisor=ad.indexcompose(strmcv);
									
								}
								vExcel.add(ii,divisor);
								ii++;
										
								indexMove im1=new indexMove(tradingDate,close,mCap,divisor, pe, pb,divYield);
								indexMovingTable.put(new Integer(++j),im1);
								Pp.add(im1);
								
								//setVar_Table_data_vector(Table_data_vector);
								Table_data_vector.add(Pp);
								
								//Logging.debug("VEXCEL Vector "+vExcel);
							}
							session.setAttribute("StoredVector",vExcel);
							session.setAttribute("ci2",vExcel);
							session.setAttribute("selectIndex",selectIndex);
			   				session.setAttribute("from",from);
			   				session.setAttribute("to",to);
							rst.close();
							pst.close();
							
					} catch (SQLException e) {
						Logging.debug(e);
					//	e.printStackTrace();
					}
					
					finally{
						try{
							if(connection!=null)
								connection.close();
								connection=null;
						}catch(Exception ee){
							try{
								if(connection!=null)
									connection.close();
									connection=null;
							}catch(Exception ex){
								Logging.error(" Error : Unable to close connection "+ex.getMessage());
							}
							Logging.error(" Error : Unable to close connection "+ee.getMessage());
						}
					}
				
				}
		
			//setVExcel(vExcel);
			Table_data=Pp;
			return Table_data;
		} 
	 
 /* ============================================  getIndexDivisor  ===================================== */
	 
 /* ============================================  getINdexReturnsVolatality  ===================================== */	 
	
	 /*public ArrayList getIndexReturnsVolatility(String[] indexList, String from, String to) {	
					    
			ArrayList arr = new ArrayList();
			Vector vector_index_rv1;
			app.Logging.getDebug("Inside vector_indexList_rv");
			int p = 0;			
			if(indexList!=null) 
			   	if(from!=null && to!=null) {   
			   		try{				
			   				//if(connection==null)					
			   				connection=con.getdbConnection();				
			   				harrier.income.com.report.AdjustDecimal ad=new harrier.income.com.report.AdjustDecimal();
			   				//final_Vector = new ArrayList();		
			   				int j = 0;	
			   				//app.Logging.getDebug(indexList.length+"  "+from+" "+"  "+to);
			   				// app.Logging.getDebug("IndexList length is "+indexList.length);
			   				//Logging.debug("#$$$#$$#$#$#$"+indexList.length);
			   				for(int k=0;k<indexList.length;k++)
					    	{
			   					//vector_index_rv1.clear();
			   					vector_index_rv1 = new Vector();							
			   					//app.Logging.getDebug("Value of K  "+indexList[k]);
			   					//Logging.debug("#$$$#$$#$#$#$"+indexList[k]);
			   					String Query =con.queries.getProperty("indexwise_returns_and_volatility");
			   					try {
			   							pst = connection.prepareStatement(Query);
			   							pst.setString(1, indexList[k]);
			   							pst.setString(2, from);
			   							pst.setString(3, to);
			   							rst = pst.executeQuery();
			   							//vExcel.clear();
						
						
										app.Logging.getDebug("setVector_index_rv1");
										
										int i=0;
										double tmcv=0.00;
										app.Logging.getDebug("Resultset ="+rst);
						
										while(rst.next())
										{
											if (rst.getString(1) == null) {
												vector_index_rv1.add(i, "0");
												//vExcel.add(i, "0");
											} 
											else {
													vector_index_rv1.add(i, rst.getString(1));
													//vExcel.add(i, rst.getString(1));
													}
							
											i++;
		
											if (rst.getString(2) == null) {
												vector_index_rv1.add(i, "--");
												//vExcel.add(i, "--");
											} 
											else {
												vector_index_rv1.add(i, rst.getString(2));
												//vExcel.add(i, rst.getString(2));
											}
											i++;
		
											if (rst.getString(3) == null) {
												vector_index_rv1.add(i, "0");
												//vExcel.add(i, "0");
											} 
											else {
												vector_index_rv1.add(i, rst.getString(3));
												//vExcel.add(i, rst.getString(3));
												tmcv=tmcv+(double)Double.parseDouble(rst.getString(3));
											}
											i++;					
										}					
						
						
						
						app.Logging.getDebug("vector size "+vector_index_rv1.size());
						
						int m=0;String str1=null,str2=null;
						
						if(vector_index_rv1.size()!=0)
						{
							str1=(String)vector_index_rv1.get(m);
							m++;j++;
							
							str2=(String)vector_index_rv1.get(m);
							m++;j++;
							
							app.Logging.getDebug("before getMonthlyReturns");
							
							double mr=getMonthlyReturns(vector_index_rv1);
							
							String mrstr=new Double(mr).toString();
							
							mrstr=ad.indexcompose(mrstr);
							
							//vector_index_rv.add(j,mrstr);
							m++;j++;
							double volret=getAvgDailyVolatility(vector_index_rv1,tmcv);
							
							String volretstr=new Double(volret).toString();
							volretstr=ad.indexcompose(volretstr);
							//vector_index_rv.add(j,volretstr);
							j++;	
							
							//indexMovingTable.put(new Integer(++p),new returnVol(str1,str2,mrstr,volretstr));
							//int tmp=0;
							arr.add(new returnVol(str1,str2,mrstr,volretstr));
							
							 vExcel.add(tmp,str2);
							tmp++;
							vExcel.add(tmp,mrstr);
							tmp++;
							vExcel.add(tmp,volretstr);
							tmp++;
							
							app.Logging.getDebug("vector size 1 "+final_Vector.size());
							app.Logging.getDebug("vExcel vector size>>> "+vExcel.size());
							
						}
						session.setAttribute("indexList",indexList);
						session.setAttribute("to",to);
						session.setAttribute("from",from);
						session.setAttribute("ci2",vExcel);
						}
						catch(SQLException ex){}
					}
			} finally{
				try{
					if(connection!=null)
						connection.close();
				}catch(SQLException ee){
					try{
						if(connection!=null)
							connection.close();
					}catch(Exception ex){
						Logging.getError(" Error : Unable to close connection "+ex.getMessage());
					}
					Logging.getError(" Error : Unable to close connection "+ee.getMessage());
				}
			}
			   	}
			app.Logging.getDebug("Value of Final_Vector "+final_Vector);
			app.Logging.getDebug("Value of vExel>>>>> "+vExcel);
			setVExcel(vExcel);
			return arr;
		}
	 */
	 public ArrayList getIndexReturnsVolatility(String[] indexList, String from, String to) {	
			

			ArrayList arr = new ArrayList();
			Vector vector_index_rv1;
			ArrayList final_Vector = new ArrayList();
			Logging.debug("Inside vector_indexList_rv");
			if (indexList != null)
				if (from != null && to != null) {
					try {
						//if(connection==null)					
						connection = con.getdbConnection();
						harrier.income.com.report.AdjustDecimal ad = new harrier.income.com.report.AdjustDecimal();
						
						int j = 0;
						//app.Logging.getDebug(indexList.length+"  "+from+" "+"  "+to);
						// app.Logging.getDebug("IndexList length is "+indexList.length);
						vExcel.clear();
						for (int k = 0; k < indexList.length; k++) {
							//vector_index_rv1.clear();
							vector_index_rv1 = new Vector();

							Logging.debug("Value of K  " + indexList[k]);

							String Query = ConnectInit.queries
									.getProperty("indexwise_returns_and_volatility");
							try {
								pst = connection.prepareStatement(Query);
								pst.setString(1, indexList[k]);
								pst.setString(2, from);
								pst.setString(3, to);
								rst = pst.executeQuery();
								//vExcel.clear();

								Logging.debug("setVector_index_rv1");

								int i = 0;
								double tmcv = 0.00;
								Logging.debug("Resultset =" + rst);

								while (rst.next()) {
									if (rst.getString(1) == null) {
										vector_index_rv1.add(i, "0");
										//vExcel.add(i, "0");
									} else {
										vector_index_rv1.add(i, rst.getString(1));
										//vExcel.add(i, rst.getString(1));
									}

									i++;

									if (rst.getString(2) == null) {
										vector_index_rv1.add(i, "--");
										//vExcel.add(i, "--");
									} else {
										vector_index_rv1.add(i, rst.getString(2));
										//vExcel.add(i, rst.getString(2));
									}
									i++;

									if (rst.getString(3) == null) {
										vector_index_rv1.add(i, "0");
										//vExcel.add(i, "0");
									} else {
										vector_index_rv1.add(i, rst.getString(3));
										//vExcel.add(i, rst.getString(3));
										tmcv = tmcv
												+ (double) Double.parseDouble(rst
														.getString(3));
									}
									i++;
								}

								Logging.debug("vector size "
										+ vector_index_rv1.size());

								int m = 0;
								String str1 = null, str2 = null;

								if (vector_index_rv1.size() != 0) {
									str1 = (String) vector_index_rv1.get(m);
									m++;
									j++;

									str2 = (String) vector_index_rv1.get(m);
									m++;
									j++;

									Logging
											.debug("before getMonthlyReturns");

									double mr = getMonthlyReturns(vector_index_rv1);

									String mrstr = new Double(mr).toString();

									mrstr = ad.indexcompose(mrstr);

									//	vector_index_rv.add(j,mrstr);
									m++;
									j++;
									double volret = getAvgDailyVolatility(
											vector_index_rv1, tmcv);

									String volretstr = new Double(volret)
											.toString();
									volretstr = ad.indexcompose(volretstr);
									//	vector_index_rv.add(j,volretstr);
									j++;

									final_Vector.add(new returnVol(str1, str2,
											mrstr, volretstr));
									int tmp = 0;

									vExcel.add(tmp, str2);
									tmp++;
									vExcel.add(tmp, mrstr);
									tmp++;
									vExcel.add(tmp, volretstr);
									tmp++;

									Logging.debug("vector size 1 "
											+ final_Vector.size());
									Logging.debug("vExcel vector size>>> "
											+ vExcel.size());
								}
								session.setAttribute("indexList",indexList);
								session.setAttribute("to",to);
								session.setAttribute("from",from);
								session.setAttribute("ci2",vExcel);
							} catch (SQLException ex) {
							}
						}
					} finally {
						try {
							if (connection != null)
								connection.close();
						} catch (SQLException ee) {
							try {
								if (connection != null)
									connection.close();
							} catch (Exception ex) {
								Logging
										.error(" Error : Unable to close connection "
												+ ex.getMessage());
							}
							Logging
									.error(" Error : Unable to close connection "
											+ ee.getMessage());
						}
					}
				}
			Logging.debug("Value of Final_Vector " + final_Vector);
			Logging.debug("Value of vExel>>>>> " + vExcel);
			//setVExcel(vExcel);
			return final_Vector;
		}

	 public double getAvgDailyVolatility(Vector v,double indexmean)
		{
			
			try{
							
			
				Logging.debug("Inside getAvgDailyVolatility()");
				double sum_volatility=0.0,vratio=0.0,sum_indexvolatility=0.0,sum_indexvalue=0.0;
				Vector vol=new Vector();
				int i=0,m=0;
				indexmean=indexmean/(v.size()/3);
				Logging.debug("indexmean "+indexmean);				
				i=0;
				
				while(i<v.size())
				{
					i++;i++;
					double indval1=(double)Double.parseDouble((String)v.get(i));
					Logging.debug("indval1 "+indval1+" indexmean "+indexmean);
					sum_indexvalue=(indval1-indexmean);
					vol.add(new Double(sum_indexvalue).toString());
					Logging.debug("sum_indexvalue "+sum_indexvalue+" sum_indexvolatility "+sum_indexvolatility);
					sum_indexvolatility=sum_indexvolatility+sum_indexvalue;
					Logging.debug(" sum_indexvolatility "+sum_indexvolatility);
					i++;
				}
				
				i=0;
				
				while(i<vol.size())
				{
					double mult1=((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility);
					Logging.debug(" mult1 "+mult1);
					double mult=(((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility)*((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility));
					Logging.debug(" sum_volatility "+sum_volatility+" mult "+mult);
					sum_volatility=sum_volatility+mult;
					i++;
				}
			 vratio= 1.00/(((vol.size())));
			 Logging.debug(" sum_volatility "+sum_volatility);
			 double avgdailyvol=(Math.sqrt((vratio*sum_volatility)));
			 Logging.debug("vector size "+v.size()+" avgdailyvol is "+avgdailyvol);
			return avgdailyvol;
			}
			
			finally{
				try{
					
				}
				catch(Exception e)
				{
					
				}
			}
			
		}
		public double getMonthlyReturns(Vector v)
		{
				Logging.debug("Inside getMonthlyReturns()");
				double mreturn=0.0,lmr=0.0,fmr=0.0;
				int l=v.size();
				Logging.debug("Inside getMonthlyReturns()"+l);
			if(v.size()!=0){
				lmr=(double)Double.parseDouble((String)v.get(l-1));
				fmr=(double)Double.parseDouble((String)v.get(2));
			}
			Logging.debug(" l size "+l+" lmr "+lmr+" fmr"+fmr);
			if(fmr!=0.00){
					mreturn=((lmr-fmr)/fmr);
			}else{
					mreturn=0.00;
			}
			Logging.debug("mreturn "+mreturn);
			return mreturn;
		}
	 
/* ============================================  getINdexReturnsVolatality  ===================================== */
		

		
/* ============================================  getstockcapitalchange  =================================== */		
		
		public ArrayList getCapitalChangeToUniverse(String selectExchange,String from,String to, String exchangeName) {
			String stkId=null,stkName=null, faceVal=null, tis=null, mCap=null, iwf=null,
			CAName=null, date=null;
			Connection connection=null;
			PreparedStatement stmt =null;
			//Connection connection = null;
			
			//org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
			Vector vec=new Vector();
			ArrayList tempData= new ArrayList();
			int j = 0;
			
			StockDetails stkDetails;
			try {
				AdjustDecimal ad = ConnectInit.getAdjustDecimal();
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null){
				connection=con.getdbConnection();
			}
			rst = con.highlowResultmktStatus("capital_change_to_universe",selectExchange,from,to);
			Logging.debug("set vector_capitalchangetouniv of capital change to universe");
			int i =0;
			
				while (rst.next()) {
					Logging.debug(" Inside while: i="+i);

					if (rst.getString(1) == null) {						// stk_id
						stkId= "--";
						
					} else {
						stkId= rst.getString(1).toString();
					}
					vec.add(i, stkId);
					i++;
					
					if (rst.getString(2) == null) {						//Stock Name
						stkName= "--";
					} else {
						stkName= rst.getString(2).toString();
					}
					vec.add(i, stkName);
					i++;
					
					if (rst.getString(3) == null) {						// Face value
						faceVal= "--";
					} else {
						String fcVal=(String) rst.getString(3);            		
			        	fcVal=ad.indexcompose(fcVal);
			        	faceVal= fcVal;
						
					}
					vec.add(i, faceVal);
					i++;
					
					if (rst.getString(4) == null) {						// TIS
						tis= "0.00";
					} else {
						tis=rst.getString(4).toString();
					}
					vec.add(i, tis);
					i++;
				
					if (rst.getString(5) == null) {						// mCap
						mCap= "0";
					} else {
						String mVal=(String) rst.getString(5);            		
			        	mVal=ad.indexcompose(mVal);
			        	mCap= mVal;
						
					}
					vec.add(i, mCap);
					i++;
					
					if (rst.getString(6) == null) {						// iwf
						iwf= "--";
					} else {
						iwf= rst.getString(6).toString();
					}
					vec.add(i, iwf);
					i++;
					
					if (rst.getString(8) == null) {						// CA name
						CAName= "--";
					} else {
						CAName= rst.getString(8).toString();
					}
					vec.add(i, CAName);
					i++;
					
					if (rst.getString(7) == null) {						// date
						date= "--";
					} else {
						date= rst.getString(7).toString();
					}
					vec.add(i, date);
					i++;
					Logging.debug("stk ID "+ stkId + " stkName = "+ stkName+ " faceVal = " + faceVal+
								"\n tis= "+tis+ " mCap= "+mCap+" iwf= "+iwf+ "CAName= "+CAName+" date= "+date); 
					stkDetails = new StockDetails(stkId, stkName, faceVal, tis, mCap, iwf, CAName, date);
					//indexMovingTable.put(new Integer(++j),stkDetails);
					tempData.add(stkDetails);
					
				}
				Logging.debug("No of cols = "+ tempData.size());
				rst.close();
				Table_data = tempData;
				//capitalChangeVec=vec;
				session.setAttribute("ci2",vec);
				session.setAttribute("from",from);
				session.setAttribute("to",to);
				session.setAttribute("var",selectExchange);
				session.setAttribute("stkName",exchangeName);	
				//Logging.debug("^^^^^^^^^^^^^^^^6"+vec);
				return Table_data;
			} catch (SQLException sqlexp) {
				Logging.error("Error : "+sqlexp.getMessage());
			}finally{
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ee){
					try{
						if(connection!=null)
							connection.close();
					}catch(Exception ex){
						Logging.error(" Error : Unable to close Connection "+ex.getMessage());
					}
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}				
			return Table_data;
		}
		
	/* ==========================================End of getstockcapitalchange  ===================================== */
			
	/* ============================================  getstocklist  ===================================== */		
		public Collection getStockList(String selectExchange, String filter ) {
			
			String stkId=null,stkName=null, faceVal=null, tis=null, mCap=null, 
				close = null, date=null; int j = 0;
			double mCapVal=0.0;
			Connection connection=null;
			PreparedStatement stmt =null;
			//Connection connection = null;
			
			//org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
			org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
			
			Vector vec=new Vector();
			ArrayList tempData= new ArrayList();
			Vector tempVector=new Vector();
			
			StockDetails stkDetails;
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			try {
				AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			if(connection==null){
				connection=con.getdbConnection();
			}
			int i =0;
			rst = con.returnResult("get_stock_list_for_report",selectExchange);
		
				while (rst.next()) {
					if (rst.getString(1) == null) {							// stk ID
						stkId = "0";
					} else {
						stkId= rst.getString(1).toString();
					}
					vec.add(i,stkId);
					i++;
						
					if (rst.getString(2) == null) {							// stk Name
						stkName = "--";
					} else {
						stkName = rst.getString(2).toString();
					}
					vec.add(i,stkName);
					i++;
					
					if (rst.getString(3) == null) {							// tis
						tis="0";
					} else {
						tis = rst.getString(3).toString();
						tis= ad.indexcompose(tis);
					}
					vec.add(i,tis);
					i++;
					
					if (rst.getString(4) == null) {							// close
						close= "0";
					} else {
						close=rst.getString(4).toString();
						close=ad.indexcompose(close);
					}
					vec.add(i,close);
					i++;
					
					if ((rst.getString(3) != null) && (rst.getString(4) != null)) {		// Calculate mCap
						mCapVal=((double)Double.parseDouble(rst.getString(3))*(double)Double.parseDouble(rst.getString(4)));
					}else{
						mCapVal=0.00;
					}	
					if (mCapVal == 0.00) {
						mCap = "0.00";
					} else {
						mCap=ad.indexcompose(mCap);
					}
					vec.add(i,mCap);
					i++;
					
					if (rst.getString(5) == null) {						// face Val
						faceVal= "0";
					} else {
						faceVal= rst.getString(5).toString();
						faceVal=ad.indexcompose(faceVal);
					}
					vec.add(i,faceVal);
					i++;
					
					if (rst.getString(6) == null) {						// date
						date= "0";
					} else {
						date= rst.getString(6).toString();
					}
					vec.add(i,date);
					i++;
					
					if(filter == null){
						stkDetails = new StockDetails(stkId, stkName,tis,close, mCap, faceVal, date);
						tempData.add(stkDetails);
						indexMovingTable.put(new Integer(++j),stkDetails);
						
					}else {
						String fletter=filter.substring(0,1);
						String wordAlpha = stkName.substring(0,1);
						if(fletter.equals(wordAlpha))
						{
							stkDetails = new StockDetails(stkId, stkName,tis,close, mCap, faceVal, date);
							tempData.add(stkDetails);
							indexMovingTable.put(new Integer(++j),stkDetails);
						}
					}
				}
				Logging.debug("No of cols = "+ tempData.size());
				rst.close();
				Table_data = tempData;
				//stklistVec=vec;
				return Table_data;
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :"+sqlexp.getMessage());
			} finally{
			try{
				if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}	
			return Table_data;
		}
	
    /* ============================================  getstocklist  ===================================== */		
		
	/* ============================================  Traded Volume  ===================================== */
		public ArrayList getTradedVolume(String selectIndExch,String traded_volume,String from,String to, String filter) {
			Logging.debug(" Inside Traded Volume");
	//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
			//Logging.getDebug("Inside setVector_traded_volume exchange_id and indexid is "+exch_id+" , "+ind_id);
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();	
			String stk_id=null, stk_name=null, trd_vol=null;
			//Connection connection=null;
			StockDetails stkDetails;
			ArrayList tempData= new ArrayList();
			Vector vec=new Vector();
			int i=0;
			int j = 0;
			try {
				if(connection == null)
					connection = con.getdbConnection();
				if(traded_volume.length()< 1){
					traded_volume="0";
				}
				if(filter.equals("1")){  // Exchange wise
					Logging.debug(" Inside filter = 1(Exchange )");
					rst = con.tradedVolumeResult("traded_volume_list_exchange_wise",selectIndExch,traded_volume,from,to);
				}else {
					Logging.debug("Inside filter = 2 (Index) ");
					rst = con.tradedVolumeResult("traded_volume_list_index_wise",selectIndExch,traded_volume,from,to);
				}
				
				Logging.debug("rst in traded volume is "+rst);
				
				while(rst.next()) {
			
					if (rst.getString(1) == null) {				// stock id
						stk_id= "0";
					} else {
						stk_id= rst.getString(1).toString();
					}
					vec.add(i,stk_id);
					i++;
							
					if (rst.getString(2) == null) {				// stock name
						stk_name= "--";
					} else {
						stk_name= rst.getString(2).toString();
					}
					vec.add(i,stk_name);
					i++;
						
					if (rst.getString(3) == null) {				// traded vol
						trd_vol= "0.00";
					} else {
						String ad1=(String) rst.getString(3);            		
			        	ad1=ad.indexcompose(ad1);
			        	trd_vol= ad1;
					}
					vec.add(i,trd_vol);
					i++;
					
					stkDetails = new StockDetails(stk_id, stk_name,trd_vol);
					tempData.add(stkDetails);
					indexMovingTable.put(new Integer(++j),stkDetails);
					
				}
				Table_data = tempData;
				//trdVolVec=vec;
				Logging.debug("size of arraylist "+tempData.size());
				session.setAttribute("ci2",vec);
				session.setAttribute("var",filter);
				session.setAttribute("indExch",selectIndExch);
				session.setAttribute("from",from);
				session.setAttribute("to",to);				
				
				return Table_data;
				
			} catch (Exception sqlexp) {
				Logging.error("Error : "+sqlexp.getMessage());
			}finally{
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ee){
					
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
			return Table_data;
		}
	/* ============================================ Traded Volume ===================================== */	
	
	/* ============================================  Stock Dividend  ===================================== */		
		public ArrayList getStockDividend(String selectIndExch, String from, String to, String filter,String indExch) {
			Logging.debug(" Inside Stock dividend");
			String stk_id=null, stk_name=null, face=null, tis = null, mcv=null, amt=null, date=null;
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
			session.setAttribute("var",filter);
			session.setAttribute("varid",filter);
			session.setAttribute("from",from);
			session.setAttribute("to",to);
			
			session.setAttribute("indExch",indExch);
			//Connection connection=null;
			StockDetails stkDetails;
			ArrayList tableData = new ArrayList();
			ArrayList tempData= new ArrayList();
			Vector vec = new Vector();
			Vector stkDividentVec = new Vector();
			int i=0;
			int j = 0;
			try {
				if(connection == null)
					connection = con.getdbConnection();
				
				if(filter.equals("1")){  // Exchange wise
					Logging.debug(" Inside filter = 1(Exchange )");
					rst = con.changeInStockDetailResult("stock_divident_exchange_wise",selectIndExch,from,to);
					
				}else {
					Logging.debug("Inside filter = 2 (Index) ");
					rst = con.changeInStockDetailResult("stock_divident_index_wise",selectIndExch,from,to);
				}
				
				Logging.debug("rst in traded volume is "+rst);
				
				while (rst.next()) {

					if (rst.getString(1) == null) {							// stock id
						stk_id = "0";
						//vector_stockDivident.add(i, "0");
					} else {
						stk_id = rst.getString(1).toString();
						//vector_stockDivident.add(i, rst.getString(1));
					}
					vec.add(i,stk_id);
					i++;

					if (rst.getString(2) == null) {							// stock name
						stk_name = "--";
						//vector_stockDivident.add(i, "--");
					} else {
						stk_name = rst.getString(2).toString();
						//vector_stockDivident.add(i, rst.getString(2));
					}
					vec.add(i,stk_name);
					i++;

					if (rst.getString(3) == null) {							// face Val
						face = "0";
						//vector_stockDivident.add(i, "0");
					} else {
						face = rst.getString(3).toString();
						//vector_stockDivident.add(i, rst.getString(3));
					}
					vec.add(i,face);
					i++;
					
					if (rst.getString(4) == null) {							// tis
						tis = "0";
						//vector_stockDivident.add(i, "0");
					} else {
						tis = rst.getString(4).toString();
						//vector_stockDivident.add(i, rst.getString(4));
					}
					vec.add(i,tis);
					i++;

					if (rst.getString(5) == null) {							// mcv
						mcv = "0.00";
						//vector_stockDivident.add(i, "0.00");
					} else {
						String ad1=(String) rst.getString(5);            		
		        		ad1=ad.indexcompose(ad1);
		        		mcv = ad1;
						//vector_stockDivident.add(i, ad1);
					}
					vec.add(i,mcv);
					i++;
					if (rst.getString(6) == null) {							// amount
						amt = "0.00";
						//vector_stockDivident.add(i, "0.00");
					} else {
						amt = rst.getString(6).toString();
						//vector_stockDivident.add(i, rst.getString(6));
					}
					vec.add(i,amt);
					i++;	
					if (rst.getString(7) == null) {							// date
						date = "--";
						//vector_stockDivident.add(i, "--");
					} else {
						date = rst.getString(7).toString();
						//vector_stockDivident.add(i, rst.getString(7));
					}
					vec.add(i,date);
					i++;
					
					stkDetails = new StockDetails(stk_id, stk_name,face, tis, mcv, amt, date, 21);
					tempData.add(stkDetails);
					//indexMovingTable.put(new Integer);
					
				}
				tableData = tempData;
				stkDividentVec= vec;
				
				session.setAttribute("ci2",vec);
				Logging.debug("size of arraylist "+tempData.size());
				//return indexMovingTable.values();
				return tableData ;
			} catch (Exception sqlexp) {
				Logging.error("Error : "+sqlexp.getMessage());
			}finally{
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ee){
					try{
						if(connection!=null)
							connection.close();
					}catch(Exception ex){
						Logging.error(" Error : Unable to close Connection "+ex.getMessage());
					}
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
			 return tableData ; 
			//return indexMovingTable.values();
		}			
	/* ============================================  Stock Dividend  ===================================== */

	/* ============================================  inactive Stock list ===================================== */		

		
		public ArrayList getInactiveStockList(String index_id) {
	 		//Connect con=ConnectInit.getConnect();
			//Connection connection=null;
			//PreparedStatement pst;
			ResultSet rs=null;
			String query;
			ArrayList temp = new ArrayList();
			ArrayList tableData = new ArrayList();
			Vector vExcel = new Vector();
			
			//temporary variables to pass to the constructor of InactiveStockDetails
			String stockId,stockName,outstandingShares,faceValue,date;
			//String index_id=getD1();
			try{
				query = ConnectInit.queries.getProperty("get_stock_list_for_inactive_stock");
				if(connection==null)
					connection=con.getdbConnection();
					try {
						pst = connection.prepareStatement(query);
						pst.setString(1, index_id);
						rs = pst.executeQuery();
						int i=0;
						vExcel.clear();
						while (rs.next()) {
							if (rs.getString(1) == null) {
								stockId="0";
							} else {
								stockId=rs.getString(1);
							}
							vExcel.add(i,stockId);
							i++;		
							if (rs.getString(2) == null) {
								stockName="--";
							} else {
								stockName=rs.getString(2);
							}
							vExcel.add(i,stockName);
							i++;

							if (rs.getString(3) == null) {
								outstandingShares="0";
							} else {
								outstandingShares=rs.getString(3);	
							}
							vExcel.add(i,outstandingShares);
							i++;
							if (rs.getString(4) == null) {
								faceValue="0";
							} else {
								String price=(String)rs.getString(4);
								faceValue=price;
							}
							vExcel.add(i,faceValue);
							i++;				

							if (rs.getString(5) == null) {
								date="0";
							} else {
								date=rs.getString(5);
								
							}
							vExcel.add(i,date );
							i++;										
							InactiveStockDetails isd=new InactiveStockDetails(stockId,stockName,outstandingShares,faceValue,date);
							temp.add(isd);
						}
						
					} catch (Exception e) {
						// TODO: handle exception
						Logging.error(" Error : "+e.getMessage());
											}
					    }catch(Exception e){
					    	Logging.error(" Error : "+e.getMessage());
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
							    Logging.error(" Error : Unable to close Connection "+ex.getMessage());
			    					 }
						 	   Logging.error(" Error : Unable to close Connection "+ee.getMessage());
					    		}
					    }
					    //setVExcel(vExcel);
					    session.setAttribute("ci2",vExcel);
					    session.setAttribute("var",index_id);
					    tableData=temp;
					    return tableData;
		}	
	/* ============================================  inactive Stock list ===================================== */	
		
	/* ============================================  Stock Details Bet Dates ===================================== */	
		public ArrayList StockDetailsBetweenDate(String index, String selectStock, String from, String to) {
			String stkName=null, openVal=null, closeVal=null, lowVal=null, highVal=null,
			trdVal=null, trdVol=null, noTrades = null, mcv =null, pDate=null;
			//Connect con1=ConnectInit.getConnect();
			//Connect con=new app.Connect();
			PreparedStatement stmt =null;
			//Connection connection = null;
			//StockDetails[] sd1 ;
			
			//Vector vec=new Vector();
			//Vector vecStockDetails=new Vector();
			ArrayList tempData= new ArrayList();
			ArrayList tableData= new ArrayList();
			StockDetails stkDetails;
			Connection connection=null;
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			try {
			if(connection==null)
			{
				connection=con.getdbConnection();
			}
			rst = con.highlowResult("stock_price_daily_between_date", selectStock,from,to);
			try {
			int i =0;
			
			//	String query=con.queries.getProperty("total_return_index_old_data");
				//Logging.getDebug(" query = " + query);
				//stmt = Connect.con.prepareStatement(query);
				//stmt.setString(1,selectStock);
				//stmt.setString(2,from);
				//stmt.setString(3,to);
				//rst = stmt.executeQuery();
			
				while (rst.next()) {
					
					if (rst.getString(2) == null) {						// stock name
						stkName = "--";
					} else {
						stkName= rst.getString(2).toString();
					}
					Table_data_vector.add(i, stkName);
					i++;
					
					if (rst.getString(3) == null) {						// open val
						openVal ="0";
					} else {
						openVal = rst.getString(3).toString();
					}
					Table_data_vector.add(i,openVal);
					i++;
					
					if (rst.getString(4) == null) {						// close val
						closeVal = "0";
					} else {
						closeVal=rst.getString(4).toString();
					}
					Table_data_vector.add(i,closeVal);
					i++;
					
					if (rst.getString(5) == null) {						// low val
						lowVal ="0";
					} else {
						lowVal = rst.getString(5).toString();					
					}
					Table_data_vector.add(i,lowVal);
					i++;
					
					if (rst.getString(6) == null) {						// high Val
						highVal = "0";					
					} else {
						highVal = rst.getString(6).toString();
					}
					Table_data_vector.add(i,highVal);
					i++;
					
					if (rst.getString(7) == null) {						// Traded Volume
						trdVol = "0";
					} else {
						trdVol = rst.getString(7).toString();
					}
					Table_data_vector.add(i,trdVol);
					i++;
					
					if (rst.getString(10) == null) {					// Traded Value
						trdVal = "--";
					} else {
						trdVal = rst.getString(10).toString();
					}
					Table_data_vector.add(i,trdVal);
					i++;
					
					if (rst.getString(9) == null) {						// MCap mcv
						mcv ="--";
					} else {
						//Logging.debug("*************************************** MCap = "+  rst.getDouble(9));
						//double mCapVal = (double)rst.getDouble(9);					
						//mcv = new Double(mCapVal).toString();//    rst.getString(9);
						mcv = rst.getString(9).toString();
					}
					Table_data_vector.add(i,mcv);
					i++;
						
					if (rst.getString(11) == null) {					// No of trades
						noTrades = "0";
					} else {
						noTrades = rst.getString(11).toString();
					}			
					Table_data_vector.add(i,noTrades);
					i++;
				
					if (rst.getString(8) == null) {						// Price date
						pDate = "--";
					} else {
						pDate = rst.getString(8).toString();
					}
					Table_data_vector.add(i,pDate);
					i++;
						stkDetails = new StockDetails(stkName, openVal, closeVal, lowVal, highVal, 
							trdVal, pDate, mcv, trdVol, noTrades );
					/*sd1= new StockDetails(stkName, openVal, closeVal, lowVal, highVal, 
							trdVal, pDate, mcv, trdVol, noTrades );
					*/
					tempData.add(stkDetails);	
					session.setAttribute("StoredVector",Table_data_vector);
					session.setAttribute("ci2",Table_data_vector);
				}	
				rst.close();	
				
			}catch (SQLException sqlexp) {
				Logging.error("SQL Error : "+sqlexp.getMessage());
			}catch (Exception exp) {
				Logging.error(" Error : "+exp.getMessage());
			}
			
			Logging.debug(" tempData = "+ tempData.size());
			tableData = tempData;
			
			//vecStockDetails = vec;
			//Logging.getDebug(" vector = " + vecStockDetails.toString());
			
			//setVector_highlowtable(vector_highlowtable);
	//		org.jfree.chart.demo.servlet.ComposeIndex ci = new org.jfree.chart.demo.servlet.ComposeIndex();
			ComposeIndex ci=ConnectInit.getComposeIndex();
			String indexName = ci.getIndexName(index);
			session.setAttribute("ci2",Table_data_vector);
			session.setAttribute("StoredVector",Table_data_vector);
			session.setAttribute("type","6");	
			session.setAttribute("var1",index);
			session.setAttribute("indName",indexName);
			session.setAttribute("stkName",stkName);
			session.setAttribute("from",from);  
			session.setAttribute("to",to);			
			
//			Close the Dynamic Connection : Manoj Adekar
			} catch (Exception e) {
				Logging.debug("Error :" + e);
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
			
			return tableData;
		}
	/* ============================================  Stock Details Bet Dates ===================================== */
		
	/* ============================================  Index Wise Weightage ===================================== */	
		public ArrayList getIndexWiseWeightage(String index12) {
			String industryname=null,marketcap=null,weightage=null,Strmvc=null,mar=null;
			
			IndexComposeReportMethod Icr = new IndexComposeReportMethod();
			Vector vi=new Vector();
			//Connection connection=null;
			double total1=0.00,total2=0.00;
			double strweightage=0.0,market=0.00;			
			String tno1=null;
			
			//Connect con=new app.Connect();
			ArrayList indweighttable = new ArrayList();
			ArrayList tempdata=new ArrayList();
			IndexWise indexwise;
			try {
				AdjustDecimal ad = ConnectInit.getAdjustDecimal();
				if (connection==null){
					
				
					connection=con.getdbConnection();
				}
			rst = Icr.indweightResult(connection,"industry_wise_weightage", index12);
			int i = 0;
			Logging.debug("setVector_indweighttable of Industry wise weightage");
			try {

				while (rst.next()) {

					
					if (rst.getString(1) == null) {
						industryname= "0";
						} else {
							industryname=rst.getString(1);
							vi.add(i,rst.getString(1));
						}
					i++;
					if (rst.getString(2) == null) {
						marketcap= "0";
						} else {
							marketcap=rst.getString(2);
							marketcap=ad.indexcompose(marketcap);
							marketcap=org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(marketcap);
							vi.add(i,rst.getString(2));
						}
					i++;
					mar=rst.getString(2);
					mar=ad.indexcompose(mar);
					market=Double.parseDouble(mar);
					total2=total2+market;
					tno1=ad.shareholdingpatt(total2);
					tno1=org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(tno1);
					if (rst.getString(3) == null) {
						weightage= "0";
						} else {
							weightage=rst.getString(3);
							weightage=ad.indexcompose(weightage);
							vi.add(i,rst.getString(3));
						}
					i++;
					Strmvc=rst.getString(3);
					strweightage=Double.parseDouble(Strmvc);
					total1=total1+strweightage;
					indexwise = new IndexWise(industryname,marketcap,weightage);	
					tempdata.add(indexwise);
				}
				/*
				 *	To add a row For Total indexweight and Total market Cap				 
				 */
				if(total1>=99.9999)
					total1=100.00;
				String strtotal=(String)new Double(total1).toString();
				strtotal=ad.indexcompose(strtotal)+"%";
				
				/*Double totalmarketcapvalue = new Double(total2);
				String totalmktcapvalue = totalmarketcapvalue.toString();
				Double totalweightage = new Double(total1);
				String totalweight = totalweightage.toString()+"%";*/
				
				tempdata.add( new IndexWise( "Total", tno1, strtotal ));
				rst.close();
			} catch (SQLException sqlexp) {
				Logging.error("Error : "+sqlexp.getMessage());
			}
			}catch(Exception e){
				Logging.error(" Error : "+e.getMessage());
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
						Logging.error(" Error : Unable to close connection "+ex.getMessage());
					}
					Logging.error(" Error : Unable to close connection "+ee.getMessage());
				}
			}
			indweighttable=tempdata;
			
				String s1="<table class ='sorted' ID='sortTable' border='0' align='center' cellpadding='0' cellspacing='0' bgcolor='#EFEFEF' style='display:none'>"   
	               +"<thead ><tr><th width='150'>Industry Name</th><th width='170'>Mkt. Cap.(In Millions)</th>"	
				   +"<th width='150'>Weightage(%)</th></tr></thead><tbody>"; 
					int i=0;
					while(i<vi.size()){
						
						s1=s1+"<tr><td>"+vi.get(i)+"</td>";i++;
						s1=s1+"<td>"+vi.get(i)+"</td>";i++;
						s1=s1+"<td>"+vi.get(i)+"</td></tr>";i++;
						
					}
					s1=s1+"</tbody></table>";
			
			session.setAttribute("StoredVector",vi);
			session.setAttribute("type","3");
			session.setAttribute("ci2",vi) ; //this line is added for links excel,pdf
			session.setAttribute("var",index12);
			session.setAttribute("varid",index12);  //used for e- mail report 
			 //Logging.debug(indweighttable);
			 //setTotal(total1);
			 //setVal(tno1);
			 //setVi(vi);
			 return indweighttable;
		}
   /* ============================================  Index Wise Weightage ===================================== */
		
   /* ============================================  CompanyWeightage  ===================================== */		
		
		public ArrayList getCompanyWeightage(String local_d1, String local_date) {
			double totalMcap=0;
			double totalWt=0;
			Vector CompanyWeightageVector = new Vector();
			//Connect con=ConnectInit.getConnect();
			//Connection connection=null;
			//PreparedStatement pst=null;
			//ResultSet rs=null;
			String company=null,mcap=null,weightage=null;
			ArrayList temp=new ArrayList();
			ArrayList companyWeightage=new ArrayList();
			try{
				AdjustDecimal ad = ConnectInit.getAdjustDecimal();
				if(connection==null)
				{
					connection=con.getdbConnection();
				}
					try {
						
						String query = ConnectInit.queries.getProperty("company_wise_weightage");
						//Logging.debug("Query   = "+query);
						pst = connection.prepareStatement(query);
						pst.setString(1, local_d1);
						//pst.setString(2, local_date);			
						rst = pst.executeQuery();
						int i=0;
						
						while (rst.next()) {
							if (rst.getString(1) == null) {
								company="--";
								CompanyWeightageVector.add(i, "--");
								
							} else {
								company=rst.getString(1);
								CompanyWeightageVector.add(i, rst.getString(1));
								
							}
							i++;
							if (rst.getString(2) == null) {
								mcap="0";
								CompanyWeightageVector.add(i, "0");
								
							} else {
								mcap=rst.getString(2);
								CompanyWeightageVector.add(i, rst.getString(2));
								double NumericMcap=Double.parseDouble(mcap);
								totalMcap=totalMcap+NumericMcap;
							}
							i++;
							if (rst.getString(3) == null) {
								weightage="0";
								CompanyWeightageVector.add(i, "0");
							} else {
								weightage=rst.getString(3);
								double NumericWeightage=Double.parseDouble(weightage);
								totalWt=totalWt+NumericWeightage;
								CompanyWeightageVector.add(i, rst.getString(3));
							}
							i++;
							CompanyWiseWeightageDetails cww=new CompanyWiseWeightageDetails(company,mcap,weightage);
							temp.add(cww);
						}//end of while 
						
						String strtotal=(String)new Double(totalWt).toString();
						strtotal=ad.indexcompose(strtotal)+"%";
						
						String totalMarketCap = (String)new Double(totalMcap).toString();  
						
						temp.add(new CompanyWiseWeightageDetails("Total",totalMarketCap,strtotal));
										
						
					} catch (Exception e) {
						// TODO: handle exception
						Logging.error(" Error : "+e.getMessage());
											}
					    }catch(Exception e){
					    	Logging.error(" Error : "+e.getMessage());
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
							    Logging.error(" Error : Unable to close Connection "+ex.getMessage());
			    					 }
						 	   Logging.error(" Error : Unable to close Connection "+ee.getMessage());
					    		}
					    }
					    
					    //Logging.debug("Table Vector"+CompanyWeightageVector);
					    Table_data_vector=CompanyWeightageVector;
					    session.setAttribute("StoredVector",Table_data_vector);
					    
						session.setAttribute("type","2");
						session.setAttribute("ci2",Table_data_vector) ; //this line is added for links excel,pdf
						session.setAttribute("var",local_d1);
																	    
					    //setCompanyWeightageVector(CompanyWeightageVector);
					    companyWeightage=temp;
					    return companyWeightage;
			
		}		
		
	/* ============================================  CompanyWeightage  ===================================== */	
		
		public ArrayList getStockcontriIndexchange(String index1, String fodate, String todate) {
			
			//Logging.getDebug("Inside Vector_stockcotriIndexchange");
			
			String fdate = null;
			String tdate = null;
			
			Vector vi=new Vector();
			String stockname=null,indexmarket=null,stockmarket=null,weightage=null;
			ArrayList stockcotriIndexchange = new ArrayList();
			ArrayList tempdata=new ArrayList();
			StockContri stockcontri;
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			Connection connection=null;
			try {
				Vector date=new Vector();
		//		app.Connect con=new app.Connect();
				Connect con = ConnectInit.getConnect();		
				if(connection==null)
					{
					connection=con.getdbConnection();
					}
					rst = con.StockcontriIndexResult("stock_contribution_to_change_in_index",index1,fodate,todate);
					int i = 0,q=0;
					Logging.debug("setVector_stockcotriIndexchange");	
					try {
					while(rst.next())
					{
						Logging.debug("inside first while end "+rst);
					
						try{
							//Logging.getDebug("get tring "+rst.getString(1));
							if(rst.getString(1)==null) {
								stockname= "0";
								Logging.debug("after get");
							} else {
								stockname=(rst.getString(1).trim());
								vi.add(i,rst.getString(1));
							}
						}catch(Exception e){
							Logging.debug("Error while returning resultset"+e.getMessage());
						}
						
						i++;
						if (rst.getString(2) == null) {
							indexmarket= "0";
						} else {
							 
								String str=rst.getString(2);
								String str2=str.substring(str.indexOf(46),(str.indexOf(46)+3));
								String str1=str.substring(0,str.indexOf(46))+str2;
								indexmarket= str1;
								vi.add(i,indexmarket);
								//app.Logging.getDebug((String)rst.getString(2));
						}
					i++;
						
						if (rst.getString(3) == null) {
							stockmarket= "0";
						} else {
							String str=rst.getString(3);
							String str2=str.substring(str.indexOf(46),(str.indexOf(46)+3));
							String str1=str.substring(0,str.indexOf(46))+str2;
							stockmarket= str1;
							vi.add(i,stockmarket);
							//app.Logging.getDebug((String)rst.getString(3));
						}
						i++;
						if (rst.getString(4) == null) {
							weightage= "0";
						} else {
							weightage=rst.getString(4);
							vi.add(i,rst.getString(4));
							//app.Logging.getDebug((String)rst.getString(4));
						}
						i++;
						date.add(q,rst.getString(5));
						
						q++;
						
						//app.Logging.getDebug((String)rst.getString(5));
						date.add(q,rst.getString(6));
						
						//app.Logging.getDebug((String)rst.getString(6));
						q++;		
						
						stockcontri = new StockContri(stockname,indexmarket,stockmarket,weightage);
						tempdata.add(stockcontri);
						
					}
					}catch (SQLException sqlexp) {
						Logging.error("SQL Error :"+sqlexp.getMessage());
					}
					
				Logging.debug(""+stockcotriIndexchange.size());
				
				Logging.debug("After first while end");
				Vector vector_remStockid = new Vector();
				if(date.size()>1){
				 tdate=(String)date.get(0);
				 fdate=(String)date.get(1);
				}						
				Logging.debug("in bean"+index1+"  "+fdate+" "+"  "+tdate);
				ResultSet rst1 = new app.Connect().StockcontriSidlResult("stock_contribution_stock_id_left",index1,fdate,tdate);
				int j=0;
				while(rst1.next())
				{
					vector_remStockid.add(j, rst1.getString(1));
				}
				if(vector_remStockid.size()!=0)
				{
					for(int k=0;k<(vector_remStockid.size());k++)
					{
						String s_id=(String)vector_remStockid.get(k);
						ResultSet rst2 = new app.Connect().stockcontriIndResult("stock_contribution_to_change_in_index_individual",index1,s_id,todate,fodate);
						Logging.debug("setVector_stockcotriIndexchange");
						
							while (rst2.next()) {
								i++;
								if(rst.getString(1)==null) {
									stockname= "0";
									
								} else {
									stockname=(rst.getString(1).trim());
									
								}

								if (rst.getString(2) == null) {
									indexmarket= "0";
								} else {
									 
										String str=rst.getString(2);
										String str2=str.substring(str.indexOf(46),(str.indexOf(46)+3));
										String str1=str.substring(0,str.indexOf(46))+str2;
										indexmarket= str1;
										//app.Logging.getDebug((String)rst.getString(2));
								}

								if (rst.getString(3) == null) {
									stockmarket= "0";
								} else {
									String str=rst.getString(3);
									String str2=str.substring(str.indexOf(46),(str.indexOf(46)+3));
									String str1=str.substring(0,str.indexOf(46))+str2;
									stockmarket= str1;
									//app.Logging.getDebug((String)rst.getString(3));
								}
								if (rst.getString(4) == null) {
									weightage= "0";
								} else {
									weightage=rst.getString(4);
									//app.Logging.getDebug((String)rst.getString(4));
								}
							//	stockcontri = new StockContri(stockname,indexmarket,stockmarket,weightage);
								//Logging.debug("-----------------------------------"+stockname);
								
							}
							rst.close();
							
						}
					}
				
				//Close dynamic connection of connect class : by Manoj Adekar
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
			
			stockcotriIndexchange=tempdata;
			Logging.debug("SQL Error before return :"+stockcotriIndexchange);
			//setVi(vi);
			
			return stockcotriIndexchange;
		}	
		
	 /*	public ArrayList getTableDataPortpolio() {
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String symbol=null,ltp=null,mcap=null,shares=null;
		ArrayList temp=new ArrayList();
		String local_id=getIndex_id();
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
					String query = con.queries.getProperty("portpolio_tis_calculator_report");
					Logging.debug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					pst.setString(2,local_id);
					rs = pst.executeQuery();
					int i=0;
					while(rs.next())
					{
						symbol=rs.getString(7);
						 ltp=rs.getString(6);
						 mcap=rs.getString(4);
						 shares=rs.getString(2);

						PortpolioTisDetails ptd=new PortpolioTisDetails(symbol,ltp,mcap,shares);
						temp.add(ptd);
					}
				} catch (Exception e) {
					// TODO: handle exception
					Logging.getError(" Error : "+e.getMessage());
										}
				    }catch(Exception e){
				    	Logging.getError(" Error : "+e.getMessage());
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
						    Logging.getError(" Error : Unable to close Connection "+ex.getMessage());
		    					 }
					 	   Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
				    		}
				    }
				    
				    tableDataPortpolio=temp;
				    return tableDataPortpolio;
	}
	
	
	
	*/
	
	/*public String getChart(String fodate,String todate,String selectIndex) {
		
		GraphMethods objGM = new GraphMethods();
		Logging.debug("Before calling getGraphchart");
 		objGM.getGraphChart1(request,new PrintWriter(out));
 	 	String filename=objGM.getFilename();
	  	String graphURL=objGM.getGraphURL();  
	  	Logging.debug("After calling getGraphchart");
		return "";
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		out = res.getWriter();
		request = req; 
		Logging.debug("*&&&&&*&*&*"+req);
		GraphMethods objGM = new GraphMethods();
		Logging.debug("Before calling getGraphchart");
 		objGM.getGraphChart1(request,new PrintWriter(out));
 	 	String filename=objGM.getFilename();
	  	String graphURL=objGM.getGraphURL();  
	  	Logging.debug("After calling getGraphchart");		
	}
	*/
}
