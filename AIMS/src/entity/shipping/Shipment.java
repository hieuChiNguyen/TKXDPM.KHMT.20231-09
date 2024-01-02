package entity.shipping;

public class Shipment {

    // logical cohesion
    public void validateDeliveryInfo() {
        // TODO: implement later on
    }

    // coincidental cohesion
    public Shipment createNewShipment() {
        // TODO: implement later on
        return new Shipment();
    }
}
//OCP
//phương thức validateDeliveryInfo và createNewShipment đều không liên quan chặt chẽ và có thể dẫn đến vấn đề khi muốn thay đổi hoặc mở rộng chúng.

//khắc phục: tách chúng thành các lớp hoặc giao diện riêng biệt
