/*
 * Created on Mar 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tree;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Connection;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Vivek
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ExternalTree implements Externalizable {
	Logger logger = null;
	Thread t;
	Connect c = ConnectInit.getConnect();
	com.tree.Tree tree;
	StringBuffer treeStringBuffer, ClassificationStringBuffer;
	public Hashtable ht = new Hashtable();

	

	public ExternalTree() {

		try {

			ht.clear();
			logger = Logger.getLogger(ExternalTree.class);
			PropertyConfigurator.configure(c.resourceurl
					+ "resources/l4j3.properties");
			logger.debug("ReCreating Tree");
			tree = new com.tree.Tree();
			// added con parameter
			tree.constructTree("tree_index_query_online");
			Object[][] node = tree.getIndexArray();
			treeStringBuffer = tree.drawTreeIndex(node);
			// System.out.println("Tree buffer is : \n"+treeStringBuffer);
			String string = new String(treeStringBuffer);
			ht.put("index", string);
			/*
			 * tree.constructIndustryClassificationTree("tree_cap_query");
			 * tree.clearSBClassification();
			 * tree.drawIndustryClassificationTree();
			 * ClassificationStringBuffer=tree.getSBClassification();
			 * ht.put("classify",ClassificationStringBuffer);
			 */
			logger.debug("Tree ReCreated successfully");
		} catch (Exception e) {
			// e.printStackTrace();
			logger.debug(e);
		}

	}

	public com.tree.Tree getTree() {
		return tree;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		if (ht != null && ht.size() > 0)
			out.writeObject(ht);
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		Hashtable httable = (Hashtable) in.readObject();
	}

}
