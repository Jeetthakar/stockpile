/**
 * 
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextField;
import org.apache.commons.net.ftp.*;
import org.apache.log4j.Logger;

import app.UpdateSeriesReadFile;

import java.awt.*;
import java.util.*;


class SECThread implements Runnable {
	Logger Logging = Logger.getLogger(SECThread.class);
	String filename;
	String name;
	Thread tsec;
	String folder;
	String destination;
	FTPClient ftp1= new FTPClient();	
	public static String str=null;
	public static long len;
	public static int nofiles=0;
	static long time;
	public static String rightnow=null;
	int filenm=1;

	SECThread(String threadname,String fldr,String dstn,FTPClient ftp){		
	
	folder=fldr;
	destination=dstn;
	ftp1=ftp;
	name=threadname;
		
	tsec= new Thread(this,name);
	
	tsec.start();//start the thread
	}
//	This is the entry point for the thread.
	public void run(){
		boolean flag=false;
		Logging.debug("in run ");
		
		try{
			    Logging.debug("path is" + folder);
			    Logging.debug("befr ftp1");	
				ftp1.changeWorkingDirectory(folder);//change to the directory contaning files
				Logging.debug("after change working directory");
				String name=ftp1.getSystemName();
				Logging.debug("name is "+ name);
				Logging.debug("after ftp1");
    			FTPFile[] files = ftp1.listFiles();// List the files in the directory
			    Logging.debug("Number of files in dir: " + files.length);			    
			    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			    
			    int l=files.length;			   
			    int temp=0;			    
			    String div[]=new String[3];			    
			    int position=0;
			    int value=0;
			    
			    File directory = new File(destination);
			    
			 	File[] filesloc = directory.listFiles();
			 	int n = filesloc.length;
			 	Logging.debug("file lenght "+files.length);
			 	String filename="Securities.dat";
			    for (int i = 0; i < files.length; i++) 
			    {
			    
			     // Download a file from the FTP Server
			     //Logging.debug("value of i is "+i);
			     System.out.print(df.format(files[i].getTimestamp().getTime()));
			     Logging.debug("\t" + files[i].getName());
			     
			     str=files[i].getName();//File name
			     
			     Logging.debug("File name is "+str);
			     
			     len=files[i].getSize();// Gives the files Size in bytes
			     
			     Logging.debug("Size of file: "+len+"Bytes");			     
			    
			   
				 	int nFiles = 0;
				 					 	
				 	if (str.equals(filename)) {
				 				 	
						  File file = new File(destination + File.separator + files[i].getName());
						  FileOutputStream fos = new FileOutputStream(file);
						  Date fileDate = files[i].getTimestamp().getTime();
						  filename=files[i].getName();
						  						  
						  Logging.debug("Date of the file "+fileDate);
						 					  
						  ftp1.retrieveFile(files[i].getName(), fos);//writes file to the file o/p stream
						  //Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
						  rightnow = "" + new Date();
						  rightnow=rightnow.substring(11,19);
						  Logging.debug("System Time is "+rightnow);
						  //rightnow=rightnow.substring(0,)
						  fos.close();//Closes the file o/p stream
						  file.setLastModified(fileDate.getTime());  
						  nofiles++;
						  Logging.debug("No. of Files downloaded "+nofiles);
						  boolean extension=filename.endsWith("sec");
						 // boolean extension1=filename.endsWith("ind");
						
						  	new SecThrDB(filename,destination);
						  
						  						  
				 	}
				 	else{
				 		int checkflag=0;
				 	
				 	for (int t = 0; t < filesloc.length; t++) {
				 		if(filesloc[t].isFile() == true)
				 			nFiles++;
				 		
				 			String dwfile=filesloc[t].getName();	
				 			
				 		
				 			if(dwfile.equals(files[i].getName())){
				 				
				 				checkflag=1;
				 				break;
				 			}
				 			
				 			 
				 		}//for ends
				 	if(checkflag==0){
		 				Logging.debug("Files in c:source "+files[i].getName()); // Will print only Files.			 	
					   File file = new File(destination + File.separator + files[i].getName());
					   FileOutputStream fos = new FileOutputStream(file);
					  Date fileDate = files[i].getTimestamp().getTime();
					  filename=files[i].getName();
					 					  
					  ftp1.retrieveFile(files[i].getName(), fos);//writes file to the file o/p stream
					  rightnow = "" + new Date();
					  rightnow=rightnow.substring(11,19);
					  Logging.debug("System Time is "+rightnow);
					  fos.close();
					  file.setLastModified(fileDate.getTime());  
					  nofiles++;
					  Logging.debug("No of Files downloaded "+nofiles);
					  Logging.debug("calling mktthrdb");
					  //tsec.sleep(300000);//Thread sleeps till new file is added to the ftp directory
					  boolean extension12=filename.endsWith("sec");
					  //boolean extension21=filename.endsWith("ind");
					  if(extension12)
					        new SecThrDB(filename,destination);
					  //else if(extension21)
					  	   //  new IndThrDB(filename,destination);     
					  Logging.debug("Thread is sleeping ");
					  //t1.sleep(10000);//Thread sleeps till new file is added to the ftp directory
		 			}
				 	}
				 	   			    
			    }//outer for ends
			   
			   			 	
			 Logging.debug(n); // Will print Files + Directories
			 
			}
	
	catch (IOException e){
		Logging.debug(name +"Interrupted");
		}
	/*catch (InterruptedException e){
		Logging.debug(name +"Interrupted");
		}*/
	catch (Exception e){
		Logging.debug(e.getMessage());}
		flag=true;
	}
	}


public class ClientSec extends Thread{
	Logger Logging = Logger.getLogger(ClientSec.class);
	private String server,username,password,folder,destinationFolder;
 FTPClient ftp;String filename;

 
 public ClientSec(String server, String username, String password,String folder, String destinationFolder) {
 	this.server=server;
 	this.username=username;
 	this.password=password;
 	this.folder=folder;
 	this.destinationFolder=destinationFolder;
 	
 	//constructor
 	ftp = new FTPClient();
 	
 }
 
 public void run(){
	 try {	 
		   ftp.connect(server);
		   ftp.login(username, password);//Login using Username and Password 
		   Logging.debug("Connected to " + server + ".");	   
		   retrieveFiles();
		 }
		 catch(IOException ioe){}
		 finally {
			   if (ftp.isConnected()) {
			    try {
			     ftp.disconnect();
			    }
			     catch (IOException f) {
			     // do nothing
			    }
			   }
			  }
 }
 
 public  void retrieveFiles()  
 {
	 
  try {
	  
	  	new SECThread("One",folder,destinationFolder,ftp);	//threads start here	     				
	  	Thread.sleep(64500000);//If the last for the day is downloaded the thread sleeps for the next day	
	  	retrieveFiles(); 	   	
  }
  	
  	catch(Exception eee){}	

 }
}


