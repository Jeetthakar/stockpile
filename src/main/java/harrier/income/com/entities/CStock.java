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
/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CStock extends ActionForm{
	private long l_marketLot;
	private boolean bool_global100;
	private double d_alertPer;
	private double d_faceValue;
	private double d_iwf;	
	private double d_rejectPerc;
	private double d_tis;
	private double d_withTaxApplied;
	private double d_withTaxPercent;
	private Date dt_listDate;
	private String s_asx;
	private String s_comName;
	private String s_natureOfStock;
	private String s_rateCode;
	private String s_stockExchName;
	private String s_stockName;
	private String s_stockType;
	/**
	 * @return Returns the bool_global100.
	 */
	public boolean isBool_global100() {
		return bool_global100;
	}
	/**
	 * @param bool_global100 The bool_global100 to set.
	 */
	public void setBool_global100(boolean bool_global100) {
		this.bool_global100 = bool_global100;
	}
	/**
	 * @return Returns the d_alertPer.
	 */
	public double getD_alertPer() {
		return d_alertPer;
	}
	/**
	 * @param per The d_alertPer to set.
	 */
	public void setD_alertPer(double per) {
		d_alertPer = per;
	}
	/**
	 * @return Returns the d_faceValue.
	 */
	public double getD_faceValue() {
		return d_faceValue;
	}
	/**
	 * @param value The d_faceValue to set.
	 */
	public void setD_faceValue(double value) {
		d_faceValue = value;
	}
	/**
	 * @return Returns the d_iwf.
	 */
	public double getD_iwf() {
		return d_iwf;
	}
	/**
	 * @param d_iwf The d_iwf to set.
	 */
	public void setD_iwf(double d_iwf) {
		this.d_iwf = d_iwf;
	}
	/**
	 * @return Returns the d_marketLot.
	 */
	public double getL_marketLot() {
		return l_marketLot;
	}
	/**
	 * @param lot The d_marketLot to set.
	 */
	public void setL_marketLot(long lot) {
		l_marketLot = lot;
	}
	/**
	 * @return Returns the d_rejectPerc.
	 */
	public double getD_rejectPerc() {
		return d_rejectPerc;
	}
	/**
	 * @param perc The d_rejectPerc to set.
	 */
	public void setD_rejectPerc(double perc) {
		d_rejectPerc = perc;
	}
	/**
	 * @return Returns the d_tis.
	 */
	public double getD_tis() {
		return d_tis;
	}
	/**
	 * @param d_tis The d_tis to set.
	 */
	public void setD_tis(double d_tis) {
		this.d_tis = d_tis;
	}
	/**
	 * @return Returns the d_withTaxApplied.
	 */
	public double getD_withTaxApplied() {
		return d_withTaxApplied;
	}
	/**
	 * @param taxApplied The d_withTaxApplied to set.
	 */
	public void setD_withTaxApplied(double taxApplied) {
		d_withTaxApplied = taxApplied;
	}
	/**
	 * @return Returns the d_withTaxPercent.
	 */
	public double getD_withTaxPercent() {
		return d_withTaxPercent;
	}
	/**
	 * @param taxPercent The d_withTaxPercent to set.
	 */
	public void setD_withTaxPercent(double taxPercent) {
		d_withTaxPercent = taxPercent;
	}
	/**
	 * @return Returns the dt_listDate.
	 */
	public Date getDt_listDate() {
		return dt_listDate;
	}
	/**
	 * @param dt_listDate The dt_listDate to set.
	 */
	public void setDt_listDate(Date dt_listDate) {
		this.dt_listDate = dt_listDate;
	}
	/**
	 * @return Returns the s_asx.
	 */
	public String getS_asx() {
		return s_asx;
	}
	/**
	 * @param s_asx The s_asx to set.
	 */
	public void setS_asx(String s_asx) {
		this.s_asx = s_asx;
	}
	/**
	 * @return Returns the s_comName.
	 */
	public String getS_comName() {
		return s_comName;
	}
	/**
	 * @param name The s_comName to set.
	 */
	public void setS_comName(String name) {
		s_comName = name;
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
	 * @return Returns the s_rateCode.
	 */
	public String getS_rateCode() {
		return s_rateCode;
	}
	/**
	 * @param code The s_rateCode to set.
	 */
	public void setS_rateCode(String code) {
		s_rateCode = code;
	}
	/**
	 * @return Returns the s_stockExchName.
	 */
	public String getS_stockExchName() {
		return s_stockExchName;
	}
	/**
	 * @param exchName The s_stockExchName to set.
	 */
	public void setS_stockExchName(String exchName) {
		s_stockExchName = exchName;
	}
	/**
	 * @return Returns the s_stockName.
	 */
	public String getS_stockName() {
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
}
