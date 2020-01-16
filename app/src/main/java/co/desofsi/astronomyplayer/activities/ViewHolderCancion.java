package co.desofsi.astronomyplayer.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import co.desofsi.astronomyplayer.R;
import co.desofsi.astronomyplayer.activities.RecyclerCancionesAdapter;


public class ViewHolderCancion extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView text_cate_name;
    private TextView text_saldo;
    private ImageView img_lista;

    RecyclerCancionesAdapter.OnItemHomeClickListener mylistener;


    public ViewHolderCancion(View itemView, final RecyclerCancionesAdapter.OnItemHomeClickListener listener) {
        super(itemView);
        text_cate_name = (TextView) itemView.findViewById(R.id.txt_nombre);
        text_saldo = (TextView) itemView.findViewById(R.id.txt_cantante);
        mylistener = listener;

        itemView.setOnClickListener(this);
    }


    public void setDescription(String name) {
        text_cate_name.setText(name);
    }

    public void setValor(String valor) {
        text_saldo.setText(valor);
    }

    @Override
    public void onClick(View v) {
        mylistener.onItemHomeClick(getAdapterPosition());
    }
}
