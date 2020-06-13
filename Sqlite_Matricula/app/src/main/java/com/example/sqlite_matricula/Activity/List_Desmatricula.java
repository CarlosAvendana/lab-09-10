package com.example.sqlite_matricula.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;

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

public class List_Desmatricula extends AppCompatActivity
        implements MatriculaAdapter.MatriculaAdapterListener,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private RecyclerView mRecyclerView;
    private MatriculaAdapter mAdapter;
    private List<Matricula> estudianteList;
    private SearchView searchView;
    private ModelData model;
    private EditText campoTextoIdDesmatricular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__desmatricula);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Desmatricular");

        campoTextoIdDesmatricular = (EditText) findViewById(R.id.campoADesmatricularFLD);
        campoTextoIdDesmatricular.setEnabled(false);

        mRecyclerView = findViewById(R.id.recycler_desmatriculaFld);
        estudianteList = new ArrayList<>();

        // model = ModelData.getInstance();
        // estudianteList = model.getMatriculaList();

        mAdapter = new MatriculaAdapter(estudianteList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);


        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onContactSelected(Matricula carrera) {
        campoTextoIdDesmatricular.setText(carrera.getIdCurso());
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

}// cierre de la clase lista desmatricula