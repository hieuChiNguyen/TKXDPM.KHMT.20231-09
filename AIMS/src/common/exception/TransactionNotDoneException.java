package common.exception;

public class TransactionNotDoneException extends PaymentException {
    public TransactionNotDoneException() {
        super("ERROR: Transaction is not completed!");
    }
}
