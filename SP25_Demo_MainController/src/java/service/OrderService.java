package service;

import OrderDAO.IOrderDao;
import OrderDAO.OrderDao;
import model.Order;
import java.util.List;
import java.util.Map;

public class OrderService implements IOrderService {
    private final IOrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    @Override
    public void create(Order order) {
        // Validate đơn giản
        if (order.getUserId() <= 0) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (order.getTotalPrice() < 0) {
            throw new IllegalArgumentException("Total price cannot be negative");
        }
        if (order.getStatus() == null || order.getStatus().trim().isEmpty()) {
            order.setStatus("pending"); // Default status
        }
        orderDao.insertOrder(order);
    }

    @Override
    public void createOrderWithItems(Order order, Map<Integer, Integer> cartItems) {
        // Validate
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart items cannot be empty");
        }
        
        // Tạo order trước
        create(order);
        
        // Lấy order ID vừa tạo (cần implement method để lấy ID)
        // Tạm thời sử dụng cách khác hoặc cần modify OrderDao
        // orderDao.insertOrderDetails(orderId, productId, quantity, price);
    }

    @Override
    public Order findById(int id) {
        return orderDao.selectOrder(id);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.selectAllOrders();
    }

    @Override
    public boolean update(Order order) {
        if (order.getId() <= 0) {
            throw new IllegalArgumentException("Order ID is required for update");
        }
        return orderDao.updateOrder(order);
    }

    @Override
    public boolean delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Order ID must be positive");
        }
        return orderDao.deleteOrder(id);
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        return orderDao.getOrdersByUser(userId);
    }

    @Override
    public List<Order> findOrdersByUserAndStatus(int userId, String status) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        return orderDao.findOrdersByUserAndStatus(userId, status);
    }

    @Override
    public void addOrderDetail(int orderId, int productId, int quantity, double price) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive");
        }
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be positive");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        orderDao.insertOrderDetails(orderId, productId, quantity, price);
    }
}