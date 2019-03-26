package harrier.income.com.fixedincome;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
public class StockBondsResults {
	static Logger Logging = Logger.getLogger(StockBondsResults.class);
	public static Vector identifier_list=new Vector();
	Vector vector_IdentifierCodeBelogsTo;
	
	/**
	 * This method is written by Manoj Adekar
	 * method to get details for stock .
	 * input parameter is stock_id.
	 */
	public static ResultSet resultStockBonds(Connection connection,String stkid)
	{
		ResultSet rs=null;
	//	app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		if(connection==null)
		{
			connection = con.getdbConnection();
		}		
		
		try
		{
			PreparedStatement  stmt = connection.prepareStatement(ConnectInit.queries.
				getProperty("stock_details_for_fixed_income_stock_master"));
		
			stmt.setInt(1,Integer.parseInt(stkid));
			Logging.debug("Query atter set "+stmt);
			rs = stmt.executeQuery();		
			Logging.debug("Query executed "+rs);
		}
		catch(Exception e) 
		{
			Logging.error("StockResult Class error : "+e.getMessage()); 
		}			
		Logging.debug("Result set "+rs);
		return rs;
	}
	
	public static  Vector getIdentifierCode_fistkid(String fistockid)
	{
		Connection connection = null;
		
		ResultSet rs =null;				
//		app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		try{
			if(connection==null)
			{
				connection = con.getdbConnection();
			}	
			String[] identifier_name={"sedol","isin","ric","crisil","cusip","exchange_code","ticker"};
			int k=0;
			Logging.debug("identifier_name length is "+identifier_name.length);
			for(int i=0;i<(identifier_name.length);i++)
			{
				Logging.debug("i is "+i);
				if((identifier_name[i]).equals("exchange_code"))
				{
					PreparedStatement  stmt1 = connection.prepareStatement(ConnectInit.queries.
							getProperty("get_exchange_code_name_for_fixed_income_stockid"));
					
					stmt1.setString(1,fistockid);
					stmt1.setString(2,fistockid);
					
					Logging.debug("Query after set "+stmt1);
					
					ResultSet rs1 = stmt1.executeQuery();
					Logging.debug("rs1 is "+rs1);
					
					if(rs1.next())
					{
						String exch_code=(String)rs1.getString("identifier_code_value");
						Logging.debug("exch_code is "+exch_code);
						identifier_list.add(k,exch_code);
						k++;
					}
					else
					{
						identifier_list.add(k,"");k++;
					}
				}
				else
				{
					rs=null;
					Logging.debug("(identifier_name[i]) is "+(identifier_name[i]));
					PreparedStatement  stmt = connection.prepareStatement(ConnectInit.queries.
							getProperty("get_identifier_code_name_for_fixed_income_stockid"));
					stmt.setString(1,fistockid);
					stmt.setString(2,(identifier_name[i]));
					Logging.debug("Query atter set "+stmt);
					rs = stmt.executeQuery();	
					if(rs.next()){
						String identifier=((String)rs.getString("identifier_code_value"));
						Logging.debug("identifier is "+identifier);
						identifier_list.add(k,identifier);					
						k++;
					}else{
						identifier_list.add(k,"");	
						k++;
					}	
				}
			}
			   rs.close();
			  
			Logging.debug("vector size in get identifier "+identifier_list.size());
		}catch(SQLException e){
			Logging.error(" SQL Error : "+e.getMessage());
		}finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return identifier_list;
		
	}
	public Vector getIdentifierCodeBelogsTo() {
		return vector_IdentifierCodeBelogsTo;
	}
	public void setIdentifierCodeBelogsTo(String sedol, String isin,
			String ric, String crisil, String cusip, String exchange_code,
			String ticker, String stockid) {
		Vector icode_list = new Vector();
		int l = -1;
		if (sedol != null && !sedol.equals("")) {
			l++;
			icode_list.add(l, sedol);
		}
		if (isin != null && !isin.equals("")) {
			l++;
			icode_list.add(l, isin);
		}
		if (ric != null && !ric.equals("")) {
			l++;
			icode_list.add(l, ric);
		}
		if (crisil != null && !crisil.equals("")) {
			l++;
			icode_list.add(l, crisil);
		}
		if (cusip != null && !cusip.equals("")) {
			l++;
			icode_list.add(l, cusip);
		}
		if (exchange_code != null && !exchange_code.equals("")) {
			l++;
			icode_list.add(l, exchange_code);
		}
		if (ticker != null && !ticker.equals("")) {
			l++;
			icode_list.add(l, ticker);
		}
//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		java.sql.ResultSet rs = null;
		
		
		vector_IdentifierCodeBelogsTo = new Vector();

		int i = 0;
		Logging
				.debug("setVector_IdentifierCodeBelogsTo of setIdentifierCodeBelogsTo");
		try {
			  if (connection == null) {
				connection = con.getdbConnection();
			  }
			for (int m = 0; m < icode_list.size(); m++) {
				rs = con.getStockList(connection,"identifier_code_belongs_to_fixed_income",
						(String) icode_list.get(m));
				while (rs.next()) {
					if (!(rs.getString(2).equals(stockid))) {
						if (rs.getString(1) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(1));
						}
						i++;

						if (rs.getString(2) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "0");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(2));
						}
						i++;

						if (rs.getString(3) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(3));
						}
						i++;
						if (rs.getString(4) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(4));
						}
						i++;
						if (rs.getString(5) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(5));
						}
						i++;

						if (rs.getString(6) == null) {
							vector_IdentifierCodeBelogsTo.add(i, "--");
						} else {
							vector_IdentifierCodeBelogsTo.add(i, rs
									.getString(6));
						}
						i++;
					}
				}
				
			}
			rs.close();
		} catch (SQLException sqlexp) {
			Logging.error("Error : " + sqlexp.getMessage());
		}finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	

}
