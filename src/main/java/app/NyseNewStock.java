/*
 * Created on Mar 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import harrier.income.com.masters.StockMasterForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NyseNewStock {
	static Logger Logging = Logger.getLogger(NyseNewStock.class);
	private static Connection con=null;
	private static String identifier_code; 
	//static Connect con1 = ConnectInit.getConnect();
	
	public static void insertIntoStockMaster(String query,String qry1,StockMasterForm form){	
		Connection connection=null;
		Connect con=ConnectInit.getConnect();
		try {
			//getConnection();
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
			{
				connection=con.getdbConnection();
			 }
			
			StockMasterForm form1 = (StockMasterForm)form;
			
			String stk_type=null;	
			Logging.debug("Inside insert Stock Master");
			int exc_id=(int)Integer.parseInt(form1.getS_stockExchange());
			Logging.debug("Exchange id is "+exc_id);
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_identifier_code"));
			pst.setInt(1,exc_id);
			ResultSet rst = pst.executeQuery();
			while(rst.next())
			{
				identifier_code=rst.getString(1);
			}
			Logging.debug("Identifier code "+identifier_code);
			String query1=null;
			boolean flag=true;
			String stk_id=null;			
			PreparedStatement psmt = connection.prepareStatement(query);
			Logging.debug("in query");
			Statement stmt = connection.createStatement();
			ResultSet rs=null; 
			int para=0;
			rs= stmt.executeQuery("SELECT NEXTVAL('stock_id')");
			while(rs.next()){
				//Logging.getDebug("stock max"+rs.getInt(1));
				para=rs.getInt(1);
				form1.setS_stockID(String.valueOf(rs.getInt(1)));
			}
			psmt.setInt(1,para);
			Logging.debug("para is "+para);	
			rs.close();	
			Logging.debug("in 1 q"+form1.getS_companyName());	
			//Logging.getDebug(s_val);
			psmt.setString(2,null);//form1.getS_stockType());
			psmt.setString(3,form1.getS_companyName());
			psmt.setString(4,"1");
			psmt.setString(5,form1.getF_issuedShares());
			psmt.setString(6,form1.getS_stockName());
			psmt.setString(7,"1");
			psmt.setString(8,form1.getD_listingDate());
			psmt.setString(9,null);
			psmt.setString(10,null);//form1.getS_ratingCode());			
			psmt.setString(11,null);
			psmt.setString(12,form1.getF_alertPercent());
			psmt.setString(13,form1.getF_rejectionPercent());
			psmt.setString(14,null);
			psmt.setString(15,null);
			psmt.setString(16,"1");
			psmt.setString(17,form1.getS_stockExchange());
			psmt.setString(18,form1.getS_countryName());
			psmt.setString(19,"y");
			psmt.setString(20,form1.getS_stockCurrency());
			psmt.setString(21,"y");
			psmt.setString(22,null);
			psmt.executeUpdate();
			Logging.debug("insert into stock master"+psmt);
			String stockmaster=null+","+form1.getS_companyName()+","+form1.getD_iwf()+","+form1.getF_issuedShares()+
			","+form1.getS_stockName()+","+form1.getF_faceValue()+","+form1.getD_listingDate()+","+form1.getS_growthValueType()+
			","+form1.getB_global100()+","+form1.getF_alertPercent()+","+form1.getF_rejectionPercent()+","+form1.getB_withHoldingTaxApplicable()+
			","+form1.getB_withHoldingTaxApplicable()+","+form1.getF_withholdingTaxPercent()+","+form1.getS_marketLot()+","+form1.getS_stockExchange()+
			","+form1.getS_countryName()+","+"y"+","+form1.getS_stockCurrency()+","+form1.getB_isPriceForLot()+","+form1.getD_paidValue();
			AuditTrail.StoreTableInsertUpdate("58",null,stockmaster);
			Logging.info("Stock values inserted in stock master");
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date();
			String l_date = fr.format(dt).toString();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT nextval('stock_mh_id')");
			
			int smid=0;
			while(rs.next()){	
				smid=rs.getInt(1);
			}
			rs.close();
			Logging.debug("smid is "+smid+"  query is "+qry1);
			
			psmt = connection.prepareStatement(qry1);
			String sid=form1.getS_stockID();
			Logging.debug("si="+sid);
			psmt.setInt(1,smid);
			psmt.setString(2,sid);		
			psmt.setString(3,null);//form1.getS_stockType());
			//Logging.getDebug(s_val);
			
			//int c_id=(int)Integer.parseInt(form1.getS_companyName());
			String cnm=form1.getS_companyName();
			psmt.setString(4,cnm);
			Logging.debug("cnm="+cnm);
			Logging.debug(form1.getS_companyName());
			psmt.setString(5,"1");
			psmt.setString(6,form1.getF_issuedShares());
			psmt.setString(7,form1.getS_stockName());
			psmt.setString(8,"1");
			psmt.setString(9,form1.getD_listingDate());	
			//Logging.getDebug(form1.getS_growthValueType());
			psmt.setString(10,null);	
			psmt.setString(11,null);//form1.getS_ratingCode());
			psmt.setString(12,null);
			psmt.setString(13,form1.getF_alertPercent());
			psmt.setString(14,form1.getF_rejectionPercent());
			psmt.setString(15,null);
			psmt.setString(16,null);
			psmt.setString(17,"1");	
			psmt.setString(18,form1.getS_stockExchange());
			psmt.setString(19,form1.getS_countryName());
			psmt.setString(20,"y");
			psmt.setString(21,form1.getS_stockCurrency());
			psmt.setString(22,"y");
			psmt.setString(23,form1.getD_paidValue());
			psmt.setString(24,l_date);
			psmt.setString(25,null);
			//Logging.getDebug("r_val is 4"+r_val);		
			psmt.executeUpdate();
			Logging.info("Stock values inserted in stock master history");
			String stockmasterhistory=null+","+form1.getS_companyName()+","+form1.getD_iwf()+","+form1.getF_issuedShares()+
			","+form1.getS_stockName()+","+form1.getF_faceValue()+","+form1.getD_listingDate()+","+form1.getS_growthValueType()+
			","+form1.getB_global100()+","+form1.getF_alertPercent()+","+form1.getF_rejectionPercent()+","+form1.getB_withHoldingTaxApplicable()+
			","+form1.getF_withholdingTaxPercent()+","+form1.getS_marketLot()+","+form1.getS_stockExchange()+
			","+form1.getS_countryName()+","+"y"+","+form1.getS_stockCurrency()+","+form1.getB_isPriceForLot()+","+form1.getD_paidValue()+","+l_date+","+null;
			AuditTrail.StoreTableInsertUpdate("60",null,stockmasterhistory);
			//Logging.getDebug("r_val is "+r_val); 
			 
			query1=null;
			//Logging.getDebug("query is "+query1);
			 query1="select max(stock_id)  from stock_master";
			 Statement stmt2 = connection.createStatement();
				ResultSet rs2 = stmt2.executeQuery(query1);
				int stk_val=0;
				while(rs2.next()){
					stk_val=rs2.getInt(1);
				}	
				rs2.close();		
				Logging.debug("stk_val is "+stk_val);			 
				int stk_id_code=0;
				String sdl=form1.getB_sdl();
				String isin=form1.getB_isn();
				String ric=form1.getB_ric();
				String cusip=form1.getB_csp();	
				String exc=form1.getB_exc_code();
				String tkr=form1.getB_tkr();
				String crisil=form1.getB_crisil();
				/*Logging.getDebug("sdl is " + sdl);
				Logging.getDebug("isin is " + isin);
				Logging.getDebug("ric is "+ric);
				Logging.getDebug("cusip is "+cusip);
				Logging.getDebug("exc is " +exc);
				Logging.getDebug("tkr is "+tkr);
				Logging.getDebug("crisil is "+crisil);*/
				String qry_ident;
				qry_ident="select identifier_code_id from identifier_codes where identifier_code_name=";
				if(!((sdl==null)||(sdl.equals(""))))
				{	
					String sdl_id=QueryClass1.getIdentifierCodeId("sedol");
					stockidentifier(stk_val,sdl,sdl_id,stk_type);				
				}			
				if(!((ric==null)|| ric.equals("")))
				{
					String ric_id=QueryClass1.getIdentifierCodeId("ric");
					stockidentifier(stk_val,ric,ric_id,stk_type);				
				}
				if(!((isin==null)|| isin.equals("")))
				{
					String isin_id=QueryClass1.getIdentifierCodeId("isin");
					Logging.debug("isin_id is "+isin_id);
					stockidentifier(stk_val,isin,isin_id,stk_type);				
				}
				if(!((cusip==null)|| cusip.equals("")))
				{
					String csp_id=QueryClass1.getIdentifierCodeId("cusip");
					stockidentifier(stk_val,cusip,csp_id,stk_type);				
				}
				if(!((exc==null) || exc.equals("")))
				{
					exc_id=(int)Integer.parseInt(form1.getS_stockExchange());
					Logging.debug("Exchange id is "+exc_id);
					PreparedStatement pst11 = connection.prepareStatement(ConnectInit.queries
							.getProperty("get_identifier_code"));
					pst.setInt(1,exc_id);
					ResultSet rst11 = pst.executeQuery();
					while(rst11.next())
					{
						identifier_code=rst11.getString(1);
					}
					Logging.debug("Identifier code "+identifier_code);
					Logging.debug(" stk_val "+stk_val+" exc "+exc+" stk_type "+stk_type);
					stockidentifier(stk_val,exc,identifier_code,stk_type);
					//int stockID,String ident_val,int ident_id,String series
				}			
				if(!((tkr==null)||(tkr.equals(""))))
				{
					String tkr_id=QueryClass1.getIdentifierCodeId("tiker");
					stockidentifier(stk_val,tkr,tkr_id,stk_type);				
				}
				if(!((crisil==null)||(crisil.equals(""))))
				{
					String crisil_id=QueryClass1.getIdentifierCodeId("crisil");
					stockidentifier(stk_val,crisil,crisil_id,stk_type);				
				}
				Logging.info("values inserted in identifier code sucessfully");
				query1=null;
				String adr=form1.getS_adrRatio();
				String adr1=form1.getS_adrRatio1();
				if(!((adr==null || adr.equals(""))) || !((adr1==null || adr1.equals("")))){
					query1="select nextval('stock_adr_gdr_id') ";
				    Statement stmt3 = connection.createStatement();
					ResultSet rs3 = stmt3.executeQuery(query1);
					int stk_adr_gdr_id=0;
					while(rs3.next()){
						stk_adr_gdr_id=rs3.getInt(1) ;
					}
					rs3.close();
					String qry11=ConnectInit.queries.getProperty("insert_into_stock_adr_gdr_ratio");
					psmt = connection.prepareStatement(qry11);
					String s_id=form1.getS_stockID();
					Logging.debug(" adr_gdr_id "+stk_adr_gdr_id+" si="+s_id);
					Logging.debug("form1.getS_adrRatio() "+form1.getS_adrRatio()+" form1.getS_adrRatio1()="+form1.getS_adrRatio1());
					psmt.setInt(1,stk_adr_gdr_id);
					psmt.setString(2,s_id);
					String adr_gdr_id=form1.getAdr_gdr_id();
					//int adr_gdr_id=getadrgdrId("American");
					psmt.setString(3,adr_gdr_id);				
					psmt.setString(4,form1.getS_adrRatio());	
					psmt.setString(5,form1.getS_adrRatio1());
					Logging.debug("psmt of adr_gdr "+psmt+ "ratio is "+form1.getS_adrRatio());
					psmt.executeUpdate();
					String stktype=stk_adr_gdr_id+s_id+adr_gdr_id+form1.getS_adrRatio()+form1.getS_adrRatio1();
					AuditTrail.StoreTableInsertUpdate("61",null,stktype);
					Logging.info("adr gdr values inserted  sucessfully");
				}
				Logging.debug("final");
		}catch(Exception e){ Logging.error("Error while creating prepared Statemwent" +e.getMessage());}	

//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	public static void stockidentifier(int stockID,String ident_val,String ident_id,String series)
	  {
		Connection connection=null;
		Connect con=ConnectInit.getConnect();
		try{
		//getConnection();
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
			{
				connection=con.getdbConnection();
			 }
			
		String qry="select nextval('stock_identifier_code_id')";
		ResultSet rs1 = connection.createStatement().executeQuery(qry);	
		int stk_ident_code=0;
		while(rs1.next()){
			stk_ident_code=rs1.getInt(1);
		}
		rs1.close();
		Logging.debug("stockID "+stockID+" ident_val "+ident_val+" ident_id "+ident_id+" series "+series);
		String query1=ConnectInit.queries.getProperty("insert_into_stock_identifier_codes");	
		Logging.debug(" query1 "+query1) ;
		PreparedStatement psmt1 = connection.prepareStatement(query1);
		Logging.debug(" psmt1 "+psmt1);
		psmt1.setInt(1,stk_ident_code);
		psmt1.setString(2,ident_id);
		psmt1.setInt(3,stockID);
		psmt1.setString(4,ident_val.trim());
		psmt1.setString(5,null);
		psmt1.executeUpdate();
		String values=stk_ident_code+ident_id+stockID+ident_val+series;
		Logging.debug(" values "+values);
		AuditTrail.StoreTableInsertUpdate("62",null,values);
		Logging.debug("identifer code values inserted in stock identifier codes sucessfully");
		}catch(Exception e){
			Logging.error("Errro while inserting copy of parent "+e.getMessage());
		}

//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	  }

	/*private static void  getConnection()
	{
		if(con == null)
			con = con1.getConnection();		
	}*/
}
