package com.example.tareaunidad2.fragmentos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareaunidad2.R;
import com.example.tareaunidad2.viewmodel.TareaViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragmento1 extends Fragment {
    private TareaViewModel tareaViewModel;
    private EditText titulo, fechaCreacion, fechaObjetivo;
    private Spinner progreso;
    private CheckBox prioritaria;

    public interface OnBotonSiguienteClickListener{
        void onBotonSiguienteClicked();
    }
    private OnBotonSiguienteClickListener listenerSiguienteEnActividad;

    public interface OnBotonCancelarCLickListener{
        void onBotonCancelarClickListener();
    }

    private OnBotonCancelarCLickListener listenerCancelarEnActividad;

    public Fragmento1(){}
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnBotonSiguienteClickListener) {  //Si la clase implementa la interfaz
            listenerSiguienteEnActividad = (OnBotonSiguienteClickListener) context; //La clase se convierte en escuchadora
        } else {
            throw new ClassCastException(context + " debe implementar OnBotonSiguienteClickListener");
        }
        if (context instanceof OnBotonCancelarCLickListener) {  //Si la clase implementa la interfaz
            listenerCancelarEnActividad = (OnBotonCancelarCLickListener) context; //La clase se convierte en escuchadora
        } else {
            throw new ClassCastException(context + " debe implementar OnBotonCancelarClickListener");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Vinculamos el fragmento con el ViewModel
        tareaViewModel = new ViewModelProvider(requireActivity()).get(TareaViewModel.class);

        //Inflamos el fragmento
        return inflater.inflate(R.layout.fragmento_uno, container,false);
    }

    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //Bindeamos y setteamos el EditText Titulo
        titulo = view.findViewById(R.id.etTitulo);
        @Nullable String title = tareaViewModel.getTitulo();
        if(title!=null){
            titulo.setText(title);
        }
        //Binding, config y set EditText Fechas
        fechaCreacion = view.findViewById(R.id.etFechaCreacion);
        fechaCreacion.setOnClickListener(this::listenerFecha);
        @Nullable String createDate = tareaViewModel.getFechaCreacion();
        if(createDate != null)
            fechaCreacion.setText(createDate);
        else{
            Date fechaActual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaCreacion.setText(formato.format(fechaActual));
        }
        fechaObjetivo = view.findViewById(R.id.dFechaObjetivo);
        fechaObjetivo.setOnClickListener(this::listenerFecha);
        @Nullable String target_date = tareaViewModel.getFechaObjetivo();
        if(target_date != null)
            fechaObjetivo.setText(target_date);
        else{
            Date fechaActual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaObjetivo.setText(formato.format(fechaActual));
        }

        //Binding, config y set Spinner Progreso


        //Binding y config Button Siguiente
        Button siguiente = view.findViewById(R.id.btSiguiente);
        siguiente.setOnClickListener(v -> {
            //Escribimos en el ViewModel
            tareaViewModel.setTitulo(titulo.getText().toString());
            tareaViewModel.setFechaCreacion(fechaCreacion.getText().toString());
            tareaViewModel.setFechaObjetivo(fechaObjetivo.getText().toString());
            tareaViewModel.setProgreso(25 * progreso.getSelectedItemPosition());
            tareaViewModel.setPrioritaria(prioritaria.isChecked());
            //Llamamos al método onBotonSiguienteClicked que está implementado en la actividad.
            if (listenerSiguienteEnActividad != null) {
                listenerSiguienteEnActividad.onBotonSiguienteClicked();
            }
        });

        //Binding y config Button Cancelar
        Button cancelar = view.findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(v -> {
            //Llamamos al método onBotonCancelarClicked que está implementado en la actividad.
            if (listenerCancelarEnActividad != null) {
                listenerCancelarEnActividad.onBotonCancelarClickListener();
            }
        });
        progreso = view.findViewById(R.id.spProgreso);
        String[] progresos = requireContext().getResources().getStringArray(R.array.progreso_texto);
        ArrayAdapter<String> adaptadorProgreso = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, progresos);
        adaptadorProgreso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        progreso.setAdapter(adaptadorProgreso);
        int progress = tareaViewModel.getProgreso();
        progreso.setSelection(progress/25); //Situamos el spinner en la posición calculada como progreso/25

        //Binding y set CheckBox Prioritaria
        prioritaria = view.findViewById(R.id.chbPrioridad);
        boolean prior = tareaViewModel.isPrioritaria();
        prioritaria.setChecked(prior);
    }
    private void listenerFecha(View view){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anno, int mes, int dia) {
                // mes+1 ya que Enero es 0
                final String selectedDate = String.format("%02d/%02d/%04d",dia,(mes+1),anno);
                ((EditText)view).setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}
