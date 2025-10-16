<%-- 
    Document   : login2
    Created on : Sep 24, 2025, 1:32:04 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Logged out</title>
  <meta http-equiv="refresh" content="3;url=${loginUrl}"><!-- tự chuyển sau 3s -->
  <style>
    body { 
      font-family: Arial, sans-serif; 
      margin: 0;
      padding: 20px;
      background-color: #f5f5f5;
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    
    .container {
      max-width: 500px;
      width: 100%;
      background: white;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      overflow: hidden;
    }
    
    .header {
      background-color: #4CAF50;
      color: white;
      padding: 20px;
      text-align: center;
      margin-bottom: 0;
    }
    
    .header h2 {
      margin: 0;
      font-size: 24px;
    }
    
    .content {
      padding: 30px;
      text-align: center;
    }
    
    .message {
      padding: 10px;
      margin: 10px 0;
      border-radius: 4px;
    }
    
    .success {
      background-color: #d4edda;
      color: #155724;
      border: 1px solid #c3e6cb;
      padding: 15px;
      margin-bottom: 20px;
    }
    
    .chip {
      display: inline-block;
      padding: 8px 16px;
      background-color: #4CAF50;
      color: white;
      border-radius: 20px;
      font-weight: 500;
      margin-bottom: 15px;
    }
    
    .btn {
      padding: 12px 24px;
      margin: 4px;
      text-decoration: none;
      border: none;
      cursor: pointer;
      border-radius: 4px;
      font-size: 14px;
      font-weight: 500;
      transition: background-color 0.3s ease;
      display: inline-block;
    }
    
    .btn-primary {
      background-color: #2196F3;
      color: white;
    }
    
    .btn-primary:hover {
      background-color: #1976D2;
    }
    
    .btn-success {
      background-color: #28a745;
      color: white;
    }
    
    .btn-success:hover {
      background-color: #218838;
    }
    
    .hint {
      font-size: 13px;
      color: #666;
      margin-top: 15px;
      line-height: 1.4;
    }
    
    .nav-links {
      text-align: center;
      margin-top: 20px;
    }
    
    .nav-links a {
      color: #2196F3;
      text-decoration: none;
      font-size: 14px;
      margin: 0 10px;
    }
    
    .nav-links a:hover {
      text-decoration: underline;
    }
    
    .countdown {
      font-weight: bold;
      color: #2196F3;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="header">
      <h2>🚪 Đăng Xuất</h2>
    </div>
    
    <div class="content">
      <div class="message success">
        <div class="chip">✅ Thành công</div>
        <h3>Hẹn gặp lại! 👋</h3>
        <p>Phiên đăng nhập của bạn đã kết thúc an toàn.</p>
      </div>
      
      <div class="nav-links">
        <a href="${loginUrl}" class="btn btn-primary">🔐 Đăng nhập lại</a>
        <a href="${pageContext.request.contextPath}/" class="btn btn-success">🏠 Về trang chủ</a>
      </div>
      
      <div class="hint">
        Tự động chuyển về trang đăng nhập sau <span class="countdown">3</span> giây...
      </div>
    </div>
  </div>
</body>
</html>
