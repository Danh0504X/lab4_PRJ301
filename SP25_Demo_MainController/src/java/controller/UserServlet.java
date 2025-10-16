package controller;

import userDAO.UserDao;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import service.IUserService;
import service.UserServiceImpl;

@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {
     private IUserService userService;
    @Override
    public void init() {
         userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteUser(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            default:
                listUsers(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
            case "insert": // hỗ trợ từ form createUser.jsp
                createUser(req, resp);
                break;
            case "edit":
            case "update": // hỗ trợ từ form editUser.jsp
                updateUser(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/users");
        }
    }
        
    private void listUsers(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> list = userService.findAll();
        req.setAttribute("listUser", list);
        req.getRequestDispatcher("/views/user/listUser.jsp").forward(req, resp);
    }
    
    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/createUser.jsp").forward(req, resp);
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
    User u = new User();
    String username = req.getParameter("username");
    if (username == null) username = req.getParameter("name");
    u.setUsername(username);
    u.setEmail(req.getParameter("email"));
    u.setCountry(req.getParameter("country"));
    u.setRole(req.getParameter("role") != null ? req.getParameter("role") : "user");

    String statusParam = req.getParameter("status");
    u.setStatus("on".equals(statusParam) || "1".equals(statusParam) || "true".equalsIgnoreCase(statusParam));
    u.setPassword(req.getParameter("password") != null ? req.getParameter("password") : "abc@123");

    // 🚨 Kiểm tra username đã tồn tại chưa
    if (userService.existsByUsername(username)) {
        req.setAttribute("error", "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác.");
        req.setAttribute("old_user", u); // để hiển thị lại dữ liệu trên form
        req.getRequestDispatcher("/views/users/create.jsp").forward(req, resp);
        return;
    }

    userService.create(u);
    resp.sendRedirect(req.getContextPath() + "/users");
}

    
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.findById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/user/editUser.jsp").forward(req, resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User u = new User();
        u.setId(Integer.parseInt(req.getParameter("id")));
        // đồng bộ tham số
        String username = req.getParameter("username");
        if (username == null) username = req.getParameter("name");
        u.setUsername(username);
        u.setEmail(req.getParameter("email"));
        u.setCountry(req.getParameter("country"));
        u.setRole(req.getParameter("role"));
        String statusParam = req.getParameter("status");
        u.setStatus("on".equals(statusParam) || "1".equals(statusParam) || "true".equalsIgnoreCase(statusParam));
        u.setPassword(req.getParameter("password"));

        userService.update(u);
        resp.sendRedirect(req.getContextPath() + "/users");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/users");
    }
    
    private void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String q = req.getParameter("q");
        List<User> list = userService.searchByKeyword(q);
        req.setAttribute("listUser", list);
        req.getRequestDispatcher("/views/user/listUser.jsp").forward(req, resp);
    }
}
