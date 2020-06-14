package com.example.sqlite_matricula.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_matricula.DatosPrueba.ModelData;
import com.example.sqlite_matricula.Model.Estudiante;
import com.example.sqlite_matricula.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Registrar_Estudiante extends AppCompatActivity {

    private FloatingActionButton btnAgregar;
    private EditText idFld;
    private EditText nombreFld;
    private EditText apellidoFld;
    private EditText aniosFld;
    private EditText contraFld;
    private EditText recontraFld;
    private ModelData model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__estudiante);
        getSupportActionBar().setTitle("Registro de Estudiante");

        model = new ModelData(Registrar_Estudiante.this);

        idFld = findViewById(R.id.registro_Estudiante_ID);
        nombreFld = findViewById(R.id.registro_Estudiante_Nombre);
        apellidoFld = findViewById(R.id.registro_Estudiante_Apellido);
        aniosFld = findViewById(R.id.registro_Estudiante_edad);
        contraFld = findViewById(R.id.registro_Estudiante_contra);
        recontraFld = findViewById(R.id.registro_Estudiante_recontra);
        btnAgregar = findViewById(R.id.btn_agregarEstudiante);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEstudiante();
            }
        });

    }

    public void addEstudiante() {
        if (validateForm()) {
            Estudiante est = new Estudiante(idFld.getText().toString(), nombreFld.getText().toString(), apellidoFld.getText().toString(), contraFld.getText().toString(), Integer.parseInt(aniosFld.getText().toString()));
            model.addEstudiante(est);
            Intent intent = new Intent(getBaseContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean validateForm() {
        int error = 0;

        if (TextUtils.isEmpty(this.idFld.getText())) {
            idFld.setError("Error requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.nombreFld.getText())) {
            nombreFld.setError("Error requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.apellidoFld.getText())) {
            apellidoFld.setError("Error requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.aniosFld.getText())) {
            contraFld.setError("Error requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.contraFld.getText())) {
            contraFld.setError("Error requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.recontraFld.getText())) {
            recontraFld.setError("Error requerido");
            error++;
        }
        if (!this.contraFld.getText().equals(this.recontraFld.getText())) {
            contraFld.setError("Contraseña debe ser igual");
            recontraFld.setError("Contraseña debe ser igual");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


}