package com.example.tareaunidad2.viewmodel;

import androidx.lifecycle.ViewModel;

public class TareaViewModel extends ViewModel {
    private String titulo;
    private String fechaCreacion;
    private String fechaObjetivo;
    private int progreso;
    private boolean prioritaria;
    private String descripcion;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaObjetivo() {
        return fechaObjetivo;
    }

    public void setFechaObjetivo(String fechaObjetivo) {
        this.fechaObjetivo = fechaObjetivo;
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
}
