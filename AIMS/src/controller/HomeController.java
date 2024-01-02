package controller;

import java.sql.SQLException;
import java.util.List;

import entity.cart.Cart;
import entity.media.Media;

/**
 * This class controls the flow of events in homescreen
 * 
 * @author nguyenlm
 */
//functional cohesion
public class HomeController extends BaseController {

    /**
     * this method gets all Media in DB and return back to home to display
     * 
     * @return List[Media]
     * @throws SQLException
     */
    // data coupling
    public List getAllMedia() throws SQLException {
        return new Media().getAllMedia();
    }
    //OCP
    //tạo 1 interface hoặc abstract có 1 phương thức getAllMedia để có thể triển khai nó với các lớp cụ thể cho các loại Media khác nhau mà không làm ảnh hưởng đến HomeController

}
