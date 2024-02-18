package com.example.hatunoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;


import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
public class RegisterActivity extends AppCompatActivity {

    private SimpleDateFormat dateFormat;
    private Spinner spinnerSexo,spinnerCiudad,spinnerEstado;
    private EditText ciEditText, nombreEditText, apellidoPaternoEditText, apellidoMaternoEditText, correoEditText, contraseniaEditText, direccionEditText, celularEditText, estadoCivilEditText, fechaNaciEditText;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ciEditText = findViewById(R.id.editTextCi1);
        nombreEditText = findViewById(R.id.editTextNombreUsu1);
        apellidoPaternoEditText = findViewById(R.id.editTextApellidosPaterno1);
        apellidoMaternoEditText = findViewById(R.id.editTextApellidoMaterno);
        correoEditText = findViewById(R.id.editTextCorreoUsu);
        contraseniaEditText = findViewById(R.id.editTextContrasenia);
        spinnerCiudad = findViewById(R.id.spinnerCiudades);
        spinnerSexo = findViewById(R.id.spinnerSexo);
        direccionEditText = findViewById(R.id.editTextDireccion);
        celularEditText = findViewById(R.id.editTextCelular);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        fechaNaciEditText = findViewById(R.id.editTextFechaNaci);


        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        fechaNaciEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        btn_insert = findViewById(R.id.botonRegistrar);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
//
//
//                RegisterActivity.this.startActivity(intent);


                insertData();

                Intent nv = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(nv);
            }
        });
    }
    private void insertData() {

        final String ci = ciEditText.getText().toString().trim();
        final String nombre = nombreEditText.getText().toString().trim();
        final String paterno = apellidoPaternoEditText.getText().toString().trim();
        final String materno = apellidoMaternoEditText.getText().toString().trim();
        final String Correo_Usu = correoEditText.getText().toString().trim();
        final String Contrasenia = contraseniaEditText.getText().toString().trim();
        final String ciudad = spinnerCiudad.getSelectedItem().toString().trim();
        final String sexo = spinnerSexo.getSelectedItem().toString().trim();
        final String direccion = direccionEditText.getText().toString().trim();
        final String celular = celularEditText.getText().toString().trim();
        final String esta_civil = spinnerEstado.getSelectedItem().toString().trim();

        final String fecha_naci = fechaNaciEditText.getText().toString().trim();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(ci.isEmpty()){


            ciEditText.setError("complete los campos");
            return;
        }
        else if(nombre.isEmpty()){

            nombreEditText.setError("complete los campos");
            return;
        }
        else if(paterno.isEmpty()){

            apellidoPaternoEditText.setError("complete los campos");
            return;
        }else if(materno.isEmpty()){

            apellidoMaternoEditText.setError("complete los campos");
            return;
        }else if(Correo_Usu.isEmpty()){

            correoEditText.setError("complete los campos");
            return;
        }else if(Contrasenia.isEmpty()){

            contraseniaEditText.setError("complete los campos");
            return;
        }else if(direccion.isEmpty()){

            direccionEditText.setError("complete los campos");
            return;
        }else if(celular.isEmpty()){

            celularEditText.setError("complete los campos");
            return;
        }


        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://paranoid-magnets.000webhostapp.com/proyecto281/insetar.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Datos insertados")){



                                Toast.makeText(RegisterActivity.this, "Datos insertados", Toast.LENGTH_SHORT).show();




                                progressDialog.dismiss();



                            }
                            else{
                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "No se puede insrtar", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("ci",ci);
                    params.put("nombre",nombre);
                    params.put("paterno",paterno);
                    params.put("materno",materno);
                    params.put("Correo_Usu",Correo_Usu);
                    params.put("Contrasenia",Contrasenia);
                    params.put("ciudad",ciudad);
                    params.put("sexo",sexo);
                    params.put("direccion",direccion);
                    params.put("celular",celular);
                    params.put("esta_civil",esta_civil);
                    params.put("fecha_naci",fecha_naci);










                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(request);



        }




    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // La fecha seleccionada se muestra en el EditText en el formato deseado.
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                fechaNaciEditText.setText(dateFormat.format(selectedDate.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}