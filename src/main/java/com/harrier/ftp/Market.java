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
public class Market {
	short Trans_c=0;
	long Time_stamp=0;
	short Message_l=0;
	short Sec_token=0;
	float Last_tr_price=0.0F;
	int Best_by_quant=0;
	float Best_by_price=0.0F;
	int Best_sell_quant=0;
	float Best_sell_price=0.0F;
	int Total_traded_quant=0;
	float Avg_trad_price=0.0F;
	float op=0.0F;
	float hp=0.0F;
	float lp=0.0F;
	float cp=0.0F;
	int filler=0;

	
	/**
	 * @return Returns the avg_trad_price.
	 */
	public float getAvg_trad_price() {
		return Avg_trad_price;
	}
	/**
	 * @param avg_trad_price The avg_trad_price to set.
	 */
	public void setAvg_trad_price(float avg_trad_price) {
		Avg_trad_price = avg_trad_price;
	}
	/**
	 * @return Returns the best_by_price.
	 */
	public float getBest_by_price() {
		return Best_by_price;
	}
	/**
	 * @param best_by_price The best_by_price to set.
	 */
	public void setBest_by_price(float best_by_price) {
		Best_by_price = best_by_price;
	}
	/**
	 * @return Returns the best_by_quant.
	 */
	public int getBest_by_quant() {
		return Best_by_quant;
	}
	/**
	 * @param best_by_quant The best_by_quant to set.
	 */
	public void setBest_by_quant(int best_by_quant) {
		Best_by_quant = best_by_quant;
	}
	/**
	 * @return Returns the best_sell_price.
	 */
	public float getBest_sell_price() {
		return Best_sell_price;
	}
	/**
	 * @param best_sell_price The best_sell_price to set.
	 */
	public void setBest_sell_price(float best_sell_price) {
		Best_sell_price = best_sell_price;
	}
	/**
	 * @return Returns the best_sell_quant.
	 */
	public int getBest_sell_quant() {
		return Best_sell_quant;
	}
	/**
	 * @param best_sell_quant The best_sell_quant to set.
	 */
	public void setBest_sell_quant(int best_sell_quant) {
		Best_sell_quant = best_sell_quant;
	}
	/**
	 * @return Returns the cp.
	 */
	public float getCp() {
		return cp;
	}
	/**
	 * @param cp The cp to set.
	 */
	public void setCp(float cp) {
		this.cp = cp;
	}
	/**
	 * @return Returns the filler.
	 */
	public int getFiller() {
		return filler;
	}
	/**
	 * @param filler The filler to set.
	 */
	public void setFiller(int filler) {
		this.filler = filler;
	}
	/**
	 * @return Returns the hp.
	 */
	public float getHp() {
		return hp;
	}
	/**
	 * @param hp The hp to set.
	 */
	public void setHp(float hp) {
		this.hp = hp;
	}
	/**
	 * @return Returns the last_tr_price.
	 */
	public float getLast_tr_price() {
		return Last_tr_price;
	}
	/**
	 * @param last_tr_price The last_tr_price to set.
	 */
	public void setLast_tr_price(float last_tr_price) {
		Last_tr_price = last_tr_price;
	}
	/**
	 * @return Returns the lp.
	 */
	public float getLp() {
		return lp;
	}
	/**
	 * @param lp The lp to set.
	 */
	public void setLp(float lp) {
		this.lp = lp;
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
	 * @return Returns the op.
	 */
	public float getOp() {
		return op;
	}
	/**
	 * @param op The op to set.
	 */
	public void setOp(float op) {
		this.op = op;
	}
	/**
	 * @return Returns the sec_token.
	 */
	public short getSec_token() {
		return Sec_token;
	}
	/**
	 * @param sec_token The sec_token to set.
	 */
	public void setSec_token(short sec_token) {
		Sec_token = sec_token;
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
	 * @return Returns the total_traded_quant.
	 */
	public int getTotal_traded_quant() {
		return Total_traded_quant;
	}
	/**
	 * @param total_traded_quant The total_traded_quant to set.
	 */
	public void setTotal_traded_quant(int total_traded_quant) {
		Total_traded_quant = total_traded_quant;
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
}
