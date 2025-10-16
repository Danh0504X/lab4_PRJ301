/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userDAO;


import dao.DBConnection;
import java.util.List;
import model.User;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */

public class UserDao implements IUserDAO {

    // ví dụ: lấy connection từ class DBConnection của bạn
    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    // --- SQL ---
    private static final String INSERT_SQL =
        "INSERT INTO Users (name, email, country, role, status, password) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL =
        "SELECT id, name, email, country, role, status, password FROM Users WHERE id = ?";
    private static final String SELECT_ALL_SQL =
        "SELECT id, name, email, country, role, status, password FROM Users";
    private static final String UPDATE_SQL =
        "UPDATE Users SET name=?, email=?, country=?, role=?, status=?, password=? WHERE id=?";
    private static final String DELETE_SQL =
        "DELETE FROM Users WHERE id=?";
    private static final String SEARCH_SQL =
        "SELECT id, name, email, country, role, status, password FROM Users " +
        "WHERE name LIKE ? OR email LIKE ? OR country LIKE ?";
    private static final String LOGIN_SQL =
    "SELECT id, name, email, country, role, status, password, dob " +
    "FROM Users WHERE name = ? AND password = ? AND status = 1";
    @Override
    public void insertUser(User user) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, user.isStatus());
            ps.setString(6, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("insertUser failed", e);
        }
    }

    @Override
    public User selectUser(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("selectUser failed", e);
        }
    }
    
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setEmail(rs.getString("email"));
                    u.setCountry(rs.getString("country"));
                    u.setRole(rs.getString("role"));
                    u.setStatus(rs.getBoolean("status"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // không tìm thấy
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("selectAllUsers failed", e);
        }
        return list;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, user.isStatus());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("updateUser failed", e);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("deleteUser failed", e);
        }
    }

    @Override
    public List<User> searchByKeyword(String keyword) {
        List<User> list = new ArrayList<>();
        String kw = "%" + (keyword == null ? "" : keyword.trim()) + "%";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SEARCH_SQL)) {
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("searchByKeyword failed", e);
        }
        return list;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setCountry(rs.getString("country"));
        u.setRole(rs.getString("role"));
        u.setStatus(rs.getBoolean("status"));
        u.setPassword(rs.getString("password"));
        return u;
    }
        
    public User findByCredentials(String username, String password) {
    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(LOGIN_SQL)) {
        ps.setString(1, username);
        ps.setString(2, password); // lab dùng plain; thực tế nên hash
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next() ? mapRow(rs) : null;
        }
    } catch (SQLException e) {
        throw new RuntimeException("login failed", e);
    }
}
}
