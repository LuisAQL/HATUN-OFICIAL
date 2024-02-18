package com.example.hatunoficial;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "https://paranoid-magnets.000webhostapp.com/proyecto281/login.php";

    private Map<String, String> params;

    public LoginRequest(String Correo_Usu, String Contrasenia, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Correo_Usu", Correo_Usu);
        params.put("Contrasenia", Contrasenia);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
