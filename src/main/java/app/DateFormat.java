package app;

import java.text.SimpleDateFormat;

import java.util.Date;


public class DateFormat {

	public static String formatDate() {
        SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
        Date dt = new Date();
       
        return fr.format(dt).toString();
    }

	public static String formatDateto() {
        SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
       
        Date dt = new Date();
        dt.setDate(dt.getDate()); 
        
        return fr.format(dt).toString();
    }
	
	public static void main(String args[]){
		DateFormat dt=new DateFormat();
		String a= dt.formatDateto();
		//System.out.println("Date is : "+a);
	}
}
