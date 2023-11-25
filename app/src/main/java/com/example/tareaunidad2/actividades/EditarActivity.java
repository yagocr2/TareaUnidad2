package com.example.tareaunidad2.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareaunidad2.R;
import com.example.tareaunidad2.datos.Tarea;
import com.example.tareaunidad2.fragmentos.Fragmento1;
import com.example.tareaunidad2.fragmentos.Fragmento2;
import com.example.tareaunidad2.viewmodel.TareaViewModel;

public class EditarActivity extends AppCompatActivity implements
        Fragmento1.OnBotonSiguienteClickListener, Fragmento1.OnBotonCancelarCLickListener,
        Fragmento2.OnBotonGuardarClickListener,Fragmento2.OnBotonVolverClickListener{

    private Tarea tareaEditable;
    private TareaViewModel tareaViewModel;
    private FragmentManager miManejador;
    private String titulo, descripcion;
    private String fechaCreacion, fechaObjetivo;
    private int progreso;
    private boolean prioritaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_tarea_activity);

        //Recibimos la tarea que va a ser editada
        Bundle bundle = getIntent().getExtras();
        try {
            if (bundle != null) {
                this.tareaEditable = bundle.getParcelable("TareaEditable");
            }
        }catch (NullPointerException e){
            Log.e("Bundle recibido nulo", e.toString());
        }

        //Instanciamos el ViewModel
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);

        //Escribimos valores en el ViewModel
        tareaViewModel.setTitulo(tareaEditable.getTitulo());
        tareaViewModel.setFechaCreacion(tareaEditable.getFechaCreacion());
        tareaViewModel.setFechaObjetivo(tareaEditable.getFechaObjetivo());
        tareaViewModel.setProgreso(tareaEditable.getProgreso());
        tareaViewModel.setPrioritaria(tareaEditable.isPrioritaria());
        tareaViewModel.setDescripcion(tareaEditable.getDescripcion());

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

        //Cambiamos el fragmento
        Fragment fragmento2 = new Fragmento2();
        miManejador.beginTransaction().replace(R.id.contenedor_frag, fragmento2).commit();
    }

    @Override
    public void onBotonCancelarClicked() {
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
        //Creamos un nuevo objeto tarea con los campos editados
        Tarea tareaEditada = new Tarea(titulo, fechaCreacion,fechaObjetivo, progreso,prioritaria, descripcion);

        //Creamos un intent de vuelta para la actividad Listado
        Intent aListado = new Intent();
        //Creamos un Bundle para introducir la tarea editada
        Bundle bundle = new Bundle();
        bundle.putParcelable("TareaEditada", tareaEditada);
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
