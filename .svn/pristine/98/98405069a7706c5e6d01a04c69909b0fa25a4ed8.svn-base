package mail;
import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.apache.log4j.Logger;

import app.Connect;

public class MyAutheticator extends Authenticator {
	Logger Logging = Logger.getLogger(MyAutheticator.class);
  public PasswordAuthentication getPasswordAuthentication() {
    String username="", password="";

  //  String result = JOptionPane.showInputDialog(
   //   "Enter 'username,password'");

   // StringTokenizer st = new StringTokenizer(result, ",");
    try
    {
    String resourceurl = Connect.getPropertiespath("Open16.gif");
    Properties rs = new Properties();
	rs.load(new FileInputStream(resourceurl+"resources/database.properties"));
    username =rs.getProperty("username");
    password =rs.getProperty("password");

  }catch(Exception f){Logging.error("Exception in smtp server path."+f);}
    
    return new PasswordAuthentication(username, password);
  }

}








