//
//package service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import model.Cart;
//import model.CartItem;
//import model.Product;
//
//public class CartService implements ICartService {
////
////    @Override
////    public void addToCart(Cart cart, Product product, int quantity) {
////        if (cart == null || product == null) return;
////        List<CartItem> items = getOrInitItems(cart);
////        int idx = indexOf(items, Integer.parseInt(product.getId()));
////        int qty = Math.max(1, quantity);
////
////        if (idx >= 0) {
////            CartItem it = items.get(idx);
////            it.setQuantity(it.getQuantity() + qty);
////        } else {
////            CartItem it = new CartItem();
////            it.setProduct(product);
////            it.setQuantity(qty);
////            items.add(it);
////        }
////        cart.setItems(items);
////        cart.setTotal(calc(items));
////    }
////
////    @Override
////    public void updateCartItem(Cart cart, int productId, int quantity) {
////        if (cart == null) return;
////        List<CartItem> items = getOrInitItems(cart);
////        int idx = indexOf(items, productId);
////        if (idx >= 0) {
////            if (quantity <= 0) {
////                items.remove(idx);
////            } else {
////                items.get(idx).setQuantity(quantity);
////            }
////            cart.setItems(items);
////            cart.setTotal(calc(items));
////        }
////    }
////
////    @Override
////    public void removeCartItem(Cart cart, int productId) {
////        if (cart == null) return;
////        List<CartItem> items = getOrInitItems(cart);
////        int idx = indexOf(items, productId);
////        if (idx >= 0) {
////            items.remove(idx);
////            cart.setItems(items);
////            cart.setTotal(calc(items));
////        }
////    }
////
////    @Override
////    public void clearCart(Cart cart) {
////        if (cart == null) return;
////        List<CartItem> items = getOrInitItems(cart);
////        items.clear();
////        cart.setItems(items);
////        cart.setTotal(0.0);
////    }
////
////    @Override
////    public double calculateTotal(Cart cart) {
////        if (cart == null) return 0.0;
////        double total = calc(getOrInitItems(cart));
////        cart.setTotal(total);
////        return total;
////    }
////
////    @Override
////    public List<CartItem> getItems(Cart cart) {
////        return getOrInitItems(cart);
////    }
////
////    // ---------------- helpers ----------------
////
////    private List<CartItem> getOrInitItems(Cart cart) {
////        List<CartItem> items = cart.getItems();
////        if (items == null) {
////            items = new ArrayList<>();
////            cart.setItems(items);
////        }
////        return items;
////    }
////private int indexOf(List<CartItem> items, int productId) {
////    for (int i = 0; i < items.size(); i++) {
////        Product p = items.get(i).getProduct();
////        if (p != null) {
////            try {
////                int pid = Integer.parseInt(p.getId());
////                if (pid == productId) {
////                    return i; // tìm thấy
////                }
////            } catch (NumberFormatException e) {
////                // id không phải số thì bỏ qua
////            }
////        }
////    }
////    return -1; // không tìm thấy
////}
////
////    /** Tính tổng tiền từ danh sách items (an toàn với kiểu price khác nhau) */
////    private double calc(List<CartItem> items) {
////        double sum = 0.0;
////        for (CartItem it : items) {
////            if (it == null || it.getProduct() == null) continue;
////            double price = extractPriceAsDouble(it.getProduct());
////            sum += price * Math.max(0, it.getQuantity());
////        }
////        return sum;
////    }
////
////    /**
////     * Dự án của bạn có thể để Product.getPrice() là double, BigDecimal, hoặc String.
////     * Hàm này chuyển về double một cách an toàn.
////     */
////    private double extractPriceAsDouble(Product p) {
////        try {
////            Object priceObj = p.getClass().getMethod("getPrice").invoke(p);
////            if (priceObj == null) return 0.0;
////            if (priceObj instanceof Number) return ((Number) priceObj).doubleValue();
////            // ví dụ Product.getPrice() trả về String:
////            return Double.parseDouble(String.valueOf(priceObj));
////        } catch (Exception e) {
////            // Nếu Product không có getPrice() hoặc kiểu khác lạ -> coi như 0
////            return 0.0;
////        }
////    }
////}