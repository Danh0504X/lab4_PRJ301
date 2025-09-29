<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>Product List</title>
</head>
<body>
<h2>Product List</h2>
<!-- Button Create Product -->
<div style="margin-bottom:10px;">
  <a href="${pageContext.request.contextPath}/products?action=new">
    <button type="button">+ Create Product</button>
  </a>
</div>
<c:set var="pageSize" value="10"/>
<c:set var="currentPage" value="${param.page != null ? param.page : 1}"/>
<c:set var="start" value="${(currentPage - 1) * pageSize}"/>
<c:set var="end" value="${start + pageSize}"/>
<c:set var="totalProducts" value="${products.size()}"/>
<!-- totalPages = (totalProducts + pageSize - 1) / pageSize -->
<c:set var="totalPages" value="${(totalProducts + pageSize - 1) / pageSize}"/>

<table border="1" cellpadding="6" cellspacing="0">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Description</th>
    <th>Stock</th>
    <th>Action</th>
  </tr>

  <c:forEach var="product" items="${products}" varStatus="status">
    <c:if test="${status.index >= start && status.index < end}">
      <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.description}</td>
        <td>${product.stock}</td>
        <td>
          <!-- GIỮ tham số page để quay lại đúng trang sau khi sửa/xóa -->
          <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}&page=${currentPage}">
            Edit
          </a>
          &nbsp;|&nbsp;
          <a href="${pageContext.request.contextPath}/products?action=delete&id=${product.id}&page=${currentPage}"
             onclick="return confirm('Xóa sản phẩm ID=${product.id}?');">
            Delete
          </a>
        </td>
      </tr>
    </c:if>
  </c:forEach>
</table>

<!-- Phân trang -->
<div style="margin-top:8px;">
  <c:if test="${currentPage > 1}">
    <a href="${pageContext.request.contextPath}/products?page=${currentPage - 1}">Previous</a>
  </c:if>

  <c:forEach var="i" begin="1" end="${totalPages}">
    <a href="${pageContext.request.contextPath}/products?page=${i}">${i}</a>
  </c:forEach>

  <c:if test="${currentPage < totalPages}">
    <a href="${pageContext.request.contextPath}/products?page=${currentPage + 1}">Next</a>
  </c:if>
</div>
</body>
</html>
