package harrier.income.com.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh.
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BatchReportFormN extends ActionForm {
	Logger Logging = Logger.getLogger(BatchReportFormN.class);
	java.util.Date dt = new java.util.Date();

	private ArrayList companyWeightage = new ArrayList();

	private Vector CompanyWeightageVector = new Vector();
	
	private String selectUser = null;
	private String filePath = null;
	private String fileVariable = null;
	private String resetButton=null;
	private String executeButton = null;
	private String editButton = null;
	private String index_name = null;
	private String backButton=null;
	private String exchange_name = null;
	private String stock_name1 = null;
	private String id = null;
	private String defaultVal = null;
	private String compute = null;
	private String radioButton = null;
	private ArrayList details=new ArrayList();
	private String vanish = null;
	private String fin = null;
	private String view = null;
	private String selectStock = null;
	private String selectStock1[] = null;
	private String selectExchange = null;
	private String selectExchange6 = null;
	private String selectExchange14 = null;
	private String selectExchange15 = null;
	
	private String checkradio = null;
	private String val = null;

	
	private String tod2 = null;
	
	private String fromd2 = null;
	
	private String tod4 = null;
	private String fromd4 = null;
	
	private String tod5 = null;
	private String fromd5 = null;
	private String tod6 = null;
	private String fromd6 = null;
	
	private String tod7 = null;
	private String fromd7 = null;
	
	private String tod8 = null;
	private String fromd8 = null;
	
	private String tod9 = null;
	private String fromd9 = null;
	
	private String tod110 = null;
	private String fromd110 = null;
	private String tod111 = null;
	private String fromd111 = null;
	
	private String tod112 = null;
	private String fromd112 = null;
	
	private String tod113 = null;
	private String fromd113 = null;
	
	private String tod114 = null;
	private String fromd114 = null;
	
	private String tod115 = null;
	private String fromd115 = null;
	
	private String tod116 = null;// adde on 6/6/07 by lokesh for index movement
	private String fromd116 = null;
	

	private Collection selectIndexCollection = null;
	private Collection stockCollection = null;
	private Collection selectExchgCollection = null;
	private Collection selectIndiCollection=null;
	private ArrayList indexMovingTable =null;

	boolean report1 = false;
	boolean report2 = false;
	boolean report3 = false;
	boolean report4 = false;
	boolean	report5 = false;
	
	boolean report6 = false;
	boolean report7 = false;
	boolean report8 = false;
	boolean report9 = false;
	boolean	report100 = false;
	boolean report101 = false;
	boolean report102 = false;
	boolean report103 = false;
	boolean report104 = false;
	boolean	report105 = false;
	boolean	report106 = false;
	boolean	valid = false;
	private String selectReport[] = new String[0];

	private String tableReport[] = new String[0];

	double totalMcap = 0;
	private String[] ind1=null;
	private String[] ind2=null;
	private String[] ind3=null;
	private String[] ind4=null;
	double totalWt = 0;

	String stockName = null;

	Connection connection = null;

	ResultSet rs = null;

	String indexName = null;
	 private String  index_name11 = null;
	public returnVol rv;

	private String dataCount = null;

	Vector ohlcParam = new Vector();
	
	private Vector vecStockDetails=new Vector();

	Vector corelParam = new Vector();
	
	Vector comParam = new Vector();
	//Vector vector_index_rv1 = new Vector();
	private String currencyParam = null;

	private String index_name2 = null;

	private Vector capitalChangeVec = null;
	private Vector var_Table_data_vector = null;

	private ArrayList tableDatas = null;

	private ArrayList tableDatac = null;

	/* variables for stock Contri reports*/
	Vector vector_remStockid = null;
	Vector vExcel106=new Vector();

	ArrayList stockcotriIndexchange = null;

	ArrayList indweighttable = null;

	private Vector vi;
	private Vector viw;

	String tdate = null, fdate = null;

	//variable for index compare report

	private String[] d2 = null;

	//variables for returns and volatility
	private String[] indexList;

	PreparedStatement pst;

	private Vector vector_index_rv1;

	private ArrayList final_Vector = null;

	// stock dividend report 
	private String filter = "0";
	private String selectIndExch = null; //,from=null,to=null;

	private ArrayList tableDataSD = null;

	private Vector stkDividentVec = new Vector();

	//traded volume
	private Vector trdVolVec = new Vector();

	private String traded_volume = null;

	private ArrayList tableDataTr = null;

	public boolean getValid(){
		return valid;
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
	 * @return Returns the checkdate.
	 */
	public boolean is9() {
		return report9;
	}
	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set9(boolean report) {
		this.report9 = report;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set100(boolean report) {
		this.report100 = report;
	}

	/**
	 * @return Returns the checkdate.
	 */
	public boolean is100() {
		return report100;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set101(boolean report) {
		this.report101 = report;
	}

	/**
	 * @return Returns the checkdate.
	 */
	public boolean is101() {
		return report101;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set102(boolean report) {
		this.report102 = report;
	}

	/**
	 * @return Returns the checkdate.
	 */
	public boolean is102() {
		return report102;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set103(boolean report) {
		this.report103 = report;
	}

	/**
	 * @return Returns the checkdate.
	 */
	public boolean is103() {
		return report103;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set104(boolean report) {
		this.report104 = report;
	}
	/**
	 * @return Returns the checkdate.
	 */
	public boolean is104() {
		return report104;
	}

	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set105(boolean report) {
		this.report105 = report;
	}
	/**
	 * @return Returns the checkdate.
	 */
	public boolean is105() {
		return report105;
	}
	
	/**
	 * @param checkdate The checkdate to set.
	 */
	public void set106(boolean report) {
		this.report106 = report;
	}
	/**
	 * @return Returns the checkdate.
	 */
	public boolean is106() {
		return report106;
	}
	
	String index12 = null, index2 = null, index3 = null;

	private Collection selectUserCollection = null;

	private Collection selectReportCollection = null;

	private ArrayList tableData = null;

	private ArrayList tabledata3 = null;

	private ArrayList tableData2 = null;

	private ArrayList tableDatnew = null;

	private String tradingDate;

	private String close;

	private String mCap;

	private String divisor;

	private String pe, to;

	private String pb, from;

	private String divYield;

	private Boolean check1;

	private Vector vw;
	private String selectIndi1 = null;
	private String selectIndi2 = null;
	private String selectIndex = null;

	private String selectIndex1 =null;

	private String selectIndex2 =null;

	private String selectIndex3 =null;

	private String selectIndex4 =null;

	private String selectIndex5 =null;
	private String selectIndex7 =null;

	private String selectIndex8 =null;

	private String selectIndex9 =null;

	private String selectIndex14 =null;

	private String selectIndex15 =null;
	
	private String selectIndex16 =null;


	public indexMove im1;

	double total = 0.00;

	int diff = 0;

	private Hashtable StockNameHash = new Hashtable();

	private Hashtable IndexNameHash = new Hashtable();

//	AdjustDecimal ad = new AdjustDecimal();

	IndexComposeReportMethod Icr = new IndexComposeReportMethod();

//	app.Connect con = new app.Connect();
	Connect con = ConnectInit.getConnect();
	private ResultSet rst;

	Vector vExcel = new Vector();
	Vector vExcel20 = new Vector();

	ArrayList index_arraylist = new ArrayList();

	/**
	 * @return Returns the vExcel20.
	 */
	public Vector getVExcel20() {
		return vExcel20;
	}

	/**
	 * @param excel The vExcel20 to set.
	 */
	public void setVExcel20(Vector excel) {
		vExcel20 = excel;
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

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		//report1 = false;
		//report2 = false;
	//	report3 = false;
	//	report4 = false;
	//	report5 = false;
		executeButton = null;

	}

	/**RESEST ALL FORM FIELDS**/
	public void reset() {

//		report1 = false;
	//	report2 = false;
	//	report3 = false;
	//	report4 = false;
	//	report5 = false;
		executeButton = null;

	}
	
	/**
	 * VALIDATE FORM DATA
	 * **/
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		Logging.debug(" Inside validate....");
		ActionErrors errors = new ActionErrors();
		try{
						
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date curr = new Date();
			if(getExecuteButton() !=null && getExecuteButton().equals("Execute")){
				if(!(is1()||is2()||is3()||is4()||is5()||is6()||is7()||is8()||is9()
						||is100()||is101()||is102()||is103()||is104()||is105()||is106())){
					
						errors.add(null,new ActionError("Error.message.Please_select_report"));				
					
				}
			else{
			
			
			if(get22() != null && is2()){
				Date d1 = df.parse(get22());
				if(d1.after(curr)){
					set2(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get12());
				Date d3=df.parse(get22());
				if(get12()!=null && d2.after(d3)){
					set2(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			}
			
			
			if(get24() != null && is4()){
				Date d1 = df.parse(get24());
				if(d1.after(curr)){
					set4(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get14());
				Date d3=df.parse(get24());
				if(get14()!=null && d2.after(d3)){
					set4(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			}
			
			if(get25() != null && is5()){
				Date d1 = df.parse(get25());
				if(d1.after(curr)){
					set5(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get15());
				Date d3=df.parse(get25());
				if(get15()!=null && d2.after(d3)){
					set5(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
				if(get45() ==null && is5())
				{
					set5(false);
					errors.add(null,new ActionError("Error.message.select_stock_to_view_details"));		
				}
			}
			
			if(get26() != null && is6()){
				Date d1 = df.parse(get26());
				if(d1.after(curr)){
					set6(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get16());
				Date d3=df.parse(get26());
				if(get16()!=null && d2.after(d3)){
					set6(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			
			}
			if(get27() != null && is7()){
				Date d1 = df.parse(get27());
				if(d1.after(curr)){
					set7(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				
			}
			
			
			if(get29() != null && is9()){
				Date d1 = df.parse(get29());
				if(d1.after(curr)){
					set9(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get19());
				Date d3=df.parse(get29());
				if(get19()!=null && d2.after(d3)){
					set1(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			}
			
			
			if(get120() != null && is100()){
				Date d1 = df.parse(get120());
				if(d1.after(curr)){
					set100(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get110());
				Date d3=df.parse(get120());
				if(get110()!=null && d2.after(d3)){
					set100(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			}
			
			if(get121() != null && is101()){
				Date d1 = df.parse(get121());
				if(d1.after(curr)){
					set101(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get111());
				Date d3=df.parse(get121());
				if(get111()!=null && d2.after(d3)){
					set101(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			}
			
			if(get122() != null && is102()){
				Date d1 = df.parse(get122());
				if(d1.after(curr)){
					set102(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get112());
				Date d3=df.parse(get122());
				if(get112()!=null && d2.after(d3)){
					set102(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			
			}
			if(get123() != null && is103()){
				Date d1 = df.parse(get123());
				if(d1.after(curr)){
					set103(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get113());
				Date d3=df.parse(get123());
				if(get113()!=null && d2.after(d3)){
					set103(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			}
			
			if(get124() != null && is104()){
				Date d1 = df.parse(get124());
				if(d1.after(curr)){
					set104(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get111());
				Date d3=df.parse(get121());
				if(get114()!=null && d2.after(d3)){
					set104(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			}
			
			if(get125() != null && is105()){
				Date d1 = df.parse(get125());
				if(d1.after(curr)){
					set105(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get115());
				Date d3=df.parse(get125());
				if(get115()!=null && d2.after(d3)){
					set105(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			
			}
			if(get126() != null && is106()){
				Date d1 = df.parse(get126());
				if(d1.after(curr)){
					set106(false);
					errors.add(null,new ActionError("Error.message.GreaterDate1"));				
				}
				Date d2=df.parse(get116());
				Date d3=df.parse(get126());
				if(get116()!=null && d2.after(d3)){
					set106(false);
					errors.add(null,new ActionError("Error.message.from_date_greaterthan_todate"));			
				}
			
			}
			
			}
			}
	   }catch(Exception e){
		   errors.add(null,new ActionError("Error.message.msg"));
				
	}	
	
	return errors;
		
	}

	/**
	 * @return Returns the selectExchange.
	 */
	public String getSelectExchange() {
		return selectExchange;
	}

	/**
	 * @param selectExchange The selectExchange to set.
	 */
	public void setSelectExchange(String selectExchange) {
		this.selectExchange = selectExchange;
	}

	/**
	 * @return Returns the selectExchange.
	 */
	public String get56() {
		return selectExchange6;
	}

	/**
	 * @param selectExchange The selectExchange to set.
	 */
	public void set56(String selectExchange6) {
		this.selectExchange6 = selectExchange6;
	}

	/**
	 * @return Returns the selectExchange.
	 */
	public String get154() {
		return selectExchange14;
	}

	/**
	 * @param selectExchange The selectExchange to set.
	 */
	public void set154(String selectExchange14) {
		this.selectExchange14 = selectExchange14;
	}

	/**
	 * @return Returns the selectExchange.
	 */
	public String get155() {
		return selectExchange15;
	}

	/**
	 * @param selectExchange15 The selectExchange15 to set.
	 */
	public void set155(String selectExchange15) {
		this.selectExchange15 = selectExchange15;
	}

	
	/**
	 * @return Returns the selectExchgCollection.
	 */
	public Collection getSelectExchgCollection() {
		Vector vec = new Vector();

		//vec.add(new LabelValueBean("Not Selected","0"));
		PreparedStatement stmt = null;
		Connection connection= null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
				stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stock_exchange_list"));
				ResultSet rst = stmt.executeQuery();
				while (rst.next()) {
					vec.add(new LabelValueBean(rst.getString(2), rst.getString(1)));
				}
				rst.close();
				stmt.close();
				
				selectExchgCollection = vec;
			
		} catch (SQLException e) {
			Logging.debug(e);
		//	e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null){
						connection.close();
					}
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}

		return selectExchgCollection;
	}

	/**
	 * @param selectExchgCollection The selectExchgCollection to set.
	 */
	public void setSelectExchgCollection(Collection selectExchgCollection) {
		this.selectExchgCollection = selectExchgCollection;
	}
	//////////////////
	/**
	 * @return Returns the selectCollection.
	 */
	public Collection getSelectIndiCollection() {
		Vector vec = new Vector();
		
		vec.add(new LabelValueBean("Not Selected","0"));
		vec.add(new LabelValueBean("Exchange Wise","1"));
		vec.add(new LabelValueBean("Index Wise","2"));
		
		
				selectIndiCollection = vec;
				return selectIndiCollection;
	}

	/**
	 * @param selectCollection The selectCollection to set.
	 */
	public void setSelectIndiCollection(Collection selectIndiCollection) {
		this.selectIndiCollection = selectIndiCollection;
	}
	/**
	 * @return Returns the d2.
	 */
	public String[] get71() {
		return ind1;
	}
	/**
	 * @param d2 The d2 to set.
	 */
	public void set71(String[] ind1) {
		this.ind1 = ind1;
	}
	/**
	 * @return Returns the d2.
	 */
	public String[] get72() {
		return ind2;
	}
	/**
	 * @param d2 The d2 to set.
	 */
	public void set72(String[] ind2) {
		this.ind2 = ind2;
	}
	/**
	 * @return Returns the d2.
	 */
	public String[] get73() {
		return ind3;
	}
	/**
	 * @param d2 The d2 to set.
	 */
	public void set73(String[] ind3) {
		this.ind3 = ind3;
	}
	/**
	 * @return Returns the d2.
	 */
	public String[] get74() {
		return ind4;
	}
	/**
	 * @param d2 The d2 to set.
	 */
	public void set74(String[] ind4) {
		this.ind4 = ind4;
	}
	/**
	 * @return Returns the select.
	 */
	public String get62() {

		return selectIndi2;
	}

	/**
	 * @param select The select to set.
	 */
	public void set62(String selectIndi2) {
		
		this.selectIndi2 = selectIndi2;
	}
	/**
	 * @return Returns the select.
	 */
	public String get61() {

		return selectIndi1;
	}

	/**
	 * @param select The select to set.
	 */
	public void set61(String selectIndi1) {
		
		this.selectIndi1 = selectIndi1;
	}
///////////////////
	/**
	 * @return Returns the selectCollection.
	 */
	public Collection getSelectIndexCollection() {
		Vector vec = new Vector();
		//dbconnect();
		Connection connection = null;
		vec.add(new LabelValueBean("Not Selected", "0"));
		PreparedStatement stmt = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();

			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("index_list_without_child"));
			//query = c.queries.getProperty("index_list_without_child");

			ResultSet rst = stmt.executeQuery();
			while (rst.next()) {
				vec.add(new LabelValueBean(rst.getString(2), rst.getString(1)));
				IndexNameHash.put(rst.getString(1), rst.getString(2));
			}

			rst.close();
			stmt.close();
			selectIndexCollection = vec;
			return selectIndexCollection;
		} catch (SQLException e) {
			Logging.debug(e);
		//	e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
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

	/**
	 * @return Returns the select.
	 */
	public String getSelectIndex() {

		return selectIndex;
	}

	/**
	 * @param select The select to set.
	 */
	public void setSelectIndex(String selectIndex) {
		
		this.selectIndex = selectIndex;
	}

	/**
	 * @return Returns the select.
	 */
	public String get31() {

		return selectIndex1;
	}

	/**
	 * @param select The select to set.
	 */
	public void set31(String selectIndex1) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex1 = selectIndex1;
	}

	/**
	 * @return Returns the select.
	 */
	public String get32() {

		return selectIndex2;
	}

	/**
	 * @param select The select to set.
	 */
	public void set32(String selectIndex2) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex2 = selectIndex2;
	}

	/**
	 * @return Returns the select.
	 */
	public String get33() {

		return selectIndex3;
	}

	/**
	 * @param select The select to set.
	 */
	public void set33(String selectIndex3) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex3 = selectIndex3;
	}

	/**
	 * @return Returns the select.
	 */
	public String get34() {

		return selectIndex4;
	}

	/**
	 * @param select The select to set.
	 */
	public void set34(String selectIndex4) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex4 = selectIndex4;
	}


	
	/**
	 * @param select The select to set.
	 */
	public void set35(String selectIndex5) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex5 = selectIndex5;
	}

	/**
	 * @return Returns the select.
	 */
	public String get35() {

		return selectIndex5;
	}

	/**
	 * @param select The select to set.
	 */
	public void set37(String selectIndex7) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex7 = selectIndex7;
	}

	/**
	 * @return Returns the select.
	 */
	public String get37() {

		return selectIndex7;
	}

	/**
	 * @param select The select to set.
	 */
	public void set38(String selectIndex9) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex8 = selectIndex9;
	}
	/**
	 * @return Returns the select.
	 */
	public String get38() {

		return selectIndex8;
	}

	/**
	 * @return Returns the select.
	 */
	public String get39() {

		return selectIndex9;
	}
	/**
	 * @param select The select to set.
	 */
	public void set39(String selectIndex9) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex9 = selectIndex9;
	}
	/**
	 * @param select The select to set.
	 */
	public void set134(String selectIndex14) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex14 = selectIndex14;
	}

	/**
	 * @return Returns the select.
	 */
	public String get134() {

		return selectIndex14;
	}

	/**
	 * @param select The select to set.
	 */
	public void set135(String selectIndex15) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex15 = selectIndex15;
	}

	/**
	 * @return Returns the select.
	 */
	public String get135() {

		return selectIndex15;
	}
	
	/**
	 * @param select The select to set.
	 */
	public void set136(String selectIndex16) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex16 = selectIndex16;
	}

	/**
	 * @return Returns the select.
	 */
	public String get136() {

		return selectIndex16;
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
	 * @return Returns the selectStock.
	 */
	/*
	public String get45() {

		return selectStock;
	}
	*/
	/**
	 * @param selectStock The selectStock to set.
	 */
	/*
	public void set45(String selectStock) {
		this.selectStock = selectStock;
	}
	*/
	public String[] get45() {
		return selectStock1;
	}

	public void set45(String[] selectStock1) {
		this.selectStock1 = selectStock1;
	}
	/**
	 * @return Returns the stockCollection.
	 */
	public Collection getStockCollection() {
		//dbconnect();
		Vector vec = new Vector();
		Connection connection = null;
		String indexNew=null;
		indexNew=get31();
		try {
			if (connection == null)
				connection = con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("stockDetailFromDate_stock_list_index"));
			stmt.setString(1, indexNew);
			ResultSet rst = stmt.executeQuery();
			while (rst.next()) {
				vec.add(new LabelValueBean(rst.getString(1), rst.getString(2)));
				StockNameHash.put(rst.getString(2), rst.getString(1));
			}
			rst.close();
			stmt.close();
			stockCollection = vec;
			//	return stockCollection;
		} catch (SQLException e) {
			Logging.debug(e);
		//	e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		stockCollection = vec;
		return stockCollection;
	}

	/**
	 * @param stockCollection The stockCollection to set.
	 */
	public void setStockCollection(Collection stockCollection) {
		//Logging.getDebug(" Inside set Stock Collection...");
		this.stockCollection = stockCollection;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileVariable() {
		return fileVariable;
	}

	public void setFileVariable(String fileVariable) {
		this.fileVariable = fileVariable;
	}

	// tabledata for all reports
	/**
	 * @return Returns the tableData.
	 */
	public ArrayList getTableData() {
		if(!(is1()||is2()||is3()||is4()||is5()||is6()||is7()||is8()||
				is9()||is100()||is101()||is102()||is103()||is104()||is105()||is106())){
		//Logging.getDebug("inside the gettabledata");
		//Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList temp = new ArrayList();
		String local_id = getSelectUser();
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				String query = ConnectInit.queries
						.getProperty("select_*_from_preferencedetail1");
				//select * from preferencedetail where preference_id\=?
				//System.out.println("Query   = " + query);
				pst = connection.prepareStatement(query);
				pst.setString(1, local_id);

				rs = pst.executeQuery();
				int i = 0;
				while (rs.next()) {

					String index_id1 = null;//this is very imp lokesh
					filePath = rs.getString("report_name");
					String exchange_id = rs.getString("exchange_id");
					
					
					if (exchange_id != null) {
						set56(exchange_id);
						set154(exchange_id);
						set155(exchange_id);
						//setExchange_name(exchange_id);
						//index_id1 = getExchange_name();

					} else {
						index_id1 = rs.getString("index_id1");
						id = index_id1;
						setId(id);
						id = getId();
						set31(id);
						set32(id);
						set33(id);
						set34(id);
						set35(id);
						set37(id);
						set38(id);
						set39(id);
						set134(id);
						set135(id);
						set136(id);
						
						//index_id1=getIndex_name2();

					}
					int fdate1 = rs.getInt("days_difference");
					
					
					set12(fdate1);
					set14(fdate1);
					set15(fdate1);
					set16(fdate1);
					set19(fdate1);
					set110(fdate1);
					set111(fdate1);
					set112(fdate1);
					set113(fdate1);
					set114(fdate1);
					set115(fdate1);
					set116(fdate1);
					
					set22();
					set24();
					set25();
					set26();
					set27();
					set29();
					set120();
					set121();
					set122();
					set123();
					set124();
					set125();
					set126();					
					
					Date tdate1 = new Date();
					String index_id2 = rs.getString("index_id2");
					String stock_id1 = rs.getString("stock_id1");
					// String stock_idd1=getStock_name(stock_id1);
					//setStock_name(stock_id1);
					String stock_idd1 = getStock_name();
					BatchReportDetails icd = new BatchReportDetails(filePath,index_id1, fdate1, tdate1, index_id2, stock_idd1,id);
					temp.add(icd);
				}
				
				rs.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
			
		} 
		
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		tableData = temp;
		Logging.debug("sizeeeeeeee of tabledata" + tableData.size());
		}
		return tableData;

	}

	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}


	/**
	 * @return Returns the select.
	 */
	public String getSelectUser() {

		return selectUser;
	}

	/**
	 * @param selectUser The selectUser to set.
	 */
	public void setSelectUser(String selectUser) {
		this.selectUser = selectUser;
	}
	/**
	 * @return Returns the executeButton.
	 */
	public String getBackButton() {
		return backButton;
	}

	/**
	 * @param executeButton The executeButton to set.
	 */
	public void setBackButton(String bButton) {
		this.backButton = bButton;

	}
	/**
	 * @return Returns the executeButton.
	 */
	public String getResetButton() {
		return resetButton;
	}

	/**
	 * @param executeButton The executeButton to set.
	 */
	public void setResetButton(String rButton) {
		this.resetButton = rButton;

	}
	/**
	 * @return Returns the executeButton.
	 */
	public String getExecuteButton() {
		return executeButton;
	}

	/**
	 * @param executeButton The executeButton to set.
	 */
	public void setExecuteButton(String sButton) {
		this.executeButton = sButton;

	}

	/**
	 * @return Returns the selectUserCollection.
	 */
	public Collection getSelectUserCollection() {
		Vector vec = new Vector();

		Connection connection = null;
		vec.add(new LabelValueBean("Not Selected", "0"));
		PreparedStatement stmt = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("select_*_from_report_prefrence"));
			//select * from report_prefrence				
			ResultSet rst = stmt.executeQuery();
			while (rst.next()) {
				vec.add(new LabelValueBean(rst.getString(2), rst.getString(1)));

			}

			rst.close();
			stmt.close();
			selectUserCollection = vec;
			//return selectExchgCollection;
		} catch (SQLException e) {
			Logging.debug(e);
		//	e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		Logging.debug("sizeeeeeeee of usercollection" + vec.size());
		selectUserCollection = vec;
		return selectUserCollection;
	}

	/**
	 * @param selectUserCollection The selectUserCollection to set.
	 */
	public void setSelectUserCollection(Collection selectUserCollection) {
		this.selectUserCollection = selectUserCollection;
	}

	public String getIndex_name() {
		//Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String local_id = getId();
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				String query = ConnectInit.queries
						.getProperty("query_copy_indexmaster");
				//select * from preferencedetail where preference_id\=?
				//System.out.println("Query   = " + query);
				pst = connection.prepareStatement(query);
				pst.setString(1, local_id);
				rs = pst.executeQuery();
				int i = 0;
				while (rs.next()) {
					index_name = rs.getString("index_name");
				}
				//System.out.println("indexname" + index_name);

			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		Logging.debug("sizeeeeeeee of tabledata" + tableData.size());

		return index_name;
	}

	public void setIndex_name(String index_name) {
		this.index_name = index_name;

	}

	public Boolean getCheck1() {
		return check1;
	}

	public void setCheck1(Boolean check1) {
		this.check1 = check1;
	}

	public String getExchange_name() {
		return exchange_name;
	}

	public void setExchange_name(String exchange_id) {
		//Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String local_id = exchange_id;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {
				String query = ConnectInit.queries
						.getProperty("select_stock_ex_name_from_stockmaster");
				//select * from preferencedetail where preference_id\=?
				//System.out.println("Query   = " + query);
				pst = connection.prepareStatement(query);
				pst.setString(1, local_id);

				rs = pst.executeQuery();
				int i = 0;
				while (rs.next()) {
					exchange_name = rs.getString("stock_ex_name");

				}

			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		Logging.debug("sizeeeeeeee of tabledata" + tableData.size());
		this.exchange_name = exchange_name;
	}

	public String getStock_name() {
		return stock_name1;
	}

	public void setStock_name(String stock_id) {
		//Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String local_id = stock_id;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {

				String query = ConnectInit.queries
						.getProperty("select_stock_name_from_stock_master");
				//select * from preferencedetail where preference_id\=?
				//System.out.println("Query   = " + query);
				pst = connection.prepareStatement(query);
				pst.setString(1, local_id);

				rs = pst.executeQuery();
				int i = 0;
				while (rs.next()) {
					stock_name1 = rs.getString("stock_name");

				}

			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		Logging.debug("sizeeeeeeee of tabledata" + tableData.size());
		this.stock_name1 = stock_name1;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the companyWeightage.
	 */
	public ArrayList getCompanyWeightage() {
		totalMcap = 0;
		totalWt = 0;
		//Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String company = null, mcap = null, weightage = null;
		ArrayList temp = new ArrayList();
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {

				String local_date = get27();
				
				String local_d1 = get37();
				setIndex_name11(local_d1);
				//System.out.println("local_date"+local_date);
				//System.out.println("local_d1="+local_d1);
				String query = ConnectInit.queries.getProperty("company_wise_weightage");
				//System.out.println("Query   = " + query);
				pst = connection.prepareStatement(query);
				pst.setString(1, local_d1);
				pst.setString(2, local_date);
				rs = pst.executeQuery();
				int i = 0;
				CompanyWeightageVector.clear();
				while (rs.next()) {
					if (rs.getString(1) == null) {
						company = "--";
						CompanyWeightageVector.add(i, "--");

					} else {
						company = rs.getString(1);
						CompanyWeightageVector.add(i, rs.getString(1));

					}
					i++;
					if (rs.getString(2) == null) {
						mcap = "0";
						CompanyWeightageVector.add(i, "0");

					} else {
						mcap = rs.getString(2);
						CompanyWeightageVector.add(i, rs.getString(2));
						double NumericMcap = Double.parseDouble(mcap);
						totalMcap = totalMcap + NumericMcap;
					}
					i++;
					if (rs.getString(3) == null) {
						weightage = "0";
						CompanyWeightageVector.add(i, "0");
					} else {
						weightage = rs.getString(3);
						double NumericWeightage = Double.parseDouble(weightage);
						totalWt = totalWt + NumericWeightage;
						CompanyWeightageVector.add(i, rs.getString(3));
					}
					i++;
					CompanyWiseWeightageDetails cww = new CompanyWiseWeightageDetails(
							company, mcap, weightage);
					temp.add(cww);
				}//end of while 

			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		//System.out.println("Table Vector" + CompanyWeightageVector);
		setCompanyWeightageVector(CompanyWeightageVector);
		companyWeightage = temp;
		return companyWeightage;

	}

	/**
	 * @param companyWeightage The companyWeightage to set.
	 */
	public void setCompanyWeightage(ArrayList companyWeightage) {
		this.companyWeightage = companyWeightage;
	}

	public Vector getCompanyWeightageVector() {
		return CompanyWeightageVector;
	}

	/**
	 * @param companyWeightageVector The companyWeightageVector to set.
	 */
	public void setCompanyWeightageVector(Vector companyWeightageVector) {
		this.CompanyWeightageVector = companyWeightageVector;
	}

	// Tabledata for Industry weightage report
	
	public ArrayList getIndweighttable() {
		String industryname = null, marketcap = null, weightage = null, Strmvc = null, mar = null;
		viw = new Vector();
		Connection connection = null;
		double total1 = 0.00, total2 = 0.00;
		double strweightage = 0.0, market = 0.00;
		String index12 = get38();
		String index1, index2, tno1 = null;
		index1 = index2 = get38();
		//Connect con = new Connect();
		indweighttable = new ArrayList();
		ArrayList tempdata = new ArrayList();
		IndexWise indexwise;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			/*if(Connect.con==null)
			 {
			 con.getConnection();
			 }*/
			rst = Icr.indweightResult(connection, "industry_wise_weightage",
					index12);
			int i = 0;
			Logging
					.debug("setVector_indweighttable of Industry wise weightage");
			try {
				AdjustDecimal ad = ConnectInit.getAdjustDecimal();
				while (rst.next()) {

					if (rst.getString(1) == null) {
						industryname = "0";
					} else {
						industryname = rst.getString(1);
						viw.add(i, rst.getString(1));
					}
					i++;
					if (rst.getString(2) == null) {
						marketcap = "0";
					} else {
						marketcap = rst.getString(2);
						marketcap = ad.indexcompose(marketcap);
						marketcap = org.jfree.chart.demo.servlet.AdjustDecimal
								.ArrangeAsNumeric(marketcap);
						viw.add(i, rst.getString(2));
					}
					i++;
					mar = rst.getString(2);
					mar = ad.indexcompose(mar);
					market = Double.parseDouble(mar);
					total2 = total2 + market;
					tno1 = ad.shareholdingpatt(total2);
					tno1 = org.jfree.chart.demo.servlet.AdjustDecimal
							.ArrangeAsNumeric(tno1);
					if (rst.getString(3) == null) {
						weightage = "0";
					} else {
						weightage = rst.getString(3);
						weightage = ad.indexcompose(weightage);
						viw.add(i, rst.getString(3));
					}
					i++;
					Strmvc = rst.getString(3);
					strweightage = Double.parseDouble(Strmvc);
					total1 = total1 + strweightage;
					indexwise = new IndexWise(industryname, marketcap,
							weightage);
					tempdata.add(indexwise);
				}
				rst.close();
			} catch (SQLException sqlexp) {
				Logging.error("Error : " + sqlexp.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		indweighttable = tempdata;
		//System.out.println(indweighttable);
		setTotal(total1);
		setVal(tno1);
		setViw(viw);
		return indweighttable;
	}

	public String getVal() {
		return val;
	}

	/**
	 * @param to The toDate to set.
	 */
	public void setVal(String val) {
		this.val = val;
	}

	public Vector getVi() {
		return vi;
	}

	/**
	 * @param industryWeightageVector The industryWeightageVector to set.
	 */
	public void setViw(Vector viw) {
		this.viw = viw;
	}
	public Vector getViw() {
		return viw;
	}

	/**
	 * @param companyWeightageVector The companyWeightageVector to set.
	 */
	public void setVi(Vector vi) {
		this.vi = vi;
	}
	/**
	 * @param vector_indtable
	 *            The vector_indtable to set.
	 */
	public void setIndweighttable(ArrayList indweighttable) {
		this.indweighttable = indweighttable;
	}

	public ArrayList getTabledata3() {
		
		
		
		String stockid = null, stockname = null, currency = null, tis = null, iwf = null, adjusted = null, mcv = null, stockprice = null, market = null, last = null, curr_exch_convIratecomp1 = null, strweightage1 = null;

		Connection connection = null;
		//Connect con = ConnectInit.getConnect();
		double total1 = 0.00;
		vw = new Vector();
		double tmcv = 0.0;
		ArrayList tempdata = new ArrayList();
		String index_id, date;
//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		// to retrive index id form database
		index12 = get31();
		setIndex_name11(index12);
		try {
			if (connection == null)
				connection = con.getdbConnection();
			
				//System.out.println("index1------>" + index12);
				
			} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}

		try {

			//System.out.println("index12------>" + index12);
			ResultSet tmcvrst = Icr.stiockweightageLatestResult(connection,
					"get_tmcv_for_composition", index12);
			Logging.debug("get tmcv of Compose Index");
			try {
				while (tmcvrst.next()) {
					index_id = tmcvrst.getString(1);
					tmcv = tmcvrst.getDouble(2);
					Logging.debug("tmcv is " + tmcv);
					date = tmcvrst.getString(3);

				}

			}

			catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}

			String curr_exch_convIratecomp = null, strmcv = null;
			double weightage = 0.0, mcve = 0.0;
			rst = Icr.indexComposeResult(connection,
					"get_composition_for_compose_report", index12);
			int i = 0;

			tabledata3 = new ArrayList();
			IndexCompose12 indexcompose1;
			Logging.debug("setVector_tabledata of Compose Index");
			try {
				while (rst.next()) {

					if (rst.getString(1) == null) {
						stockid = "0";
					} else {
						stockid = rst.getString(1);
						vw.add(i, rst.getString(1));
					}
					i++;
					if (rst.getString(2) == null) {
						stockname = "0";
					} else {
						stockname = rst.getString(2);
						vw.add(i, rst.getString(2));
					}
					i++;
					if (rst.getString(4) == null) {
						tis = "0";
					} else {
						tis = rst.getString(4);
						tis = AdjustDecimal.ArrangeAsNumeric(tis);
						vw.add(i, rst.getString(4));
					}
					i++;
					if (rst.getString(5) == null) {
						iwf = "0";
					} else {
						iwf = rst.getString(5);
						iwf = ad.indexcompose(iwf);
						iwf = AdjustDecimal.ArrangeAsNumeric(iwf);
						vw.add(i, rst.getString(5));
					}
					i++;
					if (rst.getString(9) == null) {
						market = "0";
					} else {
						market = rst.getString(9);
						vw.add(i, rst.getString(9));
					}
					i++;
					if (rst.getString(6) == null) {
						adjusted = "0";
					} else {
						adjusted = rst.getString(6);
						adjusted = ad.indexcompose(adjusted);
						adjusted = AdjustDecimal.ArrangeAsNumeric(adjusted);
						vw.add(i, rst.getString(6));
					}
					i++;
					if (rst.getString(10) == null) {
						last = "0";
					} else {
						last = rst.getString(10);
						last = ad.indexcompose(last);
						last = AdjustDecimal.ArrangeAsNumeric(last);
						vw.add(i, rst.getString(10));
					}
					i++;
					if (rst.getString(3) == null) {
						currency = "0";
					} else {
						currency = rst.getString(3);
						vw.add(i, rst.getString(3));
					}

					i++;
					curr_exch_convIratecomp = getCurrancyExchRate(index12,
							stockid);
					//Logging.getDebug("curr_exch_convIrate is "+curr_exch_convIrate);
					curr_exch_convIratecomp = ad
							.indexcompose4digit(curr_exch_convIratecomp);
					if (curr_exch_convIratecomp == null) {
						curr_exch_convIratecomp = "0";
						vw.add(i, curr_exch_convIratecomp);
					} else {
						curr_exch_convIratecomp1 = curr_exch_convIratecomp;
						vw.add(i, curr_exch_convIratecomp);
					}

					i++;
					if (rst.getString(7) == null) {
						mcv = "0";
					} else {
						mcv = rst.getString(7);
						mcv = ad.indexcompose(mcv);
						mcv = AdjustDecimal.ArrangeAsNumeric(mcv);
						vw.add(i, rst.getString(7));
					}
					i++;
					if (rst.getString(7) == null) {
						mcv = "0";
					} else {
						mcv = rst.getString(7);
						mcv = ad.indexcompose(mcv);
						mcv = AdjustDecimal.ArrangeAsNumeric(mcv);
						vw.add(i, rst.getString(7));
					}
					i++;
					strmcv = rst.getString(7);
					mcve = Double.parseDouble(strmcv);
					if (tmcv != 0.0) {
						weightage = (mcve / tmcv) * 100.00;
					}
					total1 = total1 + weightage;
					String strweightage = new Double(weightage).toString();
					strweightage = ad.shareholdingpatt(strweightage);
					strweightage = ad.indexcompose4digit(strweightage);
					strweightage = AdjustDecimal.ArrangeAsNumeric(strweightage);
					strweightage1 = strweightage;
					vw.add(i, strweightage1);
					//weightage 
					/*if (rst.getString(8) == null) {
					 vector_tabledata.add(i, "0");
					 } else {
					 vector_tabledata.add(i, rst.getString(8));
					 }*/

					i++;
					if (rst.getString(8) == null) {
						stockprice = "-0";
					} else {
						stockprice = rst.getString(8);
						vw.add(i, rst.getString(8));
					}
					i++;
					indexcompose1 = new IndexCompose12(stockid, stockname,
							currency, tis, iwf, adjusted, mcv, stockprice,
							market, last, curr_exch_convIratecomp1,
							strweightage1);
					tempdata.add(indexcompose1);
				}
				rst.close();
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		tabledata3 = tempdata;
		//System.out.println(tabledata3);
		setTotal(total1);
		setVw(vw);
		return tabledata3;
	}

	/**
	 * @return Returns the selectCollection.
	 */

	public void setTabledata3(ArrayList tabledata3) {
		this.tabledata3 = tabledata3;
	}

	public String getCurrancyExchRate(String index12, String stockid) {
		String cexch_rate = null;
		String stk_currency_id = null, ind_currency_id = null;
		//Connect con = new Connect();
		Connection connection = null;
		if (connection == null) {
			connection = con.getdbConnection();
		}
		try {
			//Logging.getDebug("inside getCurrancyExchRate");
			ResultSet rstexc = Icr.indwtResult(connection,
					"get_index_and_stock_currency_id", stockid, index12);
			int i = 0;
			Logging.debug("rst is " + rstexc);
			while (rstexc.next()) {
				if (rstexc.getString(1) == null) {
					stk_currency_id = "0";
				} else {
					stk_currency_id = (String) rstexc.getString(1);
				}
				if (rstexc.getString(2) == null) {
					ind_currency_id = "0";
				} else {
					ind_currency_id = (String) rstexc.getString(2);
				}
			}
			//Logging.getDebug("stk_currency_id is "+stk_currency_id+" ind_currency_id is "+ind_currency_id);
			if (stk_currency_id.equals(ind_currency_id)) {
				cexch_rate = "1.00";
			} else {
				/*ResultSet rst11 = con.indwtResult("get_currency_exchange_rate", ind_currency_id,stk_currency_id);
				 while (rst11.next()) {
				 if (rst.getString(1) == null) {
				 cexch_rate="0";
				 }else{
				 cexch_rate=(String)rst11.getString(1);
				 }					
				 }*/

				String temp = Icr.getIndexCurrancyExchRate(connection,
						stk_currency_id, ind_currency_id);
				double exch = 0.0;
				if (temp != null) {
					exch = new Double(temp).doubleValue();
				} else {
					temp = Icr.getIndexCurrancyExchRate(connection,
							ind_currency_id, stk_currency_id);
					if (temp == null) {
						exch = 1.0;
					} else {
						exch = 1 / new Double(temp).doubleValue();
					}
				}
				cexch_rate = new Double(exch).toString();
				Logging.debug("currency exchange rate is " + cexch_rate);
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : " + ex.getMessage());
				}
				Logging.error(" Error : " + ee.getMessage());
			}
		}
		return cexch_rate;
	}

	/**
	 * @return Returns the total.
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total The total to set.
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return Returns the vw.
	 */
	public Vector getVw() {
		return vw;
	}

	/**
	 * @param vw The vw to set.
	 */
	public void setVw(Vector vw) {
		this.vw = vw;
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

	// Tabledata for IndexWise  PE/PB

	public ArrayList getIndex_arraylist() {
		ArrayList vdata = new ArrayList();
		Connection connection = null;
		//Connect con = ConnectInit.getConnect();
		// To retrive index id form database
		String td = null, close = null, change = null, mcap = null, shtr = null, trnovr = null, perat = null, pbrat = null, dividend = null;
		index12 = get34();
		setIndex_name11(index12);
 		to = get24();
		from = get14();
		//System.out.println("todate" + to);
		//System.out.println("fromdate" + from);
		//System.out.println("index1--new---->" + index12);

		try {
			if (connection == null)
				connection = con.getdbConnection();
						} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		}
		try {
		//	org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			vdata = new ArrayList();
			String local_d1 = index12;
			String local_from = from;// write code for from date
			String local_to = to;
			//System.out.println("todate1" + to);
			//System.out.println("fromdate1" + from);
			double lastclose = getlastclosing(local_d1, local_from);
			Logging.debug(local_d1 + "  " + local_from + " " + "  "
					+ local_to);
			PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries
					.getProperty("indexwise_pe_pb_ratio"));
			pst.setString(1, local_d1);
			pst.setString(2, local_from);
			pst.setString(3, local_to);
			pst.setString(4, local_d1);
			pst.setString(5, local_from);
			pst.setString(6, local_to);
			pst.setString(7, local_d1);
			pst.setString(8, local_from);
			pst.setString(9, local_to);
			pst.setString(10, local_from);
			pst.setString(11, local_to);
			pst.setString(12, local_d1);
			ResultSet rst = pst.executeQuery();
			Logging.debug("setVector_vdata");
			int i = 0;
			vExcel20.clear();
			while (rst.next()) {
				if (rst.getString(1) == null) {
					td = "---";
				} else {
					td = rst.getString(1);
				}
				vExcel20.add(i, td);
				i++;
				if (rst.getString(2) == null) {
					close = "0";
				} else {
					String strclose = (String) rst.getString(2);
					strclose = ad.indexcompose(strclose);
					strclose = AdjustDecimal.ArrangeAsNumeric(strclose);
					close = strclose;
				}
				vExcel20.add(i, close);
				i++;
				double pchange = 0.0;
				if (lastclose != 0.0) {
					pchange = (((double) rst.getDouble(2)) - lastclose)
							/ lastclose;
				} else {
					pchange = 0.0;
				}
				String strpchange = new Double(pchange).toString();
				strpchange = ad.indexcompose(strpchange);
				strpchange = AdjustDecimal.ArrangeAsNumeric(strpchange);
				change = strpchange;
				vExcel20.add(i, strpchange);
				i++;

				lastclose = (double) rst.getDouble(2);

				if (rst.getString(3) == null) {
					mcap = "0";
				} else {
					double mcv = (double) rst.getDouble(3);
					mcv = mcv / 1000000.0;
					String strmcv = new Double(mcv).toString();
					strmcv = ad.shareholdingpatt(strmcv);
					strmcv = ad.indexcompose(strmcv);
					strmcv = AdjustDecimal.ArrangeAsNumeric(strmcv);
					mcap = strmcv;
				}
				vExcel20.add(i, mcap);
				i++;
				if (rst.getString(4) == null) {
					shtr = "0";
				} else {
					shtr = rst.getString(4);
				}
				vExcel20.add(i, shtr);
				i++;
				if (rst.getString(5) == null) {
					trnovr = "0";
				} else {
					double trv = (double) rst.getDouble(5);
					trv = trv / 1000000.0;
					String turnover = new Double(trv).toString();
					turnover = ad.indexcompose(turnover);
					turnover = AdjustDecimal.ArrangeAsNumeric(turnover);
					trnovr = turnover;
				}
				vExcel20.add(i, trnovr);
				i++;
				if (rst.getString(6) == null) {
					perat = "0";
				} else {
					double tmcv = (double) rst.getDouble(3);
					double dperatio = (double) rst.getDouble(6);
					Logging.debug("pe ratio is " + dperatio + " tmcv "
							+ tmcv);
					dperatio = tmcv / dperatio;
					Logging.debug("pe ratio is " + dperatio
							+ " actaual " + (double) rst.getDouble(6));
					String peratio = new Double(dperatio).toString();
					peratio = ad.shareholdingpatt(peratio);
					peratio = ad.indexcompose(peratio);
					peratio = AdjustDecimal.ArrangeAsNumeric(peratio);
					perat = peratio;
				}
				vExcel20.add(i, perat);
				i++;
				if (rst.getString(7) == null) {
					pbrat = "0";
				} else {
					double tmcv = (double) rst.getDouble(3);
					double dpbratio = (double) rst.getDouble(7);
					Logging.debug("pb ratio is " + dpbratio + " tmcv "
							+ tmcv);
					dpbratio = tmcv / dpbratio;
					Logging.debug("pb ratio is " + dpbratio
							+ " actual " + (double) rst.getDouble(7));
					String pbratio = new Double(dpbratio).toString();
					pbratio = ad.shareholdingpatt(pbratio);
					pbratio = ad.indexcompose(pbratio);
					pbratio = AdjustDecimal.ArrangeAsNumeric(pbratio);
					pbrat = pbratio;
				}
				vExcel20.add(i, pbrat);
				i++;
				if (rst.getString(8) == null) {
					dividend = "0";
				} else {
					double tmcv = (double) rst.getDouble(3);
					double divvalue = (double) rst.getDouble(8);
					divvalue = tmcv / divvalue;
					Logging.debug("div value is " + divvalue);
					String div = new Double(divvalue).toString();
					div = ad.indexcompose(div);
					div = AdjustDecimal.ArrangeAsNumeric(div);
					dividend = div;
				}
				vExcel20.add(i, dividend);
				i++;
				IndexPePbDetails ipepb = new IndexPePbDetails(td, close,
						change, mcap, shtr, trnovr, perat, pbrat, dividend);
				vdata.add(ipepb);
			} //end of while
			Logging.debug("vector size " + vdata.size());
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}

		setVExcel20(vExcel20);
		Logging.debug("vector size " + vExcel20.size());
		index_arraylist = vdata;
		return index_arraylist;
	}
	
	

	public double getlastclosing(String id, String fdate) {
		double lastclose = 0.0;
		try {
			//Connect con = new Connect();
			if (Connect.con == null) {
				con.getConnection();
			}
			PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries
					.getProperty("get_index_closing_max_value"));
			pst.setString(1, id);
			pst.setString(2, id);
			pst.setString(3, fdate);
			ResultSet rst = pst.executeQuery();
			int i = 0;
			while (rst.next()) {
				if (rst.getString(1) == null) {
					lastclose = 0.0;
				} else {
					lastclose = rst.getDouble(1);
				}
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
		return lastclose;
	}

	/**
	 * @param index_arraylist The index_arraylist to set.
	 */
	public void setIndex_arraylist(ArrayList index_arraylist) {
		this.index_arraylist = index_arraylist;
	}

	// Tabledata for Index Divisor

	public ArrayList getTableData2() {
		//if(is1()){
		ArrayList Pp = new ArrayList();
		Vector Table_data_vector = new Vector();
		harrier.income.com.report.AdjustDecimal ad = new harrier.income.com.report.AdjustDecimal();
		Connection connection = null;
		//Connect con = new Connect();

		selectIndex = get32();
		to = get22();
		from = get12();
		setIndex_name11(selectIndex);
		
		
				// tabledata for index divisor
		if(Connect.con==null)
		{
			//System.out.println("Connecting using getDB ");
			con.getConnection();
			
		}
		PreparedStatement pst = null;
		if (from != null && to != null) {
			try {
				
				pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("index_divisor_date_wise1"));
				pst.setString(1, selectIndex);
				pst.setString(2, from);
				pst.setString(3, to);
				int ii = 0;

				ResultSet rst = pst.executeQuery();
				vExcel.clear();
				while (rst.next()) {
					if (rst.getString(1) == null) {
						tradingDate = "--";

					} else {
						tradingDate = rst.getString(1);

					}
					vExcel.add(ii, tradingDate);
					ii++;

					if (rst.getString(2) == null) {
						close = "0";

					} else {
						String strclose = (String) rst.getString(2);
						close = ad.indexcompose(strclose);

					}
					vExcel.add(ii, close);
					ii++;

					if (rst.getString(3) == null) {
						mCap = "0";

					} else {
						double mcv = (double) rst.getDouble(3);
						mcv = mcv / 1000000.0;
						String strmcv = new Double(mcv).toString();
						Logging.debug("Strmcv Value is " + strmcv);
						strmcv = ad.shareholdingpatt(strmcv);
						mCap = ad.indexcompose(strmcv);

					}
					vExcel.add(ii, mCap);
					ii++;

					if (rst.getString(4) == null) {
						divisor = "0";

					} else {
						double mcv = (double) rst.getDouble(4);
						String strmcv = new Double(mcv).toString();
						Logging.debug("Strmcv1 Value is  " + strmcv);
						strmcv = ad.shareholdingpatt(strmcv);
						divisor = ad.indexcompose(strmcv);

					}
					vExcel.add(ii, divisor);
					ii++;

					 im1=new indexMove(tradingDate,close,mCap,divisor, pe, pb,divYield);
					Pp.add(im1);

					//setVar_Table_data_vector(Table_data_vector);
					Table_data_vector.add(Pp);

					Logging.debug("VEXCEL Vector "+vExcel);
				}

				rst.close();
				pst.close();

			} catch (Exception e) {
				Logging.debug("Exception naresh2");
			//	e.printStackTrace();
				Logging.debug(e);
			}

			finally {
				try {
					if (connection != null)
						connection.close();
					connection = null;
				} catch (Exception ee) {
					try {
						if (connection != null)
							connection.close();
						connection = null;
					} catch (Exception ex) {
						Logging
								.error(" Error : Unable to close connection "
										+ ex.getMessage());
					}
					Logging.error(" Error : Unable to close connection "
							+ ee.getMessage());
				}
			}

		}

		//setVExcel(vExcel);
		tableData = Pp;
		//}
		return tableData;
	}

	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableData2(ArrayList tableData2) {
		this.tableData2 = tableData2;
	}

	
	public String get12() {
		return fromd2;
	}
	public void set12(String date2){
		fromd2=date2;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set12(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd2 = ft1.format(s1);
	}

	

	public String get14() {
		return fromd4;
	}
	public void set14(String date4){
		fromd4=date4;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set14(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd4 = ft1.format(s1);
	}

	public String get15() {
		return fromd5;
	}
	public void set15(String date5){
		fromd5=date5;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set15(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd5 = ft1.format(s1);
	}
	
	/**
	 * @return Returns the fdate.
	 */
	public String get16() {
		return fromd6;
	}
	public void set16(String date6){
		fromd6=date6;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set16(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd6 = ft1.format(s1);

	}
	
	/**
	 * @return Returns the fdate.
	 */
	public String get19() {
		return fromd9;
	}
	public void set19(String date6){
		fromd9=date6;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set19(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd9 = ft1.format(s1);

	}
	public String get110() {
		return fromd110;
	}
	public void set110(String date3){
		fromd110=date3;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set110(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd110 = ft1.format(s1);
	}
	
	public String get111() {
		return fromd111;
	}
	public void set111(String date3){
		fromd111=date3;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set111(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd111 = ft1.format(s1);
	}
	public String get112() {
		return fromd112;
	}
	public void set112(String date3){
		fromd112=date3;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set112(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd112 = ft1.format(s1);
	}
	public String get113() {
		return fromd113;
	}
	public void set113(String date3){
		fromd113=date3;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set113(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd113 = ft1.format(s1);
	}
	public String get114() {
		return fromd114;
	}
	public void set114(String date3){
		fromd114=date3;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set114(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd114 = ft1.format(s1);
	}
	public String get115() {
		return fromd115;
	}
	public void set115(String date3){
		fromd115=date3;
	}
	public String get116() {
		return fromd116;
	}
	public void set116(String date3){
		fromd116=date3;
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set116(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd116 = ft1.format(s1);
	}
	/**
	 * @param fdate The fdate to set.
	 */
	public void set115(int fdate11) {

		long newd = fdate11 * 24 * 60 * 60 * 1000L;
		java.util.Date s4 = new Date();
		long t2 = s4.getTime();
		long t1 = t2 - newd;
		Date s1 = new Date(t1);
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		fromd115 = ft1.format(s1);
	}
	

	/**
	 * @return Returns the pb.
	 */
	public String getPb() {
		return pb;
	}

	/**
	 * @param pb The pb to set.
	 */
	public void setPb(String pb) {
		this.pb = pb;
	}

	public String get22() {
		return tod2;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set22() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod2 = ft.format(curr);
	}
	public void set22(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod2 = ft.format(curr);
		tod2 = todate;
	}
	
	public String get24() {
		return tod4;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set24() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod4 = ft.format(curr);
	}
	public void set24(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod4 = ft.format(curr);
		tod4 = todate ;
	}
	public String get25() {
		return tod5;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set25() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod5 = ft.format(curr);
	}
	public void set25(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod5 = ft.format(curr);
		tod5 = todate ;
	}
	
	/**
	 * @return Returns the tdate.
	 */
	public String get26() {
		return tod6;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set26() {
						
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod6 = ft.format(curr);
	}
	
	public void set26(String todate){
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod1 = ft.format(todate);
		tod6=todate;	
	}
	public String get27() {
		return tod7;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set27() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod7 = ft.format(curr);
	}
	public void set27(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod3 = ft.format(curr);
		tod7 = todate;
	}
	
	public String get29() {
		return tod9;
	}
	/**
	 * @param tdate The tdate to set.
	 */
	public void set29() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod9 = ft.format(curr);
	}
	public void set29(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod2 = ft.format(curr);
		tod9 = todate;
	}
	
	
	public String get120() {
		return tod110;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set120() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod110 = ft.format(curr);
	}
	public void set120(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod4 = ft.format(curr);
		tod110 = todate ;
	}
	public String get121() {
		return tod111;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set121() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod111 = ft.format(curr);
	}
	public void set121(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod4 = ft.format(curr);
		tod111 = todate ;
	}
	public String get122() {
		return tod112;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set122() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod112 = ft.format(curr);
	}
	public void set122(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod5 = ft.format(curr);
		tod112 = todate ;
	}
	
	/**
	 * @return Returns the tdate.
	 */
	public String get123() {
		return tod113;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set123() {
						
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod113 = ft.format(curr);
	}
	
	public void set123(String todate){
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod1 = ft.format(todate);
		tod113=todate;	
	}
	public String get124() {
		return tod114;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set124() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod114 = ft.format(curr);
	}
	public void set124(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod3 = ft.format(curr);
		tod114 = todate;
	}
	
	public String get125() {
		return tod115;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set125() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod115 = ft.format(curr);
	}
	public void set125(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod3 = ft.format(curr);
		tod115 = todate;
	}
	public String get126() {
		return tod116;
	}

	/**
	 * @param tdate The tdate to set.
	 */
	public void set126() {
		Date curr = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		tod116 = ft.format(curr);
	}
	public void set126(String todate) {
		//Date curr = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		//tod3 = ft.format(curr);
		tod116 = todate;
	}
	
	/**
	 * @return Returns the radioButton.
	 */
	public String getRadioButton() {
		return radioButton;
	}

	/**
	 * @param radioButton The radioButton to set.
	 */
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}

	/**
	 * @return Returns the selectReportCollection.
	 */
	public Collection getSelectReportCollection() {
		Vector vec = new Vector();
		String local_id = getSelectUser();
		Connection connection = null;
		//vec.add(new LabelValueBean("Not Selected","0"));
		PreparedStatement stmt = null;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("select_*_from_preferencedetail1"));
			stmt.setString(1, local_id);
			ResultSet rst = stmt.executeQuery();
			while (rst.next()) {
				vec.add(new LabelValueBean(rst.getString(2), rst.getString(2)));

			}

			rst.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		Logging.debug("sizeeeeeeee of usercollection" + vec.size());
		selectReportCollection = vec;

		return selectReportCollection;
	}

	/**
	 * @param selectReportCollection The selectReportCollection to set.
	 */
	public void setSelectReportCollection(Collection selectReportCollection) {
		this.selectReportCollection = selectReportCollection;
	}

	/**
	 * @return Returns the selectReport.
	 */
	public String[] getSelectReport() {
		//System.out.println("selectReport.length====" + selectReport.length);
		return selectReport;
	}

	/**
	 * @param selectReport The selectReport to set.
	 */
	public void setSelectReport(String[] selectReport) {

		this.selectReport = selectReport;
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
	 * @return Returns the fin.
	 */
	public String getFin() {
		return fin;
	}

	/**
	 * @param fin The fin to set.
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}

	/**
	 * @return Returns the view.
	 */
	public String getView() {
		return view;
	}

	/**
	 * @param view The view to set.
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return Returns the checkradio.
	 */
	public String getCheckradio() {
		return checkradio;
	}

	/**
	 * @param checkradio The checkradio to set.
	 */
	public void setCheckradio(String checkradio) {
		this.checkradio = checkradio;
	}

	/* From Tabledata For Stock Details Report */
	/**
	 * @return Returns the tableDatas.
	 * TableData For Stock Detail Report
	 */
	public ArrayList getTableDatas() {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String local_id = "2";
		String stockidd = null;
		String exchange_id = null;
		String stocks[]=get45();
		stockidd = null;
		exchange_id = get35();
		
		from = get15();
		to = get25();
		//System.out.println("dddddddexchange_idddddddddddddd"+exchange_id);
		setIndex_name11(exchange_id);
		//System.out.println("dddddddddddddddddddd"+getIndex_name11());
		
		String stkName = null, openVal = null, closeVal = null, lowVal = null, highVal = null, trdVal = null, trdVol = null, noTrades = null, mcv = null, pDate = null;
		//Connect con = new Connect();

		Vector vec = new Vector();
		ArrayList tempDatas = new ArrayList();
		StockDetails stkDetails;

		if (Connect.con == null) {
			con.getConnection();
		}
		Logging.debug("stockidd+fromdate+todate" + stockidd + from + to);
		/*
		setIndex_name1(stockidd);
		String[] stockid3 = getIndex_name1();
		
		
			String stockid4 = stockid3[j];
		*/
		
			
		try {
				int i =0;
				for (int j = 0; j < stocks.length; j++) {

					stockidd = stocks[j];
					setStock_name(stockidd);
					String stockid4=stockidd;
					rst = con.highlowResult("stock_price_daily_between_date", stockid4,from, to);
					
				while (rst.next()) {

					if (rst.getString(2) == null) { // stock name
						stkName = "--";
					} else {
						stkName = rst.getString(2).toString();
					}
					vec.add(i, stkName);
					i++;

					if (rst.getString(3) == null) { // open val
						openVal = "0";
					} else {
						openVal = rst.getString(3).toString();
					}
					vec.add(i, openVal);
					i++;

					if (rst.getString(4) == null) { // close val
						closeVal = "0";
					} else {
						closeVal = rst.getString(4).toString();
					}
					vec.add(i, closeVal);
					i++;

					if (rst.getString(5) == null) { // low val
						lowVal = "0";
					} else {
						lowVal = rst.getString(5).toString();
					}
					vec.add(i, lowVal);
					i++;

					if (rst.getString(6) == null) { // high Val
						highVal = "0";
					} else {
						highVal = rst.getString(6).toString();
					}
					vec.add(i, highVal);
					i++;

					if (rst.getString(7) == null) { // Traded Volume
						trdVol = "0";
					} else {
						trdVol = rst.getString(7).toString();
					}
					vec.add(i, trdVol);
					i++;

					if (rst.getString(10) == null) { // Traded Value
						trdVal = "--";
					} else {
						trdVal = rst.getString(10).toString();
					}
					vec.add(i, trdVal);
					i++;

					if (rst.getString(9) == null) { // MCap mcv
						mcv = "--";
					} else {
						mcv = rst.getString(9).toString();
					}
					vec.add(i, mcv);
					i++;

					if (rst.getString(11) == null) { // No of trades
						noTrades = "0";
					} else {
						noTrades = rst.getString(11).toString();
					}
					vec.add(i, noTrades);
					i++;

					if (rst.getString(8) == null) { // Price date
						pDate = "--";
					} else {
						pDate = rst.getString(8).toString();
					}
					vec.add(i, pDate);
					i++;
					stkDetails = new StockDetails(stkName, openVal, closeVal,
							lowVal, highVal, trdVal, pDate, mcv, trdVol,
							noTrades);

					tempDatas.add(stkDetails);
				}
				}
				rst.close();

			} catch (SQLException sqlexp) {
				Logging.error("SQL Error : " + sqlexp.getMessage());
			} catch (Exception exp) {
				Logging.error(" Error : " + exp.getMessage());
			}

		//}
		Logging.debug(" tempData = " + tempDatas.size());
		tableDatas = tempDatas;
		vecStockDetails = vec;
		setVecStockDetails(vec);
		//setVector_highlowtable(vector_highlowtable);	

		return tableDatas;
	}

	/**
	 * @return Returns the vector_highlowtable.
	 */
	public Vector getVecStockDetails() {
		
		return vecStockDetails;
	}
	/**
	 * @param vector_highlowtable The vector_highlowtable to set.
	 */
	public void setVecStockDetails(Vector vecStockDetails) {
		Logging.debug(" inside setvecStockDetails");
		this.vecStockDetails = vecStockDetails;
		
	}
	/**
	 * @param tableDatas The tableDatas to set.
	 */
	public void setTableDatas(ArrayList tableDatas) {
		this.tableDatas = tableDatas;
	}

	/* Upto Tabledata For Stock Details Report */

	/* From Tabledata For Capital Change Report */
	/**
	 * @return Returns the tableDatac.
	 * Tabledata For Capital Change Report
	 */
	public ArrayList getTableDatac() {
		//Connect con= ConnectInit.getConnect();
		//PreparedStatement stmt = null;
		Connection connection = null;

		PreparedStatement pst = null;
		ResultSet rs = null;
		//ArrayList temp = new ArrayList();
		String local_id = getSelectUser();
		String id1 = null;
		String exchange_id = null;
		exchange_id = get56();
		if (exchange_id != null) {
			id1 = exchange_id;
			setExchange_name(id1);
			
			Logging.debug("id1" + id1);
		}
		from = get16();
		to = get26();
		
		try {
			if (connection == null) {
				connection = con.getdbConnection();
			}
			try {
				String stkId = null, stkName = null, faceVal = null, tis = null, mCap = null, iwf = null, CAName = null, date = null;

		//		org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
				AdjustDecimal ad = ConnectInit.getAdjustDecimal();
				Vector vec = new Vector();
				ArrayList tempData = new ArrayList();

				StockDetails stkDetails;

				if (Connect.con == null) {
					con.getConnection();
				}

				Logging.debug("id1from to" + id1 + from + to);
				rst = con.highlowResultmktStatus("capital_change_to_universe",
						id1, from, to);
				Logging
						.debug("set vector_capitalchangetouniv of capital change to universe");
				int i = 0;
				try {
					while (rst.next()) {
						Logging.debug(" Inside while: i=" + i);

						if (rst.getString(1) == null) { // stk_id
							stkId = "--";

						} else {
							stkId = rst.getString(1).toString();
						}
						vec.add(i, stkId);
						i++;

						if (rst.getString(2) == null) { //Stock Name
							stkName = "--";
						} else {
							stkName = rst.getString(2).toString();
						}
						vec.add(i, stkName);
						i++;

						if (rst.getString(3) == null) { // Face value
							faceVal = "--";
						} else {
							String fcVal = (String) rst.getString(3);
							fcVal = ad.indexcompose(fcVal);
							faceVal = fcVal;

						}
						vec.add(i, faceVal);
						i++;

						if (rst.getString(4) == null) { // TIS
							tis = "0.00";
						} else {
							tis = rst.getString(4).toString();
						}
						vec.add(i, tis);
						i++;

						if (rst.getString(5) == null) { // mCap
							mCap = "0";
						} else {
							String mVal = (String) rst.getString(5);
							mVal = ad.indexcompose(mVal);
							mCap = mVal;

						}
						vec.add(i, mCap);
						i++;

						if (rst.getString(6) == null) { // iwf
							iwf = "--";
						} else {
							iwf = rst.getString(6).toString();
						}
						vec.add(i, iwf);
						i++;

						if (rst.getString(8) == null) { // CA name
							CAName = "--";
						} else {
							CAName = rst.getString(8).toString();
						}
						vec.add(i, CAName);
						i++;

						if (rst.getString(7) == null) { // date
							date = "--";
						} else {
							date = rst.getString(7).toString();
						}
						vec.add(i, date);
						i++;
						Logging.debug("stk ID " + stkId + " stkName = "
								+ stkName + " faceVal = " + faceVal
								+ "\n tis= " + tis + " mCap= " + mCap
								+ " iwf= " + iwf + "CAName= " + CAName
								+ " date= " + date);
						stkDetails = new StockDetails(stkId, stkName, faceVal,
								tis, mCap, iwf, CAName, date);
						tempData.add(stkDetails);

					}
					Logging.debug("No of cols = " + tempData.size());
					rst.close();
					tableDatac = tempData;
					capitalChangeVec = vec;
					setCapitalChangeVec(vec);
				} catch (SQLException sqlexp) {
					Logging.error("Error : " + sqlexp.getMessage());
				}

			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception ee) {

			}
		}

		return tableDatac;
	}

	/**
	 * @param tableDatac The tableDatac to set.
	 */
	public void setTableDatac(ArrayList tableDatac) {
		this.tableDatac = tableDatac;
	}
	
	/* Upto Tabledata For Capital Change Report */

	/**
	 * @return Returns the capitalChangeVec.
	 */
	public Vector getCapitalChangeVec() {
		return capitalChangeVec;
	}

	/**
	 * @param capitalChangeVec The capitalChangeVec to set.
	 */
	public void setCapitalChangeVec(Vector capitalChangeVec) {
		this.capitalChangeVec = capitalChangeVec;
	}

	/**
	 * @return Returns the stockName.
	 */

	public String getStock_name(String stock_id) {
		//Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String local_id = stock_id;
		try {
			if (connection == null)
				connection = con.getdbConnection();
			try {

				String query = ConnectInit.queries
						.getProperty("select_stock_name_from_stock_master");
				//		select * from preferencedetail where preference_id\=?
				//System.out.println("Query = " + query);
				pst = connection.prepareStatement(query);

				try {
					Integer.parseInt(local_id);
					pst.setString(1, local_id);
					rs = pst.executeQuery();
					int i = 0;
					while (rs.next()) {
						stock_name1 = rs.getString("stock_name");

					}
				} catch (Exception e) {
					//System.out.println("problem--->" + e);
					StringTokenizer st = new StringTokenizer(local_id, ",");
					String stockNameList = "";
					int i = 0;
					while (st.hasMoreTokens()) {
						String stock = st.nextToken();
						pst.setString(1, stock);
						rs = pst.executeQuery();
						if (rs != null) {
							while (rs.next()) {
								if (i == 0) {
									stockNameList = stockNameList
											+ rs.getString("stock_name");
									i++;
								} else {
									stockNameList = stockNameList + ","
											+ rs.getString("stock_name");
								}

							}

						}

					}
					stock_name1 = stockNameList;

				}

			} catch (Exception e) {
				//		 TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}

		//		 Logging.getDebug("sizeeeeeeee of tabledata"+tableData.size());
		this.stock_name1 = stock_name1;
		return stock_name1;

	}

	public String getStockName() {
		Logging.debug(" inside getindExchName");
		try {
			String id = getSelectExchange();
			Enumeration e;
			String str;
			String iname = "";//,ival="";
			e = IndexNameHash.keys();
			while (e.hasMoreElements()) {
				str = (String) e.nextElement();
				iname = (String) IndexNameHash.get(str);
				if (str.equals(id)) {
					Logging.debug(" found !!!!");
					stockName = iname;
					break;
				}
			}
			Logging.debug(" indExchName = " + stockName);
		} catch (Exception e) {
			Logging.debug(" Error " + e.toString());
		}
		return stockName;
	}

	/**
	 * @param stockName The stockName to set.
	 */

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	
	/**
	 * @return Returns the StockcotriIndexchange.
	 */

	public ArrayList getStockcotriIndexchange() {

		Logging.debug("Inside Vector_stockcotriIndexchange");
		String index1 = "";
		String fodate = "";
		String todate = "";

		String local_id = getSelectUser();
		String id1 = null;
		String exchange_id = null;
		int days_diff = 0;

		vi = new Vector();
		String stockname = null, indexmarket = null, stockmarket = null, weightage = null;
		stockcotriIndexchange = new ArrayList();
		ArrayList tempdata = new ArrayList();
		StockContri stockcontri;
		try {
			Vector date = new Vector();
			//Connect con = new Connect();
			connection = con.getdbConnection();

			id1 = null;

			index1 = get39();
			setIndex_name11(index1);
			if (exchange_id != null) {
				id1 = exchange_id;
				Logging.debug("id1" + id1);
			}

			fodate = get19();

			todate = get29();

			rst = con.StockcontriIndexResult(
					"stock_contribution_to_change_in_index", index1, fodate,todate);
			int i = 0, q = 0;
			Logging.debug("setVector_stockcotriIndexchange");
			try {
				while (rst.next()) {
					Logging.debug("inside first while end " + rst);

					try {
						//Logging.getDebug("get tring "+rst.getString(1));
						if (rst.getString(1) == null) {
							stockname = "0";
							Logging.debug("after get");
						} else {
							stockname = (rst.getString(1).trim());
							vi.add(i, rst.getString(1));
						}
					} catch (Exception e) {
						Logging.debug("Error while returning resultset"
								+ e.getMessage());
					}

					i++;
					if (rst.getString(2) == null) {
						indexmarket = "0";
					} else {

						String str = rst.getString(2);
						String str2 = str.substring(str.indexOf(46), (str
								.indexOf(46) + 3));
						String str1 = str.substring(0, str.indexOf(46)) + str2;
						indexmarket = str1;
						vi.add(i, indexmarket);

					}
					i++;

					if (rst.getString(3) == null) {
						stockmarket = "0";
					} else {
						String str = rst.getString(3);
						String str2 = str.substring(str.indexOf(46), (str
								.indexOf(46) + 3));
						String str1 = str.substring(0, str.indexOf(46)) + str2;
						stockmarket = str1;
						vi.add(i, stockmarket);

					}
					i++;
					if (rst.getString(4) == null) {
						weightage = "0";
					} else {
						weightage = rst.getString(4);
						vi.add(i, rst.getString(4));

					}
					i++;
					date.add(q, rst.getString(5));

					q++;

					//Logging.getDebug((String)rst.getString(5));
					date.add(q, rst.getString(6));

					//Logging.getDebug((String)rst.getString(6));
					q++;

					stockcontri = new StockContri(stockname, indexmarket,
							stockmarket, weightage);
					tempdata.add(stockcontri);

				}
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :" + sqlexp.getMessage());
			}

			Logging.debug("" + stockcotriIndexchange.size());

			Logging.debug("After first while end");
			vector_remStockid = new Vector();
			if (date.size() > 1) {
				tdate = (String) date.get(0);
				fdate = (String) date.get(1);
			}
			Logging
					.debug("in bean" + "  " + fdate + " " + "  " + tdate);
	/*		ResultSet rst1 = new Connect().StockcontriSidlResult(
					"stock_contribution_stock_id_left", index1, fdate, tdate); */
			ResultSet rst1 = con.StockcontriSidlResult(
							"stock_contribution_stock_id_left", index1, fdate, tdate);
			int j = 0;
			while (rst1.next()) {
				vector_remStockid.add(j, rst1.getString(1));
			}
			if (vector_remStockid.size() != 0) {
				for (int k = 0; k < (vector_remStockid.size()); k++) {
					String s_id = (String) vector_remStockid.get(k);
					ResultSet rst2 = new Connect().stockcontriIndResult(
							"stock_contribution_to_change_in_index_individual",
							index1, s_id, todate, fodate);
					Logging.debug("setVector_stockcotriIndexchange");

					while (rst2.next()) {
						i++;
						if (rst.getString(1) == null) {
							stockname = "0";

						} else {
							stockname = (rst.getString(1).trim());

						}

						if (rst.getString(2) == null) {
							indexmarket = "0";
						} else {

							String str = rst.getString(2);
							String str2 = str.substring(str.indexOf(46), (str
									.indexOf(46) + 3));
							String str1 = str.substring(0, str.indexOf(46))
									+ str2;
							indexmarket = str1;
							//Logging.getDebug((String)rst.getString(2));
						}

						if (rst.getString(3) == null) {
							stockmarket = "0";
						} else {
							String str = rst.getString(3);
							String str2 = str.substring(str.indexOf(46), (str
									.indexOf(46) + 3));
							String str1 = str.substring(0, str.indexOf(46))
									+ str2;
							stockmarket = str1;
							//Logging.getDebug((String)rst.getString(3));
						}
						if (rst.getString(4) == null) {
							weightage = "0";
						} else {
							weightage = rst.getString(4);
							//Logging.getDebug((String)rst.getString(4));
						}
						//	stockcontri = new StockContri(stockname,indexmarket,stockmarket,weightage);
						//System.out.println("-----------------------------------"+ stockname);

					}
					rst.close();

				}
			}

		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :" + sqlexp.getMessage());
		}
		stockcotriIndexchange = tempdata;
		Logging.debug("SQL Error before return :"
				+ stockcotriIndexchange);
		setVi(vi);

		return stockcotriIndexchange;
	}
	
	
	/**
	 * @param vector_stockcotriIndexchange
	 *            The vector_stockcotriIndexchange to set.
	 */
	public void setStockcotriIndexchange(ArrayList stockcotriIndexchange) {
		this.stockcotriIndexchange = stockcotriIndexchange;
	}

	/*   Upto for Stock Contribution to Change Report*/

	/* From index compare report */

	/**
	 * @return Returns the d2.
	 */
	public String[] getD2() {
		return d2;
	}

	/**
	 * @param d2 The d2 to set.
	 */
	public void setD2(String[] d2) {
		this.d2 = d2;
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from The from to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return Returns the to.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to The to to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}


	/*  From Index returns and volatility */

	/**
	 * @return Returns the vector_index_rv.
	 */

	public ArrayList getFinal_Vector() {

		ArrayList arr = new ArrayList();
		from = get113();
		to = get123();
		indexList = get73();
		Logging.debug("Inside vector_indexList_rv");
		if (indexList != null)
			if (from != null && to != null) {
				try {
					//if(connection==null)					
					connection = con.getdbConnection();
					harrier.income.com.report.AdjustDecimal ad = new harrier.income.com.report.AdjustDecimal();
					final_Vector = new ArrayList();
					int j = 0;
					//Logging.getDebug(indexList.length+"  "+from+" "+"  "+to);
					// Logging.getDebug("IndexList length is "+indexList.length);
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
		setVExcel(vExcel);
		return final_Vector;
	}

	
	/**
	 * @param final_Vector
	 *            The final_Vector to set.
	 */
	public void setFinal_Vector(ArrayList final_Vector) {
		this.final_Vector = final_Vector;
	}

	public double getAvgDailyVolatility(Vector v, double indexmean) {

		try {

			Logging.debug("Inside getAvgDailyVolatility()");
			double sum_volatility = 0.0, vratio = 0.0, sum_indexvolatility = 0.0, sum_indexvalue = 0.0;
			Vector vol = new Vector();
			int i = 0, m = 0;
			indexmean = indexmean / (v.size() / 3);
			Logging.debug("indexmean " + indexmean);
			i = 0;

			while (i < v.size()) {
				i++;
				i++;
				double indval1 = (double) Double.parseDouble((String) v.get(i));
				Logging.debug("indval1 " + indval1 + " indexmean "
						+ indexmean);
				sum_indexvalue = (indval1 - indexmean);
				vol.add(new Double(sum_indexvalue).toString());
				Logging.debug("sum_indexvalue " + sum_indexvalue
						+ " sum_indexvolatility " + sum_indexvolatility);
				sum_indexvolatility = sum_indexvolatility + sum_indexvalue;
				Logging.debug(" sum_indexvolatility "
						+ sum_indexvolatility);
				i++;
			}

			i = 0;

			while (i < vol.size()) {
				double mult1 = ((double) Double
						.parseDouble((String) vol.get(i)) - sum_indexvolatility);
				Logging.debug(" mult1 " + mult1);
				double mult = (((double) Double
						.parseDouble((String) vol.get(i)) - sum_indexvolatility) * ((double) Double
						.parseDouble((String) vol.get(i)) - sum_indexvolatility));
				Logging.debug(" sum_volatility " + sum_volatility
						+ " mult " + mult);
				sum_volatility = sum_volatility + mult;
				i++;
			}
			vratio = 1.00 / (((vol.size())));
			Logging.debug(" sum_volatility " + sum_volatility);
			double avgdailyvol = (Math.sqrt((vratio * sum_volatility)));
			Logging.debug("vector size " + v.size()
					+ " avgdailyvol is " + avgdailyvol);
			return avgdailyvol;
		}

		finally {
			try {

			} catch (Exception e) {

			}
		}

	}

	public double getMonthlyReturns(Vector v) {
		Logging.debug("Inside getMonthlyReturns()");
		double mreturn = 0.0, lmr = 0.0, fmr = 0.0;
		int l = v.size();
		Logging.debug("Inside getMonthlyReturns()" + l);
		if (v.size() != 0) {
			lmr = (double) Double.parseDouble((String) v.get(l - 1));
			fmr = (double) Double.parseDouble((String) v.get(2));
		}
		Logging.debug(" l size " + l + " lmr " + lmr + " fmr" + fmr);
		if (fmr != 0.00) {
			mreturn = ((lmr - fmr) / fmr);
		} else {
			mreturn = 0.00;
		}
		Logging.debug("mreturn " + mreturn);
		return mreturn;
	}

	/*  Upto Index returns and volatility */

	/**
	 * @return Returns the indexName.
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * @param indexName The indexName to set.
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	
	/*  From for stock dividend report */

	/**
	 * @return Returns the tableDataSD.
	 */
	/*
	public ArrayList getTableDataSD1() {
		Logging.getDebug(" Inside getTableData");
		String stk_id=null, stk_name=null, face=null, tis = null, mcv=null, amt=null, date=null;
		
		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		
		Connection connection=null;
		StockDetails stkDetails;
		ArrayList tempData= new ArrayList();
		Vector vec = new Vector();
		int i=0;
		String filter1=get62();
		String selectIndExch1=get52();
		String selectIndExch2=get32();
		String from1=get12();
		String to1=get22();
		try {
			if(connection == null)
				connection = con.getdbConnection();
			
			if(filter1.equals("1")){  // Exchange wise
				Logging.getDebug(" Inside filter = 1(Exchange )");
				rst =con.changeInStockDetailResult("stock_divident_exchange_wise",selectIndExch1,from1,to1);
				
			}else {
				Logging.getDebug("Inside filter = 2 (Index) ");
				rst = con.changeInStockDetailResult("stock_divident_index_wise",selectIndExch2,from1,to1);
			}
			
			Logging.getDebug("rst in traded volume is "+rst);
			
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
				
			}
			tableData = tempData;
			stkDividentVec= vec;
			setStkDividentVec(vec);
			Logging.getDebug("size of arraylist "+tempData.size());
			return tableData;
		} catch (Exception sqlexp) {
			Logging.getError("Error : "+sqlexp.getMessage());
		}finally{
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

		return tableDataSD;
	}
	*/
	/**
	 * @param tableDataSD The tableDataSD to set.
	 */
	public void setTableDataSD(ArrayList tableDataSD) {
		this.tableDataSD = tableDataSD;
	}

	/**
	 * @return Returns the stkDividentVec.
	 */
	public Vector getStkDividentVec() {
		return stkDividentVec;
	}

	/**
	 * @param stkDividentVec The stkDividentVec to set.
	 */
	public void setStkDividentVec(Vector stkDividentVec) {
		this.stkDividentVec = stkDividentVec;
	}

	/*   for stock dividend report */
	

	/**
	 * @return Returns the tableDataSD.
	 */
	public ArrayList getTableDataSD() {
		Logging.debug(" Inside getTableDataSD");
		String stk_id=null, stk_name=null, face=null, tis = null, mcv=null, amt=null, date=null;
		
	//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		String Query=null;
		Connection connection=null;
		StockDetails stkDetails;
		ArrayList tempData= new ArrayList();
		Vector vec = new Vector();
		int i=0;
		String filter1=get61();
		try {
			if(connection == null)
			{
				connection = con.getdbConnection();
			}
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		
			from="";
			to="";
			selectIndExch="";
						
				PreparedStatement pst = null;
				ResultSet rs = null;
				//ArrayList temp = new ArrayList();
				String local_id = getSelectUser();
				
				String id1 = null;
				to=get125();
				from=get115();
			if(filter1.equals("1")){  // Exchange wise
				Logging.debug(" Inside filter = 1(Exchange )");
				Query = ConnectInit.queries.getProperty("stock_divident_exchange_wise");
				id1 = get155();
				
			}else {
				Logging.debug("Inside filter = 2 (Index) ");
				Query = ConnectInit.queries.getProperty("stock_divident_index_wise");
				id1 = get135();					
			}
			 pst = connection.prepareStatement(Query);
			 pst.setString(1, id1);
			 pst.setString(2, from);
			pst.setString(3, to);
			rst = pst.executeQuery();
			
			
			
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
				
			}
			tableDataSD = tempData;
			stkDividentVec= vec;
			Logging.debug("size of arraylist "+tempData.size());
			return tableDataSD;
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
		Logging.debug("tabledatasd"+tableDataSD);
		return tableDataSD;
	}
	/**
	 * @return Returns the tableDataTr.
	 */
	public ArrayList getTableDataTr() {
		Logging.debug(" Inside getTableDataTr");
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		String Query=null;
		String id1 = null;
		String stk_id=null, stk_name=null, trd_vol=null;
		Connection connection=null;
		StockDetails stkDetails;
		ArrayList tempData= new ArrayList();
		Vector vec=new Vector();
		int i=0;
		
		String filter1=get62();
		//setTraded_volume("1000");
		to=get124();
		from=get114();
		try {
			if(connection == null)
				connection = con.getdbConnection();
			
			selectIndExch="";
			
			PreparedStatement pst = null;
			
			if(filter1.equals("1")){  // Exchange wise
				Logging.debug(" Inside filter = 1(Exchange )");
			Query = ConnectInit.queries.getProperty("traded_volume_list_exchange_wise");
				id1 =get154();
				setExchange_name(id1);
			}else {
				Logging.debug("Inside filter = 2 (Index) ");
				Query = ConnectInit.queries.getProperty("traded_volume_list_index_wise");
				id1 =get134();
				setIndex_name11(id1);
			}
			
			
				
				Logging.debug(Query);
				pst = connection.prepareStatement(Query);
				pst.setString(1, id1);
				pst.setString(2, "1");
				pst.setString(3, from);
				pst.setString(4, to);
				rst = pst.executeQuery();	
				
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
					
				}
				
			
			tableDataTr = tempData;
			trdVolVec=vec;
			setTrdVolVec(vec);
			Logging.debug("size of arraylist "+tempData.size());
			return tableDataTr;
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
	return tableDataTr;
	}
	/**
	 * @return Returns the tableDataTr.
	 */
	/*
	public ArrayList getTableDataTr1() {
		Logging.getDebug(" Inside getTableData");
		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		//Logging.getDebug("Inside setVector_traded_volume exchange_id and indexid is "+exch_id+" , "+ind_id);

		String stk_id=null, stk_name=null, trd_vol=null;
		Connection connection=null;
		StockDetails stkDetails;
		ArrayList tempData= new ArrayList();
		Vector vec=new Vector();
		int i=0;
		String from1=get11();
		String to1=get21();
		String selectIndExch1=get51();
		String selectIndExch2=get31();
		String filter1= get61();
		traded_volume="50000";
		try {
			if(connection == null)
				connection = con.getdbConnection();
			
			if(filter1.equals("1")){  // Exchange wise
				Logging.getDebug(" Inside filter = 1(Exchange )");
				rst = con.tradedVolumeResult("traded_volume_list_exchange_wise",selectIndExch1,traded_volume,from1,to1);
			}else {
				Logging.getDebug("Inside filter = 2 (Index) ");
				rst = con.tradedVolumeResult("traded_volume_list_index_wise",selectIndExch2,traded_volume,from1,to1);
			}
			
			Logging.getDebug("rst in traded volume is "+rst);
			
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
				
			}
			tableData = tempData;
			trdVolVec=vec;
			Logging.getDebug("size of arraylist "+tempData.size());
			return tableData;
		} catch (Exception sqlexp) {
			Logging.getError("Error : "+sqlexp.getMessage());
		}finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ee){
				
				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return tableDataTr;
	}
	*/
	/**
	 * @param tableData The tableDataTr to set.
	 */
	public void setTableDataTr(ArrayList tableDataTr) {
		this.tableDataTr = tableDataTr;
	}
	/**
	 * @return Returns the trdVolVec.
	 */
	public Vector getTrdVolVec() {
		return trdVolVec;
	}

	/**
	 * @param trdVolVec The trdVolVec to set.
	 */
	public void setTrdVolVec(Vector trdVolVec) {
		this.trdVolVec = trdVolVec;
	}
	

	/**
	 * @return Returns the traded_volume.
	 */
	public String getTraded_volume() {
		return traded_volume;
	}

	/**
	 * @param traded_volume The traded_volume to set.
	 */
	public void setTraded_volume(String traded_volume) {
		this.traded_volume = traded_volume;
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

	/* Upto changes for Traded Volume */

	//for separate ids and convert it into String array
	public void setIndex_name1(String index_id) {

		StringTokenizer st = new StringTokenizer(index_id, ",");
		String indexList1[] = new String[5];
		int i = 0;

		while (st.hasMoreTokens()) {
			indexList1[i] = st.nextToken();
			i++;

		}

		indexList = indexList1;
	}

	public String[] getIndex_name1() {
		return indexList;
	}

	public String getIndex_name2() {
		return index_name2;
	}

	//		for separate ids and generate names for  it 
	public void setIndex_name2(String index_id) {
		Connect con1 = ConnectInit.getConnect();
		Connection connection1 = null;
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		String indname2 = null;
		String local_id = index_id;
		try {
			if (connection1 == null)
				connection1 = con1.getdbConnection();
			try {

				String query = ConnectInit.queries
						.getProperty("query_copy_indexmaster");
				//select * from preferencedetail where preference_id\=?
				//System.out.println("Query   = " + query);
				pst1 = connection1.prepareStatement(query);
				try {

					Integer.parseInt(local_id);
					pst1.setString(1, local_id);
					rs1 = pst1.executeQuery();
					while (rs1.next()) {
						indname2 = rs1.getString(2);
					}

				} catch (Exception e) {

					StringTokenizer st = new StringTokenizer(local_id, ",");
					String indexNameList = "";
					int i = 0;

					while (st.hasMoreTokens()) {
						String idx = st.nextToken();
						pst1.setString(1, idx);
						rs1 = pst1.executeQuery();
						if (rs1 != null) {
							while (rs1.next()) {
								if (i == 0) {
									indexNameList = indexNameList
											+ rs1.getString(2);
									i++;
								} else {
									indexNameList = indexNameList + ","
											+ rs1.getString(2);
								}

							}

						}

					}
					indname2 = indexNameList;
				}

			} catch (Exception e) {
				// TODO: handle exception
				Logging.error(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection1 != null)
					connection1.close();
			} catch (Exception ee) {
				try {
					if (connection1 != null)
						connection1.close();
				} catch (Exception ex) {
					Logging.error(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		this.index_name2 = indname2;
	}

	
	
	public String getIndex_name11() {
		return index_name11;
	}
	
	public void setIndex_name11(String index_id) {
		String local="";
		String local1="";
		local=index_id;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst =null;
		try {
			if(connection == null)
				connection = con.getdbConnection();
			
			stmt = connection.prepareStatement(ConnectInit.queries.getProperty("query_copy_indexmaster"));
			stmt.setString(1,index_id);		
			rst = stmt.executeQuery();
			while(rst.next()){
				
				local1=rst.getString(2);
			
			}
			
			rst.close();
			stmt.close();
			this.index_name11=local1;
			//System.out.println("rrrrrrrrrrrrr"+index_name11);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logging.debug(e);
		}	finally{
			try{
				if(connection != null)
					connection.close();
			}catch (Exception ee) {
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ex){
					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		this.index_name11=local1;
		
	}
	
	/*
	public void setIndex_name11(String index_id) {
		Connect con1 = ConnectInit.getConnect();
		Connection connection1 = null;
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		String indname11 = null;
		String local_id = index_id;
		try {
			if (connection1 == null)
				connection1 = con1.getdbConnection();
			try {

				String query = con1.queries.getProperty("query_copy_indexmaster");
				//select * from preferencedetail where preference_id\=?
				System.out.println("Query   = " + query);
				pst1 = connection1.prepareStatement(query);
				try {

					Integer.parseInt(local_id);
					pst1.setString(1, local_id);
					rs1 = pst1.executeQuery();
					while (rs1.next()) {
						indname11 = rs1.getString(2);
					}

				} catch (Exception e) {

					
				}

			} catch (Exception e) {
				// TODO: handle exception
				Logging.getError(" Error : " + e.getMessage());
			}
		} catch (Exception e) {
			Logging.getError(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection1 != null)
					connection1.close();
			} catch (Exception ee) {
				try {
					if (connection1 != null)
						connection1.close();
				} catch (Exception ex) {
					Logging.getError(" Error : Unable to close Connection "
							+ ex.getMessage());
				}
				Logging.getError(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		this.index_name11 = indname11;
	}
	*/
	
	//code for indexcompare OHLC

	public Vector getOhlcParam() {
		//		String index_id1=null,index_id2=null,index_id3=null;
		String fromdate = "";
		String toDate = "";
		Vector vec = new Vector();
		String[] s1=null;//{"",""};
		//Connection connection = null;
		//PreparedStatement pst = null;
		//ResultSet rs = null;
		//ArrayList temp = new ArrayList();
		//String local_id = getSelectUser();

		//String id1 = null;
		//		String exchange_id = null;
		
		fromdate = get111();

		toDate = get121();
		//id1 = get32();
		//setIndex_name1(id1);
		s1 = get72();//getIndex_name1();
		vec.add(0, fromdate);
		vec.add(1, toDate);
		vec.add(2, s1);
		this.ohlcParam = vec;

		return ohlcParam;
	}

	public void setOhlcParam() {

	}

	// from code for index corelation

	public Vector getCorelParam() {
		//		String index_id1=null,index_id2=null,index_id3=null;
		String fromdate = "";
		String toDate = "";
		Vector vect = new Vector();

		//Connection connection = null;
		//PreparedStatement pst = null;
		//ResultSet rs = null;
		//ArrayList temp = new ArrayList();
		//String local_id = getSelectUser();

		String id1 = null;
		//		String exchange_id = null;
		
		fromdate = get112();
		toDate = get122();
		
		String[] s1 = get74();
		vect.add(0, fromdate);
		vect.add(1, toDate);
		vect.add(2, s1);
		this.corelParam = vect;

		return corelParam;
	}

	public void setCorelParam() {

	}

	//Upto code for index corelation
	

	
	

	//	 code for index compare	
	public Vector getComParam() {
		//String index_id1=null,index_id2=null,index_id3=null;
		String fromdate = "";
		String toDate = "";
		Vector vect = new Vector();

		//Connection connection = null;
		//PreparedStatement pst = null;
		//ResultSet rs = null;
		//ArrayList temp = new ArrayList();
		//String local_id = getSelectUser();

		//String id1 = null;
		
		//id1 = get31();
		String[] s1 = get71();
		fromdate = get110();

		toDate = get120();

		//setIndex_name1(id1);
		//String[] s1 = getIndex_name1();
		vect.add(0, fromdate);
		vect.add(1, toDate);
		vect.add(2, s1);
		/*
		vect.add(2, s1[0]);
		vect.add(3, s1[1]);
		vect.add(4, s1[2]);
		vect.add(5, s1[3]);
		*/
		this.comParam = vect;

		return comParam;
	}

	/**
	 * @return Returns the dataCount.
	 */
	public String getDataCount() {
		return dataCount;
	}

	/**
	 * @param dataCount The dataCount to set.
	 */
	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}

	public String getCurrencyParam() {

		//Connection connection = null;
		//PreparedStatement pst = null;
		//ResultSet rs = null;

		//String local_id = getSelectUser();
		String id1 = null;

		id1 = get33();

		currencyParam = id1;

		return currencyParam;
	}
	 public ArrayList getDetails() {
		  	Connection connection=null;
		  	AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		  	Vector ve=new Vector();
		  	ResultSet rst=null;
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
			int i = 0;
			Double dd = new Double("0");
			Logging.debug("setIndex_details of Compose Index");
			String value=null,open=null,high=null,low=null,close=null,mcap=null;
			ArrayList tempdata=new ArrayList();
			//details = new ArrayList();
			DetailIndex detailindex;
			
	//		Connect con=new Connect();
			try {
				if(connection==null)
					connection=con.getdbConnection();
			
			/*if(Connect.con==null)
			{
				con.getConnection();
			}*/
			if((date1==null)||(date1.equals("null")))
			{
				rst = con.getLatestIndexDetails1("get_latest_all_index_details",l_date);
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
						ve.add(indexid);
					}
					
					
					if (rst.getString(1) == null) {					
						indexname= "0";
					} else {
					indexname=rst.getString(1);
			
					}
			
				
					if (rst.getString(2) == null) {
						current= "0";
					} else {
						current=rst.getString(2);
						current=ad.indexcompose(current);
						current=AdjustDecimal.ArrangeAsNumeric(current);
			
					}
			
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
					
			
					if (rst.getString(4) == null) {
						indexhigh= "0.00";
					} else {
						indexhigh=rst.getString(4);
						indexhigh=ad.indexcompose(indexhigh);
						indexhigh=AdjustDecimal.ArrangeAsNumeric(indexhigh);
					}	
					
					
					if (rst.getString(5) == null) {
						indexlow= "0.00";
					} else {
						indexlow=rst.getString(5);
						indexlow=ad.indexcompose(indexlow);
						indexlow=AdjustDecimal.ArrangeAsNumeric(indexlow);
						
					}
				
				
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
							
						} else {
							String temp1=rst.getString(6);
							if(temp1==null){
								temp1="0.00";
							}
							temp1=ad.indexcompose(temp1);
							temp1=AdjustDecimal.ArrangeAsNumeric(temp1);
							indexclosing= temp1	;
							
						}
					}
				
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
					
					if (rst.getString(10) == null) {
						currency= "0";
					} else {
						currency= rst.getString(10);
						}
					
				
					if (rst.getString(11) == null) {
						indexdate= "0";
					} else {
						indexdate=rst.getString(11);
						}
					
					
					Logging.debug("Vec Values =" + indexname +" : "+ indexid +" : "+current+" : "+ indexopen+" : "+indexhigh+" : "+indexlow+" : "+indexclosing+" : "+vachange+" : "+tmcv+" : "+divisor+" : "+currency+": "+indexdate+": "+indexclsv+": "+indextime);
					detailindex = new DetailIndex(indexname,indexid,current,status,indexopen,indexhigh,indexlow,indexclosing,vachange,tmcv,divisor,currency,indexdate,indexclsv,indextime);
					tempdata.add(detailindex);
				}
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
			Logging.debug("Return Index_details of size "+details.size());
			details=tempdata;
			
			return details;
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
	 
	 public ArrayList getIndexMovingTable() {
			ArrayList Table_data =new ArrayList();
			Vector Table_data_vector = new Vector();
			String tradingDate=null;
			String close=null;
			String mCap=null;
			String divisor=null;
			//String fodate =getMove_from();
			//String todate= getMove_to();
			String index= get136();
			String fodate =get116();
			String todate= get126();
			
	//		AdjustDecimal ad=new AdjustDecimal();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			try{
				if(connection==null)
					connection=null;
					connection=con.getdbConnection();
						if(fodate!=null && todate!=null){
								try {
						
									PreparedStatement pst;
									
									  pst = connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value1"));
						    		  pst.setString(1,index);
						              pst.setString(2,fodate);
						              pst.setString(3,todate);
						              ResultSet rst = pst.executeQuery(); 
						              vExcel106.clear();
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
														vExcel106.add(ii,tradingDate);
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
														vExcel106.add(ii,close);
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
														vExcel106.add(ii,mCap);
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
														vExcel106.add(ii,divisor);
														ii++;
														
								            		// Table_data.add(new IndexMovingDetail(tradingDate,close,mCap,divisor, pe, pb,divYield));
								            		Table_data.add(new IndexMovingDetail(tradingDate,close,mCap,divisor));
								            		
						              		}
						              	setVar_Table_data_vector(Table_data_vector);
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
			
		
			indexMovingTable = Table_data;
		
			return indexMovingTable;
		}
	 
	 	public Vector getVar_Table_data_vector() {
			return var_Table_data_vector;
		}

		public void setVar_Table_data_vector(Vector var_Table_data_vector) {
			this.var_Table_data_vector = var_Table_data_vector;
		}

		/**
		 * @return Returns the vExcel106.
		 */
		public Vector getVExcel106() {
			return vExcel106;
		}

		/**
		 * @param excel106 The vExcel106 to set.
		 */
		public void setVExcel106(Vector excel106) {
			vExcel106 = excel106;
		}
	   	
}

