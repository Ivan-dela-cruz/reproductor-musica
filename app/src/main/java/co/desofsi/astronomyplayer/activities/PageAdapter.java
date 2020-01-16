package co.desofsi.astronomyplayer.activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import co.desofsi.astronomyplayer.activities.FavouriteFragment;
import co.desofsi.astronomyplayer.activities.MusicListFragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    int counttab;

    public PageAdapter(@NonNull FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MusicListFragment musicListFragment = new MusicListFragment();
                return musicListFragment;
            case 1:
                FavouriteFragment favouriteFragment = new FavouriteFragment();
                return favouriteFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
