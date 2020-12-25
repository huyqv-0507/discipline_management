<%-- 
    Document   : addQuestion
    Created on : Dec 25, 2020, 7:30:31 AM
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
        <h1>ADD QUESTION TO LESSON PAGE</h1>
        <a href="/Discipline_Management/admin.jsp">Back</a>
        <s:form namespace="/addQuestionManagement" action="add">
            <s:select list="%{#session.LESSONS}" name="selectLesson" label="Choose lesson"></s:select>
            <s:iterator value="%{#session.QUESTIONS}" status="counter" var="question">
                <s:checkbox label="%{#question.desciption}" name="checkQuestion" fieldValue="%{#question.questionId}"></s:checkbox>
            </s:iterator>
            <s:submit value="Add"></s:submit>
        </s:form>
        <s:form namespace="/addQuestionManagement" action="view">
            <s:if test="%{#session.SHOPPINGQUESTION != null}">
                <s:submit value="View( %{#session.SHOPPINGQUESTION.size()} )"></s:submit>
            </s:if>
        </s:form>
    </body>
</html>
