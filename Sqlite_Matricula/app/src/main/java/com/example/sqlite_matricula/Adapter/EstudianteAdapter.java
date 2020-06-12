package com.example.sqlite_matricula.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_matricula.Model.Estudiante;
import com.example.sqlite_matricula.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class EstudianteAdapter
        extends RecyclerView.Adapter<EstudianteAdapter.MyViewHolder>
        implements Filterable {

    private List<Estudiante> carreraList;
    private List<Estudiante> carreraListFiltered;
    private EstudianteAdapterListener listener;
    private Estudiante deletedItem;

    public EstudianteAdapter(List<Estudiante> carreraList, EstudianteAdapterListener listener) {
        this.carreraList = carreraList;
        this.listener = listener;
        //init filter
        this.carreraListFiltered = carreraList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // basically a render
        final Estudiante carrera = carreraListFiltered.get(position);
        holder.title1.setText(carrera.get_id());
        holder.title2.setText(carrera.get_nombre());
        holder.description.setText(carrera.get_apellido());
    }

    @Override
    public int getItemCount() {
        return carreraListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = carreraListFiltered.remove(position);
        Iterator<Estudiante> iter = carreraList.iterator();
        while (iter.hasNext()) {
            Estudiante aux = iter.next();
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

    public Estudiante getSwipedItem(int index) {
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
                    List<Estudiante> filteredList = new ArrayList<>();
                    for (Estudiante row : carreraList) {
                        // filter use two parameters
                        if (row.get_apellido().toLowerCase().contains(charString.toLowerCase()) || row.get_nombre().toLowerCase().contains(charString.toLowerCase())) {
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
                carreraListFiltered = (ArrayList<Estudiante>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface EstudianteAdapterListener {
        void onContactSelected(Estudiante carrera);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1, title2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;
        public ImageView icono;
        public MyViewHolder(View view) {
            super(view);
            icono = view.findViewById(R.id.icono_);
            icono.setImageResource(R.drawable.ic_pag_estudiantes);
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
