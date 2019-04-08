package app;

/**
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdatefForm {

	String symbol,instrument,expr_dt,strike_pr,option_typ,open,high,low,close,settle_pr,contracts,val_inlakh,open_int,change_in_ol,timestamp;
	/**
	 * @return Returns the series.
	 */
	public String getInstrument() {
		return instrument;
	}
	/**
	 * @param series The series to set.
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	/**
	 * @return Returns the symbol.
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol The symbol to set.
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getExpr_dt() {
		return expr_dt;
	}
	
	public void setExpr_dt(String expr_dt) {
		this.expr_dt = expr_dt;
	}
	public String getStrike_pr(){
		return strike_pr;
	}
	public void setStrike_pr(String strike_pr ){
		this.strike_pr=strike_pr;
	}
	public String getOption_typ(){
		return option_typ;
	}
	public void setOption_typ(String option_typ){
		this.option_typ=option_typ;
	}
	public String getOpen(){
		return open;
	}
	public void setOpen(String open ){
		this.open=open;
	}
	public String getHigh(){
		return high;
	}
	public void setHigh(String high){
		this.high=high;
	}
	public String getLow(){
		return high;
	}
	public void setLow(String low){
		this.low=low;
	}
	public String getClose(){
		return close;
	}
	public void setClose(String close){
		this.close=close;
	}
	public String getSettle_pr(){
		return settle_pr;
	}
	public void setSettle_pr(String settle_pr){
		this.settle_pr=settle_pr;
	}
	public String getContracts(){
		return contracts;
	}
	public void setContracts(String contracts){
		this.contracts=contracts;
	}
	public String getVal_inlakh(){
		return val_inlakh;
	}
	public void setVal_inlakh(String val_inlakh){
		this.val_inlakh=val_inlakh;
	}
	public String getOpen_int(){
		return open_int;
	}
	public void setOpen_int(String open_int){
		this.open_int=open_int;
	}
	public String getChange_in_ol(){
		return change_in_ol;
	}
	public void setChange_in_ol(String change_in_ol){
		this.change_in_ol=change_in_ol;
	}
	public String getTimestamp(){
		return timestamp;
	}
	public void setTimestamp(String timestamp){
		this.timestamp=timestamp;
	}
}
