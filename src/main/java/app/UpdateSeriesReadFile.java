/*
 * Created on Jun 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateSeriesReadFile {
	static Logger Logging = Logger.getLogger(UpdateSeriesReadFile.class);
	public static Hashtable table1 = new Hashtable();
	Connection con=null;
	boolean improperFormat=true;
	 public static StringBuffer getHashnBuffer(StringBuffer buffer,BufferedReader br)
	 {
	 	Logging.debug("INside UpdateSeriesReadFile");
	 	String str;
	 	try
		{
	 		Logging.debug("Inside FDR try");
	 		String[] arr ;	
	 		int i;
	 		while((str=br.readLine())!=null )
	 		{
	 			buffer.append("<tr>");
	 			arr= str.split(",");
				i=0;
				if(arr.length==0) continue;
				UpdateSeriesForm   FD=new UpdateSeriesForm();
				int arrlen=arr.length;
				Logging.debug("Inside FDR after new FinancialDetailForm();  "+arrlen);
				if(arrlen!=2){
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer;
				}
				if(arrlen==2)
				{
					Logging.debug("Inside update series");
					while(i<arrlen)
					{						
						switch(i)
						{
							case 	0:
								FD.setSymbol(arr[i]);
								break;
							case 	1:
								FD.setSeries(arr[i]);
								break;
							default :
								Logging.debug("Default switch case : UpdateSeries");
								break;								
						}
						if(arr[i].equals("") || arr[i]==null )
						{
							buffer.append("<td align='center'><font color='white'> ");	
							buffer.append(".");
							buffer.append(" </font></td>");	
						}else
						{
							buffer.append("<td> ");	
							buffer.append(arr[i]);
							buffer.append(" </td>");	
						}		
						i++;
					}
				}
				Logging.debug("Line "+i);	
				/*
				 * if exchange is not considered then there is 
				 * possiblity of more than 1 company getting selected
				 */
				String FDseries="";
				//FDseries=FD.getSeries();
				 
				if(FD.getSymbol()!=null || !(FD.getSymbol().equals(null))) 
					table1.put(FD.getSymbol(),FD);
				buffer.append("</tr>"); 				 
		 	}	 		
		}
	 	catch(Exception e)
		{
	 		Logging.error("Error : "+e.getMessage());
	 		return null;
		}
	 	Logging.debug("Inside FDR before return buffer ");
	 	return buffer;
	 }
	 public static StringBuffer StoreSeriesUpdated(String Exchangeid) //StringBuffer
	 {
	 	StringBuffer buffer=new StringBuffer();
	 	StringBuffer buffernew=new StringBuffer();
	 	String stock_id="";
	 	int inscounter=0;
	 	int updcounter=0;
	 	int unimpcounter=0;
	 	int countnot=0;
	 	int counter1=0;
	 	Connect connect = ConnectInit.getConnect();
		Connection connection=null;
	 	try
		{	
			Logging.debug("inside try update series");
			String str="";
			int i;
			Logging.debug("StoreSeriesUpdated Before con");
			
			/*if(Connect.con == null){				
				Connection con = connect.getConnection();
			}*/
			try{
				if(connection==null)
					connection = connect.getConnectionForTransaction();
			}catch(Exception e) {
				Logging.error(" Error : "+e.getMessage());
			}
			PreparedStatement pst;	
			ResultSet result=null;	
			String stk_id=null,stk_series=null;
			Enumeration e = table1.keys();
			int counter=0;
			String key="";
			for(e=table1.keys();e.hasMoreElements();)			
			{	
				counter1++;
				if(counter==5){						
					int a=result.CLOSE_CURSORS_AT_COMMIT;				
					connection.commit();
					connection.setAutoCommit(true);
					Logging.debug("counter after commit 5 IS "+counter1);					
					counter=0;					
				}	
				if(counter1%100==0){
					connection.commit();
					connection.close();
					//Logging.getDebug("connection after counter 100 is "+connect );
					try{
						try
						{
						if(connection==null)
							connection = connect.getConnectionForTransaction();
						}catch(Exception e2) {
							Logging.error(" Error : "+e2.getMessage());
						}
						Logging.debug("connection after counter 100 is "+connect );
						connection.rollback();
						Logging.debug("connection after counter 100 is "+connection);
						}catch(SQLException ex){
							connection.close();
							Logging.error("Error : Unable to get Transaction connection "+ex.getMessage());
						}
				}
				key = (String)e.nextElement();	
				UpdateSeriesForm  FD = (UpdateSeriesForm)table1.get(key);	
				if(FD.getSeries().equals("EQ") || FD.getSeries().equals("BE")){
					pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_stock_detail_for_ticker_code"));
					Logging.debug(" ticker code " + FD.getSymbol());
					pst.setString(1,FD.getSymbol());					
					result = pst.executeQuery();
					Logging.debug("result is "+result);
					while(result.next()){
						stk_id=result.getString(1);
						stk_series=result.getString(2);
						Logging.debug("ticker code "+FD.getSymbol()+" stock_id is "+stk_id+" stk_series is "+stk_series);
					}
					Logging.debug("stk_id is "+stk_id);
					if(stk_id!=null && !(stk_series.equals(FD.getSeries()))){
						if(stk_series.equals(FD.getSeries())){
							unimpcounter++;
							buffer.append("<tr><td>");
							buffer.append(key);
							buffer.append("</td><td><font color='blue'>Series Already exist For Stock.</font></td></tr>");
							continue;
						}
						Logging.debug("before updating stock series stk_id is "+stk_id);
						String ticker=FD.getSeries()+(FD.getSymbol().substring(2));
						Logging.debug("ticker in update series is "+ticker);
						PreparedStatement update_series=connection.prepareStatement(ConnectInit.queries.getProperty("update_series_for_stock"));
						update_series.setString(1,FD.getSeries());
						update_series.setString(2,ticker);
						update_series.setString(3,stk_id);
						update_series.executeUpdate();
						Logging.debug(" stock series updated succesfully for stk_id  "+stk_id);
						inscounter++;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><td><font color='blue'>Series Updated For Stock.</font></td></tr>");
					}else{	
						updcounter++;
						buffer.append("<tr><td>");
						buffer.append(key);
						buffer.append("</td><td><font color='blue'>Stock Does Not Exist.</font></td></tr>");
						continue;
					}					
				}else{
					countnot++;
					buffer.append("<tr><td>");
					buffer.append(key);
					buffer.append("</td><td><font color='blue'>Series Other Than EQ,BE Can Not Be Updated For Stock.</font></td></tr>");
					continue;
				}
			}			
		table1.clear();
		buffernew.append("<br><tr><font color=Blue><td>Series Updated Succesfully :</td><td>");
	    buffernew.append(inscounter);
	    buffernew.append("</td></font></tr>");
		buffernew.append("<br><tr><font color=Blue><td>Stock Does Not Exist :</td><td>");
	    buffernew.append(updcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Series Already exist For Stock:</td><td>");
	    buffernew.append(unimpcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Series Other Than EQ,BE Can Not Be Updated For Stock:</td><td>");
	    buffernew.append(countnot);
	    buffernew.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
	    buffernew.append(counter1);
	    buffernew.append("</td></font></tr>");
	    buffernew.append(buffer);
	    buffer=null;
	 }catch(Exception e)
		{
			Logging.error("Error : "+e.getMessage());
		}	
	 finally{
		try{
			if(connection!=null)
				connection.close();
		}catch(Exception ex){
			Logging.error(" Error : Unable to close connection "+ex.getMessage());
		}
	}
		return buffernew;
		//Logging.getDebug("sending buffer");
		
	} 
	  
}

