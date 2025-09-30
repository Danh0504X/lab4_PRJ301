<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home</title>
  <style>
    :root{
      --primary:#6C5CE7; --accent:#00CEC9; --warn:#FF7675;
      --bg:#0f172a; --text:#e2e8f0;
    }
    *{box-sizing:border-box}
    body{
      margin:0; min-height:100vh; color:var(--text);
      font-family: "Segoe UI", Roboto, Arial, sans-serif;
      background:
        radial-gradient(1200px 600px at 10% 10%, #1e293b 0, #0f172a 60%),
        linear-gradient(135deg, #0ea5e9 0%, #7c3aed 100%);
    }
    .container{max-width:1000px; margin:40px auto; padding:0 16px;}
    .card{
      background:rgba(255,255,255,.07);
      border:1px solid rgba(255,255,255,.12);
      border-radius:16px; padding:20px;
      backdrop-filter: blur(6px);
      box-shadow:0 10px 30px rgba(0,0,0,.25);
    }
    h2{margin:0 0 16px}
    .welcome{display:flex; align-items:center; gap:10px; font-size:18px}
    .badge{
      padding:4px 10px; border-radius:999px;
      background:rgba(255,255,255,.12);
      border:1px solid rgba(255,255,255,.25); font-size:12px;
    }
    .stats{
      display:grid; gap:14px; margin:18px 0;
      grid-template-columns:repeat(auto-fit,minmax(220px,1fr));
    }
    .stat{padding:18px; border-radius:14px; color:#0b1220; font-weight:600}
    .stat .num{font-size:32px; line-height:1.1; margin-top:4px}
    .stat.users{background:linear-gradient(135deg,#a5b4fc,#60a5fa)}
    .stat.products{background:linear-gradient(135deg,#fbcfe8,#f472b6)}
    .nav{display:flex; gap:12px; flex-wrap:wrap; margin-top:16px}
    .nav a{
      display:inline-block; padding:10px 16px; border-radius:999px;
      text-decoration:none; color:var(--text);
      background:rgba(255,255,255,.10);
      border:1px solid rgba(255,255,255,.20);
      transition:transform .15s ease, background .2s ease, box-shadow .2s ease;
    }
    .nav a:hover{
      transform:translateY(-1px);
      background:rgba(255,255,255,.18);
      box-shadow:0 8px 20px rgba(0,0,0,.25);
    }
    .footer{opacity:.8; font-size:12px; margin-top:16px}
  </style>
</head>
<body>
  <div class="container">
    <div class="card">
      <div class="welcome">
        <h2>Home</h2>
        <span class="badge">Xin chào, <strong>${sessionScope.authUser.username}</strong></span>
      </div>

      <div class="stats">
        <div class="stat users">
          <div>Users</div>
          <div class="num">${userCount}</div>
        </div>
        <div class="stat products">
          <div>Products</div>
          <div class="num">${productCount}</div>
        </div>
      </div>

      <div class="nav">
        <a href="${pageContext.request.contextPath}/users">Quản lý Users</a>
        <a href="${pageContext.request.contextPath}/products">Quản lý Products</a>
        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
      </div>

      <div class="footer">
        © <c:out value="${pageContext.request.contextPath}"/> • secured area
      </div>
    </div>
  </div>
</body>
</html>
