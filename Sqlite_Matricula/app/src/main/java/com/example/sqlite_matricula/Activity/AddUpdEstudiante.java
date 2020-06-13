package com.example.sqlite_matricula.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_matricula.Database.DatabaseHelper;
import com.example.sqlite_matricula.Model.Estudiante;
import com.example.sqlite_matricula.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddUpdEstudiante extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable = true;

    private EditText idFld;
    private EditText nombreFld;
    private EditText apellidoFld;
    private EditText aniosFld;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_estudiante);
        fBtn = findViewById(R.id.addUpdEstudianteBtn);
        getSupportActionBar().setTitle("Estudiante");

        databaseHelper = new DatabaseHelper(AddUpdEstudiante.this);

        editable = true;

        idFld = findViewById(R.id.addUpd_Estudiante_Id);
        nombreFld = findViewById(R.id.addUpd_Estudiante_Nombre);
        apellidoFld = findViewById(R.id.addUpd_Estudiante_Apellido);
        aniosFld = findViewById(R.id.addUpd_Estudiante_Anios);


        idFld.setText("");
        nombreFld.setText("");
        apellidoFld.setText("");
        aniosFld.setText("");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Estudiante aux = (Estudiante) getIntent().getSerializableExtra("Estudiante");

                idFld.setText(aux.get_id());
                idFld.setEnabled(false);
                nombreFld.setText(aux.get_nombre());
                apellidoFld.setText(aux.get_apellido());
                aniosFld.setText(Integer.toString(aux.get_anios()));

                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editEstudiante();
                    }
                });
            } else {
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addEstudiante();
                    }
                });
            }
        }

    }

    public void addEstudiante() {
        if (validateForm()) {
            Estudiante est = new Estudiante(idFld.getText().toString(),
                    nombreFld.getText().toString(),
                    apellidoFld.getText().toString(),
                    Integer.parseInt(aniosFld.getText().toString()));
            databaseHelper.addEstudiante(est);
            Intent intent = new Intent(getBaseContext(), List_Estudiante.class);
            intent.putExtra("addEstudiante", est);
            startActivity(intent);
            finish();
        }
    }

    public void editEstudiante() {
        if (validateForm()) {
            Estudiante est = new Estudiante(idFld.getText().toString(), nombreFld.getText().toString(), apellidoFld.getText().toString(), Integer.parseInt(aniosFld.getText().toString()));
            databaseHelper.updateEstudiante(est);
            Intent intent = new Intent(getBaseContext(), List_Estudiante.class);
            intent.putExtra("editEstudiante", est);
            startActivity(intent);
            finish();
        }
    }

    public boolean validateForm() {
        int error = 0;
        return true;
    }


}// Cierre de la clase AddUpdEstudiante