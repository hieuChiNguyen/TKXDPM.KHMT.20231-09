package entity.order;

import entity.media.Media;
//functional: chủ yếu tập trung vào việc lưu trữ thông tin về một sản phẩm trong đơn hàng và cung cấp các phương thức để truy cập và cập nhật thông tin đó.
//comunicational: tất cả các phương thức tương tác chặt chẽ với các thành phần bên trong class để thực hiện các chức năng quản lý OrderMedia.
public class OrderMedia {
    
    private Media media;
    private int price;
    private int quantity;

    public OrderMedia(Media media, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "{" +
            "  media='" + media + "'" +
            ", quantity='" + quantity + "'" +
            ", price='" + price + "'" +
            "}";
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

}
