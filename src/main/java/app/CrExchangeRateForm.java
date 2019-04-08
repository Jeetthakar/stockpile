/*
 * Created on Feb 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

/**
 * @author sudhir
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CrExchangeRateForm {
	String forex_code,open_value,high_value,prev_close,low_value,close_value,last_value,traded_volume,traded_value,time_stamp;
	/**
	 * @return Returns the close_value.
	 */
	public String getClose_value() {
		if(close_value.equals(null))
			close_value="";
		return close_value;
	}
	/**
	 * @param close_value The close_value to set.
	 */
	public void setClose_value(String close_value) {
		this.close_value = close_value;
	}
	/**
	 * @return Returns the forex_code.
	 */
	public String getForex_code() {
		return forex_code;
	}
	/**
	 * @param forex_code The forex_code to set.
	 */
	public void setForex_code(String forex_code) {
		this.forex_code = forex_code;
	}
	/**
	 * @return Returns the high_value.
	 */
	public String getHigh_value() {
		if(high_value.equals(null))
			high_value="";
		return high_value;
	}
	/**
	 * @param high_value The high_value to set.
	 */
	public void setHigh_value(String high_value) {
		this.high_value = high_value;
	}
	/**
	 * @return Returns the last_value.
	 */
	public String getLast_value() {
		if(last_value.equals(null))
			last_value="";
		return last_value;
	}
	/**
	 * @param last_value The last_value to set.
	 */
	public void setLast_value(String last_value) {
		this.last_value = last_value;
	}
	/**
	 * @return Returns the low_value.
	 */
	public String getLow_value() {
		if(low_value.equals(null))
			low_value="";
		return low_value;
	}
	/**
	 * @param low_value The low_value to set.
	 */
	public void setLow_value(String low_value) {
		this.low_value = low_value;
	}
	/**
	 * @return Returns the open_value.
	 */
	public String getOpen_value() {
		return open_value;
	}
	/**
	 * @param open_value The open_value to set.
	 */
	public void setOpen_value(String open_value) {
		this.open_value = open_value;
	}
	/**
	 * @return Returns the time_stamp.
	 */
	public String getTime_stamp() {
		return time_stamp;
	}
	/**
	 * @param time_stamp The time_stamp to set.
	 */
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	/**
	 * @return Returns the traded_value.
	 */
	public String getTraded_value() {
		return traded_value;
	}
	/**
	 * @param traded_value The traded_value to set.
	 */
	public void setTraded_value(String traded_value) {
		this.traded_value = traded_value;
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
	public static void main(String[] args) {
	}
	/**
	 * @return Returns the prev_close.
	 */
	public String getPrev_close() {
		return prev_close;
	}
	/**
	 * @param prev_close The prev_close to set.
	 */
	public void setPrev_close(String prev_close) {
		this.prev_close = prev_close;
	}
}
