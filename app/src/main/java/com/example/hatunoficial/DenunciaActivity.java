package com.example.hatunoficial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class DenunciaActivity extends AppCompatActivity {

    Button btn_insert;
    Spinner estadoD;
    private EditText fechaD, detalleD, horaD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        fechaD = findViewById(R.id.editTextFecha);
        detalleD = findViewById(R.id.editTexDescripcion);
        horaD = findViewById(R.id.editTextHora);
        estadoD = findViewById(R.id.spinnerTipoD);

        btn_insert = findViewById(R.id.buttonenviardenuncia);


        Button btnFecha = findViewById(R.id.btnFecha);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DenunciaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Formatear la fecha seleccionada al formato "yyyy-MM-dd"
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                calendar.set(year, month, dayOfMonth);
                                String selectedDate = dateFormat.format(calendar.getTime());
                                fechaD.setText(selectedDate);
                            }
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }
        });


        Button btnHora = findViewById(R.id.btnHora);

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DenunciaActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                                horaD.setText(selectedTime);
                            }
                        },
                        hour, minute, true
                );

                timePickerDialog.show();
            }
        });
        final String ci = getIntent().getStringExtra("ci");

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                insertData();
            }
        });
    }

    private void insertData() {
        Intent intent = getIntent();
        final String Fecha_D = fechaD.getText().toString().trim();
        final String Detalles_D = detalleD.getText().toString().trim();
        final String Hora_D = horaD.getText().toString().trim();
        final String Estado_D = estadoD.getSelectedItem().toString().trim();
        final String ci = intent.getStringExtra("ci").toString();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(Detalles_D.isEmpty()){


            detalleD.setError("complete los campos");
            return;
        }



        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://paranoid-magnets.000webhostapp.com/proyecto281/insetardenuncias.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Datos insertados")){

                                Toast.makeText(DenunciaActivity.this, "Datos insertados", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                                startActivity(intent);
                                progressDialog.dismiss();


                            }
                            else{
                                Toast.makeText(DenunciaActivity.this, response, Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
                                Toast.makeText(DenunciaActivity.this, "No se puede insrtar", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DenunciaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    params.put("ci",ci);
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(DenunciaActivity.this);
            requestQueue.add(request);



        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}