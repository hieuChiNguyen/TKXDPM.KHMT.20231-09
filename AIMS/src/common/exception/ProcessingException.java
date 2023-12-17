package common.exception;

;

public class ProcessingException extends PaymentException {
    public ProcessingException() {
        super("ERROR: Transaction is processing !");
    }
}
