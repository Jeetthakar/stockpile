/*
 * Created on Oct 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import org.apache.log4j.Logger;


/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdjustDecimal {
	Logger Logging = Logger.getLogger(AdjustDecimal.class);
	String param;
	/**
	 * method returns string having double value cinsisting of two digits after decimal point.
	 */	
	public String indexcompose(String tp12) {
		if(tp12==null || tp12.equals("null")) 
			return tp12="0.00";
		if(tp12.indexOf(46)==-1)
		{
			tp12=tp12+".00";
		}
		if((tp12.length())==(tp12.indexOf(46)+2))
		{
			tp12=tp12+"0";			
		}
		if((tp12.length())>(tp12.indexOf(46)+3))
		{
					String tsr2=tp12.substring(tp12.indexOf(46),(tp12.indexOf(46)+3));
					tp12=tp12.substring(0,tp12.indexOf(46))+tsr2;
		}
		return tp12;
	}
	public String indexcompose4digit(String tp12) {
		if(tp12.indexOf(46)==-1)
		{
			tp12=tp12+".00";
		}
		if((tp12.length())==(tp12.indexOf(46)+2))
		{
			tp12=tp12+"0";			
		}
		if((tp12.length())>(tp12.indexOf(46)+5))
		{
					String tsr2=tp12.substring(tp12.indexOf(46),(tp12.indexOf(46)+5));
					tp12=tp12.substring(0,tp12.indexOf(46))+tsr2;
		}
		return tp12;
	}
	public String indexcompose1(String tp12) {
		if(tp12.indexOf(46)!=-1)
		{
			tp12=tp12.substring(0,tp12.indexOf(46));
		}
		return tp12;
	}
	/**
	 * method to convert double value in exponential format to double value 
	 * consisting of two digits after decimal point.
	 * input parameter is double no.
	 */
	public String shareholdingpatt(double noheld2)
	{
		String no2=new Double(noheld2).toString(); 
     	if(no2.indexOf('E')==-1)
     	{	
     		no2=new Double(noheld2).toString(); 
		}else{
		   	String ts2=no2.substring(0,no2.indexOf(46))+no2.substring((no2.indexOf(46))+1,(no2.indexOf('E')));
			int n2=Integer.parseInt(no2.substring(no2.indexOf('E')+1));
		if(ts2.length()>=(no2.indexOf(46)+n2))
		{
			if(ts2.length()==(no2.indexOf(46)+n2))
			{
				no2=ts2.substring(0,(no2.indexOf(46)+n2))+".00";
				
			}else{
				no2=ts2.substring(0,(no2.indexOf(46)+n2))+"."+ts2.substring((no2.indexOf(46)+n2));
			}
		}else{
			int diff=(no2.indexOf(46)+n2)-ts2.length();
			for(int q=0;q< diff;q++)
			{
				ts2=ts2+"0";
			}
			no2=ts2.substring(0,(no2.indexOf(46)+n2))+".00";
		}
		}	
		return no2;
	}
	/**
	 * method to convert double value in exponential format to double value 
	 * consisting of two digits after decimal point.
	 * input parameter is String.
	 */
	public String shareholdingpatt(String no2)
	{
		int n2=0,div=0;boolean flag=false;
		if(no2.indexOf('E')==-1)
     	{	
     	}else{
			String ts2=no2.substring(0,no2.indexOf(46))+no2.substring((no2.indexOf(46))+1,(no2.indexOf('E')));
			flag=(no2.charAt(no2.indexOf('E')+1)=='-');
			Logging.debug("flag in shareholdingpatt is "+flag);
			if(flag){
				n2=Integer.parseInt(no2.substring(no2.indexOf('E')+2));
			}else{
				n2=Integer.parseInt(no2.substring(no2.indexOf('E')+1));
			}
			if(flag==true){
				div=10*n2;
				double eval=((double)Double.parseDouble(ts2))/div;
				no2=(String)new Double(eval).toString();
			}else{
				if(ts2.length()>=(no2.indexOf(46)+n2))
				{
					if(ts2.length()==(no2.indexOf(46)+n2))
					{
						no2=ts2.substring(0,(no2.indexOf(46)+n2))+".00";
						
					}else{
						no2=ts2.substring(0,(no2.indexOf(46)+n2))+"."+ts2.substring((no2.indexOf(46)+n2));
					}
				}else{
					int diff=(no2.indexOf(46)+n2)-ts2.length();
					for(int q=0;q< diff;q++)
					{
						ts2=ts2+"0";
					}
					no2=ts2.substring(0,(no2.indexOf(46)+n2))+".00";
				}
			}
		}	
		return no2;
	}
	/**
	 * method to convert double value to double value 
	 * consisting of two digits after decimal point.
	 * input parameter is double no.
	 */
	public String twodigitdeci(double pheld1)
	{
		String p1=new Double(pheld1).toString();
		String sr2=p1.substring(p1.indexOf(46),(p1.indexOf(46)+3));
		p1=p1.substring(0,p1.indexOf(46))+sr2;
		return p1;
	}
	/**
	 * method to convert double value  to double value 
	 * consisting of two digits after decimal point.
	 * input parameter is string.
	 */
	public String twodigitdeci(String p1)
	{
		String sr2=p1.substring(p1.indexOf(46),(p1.indexOf(46)+3));
		p1=p1.substring(0,p1.indexOf(46))+sr2;
		return p1;
	}
	public String getParameterindexCompose()
	{
		if(param==null)
		{
			return this.param="stock_name";
		}else
		{
			return this.param;
		}
	}
	public void setParameterindexCompose(String str)
	{		
			this.param=str;		
	}
	/**
	 * Arrange numeric field.(eg. 12,323,021)
	 * @param str
	 * @return
	 */
	public static String ArrangeAsNumeric(String str){
		int count=0;	
		String arrstr=null;
		if(str==null || str.equals("null"))
			return str="0.00";
//		app.Logging.getDebug(" str is "+str+" str length is "+str.length());
		if(str.length()<3 || str.equals("0.0"))
			return str;
		if(str.indexOf(46)!=-1){
			String rstr=str.substring(str.indexOf(46));
			String str1=str.substring(0,str.indexOf(46));
//			app.Logging.getDebug(" str1 is "+str1+" str1 length is "+str1.length());
			if(str1.length()<=3 || str1.equals("0"))
				return str;
//			app.Logging.getDebug("in decimal case  str is "+str+" rstr is "+rstr);
				for(int i=str1.length();i>2;i-=3)
				{
					count++;
					if(count!=1){
						arrstr=str1.substring((i-3),i)+","+arrstr;
					}else{
						arrstr=str1.substring((i-3),i);
					}
				}
				arrstr=str1.substring(0,(str1.length()-((count)*3)))+","+arrstr;
				arrstr=arrstr+rstr;			
		}else{
				for(int i=str.length();i>2;i-=3)
				{
					count++;
					if(count!=1){
						arrstr=str.substring((i-3),i)+","+arrstr;
					}else{
						arrstr=str.substring((i-3),i);
					}
				}
				arrstr=str.substring(0,(str.length()-(count*3)))+","+arrstr;			
		}
		if((arrstr.charAt(0))==',')
			arrstr=arrstr.substring(1,(arrstr.length()));
		if(((arrstr.charAt(0))=='-') && ((arrstr.charAt(1))==',')){
			arrstr=arrstr.substring(2,(arrstr.length()));
			arrstr="-"+arrstr;		
		}
//		app.Logging.getDebug("afterarranging str is "+arrstr);
		return arrstr;
	}
	
}
