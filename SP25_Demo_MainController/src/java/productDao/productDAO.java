/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productDao;

import dao.DBConnection;
import model.Product;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class productDAO implements IProductDAO {

    // --- INSERT ---
    @Override
    public void insertProduct(Product pro) throws SQLException {
        String sql = "INSERT INTO Product(name, price, description, stock, import_date) VALUES (?, ?, ?, ?, GETDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setNString(1, pro.getName());                          // NVARCHAR
            ps.setBigDecimal(2, new java.math.BigDecimal(pro.getPrice()));
            ps.setNString(3, pro.getDescription());
            ps.setInt(4, Integer.parseInt(pro.getStock()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pro.setId(String.valueOf(rs.getInt(1)));
                }
            }
        }
    }

    // --- SELECT ONE ---
    @Override
    public Product selectProduct(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // --- SELECT ALL ---
    @Override
    public List<Product> selectAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT id, name, price, description, stock, import_date, status\n" +
                    "FROM Product\n" +
                    "WHERE status = 1;   ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // --- DELETE ---
    @Override
    public boolean deleteProduct(int id) throws SQLException {
        String sql = "UPDATE Product SET status = 0 WHERE id = ?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // --- UPDATE ---
    @Override
    public boolean updateProduct(Product pro) throws SQLException {
        String sql = "UPDATE Product SET name=?, price=?, description=?, stock=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setNString(1, pro.getName());
            ps.setBigDecimal(2, new java.math.BigDecimal(pro.getPrice()));
            ps.setNString(3, pro.getDescription());
            ps.setInt(4, Integer.parseInt(pro.getStock()));
            ps.setInt(5, Integer.parseInt(pro.getId()));

            return ps.executeUpdate() > 0;
        }
    }

    // --- Helper mapRow ---
    private Product mapRow(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(String.valueOf(rs.getInt("id")));
        p.setName(rs.getNString("name"));
        p.setPrice(rs.getBigDecimal("price").toString());
        p.setDescription(rs.getNString("description"));
        p.setStock(String.valueOf(rs.getInt("stock")));
        p.setStatus(rs.getBoolean("status"));  
        Timestamp ts = rs.getTimestamp("import_date");
        if (ts != null) p.setImportDate(ts.toLocalDateTime());
        
        return p;
    }
 

}
