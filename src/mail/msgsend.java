/*
 * @(#)msgsend.java	1.20 07/07/06
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package mail;
import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;

import app.Connect;

/**
 * Demo app that shows how to construct and send an RFC822
 * (singlepart) message.
 *
 * XXX - allow more than one recipient on the command line
 *
 * @author Max Spivak
 * @author Bill Shannon
 */

public class msgsend {
Logger Logging = Logger.getLogger(msgsend.class);
   public void sendmail(String username,String to1 ,String userpassword) {
	String  to, subject = null, from = null, 
		cc = null, bcc = null, url = null;
	String mailhost = null;
	String mailer = "msgsend";
	String file = null;
	String protocol = "smtp", host = null, user = null, password = null;
	String record = null;	// name of folder in which to record mail
	boolean debug = false;
	BufferedReader in =
			new BufferedReader(new InputStreamReader(System.in));
	int optind;

	// inbox_mail();
	try{
		to =to1.concat(",info@harriersys.com") ;
		subject ="From Harrier Stockpile - Welcome! Email Verification Required";
	  ///dchandrashkehar@yahoo.com
	//	Properties props = System.getProperties();
		Properties props = new Properties();
		//props.setProperty("mail.store.protocol","pop3");//imp
		props.put("mail.transport.protocol", "smtp");//imp
		//props.put("mail.smtp.host","smtp.bizmail.yahoo.com");//imp
		props.put("mail.smtp.host","203.104.17.238");// For Reliance server
		props.put("mail.smtp.port","25");//imp
	    props.put("mail.smtp.auth", "true"); //imp
	  //  props.put("mail.smtp.class", "com.acme.SMTPTRANSPORT");//imp
	    mailhost="203.104.17.238";
	    System.setProperty("mail.smtp.port","25");
	    //System.setProperty("mail.smtp.host","smtp.bizmail.yahoo.com");
	    System.setProperty("mail.smtp.host","203.104.17.238");
	    
	    Authenticator auth = new MyAutheticator() ;
	    //Session session = Session.getInstance(props, auth);
	    Session session = Session.getDefaultInstance(props, auth) ;//getInstance(props, auth);
	    Provider[] pro= session.getProviders() ;
	    
	    for(int i=0;i<pro.length ;i++)
	    {
	    	Logging.debug("providers...."+pro[i]);
	    	session.setProvider(pro[i]);
	    	
	    }
	   
	    Message msg = new MimeMessage(session);
	  
	    msg.setFrom(new InternetAddress("harrier.stockpile@harriersys.com"));
	    msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
	    
	    
	    msg.setSubject(subject);
	    
//	  Create your new message part
	    BodyPart messageBodyPart = new MimeBodyPart();
	    String htmlText = "<H1>Hi</H1>"+username+",";
	    messageBodyPart.setContent(htmlText, "text/html");
	    
	    String resourceurl = Connect.getPropertiespath("Open16.gif");
		   Properties rs = new Properties();
		   rs.load(new FileInputStream(resourceurl+"resources/database.properties"));
		   String server=rs.getProperty("server");
           server=server.trim().concat("pages/confirmemail.jsp".trim()).trim();

	    String Text2="<H5>your User id:</H5>"+to1+"<br>"+"and "+"<H5>your Password:</H5>"+userpassword+
	    "<br><h5>please click below link to confirm your registration</h5>"+
//	    "<br><h5><a href='"+server+"' target='stockpile'>http://harrierstockpile.com/Stockpile/pages/confirmemail.jsp</a></h5>"
	    "<br><h5><a href='"+server+"' target='stockpile'>http://team.harriersys.com/Stockpile/pages/confirmemail.jsp</a></h5>"
	    

	    ;
	   
	    
	    messageBodyPart.setContent(Text2, "text/html");
	    
//	     Create a related multi-part to combine the parts
	    MimeMultipart multipart = new MimeMultipart("related");
	    multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
	    
	    
//	     Create part for the image
	 //   messageBodyPart = new MimeBodyPart();

//	     Fetch the image and associate to part
	  //  DataSource fds = new FileDataSource(file);
	 //   messageBodyPart.setDataHandler(new DataHandler(fds));
	 //  messageBodyPart.setHeader("Content-ID","<memememe>");

//	     Add part to multi-part
	  //  multipart.addBodyPart(messageBodyPart);

//	     Associate multi-part with message
	  //  message.setContent(multipart);

	    
	    
	    
	  //  msg.
	   /* msg.setContent("hi  " +username+"," +  
	    		
	    		"your User id:" +to1+ 
	    		
	    		"your Password:"+userpassword+"/n"+
	    		" To activate your account please click the following link"+"/n"+"/n"+
	    		"http://localhost:8081/Stockpile_Web/pages/checkmail.jsp"
	    		, "text/html");*/
	    
	    msg.setHeader("X-Mailer", mailer);
	    msg.setSentDate(new Date());
	   // Logging.debug(new Date());
	    session.setDebug(true); 
	     msg.saveChanges(); // implicit with send()
	    Transport transport = session.getTransport("smtp");
	    transport.connect();
	    Transport.send(msg);
	    
	    
	    
	 //   transport.close();
	    // send the thing off
	 //   Transport.send(msg);

	    Logging.debug("\nMail was sent successfully.");

	    // Keep a copy, if requested.

	    if (record != null) {
		// Get a Store object
		Store store = null;
		if (url != null) {
		    URLName urln = new URLName(url);
		    store = session.getStore(urln);
		    store.connect();
		} else {
		    if (protocol != null)		
			store = session.getStore("smtp");
		    else
			store = session.getStore();

		    // Connect
		    if (host == null || user == null || password == null)
			store.connect(host,"harrier.stockpile", "system1234");
		    else
			store.connect();
		}

		// Get record Folder.  Create if it does not exist.
		Folder folder = store.getFolder(record);
		if (folder == null) {
		    System.err.println("Can't get record folder.");
		   // System.exit(1);
		}
		if (!folder.exists())
		    folder.create(Folder.HOLDS_MESSAGES);

		Message[] msgs = new Message[1];
		msgs[0] = msg;
		folder.appendMessages(msgs);

		Logging.debug("Mail was recorded successfully.");
	    }

	} catch (Exception e) {
	   // e.printStackTrace();
		Logging.debug(e);
	}
    }

    private void inbox_mail() {

    	Properties prop1 = new Properties();
    	//prop1.setProperty("mail.pop3.socketFactory.fallback ", "false");
    	prop1.setProperty("mail.pop3.port", "110");
    	//prop1.setProperty("mail.pop3.socketFactory.port", "110");
    	prop1.setProperty ("mail.host", "pop.mail.yahoo.com"); 
    	//prop1.setProperty("mail.pop3.host","pop.mail.yahoo.com");
    	prop1.put("mail.store.protocol", "pop3");
    	Authenticator auth = new MyAutheticator() ;
	    Session session = Session.getInstance(prop1, auth);
	   
    	//Session session = Session.getDefaultInstance(prop1);
    	Store s = null;
    	Folder inbox=null;
		try {
			
			session.setDebug(true);
			s =session.getStore("pop3");

		} catch (NoSuchProviderException e) {
			Logging.debug(e);
		//	e.printStackTrace();
		}
    	
    	Logging.debug("Connecting...");
    	try {
		s.connect();
		//	Logging.debug("Connected...");
			inbox = s.getFolder("INBOX");
		//	inbox.open(Folder.READ_ONLY);
			inbox.open(Folder.READ_WRITE);
			Message m=inbox.getMessage(1);
			Logging.debug(inbox.getMessageCount()) ;
			inbox.close(true );
			s.close();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
    	Logging.debug("Connected...");
    	
    	

	
}
    
    
    public void sendpassword(String to1 ,String userpassword) {
    	String  to, subject = null, from = null, 
    		cc = null, bcc = null, url = null;
    	String mailhost = null;
    	String mailer = "msgsend";
    	String file = null;
    	String protocol = "smtp", host = null, user = null, password = null;
    	String record = null;	// name of folder in which to record mail
    	boolean debug = false;
    	BufferedReader in =
    			new BufferedReader(new InputStreamReader(System.in));
    	int optind;

    	// inbox_mail();
    	try{
    		to =to1.concat(",info@harriersys.com") ;
    		subject ="From Harrier Stockpile -Password Request";
    	  ///dchandrashkehar@yahoo.com
    	//	Properties props = System.getProperties();
    		Properties props = new Properties();
    		//props.setProperty("mail.store.protocol","pop3");//imp
    		props.put("mail.transport.protocol", "smtp");//imp
    		//props.put("mail.smtp.host", "smtp.mail.yahoo.com");//imp
    		props.put("mail.smtp.host", "203.104.17.238");//imp , change by Manoj Adekar
    		
    	    props.put("mail.smtp.port","25");//imp
    	    props.put("mail.smtp.auth", "true"); //imp
    	  //  props.put("mail.smtp.class", "com.acme.SMTPTRANSPORT");//imp
    	   // mailhost="smtp.mail.yahoo.com";
    	    mailhost="203.104.17.238";
    	    System.setProperty("mail.smtp.port", "25");
    	    System.setProperty("mail.smtp.host","203.104.17.238");
    	    
    	    Authenticator auth = new MyAutheticator() ;
    	    //Session session = Session.getInstance(props, auth);
    	    Session session = Session.getDefaultInstance(props, auth) ;//getInstance(props, auth);
    	    Provider[] pro= session.getProviders() ;
    	    
    	    for(int i=0;i<pro.length ;i++)
    	    {
    	    	Logging.debug("providers...."+pro[i]);
    	    	session.setProvider(pro[i]);
    	    	
    	    }
    	   
    	    Message msg = new MimeMessage(session);
    	  
    	    msg.setFrom(new InternetAddress("info@harriersys.com"));
    	    msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
    	    
    	    
    	    msg.setSubject(subject);
    	    
//    	  Create your new message part
    	    BodyPart messageBodyPart = new MimeBodyPart();
    	    String htmlText = "<H1>Hi User ,</H1>";
    	    messageBodyPart.setContent(htmlText, "text/html");

    	    String Text2="<H5>Hi User,</H5><br><H5>your Password is :</H5>"+userpassword 
    	    ;
    	   
    	    
    	    messageBodyPart.setContent(Text2, "text/html");
    	    
//    	     Create a related multi-part to combine the parts
    	    MimeMultipart multipart = new MimeMultipart("related");
    	    multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);
    	    
    	    
//    	     Create part for the image
    	 //   messageBodyPart = new MimeBodyPart();

//    	     Fetch the image and associate to part
    	  //  DataSource fds = new FileDataSource(file);
    	 //   messageBodyPart.setDataHandler(new DataHandler(fds));
    	 //  messageBodyPart.setHeader("Content-ID","<memememe>");

//    	     Add part to multi-part
    	  //  multipart.addBodyPart(messageBodyPart);

//    	     Associate multi-part with message
    	  //  message.setContent(multipart);

    	    
    	    
    	    
    	  //  msg.
    	   /* msg.setContent("hi  " +username+"," +  
    	    		
    	    		"your User id:" +to1+ 
    	    		
    	    		"your Password:"+userpassword+"/n"+
    	    		" To activate your account please click the following link"+"/n"+"/n"+
    	    		"http://localhost:8081/Stockpile_Web/pages/checkmail.jsp"
    	    		, "text/html");*/
    	    
    	    msg.setHeader("X-Mailer", mailer);
    	    msg.setSentDate(new Date());
    	   // Logging.debug(new Date());
    	    session.setDebug(true); 
    	     msg.saveChanges(); // implicit with send()
    	    Transport transport = session.getTransport("smtp");
    	    transport.connect();
    	    Transport.send(msg);
    	    
    	    
    	    
    	 //   transport.close();
    	    // send the thing off
    	 //   Transport.send(msg);

    	    Logging.debug("\nMail was sent successfully.");

    	    // Keep a copy, if requested.

    	    if (record != null) {
    		// Get a Store object
    		Store store = null;
    		if (url != null) {
    		    URLName urln = new URLName(url);
    		    store = session.getStore(urln);
    		    store.connect();
    		} else {
    		    if (protocol != null)		
    			store = session.getStore("smtp");
    		    else
    			store = session.getStore();

    		    // Connect
    		    if (host == null || user == null || password == null)
    			store.connect(host,"info", "cmsahab");
    		    else
    			store.connect();
    		}

    		// Get record Folder.  Create if it does not exist.
    		Folder folder = store.getFolder(record);
    		if (folder == null) {
    		    System.err.println("Can't get record folder.");
    		//    System.exit(1);
    		}
    		if (!folder.exists())
    		    folder.create(Folder.HOLDS_MESSAGES);

    		Message[] msgs = new Message[1];
    		msgs[0] = msg;
    		folder.appendMessages(msgs);

    		Logging.debug("Mail was recorded successfully.");
    	    }

    	} catch (Exception e) {
    	   // e.printStackTrace();
    		Logging.debug(e);
    	}
        }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public static String collect(BufferedReader in) throws IOException {
	String line;
	StringBuffer sb = new StringBuffer();
	while ((line = in.readLine()) != null) {
	    sb.append(line);
	    sb.append("\n");
	}
	return sb.toString();
    }
}
