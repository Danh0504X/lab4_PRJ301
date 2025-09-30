<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>User List</title>
</head>
<body>
<h2>User List</h2>

<!-- Button Create User -->
<div style="margin-bottom:10px;">
  <a href="${pageContext.request.contextPath}/users?action=create">
    <button type="button">+ Create User</button>
  </a>
</div>
<!-- Search box -->
<form method="get" action="${pageContext.request.contextPath}/users" style="margin:8px 0;">
  <input type="hidden" name="action" value="search"/>
  <input type="text" name="q" value="${param.q}" placeholder="Search by username/email/country" size="40"/>
  <button type="submit">Search</button>
  <a href="${pageContext.request.contextPath}/users" style="margin-left:8px;">Reset</a>
</form>
<!-- Phân trang giống Product -->
<c:set var="pageSize" value="10"/>
<c:set var="currentPage" value="${param.page != null ? param.page : 1}"/>
<c:set var="start" value="${(currentPage - 1) * pageSize}"/>
<c:set var="end" value="${start + pageSize}"/>
<c:set var="totalUsers" value="${listUser != null ? listUser.size() : 0}"/>
<!-- totalPages = (totalUsers + pageSize - 1) / pageSize -->
<c:set var="totalPages" value="${(totalUsers + pageSize - 1) / pageSize}"/>

<table border="1" cellpadding="6" cellspacing="0">
  <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Email</th>
    <th>Country</th>
    <th>Role</th>
    <th>Status</th>
    <th>Action</th>
  </tr>

  <c:forEach var="u" items="${listUser}" varStatus="st">
    <c:if test="${st.index >= start && st.index < end}">
      <tr>
        <td>${u.id}</td>
        <td>${u.username}</td>
        <td>${u.email}</td>
        <td>${u.country}</td>
        <td>${u.role}</td>
        <td><c:choose>
              <c:when test="${u.status}">Active</c:when>
              <c:otherwise>Inactive</c:otherwise>
            </c:choose>
        </td>
        <td>
          <!-- GIỮ tham số page để quay lại đúng trang sau khi sửa/xóa -->
          <a href="${pageContext.request.contextPath}/users?action=edit&id=${u.id}&page=${currentPage}">
            Edit
          </a>
          &nbsp;|&nbsp;
          <a href="${pageContext.request.contextPath}/users?action=delete&id=${u.id}&page=${currentPage}"
             onclick="return confirm('Delete user ID=${u.id}?');">
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
    <a href="${pageContext.request.contextPath}/users?page=${currentPage - 1}">Previous</a>
  </c:if>

  <c:forEach var="i" begin="1" end="${totalPages}">
    <a href="${pageContext.request.contextPath}/users?page=${i}">${i}</a>
  </c:forEach>

  <c:if test="${currentPage < totalPages}">
    <a href="${pageContext.request.contextPath}/users?page=${currentPage + 1}">Next</a>
  </c:if>
</div>
</body>
</html>
