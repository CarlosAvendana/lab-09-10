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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab09y10.Adaptador.EstudianteAdapter;
import com.example.lab09y10.Helper.RecyclerItemTouchHelper;
import com.example.lab09y10.Model.Estudiante;
import com.example.lab09y10.R;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements EstudianteAdapter.EstudianteAdapterListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private RecyclerView mRecyclerView;
    private EstudianteAdapter mAdapter;
    private List<Estudiante> formList;
    //private ModelData model;
    private SearchView searchView;

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.parseColor("#455A64"));
        }
    }

    @Override
    public void onContactSelected(Estudiante form) {
        Toast.makeText(getApplicationContext(), "Selected", Toast.LENGTH_LONG).show();
    }

    public void goToAddUpdEstudiante() {
        Intent intent = new Intent(this, AddUpdEstudiante.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof EstudianteAdapter.MyViewHolder) {
                String name = formList.get(viewHolder.getAdapterPosition()).get_nombre();
                final int deletedIndex = viewHolder.getAdapterPosition();
                mAdapter.removeItem(viewHolder.getAdapterPosition());
                Toast.makeText(getApplicationContext(), name + " removido!", Toast.LENGTH_LONG).show();
            }
        } else {

            Estudiante aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            Intent intent = new Intent(this, AddUpdEstudiante.class);
            intent.putExtra("editable", true);
            intent.putExtra("Form", aux);
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
            Estudiante aux;
            aux = (Estudiante) getIntent().getSerializableExtra("addForm");
            if (aux == null) {
                aux = (Estudiante) getIntent().getSerializableExtra("editForm");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Estudiante carrera : formList) {
                        if (carrera.get_nombre().equals(aux.get_nombre())) {
                            carrera.set_anios(aux.get_anios());
                            carrera.set_apellido(aux.get_apellido());
                            carrera.set_id(aux.get_id());

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

                formList.add(aux);
                Toast.makeText(getApplicationContext(), " Just added", Toast.LENGTH_LONG).show();
            }
        }
    }


}//Cierre clase main