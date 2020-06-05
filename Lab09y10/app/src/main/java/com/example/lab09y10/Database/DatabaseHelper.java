package com.example.lab09y10.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

}
