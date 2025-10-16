<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
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
      max-width: 420px;
      width: 100%;
      background: white;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      overflow: hidden;
    }
    
    .header {
      background-color: #2196F3;
      color: white;
      padding: 20px;
      text-align: center;
      margin-bottom: 0;
    }
    
    .header h2 {
      margin: 0;
      font-size: 24px;
    }
    
    .form-container {
      padding: 30px;
    }
    
    .field {
      margin: 15px 0;
    }
    
    label {
      display: block;
      margin-bottom: 6px;
      font-size: 14px;
      font-weight: 500;
      color: #333;
    }
    
    input[type="text"], input[type="password"] {
      width: 100%;
      padding: 12px;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 14px;
      box-sizing: border-box;
      transition: border-color 0.3s ease;
    }
    
    input[type="text"]:focus, input[type="password"]:focus {
      outline: none;
      border-color: #2196F3;
      box-shadow: 0 0 0 2px rgba(33, 150, 243, 0.2);
    }
    
    .row {
      display: flex;
      align-items: center;
      gap: 8px;
      margin: 15px 0;
    }
    
    .row input[type="checkbox"] {
      width: auto;
      margin: 0;
    }
    
    .row label {
      margin: 0;
      font-size: 13px;
      color: #666;
    }
    
    .actions {
      margin-top: 20px;
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
      width: 100%;
    }
    
    .btn-primary {
      background-color: #2196F3;
      color: white;
    }
    
    .btn-primary:hover {
      background-color: #1976D2;
    }
    
    .message {
      padding: 10px;
      margin: 10px 0;
      border-radius: 4px;
    }
    
    .error {
      background-color: #f8d7da;
      color: #721c24;
      border: 1px solid #f5c6cb;
    }
    
    .hint {
      font-size: 13px;
      color: #666;
      margin-top: 15px;
      text-align: center;
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
    }
    
    .nav-links a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="header">
      <h2>🔐 Đăng Nhập</h2>
    </div>
    
    <div class="form-container">
      <c:if test="${not empty error}">
        <div class="message error">
          <strong>❌ Lỗi:</strong> <c:out value="${error}" />
        </div>
      </c:if>

      <form method="post" action="${pageContext.request.contextPath}/login" autocomplete="on">
        <div class="field">
          <label for="username">Tên đăng nhập</label>
          <input id="username" type="text" name="username"
              value="<c:out value='${old_username}'/>"
              required autocomplete="username" 
              placeholder="Nhập tên đăng nhập" />
        </div>

        <div class="field">
          <label for="password">Mật khẩu</label>
          <input id="password" type="password" name="password"
                 value="<c:out value='${old_password}'/>"
                 required autocomplete="current-password" 
                 placeholder="Nhập mật khẩu" />
        </div>

        <div class="field row">
          <input id="remember" type="checkbox" name="remember" value="1"
                 <c:if test="${param.remember != null}">checked</c:if> />
          <label for="remember">Ghi nhớ đăng nhập (30 giây)</label>
        </div>

        <div class="actions">
          <button type="submit" class="btn btn-primary">🚀 Đăng Nhập</button>
        </div>

        <div class="hint">
          Nếu đăng nhập thất bại, tên đăng nhập sẽ được giữ lại để bạn không phải gõ lại.
        </div>
      </form>
      
      <div class="nav-links">
        <a href="${pageContext.request.contextPath}/">🏠 Về trang chủ</a>
      </div>
    </div>
  </div>
</body>
</html>
