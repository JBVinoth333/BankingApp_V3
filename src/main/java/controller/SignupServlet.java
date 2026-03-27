package controller;

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
import utils.RequestUtil;
import utils.ValidationUtil;

@WebServlet("/api/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        JSONObject json = new JSONObject();

        try {
            JSONObject body = RequestUtil.parseRequestBody(request);
            String name = ValidationUtil.validateName(body.getString("name"));
            String aadhaar = ValidationUtil.validateAadhaar(body.getString("aadhaar"));
            int yob = ValidationUtil.validateYob(body.getInt("yob"));
            String mobile = ValidationUtil.validateMobile(body.getString("mobile"));
            String email = body.getString("email");
            String address = body.getString("address");
            double balance = body.getDouble("balance");
            String loginPin = body.getString("login");

            UserService service = new UserService();
            String accountNumber = service.createAccount(name, aadhaar, yob, mobile, email, address, balance, loginPin);

            if (accountNumber != null) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                json.put("message", "Account created successfully");
                json.put("name", name);
                json.put("email", email);
                json.put("accountNumber", accountNumber);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                json.put("message", "Account creation failed");
            }
        } catch (InvalidInputException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            json.put("message", e.getMessage());
        } catch (DublicateAadhaarException | DublicateUserException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            json.put("message", e.getMessage());
        } catch (JSONException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            json.put("message", "Invalid request body");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            json.put("message", "Internal server error");
        }

        response.getWriter().write(json.toString());
    }
}
