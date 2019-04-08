package com.tree;

/**
 * Created by IntelliJ IDEA.
 * User: vivek
 * Date: Feb 21, 2005
 * Time: 5:00:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockNodes {
      String id_code,stock_ex_code,stock_id;
     public StockNodes( String id_code,String stock_ex_code,String stock_id){
        this.id_code=id_code;
        this.stock_ex_code=stock_ex_code;
        this.stock_id=stock_id;       
    }


    /**
     * @return Returns the id_code.
     */
    public String getId_code() {
        return id_code;
    }
    /**
     * @param id_code The id_code to set.
     */
    public void setId_code(String id_code) {
        this.id_code = id_code;
    }
    /**
     * @return Returns the stock_ex_code.
     */
    public String getStock_ex_code() {
        return stock_ex_code;
    }
    /**
     * @param stock_ex_code The stock_ex_code to set.
     */
    public void setStock_ex_code(String stock_ex_code) {
        this.stock_ex_code = stock_ex_code;
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

}
