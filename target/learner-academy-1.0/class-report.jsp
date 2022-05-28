<%@ page import="tz.co.admin.portal.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="tz.co.admin.portal.model.Class" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class Report</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
            border: 1px solid #ddd;
        }


        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<jsp:useBean id="repository" class="tz.co.admin.portal.repository.Repository" />
<%List<Class> classes = repository.loadAllData(Class.class); %>
<div id="classReport">
    <h3>Class Report</h3>
    <div>
        <%for (Class _class : classes) { %>
        <table>
            <caption><%=_class.getName()%></caption>
            <tr>
                <th>StudentID</th>
                <th>Student</th>
            </tr>
            <%for (Student student : _class.getStudents()) { %>
                <tr>
                    <td><%=student.getId()%></td>
                    <td><%=student.getStudentName()%></td>
                </tr>
            <% } %>
        </table>
        <% } %>

    </div>
</div>

</body>
</html>
