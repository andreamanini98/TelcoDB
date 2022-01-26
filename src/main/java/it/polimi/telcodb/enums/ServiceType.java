package it.polimi.telcodb.enums;

public enum ServiceType {

    FIXED_PHONE("FixedPhone"),
    MOBILE_PHONE("MobilePhone"),
    FIXED_INTERNET("FixedInternet"),
    MOBILE_INTERNET("MobileInternet");

    private final String type;

    ServiceType(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }


    public static ServiceType fromType(String type) {
        switch (type) {
            case "FixedPhone":
                return ServiceType.FIXED_PHONE;

            case "MobilePhone":
                return ServiceType.MOBILE_PHONE;

            case "FixedInternet":
                return ServiceType.FIXED_INTERNET;

            case "MobileInternet":
                return ServiceType.MOBILE_INTERNET;

            default:
                throw new IllegalArgumentException("Invalid name");
        }
    }

}
