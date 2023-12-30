package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.API;

public class InterbankBoundary {
	// data coupling
	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}
	//OCP:
	//phương thức query đang có thể bị thay đổi bởi các thay đổi trong cơ sở dữ liệu hoặc phương thức API.post(). Nếu API.post() thay đổi, sẽ cần chỉnh sửa lớp InterbankBoundary. 
	//Điều này làm tăng khả năng cần phải sửa đổi mã nguồn khi có sự thay đổi

	//khắc phục: 
	// package subsystem.interbank;

	// import common.exception.UnrecognizedException;
	// import utils.API;

	// public interface QueryExecutor {
	// 	String query(String url, String data);
	// }

	// public class APIQueryExecutor implements QueryExecutor {
	// 	@Override
	// 	public String query(String url, String data) {
	// 		try {
	// 			return API.post(url, data);
	// 		} catch (Exception e) {
	// 			throw new UnrecognizedException();
	// 		}
	// 	}
	// }

	// public class InterbankBoundary {
	// 	private final QueryExecutor queryExecutor;

	// 	public InterbankBoundary(QueryExecutor queryExecutor) {
	// 		this.queryExecutor = queryExecutor;
	// 	}

	// 	String query(String url, String data) {
	// 		return queryExecutor.query(url, data);
	// 	}
	// }

}
