/*
 * Created on Jan 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CalculateBeta {
	Logger Logging = Logger.getLogger(CalculateBeta.class);
	public  static Vector v_all,overall_index_view;
	public static Vector v_stock=new Vector();
	public static Vector v_stockvol=new Vector();
	public double stockmean=0.0,sum_covarvalue=0.0,indexmean=0.0,sum_stockvalue=0.0,sum_stockvolatility=0.0,sum_indexvalue=0.0,covar_mean=0.0;
	public static double sd=0.0,covar=0.0,var=0.0;
	static Connect con1 = ConnectInit.getConnect();
	Connection connection=null;
	public void populateValuesBetaCalc(String indexid,String fdate,String tdate)
	{
		    
         
		Logging.debug("inside calculate beta");
         Logging.debug(indexid+" "+fdate+" "+tdate);
         v_all=new Vector();  
	try{
	//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
            {
            	connection=con.getdbConnection();
            }
            int i=0;
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("beta_calculation_data"));
			pst.setString(1,indexid);
			pst.setString(2,fdate);	
			pst.setString(3,tdate);
			ResultSet rs = pst.executeQuery(); 
			while(rs.next())
			{
				if (rs.getString(1) == null) {
					v_all.add(i, "0");
				} else {
					v_all.add(i, rs.getString(1));
				}
				i++;

				if (rs.getString(2) == null) {
					v_all.add(i, "0");
				} else {
					v_all.add(i, rs.getString(2));
				}
				i++;

				if (rs.getString(3) == null) {
					v_all.add(i, "0");
				} else {
					v_all.add(i, rs.getString(3));
				}
				i++;	
			}
		}catch(SQLException e){
			Logging.debug("Error : "+e.getMessage());
		}catch(Exception e){
			Logging.debug("Error : "+e.getMessage());
		}
//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.debug(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}

	}
	
	public double getBetaCalculated(String stockid)
	{
		v_stock.clear();
		double beta=0.0;
		sum_covarvalue=sum_stockvolatility=sum_stockvalue=sum_indexvalue=covar_mean=stockmean=indexmean=0.0;
		Logging.debug("size of vector "+v_all.size());		
		int count=0;
		for(int k=0;k<v_all.size();k+=3)
		{
			String stkid=(String)v_all.get(k);
			if(stkid.equals(stockid))
			{
				v_stock.add(count,stkid);
				count++;k++;
				v_stock.add(count,(String)v_all.get(k));
				count++;k++;
				v_stock.add(count,(String)v_all.get(k));
				count++;k--;k--;
			}
		}
		Logging.debug("size of stock vector"+v_stock.size());
		int i=0;
		while(i<v_stock.size())
		{
			i++;
			stockmean=stockmean+(double)Double.parseDouble((String)v_stock.get(i));
			covar_mean=covar_mean+((double)Double.parseDouble((String)v_stock.get(i))*(double)Double.parseDouble((String)v_stock.get(i+1)));
			i++;
			indexmean=indexmean+(double)Double.parseDouble((String)v_stock.get(i));
			i++;
		}
		Logging.debug("indexmean "+indexmean+" stockmean"+stockmean);
		if(v_stock.size()!=0)
		{
			indexmean=indexmean/(v_stock.size()/3);
			stockmean=stockmean/(v_stock.size()/3);
		}else{
			indexmean=0.0;
			stockmean=0.0;
		}		
		Logging.debug("indexmean "+indexmean+" stockmean"+stockmean+" stock size"+v_stock.size());
		 i=0;
		 int m=0;
		 v_stockvol.clear();
		while(i<v_stock.size())
		{
			i++;
			sum_covarvalue=(((double)Double.parseDouble((String)v_stock.get(i))-stockmean)*((double)Double.parseDouble((String)v_stock.get(i+1))-indexmean));
			sum_stockvalue=((double)Double.parseDouble((String)v_stock.get(i))-stockmean);
			sum_stockvolatility=sum_stockvolatility+sum_stockvalue;
			v_stockvol.add(m,(new Double(sum_stockvalue).toString()));
			m++;
			i++;
			sum_indexvalue=((double)Double.parseDouble((String)v_stock.get(i))-indexmean)*((double)Double.parseDouble((String)v_stock.get(i))-indexmean);
			i++;
		}
		Logging.debug("sum_covarvalue "+sum_covarvalue+" sum_stockvalue"+sum_stockvalue+" sum_indexvalue "+sum_indexvalue+" stock size"+v_stock.size());
		double ratio=0.0;
		if(v_stock.size()!=0){
			ratio= 1.00/((v_stock.size()-1)/3.00);
			Logging.debug("(1/(v_stock.size()-1)) is "+(1.00/((v_stock.size()-1)/3.00))+" ratio is "+ratio);
		}		
		var=(ratio*sum_indexvalue);
		 covar=(ratio*sum_covarvalue);
		 Logging.debug("var is "+var+" covar "+covar);
		 if(var>0.00000000000001){
		 	beta=covar/var;
		 }else{
		 	beta=0.00;
		 }
		 Logging.debug(" beta is "+beta);
		 return beta;
	}
	public double getAvgDailyVolatility()
	{
		Logging.debug("Inside getAvgDailyVolatility()");
		double sum_volatility=0.0,vratio=0.0;
		for(int i=0;i<v_stockvol.size();i++)
		{
			sum_volatility=sum_volatility+(((double)Double.parseDouble((String)v_stockvol.get(i))-sum_stockvolatility)*((double)Double.parseDouble((String)v_stockvol.get(i))-sum_stockvolatility));
		}
		if(v_stockvol.size()==1){
			vratio=0.00;
		}else{
			vratio= 1.00/((v_stockvol.size()-1));
		}
		 double avgdailyvol=(Math.sqrt((vratio*sum_volatility)));
		 Logging.debug("vector size "+v_stockvol.size()+" avgdailyvol is "+avgdailyvol);
		return avgdailyvol;
		//return (sum_stockvolatility/(v_stock.size()/3));
	}
	public double getMonthlyReturns()
	{
		Logging.debug("Inside getMonthlyReturns()");
		double mreturn=0.0,lmr=0.0,fmr=0.0;
		int l=v_stock.size();
		Logging.debug("Inside getMonthlyReturns()"+l);
		lmr=(double)Double.parseDouble((String)v_stock.get(l-2));
		fmr=(double)Double.parseDouble((String)v_stock.get(1));
		Logging.debug(" l size "+l+" lmr "+lmr+" fmr"+fmr);
		mreturn=((lmr-fmr)/fmr)*100;
		Logging.debug("mreturn "+mreturn);
		return mreturn;
	}
	public double getR_squared()
	{
		double sreturn=0.0,r_sqd=0.0,ireturn=0.0;
		int l=v_stock.size();
		sreturn=(double)Double.parseDouble((String)v_stock.get(l-2))-(double)Double.parseDouble((String)v_stock.get(1));
		ireturn=(double)Double.parseDouble((String)v_stock.get(l-1))-(double)Double.parseDouble((String)v_stock.get(2));
		if(ireturn!=0.0)
		{
			r_sqd=(sreturn/ireturn)*100;
		}else{
			r_sqd=0.0;
		}
		return r_sqd;
	}
	public Vector combineVector(Vector v1)
	{
		overall_index_view=new Vector();
	//	AdjustDecimal ad=new AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Logging.debug("v1 size is "+v1.size());
		 double beta=0.00,rsq=0.00,avgdailyvol=0.00,mr=0.00;
		 String tis=null;
		int m=0;
		for(int i=0;i<v1.size();i+=5)
		{
			String id=(String)v1.get(i);
			overall_index_view.add(m,id);
			beta=getBetaCalculated(id);
			m++;			
			overall_index_view.add(m,(String)v1.get(i+1));m++;
			tis=null;
			tis=(String)v1.get(i+2);
			tis=ad.shareholdingpatt(tis);
			tis=ad.indexcompose(tis);
			//tis=AdjustDecimal.ArrangeAsNumeric(tis);
			overall_index_view.add(m,tis);m++;	
			tis=null;
			tis=(String)v1.get(i+3);
			tis=ad.shareholdingpatt(tis);
			tis=ad.indexcompose(tis);
			//tis=AdjustDecimal.ArrangeAsNumeric(tis);
			overall_index_view.add(m,tis);m++;	
			tis=null;
			tis=(String)v1.get(i+4);
			tis=ad.shareholdingpatt(tis);
			//tis=AdjustDecimal.ArrangeAsNumeric(tis);
			overall_index_view.add(m,tis);m++;	
       		String betastr=new Double(beta).toString();
       		betastr=ad.indexcompose(betastr);
       		Logging.debug(betastr);
			overall_index_view.add(m,betastr);m++;
			rsq=getR_squared();
      		String rsqstr=new Double(rsq).toString();
      		rsqstr=ad.indexcompose(rsqstr);
      		//rsqstr=AdjustDecimal.ArrangeAsNumeric(rsqstr);
      		Logging.debug(" rsq is "+rsqstr);
      		overall_index_view.add(m,rsqstr);m++;
      		avgdailyvol=getAvgDailyVolatility();
          	String avgdailyvolstr=new Double(avgdailyvol).toString();
          	avgdailyvolstr=ad.indexcompose(avgdailyvolstr);
          	Logging.debug(" avgdailyvol is "+avgdailyvol);
          	overall_index_view.add(m,avgdailyvolstr);m++;
          	mr=getMonthlyReturns();
        	String mrstr=new Double(mr).toString();
        	mrstr=ad.indexcompose(mrstr);
        	Logging.debug(" mr is "+mrstr);
        	overall_index_view.add(m,mrstr);m++;
		}
		return overall_index_view;
	}
}
