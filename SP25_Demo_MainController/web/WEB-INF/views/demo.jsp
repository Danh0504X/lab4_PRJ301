<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.User" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Demo Users</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    h1 { margin-top: 0; }
    form .row { display:flex; gap:12px; margin-bottom:8px; flex-wrap: wrap; }
    input[type=text], input[type=email], input[type=password], input[type=date] { padding:6px; width:220px; }
    table { width:100%; border-collapse: collapse; margin-top:16px; }
    th, td { border: 1px solid #ddd; padding: 8px; }
    th { background:#f5f5f5; }
    .btn { padding:6px 10px; background:#1976d2; color:#fff; text-decoration:none; border-radius:4px; }
    .btn-danger { background:#d32f2f; }
    .note { color:#555; font-size: 0.92rem; }
    .err { color:#d32f2f; margin-bottom:10px; }
  </style>
</head>
<body>
  <h1>Demo Users</h1>
  <p class="note">Form dưới đây thêm nhanh 1 user; danh sách bên dưới có nút Delete.</p>

  <%
    String error = (String) request.getAttribute("error");
    if (error != null) {
  %>
    <div class="err"><%= error %></div>
  <% } %>

  <form method="post" action="<%=request.getContextPath()%>/demo">
    <div class="row">
      <input type="text"     name="username" placeholder="Username" required/>
      <input type="email"    name="email"    placeholder="Email" required/>
      <input type="text"     name="country"  placeholder="Country"/>
      <input type="text"     name="role"     placeholder="Role (mặc định: User)"/>
      <input type="password" name="password" placeholder="Password" required/>
      <input type="date"     name="dob"      placeholder="DOB (YYYY-MM-DD)"/>
      <label style="display:flex;align-items:center;gap:6px;">
        <input type="checkbox" name="status" checked/> Active
      </label>
    </div>
    <button class="btn" type="submit">Add</button>
  </form>

  <table>
    <tr>
      <th>ID</th><th>Username</th><th>Email</th><th>Country</th>
      <th>Role</th><th>Status</th><th>DOB</th><th>Action</th>
    </tr>
    <%
      List<User> users = (List<User>) request.getAttribute("users");
      if (users != null) {
        for (User u : users) {
    %>
      <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getUsername() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getCountry() %></td>
        <td><%= u.getRole() %></td>
        <td><%= u.isStatus() ? "Active" : "Inactive" %></td>
        <td><%= u.getDob() != null ? u.getDob() : "" %></td>
        <td>
          <a class="btn btn-danger"
             href="<%=request.getContextPath()%>/demo?deleteId=<%=u.getId()%>"
             onclick="return confirm('Delete user #<%=u.getId()%>?');">Delete</a>
        </td>
      </tr>
    <%
        }
      }
    %>
  </table>
</body>
</html>
