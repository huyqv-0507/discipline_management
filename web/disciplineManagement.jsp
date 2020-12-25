<%-- 
    Document   : disciplineManagement
    Created on : Dec 12, 2020, 9:43:07 AM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="common/disciplineManagement.css">
        <s:head/>
    </head>
    <body>
        <h1>DISCIPLINE PAGE</h1>
        <a href="/Discipline_Management/admin.jsp">Back</a>
        <s:form namespace="/disciplineManagement" action="create" >
            <s:textfield label="Discipline name" name="disciplineName"></s:textfield>
            <s:select list="%{#session.MAJORS}" label="Choose major: " name="major"></s:select>
            <s:submit value="Create" name="smButton"></s:submit>
        </s:form>
        <s:form namespace="/disciplineManagement" action="search">
            <s:textfield name="searchWord" label="Discipline name"></s:textfield>
            <s:submit value="Search"  name="smButton"></s:submit>
        </s:form>
        
        <s:form namespace="/disciplineManagement" action="showAll">
            <s:submit value="Refresh all"  name="smButton"></s:submit>
        </s:form>
        <s:if test="%{#session.DISCIPLINES != null && #session.DISCIPLINES.size() != 0}">
            <table border="1" width="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>DisciplineName</th>
                        <th>MajorName</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="%{#session.DISCIPLINES}" var="discipline" status="counter">

                        <tr>
                            <s:form namespace="/disciplineManagement" action="update" theme="simple">
                                <td>
                                    <s:property value="%{#counter.count}"></s:property>
                                    </td>
                                    <td>
                                    <s:textfield theme="simple" name="disciplineName" value="%{#discipline.disciplineName}"></s:textfield>
                                    </td>
                                    <td>
                                    <s:select list="%{#session.MAJORS}" value="%{#discipline.majorId}" name="major" theme="simple"></s:select>
                                    </td>
                                    <td>

                                    <s:hidden name="disciplineId" value="%{#discipline.disciplineId}"></s:hidden>
                                    <s:submit value="Update" name="smButton"></s:submit>

                                    </td>
                            </s:form>
                            <td>
                                <s:form namespace="/disciplineManagement" action="delete">
                                    <s:hidden name="disciplineId" value="%{#discipline.disciplineId}"></s:hidden>
                                    <s:submit value="Delete" name="smButton"></s:submit>
                                </s:form>
                            </td>
                        </tr>

                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:if test="%{notifyEmpty == true}">
            <h2>No result</h2>
        </s:if>
    </body>
</html>
