package com.example.tareaunidad2.actividades;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.tareaunidad2.R;
import com.example.tareaunidad2.viewmodel.TareaViewModel;

public class CrearActivity  extends AppCompatActivity{
    private TareaViewModel tareaViewModel;
    private FragmentManager miManejador;
    private String titulo, descripcion;
    private String fechaCreacion, fechaObjetivo;
    private int progreso;
    private boolean prioritaria;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.);
    }
}
