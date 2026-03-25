package utils;

import model.InvalidInputException;

public class ValidationUtil {

    public static String validateName(String name) throws InvalidInputException {
        if (!name.matches("^[a-zA-Z ]+$")) {
            throw new InvalidInputException("Name should contain only letters and spaces");
        }
        return name;
    }

    public static String validateAadhaar(String aadhaar) throws InvalidInputException {
        if (!aadhaar.replaceAll(" ", "").matches("^[2-9]\\d{11}$")) {
            throw new InvalidInputException("Invalid Aadhaar number");
        }
        return aadhaar;
    }

    public static int validateYob(int yob) throws InvalidInputException {
        if (!String.valueOf(yob).matches("^\\d{4}$")) {
            throw new InvalidInputException("Invalid year of birth");
        }
        return yob;
    }

    public static String validateMobile(String mobile) throws InvalidInputException {
        if (!mobile.matches("^(?:\\+91|91)?[6-9]\\d{9}$")) {
            throw new InvalidInputException("Invalid mobile number");
        }
        return mobile;
    }
}
