/*
 * Created on Sep 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.FormBean;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author manish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorporateActionFormBean extends ActionForm{
	private String s_marketExchange,s_stock,s_sedol,s_isin,	s_cusip,s_ric,s_exchangeCode;
	private String s_ticker,i_stockID,d_stockDate,s_stockName,d_iwfOfStock,s_company;
	private String i_issuedShares,s_stockExchange,s_country,d_faceValue,s_ratingCode;
	private String s_subIndustry,s_adrRatio, b_growth,b_value,s_corporateAction;
	private String f_amount, f_percentage,s_ratio, btn_apply, btn_commit, btn_diary;
	private String btn_cancel, l_sharesAddedReduced, l_outStandingShares;
	private String f_adjustedPrice, s_affectedIndex, d_lastValue,d_oldMarketCap;
	private String l_oldDivisor,d_newMarketCap,l_newDivisor;

	/**
	 * @return Returns the b_growth.
	 */
	public String getB_growth() {
		return b_growth;
	}
	/**
	 * @param b_growth The b_growth to set.
	 */
	public void setB_growth(String b_growth) {
		this.b_growth = b_growth;
	}
	/**
	 * @return Returns the b_value.
	 */
	public String getB_value() {
		return b_value;
	}
	/**
	 * @param b_value The b_value to set.
	 */
	public void setB_value(String b_value) {
		this.b_value = b_value;
	}
	/**
	 * @return Returns the btn_apply.
	 */
	public String getBtn_apply() {
		return btn_apply;
	}
	/**
	 * @param btn_apply The btn_apply to set.
	 */
	public void setBtn_apply(String btn_apply) {
		this.btn_apply = btn_apply;
	}
	/**
	 * @return Returns the btn_cancel.
	 */
	public String getBtn_cancel() {
		return btn_cancel;
	}
	/**
	 * @param btn_cancel The btn_cancel to set.
	 */
	public void setBtn_cancel(String btn_cancel) {
		this.btn_cancel = btn_cancel;
	}
	/**
	 * @return Returns the btn_commit.
	 */
	public String getBtn_commit() {
		return btn_commit;
	}
	/**
	 * @param btn_commit The btn_commit to set.
	 */
	public void setBtn_commit(String btn_commit) {
		this.btn_commit = btn_commit;
	}
	/**
	 * @return Returns the btn_diary.
	 */
	public String getBtn_diary() {
		return btn_diary;
	}
	/**
	 * @param btn_diary The btn_diary to set.
	 */
	public void setBtn_diary(String btn_diary) {
		this.btn_diary = btn_diary;
	}
	/**
	 * @return Returns the d_faceValue.
	 */
	public String getD_faceValue() {
		return d_faceValue;
	}
	/**
	 * @param value The d_faceValue to set.
	 */
	public void setD_faceValue(String value) {
		d_faceValue = value;
	}
	/**
	 * @return Returns the d_iwfOfStock.
	 */
	public String getD_iwfOfStock() {
		return d_iwfOfStock;
	}
	/**
	 * @param ofStock The d_iwfOfStock to set.
	 */
	public void setD_iwfOfStock(String ofStock) {
		d_iwfOfStock = ofStock;
	}
	/**
	 * @return Returns the d_lastValue.
	 */
	public String getD_lastValue() {
		return d_lastValue;
	}
	/**
	 * @param value The d_lastValue to set.
	 */
	public void setD_lastValue(String value) {
		d_lastValue = value;
	}
	/**
	 * @return Returns the d_newMarketCap.
	 */
	public String getD_newMarketCap() {
		return d_newMarketCap;
	}
	/**
	 * @param marketCap The d_newMarketCap to set.
	 */
	public void setD_newMarketCap(String marketCap) {
		d_newMarketCap = marketCap;
	}
	/**
	 * @return Returns the d_oldMarketCap.
	 */
	public String getD_oldMarketCap() {
		return d_oldMarketCap;
	}
	/**
	 * @param marketCap The d_oldMarketCap to set.
	 */
	public void setD_oldMarketCap(String marketCap) {
		d_oldMarketCap = marketCap;
	}
	/**
	 * @return Returns the d_stockDate.
	 */
	public String getD_stockDate() {
		return d_stockDate;
	}
	/**
	 * @param date The d_stockDate to set.
	 */
	public void setD_stockDate(String date) {
		d_stockDate = date;
	}
	/**
	 * @return Returns the f_adjustedPrice.
	 */
	public String getF_adjustedPrice() {
		return f_adjustedPrice;
	}
	/**
	 * @param price The f_adjustedPrice to set.
	 */
	public void setF_adjustedPrice(String price) {
		f_adjustedPrice = price;
	}
	/**
	 * @return Returns the f_amount.
	 */
	public String getF_amount() {
		return f_amount;
	}
	/**
	 * @param f_amount The f_amount to set.
	 */
	public void setF_amount(String f_amount) {
		this.f_amount = f_amount;
	}
	/**
	 * @return Returns the f_percentage.
	 */
	public String getF_percentage() {
		return f_percentage;
	}
	/**
	 * @param f_percentage The f_percentage to set.
	 */
	public void setF_percentage(String f_percentage) {
		this.f_percentage = f_percentage;
	}
	/**
	 * @return Returns the i_issuedShares.
	 */
	public String getI_issuedShares() {
		return i_issuedShares;
	}
	/**
	 * @param shares The i_issuedShares to set.
	 */
	public void setI_issuedShares(String shares) {
		i_issuedShares = shares;
	}
	/**
	 * @return Returns the i_stockID.
	 */
	public String getI_stockID() {
		return i_stockID;
	}
	/**
	 * @param i_stockid The i_stockID to set.
	 */
	public void setI_stockID(String i_stockid) {
		i_stockID = i_stockid;
	}
	/**
	 * @return Returns the l_newDivisor.
	 */
	public String getL_newDivisor() {
		return l_newDivisor;
	}
	/**
	 * @param divisor The l_newDivisor to set.
	 */
	public void setL_newDivisor(String divisor) {
		l_newDivisor = divisor;
	}
	/**
	 * @return Returns the l_oldDivisor.
	 */
	public String getL_oldDivisor() {
		return l_oldDivisor;
	}
	/**
	 * @param divisor The l_oldDivisor to set.
	 */
	public void setL_oldDivisor(String divisor) {
		l_oldDivisor = divisor;
	}
	/**
	 * @return Returns the l_outStandingShares.
	 */
	public String getL_outStandingShares() {
		return l_outStandingShares;
	}
	/**
	 * @param standingShares The l_outStandingShares to set.
	 */
	public void setL_outStandingShares(String standingShares) {
		l_outStandingShares = standingShares;
	}
	/**
	 * @return Returns the l_sharesAddedReduced.
	 */
	public String getL_sharesAddedReduced() {
		return l_sharesAddedReduced;
	}
	/**
	 * @param addedReduced The l_sharesAddedReduced to set.
	 */
	public void setL_sharesAddedReduced(String addedReduced) {
		l_sharesAddedReduced = addedReduced;
	}
	/**
	 * @return Returns the s_adrRatio.
	 */
	public String getS_adrRatio() {
		return s_adrRatio;
	}
	/**
	 * @param ratio The s_adrRatio to set.
	 */
	public void setS_adrRatio(String ratio) {
		s_adrRatio = ratio;
	}
	/**
	 * @return Returns the s_affectedIndex.
	 */
	public String getS_affectedIndex() {
		return s_affectedIndex;
	}
	/**
	 * @param index The s_affectedIndex to set.
	 */
	public void setS_affectedIndex(String index) {
		s_affectedIndex = index;
	}
	/**
	 * @return Returns the s_company.
	 */
	public String getS_company() {
		return s_company;
	}
	/**
	 * @param s_company The s_company to set.
	 */
	public void setS_company(String s_company) {
		this.s_company = s_company;
	}
	/**
	 * @return Returns the s_corporateAction.
	 */
	public String getS_corporateAction() {
		return s_corporateAction;
	}
	/**
	 * @param action The s_corporateAction to set.
	 */
	public void setS_corporateAction(String action) {
		s_corporateAction = action;
	}
	/**
	 * @return Returns the s_country.
	 */
	public String getS_country() {
		return s_country;
	}
	/**
	 * @param s_country The s_country to set.
	 */
	public void setS_country(String s_country) {
		this.s_country = s_country;
	}
	/**
	 * @return Returns the s_cusip.
	 */
	public String getS_cusip() {
		return s_cusip;
	}
	/**
	 * @param s_cusip The s_cusip to set.
	 */
	public void setS_cusip(String s_cusip) {
		this.s_cusip = s_cusip;
	}
	/**
	 * @return Returns the s_exchangeCode.
	 */
	public String getS_exchangeCode() {
		return s_exchangeCode;
	}
	/**
	 * @param code The s_exchangeCode to set.
	 */
	public void setS_exchangeCode(String code) {
		s_exchangeCode = code;
	}
	/**
	 * @return Returns the s_isin.
	 */
	public String getS_isin() {
		return s_isin;
	}
	/**
	 * @param s_isin The s_isin to set.
	 */
	public void setS_isin(String s_isin) {
		this.s_isin = s_isin;
	}
	/**
	 * @return Returns the s_marketExchange.
	 */
	public String getS_marketExchange() {
		return s_marketExchange;
	}
	/**
	 * @param exchange The s_marketExchange to set.
	 */
	public void setS_marketExchange(String exchange) {
		s_marketExchange = exchange;
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
	 * @return Returns the s_ratio.
	 */
	public String getS_ratio() {
		return s_ratio;
	}
	/**
	 * @param s_ratio The s_ratio to set.
	 */
	public void setS_ratio(String s_ratio) {
		this.s_ratio = s_ratio;
	}
	/**
	 * @return Returns the s_ric.
	 */
	public String getS_ric() {
		return s_ric;
	}
	/**
	 * @param s_ric The s_ric to set.
	 */
	public void setS_ric(String s_ric) {
		this.s_ric = s_ric;
	}
	/**
	 * @return Returns the s_sedol.
	 */
	public String getS_sedol() {
		return s_sedol;
	}
	/**
	 * @param s_sedol The s_sedol to set.
	 */
	public void setS_sedol(String s_sedol) {
		this.s_sedol = s_sedol;
	}
	/**
	 * @return Returns the s_stock.
	 */
	public String getS_stock() {
		return s_stock;
	}
	/**
	 * @param s_stock The s_stock to set.
	 */
	public void setS_stock(String s_stock) {
		this.s_stock = s_stock;
	}
	/**
	 * @return Returns the s_stockExchange.
	 */
	public String getS_stockExchange() {
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
		return s_stockName;
	}
	/**
	 * @param name The s_stockName to set.
	 */
	public void setS_stockName(String name) {
		s_stockName = name;
	}
	/**
	 * @return Returns the s_subIndustry.
	 */
	public String getS_subIndustry() {
		return s_subIndustry;
	}
	/**
	 * @param industry The s_subIndustry to set.
	 */
	public void setS_subIndustry(String industry) {
		s_subIndustry = industry;
	}
	/**
	 * @return Returns the s_ticker.
	 */
	public String getS_ticker() {
		return s_ticker;
	}
	/**
	 * @param s_ticker The s_ticker to set.
	 */
	public void setS_ticker(String s_ticker) {
		this.s_ticker = s_ticker;
	}
	public void reset(ActionMapping map ,HttpServletRequest req)
	{
		
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		if (( s_marketExchange== null) ||s_marketExchange.trim().equals("")){
			errors.add("marketExch",
					new ActionError("error.marketExch.required"));
		}
		if (( f_amount == null)|| f_amount.trim().equals("")){
			errors.add(null,new ActionError("error.amount.required"));
			//Logging.debug("amunt not given");
		}
		if (( f_percentage== null)){
			errors.add("f_percentage",
					new ActionError("error.percentage.required"));
		}
		if((s_stock==null)){
			errors.add("s_stock",new ActionError("error.stock.required"));
		}
		return errors;
	}
	
}
