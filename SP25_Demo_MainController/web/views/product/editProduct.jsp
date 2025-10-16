<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Edit Product</title></head>
<body>
<h2>Edit Product</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/products" method="post">
  <input type="hidden" name="action" value="update"/>
  <input type="hidden" name="id" value="${product.id}"/>
  <input type="hidden" name="returnPage" value="${param.page != null ? param.page : 1}"/>

  <p>
    <label>Name:</label><br/>
    <input type="text" name="name" required value="${product.name}"/>
  </p>

  <p>
    <label>Price:</label><br/>
    <input type="number" step="0.01" name="price" required value="${product.price}"/>
  </p>

  <p>
    <label>Stock:</label><br/>
    <input type="number" min="0" name="stock" required value="${product.stock}"/>
  </p>

  <p>
    <label>Description:</label><br/>
    <textarea name="description" rows="3" cols="50"><c:out value="${product.description}"/></textarea>
  </p>

  <button type="submit">Save</button>
  <a href="${pageContext.request.contextPath}/products?page=${param.page != null ? param.page : 1}">Cancel</a>
</form>

<div style="margin-bottom:10px;">
  <a href="${pageContext.request.contextPath}/products">
    <button type="button">← Back to Products List</button>
  </a>
</div>
</body>
</html>
