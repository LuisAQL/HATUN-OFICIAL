package com.example.hatunoficial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edCorreo_Usu, edContrasenia;
    Button btnLogin;
    String serverURL = "https://paranoid-magnets.000webhostapp.com/proyecto281/validar_usuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edCorreo_Usu = findViewById(R.id.correoLogin);
        edContrasenia = findViewById(R.id.passLogin);
        btnLogin = findViewById(R.id.botoniniciarsesion);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usuario = edCorreo_Usu.getText().toString();
                final String password = edContrasenia.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String ci = jsonResponse.getString("ci");
                                String nombre = jsonResponse.getString("nombre");
                                String paterno = jsonResponse.getString("paterno");
                                String materno = jsonResponse.getString("materno");
                                String ciudad = jsonResponse.getString("ciudad");
                                String sexo = jsonResponse.getString("sexo");
                                String direccion = jsonResponse.getString("direccion");
                                String celular = jsonResponse.getString("celular");
                                String esta_civil = jsonResponse.getString("esta_civil");
                                String fecha_naci = jsonResponse.getString("fecha_naci");

                                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                intent.putExtra("ci", ci);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("paterno", paterno);
                                intent.putExtra("materno", materno);
                                intent.putExtra("Correo_Usu", usuario);
                                intent.putExtra("Contrasenia", password);
                                intent.putExtra("ciudad", ciudad);
                                intent.putExtra("sexo", sexo);
                                intent.putExtra("direccion", direccion);
                                intent.putExtra("celular", celular);
                                intent.putExtra("esta_civil", esta_civil);
                                intent.putExtra("fecha_naci", fecha_naci);

                                MainActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Error Login").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Crear y agregar la solicitud al servidor
                LoginRequest loginRequest = new LoginRequest(usuario, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    public void moveToRegistration(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        finish();
    }
}
