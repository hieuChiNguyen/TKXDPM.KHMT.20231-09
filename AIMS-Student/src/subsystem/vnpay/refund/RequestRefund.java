package subsystem.vnpay.refund;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import subsystem.vnpay.refund.ConfigRefund;


public class RequestRefund {
	    private String requestId;
	    private String content;

	    public RequestRefund(String requestId, String content){
	        this.requestId = requestId;
	        this.content = content;
	    }

	    public String generateURL() throws UnsupportedEncodingException {
	        String orderType = "other";

	        String vnp_TxnRef = ConfigRefund.getRandomNumber(8);
	        String vnp_IpAddr = ConfigRefund.getIpAddress();

	        String vnp_TmnCode = ConfigRefund.vnp_TmnCode;

	        Map<String, String> vnp_Params = new HashMap<>();
	        vnp_Params.put("vnp_RequestId", requestId);
	        vnp_Params.put("vnp_Version", ConfigRefund.vnp_Version);
	        vnp_Params.put("vnp_Command", ConfigRefund.vnp_Command);
	        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
	        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
	        vnp_Params.put("vnp_OrderInfo", content);
	        vnp_Params.put("vnp_CurrCode", "VND");
	        vnp_Params.put("vnp_BankCode", "VNBANK");
	        
	       
	        vnp_Params.put("vnp_OrderType", orderType);
	        vnp_Params.put("vnp_Locale", "vn");
	        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

	        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String vnp_CreateDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

	        cld.add(Calendar.MINUTE, 15);
	        String vnp_ExpireDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

	        List fieldNames = new ArrayList(vnp_Params.keySet());
	        Collections.sort(fieldNames);
	        StringBuilder hashData = new StringBuilder();
	        StringBuilder query = new StringBuilder();
	        Iterator itr = fieldNames.iterator();
	        while (itr.hasNext()) {
	            String fieldName = (String) itr.next();
	            String fieldValue = (String) vnp_Params.get(fieldName);
	            if ((fieldValue != null) && (fieldValue.length() > 0)) {
	                //Build hash data
	                hashData.append(fieldName);
	                hashData.append('=');
	                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                //Build query
	                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
	                query.append('=');
	                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                if (itr.hasNext()) {
	                    query.append('&');
	                    hashData.append('&');
	                }
	            }
	        }
	        String queryUrl = query.toString();
	        String vnp_SecureHash = ConfigRefund.hmacSHA512(ConfigRefund.secretKey, hashData.toString());
	        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
	        System.out.println(ConfigRefund.vnp_PayUrl + "?" + queryUrl);
	        return ConfigRefund.vnp_PayUrl + "?" + queryUrl;
	    }
}
