package org.colibrisoft.colibri;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionsPagerAdapter extends FragmentPagerAdapter{
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
                case 0:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;

                case 1:
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;

                default:
                    return null;

        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Requests";

            case 1:
                return "Friends";

                default:
                    return null;
        }
    }
}
