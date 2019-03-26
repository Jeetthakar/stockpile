/*
 * Created on 27 jun,2008
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package harrier.income.com.fixedincome;
import harrier.income.com.entities.CFormula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author neha
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */



public class UpdateCorp {
	static Logger Logging = Logger.getLogger(UpdateCorp.class);
	static int count=0;
	// used in fi event
	public static void updateAction(Connection con,String query12,FixedIncomeCorporate corporateact){
		try{
			PreparedStatement  stmt = con.prepareStatement(query12);
			stmt.setString(1,corporateact.getNewTIS());	// new addition for updating stock_price_daily tis
			
			stmt.setDouble(2,Double.parseDouble(corporateact.getNewLTP()));			
			stmt.setDouble(3,corporateact.getNewmcv());
			stmt.setLong(4,Long.parseLong(corporateact.getStkid()));
			stmt.setLong(5,Long.parseLong(corporateact.getStkid()));
			stmt.executeUpdate();
			Logging.info("IN FIRSTUPDATION Action");			
		}catch(Exception e){			
			Logging.error("EXCEPTION IN UPDATION1 :" +e.getMessage());
		}
	}
	public static void update_resp_Action(Connection con,String query12,FixedIncomeCorporate corporateact){
			try{
				PreparedStatement  stmt = con.prepareStatement(query12);
				stmt.setDouble(1,Double.parseDouble(corporateact.getNewLTP()));				
				stmt.setDouble(2,corporateact.getNewmcv());
				stmt.setLong(3,Long.parseLong(corporateact.getStkid()));
				stmt.setString(4,corporateact.getApplied_date());
				stmt.executeUpdate();
				Logging.info("IN FIRST UPDATION comp");			
			}catch(Exception e){
				Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}			
		}	
	// used in fi event
	public static void update_undo_stkprice(Connection con,String undo_qry,String query,FixedIncomeCorporate corporateact)
	{
		try{
			ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,undo_qry,corporateact.getStkid(),corporateact.getApplied_date());
			String stkpri_id=null;
			if(rs!=null && rs.next()){
				 stkpri_id=rs.getString("fi_stock_price_daily_id");
			}
			if(rs!=null)
				rs.close();
			PreparedStatement  stmt = con.prepareStatement(query);			
			stmt.setDouble(1,Double.parseDouble(corporateact.getNewLTP()));				
			stmt.setDouble(2,corporateact.getNewmcv());
			stmt.setString(3,stkpri_id);			
			stmt.executeUpdate();
			
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	// used in fi event
	public static void updatecad(Connection con,String query,String query1,FixedIncomeCorporate corporateact){
			int amut=0;
			Hashtable hash1=corporateact.getHash1();
		try{
			for(Enumeration enmm =hash1.keys();enmm.hasMoreElements();)
				{
					 String id =(String)enmm.nextElement();
					 corporateact.setCad_id(id);
					 int val=Integer.parseInt(id);
					 String butt=corporateact.getButton();
					 if(query1!=null)
					 {
						ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,query1,id);
							if (rs1 != null && rs1.next()) {
									String ratio1 = rs1.getString("ratio_for_shares");
									corporateact.setRatio1(ratio1);
									String ratio2 = rs1.getString("ratio_shares_offered");
									corporateact.setRatio2(ratio2);
									String amt = rs1.getString("Amount");
									corporateact.setAmt(amt);
									String share = rs1.getString("values");
									corporateact.setShare(share);
									corporateact.setApply_date(rs1
											.getString("apply_on_date"));
							}
					 }
					 String date=corporateact.getApply_date();
					 if(date==null)
					 	date=accept_date();
					 if(date!=null)
					 	if(date.equals(""))
					 		date=accept_date();
					 check_value(corporateact);
					 String ap_date=accept_date();
					 String a_date=corporateact.getApply_date();
					 if(a_date!=null)
					     if(a_date.equals(""))
					         a_date=null;
					 PreparedStatement  stmt = con.prepareStatement(query);			 
					 stmt.setInt(1,val);
					 if(butt!=null)
					 {
					     if(butt.equals("Undo"))
					     {					         
					         if(a_date==null)
					             stmt.setString(2,corporateact.getApplied_date());
					         else
					             stmt.setString(2,a_date);
					         stmt.setString(3,null);
					     }
					     else
					     {
					         stmt.setString(2,a_date);
					         stmt.setString(3,date);
					     }
					 }
					 else
					 {
					     stmt.setString(2,a_date);
					     stmt.setString(3,corporateact.getApply_date());
					 }
					 String ratio1=corporateact.getRatio1();
					 String ratio2=corporateact.getRatio2();
					 if(ratio1!=null)
					 	if(ratio1.equals(""))
					 		ratio1=null;
					 if(ratio1==null)
					  	stmt.setString(4,null);//corporateact.getRatio1());
					else
					 stmt.setString(4,corporateact.getRatio1());
					 
					 if(ratio2!=null)
					 	if(ratio2.equals(""))
					 		ratio2=null;
					 	
					if(ratio2==null)
					 	stmt.setString(5,null);//corporateact.getRatio2());					 
					 else
					   stmt.setString(5,corporateact.getRatio2());
					 String amt=corporateact.getAmt();
					 if(amt==null)
					 	stmt.setString(6,null);					 
					 else
					 	stmt.setString(6,corporateact.getAmt());
					
					 if(butt==null)
					     stmt.setString(7,"y");
					 else
					 {
					     if(butt.equals("Undo"))
					         stmt.setString(7,"n");
					     else
					         stmt.setString(7,"y");
					 }
					 String shr=corporateact.getShare();
					 if(shr==null)
					 	stmt.setString(8,null);//corporateact.getShare());
					else
					 	stmt.setString(8,corporateact.getShare());
					 
					Date d=new Date();		
					long l1=d.getTime();	
					Time t1=new Time(l1);
					if(butt==null)
					{
						stmt.setTime(9,t1);
					}
					else
					{
						stmt.setTime(9,null);
					}
					 stmt.setInt(10,val);
					 stmt.executeUpdate();
					 Logging.info("complete");									
				}				
				}catch(Exception e){
					Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
		}
	public static void update_hist_cad(Connection con,String query,String query1,FixedIncomeCorporate corporateact,String id)
	{
		try{
		ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,query1,id);
		rs1.next();
		 PreparedStatement  stmt = con.prepareStatement(query);
		 stmt.setString(1,id);
		 String a_date=rs1.getString("apply_on_date");
		 if(a_date==null)
		 	a_date=corporateact.getApply_date();
		 stmt.setString(2,a_date);
		 stmt.setString(3,null);
		 stmt.setString(4,rs1.getString("ratio_for_shares"));
		 stmt.setString(5,rs1.getString("ratio_shares_offered"));
		 stmt.setString(6,rs1.getString("Amount"));
		 stmt.setString(7,"n");
		 stmt.setString(8,rs1.getString("values"));
		 stmt.setString(9,null);
		 stmt.setString(10,id);
		 stmt.executeUpdate();
		 Logging.info("complete from diary");
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	public static void insert_into_cad(Connection con,String query,String nextval,FixedIncomeCorporate  newcorporateact,String flag){							        					        					
			try{	
				String cad_val=null;
					if(flag==null)
					{
						int cad=FixedIncomeListTypeClass1.Select_nextval(con,nextval);
						cad_val=Integer.toString(cad);
						newcorporateact.setCad_id(cad_val);
					}					
					else
						cad_val=newcorporateact.getCad_id();

					PreparedStatement  stmt = con.prepareStatement(query);
					stmt.setString(1,cad_val);
					String a_date=newcorporateact.getAnnounce_date();
					if(newcorporateact.getAnnounce_date()==null)
						stmt.setString(2,null);
					else
					{
						if(newcorporateact.getAnnounce_date().equals(""))
							stmt.setString(2,null);
						else
							stmt.setString(2,a_date);
					}
					
					String e_date=newcorporateact.getEx_date();
					if(newcorporateact.getEx_date()==null)
						stmt.setString(3,null);//newcorporateact.getEx_date());
					else
					{
						if(newcorporateact.getEx_date().equals(""))
							stmt.setString(3,null);//newcorporateact.getEx_date());			
						else
							stmt.setString(3,e_date);//newcorporateact.getEx_date());
					}
					
					String r_date=newcorporateact.getRecord_date();
					if(newcorporateact.getRecord_date()==null)
						stmt.setString(4,null);
					else
					{
						if(newcorporateact.getRecord_date().equals(""))
							stmt.setString(4,null);
						else		
							stmt.setString(4,r_date);//newcorporateact.getRecord_date());
					}
					String bc_start=newcorporateact.getBc_start();				
					if(newcorporateact.getBc_start()==null)
						bc_start=null;
					else
						if(newcorporateact.getBc_start().equals(""))
							bc_start=null;
					String bc_end=newcorporateact.getBc_end();
					if(newcorporateact.getBc_end()==null)
						bc_end=null;
					else
						if(newcorporateact.getBc_end().equals(""))
							bc_end=null;
					String nc_start=newcorporateact.getNc_start();
					if(newcorporateact.getNc_start()==null)
						nc_start=null;
					else
						if(newcorporateact.getNc_start().equals(""))
							nc_start=null;
					String nc_end=newcorporateact.getNc_end();
					if(newcorporateact.getNc_end()==null)
						nc_end=null;
					else
						if(newcorporateact.getNc_end().equals(""))
							nc_end=null;
					String amt=newcorporateact.getAmt();
					if(newcorporateact.getAmt()==null)
						amt=null;
					else
						if(newcorporateact.getAmt().equals(""))
							amt=null;
					String percent=newcorporateact.getPercent();
					if(newcorporateact.getPercent()==null)
						percent=null;
					else
					{
						if(newcorporateact.getPercent().equals(""))
							percent=null;
						if(newcorporateact.getPercent().equals("0"))
							percent=null;
					}
					String ratio1=newcorporateact.getRatio1();
					
					if(newcorporateact.getRatio1()==null)
						ratio1=null;
					else
						if(newcorporateact.getRatio1().equals(""))
							ratio1=null;
					String ratio2=newcorporateact.getRatio2();
					if(newcorporateact.getRatio2()==null)
						ratio2=null;
					else
						if(newcorporateact.getRatio2().equals(""))
							ratio2=null;
					String share=newcorporateact.getShare().trim();
					if(newcorporateact.getShare()==null)
						share=null;
					else
						if(newcorporateact.getShare().equals(""))
							share=null;	
					stmt.setString(5,newcorporateact.getApply_date());
					stmt.setString(6,newcorporateact.getApplied_date());
					stmt.setString(7,ratio1);//newcorporateact.getRatio1());
					stmt.setString(8,ratio2);//newcorporateact.getRatio2());
					stmt.setString(9,amt);//newcorporateact.getAmt());					
					
					
					stmt.setString(10,percent);//newcorporateact.getPercent());					
					stmt.setString(11,newcorporateact.getStatus());
					stmt.setString(12,newcorporateact.getDescription());
					stmt.setString(13,newcorporateact.getCorpid());
					stmt.setString(14,newcorporateact.getStid());
					stmt.setString(15,share);//newcorporateact.getShare());
					stmt.setString(16,bc_start);//newcorporateact.getBc_start());
					stmt.setString(17,bc_end);//newcorporateact.getBc_end());
					stmt.setString(18,nc_start);//newcorporateact.getNc_start());
					stmt.setString(19,nc_end);//newcorporateact.getNc_end());
					if(flag==null)	{
					stmt.executeUpdate();				
					}
					else
					{
						stmt.setString(20,cad_val);
						stmt.executeUpdate();
					}					
					Logging.info("completed");					
				}catch(Exception e){
					Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
				}
			}
			// used in fi event	
public static void insert_into_cad_with_time(Connection con,String query,String nextval,FixedIncomeCorporate newcorporateact,String flag)
{	
		try{
		String cad_val=null;
		if(flag==null)
		{
			int cad=FixedIncomeListTypeClass1.Select_nextval(con,nextval);
			cad_val=Integer.toString(cad);
			newcorporateact.setCad_id(cad_val);
		}					
		else
			cad_val=newcorporateact.getCad_id();
		
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setString(1,cad_val);
		String a_date=newcorporateact.getAnnounce_date();
		if(newcorporateact.getAnnounce_date()==null)
		stmt.setString(2,null);
		else
		{
		if(newcorporateact.getAnnounce_date().equals(""))
		stmt.setString(2,null);
		else
		stmt.setString(2,a_date);
		}
		
		String e_date=newcorporateact.getEx_date();
		if(newcorporateact.getEx_date()==null)
		stmt.setString(3,null);//newcorporateact.getEx_date());
		else
		{
		if(newcorporateact.getEx_date().equals(""))
		stmt.setString(3,null);//newcorporateact.getEx_date());			
		else
		stmt.setString(3,e_date);//newcorporateact.getEx_date());
		}
		
		String r_date=newcorporateact.getRecord_date();
		if(newcorporateact.getRecord_date()==null)
		stmt.setString(4,null);
		else
		{
		if(newcorporateact.getRecord_date().equals(""))
		stmt.setString(4,null);
		else		
		stmt.setString(4,r_date);//newcorporateact.getRecord_date());
		}
		String bc_start=newcorporateact.getBc_start();		
		if(newcorporateact.getBc_start()==null)
		bc_start=null;
		else
		if(newcorporateact.getBc_start().equals(""))
		bc_start=null;
		String bc_end=newcorporateact.getBc_end();
		if(newcorporateact.getBc_end()==null)
		bc_end=null;
		else
		if(newcorporateact.getBc_end().equals(""))
		bc_end=null;
		String nc_start=newcorporateact.getNc_start();
		if(newcorporateact.getNc_start()==null)
		nc_start=null;
		else
		if(newcorporateact.getNc_start().equals(""))
		nc_start=null;
		String nc_end=newcorporateact.getNc_end();
		if(newcorporateact.getNc_end()==null)
		nc_end=null;
		else
		if(newcorporateact.getNc_end().equals(""))
		nc_end=null;
		String amt=newcorporateact.getAmt();
		if(newcorporateact.getAmt()==null)
		amt=null;
		else
		if(newcorporateact.getAmt().equals(""))
		amt=null;
		String percent=newcorporateact.getPercent();
		if(newcorporateact.getPercent()==null)
		percent=null;
		else
		{
		if(newcorporateact.getPercent().equals(""))
		percent=null;
		if(newcorporateact.getPercent().equals("0"))
		percent=null;
		}
		String ratio1=newcorporateact.getRatio1();
		
		if(newcorporateact.getRatio1()==null)
		ratio1=null;
		else
		if(newcorporateact.getRatio1().equals(""))
		ratio1=null;
		String ratio2=newcorporateact.getRatio2();
		if(newcorporateact.getRatio2()==null)
		ratio2=null;
		else
		if(newcorporateact.getRatio2().equals(""))
		ratio2=null;
		String share=newcorporateact.getShare();
		if(newcorporateact.getShare()==null)
		share=null;
		else
		if(newcorporateact.getShare().equals(""))
		share=null;	
		stmt.setString(5,newcorporateact.getApply_date());
		stmt.setString(6,newcorporateact.getApplied_date());
		stmt.setString(7,ratio1);//newcorporateact.getRatio1());	
		stmt.setString(8,ratio2);//newcorporateact.getRatio2());
		stmt.setString(9,amt);//newcorporateact.getAmt());					
		
		
		stmt.setString(10,percent);//newcorporateact.getPercent());
		stmt.setString(11,newcorporateact.getStatus());
		stmt.setString(12,newcorporateact.getDescription());
		stmt.setString(13,newcorporateact.getCorpid());
		stmt.setString(14,newcorporateact.getStid());
		stmt.setString(15,share);//newcorporateact.getShare());
		stmt.setString(16,bc_start);//newcorporateact.getBc_start());
		stmt.setString(17,bc_end);//newcorporateact.getBc_end());
		stmt.setString(18,nc_start);//newcorporateact.getNc_start());
		stmt.setString(19,nc_end);//newcorporateact.getNc_end());
		Date d=new Date();		
		long l1=d.getTime();	
		Time t1=new Time(l1);

		if(flag==null)	{
		stmt.setTime(20,t1);			
		stmt.executeUpdate();
		}
		else
		{
		stmt.setString(20,cad_val);
		stmt.executeUpdate();
		}					
		Logging.info("completed");	
		}catch(Exception e){
		Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
		}		
	}	
public static void reset(FixedIncomeCorporate newcorporateact)
{	
	newcorporateact.setCad_id(null);
	newcorporateact.setStid(null);
	newcorporateact.setExc(null);
	newcorporateact.setName(null);	
}
// used in fi event
public static void updatestkmaster(Connection con,String query,FixedIncomeCorporate corporateact,String face)
{		
	try{
	PreparedStatement  stmt = con.prepareStatement(query);	
	stmt.setString(1,corporateact.getNewTIS());	
	stmt.setString(2,face);	
	stmt.setString(3,corporateact.getStid());		
	stmt.executeUpdate();	
	}catch(Exception e)  
	{Logging.error("error=="+e.getMessage());}		
}
public static void update_stkmas_hist(Connection con,String query,FixedIncomeCorporate corp)
{
	try{
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setString(1,corp.getNewTIS());
		String face=corp.getNewFace();
		if(face==null)
			face=corp.getFace();
		stmt.setString(2,face);
		stmt.setString(3,corp.getCad_id());
		stmt.executeUpdate();
	}catch(Exception e){
		Logging.error("Error="+e.getMessage());
		}
}
public static void update_indcomp_history(Connection con,String query,FixedIncomeCorporate corp)
{
	try{
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setDouble(1,corp.getNewmcv());
		stmt.setString(2,corp.getI_index());
		stmt.setString(3,corp.getStid());
		stmt.setString(4,corp.getCad_id());
		stmt.executeUpdate();
		
	}catch(Exception e){
		Logging.error("Error="+e.getMessage());
		}
}
public static void insertstkmaster(Connection con,String query,String query1,FixedIncomeCorporate corporateact,String face)
{	
	try{		
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setString(1,corporateact.getStkid());
		ResultSet rs=stmt.executeQuery();
		if(rs!=null && rs.next()){
			
			Hashtable hash1=corporateact.getHash1();
		int val1;
		String corp=corporateact.getCorpid();
		Statement stmt1 = con.createStatement();		
		if(hash1.isEmpty()==false)
		{
			for(Enumeration enmm =hash1.keys();enmm.hasMoreElements();)
			{
				String id =(String)enmm.nextElement();
				String qry="";
				
				PreparedStatement  pstmt = con.prepareStatement(query1);
				pstmt.setInt(1,rs.getInt("stock_id"));			
				pstmt.setString(2,rs.getString("stock_type_id"));
				pstmt.setString(3,rs.getString("company_id"));			
				pstmt.setString(4,rs.getString("iwf"));
				pstmt.setString(5,rs.getString("tis"));			
				pstmt.setString(6,rs.getString("stock_name"));
				if(face==null)
					face=rs.getString("face_value");

				pstmt.setString(7,face);
				pstmt.setString(8,rs.getString("listing_date"));			
				pstmt.setString(9,rs.getString("growth_or_value"));
				pstmt.setString(10,rs.getString("rating_code_id"));			
				pstmt.setString(11,rs.getString("global100"));
				pstmt.setString(12,rs.getString("alert_percentage"));			
				pstmt.setString(13,rs.getString("rejection_percentage"));
				pstmt.setString(14,rs.getString("witholding_tax_applicable"));			
				pstmt.setString(15,rs.getString("witholding_tax_percent"));
				pstmt.setString(16,rs.getString("market_lot"));
				pstmt.setString(17,rs.getString("stock_exchange_id"));
				pstmt.setString(18,rs.getString("country_id"));			
				pstmt.setString(19,rs.getString("is_active"));
				pstmt.setString(20,rs.getString("stock_currency_id"));
				pstmt.setString(21,rs.getString("is_price_for_lot"));
				pstmt.setString(22,rs.getString("paid_value"));			
				String date=accept_date();				
				pstmt.setString(23,date);
				pstmt.setString(24,id);				
				pstmt.executeUpdate();				
			}//for end
		}//hash1 not empty
		else //if hash1 empty
		{
			PreparedStatement  pstmt = con.prepareStatement(query1);
			pstmt.setInt(1,rs.getInt("stock_id"));			
			pstmt.setString(2,rs.getString("stock_type_id"));
			pstmt.setString(3,rs.getString("company_id"));			
			pstmt.setString(4,rs.getString("iwf"));
			pstmt.setString(5,rs.getString("tis"));			
			pstmt.setString(6,rs.getString("stock_name"));
			if(face==null)
				face=rs.getString("face_value");
			
			pstmt.setString(7,face);
			pstmt.setString(8,rs.getString("listing_date"));			
			pstmt.setString(9,rs.getString("growth_or_value"));
			pstmt.setString(10,rs.getString("rating_code_id"));			
			pstmt.setString(11,rs.getString("global100"));
			pstmt.setString(12,rs.getString("alert_percentage"));			
			pstmt.setString(13,rs.getString("rejection_percentage"));
			pstmt.setString(14,rs.getString("witholding_tax_applicable"));			
			pstmt.setString(15,rs.getString("witholding_tax_percent"));
			pstmt.setString(16,rs.getString("market_lot"));			
			pstmt.setString(17,rs.getString("stock_exchange_id"));
			pstmt.setString(18,rs.getString("country_id"));			
			pstmt.setString(19,rs.getString("is_active"));
			pstmt.setString(20,rs.getString("stock_currency_id"));			
			pstmt.setString(21,rs.getString("is_price_for_lot"));
			pstmt.setString(22,rs.getString("paid_value"));			
			String date=accept_date();			
			pstmt.setString(23,date);
			pstmt.setString(24,corporateact.getCad_id());			
			pstmt.executeUpdate();			
		}
		}
	}catch(Exception e){
		Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());		
	
	}
}
// used in fi event
public static void update_index(Connection con,String query,ResultSet rs,FixedIncomeCorporate corporateact)
{
	try
	{
		Connect connect = ConnectInit.getConnect();	//connection
	//	CFormula cf=new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		while(rs.next() && rs!=null)
		{		
		//get currency exchange value
			String apply1=corporateact.getApply_date();
		FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,rs.getString("index_id"),rs.getString("fi_stock_id"));
		double mcv=0.0;
		mcv=cf.calMarketCap(Double.parseDouble(corporateact.getNewLTP()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getNewTIS()),Double.parseDouble(corporateact.getIwfstk()));
		
			PreparedStatement  pstmt = con.prepareStatement(query);
			pstmt.setInt(1,rs.getInt("iwf"));
			pstmt.setDouble(2,mcv);			
			pstmt.setInt(3,rs.getInt("fi_stock_id"));		
			pstmt.setInt(4,rs.getInt("index_id"));			
			pstmt.executeUpdate();
			Logging.info("comlete update index");
		}
	}catch(Exception e)
	{
		Logging.error("EXCEPTION IN UPDATION1 :" +e.getMessage());		
	}
}
public static void update_hist_index(Connection con,String query,ResultSet rs,FixedIncomeCorporate corporateact)
{
	try
	{
			PreparedStatement  pstmt = con.prepareStatement(query);
			pstmt.setInt(1,rs.getInt("iwf"));
			pstmt.setDouble(2,corporateact.getNewmcv());			
			pstmt.setInt(3,rs.getInt("stock_id"));		
			pstmt.setInt(4,rs.getInt("index_id"));			
			pstmt.executeUpdate();
			Logging.info("comlete update index");		
	}catch(Exception e)
	{
		Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());		
	}
}

public static void check_date(FixedIncomeCorporate newcorporateact)
{
	String desc=newcorporateact.getDescription();
	if(desc.equals(""))
		newcorporateact.setDescription(null);
	String ratio1=newcorporateact.getRatio1();
	if(ratio1.equals(""))
		newcorporateact.setRatio1(null);
	
	String ratio2=newcorporateact.getRatio2();
	if(ratio2.equals(""))
		newcorporateact.setRatio2(null);
	String announce=newcorporateact.getAnnounce_date();	
		if(announce.equals(""))										
			newcorporateact.setAnnounce_date(null);
	
	String ex=newcorporateact.getEx_date();
	if(ex.equals(""))
		newcorporateact.setEx_date(null);							
	
	String record=newcorporateact.getRecord_date();
	if(record.equals(""))
		newcorporateact.setRecord_date(null);
	String bc_start=newcorporateact.getBc_start();
	if(bc_start.equals(""))
		newcorporateact.setBc_start(null);
	String nc_start=newcorporateact.getNc_start();
	if(nc_start.equals(""))
		newcorporateact.setNc_start(null);
	String bc_end=newcorporateact.getBc_end();
	if(bc_end.equals(""))
		newcorporateact.setBc_end(null);
	String nc_end=newcorporateact.getNc_end();
	if(nc_end.equals(""))
		newcorporateact.setNc_end(null);	
}
public static void check_value(FixedIncomeCorporate corporateact)
{
	try
	{
		if(corporateact.getShare().equals(""))
			corporateact.setShare(null);					
		if(corporateact.getRatio1().equals(""))
		{
			if(corporateact.getCorpid().equals("8"))
				corporateact.setRatio1("1");
			else
				corporateact.setRatio1(null);
		}
		if(corporateact.getRatio2().equals(""))
		{
			if(corporateact.getCorpid().equals("8"))
				corporateact.setRatio2("1");
			else
				corporateact.setRatio2(null);
		}
		if(corporateact.getAmt().equals(""))
			corporateact.setAmt(null);
	}catch(Exception e)
	{
		Logging.error("Error="+e.getMessage());
	}
}

public static void checkvalue(FixedIncomeCorporate newcorporateact)
{
		if(newcorporateact.getShare().equals(""))
			newcorporateact.setShare(null);	
		
		if(newcorporateact.getAmt().equals(""))
			newcorporateact.setAmt(null);
		if(newcorporateact.getPercent().equals(""))
			newcorporateact.setPercent(null);
		if(newcorporateact.getPercent().equals("0"))
			newcorporateact.setPercent(null);
		if(newcorporateact.getShare().equals(""))
			newcorporateact.setShare(null);
		if(newcorporateact.getRatio1().equals(""))
		{
			if(newcorporateact.getCorpid().equals("spin-off"))
				newcorporateact.setRatio1("1");
			else
				newcorporateact.setRatio1(null);				
		}
		if(newcorporateact.getRatio2().equals(""))
		{
			if(newcorporateact.getCorpid().equals("spin-off"))
			{
				newcorporateact.setRatio2("1");
			}
			else
				newcorporateact.setRatio2(null);
		}
		if(newcorporateact.getAnnounce_date().equals(""))
			newcorporateact.setAnnounce_date(null);
		if(newcorporateact.getEx_date().equals(""))
			newcorporateact.setEx_date(null);
		if(newcorporateact.getRecord_date().equals(""))
			newcorporateact.setRecord_date(null);
		if(newcorporateact.getApply_date().equals(""))
			newcorporateact.setApply_date(null);
		
		if(newcorporateact.getBc_start().equals(""))
			newcorporateact.setBc_start(null);
		if(newcorporateact.getBc_end().equals(""))
			newcorporateact.setBc_end(null);
		if(newcorporateact.getNc_start().equals(""))
			newcorporateact.setNc_start(null);
		if(newcorporateact.getNc_end().equals(""))
			newcorporateact.setNc_end(null);
}
public static String accept_date()
{
	String months[]={"01","02","03","04","05","06","07","08","09","10","11","12"};	
	Calendar calendar=Calendar.getInstance();
	int day=calendar.get(Calendar.DATE);
	String day1=Integer.toString(day);
	String month=months[calendar.get(Calendar.MONTH)];
	int yr=calendar.get(Calendar.YEAR);
	String date=null;	
	if(day<=9)
	{
		day1=0+day1;
		date=day1+"-"+month+"-"+yr;//+(String)month+(String)yr;
	}
	else
	{
		date=day1+"-"+month+"-"+yr;//+(String)month+(String)yr;
	}			
	return date;
}
public static String accept_date1()
{
	String months[]={"01","02","03","04","05","06","07","08","09","10","11","12"};	
	Calendar calendar=Calendar.getInstance();
	int day=calendar.get(Calendar.DATE);
	String month=months[calendar.get(Calendar.MONTH)];
	int mnt=Integer.parseInt(month);	
	int yr=calendar.get(Calendar.YEAR);
	String date1=null;	
	String day1=Integer.toString(day);
	if(mnt==12)
	{	
		if(day<=9)
		{
			day1=0+day1;	
		}
		else
			day1=Integer.toString(day);
		yr=yr+1;
		date1=day1+"-"+"01"+"-"+yr;//+(String)month+(String)yr;				
	}
	else
	{
		if(day<=9)
		{
			day1=0+day1;	
		}
		else
			day1=Integer.toString(day);
	
		mnt=mnt+1;	
		String mnth=0+Integer.toString(mnt);		
		date1=day1+"-"+mnth+"-"+yr;//+(String)month+(String)yr;
	}
	return date1;
}
	public static void updateAction1(Connection con,String query12,FixedIncomeCorporate corporateact){
			try{
				PreparedStatement  stmt = con.prepareStatement(query12);
				stmt.setLong(1,Long.parseLong(corporateact.getNewTIS()));
				stmt.setLong(2,Long.parseLong(corporateact.getStkid()));
				stmt.executeUpdate();
			}catch(Exception e){
				Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
		}	
	public static void chagecurrupd(Connection con,Connect connect,FixedIncomeCorporate corp)
	{
		try{			
//as this method is used both for historic and general calculation,so there should be date comparison
			
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corp.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
			
			String corpval=corp.getCorpid();
			//get the currencies id
			int oldcur=0;
			int newcur=0;
			String query=ConnectInit.queries.getProperty("resp_ftcurr");
			ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,corp.getFtcurrency());
			int fcurr=0;
			int tcurr=0;
			if(rs!=null && rs.next()){
				fcurr=rs.getInt("from_currency_id");
				tcurr=rs.getInt("to_currency_id");
				rs.close();
			}
			int curid=Integer.parseInt(corp.getCurrencyid());
			if(curid==fcurr)
			{
				oldcur=fcurr;
				newcur=tcurr;				
			}
			if(curid==tcurr)
			{
				oldcur=tcurr;
				newcur=fcurr;
			}
			
			//insert into diary
			query=ConnectInit.queries.getProperty("insert_curr_diary");
			String corp_query =ConnectInit.queries.getProperty("get_corporate_list_index");
			
			String date=corp.getApply_date();//accept_date();
		 	corp.setAnnounce_date(date);
		 	corp.setEx_date(date);
		 	corp.setRecord_date(date);
		 	corp.setApply_date(date);
		 	corp.setApplied_date(date);
		 	corp.setStatus("y");
		 	corp.setAmount(corp.getCurr_val());
		 	corp.setValues(corp.getFtcurrency());
		 	corp.setDescription(corp.getInd_div());
			ResultSet rs2 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
			FixedIncomeListTypeClass1.check_corp_name(rs2,corpval,corp);						    
			if(rs2!=null) 
				rs2.close();	
			String nextval=ConnectInit.queries.getProperty("get_sequence_cad_id");
			insert_curr_diary(con,query,nextval,corp);			
			corp.setCorpid(corpval);
			
			corp.setValues(Integer.toString(newcur));
			//Update Index Master for parent index
			query=null;
			query=ConnectInit.queries.getProperty("update_indmaster");
			update_curr_indmaster(con,query,corp);			
			
			//update Index value daily
			if(chk_dt==0)
			{
				String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");
				update_index_daily(con,ind_val_daily,corp);
			}
			else
			{
				String qry=ConnectInit.queries.getProperty("get_undo_index_close");
				String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
				update_undo_index_daily(con,qry,ind_val_daily,corp);
			}
			//Insert Index master history
			query=null;
			String query1=ConnectInit.queries.getProperty("insert_indmas_his");
			query=ConnectInit.queries.getProperty("select_index_name");			
			insert_index_mas_his(con,query,query1,corp);
			
			//update index composition
			String ori_currid=corp.getValues();
			update_curr_indcomp(corp);
			corp.setValues(ori_currid);			
		}catch(Exception e){
			Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
		
	}
	
	public static void update_affectInd_curr(Connection con,Connect connect,FixedIncomeCorporate corp)
	{	
		try{
			int oldcur=0;
			int newcur=0;
			
			
			Hashtable hash=corp.getHash_affind();
			for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
			{
				String query=ConnectInit.queries.getProperty("resp_ftcurr");
				ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,corp.getFtcurrency());
				int fcurr=0;
				int tcurr=0;
				if(rs!=null && rs.next()){
					fcurr=rs.getInt("from_currency_id");
					tcurr=rs.getInt("to_currency_id");
					rs.close();
				}
				int curid=Integer.parseInt(corp.getCurrencyid());
				if(curid==fcurr)
				{
					oldcur=fcurr;
					newcur=tcurr;				
				}
				if(curid==tcurr)
				{
					oldcur=tcurr;
					newcur=fcurr;
				}
				corp.setValues(Integer.toString(newcur));
				
				 String id =(String)enmm.nextElement();
				 String div1[]=ActionCorp.token2(id);
				 String ind_id =div1[1];
				 corp.setI_index(ind_id);
				 //update index master for child index
				query=null;
				query=ConnectInit.queries.getProperty("update_indmaster");
				update_curr_indmaster(con,query,corp);
				
//				update Index value daily
				ActionCorp.cal_curr_ind(con,connect,corp,corp.getI_index());
				String dt=UpdateCorp.accept_date();   //get the current date
				String apply=corp.getApply_date();
				int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
				if(chk_dt==0)
				{
					String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");
					update_index_daily(con,ind_val_daily,corp);
				}
				else
				{
					String qry=ConnectInit.queries.getProperty("get_undo_index_close");					
					String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
					update_undo_index_daily(con,qry,ind_val_daily,corp);
				}
//				Insert Index master history
				query=null;
				String query1=ConnectInit.queries.getProperty("insert_indmas_his");
				query=ConnectInit.queries.getProperty("select_index_name");			
				insert_index_mas_his(con,query,query1,corp);
				
//				update index composition
				String ori_currid=corp.getValues();
				update_curr_indcomp(corp);
				corp.setValues(ori_currid);	
			}// for end
		}catch(Exception e){
			Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}		
	}
	public static void update_hist_divisor(Connection con,String query,FixedIncomeCorporate corporateact,String id){
		try{
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setDouble(1,Double.parseDouble(corporateact.getNewdivisor()));
			stmt.setDouble(2,Double.parseDouble(corporateact.getNewtmcv()));
			stmt.setLong(3,Long.parseLong(id));
			stmt.setString(4,corporateact.getApplied_date());
			stmt.executeUpdate();
		}catch(Exception e){
			Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
	}
	public static void updateDivisor(Connection con,String query11,String query12,Hashtable hash,FixedIncomeCorporate corporateact){
		try{
			Connect connect = ConnectInit.getConnect();  //connection			
		for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
		{
			 String id =(String)enmm.nextElement();						
			 	ResultSet rs1 = FixedIncomeListTypeClass1.getResult12(con,query11,id);
			 	if(rs1.next()){
			 	
				double oldtmcv=rs1.getDouble("tmcv");
				double olddivi=rs1.getDouble("divisor");
				// get currency exchange rate
				String apply1=corporateact.getApply_date();
				FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,id,corporateact.getStid());
		//		CFormula cf = new CFormula();
				CFormula cf = ConnectInit.getCFormula();
				double oldmcv=  cf.calMarketCap(Double.parseDouble(corporateact.getOldltp()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getOldtis()),Double.parseDouble(corporateact.getIwfstk()));
				double newmcv= cf.calMarketCap(Double.parseDouble(corporateact.getNewLTP()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getNewTIS()),Double.parseDouble(corporateact.getIwfstk()));						
				corporateact.setNewmcv(newmcv);	
				//corporateact.setNewmcv(newmcv);
				double newtmcv=((oldtmcv)+(newmcv-oldmcv));//change
				Logging.debug(" NEW TMCV :"+newtmcv);
				double diff=cf.diffTMCV(oldtmcv,newtmcv);
				double newdivisor=cf.newDivisorCorp(oldtmcv,diff,olddivi);
				Logging.debug(" NEW DIVISOR :" + newdivisor);
				PreparedStatement  stmt = con.prepareStatement(query12);
				stmt.setString(1,Double.toString(newdivisor));
				stmt.setString(2,Double.toString(newtmcv));
				stmt.setString(3,id);
				stmt.setString(4,id);
				stmt.executeUpdate();
				Logging.debug("updated divisor in stock event");
			 	}else{
			 		Logging.info("resultset empty");
			 		
			 	}
			}
		}catch(Exception e){
			Logging.error("Errorr in for=="+e.getMessage());
			}
	}
	// used in fi event
	public static void update_hist_value(FixedIncomeCorporate corp)	
	{			
		try{
			Connect connect = ConnectInit.getConnect();
			Connection Hist_con=connect.getConnectionForHistTransaction();
			//Logging.getDebug("in commit hist_con=="+Hist_con);
			Hist_con.commit();
		//	Hist_con.close();
			
			connect = ConnectInit.getConnect();
			Connection con=connect.getdbConnection();			
			Logging.debug("in commit con==="+con);
			
			String corp_act=corp.getCorpid();		
			String date=corp.getApply_date();
			Hashtable hash1=corp.getHash1();
			String chk=null;
			Logging.debug("hash in update=="+hash1);			
			if(hash1.isEmpty()==true)
			{
				chk=corp.getChk_but();
				if(chk==null)
				{
	//				insert into CA dairy
					corp.setCad_id(chk);					
					corp.setAnnounce_date(date);
					corp.setRecord_date(date);
					corp.setApplied_date(date);
					corp.setStatus("y");	
					String query =ConnectInit.queries.getProperty("fixed_income_get_corporate_list_stock");
					ResultSet rs = FixedIncomeListTypeClass1.resultCorporate(con,query);    
					FixedIncomeListTypeClass1.check_corp_name(rs,corp_act,corp);
					query=ConnectInit.queries.getProperty("fixed_income_insert_into_cad_values_with_time");
					String nextval=ConnectInit.queries.getProperty("get_sequence_cad_id");
					UpdateCorp.insert_into_cad_with_time(con,query,nextval,corp,null);
				}
				else
				{				
					//Logging.getDebug("chk in update=="+chk);
					String query=ConnectInit.queries.getProperty("update_cad");								
					hash1=corp.getHash1();
					hash1.clear();
					corp.setHash1(hash1);
					hash1=corp.getHash1();
					hash1.put(new String(chk),new String(chk));
					corp.setHash1(hash1);
				//	Logging.getDebug("hash in update=="+hash1);
					UpdateCorp.updatecad(con,query,null,corp);	
				}
			}
			else
			{
				String query=ConnectInit.queries.getProperty("update_cad");
				UpdateCorp.updatecad(con,query,null,corp);
			}
			corp.setCorpid(corp_act);
			int flag=0;
			corp.setApplied_date(date);
			corp.setApply_date(date);
			if(corp_act.equals("cashdividend"))
			{
				String query=ConnectInit.queries.getProperty("select_system_config");
				try{
				 ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,query);
				 if(rs!=null && rs.next()){
				 String type=rs.getString("adjust_stock_price");
				 if(type.equals("y"))
				 	flag=1;						 
				 if(type.equals("n"))
				 	flag=0;
				 }
				}catch(Exception e){Logging.error(" error=="+e.getMessage());}
			}
			else
				flag=1;
			
			String face=corp.getNewFace();
			if(face==null)
				face=corp.getFace();
			if(flag==1)
			{
		//		CFormula cf=new CFormula();
				CFormula cf = ConnectInit.getCFormula();
				corp.setStid(corp.getS_stock());
				corp.setStkid(corp.getS_stock());				
				
				double newmcv= cf.calMarketCap(Double.parseDouble(corp.getNewLTP()),Long.parseLong(corp.getMark_lot()),1,Long.parseLong(corp.getNewTIS()),Double.parseDouble(corp.getIwfstk()));
				corp.setNewmcv(newmcv);
				
	//			update tis in stock master			
				String query=ConnectInit.queries.getProperty("fixed_income_update_tis_stockmaster");
				UpdateCorp.updatestkmaster(con,query,corp,face);

	//		update respective stock price daily	
//				get stock_price_daily id
				String undo_query=ConnectInit.queries.getProperty("fixed_income_get_undo_close_value");
				
				//update stock price daily for the particular date
				query =ConnectInit.queries.getProperty("fixed_income_update_undo_stock_price");
			 	UpdateCorp.update_undo_stkprice(con,undo_query,query,corp);				
		//	update respective index value daily
			 	
			 	/*
			 	if(!(corp_act.equals("stockdividend/bonus")))
			 	{
			 		String qry=connect.queries.getProperty("get_undo_index_close");
				 	query =connect.queries.getProperty("update_undo_ind_close");
				 	UpdateCorp.hist_update_Divisor(con,qry,query,corp.getHash(),corp);					
			 	}*/
		//	update index composition 
		 		query=ConnectInit.queries.getProperty("fixed_income_select_index_composition");
				ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,query,corp.getStid());					
				String qry=ConnectInit.queries.getProperty("fixed_income_update_index");
				UpdateCorp.update_index(con,qry,rs1,corp);
			}//if flag=1
			//insert into stock master history	
			/* do later
			String query=connect.queries.getProperty("detail_stock_master");					 
			String qry=connect.queries.getProperty("insert_stock_master");
			UpdateCorp.insertstkmaster(con,query,qry,corp,face);			
			*/
			//update stock price daily till current date
			Update_hist_stkprdaily(con,connect,corp);
		}catch(Exception e){
			Logging.error("Exception in  second updation" + e.getMessage());
			}
		}
	
	public static void Update_hist_stkprdaily(Connection con,Connect connect,FixedIncomeCorporate corp)
	{
		try{
	//		  CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			String up_query =ConnectInit.queries.getProperty("fixed_income_update_undo_stock_price");
			  String query=ConnectInit.queries.getProperty("fixed_income_select_stkprice_historic");
			  ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,query,corp.getStid(),corp.getApply_date());
			  while(rs!=null && rs.next())
			  {
			  	String close=rs.getString("adjusted_price");
			  	if(close==null)
			  		close=rs.getString("stock_close_value");
			  	double mcv=0.0;
			  	mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(corp.getMark_lot()),1,Long.parseLong(corp.getNewTIS()),Double.parseDouble(corp.getIwfstk()));
			  			  	
			  	PreparedStatement  stmt = con.prepareStatement(up_query);			
				stmt.setDouble(1,Double.parseDouble(close));				
				stmt.setDouble(2,mcv);
				stmt.setString(3,rs.getString("fi_stock_price_daily_id"));			
				stmt.executeUpdate();
			  }
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	//used in fi event
	public static void reapply_action(FixedIncomeCorporate corp)
	{
		try{
			Connect connect=ConnectInit.getConnect();
			connect = ConnectInit.getConnect();
			Connection con=connect.getdbConnection();			
		//	CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			//queries
			String stk_close=ConnectInit.queries.getProperty("fixed_income_get_undo_close_value");//get stock closing value for respective date
			String stk_qry=ConnectInit.queries.getProperty("fixed_income_detail_stock_master");
			
			Vector v=corp.getV();
			int vlen=v.size();						
			for(int i=(vlen-1) ;i >=0 ;i--)
			{	
				String id=(String)v.get(i);
				corp.setCad_id(id);
				//get CA detail
				String qry=ConnectInit.queries.getProperty("select_rep_cad");
				ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,qry,id);
				rs1.next();
				String cam=rs1.getString("cam_id");
				String stock=rs1.getString("fi_stock_id");		
				String index_id=rs1.getString("index_id");
				corp.setStid(stock);
				corp.setStkid(stock);
				corp.setI_index(index_id);
				corp.setApplied_date(rs1.getString("applied_date"));
				corp.setApply_date(rs1.getString("applied_date"));
				String date=corp.getApply_date();
				corp.setValues(rs1.getString("values"));
				
				//get CA name and type				
				String query=ConnectInit.queries.getProperty("get_corp_name");
				ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,cam);
				String corp_nm=null;
				String type=null;
				if(rs!=null && rs.next()){
					corp_nm=rs.getString("cam_shortname");
					type=rs.getString("corporate_action_type");
					rs.close();
				}
				//store CA name in bean
				StringTokenizer st = new StringTokenizer(corp_nm," ");
				int val=st.countTokens();
				if(val > 1)
				{
					String div[]=ActionCorp.token2(corp_nm);
					String cval=(div[0]+div[1]).toLowerCase().trim();
					corp.setCorpid(cval);
				}
				else
					corp.setCorpid(corp_nm.toLowerCase().trim());
				
				String corp_act=corp.getCorpid();
				String close=null;				
				if(type.equals("fixedincome event"))
				{
//					get stock closing value					
					rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_close,stock,date);
					if(rs!=null && rs.next()){
						close=rs.getString("adjusted_price");
						if(close==null)
							close=rs.getString("stock_close_value");					
						corp.setClose(close);
						//				get stock detail					
						rs=FixedIncomeListTypeClass1.getAffected(con,stk_qry,stock);
						rs.next();
						corp.setTis(rs.getString("tis"));
						corp.setFace(rs.getString("face_value"));
						corp.setIwfstk(rs.getString("iwf"));
						corp.setMark_lot(rs.getString("market_lot"));
						rs.close();				
					}

					corp.setAmt(rs1.getString("amount"));
					corp.setRatio1(rs1.getString("ratio_for_shares"));
					corp.setRatio2(rs1.getString("ratio_shares_offered"));
					if(corp_nm.equals("capital reduce"))
						corp.setShare(corp.getTis());
					else
						corp.setShare(rs1.getString("values"));				
					
					FixedIncomeNCorp_Action.CalApply(con,connect,corp);
					
			//store new values in database
					//update stock master
					String face=corp.getNewFace();
					if(face==null)
						face=corp.getFace();						
					String up_stkqry=ConnectInit.queries.getProperty("fixed_income_update_tis_stockmaster");
					UpdateCorp.updatestkmaster(con,up_stkqry,corp,face);
					
          //	update stock price daily
//					get stock_price_daily id
					String undo_query=ConnectInit.queries.getProperty("fixed_income_get_undo_close_value");
					
					double mcv=cf.calMarketCap(Double.parseDouble(corp.getNewLTP()),Long.parseLong(corp.getMark_lot()),1,Long.parseLong(corp.getNewTIS()),Double.parseDouble(corp.getIwfstk()));
					corp.setNewmcv(mcv);
					//update stock price daily for the particular date
					query =ConnectInit.queries.getProperty("fixed_income_update_undo_stock_price");
				 	UpdateCorp.update_undo_stkprice(con,undo_query,query,corp);		

					Logging.debug("mcv=="+corp.getNewmcv());
					//get affected index
					if(!(corp_nm.equals("stock dividend/bonus")))
					{
						String qry1=ConnectInit.queries.getProperty("fixed_income_affected_index_by_ca");			
						rs = FixedIncomeListTypeClass1.getResult1(con,qry1,stock);
						boolean chk_rs=rs.next();
						if(chk_rs==true)  //if stock belong to any index
						{
							rs.beforeFirst();
							while(rs!=null && rs.next())
							{
								String index=rs.getString("index_id"); //get index id
								//get currency exchange rate
								String apply1=corp.getApply_date();
								Logging.debug("apply date from line no 1320 Updatecorp.java "+apply1);
								FixedIncomeNCorp_Action.get_currency(con,connect,corp,index,stock);
								mcv=cf.calMarketCap(Double.parseDouble(corp.getNewLTP()),Long.parseLong(corp.getMark_lot()),Double.parseDouble(corp.getCurr_val()),Long.parseLong(corp.getNewTIS()),Double.parseDouble(corp.getIwfstk()));
								corp.setNewmcv(mcv);
								//update index composition
								String up_qry=ConnectInit.queries.getProperty("fixed_income_update_index"); //update index composition
								UpdateCorp.update_hist_index(con,up_qry,rs,corp);
								
								String query1=ConnectInit.queries.getProperty("get_undo_index_close"); //get index values
								ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(con,query1,index,date);
								rs2.next();
								corp.setDivisor(rs2.getString("divisor"));
								corp.setTmcv(rs2.getString("tmcv"));
								
								ActionCorp.toGetaffect(corp);//calculate index new values
								
							//update index value daily
								query =ConnectInit.queries.getProperty("update_undo_ind_close");						 	
							 // not required for fi event	UpdateCorp.hist_update_Divisor(con,query1,query,corp.getHash(),corp);								
						
							}
						}
					}
//					update stock master history		
					/*  do later 
					query=connect.queries.getProperty("update_stmas_history");
					UpdateCorp.update_stkmas_hist(con,query,corp);	*/		
		
				}//stock event
				/*  do later index events
				if(type.equals("index event"))  //recalculate index
				{
					//queries 
					String indexcomp=connect.queries.getProperty("update_index");
					Hashtable hash_aff=corp.getHash_affind(); //refresh affect index
					hash_aff.clear();
					corp.setHash_affind(hash_aff);
					
					if(corp_act.equals("changeindcurr"))
					{
						double new_val=rs1.getDouble("amount");
						String ft_curr=rs1.getString("values");
						String desc=rs1.getString("description");
						//update index composition and index value daily
						ActionCorp.temp_update_curr(con,connect,corp,date,new_val,ft_curr,desc);
						//get child indices
						query=connect.queries.getProperty("resp_child_indcurr");
						ListTypeClass1.affect_child_list(con,corp,query);
						
//						calculate values for child index
						hash_aff=corp.getHash_affind();
						if(hash_aff.isEmpty()==false)
						{							
							for(Enumeration enmm=hash_aff.keys();enmm.hasMoreElements();)
							{
								String id1=(String)enmm.nextElement();
								String div[]=ActionCorp.token2(id1);
								corp.setI_index(div[1]);
								ActionCorp.temp_update_curr(con,connect,corp,date,new_val,ft_curr,desc);
							}
						}
					}//if change index currency
					else
					{
							hist_reapply_action(con,connect,corp,date); //reapply parent index
							//get respective child indices
							if(corp_act.equals("addstock"))
							{
								String child_index=connect.queries.getProperty("select_child_index");
								ListTypeClass1.affect_index_list(con,corp,child_index,stock);
							}
							if((corp_act.equals("deletestock"))|(corp_act.equals("changeiwf")))
							{
								String affect_index=connect.queries.getProperty("select_affect_index");			        
								ListTypeClass1.affect_index_list(con,corp,affect_index,stock);
							}
							//reapply child indices
							hash_aff=corp.getHash_affind();
							if(hash_aff.isEmpty()==false)
							{							
								for(Enumeration enmm=hash_aff.keys();enmm.hasMoreElements();)
								{
									String id1=(String)enmm.nextElement();
									String div[]=ActionCorp.token2(id1);
									corp.setI_index(div[1]);
									hist_reapply_action(con,connect,corp,date); //reapply child indices
								}
							}							
					}//else
				} */        //index event
			}//for end			
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	public static void hist_reapply_action(Connection con,Connect connect,FixedIncomeCorporate corp,String date)
	{
		String ori_iwf=corp.getValues();
	//	CFormula cf=new CFormula();
		CFormula cf = ConnectInit.getCFormula();
		try{			
			//queries
			String stk_close=ConnectInit.queries.getProperty("get_undo_close_value");//get stock closing value for respective date
			String stk_qry=ConnectInit.queries.getProperty("detail_stock_master");
			String indexcomp=ConnectInit.queries.getProperty("update_index");
			String qry=ConnectInit.queries.getProperty("update_indcomp_history");
			
//			get latest index closing value
			String query1=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");
			ResultSet rs=FixedIncomeListTypeClass1.getResult12(con,query1,corp.getI_index());
			String index_val=null;
			if(rs!=null && rs.next()){
				index_val=rs.getString("index_closing_value");
				rs.close();
			}
			
//			get respective stock and corporate action
			String stock=corp.getStid();
			String corp_act=corp.getCorpid();
			double tmcv=0.0,mcv=0.0;
			String ind_comp=ConnectInit.queries.getProperty("get_composition_of_parent");// get all stock's list
			ResultSet rs2=FixedIncomeListTypeClass1.getAffected(con,ind_comp,corp.getI_index());
			while(rs!=null && rs2.next())
			{
				String stk=rs2.getString("stock_id");
				String iwf=rs2.getString("iwf");
				
				rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_close,stk,date);
				String close=null;
				if(rs!=null && rs.next()){
					 close=rs.getString("adjusted_price");
					if(close==null)
						close=rs.getString("stock_closing_value");
					rs.close();
				}

//				get stock detail					
				rs=FixedIncomeListTypeClass1.getAffected(con,stk_qry,stk);
				String tis=null;
				String ml=null;
				String iwf_val=null;

				if(rs!=null && rs.next()){
					tis=rs.getString("tis");
					ml=rs.getString("market_lot");
					iwf_val=rs.getString("iwf");
					rs.close();
				}
				//get currency exchange value
				String apply1=corp.getApply_date();
				Logging.debug("apply date from line no 1461 Updatecorp.java "+apply1);
				FixedIncomeNCorp_Action.get_currency(con,connect,corp,corp.getI_index(),stk);
				
				if(stk.equals(stock))
				{
					if(corp_act.equals("deletestock"))
					{
						mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corp.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));
						corp.setNewmcv(mcv);
						String delete=ConnectInit.queries.getProperty("delete_index_comp");
						try
						{
						PreparedStatement stmt = con.prepareStatement(delete);
						stmt.setString(1,corp.getI_index());
						stmt.setString(2,corp.getStid());
						stmt.executeQuery();
						}catch(Exception e){
							Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
						}
						tmcv=tmcv;												
						//update index composition history
						update_indcomp_history(con,qry,corp);
					}
					if(corp_act.equals("changeiwf"))
					{
						mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corp.getCurr_val()),Long.parseLong(tis),Double.parseDouble(corp.getValues()));						
						corp.setNewmcv(mcv);
						tmcv=tmcv+mcv;
						//update index composition
						UpdateCorp.update_index_comp(con,indexcomp,corp);
						
//						update index composition history
						update_indcomp_history(con,qry,corp);
					}
				}
				else //if stock's are not equal
				{
					mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corp.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));											
					corp.setNewmcv(mcv);					
					tmcv=tmcv+mcv;
					corp.setValues(iwf);
					//update index composition
					UpdateCorp.update_index_comp(con,indexcomp,corp);
				}						
			}//while end
			if(corp_act.equals("addstock")) //if CA is addition of stock
			{
				rs=FixedIncomeListTypeClass1.getResult_apply(con,stk_close,stock,date);
				String close=null;
				if(rs!=null && rs.next()){
					close=rs.getString("adjusted_price");
					if(close==null)
						close=rs.getString("stock_closing_value");
					rs.close();
				}
//				get stock detail					
				rs=FixedIncomeListTypeClass1.getAffected(con,stk_qry,stock);
				String tis=null;
				String iwf=null;
				String ml=null;

				if(rs!=null && rs.next()){
					tis=rs.getString("tis");
					iwf=rs.getString("iwf");
					ml=rs.getString("market_lot");
					rs.close();
				}
				//get currency exchange value
				String apply1=corp.getApply_date();
				Logging.debug("apply date from line no 1527 Updatecorp.java "+apply1);
				FixedIncomeNCorp_Action.get_currency(con,connect,corp,corp.getI_index(),stock);
				
				mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corp.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));				
				corp.setNewmcv(mcv);
				tmcv=tmcv+mcv;
				//insert into index composition
				corp.setValues(iwf);
				String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");
				insert_index_comp(con,insert_index_comp,corp);
				
//				update index composition history
				update_indcomp_history(con,qry,corp);
			}
			//calculate divisor
			double div=tmcv/Double.parseDouble(index_val);
			corp.setNewTmcv(Double.toString(tmcv));
			corp.setNewdivisor(Double.toString(div));
			//update index value daily
			qry=ConnectInit.queries.getProperty("get_undo_index_close");			
			String query=ConnectInit.queries.getProperty("update_undo_ind_close");
			UpdateCorp.update_undo_index_daily(con,qry,query,corp);
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
		corp.setValues(ori_iwf);
	}
	
	public static void recalupdateDivisor(Connection con,String query11,String query12,Hashtable hash,FixedIncomeCorporate corporateact){
			String date=corporateact.getApply_date();//Applied_date();
			Connect connect = ConnectInit.getConnect();  //connection
			
			for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
			{
				 String id =(String)enmm.nextElement();
				 try{			 	
				 	ResultSet rs1 = FixedIncomeListTypeClass1.getResult_apply(con,query11,id,date);
				 	rs1.next();
					double oldtmcv=rs1.getDouble(1);					
					double olddivi=rs1.getDouble(2);					
					//get currency exchange value
					FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,id,corporateact.getStid());
					CFormula cf = ConnectInit.getCFormula();	
			//		CFormula cf = new CFormula();
					double oldmcv=  cf.calMarketCap(Double.parseDouble(corporateact.getOldltp()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getOldtis()),Double.parseDouble(corporateact.getIwfstk()));
					double newmcv= cf.calMarketCap(Double.parseDouble(corporateact.getNewLTP()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getNewTIS()),Double.parseDouble(corporateact.getIwfstk()));						
					corporateact.setNewmcv(newmcv);	
					
					
					//corporateact.setNewmcv(newmcv);
					
					double newtmcv=((oldtmcv - newmcv)+(oldmcv));
					
					double diff=cf.diffTMCV(oldtmcv,newtmcv);
					double newdivisor=cf.RecalnewDivisorCorp(oldtmcv,diff,olddivi);		
					PreparedStatement  stmt = con.prepareStatement(query12);					
					stmt.setDouble(1,newdivisor);
					stmt.setDouble(2,newtmcv);
					stmt.setLong(3,Long.parseLong(id));
					stmt.setString(4,date);
					stmt.executeUpdate();				
					rs1.close();
				 }catch(Exception e){
				 	Logging.error("Exception in second updation" + e.getMessage());
				 }
				 
			}
		
		}
	public static void undoupdateDivisor(Connection con,String query11,String query12,Hashtable hash,FixedIncomeCorporate corporateact){
		String date=corporateact.getApplied_date();
		Connect connect = ConnectInit.getConnect();  //connection
		
		for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
		{
			 String id =(String)enmm.nextElement();
			 try{			 	
			 	ResultSet rs1 = FixedIncomeListTypeClass1.getResult_apply(con,query11,id,date);
			 	rs1.next();
				double oldtmcv=rs1.getDouble("tmcv");
				Logging.debug("IN UPDATION CLASS old tmcv:" + oldtmcv);
				double olddivi=rs1.getDouble("divisor");
				String indval_id=rs1.getString("index_value_daily_id");
				Logging.debug("IN UPDATION CLASS old divi:" + olddivi);
				//get currency exchange value
				FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,id,corporateact.getStid());
				CFormula cf = ConnectInit.getCFormula();	
		//		CFormula cf = new CFormula();
				double oldmcv=  cf.calMarketCap(Double.parseDouble(corporateact.getOldltp()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getOldtis()),Double.parseDouble(corporateact.getIwfstk()));
				double newmcv= cf.calMarketCap(Double.parseDouble(corporateact.getNewLTP()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getNewTIS()),Double.parseDouble(corporateact.getIwfstk()));						
				corporateact.setNewmcv(newmcv);	
				
				
				//corporateact.setNewmcv(newmcv);
				
				double newtmcv=((oldtmcv - newmcv)+(oldmcv));
				
				double diff=cf.diffTMCV(oldtmcv,newtmcv);
				double newdivisor=cf.RecalnewDivisorCorp(oldtmcv,diff,olddivi);
				Logging.debug(" NEW DIVISOR :" + newdivisor);
				PreparedStatement  stmt = con.prepareStatement(query12);
				stmt.setDouble(1,newdivisor);
				stmt.setDouble(2,newtmcv);
				stmt.setString(3,indval_id);
				stmt.executeUpdate();
				Logging.info("updated");
				rs1.close();
			 }catch(Exception e){
			 	Logging.error("Exception in second updation" + e.getMessage());
			 }
			 
		}
	
	}
	public static void hist_update_Divisor(Connection con,String query11,String query12,Hashtable hash,FixedIncomeCorporate corporateact){
		String date=corporateact.getApplied_date();//Applied_date();
		Connect connect=ConnectInit.getConnect();
		for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
		{
			 String id =(String)enmm.nextElement();
			 try{		 	
			 	ResultSet rs1 = FixedIncomeListTypeClass1.getResult_apply(con,query11,id,date);
			 	rs1.next();
				double oldtmcv=rs1.getDouble("tmcv");
				Logging.debug("IN UPDATION CLASS old tmcv:" + oldtmcv);
				double olddivi=rs1.getDouble("divisor");
				String indval_id=rs1.getString("index_value_daily_id");
				Logging.debug("IN UPDATION CLASS old divi:" + olddivi);
				// get currency exchange value
				FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,id,corporateact.getStid());
	//			CFormula cf = new CFormula();
				CFormula cf = ConnectInit.getCFormula();		
				double oldmcv=  cf.calMarketCap(Double.parseDouble(corporateact.getOldltp()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getOldtis()),Double.parseDouble(corporateact.getIwfstk()));
				double newmcv= cf.calMarketCap(Double.parseDouble(corporateact.getNewLTP()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getNewTIS()),Double.parseDouble(corporateact.getIwfstk()));						
				corporateact.setNewmcv(newmcv);	
				
				
				//corporateact.setNewmcv(newmcv);
				
				double newtmcv=((oldtmcv -oldmcv)+(newmcv));
				double diff=cf.diffTMCV(oldtmcv,newtmcv);
				
				double newdivisor=cf.newDivisorCorp(oldtmcv,diff,olddivi);
				Logging.debug(" NEW DIVISOR :" + newdivisor);
				PreparedStatement  stmt = con.prepareStatement(query12);			
				stmt.setDouble(1,newdivisor);
				stmt.setDouble(2,newtmcv);
				stmt.setString(3,indval_id);
				stmt.executeUpdate();
				Logging.info("updated");
				rs1.close();
			 }catch(Exception e){
			 	Logging.error("Exception in second updation" + e.getMessage());
			 }
			 
		}
	
	}
	public static void update_Divisor(Connection con,String query11,String query12,Hashtable hash,FixedIncomeCorporate corporateact){
		String date=corporateact.getApplied_date();//Applied_date();
		Connect connect=ConnectInit.getConnect();
		for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
		{
			 String id =(String)enmm.nextElement();
			Logging.debug(" Value of Stock " + corporateact.getStkid());			
			 Logging.debug("INDEX ID :" + id);			 
			 try{
			 	
			 	ResultSet rs1 = FixedIncomeListTypeClass1.getResult_apply(con,query11,id,date);
			 	if(rs1.next()){
				double oldtmcv=rs1.getDouble(1);
				Logging.debug("IN UPDATION CLASS old tmcv:" + oldtmcv);
				double olddivi=rs1.getDouble(2);
				Logging.debug("IN UPDATION CLASS old divi:" + olddivi);
				
				// get currency exchange value
				FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,id,corporateact.getStid());
	//			CFormula cf = new CFormula();
				CFormula cf = ConnectInit.getCFormula();		
				double oldmcv=  cf.calMarketCap(Double.parseDouble(corporateact.getOldltp()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getOldtis()),Double.parseDouble(corporateact.getIwfstk()));
				double newmcv= cf.calMarketCap(Double.parseDouble(corporateact.getNewLTP()),Long.parseLong(corporateact.getMark_lot()),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(corporateact.getNewTIS()),Double.parseDouble(corporateact.getIwfstk()));						
				corporateact.setNewmcv(newmcv);	
				
				
				//corporateact.setNewmcv(newmcv);
				
				double newtmcv=((oldtmcv -oldmcv)+(newmcv));
				double diff=cf.diffTMCV(oldtmcv,newtmcv);
				
				double newdivisor=cf.newDivisorCorp(oldtmcv,diff,olddivi);
				Logging.debug(" NEW DIVISOR :" + newdivisor);
				PreparedStatement  stmt = con.prepareStatement(query12);
				stmt.setDouble(1,newdivisor);
				stmt.setDouble(2,newtmcv);
				stmt.setLong(3,Long.parseLong(id));
				stmt.setString(4,date);
				stmt.executeUpdate();
				Logging.info("updated");
				rs1.close();
			 	}else{
			 		Logging.info("no data ");
			 	}

			 }catch(Exception e){
			 	Logging.error("Exception in second updation" + e.getMessage());
			 }
			 
		}
	
	}
	public static void recal_assign_index_detail(Connection con,Connect connect,FixedIncomeCorporate corporateact)
	{
		try{
		//	CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			String query1=ConnectInit.queries.getProperty("select_rep_cad");
			String update_cad=ConnectInit.queries.getProperty("update_index_cad_with_time");
			String corp_query =ConnectInit.queries.getProperty("get_corporate_list_index");
			String insert_cad=ConnectInit.queries.getProperty("insert_index_cad");
			String query3=ConnectInit.queries.getProperty("select_stock_detail");
			String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");			
			String index_comp=ConnectInit.queries.getProperty("update_index");
			String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");
			String delete=ConnectInit.queries.getProperty("delete_index_comp");
			String index_his=ConnectInit.queries.getProperty("insert_index_com_his");			

	//as this method is used both for historic and general CA, there should be date comparison
		 	String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date

			Hashtable hash=corporateact.getHash2();
			for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
			{		
				
				 String id =(String)enmm.nextElement();
				 String div[]=ActionCorp.token(id);
				 String stock=null;
				 String val=null;
				 val=corporateact.getValues();
				 if(div[0].equals("true"))
				 {
				 	corporateact.setCad_id(div[1]);
				 	ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query1,div[1]);
				 	rs.next();
					corporateact.setAnnounce_date(rs.getString("announce_date"));
				 	corporateact.setEx_date(rs.getString("ex_date"));
				 	corporateact.setRecord_date(rs.getString("record_date"));
					corporateact.setApply_date(rs.getString("apply_on_date"));
					String date=corporateact.getApply_date();//accept_date();
					corporateact.setApplied_date(date);
					corporateact.setStatus("y");
					String corp_value=corporateact.getCorpid();
					corporateact.setStid(rs.getString("stock_id"));
					stock=rs.getString("stock_id");
					if(corp_value.equals("changeiwf"))
					{
						corporateact.setValues(corporateact.getValues());
					}
					if((corp_value.equals("addstock"))|(corp_value.equals("deletestock")))
						corporateact.setValues(null);
					corporateact.setCorpid(rs.getString("cam_id"));
					update_index_cad_with_time(con,update_cad,corporateact);
					corporateact.setValues(val);					
					
					corporateact.setCorpid(corp_value);
				 }//if true
				 if(div[0].equals("false"))
				 {
				 	stock=div[1];
				 	String date=corporateact.getApply_date();//UpdateCorp.accept_date();
				 	corporateact.setAnnounce_date(date);
				 	corporateact.setEx_date(date);
				 	corporateact.setRecord_date(date);
				 	corporateact.setApply_date(date);
				 	corporateact.setApplied_date(date);
				 	corporateact.setStatus("y");
				 	corporateact.setBc_start(null);
				 	corporateact.setBc_end(null);
				 	corporateact.setNc_start(null);
				 	corporateact.setNc_end(null);
				 	corporateact.setStid(div[1]);
				 	
				 	String corp=corporateact.getCorpid();
					val=corporateact.getValues();
					if((corp.equals("addstock"))|(corp.equals("deletestock")))
						corporateact.setValues(null);
					if(corp.equals("changeiwf"))
						corporateact.setValues(val);
				 	ResultSet rs2 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
				 	FixedIncomeListTypeClass1.check_corp_name(rs2,corp,corporateact);						    
					if(rs2!=null)
						rs2.close();	
					String nextval=ConnectInit.queries.getProperty("get_sequence_cad_id");
					insert_index_cad(con,insert_cad,nextval,corporateact);
					corporateact.setCorpid(corp);
					corporateact.setValues(val);
				 }//false if 
				 String corpid=corporateact.getCorpid();
				 ResultSet rs2=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
				 String tis=null;
				 String ml=null;
				 if(rs2!=null && rs2.next()){
				 	tis=rs2.getString("tis");
				 	ml=rs2.getString("market_lot");
				 	if(corpid.equals("addstock"))
				 	{
				 		corporateact.setValues(rs2.getString("iwf"));
				 	}
				 	if(corpid.equals("deletestock"))
					{
						ResultSet rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
					    if(rs!=null && rs.next()){
					    	corporateact.setValues(rs.getString("iwf"));
					    	rs.close();
					    }
					    	
					 }				 
					 rs2.close();	
				  }
				 String value=corporateact.getValues();		
				 Hashtable hash_error=corporateact.getHash_error();
				 boolean chk_hash=hash_error.containsKey(stock);
				 if(chk_hash==false)
				 {
				 	ResultSet rs1=null;
				 	if(chk_dt==0)
				 	{
					 	String stock_price=ConnectInit.queries.getProperty("select_stock_price_detail");
					 	rs1=FixedIncomeListTypeClass1.getResult12(con,stock_price,stock);
				 	}
				 	else
				 	{
				 		String stock_price=ConnectInit.queries.getProperty("get_undo_close_value");//get stock closing value for respective date
				 		rs1=FixedIncomeListTypeClass1.getResult_apply(con,stock_price,stock,corporateact.getApply_date());
				 	}
					boolean rs_cond=rs1.next();
					String close=null;
					if(rs_cond==true)
					{
							close=rs1.getString("adjusted_price");
							if(close==null)
								close=rs1.getString("stock_closing_value");
					}
					//get currency exchange value
					FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),stock);
					
					double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));						
					corporateact.setNewmcv(mcv);
					
					if(corpid.equals("changeiwf"))
			 			update_index_comp(con,index_comp,corporateact);
					if(corpid.equals("addstock"))
						insert_index_comp(con,insert_index_comp,corporateact);
					if(corpid.equals("deletestock"))
					{
						try
						{
						PreparedStatement stmt = con.prepareStatement(delete);
						stmt.setString(1,corporateact.getI_index());
						stmt.setString(2,corporateact.getStid());
						stmt.executeQuery();
						}catch(Exception e){
							Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
						}
					}
					insert_index_comp_his(con,index_his,corporateact);
				 }
				 
			}
			if(chk_dt==0)
			{
				String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");
				update_index_daily(con,ind_val_daily,corporateact);
			}
			else
			{
				String qry=ConnectInit.queries.getProperty("get_undo_index_close");
				String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
				update_undo_index_daily(con,qry,ind_val_daily,corporateact);
			}			
		}catch(Exception e){
			Logging.error("error on update =="+e.getMessage());
		}
	}
	public static void undocurr_ind(FixedIncomeCorporate corp,int flag)
	{
		try{
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}
			if(flag==0)  // for parent index
			{
				String query1=ConnectInit.queries.getProperty("select_rep_cad");
				String corp_value=corp.getCorpid();
				String date=corp.getApplied_date();
				
				Hashtable hash=corp.getHash2();
				Logging.debug("in update==commit action="+hash);
				for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
				{		
					String id =(String)enmm.nextElement();
					String div[]=ActionCorp.token(id);
					corp.setCad_id(div[1]);
				 	ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query1,div[1]);
				 	rs.next();
				 	String apply=rs.getString("apply_on_date");
				 	if(apply==null)
				 		apply=corp.getApplied_date();//accept_date();
					corp.setApply_date(apply);					
					corp.setApplied_date(null);
					corp.setStatus("n");
					String corp_nm=rs.getString("cam_id");
					corp.setCorpid(corp_nm);
					corp.setI_index(rs.getString("index_id"));
					//update diary
					String qry=ConnectInit.queries.getProperty("undo_curr_ind_cad");
					update_undo_curr_index(con,qry,corp);
				}//for end
				corp.setCorpid(corp_value);
				corp.setApplied_date(date);
			}//flag
			
			int oldcur=0;
			int newcur=0;
			String query=ConnectInit.queries.getProperty("resp_ftcurr");
			ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,corp.getFtcurrency());
			rs.next();
			int fcurr=rs.getInt("from_currency_id");
			int tcurr=rs.getInt("to_currency_id");
			if(rs!=null)
				rs.close();
			int curid=Integer.parseInt(corp.getCurrencyid());
			if(curid==fcurr)
			{
				oldcur=fcurr;
				newcur=tcurr;				
			}
			if(curid==tcurr)
			{
				oldcur=tcurr;
				newcur=fcurr;
			}
			corp.setValues(Integer.toString(newcur));
			//Update Index Master for parent index
			query=null;
			query=ConnectInit.queries.getProperty("update_indmaster");
			update_curr_indmaster(con,query,corp);			//done upto this on 8th august sonali

			//Update Index value daily
			String qry=ConnectInit.queries.getProperty("get_undo_index_close");
			String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
			if(flag==0) //for parent index
			{
				update_undo_index_daily(con,qry,ind_val_daily,corp);
			}
			else   //for child index
			{
				ActionCorp.cal_curr_ind(con,connect,corp,corp.getI_index());
				update_undo_index_daily(con,qry,ind_val_daily,corp);
			}
			//update index composition
			update_undo_curr_indcomp(corp);		
			
			//delete from index master history
			String del_qry=ConnectInit.queries.getProperty("delete_indmas_history");
			corp.setEvent_id(corp.getCad_id());
			delete_event(con,del_qry,corp);
			
		}catch(Exception e){
			Logging.debug("Error="+e.getMessage());
			}
	}
	public static void undoassign_index_detail(FixedIncomeCorporate corporateact)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
		if(con == null){
			 con = connect.getdbConnection();
		}
		
		String query1=ConnectInit.queries.getProperty("select_rep_cad");
		String stock_price=ConnectInit.queries.getProperty("get_undo_close_value");
		String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
		String update_cad=ConnectInit.queries.getProperty("update_index_cad_with_time");
		String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");
    	String delete=ConnectInit.queries.getProperty("delete_index_comp");
    	String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");
    	String index_comp=ConnectInit.queries.getProperty("update_index");

    	String corpnm=corporateact.getCorpid();
		try{							
			boolean affect_val=corporateact.isCheck();
			Hashtable hash=corporateact.getHash2();
			boolean rs_cond=false;
			String date=corporateact.getApplied_date();
	//		CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
				{		
					String id =(String)enmm.nextElement();
					 String div[]=ActionCorp.token(id);
					 String close=null;
					 String tis=null,ml=null;
					 String stock=null;
					 String value_count=corporateact.getCoun();
					corporateact.setCad_id(div[1]);
				 	ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query1,div[1]);
				 	rs.next();
					corporateact.setAnnounce_date(rs.getString("announce_date"));
				 	corporateact.setEx_date(rs.getString("ex_date"));
				 	corporateact.setRecord_date(rs.getString("record_date"));
				 	String apply=rs.getString("apply_on_date");
				 	if(apply==null)
				 		apply=corporateact.getApplied_date();//accept_date();
					corporateact.setApply_date(apply);					
					corporateact.setApplied_date(null);
					corporateact.setStatus("n");
					String corp_value=corporateact.getCorpid();
					corporateact.setStid(rs.getString("stock_id"));
					stock=rs.getString("stock_id");					
					String corp=rs.getString("cam_id");
					corporateact.setCorpid(corp);
					
					ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(con,stock_price,stock,date);			
					rs_cond=rs1.next();
					if(rs_cond==true)
					{
						close=rs1.getString("adjusted_price");
						if(close==null)
							close=rs1.getString("stock_closing_value");
						if(close!=null)
						{
							rs1.close();
							rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
							rs1.next();
							tis=rs1.getString("tis");
							ml=rs1.getString("market_lot");
							rs1.close();
							if(corp_value.equals("changeiwf"))
							{
								corporateact.setValues(value_count);
							}
							String val=corporateact.getValues();
							if((corp_value.equals("addstock"))|(corp_value.equals("deletestock")))
								corporateact.setValues(null);

							update_index_cad_with_time(con,update_cad,corporateact);							
							corporateact.setCorpid(corp_value);
							corporateact.setApplied_date(date);							
							
							corporateact.setValues(val);
							String corp_val=corporateact.getCorpid();
							if(corp_val.equals("addstock"))
							{
								rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
							    if(rs!=null && rs.next())
							    	corporateact.setValues(rs.getString("iwf"));
							}
							if(corp_val.equals("deletestock"))
							{
								rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
								if(rs1!=null && rs1.next())
									corporateact.setValues(rs1.getString("iwf"));
							}	
							 String value=corporateact.getValues();
							 //get currency exchange value
							 FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),stock);
							 
							 double mcv=0.0;	
							 if(corp_val!=null)
							 {
							 	if(corp_val.equals("addstock"))
							 	{
							 		mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));							 		
									corporateact.setNewmcv(mcv);
									try
									{
									PreparedStatement stmt = con.prepareStatement(delete);
									stmt.setString(1,corporateact.getI_index());
									stmt.setString(2,corporateact.getStid());
									stmt.executeQuery();
									}catch(Exception e){
										Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
									}		
							 	}
							 	if(corp_val.equals("deletestock"))
							 	{
							 		mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));							 		
									corporateact.setNewmcv(mcv);
									insert_index_comp(con,insert_index_comp,corporateact);
							 	}
							 	if(corp_val.equals("changeiwf"))
						 		{
						 			rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
								    rs.next();
								    val=rs.getString("iwf");
								    if(rs!=null)
								    	rs.close();
						 			double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));						 			
						 			double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));						 			
						 			mcv=mcv_new-mcv_old;
						 			corporateact.setNewmcv(mcv);
						 			update_index_comp(con,index_comp,corporateact);
						 		}
							 }
						}//if close						
					}//rs cond
					corporateact.setEvent_id(corporateact.getCad_id());
					String query=ConnectInit.queries.getProperty("delete_indxcomp_history");
					delete_event(con,query,corporateact);
					corporateact.setCorpid(corp_value);
				}//for hash
				corporateact.setApplied_date(date);
				String rad_val=corporateact.getInd_comp();
				String qry=null;
				if(rad_val!=null)
				{						
					qry=ConnectInit.queries.getProperty("get_undo_index_close");					
				}
				else	
					qry=ConnectInit.queries.getProperty("select_resp_close");
				
				String query =ConnectInit.queries.getProperty("update_undo_ind_close");
				undo_update_index_daily(con,qry,query,corporateact);
				
		}catch(Exception e){
			Logging.error("error on update=="+e.getMessage());
		}
		//corporateact.setCorpid(corpnm);
	}
	public static void assign_index_detail(FixedIncomeCorporate corporateact)
	{		
		try
		{	
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}	
			
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
			String query1=ConnectInit.queries.getProperty("select_rep_cad");
			//String update_cad=ConnectInit.queries.getProperty("update_index_cad");
			String update_cad=ConnectInit.queries.getProperty("update_index_cad_with_time");
			String insert_cad=ConnectInit.queries.getProperty("insert_index_cad");                								
			String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");				
        	String index_comp=ConnectInit.queries.getProperty("update_index");								
			String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");			        	
        	String delete=ConnectInit.queries.getProperty("delete_index_comp");		        							
        	String index_his=ConnectInit.queries.getProperty("insert_index_com_his");			        	
       		String stock_price=ConnectInit.queries.getProperty("select_stock_price_detail");			        	
	        String query3=ConnectInit.queries.getProperty("select_stock_detail");		
			String index_detail=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");//"select_index_detail");			
	        String select_corp=ConnectInit.queries.getProperty("select_corp_name");				        			       						    
			String corp_query =ConnectInit.queries.getProperty("get_corporate_list_index");				        
			String get_cam=ConnectInit.queries.getProperty("select_from_cam");
			String select_is_cad=ConnectInit.queries.getProperty("select_index_stock_cad");
			String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");
						
			Hashtable hash=corporateact.getHash2();
			boolean rs_cond=false;			
			boolean affect_val=corporateact.isCheck();
	//		CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			if(affect_val==true)
			{				
				for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
				{	
					
					 String id =(String)enmm.nextElement();
					 String div[]=ActionCorp.token(id);
					 String close=null;
					 String tis=null,ml=null;
					 String stock=null;
					 String value_count=corporateact.getCoun();
					 if(div[0].equals("true"))
					 {
					 	corporateact.setCad_id(div[1]);
					 	ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query1,div[1]);
					 		rs.next();    
					 		corporateact.setAnnounce_date(rs.getString("announce_date"));
					 		corporateact.setEx_date(rs.getString("ex_date"));
					 		corporateact.setRecord_date(rs.getString("record_date"));
					 		corporateact.setApply_date(rs.getString("apply_on_date"));
					 		String date=accept_date();
					 		corporateact.setApplied_date(date);
					 		corporateact.setStatus("y");
					 		//corporateact.setCorpid(rs.getString("cam_id"));
					 		String corp_value=corporateact.getCorpid();
					 		corporateact.setStid(rs.getString("stock_id"));
					 		stock=rs.getString("stock_id");					
					 		int corp=rs.getInt("cam_id");
					 		ResultSet rs1=FixedIncomeListTypeClass1.getResult12(con,stock_price,stock);			
					 		rs_cond=rs1.next();
					 		if(rs_cond==true)
					 		{
					 			close=rs1.getString("adjusted_price");
					 			if(close==null)
					 				close=rs1.getString("stock_closing_value");							
					 			rs1.close();
					 		
					 			rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
					 			rs1.next();
					 			tis=rs1.getString("tis");
					 			ml=rs1.getString("market_lot");
					 			rs1.close();
					 			if(corp_value.equals("changeiwf"))
					 			{
					 				String nature=corporateact.getNature();
					 				if(nature!=null)
					 				{
					 					if(nature.equals("n"))
					 					{
					 						corporateact.setValues(value_count);
					 					}
					 					else
					 					{
					 						corporateact.setValues(rs.getString("values"));
					 					}
					 				}		
					 				else
									corporateact.setValues(rs.getString("values"));
							}
							String corp_val=corporateact.getCorpid();
							String val=corporateact.getValues();
							
							if((corp_val.equals("addstock"))|(corp_val.equals("deletestock")))
								corporateact.setValues(null);
							
							ResultSet rs2 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
							FixedIncomeListTypeClass1.check_corp_name(rs2,corp_val,corporateact);						    
							rs2.close();
							String corpid=corporateact.getCorpid();
							update_index_cad_with_time(con,update_cad,corporateact);
							corporateact.setCorpid(corp_val);
							corporateact.setValues(val);
						}
					 }				 
					 if(div[0].equals("false"))
					 {
					 	String date=accept_date();
					 	corporateact.setAnnounce_date(date);
					 	corporateact.setEx_date(date);
					 	corporateact.setRecord_date(date);
					 	//corporateact.setApply_date(null);
					 	corporateact.setApplied_date(date);
					 	corporateact.setStatus("y");
					 	corporateact.setBc_start(null);
					 	corporateact.setBc_end(null);
					 	corporateact.setNc_start(null);
					 	corporateact.setNc_end(null);
					 	corporateact.setStid(div[1]);
					 	corporateact.setValues(value_count);
					 	stock=div[1];
					 	ResultSet rs1=FixedIncomeListTypeClass1.getResult12(con,stock_price,stock);			
						rs_cond=rs1.next();
						if(rs_cond==true)
						{
							close=rs1.getString("adjusted_price");
							if(close==null)
								close=rs1.getString("stock_closing_value");							
							rs1.close();
							rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
							if(rs1!=null && rs1.next()){
								tis=rs1.getString("tis");
								ml=rs1.getString("market_lot");
								rs1.close();
							}
						}
						if(rs_cond==true)
						{
							String corp=corporateact.getCorpid();
							String val=corporateact.getValues();							
							if((corp.equals("addstock"))|(corp.equals("deletestock")))
								corporateact.setValues(null);
							ResultSet rs2 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
							FixedIncomeListTypeClass1.check_corp_name(rs2,corp,corporateact);						    
							if(rs2!=null)
								rs2.close();	
							String nextval=ConnectInit.queries.getProperty("get_sequence_cad_id");
							insert_index_cad(con,insert_cad,nextval,corporateact);
							corporateact.setCorpid(corp);
							corporateact.setValues(val);
						}
					 }
					 String corpid=corporateact.getCorpid();
					 if(corpid.equals("addstock"))
					 {
					 	ResultSet rs2=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
						 rs2.next();
						 corporateact.setValues(rs2.getString("iwf"));
					 }
					 if(corpid.equals("deletestock"))
						{
							ResultSet rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
						    if(rs!=null && 	rs.next())
							corporateact.setValues(rs.getString("iwf"));
						}	
					 
					 String value=corporateact.getValues();
					 if(rs_cond==true)
					 {
					 	double mcv=0.0;					 	
					 	// get currency exchange value
					 	FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),stock);
					 	if(corpid!=null)
					 	{
					 		if(corpid.equals("changeiwf"))
					 		{
					 			ResultSet rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
					 			String val=null;
					 			if(rs!=null && rs.next()){
					 				val=rs.getString("iwf");
							     	rs.close();
					 			}
					 			double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));					 			
					 			double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));					 			
					 			mcv=mcv_new-mcv_old;
					 			corporateact.setNewmcv(mcv);
					 			update_index_comp(con,index_comp,corporateact);
					 		}
							if(corpid.equals("addstock"))
							{
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));								
								corporateact.setNewmcv(mcv);
								insert_index_comp(con,insert_index_comp,corporateact);
							}
							if(corpid.equals("deletestock"))
							{
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));								
								corporateact.setNewmcv(mcv);
								try
								{
								PreparedStatement stmt = con.prepareStatement(delete);
								stmt.setString(1,corporateact.getI_index());
								stmt.setString(2,corporateact.getStid());
								//stmt.executeQuery(); //commented by sonali 05-08-2006
								stmt.execute();
								//added by sonali 
								}catch(Exception e){
									Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
								}
							}						
						}				 
					insert_index_comp_his(con,index_his,corporateact);	
					 }
					 
				}
				update_index_daily(con,ind_val_daily,corporateact);			
			}
		}
		catch(Exception e)
		{
			Logging.error("Exception in second updation" + e.getMessage());
		}		
	}
	public static void hist_assign_index_detail(FixedIncomeCorporate corporateact)
	{
		try
		{	
			Connect connect = ConnectInit.getConnect();
			Connection Hist_con=connect.getConnectionForHistTransaction();
			Hist_con.commit();
			//con.close();
			
			connect = ConnectInit.getConnect();
			Connection con=connect.getdbConnection();	
			
			//update parent index
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
			String query1=ConnectInit.queries.getProperty("select_rep_cad");
			String update_cad=ConnectInit.queries.getProperty("update_index_cad_with_time");
			String insert_cad=ConnectInit.queries.getProperty("insert_index_cad");			
        	String index_comp=ConnectInit.queries.getProperty("update_index");								
			String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");			        	
        	String delete=ConnectInit.queries.getProperty("delete_index_comp");		        							
        	String index_his=ConnectInit.queries.getProperty("insert_index_com_his");
        	String stock_price=ConnectInit.queries.getProperty("get_undo_close_value");//get stock closing value for respective date       					        	
	        String query3=ConnectInit.queries.getProperty("select_stock_detail");						
	        String select_corp=ConnectInit.queries.getProperty("select_corp_name");				        			       						    
			String corp_query =ConnectInit.queries.getProperty("get_corporate_list_index");				        
			String get_cam=ConnectInit.queries.getProperty("select_from_cam");
			String select_is_cad=ConnectInit.queries.getProperty("select_index_stock_cad");
			String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");
						
			Hashtable hash=corporateact.getHash2();
			boolean rs_cond=false;			
			boolean affect_val=corporateact.isCheck();			
		//	CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
				{						
					 String id =(String)enmm.nextElement();
					 String div[]=ActionCorp.token(id);
					 String close=null;
					 String tis=null,ml=null;
					 String stock=null;
					 String value_count=corporateact.getCoun();
					 if(div[0].equals("true"))
					 {
					 	corporateact.setCad_id(div[1]);
					 	ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query1,div[1]);
					 	rs.next();
						corporateact.setAnnounce_date(rs.getString("announce_date"));
					 	corporateact.setEx_date(rs.getString("ex_date"));
					 	corporateact.setRecord_date(rs.getString("record_date"));
						corporateact.setApply_date(rs.getString("apply_on_date"));						
						corporateact.setApplied_date(rs.getString("apply_on_date"));
						corporateact.setStatus("y");
						String corp_value=corporateact.getCorpid();
						corporateact.setStid(rs.getString("stock_id"));
						stock=rs.getString("stock_id");					
						int corp=rs.getInt("cam_id");
						ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(con,stock_price,stock,corporateact.getApply_date());			
						rs_cond=rs1.next();
						if(rs_cond==true)
						{
							close=rs1.getString("adjusted_price");
							if(close==null)
								close=rs1.getString("stock_closing_value");							
							rs1.close();
							rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
							rs1.next();
							tis=rs1.getString("tis");
							ml=rs1.getString("market_lot");
							rs1.close();
							if(corp_value.equals("changeiwf"))
							{
								String nature=corporateact.getNature();
								if(nature!=null)
								{
									if(nature.equals("n"))
									{
										corporateact.setValues(value_count);
									}
									else
									{
										corporateact.setValues(rs.getString("values"));
									}
								}		
								else
									corporateact.setValues(rs.getString("values"));
							}
							String corp_val=corporateact.getCorpid();
							String val=corporateact.getValues();
							
							if((corp_val.equals("addstock"))|(corp_val.equals("deletestock")))
								corporateact.setValues(null);
							
							ResultSet rs2 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
							FixedIncomeListTypeClass1.check_corp_name(rs2,corp_val,corporateact);						    
							rs2.close();
							String corpid=corporateact.getCorpid();
							update_index_cad_with_time(con,update_cad,corporateact);
							corporateact.setCorpid(corp_val);
							corporateact.setValues(val);
						}
					 }				 
					 if(div[0].equals("false"))
					 {
					 	String date=corporateact.getApply_date();
					 	corporateact.setAnnounce_date(date);
					 	corporateact.setEx_date(date);
					 	corporateact.setRecord_date(date);
					 	corporateact.setApply_date(date);
					 	corporateact.setApplied_date(date);
					 	corporateact.setStatus("y");
					 	corporateact.setBc_start(null);
					 	corporateact.setBc_end(null);
					 	corporateact.setNc_start(null);
					 	corporateact.setNc_end(null);
					 	corporateact.setStid(div[1]);
					 	corporateact.setValues(value_count);
					 	stock=div[1];
					 	ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(con,stock_price,stock,date);			
						rs_cond=rs1.next();
						if(rs_cond==true)
						{
							close=rs1.getString("adjusted_price");
							if(close==null)
								close=rs1.getString("stock_closing_value");							
							rs1.close();
							rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
							rs1.next();
							tis=rs1.getString("tis");
							ml=rs1.getString("market_lot");
							rs1.close();
						}
						if(rs_cond==true)
						{
							String corp=corporateact.getCorpid();
							String val=corporateact.getValues();							
							if((corp.equals("addstock"))|(corp.equals("deletestock")))
								corporateact.setValues(null);
							ResultSet rs2 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
							FixedIncomeListTypeClass1.check_corp_name(rs2,corp,corporateact);						    
							rs2.close();
							String nextval=ConnectInit.queries.getProperty("get_sequence_cad_id");
							insert_index_cad(con,insert_cad,nextval,corporateact);
							corporateact.setCorpid(corp);
							corporateact.setValues(val);
						}
						corporateact.setApply_date(date);
					 }
					 String corpid=corporateact.getCorpid();
					 if(corpid.equals("addstock"))
					 {
					 	ResultSet rs2=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
						 rs2.next();
						 corporateact.setValues(rs2.getString("iwf"));
					 }
					 if(corpid.equals("deletestock"))
						{
							ResultSet rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
						    rs.next();
							corporateact.setValues(rs.getString("iwf"));
						}	
					 
					 String value=corporateact.getValues();
					 if(rs_cond==true)
					 {
					 	//get currency exchange value
					 	FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),stock);
					 	
					 	double mcv=0.0;					 	
					 	if(corpid!=null)
					 	{
					 		if(corpid.equals("changeiwf"))
					 		{
					 			ResultSet rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
							    rs.next();
							    String val=rs.getString("iwf");
							    if(rs!=null)
							    	rs.close();
					 			double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));					 			
					 			double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));					 			
					 			mcv=mcv_new-mcv_old;
					 			corporateact.setNewmcv(mcv);
					 			update_index_comp(con,index_comp,corporateact);
					 		}
							if(corpid.equals("addstock"))
							{
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));								
								corporateact.setNewmcv(mcv);
								insert_index_comp(con,insert_index_comp,corporateact);
							}
							if(corpid.equals("deletestock"))
							{
								mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));								
								corporateact.setNewmcv(mcv);
								try
								{
								PreparedStatement stmt = con.prepareStatement(delete);
								stmt.setString(1,corporateact.getI_index());
								stmt.setString(2,corporateact.getStid());
								stmt.executeQuery();
								}catch(Exception e){
									Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
								}
							}						
						}				 
					insert_index_comp_his(con,index_his,corporateact);	
					 }
				}				
				//update specific index value daily
				String qry=ConnectInit.queries.getProperty("get_undo_index_close");
				String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
				update_undo_index_daily(con,qry,ind_val_daily,corporateact);								
		
		//update child indices
		UpdateCorp.update_affect_index(con,connect,corporateact); //update child indices					
		}catch(Exception e)
		{
			Logging.error("Exception in second updation" + e.getMessage());
		}		
	}
	public static void assign_index_cad(Connection con,Connect connect,FixedIncomeCorporate corporateact)	
	{
		int val=0;		
		try
		{			
			String query=ConnectInit.queries.getProperty("update_index_cad_with_time");        	
        	String index_comp=ConnectInit.queries.getProperty("update_index");
        	String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");
        	String query1=ConnectInit.queries.getProperty("select_rep_cad");
        	String qry_detail=ConnectInit.queries.getProperty("select_stock_price_detail");
        	String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");
        	String index_comp_his=ConnectInit.queries.getProperty("insert_index_com_his");
        	String index_comp_detail=ConnectInit.queries.getProperty("index_comp_detail");
        	String delete_index_comp=ConnectInit.queries.getProperty("delete_index_comp");
			String get_name=ConnectInit.queries.getProperty("get_corp_name");							        
			String corp_query =ConnectInit.queries.getProperty("get_corporate_list_index");		
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");			
			
//as this method is used both for historic and general CA , so there should be date comparison
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date

				Hashtable data=corporateact.getHash();
				Hashtable data_error=corporateact.getHash_error();
				Hashtable data2=corporateact.getHash12();				
				val=0;
		//		CFormula cf=new CFormula();
				CFormula cf = ConnectInit.getCFormula();
				for(Enumeration enmm =data.keys();enmm.hasMoreElements();)
				{
					String tis=null,iwf=null,close=null,ml=null;
					 String sid =(String)enmm.nextElement();
					 Object obj=data.get(sid);
					 corporateact.setCad_id(obj.toString());
					 ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query1,obj.toString());
					 rs.next();					 	
					 for(Enumeration enum1 =data_error.keys();enum1.hasMoreElements();)
					 {
					 	String id1 =(String)enum1.nextElement();
					 	if(sid.equals(id1))
					 	{
					 		val=1;
					 		break;
					 	}
					 }
					 if(val==0)
					 {
					 	corporateact.reset3();
					 	corporateact.setStid(sid);
					 	corporateact.setAnnounce_date(rs.getString("announce_date"));
					 	corporateact.setEx_date(rs.getString("ex_date"));
					 	corporateact.setRecord_date(rs.getString("record_date"));
						corporateact.setApply_date(rs.getString("apply_on_date"));
						String date=null;
						if(chk_dt==0)
							date=accept_date();
						else
							date=corporateact.getApply_date();
						corporateact.setApplied_date(date);
						corporateact.setStatus("y");						
						ResultSet rs3=FixedIncomeListTypeClass1.getAffected(con,get_name,rs.getString("cam_id"));
						rs3.next();
						String corp_name=rs3.getString("cam_shortname");
						rs3.close();
						String div[]=ActionCorp.token2(corp_name);
						corp_name=(div[0]+div[1]).toLowerCase().trim();
						corporateact.setCorpid(corp_name);					
						
						
						obj=null;
						int size=data2.size();
						if(size!=0)
						{
							for(Enumeration enm=data2.keys();enm.hasMoreElements();)
							{
								String id_val=(String)enm.nextElement();
								if(id_val.equals(sid))
								{
									obj = data2.get(id_val);
									break;
								}
								else
									obj="0";								
							}					
						iwf=obj.toString();
						corporateact.setValues(iwf);
						 ResultSet rs1=null;
						 if(chk_dt==0)
						 	rs1=FixedIncomeListTypeClass1.getResult12(con,qry_detail,sid);
						 else
						 	rs1=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,sid,corporateact.getApply_date());
						 rs1.next();
						 close=rs1.getString("adjusted_price");
						 if(close==null)
						 	close=rs1.getString("stock_closing_value");
						 rs1.close();
						 rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
						 rs1.next();
						  tis=rs1.getString("tis");
						  ml=rs1.getString("market_lot");
						 rs1.close();

						}
						//get currency exchange value
						FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),sid);
						
						String corp=corporateact.getCorpid();
						if(corp.equals("addstock"))
						{
							double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));							
							 corporateact.setNewmcv(mcv);
							Logging.debug("--------- in add ------------");
							String iwf_val=corporateact.getValues();														
							corporateact.setValues(null);
							String corpval=corporateact.getCorpid();									
							ResultSet rs2 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
							FixedIncomeListTypeClass1.check_corp_name(rs2,corpval,corporateact);						    
							rs2.close();			
							String corpid=corporateact.getCorpid();									
							corporateact.setValues(iwf_val);
							insert_index_comp(con,insert_index_comp,corporateact);													
						}
						if(corp.equals("deletestock"))
						{							
							Logging.debug("--------- in del ------------");
							corporateact.setValues(null);
							 ResultSet rs1=null;
							 if(chk_dt==0)
							 	rs1=FixedIncomeListTypeClass1.getResult12(con,qry_detail,corporateact.getStid());
							 else
							 	rs1=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,corporateact.getStid(),corporateact.getApply_date());
							 rs1.next();							
							close=rs1.getString("adjusted_price");
							if(close==null)
								close=rs.getString("stock_closing_value");
							 rs1.close();
							 rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,corporateact.getStid());
							 rs1.next();
							 tis=rs1.getString("tis");
							 ml=rs1.getString("market_lot");
							 rs1.close();
							 String corpval=corporateact.getCorpid();									
							ResultSet rs4 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
							FixedIncomeListTypeClass1.check_corp_name(rs4,corpval,corporateact);						    
							rs4.close();			
							String corpid=corporateact.getCorpid();
							  ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(con,index_comp_detail,corporateact.getI_index(),corporateact.getStid());
							 rs2.next();
							 corporateact.setValues(rs2.getString("iwf"));
							 double mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(rs2.getString("iwf")));							 
							 corporateact.setNewmcv(mcv);
							 try
							 {
							PreparedStatement stmt = con.prepareStatement(delete_index_comp);
							stmt.setString(1,corporateact.getI_index());
							stmt.setString(2,corporateact.getStid());
							stmt.executeQuery();
							}catch(Exception e){
								Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
							}				
						}
						if(corp.equals("changeiwf"))
						{
							double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));							
							ResultSet rs2=FixedIncomeListTypeClass1.getResult_apply(con,index_comp_detail,corporateact.getI_index(),corporateact.getStid());
							 rs2.next();
							 String iwfval=rs2.getString("iwf");
							 rs2.close();
							double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwfval));							
							double mcv=mcv_new-mcv_old;
							corporateact.setNewmcv(mcv);
							Logging.debug("--------- in iwf ------------");
							String corpval=corporateact.getCorpid();
							rs2 = FixedIncomeListTypeClass1.resultCorporate(con,corp_query); 								    								    
							FixedIncomeListTypeClass1.check_corp_name(rs2,corpval,corporateact);						    
							rs2.close();			
							String corpid=corporateact.getCorpid();				
							update_index_comp(con,index_comp,corporateact);		
													
						}
						if(!(corp.equals("changeiwf")))
							corporateact.setValues(null);
						update_index_cad_with_time(con,query,corporateact);
						corporateact.setCorpid(corp);
						insert_index_comp_his(con,index_comp_his,corporateact);
					 }// val = 0
				}//for end			
				if(chk_dt==0)  //update latest index value daily
				{
					String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");
					update_index_daily(con,ind_val_daily,corporateact);
				}
				else  //update apply date index value 
				{
					String rad_val=corporateact.getInd_comp();
					if(rad_val==null)
					{
						String ind_val_daily=ConnectInit.queries.getProperty("update_resp_index_value_daily");
						update_resp_index_daily(con,ind_val_daily,corporateact);
					}
					else
					{
						String qry=ConnectInit.queries.getProperty("get_undo_index_close");
						String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
						update_undo_index_daily(con,qry,ind_val_daily,corporateact);
					}
				}
				Logging.info("Complete");
		}catch(Exception e)
		{
			Logging.error("Exception in second1 updation" + e.getMessage());
		}
	}

	public static void insert_index_comp(Connection con,String query,FixedIncomeCorporate corporateact)
	{
		try{
			Statement stmt1 = con.createStatement();
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getValues());
			String date=corporateact.getApply_date();
			if(date==null)
				date=accept_date();
			stmt.setString(2,date);
			stmt.setString(3,corporateact.getI_index());
			stmt.setString(4,corporateact.getStid());
			String mcv=Double.toString(corporateact.getNewmcv());
			stmt.setString(5,mcv);
			stmt.executeUpdate();
			Logging.info("com");			
		}catch(Exception e){
			Logging.error("Exception in second1 updation" + e.getMessage());
		}
	}
	
	public static void insert_index_mas_his(Connection con,String query,String query1,FixedIncomeCorporate corporateact)
	{		
		try{
			ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,corporateact.getI_index());
			rs.next();			
			PreparedStatement  stmt = con.prepareStatement(query1);			
			stmt.setInt(1,rs.getInt("index_id"));
			stmt.setString(2,rs.getString("index_name"));
			stmt.setString(3,rs.getString("creation_date"));
			stmt.setString(4,rs.getString("is_active"));
			stmt.setString(5,rs.getString("base_date"));
			stmt.setString(6,rs.getString("base_value"));			
			stmt.setString(7,rs.getString("is_captured"));
			stmt.setString(8,rs.getString("captured_from"));
			stmt.setString(9,rs.getString("m_start_time"));
			stmt.setString(10,rs.getString("n_stop_time"));
			stmt.setString(11,rs.getString("o_ric"));
			stmt.setString(12,rs.getString("is_child"));
			stmt.setString(13,rs.getString("parent_id"));
			stmt.setString(14,corporateact.getValues());
			stmt.setString(15,rs.getString("index_type_id"));
			stmt.setString(16,rs.getString("alert_percentage"));
			stmt.setString(17,rs.getString("rejection_percentage"));
			stmt.setString(18,rs.getString("industry_classification_code"));
			stmt.setString(19,rs.getString("growth_or_value"));
			stmt.setString(20,rs.getString("calculate_settlement_value"));
			stmt.setString(21,rs.getString("isin"));
			stmt.setString(22,rs.getString("is_customized"));
			stmt.setString(23,rs.getString("next_day"));
			stmt.setString(24,rs.getString("industry_classification_id"));
			String date=corporateact.getApply_date();//accept_date();
			stmt.setString(25,date);
			stmt.setString(26,corporateact.getCad_id());
			stmt.setString(27,rs.getString("computation_interval"));
			stmt.executeUpdate();
			Logging.info("completed");
		}catch(Exception e){
			Logging.error("Exception in second1 updation" + e.getMessage());
			}	
	}
	public static void insert_index_comp_his(Connection con,String query,FixedIncomeCorporate corporateact)
	{	
		try
		{
			Logging.info("----------in index_comp_history--------");
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getI_index());
			String date=corporateact.getApply_date();//accept_date();
			stmt.setString(2,corporateact.getStid());
			String mcv=Double.toString(corporateact.getNewmcv());
			stmt.setString(3,mcv);
			stmt.setString(4,corporateact.getValues());			
			stmt.setString(5,date);
			stmt.setString(6,corporateact.getCad_id());
			stmt.executeUpdate();				
		}catch(Exception e){
			Logging.error("Exception in second1 updation" + e.getMessage());
		}
	}
	public static void update_curr_indcomp(FixedIncomeCorporate corp)
	{
		try{
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}
	//		CFormula cf = new CFormula();    
			CFormula cf = ConnectInit.getCFormula();
			int oldcur=0;
			int newcur=0;
			String query1=ConnectInit.queries.getProperty("resp_ftcurr");
			ResultSet rs2=FixedIncomeListTypeClass1.getAffected(con,query1,corp.getFtcurrency());
			rs2.next();
			int fcurr=rs2.getInt("from_currency_id");
			int tcurr=rs2.getInt("to_currency_id");
			rs2.close();
			int curid=Integer.parseInt(corp.getCurrencyid());
			if(curid==fcurr)
			{
				oldcur=fcurr;
				newcur=tcurr;				
			}
			if(curid==tcurr)
			{
				oldcur=tcurr;
				newcur=fcurr;
			}
			double currval=0.0;			
			query1=ConnectInit.queries.getProperty("currency_combination");
			rs2=FixedIncomeListTypeClass1.getResult_corp(con,query1,Integer.toString(newcur),Integer.toString(oldcur));
			if(rs2.next())
			 currval=Double.parseDouble(corp.getCurr_val());
			else
			{
				query1=ConnectInit.queries.getProperty("currency_combination");
				rs2=FixedIncomeListTypeClass1.getResult_corp(con,query1,Integer.toString(oldcur),Integer.toString(newcur));
				if(rs2.next())
					currval=(1/Double.parseDouble(corp.getCurr_val()));
			}
			rs2.close();
			
			String query=ConnectInit.queries.getProperty("get_composition_of_parent");  
			ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,corp.getI_index());
			while(rs!=null &&  rs.next())
			{
				String stock=rs.getString("stock_id");
				corp.setStid(stock);
				String iwf=rs.getString("iwf");
				corp.setValues(iwf);
				
				String qry=ConnectInit.queries.getProperty("detail_stock_master");
				ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,qry,stock);
				rs1.next();
				String tis=rs1.getString("tis");				
				String ml=rs1.getString("market_lot");				
				rs1.close();
				
				String qry1=ConnectInit.queries.getProperty("get_latest_stock_closing_value_tis");
				rs1=FixedIncomeListTypeClass1.getResult12(con,qry1,stock);
				rs1.next();
				String close=rs1.getString("adjusted_price");
				if(close==null)
					close=rs1.getString("stock_closing_value");			
				
				double newmcv= cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),currval,Long.parseLong(tis),Double.parseDouble(iwf));
				corp.setNewmcv(newmcv);
				String index_comp=ConnectInit.queries.getProperty("update_index");
				update_index_comp(con,index_comp,corp);
			}
		}catch(Exception e){
			Logging.error("Exception in second1 updation" + e.getMessage());
			}
	}
	public static void update_undo_curr_indcomp(FixedIncomeCorporate corp)
	{
		try{
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}	 
			CFormula cf = ConnectInit.getCFormula();
		//	CFormula cf=new CFormula();
//			get the list of stock belonging to the index  
			String query=ConnectInit.queries.getProperty("get_composition_of_parent");
			ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query,corp.getI_index());
			while(rs!=null && rs.next())
			{
				String stock=rs.getString("stock_id");
				corp.setStid(stock);				
				String iwf=rs.getString("iwf");
				corp.setValues(iwf);
				
				//get stock details from stock master
				String qry=ConnectInit.queries.getProperty("detail_stock_master");
				ResultSet rs1=FixedIncomeListTypeClass1.getAffected(con,qry,stock);
				rs1.next();
				String tis=rs1.getString("tis");
				String ml=rs1.getString("market_lot");
				rs1.close();
				
				String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");//get stock closing value for respective date
				//get stock closing value for particular date
				rs1=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,stock,corp.getApplied_date()); //get stock closing value
				rs1.next();
				String sclose=rs1.getString("adjusted_price");
				if(sclose==null)
					sclose=rs1.getString("stock_closing_value");
				corp.setClose(sclose);
				rs1.close();
				
				//calculate MCV
				double mcv= cf.calMarketCap(Double.parseDouble(sclose),Long.parseLong(ml),Double.parseDouble(corp.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));
				corp.setNewmcv(mcv);
				
				//update index composition
				String indexcomp=ConnectInit.queries.getProperty("update_index");
				UpdateCorp.update_index_comp(con,indexcomp,corp);
			}//while close
		}catch(Exception e){
			Logging.debug("Error="+e.getMessage());
		}
	}
	public static void update_index_comp(Connection con,String query,FixedIncomeCorporate corporateact)
	{	
		try{
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getValues());
			String mcv=Double.toString(corporateact.getNewmcv());
			stmt.setString(2,mcv);
			stmt.setString(3,corporateact.getStid());
			stmt.setString(4,corporateact.getI_index());			
			stmt.executeUpdate();
			Logging.info("com");			
		}catch(Exception e){
			Logging.error("Exception in second1 updation" + e.getMessage());
		}
	}
	public static void update_index_daily(Connection con,String query,FixedIncomeCorporate corporateact)
	{
		try
		{
			String tmcv_val=corporateact.getNewTmcv();	
			String div_val=corporateact.getNewdivisor();			
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getI_index());
			if(corporateact.getNewTmcv().equals(""))
			{
				stmt.setString(2,null);
			}
			else
			{
				String tmcv1=corporateact.getNewTmcv();
				stmt.setString(2,corporateact.getNewTmcv());
			}
			if(corporateact.getNewdivisor().equals(""))
			{
				stmt.setString(3,null);
			}else
			{	
			 stmt.setString(3,corporateact.getNewdivisor());
			}
			stmt.setString(4,corporateact.getI_index());
			stmt.setString(5,corporateact.getI_index());			
			stmt.executeUpdate();
			Logging.info("after exceutioncom");
		}catch(Exception e){
			Logging.error("Exception in second1 updation" + e.getMessage());
		}
	}
	public static void undo_update_index_daily(Connection con,String qry,String query,FixedIncomeCorporate corporateact)
	{
		try{
				ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,qry,corporateact.getI_index(),corporateact.getApplied_date());
				rs.next();
				String indval_id=rs.getString("index_value_daily_id");
				if(rs!=null)
					rs.close();
				
				PreparedStatement  stmt = con.prepareStatement(query);
				stmt.setString(1,corporateact.getNewdivisor());
				stmt.setString(2,corporateact.getNewTmcv());
				stmt.setString(3,indval_id);
				stmt.executeUpdate();
				
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	public static void update_resp_index_daily(Connection con,String query,FixedIncomeCorporate corporateact)
	{
		try
		{
			String tmcv_val=corporateact.getNewTmcv();	
			String div_val=corporateact.getNewdivisor();		
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getI_index());
			stmt.setString(2,corporateact.getNewTmcv());
			stmt.setString(3,corporateact.getNewdivisor());
			stmt.setString(4,corporateact.getI_index());
			stmt.setString(5,corporateact.getApplied_date());
			stmt.executeUpdate();
		}catch(Exception e){
			Logging.error("Exception in second1 updation" + e.getMessage());
		}
	}
	public static void update_undo_index_daily(Connection con,String qry,String query,FixedIncomeCorporate corp)
	{
		try{
				ResultSet rs=FixedIncomeListTypeClass1.getResult_apply(con,qry,corp.getI_index(),corp.getApplied_date());
				rs.next();
				String ind_id=rs.getString("index_value_daily_id");
				if(rs!=null)
					rs.close();
				PreparedStatement  stmt = con.prepareStatement(query);
				stmt.setString(1,corp.getNewdivisor());
				stmt.setString(2,corp.getNewTmcv());
				stmt.setString(3,ind_id);
				stmt.executeUpdate();
		}catch(Exception e){
			Logging.error("Exception in second1 updation" + e.getMessage());
			}
	}
	public static void update_undo_curr_index(Connection con,String query,FixedIncomeCorporate corporateact)
	{
		try{
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getApply_date());
			stmt.setString(2,corporateact.getApplied_date());
			stmt.setString(3,"n");
			stmt.setString(4,null);
			stmt.setString(5,corporateact.getCad_id());
			stmt.executeUpdate();
		}catch(Exception e){
			Logging.debug("Error="+e.getMessage());
			}
	}
	public static void update_index_cad_with_time(Connection con,String query,FixedIncomeCorporate corporateact){
		String cad=null;
		try
		{
			cad=corporateact.getCad_id();									
		    PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getAnnounce_date());
			stmt.setString(2,corporateact.getEx_date());
			stmt.setString(3,corporateact.getRecord_date());
			stmt.setString(4,corporateact.getApply_date());
			String applied=corporateact.getApplied_date();			
			if(applied==null)
				stmt.setString(5,null);
			else
				stmt.setString(5,applied);
			stmt.setString(6,corporateact.getCorpid());
			String stock=corporateact.getStid();
			if(stock.equals("null"))				
				stmt.setString(7,null);//corporateact.getStid());
			else
			{
				if(stock.equals(""))
					stmt.setString(7,null);
				else
					stmt.setString(7,stock);//corporateact.getStid());
			}
			stmt.setString(8,corporateact.getI_index());
			String value=corporateact.getValues();
			if(value==null)
				stmt.setString(9,null);
			else
				stmt.setString(9,value);			
			String base_date=corporateact.getBase_date();
			if(base_date==null)
				stmt.setString(10,null);
			else
				stmt.setString(10,base_date);
			
			String status=corporateact.getStatus();
			stmt.setString(11,status);
			Date d=new Date();		
			long l1=d.getTime();			
			Time t1=new Time(l1);		
			String butt=corporateact.getButton();
			if(butt==null)
				stmt.setTime(12,t1);
			else
				stmt.setTime(12,null);
			stmt.setString(13,cad);
			stmt.executeUpdate();
		}catch(Exception e){
				Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
		}
	public static void update_index_cad(Connection con,String query,FixedIncomeCorporate corporateact){		
		String cad=null;
		try
		{
			cad=corporateact.getCad_id();							
		    PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getAnnounce_date());
			stmt.setString(2,corporateact.getEx_date());
			stmt.setString(3,corporateact.getRecord_date());
			stmt.setString(4,corporateact.getApply_date());
			String applied=corporateact.getApplied_date();			
			if(applied==null)
				stmt.setString(5,null);
			else
				stmt.setString(5,applied);
			
			stmt.setString(6,corporateact.getCorpid());
			String stock=corporateact.getStid();
			if(stock.equals("null"))				
				stmt.setString(7,null);//corporateact.getStid());
			else
			{
				if(stock.equals(""))
					stmt.setString(7,null);
				else
					stmt.setString(7,stock);//corporateact.getStid());
			}
			stmt.setString(8,corporateact.getI_index());
			String value=corporateact.getValues();			
			if(value==null)
				stmt.setString(9,null);
			else
				stmt.setString(9,value);			
			String base_date=corporateact.getBase_date();
			if(base_date==null)
				stmt.setString(10,null);
			else
				stmt.setString(10,base_date);
			
			String status=corporateact.getStatus();			
			stmt.setString(11,status);			
			stmt.setString(12,cad);
			stmt.executeUpdate();
			Logging.info("---------com------------");
		}catch(Exception e){
					Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
		}
	
	public static void insert_curr_diary(Connection con,String query,String nextval,FixedIncomeCorporate corporateact)
	{		
		try{
			int cad=FixedIncomeListTypeClass1.Select_nextval(con,nextval);					
			
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setInt(1,cad);
			if(corporateact.getAnnounce_date().equals(""))
				stmt.setString(2,null);
			else
				stmt.setString(2,corporateact.getAnnounce_date());
			
			if(corporateact.getEx_date().equals(""))
				stmt.setString(3,null);
			else
				stmt.setString(3,corporateact.getEx_date());
			
			if(corporateact.getRecord_date().equals(""))
				stmt.setString(4,null);
			else
				stmt.setString(4,corporateact.getRecord_date());
			stmt.setString(5,null);
			String applied=corporateact.getApplied_date();
			stmt.setString(6,applied);
			stmt.setString(7,corporateact.getAmount());
			stmt.setString(8,corporateact.getStatus());
			stmt.setString(9,corporateact.getCorpid());
			stmt.setString(10,corporateact.getI_index());
			stmt.setString(11,corporateact.getValues());
			Date d=new Date();		
			long l1=d.getTime();			
			Time t1=new Time(l1);
			stmt.setTime(12,t1);
			stmt.setString(13,corporateact.getDescription());
			stmt.executeUpdate();
			corporateact.setCad_id(Integer.toString(cad));
			Logging.info("complete");
		}catch(Exception e){
			Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
	}
	public static void update_curr_indmaster(Connection con,String query,FixedIncomeCorporate corporateact)
	{
		try{
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporateact.getValues());
			stmt.setString(2,corporateact.getI_index());
			stmt.executeUpdate();
		}catch(Exception e){
			Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
	}
	
	public static void insert_index_cad(Connection con,String query,String nextval,FixedIncomeCorporate corporateact){		
		try
		{			
			int cad=FixedIncomeListTypeClass1.Select_nextval(con,nextval);		
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setInt(1,cad);
			
			if(corporateact.getAnnounce_date().equals(""))
				stmt.setString(2,null);
			else
				stmt.setString(2,corporateact.getAnnounce_date());
			
			if(corporateact.getEx_date().equals(""))
				stmt.setString(3,null);
			else
				stmt.setString(3,corporateact.getEx_date());
			
			if(corporateact.getRecord_date().equals(""))
				stmt.setString(4,null);
			else
				stmt.setString(4,corporateact.getRecord_date());
			
			String apply=corporateact.getApply_date();
			if(apply==null)
				stmt.setString(5,null);
			else
			{
				if(apply.equals(""))
					stmt.setString(5,null);
				else
					stmt.setString(5,apply);
			}			
			
			String status=corporateact.getStatus();
			String applied=corporateact.getApplied_date();
			if(applied==null)
				stmt.setString(6,null);
			else
			{
				if(applied.equals(""))
					stmt.setString(6,null);
				else
					stmt.setString(6,applied);
			}
			
			if(status==null)
				stmt.setString(7,"n");
			else
			{
				if(status.equals("y"))
					stmt.setString(7,"y");
				else
					stmt.setString(7,"n");
			}
			
			stmt.setString(8,corporateact.getCorpid());
			String stock=corporateact.getStid();
			
			if(stock==null)
			{		
				stmt.setString(9,null);
			}
			else
				stmt.setString(9,stock);
			
			stmt.setString(10,corporateact.getI_index());
			String value=corporateact.getValues();
			if(value==null)
				stmt.setString(11,null);
			else
			{
				if(value.equals(""))
					stmt.setString(11,null);
				else				
					stmt.setString(11,value);
			}			
			
			String base=corporateact.getBase_date();
			if(base==null)
				stmt.setString(12,null);
			else
			{
				if(base.equals(""))
					stmt.setString(12,null);
				else
					stmt.setString(12,base);
			}
			stmt.setString(13,corporateact.getBc_start());
			stmt.setString(14,corporateact.getBc_end());
			stmt.setString(15,corporateact.getNc_start());
			stmt.setString(16,corporateact.getNc_end());
			Date d=new Date();		
			long l1=d.getTime();	
			Time t1=new Time(l1);
			stmt.setTime(17,t1);
			stmt.executeUpdate();
			corporateact.setCad_id(Integer.toString(cad));
			Logging.info("complete");
				}catch(Exception e){
					Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
		}	
	public static void check_index_value(FixedIncomeCorporate corporateact)
	{
			if(corporateact.getAnnounce_date().equals(""))
				corporateact.setAnnounce_date(null);
			if(corporateact.getApply_date().equals(""))
				corporateact.setApply_date(null);
			if(corporateact.getEx_date().equals(""))
				corporateact.setEx_date(null);
			if(corporateact.getRecord_date().equals(""))
				corporateact.setRecord_date(null);
			if(corporateact.getValues().equals(""))
				corporateact.setValues(null);
			if(corporateact.getBase_date().equals(""))
				corporateact.setBase_date(null);
			//if(corporateact.getStid().equals(""))
			//	corporateact.setStid(null);
			
	}
	public static void delete_event(Connection con,String query,FixedIncomeCorporate corporateact)
	{
		 try
		 {
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1,corporateact.getEvent_id());		
		stmt.executeQuery();
		Logging.info("delete entry");
		}catch(Exception e){
			Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
		}
	}	
	public static void insert_into_event(Connection con,String query,String nextval,FixedIncomeCorporate newcorporateact,String flag){		
		try{	
		String event_val=null;
		if(flag==null)
		{
			int event=FixedIncomeListTypeClass1.Select_nextval(con,nextval);
			event_val=Integer.toString(event);
			newcorporateact.setCad_id(event_val);
		}
		else
		{
			event_val=newcorporateact.getCad_id();
		}
		checkvalue(newcorporateact);		
		PreparedStatement  stmt = con.prepareStatement(query);
		stmt.setString(1,event_val);
		stmt.setString(2,newcorporateact.getAnnounce_date());
		stmt.setString(3,newcorporateact.getEx_date());
		stmt.setString(4,newcorporateact.getRecord_date());
		stmt.setString(5,newcorporateact.getApply_date());
		stmt.setString(6,newcorporateact.getApplied_date());
		stmt.setString(7,newcorporateact.getRatio1());
		stmt.setString(8,newcorporateact.getRatio2());
		stmt.setString(9,newcorporateact.getAmt());
		String percent=newcorporateact.getPercent();
		if(percent==null)
			stmt.setString(10,null);
		else
		{
			if(percent.equals(""))
				stmt.setString(10,null);
			else
			{
				if(percent.equals("0"))
					stmt.setString(10,null);
				else
					stmt.setString(10,newcorporateact.getPercent());
			}
		}					
		stmt.setString(11,newcorporateact.getStatus());
		stmt.setString(12,newcorporateact.getDescription());
		stmt.setString(13,newcorporateact.getStid());
		stmt.setString(14,newcorporateact.getShare());
		stmt.setString(15,newcorporateact.getBc_start());
		stmt.setString(16,newcorporateact.getBc_end());
		stmt.setString(17,newcorporateact.getNc_start());
		stmt.setString(18,newcorporateact.getNc_end());
		if(flag==null)						
			stmt.executeUpdate();
		else
		{
			stmt.setString(19,event_val);
			stmt.executeUpdate();
		}					
		//newcorporateact.setCad_id(null);
		reset(newcorporateact);
		Logging.info("completed");	
	}catch(Exception e){
		Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
	}
}
	public static void recal_update_affect_value(FixedIncomeCorporate corporateact)
	{
		String ori_index=corporateact.getI_index();
		String ori_tmcv=corporateact.getTmcv();
		String ori_newtmcv=corporateact.getNewTmcv();
		String ori_div=corporateact.getDivisor();
		String ori_newdiv=corporateact.getNewdivisor();
 
		try{
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}
			CFormula cf = ConnectInit.getCFormula();	
	//		CFormula cf=new CFormula();
			String stock_price=ConnectInit.queries.getProperty("select_stock_price_detail");
			String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");//get stock closing value for respective date
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
			String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");
			String index_comp_detail=ConnectInit.queries.getProperty("index_comp_detail");
			String index_comp=ConnectInit.queries.getProperty("update_index");
			String delete=ConnectInit.queries.getProperty("delete_index_comp");
			String index_his=ConnectInit.queries.getProperty("insert_index_com_his");			
			String get_cam=ConnectInit.queries.getProperty("select_from_cam");
			String select_is_cad=ConnectInit.queries.getProperty("select_index_stock_cad");
			
//			as this method is used both for historic and general CA, so there should be date comparison
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date						

			Hashtable hash1=corporateact.getHash_affind();
			Hashtable hash=corporateact.getHash2();
			for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
			{
				String ele=(String)enum1.nextElement();
				String div1[]=ActionCorp.token2(ele);
				 String ind_id =div1[1];
				 String ind_stk=null;
				 Object obj=hash1.get(ele);
				 ind_stk=obj.toString();
				 corporateact.setI_index(ind_id);
				 //calculate Affect Index Tmcv and Divisor
				 ActionCorp.recal_affect_value(con,connect,corporateact,corporateact.getI_index());
				 
				 corporateact.setTmcv(corporateact.getTmcv1());
				 corporateact.setDivisor(corporateact.getDivisor1());
				 corporateact.setNewTmcv(corporateact.getNewtmcv1());
				 corporateact.setNewdivisor(corporateact.getNewdivisor1());
				 Hashtable stk_error=corporateact.getHash_error();
				 for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
					{							
						 String id =(String)enmm.nextElement();
						 String div[]=ActionCorp.token(id);
						 Object obj1=hash.get(id);
						 String stock=obj1.toString();
						 String close=null;
						 double mcv=0.0;
						 if(stock.equals(ind_stk))
						 {							 		
							 	ResultSet rs1=null;
							 	if(chk_dt==0)
							 		rs1=FixedIncomeListTypeClass1.getResult12(con,stock_price,stock);
							 	else
							 		rs1=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,stock,corporateact.getApply_date());
								rs1.next();
								String corp_value=corporateact.getCorpid();
								boolean chk_stk=stk_error.containsKey(stock);
								if(chk_stk==false)
								{
									close=rs1.getString("adjusted_price");
									if(close==null)
										close=rs1.getString("stock_closing_value");
									rs1.close();
									//get currency exchange value
									FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),stock);
									
									rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
									rs1.next();
									String tis=rs1.getString("tis");
									String ml=rs1.getString("market_lot");
									if(corp_value.equals("addstock"))
									{
										String iwf=rs1.getString("iwf");
										corporateact.setValues(iwf);
										mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));										
									 	corporateact.setNewmcv(mcv);
										insert_index_comp(con,insert_index_comp,corporateact);
									}
									if(corp_value.equals("changeiwf"))
									{
										rs1=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_detail,corporateact.getI_index(),stock);
										rs1.next();
										String iwf=corporateact.getValues();//rs1.getString("iwf");
										corporateact.setValues(iwf);
										mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));										
										corporateact.setNewmcv(mcv);
										update_index_comp(con,index_comp,corporateact);
									}
									if(corp_value.equals("deletestock"))
									{
										rs1=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_detail,corporateact.getI_index(),stock);
										rs1.next();
										String iwf=rs1.getString("iwf");
										corporateact.setValues(iwf);
										mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));										
										corporateact.setNewmcv(mcv);
										try
										{
										PreparedStatement stmt = con.prepareStatement(delete);
										stmt.setString(1,corporateact.getI_index());
										stmt.setString(2,corporateact.getStid());
										stmt.executeQuery();
										}catch(Exception e){
											Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
										}
									}
									if(div[0].equals("false"))
										get_name(con,get_cam,select_is_cad,corporateact,ori_index);
									if(div[0].equals("true"))
											corporateact.setCad_id(div[1]);
									insert_index_comp_his(con,index_his,corporateact);								
								}
							}				 	
							
					}//for stock
				 if(chk_dt==0)
				 {
				 	String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");
				 	update_index_daily(con,ind_val_daily,corporateact);
				 }
				 else
				 {
				 	String qry=ConnectInit.queries.getProperty("get_undo_index_close");
				 	String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
					update_undo_index_daily(con,qry,ind_val_daily,corporateact);
				 }
				}//affect index
				 
			
			
		}catch(Exception e){
			Logging.error("error on update=="+e.getMessage());
		}
		corporateact.setI_index(ori_index);
		corporateact.setTmcv(ori_tmcv);
		corporateact.setNewTmcv(ori_newtmcv);
		corporateact.setDivisor(ori_div);
		corporateact.setNewdivisor(ori_newdiv);
	
	}
	public static void undoupdate_affect_index(FixedIncomeCorporate corporateact)
	{
		Connect connect = ConnectInit.getConnect();
		Connection con=null;
		if(con == null){
			 con = connect.getdbConnection();			
		}	
		String ori_index=corporateact.getI_index();
		String ori_tmcv=corporateact.getTmcv();
		String ori_newtmcv=corporateact.getNewTmcv();
		String ori_div=corporateact.getDivisor();
		String ori_newdiv=corporateact.getNewdivisor();
		
		String ori_affect=corporateact.getAffect();
		String ori_tmcv1=corporateact.getTmcv1();
		String ori_newtmcv1=corporateact.getNewtmcv1();
		String ori_div1=corporateact.getDivisor1();
		String ori_newdiv1=corporateact.getNewdivisor1();
		
		String stock_price=ConnectInit.queries.getProperty("get_undo_close_value");		
		String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
		String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");		
		String delete=ConnectInit.queries.getProperty("delete_index_comp");		        							
		String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");			        	
		String index_comp=ConnectInit.queries.getProperty("update_index");								
		
		
		try{
			boolean affect_val=corporateact.isCheck();			
			String date=corporateact.getApplied_date();
			if(affect_val==true)
		    {
				Hashtable hash=corporateact.getHash2();				
				Hashtable hash1=corporateact.getHash_affind();
				boolean rs_cond=false,rs_val=false,chk_stk=false;
				Object obj=null;
//				CFormula cf=new CFormula();
				CFormula cf = ConnectInit.getCFormula();
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{
					double newtmcv=0.0,newdiv=0.0;
					String tmcv=null,divi=null,ind_stk=null,index_close=null;
					String ele=(String)enum1.nextElement();
					String div1[]=ActionCorp.token2(ele);
					 String ind_id =div1[1];
					 obj=hash1.get(ele);
					 ind_stk=obj.toString();
					 corporateact.setI_index(ind_id);
					 
					 String rad_val=corporateact.getInd_comp();
					 if(rad_val!=null)
					 	ActionCorp.undo_recal_affect_value(corporateact,corporateact.getI_index());
					 else	
					 	ActionCorp.undoaffected_index_value(corporateact,corporateact.getI_index());
					 
					 corporateact.setTmcv(corporateact.getTmcv1());
					 corporateact.setDivisor(corporateact.getDivisor1());
					 corporateact.setNewTmcv(corporateact.getNewtmcv1());
					 corporateact.setNewdivisor(corporateact.getNewdivisor1());
					 
					 for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
						{							
							 String id =(String)enmm.nextElement();
							 String div[]=ActionCorp.token(id);
							 String close=null;
							 String tis=null,ml=null;
							 double mcv=0.0;
							 String stock=null;
							 String value_count=corporateact.getCoun();
							 String corp_value=corporateact.getCorpid();							 
							 corporateact.setStid(ind_stk);
							 stock=corporateact.getStid();
							 ResultSet rs1=FixedIncomeListTypeClass1.getResult_apply(con,stock_price,stock,date);			
							 rs_cond=rs1.next();
							 if(rs_cond==true)
								{
									close=rs1.getString("adjusted_price");
									if(close==null)
										close=rs1.getString("stock_closing_value");
									if(close!=null)
									{
										rs1.close();
										rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
										rs1.next();
										tis=rs1.getString("tis");
										ml=rs1.getString("market_lot");
										rs1.close();
										if(corp_value.equals("changeiwf"))
										{
											corporateact.setValues(value_count);
										}
										if(corp_value.equals("addstock"))
										{
											ResultSet rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
										    rs.next();
											corporateact.setValues(rs.getString("iwf"));
										}
										if(corp_value.equals("deletestock"))
										{
											rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
											rs1.next();
											 corporateact.setValues(rs1.getString("iwf"));
										}
										String value=corporateact.getValues();
										//get currency exchange value
										FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),corporateact.getStid());
										
										if(corp_value.equals("addstock"))
										{
											mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));												
											corporateact.setNewmcv(mcv);
											try
											{
											PreparedStatement stmt = con.prepareStatement(delete);
											stmt.setString(1,corporateact.getI_index());
											stmt.setString(2,corporateact.getStid());
											stmt.executeQuery();
											}catch(Exception e){
												Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
											}
										}
										if(corp_value.equals("deletestock"))
										{
											mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));											
											corporateact.setNewmcv(mcv);
											insert_index_comp(con,insert_index_comp,corporateact);											
										}
										if(corp_value.equals("changeiwf"))
										{
											ResultSet rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
										    rs.next();
										    String val=rs.getString("iwf");
										    double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));										    
										    double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));										    
										    mcv=mcv_new-mcv_old;
										    corporateact.setNewmcv(mcv);
										    update_index_comp(con,index_comp,corporateact);
										}
									}
								}	
						}//for hash
						String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
						String qry=ConnectInit.queries.getProperty("get_undo_index_close");
						update_undo_index_daily(con,qry,ind_val_daily,corporateact);		
				}//for hash1				
		    }//if affect_val
		}catch(Exception e){
			Logging.error("error on update=="+e.getMessage());
		}		
		corporateact.setI_index(ori_index);
		corporateact.setTmcv(ori_tmcv);
		corporateact.setNewTmcv(ori_newtmcv);
		corporateact.setDivisor(ori_div);
		corporateact.setNewdivisor(ori_newdiv);
		
		corporateact.setAffect(ori_affect);
		corporateact.setTmcv1(ori_tmcv1);
		corporateact.setNewtmcv1(ori_newtmcv1);
		corporateact.setDivisor1(ori_div1);
		corporateact.setNewdivisor1(ori_newdiv1);
	}
	public static void update_affect_index(Connection con,Connect connect,FixedIncomeCorporate corporateact)
	{
		try{
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
			String query1=ConnectInit.queries.getProperty("select_rep_cad");	                		
			String insert_cad=ConnectInit.queries.getProperty("insert_index_cad");               								
							
        	String index_comp=ConnectInit.queries.getProperty("update_index");								
			String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");			        	
        	String delete=ConnectInit.queries.getProperty("delete_index_comp");		        							
        	String index_his=ConnectInit.queries.getProperty("insert_index_com_his");		        	
       					        	
	        String query3=ConnectInit.queries.getProperty("select_stock_detail");	
						
	        String select_corp=ConnectInit.queries.getProperty("select_corp_name");				        			       						    
			String corp_query =ConnectInit.queries.getProperty("get_corporate_list_index");				        
			String get_cam=ConnectInit.queries.getProperty("select_from_cam");
			String select_is_cad=ConnectInit.queries.getProperty("select_index_stock_cad");
			String index_comp_val=ConnectInit.queries.getProperty("select_index_comp");
			CFormula cf = ConnectInit.getCFormula();
		//	CFormula cf=new CFormula();
			boolean affect_val=corporateact.isCheck();
			
// as this method is used both, for historic and general CA,there should be date comparison
			
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
			
			Hashtable hash1=corporateact.getHash_affind();
			if(hash1.isEmpty()==false)
		    {				
				String ori_index=corporateact.getI_index();
				String ori_tmcv=corporateact.getTmcv();
				String ori_newtmcv=corporateact.getNewTmcv();
				String ori_div=corporateact.getDivisor();
				String ori_newdiv=corporateact.getNewdivisor();
				Hashtable hash=corporateact.getHash2();		
				boolean rs_cond=false,rs_val=false,chk_stk=false;
				Object obj=null;
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{
					double newtmcv=0.0,newdiv=0.0;
					String tmcv=null,divi=null,ind_stk=null,index_close=null;
					String ele=(String)enum1.nextElement();
					String div1[]=ActionCorp.token2(ele);
					 String ind_id =div1[1];
					 obj=hash1.get(ele);
					 ind_stk=obj.toString();
					 corporateact.setI_index(ind_id);
					for(Enumeration enmm =hash.keys();enmm.hasMoreElements();)
					{							
						 String id =(String)enmm.nextElement();
						 String div[]=ActionCorp.token(id);
						 String close=null;
						 String tis=null,ml=null;
						 String stock=null;
						 String value_count=corporateact.getCoun();
						 if(div[0].equals("true"))
						 {				 	
						 	
						 	ResultSet rs=FixedIncomeListTypeClass1.getAffected(con,query1,div[1]);
						 	if (rs != null && rs.next()) {
								stock = rs.getString("stock_id");
								if (ind_stk.equals(stock))
									chk_stk = true;
								else
									chk_stk = false;
								if (chk_stk == true) {
									corporateact.setStid(rs.getString("stock_id"));
									int corp = rs.getInt("cam_id");
									ResultSet rs1 = null;
									if (chk_dt == 0) {
										//get latest stock closing value
										String stock_price = ConnectInit.queries
												.getProperty("select_stock_price_detail");
										rs1 = FixedIncomeListTypeClass1.getResult12(con,stock_price, stock);
									} else {
										String stock_price = ConnectInit.queries
												.getProperty("get_undo_close_value");
										rs1 = FixedIncomeListTypeClass1.getResult_apply(
												con, stock_price, stock, apply);
									}
									rs_cond = rs1.next();
									String corp_value = corporateact
											.getCorpid();
									if (rs_cond == true) {
										close = rs1.getString("adjusted_price");
										if (close == null)
											close = rs1
													.getString("stock_closing_value");
										rs1.close();
										rs1 = FixedIncomeListTypeClass1.getAffected(con,
												stk_query, stock);
										rs1.next();
										tis = rs1.getString("tis");
										ml = rs1.getString("market_lot");
										rs1.close();
										if (corp_value.equals("changeiwf")) {
											String nature = corporateact
													.getNature();
											if (nature != null) {
												if (nature.equals("n")) {
													corporateact
															.setValues(value_count);
												} else {
													corporateact
															.setValues(rs
																	.getString("values"));
												}
											} else
												corporateact.setValues(rs
														.getString("values"));
										}
									}
								}//chk_stk
							} //close if if (rs!=null)  
						 }//true if				 
						 if(div[0].equals("false"))
						 {
						 	if(ind_stk.equals(div[1]))
						 		chk_stk=true;
						 	else
						 		chk_stk=false;
						 	if(chk_stk==true)
						 	{
							 	corporateact.setStid(div[1]);
							 	corporateact.setValues(value_count);
							 	stock=div[1];
							 	ResultSet rs1=null;
							 	if(chk_dt==0)
								{
									//get latest stock closing value
									String stock_price=ConnectInit.queries.getProperty("select_stock_price_detail");
									rs1=FixedIncomeListTypeClass1.getResult12(con,stock_price,stock);
								}
								else									
								{
									String stock_price=ConnectInit.queries.getProperty("get_undo_close_value");
									rs1=FixedIncomeListTypeClass1.getResult_apply(con,stock_price,stock,apply);
								}							 				
								rs_cond=rs1.next();
								if(rs_cond==true)
								{
									close=rs1.getString("adjusted_price");
									if(close==null)
											close=rs1.getString("stock_closing_value");									
									rs1.close();
									rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,stock);
									if(rs1!=null && rs1.next()){
										tis=rs1.getString("tis");
										ml=rs1.getString("market_lot");
										rs1.close();
									}
								}
						 	}
						 }//false if
						 if(chk_stk==true)
						 {
							 String corpid=corporateact.getCorpid();
							 if(corpid.equals("addstock"))
							 {
							 	ResultSet rs2=FixedIncomeListTypeClass1.getResult12(con,query3,stock);
								 if(rs2!=null && rs2.next())
								 	corporateact.setValues(rs2.getString("iwf"));
								 
							 }
							 if(corpid.equals("deletestock"))
								{
									ResultSet rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
								    if(rs!=null & rs.next())
								    	corporateact.setValues(rs.getString("iwf"));
								}
							 String value=corporateact.getValues();
							 if(rs_cond==true)
							 {
							 	double mcv=0.0;							 								 	
							 	//get currency exchange rate
							 	FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),stock);
							 	
							 	if(corpid!=null)
							 	{
							 		ResultSet rs=null;
							 		if(chk_dt==0)
							 		{
							 			String index_detail=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");//get latest index values
							 			rs=FixedIncomeListTypeClass1.getResult12(con,index_detail,ind_id);
							 		}
							 		else
							 		{
							 			String index_detail=ConnectInit.queries.getProperty("get_undo_index_close");  //get index values for particular date			
										rs=FixedIncomeListTypeClass1.getResult_apply(con,index_detail,ind_id,apply);
							 		}
									rs_val=rs.next();
									if(rs_val==true)
									{
										tmcv=rs.getString("tmcv");										
										corporateact.setTmcv(tmcv);
										divi=rs.getString("divisor");
										index_close=rs.getString("index_closing_value");
										corporateact.setDivisor(divi);
										if(rs!=null)
											rs.close();
										if(corpid.equals("changeiwf"))
										{
											rs=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_val,corporateact.getI_index(),stock);
										    rs.next();
										    String val=rs.getString("iwf");
										    double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));										    
										    double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(val));										    
										    mcv=mcv_new-mcv_old;
										    corporateact.setNewmcv(mcv);
											if(newtmcv==0.0)
												newtmcv=Double.parseDouble(tmcv)+mcv;
											else
												newtmcv=newtmcv+mcv;
										}
										if(corpid.equals("addstock"))
										{
											mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));											
											corporateact.setNewmcv(mcv);
											if(newtmcv==0.0)
												newtmcv=Double.parseDouble(tmcv)+mcv;
											else
												newtmcv=newtmcv+mcv;
										}
										if(corpid.equals("deletestock"))
										{
											mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(value));											
											corporateact.setNewmcv(mcv);
											if(newtmcv==0.0)
												newtmcv=Double.parseDouble(tmcv)-mcv;
											else
												newtmcv=newtmcv-mcv;
										}
									}//rs val									
							 	}// if corp 
							 }	//rs cond	
							 if(corpid.equals("changeiwf"))
					 			update_index_comp(con,index_comp,corporateact);
							if(corpid.equals("addstock"))
								insert_index_comp(con,insert_index_comp,corporateact);
							if(corpid.equals("deletestock"))
							{
								try
								{
								PreparedStatement stmt = con.prepareStatement(delete);
								stmt.setString(1,corporateact.getI_index());
								stmt.setString(2,corporateact.getStid());
								stmt.executeQuery();
								}catch(Exception e){
									Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
								}
							}
							get_name(con,get_cam,select_is_cad,corporateact,ori_index);
							insert_index_comp_his(con,index_his,corporateact);								
						 }//chk_stk						 
					}//inner for stk
					corporateact.setNewTmcv(Double.toString(newtmcv));
					double div=Double.parseDouble(divi);
					if(div==0.0)
					{
						double newdivisor=(newtmcv/Double.parseDouble(index_close));
						corporateact.setNewdivisor(Double.toString(newdivisor));
						
					}
					else
					{					
						double diff=cf.diffTMCV(Double.parseDouble(tmcv),newtmcv);			
						double newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(divi));
						corporateact.setNewdivisor(Double.toString(newdivisor));
					}
					if(chk_dt==0)
					{
						String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");
						update_index_daily(con,ind_val_daily,corporateact);
					}
					else
					{
						String qry=ConnectInit.queries.getProperty("get_undo_index_close");
						String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
						update_undo_index_daily(con,qry,ind_val_daily,corporateact);
					}									
				}//outer for index
			corporateact.setI_index(ori_index);
			corporateact.setTmcv(ori_tmcv);
			corporateact.setNewTmcv(ori_newtmcv);
			corporateact.setDivisor(ori_div);
			corporateact.setNewdivisor(ori_newdiv);
		    }//affect if
		}catch(Exception e){
			Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
		}
	} //end of update_affect_index
	
	public static void recal_update_affect_index_comp(FixedIncomeCorporate corporateact)
	{
		String ori_index=corporateact.getI_index();
		String ori_tmcv=corporateact.getTmcv();
		String ori_newtmcv=corporateact.getNewTmcv();
		String ori_div=corporateact.getDivisor();
		String ori_newdiv=corporateact.getNewdivisor();
		
		String ori_affect=corporateact.getAffect();
		String ori_tmcv1=corporateact.getTmcv1();
		String ori_newtmcv1=corporateact.getNewtmcv1();
		String ori_div1=corporateact.getDivisor1();
		String ori_newdiv1=corporateact.getNewdivisor1();
		
		try{
			
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}	

			
        	String index_comp=ConnectInit.queries.getProperty("update_index");
        	String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");
        	String query1=ConnectInit.queries.getProperty("select_rep_cad");
        	String qry_detail=ConnectInit.queries.getProperty("select_stock_price_detail");
        	String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value"); //get stock_closing value for particular date
        	
        	String index_comp_his=ConnectInit.queries.getProperty("insert_index_com_his");
        	String index_comp_detail=ConnectInit.queries.getProperty("index_comp_detail");
        	String delete_index_comp=ConnectInit.queries.getProperty("delete_index_comp");
			String get_name=ConnectInit.queries.getProperty("get_corp_name");							        
			String corp_query =ConnectInit.queries.getProperty("get_corporate_list_index");
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
			
			Hashtable hash1=corporateact.getHash_affind();
			Hashtable data=corporateact.getHash();
			Hashtable data_error=corporateact.getHash_error();
			Hashtable data2=corporateact.getHash12();	
			boolean chk_hash1=hash1.isEmpty();
			boolean chk_stk=false;
		//	CFormula cf=new CFormula();
			CFormula cf = ConnectInit.getCFormula();
			// as this method is used both for historic nad general CA, so there should be date comparison
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date
			
			if(chk_hash1==false)
			{				
				String tmcv=null,divi=null,ind_stk=null;
				for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
				{						
					String ele=(String)enum1.nextElement();
					String div1[]=ActionCorp.token2(ele);
					 String ind_id =div1[1];
					 Object obj=hash1.get(ele);
					 ind_stk=obj.toString();
					 corporateact.setI_index(ind_id);
					 double newtmcv=0.0,newdiv=0.0,mcv=0.0,index_close=0.0;
					 String tis=null,close=null,iwf=null,ml=null;
					 
					 ActionCorp.recal_affect_index(con,connect,corporateact,ind_id);
					 corporateact.setTmcv(corporateact.getTmcv1());
					 corporateact.setDivisor(corporateact.getDivisor1());
					 corporateact.setNewTmcv(corporateact.getTmcv1());
					 corporateact.setNewdivisor(corporateact.getNewdivisor1());
					 int val=0;
					 for(Enumeration enmm =data.keys();enmm.hasMoreElements();)
						{
							String sid=(String)enmm.nextElement(); 
							obj=data.get(sid);							
							String cad_id =obj.toString();
							 corporateact.setCad_id(cad_id);
							 if(sid.equals(ind_stk))
							 	chk_stk=true;
							 else
							 	chk_stk=false;
							 if(chk_stk==true)
							 {
							 	for(Enumeration enum2 =data_error.keys();enum2.hasMoreElements();)
								 {
								 	String id1 =(String)enum1.nextElement();
								 	if(sid.equals(id1))
								 	{
								 		val=1;
								 		break;
								 	}
								 }
							 	 if(val==0)
								 {
									//get currency exchange value
							 	 	FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,corporateact.getI_index(),sid);
									 
							 		String corp=corporateact.getCorpid();
							 		
							 		//get stock closing value
							 		ResultSet rs1=null;
									 if(chk_dt==0)
									 	rs1=FixedIncomeListTypeClass1.getResult12(con,qry_detail,sid);
									 else
									 	rs1=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,sid,corporateact.getApply_date());
									if((corp.equals("addstock"))|(corp.equals("changeiwf")))
									{				
									
									 	int size=data2.size();
									 	if(size!=0)
										{
											for(Enumeration enm=data2.keys();enm.hasMoreElements();)
											{
												String id_val=(String)enm.nextElement();
												if(id_val.equals(sid))
												{
													obj = data2.get(id_val);
													break;
												}
												else
													obj="0";												
											}					
											iwf=obj.toString();
										}	
										 
										 rs1.next();											 
										 close=rs1.getString("adjusted_price");
										 if(close==null)
										 	close=rs1.getString("stock_closing_value");
										 rs1.close();
										 rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
										 rs1.next();
										 tis=rs1.getString("tis");
										 ml=rs1.getString("market_lot");
										 
										 corporateact.setValues(iwf);
										 if(corp.equals("addstock"))
										 {
											mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));											
											corporateact.setNewmcv(mcv);
										 }
										 if(corp.equals("changeiwf"))
										 {
										 	double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));										 	
										 	rs1=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_detail,corporateact.getI_index(),sid);
											rs1.next();
											String iwfval=rs1.getString("iwf");
											rs1.close();
											double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwfval));											
											mcv=mcv_new-mcv_old;
											 corporateact.setNewmcv(mcv);
										 }																					
									}										
									if(corp.equals("deletestock"))
									{										
										 rs1.next();											 
										 close=rs1.getString("adjusted_price");
										 if(close==null)
										 	close=rs1.getString("stock_closing_value");
										 rs1.close();
										 rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
										 rs1.next();
										 tis=rs1.getString("tis");
										 ml=rs1.getString("market_lot");
										 rs1=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_detail,corporateact.getI_index(),sid);
										rs1.next();
										iwf=rs1.getString("iwf");
										corporateact.setValues(iwf);
										mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));											
										corporateact.setNewmcv(mcv);									
										
									}									
									if(corp.equals("addstock"))
									{
										
										String iwf_val=corporateact.getValues();
										insert_index_comp(con,insert_index_comp,corporateact);
										insert_index_comp_his(con,index_comp_his,corporateact);										
									}
									if(corp.equals("deletestock"))
									{
										 try
										 {
										PreparedStatement stmt = con.prepareStatement(delete_index_comp);
										stmt.setString(1,corporateact.getI_index());
										stmt.setString(2,corporateact.getStid());
										stmt.executeQuery();
										}catch(Exception e){
											Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
										}	
										insert_index_comp_his(con,index_comp_his,corporateact);
									}
									if(corp.equals("changeiwf"))
									{	
										update_index_comp(con,index_comp,corporateact);		
										insert_index_comp_his(con,index_comp_his,corporateact);					
									}		
								 }
							 }//if chk_stk
						}//for stock					 
					 if(chk_dt==0)
					 {
					 	String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");
					 	update_index_daily(con,ind_val_daily,corporateact);
					 }
					 else
					 {
					 	String qry=ConnectInit.queries.getProperty("get_undo_index_close");
					 	String ind_val_daily=ConnectInit.queries.getProperty("update_undo_ind_close");
						update_undo_index_daily(con,qry,ind_val_daily,corporateact);
					 }
					 
				}//for hash_affect
			}
			
		}catch(Exception e){Logging.error("error==="+e.getMessage());}
		corporateact.setI_index(ori_index);
		corporateact.setTmcv(ori_tmcv);
		corporateact.setNewTmcv(ori_newtmcv);
		corporateact.setDivisor(ori_div);
		corporateact.setNewdivisor(ori_newdiv);
		
		corporateact.setAffect(ori_affect);
		corporateact.setTmcv1(ori_tmcv1);
		corporateact.setNewtmcv1(ori_newtmcv1);
		corporateact.setDivisor1(ori_div1);
		corporateact.setNewdivisor1(ori_newdiv1);
	}
	public static void update_affect_index_comp(FixedIncomeCorporate corporateact)
	{
		String ori_index=corporateact.getI_index();
		String ori_tmcv=corporateact.getTmcv();
		String ori_newtmcv=corporateact.getNewTmcv();
		String ori_div=corporateact.getDivisor();
		String ori_newdiv=corporateact.getNewdivisor();
		
		try{
			
			Connect connect = ConnectInit.getConnect();
			Connection con=null;
			if(con == null){
				 con = connect.getdbConnection();
			}	

			
        	String index_comp=ConnectInit.queries.getProperty("update_index");
        	String insert_index_comp=ConnectInit.queries.getProperty("insert_into_index_composition");
        	String query1=ConnectInit.queries.getProperty("select_rep_cad");
        	String qry_detail=ConnectInit.queries.getProperty("select_stock_price_detail");
        	String stk_qry=ConnectInit.queries.getProperty("get_undo_close_value");//get stock closing value for respective date
        	String index_comp_his=ConnectInit.queries.getProperty("insert_index_com_his");
        	String index_comp_detail=ConnectInit.queries.getProperty("index_comp_detail");
        	String delete_index_comp=ConnectInit.queries.getProperty("delete_index_comp");		        	
			String index_detail=ConnectInit.queries.getProperty("get_latest_index_value_index_wise");//"select_index_detail");
			String ind_query=ConnectInit.queries.getProperty("select_resp_close");  //get index values for particular date
			String get_name=ConnectInit.queries.getProperty("get_corp_name");							        
			String corp_query =ConnectInit.queries.getProperty("get_corporate_list_index");
			String stk_query=ConnectInit.queries.getProperty("detail_stock_master");
			
//as this method is used both for historic and general CA,so there should be date comparison
			String dt=UpdateCorp.accept_date();   //get the current date
			String apply=corporateact.getApply_date();
			int chk_dt=FixedIncomeComputeIndexForm.CompareDate(apply,dt);	//check for the current date and user's entered date

			boolean check_rs=corporateact.isCheck();
			if(check_rs==true)
			{
				Hashtable data=corporateact.getHash();				
				Hashtable hash1=corporateact.getHash_affind();				
				Hashtable data_error=corporateact.getHash_error();
				Hashtable data2=corporateact.getHash12();	
				boolean chk_hash1=hash1.isEmpty();				
				boolean chk_stk=false;
			//	CFormula cf = new CFormula();
				CFormula cf = ConnectInit.getCFormula();
				String tmcv=null,divi=null,ind_stk=null;
				if(chk_hash1==false)
				{
					for(Enumeration enum1 =hash1.keys();enum1.hasMoreElements();)
					{						
						String ele=(String)enum1.nextElement();
						String div1[]=ActionCorp.token2(ele);
						 String ind_id =div1[1];
						 Object obj=hash1.get(ele);
						 double newtmcv=0.0,newdiv=0.0,mcv=0.0,index_close=0.0;
						 String tis=null,close=null,iwf=null,ml=null;						 
						  ind_stk=obj.toString();
						 corporateact.setI_index(ind_id);
						 int val=0;
						 for(Enumeration enmm =data.keys();enmm.hasMoreElements();)
							{
								String sid=(String)enmm.nextElement(); 
								obj=data.get(sid);								
								String cad_id =obj.toString();
								 corporateact.setCad_id(cad_id);
								 if(sid.equals(ind_stk))
								 	chk_stk=true;
								 else
								 	chk_stk=false;
								 if(chk_stk==true)
								 {
								 	for(Enumeration enum2 =data_error.keys();enum2.hasMoreElements();)
									 {
									 	String id1 =(String)enum1.nextElement();
									 	if(sid.equals(id1))
									 	{
									 		val=1;
									 		break;
									 	}
									 }
								 	 if(val==0)
									 {	
								 		String corp=corporateact.getCorpid();								 		
										ResultSet rs2=null;
										if(chk_dt==0)
											rs2=FixedIncomeListTypeClass1.getResult12(con,index_detail,ind_id);
										else
											rs2=FixedIncomeListTypeClass1.getResult_apply(con,ind_query,ind_id,corporateact.getApply_date());
										rs2.next();			
										tmcv=rs2.getString("tmcv");										
										corporateact.setTmcv(tmcv);
										divi=rs2.getString("divisor");
										corporateact.setDivisor(divi);
										index_close=rs2.getDouble("index_closing_value");
										rs2.close();
										
										//get currency exchange value
										FixedIncomeNCorp_Action.get_currency(con,connect,corporateact,ind_id,sid);
										//get stock closing value
										ResultSet rs1=null;
										if(chk_dt==0)
											rs1=FixedIncomeListTypeClass1.getResult12(con,qry_detail,sid);
										else
											rs1=FixedIncomeListTypeClass1.getResult_apply(con,stk_qry,sid,corporateact.getApply_date());
										if((corp.equals("addstock"))|(corp.equals("changeiwf")))
										{
										 	int size=data2.size();
										 	if(size!=0)
											{
												for(Enumeration enm=data2.keys();enm.hasMoreElements();)
												{
													String id_val=(String)enm.nextElement();
													if(id_val.equals(sid))
													{
														obj = data2.get(id_val);
														break;
													}
													else
														obj="0";													
												}					
												iwf=obj.toString();
											}	
											 
											 rs1.next();											 
											 close=rs1.getString("adjusted_price");
											 if(close==null)
											 	close=rs1.getString("stock_closing_value");
											 rs1.close();
											 
											 rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
											 rs1.next();
											 tis=rs1.getString("tis");
											 ml=rs1.getString("market_lot");
											 
											 corporateact.setValues(iwf);
											 if(corp.equals("addstock"))
											 {
												mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));												
												corporateact.setNewmcv(mcv);
												if(newtmcv==0.0)
													newtmcv=Double.parseDouble(tmcv)+mcv;
												else
													newtmcv=newtmcv+mcv;
											 }
											 if(corp.equals("changeiwf"))
											 {
											 	double mcv_new=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));											 	
											 	rs1=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_detail,corporateact.getI_index(),sid);
													rs1.next();
												String iwfval=rs1.getString("iwf");
												double mcv_old=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwfval));												
												mcv=mcv_new-mcv_old;
												corporateact.setNewmcv(mcv);
												if(newtmcv==0.0)
													newtmcv=Double.parseDouble(tmcv)+mcv;
												else
													newtmcv=newtmcv+mcv;
											 }																						
										}										
										if(corp.equals("deletestock"))
										{											
											 rs1.next();											 
											 close=rs1.getString("adjusted_price");
											 if(close==null)
											 	close=rs1.getString("stock_closing_value");
											 rs1.close();
											 rs1=FixedIncomeListTypeClass1.getAffected(con,stk_query,sid);
											 rs1.next();
											 tis=rs1.getString("tis");
											 ml=rs1.getString("market_lot");
											 rs1=FixedIncomeListTypeClass1.getResult_corp(con,index_comp_detail,corporateact.getI_index(),sid);
											rs1.next();
											iwf=rs1.getString("iwf");
											corporateact.setValues(iwf);
											mcv=cf.calMarketCap(Double.parseDouble(close),Long.parseLong(ml),Double.parseDouble(corporateact.getCurr_val()),Long.parseLong(tis),Double.parseDouble(iwf));											
											corporateact.setNewmcv(mcv);											
											if(newtmcv==0.0)
												newtmcv=Double.parseDouble(tmcv)-mcv;
											else
												newtmcv=newtmcv-mcv;
										}
										
										if(corp.equals("addstock"))
										{
											
											String iwf_val=corporateact.getValues();
											insert_index_comp(con,insert_index_comp,corporateact);
											insert_index_comp_his(con,index_comp_his,corporateact);										
										}
										if(corp.equals("deletestock"))
										{
											 try
											 {
											PreparedStatement stmt = con.prepareStatement(delete_index_comp);
											stmt.setString(1,corporateact.getI_index());
											stmt.setString(2,corporateact.getStid());
											stmt.executeQuery();
											}catch(Exception e){
												Logging.error("ListTypeClass:Error in DeleteStatement "+e.getMessage());
											}	
											insert_index_comp_his(con,index_comp_his,corporateact);
										}
										if(corp.equals("changeiwf"))
										{	
											update_index_comp(con,index_comp,corporateact);		
											insert_index_comp_his(con,index_comp_his,corporateact);					
										}		
									 }
								 }//if chk_stk
							}//for stock
						 corporateact.setNewTmcv(Double.toString(newtmcv));
						 double divi1=Double.parseDouble(divi);
						 if(divi1==0.0)
						 {
						 	double newdivisor=newtmcv/index_close;
						 	corporateact.setNewdivisor(Double.toString(newdivisor));
						 }
						 else
						 {
						 	double diff=cf.diffTMCV(Double.parseDouble(tmcv),newtmcv);			
							double newdivisor=cf.newDivisorCorp(Double.parseDouble(tmcv),diff,Double.parseDouble(divi));					
							corporateact.setNewdivisor(Double.toString(newdivisor));						 	
						 }
						 if(chk_dt==0)
						 {
						 	String ind_val_daily=ConnectInit.queries.getProperty("update_index_value_daily");
						 	update_index_daily(con,ind_val_daily,corporateact);
						 }
						 else
						 {
						 	String ind_val_daily=ConnectInit.queries.getProperty("update_resp_index_value_daily");
							update_resp_index_daily(con,ind_val_daily,corporateact);
						 }
						 
					}//for index
				}//chk hash1
			}//check rs	
				}catch(Exception e){
			Logging.error("EXCEPTION IN  UPDATION1 :" +e.getMessage());
		}		
		corporateact.setI_index(ori_index);
		corporateact.setTmcv(ori_tmcv);
		corporateact.setNewTmcv(ori_newtmcv);
		corporateact.setDivisor(ori_div);
		corporateact.setNewdivisor(ori_newdiv);
	}

	//this method is used to calclulate Index parameter after Historic date till current date
	public static void update_hist_current_index(FixedIncomeCorporate corp)
	{
		try{
			Logging.debug("rad val==="+corp.getInd_comp());
			Logging.debug("in update");
			String apply=corp.getApply_date();			
			
				Date dd = new Date(new Integer(apply.trim().substring(6,
					10)).intValue(), new Integer(apply.trim().substring(3,
					5)).intValue(), new Integer(apply.trim().substring(0,
					2)).intValue());				
			long l1=((dd.getTime())+(86400000 * 1));		
			Date ds=new Date(l1);
			Logging.debug("previous date is "+ds);
			String month=new Integer(ds.getMonth()).toString();
			String day=new Integer(ds.getDate()).toString();
			if(day.length()!=0 && day.length()< 2)
				day="0"+day;
			if(month.length()!=0 && month.length()< 2)
				month="0"+month;
			String ldate=day+"-"+month+"-"+ds.getYear();
			Logging.debug("month len=="+month.length()+"day len=="+day.length());
			Logging.debug(" previous date is "+ldate);			
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	public static void update_historic(FixedIncomeCorporate corp)
	{
		try{
			Connect connect = ConnectInit.getConnect();
			Connection Hist_con=connect.getConnectionForHistTransaction();
			Hist_con.commit();
			//con.close();
			
			connect = ConnectInit.getConnect();
			Connection con=connect.getdbConnection();	
			String corp_nm=corp.getCorpid();
			if(corp_nm.equals("changeindcurr"))
			{
				//update parent index
				UpdateCorp.chagecurrupd(con,connect,corp);
//				for value persistant							
				String otmcv=corp.getTmcv();
				String ntmcv=corp.getNewTmcv();
				String odiv=corp.getDivisor();
				String ndiv=corp.getNewdivisor();
				
				String otmcv1=corp.getTmcv1();
				String ntmcv1=corp.getNewtmcv1();
				String odiv1=corp.getDivisor1();
				String ndiv1=corp.getNewdivisor1();
				
				String ori_index=corp.getI_index();	
				String corp_val=corp.getCorpid();
//				update child indices
				UpdateCorp.update_affectInd_curr(con,connect,corp);
				
				corp.setTmcv(otmcv);
				corp.setNewTmcv(ntmcv);
				corp.setDivisor(odiv);
				corp.setNewdivisor(ndiv);
				
				corp.setTmcv1(otmcv1);
				corp.setNewtmcv1(ntmcv1);
				corp.setDivisor1(odiv1);
				corp.setNewdivisor1(ndiv1);
				
				corp.setI_index(ori_index);
				corp.setCorpid(corp_val);
			}//if change currency
			else
			{
				//update parent index
				recal_assign_index_detail(con,connect,corp);
//				update child indices
				recal_update_affect_value(corp);
			}
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
	public static void update_hist_diary(FixedIncomeCorporate corp)
	{
		try{
			Connect connect = ConnectInit.getConnect();
			Connection Hist_con=connect.getConnectionForHistTransaction();
			Hist_con.commit();
			//con.close();
			
			connect = ConnectInit.getConnect();
			Connection con=connect.getdbConnection();
			//update parent index
			assign_index_cad(con,connect,corp);
			
			//update child indices
			String rad_val=corp.getInd_comp();
			if(rad_val!=null)
				recal_update_affect_index_comp(corp); 
			else
				update_affect_index_comp(corp);
			
			String ori_corp=corp.getCorpid(); //data persistent
			String ori_index=corp.getI_index();			 
			String otmcv=corp.getTmcv();
			String otmcv1=corp.getTmcv1();
			String ntmcv=corp.getNewTmcv();
			String ntmcv1=corp.getNewtmcv1();
			
			String odiv=corp.getDivisor();
			String odiv1=corp.getDivisor1();
			String ndiv=corp.getNewdivisor();
			String ndiv1=corp.getNewdivisor1();
			String ori_affect=corp.getAffect();
			
			Hashtable aff=corp.getHash_affind();
			Hashtable copy=corp.getCopy_hash();
			copy.clear();
			corp.setCopy_hash(copy);
			copy=corp.getCopy_hash();
			
			if(!(aff.isEmpty()))
			{
				for(Enumeration enum1 =aff.keys();enum1.hasMoreElements();)
				{				
					String id2 =(String)enum1.nextElement();
					Object obj=aff.get(id2);
					String stid=obj.toString();
					copy.put(new String(id2),new String(stid));
				}
				corp.setCopy_hash(copy);
			}
			//reapply all undo action
			reapply_action(corp);
			
			corp.setCorpid(ori_corp);  //set original data
			corp.setTmcv(otmcv);
			corp.setTmcv1(otmcv1);
			corp.setNewTmcv(ntmcv);
			corp.setNewtmcv1(ntmcv1);
			
			corp.setDivisor(odiv);
			corp.setDivisor1(odiv1);
			corp.setNewdivisor(ndiv);
			corp.setNewdivisor1(ndiv1);
			corp.setAffect(ori_affect);
			
			copy=corp.getCopy_hash();
			aff=corp.getHash_affind();
			aff.clear();
			corp.setHash_affind(aff);
			aff=corp.getHash_affind();
			if(!(copy.isEmpty()))
			{
				for(Enumeration enum1 =copy.keys();enum1.hasMoreElements();)
				{				
					String id2 =(String)enum1.nextElement();
					Object obj=copy.get(id2);
					String stid=obj.toString();
					aff.put(new String(id2),new String(stid));
				}
				corp.setHash_affind(aff);
			}
		}catch(Exception e){
			Logging.error("Error="+e.getMessage());
			}
	}
		public static void get_name(Connection con,String query,String query1,FixedIncomeCorporate corporateact,String index)
		{
			try{
				String id=null;
				String date=accept_date();
				ResultSet rs = FixedIncomeListTypeClass1.resultCorporate(con,query);
				rs.next();
				while(rs!=null && rs.next())
				{
					String name=rs.getString("cam_name");
					String div[]=ActionCorp.token2(name);
					String cname=div[0]+div[1];
					String corp_name=corporateact.getCorpid();
					if(cname.equals(corp_name))
					{
						id=rs.getString("cam_id");
						break;
					}
				}
				if(rs!=null)
					rs.close();								
				PreparedStatement  stmt = con.prepareStatement(query1);
				stmt.setString(1,index);
				stmt.setString(2,corporateact.getStid());
				stmt.setString(3,id);
				stmt.setString(4,date);
				ResultSet rs1 = stmt.executeQuery();
				rs1.next();
				String cad=rs1.getString("cad_id");
				if(rs!=null)
					rs.close();
				corporateact.setCad_id(cad);				
			}catch(Exception e){
				Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
			}
			
		}
	public static void insert_corp(Connection con,String query,String corp_name,String nextval,FixedIncomeCorporate corporate)
	{
		try{
				String corpid=corporate.getCorpid();
				ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,corp_name);
				FixedIncomeListTypeClass1.check_corp_name(rs,corporate.getCorpid(),corporate);				
				
				int cad=FixedIncomeListTypeClass1.Select_nextval(con,nextval);				
				PreparedStatement  stmt = con.prepareStatement(query);
				stmt.setInt(1,cad);
				if(corporate.getAnnounce_date().equals(""))
					stmt.setString(2,null);
				else					
					stmt.setString(2,corporate.getAnnounce_date());
				if(corporate.getEx_date().equals(""))
					stmt.setString(3,null);
				else						
					stmt.setString(3,corporate.getEx_date());
				if(corporate.getRecord_date().equals(""))
					stmt.setString(4,null);
				else	
					stmt.setString(4,corporate.getRecord_date());
				stmt.setString(5,corporate.getApply_date());
				stmt.setString(6,corporate.getApplied_date());
				stmt.setString(7,corporate.getCorpid());
				corporate.setCorpid(corpid);		
				stmt.setString(8,corporate.getStid());
				stmt.setString(9,corporate.getI_index());
				if((corpid.equals("addstock"))|(corpid.equals("deletestock")))
					stmt.setString(10,null);
				else
					stmt.setString(10,corporate.getValues());
				if(corpid.equals("rebasing"))
					stmt.setString(11,corporate.getBase_date());
				else
					stmt.setString(11,null);
				stmt.setString(12,corporate.getStatus());
				stmt.executeUpdate();
				Logging.info("complete");
		}catch(Exception e){
			Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
		}
	}
	public static void update_corp(Connection con,String query,String corp_name,FixedIncomeCorporate corporate)
	{
		try{
			String corpid=corporate.getCorpid();
			ResultSet rs=FixedIncomeListTypeClass1.resultCorporate(con,corp_name);
			FixedIncomeListTypeClass1.check_corp_name(rs,corporate.getCorpid(),corporate);
						
			PreparedStatement  stmt = con.prepareStatement(query);
			stmt.setString(1,corporate.getAnnounce_date());
			stmt.setString(2,corporate.getEx_date());
			stmt.setString(3,corporate.getRecord_date());
			stmt.setString(4,corporate.getApply_date());
			stmt.setString(5,corporate.getApplied_date());
			stmt.setString(6,corporate.getCorpid());
			String stock=corporate.getStid();
			stmt.setString(7,corporate.getStid());			
			stmt.setString(8,corporate.getI_index());			
			stmt.setString(9,corporate.getValues());			
			stmt.setString(10,corporate.getBase_date());			
			stmt.setString(11,corporate.getStatus());			
			stmt.setString(12,corporate.getCad_id());
			stmt.executeUpdate();
			corporate.setCorpid(corpid);
			Logging.info(" Complete ");
		}catch(Exception e){
			Logging.error(" EXCEPTION IN UPDATION1 :" +e.getMessage());
		}
	}
	
	public static void rebasingAction(FixedIncomeCorporate corp){
		String newBaseDate=corp.getBase_date();
		String newBaseValue=corp.getB_natureindex();
	}
	
	public static double get_usd_exchange(Connection con,Connect connect,FixedIncomeCorporate corp)
	{
	double exc_rate=0.0;
	try{
//	 get stock currencies
	String qry1=ConnectInit.queries.getProperty("detail_stock_master");
//	 commented by pranoti 11Aug05 as per IISL requirement(no cursor exception)
	/*stmt = con.prepareStatement(qry1);
	stmt.setString(1,stock);
	rs = stmt.executeQuery();*/
	ResultSet rs =FixedIncomeListTypeClass1.getAffected(con,qry1,corp.getS_stock());
	rs.next();
	String stk_curr=rs.getString(5);
	if(rs!=null)
		rs.close();

//	 commented by pranoti 11Aug05 as per IISL requirement(assuming that from system_setup/scrips USD currency_id will come)
	/*String query=connect.queries.getProperty("select_system_config");
	rs=ListTypeClass1.resultCorporate(con,query);
	rs.next();
	rs.close();
	*/
	String other_curr="1";
	if(stk_curr.equals(other_curr)) //if both r equal
	exc_rate=1.0;
	else
	{
	String ft_curr=null;
	int flg=0;
//	get the respective combination
	String qry=ConnectInit.queries.getProperty("currency_combination");
//	 commented by pranoti 11Aug05 as per IISL requirement(no cursor exception)
	/*stmt = con.prepareStatement(qry);
	stmt.setString(1,ind_curr);
	stmt.setString(2,stk_curr);
	rs = stmt.executeQuery();*/
	rs=FixedIncomeListTypeClass1.getResult_corp(con,qry,other_curr,stk_curr);
	if(rs.next())
	{
	ft_curr=rs.getString(1);
	if(rs!=null)
		rs.close();
	flg=1;
	}
	else
	{
	rs.close();
	rs=FixedIncomeListTypeClass1.getResult_corp(con,qry,stk_curr,other_curr);
	if(rs.next())
	{
	ft_curr=rs.getString(1);
	rs.close();
	flg=2;
	}
	else
	{
	rs.close();
	exc_rate=1.0;
	flg=0;
	}
	}//outer else

//	from combination get the exchange rate
	if(flg!=0)
	{
	String query=ConnectInit.queries.getProperty("resp_exrate_value");
	rs=FixedIncomeListTypeClass1.getAffected(con,query,ft_curr);
	rs.next();
	double val=rs.getDouble(3);
	if(rs!=null)
		rs.close();
//	stmt.close();
	if(flg==1)
	exc_rate=val;
	if(flg==2)
	exc_rate=(1/val);
	}
	}
	}catch(Exception e){
	Logging.error("Error="+e.getMessage());
	}
	return exc_rate;
	}
	

}