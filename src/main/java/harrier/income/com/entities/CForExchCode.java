/*
 * Created on Sep 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.entities;
import java.util.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CForExchCode {
	private double d_exchCloseValue;
	private double d_exchLowValue;
	private double d_exchOpenValue;
	private double d_exchRate;
	private double d_exchHighValue;
	private String s_forexCode;
	private Date dt_exchRateDate;
	
	/**
	 * 
	 */
	public CForExchCode() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @return Returns the d_exchCloseValue.
	 */
	public double getD_exchCloseValue() {
		return d_exchCloseValue;
	}
	/**
	 * @param closeValue The d_exchCloseValue to set.
	 */
	public void setD_exchCloseValue(double closeValue) {
		d_exchCloseValue = closeValue;
	}
	/**
	 * @return Returns the d_exchHighValue.
	 */
	public double getD_exchHighValue() {
		return d_exchHighValue;
	}
	/**
	 * @param highValue The d_exchHighValue to set.
	 */
	public void setD_exchHighValue(double highValue) {
		d_exchHighValue = highValue;
	}
	/**
	 * @return Returns the d_exchLowValue.
	 */
	public double getD_exchLowValue() {
		return d_exchLowValue;
	}
	/**
	 * @param lowValue The d_exchLowValue to set.
	 */
	public void setD_exchLowValue(double lowValue) {
		d_exchLowValue = lowValue;
	}
	/**
	 * @return Returns the d_exchOpenValue.
	 */
	public double getD_exchOpenValue() {
		return d_exchOpenValue;
	}
	/**
	 * @param openValue The d_exchOpenValue to set.
	 */
	public void setD_exchOpenValue(double openValue) {
		d_exchOpenValue = openValue;
	}
	/**
	 * @return Returns the d_exchRate.
	 */
	public double getD_exchRate() {
		return d_exchRate;
	}
	/**
	 * @param rate The d_exchRate to set.
	 */
	public void setD_exchRate(double rate) {
		d_exchRate = rate;
	}
	/**
	 * @return Returns the dt_exchRateDate.
	 */
	public Date getDt_exchRateDate() {
		return dt_exchRateDate;
	}
	/**
	 * @param dt_exchRateDate The dt_exchRateDate to set.
	 */
	public void setDt_exchRateDate(Date dt_exchRateDate) {
		this.dt_exchRateDate = dt_exchRateDate;
	}
	/**
	 * @return Returns the s_forexCode.
	 */
	public String getS_forexCode() {
		return s_forexCode;
	}
	/**
	 * @param code The s_forexCode to set.
	 */
	public void setS_forexCode(String code) {
		s_forexCode = code;
	}
}
