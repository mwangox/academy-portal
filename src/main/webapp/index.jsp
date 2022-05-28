<%@ page import="tz.co.admin.portal.model.Class" %>
<%@ page import="tz.co.admin.portal.model.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="tz.co.admin.portal.model.Teacher" %>
<%@ page import="tz.co.admin.portal.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    * {box-sizing: border-box}
    body {font-family: "Lato", sans-serif;}

    .tab {
      float: left;
      border: 1px solid #ccc;
      background-color: #f1f1f1;
      width: 30%;
      height: 500px;
    }

    .tab button {
      display: block;
      background-color: inherit;
      color: black;
      padding: 22px 16px;
      width: 100%;
      border: none;
      outline: none;
      text-align: left;
      cursor: pointer;
      transition: 0.3s;
      font-size: 17px;
    }

    .tab button:hover {
      background-color: #ddd;
    }

    .tab button.active {
      background-color: #ccc;
    }

    .tabcontent {
      float: left;
      padding: 0px 12px;
      border: 1px solid #ccc;
      width: 70%;
      border-left: none;
      height: 300px;
    }

    input[type=text], select, textarea {
      width: 100%;
      padding: 12px;
      border: 1px solid #ccc;
      border-radius: 4px;
      resize: vertical;
    }

    label {
      padding: 12px 12px 12px 0;
      display: inline-block;
    }

    input[type=submit] {
      background-color: #04AA6D;
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      float: right;
    }

    input[type=submit]:hover {
      background-color: #45a049;
    }

    .container {
      border-radius: 5px;
      background-color: #f2f2f2;
      padding: 20px;
    }

    .col-25 {
      float: left;
      width: 25%;
      margin-top: 6px;
    }

    .col-75 {
      float: left;
      width: 75%;
      margin-top: 6px;
    }

    .row:after {
      content: "";
      display: table;
      clear: both;
    }
  </style>
</head>
<body>
<jsp:useBean id="repository" class="tz.co.admin.portal.repository.Repository" />
<div class="header">
  <div style="float: left">
    <h1>Learner Academy</h1>
    <h3>Online Management System</h3>
  </div>
  <div style="float: left">
    <a href="student-report.jsp">Student Report</a>
  </div>
  <div style="float: left">
    <a href="class-report.jsp">Class Report</a>
  </div>
</div>

<div class="tab" style="clear: both">
  <button class="tablinks" onclick="openMenu(event, 'subject')" id="defaultOpen">Register Subject</button>
  <button class="tablinks" onclick="openMenu(event, 'teacher')">Register Teacher</button>
  <button class="tablinks" onclick="openMenu(event, 'class')">Register Class</button>
  <button class="tablinks" onclick="openMenu(event, 'student')">Register Student</button>
  <div style="width: 100%">Assignment</div>
  <button class="tablinks" onclick="openMenu(event, 'subjectAssign')">Class - Subject Mapping</button>
  <button class="tablinks" onclick="openMenu(event, 'teacherAssign')">Teacher - Subject Mapping</button>
</div>

<div id="subject" class="tabcontent">
  <h3>Subject Registration</h3>
  <div class="container">
    <form action="subject" method="post">
      <div class="row">
        <div class="col-25">
          <label for="subjectName">Subject Name</label>
        </div>
        <div class="col-75">
          <input type="text" id="subjectName" name="subjectName" placeholder="">
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Submit">
      </div>
    </form>
  </div>
</div>

<div id="student" class="tabcontent">
  <h3>Student Registration</h3>
  <div class="container">
    <form action="student" method="post">
      <div class="row">
        <div class="col-25">
          <label for="studentName">Student Name</label>
        </div>
        <div class="col-75">
          <input type="text" id="studentName" name="studentName" placeholder="">
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="studentClass">Class</label>
        </div>
        <div class="col-75">
          <select id="studentClass" name="studentClass">
              <%
                List<Class> classes = repository.getClasses();
                for (Class aClass : classes) { %>
            <option value="<%=aClass.getId()%>"><%=aClass.getName()%></option>
            <% } %>
          </select>
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Submit">
      </div>
    </form>
  </div>
</div>

<div id="class" class="tabcontent">
  <h3>Class Registration</h3>
  <div class="container">
    <form action="class" method="post">
      <div class="row">
        <div class="col-25">
          <label for="className">Class Name</label>
        </div>
        <div class="col-75">
          <input type="text" id="className" name="className" placeholder="">
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Submit">
      </div>
    </form>
  </div>
</div>

<div id="teacher" class="tabcontent">
  <h3>Teacher Registration</h3>
  <div class="container">
    <form action="teacher" method="post">
      <div class="row">
        <div class="col-25">
          <label for="teacherName">Teacher Name</label>
        </div>
        <div class="col-75">
          <input type="text" id="teacherName" name="teacherName" placeholder="">
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Submit">
      </div>
    </form>
  </div>
</div>

<div id="subjectAssign" class="tabcontent">
  <h3>Subject Assignment</h3>
  <div class="container">
    <form action="subject-assignment" method="post">
      <div class="row">
        <div class="col-25">
          <label for="classSubject">Subject</label>
        </div>
        <div class="col-75">
          <select id="classSubject" name="classSubject">
            <%
              List<Subject> subjects = repository.loadAllData(Subject.class);
              for (Subject subject : subjects) { %>
            <option value="<%=subject.getId()%>"><%=subject.getSubjectName()%></option>
            <% } %>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="assignedClass">Class</label>
        </div>
        <div class="col-75">
          <select id="assignedClass" name="assignedClass">
              <%for (Class aClass : classes) { %>
            <option value="<%=aClass.getId()%>"><%=aClass.getName()%></option>
            <% } %>
          </select>
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Submit">
      </div>
    </form>
  </div>
</div>

<div id="teacherAssign" class="tabcontent">
  <h3>Teacher Assignment</h3>
  <div class="container">
    <form action="teacher-assignment" method="post">
      <div class="row">
        <div class="col-25">
          <label for="assignedTeacher">Teacher</label>
        </div>
        <div class="col-75">
            <select id="assignedTeacher" name="assignedTeacher">
              <%
                List<Teacher> teachers = repository.loadAllData(Teacher.class);
                for (Teacher teacher : teachers) { %>
              <option value="<%=teacher.getId()%>"><%=teacher.getName()%></option>
              <% } %>
            </select>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="classSubject_">Subject</label>
        </div>
        <div class="col-75">
          <select id="classSubject_" name="classSubject">
            <%
              for (Subject subject : subjects) { %>
            <option value="<%=subject.getId()%>"><%=subject.getSubjectName()%></option>
            <% } %>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="assignedClass_">Class</label>
        </div>
        <div class="col-75">
          <select id="assignedClass_" name="assignedClass">
            <%for (Class aClass : classes) { %>
            <option value="<%=aClass.getId()%>"><%=aClass.getName()%></option>
            <% } %>
          </select>
        </div>
      </div>
      <div class="row">
        <input type="submit" value="Submit">
      </div>
    </form>
  </div>
</div>

<script>
  function openMenu(evt, menu) {
    let i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(menu).style.display = "block";
    evt.currentTarget.className += " active";
  }

  document.getElementById("defaultOpen").click();
</script>

</body>
</html>
