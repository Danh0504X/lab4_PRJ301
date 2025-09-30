<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Login</title></head>
<body>
<h2>Đăng nhập</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/login">
  <div>
    <label>Username:</label>
    <input type="text" name="username" value="${old_username}" required>
  </div>
  <div>
    <label>Password:</label>
    <input type="password" name="password" required>
  </div>
  <div style="margin-top:8px;">
    <button type="submit">Login</button>
  </div>
</form>
</body>
</html>
