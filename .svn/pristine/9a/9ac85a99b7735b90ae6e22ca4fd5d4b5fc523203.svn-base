package harrier.income.com.fixedincome;

/*
 * @author Manoj Adekar
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class StockMasterBondsAction extends Action 
{static Logger Logging = Logger.getLogger(StockMasterBondsAction.class);
	Connect c = ConnectInit.getConnect();
	private static String identifier_code;
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception 
	{
	
		String query1=null,query2=null,query3=null;
		StockMasterBondsCommodities stockmaster = (StockMasterBondsCommodities) form;
		//String commit_button = stockmaster.getCommit_button();
			
		query1=ConnectInit.queries.getProperty("insert_into_fixed_income_stock_master");
		query2=ConnectInit.queries.getProperty("insert_into_fixed_income_rating_code_mapping");
		//query3=c.queries.getProperty("insert_into_stock_identifier_code_fixed_income");
		
		insertIntoFixedIncomeStockMaster(query1,query2,form);
	/*	if (commit_button.equals("commit")) 
		{
			stockmaster.setVarify("fill");
			//
		}*/
		return new ActionForward(
				"/pages/fixedincome/StockMasterBonds.jsp?from=action");
	}
	
	/**
	 * insert the stock details in Fixed_income_stock_master,Fixed_income_Rating_code_mapping and identifier_codes.
	 */
	public static void insertIntoFixedIncomeStockMaster(String query1,String query2,ActionForm form)
	{	
	
		Connection connection = null;
		Connect c= ConnectInit.getConnect();
		StockMasterBondsCommodities form1 = (StockMasterBondsCommodities)form;
		try 
		{
			//getConnection();
			PreparedStatement pseq1=null;
			ResultSet seqno1=null;
			int user_id=0; 
		
			if(connection == null)
			{
				connection = c.getdbConnection();
			}
	
			String stk_type="";	
			Logging.debug("Inside insert Fixed Income Stock Master");
			int exc_id=(int)Integer.parseInt(form1.getS_stockExchange());
			//Logging.getDebug("Exchange id is "+exc_id);
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_identifier_code"));
			pst.setInt(1,exc_id);
			ResultSet rst = pst.executeQuery();
			while(rst.next())
			{
				identifier_code=rst.getString(1);
			}
			rst.close();
			pst.close();
		
			Logging.debug("Identifier code "+identifier_code);
	
			boolean flag=true;
			String stk_id=null;
		
			PreparedStatement psmt = connection.prepareStatement(query1);
			Logging.debug("in query");
			Statement stmt = connection.createStatement();
			ResultSet rs=null; 
			int para=0;
			rs= stmt.executeQuery(ConnectInit.queries.getProperty("get_sequence_stockbond_id"));
			while(rs.next())
			{
				Logging.debug("stock max"+rs.getInt(1));
				para=rs.getInt(1);
				form1.setS_stockID(String.valueOf(rs.getInt(1)));
			}
			stmt.close();
			psmt.setInt(1,para);
			Logging.debug("para is "+para);	
			rs.close();	
			psmt.setString(2,"3718");//form1.getS_stockType());
			psmt.setString(3,form1.getS_companyName());
			float iwf = Float.parseFloat(form1.getD_iwf());
			psmt.setFloat(4,iwf);
			psmt.setFloat(5,Float.parseFloat(form1.getF_issuedShares()));		
			
			psmt.setString(6,form1.getS_stockName().trim());
			psmt.setFloat(7,Float.parseFloat(form1.getF_faceValue()));
			psmt.setString(8,form1.getD_listingDate());
			psmt.setFloat(9,Float.parseFloat(form1.getF_alertPercent()));
			psmt.setFloat(10,Float.parseFloat(form1.getF_rejectionPercent()));
			psmt.setString(11,form1.getS_marketLot());
			psmt.setString(12,form1.getS_stockExchange());
			psmt.setString(13,form1.getS_countryName());
			psmt.setString(14,form1.getS_stockCurrency());
			psmt.setString(15,form1.getB_isPriceForLot());
			psmt.setString(16,form1.getD_paidValue());
			psmt.setString(17,form1.getStart_date());
			psmt.setString(18,form1.getMaturity_date());
			psmt.setString(19,form1.getCoupon_percentage());
			psmt.setString(20,form1.getCoupon_period());
			psmt.setString(21,form1.getInterest_basis_month());
			psmt.setString(22,form1.getInterest_basis_year());
			psmt.setString(23,form1.getDescription());
			psmt.setString(24,form1.getIsssuesize());
			psmt.setString(25,form1.getIssuedate());
			psmt.setString(26,form1.getDelistingdate());
			psmt.setString(27,form1.getB_isActive());
			//INSERT INTO fixed_income_stock_master(stock_id, stock_type_id, company_id, iwf, tis, stock_name, face_value,listing_date, alert_percentage, rejection_percentage, market_lot,stock_exchange_id, country_id,stock_currency_id,is_price_for_lot, paid_value, start_date, maturity_date, coupon_percentage,coupon_period, interest_rate_daysinmonth, interest_rate_daysinyear,description, issue_size, issue_date, delisting_date,is_active)VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			psmt.executeUpdate();
			//psmt.close();
			Logging.info("Stock values inserted in Fixed Income stock master");
	 
	
				
			/*
			 * **************Insert into fixed rating code mapping***********************
			 * 
			 * */
			
			String fi_stockid = form1.getS_stockID();
			String[] str = form1.getRem();
			String buff	= str[0].toString();
			
			try
			{
				
				int num=Integer.parseInt(fi_stockid);
				connection.commit();
				connection.setAutoCommit(false);
				if(num!=0)
				{
					PreparedStatement stmt1 = connection.prepareStatement(ConnectInit.queries.getProperty("delete_rating_code_from_fixed_income_rating_code_mapping"));	
					stmt1.setInt(1,num);
				    stmt1.executeUpdate();	
					stmt1.close();
				}
			}
			catch(Exception e)
			{
				Logging.debug(e);
				try 
				{
					connection.close();
				}
				catch (SQLException e1) 
				{
				//	e1.printStackTrace();
					Logging.debug(e1);
				}
				return;
			} 
			
			StringTokenizer st = new StringTokenizer(buff,",");
			int num=Integer.parseInt(fi_stockid);
			if(num!=0)
			{
				try 
				{
					PreparedStatement	stmt2 = connection.prepareStatement(query2);
					while (st.hasMoreTokens()) 
					{
						String ratingcode_id=st.nextToken();
			          	int numrateID=Integer.parseInt(ratingcode_id);
				    		
			          	stmt2.setInt(1,num);	
			          	stmt2.setInt(2,numrateID);
			            stmt2.addBatch();
					} 
					stmt2.executeBatch();	
					stmt2.close();
					connection.setAutoCommit(true);
					//connection.close();
				}
				catch (SQLException e1) 
				{
			     	//	e1.printStackTrace();
					Logging.debug(e1);	     	
			    }
			} 
			
			/*
			 * **************Insert into Stock identifier codes***********************
			 * */
			
			String q1=null;
			String stk_val=null;
			int stk_id_code=0;
			
			q1=ConnectInit.queries.getProperty("get_max_fixed_income_stock_id");
			
			Statement stmt2 = connection.createStatement();
			ResultSet rs2 = stmt2.executeQuery(q1);
			
			while(rs2.next())
			{
				stk_val=rs2.getString(1);
			}
			stmt2.close();
			
			Logging.debug("stk_val is "+stk_val);
			
			
			String sdl=form1.getB_sdl();
			Logging.debug("sdl is " + sdl);
			
			String isin=form1.getB_isn();
			Logging.debug("isin is " + isin);
			
			String ric=form1.getB_ric();
			Logging.debug("ric is "+ric);
			
			String cusip=form1.getB_csp();	
			Logging.debug("cusip is "+cusip);
			
			String exc=form1.getB_exc_code();
			Logging.debug("exc is " +exc);
			
			String tkr=form1.getB_tkr();
			Logging.debug("tkr is "+tkr);
			
			String crisil=form1.getB_crisil();
			Logging.debug("crisil is "+crisil);
			
			if((sdl==null)||(sdl.equals("")))//no identifier code exist.
			{	
				Logging.debug("no code");
			}
			else
			{
				String sdl_id=getIdentifierCodeId("sedol");
				stockidentifier(stk_val,sdl.trim(),sdl_id,stk_type);	//insert values into identifier_code for sedol.		
			}			
			
			if((ric==null)||(ric.equals("")))//no sedol code exist.
			{	
				Logging.debug("no code");
			}
			else
			{
				String ric_id=getIdentifierCodeId("ric");		
				stockidentifier(stk_val,ric.trim(),ric_id,stk_type);	//insert values into identifier_code for ric.			
			}
			
			if((isin==null)||(isin.equals("")))//no isin code exist.
			{	
				Logging.debug("no code");
			}
			else
			{
				String isin_id=getIdentifierCodeId("isin");	
				Logging.debug("isin_id is "+isin_id);
				stockidentifier(stk_val,isin.trim(),isin_id,stk_type);	//insert values into identifier_code for isin.				
			}
			
			if((cusip==null)||(cusip.equals("")))//no cusip code exist.
			{	
				Logging.debug("no code");
			}
			else
			{
				String csp_id=getIdentifierCodeId("cusip");
				stockidentifier(stk_val,cusip.trim(),csp_id,stk_type);	//insert values into identifier_code for cusip.					
			}
			
			if((exc==null)||(exc.equals("")))//no exchange code exist.
			{	
				Logging.debug("no code");
			}
			else
			{
				exc_id=(int)Integer.parseInt(form1.getS_stockExchange());
				Logging.debug("Exchange id is "+exc_id);
				
				PreparedStatement pst11 = connection.prepareStatement(ConnectInit.queries.getProperty("get_identifier_code"));
				pst11.setInt(1,exc_id);
				
				ResultSet rst11 = pst11.executeQuery();
				
				while(rst11.next())
				{
					identifier_code=rst11.getString(1);
				}
				rst11.close();
				pst11.close();
				
				stockidentifier(stk_val,exc.trim(),identifier_code,stk_type);//insert values into identifier_code for exchange code.				
			}
			
			if((tkr==null)||(tkr.equals("")))//no ticker code exist.
			{	
				Logging.debug("no code");
			}
			else
			{
				String tkr_id=getIdentifierCodeId("ticker");
				stockidentifier(stk_val,tkr.trim(),tkr_id,stk_type);//insert values into identifier_code for ticker.				
			}
			
			if((crisil==null)||(crisil.equals("")))//no crisil code exist.
			{	
				Logging.debug("no code");
			}
			else
			{
				String crisil_id=getIdentifierCodeId("crisil");
				stockidentifier(stk_val,crisil.trim(),crisil_id,stk_type);				
			}
			
		}
		catch(Exception e)
		{ 
			Logging.error("Error while creating prepared Statemwent" +e.getMessage());
			form1.setTrans_flag(true);
		}	
		finally
		{
			try
			{
				if(connection!=null)
				connection.close();
		    
			}
			catch(Exception ee)
			{
				try
				{
					if(connection!=null)
						connection.close();
				}
				catch(Exception ex)
				{
					Logging.error("Error : Unable to close Connection "+ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}

	public static String getIdentifierCodeId(String identifier_code_value)
	{
		String id="0";	
		Connection connection = null;
		Connect c = ConnectInit.getConnect();
		try{
			String qry=null;
			//getConnection();
			if(connection == null)
			{
				connection = c.getdbConnection();
			}
			qry=ConnectInit.queries.getProperty("get_identifier_code_id");
			ResultSet rs1 = connection.createStatement().executeQuery(qry);
			while(rs1.next())
			{
				if(identifier_code_value.equals(rs1.getString(2).toLowerCase()))
				{
					id=rs1.getString(1);
				}
				
			}
			rs1.close();
		}catch(Exception e){
			Logging.error("Errro while finding identifier_code_id "+e.getMessage());		
		}	finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ee){
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ex){
					Logging.error("Error : Unable to close Connection "+ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return id;
	}
	
	
	public static void stockidentifier(String fistockID,String ident_val,String ident_id,String series)
	  {
		Connection connection = null;
		Connect c= ConnectInit.getConnect();
		try
		{
			//getConnection();		
		
			if(connection == null)
			{
				connection = c.getdbConnection();
			}
			String qry=ConnectInit.queries.getProperty("get_stock_identifier_code_id");
			ResultSet rs1 = connection.createStatement().executeQuery(qry);	
			rs1.next();
			long stk_ident_code=rs1.getLong(1);
			rs1.close();
			
			Logging.debug("stk_ident_code "+stk_ident_code+" ident_id is "+ident_id+" fistockID "+fistockID+" ident_val "+ident_val+" series "+series);
			
			String query1=ConnectInit.queries.getProperty("insert_into_stock_identifier_code_fixed_income");	
			
			PreparedStatement psmt1 = connection.prepareStatement(query1);
			
			psmt1.setLong(1,stk_ident_code);
			Logging.debug("after stk_ident_code"+stk_ident_code);
			
			psmt1.setInt(2,Integer.parseInt(ident_id));
			Logging.debug("after ident_id"+ident_id);
						
			psmt1.setString(3,ident_val);
			Logging.debug("after ident_val"+ident_val);
			
			
			psmt1.setInt(4,Integer.parseInt(fistockID));
			Logging.debug("after fistockID"+fistockID);
			
			//INSERT INTO stock_identifier_codes(stock_identifier_code_id, identifier_code_id, identifier_code_value,  fi_stock_id)VALUES (?,?,?,?)
			
			psmt1.executeUpdate();
			
			String values=(new Long(stk_ident_code).toString())+","+ident_id+","+fistockID+","+ident_val+","+series;
			Logging.debug("after values"+values);
			//maintain audit trail for stock_identifier_codes insert.
				//AuditTrail.StoreTableInsertUpdate("62",null,values);
				//Logging.getInfo("identifer code values inserted in stock identifier codes sucessfully");
		}
		catch(Exception e)
		{
			Logging.error(" Errro in identifier code insert : "+e.getMessage());
		}
		finally
		{
			try
			{
				if(connection!=null)
					connection.close();
			}
			catch(Exception ee)
			{
				try
				{
					if(connection!=null)
						connection.close();
				}
				catch(Exception ex)
				{
					Logging.error("Error : Unable to close Connection "+ex.getMessage());
				}
				Logging.error("Error : Unable to close Connection "+ee.getMessage());
			}
		}
	 }
}	

	
	
	
	
	
	
	
	
	
