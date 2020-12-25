<%-- 
    Document   : questionManagement
    Created on : Dec 12, 2020, 9:53:43 AM
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
        <h1>QUESTION BANK PAGE</h1>
        <a href="/Discipline_Management/admin.jsp">Back</a>
        <s:form namespace="/questionManagement" action="create">
            <s:textfield label="Question description" name="questionDescription"></s:textfield>
            <s:textfield label="Answer A" name="answerA"></s:textfield>
            <s:textfield label="Answer B" name="answerB"></s:textfield>
            <s:textfield label="Answer C" name="answerC"></s:textfield>
            <s:textfield label="Answer D" name="answerD"></s:textfield>
            <s:label value="Correct answer of question:"></s:label>
            <s:select list="%{#session.ANSWERS}" name="isCorrectAnswer"></s:select>
            <s:submit value="Create"></s:submit>
        </s:form>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Question</th>
                    <th>Answer</th>
                    <th>Correct answer</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="%{#session.QBANK}" status="counter" var="qbank">
                    <tr>
                        <s:form namespace="/questionManagement" action="edit" theme="simple">
                            <td><s:property value="%{#counter.count}"></s:property></td>
                            <td><s:property value="key"/></td>
                            <td>
                                <s:iterator value="value" var="answer" status="counter">
                                    <s:property value="%{#answer.description}"/><br/>
                                </s:iterator>
                            </td>
                            <td>
                                <s:iterator value="value" var="answer" status="counter">
                                    <s:if test="%{#answer.isCorrect == true}">
                                        <s:property value="%{#answer.description}"/><br/>
                                    </s:if>
                                </s:iterator>
                            </td>
                            <td>
                                <s:hidden name="keyEdit" value="%{#answer.questionId}"></s:hidden>
                                <s:hidden name="tmpIdA" value="%{#value.get(0).}"></s:hidden>
                                <s:hidden name="tmpIdB" value="%{#answer.questionId}"></s:hidden>
                                <s:hidden name="tmpIdC" value="%{#answer.questionId}"></s:hidden>
                                <s:hidden name="tmpIdD" value="%{#answer.questionId}"></s:hidden>
                                
                                <s:submit value="Edit"></s:submit>
                                </td>
                        </s:form>
                        <td>
                            <s:form namespace="/questionManagement" action="delete">
                                <s:hidden name="keyEdit" value="%{#answer.questionId}"></s:hidden>
                                <s:submit value="Delete"></s:submit>
                            </s:form>

                        </td>
                    </tr>

                </s:iterator>

            </tbody>
        </table>

    </body>
</html>
