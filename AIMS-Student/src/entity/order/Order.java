package entity.order;

import java.util.ArrayList;
import java.util.List;

import entity.invoice.Invoice;
import entity.shipping.DeliveryInfor;

public class Order {

    private int id;
    private DeliveryInfor deliveryInfo;
    private Invoice invoice;
    private List<Invoice> listInvoices;

    public Order(DeliveryInfor deliveryInfo, Invoice invoice){
        this.deliveryInfo = deliveryInfo;
        this.invoice = invoice;
    }

    public DeliveryInfor getDeliveryInfo() {
        return deliveryInfo;
    }

    public Invoice getInvoice() {
        return invoice;
    }
    
    public List<Invoice> getListInvoices() {
        return listInvoices;
    }
    
    public int calculateShippingFees(){

        if (getInvoice().getTotalPrice() > 100000) {
            return 0;
        }

        double baseCost = 0;
        double baseWeight = 0;
        double additionalCostPerHalfKg = 0;

        if (getDeliveryInfo().isUrban()) {
            baseCost = 22000;
            baseWeight = 3;
        } else {
            baseCost = 30000;
            baseWeight = 0.5;
        }
        additionalCostPerHalfKg = 2500;

        double rushShippingCost = 0;

        if(getDeliveryInfo().isRushShipping()) rushShippingCost = 10000 * getInvoice().getNumberOfRushShippingProduct();

        double regularShippingCost = 0;

        if (getInvoice().getMaxWeight() <= baseWeight) {
            regularShippingCost = baseCost;
        } else {
            regularShippingCost = baseCost + Math.ceil((getInvoice().getMaxWeight() - baseWeight) * 2) * additionalCostPerHalfKg;
        }

        return (int) (rushShippingCost + regularShippingCost);
    }
    
    public void addInvoiceToOrder(Invoice invoice) {
        if (listInvoices == null) {
            listInvoices = new ArrayList<>();
        }
        listInvoices.add(invoice);
    }

}
