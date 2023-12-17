package common.exception;

;

public class SendToBankException extends PaymentException {
    public SendToBankException() {
        super("ERROR: VNPAY has sent a refund request to the Bank !");
    }
}
