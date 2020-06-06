package com.example.lab09y10.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lab09y10.Adaptador.EstudianteAdapter;
import com.example.lab09y10.Model.Curso;
import com.example.lab09y10.Model.Estudiante;
import com.example.lab09y10.Model.Matricula;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String USUARIO_TABLE = "USUARIO_TABLE";
    public static final String USUARIO_NOMBRE = "USUARIO_NOMBRE";
    public static final String USUARIO_CONTRASENA = "USUARIO_CONTRASENA";
    public static final String USUARIO_ROL = "USUARIO_ROL";

    public static final String ESTUDIANTE_TABLE = "ESTUDIANTE_TABLE";
    public static final String CURSO_TABLE = "CURSO_TABLE";
    public static final String MATRICULA_TABLE = "MATRICULA_TABLE";
    public static final String ESTUDIANTE_ID = "ESTUDIANTE_ID";
    public static final String ESTUDIANTE_NOMBRE = "ESTUDIANTE_NOMBRE";
    public static final String ESTUDIANTE_APELLIDO = "ESTUDIANTE_APELLIDO";
    public static final String ESTUDIANTE_ANIO = "ESTUDIANTE_ANIO";
    public static final String CURSO_ID = "CURSO_ID";
    public static final String CURSO_DESCRIPCION = "CURSO_DESCRIPCION";
    public static final String CURSO_CREDITOS = "CURSO_CREDITOS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "errollment.db", null, 1);
    }

    //this is called the first time, create the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USUARIO_TABLE + " (" + USUARIO_NOMBRE + " TEXT," + USUARIO_CONTRASENA + " TEXT," + USUARIO_ROL + " INTEGER);" +
                "CREATE TABLE " + ESTUDIANTE_TABLE + " ( " + ESTUDIANTE_ID + " TEXT PRIMARY KEY, " + ESTUDIANTE_NOMBRE + " TEXT, " + ESTUDIANTE_APELLIDO + " TEXT, " + ESTUDIANTE_ANIO + " INTEGER);" +
                "CREATE TABLE " + CURSO_TABLE + " ( " + CURSO_ID + " TEXT PRIMARY KEY, " + CURSO_DESCRIPCION + " TEXT, " + CURSO_CREDITOS + " INTEGER);" +
                "CREATE TABLE " + MATRICULA_TABLE + " (" +
                ESTUDIANTE_ID + " TEXT NOT NULL," +
                CURSO_ID + " TEXT NOT NULL," +
                "CONSTRAINT " + MATRICULA_TABLE + "_PK PRIMARY KEY(" + ESTUDIANTE_ID + "," + CURSO_ID + ")," +
                "CONSTRAINT " + MATRICULA_TABLE + "_ESTUDIANTE_FK FOREIGN  KEY (" + ESTUDIANTE_ID + ") REFERENCES  " + ESTUDIANTE_TABLE + " ( " + ESTUDIANTE_ID + " )" +
                "CONSTRAINT " + MATRICULA_TABLE + "_CURSO_FK FOREIGN  KEY (" + CURSO_ID + ") REFERENCES  " + CURSO_TABLE + " (" + CURSO_ID + ")" +
                ");";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //-------------CRUD CURSOS

    public boolean addCurso(Curso c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CURSO_ID, c.getId());
        cv.put(CURSO_DESCRIPCION, c.getDescripcion());
        cv.put(CURSO_CREDITOS, c.getCreditos());

        long insert = db.insert(CURSO_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteCurso(Curso c) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CURSO_TABLE + " WHERE " + CURSO_ID + " = " + c.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean updateCurso(Curso c){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + CURSO_TABLE + " SET " + CURSO_DESCRIPCION + " = "+ c.getDescripcion() +
                " , " + CURSO_CREDITOS + " = "+ c.getCreditos() + " WHERE " + CURSO_ID + " = " + c.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public List<Curso> getCursos() {
        List<Curso> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CURSO_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String cursoId = cursor.getString(0);
                String cursoDescrip = cursor.getString(1);
                int cursoCreditos = cursor.getInt(2);

                returnList.add(new Curso(cursoId, cursoDescrip, cursoCreditos));

            } while (cursor.moveToNext());
        } else {
            //failures... do not add items to the list.
        }
        cursor.close();
        db.close();

        return returnList;
    }

    //-----------CRUD ESTUDIANTES

    public boolean addEstudiante(Estudiante est) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ESTUDIANTE_ID, est.get_id());
        cv.put(ESTUDIANTE_NOMBRE, est.get_nombre());
        cv.put(ESTUDIANTE_APELLIDO, est.get_apellido());
        cv.put(ESTUDIANTE_ANIO, est.get_anios());

        long insert = db.insert(ESTUDIANTE_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteEstudiante(Estudiante est) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ESTUDIANTE_TABLE + " WHERE " + ESTUDIANTE_ID + " = " + est.get_id();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateEstudiante(Estudiante est){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + ESTUDIANTE_TABLE + " SET " + ESTUDIANTE_NOMBRE + " = "+ est.get_nombre() +
                " , " + ESTUDIANTE_APELLIDO + " = "+ est.get_apellido() +  " , " + ESTUDIANTE_ANIO+ " = "+ est.get_anios()
                + " WHERE " + ESTUDIANTE_ID + " = " + est.get_id();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public List<Estudiante> getEstudiantes() {
        List<Estudiante> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ESTUDIANTE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String idEstudiante = cursor.getString(0);
                String nomEstudiante = cursor.getString(1);
                String apeEstudiante = cursor.getString(2);
                int anioEstudiante = cursor.getInt(3);

                returnList.add(new Estudiante(idEstudiante, nomEstudiante, apeEstudiante, anioEstudiante));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnList;
    }

    //-------------CRUD MATRICULA
    public boolean addMatricula(Matricula mat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ESTUDIANTE_ID, mat.getIdEstudiante());
        cv.put(CURSO_ID, mat.getIdCurso());

        long insert = db.insert(MATRICULA_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteMatricula(Matricula mat) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + MATRICULA_TABLE + " WHERE " + ESTUDIANTE_ID + " = " + mat.getIdEstudiante()
                + " AND " + CURSO_ID + " = " + mat.getIdCurso();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public List<Matricula> getMatriculas(){
        List<Matricula> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM "+ MATRICULA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String idEstudiante = cursor.getString(0);
                String idCurso = cursor.getString(1);

                returnList.add(new Matricula(idEstudiante,idCurso));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnList;
    }

}
