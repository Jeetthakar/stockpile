package com.installation;

/**
 * Created by IntelliJ IDEA.
 * User: Vivek
 * Date: Nov 8, 2004
 * Time: 9:13:58 PM
 * To change this template use Options | File Templates.
 */
  
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Properties;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class InstallStockPile extends JApplet implements Runnable {
	static Logger Logging = Logger.getLogger(InstallStockPile.class);
	JButton openButton;
    JFileChooser fc;
    JTextField tf1,tf2,tf3,tfport,tforiginalstring,tfstringfromstockpile;
    JPanel p0,tomcatpanel,unzippingpanel,originalstringpanel,stringfromStockpilepanel,middlePanel;
    JButton jbuttonnext,jbuttoncancel,nextButton1,openbutton1,jb1,jb2,nextbuttonfortomcat,cancelbuttonfortomcat,nextbuttonfororiginalstring,nextbuttonforstringfromstockpile;
    JButton backtomcat,backoriginalstring,backstringfromstockpile,backunzipping,copybutton,pastebutton;
    static private final String newline = "\n";
    File foldertowhichcopystockpile,foldertounzip;
    Container contentPane;
    String filename,sourcefilename ,tomcatwebapps,portnumber,stringforregfile,stringforregfile1,originalstring,securityString,tempportnumber;
    String tempstringfromstockpile;
    ButtonGroup group;
    JRadioButton tomcatinstalled,tomcatnotinstalled;
    JProgressBar aJProgressBar;
    boolean tomcatpresent = false;
    JButton nextbuttonfortomcat1;
    String harrierImage,baseFoldertString;
    int xmin,xmax,ymin,ymax;
    Thread t;
    static final Frame frame= new Frame("Harrier StockPile Installation");
    //GetKey cl;
    int x1,x2,y1,y2;
    String clientKey,stringfromclient,key;
  //  KeyValidation kv;
     /**The clientConn will be used to connect to PostgreSQL database*/
    private Connection clientConn;
     /**clientCodes[] stores the codes of all clients for which DTS is to be executed*/
    private String clientCodes;

    /**recipientCodes[] stores the codes of all recipient for which DTS is to be executed*/
    private String recipientCodes;
     /**lastUpdatedDates[] stores the last updated dates for all clients for 'Update' and 'Group Update' option*/
    private String lastUpdatedDates;

    /**lastToLastUpdatedDates[] stores the last to last updated dates for all clients for 'Update' and 'Group Update' option*/
    private String lastToLastUpdatedDates;

    public static void main(String args[]) {

        try{
       //   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        }catch(Exception e){
          Logging.debug("Look and feel not supported:");
        }
        InstallStockPile install = new InstallStockPile();
        install.doChanges1();
       install.initializeCoordinates();
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(install.new ExitListener());
        install.init();
        frame.add(install);
   //     frame.setBounds(120, 65, 740, 600);
             frame.setBounds(install.xmin, install.ymin, install.xmax-install.xmin, install.ymax-install.ymin);
        frame.setResizable(false);
        //frame.show();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //  frame.pack();
                frame.show();
            }
        });

    }

    public void initializeCoordinates(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
  /*      xmin=screen.width/2-screen.width*3/8;
         Logging.debug("xmin :"+xmin);
        xmax=screen.width/2+screen.width*3/8;
         Logging.debug("xmax :"+xmax);
        ymin=screen.height/2-screen.height*4/10;
         Logging.debug("ymin :"+ymin);
       ymax=screen.height/2+screen.height*4/10;
         Logging.debug("ymax :"+ymax);
   */
         xmin=screen.width/2-screen.width*2/8;
         Logging.debug("xmin :"+xmin);
        xmax=screen.width/2+screen.width*2/8;
         Logging.debug("xmax :"+xmax);
        ymin=screen.height/2-screen.height*3/10;
         Logging.debug("ymin :"+ymin);
       ymax=screen.height/2+screen.height*3/10;
         Logging.debug("ymax :"+ymax);
        baseFoldertString=getbaseFolder("harrierImage.JPG");
         Logging.debug("baseFoldertString :"+baseFoldertString);
    }

    public void init() {
  //      setBounds(100, 100, 600, 600);
              setBounds(xmin, ymin, xmax-xmin, ymax-ymin);
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        fc = new JFileChooser();

        p0 = new JPanel();
        p0.setLayout(new GridLayout(3, 1));
   //     p0.setBounds(50, 75, 620, 420);
        Logging.debug("getWidth()-100 :"+(getWidth()-100));
         Logging.debug("getHeight()-125 :"+(getHeight()-125));
        x2=getWidth()-100;
        y2=getHeight()-125;

        p0.setBounds(50, 50, getWidth()-100, getHeight()-125);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p11 = new JPanel();
        p11.setBackground(Color.gray);
        Font sansbold14 = new Font("SansSerief", Font.BOLD, 21);
        JLabel l1 = new JLabel( createImageIcon("harrierImage.JPG"));
        l1.setFont(sansbold14);
        l1.setLayout(new FlowLayout(FlowLayout.CENTER));
        p11.add(l1);
        p1.add(p11);
        p1.setMinimumSize(new Dimension(280,80));



        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 1));
        JPanel p20=new JPanel();
        JPanel p21=new JPanel();
        p21.setLayout(new FlowLayout(FlowLayout.LEFT ));
        JPanel pl2 = new JPanel();
        //   pl2.setBackground(Color.gray);
        sansbold14 = new Font("SansSerief", Font.BOLD, 12);
        JLabel l2inp2 = new JLabel("Please Select a  Folder");
        l2inp2.setFont(sansbold14);
        l2inp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        pl2.add(l2inp2);
        p21.add(pl2);
        p2.add(p20);
        p2.add(p21);

        JPanel p3 = new JPanel();
       // p3.setLayout();

        JPanel pl3 = new JPanel();
         pl3.setLayout(new FlowLayout(FlowLayout.LEFT ));
        JLabel l2inp3 = new JLabel("Destination Folder");
        openButton = new JButton("", createImageIcon("Open16.gif"));
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == openButton) {
                    fc.setDialogTitle("select base folder name");
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          //          int returnVal = fc.showOpenDialog(openButton);
                     int returnVal = fc.showOpenDialog( frame);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        tf1.setText(file.getAbsolutePath());
                    } else {
                        //log.append("Open command cancelled by user." + newline);
                    }

                }

            }
        });
        l2inp3.setFont(sansbold14);
        tf1 = new JTextField();
        tf1.setColumns(15);
        l2inp3.setLayout(new FlowLayout(FlowLayout.LEFT));
        pl3.add(l2inp3);
        pl3.add(tf1);
        pl3.add(openButton);
        p3.add(pl3);

        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl5 = new JPanel();
        JLabel l2inp5 = new JLabel("Source Folder          ");
        nextButton1 = new JButton("", createImageIcon("Open16.gif"));
        nextButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == nextButton1) {
                    fc.setDialogTitle("select Source folder name");
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int returnVal = fc.showOpenDialog(openButton);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        tf2.setText(file.getAbsolutePath());
                    }

                }

            }
        });
        l2inp5.setFont(sansbold14);
        tf2 = new JTextField();
        tf2.setColumns(30);
        l2inp3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pl5.add(l2inp5);
        pl5.add(tf2);
        pl5.add(nextButton1);
        p5.add(pl5);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JPanel pl4 = new JPanel();
        //   pl2.setBackground(Color.gray);
        jbuttonnext = new JButton("Next >>");
        jbuttonnext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == jbuttonnext) {
                    filename = tf1.getText();
                    sourcefilename =baseFoldertString ;
                    Logging.debug("filename..."+filename);
                    Logging.debug("sourcefilename..."+sourcefilename);
                //    sourcefilename=
                     String warfilename = sourcefilename + "\\StockPile\\code\\Tomcat\\webapps\\Income";
                            File winrar = new File(warfilename);
                            if (!winrar.exists()) {
                                JOptionPane.showMessageDialog(nextButton1,
                                        "This Folder doesn't contain all necessary files",
                                        "StockPile",
                                        JOptionPane.ERROR_MESSAGE);
                            } else if (sourcefilename == null || sourcefilename.trim().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(jbuttoncancel,
                                "Unable to start installation  !! ",
                                "StockPile",
                                JOptionPane.ERROR_MESSAGE);
                    }else if (filename == null || filename.trim().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(jbuttoncancel,
                                "Please Give the name of destination folder.....  !! ",
                                "StockPile",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (sourcefilename == null || sourcefilename.trim().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(jbuttoncancel,
                                "Unable to start installation  !!  ",
                                "StockPile",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (filename.trim().indexOf(" ")!=-1) {
                        JOptionPane.showMessageDialog(jbuttoncancel,
                                "Space not allowed in Destination Folder ",
                                "StockPile",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        foldertowhichcopystockpile = new File(filename);
                        foldertounzip = new File(sourcefilename);
                        if (!foldertowhichcopystockpile.exists()) {
                            JOptionPane.showMessageDialog(nextButton1,
                                    "Destination Folder does not exists",
                                    "StockPile",
                                    JOptionPane.ERROR_MESSAGE);
                        } else if (!foldertounzip.exists()) {
                            JOptionPane.showMessageDialog(nextButton1,
                                    sourcefilename+ "  does not seems to be accessible....  !! ",
                                    "StockPile",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            //do further processing here
                             warfilename = sourcefilename + "\\StockPile\\database\\cygwin\\bin";
                             winrar = new File(warfilename);
                            if (!winrar.exists()) {
                                JOptionPane.showMessageDialog(nextButton1,
                                        "Some files missing",
                                        "StockPile",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                Logging.debug("Starting Unzipping...");


                                JOptionPane.showMessageDialog(nextButton1,
                                        "Please give full access rights to "+filename,
                                        "StockPile",
                                        JOptionPane.INFORMATION_MESSAGE);
                                 p0.setVisible(false);
                                //      manageTomcat();   //comment it for use without copy protection
                                  StartCopying();   //uncomment it for use without copy protection

                            }


                        }
                    }
                }

            }
        });
        jbuttoncancel = new JButton("Cancel");
        jbuttoncancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == jbuttoncancel) {
                    int n = JOptionPane.showConfirmDialog(jbuttoncancel, "Installation is not yet complete !!\n Are You sure You want to cancel the installation ?? ", "Exit ??", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
        sansbold14 = new Font("SansSerief", Font.BOLD, 12);
        JLabel l2inp4 = new JLabel("Please Select the destination Folder In your machine");
        l2inp4.setFont(sansbold14);
        l2inp2.setLayout(new FlowLayout());
        pl4.add(jbuttonnext);
        pl4.add(jbuttoncancel);
        p4.add(pl4);

         middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(2, 1));
        middlePanel.add(p2);
     //   middlePanel.add(p5);
    //       middlePanel.add(new JPanel());
        middlePanel.add(p3);

        JPanel lowerPanel=new JPanel();
        lowerPanel.setLayout(new GridLayout(2, 1));
        lowerPanel.add(new JPanel());
        lowerPanel.add(p4);

        Border b = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, Color.BLACK);
        p0.setBorder(b);

        p0.add(p1);
        p0.add(middlePanel);
  //      p0.add(p5);
 //       p0.add(p3);
        p0.add(lowerPanel);

        contentPane.add(p0);
        setVisible(true);
        p0.setVisible(true);
    }


    public void manageTomcat() {
        Logging.debug("manageTomcat()");
        //  contentPane.removeAll();
        tomcatpanel = new JPanel();
        tomcatpanel.setLayout(new GridLayout(5, 1));
        tomcatpanel.setBounds(50, 75, 620, 420);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p11 = new JPanel();
        p11.setBackground(Color.gray);
        Font sansbold14 = new Font("SansSerief", Font.BOLD, 21);
        JLabel l1 = new JLabel("StockPile");
        l1.setFont(sansbold14);
        l1.setLayout(new FlowLayout(FlowLayout.CENTER));
        p11.add(l1);
        p1.add(p11);
        Logging.debug("manageTomcat()");

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl2 = new JPanel();
        //   pl2.setBackground(Color.gray);
        JLabel l2inp2 = new JLabel("Is Tomcat Already Installed in your system ??   ");
        sansbold14 = new Font("SansSerief", Font.BOLD, 12);
        l2inp2.setFont(sansbold14);
        l2inp2.setLayout(new FlowLayout());
        group = new ButtonGroup();
        tomcatinstalled = new JRadioButton("Yes", false);
        group.add(tomcatinstalled);
        tomcatnotinstalled = new JRadioButton("No", true);
        group.add(tomcatnotinstalled);

        tomcatnotinstalled.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == tomcatnotinstalled) {
                    tf3.setEditable(false);
                    tf3.setText(foldertowhichcopystockpile + "\\crisil\\jakarta-tomcat-4.0.4\\webapps");
                    tfport.setEditable(true);
                    tfport.setText("");
                    Logging.debug("Before if");
                    if(tempportnumber!=null || !tempportnumber.trim().equals("")){
                        Logging.debug("After if");
                           tfport.setText(tempportnumber);
                     }
                }
            }
        });
        tomcatinstalled.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == tomcatinstalled) {
                    tf3.setEditable(true);
                    tfport.setEditable(false);
                    tfport.setText("Notapplicable");
                    tf3.setText("");
                }
            }
        });


        pl2.add(l2inp2);
        // pl2.add(jb1);
        // pl2.add(jb2);
        pl2.add(tomcatinstalled);
        pl2.add(tomcatnotinstalled);
        p2.add(pl2);

        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl3 = new JPanel();
        JLabel l2inp3 = new JLabel("path to tomcat base folder(CATALINA_HOME)");
        tf3 = new JTextField();
        tf3.setColumns(20);
        tf3.setText(foldertowhichcopystockpile + "\\crisil\\jakarta-tomcat-4.0.4");
        tf3.setEditable(false);
        l2inp3.setFont(sansbold14);
        l2inp3.setLayout(new FlowLayout());
        pl3.add(l2inp3);
        pl3.add(tf3);
        p3.add(pl3);

        //   pl2.setBackground(Color.gray);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JPanel pl4 = new JPanel();
        //   pl2.setBackground(Color.gray);
        backtomcat = new JButton("<<< Back");
        backtomcat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == backtomcat) {
                    init();
                }
            }
        });
        nextbuttonfortomcat = new JButton("Next >>>");
        nextbuttonfortomcat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == nextbuttonfortomcat) {
                    tomcatwebapps = tf3.getText();
                    Logging.debug(tomcatwebapps);
                    if (tomcatwebapps == null || tomcatwebapps.trim().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(jbuttoncancel,
                                "Please Give the name of name of tomcat folder.....  !! ",
                                "No tomcat folder name given??",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Logging.debug("1");

                        File checktomcat = new File(tomcatwebapps + "\\bin\\startup.bat");
                        Logging.debug("starttomcat.bat path is : " + checktomcat.getAbsolutePath());
                        if (!checktomcat.exists() && tomcatinstalled.isSelected()) {
                            JOptionPane.showMessageDialog(nextButton1,
                                    "Problem with tomcat Folder....  !!\n tomcat startup file startup.bat not found in bin directory",
                                    "startup.bat not found ??",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (tomcatinstalled.isSelected()) {
                                tomcatpresent = true;
                            }

                            //do further processing here
                            if (tomcatnotinstalled.isSelected()) {
                                portnumber = tfport.getText().trim();
                                if (invalidint(portnumber)) {
                                    JOptionPane.showMessageDialog(nextButton1,
                                            "Port number seems to be invalid.... ",
                                            "invalid port number...",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    //check here whether port is available
                                    Integer i;
                                    int portnum = 0;
                                    try {
                                        i = new Integer(portnumber);
                                        portnum = i.intValue();

                                        if ((!isPortAvailable(portnum)) || (portnum < 1024)) {
                                            Logging.debug("port number " + portnum + " is not available");
                                            JOptionPane.showMessageDialog(nextButton1,
                                                    "This port number seems to be used by some another application or is a reserved one.... ",
                                                    "invalid port number...",
                                                    JOptionPane.ERROR_MESSAGE);
                                        } else {


                                            String warfilename = sourcefilename + "\\Crisilfe.rar";
                                            File winrar = new File(warfilename);
                                            if (!winrar.exists()) {
                                                JOptionPane.showMessageDialog(nextButton1,
                                                        "Crisilfe.rar file not Found in this folder ..!!\n Please give a valid source folder name ",
                                                        "WinRAR not Found...",
                                                        JOptionPane.ERROR_MESSAGE);
                                            } else {
                                                Logging.debug("just going to call method to Starting Unzipping...");
                                                tomcatpanel.setVisible(false);
                                                //  StartCopying();  //call it now after validation
                                                try{
                                                tempportnumber=portnumber;
                                                }catch(Exception e){}
                                                showOriginalString();
                                              //   StartCopying();

                                            }
                                        }

                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(nextButton1,
                                                "Please enter valid integer number between 8000 to 9000 ",
                                                "invalid port number...",
                                                JOptionPane.ERROR_MESSAGE);

                                    }

                                }

                            }

                        }
                    }
                }

            }
        });
        jbuttoncancel = new JButton("Cancel");
        jbuttoncancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == jbuttoncancel) {
                    int n = JOptionPane.showConfirmDialog(jbuttoncancel, "Installation is not yet complete !!\n Are You sure You want to cancel the installation ?? ", "Exit ??", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
        sansbold14 = new Font("SansSerief", Font.BOLD, 12);
        JLabel l2inp4 = new JLabel("Please Select the destination Folder In your machine");
        l2inp4.setFont(sansbold14);
        l2inp2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //  pl4.add(backtomcat);
        pl4.add(nextbuttonfortomcat);
        pl4.add(jbuttoncancel);
        p4.add(pl4);

        String firstavailableport=firstAvailablePort();
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl5 = new JPanel();
        JLabel l2inp5 = new JLabel("Port No.");
        l2inp5.setFont(sansbold14);
        tfport = new JTextField();
        tfport.setColumns(20);
        String tempportnumber1=firstAvailablePort();
        tfport.setText(tempportnumber1);

        l2inp3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pl5.add(l2inp5);
        pl5.add(tfport);
        p5.add(pl5);

        //putting code to restore value of port number comming back
        try{
        if(tempportnumber!=null || !tempportnumber.trim().equals("")){
                   Logging.debug("After if");
                    tfport.setText(tempportnumber);
         }
        }catch(Exception e){}

        Border b = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, Color.BLACK);
        tomcatpanel.setBorder(b);

        tomcatpanel.add(p1);
        tomcatpanel.add(p2);
        tomcatpanel.add(p3);
        tomcatpanel.add(p5);
        tomcatpanel.add(p4);
        contentPane.add(tomcatpanel);
        Logging.debug(contentPane.countComponents());
        tomcatpanel.setVisible(true);

    }

    public String firstAvailablePort(){
        String portavailable="";
        Integer i;int portnum=8080;
        while(true){
            if(isPortAvailable(portnum)){
                break;
            }
            portnum++;
        }
        i=new Integer(portnum);
        portavailable=i.toString();
        return portavailable;
    }

    public void showOriginalString() {
        try {
            //cl = new GetKey(sourcefilename);
     //       clientKey = cl.getEncryptedClientKey();
        } catch (Exception e) {
            Logging.debug(e);
        }

        originalstringpanel = new JPanel();
        originalstringpanel.setLayout(new GridLayout(5, 1));
        originalstringpanel.setBounds(50, 75, 620, 420);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p11 = new JPanel();
        p11.setBackground(Color.gray);
        Font sansbold14 = new Font("SansSerief", Font.BOLD, 21);
        JLabel l1 = new JLabel("CRIS INFAC Research Pro Installation");
        l1.setFont(sansbold14);
        l1.setLayout(new FlowLayout(FlowLayout.CENTER));
        p11.add(l1);
        p1.add(p11);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl2 = new JPanel();
        //   pl2.setBackground(Color.gray);
        JLabel l2inp2 = new JLabel("copy and Paste this String into the url \"http:\\\\crisilinfdsk006:8080\\validate.jsp\"");
        sansbold14 = new Font("SansSerief", Font.BOLD, 12);
        l2inp2.setFont(sansbold14);
        l2inp2.setLayout(new FlowLayout());
        pl2.add(l2inp2);
        p2.add(pl2);

        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl3 = new JPanel();
        JLabel l2inp3 = new JLabel("Security String ");
        tforiginalstring = new JTextField();
        tforiginalstring.setColumns(40);
        String temporiginalstring;
        temporiginalstring = clientKey;
        // temporiginalstring = "string to be pasted by vinod";
        tforiginalstring.setText(temporiginalstring);
      //  tforiginalstring.setEditable(false);
        l2inp3.setFont(sansbold14);
        l2inp3.setLayout(new FlowLayout());
        pl3.add(l2inp3);
        pl3.add(tforiginalstring);
        p3.add(pl3);

        //   pl2.setBackground(Color.gray);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JPanel pl4 = new JPanel();
        //   pl2.setBackground(Color.gray);
        Logging.debug("before nextbuttonfororiginalstring action");
        backoriginalstring = new JButton("<<< Back");
        backoriginalstring.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == backoriginalstring) {
                    Logging.debug("Action received backoriginalstring");
                    originalstringpanel.setVisible(false);
                    manageTomcat();
                }
            }
        });
        nextbuttonfororiginalstring = new JButton("got String >>>");
        nextbuttonfororiginalstring.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Logging.debug("inside nextbuttonfororiginalstring action");
                if (evt.getSource() == nextbuttonfororiginalstring) {
                    Logging.debug("Entered showOriginalString actionPerformed");
                    getStringFromCrisil();
                    //   StartCopying();
                }

            }
        });

        copybutton = new JButton("Copy");
        copybutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Logging.debug("inside nextbuttonfororiginalstring action");
                if (evt.getSource() == copybutton) {
                    String temp=tforiginalstring.getText();
                    if(temp!=null || !temp.trim().equals("")){
                        securityString=temp;
                    }else{
                        JOptionPane.showMessageDialog(jbuttoncancel,
                            "Testfield seems to be blank ",
                            "nothing to copy...",
                            JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        });
        jbuttoncancel = new JButton("Cancel");
        jbuttoncancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == jbuttoncancel) {
                    int n = JOptionPane.showConfirmDialog(jbuttoncancel, "Installation is not yet complete !!\n Are You sure You want to cancel the installation ?? ", "Exit ??", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
        pl4.add(backoriginalstring);
         pl4.add(copybutton);
        pl4.add(nextbuttonfororiginalstring);
        pl4.add(jbuttoncancel);
        p4.add(pl4);


        Border b = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, Color.BLACK);
        originalstringpanel.setBorder(b);

        originalstringpanel.add(p1);
        originalstringpanel.add(p2);
        originalstringpanel.add(p3);
        //originalstringpanel.add(p5);
        originalstringpanel.add(p4);
        contentPane.add(originalstringpanel);


    }


    public void getStringFromCrisil() {
        Logging.debug("inside");
        stringfromStockpilepanel = new JPanel();
        stringfromStockpilepanel.setLayout(new GridLayout(5, 1));
        stringfromStockpilepanel.setBounds(50, 75, 620, 420);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p11 = new JPanel();
        p11.setBackground(Color.gray);
        Font sansbold14 = new Font("SansSerief", Font.BOLD, 21);
        JLabel l1 = new JLabel("CRIS INFAC Research Pro Installation");
        l1.setFont(sansbold14);
        l1.setLayout(new FlowLayout(FlowLayout.CENTER));
        p11.add(l1);
        p1.add(p11);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl2 = new JPanel();
        //   pl2.setBackground(Color.gray);
        JLabel l2inp2 = new JLabel("paste the string you got from crisil site here");
        sansbold14 = new Font("SansSerief", Font.BOLD, 12);
        l2inp2.setFont(sansbold14);
        l2inp2.setLayout(new FlowLayout());
        pl2.add(l2inp2);
        p2.add(pl2);

        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl3 = new JPanel();
        JLabel l2inp3 = new JLabel("Security String ");
        tfstringfromstockpile = new JTextField();
        tfstringfromstockpile.setColumns(40);
        l2inp3.setFont(sansbold14);
        l2inp3.setLayout(new FlowLayout());
        pl3.add(l2inp3);
        pl3.add(tfstringfromstockpile);
        p3.add(pl3);

        //   pl2.setBackground(Color.gray);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JPanel pl4 = new JPanel();
      /*   try
            {
                ApacheSoapProxy proxy = new ApacheSoapProxy ();
                key = proxy.generateOID (clientKey);
             }  catch (java.net.MalformedURLException exception) {
                  exception.printStackTrace ();
             }
             catch (org.apache.soap.SOAPException exception)
             {
                   exception.printStackTrace ();
              }
          if(key!=null)
          {
             tfstringfromstockpile.setText(key);
          }
         else
          {
              tfstringfromstockpile.setText("Get Key From Crisil");
          }  */
        //   pl2.setBackground(Color.gray);
        backstringfromstockpile = new JButton("<<< Back");
        backstringfromstockpile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == backstringfromstockpile) {
                    Logging.debug("Action received backoriginalstring");
                    stringfromStockpilepanel.setVisible(false);
                    showOriginalString();
                }
            }
        });


        pastebutton = new JButton("paste");
        pastebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Logging.debug("inside nextbuttonfororiginalstring action");
                if (evt.getSource() == pastebutton) {
                    String temp=tforiginalstring.getText();
                    if(temp!=null || !temp.trim().equals("")){
                       // tfstringfromstockpile.setText(securityString);
                     }else{
                        JOptionPane.showMessageDialog(jbuttoncancel,
                            "Nothing copied",
                            "Please try to copy again",
                            JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        });
        Logging.debug("before nextbuttonfororiginalstring action");
        nextbuttonforstringfromstockpile = new JButton("Validate>>>");
        nextbuttonforstringfromstockpile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stringfromclient = tfstringfromstockpile.getText();
                if (stringfromclient == null || stringfromclient.trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(jbuttoncancel,
                            "Please give valid key ",
                            "not a valid key",
                            JOptionPane.ERROR_MESSAGE);
                } else if (evt.getSource() == nextbuttonforstringfromstockpile) {
             //       kv = new KeyValidation(key, sourcefilename);
             //       int output = kv.validateKey();
           //         Logging.debug("outpur  value is : "+output);
                   int output=0;
                    try{
                        tempstringfromstockpile=tfstringfromstockpile.getText();
                    }catch(Exception e){  }
                    if(output==0){
                       StartCopying();
                    }else if(output==1){
                          JOptionPane.showMessageDialog(jbuttoncancel,
                            "You doesn't seem to be valid client\n For further assistance Please contact crisil",
                            "in valid user",
                            JOptionPane.ERROR_MESSAGE);
                       // System.exit(0);
                    }else if(output==2){
                          JOptionPane.showMessageDialog(jbuttoncancel,
                            "The number of users exceeded\n for further assistance Please contact crisil",
                            "users exceeded",
                            JOptionPane.ERROR_MESSAGE);
                      //  System.exit(0);
                    } else if(output==3){
                        JOptionPane.showMessageDialog(jbuttoncancel,
                            "This serial key expired \n for further assistance Please contact crisil",
                            "get new serial key !!",
                            JOptionPane.ERROR_MESSAGE);
                    //    System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(jbuttoncancel,
                            "Some error occured during installation \n for further assistance Please contact crisil",
                            "get new serial key !!",
                            JOptionPane.ERROR_MESSAGE);
                  //      System.exit(0);
                    }
                    Logging.debug("Entered getStringFromCrisil actionPerformed");
                  //  StartCopying();
                }

            }
        });
        jbuttoncancel = new JButton("Cancel");
        jbuttoncancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == jbuttoncancel) {
                    int n = JOptionPane.showConfirmDialog(jbuttoncancel, "Installation is not yet complete !!\n Are You sure You want to cancel the installation ?? ", "Exit ??", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
        pl4.add(backstringfromstockpile);
         pl4.add(pastebutton);
        pl4.add(nextbuttonforstringfromstockpile);
        pl4.add(jbuttoncancel);
        p4.add(pl4);

         try{
        if(tempstringfromstockpile!=null || !tempstringfromstockpile.trim().equals("")){
                    tfstringfromstockpile.setText(tempstringfromstockpile);
         }
        }catch(Exception e){}

        Border b = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, Color.BLACK);
        stringfromStockpilepanel.setBorder(b);
        originalstringpanel.setVisible(false);
        stringfromStockpilepanel.add(p1);
        stringfromStockpilepanel.add(p2);
        stringfromStockpilepanel.add(p3);
        Logging.debug(stringfromStockpilepanel.countComponents());
        //originalstringpanel.add(p5);
        stringfromStockpilepanel.add(p4);
        // contentPane.add(stringfromStockpilepanel);
        stringfromStockpilepanel.setVisible(true);
        contentPane.add(stringfromStockpilepanel);
        stringfromStockpilepanel.setVisible(true);
        Logging.debug("exit");

    }


    public void StartCopying() {
        portnumber=firstAvailablePort();

        Logging.debug("started unzipping");
        unzippingpanel = new JPanel();
        unzippingpanel.setLayout(new GridLayout(3, 1));
 //       unzippingpanel.setBounds(50, 75, 620, 420);
        Logging.debug("getWidth()-100 1:"+x2);
         Logging.debug("getHeight()-125 1:"+y2);
        unzippingpanel.setBounds(50, 50, x2, y2);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p11 = new JPanel();
        p11.setBackground(Color.gray);
        Font sansbold14 = new Font("SansSerief", Font.BOLD, 21);
        JLabel l1 = new JLabel( createImageIcon("harrierImage.JPG"));
        l1.setFont(sansbold14);
        l1.setLayout(new FlowLayout(FlowLayout.CENTER));
        p11.add(l1);
        p1.add(p11);
        p1.setMinimumSize(new Dimension(280,80));

  /*      JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p11 = new JPanel();
        p11.setBackground(Color.gray);
        Font sansbold14 = new Font("SansSerief", Font.BOLD, 21);
        JLabel l1 = new JLabel("CRIS INFAC Research Pro Installation");
        l1.setFont(sansbold14);
        l1.setLayout(new FlowLayout(FlowLayout.CENTER));
        p11.add(l1);
        p1.add(p11);                     */
        Logging.debug("added panel 1 to unzipping panel");
        JPanel p2 = new JPanel();

        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pl3 = new JPanel();
        JLabel l2inp3 = new JLabel("Click Start to start installation....");
        sansbold14 = new Font("SansSerief", Font.BOLD, 12);
        aJProgressBar = new JProgressBar(0, 100);
        aJProgressBar.setIndeterminate(false);

        try {

            //commented for west indies
            /*
            if (tomcatinstalled.isSelected()) {
                tomcatpresent = true;
            }     */

        } catch (Exception e) {
            Logging.debug(e.toString());
        }

        l2inp3.setFont(sansbold14);
        l2inp3.setLayout(new FlowLayout());
        pl3.add(l2inp3);
      //  pl3.add(aJProgressBar);
        p3.add(pl3);

        //   pl2.setBackground(Color.gray);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JPanel pl4 = new JPanel();
        //   pl2.setBackground(Color.gray);
        backunzipping = new JButton("<<< Back");
        backunzipping.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == backunzipping) {
                    Logging.debug("Action received backunzipping");
                    unzippingpanel.setVisible(false);
                //    getStringFromCrisil();
                    manageTomcat();
                }
            }
        });
        nextbuttonforstringfromstockpile = new JButton("Validate>>>");
        nextbuttonforstringfromstockpile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //   Logging.debug("inside nextbuttonfororiginalstring action");
                if (evt.getSource() == nextbuttonforstringfromstockpile) {
                    Logging.debug("Entered getStringFromCrisil actionPerformed");
                    StartCopying();
                }

            }
        });
        nextbuttonfortomcat1 = new JButton("Start >>>");
        nextbuttonfortomcat1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == nextbuttonfortomcat1) {
                    //Logging.debug("1");
                    nextbuttonfortomcat1.disable();
    //                aJProgressBar.setIndeterminate(true);
                    JOptionPane.showMessageDialog(jbuttoncancel,
                                "Installation will take around 15 minutes",
                                "StockPile",
                                JOptionPane.INFORMATION_MESSAGE);
                    boolean flag=transfer();
              //      boolean flag=true;
                      if(flag){
                          startapplication();
                      }else{
                         JOptionPane.showMessageDialog(jbuttoncancel,
                                "Some error occured while installation ...\nContact Harrier For further assistance ",
                                "StockPile",
                                JOptionPane.INFORMATION_MESSAGE);
                           Logging.debug("Starting StockPile Application  ");
                      }

                 //uncomment it***
                    //Logging.debug("2");
                    //Logging.debug("Updating database");
          //          updateDatabase();
                       //   String st = "c:\\Program Files\\WinRAR\\rar x -y " + foldertounzip + "\\Crisilfe.rar " + foldertowhichcopystockpile;
                        //Logging.debug(st);


               //     JOptionPane.showMessageDialog(jbuttoncancel,
               //                 "Application Server will take around 30 minutes to start",
               //                 "CRIS INFAC Research Pro",
              //                  JOptionPane.INFORMATION_MESSAGE);






                }
            }
        });

        jbuttoncancel = new JButton("Cancel");
        jbuttoncancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == jbuttoncancel) {
                    int n = JOptionPane.showConfirmDialog(jbuttoncancel, "Installation is not yet complete !!\n Are You sure You want to cancel the installation ?? ", "Exit ??", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
        sansbold14 = new Font("SansSerief", Font.BOLD, 12);
        JLabel l2inp4 = new JLabel("Please Select the destination Folder In your machine");
        l2inp4.setFont(sansbold14);
        l2inp4.setLayout(new FlowLayout(FlowLayout.RIGHT));
    //    pl4.add(backunzipping);
        pl4.add(nextbuttonfortomcat1);
        pl4.add(jbuttoncancel);
        p4.add(pl4);

        JPanel lowerPanel=new JPanel();
        lowerPanel.setLayout(new GridLayout(2, 1));
        lowerPanel.add(new JPanel());
        lowerPanel.add(p4);

        Border b = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, Color.BLACK);
        unzippingpanel.setBorder(b);

        unzippingpanel.add(p1);
    //    unzippingpanel.add(p2);
        unzippingpanel.add(p3);


        unzippingpanel.add(lowerPanel);
        unzippingpanel.countComponents();
 //       stringfromStockpilepanel.setVisible(false);

        contentPane.add(unzippingpanel);
        unzippingpanel.setVisible(true);

    }

    public void startapplication(){
        try{
         t = new Thread(this);
            t.start();
            t.join();
            JOptionPane.showMessageDialog(jbuttoncancel,
                                "StockPile has been successfully installed...\ncopy and paste the url given in the url.txt of your desktop\nin the browser after the Application server is started . ",
                                "StockPile",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
        }catch(Exception ex){

        }



    }


    public boolean transfer() {
        Process pro;
        boolean  flag=false;
        try {
            //code to transfer folder

            //  nextbuttonfortomcat1.setVisible(true);
            String st;
            //Logging.debug("Unzipping Crisilfe.rar file...");
            //Logging.debug("before starting unzipping values are ");
             Logging.debug("filename..."+filename);
                    Logging.debug("sourcefilename..."+sourcefilename+"\\StockPile");
               flag=true;
              flag=copyDirectory(new File(sourcefilename+"\\StockPile"),new File(filename+"\\StockPile"));
            //Logging.debug("foldertowhichcopystockpile  :" + foldertowhichcopystockpile);
            //  String st = "c:\\Program Files\\WinRAR\\rar x -y " + foldertounzip + "\\Crisilfe.rar " + foldertowhichcopystockpile;
         //   st = sourcefilename + "\\unzip.bat " + foldertounzip + "\\Crisilfe.rar " + foldertowhichcopystockpile + "  " + foldertounzip;
            //   String st = "c:\\Program Files\\WinRAR\\rar x -y " + foldertounzip + "\\Crisilfe.rar " + foldertowhichcopystockpile;
            //Logging.debug(st);
         //   pro = Runtime.getRuntime().exec(st);
            //Logging.debug("Unzipping  Crisilfe.rar file in progress...");

        //    pro.waitFor();              //if necessary, wait until process is over.
            //Logging.debug("Waiting for Crisilfe.rar file...");
        //    pro.destroy();
            //Logging.debug("Unzipping for Crisilfe.rar file over...");
      //      if (tomcatpresent) {
      //          try {
      //              File sourceCrisilfe = new File(foldertowhichcopystockpile + "\\Crisil\\jakarta-tomcat-4.0.4\\webapps\\Crisilfe");
     //               File targetCrisilfe = new File(tomcatwebapps + "\\webapps\\");
                    //Logging.debug("sourceCrisilfe :" + sourceCrisilfe);
                    //Logging.debug("targetCrisilfe :" + targetCrisilfe);
       //             if (sourceCrisilfe.exists() && targetCrisilfe.exists()) {
                        //     l2inp3.setText("Transfering crisil web application to target tomcat");
        //                //Logging.debug("Transfering crisil web application to target tomcat");
      //                  copyDirectory(sourceCrisilfe, targetCrisilfe);
        ///                aJProgressBar.setIndeterminate(false);

         //           }
        //        } catch (Exception e) {
       //             Logging.debug(e.getMessage());
       //         }

       //     }
            if(flag){
                Logging.debug("Files copied Successfully");
             flag=makeChangesIntoFiles();
            } else {
                System.err.println("Error in copying files");
            }

            //Logging.debug("moving original profile file...");
        //    copydumpfile("\\template\\profile_start");
            //Logging.debug("Copied original fileDone...");
    //        t = new Thread(this);
     //       t.start();
    //        t.join();

        } catch (Exception e) {
            Logging.debug(e);
        }
        return flag;

    }

      /** Returns an ImageIcon, or null if the path was invalid. */
    protected static String getbaseFolder(String path) {
        java.net.URL imgURL = InstallStockPile.class.getResource(path);
          String base;
        if (imgURL != null) {
             base=imgURL.toString().trim();
            System.err.println("File absolute name: " + base);
            base=base.substring(6,base.indexOf("StockPile")+new String("StockPile").length());
            System.err.println("File absolute name: " + base);
            return base;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = InstallStockPile.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    void setLabel(String newText) {
        //  label.setText(newText);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    private class ExitListener extends WindowAdapter {
        /**
         * windowClosed
         *
         * @param e A WindowEvent for a closed Window event
         */
        public void windowClosed(WindowEvent e) {
            System.exit(0);
        }

        /**
         * windowClosing for a closing window event
         *
         * @param e A WindowEvent for a closing window event
         */
        public void windowClosing(WindowEvent e) {
            ((Window) e.getSource()).dispose();
        }
    }

    public boolean copyDirectory(File aSourceFolder, File aTargetFolder) {
        boolean flag = false;
        aTargetFolder.mkdirs();                  //if target folder doesnot exists then create one

        if (aSourceFolder.exists() && aSourceFolder.isDirectory()) {
            File[] files = aSourceFolder.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    File dest = new File(aTargetFolder.getAbsolutePath() + "\\" + files[i].getName());
                    flag = copyDirectory(files[i], dest);
                } else {
                    flag = copyFileToFolder(files[i], aTargetFolder);
                }
            }
        }
        return flag;
    }


    public boolean copyFileToFolder(File aSourceFile, File aTargetFolder) {
        boolean flag = false;
        if (aSourceFile != null && aTargetFolder != null) {
            try {
                FileInputStream fin = new FileInputStream(aSourceFile);
                String targetFileName = aTargetFolder.getAbsolutePath() + "\\" + aSourceFile.getName();
                File targetFile = new File(targetFileName);
                FileOutputStream fout = new FileOutputStream(targetFile);
                byte[] b = new byte[(int) aSourceFile.length()];

                fin.read(b);
                fout.write(b);

                fout.close();
                fin.close();
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }

    public boolean makeChangesIntoFiles() {
        boolean flag=false;
        try {
        //    if (!tomcatpresent) {

                doChanges("\\template\\catalina.txt", "StockPile\\code\\Tomcat\\bin\\catalina.bat");
        //    }
      //       copydumpfile("\\template\\profile_start");
            doChanges1();
  //          if (!tomcatpresent) {
   //             doChanges2("\\template\\server.xml", "Crisil\\jakarta-tomcat-4.0.4\\conf\\server.xml");
             //   doChanges("\\template\\tomcatstart.txt", "Crisil\\StartTomcat.bat");
   //             doChangesforstarttomcat();
//            }
            doChanges3("\\template\\register.txt", "StockPile\\database\\CYGWIN\\bin\\Register.reg");
           flag=true;
        } catch (Exception e) {
            Logging.debug(e);
        }
        return  flag;
    }

    public void doChangesforstarttomcat() {
           try {
               FileOutputStream fout = new FileOutputStream(filename + "\\crisil\\StartTomcat.bat");
               OutputStreamWriter out = new OutputStreamWriter(fout);
               Character drive = new Character(filename.charAt(0));
               String tempdrive = drive.toString();
               stringforregfile = tempdrive + ":\\\\";
               int count = 0;
               out.write("echo Starting CRISIL Front Ends Tomcat\r\n");
               out.write("@echo off\r\n");
               out.write(tempdrive + ":\r\n");
               out.write("cd\\ \r\n");
               out.write("chdir " + filename + "\\CRISIL\\jakarta-tomcat-4.0.4 \r\n");
             //  out.write("set CATALINA_HOME=%cd% \r\n");
               out.write(".\\bin\\startup \r\n");
               out.close();
           } catch (IOException e) {
               Logging.debug(e);
           } catch (Exception e) {
               Logging.debug(e);
           }
       }



    static public final byte[] ReadFile(String strFile) throws IOException {
        int nSize = 32768;
        // open the input file stream
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(strFile), nSize);
        byte[] pBuffer = new byte[nSize];
        int nPos = 0;
        // read bytes into a buffer
        nPos += inStream.read(pBuffer, nPos, nSize - nPos);
        // while the buffer is filled, double the buffer size and read more
        //Logging.debug("came nicely");
        while (nPos == nSize) {
            byte[] pTemp = pBuffer;
            nSize *= 2;
            pBuffer = new byte[nSize];
            System.arraycopy(pTemp, 0, pBuffer, 0, nPos);
            nPos += inStream.read(pBuffer, nPos, nSize - nPos);
        }
        // close the input stream
        inStream.close();
        if (nPos == 0) {
            return "".getBytes();
        }
        // return data read into the buffer as a byte array
        byte[] pData = new byte[nPos];
        System.arraycopy(pBuffer, 0, pData, 0, nPos);
        return pData;
    }

    // helper function to write a byte array into a file
    static public final void WriteFile(String strFile, byte[] pData) throws IOException {
        BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(strFile), 32768);
        if (pData.length > 0) outStream.write(pData, 0, pData.length);
        outStream.close();
    }

    public boolean invalidint(String local) {
        int length = local.length();
        boolean b = false;
        char[] charr = new char[length + 1];
        charr = local.toCharArray();
        for (int i = 0; i < length; i++) {
            if (Character.isLetter(charr[i])) {
                b = true;
                break;
            }

        }
        return b;
    }


    public void doChanges(String filenametobechanged, String changedfilename) {
        try {
            Logging.debug(filename);
            Logging.debug(sourcefilename);
            String source = sourcefilename + filenametobechanged;
            //Logging.debug("Source name is :  " + source);
            File templateFile = new File(source);
            String replaceString,replaceWith;
            replaceString = "C:\\";
            replaceWith = filename + "\\";
            //Logging.debug(templateFile.getAbsolutePath());
            //Logging.debug(templateFile.exists());
            if (templateFile.exists()) {
                try {
                    //Logging.debug("Inside :  " + templateFile.getAbsolutePath());
                    StringBuffer strOutput = new StringBuffer();
                    //Logging.debug("Inside1 :  " + templateFile.getAbsolutePath());
                    byte[] pInput = ReadFile(source);
                    //Logging.debug("Inside2 :  " + templateFile.getAbsolutePath());
                    String strInput = new String(pInput);
                   // Logging.debug("Replacing \"" + replaceString + "\" with \"" + replaceWith + "\" in file: " + source);

                    int nPos = 0;
                    while (true) {
                        int nIndex = strInput.indexOf(replaceString, nPos);
                        //Logging.debug(nIndex);
                        if (nIndex < 0) {
                            strOutput.append(strInput.substring(nPos));
                            break;
                        } else {
                            strOutput.append(strInput.substring(nPos, nIndex));
                            strOutput.append(replaceWith);
                            nPos = nIndex + replaceString.length();
                        }
                    }
                    strInput = strOutput.toString();
                    String target = filename + "\\" + changedfilename;
                    //Logging.debug(target);
                    WriteFile(target, strInput.getBytes());
                } catch (Exception e) {
                }
            } else {
                //Logging.debug("inside else : " + templateFile.exists());
                JOptionPane.showMessageDialog(nextButton1,
                        "File " + source + " not found",
                        "File not found ??",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Logging.debug(e);
        }
    }


    //code to generate InstallCRISIL_EV.bat
    public void doChanges1_old() {
        try {
            Logging.debug("Creating InstallCRISIL_EV.bat : ");
            Logging.debug(filename + "\\crisil\\InstallCRISIL_EV.bat");
            FileOutputStream fout = new FileOutputStream(filename + "\\crisil\\InstallCRISIL_EV.bat");
            OutputStreamWriter out = new OutputStreamWriter(fout);
            Character drive = new Character(filename.charAt(0));
            String tempdrive = drive.toString();
            stringforregfile = tempdrive + ":\\\\";
            int count = 0;
            out.write("echo Installing CRISIL EV Database \r\n");
            out.write("@echo off\r\n");
            out.write(tempdrive + ":\r\n");
            out.write("chdir " + filename + "\\CRISIL\\cygwin\\bin\r\n");
            out.write("echo To stop it please run following command\r\n");
            out.write("echo \"pg_ctl stop -w -D /usr/share/postgresql/data -s\"\r\n");
            out.write("bash exit\r\n");
            out.write("cd /cygdrive/" + tempdrive + "/");
            String smallString = filename.substring(2);
            StringTokenizer stk = new StringTokenizer(smallString, "\\");
            if (stk != null) {
                while (stk.hasMoreTokens()) {
                    String temp = stk.nextToken();
                    out.write(temp + "/");
                    if (count == 0) {
                        count++;
                    }
                    stringforregfile = stringforregfile + temp + "\\\\";

                }
            }
            out.write("crisil/cygwin/bin\r\n");
            out.write("ln -s postgres.exe postmaster\r\n");
            out.write("bash --login -i\r\n");
            out.close();
        } catch (IOException e) {
            Logging.debug(e);
        } catch (Exception e) {
            Logging.debug(e);
        }
    }


    //code to generate InstallCRISIL_EV.bat
    public void doChangesintomcatstart() {
        try {
            //Logging.debug("Creating Starttomcat.bat : ");
            //Logging.debug(filename + "\\crisil\\InstallCRISIL_EV.bat");
            FileOutputStream fout = new FileOutputStream(filename + "\\crisil\\StartTomcat.bat");
            OutputStreamWriter out = new OutputStreamWriter(fout);
            Character drive = new Character(filename.charAt(0));
            String tempdrive = drive.toString();
            stringforregfile = tempdrive + ":\\\\";
            int count = 0;
            out.write("REM Starting CRISIL EV Front Ends Tomcat \r\n");
            out.write("@echo off\r\n");
            out.write(tempdrive + ":\r\n");
            out.write("cd " + filename + "\\CRISIL\\jakarta-tomcat-4.0.4\r\n");
            out.write("set crisil=%cd%\\\r\n");
            out.write("echo To stop it please run following command\r\n");
            out.write("echo \"pg_ctl stop -w -D /usr/share/postgresql/data -s\"\r\n");
            out.write("bash exit\r\n");
            out.write("cd /cygdrive/" + tempdrive + "/");
            String smallString = filename.substring(2);
            StringTokenizer stk = new StringTokenizer(smallString, "\\");
            if (stk != null) {
                while (stk.hasMoreTokens()) {
                    String temp = stk.nextToken();
                    out.write(temp + "/");
                    if (count == 0) {
                        count++;
                    }
                    stringforregfile = stringforregfile + temp + "\\\\";

                }
            }
            out.write("crisil/cygwin/bin\r\n");
            out.write("ln -s postgres.exe postmaster\r\n");
            out.write("bash --login -i\r\n");
            out.close();
        } catch (IOException e) {
            Logging.debug(e);
        } catch (Exception e) {
            Logging.debug(e);
        }
    }

    //code to generate InstallCRISIL_EV.bat
    public void doChanges1() {
        try {
            //filename="D:\\StockPile";
            Logging.debug("Creating StartStockPile.bat : ");
            Logging.debug(filename + "\\StockPile\\StartTomcatServer.bat");
            FileOutputStream fout = new FileOutputStream(filename + "\\StockPile\\StartTomcatServer.bat");
            OutputStreamWriter out = new OutputStreamWriter(fout);
            Character drive = new Character(filename.charAt(0));
            String tempdrive = drive.toString();
            stringforregfile = tempdrive + ":\\\\";
            int count = 0;
             String smallString = filename.substring(2);
            StringTokenizer stk = new StringTokenizer(smallString, "\\");
            if (stk != null) {
                while (stk.hasMoreTokens()) {
                    String temp = stk.nextToken();
                    //     out.write(temp + "/");
                    if (count == 0) {
                        count++;
                    }
                    stringforregfile = stringforregfile + temp + "\\\\";

                }
            }
            Logging.debug("stringforregfile :"+stringforregfile);
            out.write("echo Installing StockPile Database \r\n");
            out.write("@echo off\r\n");
             out.write("set CATALINA_HOME="+stringforregfile+"StockPile\\code\\Tomcat\r\n");
             out.write(stringforregfile+"StockPile\\code\\Tomcat\\bin\\startup.bat\r\n");

            //  out.write("bash exit\r\n");
            //  out.write("cd /cygdrive/" + tempdrive + "/");

            //  out.write("crisil/cygwin/bin\r\n");
            stringforregfile1=stringforregfile;
            Logging.debug("String for reg file "+stringforregfile);
            //    out.write("ln -s postgres.exe postmaster\r\n");
            out.write("bash --login -i\r\n");
             out.write("echo done\r\n");
            out.close();
            doChangesfordatabase();
        } catch (IOException e) {
            Logging.debug(e);
        } catch (Exception e) {
            Logging.debug(e);
        }
    }

     public void doChangesfordatabase() {
        try {
            //filename="D:\\StockPile";
            File postmaster=new File(filename+"\\StockPile\\database\\cygwin\\bin\\postmaster");
             Logging.debug("postmaster "+postmaster+"  : "+postmaster.exists());
            if(postmaster.exists()){
                postmaster.delete();
                 Logging.debug("postmaster 1 "+postmaster+"  : "+postmaster.exists());
            }
              Logging.debug("postmaster 2 "+postmaster+"  : "+postmaster.exists());
            Logging.debug("Creating StartStockPile.bat : ");
            Logging.debug(filename + "\\StockPile\\StartDatabse.bat");
            FileOutputStream fout = new FileOutputStream(filename + "\\StockPile\\StartDataBase.bat");
            OutputStreamWriter out = new OutputStreamWriter(fout);
            Character drive = new Character(filename.charAt(0));
            String tempdrive = drive.toString();
            stringforregfile = tempdrive + ":\\\\";
            int count = 0;
             String smallString = filename.substring(2);
            StringTokenizer stk = new StringTokenizer(smallString, "\\");
            String temp1="";
            if (stk != null) {
                while (stk.hasMoreTokens()) {
                    String temp = stk.nextToken();
                    temp1=temp1+temp+ "/";
                    //     out.write(temp + "/");
                    if (count == 0) {
                        count++;
                    }
                    stringforregfile = stringforregfile + temp + "\\\\";

                }
            }
            Logging.debug("stringforregfile :"+stringforregfile);
            out.write("echo Installing StockPile Database \r\n");
            out.write("@echo off\r\n");
            out.write(tempdrive + ":\r\n");
            out.write("chdir " + filename + "\\StockPile\\database\\cygwin\\bin\r\n");
            out.write("echo To stop it please run following command\r\n");
            out.write("echo \"pg_ctl stop -w -D /usr/local/pgsql/data -s\"\r\n");
       //     ln -s postgres.exe postmaster
              out.write("bash exit\r\n");
             out.write("cd /cygdrive/" + tempdrive + "/");

              out.write(temp1+"stockpile/cygwin/bin\r\n");
            stringforregfile1=stringforregfile;
            Logging.debug("String for reg file "+stringforregfile);
       //     out.write("chdir " + filename + "\\StockPile\\database\\cygwin\\bin\r\n");
               out.write("ln -s postgres.exe postmaster\r\n");
            out.write("bash --login -i\r\n");
               out.write("pause\r\n");
             out.write("echo done\r\n");
            out.close();
        } catch (IOException e) {
            Logging.debug(e);
        } catch (Exception e) {
            Logging.debug(e);
        }
    }

    public void doChanges2(String filenametobechanged, String changedfilename) {
        try {
            //Logging.debug("destination filename  :" + filename);
            //Logging.debug("source file name in doChanges2  :" + sourcefilename);
            String source = sourcefilename + filenametobechanged;
            //Logging.debug("Source name is :  " + source);
            File templateFile = new File(source);
            String replaceString,replaceWith;
            replaceString = "porttobechanged";
            replaceWith = portnumber;
            //Logging.debug(templateFile.getAbsolutePath());
            //Logging.debug(templateFile.exists());
            if (templateFile.exists()) {
                try {
                    //Logging.debug("Inside :  " + templateFile.getAbsolutePath());
                    StringBuffer strOutput = new StringBuffer();
                    //Logging.debug("Inside1 :  " + templateFile.getAbsolutePath());
                    byte[] pInput = ReadFile(source);
                    //Logging.debug("Inside2 :  " + templateFile.getAbsolutePath());
                    String strInput = new String(pInput);
                    //Logging.debug("Replacing \"" + replaceString + "\" with \"" + replaceWith + "\" in file: " + source);

                    int nPos = 0;
                    while (true) {
                        int nIndex = strInput.indexOf(replaceString, nPos);
                        //Logging.debug(nIndex);
                        if (nIndex < 0) {
                            strOutput.append(strInput.substring(nPos));
                            break;
                        } else {
                            strOutput.append(strInput.substring(nPos, nIndex));
                            strOutput.append(replaceWith);
                            nPos = nIndex + replaceString.length();
                        }
                    }
                    strInput = strOutput.toString();
                    String target = filename + "\\" + changedfilename;
                    //Logging.debug(target);
                    WriteFile(target, strInput.getBytes());
                } catch (Exception e) {
                }
            } else {
                //Logging.debug("inside else : " + templateFile.exists());
                JOptionPane.showMessageDialog(nextButton1,
                        "File " + source + " not found",
                        "File not found ??",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Logging.debug(e);
        }
    }



    public void doChanges3(String filenametobechanged, String changedfilename) {
        try {
            //Logging.debug("destination file name : " + filename);
            //Logging.debug(sourcefilename);
            String source = sourcefilename + filenametobechanged;
            Logging.debug("Source name is :  " + source);
            File templateFile = new File(source);
             Logging.debug("templateFile : "+templateFile.exists());
            String replaceString,replaceWith;
            replaceString = "C:\\\\";
            Logging.debug("string got for reg file"+stringforregfile1);
            replaceWith = stringforregfile1;
            //Logging.debug(templateFile.getAbsolutePath());
            //Logging.debug(templateFile.exists());
            if (templateFile.exists()) {
                try {
                    //Logging.debug("Inside :  " + templateFile.getAbsolutePath());
                    StringBuffer strOutput = new StringBuffer();
                    //Logging.debug("Inside1 :  " + templateFile.getAbsolutePath());
                    byte[] pInput = ReadFile(source);
                    //Logging.debug("Inside2 :  " + templateFile.getAbsolutePath());
                    String strInput = new String(pInput);
                    //Logging.debug("Replacing \"" + replaceString + "\" with \"" + replaceWith + "\" in file: " + source);

                    int nPos = 0;
                    while (true) {
                        int nIndex = strInput.indexOf(replaceString, nPos);
                        //Logging.debug(nIndex);
                        if (nIndex < 0) {
                            strOutput.append(strInput.substring(nPos));
                            break;
                        } else {
                            strOutput.append(strInput.substring(nPos, nIndex));
                            strOutput.append(replaceWith);
                            nPos = nIndex + replaceString.length();
                        }
                    }
                    strInput = strOutput.toString();
                    String target = filename + "\\" + changedfilename;
                    //Logging.debug(target);
                    WriteFile(target, strInput.getBytes());
                } catch (Exception e) {
                }
            } else {
                //Logging.debug("inside else : " + templateFile.exists());
                JOptionPane.showMessageDialog(nextButton1,
                        "File " + source + " not found",
                        "File not found ??",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Logging.debug(e);
        }
    }



    public boolean copydumpfile(String SourceFile) {
        boolean flag = false;
        SourceFile = sourcefilename + SourceFile;
   //     SourceFile="Z:\\cygwin\\bin\\postmaster";
        Logging.debug("SourceFile postmaster :"+SourceFile);
        String target = filename + "\\StockPile\\database\\cygwin\\bin\\postmaster";
        Logging.debug("target postmaster :"+target);
        File aTargetFolder = new File(target);
        File aSourceFile = new File(SourceFile);
         Logging.debug("aTargetFolder :"+aTargetFolder.exists());
        Logging.debug("aSourceFile :"+aSourceFile.exists());
        if (aSourceFile != null && aTargetFolder != null) {
            try {
                FileInputStream fin = new FileInputStream(aSourceFile);
                String targetFileName = aTargetFolder.getAbsolutePath();
                File targetFile = new File(targetFileName);
                FileOutputStream fout = new FileOutputStream(targetFile);
                byte[] b = new byte[(int) aSourceFile.length()];

                fin.read(b);
                fout.write(b);

                fout.close();
                fin.close();
                flag = true;
                 Logging.debug("SourceFile postmaster 1:"+SourceFile);
            } catch (Exception e) {
                 Logging.debug("SourceFile postmaster 2:"+e);
              //  e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }


    public boolean isPortAvailable(int portno) {
        try {
            ServerSocket ss = new ServerSocket(portno);
            Logging.debug("port Available");
            ss.close();
            return true;
        } catch (Exception e) {
            Logging.debug("port unAvailable");
        }
        return false;
    }


    public boolean copydumpFileToclientcrisil(File aSourceFile, File aTargetFolder) {
        boolean flag = false;
        if (aSourceFile != null && aTargetFolder != null) {
            try {
                FileInputStream fin = new FileInputStream(aSourceFile);
                String targetFileName = aTargetFolder.getAbsolutePath() + "\\" + aSourceFile.getName();
                File targetFile = new File(targetFileName);
                FileOutputStream fout = new FileOutputStream(targetFile);
                byte[] b = new byte[(int) aSourceFile.length()];

                fin.read(b);
                fout.write(b);

                fout.close();
                fin.close();
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }

    public void run() {

        //implement startup here
        try {
   //         Thread.sleep(20000);
            try {
                         Logging.debug("Registering Database Commands");
                        String st = sourcefilename + "\\RegisterCommands.bat " + filename + "\\StockPile\\database\\cygwin\\bin\\Register.reg";
                        //   String st = "c:\\Program Files\\WinRAR\\rar x -y " + foldertounzip + "\\Crisilfe.rar " + foldertowhichcopystockpile;
                        //Logging.debug(st);
                        Process pro = Runtime.getRuntime().exec(st);
                         pro.waitFor();
                        Logging.debug(" Database Commands Registered");
                        witeIntoDesktop();
                          Logging.debug("Starting Tomcat in progress");
                         try {
                         //    copydumpfile("\\template\\postmaster");
                             File s= new File(sourcefilename+"\\template\\bin");
                             File d=new File(filename + "\\StockPile\\database\\cygwin\\bin");
                              File d1=new File(filename + "\\StockPile\\database\\cygwin\\bin");
                              //   Logging.debug("D1 : "+copydumpfile("\\template\\postmaster"));
                             if(d1.exists()){
                   //              d1.delete();
                                 Logging.debug("d deleted ");
                                 Thread.sleep(10000);
                             }
                               Logging.debug("s : "+s.exists()+"  : "+s.getAbsolutePath());
                             Logging.debug("d : "+d.exists()+"  : "+d.getAbsolutePath());
                         //     Logging.debug("copyDirectory(s,d) : "+copyDirectory(s,d));
                        //     copyDirectory(s,d);
                    File pid = new File(filename + "\\StockPile\\database\\cygwin\\usr\\local\\pgsql\\data\\postmaster.pid");
                    pid.delete();
                    Logging.debug("pid file found");
                } catch (Exception e) {
                    Logging.debug("pid file not found");
                }

               File postmaster=new File(filename+"\\StockPile\\database\\cygwin\\bin\\postmaster");
             Logging.debug("postmaster "+postmaster+"  : "+postmaster.exists());
            if(postmaster.exists()){
                postmaster.delete();
                 Logging.debug("postmaster 1 "+postmaster+"  : "+postmaster.exists());
            }

                Thread.sleep(30000);
                           Logging.debug("Starting StockPile Application  ");
                          st = filename + "\\StockPile\\StartDataBase.bat" ;
                        //   String st = "c:\\Program Files\\WinRAR\\rar x -y " + foldertounzip + "\\Crisilfe.rar " + foldertowhichcopystockpile;
                        Logging.debug(st);
                File ff=new File(st);
                Logging.debug("StartDataBase.bat : "+ff.exists());
                         pro = Runtime.getRuntime().exec("cmd.exe /c start "+st);
                 Thread.sleep(10000);
                 st = filename + "\\StockPile\\StartTomcatServer.bat" ;
                        //   String st = "c:\\Program Files\\WinRAR\\rar x -y " + foldertounzip + "\\Crisilfe.rar " + foldertowhichcopystockpile;
                        //Logging.debug(st);
                         pro = Runtime.getRuntime().exec(st);
                    //      pro.waitFor();
                        Logging.debug("StockPile Application  Started");


                    } catch (Exception e) {
                    }
            //Logging.debug("Done...");

        } catch (Exception e) {
            Logging.debug("Problem with database creation");
        }
    }

    public void updateDatabase(){
         boolean flag = createClientDBConnection();
        if(flag){
         //   readPropertyFile();
            eraseExtraData(0);
            insertParameterDetails();
            witeIntoDesktop();
        }
    }

      private boolean createClientDBConnection() {
          try{
        clientConn = getDbConnection("org.postgresql.Driver", "jdbc:postgresql://localhost/crisil", "crisil", "");  //connectiing to client database
          }catch(Exception e){
        	  //e.printStackTrace();
        	  Logging.debug(e);
        	  }
        if (clientConn == null) {
            Logging.debug("Cannot connect to PostgreSQL database");
            return false;
        } else {
            return true;
        }
    }

     public Connection getDbConnection(String aDriver, String aUrl, String aUserName, String aPassword) {
        Connection conn = null;
        try {
            Class.forName(aDriver);
            conn = DriverManager.getConnection(aUrl, aUserName, aPassword);
        } catch (Exception e) {
          //  e.printStackTrace();
        	 Logging.debug(e);
        }

        return conn;
    }

     public void eraseExtraData(int ci) {
        try {

            //Logging.debug("Erasing Extra data...");

            try {
                Statement stmtClient = clientConn.createStatement();
                if (stmtClient != null) {
                    try {
                        String query;
                        try {
                            query="delete from subscriptions where clientcode<>'" + clientCodes + "' and recipientcode<>'" + recipientCodes + "'";
                            //Logging.debug(query);
                            stmtClient.executeQuery(query);
                        } catch (Exception e) {
                             Logging.debug(e);
                        }
                        try {
                             query="delete from clientdetails where clientcode<>'" + clientCodes + "' and recipientcode<>'" + recipientCodes + "'";
                           // Logging.debug(query);
                            stmtClient.executeQuery(query);
                            //stmtClient.executeQuery("delete from clientdetails where clientcode<>'" + clientCodes[ci] + "' and recipientcode<>'" + recipientCodes[ci] + "'");
                        } catch (Exception e) {
                            Logging.debug(e);
                        }
                    } catch (Exception e) {
                    }
                    stmtClient.close();
                }
            } catch (Exception e) {
                Logging.debug("Error in deleting extra data: " + e);
            }

        } catch (Exception e) {

        }
    }




    public void insertParameterDetails(){

         try {
             Statement stmtClient = clientConn.createStatement();
             ResultSet rs;String query,lupdate,llupdate;
             lupdate=lastUpdatedDates;llupdate=lastToLastUpdatedDates;
                 rs = stmtClient.executeQuery("select * from parameter");
                //  Logging.debug("1");
                if (rs.next()) {
                    Logging.debug("Before Updating parameter");
                    query = "update  parameter set lastupdated= '" + lupdate + "', lasttolastupdated='" + llupdate + "' where clientcode = '" + clientCodes + "' and recipientcode = '" + recipientCodes + "'";
                    Logging.debug(query);
                    rs = stmtClient.executeQuery(query);
                } else {
                    Logging.debug("Before Updating parameter");
                    query = "insert into parameter (clientcode,recipientcode,lastupdated,location) values ('" + clientCodes + "' , '" + recipientCodes + "', '" + lupdate  + "','India' )";
                    Logging.debug(query);
                    rs = stmtClient.executeQuery(query);
                }

            } catch (Exception e) {
                Logging.debug("Error in deleting extra data: " + e);
            }


    }

    public void witeIntoDesktop(){
        try{ Process p; Runtime r = Runtime.getRuntime();
            Properties envVars = new Properties();
             p = r.exec("cmd.exe /c set");
            BufferedReader br = new BufferedReader
                (new InputStreamReader(p.getInputStream()));
        String line;
        String value1 = " ";
        while ((line = br.readLine()) != null) {
            int idx = line.indexOf('=');
            String key = line.substring(0, idx);
            String value = line.substring(idx + 1);
            if (key.trim().equalsIgnoreCase("COMPUTERNAME") || key.trim().equalsIgnoreCase("USERPROFILE")) {
                envVars.setProperty(key, value);
            //    Logging.debug(key + " = " + value);
            }
        }
            FileOutputStream fout1 = new FileOutputStream(filename+"\\Stockpile\\url.properties");
           envVars.store(fout1, null);
   //         Logging.debug("1");
          //  File f=new File(envVars);

            envVars.load(new FileInputStream(filename+"\\Stockpile\\url.properties"));
            String cname=envVars.getProperty(new String("COMPUTERNAME").trim());
            String desktop=envVars.getProperty(new String("USERPROFILE").trim())+"\\Desktop";
             //String portnumber="8080";
          //   File f=new File("desktop\\url.txt");
         //   Logging.debug(desktop+"\\url.txt");
          ///  Logging.debug("1");
         //   Logging.debug(desktop+"\\url.txt");
          ///  Logging.debug("1");
             FileOutputStream fout = new FileOutputStream(desktop+"\\url.txt");
             OutputStreamWriter out = new OutputStreamWriter(fout);
         //   Logging.debug("1");
            out.write(" URL to open the StockPile Application\r\n");
            out.write("Paste this URL into the browser \r\n");
            out.write( "http://"+cname+":"+portnumber+"/Income\r\n");
            out.close();
            File f=new File(filename+"\\crisil\\url.properties");
            f.delete();
        }catch(Exception e){
            Logging.debug("Error "+e+" occured while into desktop");
        }
    }

}

