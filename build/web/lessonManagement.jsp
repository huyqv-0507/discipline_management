<%-- 
    Document   : lessonManagement
    Created on : Dec 12, 2020, 9:53:55 AM
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
        <h1>LESSON PAGE</h1>
        <a href="/Discipline_Management/admin.jsp">Back</a>

        <s:form namespace="/lessonManagement" action="create" method="post">
            <s:textarea label="Lesson title" name="title"></s:textarea>
            <s:textarea label="Lesson content" name="content"></s:textarea>
            <s:file id="fileName" value="Choose" enctype="multipart/form-data" onchange="uploadFile(this)"></s:file>
            <s:hidden id="fileURL" name="fileURL"></s:hidden>
            <s:select list="%{#session.DEPARTMENTS}" label="Choose department" name="department"></s:select>
            <s:submit value="Create" name="smButton"></s:submit>
        </s:form>
        
        <s:form namespace="/lessonManagement" action="search">
            <s:textfield name="searchWord" label="Enter title"></s:textfield>
            <s:submit value="Search" name="smButton"></s:submit>
        </s:form>
        <s:form namespace="/lessonManagement" action="showAll">
            <s:submit value="Refresh all"  name="smButton"></s:submit>
        </s:form>
        <s:if test="%{#session.LESSONS != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>Document</th>
                        <th>Department</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="%{#session.LESSONS}" var="lesson" status="counter">

                        <tr>
                            <s:form namespace="/lessonManagement" action="update" theme="simple">
                                <td><s:property value="%{#counter.count}"></s:property></td>
                                    <td>
                                    <s:textfield name="title" value="%{#lesson.lessonTitle}"></s:textfield>
                                    </td>
                                    <td>
                                    <s:textfield name="content" value="%{#lesson.lessonContent}"></s:textfield>
                                    </td>
                                    <td>
                                    <s:a href="%{#lesson.lessonUrl}" target="_blank" rel="noopener noreferrer">Open</s:a>
                                    </td>
                                    <td>
                                    <s:select list="%{#session.DEPARTMENTS}" value="%{#lesson.department}" name="department"></s:select>
                                    </td>
                                    <td>
                                    <s:hidden name="lessonIdUpdate" value="%{#lesson.lessonId}"></s:hidden>
                                    <s:submit value="Update" name="smButton"></s:submit>
                                    </td>

                            </s:form>
                            <td>
                                <s:form namespace="/lessonManagement" action="delete" >
                                    <s:hidden name="deleteId" value="%{#lesson.lessonId}"></s:hidden>
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
    <!-- Library -->
    <script src="https://www.gstatic.com/firebasejs/8.2.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.1.2/firebase-storage.js"></script>
    <script>
        var firebaseConfig = {
            apiKey: "AIzaSyCm5UVxGS3UeqwtVBgMa1aFaWSArJXbT4U",
            authDomain: "disciplinemanagement-78b8c.firebaseapp.com",
            projectId: "disciplinemanagement-78b8c",
            storageBucket: "disciplinemanagement-78b8c.appspot.com",
            messagingSenderId: "106722870752",
            appId: "1:106722870752:web:3a11ae03c670f0b80c5785",
            measurementId: "G-SD5SB1E9WX"
        };
        firebase.initializeApp(firebaseConfig);

        function uploadFile(input) {
            console.log(input.files[0]);
            var storage = firebase.storage();
            console.log(storage);
            var file = input.files[0];

            console.log(file);

            var metadata = {
                contentType: file.type
            };
            var fname = new Date().getTime() + file.name + "";

            var storageRef = storage.ref().child(fname);
            console.log(storageRef);
            storageRef.put(file, metadata).then(function (snapshot) {
                snapshot.ref.getDownloadURL().then(function (downloadURL) {
                    document.getElementById("fileURL").value = downloadURL;
                    console.log(document.getElementById("fileURL").value);
                });
            });
        }
    </script>
</html>
