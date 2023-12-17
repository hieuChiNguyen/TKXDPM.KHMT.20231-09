package views.screen.invoice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import common.exception.ProcessInvoiceException;
import controller.PaymentController;
import controller.PlaceOrderController;
import entity.order.Order;
import entity.order.OrderMedia;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.popup.PopupScreen;

public class InvoiceScreenHandler extends BaseScreenHandler {

	private static Logger LOGGER = Utils.getLogger(InvoiceScreenHandler.class.getName());

	@FXML
	private Label pageTitle;

	@FXML
	private TextField name;

	@FXML
	private TextField phone;

	@FXML
	private ComboBox<String> province;

	@FXML
	private TextField address;

	@FXML
	private TextField instructions;

	@FXML
	private RadioButton chooseShip;

	@FXML
	private TextField time;

	@FXML
	private TextField rushInstruction;

	@FXML
	private Label VAT;

	@FXML
	private Label noVAT;

	@FXML
	private Label shippingFees;

	@FXML
	private Label total;

	@FXML
	private Label lbtime;

	@FXML
	private Label lbrushInstruction;

	@FXML
	private VBox vboxItems;

	private Order order;

	public PlaceOrderController getBController() {
		return (PlaceOrderController) super.getBController();
	}

	public InvoiceScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.order = order;
		this.setBController(new PlaceOrderController());
		this.province.getItems().addAll(Configs.PROVINCES);
	
		lbtime.setVisible(false);
		lbrushInstruction.setVisible(false);
		time.setVisible(false);
		rushInstruction.setVisible(false);

//		chooseShip.selectedProperty().addListener(new ChangeListener<Boolean>() {
//			@Override
//			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
//				if (isNowSelected) {
//					String status = getBController().validateRushShipping(order);
//					if(status.equals("EMPTY")){
//						try {
//							PopupScreen.error("Empty province");
//							chooseShip.setSelected(false);
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//					else if(status.equals("ADDRESS_NOT_SUPPORT")){
//						try {
//							PopupScreen.error("Address not support");
//							chooseShip.setSelected(false);
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//					else if(status.equals("PRODUCT_NOT_SUPPORT")){
//						try {
//							PopupScreen.error("Product not support");
//							chooseShip.setSelected(false);
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//					else{
//						lbtime.setVisible(true);
//						lbrushInstruction.setVisible(true);
//						time.setVisible(true);
//						rushInstruction.setVisible(true);
//					}
//				} else {
//					lbtime.setVisible(false);
//					lbrushInstruction.setVisible(false);
//					time.setVisible(false);
//					rushInstruction.setVisible(false);
//				}
//			}
//		});

//		province.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
//			try {
//				updateShippingFees(newValue);
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});


	}

	@FXML
	void confirmInvoice(MouseEvent event) throws IOException {
		BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, order);
		paymentScreen.setBController(new PaymentController());
		paymentScreen.setPreviousScreen(this);
		paymentScreen.setHomeScreenHandler(homeScreenHandler);
		paymentScreen.setScreenTitle("Payment Screen");
		paymentScreen.show();
		LOGGER.info("Confirmed invoice");
	}

}
