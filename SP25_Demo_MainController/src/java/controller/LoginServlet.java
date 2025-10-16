 package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import service.IUserService;
import service.UserServiceImpl;
import java.util.Base64;
import java.nio.charset.StandardCharsets;


import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private IUserService userService;

    @Override public void init() { userService = new UserServiceImpl(); }

   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    HttpSession s = req.getSession(false);
    if (s != null && s.getAttribute("authUser") != null) {
        resp.sendRedirect(req.getContextPath() + "/carts?action=list");
        return;
    }

    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("userC".equals(c.getName())) {
                String enc = c.getValue();
                try {
                    // DÙNG getUrlDecoder nếu bạn đã encode bằng getUrlEncoder().withoutPadding()
                    String user = new String(Base64.getUrlDecoder().decode(enc), StandardCharsets.UTF_8);
                    req.setAttribute("old_username", user);
                } catch (IllegalArgumentException ex) {
                    // Nếu decode thất bại — có thể cookie không phải base64 hoặc đã bị thay đổi.
                    // Fallback: dùng giá trị thô (không khuyến nghị)
                    req.setAttribute("old_username", enc);
                }
            } else if ("passC".equals(c.getName())) {
                String enc = c.getValue();
                try {
                    String pass = new String(Base64.getUrlDecoder().decode(enc), StandardCharsets.UTF_8);
                    req.setAttribute("old_password", pass);
                } catch (IllegalArgumentException ex) {
                    // ignore / fallback
                }
            }
        }
    }

    req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String remember = req.getParameter("remember");

    User u = userService.login(username, password);
    if (u == null) {
        req.setAttribute("error", "Sai tài khoản hoặc mật khẩu.");
        req.setAttribute("old_username", username);
        req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        return;
    }

    HttpSession session = req.getSession(true);
    session.setAttribute("authUser", u);
    session.setMaxInactiveInterval(120 * 60); // 120 phút = 2 giờ session timeout

    if (remember != null) {
        // Mã hoá base64 URL-safe (không có padding) để tránh ký tự không hợp lệ trong cookie
        String encUser = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(username.getBytes(StandardCharsets.UTF_8));
        String encPass = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(password.getBytes(StandardCharsets.UTF_8));

        String ctx = req.getContextPath();
        if (ctx == null || ctx.isEmpty()) ctx = "/";

        Cookie userCookie = new Cookie("userC", encUser);
        Cookie passCookie = new Cookie("passC", encPass);

        userCookie.setPath(ctx);
        passCookie.setPath(ctx);

        userCookie.setHttpOnly(true);
        passCookie.setHttpOnly(true);

        // bật secure nếu chạy HTTPS:
        // userCookie.setSecure(true);
        // passCookie.setSecure(true);

        userCookie.setMaxAge(30); // 30 seconds theo yêu cầu
        passCookie.setMaxAge(30);

        resp.addCookie(userCookie);
        resp.addCookie(passCookie);
    } else {
        String ctx = req.getContextPath();
        if (ctx == null || ctx.isEmpty()) ctx = "/";

        Cookie userCookie = new Cookie("userC", "");
        Cookie passCookie = new Cookie("passC", "");
        userCookie.setPath(ctx);
        passCookie.setPath(ctx);
        userCookie.setMaxAge(0);
        passCookie.setMaxAge(0);
        resp.addCookie(userCookie);
        resp.addCookie(passCookie);
    }

    resp.sendRedirect(req.getContextPath() + "/carts?action=list");
}

}
