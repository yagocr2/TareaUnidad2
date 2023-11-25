package com.example.tareaunidad2.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareaunidad2.R;
import com.example.tareaunidad2.viewmodel.TareaViewModel;

public class Fragmento2 extends Fragment {

    // Declaración de variables miembro.
    private TareaViewModel tareaViewModel;
    private EditText etDescripcion;
    private Button bt_Volver, bt_Guardar;

    // Interfaz para manejar el evento de hacer clic en el botón de guardar.
    public interface OnBotonGuardarClickListener {
        void onBotonGuardarClicked();
    }

    // Interfaz para manejar el evento de hacer clic en el botón de volver.
    public interface OnBotonVolverClickListener {
        void onBotonVolverClicked();
    }

    // Variables para almacenar las instancias de las interfaces.
    private Fragmento2.OnBotonGuardarClickListener listenerGuardarEnActividad;
    private Fragmento2.OnBotonVolverClickListener listenerVolverEnActivity;

    // Constructor privado para evitar instanciación sin parámetros.
    private Fragmento2() {}

    // Método llamado cuando el fragmento se adjunta a la actividad.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Verifica si la actividad implementa la interfaz OnBotonGuardarClickListener.
        if (context instanceof Fragmento2.OnBotonGuardarClickListener) {
            listenerGuardarEnActividad = (Fragmento2.OnBotonGuardarClickListener) context;
        } else {
            // Si no implementa la interfaz, lanza una excepción.
            throw new ClassCastException(context.toString() + " debe implementar OnBotonGuardarListener");
        }

        // Verifica si la actividad implementa la interfaz OnBotonVolverClickListener.
        if (context instanceof Fragmento2.OnBotonVolverClickListener) {
            listenerVolverEnActivity = (Fragmento2.OnBotonVolverClickListener) context;
        } else {
            // Si no implementa la interfaz, lanza una excepción.
            throw new ClassCastException(context.toString() + " debe implementar OnBotonVolverListener");
        }
    }

    // Método llamado cuando se crea el fragmento.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Método llamado para crear la vista del fragmento.
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Vinculamos el fragmento con el ViewModel.
        tareaViewModel = new ViewModelProvider(requireActivity()).get(TareaViewModel.class);

        // Inflamos el fragmento utilizando el layout definido en fragmento_dos.xml.
        return inflater.inflate(R.layout.fragmento_dos, container, false);
    }

    // Método llamado después de que la vista del fragmento ha sido creada.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Binding y configuración del EditText para la descripción.
        etDescripcion = view.findViewById(R.id.et_descripcion);
        @Nullable String description = tareaViewModel.getDescripcion();
        if (description != null)
            etDescripcion.setText(tareaViewModel.getDescripcion());

        // Binding y configuración del botón Volver.
        bt_Volver = view.findViewById(R.id.btVolver);
        bt_Volver.setOnClickListener(v -> {
            // Escribimos en el ViewModel la descripción actualizada.
            tareaViewModel.setDescripcion(etDescripcion.getText().toString());
            // Llamamos al método onBotonVolverClicked que está implementado en la actividad.
            if(listenerVolverEnActivity != null)
                listenerVolverEnActivity.onBotonVolverClicked();
        });

        // Binding y configuración del botón Guardar.
        bt_Guardar = view.findViewById(R.id.btGuardar);
        bt_Guardar.setOnClickListener(v -> {
            // Escribimos en el ViewModel la descripción actualizada.
            tareaViewModel.setDescripcion(etDescripcion.getText().toString());
            // Llamamos al método onBotonGuardarClicked que está implementado en la actividad.
            if(listenerGuardarEnActividad != null)
                listenerGuardarEnActividad.onBotonGuardarClicked();
        });
    }

}
