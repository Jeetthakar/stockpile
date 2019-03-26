/*
 * Created on Feb 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.Connect;


import com.harrier.initializeation.ConnectInit;

/**
 * @author Ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexReturnsVolatilityForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexReturnsVolatilityForm.class);
	private String check;
	private String query=null,roleId_ret;
	private String selectInd=null;
	private String indexName=null;
	private String from=null;
	private String to=null;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	private String[] indexList;
	String userid1;
	ResultSet rs1;
	private Vector vector_index_rv1;
	private ArrayList final_Vector=null;
	private Collection selectIndexCollection1=null;
	private ResultSet rst ;
	
	Vector vExcel=new Vector();
	
	Connect  con = ConnectInit.getConnect();
    Connection connection=null;   
    
    PreparedStatement pst;
    ResultSet rs = null;
	public returnVol rv;
    
	public ActionErrors validate(ActionMapping mapping,
		    HttpServletRequest request){
		  	ActionErrors errors = new ActionErrors();
		  	return errors;
		  }
		
	/**
	 * 
	 * @return Returns the slectindexcollection
	 */
	public Collection getSelectIndexCollection1() {
				
		try{
			
			if(connection==null)
				
				connection=con.getdbConnection();
			
				if (check != null && check.equals("on"))
						{
							query = ConnectInit.queries.getProperty("index_list");
						}
					else
						{
							query = ConnectInit.queries.getProperty("index_list_without_child");
						}
							Vector vec = new Vector();
						 
							try {
								
								pst = connection.prepareStatement(query);
								pst.setString(1,userid1);
								rs = pst.executeQuery();
					//			AcessControl asc=new AcessControl();
								AcessControl asc = ConnectInit.getAcessControl();
								String NotSelected=asc.getLangValues("Masters.NotSelected");
								Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
								
								vec.add(new LabelValueBean("Not Selected","0"));
								while (rs.next()) {
									
									vec.add(new LabelValueBean(rs.getString(2), rs.getString(1)));
									
								}
//								Change by Manoj adekar
								int role_id2=Integer.parseInt(roleId_ret);
								if(role_id2!=1)
								{

								ResultSet rbs = Icr.benchindecolection(connection,"index_list_without_child_bench");
								while (rbs.next()) {
									
									vec.add(new LabelValueBean(rbs.getString(2), rbs.getString(1)));
									
								}
								}
								selectIndexCollection1 = vec;
							}
							
							catch (Exception e) {
									// TODO: handle exception
									Logging.error(" Error : "+e.getMessage());
								}
		    
		} catch (Exception e) {
			// TODO: handle exception
			Logging.error(" Error :"+e.getMessage());
		//	e.printStackTrace();
		}
		finally{
			try{
				if(connection!=null)
					rs.close();
			    	pst.close();
					connection.close();
					connection=null;
			}catch(Exception ee){
				try{
					if(connection!=null)
						connection.close();
						connection=null;
				}catch(Exception ex){
					Logging.error(" Error : Unable to close connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "+ee.getMessage());
			}
		}
	

	
		return selectIndexCollection1;
		
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	public void setSelectIndexCollection1(Collection selectIndexCollection1) {
		this.selectIndexCollection1 = selectIndexCollection1;
	}
	
	/**
	 * @return Returns the check.
	 */
	public String getCheck() {
		return check;
	}
	/**
	 * @param check The check to set.
	 */
	public void setCheck(String check) {
		this.check = check;
	}
	
	/**
	 * @return Returns the d1.
	 */
	public String getSelectInd() {
		return selectInd;
	}
	/**
	 * @param d1 The d1 to set.
	 */
	public void setSelectInd(String selectInd) {
		this.selectInd = selectInd;
	}
	
	
	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from The from to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return Returns the to.
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to The to to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}
		
	/**
	 * @return Returns the indexList.
	 */
	public String[] getIndexList() {
		return indexList;
	}
	/**
	 * @param indexList The indexList to set.
	 */
	public void setIndexList(String[] indexList) {
		this.indexList = indexList;
	}
		
	/**
	 * @return Returns the vector_index_rv.
	 */
	public ArrayList getFinal_Vector() {	

		ArrayList arr = new ArrayList();
		Vector vector_index_rv1;
		ArrayList final_Vector = new ArrayList();
		Logging.debug("Inside vector_indexList_rv");
		if (indexList != null)
			if (from != null && to != null) {
				try {
					//if(connection==null)					
					connection = con.getdbConnection();
					harrier.income.com.report.AdjustDecimal ad = new harrier.income.com.report.AdjustDecimal();
					
					int j = 0;
					//app.Logging.getDebug(indexList.length+"  "+from+" "+"  "+to);
					// app.Logging.getDebug("IndexList length is "+indexList.length);
					vExcel.clear();
					for (int k = 0; k < indexList.length; k++) {
						//vector_index_rv1.clear();
						vector_index_rv1 = new Vector();

						Logging.debug("Value of K  " + indexList[k]);

						String Query = ConnectInit.queries
								.getProperty("indexwise_returns_and_volatility");
						try {
							pst = connection.prepareStatement(Query);
							pst.setString(1, indexList[k]);
							pst.setString(2, from);
							pst.setString(3, to);
							rst = pst.executeQuery();
							//vExcel.clear();

							Logging.debug("setVector_index_rv1");

							int i = 0;
							double tmcv = 0.00;
							Logging.debug("Resultset =" + rst);

							while (rst.next()) {
								if (rst.getString(1) == null) {
									vector_index_rv1.add(i, "0");
									//vExcel.add(i, "0");
								} else {
									vector_index_rv1.add(i, rst.getString(1));
									//vExcel.add(i, rst.getString(1));
								}

								i++;

								if (rst.getString(2) == null) {
									vector_index_rv1.add(i, "--");
									//vExcel.add(i, "--");
								} else {
									vector_index_rv1.add(i, rst.getString(2));
									//vExcel.add(i, rst.getString(2));
								}
								i++;

								if (rst.getString(3) == null) {
									vector_index_rv1.add(i, "0");
									//vExcel.add(i, "0");
								} else {
									vector_index_rv1.add(i, rst.getString(3));
									//vExcel.add(i, rst.getString(3));
									tmcv = tmcv
											+ (double) Double.parseDouble(rst
													.getString(3));
								}
								i++;
							}

							Logging.debug("vector size "
									+ vector_index_rv1.size());

							int m = 0;
							String str1 = null, str2 = null;

							if (vector_index_rv1.size() != 0) {
								str1 = (String) vector_index_rv1.get(m);
								m++;
								j++;

								str2 = (String) vector_index_rv1.get(m);
								m++;
								j++;

								Logging
										.debug("before getMonthlyReturns");

								double mr = getMonthlyReturns(vector_index_rv1);

								String mrstr = new Double(mr).toString();

								mrstr = ad.indexcompose(mrstr);

								//	vector_index_rv.add(j,mrstr);
								m++;
								j++;
								double volret = getAvgDailyVolatility(
										vector_index_rv1, tmcv);

								String volretstr = new Double(volret)
										.toString();
								volretstr = ad.indexcompose(volretstr);
								//	vector_index_rv.add(j,volretstr);
								j++;

								final_Vector.add(new returnVol(str1, str2,
										mrstr, volretstr));
								int tmp = 0;

								vExcel.add(tmp, str2);
								tmp++;
								vExcel.add(tmp, mrstr);
								tmp++;
								vExcel.add(tmp, volretstr);
								tmp++;

								Logging.debug("vector size 1 "
										+ final_Vector.size());
								Logging.debug("vExcel vector size>>> "
										+ vExcel.size());
							}
							
						} catch (SQLException ex) {
						}
					}
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (SQLException ee) {
						try {
							if (connection != null)
								connection.close();
						} catch (Exception ex) {
							Logging
									.error(" Error : Unable to close connection "
											+ ex.getMessage());
						}
						Logging
								.error(" Error : Unable to close connection "
										+ ee.getMessage());
					}
				}
			}
		Logging.debug("Value of Final_Vector " + final_Vector);
		Logging.debug("Value of vExel>>>>> " + vExcel);
		//setVExcel(vExcel);
		return final_Vector;
	

	}
	/**
	 * @param final_Vector
	 *            The final_Vector to set.
	 */
	public void setFinal_Vector(ArrayList final_Vector) {
		this.final_Vector=final_Vector;
	}
	
	public double getAvgDailyVolatility(Vector v,double indexmean)
	{
		
		try{
						
		
			Logging.debug("Inside getAvgDailyVolatility()");
			double sum_volatility=0.0,vratio=0.0,sum_indexvolatility=0.0,sum_indexvalue=0.0;
			Vector vol=new Vector();
			int i=0,m=0;
			indexmean=indexmean/(v.size()/3);
			Logging.debug("indexmean "+indexmean);				
			i=0;
			
			while(i<v.size())
			{
				i++;i++;
				double indval1=(double)Double.parseDouble((String)v.get(i));
				Logging.debug("indval1 "+indval1+" indexmean "+indexmean);
				sum_indexvalue=(indval1-indexmean);
				vol.add(new Double(sum_indexvalue).toString());
				Logging.debug("sum_indexvalue "+sum_indexvalue+" sum_indexvolatility "+sum_indexvolatility);
				sum_indexvolatility=sum_indexvolatility+sum_indexvalue;
				Logging.debug(" sum_indexvolatility "+sum_indexvolatility);
				i++;
			}
			
			i=0;
			
			while(i<vol.size())
			{
				double mult1=((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility);
				Logging.debug(" mult1 "+mult1);
				double mult=(((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility)*((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility));
				Logging.debug(" sum_volatility "+sum_volatility+" mult "+mult);
				sum_volatility=sum_volatility+mult;
				i++;
			}
		 vratio= 1.00/(((vol.size())));
		 Logging.debug(" sum_volatility "+sum_volatility);
		 double avgdailyvol=(Math.sqrt((vratio*sum_volatility)));
		 Logging.debug("vector size "+v.size()+" avgdailyvol is "+avgdailyvol);
		return avgdailyvol;
		}
		
		finally{
			try{
				
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
	public double getMonthlyReturns(Vector v)
	{
			Logging.debug("Inside getMonthlyReturns()");
			double mreturn=0.0,lmr=0.0,fmr=0.0;
			int l=v.size();
			Logging.debug("Inside getMonthlyReturns()"+l);
		if(v.size()!=0){
			lmr=(double)Double.parseDouble((String)v.get(l-1));
			fmr=(double)Double.parseDouble((String)v.get(2));
		}
		Logging.debug(" l size "+l+" lmr "+lmr+" fmr"+fmr);
		if(fmr!=0.00){
				mreturn=((lmr-fmr)/fmr);
		}else{
				mreturn=0.00;
		}
		Logging.debug("mreturn "+mreturn);
		return mreturn;
	}
	
	
	/**
	 * @return Returns the vExcel.
	 */
	public Vector getVExcel() {
		return vExcel;
	}
	/**
	 * @param excel The vExcel to set.
	 */
	public void setVExcel(Vector vExcel) {
		vExcel = vExcel;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.ServletRequest)
	 */
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
		try{
			
			this.connection=null;
		}catch(Exception e){}
		
		this.check=null;
		this.from=null;
		this.to=null;
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

	public String getUserid1() {
		return userid1;
	}

	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}

	public String getRoleId_ret() {
		return roleId_ret;
	}

	public void setRoleId_ret(String roleId_ret) {
		this.roleId_ret = roleId_ret;
	}
}
