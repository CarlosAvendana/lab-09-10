package com.example.lab09y10.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab09y10.Adaptador.CursoAdapter;
import com.example.lab09y10.Helper.RecyclerItemTouchHelper;
import com.example.lab09y10.Model.Curso;
import com.example.lab09y10.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Lista_Cursos extends AppCompatActivity
        implements CursoAdapter.CursoAdapterListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private RecyclerView mRecyclerView;
    private CursoAdapter mAdapter;
    private List<Curso> cursos_List;
    //private ModelData model;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__cursos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cursos");

        mRecyclerView = findViewById(R.id.recycler_Curso_Fld);
        cursos_List = new ArrayList<>();

        //Hay que cargar todas los estudiantes en la base de datos dentro de este onCreate

        //model = ModelData.getInstance();
        //estudiantes_List = model.getFormList();
        //mAdapter = new EstudianteAdapter(estudiantes_List, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.agregarCurso_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdCurso();
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        checkIntentInformation();
        mAdapter.notifyDataSetChanged();
        whiteNotificationBar(mRecyclerView);

    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.parseColor("#455A64"));
        }
    }

    @Override
    public void onContactSelected(Curso form) {
        //Toast.makeText(getApplicationContext(), "Selected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof CursoAdapter.MyViewHolder) {
                String name = cursos_List.get(viewHolder.getAdapterPosition()).getDescripcion();
                final int deletedIndex = viewHolder.getAdapterPosition();
                mAdapter.removeItem(viewHolder.getAdapterPosition());
                Toast.makeText(getApplicationContext(), name + " deleted!", Toast.LENGTH_LONG).show();
            }
        } else {

            Curso aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            Intent intent = new Intent(this, AddUpdCurso.class);
            intent.putExtra("editable", true);
            intent.putExtra("Curso", aux);
            mAdapter.notifyDataSetChanged();
            startActivity(intent);
        }
    }

    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() { //TODO it's not working yet
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        Intent a = new Intent(this, NavDrawer.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
    }

    private void checkIntentInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Curso aux;
            aux = (Curso) getIntent().getSerializableExtra("addCurso");
            if (aux == null) {
                aux = (Curso) getIntent().getSerializableExtra("editCurso");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Curso carrera : cursos_List) {
                        if (carrera.getId().equals(aux.getId())) {
                            carrera.setDescripcion(aux.getDescripcion());
                            carrera.setCreditos(aux.getCreditos());
                            founded = true;
                            break;
                        }
                    }

                    if (founded) {
                        Toast.makeText(getApplicationContext(), " Just edited", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Sorry not founded", Toast.LENGTH_LONG).show();
                    }
                }
            } else {

                cursos_List.add(aux);
                Toast.makeText(getApplicationContext(), " Just added", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void goToAddUpdCurso() {
        Intent intent = new Intent(this, AddUpdCurso.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }


}//Cierre clase lista_Curso