package com.example.sqlite_matricula.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_matricula.DatosPrueba.ModelData;
import com.example.sqlite_matricula.Model.Curso;
import com.example.sqlite_matricula.Model.Estudiante;
import com.example.sqlite_matricula.R;

public class Login extends AppCompatActivity {

    ImageButton loginButton;
    EditText userName;
    EditText password;
    ImageView icono;
    ModelData model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.enviar_btn);
        userName = findViewById(R.id.usuario_fld);
        password = findViewById(R.id.pass_fld);
        model = new ModelData(Login.this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sent_To_NavDrawer_Activity();
            }
        });

        datosPrueba();

        //primero valida si es el admin
        //sino es que valide que el estudiante esta en la base datos
        //sino esta en la bd que no lo deje pasar


    }

    public void datosPrueba() {
        model.addEstudiante(new Estudiante("117280151", "Felipe", "Piedra", "123456", 21));
        model.addEstudiante(new Estudiante("402370159", "Carlos", "Obando", "123456", 22));

        Curso n = new Curso("EIF400", "Paradigmas", 2);
        Curso nn = new Curso("EIF401", "Bases", 3);
        Curso nnn = new Curso("EIF402", "Moviles", 4);
        model.addCurso(n);
        model.addCurso(nn);
        model.addCurso(nnn);

    }

    public void sent_To_NavDrawer_Activity() {
        Intent i = new Intent(Login.this, NavDrawer.class);
        startActivity(i);
        this.finish();
    }

    public void validaRol(String nombre, String pass) {
        if (nombre.equals("admin") && pass.equals("admin")) {
            model.setCedula("admin");
        }
    }

    public boolean isEstudiante(String cedula, String pass) {
        boolean bandera = false;
        model.setCedula(cedula);
        return bandera;
    }

}