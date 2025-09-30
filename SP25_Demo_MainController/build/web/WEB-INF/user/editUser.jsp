<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>Edit User</title>
</head>
<body>
<h2>Edit User</h2>

<form action="${pageContext.request.contextPath}/users" method="post">
  <input type="hidden" name="action" value="update"/>
  <input type="hidden" name="id" value="${user.id}"/>
  <input type="hidden" name="page" value="${param.page}"/>

  <table>
    <tr>
      <td>ID:</td>
      <td><strong>${user.id}</strong></td>
    </tr>
    <tr>
      <td>Username:</td>
      <td><input type="text" name="username" value="${user.username}" required/></td>
    </tr>
    <tr>
      <td>Email:</td>
      <td><input type="email" name="email" value="${user.email}" required/></td>
    </tr>
    <tr>
      <td>Country:</td>
      <td><input type="text" name="country" value="${user.country}"/></td>
    </tr>
    <tr>
      <td>Role:</td>
      <td><input type="text" name="role" value="${user.role}"/></td>
    </tr>
    <tr>
      <td>Status:</td>
      <td>
        <input type="checkbox" name="status" value="true" <c:if test="${user.status}">checked</c:if>/> Active
      </td>
    </tr>
    <tr>
      <td>Password:</td>
      <td>
        <!-- Tùy yêu cầu: có thể cho phép đổi pass hoặc để trống = giữ nguyên -->
        <input type="password" name="password" value="${user.password}"/>
      </td>
    </tr>
  </table>

  <div style="margin-top:10px;">
    <button type="submit">Update</button>
    <a href="${pageContext.request.contextPath}/users?page=${param.page}">Cancel</a>
  </div>
</form>
</body>
</html>
