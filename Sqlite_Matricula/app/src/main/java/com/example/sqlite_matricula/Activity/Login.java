package com.example.sqlite_matricula.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_matricula.Database.DatabaseHelper;
import com.example.sqlite_matricula.R;

public class Login extends AppCompatActivity {

    ImageButton loginButton;
    EditText userName;
    EditText password;
    ImageView icono;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.enviar_btn);
        userName = findViewById(R.id.usuario_fld);
        password = findViewById(R.id.pass_fld);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sent_To_NavDrawer_Activity();
            }
        });
        databaseHelper = new DatabaseHelper(Login.this);
        //datosPrueba();

    }

    /*public void datosPrueba(){
         databaseHelper.addEstudiante(new Estudiante("117280151","Felipe","Piedra","123456",21));
         databaseHelper.addEstudiante(new Estudiante("402370159","Carlos","Obando","123456",22));
     }*/
    public void sent_To_NavDrawer_Activity() {
        Intent i = new Intent(Login.this, NavDrawer.class);
        startActivity(i);
        this.finish();
    }


}