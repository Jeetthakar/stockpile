 /*
 * Created on Feb 16, 2006
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



/**
 * @author pankaj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MovingIndexValueForm extends ActionForm{
	Logger Logging = Logger.getLogger(MovingIndexValueForm.class);
	private Collection indexCollection = null; 
	private Collection spanCollection = null;
	private Collection chartCollection =null;
	private ArrayList indexMovingTable =null;
	private Vector var_Table_data_vector = null;
	private String  selectIndex =null ;
	//private String varExcelflag=null;
	private String  selectChart=null ,selectSpan=null; 
	private  String sectorIndexCheck ,check_moving_avg,move_from=null,move_to=null,from_button,to_button;
	private String varTableData=null; 
	private String chart=null,roleId_move;
	Vector vExcel=new Vector();
	//data base variables
	String query,userid1 ;
    Connect  con = ConnectInit.getConnect();
    Connection connection=null;
    PreparedStatement pst;
	ResultSet rs1;	
    ResultSet rs = null;
    IndexComposeReportMethod Icr = new IndexComposeReportMethod();
    
    
	
	 public String getSelectIndex() {
		return selectIndex;
	}
	 
	 public ActionErrors validate(ActionMapping mapping,
			    HttpServletRequest request)
			  {
			  	ActionErrors errors = new ActionErrors();
			  	return errors;
			  }  
	 
	 

/**
getIndexCollection method for collecting data from data base into Vector vector_indexlist

*/
	public Collection getIndexCollection() {
		try{
			if(connection==null)
				connection=con.getdbConnection();
				String chk =getSectorIndexCheck();
						if(chk!=null ){
										query = ConnectInit.queries.getProperty("index_list");
										}
										else
										{
										query = ConnectInit.queries.getProperty("index_list_without_child");
										}
										Vector vector_indexlist = new Vector();
										try { 
										pst = connection.prepareStatement(query);
										pst.setString(1,userid1);
										rs = pst.executeQuery();
									//	AcessControl asc=new AcessControl();
										AcessControl asc = ConnectInit.getAcessControl();
										String NotSelected=asc.getLangValues("Masters.NotSelected");
										Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
										
										vector_indexlist.add(new LabelValueBean("Not Selected","0"));
										while (rs.next()) {
											vector_indexlist.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
										}
										
//										Change by Manoj adekar
										int role_id2=Integer.parseInt(roleId_move);
										if(role_id2!=1)
										{
											ResultSet rbs = Icr.benchindecolection(connection,"index_list_without_child_bench");
											while (rbs.next()) {
												vector_indexlist.add(new LabelValueBean(rbs.getString(2),rbs.getString(1)));
											}
										}
										indexCollection = vector_indexlist;
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
		
		return indexCollection;
	}

	public void setIndexCollection(Collection indexCollection) {
		this.indexCollection = indexCollection;
	}


	public void setSelectIndex(String selectIndex) {
		this.selectIndex = selectIndex;
	}

	public String getSelectSpan() {
		return selectSpan;
	}

	public void setSelectSpan(String selectSpan) {
		this.selectSpan = selectSpan;
	}
/**
*  getChartCollection method is used to Fill Vector chartc which show various chart options 
*
*/

	public Collection getChartCollection() {
			Vector chartc = new Vector();
			
			Logging.debug(" Vec1 value "+chartc);
			chartc.add(new LabelValueBean("Not Selected","0"));
			chartc.add(new LabelValueBean("Moving Average Chart","1"));		
			chartc.add(new LabelValueBean("Bar Chart","2"));
			chartc.add(new LabelValueBean("Area Chart","3"));
			//chartc.add(new LabelValueBean("Moving Average Chart","4"));
			chartCollection = chartc;
		return chartCollection;
	}

	public void setChartCollection(Collection chartCollection) {
		this.chartCollection = chartCollection;
	}
	
	public String getSelectChart() {
		return selectChart;
	}

	public void setSelectChart(String selectChart) {
		this.selectChart = selectChart;
	}
	

	public String getSectorIndexCheck() {
		return sectorIndexCheck;
	}

	public void setSectorIndexCheck(String sectorIndexCheck) {
		this.sectorIndexCheck = (String)sectorIndexCheck;
	}
	/**
	getSpanCollection method used to fill Vector spanc which show span number
	*/
	public Collection getSpanCollection() {
			Vector spanc = new Vector();
			for(int span=1;span<=30;span++) 
			{
				Integer v1 = new Integer(span);
				String sapn_s= v1.toString();
				spanc.add(new LabelValueBean(sapn_s,sapn_s));
			}
			spanCollection = spanc;
	
		return spanCollection;
	}

	public void setSpanCollection(Collection spanCollection) {
		this.spanCollection = spanCollection;
	}

	

	public String getCheck_moving_avg() {
		return check_moving_avg;
	}

	public void setCheck_moving_avg(String check_moving_avg) {
		this.check_moving_avg = check_moving_avg;
	}

	public String getFrom_button() {
		return from_button;
	}

	public void setFrom_button(String from_button) {
		this.from_button = (String)from_button;
	}

	public String getMove_from() {
		return move_from;
	}

	public void setMove_from(String move_from) {
		this.move_from = move_from;
		Logging.debug(this.move_from);
	}

	public String getMove_to() {
		return move_to;
	}

	public void setMove_to(String move_to) {
		this.move_to = move_to;
	}

	public String getTo_button() {
		return to_button;
	}

	public void setTo_button(String to_button) {
		this.to_button = to_button;
	}
	 public void reset(ActionMapping mapping,HttpServletRequest request){

		// TODO Auto-generated method stub
			//super.reset(arg0, arg1);
			this.sectorIndexCheck=null;
			this.indexCollection= null;
			 this.connection=null;
			 this.to_button=null;
			// this.varExcelflag=null;
			 move_to=null;
			 move_from=null;
		
	}

	public ArrayList getIndexMovingTable() {
		ArrayList Table_data =new ArrayList();
		Vector Table_data_vector = new Vector();
		String tradingDate=null;
		String close=null;
		String mCap=null;
		String divisor=null;
		String fodate =getMove_from();
		String todate= getMove_to();
		String index= getSelectIndex();
		
		AdjustDecimal ad=new AdjustDecimal();
	
		try{
			if(connection==null)
				connection=null;
				connection=con.getdbConnection();
					if(move_from!=null && move_to!=null){
							try {
					
								PreparedStatement pst;
								
								  pst = connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value1"));
					    		  pst.setString(1,index);
					              pst.setString(2,fodate);
					              pst.setString(3,todate);
					              ResultSet rst = pst.executeQuery(); 
					              vExcel.clear();
					              int i=0;
					              int ii=0;
					              while(rst.next()){
													if (rst.getString(1) == null) {
														tradingDate="--";
													} else {
														tradingDate=rst.getString(1);
														Table_data_vector.add(i,tradingDate);
														i++;
													}
													vExcel.add(ii,tradingDate);
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
													vExcel.add(ii,close);
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
													vExcel.add(ii,mCap);
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
													vExcel.add(ii,divisor);
													ii++;
													
							            		// Table_data.add(new IndexMovingDetail(tradingDate,close,mCap,divisor, pe, pb,divYield));
							            		Table_data.add(new IndexMovingDetail(tradingDate,close,mCap,divisor));
							            		
					              		}
					              	setVar_Table_data_vector(Table_data_vector);
									} catch (SQLException e)
										{
										Logging.debug(e);
													// TODO Auto-generated catch block
												//	e.printStackTrace();
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
		
	
		indexMovingTable = Table_data;
	
		return indexMovingTable;
	}

	public void setIndexMovingTable(ArrayList indexMovingTable) {
		this.indexMovingTable = indexMovingTable;
	}

	public String getVarTableData() {
		return varTableData;
	}

	public void setVarTableData(String varTableData) {
		this.varTableData = varTableData;
	}

	public Vector getVar_Table_data_vector() {
		return var_Table_data_vector;
	}

	public void setVar_Table_data_vector(Vector var_Table_data_vector) {
		this.var_Table_data_vector = var_Table_data_vector;
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

	public String getRoleId_move() {
		return roleId_move;
	}

	public void setRoleId_move(String roleId_move) {
		this.roleId_move = roleId_move;
	}

}
