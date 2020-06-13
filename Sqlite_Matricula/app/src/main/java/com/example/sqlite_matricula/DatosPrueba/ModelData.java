package com.example.sqlite_matricula.DatosPrueba;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite_matricula.Database.DatabaseHelper;
import com.example.sqlite_matricula.Model.Curso;
import com.example.sqlite_matricula.Model.Estudiante;
import com.example.sqlite_matricula.Model.Matricula;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ModelData implements Serializable {

    DatabaseHelper databaseHelper;

    public ModelData(Context context) {
      this.databaseHelper = new DatabaseHelper(context);

    }
    //-----MATRICULAS
    public boolean addMatricula(Matricula mat){
        return this.databaseHelper.addMatricula(mat);
    }
    public boolean deleteMatricula(Matricula mat) {
       return this.databaseHelper.deleteMatricula(mat);
    }
    public List<Matricula> getMatriculasEst(String estId){
        return this.databaseHelper.getMatriculasEst(estId);
    }
    public List<Matricula> getMatriculasNoEst(String estId){
        return this.databaseHelper.getMatriculasNoEst(estId);
    }


    //-----ESTUDIANTES
    public boolean addEstudiante(Estudiante est){
        return this.databaseHelper.addEstudiante(est);
    }
    public boolean deleteEstudiante(Estudiante est){
        return this.databaseHelper.deleteEstudiante(est);
    }
    public boolean updateEstudiante(Estudiante est){
        return this.databaseHelper.updateEstudiante(est);
    }
    public boolean existeEstudiante(String user,String password){
        return this.databaseHelper.existeEstudiante(user,password);
    }
    public List<Estudiante> getEstudianteList() {
        return databaseHelper.getEstudiantes();
    }


    //----CURSOS
    public boolean addCurso(Curso c){
        return this.databaseHelper.addCurso(c);
    }
    public boolean deleteCurso(Curso c){
        return this.databaseHelper.deleteCurso(c);
    }
    public boolean updateCurso(Curso c){
        return this.databaseHelper.updateCurso(c);
    }
    public List<Curso> getCursoList() {
        return databaseHelper.getCursos();
    }





}
