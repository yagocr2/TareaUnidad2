package com.example.tareaunidad2.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tareaunidad2.R;

public class MainActivity extends AppCompatActivity{
    private Button btEmpezar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btEmpezar = findViewById(R.id.btn_Empezar);

    }

    private void empezar(View v){
        Intent aEmpezar = new Intent(this, ListadoTareasActivity.class);
        startActivity(aEmpezar);
    }
}
