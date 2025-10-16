package service;

import model.Order;
import java.util.Map;
import java.util.List;

public interface IOrderService {
    // Tạo order mới
    void create(Order order);
    
    // Tạo order với cart items
    void createOrderWithItems(Order order, Map<Integer, Integer> cartItems);
    
    // Lấy order theo ID
    Order findById(int id);
    
    // Lấy tất cả orders
    List<Order> findAll();
    
    // Cập nhật order
    boolean update(Order order);
    
    // Xóa order
    boolean delete(int id);
    
    // Lấy danh sách order theo user ID
    List<Order> getOrdersByUser(int userId);
    
    // Tìm order theo user ID và status
    List<Order> findOrdersByUserAndStatus(int userId, String status);
    
    // Thêm order detail
    void addOrderDetail(int orderId, int productId, int quantity, double price);
}