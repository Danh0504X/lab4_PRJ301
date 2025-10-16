<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <meta charset="UTF-8">
  <title>Quản Lý Sản Phẩm</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
  <div class="container">
    <div class="header">
      <h1>📦 Quản Lý Sản Phẩm</h1>
      <p>Danh sách và quản lý sản phẩm trong hệ thống</p>
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
        <c:if test="${sessionScope.authUser != null && (sessionScope.authUser.role == 'Admin' || sessionScope.authUser.role == 'Moderator')}">
          <a href="${pageContext.request.contextPath}/products?action=new" class="btn btn-success">➕ Tạo Sản Phẩm Mới</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/carts?action=list" class="btn btn-primary">🛍️ Mua Sắm</a>
        <a href="${pageContext.request.contextPath}/carts?action=view" class="btn btn-info">🛒 Xem Giỏ Hàng</a>
        <c:if test="${sessionScope.authUser != null && sessionScope.authUser.role == 'Admin'}">
          <a href="${pageContext.request.contextPath}/users" class="btn btn-warning">👥 Quản Lý User</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">🚪 Đăng Xuất</a>
      </div>
<c:set var="pageSize" value="10"/>
<c:set var="currentPage" value="${param.page != null ? param.page : 1}"/>
<c:set var="start" value="${(currentPage - 1) * pageSize}"/>
<c:set var="end" value="${start + pageSize}"/>
<c:set var="totalProducts" value="${products.size()}"/>
<!-- totalPages = (totalProducts + pageSize - 1) / pageSize -->
<c:set var="totalPages" value="${(totalProducts + pageSize - 1) / pageSize}"/>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Tên Sản Phẩm</th>
            <th>Giá</th>
            <th>Mô Tả</th>
            <th>Kho</th>
            <th>Thao Tác</th>
          </tr>
        </thead>
        <tbody>

  <c:forEach var="product" items="${products}" varStatus="status">
    <c:if test="${status.index >= start && status.index < end}">
      <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.description}</td>
        <td>${product.stock}</td>
        <td>
          <c:if test="${sessionScope.authUser != null && (sessionScope.authUser.role == 'Admin' || sessionScope.authUser.role == 'Moderator')}">
            <!-- GIỮ tham số page để quay lại đúng trang sau khi sửa/xóa -->
            <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}&page=${currentPage}" class="btn btn-sm btn-primary">✏️ Sửa</a>
            <a href="${pageContext.request.contextPath}/products?action=delete&id=${product.id}&page=${currentPage}"
               onclick="return confirm('Xóa sản phẩm ID=${product.id}?');" class="btn btn-sm btn-danger">🗑️ Xóa</a>
          </c:if>
          <!-- Link thêm vào giỏ hàng cho tất cả user -->
          <a href="${pageContext.request.contextPath}/carts?action=add&productId=${product.id}&quantity=1" class="btn btn-sm btn-success">🛒 Thêm vào giỏ</a>
        </td>
      </tr>
    </c:if>
  </c:forEach>
        </tbody>
      </table>

      <!-- Phân trang -->
      <div class="nav-links mt-4">
        <c:if test="${currentPage > 1}">
          <a href="${pageContext.request.contextPath}/products?page=${currentPage - 1}" class="btn btn-primary">⬅️ Trang Trước</a>
        </c:if>
        
        <c:forEach var="i" begin="1" end="${totalPages}">
          <c:choose>
            <c:when test="${i == currentPage}">
              <span class="btn btn-info">${i}</span>
            </c:when>
            <c:otherwise>
              <a href="${pageContext.request.contextPath}/products?page=${i}" class="btn btn-secondary">${i}</a>
            </c:otherwise>
          </c:choose>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
          <a href="${pageContext.request.contextPath}/products?page=${currentPage + 1}" class="btn btn-primary">Trang Sau ➡️</a>
        </c:if>
      </div>
    </div>
  </div>
</body>
</html>
