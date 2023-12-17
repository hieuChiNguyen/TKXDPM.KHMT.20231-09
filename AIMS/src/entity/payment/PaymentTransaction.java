package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String transactionTime;
	
	public PaymentTransaction(String errorCode, String transactionId, String transactionContent,
			int amount, String transactionTime) {
		super();
		this.errorCode = errorCode;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.transactionTime = transactionTime;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public String getTransactionContent() {
		return transactionContent;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getTransactionTime() {
		return transactionTime;
	}
}
