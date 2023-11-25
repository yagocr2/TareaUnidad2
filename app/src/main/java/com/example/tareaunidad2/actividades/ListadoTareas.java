package com.example.tareaunidad2.actividades;

import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tareaunidad2.datos.Tarea;

import java.util.ArrayList;

public class ListadoTareas extends AppCompatActivity {
    private RecyclerView rv;
    private TextView listadoVacio;
    private MenuItem priority;
    private ArrayList<Tarea>tareas = new ArrayList<>();
}
