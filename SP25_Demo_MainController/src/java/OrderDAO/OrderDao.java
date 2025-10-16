package OrderDAO;

import dao.DBConnection;
import model.Order;

import java.sql.*;
import java.util.*;

public class OrderDao implements IOrderDao {
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM Orders WHERE id = ?";
    private static final String SELECT_ORDERS_BY_USER = "SELECT * FROM Orders WHERE user_id = ?";
    private static final String SELECT_ORDERS_BY_USER_STATUS = "SELECT * FROM Orders WHERE user_id = ? AND status = ?";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM Orders ORDER BY id DESC";
    private static final String INSERT_ORDER = "INSERT INTO orders (user_id, total_price, status) VALUES(?, ?, ?)";
    private static final String INSERT_ORDER_DETAILS = "INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_ORDER = "UPDATE Orders SET user_id = ?, total_price = ?, status = ? WHERE id = ?";
    private static final String DELETE_ORDER = "DELETE FROM Orders WHERE id = ?";
    private static final String DELETE_ORDER_DETAILS = "DELETE FROM OrderDetails WHERE order_id = ?";

    // Helper method để lấy connection
    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    @Override
    public void insertOrder(Order order) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER)) {
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, order.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("insertOrder failed", e);
        }
    }

    @Override
    public void insertOrderDetails(int orderId, int productId, int quantity, double price) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER_DETAILS)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("insertOrderDetails failed", e);
        }
    }

    @Override
    public Order selectOrder(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ORDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("selectOrder failed", e);
        }
    }

    @Override
    public List<Order> selectAllOrders() {
        List<Order> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_ORDERS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("selectAllOrders failed", e);
        }
        return list;
    }

    @Override
    public boolean updateOrder(Order order) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ORDER)) {
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, order.getStatus());
            ps.setInt(4, order.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("updateOrder failed", e);
        }
    }

    @Override
    public boolean deleteOrder(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ORDER_DETAILS)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("deleteOrderDetails failed", e);
        }
        
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ORDER)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("deleteOrder failed", e);
        }
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        List<Order> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ORDERS_BY_USER)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("getOrdersByUser failed", e);
        }
        return list;
    }

    @Override
    public List<Order> findOrdersByUserAndStatus(int userId, String status) {
        List<Order> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ORDERS_BY_USER_STATUS)) {
            ps.setInt(1, userId);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("findOrdersByUserAndStatus failed", e);
        }
        return list;
    }

    private Order mapRow(ResultSet rs) throws SQLException {
        return Order.fromResultSet(rs);
    }
}