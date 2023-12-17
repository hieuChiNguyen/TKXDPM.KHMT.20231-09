package subsystem.vnpay;

import java.io.IOException;
import java.util.Map;

import common.exception.UnrecognizedException;
import entity.payment.PaymentTransaction;

public class VNPayBoundary {
	public String generateQueryURL(int amount, String content) throws IOException{
		RequestVNPay requestVNPay = new RequestVNPay(amount, content);
		return requestVNPay.generateURL();
	}
	
	public PaymentTransaction getPaymentTransaction(Map<String,String> response) {
		ResponseVNPay responseVNPay = new ResponseVNPay(response);
		return responseVNPay.getPaymentTransaction();
	}
}
