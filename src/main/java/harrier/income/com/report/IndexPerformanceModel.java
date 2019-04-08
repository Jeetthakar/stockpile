/*
 * Created on Feb 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexPerformanceModel {
String indexId;	
String indexName=null;
String month1=null;
String month3=null;
String month6=null;
String month12=null;

//constructor

IndexPerformanceModel(String id ,String iname,String m1,String m3,String m6,String m12){
this.indexId=id;
this.indexName=iname;	
this.month1=m1;
this.month3=m3;
this.month6=m6;
this.month12=m12;
}

/**
 * @return Returns the indexId.
 */
public String getIndexId() {
	return indexId;
}
/**
 * @param indexId The indexId to set.
 */
public void setIndexId(String indexId) {
	this.indexId = indexId;
}
/**
 * @return Returns the indexName.
 */
public String getIndexName() {
	return indexName;
}
/**
 * @param indexName The indexName to set.
 */
public void setIndexName(String indexName) {
	this.indexName = indexName;
}
/**
 * @return Returns the month1.
 */
public String getMonth1() {
	return month1;
}
/**
 * @param month1 The month1 to set.
 */
public void setMonth1(String month1) {
	this.month1 = month1;
}
/**
 * @return Returns the month12.
 */
public String getMonth12() {
	return month12;
}
/**
 * @param month12 The month12 to set.
 */
public void setMonth12(String month12) {
	this.month12 = month12;
}
/**
 * @return Returns the month3.
 */
public String getMonth3() {
	return month3;
}
/**
 * @param month3 The month3 to set.
 */
public void setMonth3(String month3) {
	this.month3 = month3;
}
/**
 * @return Returns the month6.
 */
public String getMonth6() {
	return month6;
}
/**
 * @param month6 The month6 to set.
 */
public void setMonth6(String month6) {
	this.month6 = month6;
}
}
