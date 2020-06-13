package com.example.sqlite_matricula.DatosPrueba;

import com.example.sqlite_matricula.Model.Curso;
import com.example.sqlite_matricula.Model.Estudiante;
import com.example.sqlite_matricula.Model.Matricula;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ModelData implements Serializable {

    private static ModelData singleton_instance = null;

    private List<Estudiante> estudianteList;
    private List<Curso> cursoList;
    private List<Matricula> matriculaList;

    public ModelData() {
        estudianteList = new ArrayList<>();
        cursoList = new ArrayList<>();
        matriculaList = new ArrayList<>();
        prepareCarreraData();

    }

    public static ModelData getInstance() {
        if (singleton_instance == null)
            singleton_instance = new ModelData();
        return singleton_instance;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
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

        Curso curso = new Curso("EIF400", "Estructura de Datos", 6);
        cursoList.add(curso);

        Matricula matricula = new Matricula("EIF400", "EIF400", "Estructura de Datos");
        matriculaList.add(matricula);


    }


}
