/*
 * Created on Sep 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.entities;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.*;
import harrier.income.com.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CIndex extends ActionForm{
	private String s_baseCurrency;
	private String s_capturedFrom;
	private String s_indClassificaiton;
	private String s_indexName;
	private String s_indexType;
	private String s_natureOfStock;
	private String s_reuterCode;
	private String s_ISINCode;	
	private double d_divisor;
	private double d_inxCloseValue;
	private double d_inxHighVal;
	private double d_inxLowVal;
	private double d_inxOpenVal;
	private double d_lastVal;
	private double d_mktCap;
	private double d_setmentVal;
	private float f_pctageAlert;
	private float f_pctageRejection;
	private long l_computationInterval;
	private boolean bool_isActive;
	private boolean bool_isCaptured;
	private boolean bool_isChild;
	private boolean bool_isSetmentReq;
	private Date dt_baseDate;
	private Date dt_cretaeDate;
	private Date dt_startTime;
	private Date dt_closeTime;
	private CStockList o_stockList;
	
	/**
	 * @return Returns the o_stockList.
	 */
	public CStockList getO_stockList() {
		return o_stockList;
	}
	/**
	 * @param list The o_stockList to set.
	 */
	public void setO_stockList(CStockList list) {
		o_stockList = list;
	}
	/**
	 * @return Returns the s_ISINCode.
	 */
	public String getS_ISINCode() {
		return s_ISINCode;
	}
	/**
	 * @param code The s_ISINCode to set.
	 */
	public void setS_ISINCode(String code) {
		s_ISINCode = code;
	}	
	
	/**
	 * @return Returns the bool_isActive.
	 */
	public boolean isBool_isActive() {
		return bool_isActive;
	}
	/**
	 * @param bool_isActive The bool_isActive to set.
	 */
	public void setBool_isActive(boolean bool_isActive) {
		this.bool_isActive = bool_isActive;
	}
	/**
	 * @return Returns the bool_isCaptured.
	 */
	public boolean isBool_isCaptured() {
		return bool_isCaptured;
	}
	/**
	 * @param bool_isCaptured The bool_isCaptured to set.
	 */
	public void setBool_isCaptured(boolean bool_isCaptured) {
		this.bool_isCaptured = bool_isCaptured;
	}
	/**
	 * @return Returns the bool_isChild.
	 */
	public boolean isBool_isChild() {
		return bool_isChild;
	}
	/**
	 * @param bool_isChild The bool_isChild to set.
	 */
	public void setBool_isChild(boolean bool_isChild) {
		this.bool_isChild = bool_isChild;
	}
	/**
	 * @return Returns the bool_isSetmentReq.
	 */
	public boolean isBool_isSetmentReq() {
		return bool_isSetmentReq;
	}
	/**
	 * @param bool_isSetmentReq The bool_isSetmentReq to set.
	 */
	public void setBool_isSetmentReq(boolean bool_isSetmentReq) {
		this.bool_isSetmentReq = bool_isSetmentReq;
	}
	/**
	 * @return Returns the d_divisor.
	 */
	public double getD_divisor() {
		return d_divisor;
	}
	/**
	 * @param d_divisor The d_divisor to set.
	 */
	public void setD_divisor(double d_divisor) {
		this.d_divisor = d_divisor;
	}
	/**
	 * @return Returns the d_inxCloseValue.
	 */
	public double getD_inxCloseValue() {
		return d_inxCloseValue;
	}
	/**
	 * @param closeValue The d_inxCloseValue to set.
	 */
	public void setD_inxCloseValue(double closeValue) {
		d_inxCloseValue = closeValue;
	}
	/**
	 * @return Returns the d_inxHighVal.
	 */
	public double getD_inxHighVal() {
		return d_inxHighVal;
	}
	/**
	 * @param highVal The d_inxHighVal to set.
	 */
	public void setD_inxHighVal(double highVal) {
		d_inxHighVal = highVal;
	}
	/**
	 * @return Returns the d_inxLowVal.
	 */
	public double getD_inxLowVal() {
		return d_inxLowVal;
	}
	/**
	 * @param lowVal The d_inxLowVal to set.
	 */
	public void setD_inxLowVal(double lowVal) {
		d_inxLowVal = lowVal;
	}
	/**
	 * @return Returns the d_inxOpenVal.
	 */
	public double getD_inxOpenVal() {
		return d_inxOpenVal;
	}
	/**
	 * @param openVal The d_inxOpenVal to set.
	 */
	public void setD_inxOpenVal(double openVal) {
		d_inxOpenVal = openVal;
	}
	/**
	 * @return Returns the d_lastVal.
	 */
	public double getD_lastVal() {
		return d_lastVal;
	}
	/**
	 * @param val The d_lastVal to set.
	 */
	public void setD_lastVal(double val) {
		d_lastVal = val;
	}
	/**
	 * @return Returns the d_mktCap.
	 */
	public double getD_mktCap() {
		return d_mktCap;
	}
	/**
	 * @param cap The d_mktCap to set.
	 */
	public void setD_mktCap(double cap) {
		d_mktCap = cap;
	}
	/**
	 * @return Returns the d_setmentVal.
	 */
	public double getD_setmentVal() {
		return d_setmentVal;
	}
	/**
	 * @param val The d_setmentVal to set.
	 */
	public void setD_setmentVal(double val) {
		d_setmentVal = val;
	}
	/**
	 * @return Returns the dt_baseDate.
	 */
	public Date getDt_baseDate() {
		return dt_baseDate;
	}
	/**
	 * @param dt_baseDate The dt_baseDate to set.
	 */
	public void setDt_baseDate(Date dt_baseDate) {
		this.dt_baseDate = dt_baseDate;
	}
	/**
	 * @return Returns the dt_closeTime.
	 */
	public Date getDt_closeTime() {
		return dt_closeTime;
	}
	/**
	 * @param dt_closeTime The dt_closeTime to set.
	 */
	public void setDt_closeTime(Date dt_closeTime) {
		this.dt_closeTime = dt_closeTime;
	}
	/**
	 * @return Returns the dt_cretaeDate.
	 */
	public Date getDt_cretaeDate() {
		return dt_cretaeDate;
	}
	/**
	 * @param dt_cretaeDate The dt_cretaeDate to set.
	 */
	public void setDt_cretaeDate(Date dt_cretaeDate) {
		this.dt_cretaeDate = dt_cretaeDate;
	}
	/**
	 * @return Returns the dt_startTime.
	 */
	public Date getDt_startTime() {
		return dt_startTime;
	}
	/**
	 * @param dt_startTime The dt_startTime to set.
	 */
	public void setDt_startTime(Date dt_startTime) {
		this.dt_startTime = dt_startTime;
	}
	/**
	 * @return Returns the f_pctageAlert.
	 */
	public float getF_pctageAlert() {
		return f_pctageAlert;
	}
	/**
	 * @param alert The f_pctageAlert to set.
	 */
	public void setF_pctageAlert(float alert) {
		f_pctageAlert = alert;
	}
	/**
	 * @return Returns the f_pctageRejection.
	 */
	public float getF_pctageRejection() {
		return f_pctageRejection;
	}
	/**
	 * @param rejection The f_pctageRejection to set.
	 */
	public void setF_pctageRejection(float rejection) {
		f_pctageRejection = rejection;
	}
	/**
	 * @return Returns the l_computationInterval.
	 */
	public long getL_computationInterval() {
		return l_computationInterval;
	}
	/**
	 * @param interval The l_computationInterval to set.
	 */
	public void setL_computationInterval(long interval) {
		l_computationInterval = interval;
	}
	/**
	 * @return Returns the s_baseCurrency.
	 */
	public String getS_baseCurrency() {
		return s_baseCurrency;
	}
	/**
	 * @param currency The s_baseCurrency to set.
	 */
	public void setS_baseCurrency(String currency) {
		s_baseCurrency = currency;
	}
	/**
	 * @return Returns the s_capturedFrom.
	 */
	public String getS_capturedFrom() {
		return s_capturedFrom;
	}
	/**
	 * @param from The s_capturedFrom to set.
	 */
	public void setS_capturedFrom(String from) {
		s_capturedFrom = from;
	}
	/**
	 * @return Returns the s_indClassificaiton.
	 */
	public String getS_indClassificaiton() {
		return s_indClassificaiton;
	}
	/**
	 * @param classificaiton The s_indClassificaiton to set.
	 */
	public void setS_indClassificaiton(String classificaiton) {
		s_indClassificaiton = classificaiton;
	}
	/**
	 * @return Returns the s_indexName.
	 */
	public String getS_indexName() {
		return s_indexName;
	}
	/**
	 * @param name The s_indexName to set.
	 */
	public void setS_indexName(String name) {
		s_indexName = name;
	}
	/**
	 * @return Returns the s_indexType.
	 */
	public String getS_indexType() {
		return s_indexType;
	}
	/**
	 * @param type The s_indexType to set.
	 */
	public void setS_indexType(String type) {
		s_indexType = type;
	}
	/**
	 * @return Returns the s_natureOfStock.
	 */
	public String getS_natureOfStock() {
		return s_natureOfStock;
	}
	/**
	 * @param ofStock The s_natureOfStock to set.
	 */
	public void setS_natureOfStock(String ofStock) {
		s_natureOfStock = ofStock;
	}
	/**
	 * @return Returns the s_reuterCode.
	 */
	public String getS_reuterCode() {
		return s_reuterCode;
	}
	/**
	 * @param code The s_reuterCode to set.
	 */
	public void setS_reuterCode(String code) {
		s_reuterCode = code;
	}
}
