/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.10.v20180503
 * Generated at: 2018-11-03 06:08:55 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.vithu.uscms.entities.Sales;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("jar:file:/C:/Users/THINKPAD/.m2/repository/org/springframework/spring-webmvc/5.0.2.RELEASE/spring-webmvc-5.0.2.RELEASE.jar!/META-INF/spring.tld", Long.valueOf(1511758628000L));
    _jspx_dependants.put("jar:file:/C:/Users/THINKPAD/.m2/repository/javax/servlet/jstl/1.2/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153365282000L));
    _jspx_dependants.put("file:/C:/Users/THINKPAD/.m2/repository/org/springframework/spring-webmvc/5.0.2.RELEASE/spring-webmvc-5.0.2.RELEASE.jar", Long.valueOf(1540711824235L));
    _jspx_dependants.put("file:/C:/Users/THINKPAD/.m2/repository/javax/servlet/jstl/1.2/jstl-1.2.jar", Long.valueOf(1540711797201L));
    _jspx_dependants.put("/WEB-INF/jsp/../layouts/taglib.jsp", Long.valueOf(1524938852000L));
    _jspx_dependants.put("jar:file:/C:/Users/THINKPAD/.m2/repository/org/springframework/spring-webmvc/5.0.2.RELEASE/spring-webmvc-5.0.2.RELEASE.jar!/META-INF/spring-form.tld", Long.valueOf(1511758628000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.vithu.uscms.entities.Sales");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

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
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- /** -->\r\n");
      out.write("<!--  * @author M.Vithusanth -->\r\n");
      out.write("<!--  * @CreatedOn 20th April 2018 -->\r\n");
      out.write("<!--  * @Purpose Maintaining libs -->\r\n");
      out.write("<!--  */ -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Content Wrapper. Contains page content -->\r\n");
      out.write("<div class=\"content-wrapper\">\r\n");
      out.write("\t<!-- Content Header (Page header) -->\r\n");
      out.write("\t<section class=\"content-header\">\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t<a\r\n");
      out.write("\t\t\t\t\thref=\"/salesAddView.html?token=");
      out.print(session.getAttribute("Token"));
      out.write("\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn btn-info\">Sales</a> <a\r\n");
      out.write("\t\t\t\t\thref=\"/purchaseAddView.html?token=");
      out.print(session.getAttribute("Token"));
      out.write("\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn btn-info\">Purchase</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</section>\r\n");
      out.write("\t<!-- Main content -->\r\n");
      out.write("\t<section class=\"content\">\r\n");
      out.write("\t\t<!-- Small boxes (Stat box) -->\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div class=\"col-lg-4 col-xs-6\">\r\n");
      out.write("\t\t\t\t<!-- small box -->\r\n");
      out.write("\t\t\t\t<div class=\"small-box bg-aqua\">\r\n");
      out.write("\t\t\t\t\t<div class=\"inner\">\r\n");
      out.write("\t\t\t\t\t\t<h3>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${todayPurchaseOrders}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<p>Today Purchase Order</p>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"icon\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"ion ion-bag\"></i>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\" class=\"small-box-footer\">More info <i\r\n");
      out.write("\t\t\t\t\t\tclass=\"fa fa-arrow-circle-right\"></i></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t<div class=\"col-lg-4 col-xs-6\">\r\n");
      out.write("\t\t\t\t<!-- small box -->\r\n");
      out.write("\t\t\t\t<div class=\"small-box bg-yellow\">\r\n");
      out.write("\t\t\t\t\t<div class=\"inner\">\r\n");
      out.write("\t\t\t\t\t\t<h3>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${todayRegisteredCustomers}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<p>Today Customer Registrations</p>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"icon\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"ion ion-person-add\"></i>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\" class=\"small-box-footer\">More info <i\r\n");
      out.write("\t\t\t\t\t\tclass=\"fa fa-arrow-circle-right\"></i></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- ./col -->\r\n");
      out.write("\t\t\t<div class=\"col-lg-4 col-xs-6\">\r\n");
      out.write("\t\t\t\t<!-- small box -->\r\n");
      out.write("\t\t\t\t<div class=\"small-box bg-red\">\r\n");
      out.write("\t\t\t\t\t<div class=\"inner\">\r\n");
      out.write("\t\t\t\t\t\t<h3>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${todayRegisteredSupplier}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<p>Today Supplier Registrations</p>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"icon\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"ion ion-person-add\"></i>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\" class=\"small-box-footer\">More info <i\r\n");
      out.write("\t\t\t\t\t\tclass=\"fa fa-arrow-circle-right\"></i></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- ./col -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- /.row -->\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- AREA CHART -->\r\n");
      out.write("\t\t<div class=\"box box-primary\">\r\n");
      out.write("\t\t\t<div class=\"box-header with-border\">\r\n");
      out.write("\t\t\t\t<h3 class=\"box-title\">Last 14 days Sales Vs Purchases</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"box-tools pull-right\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-box-tool\"\r\n");
      out.write("\t\t\t\t\t\tdata-widget=\"collapse\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"fa fa-minus\"></i>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-box-tool\" data-widget=\"remove\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"fa fa-times\"></i>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"box-body\">\r\n");
      out.write("\t\t\t\t<div class=\"chart\">\r\n");
      out.write("\t\t\t\t\t<canvas id=\"areaChart\" style=\"height:250px\"></canvas>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- /.box-body -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t</section>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- ChartJS 1.0.1 -->\r\n");
      out.write("<script src=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\tvar logger=\"\";\r\n");
      out.write("\tlogger=\"");
      out.print(session.getAttribute("USER-NAME"));
      out.write("\";\r\n");
      out.write("\t//console.log(logger);\r\n");
      out.write("\t\r\n");
      out.write("\tvar salesAmountDayBy = \"\";\r\n");
      out.write("\tsalesAmountDayBy = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${salesAmountDayBy.resultString}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(";\r\n");
      out.write("\tconsole.log(salesAmountDayBy);\r\n");
      out.write("\t\r\n");
      out.write("\tvar purAmountDayBy = \"\";\r\n");
      out.write("\tpurAmountDayBy = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purAmountDayBy.resultString}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(";\r\n");
      out.write("\tconsole.log(purAmountDayBy);\r\n");
      out.write("\t\r\n");
      out.write("\tvar todayPurchaseOrders = \"\";\r\n");
      out.write("\ttodayPurchaseOrders = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${todayPurchaseOrders}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(";\r\n");
      out.write("\tconsole.log(\"--\"+todayPurchaseOrders);\r\n");
      out.write("\t\r\n");
      out.write("\tvar todayRegisteredSupplier = \"\";\r\n");
      out.write("\ttodayRegisteredSupplier = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${todayRegisteredSupplier}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(";\r\n");
      out.write("\tconsole.log(\"--\"+todayRegisteredSupplier);\r\n");
      out.write("\r\n");
      out.write("\tvar sales = \"\";\r\n");
      out.write("\tsales = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sales.resultString}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(";\r\n");
      out.write("\tconsole.log(sales);\r\n");
      out.write("\t\r\n");
      out.write("\tvar d = new Date();\r\n");
      out.write("\tvar t='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\td.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t1='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt1 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt1 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\td.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t2='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt2 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt2 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\td.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t3='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt3 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt3 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\td.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t4='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt4 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt4 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\td.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t5='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt5 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt5 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\td.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t6='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt6 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt6 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\td.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t7='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt7 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt7 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("    d.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t8='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt8 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt8 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    d.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t9='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt9 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt9 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("    d.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t10='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt10 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt10 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("    d.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t11='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt11 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt11 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("    d.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t12='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt12 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt12 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("    d.setDate(d.getDate() - 1);\r\n");
      out.write("\tvar t13='';\r\n");
      out.write("\tif(d.getDate() < 10){\r\n");
      out.write("\t\tt13 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-0\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tt13 = d.getFullYear() + \"-\" + (d.getMonth()+1) + \"-\" + d.getDate();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar s = 0;\r\n");
      out.write("\tvar s1 = 0;\r\n");
      out.write("\tvar s2 = 0;\r\n");
      out.write("\tvar s3 = 0;\r\n");
      out.write("\tvar s4 = 0;\r\n");
      out.write("\tvar s5 = 0;\r\n");
      out.write("\tvar s6 = 0;\r\n");
      out.write("\tvar s7 = 0;\r\n");
      out.write("\tvar s8 = 0;\r\n");
      out.write("\tvar s9 = 0;\r\n");
      out.write("\tvar s10 = 0;\r\n");
      out.write("\tvar s11 = 0;\r\n");
      out.write("\tvar s12 = 0;\r\n");
      out.write("\tvar s13 = 0;\r\n");
      out.write("\t\r\n");
      out.write("\t$.each( salesAmountDayBy.result, function(i, data) {\r\n");
      out.write("\t\tif(data.tDate == t){\r\n");
      out.write("\t\t\ts = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t1){\r\n");
      out.write("\t\t\ts1 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t2){\r\n");
      out.write("\t\t\ts2 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t3){\r\n");
      out.write("\t\t\ts3 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t4){\r\n");
      out.write("\t\t\ts4 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t5){\r\n");
      out.write("\t\t\ts5 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t6){\r\n");
      out.write("\t\t\ts6 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t7){\r\n");
      out.write("\t\t\ts7 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t8){\r\n");
      out.write("\t\t\ts8 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t9){\r\n");
      out.write("\t\t\ts9 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t10){\r\n");
      out.write("\t\t\ts10 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t11){\r\n");
      out.write("\t\t\ts11 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t12){\r\n");
      out.write("\t\t\ts12 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t13){\r\n");
      out.write("\t\t\ts13 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tvar p = 0;\r\n");
      out.write("\tvar p1 = 0;\r\n");
      out.write("\tvar p2 = 0;\r\n");
      out.write("\tvar p3 = 0;\r\n");
      out.write("\tvar p4 = 0;\r\n");
      out.write("\tvar p5 = 0;\r\n");
      out.write("\tvar p6 = 0;\r\n");
      out.write("\tvar p7 = 0;\r\n");
      out.write("\tvar p8 = 0;\r\n");
      out.write("\tvar p9 = 0;\r\n");
      out.write("\tvar p10 = 0;\r\n");
      out.write("\tvar p11 = 0;\r\n");
      out.write("\tvar p12 = 0;\r\n");
      out.write("\tvar p13 = 0;\r\n");
      out.write("\r\n");
      out.write("\t$.each( purAmountDayBy.result, function(i, data) {\r\n");
      out.write("\t\tif(data.tDate == t){\r\n");
      out.write("\t\t\tp = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t1){\r\n");
      out.write("\t\t\tp1 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t2){\r\n");
      out.write("\t\t\tp2 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t3){\r\n");
      out.write("\t\t\tp3 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t4){\r\n");
      out.write("\t\t\tp4 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t5){\r\n");
      out.write("\t\t\tp5 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t6){\r\n");
      out.write("\t\t\tp6 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t7){\r\n");
      out.write("\t\t\tp7 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t8){\r\n");
      out.write("\t\t\tp8 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t9){\r\n");
      out.write("\t\t\tp9 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t10){\r\n");
      out.write("\t\t\tp10 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t11){\r\n");
      out.write("\t\t\tp11 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t12){\r\n");
      out.write("\t\t\tp12 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(data.tDate == t13){\r\n");
      out.write("\t\t\tp13 = data.amount;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("    //--------------\r\n");
      out.write("    //- AREA CHART -\r\n");
      out.write("    //--------------\r\n");
      out.write("\r\n");
      out.write("    // Get context with jQuery - using jQuery's .get() method.\r\n");
      out.write("    var areaChartCanvas = $(\"#areaChart\").get(0).getContext(\"2d\");\r\n");
      out.write("    // This will get the first returned node in the jQuery collection.\r\n");
      out.write("    var areaChart = new Chart(areaChartCanvas);\r\n");
      out.write("\r\n");
      out.write("    var areaChartData = {\r\n");
      out.write("      labels: [t13, t12, t11, t10, t9, t8, t7, t6, t5, t4, t3, t2, t1, t],\r\n");
      out.write("      datasets: [\r\n");
      out.write("        {\r\n");
      out.write("          label: \"Sales\",\r\n");
      out.write("          fillColor: \"rgba(210, 214, 222, 1)\",\r\n");
      out.write("          strokeColor: \"rgba(210, 214, 222, 1)\",\r\n");
      out.write("          pointColor: \"rgba(210, 214, 222, 1)\",\r\n");
      out.write("          pointStrokeColor: \"#c1c7d1\",\r\n");
      out.write("          pointHighlightFill: \"#fff\",\r\n");
      out.write("          pointHighlightStroke: \"rgba(220,220,220,1)\",\r\n");
      out.write("          data: [s13, s12, s11, s10, s9, s8, s7, s6, s5, s4, s3, s2, s1, s]\r\n");
      out.write("        },\r\n");
      out.write("        {\r\n");
      out.write("          label: \"Purchase\",\r\n");
      out.write("          fillColor: \"rgba(60,141,188,0.9)\",\r\n");
      out.write("          strokeColor: \"rgba(60,141,188,0.8)\",\r\n");
      out.write("          pointColor: \"#3b8bba\",\r\n");
      out.write("          pointStrokeColor: \"rgba(60,141,188,1)\",\r\n");
      out.write("          pointHighlightFill: \"#fff\",\r\n");
      out.write("          pointHighlightStroke: \"rgba(60,141,188,1)\",\r\n");
      out.write("          data: [p13, p12, p11, p10, p9, p8, p7, p6, p5, p4, p3, p2, p1, p]\r\n");
      out.write("        }\r\n");
      out.write("      ]\r\n");
      out.write("    };\r\n");
      out.write("\r\n");
      out.write("    var areaChartOptions = {\r\n");
      out.write("      //Boolean - If we should show the scale at all\r\n");
      out.write("      showScale: true,\r\n");
      out.write("      //Boolean - Whether grid lines are shown across the chart\r\n");
      out.write("      scaleShowGridLines: false,\r\n");
      out.write("      //String - Colour of the grid lines\r\n");
      out.write("      scaleGridLineColor: \"rgba(0,0,0,.05)\",\r\n");
      out.write("      //Number - Width of the grid lines\r\n");
      out.write("      scaleGridLineWidth: 1,\r\n");
      out.write("      //Boolean - Whether to show horizontal lines (except X axis)\r\n");
      out.write("      scaleShowHorizontalLines: true,\r\n");
      out.write("      //Boolean - Whether to show vertical lines (except Y axis)\r\n");
      out.write("      scaleShowVerticalLines: true,\r\n");
      out.write("      //Boolean - Whether the line is curved between points\r\n");
      out.write("      bezierCurve: true,\r\n");
      out.write("      //Number - Tension of the bezier curve between points\r\n");
      out.write("      bezierCurveTension: 0.3,\r\n");
      out.write("      //Boolean - Whether to show a dot for each point\r\n");
      out.write("      pointDot: false,\r\n");
      out.write("      //Number - Radius of each point dot in pixels\r\n");
      out.write("      pointDotRadius: 4,\r\n");
      out.write("      //Number - Pixel width of point dot stroke\r\n");
      out.write("      pointDotStrokeWidth: 1,\r\n");
      out.write("      //Number - amount extra to add to the radius to cater for hit detection outside the drawn point\r\n");
      out.write("      pointHitDetectionRadius: 20,\r\n");
      out.write("      //Boolean - Whether to show a stroke for datasets\r\n");
      out.write("      datasetStroke: true,\r\n");
      out.write("      //Number - Pixel width of dataset stroke\r\n");
      out.write("      datasetStrokeWidth: 2,\r\n");
      out.write("      //Boolean - Whether to fill the dataset with a color\r\n");
      out.write("      datasetFill: true,\r\n");
      out.write("      //String - A legend template\r\n");
      out.write("     //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container\r\n");
      out.write("      maintainAspectRatio: true,\r\n");
      out.write("      //Boolean - whether to make the chart responsive to window resizing\r\n");
      out.write("      responsive: true\r\n");
      out.write("    };\r\n");
      out.write("\t\r\n");
      out.write("\tareaChart.Line(areaChartData, areaChartOptions);\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
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

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f0_reused = false;
    try {
      _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f0.setParent(null);
      // /WEB-INF/jsp/home.jsp(107,13) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f0.setValue("/resources/plugins/chartjs/Chart.min.js");
      int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
      if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      _jspx_th_c_005furl_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f0, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f0_reused);
    }
    return false;
  }
}
