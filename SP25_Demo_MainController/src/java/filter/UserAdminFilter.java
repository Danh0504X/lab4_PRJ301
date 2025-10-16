package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.User;
import java.io.IOException;

public class UserAdminFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        // Lấy user từ session
        HttpSession session = req.getSession(false);
        User authUser = null;
        
        if (session != null) {
            authUser = (User) session.getAttribute("authUser");
            // Cập nhật thời gian session để tránh timeout
            session.setMaxInactiveInterval(120 * 60); // 120 phút = 2 giờ
        }
        
        if (authUser == null) {
            req.getRequestDispatcher("/login-required.jsp").forward(req, resp);
            return;
        }
        
        String userRole = (authUser.getRole() != null) ? authUser.getRole() : "";
        
        // Chỉ cho phép admin truy cập quản lý user
        if (!"admin".equalsIgnoreCase(userRole)) {
            req.getRequestDispatcher("/admin-required.jsp").forward(req, resp);
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup filter
    }
}
