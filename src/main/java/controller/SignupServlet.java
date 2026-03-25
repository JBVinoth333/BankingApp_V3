package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DublicateAadhaarException;
import model.DublicateUserException;
import model.InvalidInputException;
import service.UserService;

@WebServlet("/api/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse res) throws IOException  {

        StringBuilder sb = new StringBuilder();
        JSONObject responseObject = new JSONObject();
        BufferedReader reader  = null ;
        try {
            UserService service = new UserService();
            reader = request.getReader();
            String line = "";
            while((line=reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject requestObject = new JSONObject(sb.toString());
            String name = requestObject.getString("name");
            if(!name.matches("^[a-zA-Z ]+$")) {
                throw new InvalidInputException("Name should not be numbers");
            }
            String aadhaar = requestObject.getString("aadhaar");
            if(!(aadhaar.replaceAll(" ","")).matches("^[2-9]{1}[0-9]{11}$")) {
                throw new InvalidInputException("Aadhaar should be numbers only");
            }
            int yob = requestObject.getInt("yob");
            if(!String.valueOf(yob).matches("^\\d{4}$")) {
                throw new InvalidInputException("Invalid Year of birth");
            }
            String mobile = requestObject.getString("mobile");
            if(!mobile.matches("^(?:\\+91|91)?[6-9]\\d{9}$")) {
                throw new InvalidInputException("Invalid mobile number");
            }
            String email = requestObject.getString("email");
            String address = requestObject.getString("address");
            double balance =requestObject.getDouble("balance");
            String loginPin = requestObject.getString("login");
            String confirmPin = requestObject.getString("confirm");


            if(service.createAccount(name,aadhaar,yob,mobile,email,address,balance,loginPin)) {
                responseObject.put("name",name);
                responseObject.put("email",email );
                responseObject.put("accountNumber",2424);
                responseObject.put("status", 201);
                responseObject.put("message", "Account created successfully");
            }else {
                responseObject.put("name",name);
                responseObject.put("email",email );
                responseObject.put("status", 200);
                responseObject.put("message", "Account created Unsuccessfull");
            }
        } catch (JSONException e) {
            responseObject.put("status",500);
            responseObject.put("message", e.getMessage());
        } catch (DublicateAadhaarException e) {
            responseObject.put("status", 200);
            responseObject.put("message", e.getMessage());
        } catch (SQLException e) {
            responseObject.put("status", 500);
            responseObject.put("message", e.getMessage());
        } catch (DublicateUserException e) {
            responseObject.put("status", 200);
            responseObject.put("message",e.getMessage());
        } catch (IOException e) {
            responseObject.put("status", 500);
            responseObject.put("message", e.getMessage());
        } catch (InvalidInputException e) {
            responseObject.put("status", 400);
            responseObject.put("message",e.getMessage());
        }

        try {
            res.getWriter().write(responseObject.toString());
        } catch (IOException e) {
            responseObject.put("status", 500);
            responseObject.put("message", e.getMessage());
            res.getWriter().write(responseObject.toString());
        }
    }

}
