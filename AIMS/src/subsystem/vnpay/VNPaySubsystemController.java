package subsystem.vnpay;

import java.io.IOException;
import java.util.Map;

import entity.payment.PaymentTransaction;

public class VNPaySubsystemController {

	private static VNPayBoundary vnpayBoundary = new VNPayBoundary();
	
	/**
     * @param money
     * @param contents
     * @return PaymentTransaction
     */
	public String generateQueryURL(int amount, String content) throws IOException {
		return vnpayBoundary.generateQueryURL(amount, content);
	}
	
	public PaymentTransaction getPaymentTransaction(Map<String,String> response) {
		return vnpayBoundary.getPaymentTransaction(response);
	}
}
