package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import service.IUserService;
import service.UserServiceImpl;
import service.IProductService;
import service.ProductServiceImpl;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private IUserService userService;
    private IProductService productService;

    @Override public void init() {
        userService = new UserServiceImpl();
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Lấy user từ session
        User me = (User) req.getSession().getAttribute("authUser");

        // Đếm nhanh (đơn giản: lấy list rồi .size(); tốt hơn thì viết DAO count)
        int userCount = userService.findAll().size();
        int productCount = productService.getAll().size();

        req.setAttribute("userCount", userCount);
        req.setAttribute("productCount", productCount);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
