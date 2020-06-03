package com.example.lab09y10.Model;

import java.io.Serializable;

public class Matricula implements Serializable {

    String idEstudiante;
    String idCurso;

    public Matricula() {
    }

    public Matricula(String idEstudiante, String idCurso) {
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
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
