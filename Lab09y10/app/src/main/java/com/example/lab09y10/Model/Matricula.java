package com.example.lab09y10.Model;

public class Matricula {

    int idEstudiante;
    int idCurso;

    public Matricula() {
    }

    public Matricula(int idEstudiante, int idCurso) {
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "idEstudiante=" + idEstudiante +
                ", idCurso=" + idCurso +
                '}';
    }
}
