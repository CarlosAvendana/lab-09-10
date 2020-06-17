package com.example.sqlite_matricula.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_matricula.DatosPrueba.ModelData;
import com.example.sqlite_matricula.Model.Curso;
import com.example.sqlite_matricula.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddUpdCurso extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable = true;

    private EditText idFld;
    private EditText descripcionFld;
    private EditText creditosFld;
    private ModelData model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_curso);
        getSupportActionBar().setTitle("Curso");

        model = new ModelData(AddUpdCurso.this);
        fBtn = findViewById(R.id.addUpdCursoBtn);
        editable = true;

        idFld = findViewById(R.id.addUpd_Curso_Id);
        descripcionFld = findViewById(R.id.addUpd_Curso_Descipcion);
        creditosFld = findViewById(R.id.addUpd_Curso_Creditos);

        idFld.setText("");
        descripcionFld.setText("");
        creditosFld.setText("");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Curso aux = (Curso) getIntent().getSerializableExtra("Curso");

                idFld.setText(aux.getId());
                idFld.setEnabled(false);
                descripcionFld.setText(aux.getDescripcion());
                creditosFld.setText("" + aux.getCreditos());

                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCurso();
                    }
                });
            } else {
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCurso();
                    }
                });
            }
        }
    }

    public void addCurso() {
        if (validateForm() && !this.model.existeCurso(idFld.getText().toString())) {
            Curso est = new Curso(idFld.getText().toString(),
                    descripcionFld.getText().toString(),
                    Integer.parseInt(creditosFld.getText().toString()));
            model.addCurso(est);

            Intent intent = new Intent(getBaseContext(), List_Cursos.class);
            intent.putExtra("addCurso", est);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "ERROR, ID de curso repetido", Toast.LENGTH_LONG).show();
        }
    }

    public void editCurso() {
        if (validateForm()) {
            Curso est = new Curso(idFld.getText().toString(),
                    descripcionFld.getText().toString(),
                    Integer.parseInt(creditosFld.getText().toString()));
            model.updateCurso(est);
            Intent intent = new Intent(getBaseContext(), List_Cursos.class);
            intent.putExtra("addCurso", est);
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
        if (TextUtils.isEmpty(this.descripcionFld.getText())) {
            descripcionFld.setError("Error requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.creditosFld.getText())) {
            creditosFld.setError("Error requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


}//Cierre de la clase AddUpdCurso