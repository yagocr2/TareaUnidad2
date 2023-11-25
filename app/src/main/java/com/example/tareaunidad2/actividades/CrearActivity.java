package com.example.tareaunidad2.actividades;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareaunidad2.R;
import com.example.tareaunidad2.datos.Tarea;
import com.example.tareaunidad2.fragmentos.Fragmento1;
import com.example.tareaunidad2.fragmentos.Fragmento2;
import com.example.tareaunidad2.viewmodel.TareaViewModel;

import java.util.Date;

public class CrearActivity  extends AppCompatActivity implements
        Fragmento1.OnBotonSiguienteClickListener,
        Fragmento1.OnBotonCancelarCLickListener,
        Fragmento2.OnBotonVolverClickListener,
        Fragmento2.OnBotonGuardarClickListener{
    private TareaViewModel tareaViewModel;
    private FragmentManager miManejador;
    private String titulo, descripcion;
    private String fechaCreacion, fechaObjetivo;
    private int progreso;
    private boolean prioritaria;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_tarea_activity);

        //Instanciamos el ViewModel
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);
        //Instanciamos el FragmentManager
        miManejador = getSupportFragmentManager();
        //Creamos el primer fragmento
        Fragment fragmento1 = new Fragmento1();
        //Lo cargamos en el contenedor
        miManejador.beginTransaction().replace(R.id.contenedor_frag, fragmento1).addToBackStack("Frag1").commit();
    }
    @Override
    public void onBotonSiguienteClicked() {
        //Leemos los valores del formulario del fragmento 1
        titulo = tareaViewModel.getTitulo();
        fechaCreacion = tareaViewModel.getFechaCreacion();
        fechaObjetivo = tareaViewModel.getFechaObjetivo();
        progreso = tareaViewModel.getProgreso();
        prioritaria = tareaViewModel.isPrioritaria();

        Fragment fragmento2 = new Fragmento2();
        miManejador.beginTransaction().replace(R.id.contenedor_frag, fragmento2).commit();
    }

    @Override
    public void onBotonCancelarClickListener() {
        Intent aListado = new Intent();
        //Indicamos en el resultado que ha sido cancelada la actividad
        setResult(RESULT_CANCELED, aListado);
        //Volvemos a la actividad Listado
        finish();
    }
    @Override
    public void onBotonGuardarClicked() {
        //Leemos los valores del formulario del fragmento 2
        descripcion = tareaViewModel.getDescripcion();
        //Creamos la nueva tarea
        Tarea nuevaTarea = new Tarea(titulo, fechaCreacion,fechaObjetivo, progreso,prioritaria, descripcion);
        //Creamos un intent de vuelta para la actividad Listado
        Intent aListado = new Intent();
        //Creamos un Bundle para introducir la nueva tarea
        Bundle bundle = new Bundle();
        bundle.putParcelable("NuevaTarea", nuevaTarea);
        aListado.putExtras(bundle);

        //Indicamos que el resultado ha sido OK
        setResult(RESULT_OK, aListado);

        //Volvemos a la actividad Listado
        finish();
    }

    @Override
    public void onBotonVolverClicked() {
        //Leemos los valores del formulario del fragmento 2
        descripcion = tareaViewModel.getDescripcion();

        //Cambiamos el fragmento2 por el 1
        Fragment fragmento1 = new Fragmento1();
        miManejador.beginTransaction().replace(R.id.contenedor_frag, fragmento1).commit();
    }
}
