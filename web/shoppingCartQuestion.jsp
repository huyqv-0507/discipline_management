<%-- 
    Document   : shoppingCartQuestion
    Created on : Dec 24, 2020, 10:28:37 PM
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
        <h1>ADD QUESTIONS TO LESSON PAGE</h1>
        <a href="/Discipline_Management/addQuestion.jsp">Back</a>
        <s:if test="%{#session.SHOPPINGQUESTION != null}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Lesson title</th>
                            <th>Question</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{#session.SHOPPINGQUESTION}" status="counter" var="questionShopping">
                            <tr>
                                <td><s:property value="%{#counter.count}"></s:property></td>
                                <td style="width: 100px"><s:property value="key.lessonTitle"></s:property></td>
                                <td><s:iterator value="value" var="question" status="counter">
                                        <s:property value="%{#question.desciption}"></s:property>
                                            <br/><hr>
                                    </s:iterator></td>
                                <td>
                                    <s:form namespace="/addQuestionManagement" action="delete" theme="simple">
                                        <s:hidden name="deleteKey" value="%{#questionShopping.key.lessonId}"></s:hidden>
                                        <s:submit value="Delete"></s:submit>
                                    </s:form>
                                </td>
                            </tr>

                        </s:iterator>

                    </tbody>
                </table>
        </s:if>
        <s:form namespace="/addQuestionManagement" action="addToDb">
            <s:submit value="Add to DB"></s:submit>
        </s:form>
    </body>
</html>
