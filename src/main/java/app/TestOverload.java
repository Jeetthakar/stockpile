/*
 * Created on Apr 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.awt.Button;

import org.apache.log4j.Logger;

/**
 * @author rahul
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestOverload { 
	static Logger Logging = Logger.getLogger(TestOverload.class);
	public static void print(Float a, double b) { 
	Logging.debug("1"); } 
	public static void print(double a, double b) { 
	Logging.debug("2"); } 
	public static void print(float a, int b) { 
	Logging.debug("3"); } 
	public static void print(int a, float b) { 
	Logging.debug("4"); } 
	public static void print(double a, int b) { 
	Logging.debug("5"); } 

	public static void main(String args[]) { 
		try { 
			Button b1 = new Button("My Button"); 
			Button b2 = b1; 
			b1.setLabel("Goodbye"); 
			b1 = null; 
			Logging.debug( b2.getLabel() ); 
			} catch (NullPointerException e) { 
			Logging.debug("Null Pointer Exception"); 
			} 
		//	System.out.print("total "+values.length ); 
			

	} 
	} 
