package harrier.income.com.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import uk.ltd.getahead.dwr.ExecutionContext;
import app.Connect;

import com.harrier.initializeation.ConnectInit;


public class IndexComposeReportAjax {	
	Logger Logging = Logger.getLogger(IndexComposeReportAjax.class);
	/**
	 * @param retrieving tableData for indexcomposereport
	 */
	private Map tabledata = new HashMap();
	private Vector vw;
	private ResultSet rst;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	String date;
	HttpSession session = ExecutionContext.get().getSession();

	public ArrayList getTabledata(String index) {
		String stockid=null,stockname=null,currency=null,tis=null, iwf=null, adjusted=null, mcv=null, stockprice=null, market=null, last=null,curr_exch_convIratecomp1=null,strweightage1=null;
		
		String index12 = index;
		//AdjustDecimal ad=new AdjustDecimal();	
		Connection connection=null;
		Connect  c = ConnectInit.getConnect();	
		
		double total1=0.00;
		vw=new Vector();
		double tmcv=0.0;
		ArrayList tempdata=new ArrayList();
		String index_id;
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();	
		int j=0;
		 
		try{
		if(connection==null)
		connection=c.getdbConnection();
		ResultSet tmcvrst = Icr.stiockweightageLatestResult(connection,"get_tmcv_for_composition", index12);
		Logging.debug("get tmcv of Compose Index");
		try 
		{
			while (tmcvrst.next()) 
			{
				index_id=tmcvrst.getString(1);
				tmcv=tmcvrst.getDouble(2);
				Logging.debug("tmcv is "+tmcv);
				date=tmcvrst.getString(3);
				
			}
		}
		catch (SQLException sqlexp) 
		{
			Logging.error("SQL Error :"+sqlexp.getMessage());
		}
		
		String curr_exch_convIratecomp=null,strmcv=null;
		double weightage=0.0,mcve=0.0;
		rst = Icr.indexComposeResult(connection,"get_composition_for_compose_report", index12);
		int i = 0;		 
		
		 tabledata=new HashMap();
		 IndexCompose12 indexcompose1;
		Logging.debug("setVector_tabledata of Compose Index");
		try {
			while (rst.next()) {
				
				if (rst.getString(1) == null) {
					stockid= "0";
					} else {
						stockid=rst.getString(1);
						vw.add(i,rst.getString(1));
					}
				i++;
				if (rst.getString(2) == null) {
					stockname="0";
				} else {
					stockname=rst.getString(2);
					vw.add(i,rst.getString(2));
				}
				i++;
				if (rst.getString(4) == null) {
					tis="0";
					} else {
						tis=rst.getString(4);
						tis=AdjustDecimal.ArrangeAsNumeric(tis);
						vw.add(i,rst.getString(4));
					}
				i++;
				if (rst.getString(5) == null) {
					iwf="0";
				} else {
					iwf=rst.getString(5);
					iwf=ad.indexcompose(iwf);
					iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
					vw.add(i,rst.getString(5));
				}
				i++;
				if (rst.getString(9) == null) {
					market= "0";
				} else {
					market=rst.getString(9);
					vw.add(i,rst.getString(9));
				}
				i++;
				if (rst.getString(6) == null) {
					adjusted= "0";
				} else {
					adjusted=rst.getString(6);
					adjusted=ad.indexcompose(adjusted);
					adjusted=AdjustDecimal.ArrangeAsNumeric(adjusted);
					//System.out.println("$%$%$%$%$%$%$ date :"+adjusted);
					vw.add(i,rst.getString(6));
				}
				i++;
				if (rst.getString(10) == null) {
					last= "0";
				} else {
					last=rst.getString(10);
					last=ad.indexcompose(last);
					last=AdjustDecimal.ArrangeAsNumeric(last);
					vw.add(i,rst.getString(10));
				}
				i++;
				if(rst.getString(3) == null) {
					currency= "0";
				} else {
					currency=rst.getString(3);
					vw.add(i,rst.getString(3));
				}
				
				i++;
				curr_exch_convIratecomp=getCurrancyExchRate(index12,stockid);
				//app.Logging.getDebug("curr_exch_convIrate is "+curr_exch_convIrate);
				curr_exch_convIratecomp=ad.indexcompose4digit(curr_exch_convIratecomp);
				if(curr_exch_convIratecomp==null){
					curr_exch_convIratecomp="0";
					vw.add(i,curr_exch_convIratecomp);
				}else{
					curr_exch_convIratecomp1=curr_exch_convIratecomp;
					vw.add(i,curr_exch_convIratecomp);
				}
			
				i++;					
				if (rst.getString(7) == null) {
					mcv= "0";
				} else {
					mcv=rst.getString(7);
					mcv=ad.indexcompose(mcv);
					mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
					vw.add(i,rst.getString(7));
					i++;
					vw.add(i,rst.getString(7));
				}
				i++;
				strmcv=rst.getString(7);
				 mcve=Double.parseDouble(strmcv);
				 if(tmcv!=0.0){
				 	weightage=(mcve/tmcv)*100.00;
				 }
				 total1=total1+weightage;
				 String strweightage=new Double(weightage).toString();
				 strweightage=ad.shareholdingpatt(strweightage);
				 strweightage=ad.indexcompose4digit(strweightage);
				 strweightage=AdjustDecimal.ArrangeAsNumeric(strweightage);
				 strweightage1=strweightage;
				 vw.add(i,strweightage1);
				 //weightage 
				/*if (rst.getString(8) == null) {
					vector_tabledata.add(i, "0");
				} else {
					vector_tabledata.add(i, rst.getString(8));
				}*/
			
				 i++;
				if (rst.getString(8) == null) {
					date = "-0";
				} else {
					date = rst.getString(8);
					//System.out.println("%^%^%^%^%^%%stokpricw"+stockprice+"adjusted  :"+adjusted+"curency  :"+currency);
					vw.add(i,rst.getString(8));
				}
				i++;
				indexcompose1 = new IndexCompose12( stockid,stockname,tis,iwf,market,adjusted,last,currency,curr_exch_convIratecomp1,mcv,mcv,strweightage1,date);
				tempdata.add(indexcompose1);
				//tabledata.put(new Integer(++j),indexcompose1);
				}
			
			if(total1>=99.9999)
				total1=100.00;
			String strtotal=(String)new Double(total1).toString();
			strtotal=ad.indexcompose(strtotal);
			tempdata.add(new IndexCompose12(" "," "," "," "," "," "," "," "," "," ","Total",strtotal,""));
			session.setAttribute("StoredVector",vw);
			session.setAttribute("type","1");
			session.setAttribute("ci2",vw);
			session.setAttribute("var",index12);
			rst.close();
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
		}
		}catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		 finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ee){
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ex){
					Logging.error(" Error : Unable to close connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "+ee.getMessage());
			}
		}
		//tabledata=tempdata;
		
		//setTotal(total1);
		//setVw(vw);
		 return tempdata;
	  	}

	public String getCurrancyExchRate(String index12,String stockid)
  	{
	String cexch_rate=null;
	String stk_currency_id=null,ind_currency_id=null;
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	Connection connection=null;
	if(connection==null)
	{
		connection=con.getdbConnection();
	}
	try {
		//app.Logging.getDebug("inside getCurrancyExchRate");
		ResultSet rstexc = Icr.indwtResult(connection,"get_index_and_stock_currency_id", stockid,index12);
		int i = 0;
		Logging.debug("rst is "+rstexc);	
		while (rstexc.next()) {
			if (rstexc.getString(1) == null) {
				stk_currency_id="0";
			}else{
				stk_currency_id=(String)rstexc.getString(1);
			}
			if (rstexc.getString(2) == null) {
				ind_currency_id="0";
			}else{
				ind_currency_id=(String)rstexc.getString(2);
			}				
		}
		//app.Logging.getDebug("stk_currency_id is "+stk_currency_id+" ind_currency_id is "+ind_currency_id);
		if(stk_currency_id.equals(ind_currency_id)){
			cexch_rate="1.00";
		}else{
			/*ResultSet rst11 = con.indwtResult("get_currency_exchange_rate", ind_currency_id,stk_currency_id);
			while (rst11.next()) {
				if (rst.getString(1) == null) {
					cexch_rate="0";
				}else{
					cexch_rate=(String)rst11.getString(1);
				}					
			}*/
			
			String temp=Icr.getIndexCurrancyExchRate(connection,stk_currency_id,ind_currency_id);
        	double exch=0.0; 
			if(temp!=null){
        		exch=new Double(temp).doubleValue();
        	}else{
        		temp=Icr.getIndexCurrancyExchRate(connection,ind_currency_id,stk_currency_id);
        		if(temp==null){
        			exch=1.0;
        		}else{
        			exch=1/new Double(temp).doubleValue();
        		}
        	}
			cexch_rate=new Double(exch).toString();
			Logging.debug("currency exchange rate is "+cexch_rate);
		}
	}catch (SQLException sqlexp) {
		Logging.error("SQL Error :"+sqlexp.getMessage());
	}
	finally{
		try{
			if(connection!=null)
				connection.close();
		}catch(Exception ee){
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ex){
				Logging.error(" Error : "+ex.getMessage());
			}
			Logging.error(" Error : "+ee.getMessage());
		}
	}
	return cexch_rate;
  	}
	
}
