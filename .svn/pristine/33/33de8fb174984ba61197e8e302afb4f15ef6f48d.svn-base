<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*,java.sql.*" %>
<%@ include file="Header.jsp" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
// Logger log = Logger.getLogger(this.getClass());
// log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html:form action="/stockMasterAction">
        <table border="1" width="100%" cellspacing="0" cellpadding="0">
          <tr>
            <td width="64%">
              <table border="0" width="97%" cellspacing="0" cellpadding="2">
                <tr>
                  <td width="25%"><b><font face="Arial" size="2">Stock Details</font></b></td>
                  <td width="25%"></td>
                  <td width="25%"></td>
                  <td width="25%"></td>
                </tr>
                <tr>
                  <td width="25%"><font size="1" face="Arial">&nbsp;Stock Name</font></td>
                  <td width="25%"><html:text property="s_stockName" size="9"/></td>
                  <td width="25%"><font size="1" face="Arial">Listing Date</font></td>
                  <td width="25%"><input readOnly name="d_listingDate" defualt size="9"/><input onclick="c2.popup('d_listingDate');" tabIndex="6" type="button" value="..."/>
                </tr>
                <tr>
                  <td width="25%"><font size="1" face="Arial">&nbsp;Stock Type</font></td>
                  <td width="25%"><html:select size="1" property="s_stockType">
                    <html:option value="India">India</html:option>
                    </html:select></td>
                  <td width="25%"><font size="1" face="Arial">IWF of Stock</font></td>
                  <td width="25%"><html:text property="d_iwf" size="9"/></td>
                </tr>
                <tr>
                  <td width="25%"><font size="1" face="Arial">&nbsp;Company</font></td>
                  <td width="25%"><html:text property="s_companyName" size="9"/></td>
                  <td width="25%"><font size="1" face="Arial">No. of Issued
                    Shares</font></td>
                  <td width="25%"><html:text property="f_issuedShares" size="9"/></td>
                </tr>
                <tr>
                  <td width="25%"><font size="1" face="Arial">&nbsp;Stock Exchange</font></td>
                  <td width="25%"><html:text property="s_stockExchange" size="9"/></td>
                  <td width="25%"><font size="1" face="Arial">Country</font></td>
                  <td width="25%"><html:select size="1" property="s_countryName">
                      <html:option value="India">India</html:option>
                    </html:select></td>
                </tr>
                <tr>
                  <td width="25%"><font size="1" face="Arial">&nbsp;Face Value</font></td>
                  <td width="25%"><html:text property="f_faceValue" size="9"/></td>
                  <td width="25%"><font size="1" face="Arial">Rating Code</font></td>
                  <td width="25%"><html:text property="s_ratingCode" size="9"/></td>
                </tr>
                <tr>
                  <td width="25%"><font size="1" face="Arial">&nbsp;Paid Value</font></td>
                  <td width="25%"><html:text property="d_paidValue" size="9"/></td>
                  <td width="25%"><font size="1" face="Arial">ADR Ratio</font></td>
                  <td width="25%"><html:text property="s_adrRatio" size="9"/></td>
                </tr>
                <tr>
                  <td width="25%"><font face="Arial" size="1">&nbsp;Global 100 ?</font></td>
                  <td width="25%"><html:select size="1" property="b_global100">
                      <html:option value="y">Yes</html:option>
                      <html:option value="n">No</html:option>
                    </html:select></td>
                  <td width="25%"><font face="Arial" size="1">Market Lot</font></td>
                  <td width="25%"><html:text property="s_marketLot" size="9"/></td>
                </tr>
                <tr>
                  <td width="25%"><font face="Arial" size="1">&nbsp;Alert 
                  Percentage</font></td>
                  <td width="25%"><html:text property="f_alertPercent" size="9"/></td>
                  <td width="25%"><font face="Arial" size="1">Rejection 
                  Percentage</font></td>
                  <td width="25%"><html:text property="f_rejectionPercent" size="9"/></td>
                </tr>
                <tr>
                  <td width="25%"><font face="Arial" size="1">&nbsp;Withholding 
                  Tax Applicable ?</font></td>
                  <td width="25%"><html:select size="1" property="b_withHoldingTaxApplicable">
                      <html:option value="y">Yes</html:option>
                      <html:option value="n">No</html:option>
                    </html:select></td>
                  <td width="25%"><font face="Arial" size="1">Withholding Tax 
                  Percent</font></td>
                  <td width="25%"><html:text property="f_withholdingTaxPercent" size="9"/></td>
                </tr>
                <tr>
                  <td width="25%"><font face="Arial" size="1">&nbsp;Stock 
                  Currency</font></td>
                  <td width="25%"><html:select size="1" property="s_stockCurrency">
                      <html:option value="val">Yes</html:option>
                      <html:option value="al1">No</html:option>
                    </html:select></td>
                  <td width="25%"><font face="Arial" size="1">Is Price for Lot ?</font></td>
                  <td width="25%"><html:select size="1" property="b_isPriceForLot">
                      <html:option value="y">Yes</html:option>
                      <html:option value="n">No</html:option>
                    </html:select></td>
                </tr>
             <!-- </table>-->
            </td>
          </tr>
        </table>
        <table border="0" width="101%" cellspacing="0" cellpadding="4">
          <tr>
            <td width="49%" valign="top">
              <table border="1" width="69%" bordercolorlight="#808080" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="100%"><font size="1" face="Arial"><html:radio value="g" property="s_growthValueType"/>
                    Growth Stocks&nbsp;&nbsp; <html:radio value="v" property="s_growthValueType"/>
                    Value Stock</font></td>
                </tr>
              </table>
            </td>
            <td width="97%" valign="top">
              <p align="left">
              <font color="#FF0000" size="2" face="Arial"><b>ACTIVE ?</b></font> <html:checkbox property="b_isActive" value="y"/></td>
          </tr>
          <tr>
            <td width="146%" valign="top" colspan="2">
              <table border="0" width="100%" bordercolorlight="#808080" cellspacing="0" cellpadding="0" bordercolordark="#808080" style="border-collapse: collapse" bordercolor="#111111">
                <tr>
                  <td width="100%"><b><font size="2" face="Arial">&nbsp;CODES</font></b>
                    <table border="0" width="100%" cellspacing="0" cellpadding="2" height="22">
                      <tr>
                        <td width="100%" align="center" height="18">
                        <div align="left">
                          <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="37%" id="AutoNumber1">
                            <tr>
                              <td width="12%"> <html:checkbox property="C1" value="ON"/></td>
                              <td width="54%"><font size="1" face="Arial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                              Identifier Code&nbsp; </font></td>
                              <td width="34%"><html:text property="T1" size="9"/></td>
                            </tr>
                          </table>
                        </div>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td width="92%" valign="top" colspan="2">
              <p align="center">&nbsp;</p>
            </td>
            <td width="9%" valign="top">&nbsp;</td>
          </tr>
          <tr>
            <td width="100%" valign="top" colspan="3">
              <p align="center"><input type="button" value="View Indices" name="B3">&nbsp;&nbsp;&nbsp;
              <html:submit value="Submit" property="B1"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <html:reset value="New" property="B2"/>&nbsp;&nbsp; &nbsp; <html:reset value="Reset" property="B3"/>&nbsp;&nbsp;&nbsp;
<!--          <input type="button" value="Import From file..." name="B3"/></td>-->
          </tr>
        </table>
        </table>
      </td>
          </tr>

        </table>
      </td>

    </tr>

</tbody>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="0">
  <tr>
    <td width="28%" valign="top">
      <p></p>
    </td>
    <td width="72%" valign="top">
      <p>
    </td>
  </tr>
</table>
</html:form>       
</body>
</html>