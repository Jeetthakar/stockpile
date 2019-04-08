/*
 * Created on Sep 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.harrier.initializeation.ConnectInit;

public class StockDetailsCollection
{
	
	public Hashtable destinationTable = new Hashtable();
	public Hashtable sourceTable = new Hashtable();
	public String errorMessage=null;
	public double totalMCV=0;
	private static Vector stockids = new Vector();
	Logger logger;
	
	public StockDetailsCollection()
	 {
		logger =Logger.getLogger(StockDetailsCollection.class);
		//PropertyConfigurator.configure(Connect.resourceurl+"resources/log4j.properties");
	 }

	public void addStocksInSourceTable(Connection con,String query,String param,String indexID,String type,boolean forExch,String basedate)
	 {
	    ResultSet rs_stockclosingvalue;
	    logger.info("sourceTable  : "+sourceTable.size() );
	    logger.info("destinationTable  : "+destinationTable.size() );
		Hashtable table = null;
		logger.debug("hi in addStocksInSourceTable : ");
		
		if(forExch || type.equals("1"))
		{
		    logger.info("sourceTable  a: "+sourceTable.size() );
		    logger.info("destinationTable  a: "+destinationTable.size() );
		 table = sourceTable;
		 logger.info("sourceTable  a: "+sourceTable.size() );
		    logger.info("destinationTable  a: "+destinationTable.size() );
		}
		else  if(type.equals("2"))
		  {
			logger.debug("Inside 2nd else : "+destinationTable.size());
			table = destinationTable;
			logger.debug("StockDetailsCollection:Inside else");
		  }
		 	
		logger.info("sourceTable  : "+sourceTable.size() );
	    logger.info("destinationTable  : "+destinationTable.size() );
	    
	    // source is getting cleared over here
		if(!table.isEmpty()&& indexID != null)
	 		table.clear();	
		logger.info("sourceTable  : "+sourceTable.size() );
	    logger.info("destinationTable  : "+destinationTable.size() );
		
		try{
				ResultSet rs = null;
				PreparedStatement stmt = con.prepareStatement(query);
				logger.info("sourceTable  x: "+sourceTable.size() );
			    logger.info("destinationTable  x: "+destinationTable.size() );
				logger.debug("records for stock details are : ");
				logger.debug("query :"+query);
				logger.debug("param :"+param);
				logger.debug("indexID :"+indexID);
				logger.info("sourceTable  : "+sourceTable.size() );
			    logger.info("destinationTable  : "+destinationTable.size() );
				if(forExch)
				{
					logger.debug("inside xchng check");
					stmt.setInt(1,Integer.parseInt(param));
					stmt.setInt(2,Integer.parseInt(indexID));
					stmt.setInt(3,Integer.parseInt(param));
				}
				else if(type.equals("1"))
				   stmt.setInt(1,Integer.parseInt(indexID));
				else if(type.equals("2"))	
					{
						logger.debug("StockDetailsCollection: Inside if of 2");
						stmt.setInt(1,Integer.parseInt(indexID));
					}
					
					rs = stmt.executeQuery();
					logger.info("sourceTable  aa: "+sourceTable.size() );
				    logger.info("destinationTable  aa: "+destinationTable.size() );					
				if(rs != null)
				 {
      				
					int i=0;
					//logger.debug("StockDetailsCollection: After rs null check ");
					logger.info("sourceTable  bb: "+sourceTable.size() );
				    logger.info("destinationTable  bb: "+destinationTable.size() );
					while(rs.next())
				    {          
						
					  ++i;
					  int id = rs.getInt(1);
				   	  String name=rs.getString(2);
				   	  double iwf = rs.getDouble(3);
				   	  String date=rs.getString(4);
				   	  // add code here to match tmcv
				   	double  ltp = rs.getDouble(5);
				   	query=ConnectInit.queries.getProperty("stock_closingvalue");
				   	PreparedStatement pst_closing  =con.prepareStatement(query);
				   	pst_closing.setInt(1,id);
				   	rs_stockclosingvalue=pst_closing.executeQuery();
				   	if(rs_stockclosingvalue.next())
				   	ltp=rs_stockclosingvalue.getDouble("stock_closing_value");
				   	  
				   	  String currency = rs.getString(6);
				   	  long tis = rs.getLong(7);
				   	  long market_lot=rs.getLong(8);
			//	   	logger.debug("While putting in hashtable 1 ");
				  	logger.debug("name : "+name);
			//	  	logger.debug("id : "+id);
				   	  table.put(String.valueOf(id), new StockDetails(id,name,iwf,ltp,currency,tis,market_lot,date));
				   	}	
					
					 logger.debug("Number of Stocks "+i);
					 logger.info("sourceTable  cc: "+sourceTable.size() );
					    logger.info("destinationTable  cc: "+destinationTable.size() );
					 sourceTable.putAll(table);
			//		 destinationTable.clear();
					 logger.info("sourceTable  dd: "+sourceTable.size() );
					    logger.info("destinationTable  dd: "+destinationTable.size() );
					 logger.debug("source table size=  "+sourceTable.size());
				 }	
				rs= null;
				stmt=null;				
		   } catch(SQLException e){logger.debug("Error in sourceTable "+e.getMessage());}				
						
		  // table=null;
	 }

	public void addStocksInSourceTable(Connection con, String query,String code)
	{
		if(!sourceTable.isEmpty())
			sourceTable.clear();	
		
		try{
				ResultSet rs = null;
				PreparedStatement stmt = con.prepareStatement(query);	
				logger.debug("query : "+query);
				logger.debug("code : "+code);
				stmt.setInt(1,Integer.parseInt(code));
				rs = stmt.executeQuery();
				if(rs != null)
				 {
					int i=0;
					while(rs.next())
				     {          
						++i;
					  	int id = rs.getInt(1);
					  	String name=rs.getString(2);
					  	double iwf = rs.getDouble(3);
					  	String date=rs.getString(4);
					  	double ltp = rs.getDouble(5);
					  	String currency = rs.getString(6);
					  	long tis = rs.getLong(7);
					  	long market_lot=rs.getLong(8);
					  	logger.debug("While putting in hashtable 2");
					  	logger.debug("name : "+name);
					  	logger.debug("id : "+id);
					  	sourceTable.put(String.valueOf(id), new StockDetails(id,name,iwf,ltp,currency,tis,market_lot,date));
				     }	
				 }
		rs= null;
		stmt=null;
	
		}catch(Exception e) {}		
	}
	
	public void addStocksInSourceTable(String[] keys)
	 {
		errorMessage =null;
		logger.debug("Inside add stocks in source table");
		if(!(destinationTable.isEmpty())&& (keys.length > 0) && (keys!=null))
		{
			for(int i=0;i<keys.length;i++)
			{
			 try{
			 	logger.debug(keys[i]);
				Object obj = destinationTable.get(keys[i]);
				if(!sourceTable.containsKey(keys[i]))
				{
					sourceTable.put(keys[i],obj);
					destinationTable.remove(keys[i]);
				}
			 }catch(Exception e){}
			 }
		}
		setTotalMCV();
	 }
	public void addStocksInSourceTable1(Corporate corporateact,String[] keys)
	 {
		logger.debug("in add");
		Hashtable data=corporateact.getHash2();		
		logger.debug("data=="+data+"keys="+keys.length);
		Hashtable hash4=corporateact.getHash4();
		hash4.clear();
		corporateact.setHash4(hash4);
		hash4=corporateact.getHash4();
		if(keys!=null)
		{
			if(!(data.isEmpty())&& (keys.length > 0) && (keys!=null))
			{
				for(int i=0;i<keys.length;i++)
				{
				 try{
				 	logger.debug(keys[i]);
					Object obj = data.get(keys[i]);
					logger.debug("obj==="+obj+"hash4=="+hash4);
					if(!hash4.containsKey(keys[i]))
					{
						hash4.put(keys[i],obj);						
						data.remove(keys[i]);
					}					
				 }catch(Exception e){}
				 }
				corporateact.setHash4(hash4);
				logger.debug("hash4=="+corporateact.getHash4());
			}
		}
	}
	
	public void removeStocksFromSourceTable(String[] keys)
	 {
		errorMessage =null;
		logger.debug("sourceTable.isEmpty() : "+sourceTable.isEmpty());
		logger.debug("sourceTable.size() : "+sourceTable.size());
		if(!(sourceTable.isEmpty())&& (keys!=null) && (keys.length > 0)  )
		{
			logger.debug("Inside if of removeStocks : "+keys.length);
		 
			 for(int i=0;i<keys.length;i++)
			 {
			     logger.debug("i : "+ i);
			  try{
			      logger.debug("i1 : "+ i);
			 	Object obj = sourceTable.get(keys[i]);
				if(!destinationTable.containsKey(keys[i]))
				{
				    logger.debug("i2 : "+ i);
					System.out.print(keys[i]+" ");
					logger.info("sourceTable.size() : "+ sourceTable.size());
					logger.info("destinationTable.size() : "+destinationTable.size() );
					destinationTable.put(keys[i],obj);
					sourceTable.remove(keys[i]);
					logger.info("sourceTable.size() : "+ sourceTable.size());
					logger.info("destinationTable.size() : "+destinationTable.size() );
				}
			  }catch(Exception e){logger.debug("Error in removeStocks "+e.getMessage());}
		 	}
		 
	}
	//	logger.debug("after if of removeStocks");
		setTotalMCV();
	 }
	public void removeStocksFromSourceTable1(Corporate corporateact,String[] keys)
	 {
		logger.debug("data==="+keys[0]);
		Hashtable data=corporateact.getHash1();
		Hashtable hash3=corporateact.getHash3();
		//hash3.clear();
		logger.debug("data==="+data);
		logger.debug("data3==="+hash3);
		if(!(data.isEmpty())&& (keys!=null) && (keys.length > 0)  )
		{
				 
			 for(int i=0;i<keys.length;i++)
			 {
			  try{
			 	Object obj = data.get(keys[i]);
			 	logger.debug("odj in remove=="+obj);
				if(!hash3.containsKey(keys[i]))
				{
					System.out.print(keys[i]+" ");
					hash3.put(keys[i],obj);
					data.remove(keys[i]);
				}
			  }catch(Exception e){logger.debug("Error in removeStocks "+e.getMessage());}
		 	}
		 
	}	
	 }	
public double getTotalMCV()
 {
	return totalMCV;
 }
	
public void setTotalMCV()
	{
	/*instead of creating object of index composition to get parent index currency id
	 * try to get the currency id from index composition form since this method is being 
	 * is being called from the index composition page .
	*/
		double total=0;
		for(Enumeration e=destinationTable.keys();e.hasMoreElements();)
		{
			String id = e.nextElement().toString();
			StockDetails rs = (StockDetails)destinationTable.get(id);
			total+=rs.getMktCapital(2);
			rs.setTotalMktCapital(total);
		}
		this.totalMCV = total;	
	}
public void calculateAdjustedMktCap(String id[],HttpServletRequest request)
    throws NumberFormatException
	{
		if(id !=null && id.length>0)
		{
		//	logger.debug("Id length "+id.length);
			for(int i=0;i<id.length;i++)
			{
			 StockDetails  st = (StockDetails)destinationTable.get(id[i]);
			  try{
			 	  double iwf = Double.parseDouble(request.getParameter("iwf:"+st.getStockID())); 
			 		if(iwf > 1)
			 		 throw new NumberFormatException();	
			 		st.setAdjustedIWF(iwf);
			 		errorMessage=null;
			     }catch(NumberFormatException e)
						  {
			 		    	errorMessage= "Invalid value, should be between 0 and 1";
						  }
			}
		}
	
	}

public String getErrorMessage()
{
	return errorMessage;
}
public long  getTotalStocks()
	{
	  long stocks =0,count=0;
	   for(Enumeration e = destinationTable.keys();e.hasMoreElements();)
		{
			String id = e.nextElement().toString();
			StockDetails rs = (StockDetails)destinationTable.get(id);
			stocks +=rs.getOutStanding();
			count++;
		}
	   logger.debug("no of stocks "+stocks+" count "+count);
		return count;
	}


public void storeComposition(String indexID,Connection con,String query)
	{
	/*instead of creating object of index composition to get parent index currency id
	 * try to get the currency id from index composition form since this method is being 
	 * is being called from the index composition page .
	*/
	IndexCompositionForm icf=new IndexCompositionForm();//created object of indexcomposition form class 
	String indexCurrencyId=icf.getParentCurrencyId();//get the index currency id from object
	logger.debug("before if Inside isEmpty  "+destinationTable.isEmpty());	
		if(!destinationTable.isEmpty())
			{
			logger.debug("Inside isEmpty  "+destinationTable.isEmpty() +" and  query is "+query); 
			try {
				
				PreparedStatement psmt = con.prepareStatement(query);
			   	Statement st = con.createStatement();
				 	
				int id1=0;
				ResultSet rs=null;	
			 for(Enumeration e=destinationTable.keys();e.hasMoreElements();)
				{
				    rs = st.executeQuery("select nextval('index_stock_id')");
				
					if(rs.next())	
					 id1 = rs.getInt(1);
					  logger.debug("id : "+id1);
				    	logger.debug("indexID : "+indexID);
					String id = e.nextElement().toString();
					StockDetails sd = (StockDetails)destinationTable.get(id);
					
					psmt.setInt(1,id1);
					psmt.setDouble(2,sd.getAdjustedIWF());
			
					 String date=QueryClass.formatDate();
					psmt.setString(3,date);
					psmt.setInt(4,Integer.parseInt(indexID));
					psmt.setInt(5,sd.getStockID());
					psmt.setDouble(6,sd.getMktCapital(2));
						
					psmt.executeUpdate();
					rs=null;
				}
				
				logger.debug("Composition stored successfully!!! ");
				errorMessage=null;
		 	 }catch(Exception e){ logger.debug("StockDetailsCollection: Error inserting in table "+e.getMessage());} 
	  
	  }else errorMessage ="";
	
	}
public void flushData()
{
	sourceTable.clear();
	destinationTable.clear();
	logger.debug("Data Is Flushed !!! with source size = "+sourceTable.size()+" and destination size="+destinationTable.size());	
}

}
