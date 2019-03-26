/*
 * Created on Sep 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.FormBean;
//import org.apache.struts.validator.ValidatorForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * @author manish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NewIndexForm extends ActionForm{
	 Logger Logging = Logger.getLogger(NewIndexForm.class);
private String b_isNewIndexIsChild,s_alertPercent,s_rejectionPercent,
s_parentIndex,b_isIndexIsChildOrCustomized, 
b_isIndexCustomizedVersion,
i_indexID,
s_indexName,
s_indexType,
d_creationDate,
d_baseDate,
s_industryClassificationID,
s_applyClassification,
s_baseCurrency,
d_baseValue,
s_reutersCode,
d_startTime,
d_stopTime,
s_ISIN,
b_isActive,
b_isCaptured,
i_timeInterval,
b_computeSettlementValue,
s_capturedFrom,
s_type;



/**
 * @return Returns the b_computeSettlementValue.
 */
public String getB_computeSettlementValue() {
	return b_computeSettlementValue;
}
/**
 * @param settlementValue The b_computeSettlementValue to set.
 */
public void setB_computeSettlementValue(String settlementValue) {
	b_computeSettlementValue = settlementValue;
	Logging.debug("settlementValue");
}
/**
 * @return Returns the b_isActive.
 */
public String getB_isActive() {
	return b_isActive;
}
/**
 * @param active The b_isActive to set.
 */
public void setB_isActive(String active) {
	b_isActive = active;
}
/**
 * @return Returns the b_isCaptured.
 */
public String getB_isCaptured() {
	return b_isCaptured;
}
/**
 * @param captured The b_isCaptured to set.
 */
public void setB_isCaptured(String captured) {
	b_isCaptured = captured;
}
/**
 * @return Returns the b_isIndexCustomizedVersion.
 */
public String getB_isIndexCustomizedVersion() {
	return b_isIndexCustomizedVersion;
}
/**
 * @param indexCustomizedVersion The b_isIndexCustomizedVersion to set.
 */
public void setB_isIndexCustomizedVersion(String indexCustomizedVersion) {
	b_isIndexCustomizedVersion = indexCustomizedVersion;
}
/**
 * @return Returns the b_isNewIndexIsChild.
 */
public String getB_isNewIndexIsChild() {
	return b_isNewIndexIsChild;
}
/**
 * @param newIndexIsChild The b_isNewIndexIsChild to set.
 */
public void setB_isNewIndexIsChild(String newIndexIsChild) {
	b_isNewIndexIsChild = newIndexIsChild;
}
/**
 * @return Returns the d_baseDate.
 */
public String getD_baseDate() {
	return d_baseDate;
}
/**
 * @param date The d_baseDate to set.
 */
public void setD_baseDate(String date) {
	d_baseDate = date;
}
/**
 * @return Returns the d_baseValue.
 */
public String getD_baseValue() {
	return d_baseValue;
}
/**
 * @param value The d_baseValue to set.
 */
public void setD_baseValue(String value) {
	d_baseValue = value;
}
/**
 * @return Returns the d_creationDate.
 */
public String getD_creationDate() {
	return d_creationDate;
}
/**
 * @param date The d_creationDate to set.
 */
public void setD_creationDate(String date) {
	d_creationDate = date;
}
/**
 * @return Returns the d_startTime.
 */
public String getD_startTime() {
	return d_startTime;
}
/**
 * @param time The d_startTime to set.
 */
public void setD_startTime(String time) {
	d_startTime = time;
}
/**
 * @return Returns the d_stopTime.
 */
public String getD_stopTime() {
	return d_stopTime;
}
/**
 * @param time The d_stopTime to set.
 */
public void setD_stopTime(String time) {
	d_stopTime = time;
}
/**
 * @return Returns the i_indexID.
 */
public String getI_indexID() {
	return i_indexID;
}
/**
 * @param i_indexid The i_indexID to set.
 */
public void setI_indexID(String i_indexid) {
	i_indexID = i_indexid;
}
/**
 * @return Returns the i_timeInterval.
 */
public String getI_timeInterval() {
	return i_timeInterval;
}
/**
 * @param interval The i_timeInterval to set.
 */
public void setI_timeInterval(String interval) {
	i_timeInterval = interval;
}
/**
 * @return Returns the s_applyClassification.
 */
public String getS_applyClassification() {
	return s_applyClassification;
}
/**
 * @param classification The s_applyClassification to set.
 */
public void setS_applyClassification(String classification) {
	s_applyClassification = classification;
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
 * @return Returns the s_industryClassificationID.
 */
public String getS_industryClassificationID() {
	return s_industryClassificationID;
}
/**
 * @param classificationID The s_industryClassificationID to set.
 */
public void setS_industryClassificationID(String classificationID) {
	s_industryClassificationID = classificationID;
}
/**
 * @return Returns the s_ISIN.
 */
public String getS_ISIN() {
	return s_ISIN;
}
/**
 * @param s_isin The s_ISIN to set.
 */
public void setS_ISIN(String s_isin) {
	s_ISIN = s_isin;
}
/**
 * @return Returns the s_reutersCode.
 */
public String getS_reutersCode() {
	return s_reutersCode;
}
/**
 * @param code The s_reutersCode to set.
 */
public void setS_reutersCode(String code) {
	s_reutersCode = code;
}
/**
 * @return Returns the s_type.
 */
public String getS_type() {
	return s_type;
}
/**
 * @param s_type The s_type to set.
 */
public void setS_type(String s_type) {
	this.s_type = s_type;
}
/**
 * @return Returns the s_parentIndex.
 */
public String getS_parentIndex() {
	return s_parentIndex;
}
/**
 * @param index The s_parentIndex to set.
 */
public void setS_parentIndex(String index) {
	s_parentIndex = index;
}
/**
 * @return Returns the s_alertPercent.
 */
public String getS_alertPercent() {
	return s_alertPercent;
}
/**
 * @param percent The s_alertPercent to set.
 */
public void setS_alertPercent(String percent) {
	s_alertPercent = percent;
}
/**
 * @return Returns the s_rejectionPercent.
 */
public String getS_rejectionPercent() {
	return s_rejectionPercent;
}
/**
 * @param percent The s_rejectionPercent to set.
 */
public void setS_rejectionPercent(String percent) {
	s_rejectionPercent = percent;
}
/**
 * @return Returns the b_isIndexIsChildOrCustomized.
 */
public String getB_isIndexIsChildOrCustomized() {
		
	
	return b_isIndexIsChildOrCustomized;
}
/**
 * @param indexIsChildOrCustomized The b_isIndexIsChildOrCustomized to set.
 */
public void setB_isIndexIsChildOrCustomized(String indexIsChildOrCustomized) {
	b_isIndexIsChildOrCustomized = indexIsChildOrCustomized;
	
	
}
public void reset(ActionMapping mapping, HttpServletRequest request)
{
	b_isNewIndexIsChild =null;
	s_alertPercent=null;
	s_rejectionPercent=null;
	s_parentIndex =null;
	b_isIndexIsChildOrCustomized=null; 
	b_isIndexCustomizedVersion=null;
	i_indexID=null;
	s_indexName=null;
	s_indexType=null;
	d_creationDate=null;
	d_baseDate=null;
	s_industryClassificationID=null;
	s_applyClassification=null;
	s_baseCurrency=null;
	d_baseValue=null;
	s_reutersCode=null;
	d_startTime=null;
	d_stopTime=null;
	s_ISIN=null;
	b_isActive=null;
	b_isCaptured=null;
	i_timeInterval=null;
	b_computeSettlementValue=null;
	s_capturedFrom=null;
	s_type=null;
}
public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
{
	ActionErrors errors = new ActionErrors();
	 Logging.debug("Inside Validation");
	Logging.debug(b_isIndexIsChildOrCustomized);
	if(s_indexType =="0")
	{
		errors.add("s_indexType",new ActionError("Error.message.IndexType"));
		Logging.debug("Inside Validation1");
	}

	if(s_industryClassificationID =="0")
	{
		errors.add(null,new ActionError("Error.message.IndustryClassification"));
		Logging.debug("Inside Validation2");
	}
	
	if(s_indexName ==null )//|| s_indexName.charAt(0)==' ')
		{
			errors.add(null,new ActionError("Error.message.IndexName"));
			Logging.debug("Inside Validation3");
		}
	
	if(s_parentIndex =="0")
		{
			errors.add(null,new ActionError("Error.message.ParentIndex"));
			Logging.debug("Inside Validation4");
		}
	
	if(b_isIndexIsChildOrCustomized ==null)
		{
			errors.add(null,new ActionError("Error.message.ChildIndexAndCustomizedVersion"));
			Logging.debug("Inside Validation5");
		}
	
	//if(s_alertPercent == null)  & s_rejectionPercent )
	
	if(s_type==null)
		{
			errors.add(null,new ActionError("Error.message.Nature"));
			Logging.debug("Inside Validation6");
		}
	
	if(d_baseDate == null)
		{
			errors.add(null,new ActionError("Error.message.BaseDate"));
			Logging.debug("Inside Validation7");
		}
	
	 if(!Validate(d_startTime))
	 {
	   errors.add(null,new ActionError("Error.message.StartTime"));
	 }
	 
	/** if(!Validate(d_stopTime))
	 {
	   errors.add(null,new ActionError("Error.message.StopTime"));
	 }
	 if(!isValid(d_baseValue)) 
	 {
	 	errors.add(null,new ActionError("Error.mesage.BaseValue"));	
	 }*/
	 
	 	 
	 return errors;

  }


boolean Validate(String time){
	String []arr  = time.split(":");		
	String []temp = {"23","59","59"};
	if(arr.length < 3 || arr.length > 3)
		return false;
	for(int i=0;i<arr.length;i++){
		//Logging.debug(arr[i]);
		if(arr[i].compareTo(temp[i])>0)
			return false;					
	}
	return true;
 }


}
