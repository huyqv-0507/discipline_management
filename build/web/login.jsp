<%-- 
    Document   : index
    Created on : Dec 11, 2020, 11:20:16 PM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Discipline Management</h1>
        <h2>LOGIN PAGE</h2>
        <s:form action="login" method="post">
            <s:textfield label="UserName" name="userName"></s:textfield>
            <s:password label="Password" name="password"></s:password>
            <s:submit value="Login"></s:submit>
        </s:form>
    </body>
</html>
