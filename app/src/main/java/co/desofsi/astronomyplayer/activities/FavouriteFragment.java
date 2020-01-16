package co.desofsi.astronomyplayer.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import co.desofsi.astronomyplayer.R;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel ahorroViewModel;


    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ahorroViewModel =
                ViewModelProviders.of(this).get(FavouriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);

        init(root);


        return root;
    }

    public void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_canciones);
    }

}
