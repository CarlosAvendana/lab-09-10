package com.example.sqlite_matricula.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_matricula.Adapter.MatriculaAdapter;
import com.example.sqlite_matricula.DatosPrueba.ModelData;
import com.example.sqlite_matricula.Helper.RecyclerItemTouchHelper;
import com.example.sqlite_matricula.Model.Matricula;
import com.example.sqlite_matricula.R;

import java.util.ArrayList;
import java.util.List;

public class List_Matricula extends AppCompatActivity
        implements MatriculaAdapter.MatriculaAdapterListener,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private RecyclerView mRecyclerView;
    private MatriculaAdapter mAdapter;
    private List<Matricula> estudianteList;
    private SearchView searchView;
    private ModelData model;
    private EditText campoTextoIdMatricular;
    private ImageButton bntEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__matricula);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Matricular");

        campoTextoIdMatricular = (EditText) findViewById(R.id.editTextTextPersonName2);
        campoTextoIdMatricular.setEnabled(false);

        mRecyclerView = findViewById(R.id.recycler_matriculaFld);
        estudianteList = new ArrayList<>();

        bntEnviar = findViewById(R.id.botonMatricular);

        //
        model = new ModelData(List_Matricula.this);
        estudianteList = model.getMatriculasNoEst(ModelData.cedula);
        mAdapter = new MatriculaAdapter(estudianteList, this);
        //

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        mAdapter.notifyDataSetChanged();
        bntEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matricular();
            }
        });

    }

    @Override
    public void onContactSelected(Matricula carrera) {
        campoTextoIdMatricular.setText(carrera.getIdCurso());
        //Toast.makeText(this, carrera.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

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
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
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

    public void matricular() {
        if (!TextUtils.isEmpty(campoTextoIdMatricular.getText())) {
            model.addMatricula(new Matricula(model.getCedula(), campoTextoIdMatricular.getText().toString()));
            model = new ModelData(List_Matricula.this);
            estudianteList = model.getMatriculasNoEst(ModelData.cedula);
            mAdapter = new MatriculaAdapter(estudianteList, this);
            mRecyclerView.setAdapter(mAdapter);
            Toast.makeText(this, "Curso matriculado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Seleccione un curso", Toast.LENGTH_SHORT).show();
        }
    }


}