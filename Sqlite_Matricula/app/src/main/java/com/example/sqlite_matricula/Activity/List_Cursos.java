package com.example.sqlite_matricula.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import com.example.sqlite_matricula.Adapter.CursoAdapter;
import com.example.sqlite_matricula.DatosPrueba.ModelData;
import com.example.sqlite_matricula.Helper.RecyclerItemTouchHelper;
import com.example.sqlite_matricula.Model.Curso;
import com.example.sqlite_matricula.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class List_Cursos extends AppCompatActivity
        implements CursoAdapter.CursoAdapterListener,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private RecyclerView mRecyclerView;
    private CursoAdapter mAdapter;
    private List<Curso> cursoList;
    private SearchView searchView;
    private ModelData model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__cursos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cursos");

        mRecyclerView = findViewById(R.id.recycler_cursosFld);
        cursoList = new ArrayList<>();

        model = ModelData.getInstance();
        cursoList = model.getCursoList();
        mAdapter = new CursoAdapter(cursoList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sent_To_AddUpdCurso();
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        checkIntentInformation();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onContactSelected(Curso carrera) {

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof CursoAdapter.MyViewHolder) {
                String name = cursoList.get(viewHolder.getAdapterPosition()).getId();
                final int deletedIndex = viewHolder.getAdapterPosition();
                mAdapter.removeItem(viewHolder.getAdapterPosition());
                Toast.makeText(getApplicationContext(), name + " removido!", Toast.LENGTH_LONG).show();
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
                    for (Curso carrera : cursoList) {
                        if (carrera.getId().equals(aux.getId())) {
                            carrera.setCreditos(aux.getCreditos());
                            carrera.setDescripcion(aux.getDescripcion());
                            founded = true;
                            break;
                        }
                    }
                    //check if exist
                    if (founded) {
                        Toast.makeText(getApplicationContext(), aux.getId() + " editado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), aux.getId() + " no encontrado", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //found a new Carrera Object
                cursoList.add(aux);
                Toast.makeText(getApplicationContext(), aux.getId() + " agregado correctamente!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void sent_To_AddUpdCurso() {
        Intent intent = new Intent(this, AddUpdCurso.class);
        intent.putExtra("editable", false);
        startActivity(intent);
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

}//Cierre clase list_Cursos