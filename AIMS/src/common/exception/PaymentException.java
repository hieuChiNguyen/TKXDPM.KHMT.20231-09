package common.exception;;
//functional cohesion
public class PaymentException extends RuntimeException {
	public PaymentException(String message) {
		super(message);
	}
}
