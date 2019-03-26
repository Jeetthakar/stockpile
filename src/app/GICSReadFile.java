package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.harrier.initializeation.ConnectInit;
/**
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GICSReadFile {
	Logger Logging = Logger.getLogger(GICSReadFile.class);
Hashtable targethashtable=new Hashtable();
/**
 * @return Returns the targethashtable.
 */
public Hashtable getTargethashtable() {
    return targethashtable;
}
/**
 * @param targethashtable The targethashtable to set.
 */
public void setTargethashtable(Hashtable targethashtable) {
    this.targethashtable = targethashtable;
}
private static String str_fileName=null;

private Corporate corp=new Corporate();
private String stock_exid="";
private String currency_id;	
private String country_id;
private String correctedFile;
private static ResultSet rst2=null;
private boolean FileFormatOK = true;
public boolean checkPriceOnDate = false;
private boolean ExchangeOK = true;
public  static String filedates;

/**
 * @param filedate The filedate to set.
 */
public static void setFiledate(String filedate) {
	filedates = filedate;
//	Logging.getDebug("Date in Pop"+filedate);
}
/**
 * @return Returns the filedate.
 */
public static String getFiledate() {
	return filedates;
}
String D1;
Vector vector_importfilelist;	
private int stock_currency_id;	
private String identifier_code_id;
private static String filedate=null;
public static Hashtable table = new Hashtable();//static
private String file_type=null;
private static String hist_Date=null;
private String file_type_name=null;	
Connection con=null;
PopFileDialog pfd=null;
String fromIndexcomposition=null;

/**
 * @param file_type The file_type to set.
 */
public void setFile_type(String File_type) {
	Logging.debug("set File type is "+File_type);
	this.file_type="";
	this.file_type = File_type;
}
/**
 * @return str_fileName.
 */
public static String getStr_fileName() {
	return str_fileName;
}
/**
 * @param str_fileName The str_fileName to set.
 */
public static void setStr_fileName(String str_fileName) {
	//Logging.getDebug("File is set"+str_fileName);
	str_fileName=null;
	str_fileName = str_fileName;
}
/**
 *method to check extension(like csv,dbf,xml) of file and returns true 
 *if chosen file extension is allowed.
 */
private boolean checkExt(){
	Logging.debug("Inside check ext");
	String temp ="";		 
	Logging.debug("file is null");
	temp = str_fileName.substring(str_fileName.lastIndexOf(".")+1);
	Logging.debug(temp);
	if(temp.equalsIgnoreCase("csv"))
		return true;
	else return false;
}
public StringBuffer displaydat(String str_fileName,GICSFileUploadForm gic){
	String errorMessage;
	Logging.debug("Display File Name is "+ str_fileName);		
	Logging.debug("inside display");	
	if((str_fileName==null) || (str_fileName.trim().length()==0)){
		Logging.debug("file is null using or");
		return null;
	}
	errorMessage = null;
	StringBuffer buffer = new StringBuffer();
	String path=Connect.getCoolMenuspath();
	Logging.debug("path is : "+path);
	String str_dirName = path+"/CoolMenus/";//get directory path upto CoolMenus Folder.
	Logging.debug(str_dirName);
	Logging.debug("file name is using or "+str_fileName);
	stock_exid=gic.getB1();
	str_fileName=str_dirName+str_fileName;
	
	String temp="";
	temp = str_fileName.substring(str_fileName.lastIndexOf(".")+1);
	Logging.debug("Exchang id after ifs"+stock_exid);
	
	if(temp.equalsIgnoreCase("csv")){
		try
		{
			Logging.debug("File path is "+str_fileName);
			FileReader file = new FileReader(str_fileName);		
			BufferedReader br = new BufferedReader(file);
			String str="";
			int i;
			//int nseNewStockfileflag=1;	
			FileFormatOK = true;
			String file_type= gic.getFileType();
			Logging.debug("filetype = "+file_type);
				
			Logging.debug("File type after ifs "+file_type);
			String[] arr ;	
			int count=0;
			while((str=br.readLine())!=null )
			{		
				arr= str.split(",");
				count++;
				i=0;				 
				file_type_name=null;
				GICSForm FD=new GICSForm();
				Logging.debug("Before switch"+arr.length);					
				switch(arr.length)//work out using table file import and related
				{
				  case 5:
				  	if(file_type.equals("1"))
						file_type_name="Import Gics file";
					break;
				  default:
					FileFormatOK=false;
					Logging.debug("Inside switch with "+arr.length + " No cases matched");
					break;
				}
				if(FileFormatOK==false)
				{
					buffer.append("<font size='2' face='Arial' color='Red'><tr>File with improper format </tr></font>");
					FileFormatOK=true;
					return buffer;
				}
				buffer.append("<tr>");
				if(count==1) {
					while(i<arr.length)
					{
						//Logging.getDebug("Inside while of First Readline");
						buffer.append("<td>");
						buffer.append("<font size='3' face='Arial' color='Blue'>"+arr[i++]+"</font>");
						buffer.append("</td>");					
					}		
				}else{
					while(i<arr.length)
					{
						switch(i)
						{
				    		case 	0:
					   			FD.setCompany_name(arr[i]);					   			
								break;
				    		case 	1:
								FD.setBasic_industry(arr[i]);
								break;
				    		case 2:
								  FD.setIndustry(arr[i]);
								  break;
				    		case 3:
							   FD.setSector(arr[i]);
							      break;
				    		case 4:
							 	   FD.setEconomic_sector(arr[i]);
							 	   break;
				    		default :
								Logging.debug("Default switch case : updatef");
								break;								
						}
						if(arr[i].equals("") || arr[i]==null ){
						}else{
							buffer.append("<td>");
							buffer.append("<font size='2' face='Arial' color='Black'>"+arr[i++]+"</font>");
							buffer.append("</td>");	
						}							
					}	
					table.put(FD.getCompany_name(),FD);
				}
				buffer.append("</tr>");			
				
			}
			if(file_type.equals("1") && file_type_name!=null && file_type_name.equals("Import Gics file"))
			{
				Logging.debug("Calling UpdateDat");
			//	return UpdateDat.getHashnBufferdDat(buffer,br);					
			}
			
		}catch (IOException e) {
			errorMessage = e.getMessage();
			Logging.error("Error : "+e.getMessage());
			e.getStackTrace();
			return null;
		}	
	}
	Logging.debug("Display Complete" );
	return buffer;
	
	}

public Vector getVector_importfilelist() {
	return vector_importfilelist;
}
public void setVector_importfilelist() {
vector_importfilelist=new Vector();
int i = 1;	
vector_importfilelist.add(new Integer(i));
vector_importfilelist.add("Open_High_Low_Close_File");//1
Logging.debug("Inside setvector importfile");
}

public String getStock_exid() {		
	return stock_exid;
}	
public void setStock_exid(String stock_exid) {
	this.stock_exid="";
	Logging.debug("The stock id is" +stock_exid);
	this.stock_exid = stock_exid;
	//Logging.getDebug("setstock_exid is "+ stock_exid);
}	

public  Hashtable getTable() {  //static
	return table;
}
/**
 * @param table The table to set.
 */
public  void setTable(Hashtable table) { //static
	 this.table = table;
}
public String getCorrectedFile() {
	return correctedFile;
}
/**
 * @param correctedFile The correctedFile to set.
 */
public void setCorrectedFile(String correctedFile) {
	this.correctedFile = correctedFile;
}
/**
 * @return Returns the hist_Date.
 */
public static String getHist_Date() {
	return hist_Date;
}
/**
 * @param hist_Date The hist_Date to set.
 */
public static void setHist_Date(String hist_Date) {
	hist_Date = hist_Date;
}
public boolean isCheckPriceOnDate() {
	return checkPriceOnDate;
}
/**
 * @param checkPriceOnDate The checkPriceOnDate to set.
 */
public void setCheckPriceOnDate(boolean checkPriceOnDate) {
	this.checkPriceOnDate = checkPriceOnDate;
}
  
public StringBuffer storeStock(String exchange_id,HttpServletRequest request){
	
	Logging.debug("inside storeStock");
	
	StringBuffer buffer=new StringBuffer();
	Enumeration en=table.keys();
	Connect con=ConnectInit.getConnect();
	ResultSet rs=null;
	PreparedStatement pst=null;
	Connection connection =null;
	try{		
		String scrip_id=null;
		if(connection==null)
			connection = con.getConnectionForTransaction();	
		connection.setAutoCommit(false);
		int counter=0;
		for(en=table.keys();en.hasMoreElements();)			
		{		
			counter++;
			scrip_id=null;
			if(counter%10==0){						
				connection.commit();
				connection.setAutoCommit(true);
				Logging.debug("counter after commit 3 IS"+counter);					
				counter=0;	
				if(connection!=null)
					connection.close();
				connection=null;
				connection=con.getConnectionForTransaction();				
			}	
			String str=(String)en.nextElement();
			GICSForm FD=(GICSForm)table.get(str);
			String query=ConnectInit.queries.getProperty("GICS_import_get_scrip_id");
			pst =  connection.prepareStatement(query);
			pst.setString(1,str);
			pst.setString(2,exchange_id);
			rs = pst.executeQuery();
			int indexid=0;
			if(rs.next()){
				scrip_id=rs.getString(1);
			}
			rs.close();
			pst.close();
			if(scrip_id==null) {
				buffer.append("<tr><td>");
				buffer.append(str);
				buffer.append("</td><td><font color='blue'>Company Not Found</font></td></tr>");
			}else{
				String msg="Entries Already Exist For ";
				String msg1="Entries does not exist for";
				boolean[] flag1=saveIndClassification(connection,"1",FD.getEconomic_sector(),scrip_id);
				if(flag1[0]==true)
					msg=msg+" , "+FD.getEconomic_sector();
				else{
					msg=msg+"Entry does not exist for , "+FD.getEconomic_sector();
					msg1=msg1+","+FD.getEconomic_sector();
				}
				boolean[] flag2=saveIndClassification(connection,"2",FD.getSector(),scrip_id);
				if(flag2[0]==true)
					msg=msg+" , "+FD.getSector();
				else{
					msg=msg+"Entry does not exist for  , "+FD.getSector();
					msg1=msg1+" , "+FD.getSector();
				}
				boolean[] flag3=saveIndClassification(connection,"3",FD.getIndustry(),scrip_id);
				if(flag3[0]==true)
					msg=msg+" , "+FD.getIndustry();
				else{
				msg=msg+" Entry does not exist for, "+FD.getIndustry();
				msg1=msg1+" , "+FD.getIndustry();
				}
				boolean[] flag4=saveIndClassification(connection,"4",FD.getBasic_industry(),scrip_id);
				if(flag4[0]==true)
					msg=msg+" , "+FD.getBasic_industry();
				else{
					msg=msg+"Entry does not exist for , "+FD.getBasic_industry();
				msg1=msg1+" , "+FD.getBasic_industry();
			}
				if(flag1[0]==true || flag2[0]==true || flag3[0]==true || flag4[0]==true) {
					buffer.append("<tr><td>");
					buffer.append(str);
					buffer.append("</td><td><font color='blue'>"+msg+"</font></td></tr>");
				}
				else{
				if(flag1[0]==false && flag2[0]==false && flag3[0]==false && flag4[0]==false 
						&& flag1[1]==true && flag2[1]==true && flag3[1]==true && flag4[1]==true){
				buffer.append("<tr><td>");
				buffer.append(str);
				buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +"</font></td></tr>");
				}
				
				if(flag1[1]==false && flag2[1]==true && flag3[1]==true && flag4[1]==true){
					buffer.append("<tr><td>");
					buffer.append(str);
					buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
							"But entry does not exist for"+FD.getEconomic_sector()+"</font></td></tr>");
					}
					if(flag2[1]==false&& flag1[1]==true && flag3[1]==true && flag4[1]==true){
						buffer.append("<tr><td>");
						buffer.append(str);
						buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
								"But entry does not exist for"+FD.getSector()+"</font></td></tr>");
						}
					if(flag3[1]==false&& flag2[1]==true && flag1[1]==true && flag4[1]==true){
						buffer.append("<tr><td>");
						buffer.append(str);
						buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
								"But entry does not exist for"+FD.getIndustry()+"</font></td></tr>");
						}
					if(flag4[1]==false&& flag2[1]==true && flag3[1]==true && flag1[1]==true){
						buffer.append("<tr><td>");
						buffer.append(str);
						buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
								"But entry does not exist for"+FD.getBasic_industry()+"</font></td></tr>");
						}
					if(flag1[1]==false && flag2[1]==false && flag3[1]==true && flag4[1]==true){
						buffer.append("<tr><td>");
						buffer.append(str);
						buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
								"But entry does not exist for"+FD.getEconomic_sector()+
								" ,"+FD.getSector()+"</font></td></tr>");
						}
					if(flag1[1]==false && flag2[1]==false && flag3[1]==false && flag4[1]==true){
						buffer.append("<tr><td>");
						buffer.append(str);
						buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
								"But entry does not exist for"+FD.getEconomic_sector()+
								" ,"+FD.getSector()+" ,"+FD.getIndustry()+"</font></td></tr>");
						}
					if(flag1[1]==true && flag2[1]==false && flag3[1]==false && flag4[1]==true){
						buffer.append("<tr><td>");
						buffer.append(str);
						buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
								"But entry does not exist for"
							+FD.getSector()+" ,"+FD.getIndustry()+"</font></td></tr>");
						}
					if(flag1[1]==true && flag2[1]==true && flag3[1]==false && flag4[1]==false){
						buffer.append("<tr><td>");
						buffer.append(str);
						buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
								"But entry does not exist for"
							+FD.getIndustry()+" ,"+FD.getBasic_industry()+"</font></td></tr>");
						}
					/*if(flag1[1]==false && flag2[1]==false && flag3[1]==true && flag4[1]==true){
						buffer.append("<tr><td>");
						buffer.append(str);
						buffer.append("</td><td><font color='blue'>Classification Stored Successfully" +
								"But entry does not exist for"
							+FD.getIndustry()+" ,"+FD.getBasic_industry()+"</font></td></tr>");
						}*/
				
				}
			}
		}
		connection.commit();
	}catch(Exception ee){
		Logging.error(" Error : "+ee.getMessage());
	}
	finally{
		try{
			if(connection!=null)
				connection.close();
		}catch(Exception ex){
			Logging.error(" Error : Unable to close connection "+ex.getMessage());
		}
	}
	return buffer;
	
}
public boolean[] saveIndClassification(Connection connection,String level_id,String field_value,String scrip_id) {
	Connect con=ConnectInit.getConnect();
	boolean[] flag={false,true};
	boolean flag1=false;
	PreparedStatement pst=null;
	ResultSet rs=null;
	try{
		String I_class_id=null;
		String query=ConnectInit.queries.getProperty("GICS_get_ind_class_id");
		pst =  connection.prepareStatement(query);
		Logging.debug("scrip_id= "+scrip_id+"level_id= "+level_id+"class_name(field_value)="+field_value.trim());
		pst.setString(1,level_id);
		pst.setString(2,field_value.trim());
		rs = pst.executeQuery();
		int indexid=0;
		while(rs.next()){
			I_class_id=rs.getString(1);
		}
		rs.close();
		pst.close();
		pst=null;
		query=null;
		
		if(I_class_id==null)
		{
		flag[1]=false;
		return flag;
		}
		if(I_class_id!=null)
		{
		Logging.debug("Class_id isisis="+I_class_id);
		query=ConnectInit.queries.getProperty("GICS_import_check_for_data_already_exist");
		pst =  connection.prepareStatement(query);
		pst.setString(1,I_class_id);
		pst.setString(2,scrip_id);
		rs=pst.executeQuery();
		if(rs.next()) {			
			flag[0]=true;
		}else{
			query=null;
			query=ConnectInit.queries.getProperty("GICS_import_insert_into_class_scrip");
			pst =  connection.prepareStatement(query);
			Logging.debug("I_class_id>>>>>"+I_class_id);
			Logging.debug("scrip_id>>>>>"+scrip_id);
			pst.setString(1,I_class_id);
			pst.setString(2,scrip_id);
			pst.executeUpdate();
		}
		pst.close();
		}
	}catch(Exception e){
		Logging.error(" Error :"+e.getMessage());
	}	
	return flag;
}
	
}

