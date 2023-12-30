package subsystem.interbank;

import java.util.Map;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.InvalidTransactionAmountException;
import common.exception.InvalidVersionException;
import common.exception.NotEnoughBalanceException;
import common.exception.NotEnoughTransactionInfoException;
import common.exception.SuspiciousTransactionException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import utils.Configs;
import utils.MyMap;
import utils.Utils;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
	private static final String SECRET_KEY = "BUXj/7/gHHI=";
	private static final String PAY_COMMAND = "pay";
	private static final String VERSION = "1.0.0";

	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		return null;
	}

	// coincidental cohesion
	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	// data coupling
	// logical cohesion
	//OCP:
	//sử dụng trực tiếp Configs.PROCESS_TRANSACTION_URL. 
	//Nếu URL này thay đổi, sẽ cần phải sửa đổi mã nguồn. 
	//Điều này làm giảm tính mở rộng của mã nguồn.
	//khắc phục: chuyển URL như một tham số hoặc sử dụng một cơ chế cấu hình để giảm sự phụ thuộc

	//Lớp InterbankSubsystemController phụ thuộc trực tiếp vào InterbankBoundary, điều này làm cho mã nguồn không mở rộng được. 
	//Nếu muốn thay đổi phương thức gửi yêu cầu (query) hay thay đổi triển khai của InterbankBoundary, sẽ cần phải sửa đổi mã nguồn ở đây.
	//khắc phục: chuyển các giá trị cấu hình hoặc triển khai của InterbankBoundary qua các tham số của phương thức hoặc thông qua constructor.

	//sử dụng trực tiếp MyMap, đây có thể là một vấn đề nếu bạn muốn thay đổi hoặc mở rộng cách bạn xử lý dữ liệu JSON. 
	//khắc phục: sử dụng một thư viện JSON hoặc một cơ chế đối tượng ánh xạ từ JSON sang đối tượng.
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
//control coupling
		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}

	// data coupling
	// logical cohesion
	private PaymentTransaction makePaymentTransaction(MyMap response) {
		if (response == null)
			return null;
		MyMap transcation = (MyMap) response.get("transaction");
		CreditCard card = new CreditCard((String) transcation.get("cardCode"), (String) transcation.get("owner"),
				Integer.parseInt((String) transcation.get("cvvCode")), (String) transcation.get("dateExpired"));
		PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
				(String) transcation.get("transactionId"), (String) transcation.get("transactionContent"),
				Integer.parseInt((String) transcation.get("amount")), (String) transcation.get("createdAt"));

		switch (trans.getErrorCode()) {
			case "00":
				break;
			case "01":
				throw new InvalidCardException();
			case "02":
				throw new NotEnoughBalanceException();
			case "03":
				throw new InternalServerErrorException();
			case "04":
				throw new SuspiciousTransactionException();
			case "05":
				throw new NotEnoughTransactionInfoException();
			case "06":
				throw new InvalidVersionException();
			case "07":
				throw new InvalidTransactionAmountException();
			default:
				throw new UnrecognizedException();
		}

		return trans;
	}

}
