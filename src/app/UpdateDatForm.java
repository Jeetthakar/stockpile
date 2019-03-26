package app;
 import org.apache.struts.action.ActionForm;
/**
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateDatForm  {

	String index_name,previous_close,open_close,high_close,low_close,percentage_change,fifty_two_week_high,fifty_two_week_low,close,date;
	/**
	 * @return Returns the series.
	 */
	public String getIndex_Name() {
		return index_name;
	}
	/**
	 * @param series The series to set.
	 */
	public void setIndex_Name(String index_name) {
		this.index_name = index_name;
	}
	/**
	 * @return Returns the symbol.
	 */
	public String getprevious_Close() {
		return previous_close;
	}
	/**
	 * @param symbol The symbol to set.
	 */
	public void setPrevious_Close(String previous_close) {
		this.previous_close = previous_close;
	}
	public String getOpen_Close() {
		return open_close;
	}
	
	public void setOpen_Close(String open_close) {
		this.open_close = open_close;
	}
	public String getHigh_Close(){
		return high_close;
	}
	public void setHigh_Close(String high_close ){
		this.high_close=high_close;
	}
	public String getLow_Close(){
		return low_close;
	}
	public void setLow_Close(String low_close){
		this.low_close=low_close;
	}
	public String getPercentage_Change(){
		return percentage_change;
	}
	public void setPercentage_Change(String percentage_change ){
		this.percentage_change=percentage_change;
	}
	public String getFiFty_Two_week_High(){
		return fifty_two_week_high;
	}
	public void setFiFty_Two_week_High(String fifty_two_week_high){
		this.fifty_two_week_high=fifty_two_week_high;
	}
	public String getFifty_Two_Week_Low(){
		return fifty_two_week_low;
	}
	public void setFifty_Two_Week_Low(String fifty_two_week_low){
		this.fifty_two_week_low=fifty_two_week_low;
	}
	public String getClose(){
		return close;
	}
	public void setClose(String close){
		this.close=close;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date=date;
	}
	/*public String getB1() {
		return b1;
	}
	/**
	 * @param b1 The b1 to set.
	 */
	/*public void setB1(String b1) {
		this.b1 = b1;
	}*/
	
}
