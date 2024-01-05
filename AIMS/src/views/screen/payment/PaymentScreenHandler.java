package views.screen.payment;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import controller.PaymentController;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import subsystem.vnpay.ConfigVNPay;
import utils.Configs;
import views.screen.BaseScreenHandler;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class PaymentScreenHandler extends BaseScreenHandler {

	private Invoice invoice;
	@FXML
	private Label pageTitle;
	@FXML
	private VBox vBox;

	public PaymentScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.invoice = order.getInvoice();
		this.setBController(new PaymentController());
		WebView paymentView = new WebView();
		WebEngine webEngine = paymentView.getEngine();
		webEngine.load(((PaymentController) getBController()).generateURL(invoice.getTotalPrice(), "Thanh toán hóa đơn"));
		webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
			handleUrlChanged(newValue);
		});
		vBox.getChildren().clear();
		vBox.getChildren().add(paymentView);
	}

	// Hàm chuyển đổi query string thành Map
	private static Map<String, String> parseQueryString(String query) {
		Map<String, String> params = new HashMap<>();
		if (query != null && !query.isEmpty()) {
			String[] pairs = query.split("&");
			for (String pair : pairs) {
				String[] keyValue = pair.split("=");
				if (keyValue.length == 2) {
					params.put(keyValue[0], keyValue[1]);
				}
			}
		}
		return params;
	}

	private void handleUrlChanged(String newValue) {
		if (newValue.contains(ConfigVNPay.vnp_ReturnUrl)) {
			try {
				URI uri = new URI(newValue);
				String query = uri.getQuery();
				System.out.println("chuỗi uri "+uri);
				System.out.println("chuỗi api"+query);
                
				Map<String, String> params = parseQueryString(query);

				confirmToPayOrder(params);
				
				// Gửi URI đến email
	            sendEmailWithURI(uri.toString());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @throws IOException
	 */
	void confirmToPayOrder(Map<String, String> res) throws IOException {

		PaymentController controller = (PaymentController) getBController();
		Map<String, String> response = controller.makePayment(res);

		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
				response.get("RESULT"), response.get("MESSAGE"));
		controller.emptyCart();
		resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreenHandler(homeScreenHandler);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();

	}
	
	private void sendEmailWithURI(String uri) {
	    // Thông tin tài khoản email nguồn
	    String senderEmail = "baohieu888@gmail.com";
	    String senderPassword = "gwni riqq zzwo lljq";

	    // Thông tin tài khoản email đích
	    String recipientEmail = "baohieu888@gmail.com";

	    // Cấu hình Java Mail
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");

	    // Tạo đối tượng Session
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(senderEmail, senderPassword);
	        }
	    });

	    try {
	        // Tạo đối tượng Message
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(senderEmail));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	        message.setSubject("Thanh toán thành công");

	        // Tạo nội dung email
	        String content = "Xin chào,\n\nCảm ơn bạn đã lựa chọn AIMS project từ nhóm 9!\n\nDưới đây là URL để bạn xem lại thông tin "
	        		+ "thanh toán của mình:\n" + uri;

	        // Thiết lập nội dung email
	        message.setText(content);

	        // Gửi email
	        Transport.send(message);

	        System.out.println("Email sended!");
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}

}