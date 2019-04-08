
/*
 * Created on Sep 7, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
 * @author ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatForm extends ActionForm {
	Logger Logging = Logger.getLogger(PatForm.class);
	private String d1 = null;
	private ResultSet rst=null;
	private ResultSet rst1=null;
	private Vector exchangeCollection=null;
	private ArrayList tableData;
	private String indName = null;

	private String b1 = null;

	private String fileType = null;

	private Vector finance = null;

	private String to = null;
	
	private String selectfinance = null;

	private String from = null;
	
	private String shwTo = null;
	 
	 private String shwFrom = null;
	 
	private String selectStock = null;
	
	private String selectExchange = null;
	
	private String resetval = null;
	
	private String resetvalue = null;
	private String hiddenVar1 = null;

	private String b11 = null;
	private String mvcdate = null;
	
	///////////////
	private String compute;
	private String show1;
	private String numOfMonths;
	private String xDate;
	private String xDate1;
	private String pat;
	private String dividend="--";
	private String tax="--";
	private String totalDebt="--";
	private String netWorth="--";
	private String interest="--";
	
	private String paidUpCap="--";
	private String sales="--";
	boolean report1 = false;
	boolean report2 = false;
	boolean report3 = false;
	boolean report4 = false;
	boolean	report5 = false;
	boolean report6 = false;
	boolean report7 = false;
	boolean	report8 = false;
	private String submitButton=null;
	private String resetButton=null;

	private String totNum;
	private String mcv;
	private String totPat1;
	private String pe;
	
	private String date1;
	private String pb;
	private String check=null;
	private String vanish=null;
	int [] counter  =new int[8];
	int []checkarr=new int[8];
	int []checkmonth=new int[8];
	 boolean flag=false;
	 int count=0;
	 int months=0;
	/////////////////////////////
	private Collection stockCollection=null;

	private static StringBuffer disp_buffer=new StringBuffer();
	
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	
	public void reset() {
		
		this.setSelectExchange("0");
		this.setSelectfinance("0");
		this.setTo("");
		
		
	}
	
	public void reset1() {
		
		this.setSelectExchange("0");
		this.setSelectfinance("0");
		this.setMvcdate("");
		this.setSelectStock("0");
		this.setCheck("no");
		this.set1(false);
			this.set2(false);
			this.set3(false);
			this.set4(false);
			this.set5(false);
			this.set6(false);
			this.set7(false);
			this.set8(false);
	
	}
	
	
	
	
	/**
	 * @return Returns the indName.
	 */
	public String getIndName() {
		return indName;
	}

	/**
	 * @param indName The indName to set.
	 */
	public void setIndName(String indName) {
		this.indName = indName;
	}
	/**
	 * @return Returns the executeButton.
	 */
	public String getSubmitButton() {
		return submitButton;
	}

	/**
	 * @param executeButton The executeButton to set.
	 */
	public void setSubmitButton(String rButton) {
		this.submitButton = rButton;

	}
	public Vector getExchangeCollection() {
		Vector temp2=new Vector();
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		ResultSet rs=null;
		String query;

		//query=stock_exchange_list
		
		try{
			if(connection==null)
				connection=con.getdbConnection();
					query = ConnectInit.queries.getProperty("stock_exchange_online_list");
				 temp2 = new Vector();
				 temp2.add(new LabelValueBean("Not Selected","0"));
				try {
					Statement stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						temp2.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
					}
					exchangeCollection = temp2;
					
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
				    			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    		}
			    }

		return exchangeCollection;
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

	/**
	 * @return Returns the d1.
	 */
	public String getD1() {
		return d1;
	}

	/**
	 * @param d1 The d1 to set.
	 */
	public void setD1(String d1) {
		this.d1 = d1;
	}

	/**
	 * @return Returns the fileType.
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType The fileType to set.
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Vector getFinance() {
		Vector temp2=new Vector();
		temp2 = new Vector();
		temp2.add(new LabelValueBean("Not Selected","0"));
		temp2.add(new LabelValueBean(" P/E ","1"));
		temp2.add(new LabelValueBean(" P/B ","2"));
					
			finance = temp2;
					
			return finance;
	}

	/**
	 * @return Returns the stockCollection.
	 */
	public Collection getStockCollection() {
		
		Vector vec=new Vector();
		Connection connection=null;
			try {
				if(connection == null)
					connection = con.getdbConnection();
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_stock_list_exchange"));
				stmt.setString(1,selectExchange);
				ResultSet rst = stmt.executeQuery();
				vec.add(new LabelValueBean("Not Selected","0"));
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				stockCollection = vec;
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				Logging.debug(e);
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
		stockCollection=vec;
		return stockCollection;
	}
	/**
	 * @param stockCollection The stockCollection to set.
	 */
	public void setStockCollection(Collection stockCollection) {
		//Logging.getDebug(" Inside set Stock Collection...");
		this.stockCollection = stockCollection;
	}
	public String getSelectExchange() {
		return selectExchange;
	}

	public void setSelectExchange(String selectExchange) {
		this.selectExchange = selectExchange;
	}

	public String getSelectfinance() {
		return selectfinance;
	}

	public void setSelectfinance(String selectfinance) {
		this.selectfinance = selectfinance;
	}

	public void setExchangeCollection(Vector exchangeCollection) {
		this.exchangeCollection = exchangeCollection;
	}

	public void setFinance(Vector finance) {
		this.finance = finance;
	}

	public String getSelectStock() {
		return selectStock;
	}

	public void setSelectStock(String selectStock) {
		this.selectStock = selectStock;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getShwFrom() {
		return shwFrom;
	}

	public void setShwFrom(String shwFrom) {
		this.shwFrom = shwFrom;
	}

	public String getShwTo() {
		return shwTo;
	}

	public void setShwTo(String shwTo) {
		this.shwTo = shwTo;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return Returns the compute.
	 */
	public String getShow1() {
		return show1;
	}
	/**
	 * @param compute The compute to set.
	 */
	public void setShow1(String show1) {
		this.show1 = show1;
	}
	/**
	 * @return Returns the compute.
	 */
	public String getCompute() {
		return compute;
	}
	/**
	 * @param compute The compute to set.
	 */
	public void setCompute(String compute) {
		this.compute = compute;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getNumOfMonths() {
		return numOfMonths;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setNumOfMonths(String numOfMonths) {
		this.numOfMonths = numOfMonths;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getXDate() {
		return xDate;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setXDate(String xDate) {
		this.xDate = xDate;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getXDate1() {
		return xDate1;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setXDate1(String xDate1) {
		this.xDate1 = xDate1;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getTotNum() {
		return totNum;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getMcv() {
		return mcv;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setMcv(String mcv) {
		this.mcv = mcv;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getTotPat1() {
		return totPat1;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setTotPat1(String totPat1) {
		this.totPat1 = totPat1;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getPe() {
		return pe;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setPe(String pe) {
		this.pe = pe;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getPat() {
		return pat;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setPat(String pat) {
		this.pat = pat;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getDividend() {
		return dividend;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setDividend(String dividend) {
		this.dividend = dividend;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getTax() {
		return tax;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getTotalDebt() {
		return totalDebt;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setTotalDebt(String totalDebt) {
		this.totalDebt = totalDebt;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getNetWorth() {
		return netWorth;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setNetWorth(String netWorth) {
		this.netWorth = netWorth;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getInterest() {
		return interest;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getPaidUpCap() {
		return paidUpCap;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setPaidUpCap(String paidUpCap) {
		this.paidUpCap = paidUpCap;
	}
	/**
	 * @return Returns the stockId.
	 */
	public String getSales() {
		return sales;
	}
	/**
	 * @param stockId The stockId to set.
	 */
	public void setSales(String sales) {
		this.sales = sales;
	}
	/**
	 * @return Returns the checkdate.
	 */
	public boolean is1() {
		return report1;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set1(boolean report) {
		this.report1 = report;
	}

	/**
	 * @return Returns the checkdate.
	 */
	public boolean is2() {
		return report2;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set2(boolean report) {
		this.report2 = report;
	}

	/**
	 * @return Returns the checkdate.
	 */
	public boolean is3() {
		return report3;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set3(boolean report) {
		this.report3 = report;
	}

	/**
	 * @return Returns the checkdate.
	 */
	public boolean is4() {
		return report4;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set4(boolean report) {
		this.report4 = report;
	}

	/**
	 * @return Returns the checkdate.
	 */
	public boolean is5() {
		return report5;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set5(boolean report) {
		this.report5 = report;
	}
	/**
	 * @return Returns the checkdate.
	 */
	public boolean is6() {
		return report6;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set6(boolean report) {
		this.report6 = report;
	}
	/**
	 * @return Returns the checkdate.
	 */
	public boolean is7() {
		return report7;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set7(boolean report) {
		this.report7 = report;
	}
	/**
	 * @return Returns the checkdate.
	 */
	public boolean is8() {
		return report8;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set8(boolean report) {
		this.report8 = report;
	}
	/**
	 * @return Returns the tableData.
	 */
	
	
	public Vector getTableData() {
		connection=null;
		PreparedStatement stmt =null;
		PreparedStatement stmt1 =null;
		ArrayList tempdata = new ArrayList();
		tableData=new ArrayList();
		ResultSet rst12=null;
		Vector vec= new Vector();
		
		try{
		if(connection==null){
			connection=con.getdbConnection();
		}

		int i =0;
		int d=0;
		try {
			String query=ConnectInit.queries.getProperty("select_financial_information");
			Logging.debug(" query = " + query);
			stmt = connection.prepareStatement(query);
			stmt.setString(1,selectStock);
			rst = stmt.executeQuery();
			
			while (rst.next()) {
				
				if (rst.getString(1) == null) {						// xDate
					xDate = "--";
				} else {
					xDate= rst.getString(1).toString();
				}
				vec.add(i, xDate);
				i++;
				
				if (rst.getString(2) == null) {						// open val
					numOfMonths ="0";
				} else {
					numOfMonths = rst.getString(2).toString();
					// changes done for getting no of months
					int mth=Integer.parseInt(numOfMonths);
					checkmonth[d]=mth;
					d++;
				}
				vec.add(i,numOfMonths);
				i++;
				
				if (rst.getString(3) == null) {						// close val
					pat = "0";
				} else {
					pat=rst.getString(3).toString();
				}
				vec.add(i,pat);
				i++;
				if (rst.getString(4) == null) {						// close val
					netWorth = "0";
				} else {
					netWorth=rst.getString(4).toString();
				}
				vec.add(i,netWorth);
				i++;
				if (rst.getString(5) == null) {						// close val
					dividend = "0";
				} else {
					dividend=rst.getString(5).toString();
				}
				vec.add(i,dividend);
				i++;
				if (rst.getString(6) == null) {						// close val
					interest = "0";
				} else {
					interest=rst.getString(6).toString();
				}
				vec.add(i,interest);
				i++;
				if (rst.getString(7) == null) {						// close val
					tax = "0";
				} else {
					tax=rst.getString(7).toString();
				}
				vec.add(i,tax);
				i++;
				if (rst.getString(8) == null) {						// close val
					paidUpCap = "0";
				} else {
					paidUpCap=rst.getString(8).toString();
				}
				vec.add(i,paidUpCap);
				i++;
				if (rst.getString(9) == null) {						// close val
					totalDebt = "0";
				} else {
					totalDebt=rst.getString(9).toString();
				}
				vec.add(i,totalDebt);
				i++;
				if (rst.getString(10) == null) {						// close val
					sales = "0";
				} else {
					sales=rst.getString(10).toString();
				}
				vec.add(i,sales);
				i++;
				tempdata.add(vec);
			}
			
		}catch (SQLException sqlexp) {
			Logging.error("SQL Error : "+sqlexp.getMessage());
		} 
		}catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}

		tableData =tempdata;
		return vec;
	}	
	
	
	
	
	
	
	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}
	/**
	 * validate stock master form values.
	 */
	
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();
		
	/*		try{
				if(hiddenVar!=null && hiddenVar.equals("yes")){
			if(selectExchange==null || selectExchange.trim().equals("0") || selectExchange.trim().equals("Not Selected")){
				errors.add("s_stockExchange",new ActionError("Error.message.stk_stockExchange"));				
			}
			
			if(selectfinance==null || selectfinance.trim().equals("0") || selectfinance.trim().equals("Not Selected")){
				errors.add("s_companyName",new ActionError("Error.message.s_Finacialresult"));				
			}
			
		}
			}catch(Exception e){
			errors.add(null,new ActionError("Error.message.msg"));
			Logging.getDebug("Error in Validation ");			
		}*/	
		try{
			int totalmonths=0;
			//if(getSubmitButton() !=null && getSubmitButton().equals("Submit"))
			if(getHiddenVar1() !=null && getHiddenVar1().equals("yes") && getSelectfinance().equals("1")){
				
			     if(is1()== true){
			     	int c1=1;
					counter[0]=c1;
					count++;
					months=checkmonth[0];
					
					totalmonths=totalmonths+months;
					
			     }
			     if(is2()==true){
			     	int c2=2;
				    counter[1]=c2;
				    count++;
				    months=checkmonth[1];
					
					totalmonths=totalmonths+months;
				  
			     }
			     if(is3()== true){
			     	int c3=3;
					counter[2]=c3;
					count++;
					months=checkmonth[2];
					
					totalmonths=totalmonths+months;
					
			     }
			     if(is4()== true){
			     	int c4=4;
					counter[3]=c4;
					count++;
					months=checkmonth[3];
					
					totalmonths=totalmonths+months;
					
			     }
			     if(is5() == true){
			     	int c5=5;
					counter[4]=c5;
					count++;
					months=checkmonth[4];
					
					totalmonths=totalmonths+months;
					
			     }
			     if(is6() == true){
			     	int c6=6;
					counter[5]=c6;
					count++;
					months=checkmonth[5];
					
					totalmonths=totalmonths+months;
					
			     }
			     if(is7() == true){
			     	int c7=7;
					counter[6]=c7;
					count++;
					months=checkmonth[6];
					
					totalmonths=totalmonths+months;
					
			     }
			     if(is8() == true){
			     	int c8=8;
					counter[7]=c8;
					count++;
					months=checkmonth[7];
					
					totalmonths=totalmonths+months;
					
					
			     }
		
			    
			    
			  if(count==0){
				  errors.add("s_companyName",new ActionError("Error.message.Check"));
				  return errors;
			  }
			     for(int i=0,j=0;i<8;i++)
	   			{
	   				
	   				if(counter[i]!=0)
	   				{
	   					checkarr[j]=counter[i];
	   				
	   					j++;
	   					
	   				}
	   				
	   			}
			 
	   			for(int j=0,k=0;j<count;j++)
	   			{
	   				
	   				int t1=checkarr[k]+1;
	   				int t2=checkarr[++k];
	   				if(t2!=0){
	   				  if( t1!= t2)
	   				   {
	   					flag=true;
	   					
	   				   }
	   				}
	   				
	   			}
	   			
			Logging.debug("count"+count);
			
	   				if(flag ==true)
	   				{   
	   					
	   					errors.add("s_companyName",new ActionError("Error.message.s_test"));
	   					
	   					setCheck("no");
	   					setCompute("yes");
	   					this.set1(false);
	   					this.set2(false);
	   					this.set3(false);
	   					this.set4(false);
	   					this.set5(false);
	   					this.set6(false);
	   					this.set7(false);
	   					this.set8(false);
	   					for(int j=0;j<8;j++)
	   					{
	   						counter[j]=0;
	   					}
	   					flag=false;
	   					count=0;	
	   					
	   					return errors;
	   				}
	   				
	   				else
	   				{   
	   					
				       if(totalmonths >24){
				     	
				       	errors.add("s_companyName",new ActionError("Error.message.s_test1"));
				     	this.set1(false);
	   					this.set2(false);
	   					this.set3(false);
	   					this.set4(false);
	   					this.set5(false);
	   					this.set6(false);
	   					this.set7(false);
	   					this.set8(false);
	   					
	   					//int [] counter  =new int[0];
	   					int []checkmonth=new int[0];
	   					for(int j=0;j<8;j++)
	   					{
	   						counter[j]=0;
	   					}
	   					count =0;
				     	return errors;
				        }
				 	  else{
	   					setCheck("yes");
	   					count =0;
	   					return null;
				 	  }
				       
	   				}
	   				
			}	
		}
			catch(Exception e){
				Logging.error(" Error : Unable to close Connection "+e.getMessage());
				Logging.debug("Error in Validation ");			
			}	
		return null;
	}
	
public static StringBuffer getDisp_buffer() {
	return disp_buffer;
}
/**
 * @param disp_buffer The disp_buffer to set.
 */
public static void setDisp_buffer(StringBuffer disp_buffer) {
	PatForm.disp_buffer = disp_buffer;
}

public String getB11() {
	return b11;
}

public void setB11(String b11) {
	this.b11 = b11;
}

public String getResetvalue() {
	return resetvalue;
}

public void setResetvalue(String resetvalue) {
	this.resetvalue = resetvalue;
}
public String getMvcdate() {
	return mvcdate;
}
public void setMvcdate(String mvcdate) {
	this.mvcdate = mvcdate;
}




	/**
	 * @return Returns the check.
	 */
	public String getCheck() {
		return check;
	}
	/**
	 * @param check The check to set.
	 */
	public void setCheck(String check) {
		this.check = check;
	}
	/**
	 * @return Returns the vanish.
	 */
	public String getVanish() {
		return vanish;
	}
	/**
	 * @param vanish The vanish to set.
	 */
	public void setVanish(String vanish) {
		this.vanish = vanish;
	}
	/**
	 * @return Returns the resetButton.
	 */
	public String getResetButton() {
		return resetButton;
	}
	/**
	 * @param resetButton The resetButton to set.
	 */
	public void setResetButton(String resetButton) {
		this.resetButton = resetButton;
	}

	public String getHiddenVar1() {
		return hiddenVar1;
	}

	public void setHiddenVar1(String hiddenVar1) {
		this.hiddenVar1 = hiddenVar1;
	}

	public String getResetval() {
		return resetval;
	}

	public void setResetval(String resetval) {
		this.resetval = resetval;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getPb() {
		return pb;
	}

	public void setPb(String pb) {
		this.pb = pb;
	}
}