/**
 * MyDom
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.harrier.ftp;
import javax.xml.parsers.*;

import org.apache.log4j.Logger;
import org.w3c.dom.*;
import java.io.*;
import org.xml.sax.*;

import app.UpdateSeriesReadFile;

/**Class MyDom is used to parse the XML file and reads the contents of the file.DOM parser is used for parsing.
 * The XML File which is used for parsing by DOM parser,consists of IP Address,Username and Password.This IP Address,
 * Username and Password is passed to FTP server to download files from FTP server through FTP connection.In this way
 * Hardcoding of IP Address, Username and Password is avoided.
 */
public class MyDom
{
	static Logger Logging = Logger.getLogger(MyDom.class);
	static Document document;
	String s1,s2,s3;
	String xmlfile="D:/roses.xml";
	public  MyDom()
	{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder= factory.newDocumentBuilder();
			builder.setErrorHandler(  new MyErrorHandler());
			document=builder.parse(new File(xmlfile));
			//DocumentType doctype=document.getDoctype();
			//Logging.debug("The name of the DTD is:"+doctype.getName()+"\n");
			//Logging.debug("The system id of the doctype is:"+doctype.getSystemId()+"\n" );
			NodeList list1=document.getElementsByTagName("value1");
			NodeList list2=document.getElementsByTagName("value2");
			NodeList list3=document.getElementsByTagName("value3");
			int Listlength1=list1.getLength();//get the length of value1 tag.
			int Listlength2=list2.getLength();//get the length of value2 tag.
			int Listlength3=list3.getLength();//get the length of value3 tag.
			Logging.debug("The number of value1 element nodes are:"+Listlength1+"\n");
			for(int i=0;i<Listlength1;i++)
			{
				Logging.debug("The name of the node is:"+list1.item(i).getNodeName()+"\n");
				Node textNode1=list1.item(i).getLastChild();
				s1=textNode1.getNodeValue().trim();
				Logging.debug("The value of the textnode is:"+s1+"\n");
			}
			Logging.debug("The number of value2 element nodes are:"+Listlength2+"\n");
			for(int i=0;i<Listlength1;i++)
			{
				Logging.debug("The name of the node is:"+list2.item(i).getNodeName()+"\n");
				Node textNode2=list2.item(i).getLastChild();
				s2=textNode2.getNodeValue().trim();
				Logging.debug("The value of the textnode is:"+s2+"\n");
			}
		
			Logging.debug("The number of value3 element nodes are:"+Listlength3+"\n");
			for(int i=0;i<Listlength3;i++)
			{
				Logging.debug("The name of the node is:"+list3.item(i).getNodeName()+"\n");
				Node textNode3=list3.item(i).getLastChild();
				s3=textNode3.getNodeValue().trim();
				Logging.debug("The value of the textnode is:"+s3+"\n");
			}
			
		
		}catch(SAXParseException saxexception){}
		catch(SAXException saxEx){}
		catch(ParserConfigurationException pce){}
		catch(IOException ioe){}
	
	
	}
	static class MyErrorHandler implements ErrorHandler
	{
	
		public void fatalError(SAXParseException saxException)
		{	
			Logging.debug("fatal Error occured"+saxException);
	    }
		public void error(SAXParseException saxException) 
		{
			Logging.debug("error occured"+saxException);
		}
		public void warning(SAXParseException saxException)
		{
			Logging.debug("warning occured"+saxException);
		}

	}
}	