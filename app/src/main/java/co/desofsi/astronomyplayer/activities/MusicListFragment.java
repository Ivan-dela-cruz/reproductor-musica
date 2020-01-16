package co.desofsi.astronomyplayer.activities;

import android.Manifest;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.desofsi.astronomyplayer.R;

public class MusicListFragment extends Fragment implements RecyclerCancionesAdapter.OnItemHomeClickListener {

    private MusicListViewModel ahorroViewModel;
    String[] items;
    private ArrayList<Cancion> songs;
    private ArrayList<File> my_songs_list;
    private Cancion cancion;
    private String name_song;
    private int position_selected = 0;

    RecyclerView recyclerView;
   public static MediaPlayer mediaPlayer;


    private ImageView btn_play, btn_pause, btn_next, btn_previ;
    private RelativeLayout linerlayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ahorroViewModel =
                ViewModelProviders.of(this).get(MusicListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_music_list, container, false);

        init(root);
        runtimePermission();


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPista();
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPist();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPist();
            }
        });
        btn_previ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previPist();
            }
        });


        return root;
    }


    public void runtimePermission() {
        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                display();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }

    public ArrayList<File> findSongs(File file) {

        ArrayList<File> arrayList = new ArrayList<>();

        File[] files = file.listFiles();

        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.addAll(findSongs(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    void display() {
        final ArrayList<File> my_songs = findSongs(Environment.getExternalStorageDirectory());
        songs = new ArrayList<>();

        for (int i = 0; i < my_songs.size(); i++) {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(my_songs.get(i).getPath());

            String albumName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String artista = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);


            String name = my_songs.get(i).getName().toString().replace(".mp3", "").replace(".wav", "");

            if (artista == null) {
                artista = "Desconocido";
            }
            if (albumName == null) {
                artista = "Desconocido";
            }
            Cancion cancion = new Cancion(i, name, artista, albumName, i);
            //items[i] = my_songs.get(i).getName().toString().replace(".mp3", "").replace(".wav", "");
            songs.add(cancion);
        }

        recyclerView.clearAnimation();
        RecyclerCancionesAdapter adapterTools = new RecyclerCancionesAdapter(songs, this);
        recyclerView.setAdapter(adapterTools);
        my_songs_list = my_songs;

    }

    public void init(View view) {

        linerlayout = (RelativeLayout) view.findViewById(R.id.linearLayout);
        btn_play = (ImageView) view.findViewById(R.id.btn_play);
        btn_pause = (ImageView) view.findViewById(R.id.btn_pause);
        btn_next = (ImageView) view.findViewById(R.id.btn_next);
        btn_previ = (ImageView) view.findViewById(R.id.btn_previus);




        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_canciones);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        my_songs_list = new ArrayList<>();

    }

    @Override
    public void onItemHomeClick(int position) {

        recyclerView.smoothScrollToPosition(position);
        position_selected = position;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        linerlayout.setVisibility(View.VISIBLE);
        name_song = my_songs_list.get(position).getName().toString();

        Uri u = Uri.parse(my_songs_list.get(position).toString());
        cancion = songs.get(position);


        mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), u);
        mediaPlayer.start();
        btn_play.setVisibility(View.GONE);
        btn_pause.setVisibility(View.VISIBLE);


    }

    public void playPista() {

        mediaPlayer.start();
        btn_play.setVisibility(View.GONE);
        btn_pause.setVisibility(View.VISIBLE);
    }

    public void stopPist() {
        mediaPlayer.pause();
        btn_play.setVisibility(View.VISIBLE);
        btn_pause.setVisibility(View.GONE);

    }

    public void nextPist() {
        position_selected++;

        if (position_selected < my_songs_list.size()) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            name_song = my_songs_list.get(position_selected).getName().toString();

            Uri u = Uri.parse(my_songs_list.get(position_selected).toString());

            cancion = songs.get(position_selected);
            mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), u);
            mediaPlayer.start();
            btn_play.setVisibility(View.GONE);
            btn_pause.setVisibility(View.VISIBLE);
        } else {
            position_selected--;
        }


    }

    public void previPist() {
        position_selected--;

        if (position_selected >= 0) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            name_song = my_songs_list.get(position_selected).getName().toString();

            Uri u = Uri.parse(my_songs_list.get(position_selected).toString());

            cancion = songs.get(position_selected);
            mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), u);
            mediaPlayer.start();
            btn_play.setVisibility(View.GONE);
            btn_pause.setVisibility(View.VISIBLE);
        } else {
            position_selected++;
        }
    }
}
