package controller;

import model.Product;
import service.IProductService;
import service.ProductServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {

    private IProductService productService;

    @Override
    public void init() {
        productService = new ProductServiceImpl(); // chỉ dùng Service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showCreateForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listProducts(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "insert":
                insertProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/products");
        }
    }

    // ====== handlers ======

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productService.getAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/product/productList.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/product/createProduct.jsp").forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = trimOrNull(request.getParameter("name"));
        String price = trimOrNull(request.getParameter("price"));
        String description = trimOrNull(request.getParameter("description"));
        String stock = trimOrNull(request.getParameter("stock"));

        if (name == null || price == null || stock == null) {
            request.setAttribute("error", "Vui lòng nhập đủ Name, Price, Stock.");
            request.setAttribute("old_name", name);
            request.setAttribute("old_price", price);
            request.setAttribute("old_description", description);
            request.setAttribute("old_stock", stock);
            showCreateForm(request, response);
            return;
        }

        try {
            Product p = new Product(name, price, description, stock, (LocalDateTime) null);
            productService.create(p);
            response.sendRedirect(request.getContextPath() + "/products?msg=created");
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi thêm: " + e.getMessage());
            showCreateForm(request, response);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idRaw = request.getParameter("id");
        String page = request.getParameter("page");
        if (idRaw == null) {
            response.sendRedirect(request.getContextPath() + "/products?err=missing_id");
            return;
        }
        try {
            int id = Integer.parseInt(idRaw);
            boolean ok = productService.delete(id);
            String qs = ok ? "msg=deleted" : "err=delete_failed";
            if (page != null) qs += "&page=" + page;
            response.sendRedirect(request.getContextPath() + "/products?" + qs);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/products?err=" + e.getMessage());
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("id");
        if (idRaw == null) {
            response.sendRedirect(request.getContextPath() + "/products?err=missing_id");
            return;
        }
        try {
            int id = Integer.parseInt(idRaw);
            Product p = productService.getById(id);
            if (p == null) {
                response.sendRedirect(request.getContextPath() + "/products?err=not_found");
                return;
            }
            request.setAttribute("product", p);
            request.getRequestDispatcher("/WEB-INF/product/editProduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/products?err=" + e.getMessage());
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        String description = request.getParameter("description");
        String returnPage = request.getParameter("returnPage");

        if (id == null || name == null || price == null || stock == null) {
            request.setAttribute("error", "Thiếu dữ liệu bắt buộc.");
            showEditForm(request, response);
            return;
        }

        try {
            Product p = new Product();
            p.setId(id);
            p.setName(name.trim());
            p.setPrice(price.trim());
            p.setStock(stock.trim());
            p.setDescription(description != null ? description.trim() : null);

            boolean ok = productService.update(p);
            String pageQs = (returnPage != null && !returnPage.isBlank()) ? ("&page=" + returnPage) : "";
            response.sendRedirect(request.getContextPath() + "/products?msg=" + (ok ? "updated" : "update_failed") + pageQs);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi cập nhật: " + e.getMessage());
            showEditForm(request, response);
        }
    }

    private String trimOrNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
}
