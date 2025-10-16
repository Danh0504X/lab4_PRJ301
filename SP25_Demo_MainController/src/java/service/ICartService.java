
package service;

import java.util.List;
import model.Cart;
import model.CartItem;
import model.Product;

public interface ICartService {

    /** Thêm sản phẩm vào giỏ (cộng dồn nếu đã có) */
    void addToCart(Cart cart, Product product, int quantity);

    /** Cập nhật số lượng 1 dòng; quantity <= 0 sẽ xóa dòng */
    void updateCartItem(Cart cart, int productId, int quantity);

    /** Xóa 1 dòng theo productId */
    void removeCartItem(Cart cart, int productId);

    /** Xóa toàn bộ giỏ */
    void clearCart(Cart cart);

    /** Tính và gán lại tổng tiền cho cart (đồng thời trả về tổng) */
    double calculateTotal(Cart cart);

    /** Lấy danh sách items (đảm bảo không null) */
    List<CartItem> getItems(Cart cart);
}