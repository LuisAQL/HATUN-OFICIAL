package com.example.hatunoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class NumerosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);
    }
    public void llamar1(View vista){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:156"));
        startActivity(intent);
    }
    public void llamar2(View vista){
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:110"));
        startActivity(intent);
    }
    public void llamar3(View vista){
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:80059756"));
        startActivity(intent);
    }
}