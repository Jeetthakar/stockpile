/*
 * Created on Jan 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sysconfig.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.Logger;

import sysconfig.action.SysConfigForm;
import app.Connect;

import com.harrier.initializeation.ConnectInit;


/**
 * @author manoj
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysConfig {
	Logger Logging = Logger.getLogger(SysConfig.class);
	String query;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public ResultSet getRst() {
		return rst;
	}
	public void setRst(ResultSet rst) {
		this.rst = rst;
	}
	public int getIntConfigurationId() {
		return intConfigurationId;
	}
	public void setIntConfigurationId(int intConfigurationId) {
		this.intConfigurationId = intConfigurationId;
	}
	public String getCharIcId() {
		return charIcId;
	}
	public void setCharIcId(String charIcId) {
		this.charIcId = charIcId;
	}
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	public String getCharseId() {
		return charseId;
	}
	public void setCharseId(String charseId) {
		this.charseId = charseId;
	}
	public String getCharcuId() {
		return charcuId;
	}
	public void setCharcuId(String charcuId) {
		this.charcuId = charcuId;
	}
	public String getCharcoId() {
		return charcoId;
	}
	public void setCharcoId(String charcoId) {
		this.charcoId = charcoId;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getIndId() {
		return indId;
	}
	public void setIndId(String indId) {
		this.indId = indId;
	}
	public String getIndexTyp() {
		return indexTyp;
	}
	public void setIndexTyp(String indexTyp) {
		this.indexTyp = indexTyp;
	}
	public String getStockTyp() {
		return stockTyp;
	}
	public void setStockTyp(String stockTyp) {
		this.stockTyp = stockTyp;
	}
	public int getIntIcId() {
		return intIcId;
	}
	public void setIntIcId(int intIcId) {
		this.intIcId = intIcId;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public Vector getOptions() {
		return options;
	}
	public void setOptions(Vector options) {
		this.options = options;
	}
	public Vector getTime() {
		return time;
	}
	public void setTime(Vector time) {
		this.time = time;
	}
	public Vector getStocksEx() {
		return stocksEx;
	}
	public void setStocksEx(Vector stocksEx) {
		this.stocksEx = stocksEx;
	}
	public Vector getCountries() {
		return countries;
	}
	public void setCountries(Vector countries) {
		this.countries = countries;
	}
	public Vector getCurrencies() {
		return currencies;
	}
	public void setCurrencies(Vector currencies) {
		this.currencies = currencies;
	}
	public ArrayList getOptions1() {
		return options1;
	}
	public void setOptions1(ArrayList options1) {
		this.options1 = options1;
	}
	public Vector getV() {
		return v;
	}
	public void setV(Vector v) {
		this.v = v;
	}
	public Vector getIndexList() {
		return indexList;
	}
	public void setIndexList(Vector indexList) {
		this.indexList = indexList;
	}
	public Connect getCon() {
		return con;
	}
	public void setCon(Connect con) {
		this.con = ConnectInit.getConnect();
	}
	public PreparedStatement getStmt1() {
		return stmt1;
	}
	public void setStmt1(PreparedStatement stmt1) {
		this.stmt1 = stmt1;
	}
	ResultSet rst;
	int intConfigurationId;	
	String charIcId,chartId,charseId,charcuId,charcoId,lan,indId,indexTyp,stockTyp;
	int intIcId;
	String c;
	Vector options = new Vector(10);
	Vector time=new Vector(30,30);
	Vector stocksEx=new Vector(150,50);
	Vector countries=new Vector(300,100);
	Vector currencies=new Vector(200,50);
	ArrayList options1=new ArrayList();
	Vector v= new Vector(50,50);
	Vector indexList= new Vector(50,50);
	Connect con=ConnectInit.getConnect();
	PreparedStatement stmt1;
	Vector  industryClassification = new Vector(10);
	public Vector getIndustryClassification() {
		return industryClassification;
	}
	public void setIndustryClassification(Vector industryClassification) {
		this.industryClassification = industryClassification;
	}
	/**Adding Entries In Database
	 * */
	public void AddSysConfig(int ComputationInterval,int MonitorRefreshRate,int PrecisionValue,int RateOfPriceFeed,String CustomerName,String NameLogoVerticalAlign,String NameLogoHorizontalAlign,int MaxNoOfCompanies,float AlertPercentage,float RejectionPercentage,
			int IntraDayArchivalInterval,String IndustryClassificationId,String TimeZoneId,String CustomerLogoPath,String DateFormat,int MarketLot,String StockExId,String CurrencyId,String CountryId,int DateDifference,String AdjustStockPrice,float percentageChaneInShare,
			String language,String indexId,float faceValue, float paidValue,String indexType,String stockType){
			SysConfigForm syf = new SysConfigForm();
			Connection connection=null;
		try {
			if(connection==null)
				connection=con.getdbConnection();
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("sysconfig_select_configuration_id_from_system_configuration"));	
			rst = stmt.executeQuery();	
			while(rst.next()){
				intConfigurationId=rst.getInt(1);
				Logging.debug(intConfigurationId);
			}
			rst.close();
			stmt.close();
			/**Getting all the ids*/
			charIcId	=	IndustryClassificationId.split("e")[1];
			chartId 	=	TimeZoneId.split("e")[1];
			charseId 	=	StockExId.split("e")[1];
			charcuId	=	CurrencyId.split("e")[1];
			charcoId	=	CountryId.split("e")[1];
			lan			=	language.split("e")[1];
			indId		=	indexId.split("e")[1];
			
			Logging.debug("index id is"+indId);
			Logging.debug("stocktype id is"+stockType);
			Logging.debug("indextype id is"+indexType);
			indexTyp	=	indexType.split("e")[1];
			Logging.debug("index id is"+indexTyp);
			stockTyp	=	stockType.split("e")[1];
			/**If system_configuration table is empty then insert values in table*/
			if(intConfigurationId==0){
			    stmt1 = connection.prepareStatement(ConnectInit.queries.getProperty("sysconfig_insert"));
       		}	
			else{
				stmt1 = connection.prepareStatement(ConnectInit.queries.getProperty("sysconfig_update"));
			}
				stmt1.setInt(1,ComputationInterval);
         		stmt1.setInt(2,MonitorRefreshRate);
         		stmt1.setInt(3,PrecisionValue);
         		stmt1.setInt(4,RateOfPriceFeed);
         		stmt1.setString(5,CustomerName.trim());
         		stmt1.setString(6,NameLogoVerticalAlign);
         		stmt1.setString(7,NameLogoHorizontalAlign);
         		stmt1.setInt(8,MaxNoOfCompanies);
         		stmt1.setFloat(9,AlertPercentage);
         		stmt1.setFloat(10,RejectionPercentage);
         		stmt1.setInt(11,IntraDayArchivalInterval);
         		stmt1.setString(12,charIcId);
         		stmt1.setString(13,chartId);
         		if(CustomerLogoPath==null || CustomerLogoPath.equals("")){
         			stmt1.setString(14,null);
         		}
         		else{
         			stmt1.setString(14,CustomerLogoPath.trim());
         		}
         		stmt1.setString(15,DateFormat);
         		stmt1.setInt(16,MarketLot);
         		stmt1.setString(17,charseId);
         		stmt1.setString(18,charcuId);
         		stmt1.setString(19,charcoId);
         		stmt1.setInt(20,DateDifference);
         		stmt1.setString(21,AdjustStockPrice);
         		stmt1.setFloat(22,percentageChaneInShare);
         		if(lan.equals("0")){
         			stmt1.setInt(23,1);
         		}
         		else{
         			stmt1.setString(23,lan);
         		}
         		if(indId.equals("0")){
         			stmt1.setString(24,null);
         		}
         		else{
         			stmt1.setString(24,indId);
         		}
         		stmt1.setFloat(25,faceValue);
         		stmt1.setFloat(26,paidValue);
         		stmt1.setString(27,indexTyp);
         		stmt1.setString(28,stockTyp);
         		
         		stmt1.executeUpdate();
         		stmt1.close();
		
		} catch (SQLException e) {
			Logging.error(" Error : "+e.getMessage());
			//e.printStackTrace();
			}
		finally{
	   		try{
	   			if(connection!=null)
	   				connection.close();
	   		}catch(Exception ex){
	   			Logging.error(" Error : Unable to close Connection "+ex.getMessage());
	   		}
	   	}	
	}

	/**
	 * Database Connectivity
	 * */
	/*public void dbconnect(){
		
		try {
  		  	if(app.Connect.con==null)
		{
			con.getConnection();
		}

		} catch (Exception e) {	Logging.debug(e);} 
		
	}*/
	
}
