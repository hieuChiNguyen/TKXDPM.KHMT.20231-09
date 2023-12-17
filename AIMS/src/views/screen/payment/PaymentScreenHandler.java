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
import subsystem.vnpay.VNPayConfig;
import utils.Configs;
import views.screen.BaseScreenHandler;

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
		if (newValue.contains(VNPayConfig.vnp_ReturnUrl)) {
			try {
				URI uri = new URI(newValue);
				String query = uri.getQuery();
				System.out.println(query);

				Map<String, String> params = parseQueryString(query);

				confirmToPayOrder(params);

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
		Map<String, String> response = controller.payOrder(res);

		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
				response.get("RESULT"), response.get("MESSAGE"));
		controller.emptyCart();
		resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreenHandler(homeScreenHandler);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();

	}

}