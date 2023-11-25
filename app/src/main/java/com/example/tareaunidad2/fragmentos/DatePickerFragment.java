package com.example.tareaunidad2.fragmentos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;
    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        return fragment;
    }
    public void setListener(DatePickerDialog.OnDateSetListener listener){
        this.listener = listener;
    }
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle saverdInstance){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        if(getActivity().getLocalClassName().equals("actividades.EditarActivity")){
            int anyo = getArguments().getInt("anyo");
            int mes = getArguments().getInt("mes");
            int dia = getArguments().getInt("dia");
            if (anyo<= year && mes >= 0 && mes<= 12 && dia >=1 && dia <= 31){
                return new DatePickerDialog(getActivity(),listener, anyo,mes,dia );
            }else{
                return new DatePickerDialog(getActivity(), listener, year, month, day);
            }
        }else{
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }
    }

}
