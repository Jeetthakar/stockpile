/*
 * Created on Mar 11, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import com.harrier.initializeation.ConnectInit;

import sysconfig.model.stockDetailFromDate;
import app.Connect;
import app.IndexCalculatorCollection;
/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StockDetailFromDateForm extends ActionForm{
	Logger Logging = Logger.getLogger(StockDetailFromDateAction.class);
	private String fromDate=null,filter=null,select=null,text=null,b1=null;
	private String selectStock=null,ic=null,clear=null,toDate=null,defaultVal=null,selectDemo=null;
	public String selectStock1;
	private Collection selectCollection=null,stockCollection=null;
	private ArrayList tableDate=null;
	private Vector vExcel=new Vector();		//vector for excel download
	
	private String hiddenVar=null;
	int id=0;
	String tval,tvol;
	stockDetailFromDate sdf;
	double exch;
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	private Connection connection=null;
	
	 /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	fromDate=null;
	  	select=null;
	  	b1=null;
	  	toDate=null;
	  	filter=null;
	  	selectCollection=null;
	  	stockCollection=null;
	  	tableDate=null;
	  	tableDate=null;text=null;
	  	selectStock=null;
	  	clear=null;
	  	defaultVal=null;
	  	selectDemo=null;
	  	hiddenVar=null;
	  }
	  

	  /**
	   * VALIDATE FORM DATA
	  * **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request)
	  {
	  	ActionErrors errors = new ActionErrors();
	  	return errors;
	  }
	  
	/**
	 * @return Returns the clear.
	 */
	public String getClear() {
		return clear;
	}
	/**
	 * @param clear The clear to set.
	 */
	public void setClear(String clear) {
		this.clear = clear;
	}
	/**
	 * @return Returns the filter.
	 */
	public String getFilter() {
		return filter;
	}
	/**
	 * @param filter The filter to set.
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
	/**
	 * @return Returns the fromDate.
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return Returns the select.
	 */
	public String getSelect() {
		if(defaultVal!=null && defaultVal.equals("yes")){
			try {
				if(filter!=null && filter.equals("Index Wise")){
					PreparedStatement stmt = Connect.con.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 select=rst.getString(25);
					}
					rst.close();
					stmt.close();
				}
				else if(filter!=null && filter.equals("Exchange Wise")){
					PreparedStatement stmt = Connect.con.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 select=rst.getString(18);
					}
					rst.close();
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				Logging.debug(e);
			}
		}
		return select;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelect(String select) {
		this.selectDemo=select;
		this.select = select;
	}
	/**
	 * @return Returns the selectDemo.
	 */
	public String getSelectDemo() {
		return selectDemo;
	}
	/**
	 * @param selectDemo The selectDemo to set.
	 */
	public void setSelectDemo(String selectDemo) {
		this.selectDemo = selectDemo;
	}
	
	/**
	 * @return Returns the selectCollection.
	 */
	public Collection getSelectCollection() {
		Vector vec = new Vector();
		Vector vec1 = new Vector();
	//	dbconnect();
		try {
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
			{
				connection=con.getdbConnection();
			 }
		vec.add(new LabelValueBean("Not Selected","0"));
		if(filter==null || filter.trim().length()==0 || filter.equals("0")){
				selectCollection = vec;
				return selectCollection;
		}
		else if(filter.equals("Index Wise")){
			try {
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("indexUpdate_getall1"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				
				rst.close();
				stmt.close();
				selectCollection = vec;
				return selectCollection;
			} catch (SQLException e) {
				//e.printStackTrace();
				Logging.debug(e);
			}	
		}
		else if(filter.equals("Exchange Wise")){
			try {
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stock_exchange_online_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectCollection = vec;
				return selectCollection;
			} catch (SQLException e) {
				//e.printStackTrace();
				Logging.debug(e);
			}	
			
		}
//		Close the Dynamic Connection : Manoj Adekar
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

		selectCollection = vec1;
		return selectCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	public void setSelectCollection(Collection selectCollection) {
		this.selectCollection = selectCollection;
	}
	/**
	 * @return Returns the tableDate.
	 */
	synchronized public ArrayList getTableDate() {
		ArrayList Pp 	=	new ArrayList();
		TreeMap map		=	new TreeMap();
		String stockName1,priceDate1,isCA1,Series1,MCV1,Price1,stockCurrId,Pnusd,Mnusd;
		String Tis1,tradedVolume1;
		double tradedValue1;
		//dbconnect();
		try {
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
			{
				connection=con.getdbConnection();
			 }
		if(fromDate!=null && fromDate.trim().length()!=0){
			try {
				/**For C.A Applied*/	
					PreparedStatement stmt1;
					ResultSet rst1;
					//int i;
					try {
						stmt1 =connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_corporate_action"));
						stmt1.setString(1,selectStock);
						stmt1.setString(2,fromDate);
						rst1 = stmt1.executeQuery();
						
							while(rst1.next()){
								map.put(rst1.getString(2),rst1.getString(1));
							}
						rst1.close();
						stmt1.close();
					} catch (RuntimeException e2) {
					//	e2.printStackTrace();
						Logging.debug(e2);
					}
				/**For stock identifier code*/
					try {
						PreparedStatement stmt2 = connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_ic"));
						stmt2.setString(1,selectStock);
						ResultSet rst2 = stmt2.executeQuery();
						while(rst2.next()){
								ic=rst2.getString(1);
								break;
						}
						rst2.close();
						stmt2.close();
					} catch (RuntimeException e3) {
					//	e3.printStackTrace();
						Logging.debug(e3);
					}
				vExcel.clear();	
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_date_stock"));
				stmt.setString(1,selectStock);
				stmt.setString(2,fromDate);
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
				//		AdjustDecimal ad= new AdjustDecimal();
					AdjustDecimal ad = ConnectInit.getAdjustDecimal();
					stockCurrId=rst.getString(9);
						stockName1=rst.getString(2);
						Series1=rst.getString(3);
						float Tis11=rst.getFloat(4);
						Tis1=ad.shareholdingpatt(Tis11);
						Tis1=ad.indexcompose(Tis1);
						
						/*Conversion*/
						 try{
						 	String string=(new Long(stockCurrId)).toString();
	                    	String string2=(new Long("153")).toString();
	                    	String temp=IndexCalculatorCollection.getIndexCurrancyExchRate(string,string2);
	                    	if(temp!=null){
	                    		exch=new Double(temp).doubleValue();
	                    	}else{
	                    		temp=IndexCalculatorCollection.getIndexCurrancyExchRate(string2,string);
	                    		if(temp==null){
	                    			exch=1.0;
	                    		}else{
	                    			exch=1/new Double(temp).doubleValue();
	                    		}
	                    	}
						 }
	                     catch(Exception e){
	                     	Logging.debug(e);
	                     }
	                     
						/*-----------*/						
	                     
	                     float Price11=rst.getFloat(5);
	                     Pnusd=ad.shareholdingpatt(Price11);
	                     Pnusd=ad.indexcompose(Pnusd);
	                     double Price12=exch * Price11;
	                     Price1=ad.shareholdingpatt(Price12);
	                     Price1=ad.indexcompose(Price1);
	                     priceDate1=rst.getString(6);
	                     if(map.containsKey(priceDate1)){
							String nam=(String)map.get(priceDate1);
							isCA1=nam;
						}
						else{
							isCA1="n";
						}
						float MCV11=rst.getFloat(7);
						Mnusd=ad.shareholdingpatt(MCV11);
						Mnusd=ad.indexcompose(Mnusd);
						double MCV12= exch*MCV11;
						MCV1=ad.shareholdingpatt(MCV12);
						MCV1=ad.indexcompose(MCV1);
						float tradedVolume11=rst.getFloat(8);
						tvol=ad.shareholdingpatt(tradedVolume11);
						tvol=ad.indexcompose(tvol);
						try {
							tradedValue1=(Price11) * (tradedVolume11);
							tval=ad.shareholdingpatt(tradedValue1);
							tval=ad.indexcompose(tval);
						} catch (RuntimeException e1) {
							tradedValue1=0;
						//	e1.printStackTrace();
							Logging.debug(e1);
						}
						
						sdf=new stockDetailFromDate(stockName1,Series1,Tis1,Price1, priceDate1, MCV1,tvol,tval,isCA1,ic,Pnusd,Mnusd);
						Pp.add(sdf);
						//Adding data into vector for excel 
						int i=0;
						
						vExcel.add(i,stockName1);
						i++;
						
						vExcel.add(i,priceDate1);
						i++;

						vExcel.add(i,Price1);
						i++;

						vExcel.add(i,Pnusd);
						i++;

						vExcel.add(i,Tis1);
						i++;

						vExcel.add(i,MCV1);
						i++;
						
						vExcel.add(i,Mnusd);
						i++;

						vExcel.add(i,tvol);
						i++;
						
						vExcel.add(i,tval);
						i++;

						
						vExcel.add(i,Series1);
						i++;

						vExcel.add(i,isCA1);
						i++;

				}
					stmt.close();
					rst.close();
			} catch (SQLException e) {
			//	e.printStackTrace();
				Logging.debug(e);
			}
			
		}
//		Close the Dynamic Connection : Manoj Adekar
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

		setVExcel(vExcel);
		tableDate=Pp;
		return tableDate;
	}
	
	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableDate(ArrayList tableDate) {
		this.tableDate = tableDate;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = null;
	}
	/**
	 * @return Returns the selectStock.
	 */
	public String getSelectStock() {
		
		return selectStock;
	}
	/**
	 * @param selectStock The selectStock to set.
	 */
	public void setSelectStock(String selectStock) {
		this.selectStock = selectStock;
	}
		
	/**
	 * @return Returns the selectStock1.
	 */
	public String getSelectStock1() {
		return selectStock1;
	}
	/**
	 * @param selectStock1 The selectStock1 to set.
	 */
	public void setSelectStock1(String selectStock1) {
		this.selectStock1 = selectStock1;
	}
	/**
	 * @return Returns the stockCollection.
	 */
	public Collection getStockCollection() {
		//dbconnect();
		Vector vec=new Vector();
		try{
		//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		if(connection==null)
		{
			connection=con.getdbConnection();
		 }
		if(filter!=null && filter.trim().equals("Index Wise")){
			try {
			
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_stock_list_index"));
				stmt.setString(1,select);
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(1),rst.getString(2)));
				}
				rst.close();
				stmt.close();
				stockCollection = vec;
				return stockCollection;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				Logging.debug(e);
			}	
			
		}
		else if(filter!=null && filter.trim().equals("Exchange Wise")){
			try {
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_stock_list_exchange"));
				stmt.setString(1,select);
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				stockCollection = vec;
				return stockCollection;
			} catch (SQLException e) {
				//e.printStackTrace();
				Logging.debug(e);
			}	
			
		}
//		Close the Dynamic Connection : Manoj Adekar
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

		stockCollection=vec;
		return stockCollection;
	}
	/**
	 * @param stockCollection The stockCollection to set.
	 */
	public void setStockCollection(Collection stockCollection) {
		this.stockCollection = stockCollection;
	}
	/**
	 * Database Connectivity
	 * */
/*	public void dbconnect(){
		
		try {if(app.Connect.con==null){
			con.getConnection();
			}
		} catch (Exception e) {	Logging.debug(e);} 
		
	}*/
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
	 * @return Returns the tableDate.
	 */
	public ArrayList getTableDateStock() {
		ArrayList Pp 	=	new ArrayList();
		TreeMap map		=	new TreeMap();
		String stockName1,priceDate1,isCA1,Series1,MCV1,Price1,stockCurrId,Pnusd,Mnusd;
		String Tis1,tradedVolume1;
		double tradedValue1;
		//dbconnect();
		
		if(fromDate!=null && fromDate.trim().length()!=0 && toDate!=null && toDate.trim().length()!=0){
			try {
				//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
	 			if(connection==null)
	 			{
	 				connection=con.getdbConnection();
	 			}
					
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stockWisePe_Pb_get_stocks"));
				stmt.setString(1,selectStock);
				stmt.setString(2,fromDate);
				stmt.setString(3,toDate);
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						String name=rst.getString(1);
						if(name!=null){
							stockName1=name;
						}else{
							stockName1="--";
						}
						String date=rst.getString(2);
						if(date!=null){
							priceDate1=date;
						}
						else{
							priceDate1="--";
						}
				//		AdjustDecimal ad= new AdjustDecimal();
						AdjustDecimal ad = ConnectInit.getAdjustDecimal();
						float Price11=rst.getFloat(3);
						Pnusd=ad.shareholdingpatt(Price11);
						Pnusd=ad.indexcompose(Pnusd);
						float MCV11=rst.getFloat(4);
						Mnusd=ad.shareholdingpatt(MCV11);
						Mnusd=ad.indexcompose(Mnusd);
						float pe=rst.getFloat(5);
						tvol=ad.shareholdingpatt(pe);
						tvol=ad.indexcompose(tvol);
						float pb=rst.getFloat(6);
						tval=ad.shareholdingpatt(pb);
						tval=ad.indexcompose(tval);
						float div=rst.getFloat(7);
						ic=ad.shareholdingpatt(div);
						ic=ad.indexcompose(ic);
						
						
						sdf=new stockDetailFromDate(stockName1,priceDate1,Pnusd,Mnusd,tvol,tval,ic,null,null,null,null,null);
						Pp.add(sdf);
						/*sdf=(stockDetailFromDate)Pp.get(1);
						  sdf.getIc()*/
						 
				}
					stmt.close();
					rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
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
		}
		
		tableDate=Pp;
		return tableDate;
	}
	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableDateStock(ArrayList tableDate) {
		this.tableDate = tableDate;
	}
	
	/**
	 * @return Returns the toDate.
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate The toDate to set.
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	/**
	 * @return Returns the defaultVal.
	 */
	public String getDefaultVal() {
		return defaultVal;
	}
	/**
	 * @param defaultVal The defaultVal to set.
	 */
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}
	
	/**
	 * @return Returns the b1.
	 */
	public String getB1() {
		return b1;
	}
	/**
	 * @param b1 The b1 to set.
	 */
	public void setB1(String b1) {
		this.b1 = b1;
	}



}
