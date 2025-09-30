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
    :root{
      --primary:#6C5CE7; --accent:#00CEC9; --warn:#FF7675;
      --bg:#0f172a; --text:#e2e8f0;
    }
    *{box-sizing:border-box}
    body{
      margin:0; min-height:100vh; color:var(--text);
      font-family:"Segoe UI", Roboto, Arial, sans-serif;
      background:
        radial-gradient(1200px 600px at 10% 10%, #1e293b 0, #0f172a 60%),
        linear-gradient(135deg, #0ea5e9 0%, #7c3aed 100%);
      display:flex; align-items:center; justify-content:center; padding:16px;
    }
    .card{
      width:min(680px, 92vw);
      background:rgba(255,255,255,.07);
      border:1px solid rgba(255,255,255,.12);
      border-radius:16px; padding:28px 24px;
      backdrop-filter: blur(6px);
      box-shadow:0 10px 30px rgba(0,0,0,.25);
      text-align:center;
    }
    h2{margin:0 0 8px}
    p{margin:8px 0 18px; opacity:.9}
    .chip{
      display:inline-block; padding:6px 12px; border-radius:999px;
      background:linear-gradient(135deg,#a5b4fc,#60a5fa);
      color:#0b1220; font-weight:600;
      box-shadow:0 6px 16px rgba(0,0,0,.2);
      margin-bottom:14px;
    }
    .btn{
      display:inline-block; padding:10px 16px; border-radius:999px;
      text-decoration:none; color:var(--text);
      background:rgba(255,255,255,.12);
      border:1px solid rgba(255,255,255,.25);
      transition:transform .15s ease, background .2s ease, box-shadow .2s ease;
    }
    .btn:hover{
      transform:translateY(-1px);
      background:rgba(255,255,255,.18);
      box-shadow:0 8px 20px rgba(0,0,0,.25);
    }
    .hint{opacity:.75; font-size:12px; margin-top:10px}
  </style>
</head>
<body>
  <div class="card">
    <div class="chip">Bạn đã đăng xuất</div>
    <h2>Hẹn gặp lại 👋</h2>
    <p>Phiên đăng nhập của bạn đã kết thúc an toàn.</p>
    <p>
      <a class="btn" href="${loginUrl}">⟲ Quay về trang đăng nhập</a>
    </p>
    <div class="hint">Tự chuyển về login sau 3 giây…</div>
  </div>
</body>
</html>
