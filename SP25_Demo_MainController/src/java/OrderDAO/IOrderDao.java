package OrderDAO;

import model.Order;
import java.util.List;

public interface IOrderDao {
    // Tạo order mới và trả về order ID
    void insertOrder(Order order);
    
    // Thêm chi tiết order (sản phẩm trong order)
    void insertOrderDetails(int orderId, int productId, int quantity, double price);
    
    // Lấy order theo ID
    Order selectOrder(int id);
    
    // Lấy tất cả orders
    List<Order> selectAllOrders();
    
    // Xóa order
    boolean deleteOrder(int id);
    
    // Cập nhật order
    boolean updateOrder(Order order);
    
    // Lấy danh sách order theo user ID
    List<Order> getOrdersByUser(int userId);
    
    // Tìm order theo user ID và status
    List<Order> findOrdersByUserAndStatus(int userId, String status);
}


