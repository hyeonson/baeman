package project.hs.baeman.Adpater;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import project.hs.baeman.Fragment.InfoFragment;
import project.hs.baeman.Fragment.MenuFragment;

public class DetailPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_NUMBER = 2;

    public DetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MenuFragment.newInstance();
            case 1:
                return InfoFragment.newInstance();
            default:
                return null;

        }
    }


    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "메뉴";
            case 1:
                return "정보";
            default:
                return null;
        }
    }
}
