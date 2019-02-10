package project.hs.baeman.Adpater;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import project.hs.baeman.Fragment.PageFiveFragment;
import project.hs.baeman.Fragment.PageFourFragment;
import project.hs.baeman.Fragment.PageOneFragment;
import project.hs.baeman.Fragment.PageThreeFragment;
import project.hs.baeman.Fragment.PageTwoFragment;

public class MenuPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_NUMBER = 9;

    public MenuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PageOneFragment.newInstance();
            case 1:
                return PageTwoFragment.newInstance();
            case 2:
                return PageThreeFragment.newInstance();
            case 3:
                return PageFourFragment.newInstance();
            case 4:
                return PageFiveFragment.newInstance();
            case 5:
                return PageFiveFragment.newInstance();
            case 6:
                return PageFiveFragment.newInstance();
            case 7:
                return PageFiveFragment.newInstance();
            case 8:
                return PageFiveFragment.newInstance();
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
                return "한식";
            case 1:
                return "분식";
            case 2:
                return "돈까스,회,일식";
            case 3:
                return "치킨";
            case 4:
                return "피자";
            case 5:
                return "중국집";
            case 6:
                return "족발,보쌈";
            case 7:
                return "야식";
            case 8:
                return "찜,탕";
            default:
                return null;
        }
    }
}
