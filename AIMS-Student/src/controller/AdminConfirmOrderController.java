package controller;

import entity.order.Order;
import entity.order.OrderMedia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AdminConfirmOrderController extends BaseController {
	private static Logger LOGGER = utils.Utils.getLogger(AdminConfirmOrderController.class.getName());

	public ObservableList<Order> getAllOrders() throws SQLException {
		return FXCollections.observableArrayList(Order.getAllOrders());
	}

	public int saveNewOrder(Order order) throws SQLException {
		Integer id = Order.saveNewOrder(order);
		return id;
	}

	public void updateOrderStatus(Integer id, String newState) throws SQLException {
		Order.updateOrderStatus(id, newState);
	}

	public ObservableList<OrderMedia> getDetailOrder(Integer orderId) {
        return null;
    }
}

