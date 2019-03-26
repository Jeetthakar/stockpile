/*
 * Created on Dec 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import org.apache.log4j.Logger;

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Testdate {
	
Logger Logging = Logger.getLogger(Testdate.class);
	
	public String getdate(String filename){
		String filedate=filename;
		int id=filedate.lastIndexOf("QE");
		String date=filedate.substring(id);
		
		String finaldate1=null;
		String finaldate=null;
		String dd=date.substring(2,4);
		Logging.debug("dd"+dd);
		String mm=date.substring(4,6);
		Logging.debug("mm"+mm);
		String yy=date.substring(6,8);
		Logging.debug("yy"+yy);
		finaldate1=dd+"-"+mm+"-"+"20"+yy;
		Logging.debug("finaldate1"+finaldate1);
		finaldate=finaldate1.trim();
			
		return finaldate;
	}
	

	public static void main(String[] args) {
		
		Testdate tdt=new Testdate();
		String filename="D:/eclipse/workspace1/Stockpile/WebContent/WEB-INF/classes/CoolMenus/QE031207";
		String result=tdt.getdate(filename);
		
	//	Logging.debug("result date is  ==>"+result);
	//	Logging.debug("hi");
		
		
		
	}
}
