package com.example.lab09y10.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab09y10.R;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ImageButton loginButton = (ImageButton) findViewById(R.id.btn_submit);
        final EditText UserName = (EditText) findViewById(R.id.et_username);
        final EditText Password = (EditText) findViewById(R.id.et_password);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentNavigationDrawer();
            }
        });
    }//Cierre on Create

    private void sentNavigationDrawer() {
        startActivity(new Intent(LogIn.this, NavDrawer.class));
        this.finish();
    }































/*
    private boolean validarUser(String x, String y) {
        for (int i = 0; i < Data.getInstance().usuarios.size(); i++)
            if (x.equals(Data.getInstance().usuarios.get(i).getId()) && y.equals(Data.getInstance().usuarios.get(i).getPassword()))
                return true;
        return false;
    }*/

    private void sendToNavigationDrawer(String nombre, String pass) {
        boolean isAdmin = false;
        Intent i = new Intent(LogIn.this, NavDrawer.class);
        if (nombre.equals("admin") && pass.equals("admin")) {
            isAdmin = true;
        }
        i.putExtra("admin", isAdmin);
        startActivity(i);
    }


}//Cierre del Login