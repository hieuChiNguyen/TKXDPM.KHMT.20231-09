package subsystem;

import java.io.IOException;
import java.util.Map;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.NotEnoughBalanceException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.vnpay.VNPaySubsystemController;

/***
 * The {@code VNPaySubsystem} class is used to communicate with the
 * VNPay to make transaction.
 */
public class VNPaySubsystem implements VNPayInterface {

	/**
     * Represent the controller of the subsystem.
     */
    private VNPaySubsystemController ctrl;

	/**
	 * Initializes a newly created {@code VNPaySubsystem} object so that it
	 * represents an VNPay subsystem.
	 */
	public VNPaySubsystem() {
		this.ctrl = new VNPaySubsystemController();
	}
	
	@Override
	public PaymentTransaction makePaymentTransaction(Map<String, String> response) {
        return ctrl.getPaymentTransaction(response);
    }

	@Override
	public String generatePayUrl(int amount, String content) throws PaymentException, UnrecognizedException {
		// TODO Auto-generated method stub
		try {
            return ctrl.generateQueryURL(amount, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
}
