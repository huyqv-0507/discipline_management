<%-- 
    Document   : admin.jsp
    Created on : Dec 12, 2020, 9:30:44 AM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ADMIN PAGE</h1>
        <s:form action="manage" theme="simple">
            <s:submit value="Manage Discipline" name="smDiscipline"></s:submit><br/>
            <s:submit value="Manage Lesson" name="smLesson"></s:submit><br/>
            <s:submit value="Manage Question" name="smQuestion"></s:submit><br/>
        </s:form>
        <s:form namespace="/disciplineManagement" action="addLesson" theme="simple">
            <s:submit value="Add Lesson To Discipline" name="smButton"></s:submit>
        </s:form>
        <s:form namespace="/addQuestionManagement" action="addQuestion"  theme="simple">
            <s:submit value="Add Question To Lesson"></s:submit>
        </s:form>
        <s:form namespace="/testManagement" action="createTest" theme="simple">
            <s:submit value="Create test"></s:submit>
        </s:form>
    </body>
</html>
