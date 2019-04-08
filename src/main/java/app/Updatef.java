/**
 * Updatef
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;
import com.linuxense.javadbf.DBFReader;



public class Updatef {
	static Logger Logging = Logger.getLogger(Updatef.class);
	public static Hashtable tablen = new Hashtable(200000);//Creation of hashtable object for csv file
	public static Hashtable tablendbf=new Hashtable(300000);//Creation of hashtable object dbf file
	private static Connection con=null;
	static Connect con1 = ConnectInit.getConnect();
	boolean improperFormat=true;
	public static String extension=null; //String extension used to store extension such as "csv","dbf"
	//public int fo_new_issue_stk_id=0;
	
	/** getHashnBufferCsv method is used to read data line by line(read each record)
	 *  from csv file and store data into
	 * into the StringBuffer.The method also inserts a unique hash key into the hashtable.
	 * so that,while extracting data
	 * the unique hash key is used.
	 */
	public static StringBuffer getHashnBufferCsv(StringBuffer buffer,BufferedReader br,String temp){ 
		extension=temp;
		Logging.debug("INside getHashnBufferCsv");
		String str;
		try{
			Logging.debug("Inside getHashnBufferCsv try");
			String[] arr ;	
			int i;
			int counter=1;//Counter used to craete unique hashkey.
			while((str=br.readLine())!=null )//Read the record line by line. 
			{
				buffer.append("<tr>");
				arr= str.split(",");
				i=0;
				if(arr.length==0) continue;
				UpdatefForm   FD=new UpdatefForm();//Creation of object of UpdatefForm class in which getters and setters of each fields of csv files are there.
				int arrlen=arr.length;//Finds out no fields in F& O csv file.
				Logging.debug("Inside getHashnBufferCsv after new UpdatefForm;  "+arrlen);
		
				/*If arrlen!=15 means it is not F&O csv file,it means user have selected
				 * improper file format from dropdown list.So,don't insert anything in the buffer.
				 */ 
				if(arrlen!=15){
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					return buffer;
				}
				if(arrlen==15)
				{
					Logging.debug("Inside getHashnBufferCsv");
					while(i<arrlen)//Set the corrosponding field value and insert the field value into buffer.
					{						
						switch(i)
						{
				    		case 	0:
					   			FD.setInstrument(arr[i]);
								break;
				    		case 	1:
								FD.setSymbol(arr[i]);
								break;
				    		case 2:
								  FD.setExpr_dt(arr[i]);
								  break;
				    		case 3:
							   FD.setStrike_pr(arr[i]);
							      break;
				    		case 4:
							 	   FD.setOption_typ(arr[i]);
							 	   break;
				    		case 5:
							 	FD.setOpen(arr[i]);
							     break;
				    		case 6:
							 	FD.setHigh(arr[i]);
							 	break;
				    		case 7:
							 	FD.setLow(arr[i]);
				    		case 8:
							 	FD.setClose(arr[i]);
						     	break;
				    		case 9:
							  	FD.setSettle_pr(arr[i]);
							 		break;
				    		case 10:
							 	FD.setContracts(arr[i]);
							 	break;
				    		case 11:
							 		FD.setVal_inlakh(arr[i]);
							 		break;
				    		case 12:
							 	FD.setOpen_int(arr[i]);
							 	break;
				    		case 13:
							 	FD.setChange_in_ol(arr[i]);
							 	break;
				    		case 14:
							 	FD.setTimestamp(arr[i]);
							 	break;
							 	
				    		default :
								Logging.debug("Default switch case : updatef");
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
					String FDseries="";
				
					/*Creation of unique hashkey and insertion into unique hashtable*/	
				 
					if(FD.getSymbol()!=null || !(FD.getSymbol().equals(null))) 
					{
						String key_symbol=FD.getSymbol();
						String key_expr_date=FD.getExpr_dt();
						//String Key_strike_pr=FD.getStrike_pr();
						Integer c1= new Integer(counter);
						String count=c1.toString();
						String key_fr_hashtable=key_symbol+key_expr_date+count;
						tablen.put(key_fr_hashtable,FD);
						counter++;
					
					}
					buffer.append("</tr>"); 				 
			}	 		
		}
		catch(Exception e)
		{
			Logging.error("Error : "+e.getMessage());
			return null;
		}
	 		Logging.debug("Inside getHashnBuffer before return buffer ");
	 		return buffer;
	}
	/** getHashnBufferDbf method is used to read data line by line(read each record)
	 *  from dbf file and store data into
	 * into the StringBuffer.The method also inserts a unique hash key into the hashtable.
	 * so that,while extracting data
	 * the unique hash key is used.
	 */ 
	public static StringBuffer getHashnBufferDbf(StringBuffer buffer_dbf,String temp,String str_fileName,Object []  rowObjects)
	{
		extension=temp;//Store  File extension into String extension.
		Logging.debug("Inside getHashnBufferDbf");
		String str;
	 	
		try
		{     
			FileInputStream inputStream1  = new FileInputStream(str_fileName);//Creates FileInputStream object and pass the filename to this object.
			DBFReader reader1 = new DBFReader(inputStream1);//Creates DBFReader object and pass the FileInputStreamObject inputsream1 to this DBFReader.
			int counterdbf=1;//Counter used to create unique hashkey.
			Logging.debug("Inside getHashBufferDbf try");
			int i;
			while((rowObjects = reader1.nextRecord())!=null )//Read the record line by line.
	 	    {
				UpdatefDbform FC=new UpdatefDbform();//Creation of object of UpdatefDbForm class in which getters and setters of each fields of dbf files are there.
				buffer_dbf.append("<tr>");
				int arrlen=0;
				arrlen=reader1.getFieldCount();//Finds out no fields in F& O dbf file.
				i=0;
			 	Logging.debug("Inside getHashnBufferDbf");
			 	if(arrlen==0) continue;			
			 	while(i<arrlen)//Set the corrosponding field value and insert the field value into buffer.
			 	{						
			 		switch(i)
					{
						case 	0:
								FC.setInstrumentdbf(rowObjects[i].toString());
								break;
						case 	1:
								FC.setSymboldbf(rowObjects[i].toString());
								break;
						case 2:
								  FC.setExpr_dtdbf(rowObjects[i].toString());
								  break;
						case 3:
							   FC.setStrike_prdbf(rowObjects[i].toString());
							      break;
						case 4:
							 	   FC.setOption_typdbf(rowObjects[i].toString());
							 	   break;
						case 5:
							 	FC.setOpendbf(rowObjects[i].toString());
							     break;
						case 6:
							 	FC.setHighdbf(rowObjects[i].toString());
							 	break;
						case 7:
							 	FC.setLowdbf(rowObjects[i].toString());
						case 8:
							 	FC.setClosedbf(rowObjects[i].toString());
							 	break;
						case 9:
							 		FC.setSettle_prdbf(rowObjects[i].toString());
							 		break;
						case 10:
							 	FC.setContractsdbf(rowObjects[i].toString());
							 	break;
						case 11:
							 		FC.setVal_inlakhdbf(rowObjects[i].toString());
							 		break;
						case 12:
							 	FC.setOpen_intdbf(rowObjects[i].toString());
							 	break;
						case 13:
							 	FC.setChange_in_oldbf(rowObjects[i].toString());
							 	break;
						case 14:
							 	FC.setTimestampdbf(rowObjects[i].toString());
							 	break;
							 	
						default :
								Logging.debug("Default switch case : updatef");
								break;								
					}
			 		if(rowObjects[i].equals("") || rowObjects[i]==null )
			 		{
			 			buffer_dbf.append("<td align='center'><font color='white'> ");	
			 			buffer_dbf.append(".");
			 			buffer_dbf.append(" </font></td>");	
			 		}else
			 		{
			 			buffer_dbf.append("<td> ");	
			 			buffer_dbf.append(rowObjects[i]);
			 			buffer_dbf.append(" </td>");	
			 		}		
			 		i++;
			 	}
				
			 	Logging.debug("Line "+i);	
			 	String FDseries="";
				    
			 	/*Creation of unique hashkey and insertion into unique hashtable*/	
				 
			 	if(FC.getSymboldbf()!=null || !(FC.getSymboldbf().equals(null))) 
			 	{
			 		String key_symbol_dbf=FC.getSymboldbf().trim();
			 		String key_expr_date_dbf=FC.getExpr_dtdbf().trim();
			 		Integer c2= new Integer(counterdbf);
			 		String countdbf=c2.toString().trim();
			 		String key_fr_hashtable_dbf=key_symbol_dbf+key_expr_date_dbf+countdbf;
			 		tablendbf.put(key_fr_hashtable_dbf,FC);
			 		counterdbf++;
			 	}
			 	buffer_dbf.append("</tr>"); 				 
	 	    }	
	 		
	 	  
		}
	 	
		catch(Exception e)
		{
			Logging.error("Error : "+e.getMessage());
			return null;
		}
	 	
		Logging.debug("Inside gethashnBuffer1 before return buffer ");
	 	return buffer_dbf;
	}
	/**The method storefo checks the file extension and depending upon the file 
	 * extension the corrosponding methods are called.it also extracts stockid 
	 * and indexid.If the stock already exists  then it calls dailystockprices method
	 * and if stock does not exists then it calls unimportedstockprices method.If the index 
	 * already exists then it calls dailyindexprices method and if index does not exists then
	 * it calls unimportedindexprices method.It also extracts "id" from future_options_instruments table.
	 * the "id" is used to recognize that whether the particular record in file is a stock record or index record
	 * ,in a situation when,both stock and indexes are new i.e.  both not already exists.
	 */
     
	public static StringBuffer Storefo(String stk_ex_id) 
	{
		/*Creation of StringBuffer object used to display message on the screen
		 that,if particular stock or index does not exists
		 */
		StringBuffer buffer=new StringBuffer(300000);
		String stockID = null;//Used to collect stockid for csv file.
		String indexID=null;//Used to collect indexid for csv file.
		String id=null;//Used to collect "id" for csv file.
		String iddb=null;//Used to collect "iddb" for dbf file.
		boolean allow=true;
		int inscounter=0;
		int inscounter1=0;
	 	int updcounter=0;
	 	int unimpcounter=0;
	 	int counter1=0;
	 	String stock_id="";
		int count=0;
		String key="";//Used to collect unique hashkey for csv file.
		String keydbf="";//Used to collect unique hashkey for dbf file.
		String stockIDdb=null;//Used to collect stockid for dbf file.
		String indexIDdb=null;//Used to collect indexid for dbf file.
		int fo_new_issue=0;
	        
		if(extension.equals("csv"))//If file extension equals to "csv"
		{
			Logging.debug("Valueof temp in csv"+extension);
			Enumeration e = tablen.keys();//Stores hashkeys into enumeration e.
			while(e.hasMoreElements())//Repeat while loop until e has hashkeys.
			{
				allow=true;
				count++;
				stockID=null;
				indexID=null;
				id=null;
				/*Create the object of Connect class and get the connection*/
				Connect con = ConnectInit.getConnect();
				Connection connection=null;
				if(connection == null){
					connection=con.getdbConnection();
				}
			
				key = (String)e.nextElement();//Obtains the unique hashkey.	
				Logging.debug("key is "+key);
				UpdatefForm  FDD = (UpdatefForm)tablen.get(key);	//Maps the hashkey value to UpdatefForm object.
			
				try
				{
			          
					Logging.debug("Entry point");
					/* Obtains the value of symbol field which is used as searchkey for obtaining stockid and 
					 and index id from stock_identifier_codes and index_master tables respectively*/
					String searchkey=FDD.getSymbol().trim();
					Logging.debug("searchkey is"+searchkey);
					/*Obtains the value of instrument field which is used to obtain id*/
					String instrument=FDD.getInstrument().trim();
					Logging.debug("instrument is"+instrument);
					stockID=getStockId_NSE(stk_ex_id,searchkey,connection,con);//Obtains stockid.
					Logging.debug("stockID  is"+stockID);
					//indexID=getIndexId_NSE(stk_ex_id,searchkey);
					id=getStockIndexNumber(instrument,connection,con);//Obtains id.
					Logging.debug("id  is"+id);
				
					if(stockID==null && indexID==null && id.equals("1"))//It means that,particular record is new stock then store that particular record using unimportedstockprices method.
					{
						//String stkprice_unimprt_query=con.queries.getProperty("insert_into_F&O_new_issues_stock"); 
						insertUnimportedStockPrices(FDD,connection,con);
						unimpcounter++;
						buffer.append("<tr><td>");
						buffer.append(key);//Append the value of newstock into buffer.
						buffer.append("</td><td><font color='black'>Stock Does Not Exist.</font></td>");
						Logging.debug("Insertion into future and options stock prices new issues completes");
					}
					else if(stockID==null && indexID==null && id.equals("2"))//It means that,particular record is new index then store that particular record using unimportedindexprices method.
					{
						//String indexprice_unimprt_query=con.queries.getProperty("insert_into_F&O_new_issues_index"); 
						insertUnimportedIndexPrices(FDD,connection,con);
						updcounter++;
						buffer.append("<tr><td>");
						buffer.append(key);//Append the value of newindex into buffer.
						buffer.append("</td><td><font color='black'>Index Does Not Exist.</font></td>");
						Logging.debug("Insertion into future and options index prices new issues completes");
					}
					else if(stockID!=null && indexID==null)//It means that particular record in file is stock,which already exists in our database.
					{
						Logging.debug("Insertion into F&O daily stk");
						insertDailyStockPrices(FDD,stockID,connection,con);
						inscounter++;
						Logging.debug("Insertion into F&O daily stk completes");
					}
					else if(stockID==null && indexID!=null )//It means that particular record in file is index,which already exists in our database.
					{
						Logging.debug("Insertion into F&O daily prices");
						insertDailyIndexPrices(FDD,indexID,connection,con);
						inscounter1++;
						Logging.debug("Insertion into F&O daily index prices completes");
					}
				
				
				} catch (Exception e1) 
				{ 
					Logging.error("Error is>>"+e1 );
				}
				/*Close the connection finally*/
				finally{
						try{
							if(connection!=null)
								connection.close();
							}catch(Exception ee){
							try{
									if(connection!=null)
										connection.close();
								}catch(Exception ex)
								{
									Logging.error(" Error : Unable to close Connection "+ex.getMessage());
								}
								Logging.error(" Error : Unable to close Connection "+ee.getMessage());
							}
						}
				counter1++;
			}
	 	
	 	         
		}
		else if(extension.equals("dbf"))//If file extension equals to dbf.
		{
			Logging.debug("Valueof temp in dbf"+extension);
			Enumeration e1 = tablendbf.keys();//Stores hashkeys into enumeration e1.
			//Enumeration e1 = tablen1.keys();
			while(e1.hasMoreElements())//Repeat while loop until e1 has hashkeys.
			{
				allow=true;
				count++;
				stockIDdb=null;
				indexIDdb=null;
				iddb=null;
				/*Create the object of Connect class and get the connection*/
				Connect con = ConnectInit.getConnect();
				Connection connection=null;
				if(connection == null){
					connection=con.getdbConnection();}
			
				keydbf = (String)e1.nextElement();//Obtains the unique hashkey.	
				Logging.debug("key is "+keydbf);
				UpdatefDbform  FDDS = (UpdatefDbform)tablendbf.get(keydbf);//Maps the hashkey value to UpdatefDbForm object.	
			
				try
				{
					Logging.debug("Valueof temp in dbf"+extension);
					Logging.debug("Entry point for dbf");
					/* Obtains the value of symbol field which is used as searchkey for obtaining stockid and 
					 and index id from stock_identifier_codes and index_master tables respectively*/
					String searchkeydbf=FDDS.getSymboldbf().trim();
					Logging.debug("searchkey is(dbf)"+searchkeydbf);
					/*Obtains the value of instrument field which is used to obtain iddb*/
					String instrumentdbf=FDDS.getInstrumentdbf().trim();
					Logging.debug("instrument is(dbf)"+instrumentdbf);
					stockIDdb=getStockId_NSE_dbf(stk_ex_id,searchkeydbf,connection,con);
					Logging.debug("stockID  is"+stockIDdb);
					//indexIDdb=getIndexId_NSE(stk_ex_id,searchkey);
					iddb=getStockIndexNumberdbf(instrumentdbf,connection,con);
					Logging.debug("id is(dbf)"+iddb);
						
					//It means that,particular record is new stock then store that particular record using unimportedstockprices method.
					if(stockIDdb==null && indexIDdb==null && iddb.equals("1"))
					{
						//String stkprice_unimprt_query_dbf=con121.queries.getProperty("insert_into_F&O_new_issues_stock"); 
						insertUnimportedStockPricesdb(FDDS,connection,con);
						unimpcounter++;
						buffer.append("<tr><td>");
						buffer.append(keydbf);//Append the value of newstock into buffer.
						buffer.append("</td><td><font color='black'>Stock Does Not Exist.</font></td>");
						Logging.debug("Insertion into future and options stock prices new issues completes");
					}
					//It means that,particular record is new index then store that particular record using unimportedindexprices method.
					else if(stockIDdb==null && indexIDdb==null && iddb.equals("2"))
					{
						//String indexprice_unimprt_query_dbf=con121.queries.getProperty("insert_into_F&O_new_issues_index"); 
						insertUnimportedIndexPricesdb(FDDS,connection,con);
						updcounter++;
						buffer.append("<tr><td>");
						buffer.append(keydbf);//Append the value of newindex into buffer.
						buffer.append("</td><td><font color='black'>Index Does Not Exist.</font></td>");
						Logging.debug("Insertion into future and options index prices new issues completes");
					}
						//It means that particular record in file is stock,which already exists in our database.
					else if(stockIDdb!=null && indexIDdb==null)
					{
						Logging.debug("Insertion into F&O daily stk_dbf");
						insertDailyStockPricesdb(FDDS,stockIDdb,connection,con);
						inscounter++;
						Logging.debug("Insertion into F&O daily stk completes");
					}
					//It means that particular record in file is index,which already exists in our database.
					else if(stockIDdb==null && indexIDdb!=null )
					{
						Logging.debug("Insertion into F&O daily prices_dbf");
						insertDailyIndexPricesdb(FDDS,indexIDdb,connection,con);
						inscounter1++;
						Logging.debug("Insertion into F&O daily index prices completes");
					}
				} catch (Exception e2) { Logging.error("Error is>>"+e2 );}
				/*Close the connection finally*/
				finally{
						try{
							if(connection!=null)
								connection.close();
							}catch(Exception ee){
								try{
									if(connection!=null)
										connection.close();
									}catch(Exception ex){
											Logging.error(" Error : Unable to close Connection "+ex.getMessage());
									}
									Logging.error(" Error : Unable to close Connection "+ee.getMessage());
							}
				}	
				counter1++;
			}
	 	
					 	
		}
	        
		/*buffer.append("<tr><td>");
		buffer.append("");
		buffer.append("</td><td><font color='blue'>Prices Entered Successfully For All Other Stock.</font></td>");	*/
		//tablen.clear();
		Logging.info("Bhavcopy Inserted Successfully");
		tablen.clear();//Clear the hashtable which is  used to store unique hashkeys of csv file.
		tablendbf.clear();//Clear the hashtable which is used to store unique hashkeys of dbf file.
		StringBuffer buffernew=new StringBuffer();
		buffernew.append("<br><tr><font color=Blue><td>Values Inserted for F&O daily stk:</td><td>");
	    buffernew.append(inscounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Value Inserted for F&O daily index prices:</td><td>");
	    buffernew.append(inscounter1);
	    buffernew.append("</td></font></tr>");
		buffernew.append("<br><tr><font color=Blue><td>Index Does Not Exist :</td><td>");
	    buffernew.append(updcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Stock Does Not Exist:</td><td>");
	    buffernew.append(unimpcounter);
	    buffernew.append("</td></font></tr>");
	    buffernew.append("<br><tr><font color=Blue><td>Total Rows :</td><td>");
	    buffernew.append(counter1);
	    buffernew.append("</td></font></tr>");
	    buffernew.append(buffer);
	    buffer=null;
		
		return buffernew;
	}
	/**
	 * This method is used to store data of csv file into future_options_daily_stock_prices table.This
	 * method is called when stock exists(means it is not new stock)and indexid=null.It means that particular record
	 * is of stock.
	 */
	public static void insertDailyStockPrices(UpdatefForm FDD,String stockid,Connection connection,Connect con)
	{
		int fo_daily_stk_id=0;//sequence variable is intialized
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement pst=null;
		try 
		{
			String stockID=stockid;
			UpdatefForm FDD2= (UpdatefForm)FDD;
			Logging.debug("Insertion into F&O daily stk starts");
			stmt = Connect.con.createStatement();
			rs = stmt.executeQuery("SELECT NEXTVAL('fo_daily_stk_id')");//Execute query to obtain value of 'fo_daily_stk_id' sequence
			Logging.debug("rs for nextval id is "+rs);
			while(rs.next()){
				fo_daily_stk_id=rs.getInt(1);
			}
			pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_F&O_daily_stock"));
			Logging.debug("Insertion into Future_Options_Daily_Stock_Prices starts");
			/* Insertion of data into future_options_daily_stock_prices*/
			pst.setInt(1,fo_daily_stk_id);//Insertion of  sequence into Future_Options_Daily_Stock_Prices.
			Logging.debug("Insertion of sequence into F&O daily stk");
			/* conversion of Expr-date into format "dd-MM-yyyy"*/
			java.util.Date dDate = new java.util.Date(FDD2.getExpr_dt().trim());
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr.format(dDate).toString());
			String datecsv=fr.format(dDate).toString();
			String key_symbol=FDD2.getSymbol()+datecsv;//key_symbol=symbol+date(in "dd-MM-yyyy").
			pst.setString(2,key_symbol);
			Logging.debug("Insertion of key into F&O daily stk");
			pst.setInt(3,Integer.parseInt(stockID));
			Logging.debug("Insertion of stock_id into F&O daily stk");
			pst.setString(4,FDD2.getInstrument());
			Logging.debug("Insertion of instrument into F&O daily stk");
			pst.setString(5,FDD2.getSymbol());
			Logging.debug("Insertion  of symbol into F&O daily stk");
			pst.setString(6,datecsv);
			Logging.debug("Insertion of expr_dt into F&O daily stk");
			pst.setDouble(7,Double.parseDouble(FDD2.getStrike_pr()));
			Logging.debug("Insertion of strike_pr into F&O daily stk");
			pst.setString(8,FDD2.getOption_typ());
			Logging.debug("Insertion of option_typ into F&O daily stk");
			pst.setDouble(9,Double.parseDouble(FDD2.getOpen()));
			Logging.debug("Insertion of open  into F&O daily stk");
			pst.setDouble(10,Double.parseDouble(FDD2.getHigh()));
			Logging.debug("Insertion of high into F&O daily stk");
			pst.setDouble(11,Double.parseDouble(FDD2.getLow()));
			Logging.debug("Insertion of low into F&O daily stk");
			pst.setDouble(12,Double.parseDouble(FDD2.getClose()));
			Logging.debug("Insertion of close into F&O daily stk");
			pst.setDouble(13,Double.parseDouble(FDD2.getSettle_pr()));
			Logging.debug("Insertion of settle_pr into F&O daily stk");
			pst.setDouble(14,Double.parseDouble(FDD2.getContracts()));
			Logging.debug("Insertion of contracts into F&O daily stk");
			pst.setDouble(15,Double.parseDouble(FDD2.getVal_inlakh()));
			Logging.debug("Insertion of val_inlakh into F&O daily stk");
			pst.setDouble(16,Double.parseDouble(FDD2.getOpen_int()));
			Logging.debug("Insertion of open_int into F&O daily stk");
			pst.setDouble(17,Double.parseDouble(FDD2.getChange_in_ol()));
			Logging.debug("Insertion of change_in_ol into F&O daily stk");
			/* Conversion of timestamp date into "dd-MM-yyyy"*/
			java.util.Date dDate11 = new java.util.Date(FDD2.getTimestamp().trim());
			SimpleDateFormat fr1 = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr1.format(dDate11).toString());
			String datecsv1=fr1.format(dDate11).toString();
			pst.setString(18,datecsv1);
			Logging.debug("Insertion of timestamp  into F&O daily stk");
			pst.executeUpdate();
			Logging.debug("Insertion into F&O daily stk completes");
		}catch(Exception e){ Logging.error("Error : " +e.getMessage());}
		/*Close the preapredStatement,resultset and statement after inserting each record*/
		finally{
				try{
					if(pst!=null)
						pst.close();
					if(rs!=null)
						rs.close();
					if(stmt!=null)
						stmt.close();
					}catch(SQLException ex){
						Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());
					}
			}		
	}
	/**
	 * This method is used to store data of dbf file into future_options_daily_index_prices table.This
	 * method is called when index exists(means it is not new index)and stockid=null.It means that particular record
	 * is of index.
	 */
	public static void insertDailyStockPricesdb(UpdatefDbform FDDS,String stockid,Connection connection,Connect con)
	{
		int fo_daily_stk_id=0;//sequence variable is intialized
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement pst=null;
			
		try
		{
			String stockID=stockid;
			UpdatefDbform FDD2= (UpdatefDbform)FDDS;
			Logging.debug("Insertion into F&O daily stk starts");
			stmt = Connect.con.createStatement();
			rs = stmt.executeQuery("SELECT NEXTVAL('fo_daily_stk_id')");//Execute query to obtain value of 'fo_daily_stk_id' sequence
			Logging.debug("rs for nextval id is "+rs);
			while(rs.next()){
				fo_daily_stk_id=rs.getInt(1);
			}
			pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_F&O_daily_stock"));
			Logging.debug("Insertion into F&O daily stk starts");
			/* Insertion of data into future_options_daily_stock_prices*/
			pst.setInt(1,fo_daily_stk_id);
			Logging.debug("Insertion of sequence into F&O daily stk");
			/* conversion of Expr-date into format "dd-MM-yyyy"*/
			java.util.Date dDate1 = new java.util.Date(FDD2.getExpr_dtdbf().trim());
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr.format(dDate1).toString());
			String datedbf=fr.format(dDate1).toString();
			String key_symbol=FDD2.getSymboldbf().trim()+datedbf.trim();
				
			pst.setString(2,key_symbol);
			Logging.debug("Insertion of key into F&O daily stk");
			pst.setInt(3,Integer.parseInt(stockID));//Insertion of  sequence into Future_Options_Daily_Stock_Prices.
			Logging.debug("Insertion of stock_id into F&O daily stk");
			pst.setString(4,FDD2.getInstrumentdbf().trim());
			Logging.debug("Insertion of instrument into F&O daily stk");
			pst.setString(5,FDD2.getSymboldbf().trim());
			Logging.debug("Insertion  of symbol into F&O daily stk");
			pst.setString(6,datedbf);
			Logging.debug("Insertion of expr_dt into F&O daily stk");
			pst.setDouble(7,Double.parseDouble(FDD2.getStrike_prdbf()));
			Logging.debug("Insertion of strike_pr into F&O daily stk");
			pst.setString(8,FDD2.getOption_typdbf());
			Logging.debug("Insertion of option_typ into F&O daily stk");
			pst.setDouble(9,Double.parseDouble(FDD2.getOpendbf()));
			Logging.debug("Insertion of open  into F&O daily stk");
			pst.setDouble(10,Double.parseDouble(FDD2.getHighdbf()));
			Logging.debug("Insertion of high into F&O daily stk");
			pst.setDouble(11,Double.parseDouble(FDD2.getLowdbf()));
			Logging.debug("Insertion of low into F&O daily stk");
			pst.setDouble(12,Double.parseDouble(FDD2.getClosedbf()));
			Logging.debug("Insertion of close into F&O daily stk");
			pst.setDouble(13,Double.parseDouble(FDD2.getSettle_prdbf()));
			Logging.debug("Insertion of settle_pr into F&O daily stk");
			pst.setDouble(14,Double.parseDouble(FDD2.getContractsdbf()));
			Logging.debug("Insertion of contracts into F&O daily stk");
			pst.setDouble(15,Double.parseDouble(FDD2.getVal_inlakhdbf()));
			Logging.debug("Insertion of val_inlakh into F&O daily stk");
			pst.setDouble(16,Double.parseDouble(FDD2.getOpen_intdbf()));
			Logging.debug("Insertion of open_int into F&O daily stk");
			pst.setDouble(17,Double.parseDouble(FDD2.getChange_in_oldbf()));
			Logging.debug("Insertion of change_in_ol into F&O daily stk");
			/* Conversion of timestamp date into "dd-MM-yyyy"*/
			java.util.Date dDate12 = new java.util.Date(FDD2.getTimestampdbf().trim());
			SimpleDateFormat fr1 = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr1.format(dDate12).toString());
			String datedbf12=fr1.format(dDate12).toString();
			    
			pst.setString(18,datedbf12);
			Logging.debug("Insertion of timestamp  into F&O daily stk");
			pst.executeUpdate();
			Logging.debug("Insertion into F&O daily stk completes");
		}catch(Exception e){ Logging.error("Error : " +e.getMessage());}
		/*Close the preapredStatement,resultset and statement after inserting each record*/
		finally{
				try{
					if(pst!=null)
						pst.close();
					if(rs!=null)
						rs.close();
					if(stmt!=null)
						stmt.close();
					}catch(SQLException ee){Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ee.getMessage());}
						
			}
	}
	/**
	 * This method is used to obtain stockid for dbf file.For that,it uses identifier_code_value into it's
	 * query.
	 */
	 	
	public  static String getStockId_NSE_dbf(String stk_ex_id,String searchkeydbf,Connection connection,Connect con)
	{
		String stock_id_dbf=null;
		String searchkeysymdbf=searchkeydbf;
		PreparedStatement alertpstdb=null;
	 	ResultSet alertresultdb=null;
	 	
	 	try
		{
	 		alertpstdb = Connect.con.prepareStatement(ConnectInit.queries.getProperty("get_stockid_for_nsestock"));
	 		alertpstdb.setString(1,searchkeysymdbf);
	 		alertresultdb=alertpstdb.executeQuery();
	 		while(alertresultdb.next())
			{
	 			stock_id_dbf=alertresultdb.getString(1);
	 			Logging.debug("stockid in getStockId_NSE is "+stock_id_dbf);
			}	
		}catch(Exception e){Logging.error("Error : "+e.getMessage());}
	 	/*Close the resultset and preparedstatement*/
		finally{
					try{
							if(alertresultdb!=null)
								alertresultdb.close();
							if( alertpstdb!=null)
								alertpstdb.close();
						}catch(SQLException ee){Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ee.getMessage());}
				
			}	
		return stock_id_dbf;
	}
	/**
	 * This method is used to obtain stockid for csv file.For that,it uses identifier_code_value into it's
	 * query.
	 */
	public  static String getStockId_NSE(String stk_ex_id,String searchkey,Connection connection,Connect con)
	{
		String stock_id=null;
		String searchkeysym=searchkey;
		PreparedStatement alertpst=null;
		ResultSet alertresult=null;
	 	
	 	try
		{
	 		alertpst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("get_stockid_for_nsestock"));
	 		alertpst.setString(1,searchkeysym);
	 		alertresult=alertpst.executeQuery();
	 		while(alertresult.next())
			{
	 			stock_id=alertresult.getString(1);
	 			Logging.debug("stockid in getStockId_NSE is "+stock_id);
			}	
		}catch(Exception e)
		{
			Logging.error("Error : "+e.getMessage());
		}
	 	/*Close the resultset and preparedstatement*/
	 	finally{
	 			try{
	 				if(alertresult!=null)
	 					alertresult.close();
	 				if(alertpst!=null)
	 					alertpst.close();
	 			}catch(SQLException ex){
	 				Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());
	 			}
					
	 	}
	 		return stock_id;
	 	
	}
	/**
	 * This method is used to store data of csv file into future_options_stock_prices_new_issues table.The data which is
	 * inserted into that table,is of new stock(the stock which already does not exists).
	 */
	public static void  insertUnimportedStockPrices(UpdatefForm FDD,Connection connection,Connect con)
	{
		int fo_new_issue_stk_id=0;
		Statement stmt1=null;
		ResultSet rs1=null;
		PreparedStatement pst212=null;
	 	
		try 
		{
	 	  
			UpdatefForm FDD1= (UpdatefForm)FDD;
			stmt1 = Connect.con.createStatement();
			rs1 = stmt1.executeQuery("SELECT NEXTVAL('fo_new_issue_stk_id')");//Execute query to obtain value of 'fo_new_issue_stk_id' sequence.
			while(rs1.next())
			{
				fo_new_issue_stk_id=rs1.getInt(1);
			}
			pst212 = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_F&O_new_issues_stock"));
			Logging.debug("in query psmt is "+pst212);
			/* Insertion of data into future_options_stock_prices_new_issues*/
			pst212.setInt(1,fo_new_issue_stk_id);
			/*Conversion of Expr-date into "dd-MM-yyyy" format"*/
			java.util.Date dDate2 = new java.util.Date(FDD1.getExpr_dt().trim());
			SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr.format(dDate2).toString());
			String date_unimp_stk_prices=fr.format(dDate2).toString();
			String Key_unimp_stk_prices=FDD1.getSymbol()+date_unimp_stk_prices;
			pst212.setString(2,Key_unimp_stk_prices);
			pst212.setString(3,FDD1.getInstrument());
			pst212.setString(4,FDD1.getSymbol());
			pst212.setString(5,date_unimp_stk_prices);
			pst212.setDouble(6,Double.parseDouble(FDD.getStrike_pr()));
			pst212.setString(7,FDD1.getOption_typ());
			pst212.setDouble(8,Double.parseDouble(FDD1.getOpen()));
			pst212.setDouble(9,Double.parseDouble(FDD1.getHigh()));
			pst212.setDouble(10,Double.parseDouble(FDD1.getLow()));
			pst212.setDouble(11,Double.parseDouble(FDD1.getClose()));
			pst212.setDouble(12,Double.parseDouble(FDD1.getSettle_pr()));
			pst212.setDouble(13,Double.parseDouble(FDD1.getContracts()));
			pst212.setDouble(14,Double.parseDouble(FDD1.getVal_inlakh()));
			pst212.setDouble(15,Double.parseDouble(FDD1.getOpen_int()));
			pst212.setDouble(16,Double.parseDouble(FDD1.getChange_in_ol()));
			/* Conversion of timestamp date into"dd-MM-yyyy" format*/
			java.util.Date dDate21 = new java.util.Date(FDD1.getTimestamp().trim());
			SimpleDateFormat fr1 = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr1.format(dDate21).toString());
			String date_unimp_stk_prices_timestamp=fr1.format(dDate21).toString();
			pst212.setString(17,date_unimp_stk_prices_timestamp);
			pst212.executeUpdate();
	 	
		}catch(Exception e){Logging.error("Error : " +e.getMessage());}
	 	/*Close the resultset,preparedstatement and statement*/
	 	finally{
	 			try{
	 				if(pst212!=null)
	 					pst212.close();
	 				if(rs1!=null)
	 					rs1.close();
	 				if(stmt1!=null)
	 					stmt1.close();
	 				}catch(SQLException ex){
	 					Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());
	 				}
	 		}		
	 	
	}
	/**
	 * This method is used to store data of dbf file into future_options_stock_prices_new_issues table.The data which is
	 * inserted into that table,is of new stock(the stock which already does not exists).
	 */
	public static void  insertUnimportedStockPricesdb(UpdatefDbform FDDL,Connection connection,Connect con)
	{
		int fo_new_issue_stk_id=0;
		Statement stmt1=null;
		ResultSet rs1=null;
		PreparedStatement pst213=null;
	 	
	 	try 
		{
	 		
	 		UpdatefDbform FDD1= (UpdatefDbform)FDDL;
	 		stmt1 = Connect.con.createStatement();
	 		rs1 = stmt1.executeQuery("SELECT NEXTVAL('fo_new_issue_stk_id')");//Execute query to obtain value of 'fo_new_issue_stk_id' sequence.
	 		while(rs1.next())
	 		{
	 			fo_new_issue_stk_id=rs1.getInt(1);
	 		}
	 		pst213 = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_F&O_new_issues_stock"));
	 		Logging.debug("in query psmt is "+pst213);
	 		/*Insertion of data into future_options_stock_prices_new_issues*/
	 		pst213.setInt(1,fo_new_issue_stk_id);
	 		/*Conversion of Expr-date into "dd-MM-yyyy" format"*/
	 		java.util.Date dDate3 = new java.util.Date(FDD1.getExpr_dtdbf().trim());
	 		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
	 		Logging.debug("After Simpledate"+fr.format(dDate3).toString());
	 		String date_unimp_stk_prices_db=fr.format(dDate3).toString();
	 		String key_symbol_db=FDD1.getSymboldbf().trim()+date_unimp_stk_prices_db.trim();
	 		pst213.setString(2,key_symbol_db);
	 		pst213.setString(3,FDD1.getInstrumentdbf().trim());
	 		pst213.setString(4,FDD1.getSymboldbf().trim());
	 		pst213.setString(5,date_unimp_stk_prices_db);
	 		pst213.setDouble(6,Double.parseDouble(FDD1.getStrike_prdbf()));
	 		pst213.setString(7,FDD1.getOption_typdbf());
	 		pst213.setDouble(8,Double.parseDouble(FDD1.getOpendbf()));
	 		pst213.setDouble(9,Double.parseDouble(FDD1.getHighdbf()));
	 		pst213.setDouble(10,Double.parseDouble(FDD1.getLowdbf()));
	 		pst213.setDouble(11,Double.parseDouble(FDD1.getClosedbf()));
	 		pst213.setDouble(12,Double.parseDouble(FDD1.getSettle_prdbf()));
	 		pst213.setDouble(13,Double.parseDouble(FDD1.getContractsdbf()));
	 		pst213.setDouble(14,Double.parseDouble(FDD1.getVal_inlakhdbf()));
	 		pst213.setDouble(15,Double.parseDouble(FDD1.getOpen_intdbf()));
	 		pst213.setDouble(16,Double.parseDouble(FDD1.getChange_in_oldbf()));
	 		/*Conversion of timestamp date into "dd-MM-yyyy" format"*/
	 		java.util.Date dDate31 = new java.util.Date(FDD1.getTimestampdbf().trim());
	 		SimpleDateFormat fr1 = new SimpleDateFormat("dd-MM-yyyy");
	 		Logging.debug("After Simpledate"+fr1.format(dDate31).toString());
	 		String date_unimp_stk_prices_db_timestamp=fr1.format(dDate31).toString();
	 		pst213.setString(17,date_unimp_stk_prices_db_timestamp);
	 		pst213.executeUpdate();
	 	
		}catch(Exception e){ Logging.error("Error : " +e.getMessage());}
	 	/*Close the resultset,preparedstatement and statement*/
	 	finally{
	 			try{
	 				if(pst213!=null)
					pst213.close();
	 				if(rs1!=null)
	 					rs1.close();
	 				if(stmt1!=null)
	 					stmt1.close();
	 				}catch(SQLException ex){
	 					Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());
	 			}
				
	 	}	
	 	
	}
	/**
	 * This method is used to store data of csv file into future_options_daily_index_prices table.The data which is
	 * inserted into that table,is of  index(the index which already  exists).
	 */
	 public static void insertDailyIndexPrices(UpdatefForm FDD, String indexid,Connection connection,Connect con)
	 {
	 	int fo_daily_indx_id=0;
	 	Statement stmt=null;
	 	ResultSet rs=null;
	 	PreparedStatement pst2=null;
	 	
	 	try 
		{
	 		String indexID=indexid;
	 		UpdatefForm FDD3= (UpdatefForm)FDD;
	 		Logging.debug("Insertion into F&O daily index starts");
	 		stmt = Connect.con.createStatement();
	 		rs = stmt.executeQuery("SELECT NEXTVAL('fo_daily_indx_id')");//Execute query to obtain value of 'fo_daily_indx_id' sequence.
	 		Logging.debug("rs for nextval id is "+rs);
	 		while(rs.next())
	 		{
	 			fo_daily_indx_id=rs.getInt(1);
	 		}
	 		pst2 = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_F&O_daily_index"));
	 		Logging.debug("Insertion into F&O daily stk starts");
	 		/*Insertion of data into future_options_daily_index_prices*/
	 		pst2.setInt(1,fo_daily_indx_id);
	 		/*Conversion of Expr-date into "dd-MM-yyyy" format"*/
	 		java.util.Date dDate3 = new java.util.Date(FDD3.getExpr_dt().trim());
	 		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
	 		Logging.debug("After Simpledate"+fr.format(dDate3).toString());
	 		String date_unimp_indx_prices=fr.format(dDate3).toString();
	 		String key_symbol_indx_csv=FDD3.getSymbol()+date_unimp_indx_prices;
			Logging.debug("Insertion of sequence into F&O daily stk");
			pst2.setString(2,key_symbol_indx_csv);
			Logging.debug("Insertion of key into F&O daily stk");
			pst2.setInt(3,Integer.parseInt(indexID));
			Logging.debug("Insertion of stock_id into F&O daily stk");
			pst2.setString(4,FDD3.getInstrument());
			Logging.debug("Insertion of instrument into F&O daily stk");
			pst2.setString(5,FDD3.getSymbol());
			Logging.debug("Insertion  of symbol into F&O daily stk");
			pst2.setString(6,date_unimp_indx_prices);
			Logging.debug("Insertion of expr_dt into F&O daily stk");
			pst2.setDouble(7,Double.parseDouble(FDD3.getStrike_pr()));
			Logging.debug("Insertion of strike_pr into F&O daily stk");
			pst2.setString(8,FDD3.getOption_typ());
			Logging.debug("Insertion of option_typ into F&O daily stk");
			pst2.setDouble(9,Double.parseDouble(FDD3.getOpen()));
			Logging.debug("Insertion of open  into F&O daily stk");
			pst2.setDouble(10,Double.parseDouble(FDD3.getHigh()));
			Logging.debug("Insertion of high into F&O daily stk");
			pst2.setDouble(11,Double.parseDouble(FDD3.getLow()));
			Logging.debug("Insertion of low into F&O daily stk");
			pst2.setDouble(12,Double.parseDouble(FDD3.getClose()));
			Logging.debug("Insertion of close into F&O daily stk");
			pst2.setDouble(13,Double.parseDouble(FDD3.getSettle_pr()));
			Logging.debug("Insertion of settle_pr into F&O daily stk");
			pst2.setDouble(14,Double.parseDouble(FDD3.getContracts()));
			Logging.debug("Insertion of contracts into F&O daily stk");
			pst2.setDouble(15,Double.parseDouble(FDD3.getVal_inlakh()));
			Logging.debug("Insertion of val_inlakh into F&O daily stk");
			pst2.setDouble(16,Double.parseDouble(FDD3.getOpen_int()));
			Logging.debug("Insertion of open_int into F&O daily stk");
			pst2.setDouble(17,Double.parseDouble(FDD3.getChange_in_ol()));
			Logging.debug("Insertion of change_in_ol into F&O daily stk");
			/*Conversion of timestamp date into "dd-MM-yyyy" format"*/
			java.util.Date dDate32 = new java.util.Date(FDD3.getTimestamp().trim());
			SimpleDateFormat fr1 = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr1.format(dDate32).toString());
			String date_unimp_indx_prices_timestamp=fr1.format(dDate32).toString();
			pst2.setString(18,date_unimp_indx_prices_timestamp);
			Logging.debug("Insertion of timestamp  into F&O daily stk");
			pst2.executeUpdate();
			Logging.debug("Insertion into F&O daily stk completes");
		}catch(Exception e){ Logging.error("Error : " +e.getMessage());}
		/*Close the resultset,preparedstatement and statement*/
		finally{
				try{
					if(pst2!=null)
						pst2.close();
					if(rs!=null)
						rs.close();
					if(stmt!=null)
						stmt.close();
				}catch(SQLException ex){
					Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());
			}
				
		}	
	 	
	 	
	 }
	 /**
	  * This method is used to store data of dbf file into future_options_daily_index_prices table.The data which is
	  * inserted into that table,is of  index(the index which already  exists).
	  */
	 public static void insertDailyIndexPricesdb(UpdatefDbform FDDG, String indexid,Connection connection,Connect con)
	 {
	 	int fo_daily_indx_id=0;
	 	Statement stmt=null;
	 	ResultSet rs=null;
	 	PreparedStatement pst2=null;
	 	try
		{
	 		String indexID=indexid;
	 		UpdatefDbform FDD3= (UpdatefDbform)FDDG;
	 		Logging.debug("Insertion into F&O daily index starts");
	 		stmt = Connect.con.createStatement();
	 		rs = stmt.executeQuery("SELECT NEXTVAL('fo_daily_indx_id')");//Execute query to obtain value of 'fo_daily_indx_id' sequence.
	 		Logging.debug("rs for nextval id is "+rs);
	 		while(rs.next())
	 		{
	 			fo_daily_indx_id=rs.getInt(1);
	 		}
	 		pst2 = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_F&O_daily_index"));
	 		Logging.debug("Insertion into F&O daily stk starts");
	 		/*Insertion of data into future_options_daily_index_prices*/
	 		pst2.setInt(1,fo_daily_indx_id);
	 		Logging.debug("Insertion of sequence into F&O daily stk");
	 		/*Conversion of Expr-date into "dd-MM-yyyy" format"*/
	 		java.util.Date dDate4 = new java.util.Date(FDD3.getExpr_dtdbf().trim());
	 		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
	 		Logging.debug("After Simpledate"+fr.format(dDate4).toString());
	 		String date_daily_indx_prices_db=fr.format(dDate4).toString();
	 		String key_symbol_indx_db=FDD3.getSymboldbf().trim()+date_daily_indx_prices_db.trim();
			pst2.setString(2,key_symbol_indx_db);
			Logging.debug("Insertion of key into F&O daily stk");
			pst2.setInt(3,Integer.parseInt(indexID));
			Logging.debug("Insertion of stock_id into F&O daily stk");
			pst2.setString(4,FDD3.getInstrumentdbf().trim());
			Logging.debug("Insertion of instrument into F&O daily stk");
			pst2.setString(5,FDD3.getSymboldbf().trim());
			Logging.debug("Insertion  of symbol into F&O daily stk");
			pst2.setString(6,date_daily_indx_prices_db);
			Logging.debug("Insertion of expr_dt into F&O daily stk");
			pst2.setDouble(7,Double.parseDouble(FDD3.getStrike_prdbf()));
			Logging.debug("Insertion of strike_pr into F&O daily stk");
			pst2.setString(8,FDD3.getOption_typdbf());
			Logging.debug("Insertion of option_typ into F&O daily stk");
			pst2.setDouble(9,Double.parseDouble(FDD3.getOpendbf()));
			Logging.debug("Insertion of open  into F&O daily stk");
			pst2.setDouble(10,Double.parseDouble(FDD3.getHighdbf()));
			Logging.debug("Insertion of high into F&O daily stk");
			pst2.setDouble(11,Double.parseDouble(FDD3.getLowdbf()));
			Logging.debug("Insertion of low into F&O daily stk");
			pst2.setDouble(12,Double.parseDouble(FDD3.getClosedbf()));
			Logging.debug("Insertion of close into F&O daily stk");
			pst2.setDouble(13,Double.parseDouble(FDD3.getSettle_prdbf()));
			Logging.debug("Insertion of settle_pr into F&O daily stk");
			pst2.setDouble(14,Double.parseDouble(FDD3.getContractsdbf()));
			Logging.debug("Insertion of contracts into F&O daily stk");
			pst2.setDouble(15,Double.parseDouble(FDD3.getVal_inlakhdbf()));
			Logging.debug("Insertion of val_inlakh into F&O daily stk");
			pst2.setDouble(16,Double.parseDouble(FDD3.getOpen_intdbf()));
			Logging.debug("Insertion of open_int into F&O daily stk");
			pst2.setDouble(17,Double.parseDouble(FDD3.getChange_in_oldbf()));
			Logging.debug("Insertion of change_in_ol into F&O daily stk");
			/*Conversion of timestamp date into "dd-MM-yyyy" format"*/
			java.util.Date dDate41 = new java.util.Date(FDD3.getTimestampdbf().trim());
			SimpleDateFormat fr1 = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr1.format(dDate41).toString());
			String date_daily_indx_prices_db_timestamp=fr1.format(dDate41).toString();
			pst2.setString(18,date_daily_indx_prices_db_timestamp);
			Logging.debug("Insertion of timestamp  into F&O daily stk");
			pst2.executeUpdate();
			Logging.debug("Insertion into F&O daily stk completes");
		}catch(Exception e){ Logging.error("Error : " +e.getMessage());}
		/*Close the resultset,preparedstatement and statement*/
		finally{
				try{
					if(pst2!=null)
						pst2.close();
					if(rs!=null)
						rs.close();
					if(stmt!=null)
						stmt.close();
				}catch(SQLException ex){
					Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());
			}
				
		}	
	 	
	 	
	 }
	 /**
	  * This method is used to store data of csv file into future_options_index_prices_new_issues table.The data which is
	  * inserted into that table,is of new  index(the index which already does not exists).
	  */
	 public static void insertUnimportedIndexPrices(UpdatefForm FDD,Connection connection,Connect con)
	 {
	 	int fo_new_issue_indx_id=0;
	 	Statement stmt1=null;
	 	ResultSet rs1=null;
	 	PreparedStatement pst204=null;
	 	try 
		{
	 		UpdatefForm FDD4= (UpdatefForm)FDD;
	 		stmt1 = Connect.con.createStatement();
	 		rs1 = stmt1.executeQuery("SELECT NEXTVAL('fo_new_issue_stk_id')");//Execute query to obtain value of 'fo_new_issue_indx_id' sequence.
	 		while(rs1.next())
	 		{
	 			fo_new_issue_indx_id=rs1.getInt(1);
	 		}
	 		pst204 = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_F&O_new_issues_index"));
	 		Logging.debug("in query psmt is "+pst204);
	 		/*Insertion of data into future_options_index_prices_new_issues*/
	 		pst204.setInt(1,fo_new_issue_indx_id);
	 		/*Conversion of Expr-date into "dd-MM-yyyy" format"*/
	 		java.util.Date dDate5 = new java.util.Date(FDD4.getExpr_dt().trim());
	 		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
	 		Logging.debug("After Simpledate"+fr.format(dDate5).toString());
	 		String date_unimp_indx_prices=fr.format(dDate5).toString();
	 		String key_symbol_indx=FDD4.getSymbol()+date_unimp_indx_prices;
			pst204.setString(2,key_symbol_indx);
			pst204.setString(3,FDD4.getInstrument());
			pst204.setString(4,FDD4.getSymbol());
			pst204.setString(5,date_unimp_indx_prices);
			pst204.setDouble(6,Double.parseDouble(FDD4.getStrike_pr()));
			pst204.setString(7,FDD4.getOption_typ());
			pst204.setDouble(8,Double.parseDouble(FDD4.getOpen()));
			pst204.setDouble(9,Double.parseDouble(FDD4.getHigh()));
			pst204.setDouble(10,Double.parseDouble(FDD4.getLow()));
			pst204.setDouble(11,Double.parseDouble(FDD4.getClose()));
			pst204.setDouble(12,Double.parseDouble(FDD4.getSettle_pr()));
			pst204.setDouble(13,Double.parseDouble(FDD4.getContracts()));
			pst204.setDouble(14,Double.parseDouble(FDD4.getVal_inlakh()));
			pst204.setDouble(15,Double.parseDouble(FDD4.getOpen_int()));
			pst204.setDouble(16,Double.parseDouble(FDD4.getChange_in_ol()));
			/*Conversion of timestamp date into "dd-MM-yyyy" format"*/
			java.util.Date dDate51 = new java.util.Date(FDD4.getTimestamp().trim());
			SimpleDateFormat fr1 = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr1.format(dDate51).toString());
			String date_unimp_indx_prices_timestamp=fr1.format(dDate51).toString();
			pst204.setString(17,date_unimp_indx_prices_timestamp);
			pst204.executeUpdate();
	 	
		}catch(Exception e){ Logging.error("Error : " +e.getMessage());}
	 	/*Close the resultset,preparedstatement and statement*/
	 	finally{
	 			try{
	 				if(pst204!=null)
	 					pst204.close();
	 				if(rs1!=null)
	 					rs1.close();
	 				if(stmt1!=null)
	 					stmt1.close();
	 			}catch(SQLException ex){
	 				Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());
	 		}
				
	 	}	
	 	
	 	
	 }
	 /**
	  * This method is used to store data of dbf file into future_options_index_prices_new_issues table.The data which is
	  * inserted into that table,is of new  index(the index which already does not exists).
	  */
	 public static void insertUnimportedIndexPricesdb(UpdatefDbform FDDM,Connection connection,Connect con)
	 {
	 	int fo_new_issue_indx_id=0;
	 	Statement stmt1=null;
	 	ResultSet rs1 =null;
	 	PreparedStatement pst205=null;
	 	try
		{
	 		
	 		UpdatefDbform FDD4= (UpdatefDbform)FDDM;
	 		stmt1 = Connect.con.createStatement();
	 		rs1 = stmt1.executeQuery("SELECT NEXTVAL('fo_new_issue_stk_id')");//Execute query to obtain value of 'fo_new_issue_indx_id' sequence.
	 		while(rs1.next())
	 		{
	 			fo_new_issue_indx_id=rs1.getInt(1);
	 		}
	 		pst205 = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_F&O_new_issues_index"));
	 		Logging.debug("in query psmt is "+pst205);
	 		/*Insertion of data into future_options_index_prices_new_issues*/
	 		pst205.setInt(1,fo_new_issue_indx_id);
	 		/*Conversion of Expr-date into "dd-MM-yyyy" format"*/
	 		java.util.Date dDate6 = new java.util.Date(FDD4.getExpr_dtdbf().trim());
	 		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
	 		Logging.debug("After Simpledate"+fr.format(dDate6).toString());
	 		String date_unimp_indx_prices_db=fr.format(dDate6).toString();
	 		String key_symbol_indx_db=FDD4.getSymboldbf().trim()+date_unimp_indx_prices_db.trim();
			pst205.setString(2,key_symbol_indx_db);
			pst205.setString(3,FDD4.getInstrumentdbf().trim());
			pst205.setString(4,FDD4.getSymboldbf().trim());
			pst205.setString(5,date_unimp_indx_prices_db);
			pst205.setDouble(6,Double.parseDouble(FDD4.getStrike_prdbf()));
			pst205.setString(7,FDD4.getOption_typdbf());
			pst205.setDouble(8,Double.parseDouble(FDD4.getOpendbf()));
			pst205.setDouble(9,Double.parseDouble(FDD4.getHighdbf()));
			pst205.setDouble(10,Double.parseDouble(FDD4.getLowdbf()));
			pst205.setDouble(11,Double.parseDouble(FDD4.getClosedbf()));
			pst205.setDouble(12,Double.parseDouble(FDD4.getSettle_prdbf()));
			pst205.setDouble(13,Double.parseDouble(FDD4.getContractsdbf()));
			pst205.setDouble(14,Double.parseDouble(FDD4.getVal_inlakhdbf()));
			pst205.setDouble(15,Double.parseDouble(FDD4.getOpen_intdbf()));
			pst205.setDouble(16,Double.parseDouble(FDD4.getChange_in_oldbf()));
			/*Conversion of timestamp date into "dd-MM-yyyy" format"*/
			java.util.Date dDate61 = new java.util.Date(FDD4.getTimestampdbf().trim());
			SimpleDateFormat fr1 = new SimpleDateFormat("dd-MM-yyyy");
			Logging.debug("After Simpledate"+fr1.format(dDate61).toString());
			String date_unimp_indx_prices_db_timestamp=fr1.format(dDate61).toString();
			pst205.setString(17,date_unimp_indx_prices_db_timestamp);
			pst205.executeUpdate();
	 	
		}catch(Exception e){ Logging.error("Error : " +e.getMessage());}
	 	/*Close the resultset,preparedstatement and statement*/
	 	finally{
	 			try{
	 				if(pst205!=null)
	 					pst205.close();
	 				if(rs1!=null)
	 					rs1.close();
	 				if(stmt1!=null)
	 					stmt1.close();
	 			}catch(SQLException ex){
	 				Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());
			 }
				
	 	}	
	 	
	 	
	 }
	 /**
	  * This method is used to obtain the id for csv file from future_options_instrument table.
	  * The id is used to recognize that,particular record is of new stock or new index,if both
	  * stockid and indexid is equal to null.
	  */
	public  static String  getStockIndexNumber(String instrmnt,Connection connection,Connect con)
	{
		 String intr=null;
		 PreparedStatement alertpstq =null;
		 ResultSet alertresultq=null;
		 String instrcsv=instrmnt;
	 	
		 try{
		 		Logging.debug("Before preparedstatement");
		 		alertpstq = Connect.con.prepareStatement(ConnectInit.queries.getProperty("get_id_from_future_options_instrument"));
		 		Logging.debug("After preparedstatement");
		 		alertpstq.setString(1,instrcsv);
		 		Logging.debug("After alertpstq");
		 		alertresultq=alertpstq.executeQuery();
		 		Logging.debug("After Resultset");
		 		while(alertresultq.next())
		 		{
		 			intr=alertresultq.getString(1);
		 			Logging.debug("id in get_id_in_future_options_instrument is "+intr);
		 		}
		 		Logging.debug("After while");
	 		}catch(Exception e){
	 			Logging.error("Error : "+e.getMessage());}
	 		/*Close the resultset,preparedstatement */
	 		finally{
			try{
					if(alertresultq!=null)
						alertresultq.close();
					if(alertpstq!=null)
						alertpstq.close();
				}catch(SQLException ex){
				Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());}
				
	 			}	
	 		return intr;
	}
	/**
	 * This method is used to obtain the id for dbf file from future_options_instrument table.
	 * The id is used to recognize that,particular record is of new stock or new index,if both
	 * stockid and indexid is equal to null.
	 */
	public  static String getStockIndexNumberdbf(String instrmntdbf,Connection connection,Connect con)
	{
		String intrdbf=null;
		PreparedStatement alertpstdbfq=null;
		ResultSet alertresultdbfq=null;
		String instrumentdb=instrmntdbf;
		
		try
		{
			alertpstdbfq = Connect.con.prepareStatement(ConnectInit.queries.getProperty("get_id_from_future_options_instrument"));
			alertpstdbfq.setString(1,instrumentdb);
			alertresultdbfq=alertpstdbfq.executeQuery();
			while(alertresultdbfq.next())
	 		{
				intrdbf=alertresultdbfq.getString(1);
				Logging.debug("id in get_id_in_future_options_instrument is "+intrdbf);
	 		}		
	 	}catch(Exception e){
			Logging.error("Error : "+e.getMessage());}
	 	/*Close the resultset,preparedstatement */
	 	finally{
	 			try{
	 					if(alertresultdbfq!=null)
	 						alertresultdbfq.close();
	 					if(alertpstdbfq!=null)
	 						alertpstdbfq.close();
	 				}catch(SQLException ex){
				Logging.error("Error : Unable to close ResultSet,PreparedStatement "+ex.getMessage());}
				
	 				}	
	 		return intrdbf;
	}
	
}
 