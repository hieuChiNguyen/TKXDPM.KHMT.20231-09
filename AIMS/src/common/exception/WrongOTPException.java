package common.exception;

public class WrongOTPException extends PaymentException{
	public WrongOTPException() {
		super("ERROR: Wrong OTP code !");
	}
}
