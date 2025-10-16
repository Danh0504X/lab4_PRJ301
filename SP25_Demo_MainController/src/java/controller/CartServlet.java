package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.CartItem;
import model.Product;
import model.User;
import productDao.productDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/carts")
public class CartServlet extends HttpServlet {

    private productDAO productDAO;

    @Override
    public void init() {
        productDAO = new productDAO();
    }
    
    /**
     * Lấy cart từ session một cách an toàn
     */
    private Map<Integer, CartItem> getCartFromSession(HttpSession session) {
        Map<Integer, CartItem> cart = null;
        try {
            Object cartObj = session.getAttribute("cart");
            if (cartObj instanceof Map) {
                cart = (Map<Integer, CartItem>) cartObj;
                // Kiểm tra xem cart có chứa CartItem hợp lệ không
                if (cart != null) {
                    cart.entrySet().removeIf(entry -> 
                        entry.getValue() == null || 
                        entry.getValue().getProduct() == null ||
                        entry.getValue().getQuantity() <= 0
                    );
                }
            }
        } catch (ClassCastException e) {
            System.out.println("DEBUG: Cart type mismatch, creating new cart");
            cart = new HashMap<>();
        }
        
        if (cart == null) {
            cart = new HashMap<>();
        }
        
        return cart;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "view";

        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = getCartFromSession(session);
        
        // Kiểm tra đăng nhập cho các action cần thiết
        User user = (User) session.getAttribute("authUser");
        if (user == null && !"list".equals(action)) {
            request.setAttribute("error", "Vui lòng đăng nhập để truy cập giỏ hàng!");
            try {
                List<Product> products = productDAO.selectAllProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("/views/cart/productlistcart.jsp").forward(request, response);
            } catch (Exception e) {
                request.getRequestDispatcher("/login-required.jsp").forward(request, response);
            }
            return;
        }

        switch (action) {
            case "list":
                // Hiển thị danh sách sản phẩm
                try {
                    List<Product> products = productDAO.selectAllProducts();
                    request.setAttribute("products", products);
                    request.getRequestDispatcher("/views/cart/productlistcart.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "Không thể tải danh sách sản phẩm!");
                    request.getRequestDispatcher("/views/cart/productlistcart.jsp").forward(request, response);
                }
                break;
            case "view":
                // Hiển thị giỏ hàng
                System.out.println("DEBUG: Cart size = " + cart.size()); // Debug
                for (Map.Entry<Integer, CartItem> entry : cart.entrySet()) {
                    System.out.println("DEBUG: Cart item " + entry.getKey() + " = " + entry.getValue());
                    if (entry.getValue() != null) {
                        System.out.println("DEBUG: CartItem product = " + entry.getValue().getProduct());
                    }
                }
                session.setAttribute("cart", cart);
                request.setAttribute("cart", cart);
                request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
                break;
            case "update":
                // Cập nhật số lượng sản phẩm
                try {
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    
                    if (quantity <= 0) {
                        cart.remove(productId);
                    } else {
                        CartItem item = cart.get(productId);
                        if (item != null) {
                            item.setQuantity(quantity);
                        }
                    }
                    session.setAttribute("cart", cart);
                    request.setAttribute("message", "Đã cập nhật số lượng sản phẩm!");
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Số lượng không hợp lệ!");
                }
                request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
                break;
            case "remove":
                // Xóa sản phẩm khỏi giỏ hàng
                try {
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    cart.remove(productId);
                    session.setAttribute("cart", cart);
                    request.setAttribute("message", "Đã xóa sản phẩm khỏi giỏ hàng!");
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "ID sản phẩm không hợp lệ!");
                }
                request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
                break;
            case "clear":
                // Xóa toàn bộ giỏ hàng
                cart.clear();
                session.setAttribute("cart", cart);
                request.setAttribute("message", "Đã xóa toàn bộ giỏ hàng!");
                request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "add";

        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = getCartFromSession(session);

        if (action.equals("add")) {
            // Kiểm tra user đã đăng nhập chưa
            User user = (User) session.getAttribute("authUser");
            if (user == null) {
                request.setAttribute("error", "Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng!");
                try {
                    List<Product> products = productDAO.selectAllProducts();
                    request.setAttribute("products", products);
                } catch (Exception e) {
                    request.setAttribute("error", "Không thể tải danh sách sản phẩm!");
                }
                request.getRequestDispatcher("/views/cart/productlistcart.jsp").forward(request, response);
                return;
            }
            
            // Kiểm tra quyền truy cập cho moderator
            String userRole = (user.getRole() != null) ? user.getRole() : "";
            if (!"admin".equalsIgnoreCase(userRole) && !"moderator".equalsIgnoreCase(userRole) && !"user".equalsIgnoreCase(userRole)) {
                request.setAttribute("error", "Bạn không có quyền thêm sản phẩm vào giỏ hàng!");
                try {
                    List<Product> products = productDAO.selectAllProducts();
                    request.setAttribute("products", products);
                } catch (Exception e) {
                    request.setAttribute("error", "Không thể tải danh sách sản phẩm!");
                }
                request.getRequestDispatcher("/views/cart/productlistcart.jsp").forward(request, response);
                return;
            }
            
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                if (quantity <= 0) {
                    request.setAttribute("error", "Số lượng phải lớn hơn 0!");
                } else {
                    Product product = productDAO.selectProduct(productId);
                    System.out.println("DEBUG: Product = " + product); // Debug
                    
                    // Validation Product
                    if (product == null) {
                        request.setAttribute("error", "Không tìm thấy sản phẩm!");
                    } else if (product.getName() == null || product.getName().trim().isEmpty()) {
                        request.setAttribute("error", "Thông tin sản phẩm không hợp lệ!");
                    } else if (product.getPrice() == null || product.getPrice().trim().isEmpty()) {
                        request.setAttribute("error", "Giá sản phẩm không hợp lệ!");
                    } else {
                        // Tạo CartItem an toàn
                        CartItem item = cart.get(productId);
                        if (item == null) {
                            item = new CartItem(product, quantity);
                            System.out.println("DEBUG: Created new CartItem = " + item); // Debug
                        } else {
                            // Kiểm tra CartItem hiện tại có hợp lệ không
                            if (item.getProduct() == null) {
                                item = new CartItem(product, quantity);
                                System.out.println("DEBUG: Recreated CartItem with valid product = " + item); // Debug
                            } else {
                                item.setQuantity(item.getQuantity() + quantity);
                                System.out.println("DEBUG: Updated existing CartItem = " + item); // Debug
                            }
                        }
                        cart.put(productId, item);
                        session.setAttribute("cart", cart);
                        request.setAttribute("message", "Đã thêm sản phẩm vào giỏ hàng!");
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Dữ liệu không hợp lệ!");
            }
        }

        // Forward về danh sách sản phẩm với thông báo
        try {
            List<Product> products = productDAO.selectAllProducts();
            request.setAttribute("products", products);
        } catch (Exception e) {
            request.setAttribute("error", "Không thể tải danh sách sản phẩm!");
        }
        request.getRequestDispatcher("/views/cart/productlistcart.jsp").forward(request, response);
    }
}