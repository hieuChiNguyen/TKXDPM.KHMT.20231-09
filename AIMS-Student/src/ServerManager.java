import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import entity.order.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ServerManager {
	
	private static HttpServer server;
	
    public static void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/vnpay_jsp/vnpay_return.jsp", new MyHandler());
        server.setExecutor(null);
        System.out.println("Server started !");
        server.start();
    }
    
    public static void stopServer() {
        if (server != null) {
            server.stop(0);
        }
    }

    static class MyHandler implements HttpHandler {
    	private Order selectedOrder;
        @Override
        public void handle(HttpExchange exchange) throws IOException {
        	 if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                 // Xử lý sự kiện bấm nút ở đây
                 System.out.println("Nút đã được bấm!");
                 String response= "";
                 String id = "";
                 String query = exchange.getRequestURI().getQuery();
                 System.out.println(query);
                 String[] params = query.split("&"); // Tách các cặp key=value
                  
                 // Duyệt qua các cặp key=value để tìm order_id
                 for (String param : params) {
                     String[] keyValue = param.split("=");
                     if (keyValue.length == 2 && keyValue[0].equals("order_id")) {
                         id = keyValue[1];
                         System.out.println("order_id: " + id);
                         break;
                     }
                 }
                 try {
                	 Order order = selectedOrder.getOrderById(Integer.parseInt(id));
                	 if(order != null) {
                		 System.out.println(order.getStatus());
                		 if(order.getStatus().equals("CHỜ DUYỆT")) {
                			 order.deleteOrderById(Integer.parseInt(id));
                    		 response = "Order cancelled successfully !!"; 
                		 }else {
                			 response = "The order has been declined, and cannot be cancelled.!!"; 
                		 
                		 }
                	 }else {
                		 response = " Cannot find the order!!"; 
                	 }
				} catch (SQLException e) {
					System.out.println(e);
					throw new RuntimeException(e);
				}
            
                 
                 
                 exchange.sendResponseHeaders(200, response.getBytes().length);
                 OutputStream os = exchange.getResponseBody();
                 os.write(response.getBytes());
                 os.close();
             } else {
                 // Trả về nội dung file HTML khi không phải là yêu cầu POST
                 String rootPath = "src/paymentInfo.html"; // Đường dẫn tới file HTML của bạn
                 Path path = Paths.get(rootPath);
                 byte[] fileBytes = Files.readAllBytes(path);
                 exchange.sendResponseHeaders(200, fileBytes.length);
                 OutputStream os = exchange.getResponseBody();
                 os.write(fileBytes);
                 os.close();
             }
        }
    }
}
