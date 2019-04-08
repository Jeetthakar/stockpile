package com.tree;

/**
 * Created by IntelliJ IDEA.
 * User: vivek
 * Date: Feb 12, 2005
 * Time: 7:13:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeNode {
    String index_name,index_id,parent_id,stock_name,stock_id,is_captured,index_type_code;

    public TreeNode( String index_name,String index_id,String parent_id,String stock_name,String stock_id,String is_captured,String index_type_code){
        this.index_name=index_name;
        this.index_id=index_id;
        this.parent_id=parent_id;
        this.stock_name=stock_name;
        this.stock_id=stock_id;
        this.is_captured=is_captured;
        this.index_type_code=index_type_code;
    }

    /**
     * @return Returns the index_id.
     */
    public String getIndex_id() {
        return index_id;
    }
    /**
     * @param index_id The index_id to set.
     */
    public void setIndex_id(String index_id) {
        this.index_id = index_id;
    }
    /**
     * @return Returns the index_name.
     */
    public String getIndex_name() {
        return index_name;
    }
    /**
     * @param index_name The index_name to set.
     */
    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }
    /**
     * @return Returns the parent_id.
     */
    public String getParent_id() {
        return parent_id;
    }
    /**
     * @param parent_id The parent_id to set.
     */
    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    /**
     * @return Returns the stock_id.
     */
    public String getStock_id() {
        return stock_id;
    }
    /**
     * @param stock_id The stock_id to set.
     */
    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }
    /**
     * @return Returns the stock_name.
     */
    public String getStock_name() {
        return stock_name;
    }
    /**
     * @param stock_name The stock_name to set.
     */
    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }
    /**
     * @return Returns the index_type_code.
     */
    public String getIndex_type_code() {
        return index_type_code;
    }
    /**
     * @param index_type_code The index_type_code to set.
     */
    public void setIndex_type_code(String index_type_code) {
        this.index_type_code = index_type_code;
    }
    /**
     * @return Returns the is_captured.
     */
    public String getIs_captured() {
        return is_captured;
    }
    /**
     * @param is_captured The is_captured to set.
     */
    public void setIs_captured(String is_captured) {
        this.is_captured = is_captured;
    }
}