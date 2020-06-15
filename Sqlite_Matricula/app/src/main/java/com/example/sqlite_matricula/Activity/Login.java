package com.example.sqlite_matricula.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_matricula.DatosPrueba.ModelData;
import com.example.sqlite_matricula.Model.Curso;
import com.example.sqlite_matricula.Model.Estudiante;
import com.example.sqlite_matricula.Model.Matricula;
import com.example.sqlite_matricula.R;

public class Login extends AppCompatActivity {

    ImageButton loginButton;
    EditText userName;
    EditText password;
    ImageView icono;
    ModelData model;
    ImageButton btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.enviar_btn);
        userName = findViewById(R.id.usuario_fld);
        password = findViewById(R.id.pass_fld);
        btnAgregar = findViewById(R.id.btnAgregarLogin);

        model = new ModelData(Login.this);

        //datosPrueba();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sent_AgregarEstudiante();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(password.getText())) {
                    if (isAdmin(userName.getText().toString(), password.getText().toString())) {
                        Toast.makeText(Login.this, "Bienvenido admin", Toast.LENGTH_LONG).show();
                        sent_To_NavDrawer_Activity();
                    } else {
                        if (isEstudiante(userName.getText().toString(), password.getText().toString())) {
                            Toast.makeText(Login.this, "Bienvenido estudiante", Toast.LENGTH_LONG).show();
                            sent_To_NavDrawer_Activity();
                        } else {
                            Toast.makeText(Login.this, "No se encuentra registrado", Toast.LENGTH_LONG).show();
                        }

                    }
                } else {
                    Toast.makeText(Login.this, "Ingrese datos por favor", Toast.LENGTH_LONG).show();
                }
                //sent_To_NavDrawer_Activity();
            }
        });

    }

    public void datosPrueba() {
        model.addEstudiante(new Estudiante("117280151", "Felipe", "Piedra", "123456", 21));
        model.addEstudiante(new Estudiante("117080857", "Gustavo", "Sanchez", "123456", 22));
        model.addEstudiante(new Estudiante("116340204", "Juan", "Madrigal", "123456", 24));
        model.addEstudiante(new Estudiante("117090732", "Paola", "Zuniga", "123456", 22));
        model.addEstudiante(new Estudiante("402370159", "Carlos", "Obando", "123456", 22));

        //Administrador
        model.addEstudiante(new Estudiante("117090732", "Paola", "Zuniga", "123456", 22));


        Curso n = new Curso("EIF400", "Paradigmas", 2);
        Curso nn = new Curso("EIF401", "Bases", 3);
        Curso nnn = new Curso("EIF402", "Moviles", 4);
        Curso nuevo = new Curso("AOA102", "Expresion del movimiento", 4);
        Curso nuevo1 = new Curso("MCA302", "Baile folklorico", 4);
        Curso nuevo2 = new Curso("ECG021", "Derechos Humanos", 4);
        model.addCurso(n);
        model.addCurso(nn);
        model.addCurso(nnn);
        model.addCurso(nuevo);
        model.addCurso(nuevo1);
        model.addCurso(nuevo2);

        Matricula matricula1 = new Matricula("117280151", "EIF400");
        Matricula matricula2 = new Matricula("117280151", "EIF401");
        Matricula matricula3 = new Matricula("117280151", "EIF402");

        Matricula matricula4 = new Matricula("117080857", "EIF400");
        Matricula matricula5 = new Matricula("116340204", "EIF400");
        Matricula matricula6 = new Matricula("402370159", "EIF400");
        Matricula matricula7 = new Matricula("402370159", "ECG021");

        model.addMatricula(matricula1);
        model.addMatricula(matricula2);
        model.addMatricula(matricula3);
        model.addMatricula(matricula4);
        model.addMatricula(matricula5);
        model.addMatricula(matricula6);
        model.addMatricula(matricula7);

    }


    public void sent_To_NavDrawer_Activity() {
        Intent i = new Intent(Login.this, NavDrawer.class);
        startActivity(i);
        this.finish();
    }

    public boolean isAdmin(String nombre, String pass) {
        boolean bandera = false;
        if (nombre.equals("admin") && pass.equals("admin")) {
            ModelData.setAdmin("admin");
            ModelData.setCedula("402370159");
            bandera = true;
        }
        return bandera;
    }

    public boolean isEstudiante(String cedula, String pass) {
        boolean bandera = false;
        if (model.existeEstudiante(cedula, pass)) {
            ModelData.setCedula(cedula);
            ModelData.setAdmin("");
            bandera = true;
        }
        return bandera;
    }

    public void sent_AgregarEstudiante() {
        Intent i = new Intent(Login.this, Registrar_Estudiante.class);
        startActivity(i);
    }

}