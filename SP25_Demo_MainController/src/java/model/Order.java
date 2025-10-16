package model;

import java.time.LocalDateTime;
import java.util.List;
import java.sql.*;

public class Order {
 private int id;
 private int userId;
 private double totalPrice;
 private String status;
 private String promoCode;
 public Order() {
 }
 public Order(int id, int userId, double totalPrice, String status) {
 this.id = id;
 this.userId = userId;
 this.totalPrice = totalPrice;
 this.status = status;
 }
 public int getId() {
 return id;
 }
 public void setId(int id) {
 this.id = id;
 }
 public int getUserId() {
 return userId;
 }
 public void setUserId(int userId) {
 this.userId = userId;
 }
 public double getTotalPrice() {
 return totalPrice;
 }
 public void setTotalPrice(double totalPrice) {
 this.totalPrice = totalPrice;
 }
 public String getStatus() {
 return status;
 }
 public void setStatus(String status) {
 this.status = status;
 }
 public String getPromoCode() {
 return promoCode;
 }
 public void setPromoCode(String promoCode) {
 this.promoCode = promoCode;
 }
 @Override
 public String toString() {
 return "id=" + "Order{" + id + ", userId=" + userId + ", totalPrice=" + totalPrice + ",status=" + status + '}';
 }

 public static Order fromResultSet(ResultSet rs) throws SQLException {
 Order order = new Order();
 order.setId(rs.getInt("id"));
 order.setUserId(rs.getInt("user_id"));
 order.setTotalPrice(rs.getDouble("total_price"));
 order.setStatus(rs.getString("status"));
 return order;
 }
}
//
//CREATE TABLE Orders (
// id INT PRIMARY KEY IDENTITY(1,1),
//-- ID đơn hàng, tự động tăng
// user_id INT NOT NULL, -- Người
//đặt hàng (liên kết với Users)
// order_date DATETIME DEFAULT
//GETDATE(), -- Ngày đặt hàng, mặc định
//là thời gian hiện tại
// total_price DECIMAL(10,2) NOT
//NULL, -- Tổng tiền của đơn hàng
// status NVARCHAR(50) NOT NULL
//DEFAULT N'Pending', -- Trạng thái đơn
//hàng
// FOREIGN KEY (user_id) REFERENCES
//Users(id) -- Khóa ngoại tham chiếu
//Users
//);
//INSERT INTO Orders (user_id,
//total_price, status)
//VALUES (1, 1500.00, N'Pending');
//CREATE TABLE OrderDetails (
// id INT PRIMARY KEY IDENTITY(1,1),
//-- ID chi tiết đơn hàng, tự động tăng
// order_id INT NOT NULL, -- Liên
//kết với bảng Orders
// product_id INT NOT NULL, -- Liên
//kết với bảng Product
// quantity INT NOT NULL CHECK
//(quantity > 0), -- Số lượng sản phẩm
// price DECIMAL(10,2) NOT NULL, --
//Giá tại thời điểm mua
// subtotal AS (quantity * price), -
//- Tổng tiền từng sản phẩm (computed
//column)
// FOREIGN KEY (order_id) REFERENCES
//Orders(id) ON DELETE CASCADE, -- Khi
//xóa đơn hàng, xóa luôn chi tiết
// FOREIGN KEY (product_id)
//REFERENCES Product(id) -- Liên kết
//với Product
//);