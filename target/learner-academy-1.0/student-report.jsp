<%@ page import="tz.co.admin.portal.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Report</title>
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
<div id="studentReport" border="1" >
    <h3>Students Report</h3>
    <div>
        <table>
            <tr>
                <th>StudentID</th>
                <th>Student</th>
                <th>Class</th>
            </tr>
            <%
                List<Student> students = repository.loadAllData(Student.class);
                for (Student student : students) { %>
            <tr>
                <td><%=student.getId()%></td>
                <td><%=student.getStudentName()%></td>
                <td><%=student.getStudentClass().getName()%></td>
            </tr>
            <% } %>
        </table>
    </div>
</div>

</body>
</html>
