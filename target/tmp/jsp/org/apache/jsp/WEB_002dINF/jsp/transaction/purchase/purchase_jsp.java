/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.10.v20180503
 * Generated at: 2018-11-03 05:36:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.transaction.purchase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.vithu.uscms.entities.Purchase;
import java.util.List;
import com.vithu.uscms.others.GenericResult;

public final class purchase_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("jar:file:/C:/Users/THINKPAD/.m2/repository/org/springframework/spring-webmvc/5.0.2.RELEASE/spring-webmvc-5.0.2.RELEASE.jar!/META-INF/spring.tld", Long.valueOf(1511758628000L));
    _jspx_dependants.put("/WEB-INF/jsp/transaction/purchase/../../../layouts/taglib.jsp", Long.valueOf(1524938852000L));
    _jspx_dependants.put("jar:file:/C:/Users/THINKPAD/.m2/repository/javax/servlet/jstl/1.2/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153365282000L));
    _jspx_dependants.put("file:/C:/Users/THINKPAD/.m2/repository/org/springframework/spring-webmvc/5.0.2.RELEASE/spring-webmvc-5.0.2.RELEASE.jar", Long.valueOf(1540711824235L));
    _jspx_dependants.put("file:/C:/Users/THINKPAD/.m2/repository/javax/servlet/jstl/1.2/jstl-1.2.jar", Long.valueOf(1540711797201L));
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
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.vithu.uscms.others.GenericResult");
    _jspx_imports_classes.add("com.vithu.uscms.entities.Purchase");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
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
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!-- /** -->\r\n");
      out.write("<!--  * @author M.Vithusanth -->\r\n");
      out.write("<!--  * @Created On 27th April 2018 -->\r\n");
      out.write("<!--  * @Purpose Purchase  Page  -->\r\n");
      out.write("<!--  */ -->\r\n");
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
      out.write("<!-- Content Wrapper. Contains page content -->\r\n");
      out.write("<div class=\"content-wrapper\">\r\n");
      out.write("\t<!-- Content Header (Page header) -->\r\n");
      out.write("\t<section class=\"content-header\">\r\n");
      out.write("\t\t<a\r\n");
      out.write("\t\t\thref=\"/purchaseAddView.html?token=");
      out.print(session.getAttribute("Token"));
      out.write("\"\r\n");
      out.write("\t\t\tclass=\"btn btn-success\">Add New Purchase</a>\r\n");
      out.write("\t</section>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Main content -->\r\n");
      out.write("\t<section class=\"content\">\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t<div class=\"box box-body box-success\">\r\n");
      out.write("\t\t\t\t\t<table id=\"view-table\"\r\n");
      out.write("\t\t\t\t\t\tclass=\"table table-condensed table-bordered table-hover table-striped table-pad\">\r\n");
      out.write("\t\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th style='text-align: center'>#</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Code</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Supplier</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Purchased</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th></th>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t<div class=\"box box-body box-info\" id=\"trans-details\">\r\n");
      out.write("\t\t\t\t\t<table\r\n");
      out.write("\t\t\t\t\t\tclass=\"table table-condensed table-hover table-striped table-bordered\">\r\n");
      out.write("\t\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Product</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Exp. Price</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Qty</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Discount</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Total</th>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t\t<tbody id=\"po-product\">\r\n");
      out.write("\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t<tfoot>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th colspan=\"4\" style='text-align: center'>Grand Total</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th class='number' id=\"grand-total\"></th>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</tfoot>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"input-group\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"input-group-addon\">Added By</label> <input\r\n");
      out.write("\t\t\t\t\t\t\ttype=\"text\" readonly class=\"form-control\" id=\"added-by\" />\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"input-group\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"input-group-addon\">Department</label> <input\r\n");
      out.write("\t\t\t\t\t\t\ttype=\"text\" readonly class=\"form-control\" id=\"dept-name\" />\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"input-group\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"input-group-addon\">Note</label>\r\n");
      out.write("\t\t\t\t\t\t<textarea readonly rows=\"1\" class=\"form-control\" id=\"note\"></textarea>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div style=\"width: 100%; text-align: center\" id=\"pop-table-footer\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</section>\r\n");
      out.write("\r\n");
      out.write("\t<!-- /.content -->\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- REQUIRED JS SCRIPTS -->\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("\"></script>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tvar purchases = \"\";\r\n");
      out.write("\tpurchases = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purchases.resultString}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(";\r\n");
      out.write("\r\n");
      out.write("\t//Data table\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$('#view-table').DataTable({\r\n");
      out.write("\t\t\t\"scrollX\" : true,\r\n");
      out.write("\t\t\t\"info\":false\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\t//Function for view purchase order products\r\n");
      out.write("\tfunction viewPurProduct(id) {\r\n");
      out.write("\t\t$(\".selected-row\").removeClass(\"selected-row\");\r\n");
      out.write("\t\t$(\"#\"+id).addClass(\"selected-row\");\r\n");
      out.write("\r\n");
      out.write("\t\tvar grandTotal=0;\r\n");
      out.write("\t\t$(\"#po-product\").empty();\r\n");
      out.write("\t\t$.each(purchases.result, function(i, purchase) {\r\n");
      out.write("\t\t\tif (purchase.id == id) {\r\n");
      out.write("\t\t\t\tconsole.log(purchase);\r\n");
      out.write("\t\t\t\tconsole.log(purchase.dept.name);\r\n");
      out.write("\t\t\t\tconsole.log(purchase.note);\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t$(\"#added-by\").val(purchase.addedBy.user.name);\r\n");
      out.write("\t\t\t\t$(\"#dept-name\").val(purchase.dept.name);\r\n");
      out.write("\t\t\t\t$(\"#note\").val(purchase.note);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$.each(purchase.purProduct, function(i, pp) {\r\n");
      out.write("\t\t\t\t\tconsole.log(pp);\r\n");
      out.write("\t\t\t\t\tvar htmlStr = \"<tr>\";\r\n");
      out.write("\t\t\t\t\thtmlStr += \"<td >\"+pp.product.name+\" | <i style='font-size:12px;'>\"+pp.product.code+\"</i></td>\";\r\n");
      out.write("\t\t\t\t\thtmlStr += \"<td class='number'>\"+formatNumber(pp.unitPrice,2)+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtmlStr += \"<td class='number'>\"+formatNumber(pp.qty,2)+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtmlStr += \"<td class='number'>\"+formatNumber(pp.totDiscount,2)+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtmlStr += \"<td class='number'>\"+formatNumber(pp.qty*pp.unitPrice-pp.totDiscount,2)+\"</td>\";\r\n");
      out.write("\t\t\t\t\thtmlStr += \"</tr>\";\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t$(\"#po-product\").append(htmlStr);\r\n");
      out.write("\t\t\t\t\tgrandTotal=grandTotal+(pp.qty*pp.unitPrice-pp.totDiscount);\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#grand-total\").html(formatNumber(grandTotal,2));\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t$(document).ready(function () {\r\n");
      out.write("\t\t$(\"#sidebar-style\").addClass('sidebar-collapse');\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("</script>");
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

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f0_reused = false;
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /WEB-INF/jsp/transaction/purchase/purchase.jsp(39,7) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/transaction/purchase/purchase.jsp(39,7) '${purchases.result}'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${purchases.result}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      // /WEB-INF/jsp/transaction/purchase/purchase.jsp(39,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("purchase");
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t\t<tr onclick=\"viewPurProduct(");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purchase.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write(")\" id=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purchase.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("\">\r\n");
            out.write("\t\t\t\t\t\t\t\t\t<td></td>\r\n");
            out.write("\t\t\t\t\t\t\t\t\t<td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purchase.code}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\r\n");
            out.write("\t\t\t\t\t\t\t\t\t<td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purchase.supplier.user.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\r\n");
            out.write("\t\t\t\t\t\t\t\t\t<td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purchase.tDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\r\n");
            out.write("\t\t\t\t\t\t\t\t\t<td style='text-align: center; position: relative;'><i\r\n");
            out.write("\t\t\t\t\t\t\t\t\t\tclass='arrow glyphicon glyphicon-arrow-right'></i></td>\r\n");
            out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
            out.write("\t\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      _jspx_th_c_005fforEach_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
    }
    return false;
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
      // /WEB-INF/jsp/transaction/purchase/purchase.jsp(109,6) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f0.setValue(" /resources/plugins/datatables/jquery.dataTables.min.js");
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

  private boolean _jspx_meth_c_005furl_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f1_reused = false;
    try {
      _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f1.setParent(null);
      // /WEB-INF/jsp/transaction/purchase/purchase.jsp(111,6) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f1.setValue("/resources/plugins/datatables/dataTables.bootstrap.min.js");
      int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
      if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      _jspx_th_c_005furl_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f1, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f1_reused);
    }
    return false;
  }
}
