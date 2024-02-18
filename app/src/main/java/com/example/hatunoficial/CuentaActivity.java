package com.example.hatunoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CuentaActivity extends AppCompatActivity {

    TextView Tci,nombreC,Tcorreo,Tciudad,Tsexo,Tdireccion,Tcelular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        Tci = findViewById(R.id.tvCi);
        nombreC = findViewById(R.id.tvNombreC);
        Tcorreo = findViewById(R.id.tvCorreo);
        Tciudad = findViewById(R.id.tvCiudad);
        Tsexo = findViewById(R.id.tvSexo);
        Tdireccion = findViewById(R.id.tvDireccion);
        Tcelular = findViewById(R.id.tvCelular);

        Intent intent = getIntent();
        String ci = intent.getStringExtra("ci").toString();
        String nombre = intent.getStringExtra("nombre").toString();
        String paterno = intent.getStringExtra("paterno").toString();
        String materno = intent.getStringExtra("materno").toString();
        String Correo_Usu = intent.getStringExtra("Correo_Usu").toString();
        String ciudad = intent.getStringExtra("ciudad").toString();
        String sexo = intent.getStringExtra("sexo").toString();
        String direccion = intent.getStringExtra("direccion").toString();
        String celular = intent.getStringExtra("celular").toString();
        Tci.setText(ci);
        nombreC.setText(nombre+" "+paterno+" "+materno);
        Tcorreo.setText(Correo_Usu);
        Tciudad.setText(ciudad);
        Tsexo.setText(sexo);
        Tdireccion.setText(direccion);
        Tcelular.setText(celular);
    }
}