/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.30
 * Generated at: 2021-01-29 06:43:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.sch.pkg.*;
import java.util.*;
import java.lang.*;
import com.jcraft.jsch.*;
import org.apache.poi.*;
import java.sql.*;
import com.Ostermiller.util.*;

public final class ExistingViolations_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.lang");
    _jspx_imports_packages.add("com.Ostermiller.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("com.sch.pkg");
    _jspx_imports_packages.add("org.apache.poi");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("com.jcraft.jsch");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Schedular- QA</title>\r\n");
      out.write("<link rel =\"stylesheet\" href=\"CSS/indexStyle.css\">\r\n");
      out.write("<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(window).load(function() {\r\n");
      out.write("\t$(\".loader\").fadeOut(\"slow\");\r\n");
      out.write("\t\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write(" ");
String filename =uploadFile.getFileName();
 String xfer =uploadFile.getxferid(filename);
 String viol_tx_status =uploadFile.getViol_tx_status(xfer);
 if(viol_tx_status.equals("10"))
 {
 String level = uploadFile.getcitationlevel(xfer); 
 if(level.contains("COL"))
 {
 viol_tx_status = "NTC";	 
 }
 else if(level.contains("A"))
 {	 
 viol_tx_status = "NTA";
 }
 else if(level.contains("B"))
 {	 
 viol_tx_status = "NTB";
 }
 
 }
 
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"loader\"></div>\r\n");
      out.write("<div id=\"left\"></div>\r\n");
      out.write("<div id=\"right\"></div>\r\n");
      out.write("<div id=\"top\"></div>\r\n");
      out.write("<div id=\"bottom\"></div>\r\n");
      out.write("<div class=\"loader\"></div>\r\n");
      out.write("<div class=\"stitched\">\r\n");
      out.write("<h1>Existing Violation(s)</h1>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<center><img src='IMG/status");
      out.print(viol_tx_status);
      out.write(".png' ></img></center>\r\n");
      out.write("<nav>\r\n");
      out.write("  <ul class=\"top-menu\">\r\n");
      out.write("    <li><a href=\"index.jsp\">Home</a><div class=\"menu-item\" id=\"home\"></div></li>\r\n");
      out.write("    <li><a href=\"CreateNew.jsp\">Create New Set</a><div class=\"menu-item\" id=\"CreateNew\"></div></li>\r\n");
      out.write("    <li><a href=\"ExistingViolations.jsp\">OpenExisting</a><div class=\"menu-item\" id=\"OpenExisting\"></div></li>\r\n");
      out.write("    <li><a href=\"contact.jsp\">Contact</a><div class=\"menu-item\" id=\"contact\"></div></li>\r\n");
      out.write("    <li><a href=\"about.jsp\">About</a><div class=\"menu-item\" id=\"About\"></div></li>\r\n");
      out.write("  </ul>\r\n");
      out.write("</nav>\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<p style = \"font-weight: bold;\">Transactions for Xfer ID : ");
      out.print(xfer);
      out.write("</p>\r\n");
      out.write("<button type=\"button\" onClick=\"refreshPage()\">Refresh</button>\r\n");
      out.write("<v style =\"font-weight: bold;margin-left: 970px;\"> Summary of transactions</v>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    function refreshPage() {\r\n");
      out.write("    \t \t$(\"#rload\").load( \"ExistingViolations.jsp #rload\" );\r\n");
      out.write("    } \r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<form font-size=\"75px;\"  action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/buttonServlet\" method=\"post\">\r\n");
      out.write("<div id =\"rload\">\r\n");
      out.write("<table id = \"summary\" border=\"2\" >\r\n");
      out.write("\r\n");
      out.write("\r\n");

try
{
	String DBSel = uploadFile.getDBselection();
    String dbURL = null;
    String strUserID = null;
    String strPassword = null;
	String ipaddress = "10.36.20.51";
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	if(DBSel.equals("NJP2TST"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njp2tst)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	}
	else if(DBSel.equals("NJAETTST"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = NJAETTST)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	}
	else if(DBSel.equals("njrbtst"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	}
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
    String Statement = "select VIOL_TX_STATUS, COUNT(*) AS COUNT FROM T_VIOL_TX WHERE EXTERN_FILE_ID = '"+xfer+"' GROUP BY VIOL_TX_STATUS"; 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
  
    
      out.write("  \r\n");
      out.write("   <tr style =\"background : brown\">\r\n");
      out.write("<td style=\"color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;\">VIOL_TX_STATUS</td>\r\n");
      out.write("<td style=\"color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;\">COUNT</td>\r\n");
      out.write("\r\n");
      out.write("</tr>\r\n");
      out.write(" ");
 
while(rs.next())
{


      out.write("\r\n");
      out.write("<tr style =\"background : #f7f7f7\">\r\n");
      out.write("<td style=\"color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;\">");
      out.print(rs.getString("VIOL_TX_STATUS") );
      out.write("</td>\r\n");
      out.write("<td style=\"color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;\">");
      out.print(rs.getString("COUNT") );
      out.write("</td>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" ");


}

      out.write("\r\n");
      out.write("</table>\r\n");

c.close();
}
catch(Exception e)
{
e.printStackTrace();
}

      out.write("\r\n");
      out.write("<table border=\"2\" >\r\n");
      out.write("\r\n");

try
{
	String DBSel = uploadFile.getDBselection();
    String dbURL = null;
    String strUserID = null;
    String strPassword = null;

	String ipaddress = "10.36.20.51";
	DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver()); 
	if(DBSel.equals("NJP2TST"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njp2tst)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	}
	else if(DBSel.equals("NJAETTST"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = NJAETTST)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	} 
	else if(DBSel.equals("njrbtst"))
	{
		 dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = "+ipaddress+")(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = njrbtst)))";
	     strUserID = "pbatch";
	     strPassword = "pbatch443"; 
	} 
    Connection c=DriverManager.getConnection(dbURL,strUserID,strPassword);
    
    String Statement = "select * from t_viol_tx where extern_file_id ='"+xfer+"'"; 
    
    ResultSet rs = c.createStatement().executeQuery(Statement);
  
    
      out.write("  \r\n");
      out.write("   <tr style =\"background : brown\">\r\n");
      out.write("<td style=\"color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;\">LANE_TX_ID</td>\r\n");
      out.write("<td style=\"color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;\">VIOL_TX_STATUS</td>\r\n");
      out.write("<td style=\"color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;\">TX_DATE</td>\r\n");
      out.write("<td style=\"color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;\">PLATE STATE</td>\r\n");
      out.write("<td style=\"color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;\">PLATE NUMBER</td>\r\n");
      out.write("<td style=\"color:white;margin-left:10px;font-weight: bold;padding: 4px 6px;\">TX_TIMESTAMP</td>\r\n");
      out.write("</tr>\r\n");
      out.write(" ");
 
while(rs.next())
{


      out.write("\r\n");
      out.write("<tr style =\"background : #f7f7f7\">\r\n");
      out.write("<td style=\"color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;\">");
      out.print(rs.getString("LANE_TX_ID") );
      out.write("</td>\r\n");
      out.write("<td style=\"color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;\">");
      out.print(rs.getString("VIOL_TX_STATUS") );
      out.write("</td>\r\n");
      out.write("<td style=\"color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;\">");
      out.print(rs.getDate("TX_DATE") );
      out.write("</td>\r\n");
      out.write("<td style=\"color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;\">");
      out.print(rs.getString("PLATE_STATE") );
      out.write("</td>\r\n");
      out.write("<td style=\"color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;\">");
      out.print(rs.getString("PLATE_NUMBER") );
      out.write("</td>\r\n");
      out.write("<td style=\"color:black;margin-left:10px;font-weight: bold;padding: 4px 6px;\">");
      out.print(rs.getString("TX_TIMESTAMP") );
      out.write("</td></tr>\r\n");
      out.write("\r\n");
      out.write(" ");


}

      out.write("\r\n");
      out.write("</table>\r\n");

c.close();
}
catch(Exception e)
{
e.printStackTrace();
}

      out.write("\r\n");
      out.write("\r\n");
      out.write(" <tr><button type=\"submit\" name=\"MoveViolation\" value=\"MoveViolation\">Move Violation to Next Phase</button></tr>\r\n");
      out.write(" </div>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}