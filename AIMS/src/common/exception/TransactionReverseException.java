package common.exception;

;

public class TransactionReverseException extends PaymentException {

    public TransactionReverseException() {
        super("ERROR: Customer has had money deducted at the Bank but the transaction has been failed at VNPAY !");
    }

}
