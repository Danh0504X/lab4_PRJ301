<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Sản Phẩm</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        .add-btn {
            background-color: #4CAF50;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        .add-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🛍️ Cửa Hàng Sản Phẩm</h1>
            <p>Chọn sản phẩm yêu thích và thêm vào giỏ hàng</p>
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

            <div class="nav-links">
        <c:choose>
            <c:when test="${not empty sessionScope.authUser}">
                <!-- User đã đăng nhập -->
                <a href="${pageContext.request.contextPath}/carts?action=view">🛒 Xem Giỏ Hàng</a>
                
                <!-- Phân quyền theo role -->
                <c:choose>
                    <c:when test="${sessionScope.authUser.role == 'Admin' or sessionScope.authUser.role == 'admin'}">
                        <!-- Admin: Quản lý user + sản phẩm + tất cả quyền user -->
                        <a href="${pageContext.request.contextPath}/products">📦 Quản Lý Sản Phẩm</a>
                        <a href="${pageContext.request.contextPath}/users">👥 Quản Lý Tài Khoản</a>
                    </c:when>
                    <c:when test="${sessionScope.authUser.role == 'Moderator' or sessionScope.authUser.role == 'moderator'}">
                        <!-- Moderator: Chỉ quản lý sản phẩm + quyền user -->
                        <a href="${pageContext.request.contextPath}/products">📦 Quản Lý Sản Phẩm</a>
                    </c:when>
                    <c:otherwise>
                        <!-- User thường: Chỉ có quyền mua hàng -->
                    </c:otherwise>
                </c:choose>
                
                <a href="${pageContext.request.contextPath}/logout">🚪 Đăng Xuất</a>
            </c:when>
            <c:otherwise>
                <!-- User chưa đăng nhập -->
                <a href="${pageContext.request.contextPath}/login">🔐 Đăng Nhập</a>
                <a href="${pageContext.request.contextPath}/carts?action=view">🛒 Xem Giỏ Hàng</a>
            </c:otherwise>
        </c:choose>
    </div>

    <c:choose>
        <c:when test="${empty products}">
            <div class="empty-state">
                <h3>Không có sản phẩm nào trong cửa hàng</h3>
                <p>Vui lòng quay lại sau!</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="products-container">
                <c:forEach var="product" items="${products}">
                    <div class="product">
                        <h3>${product.name}</h3>
                        <p class="price">${product.price} VND</p>
                        <c:if test="${not empty product.description}">
                            <p>${product.description}</p>
                        </c:if>
                        <p><strong>Kho:</strong> ${product.stock} sản phẩm</p>

                        <form action="${pageContext.request.contextPath}/carts" method="post">
                            <input type="hidden" name="action" value="add"/>
                            <input type="hidden" name="productId" value="${product.id}"/>
                            <input type="number" name="quantity" value="1" min="1" max="${product.stock}" class="quantity-input"/>
                            <button type="submit" class="add-btn">➕ Thêm vào Giỏ</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
        </div>
    </div>
</body>
</html>
