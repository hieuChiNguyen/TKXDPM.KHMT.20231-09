package entity.cart;

import entity.media.Media;
//functional:chủ yếu tập trung vào việc lưu trữ thông tin về một sản phẩm trong giỏ hàng và cung cấp các phương thức để truy cập và cập nhật thông tin đó.
//comunicational: tất cả các phương thức tương tác chặt chẽ với các thành phần bên trong class để thực hiện các chức năng quản lý CartMedia.
public class CartMedia {
    
    private Media media;
    private int quantity;
    private int price;

    public CartMedia(){

    }

    public CartMedia(Media media, Cart cart, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Media getMedia() {
        return this.media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" 
            + " media='" + media + "'" 
            + ", quantity='" + quantity + "'" 
            + "}";
    }

}

    
