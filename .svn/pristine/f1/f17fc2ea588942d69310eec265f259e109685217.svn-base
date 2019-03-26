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

import java.awt.*;
import java.util.*;


class MessThread implements Runnable {
	String filename;
	String name;
	Thread tmess;
	String folder;
	String destination;
	FTPClient ftp1= new FTPClient();	
	public static String str=null;
	public static long len;
	public static int nofiles=0;
	static long time;
	public static String rightnow=null;
	int filenm=1;

	MessThread(String threadname,String fldr,String dstn,FTPClient ftp){		
	
	folder=fldr;
	destination=dstn;
	ftp1=ftp;
	name=threadname;
		
	tmess= new Thread(this,name);
	
	tmess.start();//start the thread
	}
//	This is the entry point for the thread.
	public void run(){
		boolean flag=false;
		System.out.println("in run ");
		
		try{
			    System.out.println("path is" + folder);
			    System.out.println("befr ftp1");	
				ftp1.changeWorkingDirectory(folder);//change to the directory contaning files
				System.out.println("after change working directory");
				String name=ftp1.getSystemName();
				System.out.println("name is "+ name);
				System.out.println("after ftp1");
    			FTPFile[] files = ftp1.listFiles();// List the files in the directory
			    System.out.println("Number of files in dir: " + files.length);			    
			    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			    
			    int l=files.length;			   
			    int temp=0;			    
			    String div[]=new String[3];			    
			    int position=0;
			    int value=0;
			    
			    File directory = new File(destination);
			    
			 	File[] filesloc = directory.listFiles();
			 	int n = filesloc.length;
			 	System.out.println("file lenght "+files.length);
			    for (int i = 0; i < files.length; i++) 
			    {
			    
			     // Download a file from the FTP Server
			     //System.out.println("value of i is "+i);
			     System.out.print(df.format(files[i].getTimestamp().getTime()));
			     System.out.println("\t" + files[i].getName());
			     
			     str=files[i].getName();//File name
			     
			     System.out.println("File name is "+str);
			     
			     len=files[i].getSize();// Gives the files Size in bytes
			     
			     System.out.println("Size of file: "+len+"Bytes");			     
			    
			   
				 	int nFiles = 0;
				 					 	
				 	if(filesloc.length==0){
				 				 	
						  File file = new File(destination + File.separator + files[i].getName());
						  FileOutputStream fos = new FileOutputStream(file);
						  Date fileDate = files[i].getTimestamp().getTime();
						  filename=files[i].getName();
						  						  
						  System.out.println("Date of the file "+fileDate);
						 					  
						  ftp1.retrieveFile(files[i].getName(), fos);//writes file to the file o/p stream
						  //Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
						  rightnow = "" + new Date();
						  rightnow=rightnow.substring(11,19);
						  System.out.println("System Time is "+rightnow);
						  //rightnow=rightnow.substring(0,)
						  fos.close();//Closes the file o/p stream
						  file.setLastModified(fileDate.getTime());  
						  nofiles++;
						  System.out.println("No. of Files downloaded "+nofiles);
						  boolean extension=filename.endsWith("dat");
						 // boolean extension1=filename.endsWith("ind");
						  if(extension)
						  	new MessthrDB(filename,destination);
						  
						  						  
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
		 				System.out.println("Files in c:source "+files[i].getName()); // Will print only Files.			 	
					   File file = new File(destination + File.separator + files[i].getName());
					   FileOutputStream fos = new FileOutputStream(file);
					  Date fileDate = files[i].getTimestamp().getTime();
					  filename=files[i].getName();
					 					  
					  ftp1.retrieveFile(files[i].getName(), fos);//writes file to the file o/p stream
					  rightnow = "" + new Date();
					  rightnow=rightnow.substring(11,19);
					  System.out.println("System Time is "+rightnow);
					  fos.close();
					  file.setLastModified(fileDate.getTime());  
					  nofiles++;
					  System.out.println("No of Files downloaded "+nofiles);
					  System.out.println("calling mktthrdb");
					  tmess.sleep(300000);//Thread sleeps till new file is added to the ftp directory
					  boolean extension12=filename.endsWith("dat");
					  //boolean extension21=filename.endsWith("ind");
					  if(extension12)
					        new MessthrDB(filename,destination);
					  //else if(extension21)
					  	   //  new IndThrDB(filename,destination);     
					  System.out.println("Thread is sleeping ");
					  //t1.sleep(10000);//Thread sleeps till new file is added to the ftp directory
		 			}
				 	}
				 	   			    
			    }//outer for ends
			   
			   			 	
			 System.out.println(n); // Will print Files + Directories
			 
			}
	
	catch (IOException e){
		System.out.println(name +"Interrupted");
		}
	/*catch (InterruptedException e){
		System.out.println(name +"Interrupted");
		}*/
	catch (Exception e){
		System.out.println(e.getMessage());}
		flag=true;
	}
	}


public class ClientMess extends Thread{
 Logger Logging = Logger.getLogger(ClientMess.class);
	private String server,username,password,folder,destinationFolder;
 FTPClient ftp;String filename;

 
 public ClientMess(String server, String username, String password,String folder, String destinationFolder) {
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
		 catch(IOException ioe){Logging.debug(ioe);}
		 finally {
			   if (ftp.isConnected()) {
			    try {
			     ftp.disconnect();
			    }
			     catch (IOException f) {
			    	 Logging.debug(f);
			     // do nothing
			    }
			   }
			  }
 }
 
 public  void retrieveFiles()  
 {
	 
  try {
	  
	  	new MessThread("One",folder,destinationFolder,ftp);	//threads start here	     				
	  	Thread.sleep(64500000);//If the last for the day is downloaded the thread sleeps for the next day	
	  	retrieveFiles(); 	   	
  }
  	
  	catch(Exception eee){
  		Logging.debug(eee);
  	}	

 }
}


