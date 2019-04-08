/*
 * Created on Mar 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Vivek
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


public class RefreshCache implements Runnable {
	Logger Logging = Logger.getLogger(RefreshCache.class);
	Connect c = ConnectInit.getConnect();
	boolean status=false;
    
    public RefreshCache() {        
       
    }
    public RefreshCache getCache(){
        return this;        
    }
    public void refresh(){
        try{
        Thread t=new Thread(this);
        t.start();        
        }catch (Exception e) {
        //    e.printStackTrace();
        	 Logging.debug(e);
        	// TODO: handle exception
        }
    }
    
    public void run(){
        
        while(true){
            try {
                //Logging.debug("Refreshing Tree");             
                
                /*
                 * Code for Tree Refresh commented on 26/10/07.
                 * For Improoving performance
                 */
            	/*
            	 ExternalTree externalTree=new ExternalTree();
                Logging.debug("Tree Refreshed"); 
                Thread.sleep(300000);
                */
               } catch(Exception ex) {
                //Logging.debug(ex.toString());
               }
              
        }        
    }
    public boolean newCache()
    {
    try {
    	Logging.debug("Inside newCache()");
    ExternalTree externalTree=new ExternalTree();  
    File file = new File(c.resourceurl+"temp");
    if(file.exists())
    {
     file.delete();
     Logging.debug("File Deleted");
    }
    ObjectOutputStream outputStream = new ObjectOutputStream(
            new FileOutputStream(file));
    outputStream.writeObject(externalTree.ht);
    outputStream.close();
    Logging.debug("File Created");
    status=true;
    }catch(Exception e){
    	Logging.debug("Error while caching "+e.getMessage());
    	status=false;
    }
    return status;
    }
}
