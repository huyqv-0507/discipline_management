<%-- 
    Document   : editQA
    Created on : Dec 22, 2020, 9:25:51 PM
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
        <h1>EDIT QA</h1>
        <a href="/Discipline_Management/questionManagement.jsp">Back to Question Management</a>
        <s:form namespace="/questionManagement" action="saveEdit">
            <s:textfield label="Question description" name="questionDescription" value="%{#request.QUESTION_EDIT.desciption}"></s:textfield>
            <s:hidden name="keyEdit" value="%{#request.QUESTION_EDIT.questionId}"></s:hidden>
            <s:textfield label="Answer A" name="answerA" value="%{#request.answerA}"></s:textfield>
            <s:textfield label="Answer B" name="answerB" value="%{#request.answerB}"></s:textfield>
            <s:textfield label="Answer C" name="answerC" value="%{#request.answerC}"></s:textfield>
            <s:textfield label="Answer D" name="answerD" value="%{#request.answerD}"></s:textfield>
            <s:label value="Correct answer of question:"></s:label>
            <s:select list="%{#session.ANSWERS}" name="isCorrectAnswer"></s:select>
            <s:submit value="Save"></s:submit>
        </s:form>
    </body>
</html>
