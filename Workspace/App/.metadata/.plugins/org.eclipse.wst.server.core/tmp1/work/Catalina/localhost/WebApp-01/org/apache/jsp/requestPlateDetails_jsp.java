/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.30
 * Generated at: 2021-01-29 06:44:36 UTC
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

public final class requestPlateDetails_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\" src=\"E://TSG/Workspace/App/WebApp-01/WebContent/WEB-INF/libjquery-3.1.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(window).load(function() {\r\n");
      out.write("\t$(\".loader\").fadeOut(\"slow\");\r\n");
      out.write("\t\r\n");
      out.write("    $(\"#BuildPlate\").prop('disabled', true); \r\n");
      out.write("\t\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
String agencyname = uploadFile.getagencyselection(); 
      out.write("\r\n");
      out.write("\r\n");
int num = Integer.parseInt(common.ExcelRead("PlateDetails", "D:\\TSG\\Workspace\\App\\WebApp-01\\"+agencyname+"ViolMacro.xlsm", 1, 6));
      out.write("\r\n");
      out.write(" <script type=\"text/javascript\">\r\n");
      out.write(" var cnt=false;\r\n");
      out.write("function verifyVehicle(i,num)\r\n");
      out.write("{\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\tvar plate_state = document.forms[\"verification\"][\"PlateState\"+i].value;\r\n");
      out.write("\tvar plate_number = document.forms[\"verification\"][\"PlateNumber\"+i].value;\r\n");
      out.write("\t\r\n");
      out.write("\tconsole.log(plate_state+\",\"+plate_number);\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\t type: 'GET',\r\n");
      out.write("\t\t    url: \"/WebApp-01//buttonServlet?plate_state=\"+plate_state+\"&plate_number=\"+plate_number,\t\t    \r\n");
      out.write("\t\t    success: function(data, status, xhr) {\r\n");
      out.write("\t\t        var code1 = xhr.getResponseHeader('accountNum');\r\n");
      out.write("\t\t        var message;\r\n");
      out.write("\t\t        \r\n");
      out.write("\t\t       \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t        if(code1=='0')\r\n");
      out.write("\t\t        {\r\n");
      out.write("\t\t        \t\r\n");
      out.write("\t\t        \t   message = \"Plate does not exist\";\r\n");
      out.write("\t\t\t\t\t  $('#message'+i).html(message);\r\n");
      out.write("\t\t\t\t\t  $('#message'+i).css(\"color\", \"green\");\r\n");
      out.write("\t\t\t\t\t \r\n");
      out.write("\t\t        }\r\n");
      out.write("\t\t       else\r\n");
      out.write("\t\t\t  {\r\n");
      out.write("\t\t\t  message = \"Plate already exists\";\r\n");
      out.write("\t\t\t  $('#message'+i).html(message);\r\n");
      out.write("\t\t\t  $('#message'+i).css(\"color\", \"red\");\r\n");
      out.write("\t\t\t  cnt=true;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t  }\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function getcontent(num)\r\n");
      out.write("{\r\n");
      out.write("\tvar desc1 =\"Plate\";\r\n");
      out.write("\t\r\n");
      out.write("\tvar count =($(\".message\").text().match(new RegExp(desc1, \"g\")) || []).length;\r\n");
      out.write("\t\r\n");
      out.write("\tif(count==num)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t $(\"#BuildPlate\").prop('disabled', false); \r\n");
      out.write("\t\t \r\n");
      out.write("\t}\r\n");
      out.write("\telse\r\n");
      out.write("\t{\r\n");
      out.write("\t\t $(\"#BuildPlate\").prop('disabled', true); \r\n");
      out.write("\t\t\r\n");
      out.write("\t}\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("setInterval(function(){ \r\n");
      out.write("\tgetcontent(");
      out.print(num);
      out.write("); \r\n");
      out.write("}, 3000);\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<script>\r\n");
      out.write("$(document).ready(function(){\r\n");
      out.write("$(\"#DistributePlate\").click(function(){\r\n");
      out.write("var txt1=$(\"#GeneralPlate\").val();\r\n");
      out.write("var txt2=$(\"#GeneralState\").val();\r\n");
      out.write("\r\n");
      out.write("for(var i=1;i<=");
      out.print(num);
      out.write(";i++)\r\n");
      out.write("\t\r\n");
      out.write("{\t\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#PlateNumber\"+i).val(txt1+i);\r\n");
      out.write("\t$(\"#PlateState\"+i).val(txt2);\r\n");
      out.write("\tverifyVehicle(i,");
      out.print(num);
      out.write(");\r\n");
      out.write(" }  \r\n");
      out.write("\t\r\n");
      out.write(" \r\n");
      out.write(" });\r\n");
      out.write(" });\r\n");
      out.write(" \r\n");
      out.write("$(document).ready(function(){\r\n");
      out.write("\t$(\"#DuplicatePlate\").click(function(){\r\n");
      out.write("\tvar txt1=$(\"#GeneralPlate\").val();\r\n");
      out.write("\tvar txt2=$(\"#GeneralState\").val();\r\n");
      out.write("\r\n");
      out.write("\tfor(var i=1;i<=");
      out.print(num);
      out.write(";i++)\r\n");
      out.write("\t\t\r\n");
      out.write("\t{\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#PlateNumber\"+i).val(txt1);\r\n");
      out.write("\t\t$(\"#PlateState\"+i).val(txt2);\r\n");
      out.write("\t\tverifyVehicle(i,");
      out.print(num);
      out.write(");\r\n");
      out.write("\t }  \r\n");
      out.write("\t\t\r\n");
      out.write("\t \r\n");
      out.write("\t });\r\n");
      out.write("\t });\r\n");
      out.write(" </script>\r\n");
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
      out.write("<h1>Request Image Details Page</h1>\r\n");
      out.write("</div>\r\n");
      out.write("<center><img src='IMG/status4.png' ></img></center>\r\n");
      out.write("<nav>\r\n");
      out.write("  <ul class=\"top-menu\">\r\n");
      out.write("    <li><a href=\"index.jsp\">Home</a><div class=\"menu-item\" id=\"home\"></div></li>\r\n");
      out.write("    <li><a href=\"CreateNew.jsp\">Create New Set</a><div class=\"menu-item\" id=\"CreateNew\"></div></li>\r\n");
      out.write("    <li><a href=\"ExistingViolations.jsp\">OpenExisting</a><div class=\"menu-item\" id=\"OpenExisting\"></div></li>\r\n");
      out.write("    <li><a href=\"contact.jsp\">Contact</a><div class=\"menu-item\" id=\"contact\"></div></li>\r\n");
      out.write("    <li><a href=\"about.jsp\">About</a><div class=\"menu-item\" id=\"About\"></div></li>\r\n");
      out.write("  </ul>\r\n");
      out.write("</nav>\r\n");
      out.write("<form>\r\n");
      out.write("<tr>\r\n");
      out.write("     <td align=\"center\"><input type =\"text\" id =\"GeneralState\" name =\"GeneralState\"/></td>  \r\n");
      out.write("     <td align=\"center\"><input type =\"text\" id =\"GeneralPlate\" name =\"GeneralPlate\"/></td> \r\n");
      out.write("      <td align=\"center\"><input type =\"button\" id =\"DistributePlate\" value=\"DistributePlate\"></td>\r\n");
      out.write("       <td align=\"center\"><input type =\"button\" id =\"DuplicatePlate\" value=\"DuplicatePlate\"></td>\r\n");
      out.write("     </tr>\r\n");
      out.write("</form>\r\n");
      out.write("<form font-size=\"75px;\" name=\"verification\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/buttonServlet\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("<table style=\"width:40%\" border=\"2\">\r\n");
      out.write("\r\n");
      out.write(" <tr style =\"background : Navy\">\r\n");
      out.write("<td align=\"center\"><whi/>Plate State</td>\r\n");
      out.write("<td align=\"center\"><whi/>Plate Number</td>\r\n");
      out.write("<td align=\"center\"><whi/>Verify Plate</td>\r\n");
      out.write("<td align=\"center\"><whi/>Message</td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("\r\n");
for(int i=1;i<=num;i++)
	
{	
	
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<tr>\r\n");
      out.write("     <td align=\"center\"><input type =\"text\" id =\"PlateState");
      out.print(i);
      out.write("\" name =\"PlateState");
      out.print(i);
      out.write("\"/></td> \r\n");
      out.write("    <td align=\"center\"><input type =\"text\" id =\"PlateNumber");
      out.print(i);
      out.write("\" name =\"PlateNumber");
      out.print(i);
      out.write("\"/></td> \r\n");
      out.write("    <td align=\"center\"><input type =\"button\" id =\"vplate");
      out.print(i);
      out.write("\" name =\"vplate");
      out.print(i);
      out.write("\" value=\"Verify Plate\" onClick =\"verifyVehicle(");
      out.print(i);
      out.write(',');
      out.print(num);
      out.write(")\"/></td> \r\n");
      out.write("    <td align=\"center\" style = \"background : black\"><span id=\"message");
      out.print(i);
      out.write("\" class=\"message\"></span></td> \r\n");
      out.write("    </tr> \r\n");
      out.write(" ");
} 
      out.write("     \r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("<input type='submit' name='BuildPlate' id='BuildPlate'  value='BuildPlate'/>\r\n");
      out.write("\r\n");
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