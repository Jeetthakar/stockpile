/*
 * Created on Jun 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.compute;

import harrier.income.com.entities.CFormula;
import harrier.income.com.fixedincome.FixedIncomeComputeIndexForm;
import harrier.income.com.fixedincome.QueryClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;
import app.IncomeLibrary;
import app.IndexCalculatorCollection;

import com.harrier.initializeation.ConnectInit;
/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FixedIncomeCIndexCalculator {
	Logger Logging = Logger.getLogger(FixedIncomeCIndexCalculator.class);
	private int type_of_index;
//	 private Properties p_queries, prop;
	 double[] fiftytwo_min_max=new double[2]; 
	//    private CFormula cFor = new CFormula();
	    
	    public String check_history="no";
	     boolean histforlast=false;
	     private double ltp = 0, iwf = 0, mcv = 0, mcv1 = 0, exch = 1, tmcv = 0, divisor = 0,
         base_value = 0, indexVal = 0, flag = 0, fto_exch = 1,
         icomp_mcv = 0, icomp_iwf = 0;
	     private PreparedStatement pst_preStat, pst, pst3,pst4,pst5,pst6,pst7;

	     private Statement stm;

	     private ResultSet rst, rst1, rst2, rst3,rst4,rs5,rs6;

	     private static app.Connect con_connect;

 private Vector v = new Vector();

 private long ml = 0, tis = 0, stkid = 0, curridStk = 0, curridIndex = 0;

 private long stkId = 0, currId = 0;
	    
	    
    public String computeIndex(String indexID, String settlement, String close,
            String toComputechildindexes, String ListOfChildIndices,
            HttpServletRequest req, String BaseDate,Connection connection) {
        Logging.debug("this : "+this);
   //    AdjustDecimal ad=new AdjustDecimal(); 
        AdjustDecimal ad = ConnectInit.getAdjustDecimal();
        ArrayList datelistbeforebasedate=new ArrayList();
       String returnString = null;
       ArrayList arrayList=null;
       Connect c =ConnectInit.getConnect();
        if (BaseDate == null) {
            BaseDate = QueryClass.formatDate();
        }
        int diff2=FixedIncomeComputeIndexForm.CompareDate(BaseDate,QueryClass.formatDate());
        Logging.debug("indexID is "+indexID +" before base date size of datelistbeforebasedate is : "+datelistbeforebasedate.size()); 
        if(diff2==0){
            arrayList=new ArrayList();
            arrayList.add(BaseDate);
        }else{        
	        IncomeLibrary incomeLibrary = new IncomeLibrary();
	        arrayList= incomeLibrary.getListOfDates(BaseDate,indexID);

	        datelistbeforebasedate=getDateListBeforeBaseDate(BaseDate,indexID);
        }
        Logging.debug(indexID +" before base date size of datelistbeforebasedate is : "+datelistbeforebasedate.size()); 
        Logging.debug(indexID +"arrayList.size() : "+arrayList.size());        
        try {

            long l_indexID = Long.parseLong(indexID);
            PreparedStatement pst_check;
            //remove query
            pst_check = connection.prepareStatement(ConnectInit.queries.getProperty("get_index_type"));
           
            /*
             * pst_check = Connect.con .prepareStatement("select index_type_id
             * from index_master where index_id=?")
             */;
            pst_check.setLong(1, l_indexID);
            ResultSet rs_check = pst_check.executeQuery();
            rs_check.next();
            int index_type = rs_check.getInt("index_type_id");
            type_of_index = index_type;
            Logging.debug("index type for calculation is : " + index_type);
            
         /*   if (index_type == 4) {
            	} else if (index_type == 5) {
            		} else {*/
            
            // Clean price index
            if(index_type == 7){
               
               boolean temp = false;
                Logging.debug("before base date arraylist size is "+datelistbeforebasedate.size());
                if(datelistbeforebasedate.size()!=0){
                	Logging.debug("before base date for loop");
                	 for (int k=0;k<datelistbeforebasedate.size();k++) {
                        String element = (String) datelistbeforebasedate.get(k);
                        Logging.debug("element : : " + (element));
                        Logging.debug(connection+"Before111 computing parent index with index value : ");
                        boolean cpricestatus=false;  
                       
                        cpricestatus=checkPriceAvailable(element,indexID,connection);
                        if(cpricestatus==true){
	                        if ((String) datelistbeforebasedate.get(k)!=null) {
	                         // do later   returnString = computeIndexNormallyBeforeBaseDate(indexID, "n", "y",element, temp,connection);
	                            
	                        } else {
	                        	
	                        		temp = true;
	                        	//do later	returnString = computeIndexNormallyBeforeBaseDate(indexID,settlement, close, element, temp,connection);
	                        	
	                        }
                        }
                    }
                }
                Logging.debug("after calculating index value before base date");
                Logging.debug("arrayList.size() : "+arrayList.size());
                Iterator iter = arrayList.iterator();
                int count=0;
                if(diff2==-1){
                	histforlast=true;
                }
                while (iter.hasNext()) {
                	count++;
                    String element = (String) iter.next();
                    Logging.debug("element : : " + (element));
                    //Logging.debug("Before111 computing parent index with index value : "+count);
                    boolean cpricestatus=false;                    
                    cpricestatus=checkPriceAvailable(element,indexID,connection);
                    if(cpricestatus==true || diff2==0){
                    if (iter.hasNext()) {
           
                    	check_history="yes";
                        returnString = computeIndexNormally(indexID, "n", "y",
                                element, temp,connection);
                    } else {
                    	if(histforlast)
                    	{
                    		returnString = computeIndexNormally(indexID, "n", "y", element, temp,connection);
                    	}else{
                        temp = true;
                        returnString = computeIndexNormally(indexID,settlement, close, element, temp,connection);
                    	}
                       }
                    }
                }

                Logging.debug(connection+"After computing parent index with index value : "
                                + returnString);
                //remove comment
                if (req!=null && req.getAttribute("tmcverror") != null)
                    req.removeAttribute("tmcverror");
                if (req!=null && returnString.equals("----")) {
                    req.setAttribute("tmcverror", "yes");
                }

                Logging.debug("After computing parent index with index value : "
                                + returnString);
                //do later
              /*  iter = arrayList.iterator();
                temp = false;
                while (iter.hasNext()) {
                    String element = (String) iter.next();
                    Logging.getDebug("element : : " + (element));
                    //Logging.debug("Before111 computing parent index with index value : ");
                    boolean cpricestatus=false;                    
                    cpricestatus=checkPriceAvailable(element,indexID,connection);
                                        
                    
                    if(cpricestatus==true || diff2==0){
                    if (iter.hasNext()) {
                      //do later//  ComputeChildIndexes(indexID, "n", "y",toComputechildindexes, ListOfChildIndices, element,temp,connection);
                    } else {
                        temp = true;
                      //do later/  ComputeChildIndexes(indexID, settlement, close,toComputechildindexes, ListOfChildIndices, element,temp,connection);
                    }
                    }
                }*/ 
                Logging.debug("this again  : "+this);
                returnString=ad.shareholdingpatt(returnString);
                Logging.debug("index value after calculation "+returnString);
                
               // return returnString;
           }

        } catch (Exception e) {
        	Logging.debug(" Error : "+e.getMessage());
            return "---";
        }
        return returnString;
    }
	
    public ArrayList getDateListBeforeBaseDate(String bdate,String indid){
    	ArrayList arr=new ArrayList();
    	String actual_bdate=null;
		actual_bdate=FixedIncomeComputeIndexForm.getBaseDate(indid);
		Logging.debug("actual_bdate is "+actual_bdate);
		int i=0;
		int diff=FixedIncomeComputeIndexForm.CompareDate(actual_bdate,bdate);
		while(diff!=0 && diff>0){
			 actual_bdate=getPreviousDate(actual_bdate);
			 Logging.debug("actual_bdate is "+actual_bdate);
			 arr.add(i,actual_bdate);
				i++;
			diff=FixedIncomeComputeIndexForm.CompareDate(actual_bdate,bdate);
			Logging.debug("diff is "+diff);
			
		}
		return arr;
    }
    public static  String getPreviousDate(String dt) {

		int date;
		int month;
		int year;
		int maxdate;
		int maxmonth;
		int maxyear;
		int i = 0;
		String sdate[] = new String[3];
		//String dt="01-07-2005";
		dt = dt.replace('/','-');
		dt = dt.trim();

		StringTokenizer st = new StringTokenizer(dt,"-");

		while (st.hasMoreTokens()) {

	   		sdate[i] = st.nextToken();
	   		i++;
	     }

	     date = Integer.parseInt(sdate[0]);
	     month = Integer.parseInt(sdate[1]);
	     year = Integer.parseInt(sdate[2]);

	     GregorianCalendar gc = new GregorianCalendar(year, (month-1), date);

	     maxdate = gc.getActualMaximum(gc.DATE);
	     maxmonth = gc.getActualMaximum(gc.MONTH);//returns maximum 12

		if((date > maxdate)||(date <= 0))
			throw new NumberFormatException("invalid date");

		if((month > 12) || (month <= 0))
			throw new NumberFormatException("invalid month");

		if(date > 1) {
			date = date - 1;
		}

		else if((date == 1)&&(month > 1)) {
			month--;
			gc = new GregorianCalendar(year, (month-1), date);
			date = gc.getActualMaximum(gc.DATE);
		}
		else if((date == 1)&&(month == 1)) {
			month = 12;
			year = year - 1;
			gc = new GregorianCalendar(year, (month - 1), date);
			date = gc.getActualMaximum(gc.DATE);
		}
		String dd=new Integer(date).toString();
		String mm=new Integer(month).toString();
		if(dd.length()<2)
			dd="0"+date;
		if(mm.length()<2)
			mm="0"+month;
		String finaldate = dd+"-"+mm+"-"+year;
		
		return finaldate;

	}
	
    /**
	 * to check if prices available for date or not.
	 * return true if prices are available or false if prices are not available on date.
	 * @param hist_Date
	 * @param indid
	 * @return
	 */
	public boolean checkPriceAvailable(String hist_Date,String indid,Connection connection){
		boolean flag=false;
		Connect connect = ConnectInit.getConnect();
		Logging.debug("in checkPriceAvailable");
		Logging.debug("in hist_Date "+hist_Date+"  indid is  "+indid);
		PreparedStatement pst=null;
		ResultSet rst=null;
		try
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("check_for_price_on_date"));
			pst.setString(1,hist_Date);
			pst.setString(2,indid);
			rst = pst.executeQuery();
			while(rst.next())
			{
				flag=true;
				break;
			}			
		}catch(Exception e){
			Logging.error("Error : "+e.getMessage());
		}
		finally{
			try{
				if(rst!=null)
					rst.close();
				if(pst!=null)
					pst.close();
			}catch(Exception ee){
				Logging.error(" Error : "+ee.getMessage());
			}
		}
		Logging.debug("flag is "+flag);		
		return flag;
	}


    //query to get values required in index computation
    public String computeIndexNormally(String indexID, String settlement,
            String close, String date, boolean updateIndexcompose,Connection connection) {
        Logging.debug("Computing Index for Index Id : " + indexID
                + "  with settlement value = " + settlement);
        CFormula cFor = ConnectInit.getCFormula();
        String time = getTime();
        Connect c=ConnectInit.getConnect();
  //      AdjustDecimal ad=new AdjustDecimal();
        AdjustDecimal ad = ConnectInit.getAdjustDecimal();
        long l_indexID = Long.parseLong(indexID);
        double high, low;
        String date1 = "";
        boolean firstDailyValue = false;
        long id, baseCurrencyIdForCurrencyTypeIndex = 66;
        long childIndexId = 0;
        tmcv = 0.0;
        v.clear();
        Logging.debug("initial tmcv for  Id : " + indexID + " is : " + tmcv);
        try {
        	 //doing normal calculation
        	Hashtable currWiseExcRate=getCsExcForScripCompose(connection,type_of_index,l_indexID);
            //doing normal calculation
            if (!firstDailyValue) {              
                pst_preStat = connection.prepareStatement(ConnectInit.queries.getProperty("fixed_income_compute_index"));
                pst_preStat.setLong(1, l_indexID);
                pst_preStat.setString(2, date);
                rst = pst_preStat.executeQuery();//executes query to get data
                // for
                // index computation
                //get all ltp values and stock id for the given date
                while (rst.next()) {
                    Logging.debug("Row no." + rst.getRow());
                    ltp = rst.getDouble("ltp");//get ltp
                    //ltp =(double)Double.parseDouble(ad.twodigitdeci(rst.getDouble("ltp")));//get ltp
                    iwf = rst.getDouble("iwf");//get iwf
                    ml = rst.getLong("market_lot");//get market lot
                    tis = (long) rst.getDouble("tis");//get tis
                   
                    stkid = rst.getLong("stock_id");//get stock id
                   
                    curridStk = rst.getLong("stock_currency_id");//get
                    // currencyId
                    // for stock
                    if (type_of_index == 5) {
                        curridIndex = baseCurrencyIdForCurrencyTypeIndex;
                    } else {
                        curridIndex = rst.getLong("base_currency_id");
                    }
                    date1 = rst.getString("price_date");
                    Logging.debug("Index curr id" + curridIndex);
                    Logging.debug("Stock curr id" + curridStk);
                    //added by sunil 12-JUN-2006.
                    String exch_rate="0.0";
                    if(currWiseExcRate.containsKey(new Long(curridStk).toString())){
                    	exch_rate=(String)currWiseExcRate.get(new Long(curridStk).toString());
                    }
                    exch_rate=ad.indexcompose4digit(exch_rate);
                    exch=(double)Double.parseDouble(exch_rate);
                    Logging.debug("Finally Exchange rate is : " + exch);
                    Logging.debug(" " + tis);
                    base_value = rst.getDouble("base_value");//get base value
                    // for
                    // index
                    if(type_of_index==7) {
                    	
                    	if(check_history.equals("yes")){
	                   		 //mcv = cFor.calMarketCap(ltp, ml, exch, tis, iwf);//calculates
	                   		
	                   		 //Logging.debug("stock id  "+stkid);
	                   		 //Logging.debug("date :  "+date);
                    		try{
                    		
	                   		 pst7 =  connection
	                            .prepareStatement(ConnectInit.queries.getProperty("select_mcv_from_fixed_income_stock_master"));
	                   		
	                   		  pst7.setLong(1, stkid);
	                   		  pst7.setString(2, date);
	                   		  rs6 =  pst7.executeQuery();
	                   		 while(rs6.next())
	                   		 {
	                   			 mcv = Double.parseDouble(rs6.getString(1));
	                   		 }
	        
                    		}catch(Exception e){
                    			
                    		}
	                   		 //Logging.debug("Check Value After NO function "+check_history+mcv);
	                   	 }else{
	                   		mcv = cFor.calMarketCap(ltp, ml, exch, tis, 1.0);//calculates
	                   		
	                   	 }                  
                    	//mcv = cFor.calMarketCap(ltp, ml, exch, tis, 1.0);//calculates
                    }else{
                    	mcv = cFor.calMarketCap(ltp, ml, exch, tis, iwf);//calculates
                    	
                    }
                    
               
                    v.addElement((new Double(mcv)));//Change made here******
                    
                    //update mcv in index composition table
                    icomp_mcv = mcv;
                    try {
                        if (updateIndexcompose) {
                            //   put mcv into database
                            //    Logging.getDebug("Inside try");
                            Logging.debug("mcv "+icomp_mcv);
                            Logging.debug("index id "+l_indexID);
                            Logging.debug("stock id "+stkid);
                            pst3 = connection
                                    .prepareStatement(ConnectInit.queries.getProperty("fixed_income_update_index_compose_mcv"));
                          
                            Logging.debug("icom mcv "+icomp_mcv);
                            pst3.setDouble(1, icomp_mcv);
                            pst3.setLong(2, l_indexID);

                            pst3.setDouble(3, stkid);

                            pst3.executeUpdate();//execute query
                           
                        }
                    } catch (SQLException e) {
                        Logging.debug(e.getMessage());
                        Logging.debug("index values" + e);
                    }
                }
                
                tmcv = cFor.totalMarketCap(v);//calculate tmcv
                
         
                if (tmcv == 0) {
                    return "----";
                }
                try {
                    pst_preStat = connection.prepareStatement(ConnectInit.queries.getProperty("fixed_income_daily_index_values"));
                    pst_preStat.setLong(1, l_indexID);//set ? for index id
                 
                    rst1 = pst_preStat.executeQuery();//execute query

                    if (!rst1.next())//(rst1.getRow() == 0)//check
                    {
                    	divisor=1;
                    	indexVal=base_value;
                       // divisor = cFor.divisor(tmcv, base_value);
                        Logging.debug("divisor value computed " + divisor);

                    } else {
                    	divisor=1;
                       // divisor = rst1.getDouble(1);
                    	double oldindexvalue=rst1.getDouble(1);  //previous index value
                    	double oldtmcv=rst1.getDouble(2);
                    	indexVal = cFor.fixedincomeindex(oldindexvalue,oldtmcv,tmcv); //clean price index
                    	Logging.debug("index value"+indexVal);
                        Logging.debug(" Divisor" + divisor);
                        Logging.debug("divisor taken " + divisor);
                    }
                } catch (SQLException e) {
                    Logging.debug(e.getMessage());
                    Logging.debug("index values" + e);
                }
                
                
                
                
                
            //////      indexVal = cFor.index(tmcv, divisor);// index compute
                Logging.debug("value calculated = " + indexVal);

            }
            //  normal calculation ended

            //inserts value in intra day indices
            try {
                pst_preStat = connection.prepareStatement(ConnectInit.queries.getProperty("insert_into_intra_day_indices"));

            
                if (type_of_index == 4) {
                    pst_preStat.setLong(4, childIndexId);

                } else {
                    pst_preStat.setLong(4, l_indexID);

                }
                pst_preStat.setDouble(1, indexVal);
                pst_preStat.setDouble(5, tmcv);
           
                pst_preStat.setString(2, date);

                pst_preStat.setString(3, time);

                pst_preStat.executeUpdate();//execute query

             } catch (SQLException e) {
                Logging.error("ERROR");
                Logging.error("index values" + e);
            }

            // select index_lowest_value,index_highest_value from index value
            // daily

            try {
                pst_preStat = connection
                        .prepareStatement(ConnectInit.queries.getProperty("get_high_low_index_value"));
                if (type_of_index == 5) {
                    pst_preStat.setLong(1, childIndexId);
                } else {
                    pst_preStat.setLong(1, l_indexID);//set ? for index id
                }

                pst_preStat.setString(2, date);
                Logging
                        .debug("pst_preStat to find get_high_low_index_value : "
                                + pst_preStat);
                rst2 = pst_preStat.executeQuery();//execute query
            } catch (Exception e) {
                Logging.error("ERROR");
                Logging.error("index values" + e);
            }
            //if no enteries
            rst2.next();

            if (rst2.getRow() == 0) {
                try {
                	Vector avgPe_pb_dividend=getAvgPe_Pb_dividend(indexID,date);
                	Logging.debug("avgPe_pb_dividend size is "+avgPe_pb_dividend.size());
                	
                	//code for 52 week low and high from 
                	   //  do later           	
               	fiftytwo_min_max =getFiftytwo_Week_HighLow(indexID);
                	if(indexVal > fiftytwo_min_max[0]){
                		fiftytwo_min_max[0]=indexVal;
                	}
                	if(indexVal < fiftytwo_min_max[1]){
                		fiftytwo_min_max[1]=indexVal;
                	} 
                	// code for 52 week low and high to
                	/*pst_preStat = connection
                            .prepareStatement(p_queries
                                    .getProperty("insert_into_index_value_daily1"));*/
                	pst_preStat = connection
                    .prepareStatement(ConnectInit.queries.getProperty("index_compute_insert_into_index_value_daily_pe_pb"));
                	// pst_preStat.setLong(1, id);
                  
                    //	pst_preStat.setLong(6, l_indexID);
                   // 
                    pst_preStat.setDouble(1, indexVal);
                    pst_preStat.setDouble(2, indexVal);
                    pst_preStat.setDouble(3, indexVal);
                    pst_preStat.setDouble(4, indexVal);
                    
                    if (type_of_index == 5) {
                        pst_preStat.setLong(5, childIndexId);

                    } else {
                        pst_preStat.setLong(5, l_indexID);

                    }
                    pst_preStat.setString(6, date);
                    pst_preStat.setDouble(7, divisor);
                    //        pst_preStat.setString(11, settlement);
                    pst_preStat.setDouble(8, tmcv);
                    pst_preStat.setDouble(9, tmcv);
                    pst_preStat.setDouble(10, divisor);
                    if(avgPe_pb_dividend.size()==3){
	                    pst_preStat.setString(11, ((String)avgPe_pb_dividend.get(0)));
	                    pst_preStat.setString(12, ((String)avgPe_pb_dividend.get(1)));
	                    pst_preStat.setString(13, ((String)avgPe_pb_dividend.get(2)));
                    }else{
                    	pst_preStat.setString(11, "0.0");
                        pst_preStat.setString(12, "0.0");
                        pst_preStat.setString(13, "0.0");
                    }
                    pst_preStat.setDouble(14, fiftytwo_min_max[0]);
                    pst_preStat.setDouble(15, fiftytwo_min_max[1]);
                    pst_preStat.executeUpdate();//execute query
                    Logging
                            .debug("initially insert into  index value daily : "
                                    + pst_preStat
                                    + "\nand closing value is "
                                    + close);
                   

                    insertclosing_settlement(indexVal, null, settlement, close,date, l_indexID,connection);

                  
                } catch (SQLException e) {
                    Logging.error("ERROR");
                    Logging.error("index values" + e);
                }
            } else {
                Logging.debug("privious settlement value is settlement = "
                        + rst2.getString("is_settlement_value"));
                if (rst2.getString("is_settlement_value") == null) {
                    insertclosing_settlement(indexVal, null, settlement, close,
                            date, l_indexID,connection);
                } else {
                    insertclosing_settlement(indexVal, rst2.getString("is_settlement_value"), settlement,
                            close, date, l_indexID,connection);

                }
                low = rst2.getDouble("index_lowest_value");
                high = rst2.getDouble("index_highest_value");
                if (indexVal > high) {
                    //update_high_index_value

                    pst_preStat = connection
                            .prepareStatement(ConnectInit.queries.getProperty("update_high_index_value"));
                    pst_preStat.setDouble(1, indexVal);
                    if (type_of_index == 5) {
                        pst_preStat.setLong(2, childIndexId);

                    } else {
                        pst_preStat.setLong(2, l_indexID);

                    }
                    //	pst_preStat.setLong(2, l_indexID);
                    pst_preStat.setString(3, date);
                    pst_preStat.executeUpdate();
                }
                if (indexVal < low) {
                    //update_low_index_value

                    pst_preStat = connection
                            .prepareStatement(ConnectInit.queries.getProperty("update_low_index_value"));
                    pst_preStat.setDouble(1, indexVal);
                    if (type_of_index == 5) {
                        pst_preStat.setLong(2, childIndexId);

                    } else {
                        pst_preStat.setLong(2, l_indexID);

                    }
                    //		pst_preStat.setLong(2, l_indexID);
                    pst_preStat.setString(3, date);
                    pst_preStat.executeUpdate();
                }
            }

        } catch (SQLException e) {
            Logging.error(" Error : "+e.getMessage());
        }
        //       if (close == "yes") {
        if (close.trim().equals("yes")) {
            //update information_schema.index_value_daily set

            Logging.debug("in closing part");
            try {
                pst_preStat = connection
                        .prepareStatement(ConnectInit.queries.getProperty("update_index_value_daily_set_closing_value"));
                pst_preStat.setDouble(1, indexVal);
                pst_preStat.setDouble(2, tmcv);
                if (type_of_index == 5) {
                    pst_preStat.setLong(3, childIndexId);

                } else {
                    pst_preStat.setLong(3, l_indexID);

                }
                //		pst_preStat.setLong(3, l_indexID);
                pst_preStat.setString(4, date);
                pst_preStat.executeUpdate();

            } catch (SQLException e) {
                Logging.error(e.getMessage());
            }
        }
        String str = Double.toString(indexVal);
        Logging.debug("Index Value Calculated for Index id : " + indexID
                + "  is : " + str);
        if (tmcv == 0) {
            return "----";
        }
        return str;
    }
    public String getTime() {
        java.util.Date dt = new java.util.Date();
        dt.getDate();
        return dt.toString().split(" ")[3];
    }
    /**
	 * METHOD RETURNS COLLECTION OF DISTINCT CURRENCY_MAST_ID ALONG WITH CURRENCY EXCHANGE RATE FOR
	 * COMPOSITION SCRIPS OF AN INDEX PASSED TO IT. 
	 * @param connection
	 * @param type_of_index
	 * @param index_id
	 * @return
	 */
	public Hashtable getCsExcForScripCompose(Connection connection,int type_of_index,long index_id){
		Hashtable scripCurrList=new Hashtable();
		Connect connect=ConnectInit.getConnect();
		PreparedStatement pst=null;
		ResultSet rst=null;
		Logging.debug("Finally Exchange rate is : " + exch);
        try{
        	pst = connection.prepareStatement(ConnectInit.queries.getProperty("fixed_income_get_distinct_currency_id_for_composition_scrips"));
			pst.setLong(1,index_id);
			rst = pst.executeQuery();
			while(rst.next()){
				String string2="0";
				  /*if (type_of_index == 5) {
				  	string2 = rst.getString(3);
                } else {*/
                	string2 = rst.getString(2);
               // }
	        	String string=rst.getString(1);
	        	String temp=IndexCalculatorCollection.getIndexCurrancyExchRate(string,string2,connection);
	        	if(temp!=null){
	        		exch=new Double(temp).doubleValue();
	        	}else{
	        		temp=IndexCalculatorCollection.getIndexCurrancyExchRate(string2,string,connection);
	        		if(temp==null){
	        			exch=1.0;
	        		}else{
	        			exch=1/new Double(temp).doubleValue();
	        		}
	        	}
	        	scripCurrList.put(string,(new Double(exch).toString()));
			}       
			if(rst!=null)
				rst.close();
			if(pst!=null)
				pst.close();
        }catch (Exception e) {
        	Logging.error("Error : "+e.getMessage());
        	exch=1.0;
        }
        finally{
			try{
				if(rst!=null)
					rst.close();
				if(pst!=null)
					pst.close();
			}catch (Exception ex) {
	        	Logging.error("Error : unable to close resultset,preparedStatement "+ex.getMessage());        	
			}
		}		
        Logging.debug("Finally Exchange rate is : " + exch);
		return scripCurrList;
	}
	
	public Vector getAvgPe_Pb_dividend(String indid,String date){
		
		Vector v1=new Vector();
		Connect connect = ConnectInit.getConnect();
		Logging.debug("in getAvgPe_Pb_dividend");
		Logging.debug("  indid is  "+indid);
		Connection con = null;
		PreparedStatement pst;
		ResultSet rst;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(con == null)
		{
			con = connect.getdbConnection();
		}
		try
		{
			pst = con.prepareStatement(ConnectInit.queries.getProperty("index_compute_pe_pb_dividend_index_wise"));
			pst.setString(1,indid);
			pst.setString(2,date);
			pst.setString(3,indid);
			pst.setString(4,date);
			pst.setString(5,indid);
			pst.setString(6,date);
			rst = pst.executeQuery();
			while(rst.next())
			{
				v1.add(0,rst.getString(1));
				v1.add(1,rst.getString(2));
				v1.add(2,rst.getString(3));
				Logging.debug("inside while loop  pe is "+rst.getString(1)+" pb is "+rst.getString(2)+" dividend is "+rst.getString(3));
			}
		}catch(Exception e){
			Logging.error("Error : "+e.getMessage());
		}
//		Close the Dynamic Connection : Manoj Adekar
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
		Logging.debug("v1 size is "+v1.size());		
		return v1;
	}
    
    
	public double[] getFiftytwo_Week_HighLow(String indid){
		double[] v_ftw=new double[2];
		Connect connect = ConnectInit.getConnect();
		Logging.debug("in getFiftytwo_Week_HighLow");
		Logging.debug("  indid is  "+indid);
		Connection con = null;
		PreparedStatement pst;
		ResultSet rst;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
		if(con == null)
		{
			con = connect.getdbConnection();
		}
		try
		{
			pst = con.prepareStatement(ConnectInit.queries.getProperty("get_Fiftytwo_Week_HighLow"));
			pst.setString(1,indid);
			
			rst = pst.executeQuery();
			while(rst.next())
			{
				v_ftw[0]=rst.getDouble(1);
				v_ftw[1]=rst.getDouble(2);				
				Logging.debug("max(index closing value) is "+v_ftw[0]+" min(index closing value) is "+v_ftw[1]);
			}
		}catch(Exception e){
			Logging.error("Error : "+e.getMessage());
		}
//		Close the Dynamic Connection : Manoj Adekar
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
		Logging.debug("v1 size is "+v_ftw.length);		
		return v_ftw;
	}

	 public void insertclosing_settlement(double indexVal,String is_settlement_val, String settlement, String close,String date, long l_indexID,Connection connection) {
        try {
            //    Logging.getDebug("privious settlement value is settlement = "
            //           + rst2.getString("is_settlement_value")+" and settlement variable
            // : "+settlement);
        	
        	Connect c =ConnectInit.getConnect();

            if (is_settlement_val == null
                    || is_settlement_val.trim().equals("")) {

                if (settlement.trim().equals("y")) {
                    pst_preStat = connection
                            .prepareStatement(ConnectInit.queries.getProperty("update_settlement_value"));
                    pst_preStat.setDouble(1, indexVal);

                    pst_preStat.setLong(2, l_indexID);

                    //		pst_preStat.setLong(2, l_indexID);
                    pst_preStat.setString(3, date);
                    pst_preStat.executeUpdate();
                }
            }
            if (close != null && close.equals("y")) {
                pst_preStat = connection
                        .prepareStatement(ConnectInit.queries.getProperty("update_close_value"));
                pst_preStat.setDouble(1, indexVal);

                pst_preStat.setLong(2, l_indexID);

                //		pst_preStat.setLong(2, l_indexID);

                pst_preStat.setString(3, date);
                Logging.debug("Updating closing value :" + pst_preStat);
                pst_preStat.executeUpdate();
            }

        } catch (Exception e) {
            // TODO: handle exception
            //e.printStackTrace();
        	Logging.error("Error : "+e.getMessage());
        }
    }
    
    
    
    
    
}
