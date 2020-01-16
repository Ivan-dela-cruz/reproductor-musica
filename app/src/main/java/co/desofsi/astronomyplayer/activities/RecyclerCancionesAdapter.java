package co.desofsi.astronomyplayer.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.desofsi.astronomyplayer.R;


public class RecyclerCancionesAdapter extends RecyclerView.Adapter<ViewHolderCancion> {
    private ArrayList<Cancion> listDatos;
    private OnItemHomeClickListener myListener;

    public interface OnItemHomeClickListener {
        void onItemHomeClick(int position);
    }

    public void setOnItemClickListener(OnItemHomeClickListener listener) {
        myListener = listener;
    }

    public RecyclerCancionesAdapter(ArrayList<Cancion> listDatos, OnItemHomeClickListener listener) {
        this.listDatos = listDatos;
        this.myListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderCancion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycler_song, parent, false);
        return new ViewHolderCancion(view, myListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCancion holder, int position) {
        holder.setDescription(listDatos.get(position).getNombre());
        holder.setValor(String.valueOf(listDatos.get(position).getCantante()));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }
}

