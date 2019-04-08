package harrier.income.com.fixedincome;

/*
 * @author Manoj Adekar
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.Connect;
import app.StockResult;
import app.stocklist;

import com.harrier.initializeation.ConnectInit;
/**
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StockMasterBondsCommodities extends ActionForm{
	Logger Logging = Logger.getLogger(StockMasterBondsCommodities.class);
	String s_stockID, 
	s_stockName,
	d_listingDate,
	s_stockType,
	d_iwf,
	s_companyName,
	f_issuedShares,
	s_stockExchange_null,
	s_countryName,
	f_faceValue,
	s_ratingCode,
	d_paidValue,
	newissues_but,
	s_marketLot,
	f_alertPercent,
	f_rejectionPercent,
	s_stockCurrency,
	b_isPriceForLot,
	s_growthValueType=null,
	b_isActive,
	b_indentifier,
	s_indentifier,
	b_sdl,
	s_sdl,
	b_ric,
	b_isn,
	
	b_exc_new_code,
	b_csp,
	b_new,
	b_accpt,
	success,
	b1,b2,
	exc_id,
	newIssue,
	series,b_crisil,adr_gdr_id;
	String parent_id,isssuesize,issuedate,delistingdate;
	private String[] Rem=null;
	static String b_tkr,b_exc_code;
	stocklist sl;
	static String s_stockExchange;
	String exp_date , deliverycenter , measure ;
	String  varify="clean";
	boolean bodycheck=false;
	boolean trans_flag=false;	
	private Collection stockTypeCollection =	null;
	
	private Collection companyListCollection =	null;
	private Collection stockExcListCollection=null;
	
	private Collection countryListCollection=null;
	private Collection ratingCodeListCollection=null;
	private Collection currencyListCollection=null;
	
	private Collection is_price_for_lotCollection=null;

	
	private static Vector defaultvalues=new Vector();;
	Connection connection=null;
	
	String start_date=null,
	   maturity_date=null,
	   coupon_percentage=null,
	   coupon_period=null,
	   coupon_payment_dates=null,
	   interest_basis_month=null,
	   interest_basis_year=null,
	   accrued_interest=null,
	   description=null,
	   commit_button=null,
	   success_flag=null,
	   check_flag=null;
	  
	
	
	/**
	 * To set the bean values  with stock detailsfor the existing stock. 
	 */
public void setStockDetail(String stockid){		
	
	if(s_stockID!=null){
		Connection connection = null;
		Connect c = ConnectInit.getConnect();
		StockBondsResults sbr=new StockBondsResults();
		
		Vector vidcode=new Vector();
		try
		{
			vidcode.clear();   
			if(connection == null)
			{
				connection = c.getdbConnection();
			}
			StockBondsResults.getIdentifierCode_fistkid(s_stockID);   
			
		    Logging.debug("vector size is "+(StockBondsResults.identifier_list).size());      		 
			sbr.setIdentifierCodeBelogsTo((String)StockBondsResults.identifier_list.get(0),(String)StockBondsResults.identifier_list.get(1),(String)StockBondsResults.identifier_list.get(2),(String)StockBondsResults.identifier_list.get(3),(String)StockBondsResults.identifier_list.get(4),(String)StockBondsResults.identifier_list.get(5),(String)StockBondsResults.identifier_list.get(6),s_stockID );
			ResultSet  rs = StockBondsResults.resultStockBonds(connection,s_stockID);   
			Logging.debug("before rs.next()"+rs.next());
			
			s_stockName=rs.getString("stock_name");
			d_listingDate=rs.getString("listing_date");
			s_stockType=rs.getString("stock_type_id");  
			d_iwf=rs.getString("iwf");
			s_companyName=rs.getString("company_id");
			f_issuedShares=rs.getString("tis");
			s_stockExchange=rs.getString("stock_exchange_id");
			s_countryName=rs.getString("country_id");
			f_faceValue=rs.getString("face_value");
			d_paidValue=rs.getString("paid_value");
			s_marketLot =rs.getString("market_lot");
			f_alertPercent = rs.getString("alert_percentage");
            f_rejectionPercent =rs.getString("rejection_percentage");
            issuedate=rs.getString("issue_date");
            isssuesize=rs.getString("issue_size");
			s_stockCurrency =rs.getString("stock_currency_id");
			b_isPriceForLot =rs.getString("is_price_for_lot");
			start_date=rs.getString("start_date");
			maturity_date=rs.getString("maturity_date");
			coupon_percentage=rs.getString("coupon_percentage");
			coupon_period=rs.getString("coupon_period");
			interest_basis_month=rs.getString("interest_rate_daysinmonth");
			interest_basis_year=rs.getString("interest_rate_daysinyear");
			delistingdate=rs.getString("delisting_date");
			description=rs.getString("description");
			
				
			if((rs.getString("is_active")).equals("y")){
				b_isActive ="on";
			}else{
				b_isActive =null;
			} 
			
			b_sdl =(String)StockBondsResults.identifier_list.get(0).toString().trim();
			b_isn =(String)StockBondsResults.identifier_list.get(1).toString().trim();
			b_ric =(String)StockBondsResults.identifier_list.get(2).toString().trim();	
			b_crisil=(String)StockBondsResults.identifier_list.get(3).toString().trim();
			b_csp =(String)StockBondsResults.identifier_list.get(4).toString().trim();
			b_exc_code =(String)StockBondsResults.identifier_list.get(5).toString().trim();
			b_tkr =	(String)StockBondsResults.identifier_list.get(6).toString().trim();					
			
		}
		catch(Exception e)
		{
			Logging.error(" Error : "+e.getMessage());
		}
		finally
		{
			try
			{
				if(connection!=null)
					connection.close();
			}
			catch(Exception ee)
			{
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
}

	/**
	 * @return Returns the trans_flag.
	 */
	public boolean isTrans_flag() {
		return trans_flag;
	}
	/**
	 * @param trans_flag The trans_flag to set.
	 */
	public void setTrans_flag(boolean trans_flag) {
		this.trans_flag = trans_flag;
	}

	/**
	 * @return Returns the b_crisil.
	 */
	public String getB_crisil() {
		return b_crisil;
	}
	
		
	/**
	 * @return Returns the check_flag.
	 */
	public String getCheck_flag() {
		return check_flag;
	}
	/**
	 * @param check_flag The check_flag to set.
	 */
	public void setCheck_flag(String check_flag) {
		this.check_flag = check_flag;
	}
	/**
	 * @return Returns the varify.
	 */
	public String getVarify() {
		return varify;
	}
	/**
	 * @param varify The varify to set.
	 */
	public void setVarify(String varify) {
		this.varify = varify;
	}
	/**
	 * @return Returns the measure.
	 */
	public String getMeasure() {
		return measure;
	}
	/**
	 * @param measure The measure to set.
	 */
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	/**
	 * @return Returns the deliverycenter.
	 */
	public String getDeliverycenter() {
		return deliverycenter;
	}
	/**
	 * @param deliverycenter The deliverycenter to set.
	 */
	public void setDeliverycenter(String deliverycenter) {
		this.deliverycenter = deliverycenter;
	}
	/**
	 * @return Returns the exp_date.
	 */
	public String getExp_date() {
		return exp_date;
	}
	/**
	 * @param exp_date The exp_date to set.
	 */
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	/**
	 * @param b_crisil The b_crisil to set.
	 */
	public void setB_crisil(String b_crisil) {
		this.b_crisil = b_crisil;
	}
	/**
	 * @return Returns the b_indentifier.
	 */
	public String getB_indentifier() {
		return b_indentifier;
	}
	/**
	 * @param b_indentifier The b_indentifier to set.
	 */
	public void setB_indentifier(String b_indentifier) {
		this.b_indentifier = b_indentifier;
	}
	
	public String getB_sdl() {
		return b_sdl;
	}
	
	public void setB_sdl(String b_sdl) {
		this.b_sdl = b_sdl;
	}
	
	public String getB_exc_code() {
		Logging.debug("inside getter of bexccode"+b_exc_code);
		return b_exc_code;
	}
	
	public void setB_exc_code(String b_exc_code) {
		Logging.debug("inside setter of bexccode"+b_exc_code);
		this.b_exc_code = b_exc_code;
	}
	
	public String getB_exc_new_code() {
		Logging.debug("inside getter of bexcnewnewcode"+b_exc_new_code);
		return b_exc_new_code;
	}
	
	public void setB_exc_new_code(String b_exc_new_code) {
		Logging.debug("inside setter of bexcnewnewcode"+b_exc_new_code);
		this.b_exc_new_code = b_exc_new_code;
	}
	
	public String getB_isn() {
		return b_isn;
	}
	
	public void setB_isn(String b_isn) {
		this.b_isn = b_isn;
	}
	
	public String getB_ric() {
		return b_ric;
	}
	
	public void setB_ric(String b_ric) {
		this.b_ric = b_ric;
	}
	
	public String getB_csp() {
		return b_csp;
	}
	
	public void setB_csp(String b_csp) {
		this.b_csp = b_csp;
	}

	public String getS_sdl() {
		return s_sdl;
	}
	
	public void setS_sdl(String s_sdl) {
		this.s_sdl = s_sdl;
	}
	
	/**
	 * @return Returns the s_indentifier.
	 */
	public String getS_indentifier() {
		return s_indentifier;
	}
	/**
	 * @param s_indentifier The s_indentifier to set.
	 */
	public void setS_indentifier(String s_indentifier) {
		this.s_indentifier = s_indentifier;
	}
	
	/**
	 * @return Returns the newIssue.
	 */
	public String getNewIssue() {		
		return newIssue;
	}
	/**
	 * @param newIssue The newIssue to set.
	 */
	public void setNewIssue(String newIssue) {
		this.newIssue = newIssue;
	}
	
	/**
	 * @return Returns the b_isActive.
	 */
	public String getB_isActive() {
		Logging.debug(" b_isActive is "+b_isActive);
		Logging.debug("s_stockID is "+s_stockID+" b1 is "+b1);
		if(s_stockID==null){
			if(b1==null){
				b_isActive="y";
			}else{
				if(b_isActive==null){
					b_isActive=null;
				}else{			
					b_isActive="y";
				}
			}
		}else{
			if(b_isActive==null || b_isActive.equals("n") ){
				b_isActive=null;
			}else{			
				b_isActive="y";
			}
		}
		Logging.debug(" b_isActive is "+b_isActive);
		return b_isActive;
	}
	/**
	 * @param active The b_isActive to set.
	 */
	public void setB_isActive(String active) {
		Logging.debug("setB_isActive "+active);
		b_isActive = active;
	}
	/**
	 * @return Returns the b_isPriceForLot.
	 */
	public String getB_isPriceForLot() {
		if(b_isPriceForLot==null)
			b_isPriceForLot="y";
		return b_isPriceForLot;
	}
	/**
	 * @param priceForLot The b_isPriceForLot to set.
	 */
	public void setB_isPriceForLot(String priceForLot) {
		b_isPriceForLot = priceForLot;
	}
	/**
	 * @return Returns the d_iwf.
	 */
	public String getD_iwf() {
		return d_iwf;
	}
	/**
	 * @param d_iwf The d_iwf to set.
	 */
	public void setD_iwf(String d_iwf) {
		//this.d_iwf ="1"; 
		this.d_iwf = d_iwf;
		//d_iwf;
	}
	/**
	 * @return Returns the d_listingDate.
	 */
	public String getD_listingDate() {
		SimpleDateFormat dfr = new SimpleDateFormat("dd-MM-yyyy");
		Date dt = new Date();
		String date = dfr.format(dt).toString(); 
		if(d_listingDate==null)
			d_listingDate=date;
		return d_listingDate;
	}
	/**
	 * @param date The d_listingDate to set.
	 */
	public void setD_listingDate(String date) {
		d_listingDate = date;
	}
	/**
	 * @return Returns the d_paidValue.
	 */
	public String getD_paidValue() {
		return d_paidValue;
	}
	/**
	 * @param value The d_paidValue to set.
	 */
	public void setD_paidValue(String value) {
		d_paidValue = value;
	}
	/**
	 * @return Returns the f_alertPercent.
	 */
	public String getF_alertPercent() {
		return f_alertPercent;
	}
	/**
	 * @param percent The f_alertPercent to set.
	 */
	public void setF_alertPercent(String percent) {
		f_alertPercent = percent;
	}
	/**
	 * @return Returns the f_faceValue.
	 */
	public String getF_faceValue() {
		return f_faceValue;
	}
	/**
	 * @param value The f_faceValue to set.
	 */
	public void setF_faceValue(String value) {
		f_faceValue = value;
	}
	/**
	 * @return Returns the f_issuedShares.
	 */
	public String getF_issuedShares() {
		return f_issuedShares;
	}
	/**
	 * @param shares The f_issuedShares to set.
	 */
	public void setF_issuedShares(String shares) {
		f_issuedShares = shares;
	}
	/**
	 * @return Returns the f_rejectionPercent.
	 */
	public String getF_rejectionPercent() {
		return f_rejectionPercent;
	}
	/**
	 * @param percent The f_rejectionPercent to set.
	 */
	public void setF_rejectionPercent(String percent) {
		f_rejectionPercent = percent;
	}
	
	/**
	 * @return Returns the s_companyName.
	 */
	public String getS_companyName() {
		if(s_companyName==null || s_companyName.equals("")){
			s_companyName="";
		}else{
			s_companyName=s_companyName.replaceAll("'","\'");
		}
		return s_companyName;
	}
	/**
	 * @param name The s_companyName to set.
	 */
	public void setS_companyName(String name) {
		s_companyName = name;
	}
	/**
	 * @return Returns the s_countryName.
	 */
	public String getS_countryName() {
		Logging.debug("in country name getter "+defaultvalues.size());
		/*if(s_stockID!=null && s_countryName==null){
			s_countryName=(String)defaultvalues.get(1);
		}*/
		Logging.debug(" country name is "+s_countryName);
		return s_countryName;
	}
	/**
	 * @param name The s_countryName to set.
	 */
	public void setS_countryName(String name) {
		s_countryName = name;
	}
	/**
	 * @return Returns the s_growthValueType.
	 */
	public String getS_growthValueType() {
		Logging.debug("in growth or value "+s_growthValueType);
		return s_growthValueType;
	}
	/**
	 * @param valueType The s_growthValueType to set.
	 */
	public void setS_growthValueType(String valueType) {
		s_growthValueType = valueType;
	}
		/**
	 * @return Returns the s_marketLot.
	 */
	public String getS_marketLot() {
		return s_marketLot;
	}
	/**
	 * @param lot The s_marketLot to set.
	 */
	public void setS_marketLot(String lot) {
		s_marketLot = lot;
	}
	/**
	 * @return Returns the s_ratingCode.
	 */
	public String getS_ratingCode() {
		return s_ratingCode;
	}
	/**
	 * @param code The s_ratingCode to set.
	 */
	public void setS_ratingCode(String code) {
		s_ratingCode = code;
	}
	/**
	 * @return Returns the s_stockCurrency.
	 */
	public String getS_stockCurrency() {
		Logging.debug("in courency name getter "+defaultvalues.size());
		Logging.debug("in courency name getter "+s_stockCurrency);
		return s_stockCurrency;
	}
	/**
	 * @param currency The s_stockCurrency to set.
	 */
	public void setS_stockCurrency(String currency) {
		s_stockCurrency = currency;
	}
	/**
	 * @return Returns the s_stockExchange.
	 */
	public String getS_stockExchange() {
		Logging.debug("in s_stockExchange getter "+defaultvalues.size());
		Logging.debug(" s_stockExchange is "+s_stockExchange+" s_stockExchange_null is "+s_stockExchange_null);
		
		return s_stockExchange;
	}
	/**
	 * @param exchange The s_stockExchange to set.
	 */
	public void setS_stockExchange(String exchange) {
		s_stockExchange = exchange;
	}
	/**
	 * @return Returns the s_stockName.
	 */
	public String getS_stockName() {
		if(s_stockName==null){
			s_stockName="";
		}else{
			s_stockName=s_stockName.replaceAll("'","\'");
		}
		return s_stockName;
	}
	/**
	 * @param name The s_stockName to set.
	 */
	public void setS_stockName(String name) {
		s_stockName = name;
	}
	/**
	 * @return Returns the s_stockType.
	 */
	public String getS_stockType() {
		return s_stockType;
	}
	/**
	 * @param type The s_stockType to set.
	 */
	public void setS_stockType(String type) {
		s_stockType = type;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		s_stockID = null;
		s_stockName =null;
		d_listingDate =null;
		s_stockType =null;
		 d_iwf =null;
		maturity_date=null;
		issuedate=null;
		isssuesize=null;
		start_date=null; 
		delistingdate=null;
		s_companyName ="0";
		f_issuedShares=null;
		s_countryName="0";
		f_faceValue=null;
		s_ratingCode=null;
		d_paidValue=null;
		
		s_marketLot=null;
		f_alertPercent=null;
		f_rejectionPercent=null;
		
		
		s_stockCurrency="0";
		b_isPriceForLot=null;
		s_growthValueType=null;
		b_isActive=null;
		b_sdl=null;
		s_sdl=null;
		b_ric=null;
		b_isn=null;
		
		b_exc_code=null;
		b_tkr=null;
		b_csp=null;
		success_flag=null;
		commit_button=null;
		check_flag=null;

		Logging.debug("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb: "+b_isActive);	
	}
	/**
	 * reset all the Stockmaster form fields.
	 *
	 */
	public void reset()
	{
		s_stockID = null;
		s_stockName =null;
		d_listingDate =null;
		s_stockType =null;
		d_iwf =null;
		s_companyName ="0";
		f_issuedShares=null;
		s_stockExchange="0";
		s_stockExchange_null="0";
		s_countryName="0";
		f_faceValue=null;
		s_ratingCode=null;
		d_paidValue=null;
		
		s_marketLot=null;
		f_alertPercent=null;
		f_rejectionPercent=null;
		s_stockCurrency="0";
		maturity_date=null;
		issuedate=null;
		isssuesize=null;
		start_date=null; 
		delistingdate=null;
		
		b_isPriceForLot=null;
		s_growthValueType=null;
		b_isActive=null;
		b_sdl=null;
		s_sdl=null;
		b_ric=null;
		b_isn=null;
		b_exc_code=null;
		b_tkr=null;
		b_csp=null;
		b_crisil=null;
		exp_date=null;
		deliverycenter=null;
		measure=null;
		
		coupon_percentage=null;
		coupon_period=null;
		coupon_payment_dates=null;
		interest_basis_month=null;
		accrued_interest=null;;
		description=null;
		check_flag=null;
		success_flag=null;
		commit_button=null;

	}
	/**
	 * validate stock master form values.
	 */
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		ActionErrors errors = new ActionErrors();
		boolean flag=true,flag1=true;
		try{
			if( !(s_stockName != null) ||s_stockName.trim().length() == 0 || s_stockName.trim().equals(""))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("s_stockName",new ActionError("Error.message.StockName"));			
			}
			
			if((d_listingDate==null)||(d_listingDate.trim().equals(""))){
				Logging.debug("listng date "+d_listingDate);
				errors.add("d_listingDate",new ActionError("Error.message.d_listingDate"));				
			}
			if((d_iwf==null)||(d_iwf.trim().equals("")) || (ValidateNumber(d_iwf.trim())==false)){
				Logging.debug("stock type"+s_stockType+"  rating code"+s_ratingCode+"  company"+s_companyName);
				errors.add("d_iwf",new ActionError("Error.message.IWF"));				
			}else{
				if(Double.parseDouble(d_iwf)>1.0 || Double.parseDouble(d_iwf)<0)
				{
					errors.add("d_iwf",new ActionError("Error.message.IWF"));	
				}
			}
			Logging.debug("before alert rejection validation");
			Logging.debug("(Double.parseDouble(f_alertPercent)) is "+(Double.parseDouble(f_alertPercent)));
			Logging.debug("(Double.parseDouble(f_rejectionPercent)) is "+(Double.parseDouble(f_rejectionPercent)));
			if((Double.parseDouble(f_alertPercent))>(Double.parseDouble(f_rejectionPercent))){
				errors.add(null,new ActionError("Error.message.RejectionCriteria"));
			}
			
			if(s_stockID!=null){
				flag=true;
				flag=checkExchange(s_stockExchange,s_stockID);
				if(flag==false)
				{
					errors.add("s_stockExchange",new ActionError("Error.message.s_stockExchange"));
				}
			}
			
			
			if( !(s_stockExchange !=null) ||s_stockExchange.trim().length()== 0 || s_stockExchange.trim().equals("0") || s_stockExchange.trim().equals("Select StockExchange")){
				errors.add("s_stockExchange",new ActionError("Error.message.stk_stockExchange"));				
			}
			if( !(s_countryName !=null) ||s_countryName.trim().length()== 0 || s_countryName.trim().equals("0") || s_countryName.trim().equals("Select Country")){
				errors.add("s_countryName",new ActionError("Error.message.s_countryName"));				
			}
			
			if( !(s_stockCurrency !=null) ||s_stockCurrency.trim().length()== 0 || s_stockCurrency.trim().equals("0") || s_stockCurrency.trim().equals("Select Currency")){
				errors.add("s_stockCurrency",new ActionError("Error.message.s_stockCurrency"));				
			}
			
			
		}catch(Exception e){
			errors.add(null,new ActionError("Error.message.msg"));
			Logging.debug("Error in Validation ");			
		}	
		
		return errors;
	 }
	/**
	 * check for stock exchange if it is changed or not.
	 * return true if not changed else return false.
	 * @param s_stockExchange
	 * @param s_stockID
	 * @return
	 */
	public boolean checkExchange(String stockExchange,String stockid)
	{
		boolean flag=false;
		try{
			Connect con=ConnectInit.getConnect();
			connection=null;
			if(connection==null){
				connection=con.getdbConnection();
			}
			Logging.debug(" in checkExchange stockExchange is "+stockExchange+" stockid "+stockid);
			String query=ConnectInit.queries.getProperty("get_stockexchange_id_for_stockid");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(stockid));		
			ResultSet rs = stmt.executeQuery();
			int i=0;
			String id=null;
			while(rs.next()){
				id=rs.getString(1);
			}
			Logging.debug(" stockExchange is "+stockExchange+" id is  "+id);
			if(id.equals(stockExchange)){
				flag=true;
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			Logging.debug(" ERROR : "+e.getMessage());
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return flag;
	}
	/**
	 * @return Returns the stockTypeCollection.(stock typr list)
	 */
	public Collection getStockTypeCollection() {
		Vector indexList=new Vector();
		ResultSet rst=null;
		Connection connection = null;
//		app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		if(connection==null)
		{
			connection = con.getdbConnection();
		}	
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String SelStockType=asc.getLangValues("Masters.SelStockType");
		if (stockTypeCollection == null) {
	        try {
	        	rst = StockResult.getStockTypeList(connection);
	        	indexList.add(new LabelValueBean(SelStockType,"0"));
	        	while(rst.next()){
	        		String count=rst.getString(1);
	        		indexList.add(new LabelValueBean(rst.getString(1),count));
	        	}
	        } catch (SQLException e) {
	        	Logging.error("Error  :"+e.getMessage());
	        //	e.printStackTrace();
	        }finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
	        stockTypeCollection =indexList ;
	    }
		return stockTypeCollection;
	}
	/**
	 * @return Returns the companyListCollection.(company list)
	 */
	public Collection getCompanyListCollection() {
		try{
		Vector companyList=new Vector();
		ResultSet rst=null;
	//	app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		AcessControl asc = ConnectInit.getAcessControl();
	//	AcessControl asc=new AcessControl();
		String SelCompany=asc.getLangValues("classCompany.selectCompany");
		if (companyListCollection == null) {
		    try {
		    	if(connection == null)
		    	{
		    		connection = con.getdbConnection();
		    	}
		    	rst = StockResult. getCompanyList(connection);
		    	Logging.debug(" in company collection "+rst);
		    	companyList.add(new LabelValueBean(SelCompany,"0"));
		    	while(rst.next()){	        				
		    		String count=rst.getString(1);
		    		companyList.add(new LabelValueBean(rst.getString(2),count));
		    	}
		    	rst.close();
		    } catch (SQLException e) {
		    	Logging.error("Error  :"+e.getMessage());
		    //	e.printStackTrace();
		    }finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}	
		    companyListCollection =companyList ;
		}
		}catch(Exception e){
			Logging.error(e.getMessage());
		}
		return companyListCollection;
	}
	/**
	 * @return Returns the b_tkr.
	 */
	public String getB_tkr() {
		return b_tkr;
	}
	/**
	 * @param b_tkr The b_tkr to set.
	 */
	public void setB_tkr(String b_tkr) {
		this.b_tkr = b_tkr;
	}
	/**
	 * validate input String for number.
	 * @param str
	 * @return
	 */
	private boolean ValidateNumber(String str)
	  {
		if(str== null || str.trim().equals(""))
		   {
			return false;
		   }
		for(int i=0;i<str.length();i++)
		 {	
			if(Character.isLetter(str.charAt(i)))
			 return false;
		 }
		  return true;
	 }
	/**
	 * @return Returns the result.
	 */
	/**
	 * @return Returns the s_stockID.
	 */
	public String getS_stockID() {
		return s_stockID;
	}
	/**
	 * @param s_stockid The s_stockID to set.
	 */
	public void setS_stockID(String s_stockid) {
		this.s_stockID = s_stockid;
	}
	/**
	 * @return Returns the b_new.
	 */
	public String getB_new() {
		return b_new;
	}
	/**
	 * @param b_new The b_new to set.
	 */
	public void setB_new(String b_new) {
		this.b_new = b_new;
	}
	/**
	 * @return Returns the b_accpt.
	 */
	public String getB_accpt() {
		return b_accpt;
	}
	/**
	 * @param b_accpt The b_accpt to set.
	 */
	public void setB_accpt(String b_accpt) {
		this.b_accpt = b_accpt;
	}
	/**
	 * @return Returns the success.
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	/**
	 * @return Returns the b1.
	 */
	
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
	 * @return Returns the exc_id.
	 */
	public String getExc_id() {
		return exc_id;
	}
	/**
	 * @param exc_id The exc_id to set.
	 */
	public void setExc_id(String exc_id) {
		this.exc_id = exc_id;
	}
	/**
	 * @return Returns the series.
	 */
	public String getSeries() {
		if(series==null)
			series="EQ";
		return series;
	}
	/**
	 * @param series The series to set.
	 */
	public void setSeries(String series) {
		this.series = series;
	}
	
	/**
	 * @return Returns the adr_gdr_id.
	 */
	public String getAdr_gdr_id() {
		return adr_gdr_id;
	}
	/**
	 * @param adr_gdr_id The adr_gdr_id to set.
	 */
	public void setAdr_gdr_id(String adr_gdr_id) {
		this.adr_gdr_id = adr_gdr_id;
	}
	/**
	 * @return Returns the s_stockExchange_null.
	 */
	public String getS_stockExchange_null() {
		return s_stockExchange_null;
	}
	/**
	 * @param exchange_null The s_stockExchange_null to set.
	 */
	public void setS_stockExchange_null(String exchange_null) {
		s_stockExchange_null = exchange_null;
	}
	/**
	 * @return Returns the stockExcListCollection.(stock Exchange List)
	 */
	public Collection getStockExcListCollection() {
		Vector stkExcList=new Vector();   
		ResultSet rst=null; 
//		app.Connect con=new app.Connect();      
		Connect con = ConnectInit.getConnect();	
		Connection connection = null;
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String SelStkExchg=asc.getLangValues("TradeVolumeInd.Stock");
		if (stockExcListCollection == null) {
			try {
				  if(connection == null){
				  	connection = con.getdbConnection();
				 }   
				rst = StockResult.getStockExchangeList(connection);
				Logging.debug(" in stock Exchange collection "+rst);
				stkExcList.add(new LabelValueBean(SelStkExchg,"0"));
				//stkExcList.add(new LabelValueBean("Select Exchange","0"));
				while(rst.next()){	        				
					String count=rst.getString(1);
					stkExcList.add(new LabelValueBean(rst.getString(2),count));
	            }
				rst.close();
	         } catch (SQLException e) {
	         	Logging.error("Error  :"+e.getMessage());
	         //	e.printStackTrace();
	         }	finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
	         stockExcListCollection =stkExcList ;
		}
		
		return stockExcListCollection;
	}
	/**
	 * @return Returns the countryListCollection.(country List)
	 */
	public Collection getCountryListCollection() {
		Vector stkExcList=new Vector();
		ResultSet rst=null;
//		app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();	
		Connection connection = null;
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String SelCountry=asc.getLangValues("Masters.SelectCountry");
		if (countryListCollection == null) {
			try {
				 if(connection == null){
				 	connection = con.getdbConnection();
				 }
				rst =StockResult.getCountryList(connection);		
				Logging.debug(" in stock Exchange collection "+rst);
				stkExcList.add(new LabelValueBean(SelCountry,"0"));
				while(rst.next()){	        				
					String count=rst.getString(1);
					stkExcList.add(new LabelValueBean(rst.getString(2),count));
				}
				rst.close();
			} catch (SQLException e) {
				Logging.error("Error  :"+e.getMessage());
			//	e.printStackTrace();
			}finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}	
			countryListCollection =stkExcList ;
		}
		return countryListCollection;
	}
	/**
	 * @return Returns the currencyListCollection.(currency List)
	 */
	public Collection getCurrencyListCollection() {
		Vector stkExcList=new Vector();
		Connection connection = null;
		ResultSet rst=null;
//		app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();		
//		AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String SelCurrency=asc.getLangValues("Masters.SelectCurrency");
		if (currencyListCollection == null) {
		    try {
		    	  if(connection == null){
		    	  	connection = con.getdbConnection();
		    	  }
		    	rst =StockResult.getCurrencyList(connection);		
		    	Logging.debug(" in stock Exchange collection "+rst);
		    	stkExcList.add(new LabelValueBean(SelCurrency,"0"));
		    	while(rst.next()){	        				
		    		String count=rst.getString(1);
		    		stkExcList.add(new LabelValueBean(rst.getString(2),count));
		    	}
		    	rst.close();
		    } catch (SQLException e) {
		    	Logging.error("Error  :"+e.getMessage());
		    //	e.printStackTrace();
		    }finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}	
		    currencyListCollection =stkExcList ;
		}
		return currencyListCollection;
	}
	/**
	 * @return Returns the ratingCodeListCollection.(rating code List)
	 */
	public Collection getRatingCodeListCollection() {
		Vector stkExcList=new Vector();
		Connection connection = null;
		ResultSet rst=null;
//		app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();	
//		AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String SelRatingCode=asc.getLangValues("Masters.SelectRatingCode");
		if (ratingCodeListCollection == null) {
		    try {
		    	 if(connection == null){
		    	 	connection = con.getdbConnection();
		    	 }
		    	rst =StockResult.getRatingCode(connection);		
		    	Logging.debug(" in stock Exchange collection "+rst);
		    	//stkExcList.add(new LabelValueBean(SelRatingCode,"0"));
		    	while(rst.next()){	        				
		    		String count=rst.getString(1);
		    		stkExcList.add(new LabelValueBean(rst.getString(2),count));
		    	}
		    	rst.close();
		    } catch (SQLException e) {
		    	Logging.error("Error  :"+e.getMessage());
		       // e.printStackTrace();
		    }finally{
				try{if(connection!=null)
					connection.close();
				}catch(Exception ee){
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}	
		    ratingCodeListCollection =stkExcList ;
	    }
		return ratingCodeListCollection;
	}
	

	/**
	 * @return Returns the is_price_for_lotCollection.(is price for lot List)
	 */
	public Collection getIs_price_for_lotCollection() {
		Vector stkExcList=new Vector();
		if (is_price_for_lotCollection == null) {               
			stkExcList.add(new LabelValueBean("No","n"));
			stkExcList.add(new LabelValueBean("Yes","y"));
			is_price_for_lotCollection =stkExcList ;
		}
		return is_price_for_lotCollection;
	} 
	
	
	public StringBuffer getMessages(HttpServletRequest request){
		StringBuffer buffer1=new StringBuffer();
		String display=null;		        		
       	display=request.getParameter("display");
		if(display!=null && display.equals("Insert")){
			buffer1.append("<td width='75%'><b><font face='Arial' color='Red' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"Stock Inserted Sucessfully"+"</font></b></td> ");                  
			buffer1.append("<br> ");
        }
       	 if(display!=null && display.equals("Update")){       
       	 	buffer1.append("<td width='75%'><b><font face='Arial' color='Red' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"Stock Updated Sucessfully"+"</font></b></td>");                 
      		buffer1.append("<br> ");
       } 
       return buffer1;
	}
	/**
	 * check for refressing of tree on body load of form.
	 * @param request
	 * @return boolean.
	 */
	public boolean bodycheck(HttpServletRequest request){
		boolean bodychk=false;
		String prs=b1;
		String from=request.getParameter("from");
		Logging.debug("&&&&& From "+from);
		String stkid=null;
		ResultSet rs1=null;
		stkid=s_stockID;		 
		Logging.debug("from submit "+prs+" stockid "+stkid);				
		if(prs!=null && prs.equals("Submit") && (stkid==null || stkid.equals("null"))){
			Logging.debug("In if of body tag check");	
			bodychk=true;
		}
		String check = getVarify();//These Changes Are made for Commodity.jsp....
		if(from != null && from.equals("menu"))
		{
			reset();
		}
		if(check.equals("clean"))
		{
			//reset();
		}
		setVarify("clean");
		return bodychk;	
	}
	/**
	 * check if to valiate form values and display eroors or not.
	 * @param request
	 * @param response
	 * @return boolean value
	 */
	public boolean getErrors(HttpServletRequest request,HttpServletResponse response){
		boolean errordisplay=false;
		
        String 	stockid1=null;
		String bpressed=null;
		String fr1=request.getParameter("from");
		if(fr1!=null && fr1.equals("menu")){
			b1=null;
		}
		Vector vidcode=new Vector();
		stockid1=request.getParameter("s_stockid");
		String buttonpressed=b1;
        Logging.debug("button pressed is ="+buttonpressed);
        bpressed=request.getParameter("new_but");
        Logging.debug("button pressed ="+bpressed);
        String button=request.getParameter("operation");
        boolean f1=(buttonpressed!=null && buttonpressed.equals("New"));
        boolean f2=(buttonpressed!=null && buttonpressed.equals("View Corporate Actions"));
        Logging.debug("f1 is "+f1+" f2 is "+f2+" resultant is "+(!(f1 || f2)));
        Logging.debug("button"+button);
        if(!(f1 || f2))
	    {
	         Logging.debug("!(f1 || f2) "+(!(f1 || f2)) );
	         errordisplay=true;		 
		}
        String sname=null;
        String type=null;
        ResultSet rs=null;
        if(!(button!=null && button.equals("New")))
	         {
	         		button=null;
	         }	
	          if(!(buttonpressed!=null && buttonpressed.equals("View Corporate Actions")))
	         {
	         		buttonpressed=null;
	         }			           		
   	   if((fr1!=null && fr1.equals("menu")) || (button!=null && button.equals("New")))
        {
   	   			s_stockID=null; 
        		reset();
        		
        }
        if(stockid1!=null)
        {
        		            		
          Logging.debug("stockid in check="+stockid1);
          s_stockID=stockid1; 
          setStockDetail(stockid1);                             
        }
        stockid1=s_stockID;
        Logging.debug("stockid is from bean ="+stockid1);
        if(stockid1==null){
        	stockid1=s_stockID;
         	Logging.debug("in null");
     	}else{               		
      	    try{
      	    	
     		}catch(Exception e){
     			Logging.error(" Error : "+e.getMessage());
     		}
      	}              	
      	Logging.debug("after  while");    
    return errordisplay;
	}
	
	/**
	 * @return Returns the bodycheck boolean (true/false).
	 */
	public boolean getBodycheck() {
		String prs=b1;
		String stkid=null;
		ResultSet rs1=null;
		stkid=s_stockID;		 
		Logging.debug("from submit "+prs+" stockid "+stkid);				
		if(prs!=null && prs.equals("Submit") && (stkid==null || stkid.equals("null"))){
			Logging.debug("In if of body tag check");	
			bodycheck=true;
		}		
		return bodycheck;
	}
	
	/**
	 * @param bodycheck The bodycheck to set.
	 */
	public void setBodycheck(boolean bodycheck) {
		this.bodycheck = bodycheck;
	}
	
	
	public void reset_value()
	{	
		try{
			Logging.debug("So Strange...........");
			
		Connect c=ConnectInit.getConnect();
		String qry=ConnectInit.queries.getProperty("select_system_config");			
		Statement stmt = connection.createStatement();
		ResultSet rs1 = stmt.executeQuery(qry);
		rs1.next();
		s_stockExchange=rs1.getString(11);    
		rs1.close();
		stmt.close();
		
		d_listingDate=null;
		newIssue=null;
		parent_id=null;
		}catch(Exception e){
			Logging.debug("Error="+e.getMessage());
			}
		}
	
	
	/**
	 * @return Returns the newissues_but.
	 */
	public String getNewissues_but() {
		return newissues_but;
	}
	/**
	 * @param newissues_but The newissues_but to set.
	 */
	public void setNewissues_but(String newissues_but) {
		this.newissues_but = newissues_but;
	}
	/**
	 * @return Returns the accrued_interest.
	 */
	public String getAccrued_interest() {
		return accrued_interest;
	}
	/**
	 * @param accrued_interest The accrued_interest to set.
	 */
	public void setAccrued_interest(String accrued_interest) {
		this.accrued_interest = accrued_interest;
	}
	/**
	 * @return Returns the coupon_payment_dates.
	 */
	public String getCoupon_payment_dates() {
		return coupon_payment_dates;
	}
	/**
	 * @param coupon_payment_dates The coupon_payment_dates to set.
	 */
	public void setCoupon_payment_dates(String coupon_payment_dates) {
		this.coupon_payment_dates = coupon_payment_dates;
	}
	/**
	 * @return Returns the coupon_percentage.
	 */
	public String getCoupon_percentage() {
		return coupon_percentage;
	}
	/**
	 * @param coupon_percentage The coupon_percentage to set.
	 */
	public void setCoupon_percentage(String coupon_percentage) {
		this.coupon_percentage = coupon_percentage;
	}
	/**
	 * @return Returns the coupon_period.
	 */
	public String getCoupon_period() {
		return coupon_period;
	}
	/**
	 * @param coupon_period The coupon_period to set.
	 */
	public void setCoupon_period(String coupon_period) {
		this.coupon_period = coupon_period;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the interest_basis_month.
	 */
	public String getInterest_basis_month() {
		return interest_basis_month;
	}
	/**
	 * @param interest_basis_month The interest_basis_month to set.
	 */
	public void setInterest_basis_month(String interest_basis_month) {
		this.interest_basis_month = interest_basis_month;
	}
	/**
	 * @return Returns the interest_basis_year.
	 */
	public String getInterest_basis_year() {
		return interest_basis_year;
	}
	/**
	 * @param interest_basis_year The interest_basis_year to set.
	 */
	public void setInterest_basis_year(String interest_basis_year) {
		this.interest_basis_year = interest_basis_year;
	}
	/**
	 * @return Returns the maturity_date.
	 */
	public String getMaturity_date() {
		return maturity_date;
	}
	/**
	 * @param maturity_date The maturity_date to set.
	 */
	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}
	/**
	 * @return Returns the start_date.
	 */
	public String getStart_date() {
		return start_date;
	}
	/**
	 * @param start_date The start_date to set.
	 */
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	/**
	 * @return Returns the commit_button.
	 */
	public String getCommit_button() {
		return commit_button;
	}
	/**
	 * @param commit_button The commit_button to set.
	 */
	public void setCommit_button(String commit_button) {
		this.commit_button = commit_button;
	}
	
	/**
	 * @return Returns the success_flag.
	 */
	public String getSuccess_flag() {
		return success_flag;
	}
	/**
	 * @param success_flag The success_flag to set.
	 */
	public void setSuccess_flag(String success_flag) {
		this.success_flag = success_flag;
	}
	public String getIsssuesize() {
		return isssuesize;
	}
	public void setIsssuesize(String isssuesize) {
		this.isssuesize = isssuesize;
	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	public String getDelistingdate() {
		return delistingdate;
	}
	public void setDelistingdate(String delistingdate) {
		this.delistingdate = delistingdate;
	}
	public String[] getRem() {
		return Rem;
	}
	public void setRem(String[] rem) {
		Rem = rem;
	}
	
	
}	