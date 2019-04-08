package com.tree;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import app.Connect;

import com.harrier.initializeation.ConnectInit;


/**
 * Created by IntelliJ IDEA. User: sachinb Date: Jan 5, 2004 Time: 7:41:24 PM To
 * change this template use Options | File Templates.
 */
public class TreeChache extends HttpServlet implements Runnable {
    Logger logger = null;
    Thread t;
    Connect c = ConnectInit.getConnect();

    public TreeChache() {
        logger = Logger.getLogger(TreeChache.class);
        PropertyConfigurator.configure(c.resourceurl
                + "resources/l4j3.properties");
    }

public void init(ServletConfig servletConfig) throws ServletException {
      //  logger.debug("Initializing tree");
        try{
         t=new Thread(this);
         t.start();
         t.join();
        }catch (Exception e) {
         //   e.printStackTrace();
        	 logger.debug(e);
            // TODO: handle exception
        }
       

        logger.debug("Tree Initialized");

    }
public void run(){   
        try {
         //   logger.debug("Refreshing Tree");             
            ExternalTree externalTree=new ExternalTree();  
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    new FileOutputStream(c.resourceurl+"temp"));
            outputStream.writeObject(externalTree.ht);
            outputStream.close();

            logger.debug("Tree Refreshed");
            logger.debug("Tree Refreshed");
           } catch(Exception ex) {
            logger.debug(ex.toString());
           }
   
}

}