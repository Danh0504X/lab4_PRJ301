<%-- 
    Document   : index
    Created on : Oct 1, 2025, 1:55:31 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Trang Chủ - Cửa Hàng</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    </head>
    <body>
       <% response.sendRedirect(request.getContextPath() + "/carts?action=list"); %>
    </body>
</html>
