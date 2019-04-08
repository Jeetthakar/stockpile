/*
 * Created on Nov 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import harrier.income.com.compute.CIndexCalculator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import app.CapturedIndexCollection;
import app.Connect;
import app.PopFileDialogNewStock;
import app.QueryClass1;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CapturedIndexForm extends ActionForm{
	static Logger Logging = Logger.getLogger(CapturedIndexForm.class);
	String indexID,	index_name, index_value,btn_add,check_flag,
	open_value, high_value, low_value,messagess=null,
	 closing_value, per_change, mkt_cap_value, divisor_value, currancy_name, date,b1,b2;
	Hashtable ht;
	String[] indid;
	Vector vt;
	ResultSet rst;
	public static Hashtable ind_nameList=new Hashtable();
	public static Hashtable ind_statusList=new Hashtable();
	public static double[] fiftytwo_min_max=new double[2]; 
//	static CIndexCalculator ICalculator=new CIndexCalculator();
	static Connect con1 = ConnectInit.getConnect();
	//Changes for the Static log which is deleted and new Logging.getDebug will be used at dated 2/8/2006 by ashish
	/**
	 * @return Returns the indexID.
	 */
	public String getIndexID() {
		return indexID;
	}
	
	/**
	 * @return Returns the closing_value.
	 */
	public String getClosing_value() {
		return closing_value;
	}
	/**
	 * @return Returns the currancy_name.
	 */
	public String getCurrancy_name() {
		return currancy_name;
	}
	/**
	 * @param currancy_name The currancy_name to set.
	 */
	public void setCurrancy_name(String currancy_name) {
		this.currancy_name = currancy_name;
	}
	/**
	 * @return Returns the date.
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date The date to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @return Returns the index_name.
	 */
	public String getIndex_name() {
		return index_name;
	}
	/**
	 * @param index_name The index_name to set.
	 */
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}
	/**
	 * @return Returns the divisor_value.
	 */
	public String getDivisor_value() {
		return divisor_value;
	}
	/**
	 * @return Returns the high_value.
	 */
	public String getHigh_value() {
		return high_value;
	}
	/**
	 * @return Returns the index_value.
	 */
	public String getIndex_value() {
		return index_value;
	}
	/**
	 * @return Returns the low_value.
	 */
	public String getLow_value() {
		return low_value;
	}
	/**
	 * @return Returns the mkt_cap_value.
	 */
	public String getMkt_cap_value() {
		return mkt_cap_value;
	}
	/**
	 * @return Returns the open_value.
	 */
	public String getOpen_value() {
		return open_value;
	}
	/**
	 * @return Returns the per_change.
	 */
	public String getPer_change() {
		return per_change;
	}
	public CapturedIndexForm(String id,String iname,String ivalue,String ovalue,String hvalue,String lvalue,String cvalue,
			String pchange,String mcvalue,String dvalue,String cname,String ldate) {
		this.indexID=id;	
		this.index_name=iname;
		this.index_value=ivalue;
		this.open_value=ovalue;
		this.high_value=hvalue;
		this.low_value=lvalue;
		this.closing_value=cvalue;
		this.per_change=pchange;
		this.mkt_cap_value=mcvalue;
		this.divisor_value=dvalue;
		this.currancy_name=cname;
		this.date=ldate;
	}	
	public CapturedIndexForm()
	{	
	}
	/**
	 * method to reset the captured index bean fields.
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		indexID="0";	
		 //index_name=null;
		 index_value="0.00";
		 open_value="0.00";
		 high_value="0.00";
		 low_value="0.00";
		 closing_value="0.00";
		 per_change="0.00";
		 mkt_cap_value="0.00";
		 divisor_value="0.00";
		 currancy_name=null;
		 date=null;
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
	 * @return Returns the b2.
	 */
	public String getB2() {
		return b2;
	}
	/**
	 * @param b2 The b2 to set.
	 */
	public void setB2(String b2) {
		this.b2 = b2;
	}
	/**
	 * @param closing_value The closing_value to set.
	 */
	public void setClosing_value(String closing_value) {
		this.closing_value = closing_value;
	}
	/**
	 * @param divisor_value The divisor_value to set.
	 */
	public void setDivisor_value(String divisor_value) {
		this.divisor_value = divisor_value;
	}
	/**
	 * @param high_value The high_value to set.
	 */
	public void setHigh_value(String high_value) {
		this.high_value = high_value;
	}
	/**
	 * @param index_value The index_value to set.
	 */
	public void setIndex_value(String index_value) {
		this.index_value = index_value;
	}
	/**
	 * @param indexID The indexID to set.
	 */
	public void setIndexID(String indexID) {
		this.indexID = indexID;
	}
	/**
	 * @param low_value The low_value to set.
	 */
	public void setLow_value(String low_value) {
		this.low_value = low_value;
	}
	/**
	 * @param mkt_cap_value The mkt_cap_value to set.
	 */
	public void setMkt_cap_value(String mkt_cap_value) {
		this.mkt_cap_value = mkt_cap_value;
	}
	/**
	 * @param open_value The open_value to set.
	 */
	public void setOpen_value(String open_value) {
		this.open_value = open_value;
	}
	/**
	 * @param per_change The per_change to set.
	 */
	public void setPer_change(String per_change) {
		this.per_change = per_change;
	}
	/**
	 * @return Returns the btn_add.
	 */
	public String getBtn_add() {
		return btn_add;
	}
	/**
	 * @param btn_add The btn_add to set.
	 */
	public void setBtn_add(String btn_add) {
		this.btn_add = btn_add;
	}
	/**
	 * method to validate field values for captured index.
	 */
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();
		setHastTables(request);	
		Logging.debug("ht.size(): "+ht.toString());	
		Logging.debug("vt.size(): "+vt.toString());
		indid=request.getParameterValues("indexID");
		String butoon_p=getB1();
		if(butoon_p.equals("Reset"))
		   {
			Logging.debug("reset butoon is call ");
		   }else
		   {
		addErrors(errors,indid);
		   }
		return errors;
	}
	/**
	 * Add the validation errors to the error object after validating all the field values for captured index.
	 */
	public void addErrors(ActionErrors errors,String[] indid){
		try{
			for(int hashcount=0;hashcount < vt.size();hashcount++){
				String indexid=(String)vt.get(hashcount);			
				CapturedIndexForm indexcomp = (CapturedIndexForm)ht.get(indexid);
				String ivalue=indexcomp.getIndex_value();
			    String ovalue=indexcomp.getOpen_value();
			   	String hvalue=indexcomp.getHigh_value();
			   	String lvalue=indexcomp.getLow_value();
			    String cvalue=indexcomp.getClosing_value();
			    //String pchange=indexcomp.getPer_change();
			    String mcvalue=indexcomp.getMkt_cap_value();
			   // String dvalue=indexcomp.getDivisor_value();
			    String cname=indexcomp.getCurrancy_name();
			   	String ldate=indexcomp.getDate();
			    if(ValidateNumber(ivalue)== false){
			    	errors.add(null,new ActionError("Error.message.IndexValue"));
			    }
			    //getDebug("validation 0");
			    if(QueryClass1.ValidateCurrancy(cname)== false)
			    {
			    	errors.add(null,new ActionError("Error.message.Currancy"));
			    }	
			    //getDebug("validation 1");
			    if(ValidateNumber(ovalue)==false)
			    {
			    	errors.add(null,new ActionError("Error.message.OpenValue"));
			    }
			    //getDebug("validation 3");
			    if(ValidateNumber(hvalue)==false)
			    {
			    	errors.add(null,new ActionError("Error.message.HighValue"));
			    }
			    //getDebug("validation 4");
			    if(ValidateNumber(lvalue)==false)
			    {
			    	errors.add(null,new ActionError("Error.message.LowValue"));
			    }
			    //getDebug("validation 5");
			    if(ValidateNumber(cvalue)==false)
			    {
			    	errors.add(null,new ActionError("Error.message.ClosingValue"));
			    }
			    //getDebug("validation 6");
			  //Commented by ashish because it is not been use in UI by the functionality
			   /* if(ValidateNumber(pchange)==false)
			    {
			    	errors.add(null,new ActionError("Error.message.PercentageChange"));
			    }*/
			    //getDebug("validation 7");
			    if(ValidateNumber(mcvalue)==false)
			    {
			    	errors.add(null,new ActionError("Error.message.MktCapValue"));
			    }
			    //getDebug("validation 8");
			  //Commented by ashish because it is not been use in UI by the functionality
			    /* if(ValidateNumber(dvalue)==false)
			    {
			    	errors.add(null,new ActionError("Error.message.DivisorValue"));			
			    }*/
			    //getDebug("validation 9");
			    //comented and remove from condition due to remove the latest index value from UI
			    //(ivalue.equals("0.00")) || ((ivalue.equals("0")))) ||  
			    if(((cvalue.equals("0.00")) || ((cvalue.equals("0")))) ||((lvalue.equals("0.00")) || ((lvalue.equals("0"))))
			    		|| ((ovalue.equals("0.00")) || ((ovalue.equals("0")))) || ((hvalue.equals("0.00")) || ((hvalue.equals("0")))) || ((mcvalue.equals("0.00")) || ((mcvalue.equals("0"))))){
					 	errors.add(null,new ActionError("Error.message.canNotInsertIndexValue"));	
				}
			    if(indid== null || indid.length==0){
			    	errors.add(null,new ActionError("Error.message.SelectIndex"));
			 	}
			}
			
		}catch(Exception e){
			Logging.error("Error while Validating :"+e.getMessage());
		}
	}
	/**
	 * method to validate for the number.
	 */
	private boolean ValidateNumber(String str)
	  {
		Logging.debug("Inside validate no"+str);
		if(str== null || str.trim().equals(""))
		   {	
			Logging.debug("inside black fields");
				return false;
		   }else{
				for(int i=0;i<str.length();i++)
				 {	
					if(Character.isLetter(str.charAt(i))){
						Logging.debug("inside is letter");
						return false;
					}
				 }
		   }
		  return true;
	 }
	/**
	 * method to validate string for a name.
	 */
	private boolean ValidateName(String str)
	  {
		if(str== null || str.trim().equals(""))
		   {
			return false;
		   }
		for(int i=0;i<str.length();i++)
		 {	
			if(!Character.isLetter(str.charAt(i)))
			 return false;
		 }
		  return true;
	 }
	public void setHastTables(HttpServletRequest request){
		String but_pressed=b1;		  
		   if(but_pressed.equals("Submit"))
		   {
			   Logging.debug("button pressed is submit");	
			   	String[] id1=request.getParameterValues("indexID");
			   	Logging.debug("In bean id1 is  : "+id1);
			   	if(id1==null || id1.length==0)
			   	{
			   		CapturedIndexCollection.addStocksInSourceTable(null,id1,request);
					ht=CapturedIndexCollection.table;
					vt=CapturedIndexCollection.v;
			   	}else{
					CapturedIndexCollection.addStocksInSourceTable("Submit",id1,request);
					ht=CapturedIndexCollection.table;
					vt=CapturedIndexCollection.v;
			   	}
		   }
		   if(but_pressed.equals("Reset"))
		   {	
				String[] id2=request.getParameterValues("indexID");
			   	CapturedIndexCollection.addStocksInSourceTable("Reset",id2,request);
			   	ht=CapturedIndexCollection.table;
				vt=CapturedIndexCollection.v;
		   }
		   		    	
	}
	public static void insert_in_IndValue(CapturedIndexForm form)
	{
		CapturedIndexForm form1 = (CapturedIndexForm)form;
		Connect connect = ConnectInit.getConnect();
		CIndexCalculator ICalculator = ConnectInit.getCIndexCalculator();
		Connection con1=null;
		if(con1 == null)
		{
			 con1 = connect.getdbConnection();	
		}
		try
		{
			String index_opening_value = form1.getOpen_value();
			String index_highest_value = form1.getHigh_value();
			String index_lowest_value  = form1.getLow_value();
			String index_closing_value = form1.getClosing_value();
			int index_id =getid(form1.getIndex_name()) ;
			String ind_id=new Integer(index_id).toString();
			String index_value_date = form1.getDate();
			String divisor = form1.getDivisor_value();
			String tmcv = form1.getMkt_cap_value();
			String index_value = form1.getClosing_value();
			String time=getTime();
			long tableid=0;
			String ind_date = form1.getDate();
			//ind_date=PopFileDialogNewStock.formatDate(ind_date);
			String query_insert_ind=ConnectInit.queries.getProperty("insert_into_intra_day_indices");
			String settlement="y";
			//getDebug(settlement);
			Logging.debug("before insert query in index value daily");
			
			//code for 52 week low and high from 
        	                 	
			double index_close=Double.parseDouble(index_closing_value);
        	fiftytwo_min_max =ICalculator.getFiftytwo_Week_HighLow(ind_id);
        	if(index_close > fiftytwo_min_max[0]){
        		fiftytwo_min_max[0]=index_close;
        	}
        	if(index_close < fiftytwo_min_max[1]){
        		fiftytwo_min_max[1]=index_close;
        	}
        	// code for 52 week low and high to
			PreparedStatement pst_preStat = con1.prepareStatement(ConnectInit.queries
					.getProperty("insert_into_index_value_daily"));
			pst_preStat.setDouble(1, Double.parseDouble(index_opening_value));
			pst_preStat.setDouble(2, Double.parseDouble(index_highest_value));
			pst_preStat.setDouble(3, Double.parseDouble(index_lowest_value));
			pst_preStat.setDouble(4, Double.parseDouble(index_closing_value));
			pst_preStat.setString(5, ind_id);
			pst_preStat.setString(6, ind_date);
			pst_preStat.setString(7, null);
			pst_preStat.setDouble(8, Double.parseDouble(divisor));
			pst_preStat.setDouble(9, Double.parseDouble(tmcv));
			pst_preStat.setDouble(10,Double.parseDouble(tmcv));
			pst_preStat.setDouble(11,Double.parseDouble(divisor));
			pst_preStat.setDouble(12,fiftytwo_min_max[0]);
			pst_preStat.setDouble(13,fiftytwo_min_max[1]);
			
			pst_preStat.executeUpdate();//execute query
			pst_preStat.close();
		} catch (SQLException ae) {
			Logging.error("ERROR: "+ae.getMessage());		
		}
		finally{
			try{if(con1!=null)
				con1.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	
	public static void insert_in_IntraDay(CapturedIndexForm form,String query)
	{
		CapturedIndexForm form1 = (CapturedIndexForm)form;
		Connect connect = ConnectInit.getConnect();
		Connection con1=null;
		if(con1 == null)
		{
			 con1 = connect.getdbConnection();	
		}
		String time=getTime();
		int index_id =getid(form1.getIndex_name()) ;
		String ind_id=new Integer(index_id).toString();
		String ind_date = form1.getDate();
		//ind_date=PopFileDialogNewStock.formatDate(ind_date);
		try
		{
			PreparedStatement intra = con1.prepareStatement(query);
			Logging.debug("query is ......."+query);			
			/*intra.setLong(1, tableid);*/
			intra.setString(4,ind_id );
			intra.setDouble(5, Double.parseDouble(form1.getMkt_cap_value()));
			intra.setDouble(1, Double.parseDouble(form1.getIndex_value()));
			intra.setString(2, ind_date);			
			intra.setString(3, time);			
			intra.executeUpdate();//execute query
			intra.close();
		}catch(Exception e){
			Logging.error("error in insert...2.."+e);
		}
		finally{
			try{if(con1!=null)
				con1.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		
	}
	
	public static void update_in_value(CapturedIndexForm form,String query,int update_case)
	{
		CapturedIndexForm form1 = (CapturedIndexForm)form;
		String ind_date = form1.getDate();
		ind_date=PopFileDialogNewStock.formatDate(ind_date);
		Connect connect = ConnectInit.getConnect();
		Connection con1=null;
		if(con1 == null)
		{
			 con1 = connect.getdbConnection();	
		}
		try
		{
			PreparedStatement intra = con1.prepareStatement(query);
			Logging.debug("......."+update_case);
			int index_id =getid(form1.getIndex_name()) ;
			String ind_id=new Integer(index_id).toString();
			Logging.debug("id is......."+ind_id);
			switch(update_case)
			{
			case 11:
				intra.setString(1,form1.getHigh_value());
				intra.setString(2,form1.getDivisor_value());
				intra.setString(3,form1.getMkt_cap_value());
				intra.setString(4,form1.getOpen_value());
				intra.setString(5,ind_id);
				intra.setString(6,ind_date);
				break;
			case 12:
				intra.setString(1,form1.getHigh_value());
				intra.setString(2,form1.getDivisor_value());
				intra.setString(3,form1.getMkt_cap_value());
				intra.setString(4,form1.getClosing_value());
				intra.setString(5,ind_id);
				intra.setString(6,ind_date);
				break;
			case 13:
				intra.setString(1,form1.getHigh_value());
				intra.setString(2,form1.getDivisor_value());
				intra.setString(3,form1.getMkt_cap_value());
				intra.setString(4,ind_id);
				intra.setString(5,ind_date);
				break;
			case 21:
				intra.setString(1,form1.getLow_value());
				intra.setString(2,form1.getDivisor_value());
				intra.setString(3,form1.getMkt_cap_value());
				intra.setString(4,form1.getOpen_value());
				intra.setString(5,ind_id);
				intra.setString(6,ind_date);
				break;
			case 22:
				intra.setString(1,form1.getLow_value());
				intra.setString(2,form1.getDivisor_value());
				intra.setString(3,form1.getMkt_cap_value());
				intra.setString(4,form1.getClosing_value());
				intra.setString(5,ind_id);
				intra.setString(6,ind_date);
				break;
			case 23:
				intra.setString(1,form1.getLow_value());
				intra.setString(2,form1.getDivisor_value());
				intra.setString(3,form1.getMkt_cap_value());
				intra.setString(4,ind_id);
				intra.setString(5,ind_date);
				break;
			}
			intra.executeUpdate();
			intra.close();
		}catch(Exception e){
			Logging.error("error in insert..3..."+e);
		}
		finally{
			try{if(con1!=null)
				con1.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	/**
	 * method to get index_id if the index_name is passed to it.
	 */
	public static int getid(String name)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con1=null;
		if(con1 == null)
		{
			 con1 = connect.getdbConnection();	
		}
		ResultSet rst_id;
		int ids = 0;
		try
		{
			PreparedStatement pseq = con1.prepareStatement(ConnectInit.queries
					.getProperty("get_index_id_for_index_name"));
			pseq.setString(1,name);
			rst_id = pseq.executeQuery();
			while(rst_id.next())
			{
				ids = Integer.parseInt(rst_id.getString(1));
				Logging.debug("value of id.........:"+ids);
			}
			rst_id.close();
			pseq.close();
		}catch(Exception em)
		{
			Logging.error("Error  :"+em.getMessage());
		}
		finally{
			try{if(con1!=null)
				con1.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	return ids;
	}
	/**
	 * method to get the system's current time.
	 */
	public static String getTime() {
		java.util.Date dt = new java.util.Date();
		dt.getDate();
		return dt.toString().split(" ")[3];
	}

	/**
	 * @return the check_flag
	 */
	public String getCheck_flag() {
		return check_flag;
	}

	/**
	 * @param check_flag the check_flag to set
	 */
	public void setCheck_flag(String check_flag) {
		this.check_flag = check_flag;
	}
	public StringBuffer messages(){
		StringBuffer buffer=new StringBuffer();
		if(messagess==null)
		{
			return buffer;
		}else{
	try
	{
		String status = null;
		String indname=null;
		String key=null;
		int counter=0;
		String message;
		Enumeration e = ind_nameList.keys();	
		Logging.debug("table size :"+ind_nameList.size());
		while(e.hasMoreElements()){
		counter++;
		indname=null;
		status=null;
		key=null;
		key = (String)e.nextElement();
		indname = (String)ind_nameList.get(key);
		status =(String)ind_statusList.get(key);
		buffer.append("<table>");
		if(status.equals("0")){
		String update=	"   Update Successfully";
		message=indname+"  "+update;
		buffer.append("<tr><td nowrap='nowrap'><font size='2' face='Arial' color='blue'>" +message+ "</font></td></tr>");
		}
		if(status.equals("2")){
			String insert=	"  Insert Successfully ";
			message=indname+"   "+insert;			
			buffer.append("<tr><td nowrap='nowrap'><font size='2' face='Arial' color='blue'>" +message+ "</font></td></tr>");
		}
		if(status.equals("1")){
			String err="  Error in Updating or Insert Stock";
			message=indname+"  "+err;		
			buffer.append("<tr><td nowrap='nowrap'><font size='2' face='Arial' color='red'>" +message+ "</font></td></tr>");
		}
		}
		buffer.append("</table>");
		ind_nameList.clear();
		ind_statusList.clear();
	}catch(Exception e){
	Logging.error("Error while Validating :"+e.getMessage());
	}
	}
	messagess=null;
	return buffer;
	}

	/**
	 * @return the messagess
	 */
	public String getMessagess() {
		return messagess;
	}

	/**
	 * @param messagess the messagess to set
	 */
	public void setMessagess(String messagess) {
		this.messagess = messagess;
	}
	
}
