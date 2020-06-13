package com.example.sqlite_matricula.Model;

import java.io.Serializable;

public class Matricula implements Serializable {

    String idEstudiante;
    String idCurso;
    String nombreCurso;
    String descripcionDelCurso;


    public Matricula() {
    }


    public Matricula(String idEstudiante, String idCurso) {
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
    }

    public Matricula(String idCurso, String nombreCurso, String descripcionDelCurso) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.descripcionDelCurso = descripcionDelCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripcionDelCurso() {
        return descripcionDelCurso;
    }

    public void setDescripcionDelCurso(String descripcionDelCurso) {
        this.descripcionDelCurso = descripcionDelCurso;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "idEstudiante='" + idEstudiante + '\'' +
                ", idCurso='" + idCurso + '\'' +
                '}';
    }
}
