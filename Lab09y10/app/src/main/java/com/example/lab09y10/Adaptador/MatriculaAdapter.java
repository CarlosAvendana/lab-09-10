package com.example.lab09y10.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lab09y10.Model.Matricula;
import com.example.lab09y10.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MatriculaAdapter extends RecyclerView.Adapter<MatriculaAdapter.MyViewHolder> implements Filterable {

    private List<Matricula> carreraList;
    private List<Matricula> carreraListFiltered;
    private MatriculaAdapter.MatriculaAdapterListener listener;
    private Matricula deletedItem;

    public MatriculaAdapter(List<Matricula> carreraList, MatriculaAdapter.MatriculaAdapterListener listener) {
        this.carreraList = carreraList;
        this.listener = listener;
        //init filter
        this.carreraListFiltered = carreraList;
    }

    @Override
    public MatriculaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MatriculaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MatriculaAdapter.MyViewHolder holder, final int position) {
        // basically a render
        final Matricula carrera = carreraListFiltered.get(position);
        holder.title1.setText(carrera.getIdCurso());
        holder.title2.setText(carrera.getIdCurso());
        holder.description.setText(carrera.getIdEstudiante());
    }

    @Override
    public int getItemCount() {
        return carreraListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = carreraListFiltered.remove(position);
        Iterator<Matricula> iter = carreraList.iterator();
        while (iter.hasNext()) {
            Matricula aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (carreraListFiltered.size() == carreraList.size()) {
            carreraListFiltered.add(position, deletedItem);
        } else {
            carreraListFiltered.add(position, deletedItem);
            carreraList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Matricula getSwipedItem(int index) {
        if (this.carreraList.size() == this.carreraListFiltered.size()) { //not filtered yet
            return carreraList.get(index);
        } else {
            return carreraListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (carreraList.size() == carreraListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(carreraList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(carreraList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(carreraListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(carreraListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    carreraListFiltered = carreraList;
                } else {
                    List<Matricula> filteredList = new ArrayList<>();
                    for (Matricula row : carreraList) {
                        // filter use two parameters
                        if (row.getIdEstudiante().toLowerCase().contains(charString.toLowerCase()) || row.getIdCurso().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    carreraListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = carreraListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                carreraListFiltered = (ArrayList<Matricula>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface MatriculaAdapterListener {
        void onContactSelected(Matricula carrera);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1, title2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View view) {
            super(view);
            title1 = view.findViewById(R.id.titleFirstLbl);
            title2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(carreraListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

}
