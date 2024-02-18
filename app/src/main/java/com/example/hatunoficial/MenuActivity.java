package com.example.hatunoficial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



import android.Manifest;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private static final int REQUEST_SMS_PERMISSION = 123;
    TextView tvci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvci = findViewById(R.id.tvMenu);

        Intent intent = getIntent();

        String ci = intent.getStringExtra("ci");

        String nombre = intent.getStringExtra("nombre");
        String paterno = intent.getStringExtra("paterno");
        String materno = intent.getStringExtra("materno");
        String Correo_Usu = intent.getStringExtra("Correo_Usu");
        String ciudad = intent.getStringExtra("ciudad");
        String sexo = intent.getStringExtra("sexo");
        String direccion = intent.getStringExtra("direccion");
        String celular = intent.getStringExtra("celular");

        tvci.setText("Bienvenido "+nombre+" "+paterno+" "+materno);
    }
    public void iniciS(View vista){
        Intent nv = new Intent(this, DenunciaActivity.class);
        Intent intent = getIntent();
        nv.putExtra("ci",intent.getStringExtra("ci"));
        startActivity(nv);
    }
    public void contactosButton(View vista){
        Intent nv = new Intent(this, ContactosActivity.class);
        Intent intent = getIntent();
        nv.putExtra("ci",intent.getStringExtra("ci"));
        startActivity(nv);
    }

    public void numero(View vista){
        Intent nv = new Intent(this, NumerosActivity.class);
        startActivity(nv);
    }
    public void llamar(View vista){
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:103"));
        startActivity(intent);
    }
    public void BotonCuenta(View vista){
        Intent nv = new Intent(this, CuentaActivity.class);
        Intent intent = getIntent();
        nv.putExtra("ci",intent.getStringExtra("ci"));
        nv.putExtra("nombre",intent.getStringExtra("nombre"));
        nv.putExtra("paterno",intent.getStringExtra("paterno"));
        nv.putExtra("materno",intent.getStringExtra("materno"));

        nv.putExtra("Correo_Usu",intent.getStringExtra("Correo_Usu"));
        nv.putExtra("ciudad",intent.getStringExtra("ciudad"));
        nv.putExtra("sexo",intent.getStringExtra("sexo"));
        nv.putExtra("direccion",intent.getStringExtra("direccion"));
        nv.putExtra("celular",intent.getStringExtra("celular"));
        startActivity(nv);
    }


    public void enviarM(View vista){
        String msj = "Necito ayuda este es mi ubicacion https://maps.app.goo.gl/ZZSHcHRKsTQbf7JT9";
        String numeroTel = "+000000000";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String uri = "whatsapp://send?phone=" + numeroTel + "&text=" + msj;
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }





}