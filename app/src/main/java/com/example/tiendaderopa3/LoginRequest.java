package com.example.tiendaderopa3;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://192.168.1.39:80/Login.php";


    private final Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {

        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);


        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }


    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}
