package entity.invoice;

import java.util.List;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.order.Order;
import entity.order.OrderMedia;
import utils.Configs;

public class Invoice {
	private List<OrderMedia> listOrderMedia;
	
	private double shippingFee;
    private double totalPrice;
    private double totalProductPriceIncludeVAT;
    private double totalProductPriceNotIncludeVAT;
    
    private int totalProduct;
    
    public Invoice(){
    	Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            
            order.getlstOrderMedia().add(orderMedia);
        }
        
        
    	totalPrice = this.calculateTotalPrice();
    	totalProductPriceIncludeVAT = this.calculateTotalProductPriceIncludeVAT();
    	totalProductPriceNotIncludeVAT = this.calculateTotalProductPriceNotIncludeVAT();
        shippingFee = 0;
    }
    
    public int getTotalProduct() {
        return totalProduct;
    }
    
    public double getTotalProductPriceIncludeVAT() {
        return totalProductPriceIncludeVAT;
    }
    
    public double getTotalProductPriceNotIncludeVAT() {
        return totalProductPriceNotIncludeVAT;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }
    
    public double getShippingFee() {
        return shippingFee;
    }
    
    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }
    
    public double calculateTotalPrice() {
    	totalPrice = shippingFee + calculateTotalProductPriceIncludeVAT() + calculateTotalProductPriceNotIncludeVAT();
    	return totalPrice;
    }
    
    public double calculateTotalProductPriceIncludeVAT(){
        double amount = 0;
        for (Object object : listOrderMedia) {
            OrderMedia om = (OrderMedia) object;
            amount += om.getPrice();
        }
        return amount + (Configs.PERCENT_VAT/100)*amount;
    }

    public double calculateTotalProductPriceNotIncludeVAT(){
        double amount = 0;
        for (Object object : listOrderMedia) {
            OrderMedia om = (OrderMedia) object;
            amount += om.getPrice();
        }
        return amount;
    }
    
    public void saveInvoice(){

    }
}
