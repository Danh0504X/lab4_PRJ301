/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Product;
import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    void create(Product p) throws SQLException;
    Product getById(int id);
    List<Product> getAll();
    boolean update(Product p) throws SQLException;
    boolean delete(int id) throws SQLException;

    // (tuỳ chọn) phân trang tại DB sau này
    // List<Product> getPage(int page, int size);
    // int countAll();
}
