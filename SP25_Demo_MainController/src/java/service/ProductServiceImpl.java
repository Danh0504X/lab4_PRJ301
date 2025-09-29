/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Product;
import productDao.IProductDAO;
import productDao.productDAO;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    private final IProductDAO productDao;

    public ProductServiceImpl() {
        this.productDao = new productDAO(); // default
    }
    public ProductServiceImpl(IProductDAO dao) {
        this.productDao = dao;              // inject để test/transaction
    }

    @Override
    public void create(Product p) throws SQLException {
        // Có thể thêm validate nghiệp vụ ở đây
        if (p == null) throw new IllegalArgumentException("Product null");
        if (p.getName() == null || p.getName().isBlank())
            throw new IllegalArgumentException("Name is required");
        if (p.getPrice() == null || p.getPrice().isBlank())
            throw new IllegalArgumentException("Price is required");
        if (p.getStock() == null || p.getStock().isBlank())
            p.setStock("0");
        productDao.insertProduct(p);
    }

    @Override
    public Product getById(int id) {
        return productDao.selectProduct(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.selectAllProducts();
    }

    @Override
    public boolean update(Product p) throws SQLException {
        if (p == null || p.getId() == null) throw new IllegalArgumentException("Missing id");
        return productDao.updateProduct(p);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return productDao.deleteProduct(id);
    }
}
