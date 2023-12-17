package common.exception;

;

public class AnonymousTransactionException extends PaymentException {
    public AnonymousTransactionException() {
        super("ERROR: Transaction is unidentified !");
    }
}
