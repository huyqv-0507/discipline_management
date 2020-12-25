<%-- 
    Document   : addLesson
    Created on : Dec 23, 2020, 10:07:22 AM
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
        <h1>ADD LESSON TO DISCIPLINE</h1>
        <a href="/Discipline_Management/admin.jsp">Back</a>
        <s:form namespace="/addLesson" action="add" >
            <s:select list="%{#session.DISCIPLINES}" name="disciplineSelect" label="Choose discipline"></s:select>
            <s:iterator value="%{#session.LESSONS}" var="lesson" status="counter">
                <s:checkbox label="%{#lesson.lessonTitle}" name="lessonCheck" fieldValue="%{#lesson.lessonTitle}"></s:checkbox>
            </s:iterator>
            <s:submit value="Add"></s:submit>
        </s:form>
        <s:form namespace="/addLesson" action="view">
            <s:if test="%{#session.SHOPPINGLESSON != null}">
                <s:submit value="View( %{#session.SHOPPINGLESSON.size()} )"></s:submit>
            </s:if>
        </s:form>
    </body>
</html>
