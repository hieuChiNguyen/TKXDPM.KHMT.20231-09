package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.PaymentTransaction;

import java.util.*;

/**
 * The {@code VNPaySubsystem} class is used to communicate with the
 * {@link subsystem.VNPaySubsystem VNPaySubsystem} to make transaction
 */

public interface VNPayInterface {

	/**
	 * Pay order, and then return the payment transaction
	 * 
	 * @param amount   - the amount to pay
	 * @param content - the transaction contents
	 * @return {@link entity.payment.PaymentTransaction PaymentTransaction} - if the
	 *         payment is successful
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */
	
	public abstract String generatePayUrl(int amount, String content)
            throws PaymentException, UnrecognizedException;
	
	public PaymentTransaction makePaymentTransaction(Map<String, String> response);
}
