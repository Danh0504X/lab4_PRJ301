<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Delete Product</title></head>
<body>
<h3>Delete Product</h3>
<p>Bạn có chắc muốn ẩn sản phẩm này khỏi danh sách?</p>

<table border="1" cellpadding="6">
  <tr><th>ID</th><td>${product.id}</td></tr>
  <tr><th>Name</th><td>${product.name}</td></tr>
  <tr><th>Price</th><td>${product.price}</td></tr>
  <tr><th>Description</th><td>${product.description}</td></tr>
  <tr><th>Stock</th><td>${product.stock}</td></tr>
</table>

<form method="post" action="${pageContext.request.contextPath}/products?action=delete">
  <input type="hidden" name="id" value="${product.id}"/>
  <input type="hidden" name="page" value="${param.page}"/>
  <input type="hidden" name="q" value="${param.q}"/>
  <button type="submit">Xác nhận xóa (soft delete)</button>
  <a href="${pageContext.request.contextPath}/products?page=${param.page}&q=${param.q}">Huỷ</a>
</form>
</body>
<div style="margin-bottom:10px;">
  <a href="${pageContext.request.contextPath}/products">
    <button type="button">← Back to Products List</button>
  </a>
</div>
</html>
