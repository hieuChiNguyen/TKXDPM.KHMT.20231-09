package views.screen.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.order.OrderMedia;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;

public class AdminConfirmOrderScreenHandler extends BaseScreenHandler{
	private static Logger LOGGER = Utils.getLogger(AdminConfirmOrderScreenHandler.class.getName());
	
	@FXML private VBox DetailTable;
    @FXML private TableView<OrderMedia> tableview;
    @FXML private TableColumn<?, ?> col_customerID;
    @FXML private TableColumn<?, ?> col_num;
    @FXML private TableColumn<?, ?> col_status;
    @FXML private TableColumn<?, ?> col_time;
    @FXML private TableColumn<?, ?> col_totalDrink;
    @FXML private TableColumn<?, ?> col_totalPrice;
    List<OrderMedia> OrderTable;

    @FXML private TableView<OrderMedia> tableview1;
    @FXML private TableColumn<?, ?> col_Dnum;
    @FXML private TableColumn<?, ?> col_DPrice;
    @FXML private TableColumn<?, ?> col_DQuantity;
    @FXML private TableColumn<?, ?> col_dDrink;
    @FXML private TableColumn<?, ?> col_DTotalPrice;
    List<OrderMedia> OrderDetailTable;

    @FXML private Button cancelOrderBtn;
    @FXML private Button confirmOrderBtn;
    
//    private List<OrderMedia> orderMediaDetails = Cart.getCart().getListMedia();

    public AdminConfirmOrderScreenHandler(Stage stage, String screenPath, List<OrderMedia> orderMediaDetails) throws IOException {
        super(stage, screenPath);
//        this.orderMediaDetails = orderMediaDetails;
    }

//    public void setOrderDetails(List<OrderMedia> orderMediaDetails) {
//        // Cập nhật danh sách chi tiết đơn hàng mới
//        this.orderMediaDetails = orderMediaDetails;
//
//        // Xóa toàn bộ nội dung hiện tại trong tableview1
//        tableview1.getItems().clear();
//
//        // Thêm các thông tin chi tiết đơn hàng mới
//        for (OrderMedia orderMediaDetail : orderMediaDetails) {
//            // Tạo một dòng mới trong bảng
//            TableRow<OrderMedia> row = new TableRow<>();
//            tableview1.getItems().add(orderMediaDetail);
//
//            // Cập nhật các cột của dòng
//            col_Dnum.setCellValueFactory(new PropertyValueFactory<>("number"));
//            col_dDrink.setCellValueFactory(new PropertyValueFactory<>("productName"));
//            col_DPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
//            col_DQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//            col_DTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
//        }
//    }

}
