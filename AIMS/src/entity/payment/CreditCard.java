package entity.payment;

import java.sql.Timestamp;
//functional: chủ yếu chứa thông tin về thẻ tín dụng và không có nhiều phương thức để thực hiện các chức năng liên quan.
public class CreditCard {
	private String cardCode;
	private String owner;
	private int cvvCode;
	private String dateExpired;

	public CreditCard(String cardCode, String owner, int cvvCode, String dateExpired) {
		super();
		this.cardCode = cardCode;
		this.owner = owner;
		this.cvvCode = cvvCode;
		this.dateExpired = dateExpired;
	}
}
