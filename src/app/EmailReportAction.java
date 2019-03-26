/*
 * Created on Mar 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.demo.servlet.*;
import java.io.PrintWriter;
/**
 * @author rahul
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EmailReportAction extends Action 
{
	Logger Logging = Logger.getLogger(EmailReportAction.class);
	//public static final String FORWARD_start = "success";
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)
	{
		EmailReportForm form1 = (EmailReportForm)form;
		String varid = form1.getVarid();
		String to_email = form1.getTo();
		String cc_email = form1.getCc();
		int r_type = Integer.parseInt(form1.getSwitch_type());
		String cas = form1.getCas();
		Vector vec = new Vector();
		String to_date = form1.getTo_date();
		String from = form1.getFrom();
		String var1 = form1.getVar1();
		/**
		 * Get the vector for the corresponding report to mail.
		 * While mailing the report the report is converted into excel sheet
		 * using the session vector and the same file is sent to the addresee
		 * as an attachment.
		 * 
		 * If the report doesnt contain any numeric data then, only
		 * the .png picture file is send as an attachment.
		 * 
		 * Both the excel file and the .png file are created at real time while the 
		 * mail is being sent. They are copied in the directory CoolMenus.
		 * Files are temporarily stored for the time the mail is sent.
		 * 
		 * The host for the SMTP server is stored in the file "database.properties".
		 */
			HttpSession sess = request.getSession();
			String sid = sess.getId();
		try{	
			vec = (Vector) sess.getAttribute("ci2");
		}catch(Exception e){Logging.error("exception in email.ok"+e);}
		to_email = to_email + "," + cc_email;
		String subject_email = form1.getSubject();
		if(subject_email == null || subject_email == "")
		{
			subject_email = "No subject";
		}
		String desc_email = form1.getDesc();
		if(desc_email == null)
		{
			desc_email = "";
		}
		String attach_email = form1.getAtt();
		String username = form1.getUsername();
		username = username + "@stockpile.com";
		ActionForward conf = null;
		MakeExcel ob = new MakeExcel();
		int res = 0;
		PrintWriter out = null;
		if(r_type == 7)
		{
			String filename = "IndexDivisor" + sid +".png" ;
			//DatasetFactory1.DivisorReaddata(varid, from, to_date);
			//filename = IndexDivisorDateWise.generatePieChart(sess,out,filename);
			res = email(to_email,username,desc_email,filename,subject_email);
		}
		else if(r_type == 11)
		{
			String filename = "IndexMovement.png" ;
			//DatasetFactory1.DivisorReaddata(varid, from, to_date);
			//filename = CMovingAverage.generatePieChart(sess,out);
			res = email(to_email,username,desc_email,filename,subject_email);
		}
		else if(r_type == 9)
		{
			String filename = "IndexComparison.png" ;
			String[] varray = (String[]) sess.getAttribute("varr");
			//filename = CompareIndex1.generatePieChart(sess,out,varray, from, to_date,filename);
			res = email(to_email,username,desc_email,filename,subject_email);
		}
		else if(r_type == 10) 
		{
			Vector var2 = (Vector)sess.getAttribute("vec_ind");
			ob.create_file(var2,r_type,vec,from,to_date);
			res = email(to_email,username,desc_email,attach_email,subject_email);
		}
		else if(r_type == 13) 
		{
			ob.create_file(varid,r_type,vec,from,to_date,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
		}
		else if(r_type == 14) 
		{
			String[] arr = (String[])sess.getAttribute("arr1");
			ob.create_file(arr,r_type,vec,from,to_date);
			res = email(to_email,username,desc_email,attach_email,subject_email);
		}
		else if(r_type == 15) 
		{
			Vector var2 = (Vector)sess.getAttribute("ci1");
			ob.create_file(var2,r_type,vec,from,to_date);
			res = email(to_email,username,desc_email,attach_email,subject_email);
		}
		else if(r_type == 16)  
		{
			Logging.debug("value of var......"+varid);
			ob.create_file(varid,r_type,vec,from,to_date,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
		}
		
		else 
		{
			ob.create_file(varid,r_type,vec,from,to_date,var1);
			res = email(to_email,username,desc_email,attach_email,subject_email);
		}
		
		String str_ret = "/pages/EmailConfirm.jsp?result="+res+"&sento="+to_email+"&cas="+cas+"&var="+varid;
		return conf= new ActionForward(str_ret);
	}
	public int email(String toEmail,String fromEmail,String body,String nfile,String sub)
	{
		Connect co;
		
		String resourceurl = Connect.getPropertiespath("Open16.gif");
		String smtpServer = null;
		try{
			Properties rs = new Properties();
			rs.load(new FileInputStream(resourceurl+"resources/database.properties"));
			smtpServer = rs.getProperty("smtp");
			Logging.debug("smtp path.."+smtpServer);
		}catch(Exception f){Logging.error("Exception in smtp server path."+f);}
		//smtpServer = "192.168.0.1";
		String file = Connect.getCoolMenuspath();
		
		file = file+"CoolMenus/" + nfile;
		if ( smtpServer == null || toEmail == null || fromEmail == null || body == null || file == null )
		{
		   return 0;
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
	      
	      Logging.debug( "The email was sent successfully" );
	      msg.writeTo( System.out );
		  
		  }catch(Exception E){
		    Logging.error("exception....."+E );
		    return 0;
		  }
		  try{
		  File del_file = new File(file);
		  if(del_file.exists())
		  {
		  	del_file.delete();
		  }
		  }catch(Exception er){Logging.error("Error in deleting file.:"+er);}
		return 1;
	}
	
}
