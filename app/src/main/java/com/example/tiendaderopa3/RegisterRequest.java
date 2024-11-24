package com.example.tiendaderopa3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://192.168.1.39:80/Register.php";


    private final Map<String, String> params;

    public RegisterRequest(String username, String email, int age, String password, Response.Listener<String> listener, Response.ErrorListener errorlistener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, errorlistener);


        params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("age", String.valueOf(age));
        params.put("password", password);
    }


    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}