package com.example.hatunoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ContactosActivity extends AppCompatActivity {

    private Spinner relationshipSpinner;
    private EditText numeroEditt,parentescoEditt;

    TextView text;
    public static final int numero = 0;

    Button btn_insert;
    private Spinner optionalRelationshipSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        // Define las opciones de relación
//        String[] relaciones = {"Selecciona tu Parentesco","Padre", "Madre", "Hermano", "Abuelo", "Tío", "Otros"};
//
//        // Crea un ArrayAdapter para llenar los Spinners
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, relaciones);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Asigna el ArrayAdapter a los Spinners
//        relationshipSpinner.setAdapter(adapter);
//        optionalRelationshipSpinner.setAdapter(adapter);

        numeroEditt = findViewById(R.id.editTextNumero);
        parentescoEditt = findViewById(R.id.edittextParentesco);
        text = findViewById(R.id.tvSeccion);

        Intent intent = getIntent();
        text.setText(intent.getStringExtra("ci").toString());

        btn_insert = findViewById(R.id.botoninsertar);
        final String Ci = getIntent().getStringExtra("Ci");
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                insertData();


            }
        });
    }

    private void insertData() {
        Intent intent = getIntent();
        final String Numero_Cont = numeroEditt.getText().toString().trim();
        final String Empresa = parentescoEditt.getText().toString().trim();
        final String Ci = intent.getStringExtra("ci").toString();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(Numero_Cont.isEmpty()){


            numeroEditt.setError("complete los campos");
            return;
        }
        else if(Empresa.isEmpty()){

            parentescoEditt.setError("complete los campos");
            return;
        }



        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://paranoid-magnets.000webhostapp.com/proyecto281/insertarcontactos.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Datos insertados")){

                                Toast.makeText(ContactosActivity.this, "Datos insertados", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                                startActivity(intent);
                                progressDialog.dismiss();


                            }
                            else{
                                Toast.makeText(ContactosActivity.this, response, Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
                                Toast.makeText(ContactosActivity.this, "No se puede insrtar", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ContactosActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("Numero_Cont",Numero_Cont);
                    params.put("Empresa",Empresa);
                    params.put("Ci",Ci);


                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(ContactosActivity.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}