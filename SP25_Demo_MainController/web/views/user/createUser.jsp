<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <meta charset="UTF-8">
  <title>Tạo Người Dùng Mới</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
  <div class="container">
    <div class="header">
      <h1>👤 Tạo Người Dùng Mới</h1>
      <p>Thêm tài khoản người dùng mới vào hệ thống</p>
    </div>
    
    <div class="content">

      <!-- Hiển thị thông báo -->
      <c:if test="${not empty message}">
        <div class="message success">
          <strong>✅ Thành công:</strong> ${message}
        </div>
      </c:if>
      
      <c:if test="${not empty error}">
        <div class="message error">
          <strong>❌ Lỗi:</strong> ${error}
        </div>
      </c:if>

      <form action="${pageContext.request.contextPath}/users" method="post" class="card">
        <div class="card-header">
          <h3>Thông tin người dùng</h3>
        </div>
        <div class="card-body">
          <input type="hidden" name="action" value="insert"/>

          <div class="form-group">
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" class="form-control" required placeholder="Nhập tên đăng nhập"/>
          </div>

          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required placeholder="Nhập email"/>
          </div>

          <div class="form-group">
            <label for="country">Quốc gia:</label>
            <input type="text" id="country" name="country" class="form-control" placeholder="Nhập quốc gia"/>
          </div>

          <div class="form-group">
            <label for="role">Vai trò:</label>
            <select id="role" name="role" class="form-control" required>
              <option value="">-- Chọn vai trò --</option>
              <option value="User">User</option>
              <option value="Admin">Admin</option>
              <option value="Moderator">Moderator</option>
            </select>
          </div>

          <div class="form-group">
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" class="form-control" required placeholder="Nhập mật khẩu"/>
          </div>

          <div class="form-group">
            <label>Trạng thái:</label>
            <div>
              <input type="checkbox" id="status" name="status" value="true"/>
              <label for="status">Hoạt động</label>
            </div>
          </div>
        </div>
        <div class="card-footer">
          <button type="submit" class="btn btn-success">✅ Tạo Người Dùng</button>
          <a href="${pageContext.request.contextPath}/users" class="btn btn-secondary">❌ Hủy</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
