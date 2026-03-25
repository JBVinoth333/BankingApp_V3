package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import utils.RequestUtil;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject responseObject = new JSONObject();
        try {
            UserService service = new UserService();
            JSONObject requestObject = RequestUtil.parseRequestBody(request);
            String accountNumber = requestObject.getString("accountNumber");
            String pin = requestObject.getString("pin");

            ResultSet rs = service.login(accountNumber, pin);
            if (rs != null) {
                responseObject.put("status", 200);
                responseObject.put("message", "Login successful");
                responseObject.put("accountNumber", rs.getString("AccountNumber"));
                responseObject.put("balance", rs.getDouble("Balance"));
                responseObject.put("customerName", rs.getString("CustomerName"));
                responseObject.put("email", rs.getString("Email"));
            } else {
                responseObject.put("status", 401);
                responseObject.put("message", "Invalid account number or PIN");
            }
        } catch (JSONException e) {
            responseObject.put("status", 400);
            responseObject.put("message", e.getMessage());
        } catch (SQLException e) {
            responseObject.put("status", 500);
            responseObject.put("message", "Internal server error");
        }
        response.setContentType("application/json");
        response.getWriter().write(responseObject.toString());
    }

}
