package com.example.sqlite_matricula.Activity;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sqlite_matricula.DatosPrueba.ModelData;
import com.example.sqlite_matricula.R;
import com.google.android.material.navigation.NavigationView;

public class NavDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(ModelData.getAdmin().equals("")){
            NavigationView navigationView = findViewById(R.id.nav_view);
            Menu menuNav=navigationView.getMenu();
            MenuItem curso = menuNav.findItem(R.id.nav_cursos);
            MenuItem student = menuNav.findItem(R.id.nav_student);
            curso.setVisible(false);
            student.setVisible(false);

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            //NavigationView navigationView = findViewById(R.id.nav_view);

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home)
                    .setDrawerLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            navigationView.setNavigationItemSelectedListener(this);
            drawer.openDrawer(GravityCompat.START);
        }
        if(ModelData.getAdmin().equals("admin")){
            NavigationView navigationView = findViewById(R.id.nav_view);
            Menu menuNav=navigationView.getMenu();
            MenuItem MATRICULAR = menuNav.findItem(R.id.nav_matricula);
            MenuItem DESMATRICULAR = menuNav.findItem(R.id.nav_desmatricular);
            MATRICULAR.setVisible(false);
            DESMATRICULAR.setVisible(false);

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            //NavigationView navigationView = findViewById(R.id.nav_view);

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home)
                    .setDrawerLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            navigationView.setNavigationItemSelectedListener(this);
            drawer.openDrawer(GravityCompat.START);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //--- Nuevos metodos

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_student) {
            Toast.makeText(getApplicationContext(), "Estudiantes", Toast.LENGTH_SHORT).show();
            sent_List_Estudiantes();
        } else if (id == R.id.nav_salir) {
            Toast.makeText(getApplicationContext(), "Log-Out", Toast.LENGTH_SHORT).show();
            sent_Login();
        } else if (id == R.id.nav_cursos) {
            Toast.makeText(getApplicationContext(), "Cursos", Toast.LENGTH_SHORT).show();
            sent_List_Cursos();
        } else if (id == R.id.nav_matricula) {
            Toast.makeText(getApplicationContext(), "Cursos matriculados", Toast.LENGTH_SHORT).show();
            sent_List_Matricula();
        } else if (id == R.id.nav_desmatricular) {
            Toast.makeText(getApplicationContext(), "Cursos desmatricular", Toast.LENGTH_SHORT).show();
            sent_List_Desmatricula();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sent_List_Estudiantes() {
        Intent a = new Intent(NavDrawer.this, List_Estudiante.class);
        startActivity(a);
    }

    public void sent_Login() {
        finish();
        Intent a = new Intent(NavDrawer.this, Login.class);
        startActivity(a);
    }

    public void sent_List_Cursos() {
        Intent a = new Intent(NavDrawer.this, List_Cursos.class);
        startActivity(a);
    }

    public void sent_List_Matricula() {
        Intent a = new Intent(NavDrawer.this, List_Matricula.class);
        startActivity(a);
    }

    public void sent_List_Desmatricula() {
        Intent a = new Intent(NavDrawer.this, List_Desmatricula.class);
        startActivity(a);
    }


}//cierre clase navigation Drawer
