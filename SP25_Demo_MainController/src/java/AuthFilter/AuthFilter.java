package AuthFilter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.User;
import java.io.IOException;
import java.util.Set;

public class AuthFilter implements Filter {
    
    // Public paths (không cần đăng nhập)
    private static final Set<String> PUBLIC_PATHS = Set.of(
        "/login", "/logout", "/accessDenied", "/css/", "/js/", "/images/", "/assets/",
        "/", "/index.jsp", "/products", "/productListCart.jsp", "/views/cart/productlistcart.jsp",
        "/carts"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String contextPath = req.getContextPath();
        String requestPath = req.getRequestURI().substring(contextPath.length());
        
        // Cho phép truy cập các đường dẫn public
        boolean isPublicPath = PUBLIC_PATHS.stream().anyMatch(requestPath::startsWith);
        if (isPublicPath) {
            chain.doFilter(request, response);
            return;
        }
        
        // Kiểm tra session và user đã đăng nhập
        HttpSession session = req.getSession(false);
        User authUser = null;
        
        if (session != null) {
            authUser = (User) session.getAttribute("authUser");
            // Cập nhật thời gian session để tránh timeout
            session.setMaxInactiveInterval(120 * 60); // 120 phút = 2 giờ
        }
        
        if (authUser == null) {
            // Chưa đăng nhập -> hiển thị thông báo yêu cầu đăng nhập
            req.getRequestDispatcher("/login-required.jsp").forward(req, resp);
            return;
        }
        
        // User đã đăng nhập có thể truy cập
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup filter
    }
}