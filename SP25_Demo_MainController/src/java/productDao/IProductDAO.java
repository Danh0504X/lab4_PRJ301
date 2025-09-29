/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productDao;

import model.Product;
import java.sql.*;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public interface IProductDAO {
 public void insertProduct (Product pro) throws SQLException;
 public Product selectProduct (int id);
 public List<Product> selectAllProducts();
 public boolean deleteProduct (int id) throws SQLException;
 public boolean updateProduct (Product pro) throws SQLException;
}