package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.cart.Cart;
import entity.payment.PaymentTransaction;
import subsystem.VNPayInterface;
import subsystem.VNPaySubsystem;

/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 */
public class PaymentController extends BaseController {

	/**
	 * Represent the VNPay subsystem
	 */
	private VNPayInterface vnpay;

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link java.lang.String String} representing the date in the
	 * required format "mm/yy" .
	 * 
	 * @param date - the {@link java.lang.String String} represents the input date
	 * @return {@link java.lang.String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	
//	private String getExpirationDate(String date) throws InvalidCardException {
//		String[] strs = date.split("/");
//		if (strs.length != 2) {
//			throw new InvalidCardException();
//		}
//
//		String expirationDate = null;
//		int month = -1;
//		int year = -1;
//
//		try {
//			month = Integer.parseInt(strs[0]);
//			year = Integer.parseInt(strs[1]);
//			if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
//				throw new InvalidCardException();
//			}
//			expirationDate = strs[0] + strs[1];
//
//		} catch (Exception ex) {
//			throw new InvalidCardException();
//		}
//
//		return expirationDate;
//	}

	public Map<String, String> payOrder(Map<String, String> response) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			this.vnpay = new VNPaySubsystem();
			PaymentTransaction transaction = vnpay.makePaymentTransaction(response);
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully paid the order!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}
	
	public String generateURL(int amount, String content) throws IOException {
		this.vnpay = new VNPaySubsystem();
		return vnpay.generatePayUrl(amount, content);
	}
	
	public void emptyCart() {
		Cart.getCart().emptyCart();
	}

}