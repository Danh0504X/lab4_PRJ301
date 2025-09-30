<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>Create User</title>
</head>
<body>
<h2>Create User</h2>

<form action="${pageContext.request.contextPath}/users" method="post">
  <input type="hidden" name="action" value="insert"/>

  <table>
    <tr>
      <td>Username:</td>
      <td><input type="text" name="username" required/></td>
    </tr>
    <tr>
      <td>Email:</td>
      <td><input type="email" name="email" required/></td>
    </tr>
    <tr>
      <td>Country:</td>
      <td><input type="text" name="country"/></td>
    </tr>
    <tr>
      <td>Role:</td>
      <td><input type="text" name="role"/></td>
    </tr>
    <tr>
      <td>Status:</td>
      <td>
        <!-- checkbox gửi "true" nếu check -->
        <input type="checkbox" name="status" value="true"/> Active
      </td>
    </tr>
    <tr>
      <td>Password:</td>
      <td><input type="password" name="password" required/></td>
    </tr>
  </table>

  <div style="margin-top:10px;">
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/users">Cancel</a>
  </div>
</form>
</body>
</html>
