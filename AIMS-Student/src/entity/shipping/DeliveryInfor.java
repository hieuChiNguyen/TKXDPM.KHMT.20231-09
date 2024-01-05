package entity.shipping;

import java.util.regex.Pattern;

public class DeliveryInfor {
   private int id;
   private String phoneNumber;
   private String shippingInstruction;
   private String name;
   private String province;
   private String shippingAddress;
   private String email;
   private boolean isRushShipping;
   private boolean isNormalShipping;

    public DeliveryInfor(){
        phoneNumber = "";
        shippingInstruction = "";
        name = "";
        province = "";
        shippingAddress = "";
        email = "";
        isRushShipping = false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShippingInstruction() {
        return shippingInstruction;
    }

    public void setShippingInstruction(String shippingInstruction) {
        this.shippingInstruction = shippingInstruction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRushShipping() {
        return isRushShipping;
    }

    public void setRushShipping(boolean rushShipping) {
        isRushShipping = rushShipping;
    }
    
    public boolean isNormalShipping() {
        return isNormalShipping;
    }

    public void setNormalShipping(boolean normalShipping) {
    	isNormalShipping = normalShipping;
    }
    
    public boolean isUrban(){
        if(getProvince().toLowerCase().contains("hà nội") || getProvince().toLowerCase().contains("hồ chí minh")) return true;
        return false;
    }

    public String validateDeliveryInfo(){
        if(!validateName()) return "INVALID_NAME";
        if(!validateEmail()) return "INVALID_EMAIL";
        if(!validatePhoneNumber()) return "INVALID_PHONENUMBER";
        return "VALID";
    }
    public boolean validatePhoneNumber() {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        if (!phoneNumber.matches("\\d+")) {
            return false;
        }
        if (phoneNumber.length() != 10) {
            return false;
        }
        return phoneNumber.charAt(0) == '0';
    }

    public boolean validateName() {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return name.matches("^[a-zA-Z ]*$");
    }

    public boolean validateEmail() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Pattern.matches(emailRegex, email);
    }

    public boolean validateAddressPlaceRushOrder() {
        if (province == null || province.isEmpty()) return false;
        String lowercaseAddress = province.toLowerCase();
        return lowercaseAddress.contains("hà nội");
    }

}
