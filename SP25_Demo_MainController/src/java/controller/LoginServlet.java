package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import service.IUserService;
import service.UserServiceImpl;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private IUserService userService;

    @Override public void init() { userService = new UserServiceImpl(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // nếu đã đăng nhập, đi thẳng home
        HttpSession s = req.getSession(false);
        if (s != null && s.getAttribute("authUser") != null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = userService.login(username, password); // null nếu sai
        if (u == null) {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu, hoặc tài khoản bị khóa.");
            req.setAttribute("old_username", username);
            req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("authUser", u);
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
