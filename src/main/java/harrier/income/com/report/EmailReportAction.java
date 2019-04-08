/*
 * Created on Mar 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.MyAutheticator;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


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
		Vector vid;
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
	//changes made for makeing it active on 24 september 
	   username = username; 
		ActionForward conf = null;
		MakeExcel ob = new MakeExcel();
		int res = 0;
		String indExchName=null;
		PrintWriter out = null;
		int type=0;
		String filename = null;
		switch (r_type){
		case 1:
			//IndexCompose
			Logging.debug("value of var......"+varid);
			
			//ob.create_file(varid,r_type,vec,from,to_date,null);
			ob.create_file(varid, r_type,vec,null, null,null);
			//ob.create_file_stock_detail(varid,r_type,vec,null,null,null,null,null,null,null);
			Logging.debug("value for checking the variable");
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
		
		case 2: 
			//company wise weightage
			Logging.debug("In case 2 of emailreportaction  ");
			String var=(String)sess.getAttribute("var");
			type=2;
			ob.create_file(var,type,vec,null,null,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			
		case 3:
			// Indexwise
			Logging.debug("value of var......"+varid);
			//ob.create_file_stock_detail(varid,r_type,vec,from,to_date,null,null,null,null,null);
			ob.create_file(varid,r_type,vec,from, to_date, var1);
			Logging.debug("value for checking the variable");
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
		
		case 4:
			// DisplyIndex
			Logging.debug("value of var......"+varid);
			//ob.create_file_stock_detail(varid,r_type,vec,null,null,null,null,null,null,null);
			ob.create_file(varid,r_type,vec,null, null, null);
			Logging.debug("value for checking the variable");
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;	
		
		case 5:
			// Stock contri to Index change
			Logging.debug("value of var......"+varid);
			
			ob.create_file(varid,r_type,vec,from, to_date, null);
			//comment on 24jul2007//ob.create_file_stock_detail(varid,r_type,vec,from,to_date,null,null,null,null,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;	
			
		case 6:
			//Stock details
			String indName= (String)sess.getAttribute("indName");
			ob.create_file_stock_detail(varid,r_type,vec,from,to_date,var1,"",varid,varid,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			
		case 8:
			//LatestIndex
			Logging.debug("value of var......"+varid);
			
			ob.create_file(varid,r_type,vec,null, null, null);
			//ob.create_file_stock_detail(varid,r_type,vec,null,null,null,null,null,null,null);
			Logging.debug("value for checking the variable");
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			
		case 10:
			//Index compare ohlc
			Logging.debug("value of var......"+varid);
			vid=(Vector)sess.getAttribute("vec_ind");
			ob.create_file( vid, r_type, vec,from, to_date);
			//ob.create_file(varid,r_type,vec,null, null, null);
			//ob.create_file_stock_detail(varid,r_type,vec,null,null,null,null,null,null,null);
			Logging.debug("value for checking the variable");
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			
		case 15:
			//Index Correlation
			Logging.debug("value of var......"+varid);
			vid=(Vector)sess.getAttribute("ci1");
			ob.create_file( vid, r_type, vec, from, to_date);
			//ob.create_file( vid, r_type, vec,from, to_date);
			//ob.create_file(varid,r_type,vec,null, null, null);
			//ob.create_file_stock_detail(varid,r_type,vec,null,null,null,null,null,null,null);
			Logging.debug("value for checking the variable");
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;	
			
			
		case 12:
			// Index Performance
			Logging.debug("In case 12 of emailreportaction  ");
			String fr11=(String)sess.getAttribute("dt");
			ob.create_file("1",12,vec,fr11,null,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			
		case 14:
			// Index Returns Volatility 
			String[] arr = (String[])sess.getAttribute("indexList");
			from=(String)sess.getAttribute("from");
			to_date=(String)sess.getAttribute("to");
			//Vector vec14 = (Vector)sess.getAttribute("ci2");
			ob.create_file(arr,r_type,vec,from,to_date);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
					
		case 16:
			// Capital Change To Universe
			Logging.debug("value of var......"+varid);
			ob.create_file(varid,r_type,vec,from,to_date,varid);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			
		case 17:
			// Inactive StockList
			Logging.debug("In case 17 of emailreportaction  ");
			var=(String)sess.getAttribute("var");
			type=17;
			ob.create_file(var,type,vec,null,null,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			
		case 19:
			// Index Divisor
			filename = "IndexDivisor.xls" ;
			var1=(String)sess.getAttribute("varIndexId");
			System.out.print("IndexID>>>"+var1);
			from=(String)sess.getAttribute("from");
			to_date=(String)sess.getAttribute("to");
			//Vector vec19 = (Vector)sess.getAttribute("ci2");
			ob.create_file(var1,r_type,vec,from,to_date,null);
			res = email(to_email,username,desc_email,filename,subject_email);
			break;
					
		case 20:
		     // Index Performance PE, PB
			 Logging.debug("In case 20 of emailreportaction  ");
			  var=(String)sess.getAttribute("var");
			 ob.create_file(varid,20,vec,from,to_date,null);
			 res = email(to_email,username,desc_email,attach_email,subject_email);
			 break;
		
		case 21:
			//Stock List
			ob.create_file(varid,r_type,vec,from,to_date,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			 
			 
		case 22:
			// Stock divident
			Logging.debug("value of var......"+varid);
			indExchName= (String)sess.getAttribute("varr");
			//ob.create_file(varid,r_type,vec,from,to_date,null);
			ob.create_file_stock_detail(varid,r_type,vec,from,to_date,varid,null,indExchName,null,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
		
		case 23:
			// Stock Details from date
		 	Logging.debug("In case 23 of emialreportaction  ");
			String fDate=(String)sess.getAttribute("fDate");
			String index=(String)sess.getAttribute("index");
			 type=23;
			String filter =(String)sess.getAttribute("filter");
			String stock=(String)sess.getAttribute("stock");
			ob.create_file_stock_detail(null,type,vec,fDate,index,filter,stock,null,null,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
			
		case 24:
			// Moving Index
			filename = "IndexMovement.xls" ;
			var1=(String)sess.getAttribute("varIndexId");
			from=(String)sess.getAttribute("from");
			to_date=(String)sess.getAttribute("to");
			Vector vec24 = (Vector)sess.getAttribute("ci2");
			ob.create_file(var1,r_type,vec24,from,to_date,null);
			res = email(to_email,username,desc_email,filename,subject_email);
			break;
				
		case 25:
			// Traded Volume
			Logging.debug("value of var......"+varid);
			indExchName= (String)sess.getAttribute("varr");
			//ob.create_file(varid,r_type,vec,from,to_date,null);
			ob.create_file(varid,r_type,vec,from,to_date,indExchName);
			//ob.create_file_stock_detail(varid,r_type,vec,from,to_date,varid,null,indExchName,null,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
			break;
		
		case 26 :	
			
			res = email(to_email,username,desc_email,attach_email,subject_email);
			
			 break;	
				
		
		}
		/*if(r_type == 6){
			String indName= (String)sess.getAttribute("indName");
			ob.create_file_stock_detail(varid,r_type,vec,from,to_date,var1,"",varid,varid,null);
			res = email(to_email,username,desc_email,attach_email,subject_email);
		}
		else if(r_type == 7)
		{
			String filename = "IndexDivisor" + sid +".png" ;
			DatasetFactory1.DivisorReaddata(varid, from, to_date);
			filename = IndexDivisorDateWise.generatePieChart(sess,out,filename);
			res = email(to_email,username,desc_email,filename,subject_email);
		}
		else if(r_type == 11)
		{
			String filename = "IndexMovement.png" ;
			DatasetFactory1.DivisorReaddata(varid, from, to_date);
			filename = CMovingAverage.generatePieChart(sess,out);
			res = email(to_email,username,desc_email,filename,subject_email);
		}
		else if(r_type == 9)
		{
			String filename = "IndexComparison.png" ;
			String[] varray = (String[]) sess.getAttribute("varr");
			filename = CompareIndex1.generatePieChart(sess,out,varray, from, to_date,filename);
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
		}*/
		String str_ret = "/pages/reports/EmailConfirm.jsp?result="+res+"&sento="+to_email+"&cas="+cas+"&var="+varid;
		return conf= new ActionForward(str_ret);
	}
	public int email(String toEmail,String fromEmail,String body,String nfile,String sub)
	{
		app.Connect co;
		
		String resourceurl = app.Connect.getPropertiespath("Open16.gif");
		String smtpServer = null;
		try{
			Properties rs = new Properties();
			rs.load(new FileInputStream(resourceurl+"resources/database.properties"));
			smtpServer = rs.getProperty("smtp");
			Logging.debug("smtp path.."+smtpServer);
		}catch(Exception f){Logging.error("Exception in smtp server path."+f);}
		//smtpServer = "192.168.0.1";
		String file = app.Connect.getCoolMenuspath();
		
		file = file+"CoolMenus/" + nfile;
		if ( smtpServer == null || toEmail == null || fromEmail == null || body == null || file == null )
		{
		   return 0;
		}
		  
		  try{
		  Properties props = new Properties();
	      /*props.put("mail.transport.protocol", "smtp" );
	      props.put("mail.smtp.host", smtpServer );                
	      props.put("mail.smtp.port", "25" );*/
	        props.put("mail.transport.protocol", "smtp");//imp
			//props.put("mail.smtp.host","smtp.bizmail.yahoo.com");//imp
			props.put("mail.smtp.host","203.104.17.238");//For Reliance Server
			props.put("mail.smtp.port","25");//imp
		    props.put("mail.smtp.auth", "true"); //imp
		    Authenticator auth = new MyAutheticator() ;
	  	  //--[ Create the session and create a new mail message
		  Session mailSession = Session.getDefaultInstance(props, auth);
	      //Session mailSession = Session.getInstance( props );
	      Message msg = new MimeMessage( mailSession );
		
		
		    //--[ Set the FROM, TO, DATE and SUBJECT fields
	      msg.setFrom( new InternetAddress("harrier.stockpile@harriersys.com") );
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
	      
	      
	      
		   // Logging.debug(new Date());
	      //mailSession.setDebug(true); 
		     msg.saveChanges(); // implicit with send()
		    Transport transport = mailSession.getTransport("smtp");
		    transport.connect();
		    Transport.send(msg);
	      
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
