import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        @Override
        public void handle(HttpExchange exchange) throws IOException {
        	 if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                 // Xử lý sự kiện bấm nút ở đây
                 System.out.println("Nút đã được bấm!");
                 
                 // Phản hồi thành công
                 String response = "Yêu cầu đã được xử lý thành công!";
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
