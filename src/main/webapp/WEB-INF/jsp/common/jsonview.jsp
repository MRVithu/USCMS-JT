<!-- /** -->
<!--  * @author M.Vithusanth -->
<!--  * @CreatedOn 20th April 2018 -->
<!--  * @Purpose JSON page to send -->
<!--  */ -->

<%@page import="com.vithu.uscms.others.JsonFormer"%>
<%@page import="com.vithu.uscms.others.GenericResult"%>
<% GenericResult r = (GenericResult)request.getAttribute("response");
	String json = JsonFormer.form(r);
%>
<%= json %>