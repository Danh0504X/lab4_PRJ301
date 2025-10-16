<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ Hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        .update-btn {
            background-color: #2196F3;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        .update-btn:hover {
            background-color: #1976D2;
        }
        .remove-btn {
            background-color: #f44336;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        .remove-btn:hover {
            background-color: #d32f2f;
        }
        .checkout-btn {
            background-color: #4CAF50;
            color: white;
            padding: 15px 30px;
            text-decoration: none;
            border-radius: 6px;
            display: inline-block;
            font-size: 16px;
            font-weight: bold;
            margin-top: 10px;
        }
        .checkout-btn:hover {
            background-color: #45a049;
        }
        .shopping-btn {
            background-color: #2196F3;
            color: white;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 6px;
            display: inline-block;
            font-size: 16px;
        }
        .shopping-btn:hover {
            background-color: #1976D2;
        }
        .item-total {
            font-size: 18px;
            font-weight: bold;
            color: #4CAF50;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🛒 Giỏ Hàng Của Bạn</h1>
            <p>Quản lý sản phẩm trong giỏ hàng</p>
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
                <a href="${pageContext.request.contextPath}/carts?action=list">🛍️ Tiếp Tục Mua Sắm</a>
                <a href="${pageContext.request.contextPath}/products" class="secondary">📦 Quản Lý Sản Phẩm</a>
            </div>


    <!-- Debug: Cart size = ${cart.size()} -->
    <c:choose>
        <c:when test="${empty cart}">
            <div class="empty-state">
                <h3>Giỏ hàng của bạn đang trống</h3>
                <p>Hãy thêm một số sản phẩm yêu thích vào giỏ hàng!</p>
                <a href="${pageContext.request.contextPath}/carts?action=list" class="shopping-btn">Bắt đầu mua sắm</a>
            </div>
        </c:when>
        <c:otherwise>
            <c:set var="validItems" value="0"/>
            <c:forEach var="entry" items="${cart}">
                <c:set var="item" value="${entry.value}"/>
                <!-- Debug: item = ${item} -->
                <c:if test="${not empty item}">
                    <c:set var="product" value="${item.product}"/>
                    <c:if test="${not empty product}">
                        <c:if test="${not empty product.name and not empty product.price}">
                    <c:set var="validItems" value="${validItems + 1}"/>
                    <div class="cart-item">
                        <div class="item-info">
                            <h3>${product.name}</h3>
                            <p class="price">Giá: ${product.price} VND</p>
                            <c:if test="${not empty product.description}">
                                <p>${product.description}</p>
                            </c:if>
                            <p><strong>Số lượng:</strong> ${item.quantity}</p>
                            <c:set var="price" value="${product.price}"/>
                            <c:set var="quantity" value="${item.quantity}"/>
                            <p class="item-total">Thành tiền: <c:out value="${price * quantity}"/> VND</p>
                    </div>
                    <div class="item-controls">
                        <form action="${pageContext.request.contextPath}/carts" method="get" style="display: inline;">
                            <input type="hidden" name="action" value="update"/>
                            <input type="hidden" name="productId" value="${product.id}"/>
                            <input type="hidden" name="from" value="cart"/>
                            <input type="number" name="quantity" value="${item.quantity}" min="1" max="${product.stock}" class="quantity-input"/>
                            <button type="submit" class="update-btn">✏️ Cập nhật</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/carts" method="get" style="display: inline;">
                            <input type="hidden" name="action" value="remove"/>
                            <input type="hidden" name="productId" value="${product.id}"/>
                            <input type="hidden" name="from" value="cart"/>
                            <button type="submit" class="remove-btn">🗑️ Xóa</button>
                        </form>
                    </div>
                </div>
                        </c:if>
                    </c:if>
                </c:if>
            </c:forEach>
            
            <c:if test="${validItems == 0}">
                <div class="empty-state">
                    <h3>Giỏ hàng không có sản phẩm hợp lệ</h3>
                    <p>Vui lòng thêm sản phẩm mới vào giỏ hàng!</p>
                    <a href="${pageContext.request.contextPath}/carts?action=list" class="shopping-btn">Bắt đầu mua sắm</a>
                </div>
            </c:if>
            
            <c:if test="${validItems > 0}">
            <div class="total-section">
                <c:set var="total" value="0"/>
                <c:forEach var="entry" items="${cart}">
                    <c:set var="item" value="${entry.value}"/>
                    <c:if test="${not empty item}">
                        <c:set var="product" value="${item.product}"/>
                        <c:if test="${not empty product and not empty product.price}">
                            <c:set var="itemPrice" value="${product.price}"/>
                            <c:set var="itemQty" value="${item.quantity}"/>
                            <c:set var="total" value="${total + (itemPrice * itemQty)}"/>
                        </c:if>
                    </c:if>
                </c:forEach>
                <div class="total">
                    Tổng cộng: <c:out value="${total}"/> VND
                </div>
                
                <form action="${pageContext.request.contextPath}/checkout" method="post" style="display: inline;">
                    <button type="submit" class="checkout-btn">💳 Tiến hành thanh toán</button>
                </form>
            </div>
            </c:if>
        </c:otherwise>
    </c:choose>
        </div>
    </div>
</body>
</html>