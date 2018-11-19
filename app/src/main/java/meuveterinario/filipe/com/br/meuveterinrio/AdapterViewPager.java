package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPager extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();
    public AdapterViewPager(FragmentManager fm) {
        super(fm);
    }


    public void addFragmento(Fragment fragment){
        this.fragments.add(fragment);
    }
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);

    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
