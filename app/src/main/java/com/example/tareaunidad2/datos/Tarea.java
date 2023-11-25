package com.example.tareaunidad2.datos;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tarea implements Parcelable {
    private static long contador = 0; // Contador estático para asignar identificadores únicos a las tareas
    private long id; // Identificador único de la tarea
    private String titulo; // Título de la tarea
    private Date fechaCreacion; // Fecha de creación de la tarea
    private Date fechaObjetivo; // Fecha objetivo para completar la tarea
    private int progreso; // Progreso de la tarea (puede ser un porcentaje, por ejemplo)
    private boolean prioritaria; // Indica si la tarea es prioritaria o no
    private String descripcion; // Descripción de la tarea


    public Tarea(long id, String titulo, Date fechaCreacion, Date fechaObjetivo, int progreso, boolean prioritaria, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.fechaObjetivo = fechaObjetivo;
        this.progreso = progreso;
        this.prioritaria = prioritaria;
        this.descripcion = descripcion;
    }

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static long getContador() {
        return contador;
    }

    public static void setContador(long contador) {
        Tarea.contador = contador;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaObjetivo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(fechaObjetivo);
    }

    public void setFechaObjetivo(@NonNull String fechaObjetivo) {
        if (validarFormatoFecha(fechaObjetivo)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            try {
                this.fechaObjetivo = sdf.parse(fechaObjetivo);
            } catch (Exception e) {

            }
        } else {
            Log.e("Error fecha","Formato de fecha objetivo no válido.");
        }
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public boolean isPrioritaria() {
        return prioritaria;
    }

    public void setPrioritaria(boolean prioritaria) {
        this.prioritaria = prioritaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private boolean validarFormatoFecha(@NonNull String fecha) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[0-2])[/](19|20)\\d\\d$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }

    // Método para obtener la diferencia en días entre la fecha objetivo y la fecha actual
    public int quedanDias() {
        Date hoy = new Date(); // Obtener la fecha actual
        long diferenciaMillis = fechaObjetivo.getTime() - hoy.getTime();
        long diasDiferencia = TimeUnit.DAYS.convert(diferenciaMillis, TimeUnit.MILLISECONDS);
        return (int) diasDiferencia;
    }

    // Método toString para representar la tarea como una cadena de texto
    @NonNull
    @Override
    public String toString(){
        return "Tarea: "+ titulo + "\n" + "Descripcion: " +descripcion;
    }

    // Métodos equals y hashCode para comparar tareas por su identificador
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Tarea tarea = (Tarea) other;
        return id == tarea.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //Metodos de la interfaz Parcelable
    @Override
    public int describeContents(){
        return 0;
    }

    // Método para escribir los datos de la tarea en un Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLong(this.id);
        dest.writeString(this.titulo);
        dest.writeLong(this.fechaCreacion != null ? this.fechaCreacion.getTime() : -1);
        dest.writeLong(this.fechaObjetivo != null ? this.fechaObjetivo.getTime() : -1);
        dest.writeInt(this.progreso);
        dest.writeByte(this.prioritaria ? (byte) 1 : (byte) 0);
        dest.writeString(this.descripcion);
    }

    // Método para leer los datos de un Parcel y reconstruir la tarea
    public void readFroParcel(Parcel source){
        this.id = source.readLong();
        this.titulo = source.readString();
        long tmpFechaCreacion = source.readLong();
        this.fechaCreacion = tmpFechaCreacion == -1 ? null : new Date(tmpFechaCreacion);
        long tmpFechaObjetivo = source.readLong();
        this.fechaObjetivo = tmpFechaObjetivo == -1 ? null : new Date(tmpFechaObjetivo);
        this.progreso = source.readInt();
        this.prioritaria = source.readByte() != 0;
        this.descripcion = source.readString();
    }

    // Constructor privado utilizado por el sistema para recrear la tarea desde un Parcel
    protected Tarea(Parcel in){
        this.id = in.readLong();
        this.titulo = in.readString();
        long tmpFechaCreacion = in.readLong();
        this.fechaCreacion = tmpFechaCreacion == -1 ? null : new Date(tmpFechaCreacion);
        long tmpFechaObjetivo = in.readLong();
        this.fechaObjetivo = tmpFechaObjetivo == -1 ? null : new Date(tmpFechaObjetivo);
        this.progreso = in.readInt();
        this.prioritaria = in.readByte() != 0;
        this.descripcion = in.readString();
    }

    // Creador estático necesario para implementar Parcelable
    public static final Creator<Tarea> CREATOR = new Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel source) {
            return new Tarea(source);
        }

        @Override
        public Tarea[] newArray(int tamaño) {
            return new Tarea[tamaño];
        }
    };

}
