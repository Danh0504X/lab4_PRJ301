<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <meta charset="UTF-8">
  <title>Quản Lý Người Dùng</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
  <div class="container">
    <div class="header">
      <h1>👥 Quản Lý Người Dùng</h1>
      <p>Danh sách và quản lý tài khoản người dùng trong hệ thống</p>
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

      <!-- Navigation Links -->
      <div class="nav-links">
        <a href="${pageContext.request.contextPath}/users?action=create" class="btn btn-success">➕ Tạo User Mới</a>
        <a href="${pageContext.request.contextPath}/products" class="btn btn-primary">📦 Quản Lý Sản Phẩm</a>
        <a href="${pageContext.request.contextPath}/carts?action=list" class="btn btn-info">🛍️ Mua Sắm</a>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">🚪 Đăng Xuất</a>
      </div>

      <!-- Search Form -->
      <div class="search-form">
        <form method="get" action="${pageContext.request.contextPath}/users">
          <input type="hidden" name="action" value="search"/>
          <input type="text" name="q" value="${param.q}" placeholder="Tìm kiếm theo username/email/country" class="form-control" style="width: 300px; display: inline-block;"/>
          <button type="submit" class="btn btn-primary">🔍 Tìm Kiếm</button>
          <a href="${pageContext.request.contextPath}/users" class="btn btn-secondary">🔄 Reset</a>
        </form>
      </div>
<!-- Phân trang giống Product -->
<c:set var="pageSize" value="10"/>
<c:set var="currentPage" value="${param.page != null ? param.page : 1}"/>
<c:set var="start" value="${(currentPage - 1) * pageSize}"/>
<c:set var="end" value="${start + pageSize}"/>
<c:set var="totalUsers" value="${listUser != null ? listUser.size() : 0}"/>
<!-- totalPages = (totalUsers + pageSize - 1) / pageSize -->
<c:set var="totalPages" value="${(totalUsers + pageSize - 1) / pageSize}"/>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Tên Đăng Nhập</th>
            <th>Email</th>
            <th>Quốc Gia</th>
            <th>Vai Trò</th>
            <th>Trạng Thái</th>
            <th>Thao Tác</th>
          </tr>
        </thead>
        <tbody>

  <c:forEach var="u" items="${listUser}" varStatus="st">
    <c:if test="${st.index >= start && st.index < end}">
      <tr>
        <td>${u.id}</td>
        <td>${u.username}</td>
        <td>${u.email}</td>
        <td>${u.country}</td>
        <td>${u.role}</td>
        <td>
          <c:choose>
            <c:when test="${u.status}">
              <span class="btn btn-sm btn-success">✅ Hoạt động</span>
            </c:when>
            <c:otherwise>
              <span class="btn btn-sm btn-danger">❌ Không hoạt động</span>
            </c:otherwise>
          </c:choose>
        </td>
        <td>
          <!-- GIỮ tham số page để quay lại đúng trang sau khi sửa/xóa -->
          <a href="${pageContext.request.contextPath}/users?action=edit&id=${u.id}&page=${currentPage}" class="btn btn-sm btn-primary">
            ✏️ Sửa
          </a>
          <a href="${pageContext.request.contextPath}/users?action=delete&id=${u.id}&page=${currentPage}"
             onclick="return confirm('Xóa user ID=${u.id}?');" class="btn btn-sm btn-danger">
            🗑️ Xóa
          </a>
        </td>
      </tr>
    </c:if>
  </c:forEach>
        </tbody>
      </table>

      <!-- Phân trang -->
      <div class="nav-links mt-4">
        <c:if test="${currentPage > 1}">
          <a href="${pageContext.request.contextPath}/users?page=${currentPage - 1}" class="btn btn-primary">⬅️ Trang Trước</a>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
          <c:choose>
            <c:when test="${i == currentPage}">
              <span class="btn btn-info">${i}</span>
            </c:when>
            <c:otherwise>
              <a href="${pageContext.request.contextPath}/users?page=${i}" class="btn btn-secondary">${i}</a>
            </c:otherwise>
          </c:choose>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
          <a href="${pageContext.request.contextPath}/users?page=${currentPage + 1}" class="btn btn-primary">Trang Sau ➡️</a>
        </c:if>
      </div>
    </div>
  </div>
</body>
</html>
