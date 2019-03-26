/*
 * Created on May 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.harrier.initializeation.ConnectInit;
/**
 * @author pranoti 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StockMergerForm extends ActionForm{
	Logger Logging = Logger.getLogger(StockMergerForm.class);	
	private String exc1=null,stock1=null,share1=null,share2=null,no_share=null,apply_date=null,affectedIndex1=null,
			indexval1=null,tmcv1=null,newtmcv1=null,divisor1=null,newdivisor1=null;
	private String exch1,name1,cur1,iwfstk1,com1,tis1,close1,face1,coun1,rate1,nature1,
			exch2,name2,cur2,iwfstk2,com2,tis2,close2,face2,coun2,rate2,nature2;
	private String exc2=null,stock2=null,adjust=null,newTIS=null,affectedIndex2=null,
	indexval2=null,tmcv2=null,newtmcv2=null,divisor2=null,newdivisor2=null;
	private String stock3=null,exc3=null,tis_iss=null;
	private String mergeButt=null,commit_butt=null,merge_type=null,temp_type=null,merge_info=null,rad_butt=null,rad_trans=null;
	
	/**
	 * @return Returns the exc1.
	 */
	public String getExc1() {
		return exc1;
	}
	/**
	 * @param exc1 The exc1 to set.
	 */
	public void setExc1(String exc1) {
		this.exc1 = exc1;
	}
	/**
	 * @return Returns the stock1.
	 */
	public String getStock1() {
		return stock1;
	}
	/**
	 * @param stock1 The stock1 to set.
	 */
	public void setStock1(String stock1) {
		this.stock1 = stock1;
	}
	/**
	 * @return Returns the cur1.
	 */
	public String getCur1() {
		return cur1;
	}
	/**
	 * @param cur1 The cur1 to set.
	 */
	public void setCur1(String cur1) {
		this.cur1 = cur1;
	}
	/**
	 * @return Returns the exch1.
	 */
	public String getExch1() {
		return exch1;
	}
	/**
	 * @param exch1 The exch1 to set.
	 */
	public void setExch1(String exch1) {
		this.exch1 = exch1;
	}
	/**
	 * @return Returns the iwfstk1.
	 */
	public String getIwfstk1() {
		return iwfstk1;
	}
	/**
	 * @param iwfstk1 The iwfstk1 to set.
	 */
	public void setIwfstk1(String iwfstk1) {
		this.iwfstk1 = iwfstk1;
	}
	/**
	 * @return Returns the name1.
	 */
	public String getName1() {
		return name1;
	}
	/**
	 * @param name1 The name1 to set.
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}
	/**
	 * @return Returns the close1.
	 */
	public String getClose1() {
		return close1;
	}
	/**
	 * @param close1 The close1 to set.
	 */
	public void setClose1(String close1) {
		this.close1 = close1;
	}
	/**
	 * @return Returns the com1.
	 */
	public String getCom1() {
		return com1;
	}
	/**
	 * @param com1 The com1 to set.
	 */
	public void setCom1(String com1) {
		this.com1 = com1;
	}
	/**
	 * @return Returns the face1.
	 */
	public String getFace1() {
		return face1;
	}
	/**
	 * @param face1 The face1 to set.
	 */
	public void setFace1(String face1) {
		this.face1 = face1;
	}
	/**
	 * @return Returns the tis1.
	 */
	public String getTis1() {
		return tis1;
	}
	/**
	 * @param tis1 The tis1 to set.
	 */
	public void setTis1(String tis1) {
		this.tis1 = tis1;
	}
	/**
	 * @return Returns the coun1.
	 */
	public String getCoun1() {
		return coun1;
	}
	/**
	 * @param coun1 The coun1 to set.
	 */
	public void setCoun1(String coun1) {
		this.coun1 = coun1;
	}
	/**
	 * @return Returns the nature1.
	 */
	public String getNature1() {
		return nature1;
	}
	/**
	 * @param nature1 The nature1 to set.
	 */
	public void setNature1(String nature1) {
		this.nature1 = nature1;
	}
	/**
	 * @return Returns the rate1.
	 */
	public String getRate1() {
		return rate1;
	}
	/**
	 * @param rate1 The rate1 to set.
	 */
	public void setRate1(String rate1) {
		this.rate1 = rate1;
	}
	/**
	 * @return Returns the share.
	 */
	public String getShare1() {
		return share1;
	}
	/**
	 * @param share The share to set.
	 */
	public void setShare1(String share1) {
		this.share1 = share1;
	}
	
	/**
	 * @return Returns the share2.
	 */
	public String getShare2() {
		return share2;
	}
	/**
	 * @param share2 The share2 to set.
	 */
	public void setShare2(String share2) {
		this.share2 = share2;
	}
	
	/**
	 * @return Returns the no_share.
	 */
	public String getNo_share() {
		return no_share;
	}
	/**
	 * @param no_share The no_share to set.
	 */
	public void setNo_share(String no_share) {
		this.no_share = no_share;
	}
	/**
	 * @return Returns the apply_date.
	 */
	public String getApply_date() {
		return apply_date;
	}
	/**
	 * @param apply_date The apply_date to set.
	 */
	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}
	/**
	 * @return Returns the affectedIndex1.
	 */
	public String getAffectedIndex1() {
		return affectedIndex1;
	}
	/**
	 * @param affectedIndex1 The affectedIndex1 to set.
	 */
	public void setAffectedIndex1(String affectedIndex1) {
		this.affectedIndex1 = affectedIndex1;
	}
	/**
	 * @return Returns the indexval1.
	 */
	public String getIndexval1() {
		return indexval1;
	}
	/**
	 * @param indexval1 The indexval1 to set.
	 */
	public void setIndexval1(String indexval1) {
		this.indexval1 = indexval1;
	}
	
	/**
	 * @return Returns the tmcv1.
	 */
	public String getTmcv1() {
		return tmcv1;
	}
	/**
	 * @param tmcv1 The tmcv1 to set.
	 */
	public void setTmcv1(String tmcv1) {
		this.tmcv1 = tmcv1;
	}
	/**
	 * @return Returns the newtmcv1.
	 */
	public String getNewtmcv1() {
		return newtmcv1;
	}
	/**
	 * @param newtmcv1 The newtmcv1 to set.
	 */
	public void setNewtmcv1(String newtmcv1) {
		this.newtmcv1 = newtmcv1;
	}	
	/**
	 * @return Returns the divisor1.
	 */
	public String getDivisor1() {
		return divisor1;
	}
	/**
	 * @param divisor1 The divisor1 to set.
	 */
	public void setDivisor1(String divisor1) {
		this.divisor1 = divisor1;
	}
	/**
	 * @return Returns the newdivisor1.
	 */
	public String getNewdivisor1() {
		return newdivisor1;
	}
	/**
	 * @param newdivisor1 The newdivisor1 to set.
	 */
	public void setNewdivisor1(String newdivisor1) {
		this.newdivisor1 = newdivisor1;
	}
	/**
	 * @return Returns the exc2.
	 */
	public String getExc2() {
		return exc2;
	}
	/**
	 * @param exc2 The exc2 to set.
	 */
	public void setExc2(String exc2) {
		this.exc2 = exc2;
	}
	/**
	 * @return Returns the stock2.
	 */
	public String getStock2() {
		return stock2;
	}
	/**
	 * @param stock2 The stock2 to set.
	 */
	public void setStock2(String stock2) {
		this.stock2 = stock2;
	}
	/**
	 * @return Returns the close2.
	 */
	public String getClose2() {
		return close2;
	}
	/**
	 * @param close2 The close2 to set.
	 */
	public void setClose2(String close2) {
		this.close2 = close2;
	}
	/**
	 * @return Returns the com2.
	 */
	public String getCom2() {
		return com2;
	}
	/**
	 * @param com2 The com2 to set.
	 */
	public void setCom2(String com2) {
		this.com2 = com2;
	}
	/**
	 * @return Returns the coun2.
	 */
	public String getCoun2() {
		return coun2;
	}
	/**
	 * @param coun2 The coun2 to set.
	 */
	public void setCoun2(String coun2) {
		this.coun2 = coun2;
	}
	/**
	 * @return Returns the cur2.
	 */
	public String getCur2() {
		return cur2;
	}
	/**
	 * @param cur2 The cur2 to set.
	 */
	public void setCur2(String cur2) {
		this.cur2 = cur2;
	}
	/**
	 * @return Returns the exch2.
	 */
	public String getExch2() {
		return exch2;
	}
	/**
	 * @param exch2 The exch2 to set.
	 */
	public void setExch2(String exch2) {
		this.exch2 = exch2;
	}
	/**
	 * @return Returns the face2.
	 */
	public String getFace2() {
		return face2;
	}
	/**
	 * @param face2 The face2 to set.
	 */
	public void setFace2(String face2) {
		this.face2 = face2;
	}
	/**
	 * @return Returns the iwfstk2.
	 */
	public String getIwfstk2() {
		return iwfstk2;
	}
	/**
	 * @param iwfstk2 The iwfstk2 to set.
	 */
	public void setIwfstk2(String iwfstk2) {
		this.iwfstk2 = iwfstk2;
	}
	/**
	 * @return Returns the name2.
	 */
	public String getName2() {
		return name2;
	}
	/**
	 * @param name2 The name2 to set.
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}
	/**
	 * @return Returns the nature2.
	 */
	public String getNature2() {
		return nature2;
	}
	/**
	 * @param nature2 The nature2 to set.
	 */
	public void setNature2(String nature2) {
		this.nature2 = nature2;
	}
	/**
	 * @return Returns the rate2.
	 */
	public String getRate2() {
		return rate2;
	}
	/**
	 * @param rate2 The rate2 to set.
	 */
	public void setRate2(String rate2) {
		this.rate2 = rate2;
	}
	/**
	 * @return Returns the tis2.
	 */
	public String getTis2() {
		return tis2;
	}
	/**
	 * @param tis2 The tis2 to set.
	 */
	public void setTis2(String tis2) {
		this.tis2 = tis2;
	}
	/**
	 * @return Returns the adjust.
	 */
	public String getAdjust() {
		return adjust;
	}
	/**
	 * @param adjust The adjust to set.
	 */
	public void setAdjust(String adjust) {
		this.adjust = adjust;
	}
	/**
	 * @return Returns the newTIS.
	 */
	public String getNewTIS() {
		return newTIS;
	}
	/**
	 * @param newTIS The newTIS to set.
	 */
	public void setNewTIS(String newTIS) {
		this.newTIS = newTIS;
	}
	/**
	 * @return Returns the affectedIndex2.
	 */
	public String getAffectedIndex2() {
		return affectedIndex2;
	}
	/**
	 * @param affectedIndex2 The affectedIndex2 to set.
	 */
	public void setAffectedIndex2(String affectedIndex2) {
		this.affectedIndex2 = affectedIndex2;
	}
	/**
	 * @return Returns the divisor2.
	 */
	public String getDivisor2() {
		return divisor2;
	}
	/**
	 * @param divisor2 The divisor2 to set.
	 */
	public void setDivisor2(String divisor2) {
		this.divisor2 = divisor2;
	}
	/**
	 * @return Returns the indexval2.
	 */
	public String getIndexval2() {
		return indexval2;
	}
	/**
	 * @param indexval2 The indexval2 to set.
	 */
	public void setIndexval2(String indexval2) {
		this.indexval2 = indexval2;
	}
	/**
	 * @return Returns the newdivisor2.
	 */
	public String getNewdivisor2() {
		return newdivisor2;
	}
	/**
	 * @param newdivisor2 The newdivisor2 to set.
	 */
	public void setNewdivisor2(String newdivisor2) {
		this.newdivisor2 = newdivisor2;
	}
	/**
	 * @return Returns the newtmcv2.
	 */
	public String getNewtmcv2() {
		return newtmcv2;
	}
	/**
	 * @param newtmcv2 The newtmcv2 to set.
	 */
	public void setNewtmcv2(String newtmcv2) {
		this.newtmcv2 = newtmcv2;
	}
	/**
	 * @return Returns the tmcv2.
	 */
	public String getTmcv2() {
		return tmcv2;
	}
	/**
	 * @param tmcv2 The tmcv2 to set.
	 */
	public void setTmcv2(String tmcv2) {
		this.tmcv2 = tmcv2;
	}
	
	/**
	 * @return Returns the mergeButt.
	 */
	public String getMergeButt() {
		return mergeButt;
	}
	/**
	 * @param mergeButt The mergeButt to set.
	 */
	public void setMergeButt(String mergeButt) {
		this.mergeButt = mergeButt;
	}
	
	/**
	 * @return Returns the commit_butt.
	 */
	public String getCommit_butt() {
		return commit_butt;
	}
	/**
	 * @param commit_butt The commit_butt to set.
	 */
	public void setCommit_butt(String commit_butt) {
		this.commit_butt = commit_butt;
	}
	
	/**
	 * @return Returns the merge_type.
	 */
	public String getMerge_type() {
		return merge_type;
	}
	/**
	 * @param merge_type The merge_type to set.
	 */
	public void setMerge_type(String merge_type) {
		this.merge_type = merge_type;
	}
	
	/**
	 * @return Returns the merge_info.
	 */
	public String getMerge_info() {
		return merge_info;
	}
	/**
	 * @param merge_info The merge_info to set.
	 */
	public void setMerge_info(String merge_info) {
		this.merge_info = merge_info;
	}
	
	/**
	 * @return Returns the temp_type.
	 */
	public String getTemp_type() {
		return temp_type;
	}
	/**
	 * @param temp_type The temp_type to set.
	 */
	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type;
	}
	
	/**
	 * @return Returns the rad_butt.
	 */
	public String getRad_butt() {
		return rad_butt;
	}
	/**
	 * @param rad_butt The rad_butt to set.
	 */
	public void setRad_butt(String rad_butt) {
		this.rad_butt = rad_butt;
	}
	
	/**
	 * @return Returns the rad_trans.
	 */
	public String getRad_trans() {
		return rad_trans;
	}
	
	/**
	 * @return Returns the exc3.
	 */
	public String getExc3() {
		return exc3;
	}
	/**
	 * @param exc3 The exc3 to set.
	 */
	public void setExc3(String exc3) {
		this.exc3 = exc3;
	}
	/**
	 * @return Returns the stock3.
	 */
	public String getStock3() {
		return stock3;
	}
	/**
	 * @param stock3 The stock3 to set.
	 */
	public void setStock3(String stock3) {
		this.stock3 = stock3;
	}
	
	/**
	 * @return Returns the tis_iss.
	 */
	public String getTis_iss() {
		return tis_iss;
	}
	/**
	 * @param tis_iss The tis_iss to set.
	 */
	public void setTis_iss(String tis_iss) {
		this.tis_iss = tis_iss;
	}
	/**
	 * @param rad_trans The rad_trans to set.
	 */
	public void setRad_trans(String rad_trans) {
		this.rad_trans = rad_trans;
	}
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		ActionErrors errors = new ActionErrors();	
		
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			try{
			if(con == null){
				 con =connect.getdbConnection();
			}		
			
			try{
				int flag=0;
				Logging.debug("mergebutt in apply=="+mergeButt);
				Logging.debug("exc1 in form=="+exc1);
				if(mergeButt!=null)
				{
					if((mergeButt.equals("Stock1")) | (mergeButt.equals("Apply")))
				{
					String query=ConnectInit.queries.getProperty("detail_stock_master");
					ResultSet rs=ListTypeClass1.getAffected(con,query,stock1);
					rs.next();
					String active=rs.getString("is_active");
					rs.close();
					if(active.equals("d"))
						errors.add("exc1",new ActionError("Error.message.StockStatus"));
				}
				if((mergeButt.equals("Stock2")) | (mergeButt.equals("Apply")))
				{
					String query=ConnectInit.queries.getProperty("detail_stock_master");
					ResultSet rs=ListTypeClass1.getAffected(con,query,stock2);
					rs.next();
					String active=rs.getString("is_active");
					rs.close();
					if(active.equals("d"))
						errors.add("exc1",new ActionError("Error.message.StockStatus"));
				}
				if((mergeButt.equals("Apply")) | mergeButt.equals("Add"))
				{
					if(mergeButt.equals("Apply"))					
						merge_type=null;
					temp_type=null;
					merge_info=null;
					adjust=null;
					newTIS=null;
					rad_butt=null;
					rad_trans=null;
					reset_parameter();
					if(exc1.equals("Select Exchange"))
					{
						flag=1;
						errors.add("exc1",new ActionError("Error.message.Mergeexc1"));
					}
					
					if(stock1.equals("Select Stock"))
					{
						flag=1;					
						errors.add("stk1",new ActionError("Error.message.Mergestk1"));
					}
					
					if(exc2.equals("Select Exchange"))
					{
						flag=1;
						errors.add("exc1",new ActionError("Error.message.Mergeexc2"));
					}
					
					if(stock2.equals("Select Stock"))
					{
						flag=1;
						errors.add("stk1",new ActionError("Error.message.Mergestk2"));
					}		
					
					if((share1==null | share1.equals("")) & (share2==null | share2.equals("")) & (no_share==null | no_share.equals("")))
					{
						flag=1;
						errors.add("stk1",new ActionError("Error.message.Mergeshare1"));
					}
					if(share1!=null & !(share1.equals("")))
					{
						if(share2==null | share2.equals(""))
						{
							flag=1;
							errors.add("stk1",new ActionError("Error.message.Mergeshare1"));
						}
						if(no_share!=null & (!no_share.equals("")) & flag==0)
						{
							flag=1;
							errors.add("stk1",new ActionError("Error.message.Mergeshare1"));
						}
					}
					else
					{
						if(share2!=null & (!(share2.equals(""))))
						{
							if(share1==null | share1.equals(""))
							{
								flag=1;
								errors.add("stk1",new ActionError("Error.message.Mergeshare1"));
							}
							if(no_share!=null & (!no_share.equals("")) & flag==0)
							{
								flag=1;
								errors.add("stk1",new ActionError("Error.message.Mergeshare1"));
							}
						}
						else
						{
							if(no_share!=null & (!(no_share.equals(""))))
							{
								if((share1!=null & (!(share1.equals("")))) || (share2!=null & (!(share2.equals("")))))
								{
									flag=1;
									errors.add("stk1",new ActionError("Error.message.Mergeshare1"));
								}
							}
						}
					}
					if(stock1.equals(stock2))
					{
						flag=1;
						if(mergeButt.equals("Apply"))
							errors.add("stk",new ActionError("Error.message.Samemerge"));
						if(mergeButt.equals("Add"))
							errors.add("stk",new ActionError("Error.message.Addmerge"));
					}
					if(flag==0)  //check for stock's closing value
					{
						if(close1==null | close1.equals("") | close1.equals("0"))
							errors.add("stk1",new ActionError("Error.message.Mergeclose1"));
						
						if(close2==null | close2.equals("") | close2.equals("0"))
							errors.add("stk1",new ActionError("Error.message.Mergeclose2"));
					}
				}//Apply button
				if(mergeButt.equals("Radio"))
				{			
					if(rad_butt!=null)
					{	
						String query=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");						
						if(rad_butt.equals("rb") | rad_butt.equals("rt"))
						{
							Hashtable hash1=getHash_aff1();
							int flg=0;
							for(Enumeration enumm=hash1.keys();enumm.hasMoreElements();)
							{
								String id=(String)enumm.nextElement();
								ResultSet rs=ListTypeClass1.getResult12(con,query,id);
								if(rs.next())
								{
									String close=rs.getString("index_closing_value");
									if(close==null)
									{
										flg=1;
										break;
									}
									else
									{
										if(close.equals("0"))
										{
											flg=1;
											break;
										}
									}
								}
								else
								{
									flg=1;
									break;
								}
							}
							if(flg==1)
								errors.add("hash1",new ActionError("Error.message.Mergeclose3"));
						}
						if(rad_butt.equals("rb") | rad_butt.equals("rd"))
						{
							Hashtable hash2=getHash_aff2();							
							int flg=0;
							for(Enumeration enumm=hash2.keys();enumm.hasMoreElements();)
							{
								String id=(String)enumm.nextElement();
								ResultSet rs=ListTypeClass1.getResult12(con,query,id);
								if(rs.next())
								{
									String close=rs.getString("index_closing_value");
									if(close==null)
									{
										flg=1;
										break;
									}
									else
									{
										if(close.equals("0"))
										{
											flg=1;
											break;
										}
									}
								}
								else
								{
									flg=1;
									break;
								}
							}
							if(flg==1)
								errors.add("hash1",new ActionError("Error.message.Mergeclose4"));
						}
					}//rad butt not null
				}//Radio Button
			}
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
			} catch (Exception e) {
				Logging.debug("Error :" + e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
		return errors;
	 }		
		
	private Collection exc1Collection 	= 	null;
	private Collection exc2Collection 	= 	null;
	private Collection stock1Collection 	= 	null;
	private Collection stock2Collection 	= 	null;
	private Collection aff1Collection 	= 	null;
	private Collection aff2Collection 	= 	null;		
		
	ArrayList s1_arr=new ArrayList();
	ArrayList s2_arr=new ArrayList();
	
	Hashtable hash_aff1=new Hashtable();
	Hashtable hash_aff2=new Hashtable();
	
	Hashtable hash_store1=new Hashtable();
	Hashtable hash_store2=new Hashtable();
	
	/**
	 * @return Returns the hash_store1.
	 */
	public Hashtable getHash_store1() {
		return hash_store1;
	}
	/**
	 * @param hash_store1 The hash_store1 to set.
	 */
	public void setHash_store1(Hashtable hash_store1) {
		this.hash_store1 = hash_store1;
	}
	/**
	 * @return Returns the hash_store2.
	 */
	public Hashtable getHash_store2() {
		return hash_store2;
	}
	/**
	 * @param hash_store2 The hash_store2 to set.
	 */
	public void setHash_store2(Hashtable hash_store2) {
		this.hash_store2 = hash_store2;
	}
	/**
	 * @return Returns the hash_aff1.
	 */
	public Hashtable getHash_aff1() {
		return hash_aff1;
	}
	/**
	 * @param hash_aff1 The hash_aff1 to set.
	 */
	public void setHash_aff1(Hashtable hash_aff1) {
		this.hash_aff1 = hash_aff1;
	}
	/**
	 * @return Returns the hash_aff2.
	 */
	public Hashtable getHash_aff2() {
		return hash_aff2;
	}
	/**
	 * @param hash_aff2 The hash_aff2 to set.
	 */
	public void setHash_aff2(Hashtable hash_aff2) {
		this.hash_aff2 = hash_aff2;
	}
	
	/**
	 * @return Returns the s1_arr.
	 */
	public ArrayList getS1_arr() {
		return s1_arr;
	}
	/**
	 * @param s1_arr The s1_arr to set.
	 */
	public void setS1_arr(ArrayList s1_arr) {
		this.s1_arr = s1_arr;
	}
	
	/**
	 * @return Returns the s2_arr.
	 */
	public ArrayList getS2_arr() {
		return s2_arr;
	}
	/**
	 * @param s2_arr The s2_arr to set.
	 */
	public void setS2_arr(ArrayList s2_arr) {
		this.s2_arr = s2_arr;
	}
	Connection con;
	/**
	 * @return Returns the exc1Collection.
	 */
	public Collection getExc1Collection() {
		Vector roles = new Vector();
		
		Connection con=null;
		Connect c = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con==null)
			{
				con = c.getdbConnection();
			}
			
			
			try{
			if(exc1!=null)
				if((exc1.equals(""))|(exc1.equals("Select Exchange")))
					exc1=null;
				
			roles.add(new LabelValueBean("Select Exchange","Select Exchange"));	
			String query =ConnectInit.queries.getProperty("stock_exchange_online_list");	
			if(exc1==null)
			{			
				String qry=ConnectInit.queries.getProperty("select_system_config");				
				ResultSet rs1=ListTypeClass1.resultCorporate(con,qry);
				rs1.next();
				String exc_val=rs1.getString("stock_ex_id");    
				rs1.close();
		        ResultSet rs=ListTypeClass1.resultCorporate(con,query);
		        rs.next();
		        while(rs.next())
		        {
		        	String count=rs.getString("stock_ex_id");		        	
		        	roles.add(new LabelValueBean(rs.getString(2),count));
		        }
			}
			else
			{
				ResultSet rs=ListTypeClass1.resultCorporate(con,query);
				rs.next();
				while(rs.next())
		        {
		        	String count=rs.getString("stock_ex_id");	
		        	if(exc1.equals(rs.getString("stock_ex_id")))
		        	{			        		
		        		Logging.debug("im here");
		        		setExc1(exc1);
		        		roles.add(new LabelValueBean(rs.getString(2),count));	        		
		        	}
		        	else
		        	{
		        		roles.add(new LabelValueBean(rs.getString(2),count));	        		
		        	}
		        }
			}
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		exc1Collection = roles;
		
		return exc1Collection;
	}
	/**
	 * @param exc1Collection The exc1Collection to set.
	 */
	public void setExc1Collection(Collection exc1Collection) {
		this.exc1Collection = exc1Collection;
	}
	/**
	 * @return Returns the exc2Collection.
	 */
	public Collection getExc2Collection() {
		Vector roles = new Vector();
		
		Connection con=null;
		Connect c = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con==null)
			{
				con = c.getdbConnection();
			}

			
			try{
				
			if(exc2!=null)
				if((exc2.equals(""))|(exc2.equals("Select Exchange")))
					exc2=null;
				
			roles.add(new LabelValueBean("Select Exchange","Select Exchange"));	
			String query =ConnectInit.queries.getProperty("stock_exchange_online_list");	
			if(exc2==null)
			{			
				String qry=ConnectInit.queries.getProperty("select_system_config");				
				ResultSet rs1=ListTypeClass1.resultCorporate(con,qry);
				rs1.next();
				String exc_val=rs1.getString("stock_ex_id");    
				rs1.close();
		        ResultSet rs=ListTypeClass1.resultCorporate(con,query);
		        rs.next();
		        while(rs.next())
		        {
		        	String count=rs.getString("stock_ex_id");
		        	roles.add(new LabelValueBean(rs.getString(2),count));
		        }
			}
			else
			{				
				ResultSet rs=ListTypeClass1.resultCorporate(con,query);
				rs.next();
				while(rs.next())
		        {
		        	String count=rs.getString("stock_ex_id");	
		        	if(exc2.equals(rs.getString("stock_ex_id")))
		        	{			        		
		        		Logging.debug("im here");
		        		setExc2(exc2);
		        		roles.add(new LabelValueBean(rs.getString(2),count));	        		
		        	}
		        	else
		        	{
		        		roles.add(new LabelValueBean(rs.getString(2),count));	        		
		        	}
		        }
			}
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		exc2Collection = roles;
		return exc2Collection;
	}
	/**
	 * @param exc2Collection The exc2Collection to set.
	 */
	public void setExc2Collection(Collection exc2Collection) {
		this.exc2Collection = exc2Collection;
	}
	/**
	 * @return Returns the stock1Collection.
	 */
	public Collection getStock1Collection() {
		Vector roles = new Vector();

		Connect connect = ConnectInit.getConnect();
		Connection con=null;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(con == null){
			 con = connect.getdbConnection();
		}	
		
//		for in active stock
		ArrayList a1=getS1_arr();		
		a1.clear();
		setS1_arr(a1);
		a1=getS1_arr();
		
		int position=1;  //for inactive stock display
		try{
			if(exc1!=null)
				if((exc1.equals(""))|(exc1.equals("Select Exchange")))
					exc1=null;
			if(stock1!=null)
				if((stock1.equals(""))|(stock1.equals("Select Stock")))
					stock1=null;	
			roles.add(new LabelValueBean("Select Stock","Select Stock"));	
			if(exc1!=null)
			{
				String query =ConnectInit.queries.getProperty("stock_list");
				ResultSet rs=ListTypeClass1.getAffected(con,query,exc1);
				if(stock1==null)
				{
					while(rs.next())
					{
						String count=rs.getString("stock_id");		
						String active=rs.getString("is_active");
						if(active.equals("d"))
							a1.add(Integer.toString(position));
						roles.add(new LabelValueBean(rs.getString("stock_name"),count));
						position++;
					}					
				}
				else
				{
					while(rs.next())
					{
						String count=rs.getString("stock_id");
						String active=rs.getString("is_active");
						if(active.equals("d"))
						{							
							a1.add(Integer.toString(position));
						}
						if(stock1.equals(count))
						{
							Logging.debug("here in comp");
							setStock1(rs.getString("stock_id"));
							roles.add(new LabelValueBean(rs.getString("stock_name"),count));							
						}
						else
							roles.add(new LabelValueBean(rs.getString("stock_name"),count));
						position++;
					}					
				}	
				setS1_arr(a1);
			}			
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
		}
		
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		stock1Collection=roles;
		return stock1Collection;
	}
	/**
	 * @param stock1Collection The stock1Collection to set.
	 */
	public void setStock1Collection(Collection stock1Collection) {
		this.stock1Collection = stock1Collection;
	}
	
	/**
	 * @return Returns the stock2Collection.
	 */
	public Collection getStock2Collection() {
		Vector roles = new Vector();
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
		
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(con == null){
			 con = connect.getdbConnection();
		}	
		
		//for inactive stock diaply
		ArrayList a2=getS2_arr();
		a2.clear();
		setS2_arr(a2);
		a2=getS2_arr();
		
		int position=1;
		try{
			if(exc2!=null)
				if((exc2.equals(""))|(exc2.equals("Select Exchange")))
					exc2=null;
			if(stock2!=null)
				if((stock2.equals(""))|(stock2.equals("Select Stock")))
					stock2=null;	
			roles.add(new LabelValueBean("Select Stock","Select Stock"));	
			if(exc2!=null)
			{
				String query =ConnectInit.queries.getProperty("stock_list");
				ResultSet rs=ListTypeClass1.getAffected(con,query,exc2);
				if(stock2==null)
				{
					while(rs.next())
					{
						String count=rs.getString("stock_id");
						String active=rs.getString("is_active");
						if(active.equals("d"))
							a2.add(Integer.toString(position));
						roles.add(new LabelValueBean(rs.getString("stock_name"),count));
						position++;
					}
				}
				else
				{
					while(rs.next())
					{
						String count=rs.getString("stock_id");
						String active=rs.getString("is_active");
						if(active.equals("d"))
							a2.add(Integer.toString(position));
						if(stock2.equals(count))
						{
							Logging.debug("here in comp");
							setStock2(rs.getString("stock_id"));
							roles.add(new LabelValueBean(rs.getString("stock_name"),count));
						}
						else
							roles.add(new LabelValueBean(rs.getString("stock_name"),count));
						position++;
					}
				}
				setS2_arr(a2);
			}
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		stock2Collection=roles;
		return stock2Collection;
	}
	/**
	 * @param stock2Collection The stock2Collection to set.
	 */
	public void setStock2Collection(Collection stock2Collection) {
		this.stock2Collection = stock2Collection;
	}
	
	/**
	 * @return Returns the aff1Collection.
	 */
	public Collection getAff1Collection() {
		Vector roles = new Vector();
		
		Connection con=null;
		Connect c = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con==null)
			{
				con = c.getdbConnection();
			}

			
			try{
			ResultSet rs=null;
			Hashtable hash=getHash_aff1();
			hash.clear();
			setHash_aff1(hash);
			if(affectedIndex1==null)
				affectedIndex1="Affected Index";
			
			roles.add(new LabelValueBean("Affected Index","Affected Index"));
			if(stock1!=null & (!stock1.equals("Select Stock"))) 
			{
				String qry=ConnectInit.queries.getProperty("affected_index_by_ca");			
				rs = ListTypeClass1.getResult1(con,qry,stock1);				
				if(affectedIndex1.equals("Affected Index"))
				{
					while(rs.next())
					{	
						String count=rs.getString("index_id");				
						roles.add(new LabelValueBean(rs.getString("index_name"),count));
						
					}
				}
				else
				{
					while(rs.next())
					{
						String count=rs.getString("index_id");
						if(count.equals(affectedIndex1))
							setAffectedIndex1(count);
						roles.add(new LabelValueBean(rs.getString("index_name"),count));
					
					}
				}
				rs.beforeFirst();
				hash=getHash_aff1();
				while(rs.next())
				{
					hash.put(new Integer(rs.getString("index_id")).toString(),new String(rs.getString("index_name")));
				}
				setHash_aff1(hash);
				hash=getHash_aff1();
				Logging.debug("in stock3 affect");
				//for Addition of Stock
				Hashtable temp_hash=new Hashtable();
				temp_hash.clear();
				if(stock3!=null)
				{
					if(!(stock3.equals("")))
					{
						String ind_qry=ConnectInit.queries.getProperty("select_index_name");
						String child_index=ConnectInit.queries.getProperty("select_child_index");
						for(Enumeration enumm=hash.keys();enumm.hasMoreElements();)
						{
							String id=(String)enumm.nextElement();
							Logging.debug("id ==="+id);
							rs=ListTypeClass1.getAffected(con,ind_qry,id);
							rs.next();							
							String parent=rs.getString("parent_id");
							rs.close();
							if(parent==null)
							{
								ResultSet rs1=ListTypeClass1.getResult_corp(con,child_index,id,stock2);
								while(rs1.next())
								{	
									temp_hash.put(new String(rs1.getString("index_id")),rs1.getString("index_name"));
								}
							}//parent null
						}//for
						if(!(temp_hash.isEmpty()))
						{
							for(Enumeration enum1=temp_hash.keys();enum1.hasMoreElements();)
							{
								String temp_id=(String)enum1.nextElement();
								boolean chk_hash=hash.containsKey(temp_id);
								if(chk_hash==false)
								{
									rs=ListTypeClass1.getAffected(con,ind_qry,temp_id);
									rs.next();
									hash.put(new String("index_id"),rs.getString("index_name"));
								}
							}
						}						
					}
				}
				setHash_aff1(hash);
			}			
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		aff1Collection= roles;  
		return aff1Collection;
	}
	/**
	 * @param aff1Collection The aff1Collection to set.
	 */
	public void setAff1Collection(Collection aff1Collection) {		 
		this.aff1Collection = aff1Collection;
	}
	/**
	 * @return Returns the aff2Collection.
	 */
	public Collection getAff2Collection() {
		Vector roles = new Vector();
		
		Connection con=null;
		Connect c = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con==null)
			{
				con = c.getdbConnection();
			}

			
			try{
				
			ResultSet rs=null;
			Hashtable hash=getHash_aff2();
			hash.clear();
			setHash_aff2(hash);
			if(affectedIndex2==null)
				affectedIndex2="Affected Index";
			roles.add(new LabelValueBean("Affected Index","Affected Index"));
			if(stock2!=null &(!(stock2.equals("Select Stock"))) & stock3==null)
			{	
				String qry=ConnectInit.queries.getProperty("affected_index_by_ca");			
				rs = ListTypeClass1.getResult1(con,qry,stock2);				
				if(affectedIndex2.equals("Affected Index"))
				{
					while(rs.next())
					{	
						String count=rs.getString("index_id");				
						roles.add(new LabelValueBean(rs.getString("index_name"),count));					
					}
				}
				else
				{
					while(rs.next())
					{
						String count=rs.getString("index_id");
						if(count.equals(affectedIndex2))
							setAffectedIndex2(count);
						roles.add(new LabelValueBean(rs.getString("index_name"),count));				
					}
				}
				rs.beforeFirst();
				hash=getHash_aff2();
				while(rs.next())
				{
					hash.put(new Integer(rs.getString("index_id")).toString(),new String(rs.getString("index_name")));
				}
				setHash_aff2(hash);
			}
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		aff2Collection= roles;
		return aff2Collection;
	}
	/**
	 * @param aff2Collection The aff2Collection to set.
	 */
	public void setAff2Collection(Collection aff2Collection) {
		this.aff2Collection = aff2Collection;
	}
	public void reset()
	{
		
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(con == null){
			 con = connect.getdbConnection();
		}	
		try{
			String qry=ConnectInit.queries.getProperty("select_system_config");
			Logging.debug("qry=="+qry);
			ResultSet rs1=ListTypeClass1.resultCorporate(con,qry);
			rs1.next();
			setExc1(rs1.getString("stock_ex_id"));  
			setExc2(rs1.getString("stock_ex_id"));
			rs1.close();
			}catch(Exception e){
				Logging.error("error="+e.getMessage());
		}
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		stock1=null;
		stock2=null;
		stock3=null;
		exc3=null;
		Stkreset1();
		Stkreset2();
		reset_val();
		apply_date=UpdateCorp.accept_date();
		merge_type=null;
		temp_type=null;
		merge_info=null;
		commit_butt=null;
		rad_butt=null;
		rad_trans=null;
		reset_parameter();
	}
	public void setStkResult1(ResultSet rs)
	{
		try{
			rs.next();
			setExch1(rs.getString("exchange_code"));
			setName1(rs.getString("stock_name"));
			setCur1(rs.getString("currency_code"));
			setIwfstk1(rs.getString("iwf"));
			setCom1(rs.getString("company_name"));
			setTis1(rs.getString("tis"));
			String close=rs.getString("adjusted_price");
			if(close==null)
			{
				close=rs.getString("stock_closing_value");
				if(close==null)
					setClose1("0");
				else
					setClose1(close);					
			}
			else
				setClose1(close);
			setFace1(rs.getString("face_value"));
			setCoun1(rs.getString("country_name"));
			setRate1(rs.getString("rating_code_name"));
			setNature1(rs.getString("growth_or_value"));			
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	public void setStkResult2(ResultSet rs)
	{
		try{
			rs.next();
			setExch2(rs.getString("exchange_code"));
			setName2(rs.getString("stock_name"));
			setCur2(rs.getString("currency_code"));
			setIwfstk2(rs.getString("iwf"));
			setCom2(rs.getString("company_name"));
			setTis2(rs.getString("tis"));
			String close=rs.getString("adjusted_price");
			if(close==null)
			{
				close=rs.getString("stock_closing_value");
				if(close==null)
					setClose2("0");
				else
					setClose2(close);					
			}
			else
				setClose2(close);
			setFace2(rs.getString("face_value"));
			setCoun2(rs.getString("country_name"));
			setRate2(rs.getString("rating_code_name"));
			setNature2(rs.getString("growth_or_value"));			
		}catch(Exception e){
			Logging.error(" Error="+e.getMessage());
			}
	}
	public void Stkreset1()
	{
		exch1=null;
		name1=null;
		cur1=null;
		iwfstk1=null;
		com1=null;
		tis1=null;
		close1=null;
		face1=null;
		coun1=null;
		rate1=null;
		nature1=null;		
	}
	public void Stkreset2()
	{
		exch2=null;
		name2=null;
		cur2=null;
		iwfstk2=null;
		com2=null;
		tis2=null;
		close2=null;
		face2=null;
		coun2=null;
		rate2=null;
		nature2=null;
	}
	public void ref_hash1()
	{
		Hashtable hash=getHash_aff1();
		hash.clear();
		setHash_aff1(hash);		
		Hashtable hash_store=getHash_store1();
		hash_store.clear();
		setHash_store1(hash_store);
	}
	public void ref_hash2()
	{
		Hashtable hash=getHash_aff2();
		hash.clear();
		setHash_aff2(hash);		
		Hashtable hash_store=getHash_store2();
		hash_store.clear();
		setHash_store2(hash_store);
	}
	public void reset_val()
	{
		share1=null;
		share2=null;
		no_share=null;
		adjust=null;
		newTIS=null;
		commit_butt=null;
		merge_type=null;
		temp_type=null;
		merge_info=null;
		rad_butt=null;
	}
	public void reset_parameter()
	{
		affectedIndex1="Affected Index";
		affectedIndex2="Affected Index";
		indexval1=null;
		indexval2=null;
		tmcv1=null;
		tmcv2=null;
		newtmcv1=null;
		newtmcv2=null;
		divisor1=null;
		divisor2=null;
		newdivisor1=null;
		newdivisor2=null;
	}
	public void reset_store()
	{
		Hashtable hash1=getHash_store1();
		hash1.clear();
		setHash_store1(hash1);
		Hashtable hash2=getHash_store2();
		hash2.clear();
		setHash_store2(hash2);
	}
}
