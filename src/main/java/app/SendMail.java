/*
 * Created on Mar 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.log4j.Logger;

/**
 * @author rahul
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendMail extends Object
{
	Logger Logging = Logger.getLogger(SendMail.class);
	public int email(String toEmail,String fromEmail,String body,String nfile)
	{
		String smtpServer = "192.168.0.1";
		String file = "c:\\" + nfile;
		if ( smtpServer == null || toEmail == null || fromEmail == null || body == null || file == null )
		{
		  // System.exit(1);
		}
		  
		  //--[ Obtain a session
		  try{
		    //--[ Set up the default parameters
	      Properties props = new Properties();
	      props.put("mail.transport.protocol", "smtp" );
	      props.put("mail.smtp.host", smtpServer );                
	      props.put("mail.smtp.port", "25" );
		  
	  	  //--[ Create the session and create a new mail message
	      Session mailSession = Session.getInstance( props );
	      Message msg = new MimeMessage( mailSession );
		
		
		    //--[ Set the FROM, TO, DATE and SUBJECT fields
	      msg.setFrom( new InternetAddress( fromEmail ) );
	      msg.setRecipients( Message.RecipientType.TO,
	                         InternetAddress.parse(toEmail) );
	      msg.setSentDate( new Date() );
	      msg.setSubject( "Test Mail with attachment" );
	      
	      //--[ Create the first part
	      Multipart mailBody = new MimeMultipart();
	      
	      MimeBodyPart mainBody = new MimeBodyPart();
	      mainBody.setText( body );
	      mailBody.addBodyPart(mainBody);

	      //--[ Create the second part with the attachment
	      FileDataSource fds = new FileDataSource( file );
	      MimeBodyPart mimeAttach = new MimeBodyPart();
	      mimeAttach.setDataHandler( new DataHandler(fds) );
	      mimeAttach.setFileName( fds.getName() );

	      mailBody.addBodyPart(mimeAttach);

	      //--[ Create the body of the mail
	      msg.setContent( mailBody ); 

	      Transport.send( msg );
	      
	      Logging.debug( "The email below was sent successfully" );
	      msg.writeTo( System.out );
		  
		  }catch(Exception E){
		    Logging.debug("exception....."+E );
		  }
		return 1;
	}
	public int email(String toEmail,String fromEmail,String body,String nfile,String sub)
	{
		String smtpServer = "192.168.0.1";
		String file = "c:\\eclipse\\workspace\\Income\\pages\\files\\" + nfile;
		if ( smtpServer == null || toEmail == null || fromEmail == null || body == null || file == null )
		{
		  // System.exit(1);
		}
		  
		  try{
		  Properties props = new Properties();
	      props.put("mail.transport.protocol", "smtp" );
	      props.put("mail.smtp.host", smtpServer );                
	      props.put("mail.smtp.port", "25" );
		  
	  	  //--[ Create the session and create a new mail message
	      Session mailSession = Session.getInstance( props );
	      Message msg = new MimeMessage( mailSession );
		
		
		    //--[ Set the FROM, TO, DATE and SUBJECT fields
	      msg.setFrom( new InternetAddress( fromEmail ) );
	      msg.setRecipients( Message.RecipientType.TO,
	                         InternetAddress.parse(toEmail) );
	      msg.setSentDate( new Date() );
	      msg.setSubject( sub );
	      
	      //--[ Create the first part
	      Multipart mailBody = new MimeMultipart();
	      
	      MimeBodyPart mainBody = new MimeBodyPart();
	      mainBody.setText( body );
	      mailBody.addBodyPart(mainBody);

	      //--[ Create the second part with the attachment
	      FileDataSource fds = new FileDataSource( file );
	      MimeBodyPart mimeAttach = new MimeBodyPart();
	      mimeAttach.setDataHandler( new DataHandler(fds) );
	      mimeAttach.setFileName( fds.getName() );

	      mailBody.addBodyPart(mimeAttach);

	      //--[ Create the body of the mail
	      msg.setContent( mailBody ); 

	      Transport.send( msg );
	      
	      Logging.debug( "The email below was sent successfully" );
	      msg.writeTo( System.out );
		  
		  }catch(Exception E){
		    Logging.debug("exception....."+E );
		    return 0;
		  }
		return 1;
	}
	/*
	public static void main(String args[])
	{
	    SendMail ob = new SendMail();
	    ob.email("vivek.chaturvedi@harriersys.com","sania_mirza@loveu.com","mail delivered","email.rtf");
	}
	*/
	
	
}
