<%-- 
    Document   : accessDenied
    Created on : Oct 1, 2025, 2:38:59 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Truy Cập Bị Từ Chối</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        .error-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            text-align: center;
            max-width: 500px;
        }
        .error-icon {
            font-size: 64px;
            color: #f44336;
            margin-bottom: 20px;
        }
        .error-title {
            color: #f44336;
            font-size: 24px;
            margin-bottom: 15px;
        }
        .error-message {
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
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-icon">🚫</div>
        <h2 class="error-title">Truy Cập Bị Từ Chối</h2>
        <p class="error-message">
            Xin lỗi, bạn không có quyền truy cập vào trang này.<br>
            Vui lòng đăng nhập với tài khoản có quyền phù hợp hoặc liên hệ quản trị viên.
        </p>
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/carts" class="btn btn-primary">🛍️ Về Cửa Hàng</a>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-success">🔐 Đăng Nhập</a>
        </div>
    </div>
</body>
</html>
