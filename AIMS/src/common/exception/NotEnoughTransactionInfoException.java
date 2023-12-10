package common.exception;
//functional cohesion
public class NotEnoughTransactionInfoException extends PaymentException {
public NotEnoughTransactionInfoException() {
	super("ERROR: Not Enough Transcation Information");
}
}
