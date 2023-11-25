package com.example.tareaunidad2.fragmentos;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.tareaunidad2.viewmodel.TareaViewModel;

public class Fragmento1 extends Fragment {
    private TareaViewModel tareaViewModel;
    private EditText titulo, fechaCreacion, fechaObjetivo;
    private Spinner progreso;
    private CheckBox prioritaria;

    public interface OnBotonSiguiente
}
