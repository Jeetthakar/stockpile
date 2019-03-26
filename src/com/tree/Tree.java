package com.tree;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * Created by IntelliJ IDEA. User: vivek Date: Feb 12, 2005 Time: 6:47:57 PM To
 * change this template use File | Settings | File Templates.
 */
public class Tree {
	Logger Logging = Logger.getLogger(Tree.class);
	Connection con;
	// int index = -1;
	static String useride;
	int index3 = -1, index4 = 0;
	int ii = 0, iii = -1;
	int aab = 0;
	boolean flagindex = false;
	boolean flagstock = false;
	boolean done = false, done1 = false, done2 = false;
	public Object[][] indexArray;
	Object[][] indus_Array;
	String FileString;
	Connect connect;
	Hashtable hashtable = new Hashtable();
	Hashtable myhashtable = new Hashtable();
	Hashtable newLayer = new Hashtable();
	Hashtable companyClassify = new Hashtable();
	Hashtable stockdetails = new Hashtable();
	StringBuffer stringBuffer = new StringBuffer();
	StringBuffer stringBuffer_classification = new StringBuffer();
	String temp_var;

	/**
     * 
     */
	public Tree() {
		connect = ConnectInit.getConnect();
		// getDbConnection("org.postgresql.Driver",
		// "jdbc:postgresql://192.168.0.5/income", "administrator", "");
		// con=connect.getConnection();
	}

	// ArrayList temp,temp1;
	public static void main(String[] z) {
		Tree t1 = new Tree();
		t1.getDbConnection("org.postgresql.Driver",
				"jdbc:postgresql://192.168.0.5/income", "administrator", "");
		// t1.getConnection();
		// t1.constructTree("");
		t1.constructIndustryClassificationTree("");
		t1.drawIndustryClassificationTree();
		/*
		 * try { for (int cc = 0; cc <= t1.iii; cc++) { if (((Hashtable)
		 * t1.indexArray[cc][2]) != null) { //Logging.debug(cc + " : " +
		 * (((Hashtable) t1.indexArray[cc][2])).size()); } } } catch (Exception
		 * e) { e.printStackTrace(); } t1.drawTree();
		 */
	}

	public Object[][] getIndexArray() {
		return indexArray;
	}

	public void clearSBClassification() {
		stringBuffer_classification = new StringBuffer();
	}

	public StringBuffer getSBClassification() {
		// Logging.debug("size : "+stringBuffer_classification.length());
		return stringBuffer_classification;
	}

	public void drawIndustryClassificationTree() {
		try {
			Enumeration e = myhashtable.keys();
			while (e.hasMoreElements()) {
				// Logging.debug("e.next done ");
				String id_code_name = (String) e.nextElement();
				// temp_var="<UL id=foldinglist style=\"DISPLAY: none; head: \">";
				// stringBuffer_classification.append(temp_var);
				temp_var = "<LI id=foldheader><P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial  color=blue size=1>"
						+ id_code_name + "</FONT>";
				stringBuffer_classification.append(temp_var);
				Object[][] ob = (Object[][]) myhashtable.get(id_code_name);
				int ix = 0;
				temp_var = "<UL id=foldinglist style=\"DISPLAY: none; head: \">";
				stringBuffer_classification.append(temp_var);
				while (ob[ix][0] != null) {
					IndustryClassificationNodes st = (((IndustryClassificationNodes) ob[ix][0]));
					// Logging.debug("class id : "+st.getClass_id());
					// Logging.debug("class name :"
					// +ix+" :: "+st.getClass_name());
					temp_var = "<LI id=foldheader><P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial  color=blue size=1>"
							+ st.getClass_name()
							+ "::"
							+ st.getLevel_name()
							+ "</FONT>";
					stringBuffer_classification.append(temp_var);

					if (ob[ix][1] != null) {
						ArrayList altemp = (ArrayList) ob[ix][1];
						temp_var = "<UL id=foldinglist style=\"DISPLAY: none; head: \">";
						stringBuffer_classification.append(temp_var);
						drawIndustryClassificationTreeNodes(altemp);
						temp_var = "</UL>";
						stringBuffer_classification.append(temp_var);
					}
					ix++;
					if (companyClassify.containsKey(st.class_id)) {
						ArrayList arrayList1 = (ArrayList) companyClassify
								.get(st.class_id);
						Iterator iterator = arrayList1.iterator();
						while (iterator.hasNext()) {
							String element = (String) iterator.next();
							String companyName = element.substring(0,
									element.indexOf("::"));
							String companyId = element
									.substring(element.indexOf("::") + 2,
											element.length());
							String stock_detail10 = "../pages/masters/stockmaster2.jsp?s_stockid="
									+ companyId;
							String href10 = "<a href=\""
									+ stock_detail10
									+ "\" Target=\"frmMain\" onmouseover=\"window.status='go!!';return true\">";// node_temp.getIndex_id();
							temp_var = "<LI><P style=\"MARGIN-LEFT: -45px\" align=left><FONT face=Arial  color=blue size=1>"
									+ href10
									+ companyName
									+ "::"
									+ st.getLevel_name() + "</FONT></a>";
							stringBuffer_classification.append(temp_var);
						}
						temp_var = "</LI>";
						stringBuffer_classification.append(temp_var);
					}

				}
				temp_var = "</LI></UL></LI>";
				stringBuffer_classification.append(temp_var);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			Logging.debug(e);
		}
	}

	public void clearSB() {
		stringBuffer = new StringBuffer();
	}

	public void drawIndustryClassificationTreeNodes(ArrayList myarrayList) {
		Iterator it = myarrayList.iterator();
		while (it.hasNext()) {
			Object[][] ob = (Object[][]) it.next();
			IndustryClassificationNodes st = (((IndustryClassificationNodes) ob[0][0]));
			// Logging.debug("node : "+st.getClass_name()+" :: "+st.getLevel_name());
			if (st.getClass_id().trim().equals("237")) {
				// Logging.debug("see from here  : "+st.getClass_name()+" :: "+st.getLevel_name());
			}
			// if(!it.hasNext()){
			// temp_var="</LI></UL>";
			// stringBuffer.append(temp_var);
			// }
			temp_var = "<LI id=foldheader><P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial  color=blue size=1>"
					+ st.getClass_name()
					+ "::"
					+ st.getLevel_name()
					+ "</FONT>";
			stringBuffer_classification.append(temp_var);
			if (ob[0][1] != null) {
				ArrayList altemp = (ArrayList) ob[0][1];
				temp_var = "<UL id=foldinglist style=\"DISPLAY: none; head: \">";
				stringBuffer_classification.append(temp_var);
				// Logging.debug("New Call  : "+st.getClass_name());
				drawIndustryClassificationTreeNodes(altemp);
				// Logging.debug("New Call 1 : "+st.getClass_name());
				// if(!it.hasNext()){
				if (companyClassify.containsKey(st.class_id)) {
					ArrayList arrayList1 = (ArrayList) companyClassify
							.get(st.class_id);
					Iterator iterator = arrayList1.iterator();

					while (iterator.hasNext()) {
						// Logging.debug("Drawing Company Node :: ccccccccccccccc");
						String element = (String) iterator.next();
						String companyName = element.substring(0,
								element.indexOf("::"));
						String companyId = element.substring(
								element.indexOf("::") + 2, element.length());
						String stock_detail11 = "../pages/masters/stockmaster2.jsp?s_stockid="
								+ companyId;
						String href11 = "<a href=\""
								+ stock_detail11
								+ "\" Target=\"frmMain\" onmouseover=\"window.status='go!!';return true\">";// node_temp.getIndex_id();
						temp_var = "<LI><P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial  color=blue size=1>"
								+ href11
								+ companyName
								+ "::"
								+ st.getLevel_name() + "</FONT></a>";
						stringBuffer_classification.append(temp_var);
					}
				}
				temp_var = "</LI></UL>";
				stringBuffer_classification.append(temp_var);
				// }
				// temp_var="</LI></UL>";
				// stringBuffer.append(temp_var);

			} else {

				if (companyClassify.containsKey(st.class_id)) {
					ArrayList arrayList1 = (ArrayList) companyClassify
							.get(st.class_id);
					Iterator iterator = arrayList1.iterator();
					boolean flag = false;
					if (iterator.hasNext()) {
						temp_var = "<UL id=foldinglist style=\"DISPLAY: none; head: \">";
						stringBuffer_classification.append(temp_var);
						flag = true;
					} else {
						temp_var = "<UL id=foldinglist style=\"DISPLAY: none; head: \">";
						stringBuffer_classification.append(temp_var);
						temp_var = "<LI><FONT face=Arial  color=blue size=1> No Stock Available </FONT></LI></UL>";
						stringBuffer_classification.append(temp_var);
						// flag=true;
					}
					while (iterator.hasNext()) {
						// Logging.debug("Drawing Company Node :: bbbbbbbbbbbbbbbbb");
						String element = (String) iterator.next();
						String companyName = element.substring(0,
								element.indexOf("::"));
						String companyId = element.substring(
								element.indexOf("::") + 2, element.length());
						String stock_detail12 = "../pages/masters/stockmaster2.jsp?s_stockid="
								+ companyId;
						String href12 = "<a href=\""
								+ stock_detail12
								+ "\" Target=\"frmMain\" onmouseover=\"window.status='go!!';return true\">";// node_temp.getIndex_id();
						temp_var = "<LI><P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial  color=blue size=1>"
								+ href12
								+ companyName
								+ "::"
								+ st.getLevel_name() + "</FONT></a>";
						stringBuffer_classification.append(temp_var);
					}
					if (flag) {
						temp_var = "</LI></UL>";
						stringBuffer_classification.append(temp_var);
						flag = false;
					}
				} else {
					temp_var = "<UL id=foldinglist style=\"DISPLAY: none; head: \">";
					stringBuffer_classification.append(temp_var);
					temp_var = "<LI><P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial  color=blue size=1> No Stock Available </FONT></LI></UL>";
					stringBuffer_classification.append(temp_var);
					// temp_var="</LI></UL>";
					// stringBuffer_classification.append(temp_var);
				}
				// temp_var="</LI></UL>";
				// stringBuffer.append(temp_var);
			}
			// code for companies

			temp_var = "</LI></UL>";
			stringBuffer.append(temp_var);
		}

		// temp_var="</LI></UL>";
		// stringBuffer_classification.append(temp_var);

	}

	public Connection getDbConnection(String aDriver, String aUrl,
			String aUserName, String aPassword) {
		Connection conn = null;
		try {
			Class.forName(aDriver);
			con = DriverManager.getConnection(aUrl, aUserName, aPassword);
		} catch (Exception e) {
			// e.printStackTrace();
			Logging.debug(e);
		}

		return conn;
	}

	public void drawTree() {
		try {

			FileOutputStream fout = new FileOutputStream(
					"D:\\3\\javascript\\data1.inc.js");
			OutputStreamWriter out = new OutputStreamWriter(fout);
			out.write("/*\r\n"
					+ "* data used for some of the javascript tree examples. share! :-)\r\n"
					+ "*/\r\n");
			out.write("var a = new Array;\r\n");

			out.write("a[0] = new Array;\r\n");
			out.write("a[0]['caption']          = \"Harrier\";\r\n");
			out.write("a[0]['url'] =\"http://www.yahoo.com/\";\r\n");
			out.write("a[0]['target'] =\"_blank\";\r\n");
			out.write("a[0]['children']         = new Array;\r\n");
			out.write("a[0]['children'][0] = new Array;\r\n");
			out.write("a[0]['children'][0]['caption']          = \"Indices\";\r\n");
			out.write("a[0]['children'][0]['url'] =\"http://www.yahoo.com/\";\r\n");
			out.write("a[0]['children'][0]['target'] =\"_blank\";\r\n");
			out.write("a[0]['children'][0]['children']         = new Array;\r\n");
			out.write("\r\n");
			ii = index3;
			String temp = "a[0]['children'][0]['children']";
			drawTreeNodes(indexArray, temp, out, -999, "parent");

			out.close();
		} catch (Exception e) {
			// e.printStackTrace();
			Logging.debug(e);
		}
	}

	public void constructIndustryClassificationTree(String query_name) {
		Connection connection = null;
		try {
			if (connection == null)
				connection = connect.getdbConnection();
			Hashtable ht_industryClassification = new Hashtable();
			ArrayList arrayList = new ArrayList();
			// String
			// query="select class_name,class_id,parent_class_id,level_name,cl.level_id,industry_classification_short_name,icm.industry_classification_id from industry_classification_master icm,classification_level cl,class c where icm.industry_classification_id=cl.industry_classification_id and cl.level_id=c.level_id order by class_id,parent_class_id";
			String query = ConnectInit.queries.getProperty(query_name);
			// Logging.debug("query : "+query);
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			String class_name = null, class_id = null, parent_class_id = null, level_name = null, level_id = null, industry_classification_short_name = null, industry_classification_id = null, company_name = null, company_id = null;

			int aa = 0;
			Hashtable h = new Hashtable();
			while (rst.next()) {
				class_name = rst.getString(1); // class_name
				class_id = rst.getString(2); // class_id
				parent_class_id = rst.getString(3); // parent_class_id
				level_name = rst.getString(4); // level_name
				level_id = rst.getString(5); // level_id
				industry_classification_short_name = rst.getString(6); // industry_classification_short_name
				industry_classification_id = rst.getString(7); // industry_classification_id
				try {
					if (rst.getString(8) != null)
						company_name = rst.getString(8).trim(); // company_name
					company_id = rst.getString(9); // company_id
				} catch (Exception e) {
					Logging.debug(" Error : " + e.getMessage());
				}

				if (company_id != null && company_id.trim().length() != 0) {
					if (!companyClassify.containsKey(class_id)) {
						ArrayList temp = new ArrayList();
						temp.add(company_name + "::" + company_id);
						companyClassify.put(class_id, temp);
					} else {
						ArrayList temp = (ArrayList) companyClassify
								.get(class_id);
						temp.add(company_name + "::" + company_id);
						companyClassify.put(class_id, temp);
					}
				}

				// Logging.debug("class_name " + class_name + "  level_name : "
				// + level_name);
				if (!ht_industryClassification.containsKey(class_id)) {
					ht_industryClassification.put(class_id,
							new IndustryClassificationNodes(class_name,
									class_id, parent_class_id, level_name,
									level_id,
									industry_classification_short_name,
									industry_classification_id));// ,company_name,company_id
					arrayList.add(class_id);
					if (!h.containsKey(industry_classification_id))
						h.put(industry_classification_id,
								industry_classification_id);
				}
				// Logging.debug("arrayList.size() " + arrayList.size() );
			}
			indus_Array = new Object[h.size()][2];
			ArrayList tempArraylist1 = new ArrayList();
			IndustryClassificationNodes nodes;
			Iterator it = arrayList.iterator();
			while (it.hasNext()) {
				done2 = false;
				nodes = (IndustryClassificationNodes) ht_industryClassification
						.get(it.next());
				if (nodes.getClass_id().equals("247")) {
					// Logging.debug("nodes.getClass_name() : "+
					// nodes.getClass_name());
				}
				// if(!tempArraylist1.contains(nodes.getIndustry_classification_id())){
				if (!myhashtable.containsKey(nodes
						.getIndustry_classification_short_name())) {
					myhashtable.put(
							nodes.getIndustry_classification_short_name(),
							new Object[50][2]);
					// indus_Array[tempArraylist1.size()][0]=nodes;
					// ((IndustryClassificationNodes)indus_Array[tempArraylist1.size()][0]).class_id=nodes.getIndustry_classification_short_name();
					tempArraylist1.add(nodes
							.getIndustry_classification_short_name());
					// nodes.setClass_id();
					// ArrayList al=new ArrayList();
					// al.add()
					// indus_Array[tempArraylist1.size()][1]=null;
					done2 = false;
					add_Ind_Node((Object[][]) myhashtable.get(nodes
							.getIndustry_classification_short_name()), nodes);
				} else {
					// Logging.debug("nodes : "+ nodes.getClass_id());
					// add_Ind_Node(indus_Array,nodes);
					Object[][] ob = (Object[][]) myhashtable.get(nodes
							.getIndustry_classification_short_name());
					try {
						int a = 0;
						while (ob[a][0] != null) {
							String st = (((IndustryClassificationNodes) ob[a][0])
									.getClass_id());
							// Logging.debug("ob[a][0] : "+ st);
							a++;
						}
					} catch (Exception e) {

					}
					add_Ind_Node((Object[][]) myhashtable.get(nodes
							.getIndustry_classification_short_name()), nodes);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.debug("Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
	}

	public void add_Ind_Node(Object[][] node, IndustryClassificationNodes value) {
		try {
			int ab = 0;
			try {
				while (node[ab][0] != null) {
					ab++;
				}
				// if (ab > 0) {
				// ab--;
				// }
			} catch (Exception e) {
				if (ab > 0) {
					ab--;
				}
			}
			if (done2) {
				return;
			}
			if (ab == 9) {
				// Logging.debug("st : ");
			}
			for (int i = 0; i <= ab; i++) {
				// Logging.debug("st : "+node[i][0]);
				String st = null;
				if (node[i][0] != null) {
					st = (((IndustryClassificationNodes) node[i][0])
							.getClass_id());
					IndustryClassificationNodes iu = (((IndustryClassificationNodes) node[i][0]));
					// Logging.debug("node class name : "+iu.getClass_name());
				} else {
					st = "";
				}
				// Logging.debug("st : "+st);
				String vt = value.getClass_id();
				// Logging.debug("vt : "+st);
				if (value.getParent_class_id() == null && node[ab][0] == null) {
					node[ab][0] = value;
					// ArrayList arr=new ArrayList();
					// Object[][] temp=new Object[1][2];
					// temp[0][0]=value;
					// temp[0][1]=null;
					// arr.add(temp);
					node[ab][1] = null;
					done2 = true;
					return;
				} else if (value.getParent_class_id() != null
						&& value.getParent_class_id().trim().equals(st)) {
					if (node[i][1] == null) {
						ArrayList arr = new ArrayList();
						Object[][] temp = new Object[1][2];
						temp[0][0] = value;
						temp[0][1] = null;
						arr.add(temp);
						node[i][1] = arr;
						// node[0][1]=arr;
					} else {
						ArrayList arr = (ArrayList) node[i][1];
						Object[][] temp = new Object[1][2];
						// Logging.debug("value : "+ value.getClass_id());
						temp[0][0] = value;
						temp[0][1] = null;
						arr.add(temp);
						node[i][1] = arr;
						// node[0][1]=arr;
					}

					done2 = true;
					return;
				} else if (value.getParent_class_id().trim().equals(st)) {
					// Object[][] node1=
					if (node[0][1] == null) {
						ArrayList arr = new ArrayList();
						Object[][] temp = new Object[1][2];
						temp[0][0] = value.getClass_id();
						temp[0][1] = null;
						arr.add(temp);
					} else {
						ArrayList arr = (ArrayList) node[0][1];
						Object[][] temp = new Object[1][2];
						temp[0][0] = value;
						temp[0][1] = null;
						arr.add(temp);
					}
				} else if (node[i][1] != null) {
					ArrayList arr = (ArrayList) node[i][1];
					Iterator it = arr.iterator();
					while (it.hasNext()) {
						Object[][] temp = (Object[][]) it.next();
						add_Ind_Node(temp, value);
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public Vector getProperVector(Hashtable parentHashtable, String parentid) {
		Vector hashtable_temp = null;
		try {
			if (parentHashtable.containsKey(parentid)) {

			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hashtable_temp;
	}

	public void constructTree(String query_name,Connection connection) {
		// getDbConnection("org.postgresql.Driver",
		// "jdbc:postgresql://192.168.0.11/income", "administrator", "");
		// HttpSession session;

		// String userid=this.useride;
		String usr_ID = getUseride();

		Hashtable ht = new Hashtable();
		Hashtable htcopy = new Hashtable();
		Hashtable Treeht = new Hashtable();
		ArrayList al = new ArrayList();
		Stack stack = new Stack();
		// int index;
		HashMap hm = new HashMap();
		HashMap hm1 = new HashMap();
//		Connection connection = null;
		try {
			
			if (connection == null)
				connection = connect.getdbConnection();
			// String query =
			// "select index_name,im.index_id,im.parent_id,sm.stock_name,sm.stock_id from index_master1 im, index_composition ic, stock_master  sm where im.index_id=ic.index_id and ic.stock_id=sm.stock_id order by index_id";
			// String query =
			// "select im.index_id,index_name,parent_id,ic.stock_name,ic.stock_id ,is_captured ,it.index_type_code from index_type it,index_master im left outer join (select ic.index_id,stock_name,ic.stock_id   from index_composition ic, stock_master sm where ic.stock_id=sm.stock_id  and ic.index_id in (select index_id from index_master where is_active='y')) ic on (ic.index_id=im.index_id)  where it.index_type_id=im.index_type_id order by  im.index_id,parent_id";
			// String
			// query="select im.index_id,index_name,parent_id,ic.stock_name,ic.stock_id ,is_captured ,it.index_type_code  from index_type it,index_master im left outer join (select ic.index_id,identifier_code_value as stock_name,ic.stock_id from index_composition ic,stock_master sm,stock_exchange_master sem,stock_identifier_codes sic where ic.stock_id=sm.stock_id  and sm.stock_id=sic.stock_id and sm.stock_exchange_id=sem.stock_ex_id and sem.identifier_code_id=sic.identifier_code_id and ic.index_id in (select index_id from index_master where is_active='y')) ic on (ic.index_id=im.index_id) where it.index_type_id=im.index_type_id order by  im.index_id,parent_id";
			String query = ConnectInit.queries.getProperty(query_name);
			// Logging.debug("query : "+query);
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, usr_ID);// Change by Manoj Adekar for userwise
										// tree
			ResultSet rst = stmt.executeQuery();
			String index_name, index_id, parent_id, stock_name, stock_id, is_captured, index_type_code;
			int aa = 0;
			ht.clear();
			while (rst.next()) {
				index_name = rst.getString(2); // index_name
				index_id = rst.getString(1); // index_id
				parent_id = rst.getString(3); // parent_id
				stock_name = rst.getString(4); // stock_name
				stock_id = rst.getString(5); // stock_id
				is_captured = rst.getString(6); // is_captured
				index_type_code = rst.getString(7); // index_type_code
				// //Logging.debug("index_id " + index_id + "  : " +
				// index_name);
				if (!ht.containsKey(index_id + stock_id)) {
					al.add(index_id + stock_id);
					// aa++;
					ht.put(index_id + stock_id, new TreeNode(index_name,
							index_id, parent_id, stock_name, stock_id,
							is_captured, index_type_code));
					// //Logging.debug("Total records : " + aa + "ht size : " +
					// ht.size() + "  indexname :" + index_name + stock_name);

				}

				if (parent_id == null) {
					if (!Treeht.containsKey(index_id)) {
						// al.add(index_id + stock_id);
						aa++;
						Treeht.put(index_id, index_id);
						// Logging.debug("index_id + parent_id : " + index_name
						// + "  " + parent_id);

					}
				}
			}

			index4 = aa;
			indexArray = new Object[aa][3];
			Iterator it = al.iterator();
			String enumname;
			TreeNode enumvalue;
			// while (enum.hasMoreElements()) {
			int index = 0, index2 = 0;
			ArrayList processedrecords = new ArrayList();
			while (it.hasNext()) {
				done = false;
				done1 = false;
				enumname = (String) it.next();

				enumvalue = (TreeNode) ht.get(enumname);
				if (enumvalue.getIndex_name().equals(
						"FF1000CC.Sector.Financials")) {
					// Logging.debug("FF1000CC.Sector.Financials found  : ");
				}

				boolean bx = processedrecords.contains(enumvalue.getIndex_id())
						|| processedrecords.contains(enumvalue.getParent_id());
				// Logging.debug("processedrecords.contains(enumvalue.getIndex_id())  : "
				// + bx);
				if (!bx) {
					processedrecords.add(enumvalue.getIndex_id());
					// index++;
					// Logging.debug("present at : " +
					// processedrecords.indexOf(enumvalue.getIndex_id()));
				} // else {
					// index =
					// processedrecords.indexOf(enumvalue.getIndex_id());
					// if(index==-1)
					// index =
					// processedrecords.indexOf(enumvalue.getParent_id());
					// //Logging.debug("index2 : " + index2++);

				// }
				// //Logging.debug("index : " + index);
				// JOptionPane.showMessageDialog(null,
				// enumvalue.getIndex_id()+"c Array p"+enumvalue.getParent_id(),
				// "ERROR!",
				// JOptionPane.ERROR_MESSAGE);
				String s = ":::";
				if (enumvalue.getParent_id() == null) {
					// add node
					// addNode(indexArray[index][0], enumvalue);
					// Logging.debug("Enumvalue index name : " +
					// enumvalue.getIndex_name());
					index3++;
					if (!bx) {
						// Logging.debug("Enumvalue index name1 : " +
						// enumvalue.getIndex_name());
						iii++;
						indexArray[iii][0] = enumvalue;
					}
					checkNodeforStock(indexArray, enumvalue);
					// //Logging.debug("Array size indexArray[" + iii +
					// "][1].size : " + ((ArrayList)
					// indexArray[iii][1]).size());

				} else {
					Object x, temp11;
					Object[][] z = null;

					int temp_int_var = 0;
					String temp_index_ids = ":::";
					String temp_parent_id = enumvalue.getParent_id();
					// //Logging.debug(temp_int_var +
					// " index node added in else -1: " +
					// enumvalue.getIndex_id() + " jnbj " + hm.get(new
					// Integer(temp_int_var)));
					// //Logging.debug("processedrecords.size() : " +
					// processedrecords.size());
					// //Logging.debug("temp_int_var >= processedrecords.size()"
					// + (temp_int_var <= processedrecords.size()));
					outer:
					// for (; temp_int_var <= processedrecords.size();
					// temp_int_var++) {
					for (; temp_int_var <= iii; temp_int_var++) {
						// JOptionPane.showMessageDialog(null,
						// temp_int_var + " <= " + processedrecords.size(),
						// "ERROR!",
						// JOptionPane.ERROR_MESSAGE);

						int ii = 0;

						// compare parent index with this id i.e. exactly where
						// it is present
						// //Logging.debug("index node added in else 2: " +
						// enumvalue.getIndex_id());

						// put child node at temp_int_var parent node
						// at ii depth
						// and with parents as temp1
						try {
							// //Logging.debug("index node added in else 4: " +
							// enumvalue.getIndex_id());
							// ////Logging.debug("indexArray[" + temp_int_var +
							// "][" + 0 + "] : " + indexArray[temp_int_var][0]);
							// ////Logging.debug("indexArray[" + temp_int_var +
							// "][" + 1 + "] : " + indexArray[temp_int_var][1]);
							// ////Logging.debug("indexArray[" + temp_int_var +
							// "][" + 2 + "] : " + indexArray[temp_int_var][2]);
							// addChildIndex(indexArray[temp_int_var],
							// enumvalue);
							// addChildIndex(temp11,temp_int_var, temp1, ii,
							// enumvalue);
							if (!htcopy.containsKey(enumvalue.index_id)) {
								checkNode(indexArray, enumvalue);
								/*
								 * try { Hashtable a1 = (Hashtable)
								 * indexArray[0][2]; Enumeration il = a1.keys();
								 * while (il.hasMoreElements()) { Object q =
								 * a1.get(il.nextElement()); Object[][] ol =
								 * (Object[][]) q; if (ol[0][0] != null) {
								 * //Logging.debug("ol[0][0] " + ol[0][0]); }
								 * else //Logging.debug("ol[0][0] " + null); if
								 * (ol[0][1] != null) {
								 * //Logging.debug("ol[0][1] " + null); } if
								 * (ol[0][2] != null) {
								 * //Logging.debug("ol[0][2] " + ((Hashtable)
								 * ol[0][2]).size()); } else {
								 * //Logging.debug("ol[0][2] " + null); } } }
								 * catch (Exception e) { e.printStackTrace(); }
								 */
								htcopy.put(enumvalue.index_id,
										enumvalue.index_id);
							}
							try {
								// Logging.debug("Array size indexArray[" + iii
								// + "][2].size : " + ((Hashtable)
								// indexArray[iii][2]).size());
							} catch (Exception e) {
								// e.printStackTrace();
								Logging.debug(e);
							}
							// addStock(indexArray, enumvalue,"y");
							checkNodeforStock(indexArray, enumvalue);
							/*
							 * Hashtable a1 = (Hashtable) indexArray[0][2];
							 * //Logging
							 * .debug("Total indexes inserted in nifty is " +
							 * al.size()); Enumeration il = a1.keys(); while
							 * (il.hasMoreElements()) { Object[][] ol =
							 * (Object[][]) a1.get(il.nextElement()); if
							 * (ol[0][0] != null) { //Logging.debug("ol[0][0] "
							 * + ol[0][0]); } if (ol[0][1] != null) {
							 * //Logging.debug("ol[0][1] " + ol[0][1]); } if
							 * (ol[0][2] != null) { //Logging.debug("ol[0][2] "
							 * + ol[0][2]); } }
							 */
							break outer;
						} catch (Exception e) {
							// e.printStackTrace();
							Logging.debug(e);
						}

					}

				}

			}
			// JOptionPane.showMessageDialog(null,
			// "Array completed ", "ERROR!",
			// JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			// e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.debug(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
	}
		public void constructTree(String query_name) {
		// getDbConnection("org.postgresql.Driver",
		// "jdbc:postgresql://192.168.0.11/income", "administrator", "");
		// HttpSession session;

		// String userid=this.useride;
		String usr_ID = getUseride();

		Hashtable ht = new Hashtable();
		Hashtable htcopy = new Hashtable();
		Hashtable Treeht = new Hashtable();
		ArrayList al = new ArrayList();
		Stack stack = new Stack();
		// int index;
		HashMap hm = new HashMap();
		HashMap hm1 = new HashMap();
		Connection connection = null;
		try {
			
			if (connection == null)
				connection = connect.getdbConnection();
			// String query =
			// "select index_name,im.index_id,im.parent_id,sm.stock_name,sm.stock_id from index_master1 im, index_composition ic, stock_master  sm where im.index_id=ic.index_id and ic.stock_id=sm.stock_id order by index_id";
			// String query =
			// "select im.index_id,index_name,parent_id,ic.stock_name,ic.stock_id ,is_captured ,it.index_type_code from index_type it,index_master im left outer join (select ic.index_id,stock_name,ic.stock_id   from index_composition ic, stock_master sm where ic.stock_id=sm.stock_id  and ic.index_id in (select index_id from index_master where is_active='y')) ic on (ic.index_id=im.index_id)  where it.index_type_id=im.index_type_id order by  im.index_id,parent_id";
			// String
			// query="select im.index_id,index_name,parent_id,ic.stock_name,ic.stock_id ,is_captured ,it.index_type_code  from index_type it,index_master im left outer join (select ic.index_id,identifier_code_value as stock_name,ic.stock_id from index_composition ic,stock_master sm,stock_exchange_master sem,stock_identifier_codes sic where ic.stock_id=sm.stock_id  and sm.stock_id=sic.stock_id and sm.stock_exchange_id=sem.stock_ex_id and sem.identifier_code_id=sic.identifier_code_id and ic.index_id in (select index_id from index_master where is_active='y')) ic on (ic.index_id=im.index_id) where it.index_type_id=im.index_type_id order by  im.index_id,parent_id";
			String query = ConnectInit.queries.getProperty(query_name);
			// Logging.debug("query : "+query);
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, usr_ID);// Change by Manoj Adekar for userwise
										// tree
			ResultSet rst = stmt.executeQuery();
			String index_name, index_id, parent_id, stock_name, stock_id, is_captured, index_type_code;
			int aa = 0;
			ht.clear();
			while (rst.next()) {
				index_name = rst.getString(2); // index_name
				index_id = rst.getString(1); // index_id
				parent_id = rst.getString(3); // parent_id
				stock_name = rst.getString(4); // stock_name
				stock_id = rst.getString(5); // stock_id
				is_captured = rst.getString(6); // is_captured
				index_type_code = rst.getString(7); // index_type_code
				// //Logging.debug("index_id " + index_id + "  : " +
				// index_name);
				if (!ht.containsKey(index_id + stock_id)) {
					al.add(index_id + stock_id);
					// aa++;
					ht.put(index_id + stock_id, new TreeNode(index_name,
							index_id, parent_id, stock_name, stock_id,
							is_captured, index_type_code));
					// //Logging.debug("Total records : " + aa + "ht size : " +
					// ht.size() + "  indexname :" + index_name + stock_name);

				}

				if (parent_id == null) {
					if (!Treeht.containsKey(index_id)) {
						// al.add(index_id + stock_id);
						aa++;
						Treeht.put(index_id, index_id);
						// Logging.debug("index_id + parent_id : " + index_name
						// + "  " + parent_id);

					}
				}
			}

			index4 = aa;
			indexArray = new Object[aa][3];
			Iterator it = al.iterator();
			String enumname;
			TreeNode enumvalue;
			// while (enum.hasMoreElements()) {
			int index = 0, index2 = 0;
			ArrayList processedrecords = new ArrayList();
			while (it.hasNext()) {
				done = false;
				done1 = false;
				enumname = (String) it.next();

				enumvalue = (TreeNode) ht.get(enumname);
				if (enumvalue.getIndex_name().equals(
						"FF1000CC.Sector.Financials")) {
					// Logging.debug("FF1000CC.Sector.Financials found  : ");
				}

				boolean bx = processedrecords.contains(enumvalue.getIndex_id())
						|| processedrecords.contains(enumvalue.getParent_id());
				// Logging.debug("processedrecords.contains(enumvalue.getIndex_id())  : "
				// + bx);
				if (!bx) {
					processedrecords.add(enumvalue.getIndex_id());
					// index++;
					// Logging.debug("present at : " +
					// processedrecords.indexOf(enumvalue.getIndex_id()));
				} // else {
					// index =
					// processedrecords.indexOf(enumvalue.getIndex_id());
					// if(index==-1)
					// index =
					// processedrecords.indexOf(enumvalue.getParent_id());
					// //Logging.debug("index2 : " + index2++);

				// }
				// //Logging.debug("index : " + index);
				// JOptionPane.showMessageDialog(null,
				// enumvalue.getIndex_id()+"c Array p"+enumvalue.getParent_id(),
				// "ERROR!",
				// JOptionPane.ERROR_MESSAGE);
				String s = ":::";
				if (enumvalue.getParent_id() == null) {
					// add node
					// addNode(indexArray[index][0], enumvalue);
					// Logging.debug("Enumvalue index name : " +
					// enumvalue.getIndex_name());
					index3++;
					if (!bx) {
						// Logging.debug("Enumvalue index name1 : " +
						// enumvalue.getIndex_name());
						iii++;
						indexArray[iii][0] = enumvalue;
					}
					checkNodeforStock(indexArray, enumvalue);
					// //Logging.debug("Array size indexArray[" + iii +
					// "][1].size : " + ((ArrayList)
					// indexArray[iii][1]).size());

				} else {
					Object x, temp11;
					Object[][] z = null;

					int temp_int_var = 0;
					String temp_index_ids = ":::";
					String temp_parent_id = enumvalue.getParent_id();
					// //Logging.debug(temp_int_var +
					// " index node added in else -1: " +
					// enumvalue.getIndex_id() + " jnbj " + hm.get(new
					// Integer(temp_int_var)));
					// //Logging.debug("processedrecords.size() : " +
					// processedrecords.size());
					// //Logging.debug("temp_int_var >= processedrecords.size()"
					// + (temp_int_var <= processedrecords.size()));
					outer:
					// for (; temp_int_var <= processedrecords.size();
					// temp_int_var++) {
					for (; temp_int_var <= iii; temp_int_var++) {
						// JOptionPane.showMessageDialog(null,
						// temp_int_var + " <= " + processedrecords.size(),
						// "ERROR!",
						// JOptionPane.ERROR_MESSAGE);

						int ii = 0;

						// compare parent index with this id i.e. exactly where
						// it is present
						// //Logging.debug("index node added in else 2: " +
						// enumvalue.getIndex_id());

						// put child node at temp_int_var parent node
						// at ii depth
						// and with parents as temp1
						try {
							// //Logging.debug("index node added in else 4: " +
							// enumvalue.getIndex_id());
							// ////Logging.debug("indexArray[" + temp_int_var +
							// "][" + 0 + "] : " + indexArray[temp_int_var][0]);
							// ////Logging.debug("indexArray[" + temp_int_var +
							// "][" + 1 + "] : " + indexArray[temp_int_var][1]);
							// ////Logging.debug("indexArray[" + temp_int_var +
							// "][" + 2 + "] : " + indexArray[temp_int_var][2]);
							// addChildIndex(indexArray[temp_int_var],
							// enumvalue);
							// addChildIndex(temp11,temp_int_var, temp1, ii,
							// enumvalue);
							if (!htcopy.containsKey(enumvalue.index_id)) {
								checkNode(indexArray, enumvalue);
								/*
								 * try { Hashtable a1 = (Hashtable)
								 * indexArray[0][2]; Enumeration il = a1.keys();
								 * while (il.hasMoreElements()) { Object q =
								 * a1.get(il.nextElement()); Object[][] ol =
								 * (Object[][]) q; if (ol[0][0] != null) {
								 * //Logging.debug("ol[0][0] " + ol[0][0]); }
								 * else //Logging.debug("ol[0][0] " + null); if
								 * (ol[0][1] != null) {
								 * //Logging.debug("ol[0][1] " + null); } if
								 * (ol[0][2] != null) {
								 * //Logging.debug("ol[0][2] " + ((Hashtable)
								 * ol[0][2]).size()); } else {
								 * //Logging.debug("ol[0][2] " + null); } } }
								 * catch (Exception e) { e.printStackTrace(); }
								 */
								htcopy.put(enumvalue.index_id,
										enumvalue.index_id);
							}
							try {
								// Logging.debug("Array size indexArray[" + iii
								// + "][2].size : " + ((Hashtable)
								// indexArray[iii][2]).size());
							} catch (Exception e) {
								// e.printStackTrace();
								Logging.debug(e);
							}
							// addStock(indexArray, enumvalue,"y");
							checkNodeforStock(indexArray, enumvalue);
							/*
							 * Hashtable a1 = (Hashtable) indexArray[0][2];
							 * //Logging
							 * .debug("Total indexes inserted in nifty is " +
							 * al.size()); Enumeration il = a1.keys(); while
							 * (il.hasMoreElements()) { Object[][] ol =
							 * (Object[][]) a1.get(il.nextElement()); if
							 * (ol[0][0] != null) { //Logging.debug("ol[0][0] "
							 * + ol[0][0]); } if (ol[0][1] != null) {
							 * //Logging.debug("ol[0][1] " + ol[0][1]); } if
							 * (ol[0][2] != null) { //Logging.debug("ol[0][2] "
							 * + ol[0][2]); } }
							 */
							break outer;
						} catch (Exception e) {
							// e.printStackTrace();
							Logging.debug(e);
						}

					}

				}

			}
			// JOptionPane.showMessageDialog(null,
			// "Array completed ", "ERROR!",
			// JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			// e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.debug(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
	}

	public void addNode(Object node, TreeNode value) {
		node = value;
	}

	public void addStock(Object[] StockNode, TreeNode value, String checknull) {
		ArrayList temp_arrayList;
		// Logging.debug("For index id " + value.getIndex_id() +
		// " In add stock : " + ((TreeNode) StockNode[0]).getIndex_name() + " "
		// + value.getIndex_id());
		// Logging.debug(" StockNode[1]  : " + StockNode[1]);
		try {
			temp_arrayList = null;
			if (StockNode[1] == null) {
				temp_arrayList = new ArrayList();
				// JOptionPane.showMessageDialog(null,
				// "adding " + value.getIndex_id(), "ERROR!",
				// JOptionPane.ERROR_MESSAGE);
				temp_arrayList.add(value);

			} else {
				// //Logging.debug("For index id in else  " +
				// value.getIndex_id() + " In add stock : " + StockNode[1]);
				temp_arrayList = (ArrayList) StockNode[1];
				// //Logging.debug("temp_arrayList.size() " +
				// temp_arrayList.size());
				temp_arrayList.add(value);
			}
			// Logging.debug("temp_arrayList.size() " + temp_arrayList.size());
			StockNode[1] = temp_arrayList;
			done1 = true;
		} catch (Exception e) {
			// e.printStackTrace();
			Logging.debug(e);
		}
	}

	public void addChildIndex(Object[] indexNode1, TreeNode value) {
		// Object indexNode=treeindexReference;
		// ArrayList temp_arrayList;
		Hashtable temp_arrayList;
		Object[] indexNode = (Object[]) indexNode1;

		// temp_arrayList=(ArrayList)indexNode[[2];
		// //Logging.debug(" addChildIndex 1  " + value.getIndex_id());
		try {
			temp_arrayList = null;
			if (indexNode[2] == null) {
				// //Logging.debug("addChildIndex 2" + value.getIndex_id());
				// temp_arrayList = new ArrayList();
				temp_arrayList = new Hashtable();
				Object[][] z = new Object[1][3];
				z[0][0] = value;
				z[0][1] = null;
				z[0][2] = null;
				Object tempChildIndex = z;
				try {
					Object[][] zz = (Object[][]) tempChildIndex;
					// Logging.debug("2 d array");
				} catch (Exception e) {
					// e.printStackTrace();
					Logging.debug(e);
				}
				// indexNode1[c][2]= z;
				temp_arrayList.put(new Integer(value.getIndex_id()),
						tempChildIndex);
				// //Logging.debug("addChildIndex 3" + value.getIndex_id());
			} else {

				temp_arrayList = (Hashtable) indexNode[2];
				Object[][] z = new Object[1][3];
				z[0][0] = value;
				z[0][1] = null;
				z[0][2] = null;
				Object tempChildIndex = z;
				try {
					temp_arrayList.put(new Integer(value.getIndex_id()),
							tempChildIndex);
				} catch (Exception e) {
					// //Logging.debug("in catch" + e.getMessage());
				}
				/*
				 * //Logging.debug("addChildIndex 4"+value.getIndex_id());
				 * Object[][] x = (Object[][]) indexNode[c][2]; int
				 * alreadypresent = 0; try{ while (x[alreadypresent][0] != null)
				 * { if(((String)x[alreadypresent][0]).equals(value)){
				 * JOptionPane.showMessageDialog(null,
				 * value.getIndex_id()+" already present ", "ERROR!",
				 * JOptionPane.ERROR_MESSAGE); // return; break; }
				 * alreadypresent++; } }catch(Exception e){
				 * 
				 * } Object[][] xx = new Object[alreadypresent + 1][3]; for (int
				 * a = 0; a < alreadypresent; a++) { xx[0][0] = x[a][0];
				 * xx[0][1] = x[a][1]; xx[0][2] = x[a][2]; } xx[0][0] = value;
				 * xx[0][1] = null; xx[0][2] = null; indexNode[c][2] = xx;
				 */
				// temp_arrayList.add(new
				// Integer(value.getIndex_id()).intValue(), indexNode);
			}
			// Logging.debug("addChildIndex 5" + value.getIndex_id());
			indexNode[2] = temp_arrayList;
			done = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void checkNode(Object[][] node, TreeNode value) {
		if (node[0][0] != null) {
			// Logging.debug("In checkNode : " + ((TreeNode)
			// node[0][0]).getIndex_name() + "  :  " + value.index_name);
		} else {
			// Logging.debug("In checkNode 1 : " + ((TreeNode)
			// node[0][0]).getParent_id() + "  :  " + value.index_name);
		}
		if (value.index_name.equals("FF1000CC.Sector.Financials")) {
			// Logging.debug("In checkNode 2 : " + ((TreeNode)
			// node[0][0]).getParent_id() + "  :  " + value.index_name);
		}
		Object[][] x = null;
		TreeNode temp2;
		int ab = 0;
		try {
			while (node[ab][0] != null) {
				ab++;
			}
			if (ab > 0) {
				ab--;
			}
		} catch (Exception e) {
			if (ab > 0) {
				ab--;
			}
		}
		// Logging.debug("node length in checkNode : " + ab);
		for (int i = 0; i <= ab; i++) {
			if (node[i][2] != null) {
				// Logging.debug("node[" + i + "][2] : " + ((Hashtable)
				// (node[i][2])).size());
			}
			// ArrayList temp = (ArrayList) node[i][2];
			Hashtable temp = (Hashtable) node[i][2];
			temp2 = (TreeNode) node[i][0];
			// //Logging.debug("node[" + i + "][2] : " + node[i][2]);
			// ////Logging.debug("Stock size : "+temp.size());
			if (done) {
				return;
			} else if (temp2 != null
					&& temp2.getIndex_id().equals(value.getParent_id())) { // do
																			// changes
																			// here
				// x[i]=node[i];
				// //Logging.debug("node[" + i + "]: " + node[i]);
				// x[i]=node[i];
				if (value.index_name.equals("FF1000CC.Sector.Financials")) {
					// Logging.debug("value.getParent_id() : " +
					// value.getParent_id());
					// Logging.debug("temp2.getIndex_id() : " +
					// temp2.getIndex_id() + "  : " + temp2.getIndex_name());

				}
				addChildIndex(node[i], value);
				done = true;
			} else if (temp == null) {
				// x[i]=node[i];
				// //Logging.debug("node[" + i + "]: " + node[i]);
				// x[i]=node[i];
				// Logging.debug(" Node index id : " + ((TreeNode)
				// node[i][0]).getIndex_id());
				// Logging.debug("value index id : " + value.getIndex_id());
				if (((TreeNode) node[i][0]).getIndex_id().equals(
						value.getParent_id())) {
					addChildIndex(node[i], value);
				} else {
					// return;
				}
				// addChildIndex(node[i], value);
				// done = true;
			} else if (temp.containsKey(value.getParent_id())) {
				x = (Object[][]) temp.get(new Integer(value.getParent_id()));
				addChildIndex(x[2], value);
				done = true;
				return;
			} else {
				// Iterator it = temp.iterator();
				Enumeration it = temp.keys();
				for (; it.hasMoreElements();) {
					Object[][] a = (Object[][]) temp.get(it.nextElement());
					// x = (Object[][]) a[2];
					checkNode(a, value);
				}
			}

		}
	}

	public void checkNodeforStock(Object[][] node, TreeNode value) {
		if (node[0][0] != null) {
			// Logging.debug("In checkNodeforStock : " + ((TreeNode)
			// node[0][0]).getIndex_name());
		} else {
			// Logging.debug("In checkNodeforStock 1 : " + ((TreeNode)
			// node[0][0]).getParent_id());
		}
		Object[][] x = null;
		Hashtable temp1;
		TreeNode temp2;
		int ab = 0;
		try {
			while (node[ab][0] != null) {
				ab++;
			}
			if (ab > 0) {
				ab--;
			}
		} catch (Exception e) {
			if (ab > 0) {
				ab--;
			}
			// e.printStackTrace();
		}
		// Logging.debug("node length in checkNodeforStock : " + ab);
		if (ab == 1) {
			// Logging.debug("Probably 2nd, row started");
		}
		for (int i = 0; i <= ab; i++) {
			temp1 = (Hashtable) node[i][2];
			temp2 = (TreeNode) node[i][0];
			if (done1) {
				return;
			} else if (temp2 != null
					&& temp2.getIndex_id().equals(value.getIndex_id())) {
				// x[i]=node[i];
				// //Logging.debug("node[" + i + "]: " + node[i]);
				// x[i]=node[i];

				addStock(node[i], value, null);
			} else if (temp1 == null) {
				// x[i]=node[i];
				// //Logging.debug("node[" + i + "]: " + node[i]);
				// x[i]=node[i];
				// Logging.debug(" Node index id : " + ((TreeNode)
				// node[i][0]).getIndex_id());
				// Logging.debug("value index id : " + value.getIndex_id());
				if (((TreeNode) node[i][0]).getIndex_id().equals(
						value.getIndex_id())) {
					addStock(node[i], value, null);
				} else {
					// return;
				}

			} else if (temp1.containsKey(value.getIndex_id())) {

				x = (Object[][]) temp1.get(new Integer(value.getIndex_id()));
				// addChildIndex(x[1],value);
				addStock(x, value, "y");
				// //Logging.debug("Stock size : " + temp1.size());
				done1 = true;
				return;
			} else {
				Enumeration it = temp1.keys();
				for (; it.hasMoreElements();) {
					// //Logging.debug("temp1.size() : " + temp1.size());
					Object b = temp1.get(it.nextElement());
					// //Logging.debug("b.toString() : " + b.toString());
					Object[][] a = (Object[][]) b;
					// x = (Object[][]) a[0][1];
					checkNodeforStock(a, value);
				}
			}

		}

	}

	public void putNode(Object[][] node, OutputStreamWriter out,
			String arrayString, int count) {
		try {
			Object[][] x = null;
			int icount = 0;
			for (int i = 0; i <= count; i++) {
				if (node[i][0] != null) {
					icount = i;
					String temp = arrayString + "[" + i + "]";
					// //Logging.debug("temp : " + temp);
					out.write(temp + "         = new Array;\r\n");
					out.write(temp + "['caption']         = \""
							+ ((TreeNode) (node[i][0])).getIndex_name()
							+ "\";\r\n");
					out.write(temp + "['url']         = \"yahoo sports\"\r\n");
					ArrayList temp_arr_list = (ArrayList) node[i][2];
					// //Logging.debug("node["+i+"][2] : "+node[i][2]);
					if (node[i][2] != null) {
						Iterator it = temp_arr_list.iterator();
						int tempcount = temp_arr_list.size();
						for (; it.hasNext();) {
							Object[][] xx = (Object[][]) it.next();
							putNode(xx, out, temp + "['children']", tempcount);

						}
					}

				}
			}
			String temp = arrayString + "['children'][" + icount + "]";
			ArrayList temp_arr_list1 = (ArrayList) node[icount][1];
			Iterator itt = temp_arr_list1.iterator();
			int tempcount1 = temp_arr_list1.size();
			for (; itt.hasNext();) {
				TreeNode temp_treenode = (TreeNode) itt.next();
				out.write(temp + "         = new Array;\r\n");
				out.write(temp + "['caption']         = \""
						+ temp_treenode.getIndex_name() + "\";\r\n");
				out.write(temp + "['url']         = \"yahoo sports\"\r\n");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			Logging.debug(e);
		}
	}

	public void drawStockNodex(OutputStreamWriter out) {
		try {
			out.write("a[0]['children'][1] = new Array;\r\n");
			out.write("a[0]['children'][1]['caption']          = \"Stocks\";\r\n");
			out.write("a[0]['children'][1]['url'] =\"http://www.yahoo.com/\";\r\n");
			out.write("a[0]['children'][1]['target'] =\"_blank\";\r\n");
			out.write("a[0]['children'][1]['children']         = new Array;\r\n");
			int count = 0, count1 = 0;
			String temp = "a[0]['children'][1]['children']";
			String query = "select sic.identifier_code_value,sem.stock_ex_code,sic.stock_id from  stock_master sm, stock_identifier_codes sic,  identifier_codes ic,  stock_exchange_master sem where sm.is_active='y' and sm.stock_id=sic.stock_id and  sem.identifier_code_id=ic.identifier_code_id  and ic.identifier_code_id=sic.identifier_code_id group by sem.stock_ex_code,sic.identifier_code_value,sic.stock_id order by upper(sem.stock_ex_code),upper(sic.identifier_code_value)";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			Hashtable htForStocks = new Hashtable();
			String id_code, stock_ex_code, stock_id;
			ArrayList arrayList;
			while (rst.next()) {
				id_code = rst.getString("identifier_code_value");
				stock_ex_code = rst.getString("stock_ex_code");
				stock_id = rst.getString("stock_id");
				if (!htForStocks.containsKey(stock_ex_code)) {
					arrayList = new ArrayList();
					arrayList.add(new StockNodes(id_code, stock_ex_code,
							stock_id));
					htForStocks.put(stock_ex_code, arrayList);
				} else {
					arrayList = (ArrayList) htForStocks.get(stock_ex_code);
					arrayList.add(new StockNodes(id_code, stock_ex_code,
							stock_id));
					htForStocks.put(stock_ex_code, arrayList);
				}
				// htForStocks.put(stock_ex_code,new
				// StockNodes(id_code,stock_ex_code,stock_id));
			}
			// Logging.debug("Total Number of exchanges : "+htForStocks.size());
			Enumeration enumm = htForStocks.keys();
			String temp1, temp2;
			while (enumm.hasMoreElements()) {
				String idcode = (String) enumm.nextElement();
				ArrayList arrayListForDisplay = (ArrayList) htForStocks
						.get(idcode);
				temp1 = temp + "[" + count + "]";
				out.write(temp1 + "         = new Array;\r\n");
				out.write(temp1 + "['caption']         = \"" + idcode
						+ "\";\r\n");
				out.write(temp1 + "['url']         = \"yahoo sports\"\r\n");
				out.write(temp1 + "['children']         = new Array;\r\n");
				count++;
				if (arrayListForDisplay != null
						&& arrayListForDisplay.size() != 0) {
					Iterator it = arrayListForDisplay.iterator();
					count1 = 0;
					StockNodes stockname;
					while (it.hasNext()) {
						temp2 = temp1 + "['children'][" + count1 + "]";
						stockname = (StockNodes) it.next();
						out.write(temp2 + "         = new Array;\r\n");
						out.write(temp2 + "['caption']         = \""
								+ stockname.getId_code() + "\";\r\n");
						out.write(temp2
								+ "['url']         = \"yahoo sports\"\r\n");
						count1++;
					}
				} else {
					temp2 = temp1 + "[" + 0 + "]";
					out.write(temp2 + "         = new Array;\r\n");
					out.write(temp2
							+ "['caption']         = \" + No Stock Available + \";\r\n");

				}
			}

		} catch (Exception e) {

		}

	}

	public StringBuffer drawTreeIndex(Object[][] node) {
		try {
			boolean b = false, b1 = false;
			// //Logging.debug("node[0][0] : " + ((TreeNode)
			// node[0][0]).getIndex_name());
			int i = 0;
			int ab = 0;
			int z = 0;
			String href = "";
			try {
				while (node[ab][0] != null) {
					ab++;
				}
				if (ab > 0) {
					ab--;
				}
			} catch (Exception e) {
				if (ab > 0) {

					ab--;
				}
			}
			// temp_var=" <UL><LI>";
			// stringBuffer.append(temp_var);
			b1 = false;
			for (int iiii = 0; iiii <= ab; iiii++) {

				if (iiii == 0) {
					// //Logging.debug("hi");
					// temp_var=" <UL id=foldinglist style=\"DISPLAY: none; head: \">";
					// stringBuffer.append(temp_var);
				}
				TreeNode node_temp = (TreeNode) (node[iiii][0]);
				href = "<a href=\"/Stockpile/pages/reports/IndexComposeS.jsp?index="
						+ node_temp.getIndex_id()
						+ "&compute=yes&ajax1=yes\" Target=\"frmMain\">";// node_temp.getIndex_id();
				if (node_temp.getIs_captured().trim().equalsIgnoreCase("y")) {
					temp_var = " <LI id=foldheader> <P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial color=\"#008000\" size=1><i>"
							+ href
							+ (node_temp).getIndex_name()
							+ "("
							+ node_temp.getIndex_type_code()
							+ ")(C)</i></FONT></a>";
				} else {
					temp_var = " <LI id=foldheader> <P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial color=blue size=1>"
							+ href
							+ (node_temp).getIndex_name()
							+ "("
							+ node_temp.getIndex_type_code() + ")</FONT></a>";

				}
				stringBuffer.append(temp_var);

				if (node[iiii][2] != null) {

					Hashtable indexes = (Hashtable) node[iiii][2];
					Enumeration e = indexes.keys();
					int abc = 0;
					int aa = 0;
					if (e.hasMoreElements()) {
						temp_var = " <UL id=foldinglist style=\"DISPLAY: none; head: \">";
						stringBuffer.append(temp_var);
						b1 = true;
					}
					if (e.hasMoreElements()) {
						b = true;
					}
					for (; e.hasMoreElements();) {

						Object[][] temp_index_record = (Object[][]) indexes
								.get(e.nextElement());

						drawTreeIndex(temp_index_record);
						// putStockNodes(temp_index_record, temp1, out, aa - 1,
						// null);
					}

				} else {
					b1 = false;
				}

				if (node[iiii][1] != null) {
					ArrayList al = (ArrayList) node[iiii][1];
					Iterator iterator = al.iterator();
					if (iterator.hasNext()) {
						// if(!b1){
						// temp_var=" <UL id=foldinglist style=\"DISPLAY: none; head: \">";
						// stringBuffer.append(temp_var);
						// }
						if (!b) {
							temp_var = "<UL id=foldinglist style=\"DISPLAY: none; head: \">";
							stringBuffer.append(temp_var);
							b1 = true;
						}
					}
					for (; iterator.hasNext();) {
						if (!b1) {
							temp_var = "<UL id=foldinglist style=\"DISPLAY: none; head: \">";
							stringBuffer.append(temp_var);
							b1 = true;
						}
						TreeNode treenode = (TreeNode) iterator.next();
						Logging.info("Tree class --> drawTreeIndex --> treenode*** "+treenode);
						if (treenode.getStock_name() != null) {
							Logging.info("Tree class --> drawTreeIndex --> inside if block*** ");
							Logging.info("Tree class --> drawTreeIndex --> Stock_name*** " +treenode.getStock_name());
							Logging.info("Tree class --> drawTreeIndex --> treenode stock ID*** "+treenode.getStock_id());
							String stock_detail = "/Stockpile/pages/masters/stockmaster2.jsp?s_stockid="
									+ treenode.getStock_id();
							href = "<a href=\""
									+ stock_detail
									+ "\" Target=\"frmMain\" onmouseover=\"window.status='go!!';return true\">";// node_temp.getIndex_id();

							temp_var = "<LI><P style=\"MARGIN-LEFT: -75px\" align=left><FONT face=Arial  color=blue size=1>"
									+ href
									+ treenode.getStock_name()
									+ "</FONT></a></LI>";
						} else {
							temp_var = "<LI><P style=\"MARGIN-LEFT: -75px\" align=left><FONT face=Arial  color=blue size=1>No Stocks To Display</FONT></LI>";
						}
						stringBuffer.append(temp_var);
					}
					temp_var = "</LI></UL>";
					stringBuffer.append(temp_var);
				} else {
					temp_var = "<LI><FONT face=Arial  color=blue size=1> No Stock Available </FONT></LI></UL>";
					stringBuffer.append(temp_var);
					temp_var = "</LI></UL>";
					stringBuffer.append(temp_var);
				}
				// temp_var="</LI></UL>";
				// stringBuffer.append(temp_var);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return stringBuffer;
	}

	public void drawTreeNodes(Object[][] node, String temp,
			OutputStreamWriter out, int mm, String caller) {
		try {
			// Logging.debug("node[0][0] : " + ((TreeNode)
			// node[0][0]).getIndex_name());
			int i = 0;
			int ab = 0;
			int z = 0;
			try {
				while (node[ab][0] != null) {
					ab++;
				}
				if (ab > 0) {
					ab--;
				}
			} catch (Exception e) {
				if (ab > 0) {

					ab--;
				}
			}
			for (int iiii = 0; iiii <= ab; iiii++) {
				if (node[iiii][0] != null) {
					// out.write(temp + "         = new Array;\r\n");

					if (temp.trim().equalsIgnoreCase(
							"a[0]['children'][0]['children']")) {
						mm = iiii;
					}
					TreeNode x1 = ((TreeNode) node[0][0]);
					if (x1.getParent_id() != null) {
						if (!stockdetails.containsKey(x1.getParent_id())) {
							stockdetails.put(x1.getParent_id(), new Integer(1));
						} else {
							int x = ((Integer) stockdetails.get(x1
									.getParent_id())).intValue();
							// stockdetails.remove(x1.getIndex_id());
							stockdetails.put(x1.getParent_id(),
									new Integer(++x));

						}
					}
					if (!stockdetails.containsKey(x1.getIndex_id())) {
						stockdetails.put(x1.getIndex_id(), new Integer(1));
					} else {
						int x = ((Integer) stockdetails.get(x1.getIndex_id()))
								.intValue();
						// stockdetails.remove(x1.getIndex_id());
						stockdetails.put(x1.getIndex_id(), new Integer(++x));
						// Logging.debug(" x1.getIndex_id() : " +
						// x1.getIndex_id());
						// Logging.debug(" stockdetails.get(x1.getIndex_id()) : "
						// + ((Integer)
						// stockdetails.get(x1.getIndex_id())).intValue());
					}
					if (x1.getParent_id() == null) {

						mm = iiii;
					}
					z = mm;
					// Logging.debug("node[" + mm + "][0] : " + ((TreeNode)
					// node[iiii][0]).getIndex_name());
					temp_var = "<LI id=foldheader> <P style=\"MARGIN-LEFT: -60px\" align=left><FONT face=Arial color=blue size=1>"
							+ ((TreeNode) (node[iiii][0])).getIndex_name()
							+ "</FONT> <UL id=foldinglist style=\"DISPLAY: none; head: \">";
					stringBuffer.append(temp_var);
					out.write(temp + "[" + mm + "]         = new Array;\r\n");
					out.write(temp + "[" + mm + "]['caption']         = \""
							+ ((TreeNode) (node[iiii][0])).getIndex_name()
							+ "\";\r\n");
					out.write(temp + "[" + mm
							+ "]['url']         = \"yahoo sports\";\r\n");
					try {
						// z=getInt(temp);
						// System.out.print(" int is : " + z);
						stockdetails.put(x1.getIndex_id(), new Integer(++z));

					} catch (Exception ex) {

					}
					// insert code for
					// /

					// /

					try {
						if (node[iiii][2] != null) {
							Hashtable indexes = (Hashtable) node[iiii][2];
							Enumeration e = indexes.keys();
							int abc = 0;
							int aa = 0;
							for (; e.hasMoreElements();) {
								if (temp.trim().equalsIgnoreCase(
										"a[0]['children'][0]['children']")) {
									abc = iiii;
								}
								// z=abc;

								String temp1 = temp + "[" + abc
										+ "]['children']";
								// Logging.debug(" temp : " + temp);
								// Logging.debug(" temp1 : " + temp1);

								Object[][] temp_index_record = (Object[][]) indexes
										.get(e.nextElement());
								TreeNode x = ((TreeNode) temp_index_record[0][0]);
								if (aa == 0) {
									out.write(temp1
											+ "         = new Array;\r\n");
								}
								aa++;
								newLayer.put(x.index_id, new Integer("1"));
								drawTreeNodes(temp_index_record, temp1, out,
										aa - 1, null);
								// putStockNodes(temp_index_record, temp1, out,
								// aa - 1, null);
							}

						}
					} catch (Exception e) {
						// e.printStackTrace();
						Logging.debug(e);
					}

				}
				try {
					int zzz = 0;
					// z = mm + iiii;
					String tempStock = null;
					ArrayList al = (ArrayList) node[iiii][1];
					TreeNode x1 = (TreeNode) node[iiii][0];
					if (node[iiii][0] != null) {
						try {
							Hashtable ht = (Hashtable) node[iiii][2];
							zzz = ht.size();
						} catch (Exception e) {

						}
					}

					if (x1.getIndex_name().trim().equalsIgnoreCase("feb0226")) {
						// JOptionPane.showMessageDialog(null,
						// "mm=" + mm, "ERROR!",
						// JOptionPane.ERROR_MESSAGE);
					}
					if (zzz > 0)
						tempStock = temp + "[" + (z - 1) + "]['children']";
					else {
						tempStock = temp + "[" + (z - 1) + "]['children']";
					}
					// Logging.debug(" x1.getIndex_id() : " + x1.getIndex_id());

					// if(!stockdetails.containsKey(x1.getIndex_id())){
					if (zzz == 0) {
						out.write(tempStock + "         = new Array;\r\n");
						stockdetails.put(x1.getIndex_id(), new Integer(1));
					} else {
						// zzz=((Integer)stockdetails.get(x1.getIndex_id())).intValue();
						// //Logging.debug(" stockdetails.get(x1.getIndex_id()) : "
						// + ((Integer)
						// stockdetails.get(x1.getIndex_id())).intValue());
						if (zzz == 1) {
							out.write(tempStock + "         = new Array;\r\n");
						}
						if (x1.getIndex_name().trim().equalsIgnoreCase("nifty")) {
							// JOptionPane.showMessageDialog(null,
							// "z=" + z, "ERROR!",
							// JOptionPane.ERROR_MESSAGE);
						}
					}

					if (al != null && al.size() != 0) {
						Iterator it = al.iterator();
						int noofRecord = 0;
						if (zzz > 1) {
							noofRecord = zzz - 1;
						}
						// noofRecord=zzz;
						for (; it.hasNext();) {
							TreeNode treenode = (TreeNode) it.next();
							if (treenode.getStock_name() != null) {
								out.write(tempStock + "[" + noofRecord
										+ "]         = new Array;\r\n");
								if ((treenode.getStock_name() != null)) {
									out.write(tempStock + "[" + noofRecord
											+ "]['caption']          = \""
											+ treenode.getStock_name()
											+ "\";\r\n");
								} else {
									out.write(tempStock
											+ "["
											+ noofRecord
											+ "]['caption']          = \" + No Stock Available + \";\r\n");

								}

								out.write(tempStock + "[" + noofRecord
										+ "]['url']         = \"me.com\";\r\n");
								noofRecord++;
							}
						}
						if (noofRecord == 0) {
							out.write(tempStock + "[" + 0
									+ "]         = new Array;\r\n");
							out.write(tempStock
									+ "["
									+ 0
									+ "]['caption']          =  \"No Composition available\";\r\n");
						}
					} else {
						out.write(tempStock + "[" + 0
								+ "]         = new Array;\r\n");
						out.write(tempStock
								+ "["
								+ 0
								+ "]['caption']          =  \"No Composition available\";\r\n");
						// out.write(temp +
						// "["+0+"]['url']         = \"me.com\";\r\n");
					}
				} catch (Exception ee) {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getInt(String temp) {
		int i = -999;
		String temp1 = new String(temp);
		try {

			temp1 = temp1.substring(0,
					temp.lastIndexOf(new String("['children']").trim()));
			temp1 = temp1.substring(
					temp1.lastIndexOf(new String("['children']").trim())
							+ new String("['children']").length() + 1,
					temp1.length() - 1);
			// Logging.debug("temp1 : " + temp1);
			i = Integer.parseInt(temp1);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	public void putStockNodes(Object[][] node, String temp,
			OutputStreamWriter out, int mm, String caller) {
		try {
			// Logging.debug(" name :" + ((TreeNode)
			// node[0][0]).getIndex_name());
			if (node[0][1] != null) {
				// Logging.debug("Array size name :" + ((ArrayList)
				// node[0][1]).size());
			}

			ArrayList al = null;
			temp = temp + "[0]['children']";
			out.write(temp + "         = new Array;\r\n");
			try {
				al = (ArrayList) node[0][1];

			} catch (Exception e) {
				// e.printStackTrace();
				Logging.debug(e);
			}
			if (al != null && al.size() != 0) {
				Iterator it = al.iterator();
				int noofRecord = 0;
				for (; it.hasNext();) {
					TreeNode treenode = (TreeNode) it.next();
					out.write(temp + "[" + noofRecord
							+ "]         = new Array;\r\n");
					out.write(temp + "[" + noofRecord
							+ "]['caption']          = \""
							+ treenode.getIndex_name() + "\";\r\n");
					out.write(temp + "[" + noofRecord
							+ "]['url']         = \"me.com\";\r\n");
				}
			} else {
				out.write(temp + "[" + 0 + "]         = new Array;\r\n");
				out.write(temp
						+ "["
						+ 0
						+ "]['caption']          =  \"No Composition available\";\r\n");
				// out.write(temp + "["+0+"]['url']         = \"me.com\";\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://sudhir:5432/income";
			con = DriverManager.getConnection(url, "administrator", "");
		} catch (Exception e) {
			// e.printStackTrace();
			Logging.debug(e);
		}
	}

	public void returnUserid(String usrID) {
		// this.useride=usrID;
		setUseride(usrID);
		// return useride;
	}

	public static String getUseride() {
		return useride;
	}

	public static void setUseride(String useride) {
		Tree.useride = useride;
	}

}
