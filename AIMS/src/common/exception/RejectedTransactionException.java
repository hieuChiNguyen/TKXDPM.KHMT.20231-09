package common.exception;

public class RejectedTransactionException extends PaymentException {
    public RejectedTransactionException() {
        super("ERROR: Refund transaction is declined !");
    }
}
