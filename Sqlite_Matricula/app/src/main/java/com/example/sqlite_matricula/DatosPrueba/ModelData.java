package com.example.sqlite_matricula.DatosPrueba;

import com.example.sqlite_matricula.Model.Curso;
import com.example.sqlite_matricula.Model.Estudiante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ModelData implements Serializable {

    private static ModelData singleton_instance = null;

    private List<Estudiante> estudianteList;
    private List<Curso> cursoList;

    public ModelData() {
        estudianteList = new ArrayList<>();
        cursoList = new ArrayList<>();
        prepareCarreraData();

    }

    public static ModelData getInstance() {
        if (singleton_instance == null)
            singleton_instance = new ModelData();
        return singleton_instance;
    }

    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void prepareCarreraData() {
        Estudiante carrera = new Estudiante("402370159", "Carlos", "Obando", 10);
        estudianteList.add(carrera);

        Curso curso = new Curso("EIF 400 Estructura de Datos", "Curso de carrera", 6);
        cursoList.add(curso);
    }


}
