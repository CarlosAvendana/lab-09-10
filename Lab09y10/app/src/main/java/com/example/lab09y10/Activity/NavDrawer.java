package com.example.lab09y10.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab09y10.R;
import com.google.android.material.navigation.NavigationView;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.openDrawer(GravityCompat.START);
    }//cierre del onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            this.moveTaskToBack(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_salir) {
            sent_To_Salir();
        } else if (id == R.id.nav_cursos_CRUD) {
            sent_To_Cursos_CRUD();
        } else if (id == R.id.nav_estudiante_CRUD) {
            sent_To_Estudiante_CRUD();
        } else if (id == R.id.nav_matricular) {
            sent_To_Matricular();
        } else if (id == R.id.nav_desmatricular) {
            sent_To_Desmatricular();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void sent_To_Cursos_CRUD() {
        Intent intent = new Intent(NavDrawer.this, Lista_Cursos.class);
        NavDrawer.this.startActivity(intent);
    }

    private void sent_To_Estudiante_CRUD() {
        Intent intent = new Intent(NavDrawer.this, MainActivity.class);
        NavDrawer.this.startActivity(intent);
    }

    private void sent_To_Matricular() {
        Intent intent = new Intent(NavDrawer.this, Lista_Matricula.class);
        NavDrawer.this.startActivity(intent);
    }

    private void sent_To_Desmatricular() {
        Intent intent = new Intent(NavDrawer.this, Lista_Desmatricula.class);
        NavDrawer.this.startActivity(intent);
    }

    private void sent_To_Salir() {
        finish();
        Intent intent = new Intent(NavDrawer.this, LogIn.class);
        NavDrawer.this.startActivity(intent);
    }


}