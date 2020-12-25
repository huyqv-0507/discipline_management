<%-- 
    Document   : shoppingCartLesson
    Created on : Dec 23, 2020, 10:48:00 PM
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
        <h1>ADD LESSON TO DISCIPLINE PAGE</h1>
        <a href="/Discipline_Management/addLesson.jsp">Back</a>
        <s:if test="%{#session.SHOPPINGLESSON != null}">

            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Discipline name</th>
                        <th>Lesson name</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="%{#session.SHOPPINGLESSON}" status="counter" var="disciplineLesson">
                        <tr>
                            <td><s:property value="%{#counter.count}"></s:property></td>
                            <td><s:property value="key"></s:property></td>
                            <td><s:iterator value="value" var="lesson" status="counter">
                                    <s:property value="%{#lesson}"></s:property><br/>
                                </s:iterator></td>
                            <td>
                                <s:form namespace="/addLesson" action="delete">
                                    <s:hidden name="deleteKey" value="%{#disciplineLesson.key}"></s:hidden>
                                    <s:submit value="Delete"></s:submit>
                                </s:form>
                            </td>
                        </tr>

                    </s:iterator>

                </tbody>
            </table>

            <s:form namespace="/addLesson" action="addToDb">
                <s:submit value="Add to DB"></s:submit>
            </s:form>
        </s:if>

    </body>
</html>
