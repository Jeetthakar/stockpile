/*
 * Created on Jan 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Vivek
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IncomeLibrary {
	static Logger Logging = Logger.getLogger(IncomeLibrary.class);
    public static Hashtable returnParentDetails(String pid){
        Hashtable ht=new Hashtable(); 
        Connect con1=ConnectInit.getConnect();
        Connection con=null;
        try{
        if(con==null)
        	con=con1.getdbConnection();
        PreparedStatement psmt = con.prepareStatement(ConnectInit.queries.getProperty("query_copy_indexmaster"));
        psmt.setString(1, pid);
        ResultSet indexdetails = psmt.executeQuery();
        Logging.debug("In IncomeLibrary.java");
        if(indexdetails.next()){
          
            ht.put("index_type",indexdetails.getString("index_type_id"));
            ht.put("index_indclass",indexdetails.getString("industry_classification_code"));
            ht.put("cdate",indexdetails.getString("creation_date"));
            ht.put("bdate",indexdetails.getString("base_date"));
            ht.put("cl_code",indexdetails.getString("industry_classification_id"));
            ht.put("apercent",indexdetails.getString("alert_percentage"));
            ht.put("rpercent",indexdetails.getString("rejection_percentage"));
            try{
            ht.put("rue_code",indexdetails.getString("o_ric"));
            }catch (Exception e) {
                ht.put("rue_code"," ");
                Logging.error(" Error : "+e.getMessage());
            }
            ht.put("bcurrency",indexdetails.getString("base_currency_id"));
            ht.put("bvalue",indexdetails.getString("base_value"));
            try{
            ht.put("isincode",indexdetails.getString("isin"));
            }catch (Exception e) {
                ht.put("isincode"," ");
                Logging.error(" Error : "+e.getMessage());
            }
            ht.put("starttime",indexdetails.getString("m_start_time"));
            ht.put("stoptime",indexdetails.getString("n_stop_time"));
            try{
            ht.put("computesettlement",indexdetails.getString("calculate_settlement_value"));
            }catch (Exception e) {
                ht.put("computesettlement","n");
            }
            try{
            ht.put("isinactive",indexdetails.getString("is_active"));
            }catch (Exception e) {
                ht.put("isinactive","");
            }
            ht.put("timeinterval",indexdetails.getString("computation_interval"));
            try{
            ht.put("iscaptured",indexdetails.getString("is_captured"));
            }catch (Exception e) {
                ht.put("iscaptured","n");
            }
            try{
            ht.put("capturedfrom",indexdetails.getString("captured_from"));
            Logging.debug("In IncomeLibrary.java after captured from try with : "+indexdetails.getString("captured_from"));
            }catch (Exception e) {
            	Logging.error("In IncomeLibrary.java In captured from Else"+e.getMessage());
                ht.put("capturedfrom","");
            }
            try{
            ht.put("nature",indexdetails.getString("growth_or_value"));
            Logging.debug("In IncomeLibrary.java after nature from try with : "+indexdetails.getString("growth_or_value"));
            }catch (Exception e) {
            	Logging.error("In IncomeLibrary.java In nature from Else : "+ht.size());
                ht.remove("nature");
                ht.put("nature"," ");
                Logging.error("In IncomeLibrary.java In nature from Else1"+e.getMessage());
            }
           
            
        }
        }catch (Exception e) {
          Logging.error(" Error : "+e.getMessage());
        }
        finally {
			try {					
					if(con!=null)
						con.close();
			}catch(Exception ex) {
				Logging.error(" Error : Unable to close Connection "+ex.getMessage());
			}
		}
        return ht;
        
    }
    
    public ArrayList getListOfDates(String d_baseDate,String indexid){
        ArrayList arrayList=new ArrayList();
        try{
        
     //    d_baseDate="08-03-2005";
    //    String d_baseDate="07-03-2005";
         java.util.Date d1=new Date();
      
         d1.setYear(new Integer(d_baseDate.trim()
					.substring(6, 10)).intValue()-1900);
         d1.setMonth(new Integer(d_baseDate
					.trim().substring(3, 5)).intValue()-1);
         d1.setDate( new Integer(
					d_baseDate.trim().substring(0, 2)).intValue());
       
         d1.setSeconds(0);
         
         java.util.Date d=new Date();
         d.setSeconds(0);
         long difference=d.getTime()-d1.getTime();
         
         String x,y,z;
         Hashtable holidayList=getHolidaysList(d_baseDate,d1.toString(),indexid);
         while(true){
         if(!(d1.after(d))){ 
         	if(!(holidayList.containsKey(new String(d1.toString())))){
              x=""+d1.getDate();
              if(x.length()==1)
                  x="0"+x;
              y=""+(d1.getMonth()+1);
              if(y.length()==1)
                  y="0"+y;
             arrayList.add(x+"-"+y+"-"+(d1.getYear()+1900));
             }
             d1=new Date(d1.getTime()+86400000);
        //     System.out.println("after");  
         }else{
             Logging.debug("break with arrayList.size() : : "+(arrayList.size()));
             break;
         }
         }
         Logging.debug("arrayList.size() : : "+(arrayList.size())); 
         
        return arrayList;
        }catch (Exception e) {
        	Logging.error(" Error : "+e.getMessage());
       //     e.printStackTrace();
            // TODO: handle exception
        }
        return arrayList;
    }
    /*public static void main(String[] z){
    	String d_baseDate="07-03-2005";
    	int i=0;
    	IncomeLibrary a1=new IncomeLibrary();
    	ArrayList arrlist=a1.getListOfDates(d_baseDate);
    	System.out.println(" arrlist size is "+arrlist.size());
    	while(i<arrlist.size()){
    		System.out.println(" arrlist element is "+arrlist.get(i));
    		i++;
    	}
    }*/
    /**
	 * @return Returns the Hashtable showing list of holidays values.
	 */
	public  Hashtable getHolidaysList(String hdate,String cdate,String indexid){
		Connect c= ConnectInit.getConnect();
		int flagdate=0;
		Hashtable tislist1=new Hashtable();
		tislist1.clear();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Vector holidays=new Vector();
		Vector arr_holidays=new Vector();
		Vector vexchlist=new Vector();
		holidays.clear();
		try{
			if(con == null){
				con = c.getdbConnection();
			}
			int i=0;
		pstmt = con.prepareStatement(ConnectInit.queries.
				getProperty("get_exchange_holidays"));
		pstmt.setString(1,hdate);
		pstmt.setString(2,cdate);
		pstmt.setString(3,indexid);		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			holidays.add(i,rs.getString(1));	
			i++;
			holidays.add(i,rs.getString(2));
			i++;
		}
		}catch(Exception sqlexp) {
			Logging.error("Error while getting tis "+ sqlexp.getMessage());
		}
		finally {
			try {
					if(pstmt!=null)
						pstmt.close();
					if(rs!=null)
						rs.close();
					if(con!=null)
						con.close();
			}catch(Exception ex) {
				Logging.error(" Error : Unable to close Connection "+ex.getMessage());
			}
		}
		if(holidays.size()!=0){
			vexchlist.add(holidays.get(1));
		for(int i=1;i<(holidays.size()-2);i+=2)
		{
			String str1=(String)holidays.get(i);
			String str2=(String)holidays.get(i+2);			
			int compare=(str1.compareTo(str2));
			Logging.debug("compare"+compare);
			if(compare!=0)
			{
				flagdate=0;
				Logging.debug("Indide compare if loop"+str2);
				if(vexchlist.size()==0){
					flagdate=0;
				}else{
					for(int l=0;l<vexchlist.size();l++){
						if(str2.equals((String)vexchlist.get(l))){
							flagdate=1;
						}
					}
				}
				if(flagdate==0){
					vexchlist.add(str2);
				}
			}			
		}
		for(int i=0;i<(holidays.size()-2);i+=2)
		{
			String value=(String)holidays.get(i);
			int flag=0;
			for(int k=0;k<vexchlist.size();k++){
				String exch=(String)vexchlist.get(k);
				for(int l=0;l<(holidays.size()-2);l+=2)
				{
					if((exch.equals((String)holidays.get(l+1))) && (value.equals((String)holidays.get(l)))){
						flag=1;
						break;
					}
				}
			}
			if(flag==1){
				tislist1.put(value,value);					
			}
		}
		}
		return tislist1;
	}

}
