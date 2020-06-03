package com.example.lab09y10.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String ESTUDIANTE_TABLE = "ESTUDIANTE_TABLE";
    public static final String COLUMN_ID_CURSO = "id";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRE = "_nombre";
    public static final String COLUMN_APELLIDO = "_apellido";
    public static final String COLUMN_ANIOS = "_anios";
    public static final String COLUMN_DESCRIPCION_CURSO = "descripcion";
    public static final String COLUMN_CREDITOS_CURSO = "creditos";
    public static final String CURSO_TABLE = "CURSO_TABLE";
    public static final String MATRICULA_TABLE = "MATRICULA_TABLE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "errollment.db", null, 1);
    }

    //this is called the first time, create the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ESTUDIANTE_TABLE + "( " + COLUMN_ID + " TEXT PRIMARY KEY, " + COLUMN_NOMBRE + " TEXT, " + COLUMN_APELLIDO + " TEXT, " + COLUMN_ANIOS + " INTEGER); " +
                "CREATE TABLE " + CURSO_TABLE + " (" + COLUMN_ID_CURSO + " TEXT PRIMARY KEY, " + COLUMN_DESCRIPCION_CURSO + " TEXT, " + COLUMN_CREDITOS_CURSO + " INTEGER);" +
                "CREATE TABLE " + MATRICULA_TABLE + "(estudiante_id INTEGER not null," +
                "curso_id INTEGER not null," +
                "constraint MATRICULA_TABLE_PK primary key(estudiante_id,curso_id)" +
                "constraint MATRICULA_TABLE_ESTUDIANTE_FK foreig key (estudiante_id) references " + ESTUDIANTE_TABLE + "(" + COLUMN_ID + ")," +
                "constraint MATRICULA_TABLE_CURSO_FK foreig key (curso_id) references " + CURSO_TABLE + "(" + COLUMN_ID_CURSO + "));";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
