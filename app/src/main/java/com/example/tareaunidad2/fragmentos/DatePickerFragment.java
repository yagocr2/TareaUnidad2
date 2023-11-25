package com.example.tareaunidad2.fragmentos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    // Interfaz para manejar la fecha seleccionada.
    private DatePickerDialog.OnDateSetListener listener;

    // Método estático para crear una nueva instancia de DatePickerFragment.
    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
        // Se crea una nueva instancia de DatePickerFragment.
        DatePickerFragment fragment = new DatePickerFragment();
        // Se establece el listener para manejar la fecha seleccionada.
        fragment.setListener(listener);
        // Se devuelve la instancia creada.
        return fragment;
    }
    // Método para establecer el listener para manejar la fecha seleccionada.
    public void setListener(DatePickerDialog.OnDateSetListener listener){
        this.listener = listener;
    }

    // Método que se llama para crear el diálogo de selección de fecha.
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle saverdInstance){
        // Se obtiene una instancia del calendario con la fecha actual.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Se verifica si los valores de año, mes y día son válidos.
        if(getActivity().getLocalClassName().equals("actividades.EditarActivity")){
            // Se obtienen los argumentos pasados al fragmento.
            int anyo = getArguments().getInt("anyo");
            int mes = getArguments().getInt("mes");
            int dia = getArguments().getInt("dia");

            // Se verifica si los valores de año, mes y día son válidos.
            if (anyo<= year && mes >= 0 && mes<= 12 && dia >=1 && dia <= 31){
                // Se devuelve un nuevo diálogo de selección de fecha con los valores proporcionados.
                return new DatePickerDialog(getActivity(),listener, anyo,mes,dia );
            }else{
                // Si los valores no son válidos, se devuelve un diálogo con la fecha actual.
                return new DatePickerDialog(getActivity(), listener, year, month, day);
            }
        }else{
            // Si la actividad no es "EditarActivity", se devuelve un diálogo con la fecha actual.
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }
    }

}
