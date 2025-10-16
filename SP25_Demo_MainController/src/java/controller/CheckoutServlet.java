package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import service.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private IOrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("authUser");

        // Kiểm tra user đã đăng nhập chưa
        if (u == null) {
            request.setAttribute("error", "Vui lòng đăng nhập để thanh toán!");
            request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
            return;
        }

        Object cartObj = session.getAttribute("cart");
        Map<Integer, CartItem> cart = null;

        if (cartObj instanceof Map<?, ?>) {
            try {
                cart = (Map<Integer, CartItem>) cartObj;
            } catch (ClassCastException e) {
                cart = new HashMap<>();
            }
        }

        if (cart == null || cart.isEmpty()) {
            request.setAttribute("error", "Giỏ hàng trống, không thể thanh toán!");
            request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
            return;
        }

        try {
            // Tính tổng tiền
            double total = 0;
            for (CartItem item : cart.values()) {
                double price = Double.parseDouble(item.getProduct().getPrice());
                total += price * item.getQuantity();
            }

            // Áp dụng mã giảm giá
            String promoCode = "SALE10";
            double discount = total * 0.1;
            double finalTotal = total - discount;

            // Tạo order
            Order order = new Order();
            order.setUserId(u.getId());
            order.setTotalPrice(finalTotal);
            order.setStatus("Completed");
            order.setPromoCode(promoCode);

            // Tạo order trong database
            orderService.create(order);

            // Lấy order ID vừa tạo (cần implement method để lấy ID)
            // Tạm thời sử dụng cách khác
            Order createdOrder = orderService.getOrdersByUser(u.getId()).get(0);
            int orderId = createdOrder.getId();

            // Thêm order details
            for (CartItem item : cart.values()) {
                double price = Double.parseDouble(item.getProduct().getPrice());
                orderService.addOrderDetail(orderId, Integer.parseInt(item.getProduct().getId()), 
                                          item.getQuantity(), price);
            }

            // Xóa giỏ hàng sau khi thanh toán thành công
            session.removeAttribute("cart");

            // Gửi email xác nhận (tạm thời comment)
            String subject = "Xác nhận đơn hàng #" + orderId;
            String message = "Xin chào " + u.getUsername() + ",\n\n"
                    + "Cảm ơn bạn đã đặt hàng tại cửa hàng của chúng tôi.\n"
                    + "Mã đơn hàng: #" + orderId + "\n"
                    + "Tổng tiền gốc: " + total + " VND\n"
                    + "Giảm giá (" + promoCode + "): " + discount + " VND\n"
                    + "Tổng tiền thanh toán: " + finalTotal + " VND\n\n"
                    + "Đơn hàng của bạn đang được xử lý.\n\nTrân trọng!";
            // EmailUtil.sendMail(u.getEmail(), subject, message);

            // Chuyển hướng đến trang thành công
            request.setAttribute("orderId", orderId);
            request.setAttribute("total", finalTotal);
            request.setAttribute("discount", discount);
            request.setAttribute("originalTotal", total);
            request.getRequestDispatcher("/views/cart/success.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đặt hàng thất bại, vui lòng thử lại!");
            request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
        }
    }
}
