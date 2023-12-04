package controller;

import java.sql.SQLException;
import java.util.List;

import entity.media.Media;
import entity.cart.Cart;
import entity.cart.CartMedia;

/**
 * This class controls the flow of events when users view the Cart
 * 
 * @author nguyenlm
 */

//functional: chủ yếu tập trung vào việc kiểm tra sự khả dụng của các sản phẩm trong giỏ hàng.
//comunication:  tương tác để kiểm tra sự khả dụng của sản phẩm trong giỏ hàng
public class ViewCartController extends BaseController {

    /**
     * This method checks the available products in Cart
     * 
     * @throws SQLException
     */
    // data coupling
    //control coupling
    public void checkAvailabilityOfProduct() throws SQLException {
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method calculates the cart subtotal
     * 
     * @return subtotal
     */
    // data coupling
    public int getCartSubtotal() {
        int subtotal = Cart.getCart().calSubtotal();
        return subtotal;
    }

}
