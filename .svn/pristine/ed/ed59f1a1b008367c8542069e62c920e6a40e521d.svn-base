/*
 * Created on Oct 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;
import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
import com.yworks.yguard.ObfuscatorTask.FieldSection;
/**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FieldSort {
	Logger Logging = Logger.getLogger(FieldSection.class);
	String min;
	int len=0,row=0;
	double max,small;
	static int count=0;
	static int count1=0;
	static int count2=0;
	static int count3=0;
	public static int count4=0,count5=0,count6=0,count7=0,count8=0,count9=0,count10=0,count11=0,count12=0,count13=0,count14=0,count15=0,count16=0,count17=0;
	public static String oldcode;
	static Connect con1 = ConnectInit.getConnect();
	public Vector getSortedFieldDesc(Vector ori,int fieldno,int colno)
	{
		len=ori.size();
		Logging.debug(len);
		row=len/colno;
		String temp[]=new String[20]; 
		Vector sortvector =new Vector();
		//getDebug("original vector");
		/*for(int i=0;i<ori.size();i++)
		{
			getDebug((String)ori.get(i));
		}*/
		for(int q=0;q<=(row-1);q++)
		{
		for(int i=0;i<=(row-2);i++)
		{
			max=Double.parseDouble((String)ori.get((i*colno)+fieldno));
			//getDebug(max);
			for(int j=i+1;j<=(row-1);j++)
			{				
				int pos=(j*colno)+fieldno;
				double d=Double.parseDouble((String)ori.get(pos));
				//getDebug(d);
				if(d>max)
				{
					//getDebug("Inside if of sorting");
					int m=(i*colno);
					int n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						temp[k]=(String)ori.get(m);
						//getDebug(temp[k]);
						m++;
					}
					m=(i*colno);
					n=(j*colno);
					//getDebug(m);
					//getDebug(n);
					for(int k=0;k<colno;k++)
					{
						
						String str= (String)ori.get(n);
						//getDebug(str);
						ori.remove(m);
						ori.add(m,str);
						//getDebug((String)ori.get(m));
						//getDebug((String)ori.get(k));
						m++;n++;
					}
					m=0;
					n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						ori.remove(n);
						ori.add(n,temp[m]);
						m++;n++;
					}
					//getDebug((String)ori.get(0));
					//getDebug((String)ori.get(1));
					//getDebug((String)ori.get(2));
				}
			}
		}
		}
		/*getDebug("Sorted vector");
		for(int i=0;i<ori.size();i++)
		{
			getDebug((String)ori.get(i));
		}*/
		
		return ori;
	}
	public Vector getSortedFieldAsc(Vector ori,int fieldno,int colno)
	{
		len=ori.size();
		Logging.debug("vector length is "+len);
		row=len/colno;
		String temp[]=new String[20]; 
		Vector sortvector =new Vector();
		//app.Logging.getDebug("original vector");
		/*for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		for(int q=0;q<=(row-1);q++)
		{
		for(int i=0;i<=(row-2);i++)
		{
			small=Double.parseDouble((String)ori.get((i*colno)+fieldno));
			//app.Logging.getDebug(max);
			for(int j=i+1;j<=(row-1);j++)
			{				
				int pos=(j*colno)+fieldno;
				double d1=Double.parseDouble((String)ori.get(pos));
				if(d1<small)
				{
					int m1=(i*colno);
					int n1=(j*colno);
					for(int k=0;k<colno;k++)
					{
						temp[k]=(String)ori.get(m1);
						m1++;
					}
					m1=(i*colno);
					n1=(j*colno);
					for(int k=0;k<colno;k++)
					{
						
						String str= (String)ori.get(n1);
						ori.remove(m1);
						ori.add(m1,str);
						m1++;n1++;
					}
					m1=0;
					n1=(j*colno);
					for(int k=0;k<colno;k++)
					{
						ori.remove(n1);
						ori.add(n1,temp[m1]);
						m1++;n1++;
					}					
				}
			}
		}
		}
		return ori;
	}
	public Vector SetOrderSort(Vector ori,int fieldno,int colno)
	{
		count++;
		len=ori.size();
		//app.Logging.getDebug(len);
		row=len/colno;
		String str1=(String)ori.get(fieldno);
		//app.Logging.getDebug(str1);
		int last=(((row-1)*colno)+fieldno);
		//app.Logging.getDebug(last);
		String str2=(String)ori.get(last);
		//app.Logging.getDebug(str2);
		int compare=(str1.compareTo(str2));
		if((count%2!=0))
		{
			ori=getSortedFieldStringAsc(ori,fieldno,colno);
		}
		if((count%2==0))
		{
			ori=getSortedFieldStringDesc(ori,fieldno,colno);
		}    
		return ori;
	}
	public int count(){
		return count5++;
	}
	public Vector SetOrderSort1(Vector ori,int fieldno,int colno)
	{
		count3++;
		len=ori.size();
		//app.Logging.getDebug(len);
		row=len/colno;
		String str1=(String)ori.get(fieldno);
		//app.Logging.getDebug(str1);
		int last=(((row-1)*colno)+fieldno);
		//app.Logging.getDebug(last);
		String str2=(String)ori.get(last);
		//app.Logging.getDebug(str2);
		int compare=(str1.compareTo(str2));
		if((count3%2!=0))
		{
			ori=getSortedFieldStringAsc(ori,fieldno,colno);
		}
		if((count3%2==0))
		{
			ori=getSortedFieldStringDesc(ori,fieldno,colno);
		}    
		return ori;
	}
	public Vector SetOrderSortDate(Vector ori,int fieldno,int colno)
	{
		//app.Logging.getDebug("Inside SetOrderSortDate");
		count2++;
		if((count2%2!=0))
		{
			ori=compareDateDesc(ori,fieldno,colno);
			ori=getSortedFieldDate(ori,fieldno,colno);
		}
		if((count2%2==0))
		{
			ori=compareDateDesc(ori,fieldno,colno);			
		}    
		return ori;
		/*if((count2%2!=0))
		{
			ori=getSortedFieldDate(ori,fieldno,colno);
		}
		return ori;*/
	}
	public Vector SetOrderSortNo(Vector ori,int fieldno,int colno)
	{
		//app.Logging.getDebug("Inside SetOrderSortNo");
		count1++;
		len=ori.size();
		//app.Logging.getDebug(len);
		row=len/colno;
		String str1=(String)ori.get(fieldno);
		//app.Logging.getDebug(str1);
		int last=(((row-1)*colno)+fieldno);
		//app.Logging.getDebug(last);
		String str2=(String)ori.get(last);
		//app.Logging.getDebug(str2);
		double first=Double.parseDouble(str1);
		double second=Double.parseDouble(str2);
		//app.Logging.getDebug("First  "+first);
		//app.Logging.getDebug("Second  "+second);
		//app.Logging.getDebug((first>second));
		//int compare=(str1.compareTo(str2));
		if((count1%2!=0))
		{
			ori=getSortedFieldAsc(ori,fieldno,colno);
		}
		if((count1%2==0))
		{
			ori=getSortedFieldDesc(ori,fieldno,colno);
		}    
		return ori;
	}
	public Vector SetOrderSortNo1(Vector ori,int fieldno,int colno)
	{
		//app.Logging.getDebug("Inside SetOrderSortNo");
		count2++;
		len=ori.size();
		//app.Logging.getDebug(len);
		row=len/colno;
		String str1=(String)ori.get(fieldno);
		//app.Logging.getDebug(str1);
		int last=(((row-1)*colno)+fieldno);
		//app.Logging.getDebug(last);
		String str2=(String)ori.get(last);
		//app.Logging.getDebug(str2);
		double first=Double.parseDouble(str1);
		double second=Double.parseDouble(str2);
		//app.Logging.getDebug("First  "+first);
		//app.Logging.getDebug("Second  "+second);
		//app.Logging.getDebug((first>second));
		//int compare=(str1.compareTo(str2));
		if((count2%2!=0))
		{
			ori=getSortedFieldAsc(ori,fieldno,colno);
		}
		if((count2%2==0))
		{
			ori=getSortedFieldDesc(ori,fieldno,colno);
		}    
		return ori;
	}
	public Vector SortByLetter(Vector ori,char letter)
	{
		Vector lsort_vector=new Vector();
		lsort_vector.clear();
		String sortletter=new Character(letter).toString();
		Logging.debug("Inside SortByLetter character is "+sortletter);
		Logging.debug("original vector is");
		/*for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		int k=0;
		for(int i=0;i<ori.size();i+=7)
		{
			String word=(String)ori.get(i+1);
			String fletter=word.substring(0,1);
			if(sortletter.equals(fletter))
			{
				lsort_vector.add(k,(String)ori.get(i));
				k++;
				lsort_vector.add(k,word);
				k++;
				lsort_vector.add(k,(String)ori.get(i+2));
				k++;
				lsort_vector.add(k,(String)ori.get(i+3));
				k++;
				lsort_vector.add(k,(String)ori.get(i+4));
				k++;
				lsort_vector.add(k,(String)ori.get(i+5));
				k++;
				lsort_vector.add(k,(String)ori.get(i+6));
				k++;
			}
			//app.Logging.getDebug((String)ori.get(i));
		}	
		Logging.debug("Sorted vector");
		/*for(int i=0;i<lsort_vector.size();i++)
		{
			app.Logging.getDebug((String)lsort_vector.get(i));
		}	*/	
		return lsort_vector;
	}
		
	public Vector getSortedFieldStringAsc(Vector ori,int fieldno,int colno)
	{
		len=ori.size();
		Logging.debug("vector length is "+len);
		row=len/colno;
		String temp[]=new String[20]; 
		Vector sortvector =new Vector();
		Logging.debug("original vector");
		/*for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		for(int q=0;q<=(row-2);q++)
		{
		for(int i=0;i<=row-2;i++)
		{
			min=((String)ori.get((i*colno)+fieldno)).toUpperCase();
			//app.Logging.getDebug(max);
			for(int j=i+1;j<=(row-1);j++)
			{				
				int pos=(j*colno)+fieldno;
				String d=((String)ori.get(pos)).toUpperCase();
				//app.Logging.getDebug(d);
				int diff=min.compareTo(d);
				if(diff>0)
				{
					//app.Logging.getDebug("Inside if of sorting");
					int m=(i*colno);
					int n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						temp[k]=(String)ori.get(m);
						//app.Logging.getDebug(temp[k]);
						m++;
					}
					m=(i*colno);
					n=(j*colno);
					//app.Logging.getDebug(m);
					//app.Logging.getDebug(n);
					for(int k=0;k<colno;k++)
					{
						
						String str= (String)ori.get(n);
						//app.Logging.getDebug(str);
						ori.remove(m);
						ori.add(m,str);
						//app.Logging.getDebug((String)ori.get(m));
						//app.Logging.getDebug((String)ori.get(k));
						m++;n++;
					}
					m=0;
					n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						ori.remove(n);
						ori.add(n,temp[m]);
						m++;n++;
					}
					//app.Logging.getDebug((String)ori.get(0));
					//app.Logging.getDebug((String)ori.get(1));
					//app.Logging.getDebug((String)ori.get(2));
				}
			}
		}
		}
		/*app.Logging.getDebug("Sorted vector");
		for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		
		return ori;
	}
	public Vector compareDateDesc(Vector ori,int fieldno,int colno)
	{		
		len=ori.size();
		Logging.debug("vector length is "+len);
		row=len/colno;
		String temp[]=new String[20]; 
		Vector sortvector =new Vector();
		Logging.debug("original vector");
		/*for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		for(int q=0;q<=(row-2);q++)
		{
		for(int i=0;i<=row-2;i++)
		{
			
			
			//	logger.info("creationDate :"+creationDate);
			//	logger.info("baseDate :"+baseDate);
			
			min=((String)ori.get((i*colno)+fieldno));
			Date creationDate = new Date(new Integer(min.trim()
					.substring(6, 10)).intValue(), new Integer(min
					.trim().substring(3, 5)).intValue(), new Integer(
							min.trim().substring(0, 2)).intValue());
			//app.Logging.getDebug(max);
			for(int j=i+1;j<=(row-1);j++)
			{				
				int pos=(j*colno)+fieldno;
				String d=((String)ori.get(pos)).toUpperCase();
				Date baseDate = new Date(new Integer(d.trim().substring(6,
						10)).intValue(), new Integer(d.trim().substring(3,
						5)).intValue(), new Integer(d.trim().substring(0,
						2)).intValue());
				//app.Logging.getDebug(d);
				int diff=creationDate.compareTo(baseDate);;
				if(diff>0)
				{
					//app.Logging.getDebug("Inside if of sorting");
					int m=(i*colno);
					int n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						temp[k]=(String)ori.get(m);
						//app.Logging.getDebug(temp[k]);
						m++;
					}
					m=(i*colno);
					n=(j*colno);
					//app.Logging.getDebug(m);
					//app.Logging.getDebug(n);
					for(int k=0;k<colno;k++)
					{
						
						String str= (String)ori.get(n);
						//app.Logging.getDebug(str);
						ori.remove(m);
						ori.add(m,str);
						//app.Logging.getDebug((String)ori.get(m));
						//app.Logging.getDebug((String)ori.get(k));
						m++;n++;
					}
					m=0;
					n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						ori.remove(n);
						ori.add(n,temp[m]);
						m++;n++;
					}
					//app.Logging.getDebug((String)ori.get(0));
					//app.Logging.getDebug((String)ori.get(1));
					//app.Logging.getDebug((String)ori.get(2));
				}
			}
		}
		}
		/*app.Logging.getDebug("Sorted vector");
		for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		
		return ori;
	}
	public Vector compareDateAsc(Vector ori,int fieldno,int colno)
	{		
		len=ori.size();
		Logging.debug("vector length is "+len);
		row=len/colno;
		String temp[]=new String[20]; 
		Vector sortvector =new Vector();
		Logging.debug("original vector");
		/*for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		for(int q=0;q<=(row-2);q++)
		{
		for(int i=0;i<=row-2;i++)
		{
			
			
			//	logger.info("creationDate :"+creationDate);
			//	logger.info("baseDate :"+baseDate);
			
			min=((String)ori.get((i*colno)+fieldno));
			Date creationDate = new Date(new Integer(min.trim()
					.substring(0, 2)).intValue(), new Integer(min
					.trim().substring(3, 5)).intValue(), new Integer(
							min.trim().substring(6, 10)).intValue());
			//app.Logging.getDebug(max);
			for(int j=i+1;j<=(row-1);j++)
			{				
				int pos=(j*colno)+fieldno;
				String d=((String)ori.get(pos)).toUpperCase();
				Date baseDate = new Date(new Integer(d.trim().substring(0,
						2)).intValue(), new Integer(d.trim().substring(3,
						5)).intValue(), new Integer(d.trim().substring(6,
						10)).intValue());
				//app.Logging.getDebug(d);
				int diff=creationDate.compareTo(baseDate);;
				if(diff<0)
				{
					//app.Logging.getDebug("Inside if of sorting");
					int m=(i*colno);
					int n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						temp[k]=(String)ori.get(m);
						//app.Logging.getDebug(temp[k]);
						m++;
					}
					m=(i*colno);
					n=(j*colno);
					//app.Logging.getDebug(m);
					//app.Logging.getDebug(n);
					for(int k=0;k<colno;k++)
					{
						
						String str= (String)ori.get(n);
						//app.Logging.getDebug(str);
						ori.remove(m);
						ori.add(m,str);
						//app.Logging.getDebug((String)ori.get(m));
						//app.Logging.getDebug((String)ori.get(k));
						m++;n++;
					}
					m=0;
					n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						ori.remove(n);
						ori.add(n,temp[m]);
						m++;n++;
					}
					//app.Logging.getDebug((String)ori.get(0));
					//app.Logging.getDebug((String)ori.get(1));
					//app.Logging.getDebug((String)ori.get(2));
				}
			}
		}
		}
		/*app.Logging.getDebug("Sorted vector");
		for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		
		return ori;
	}
	public Vector getSortedFieldDate(Vector ori,int fieldno,int colno)
	{
		len=ori.size();
		//app.Logging.getDebug(len);
		row=len/colno;
	//	app.Logging.getDebug(row);
		Vector sortvector =new Vector();
	/*	app.Logging.getDebug("original vector");
		for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		for(int q=(row-1);q>=0;q--)
		{
			for(int i=0;i<colno;i++)
			{
				int m=(q*colno);
				sortvector.add(ori.get(m+i));					
			}
		}
		/*for(int i=0;i<sortvector.size();i++)
		{
			app.Logging.getDebug((String)sortvector.get(i));
		}*/
		return sortvector;
	}	
	public Vector getSortedFieldStringDesc(Vector ori,int fieldno,int colno)
	{
		len=ori.size();
		Logging.debug("vector length is "+len);
		row=len/colno;
		String temp[]=new String[20]; 
		Vector sortvector =new Vector();
		Logging.debug("original vector");
		/*for(int i=0;i<ori.size();i++)
		{
			app.Logging.getDebug((String)ori.get(i));
		}*/
		for(int q=0;q<=(row-2);q++)
		{
		for(int i=0;i<=row-2;i++)
		{
			min=((String)ori.get((i*colno)+fieldno)).toUpperCase();
			//app.Logging.getDebug(max);
			for(int j=i+1;j<=(row-1);j++)
			{				
				int pos=(j*colno)+fieldno;
				String d=((String)ori.get(pos)).toUpperCase();
				//app.Logging.getDebug(d);
				int diff=min.compareTo(d);
				if(diff<0)
				{
					//app.Logging.getDebug("Inside if of sorting");
					int m=(i*colno);
					int n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						temp[k]=(String)ori.get(m);
						//app.Logging.getDebug(temp[k]);
						m++;
					}
					m=(i*colno);
					n=(j*colno);
					//app.Logging.getDebug(m);
					//app.Logging.getDebug(n);
					for(int k=0;k<colno;k++)
					{
						
						String str= (String)ori.get(n);
						//app.Logging.getDebug(str);
						ori.remove(m);
						ori.add(m,str);
						//app.Logging.getDebug((String)ori.get(m));
						//app.Logging.getDebug((String)ori.get(k));
						m++;n++;
					}
					m=0;
					n=(j*colno);
					for(int k=0;k<colno;k++)
					{
						ori.remove(n);
						ori.add(n,temp[m]);
						m++;n++;
					}					
				}
			}
		}
		}
		return ori;
	}	
	public int getcount()
	{
		return count4++;
	}
	public int getcount1()
	{
		return count6++;
	}
	public int getcount2()
	{
		return count7++;
	}
	public int getcount3()
	{
		return count8++;
	}
	public int getcount4()
	{
		return count9++;
	}
	public int getcount5()
	{
		return count10++;
	}
	public int getcount6()
	{
		return count11++;
	}
	public int getcount7()
	{
		return count12++;
	}
	public int getcount8()
	{
		return count13++;
	}
	public int getcount9()
	{
		return count14++;
	}
	public int getcount10()
	{
		return count15++;
	}
	public int getcount11()
	{
		return count16++;
	}
	public int getcount12()
	{
		return count17++;
	}
	public void setcount()
	{
		this.count4=0;
	}
	public void setOldCode(String oldCode)
	{
		
		Logging.debug("Inside field sort "+oldCode);
		this.oldcode=oldCode;
		Logging.debug("Inside field sort "+this.oldcode);
	}
	public String getOldCode()
	{
		return this.oldcode;
	}
}
