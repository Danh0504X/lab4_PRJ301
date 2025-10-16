<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanh toán thành công</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 20px;
            background-color: #f5f5f5;
        }
        .success-container {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-align: center;
        }
        .success-icon {
            font-size: 60px;
            color: #4CAF50;
            margin-bottom: 20px;
        }
        .success-title {
            color: #4CAF50;
            font-size: 28px;
            margin-bottom: 20px;
        }
        .order-info {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0;
            text-align: left;
        }
        .order-info h3 {
            color: #333;
            margin-bottom: 15px;
        }
        .info-row {
            display: flex;
            justify-content: space-between;
            margin: 10px 0;
            padding: 8px 0;
            border-bottom: 1px solid #eee;
        }
        .info-label {
            font-weight: bold;
            color: #666;
        }
        .info-value {
            color: #333;
        }
        .total-row {
            background-color: #e8f5e8;
            font-weight: bold;
            font-size: 18px;
            color: #4CAF50;
            margin-top: 15px;
            padding: 15px;
            border-radius: 5px;
        }
        .action-buttons {
            margin-top: 30px;
        }
        .btn {
            display: inline-block;
            padding: 12px 24px;
            margin: 0 10px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .btn-primary {
            background-color: #4CAF50;
            color: white;
        }
        .btn-primary:hover {
            background-color: #45a049;
        }
        .btn-secondary {
            background-color: #2196F3;
            color: white;
        }
        .btn-secondary:hover {
            background-color: #1976D2;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <div class="success-icon">✅</div>
        <h1 class="success-title">Đặt hàng thành công!</h1>
        <p style="color: #666; font-size: 16px; margin-bottom: 30px;">
            Cảm ơn bạn đã mua sắm tại cửa hàng của chúng tôi!
        </p>
        
        <div class="order-info">
            <h3>📋 Thông tin đơn hàng</h3>
            <div class="info-row">
                <span class="info-label">Mã đơn hàng:</span>
                <span class="info-value">#${orderId}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Tổng tiền gốc:</span>
                <span class="info-value">${originalTotal} VND</span>
            </div>
            <div class="info-row">
                <span class="info-label">Giảm giá (SALE10):</span>
                <span class="info-value">-${discount} VND</span>
            </div>
            <div class="total-row">
                <span class="info-label">Tổng tiền thanh toán:</span>
                <span class="info-value">${total} VND</span>
            </div>
        </div>
        
        <div style="background-color: #e3f2fd; padding: 15px; border-radius: 8px; margin: 20px 0;">
            <p style="margin: 0; color: #1976d2;">
                <strong>📧 Email xác nhận đã được gửi đến hộp thư của bạn!</strong><br>
                Đơn hàng của bạn đang được xử lý và sẽ được giao trong thời gian sớm nhất.
            </p>
        </div>
        
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/carts?action=list" class="btn btn-secondary">🛍️ Tiếp tục mua sắm</a>
        </div>
    </div>
</body>
</html>