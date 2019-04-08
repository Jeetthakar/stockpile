/*
 * Created on Aug 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

/**
 * @author ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Security {
	short Trans_c;
	long Time_stamp;
	short Message_l;
	short Token_Number;
	String symbol ;
	String Series ;
	long Issued_Capital;
	short Warning_Percent;
	short Freeze_Percent;				 	
	String Credit_Rating;					
	short Issue_Rate;				 	
	long  Issue_Start_Date;				
	long Issue_Pdate;				 	
	long  Issue_Maturity_Date;		
	int Board_Lot_Quantity;  		
	int  Tick_Size ;                       			
	String Name_of_Company ;      		 	
	long Record_Date ;                			
	long Expiry_Date ;                  			
	long  No_Delivery_Start_Date;				
	long  No_Delivery_End_Date;     			
	long Book_Closure_Start_Date ;		 	
	long  Book_Closure_End_Date;   		

	/**
	 * @return Returns the board_Lot_Quantity.
	 */
	public int getBoard_Lot_Quantity() {
		return Board_Lot_Quantity;
	}
	/**
	 * @param board_Lot_Quantity The board_Lot_Quantity to set.
	 */
	public void setBoard_Lot_Quantity(int board_Lot_Quantity) {
		Board_Lot_Quantity = board_Lot_Quantity;
	}
	
	/**
	 * @return Returns the credit_Rating.
	 */
	public String getCredit_Rating() {
		return Credit_Rating;
	}
	/**
	 * @param credit_Rating The credit_Rating to set.
	 */
	public void setCredit_Rating(String credit_Rating) {
		Credit_Rating = credit_Rating;
	}
	
	/**
	 * @return Returns the freeze_Percent.
	 */
	public short getFreeze_Percent() {
		return Freeze_Percent;
	}
	/**
	 * @param freeze_Percent The freeze_Percent to set.
	 */
	public void setFreeze_Percent(short freeze_Percent) {
		Freeze_Percent = freeze_Percent;
	}
	
	
	/**
	 * @return Returns the issue_Rate.
	 */
	public short getIssue_Rate() {
		return Issue_Rate;
	}
	/**
	 * @param issue_Rate The issue_Rate to set.
	 */
	public void setIssue_Rate(short issue_Rate) {
		Issue_Rate = issue_Rate;
	}
	
	/**
	 * @return Returns the issued_Capital.
	 */
	public long getIssued_Capital() {
		return Issued_Capital;
	}
	/**
	 * @param issued_Capital The issued_Capital to set.
	 */
	public void setIssued_Capital(long issued_Capital) {
		Issued_Capital = issued_Capital;
	}
	/**
	 * @return Returns the message_l.
	 */
	public short getMessage_l() {
		return Message_l;
	}
	/**
	 * @param message_l The message_l to set.
	 */
	public void setMessage_l(short message_l) {
		Message_l = message_l;
	}
	/**
	 * @return Returns the name_of_Company.
	 */
	public String getName_of_Company() {
		return Name_of_Company;
	}
	/**
	 * @param name_of_Company The name_of_Company to set.
	 */
	public void setName_of_Company(String name_of_Company) {
		Name_of_Company = name_of_Company;
	}
	
	
	
	/**
	 * @return Returns the book_Closure_End_Date.
	 */
	public long getBook_Closure_End_Date() {
		return Book_Closure_End_Date;
	}
	/**
	 * @param book_Closure_End_Date The book_Closure_End_Date to set.
	 */
	public void setBook_Closure_End_Date(long book_Closure_End_Date) {
		Book_Closure_End_Date = book_Closure_End_Date;
	}
	/**
	 * @return Returns the book_Closure_Start_Date.
	 */
	public long getBook_Closure_Start_Date() {
		return Book_Closure_Start_Date;
	}
	/**
	 * @param book_Closure_Start_Date The book_Closure_Start_Date to set.
	 */
	public void setBook_Closure_Start_Date(long book_Closure_Start_Date) {
		Book_Closure_Start_Date = book_Closure_Start_Date;
	}
	/**
	 * @return Returns the expiry_Date.
	 */
	public long getExpiry_Date() {
		return Expiry_Date;
	}
	/**
	 * @param expiry_Date The expiry_Date to set.
	 */
	public void setExpiry_Date(long expiry_Date) {
		Expiry_Date = expiry_Date;
	}
	/**
	 * @return Returns the issue_Maturity_Date.
	 */
	public long getIssue_Maturity_Date() {
		return Issue_Maturity_Date;
	}
	/**
	 * @param issue_Maturity_Date The issue_Maturity_Date to set.
	 */
	public void setIssue_Maturity_Date(long issue_Maturity_Date) {
		Issue_Maturity_Date = issue_Maturity_Date;
	}
	/**
	 * @return Returns the issue_Pdate.
	 */
	public long getIssue_Pdate() {
		return Issue_Pdate;
	}
	/**
	 * @param issue_Pdate The issue_Pdate to set.
	 */
	public void setIssue_Pdate(long issue_Pdate) {
		Issue_Pdate = issue_Pdate;
	}
	/**
	 * @return Returns the issue_Start_Date.
	 */
	public long getIssue_Start_Date() {
		return Issue_Start_Date;
	}
	/**
	 * @param issue_Start_Date The issue_Start_Date to set.
	 */
	public void setIssue_Start_Date(long issue_Start_Date) {
		Issue_Start_Date = issue_Start_Date;
	}
	/**
	 * @return Returns the no_Delivery_End_Date.
	 */
	public long getNo_Delivery_End_Date() {
		return No_Delivery_End_Date;
	}
	/**
	 * @param no_Delivery_End_Date The no_Delivery_End_Date to set.
	 */
	public void setNo_Delivery_End_Date(long no_Delivery_End_Date) {
		No_Delivery_End_Date = no_Delivery_End_Date;
	}
	/**
	 * @return Returns the no_Delivery_Start_Date.
	 */
	public long getNo_Delivery_Start_Date() {
		return No_Delivery_Start_Date;
	}
	/**
	 * @param no_Delivery_Start_Date The no_Delivery_Start_Date to set.
	 */
	public void setNo_Delivery_Start_Date(long no_Delivery_Start_Date) {
		No_Delivery_Start_Date = no_Delivery_Start_Date;
	}
	/**
	 * @return Returns the record_Date.
	 */
	public long getRecord_Date() {
		return Record_Date;
	}
	/**
	 * @param record_Date The record_Date to set.
	 */
	public void setRecord_Date(long record_Date) {
		Record_Date = record_Date;
	}
	/**
	 * @return Returns the series.
	 */
	public String getSeries() {
		return Series;
	}
	/**
	 * @param series The series to set.
	 */
	public void setSeries(String series) {
		Series = series;
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
	/**
	 * @return Returns the tick_Size.
	 */
	public int getTick_Size() {
		return Tick_Size;
	}
	/**
	 * @param tick_Size The tick_Size to set.
	 */
	public void setTick_Size(int tick_Size) {
		Tick_Size = tick_Size;
	}
	
	/**
	 * @return Returns the time_stamp.
	 */
	public long getTime_stamp() {
		return Time_stamp;
	}
	/**
	 * @param time_stamp The time_stamp to set.
	 */
	public void setTime_stamp(long time_stamp) {
		Time_stamp = time_stamp;
	}
	/**
	 * @return Returns the token_Number.
	 */
	public short getToken_Number() {
		return Token_Number;
	}
	/**
	 * @param token_Number The token_Number to set.
	 */
	public void setToken_Number(short token_Number) {
		Token_Number = token_Number;
	}
	/**
	 * @return Returns the trans_c.
	 */
	public short getTrans_c() {
		return Trans_c;
	}
	/**
	 * @param trans_c The trans_c to set.
	 */
	public void setTrans_c(short trans_c) {
		Trans_c = trans_c;
	}
	/**
	 * @return Returns the warning_Percent.
	 */
	public short getWarning_Percent() {
		return Warning_Percent;
	}
	/**
	 * @param warning_Percent The warning_Percent to set.
	 */
	public void setWarning_Percent(short warning_Percent) {
		Warning_Percent = warning_Percent;
	}
}
