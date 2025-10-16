<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Yêu Cầu Quyền Admin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .notification-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            text-align: center;
            max-width: 500px;
        }
        .notification-icon {
            font-size: 64px;
            color: #9c27b0;
            margin-bottom: 20px;
        }
        .notification-title {
            color: #9c27b0;
            font-size: 24px;
            margin-bottom: 15px;
        }
        .notification-message {
            color: #666;
            font-size: 16px;
            margin-bottom: 30px;
            line-height: 1.5;
        }
        .action-buttons {
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }
        .btn {
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .btn-primary {
            background-color: #2196F3;
            color: white;
        }
        .btn-primary:hover {
            background-color: #1976D2;
        }
        .btn-secondary {
            background-color: #4CAF50;
            color: white;
        }
        .btn-secondary:hover {
            background-color: #45a049;
        }
        .btn-outline {
            background-color: transparent;
            color: #666;
            border: 2px solid #ddd;
        }
        .btn-outline:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <div class="notification-container">
        <div class="notification-icon">👑</div>
        <h2 class="notification-title">Yêu Cầu Quyền Admin</h2>
        <p class="notification-message">
            <strong>Chức năng này chỉ dành cho quản trị viên!</strong><br><br>
            Bạn cần có quyền admin hoặc moderator để truy cập chức năng quản lý này.<br>
            Vui lòng liên hệ quản trị viên để được cấp quyền phù hợp.
        </p>
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/carts" class="btn btn-primary">🛍️ Về Cửa Hàng</a>
            <a href="${pageContext.request.contextPath}/view-cart" class="btn btn-secondary">🛒 Xem Giỏ Hàng</a>
        </div>
    </div>
</body>
</html>
