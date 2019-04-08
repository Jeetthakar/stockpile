/*
 * Created on Feb 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;
import java.util.*;
import java.io.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAsFile {
	static Logger log;
	static{
		log =Logger.getLogger(CalculateCorrelation.class);
		PropertyConfigurator.configure("resources/log4j.properties");
	 	
	}
	public static String filename=null; 
	public void WriteIntxtFile(String fname,String heading,String column,Vector v){
		try{
				String s1=null;
		//		app.Connect con=new app.Connect();
				Connect con = ConnectInit.getConnect();
				String path=con.getCoolMenuspath();
				log.debug("coolmenu path is "+path);
				filename=path+"/"+fname;
				File fl=new File(filename);
				FileOutputStream fout=new FileOutputStream(fl);
				byte[] b=new byte[heading.length()];
				b=heading.getBytes();
				fout.write(b);
				int colcount=(int)Integer.parseInt(column);
				for(int i=0;i<v.size();i+=colcount){
					int k=i+1;
					for(int j=0;j<=colcount;j++){						
						 s1=s1+(String)v.get(k);
						 k++;
					}
					byte[] b1=new byte[s1.length()];
					b1=heading.getBytes();
					fout.write(b1);
				}
				
		}catch(Exception e){
			log.debug("Error : "+e.getMessage());
		}
	}
	public  void flushFileName()
	{
		filename=null;
	}
	public static void main(String[] args) {
	}
}
