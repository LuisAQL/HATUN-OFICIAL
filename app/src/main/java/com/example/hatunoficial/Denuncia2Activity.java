package com.example.hatunoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Denuncia2Activity extends AppCompatActivity {

    Button btn_insert;
    private EditText fechaD, detalleD, horaD, estadoD;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia2);

        fechaD = findViewById(R.id.editTextFechaDenuncia);
        detalleD = findViewById(R.id.editTexDescripcion);
        horaD = findViewById(R.id.editTextHora);
        estadoD = findViewById(R.id.editTextEstado);

        btn_insert = findViewById(R.id.buttonenviardenuncia);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertData() {

        final String Fecha_D = fechaD.getText().toString().trim();
        final String Detalles_D = detalleD.getText().toString().trim();
        final String Hora_D = horaD.getText().toString().trim();
        final String Estado_D = estadoD.getText().toString().trim();



        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(Detalles_D.isEmpty()){


            detalleD.setError("complete los campos");
            return;
        }
        else if(Detalles_D.isEmpty()){

            detalleD.setError("complete los campos");
            return;
        }
        else if(Hora_D.isEmpty()){

            horaD.setError("complete los campos");
            return;
        }else if(Estado_D.isEmpty()){

            estadoD.setError("complete los campos");
            return;
        }


        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://paranoid-magnets.000webhostapp.com/proyecto281/insetardenuncias.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Datos insertados")){

                                Toast.makeText(Denuncia2Activity.this, "Datos insertados", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                                startActivity(intent);
                                progressDialog.dismiss();


                            }
                            else{
                                Toast.makeText(Denuncia2Activity.this, response, Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
                                Toast.makeText(Denuncia2Activity.this, "No se puede insrtar", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Denuncia2Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("Fecha_D",Fecha_D);
                    params.put("Detalles_D",Detalles_D);
                    params.put("Hora_D",Hora_D);
                    params.put("Estado_D",Estado_D);










                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(Denuncia2Activity.this);
            requestQueue.add(request);



        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}