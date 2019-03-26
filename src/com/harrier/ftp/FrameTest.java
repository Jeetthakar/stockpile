/*
 * Created on Jan 30, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.harrier.ftp;

/**
 * @author abhijit thakare
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

public class FrameTest extends JFrame{	
	
	public FrameTest() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(400, 300);
		//this.setContentPane(getJContentPane());
		this.setTitle("FTP Client");
	}
	
	public static void main(String[] args) {
		//MyDom d1=new MyDom();
    	final JLabel MKTDetails = new JLabel(" MKT File details");
    	final JLabel Filename = new JLabel(" File name");;
    	final JLabel FName = new JLabel(" Name of file ");
    	final JLabel SizeLabel = new JLabel(" Size of file");
    	final JLabel FileSize = new JLabel(" File Size");
    	final JLabel NoFiles = new JLabel(" Number of files downloaded");
    	final JLabel NumFile = new JLabel(" File Number");
    	final JLabel TFile = new JLabel(" File downloaded at time");
    	final JLabel TimeFile = new JLabel(" File Time");
    	MyDom d1=new MyDom();
    	String source="/CM05/Data/August252006/";
    	String destination="D://DownLoad1//";
    	//String destination1="D://DownLoad1//";
    	//new Client4( d1.s1 ,d1.s2 ,d1.s3,source,destination).start();
    	new Client4("203.199.75.115","C05023","HIS468",source,destination).start();
    	//new MktThrDB(name);
		new Timer(1000, new ActionListener(){		
					
            public void actionPerformed(ActionEvent evt) {
               String name = MKTThread.str;
               FName.setText(name);
               
               
               long length=MKTThread.len;
               FileSize.setText(Long.toString(length)+"Bytes");
               
               int num=MKTThread.nofiles;
               NumFile.setText(Integer.toString(num));  
               
               String filetime=MKTThread.rightnow;
               TimeFile.setText(filetime);
              }
        }).start();
		
		
		
        JPanel contentPane = new JPanel();
       
		TimeFile.setBounds(new java.awt.Rectangle(220,190,115,20));
		
		TFile.setBounds(new java.awt.Rectangle(15,190,175,20));
		
		NumFile.setBounds(new java.awt.Rectangle(220,145,119,20));
		
		NoFiles.setBounds(new java.awt.Rectangle(15,145,175,20));
		
		FileSize.setBounds(new java.awt.Rectangle(220,100,120,20));
		
		SizeLabel.setBounds(new java.awt.Rectangle(15,100,97,20));
		
		FName.setBounds(new java.awt.Rectangle(220,50,115,23));
		
		Filename.setBounds(new java.awt.Rectangle(15,50,100,21));
		
		MKTDetails.setBounds(new java.awt.Rectangle(15,15,100,20));
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.add(MKTDetails, null);
		contentPane.add(Filename, null);
		contentPane.add(FName, null);
		contentPane.add(SizeLabel, null);
		contentPane.add(FileSize, null);
		contentPane.add(NoFiles, null);
		contentPane.add(NumFile, null);
		contentPane.add(TFile, null);
		contentPane.add(TimeFile, null);
        final FrameTest f = new FrameTest();
       
        f.add(contentPane);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(contentPane);
        //f.setSize(400, 250);
       
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        }
        );
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
