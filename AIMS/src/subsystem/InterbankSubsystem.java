package subsystem;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.NotEnoughBalanceException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author hieud
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */

	//OCP:
	//Lớp InterbankSubsystem phụ thuộc chặt chẽ vào InterbankSubsystemController. 
	//khi muốn thêm một chức năng mới hoặc thay đổi hành vi, có thể phải sửa đổi lớp InterbankSubsystem

	//Đối tượng ctrl được khởi tạo trong hàm tạo của InterbankSubsystem. 
	//Nếu muốn thêm một loại controller mới hoặc thay đổi hành vi của controller, sẽ cần sửa đổi mã nguồn của lớp InterbankSubsystem

	//khắc phục: sử dụng dependency injection để đưa controller vào InterbankSubsystem, chẳng hạn thông qua một interface. 
	//Điều này giúp đảm bảo rằng InterbankSubsystem không phải sửa đổi khi có sự thay đổi trong controller.
	public InterbankSubsystem() {
		String str = new String();
		this.ctrl = new InterbankSubsystemController();
	}

	/**
	 * @see InterbankInterface#payOrder(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	// data coupling
	//control coupling   
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	// data coupling
	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}
}
