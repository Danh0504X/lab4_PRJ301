<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Product</title>
</head>
<body>
<h2>Create Product</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/products" method="post">
    <input type="hidden" name="action" value="insert"/>

    <p>
        <label>Name:</label><br/>
        <input type="text" name="name" required value="${old_name != null ? old_name : ''}"/>
    </p>

    <p>
        <label>Price:</label><br/>
        <input type="number" step="0.01" name="price" required value="${old_price != null ? old_price : ''}"/>
    </p>

    <p>
        <label>Stock:</label><br/>
        <input type="number" min="0" name="stock" required value="${old_stock != null ? old_stock : ''}"/>
    </p>

    <p>
        <label>Description:</label><br/>
        <textarea name="description" rows="3" cols="50">${old_description != null ? old_description : ''}</textarea>
    </p>

    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/products">Cancel</a>
</form>
<div style="margin-bottom:10px;">
  <a href="${pageContext.request.contextPath}/products">
    <button type="button">← Back to Products List</button>
  </a>
</div>
</body>
</html>
