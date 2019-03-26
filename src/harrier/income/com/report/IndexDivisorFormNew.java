package harrier.income.com.report;

/**
 * 
 * @author Manoj Adekar
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class IndexDivisorFormNew extends ActionForm{
	Logger Logging = Logger.getLogger(IndexDivisorFormNew.class);
	IndexDivisorForm indform =null;
	private String check;
	private String selchart;
	
	private String avgSpan=null;
	private String tradingDate;
	private String close;
	private String mCap;
	private String divisor;
	
	private String query=null;
	private String indexName=null;
	private String chart=null;
	private String graph=null;
	private String filename=null;
	private String graphURL;
	private String from=null;
	private String clear=null;
	private String to=null;
	private String check_mavg=null;
	private String selectIndex=null;
	private String compute=null;
	String userid1;
	public indexMove im1;
	private ArrayList tableData=null;
	private ArrayList tableDataNew=null;
	private Collection selectIndexCollection=null;
	private Collection selectAvgSpan=null;
	private Collection selectChart=null;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	private Vector var_Table_data_vector = null;
	AdjustDecimal ad = new AdjustDecimal();
	Hashtable IndexNameHash=new Hashtable();
	Vector vExcel=new Vector();
	PreparedStatement pst;
	ResultSet rs1;
	Connect  con = ConnectInit.getConnect();
    Connection connection=null;    	
    ResultSet rs = null;
	//app.Connect con=new app.Connect();
	
	public ActionErrors validate(ActionMapping mapping,
		    HttpServletRequest request){
		  	ActionErrors errors = new ActionErrors();
		  	return errors;
		  }
	
	/*
	 * 
	 */
	public Collection getSelectIndexCollection() {
		try{
			if(connection==null)
				connection=con.getdbConnection();
			//Logging.debug(connection);
				//String chk =getSectorIndexCheck();
			if (check != null && check.equals("on"))
			{
				query = ConnectInit.queries.getProperty("index_list");
			}
			else
			{
				query = ConnectInit.queries.getProperty("index_list_without_child");
			}
			Vector vec = new Vector();
		 
			try {
					
				pst = connection.prepareStatement(query);
				pst.setString(1,userid1);
				rs = pst.executeQuery();
		//		AcessControl asc=new AcessControl();
				AcessControl asc = ConnectInit.getAcessControl();
				String NotSelected=asc.getLangValues("Masters.NotSelected");
				Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
				
				vec.add(new LabelValueBean("Not Selected","0"));
				while (rs.next()) {
					
					vec.add(new LabelValueBean(rs.getString(2), rs.getString(1)));
					
				}
				ResultSet rbs = Icr.benchindecolection(connection,"index_list_without_child_bench");
				while (rbs.next()) {
					
					vec.add(new LabelValueBean(rbs.getString(2), rbs.getString(1)));
					
				}
				selectIndexCollection = vec;
				if(rs!=null)
				rs.close();
				
				} 
			
			catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
				}
							
							
		    }
		catch(Exception e){
		    	Logging.error(" Error : "+e.getMessage());
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
		    
		
		return selectIndexCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	public void setSelectIndexCollection(Collection selectIndexCollection) {
		this.selectIndexCollection = selectIndexCollection;
	}
	  
	  /*
	   * 
	   */
	
	    
	/**
	 * @return Returns the selectAvgSpan.
	 */
	public Collection getSelectAvgSpan() {
		
		Vector avgvec = new Vector();
		
		for(int count=1;count<=30;count++){
			avgvec.add(new LabelValueBean(""+count,""+count));
		}
		selectAvgSpan=avgvec;
		return selectAvgSpan;
	}
	
	public void setSelectAvgSpan(Collection selectAvgSpan) {
		
		this.selectAvgSpan = selectAvgSpan;
	}
	
	
	
	public String getAvgSpan () {
	    return avgSpan;
	  }
	
	public void setAvgSpan(String avgSpan){
		this.avgSpan =avgSpan ;
	}
	
	
	
	  public String getCheck() {
	    return check;
		} 
	  
	  public void setCheck(String check) {
	    this.check = check;
	  }

	  /**
	   * 
	   * @return Returns the collection to display the collection of charts
	   */
	  
	  public Collection getSelectChart(){
	  	
	  	Vector vec1 = new Vector();
	 	Logging.debug(" Vec1 value "+vec1);
		vec1.add(new LabelValueBean("Not Selected","0"));
		vec1.add(new LabelValueBean("Moving Average Chart","1"));		
		vec1.add(new LabelValueBean("Bar Chart","2"));
		vec1.add(new LabelValueBean("Area Chart","3"));
		//vec1.add(new LabelValueBean("Moving Average Chart","4"));
		selectChart=vec1;
	  	return selectChart;
	  }
	  
	  public void setSelectChart(Collection selectChart){
	  	this.selectChart=selectChart;
	  }
	

	  /**
		 * @return Returns the selchart.
		 */
		public String getSelchart() {
			return selchart;
		}

		/**
		 * @param selchart The selchart to set.
		 */
		public void setSelchart(String selchart) {
			this.selchart = selchart;
		}
	  
	
	/**
	 * @return Returns the selectIndex.
	 */
	public String getSelectIndex() {
		return selectIndex;
	}
	/**
	 * @param selectIndex The selectIndex to set.
	 */
	public void setSelectIndex(String selectIndex) {
		this.selectIndex = selectIndex;
	}
	
	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from The fromDate to set.
	 */
	public void setFrom(String from) {
		this.from = from;
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
	 * @return Returns the toDate.
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to The toDate to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getCheck_mavg() {
		return check_mavg;
	}
	/**
	 * @param to The toDate to set.
	 */
	public void setCheck_mavg(String check_mavg) {
		this.check_mavg = check_mavg;
	}
	
	
	
	public StringBuffer display_Divisor(String indIdS,String FrDt,String toDt)
	{
		String str1=null;
		StringBuffer buffer = new StringBuffer();
		ArrayList tableDataNewS=new ArrayList();
		try
				{	
					tableDataNew=loadTableDataNew(indIdS,FrDt,toDt);
					
					int count = -1;
					String style=null;
					
					String bcolor="";
					for(int i=0;i<tableDataNew.size();i++)
					{
						count++;
						if((count==0))
						{
								buffer.append("<tr width = '100%'>");
					
								buffer.append("</td>");
								buffer.append("<td width='15%' nowrap='nowrap' align='center' id='trddate' class='gridStyle-header'>");
								buffer.append("<span><b><bean:message key='IndexDivisor.trddate'/> </b></span>");//symbol.
								buffer.append("</td>");
								
								buffer.append("</td>");
								buffer.append("<td width='15%' nowrap='nowrap' align='center' id='trddate' class='gridStyle-header'>");
								buffer.append("<span><b><bean:message key='IndexCompareOHCL.close'/> </b></span>");//symbol.
								buffer.append("</td>");
								
								buffer.append("</td>");
								buffer.append("<td width='15%' nowrap='nowrap' align='center' id='trddate' class='gridStyle-header'>");
								buffer.append("<span><b><bean:message key='IndexDivisor.mcp'/> </b></span>");//symbol.
								buffer.append("</td>");
								
								buffer.append("</td>");
								buffer.append("<td width='15%' nowrap='nowrap' align='center' id='trddate' class='gridStyle-header'>");
								buffer.append("<span><b><bean:message key='indcurrwise.divisor'/> </b></span>");//symbol.
								buffer.append("</td>");
								
								buffer.append("</tr>");
						}
						else
						{			
							
								if(i%2==0)
								{
									bcolor="#84AADE";
								}
								else
								{
									bcolor="#DEE3EF";
								}
									indform = (IndexDivisorForm)tableDataNew.get(i);
									
									buffer.append("</tr 'bgcolor='"+bcolor+"'>");	
									
									buffer.append("<td height='12' nowrap='nowrap' style='padding-left:5px;'>");
									str1=indform.getTradingDate();
									buffer.append(str1);				
									buffer.append("</td>");	
								
									buffer.append("<td height='12' nowrap='nowrap' style='padding-left:5px;'>");
									str1=indform.getClose();
									buffer.append(str1);				
									buffer.append("</td>");	
									
														
									buffer.append("<td height='12' nowrap='nowrap' style='padding-left:5px;'>");
									str1=indform.getMCap();
									buffer.append(str1);			
									buffer.append("</td>");	
									
									buffer.append("<td height='12' align='center' nowrap='nowrap'>");
									str1=indform.getDivisor();
									buffer.append(str1);				
									buffer.append("</td>");	
											
									buffer.append("</tr>");
								}
						}
				}
				catch (Exception e) {
					Logging.debug("Error in Divisor Report");
				}
				
	return buffer;
}

	
	public ArrayList loadTableDataNew(String indIdS,String frDt,String toDt) {
		ArrayList Pp 	=	new ArrayList();
		harrier.income.com.report.AdjustDecimal ad=new harrier.income.com.report.AdjustDecimal();
		Connection con=null;
		Connect c = ConnectInit.getConnect();
		indform = new IndexDivisorForm();
		//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con==null)
			{
				con = c.getdbConnection();
			}

		PreparedStatement pst=null;
		if(from!=null && to!=null)
		{
				try {
					
					pst =con.prepareStatement(ConnectInit.queries.getProperty("index_divisor_date_wise1"));
					pst.setString(1,indIdS);
					pst.setString(2,frDt);
					pst.setString(3,toDt);
					
					ResultSet rst = pst.executeQuery(); 
		
					while(rst.next()){
						if (rst.getString(1) == null) {
							tradingDate="--";
						} else {
							tradingDate=rst.getString(1);
							indform.setTradingDate(tradingDate);
						}
						
						if (rst.getString(2) == null) {
							close="0";
						} else {
							String strclose=(String)rst.getString(2);
							close=ad.indexcompose(strclose);
							indform.setClose(close);
						}
						
						if (rst.getString(3) == null) {
							mCap="0";
						} else {
							double mcv=(double)rst.getDouble(3);
							mcv=mcv/1000000.0;
							String strmcv=new Double(mcv).toString();
							Logging.debug("Strmcv Value is "+strmcv);
							strmcv=ad.shareholdingpatt(strmcv);
							mCap=ad.indexcompose(strmcv);
							indform.setMCap(mCap);
						}
							
						if (rst.getString(4) == null) {
							divisor="0";
						} else {
							double mcv=(double)rst.getDouble(4);
							String strmcv=new Double(mcv).toString();
							Logging.debug("Strmcv1 Value is  "+strmcv);
							strmcv=ad.shareholdingpatt(strmcv);
							divisor=ad.indexcompose(strmcv);
							indform.setDivisor(divisor);
						}
						//im1=new indexMove(tradingDate,close,mCap,divisor, pe, pb,divYield);
						//Pp.add(im1);
						Pp.add(indform);
					}
						rst.close();
						pst.close();
						
				} catch (SQLException e) {
					Logging.debug(e);
				//	e.printStackTrace();
				}
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		}
		finally{
			try{
				if(con!=null)
					con.close();
					con=null;
			}catch(Exception ee){
				try{
					if(con!=null)
						con.close();
						con=null;
				}catch(Exception ex){
					Logging.error(" Error : Unable to close connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "+ee.getMessage());
			}
		}
		//setVExcel(vExcel);
		tableDataNew=Pp;
		return tableDataNew;
	}
	

	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}
	
	
	/**
	 * @return Returns the close.
	 */
	public String getClose() {
		return close;
	}
	/**
	 * @param close The close to set.
	 */
	public void setClose(String close) {
		this.close = close;
	}
	/**
	 * @return Returns the divisor.
	 */
	public String getDivisor() {
		return divisor;
	}
	/**
	 * @param divisor The divisor to set.
	 */
	public void setDivisor(String divisor) {
		this.divisor = divisor;
	}
	/**
	 * @return Returns the mCap.
	 */
	public String getMCap() {
		return mCap;
	}
	/**
	 * @param cap The mCap to set.
	 */
	public void setMCap(String mCap) {
		this.mCap = mCap;
	}
	/**
	 * @return Returns the tradingDate.
	 */
	public String getTradingDate() {
		return tradingDate;
	}
	/**
	 * @param tradingDate The tradingDate to set.
	 */
	public void setTradingDate(String tradingDate) {
		this.tradingDate = tradingDate;
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
	 * @return Returns the indexName.
	 */
	public String getIndexName() {
		try{
			String local_d1=getSelectIndex();
			Logging.debug("INDEX id="+local_d1);
			Enumeration e;
			String str;
			String iname="",ival="";
			e=IndexNameHash.keys();
			while(e.hasMoreElements()){
				str=(String)e.nextElement();
				iname=(String)IndexNameHash.get(str);
				if(str.equals(local_d1)){
					indexName=iname;
					break;
				}
			}
		}catch(Exception e){
			
		}
		return indexName ;
	}
	/**
	 * @param indexName The indexName to set.
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	
	

	/**
	 * @return Returns the chart.
	 */
	public String getChart() {
		return chart;
	}

	/**
	 * @param chart The chart to set.
	 */
	public void setChart(String chart) {
		this.chart = chart;
	}

	/**
	 * @return Returns the filename.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename The filename to set.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return Returns the graph.
	 */
	public String getGraph() {
		return graph;
	}

	/**
	 * @param graph The graph to set.
	 */
	public void setGraph(String graph) {
		this.graph = graph;
	}

	/**
	 * @return Returns the graphURL.
	 */
	public String getGraphURL() {
		return graphURL;
	}

	/**
	 * @param graphURL The graphURL to set.
	 */
	public void setGraphURL(String graphURL) {
		this.graphURL = graphURL;
	}

	
	public Vector getVar_Table_data_vector() {
		return var_Table_data_vector;
	}

	public void setVar_Table_data_vector(Vector var_Table_data_vector) {
		this.var_Table_data_vector = var_Table_data_vector;
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
	
	/**
	 *  reset method to reset various values
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		
		super.reset(arg0, arg1);
		try{
			
			this.connection=null;
		}catch(Exception e){}
		
		this.check=null;
		this.from=null;
		this.to=null;
		this.selectChart=null;
	}

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}

	
	
}
